package script;

import java.lang.ref.WeakReference;

public class CommandRegistry {
    private Object[] keys;
    private Object[] values;
    private int[] chains;
    private int nextFreeSlot;
    private boolean useWeakKeys;
    private boolean useWeakValues;

    public void put(Object key, Object value) throws Exception {
        validateKey(key);

        int hash = this.hash(key);
        int keyIndex = this.findKeyIndex(key, hash);

        if (keyIndex < 0) {
            keyIndex = this.insertKey(key, hash);
        }

        Object wrappedValue = this.useWeakValues ? wrapWeakReference(value) : value;
        this.values[keyIndex] = wrappedValue;
    }

    public Object get(Object key) throws Exception {
        validateKey(key);

        int hash = this.hash(key);
        int keyIndex = this.findKeyIndex(key, hash);

        return keyIndex >= 0 ? this.getValueAt(keyIndex) : null;
    }

    public Object get(int intKey) throws Exception {
        return this.get(ScriptVM.createInteger(intKey));
    }

    public Object getNextKey(Object currentKey) throws Exception {
        int searchIndex = 0;

        if (currentKey != null) {
            int hash = this.hash(currentKey);
            searchIndex = 1 + this.findKeyIndex(currentKey, hash);
            StandardFunctions.assertError(searchIndex > 0, "invalid key to 'next'");
        }

        while (searchIndex < this.keys.length) {
            Object foundKey = this.getKeyAt(searchIndex);
            boolean hasValidEntry = foundKey != null && this.getValueAt(searchIndex) != null;

            if (hasValidEntry) {
                return foundKey;
            }
            searchIndex++;
        }

        return null;
    }

    public int getArraySize() throws Exception {
        int maxIndex = this.keys.length;
        int minIndex = 0;

        while (minIndex < maxIndex) {
            int midIndex = (maxIndex + minIndex + 1) >> 1;
            Integer testKey = ScriptVM.createInteger(midIndex);

            if (this.get(testKey) == null) {
                maxIndex = midIndex - 1;
            } else {
                minIndex = midIndex;
            }
        }

        return minIndex;
    }

    private static int nextPowerOfTwo(int size) {
        if (size < 4) {
            return 4;
        }

        size--;
        size |= size >> 1;
        size |= (size >> 1) >> 2;
        size |= size >> 4;
        size |= (size >> 4) >> 8;
        size |= ((size >> 4) >> 8) >> 16;

        return size + 1;
    }

    public CommandRegistry() {
        int initialSize = nextPowerOfTwo(4);
        this.keys = new Object[initialSize];
        this.values = new Object[initialSize];
        this.chains = new int[initialSize];
        this.nextFreeSlot = initialSize;
    }

    private int hash(Object key) {
        int arrayLength = this.keys.length;
        int hashCode = getObjectHashCode(key);
        return hashCode & (arrayLength - 1);
    }

    private int getObjectHashCode(Object key) {
        if (key instanceof Integer) {
            Integer intKey = (Integer) key;
            return intKey == 0 ? 0 : intKey.hashCode();
        }

        if (key instanceof String) {
            return key.hashCode();
        }

        return System.identityHashCode(key);
    }

    private static Object unwrapWeakReference(Object obj) {
        return shouldUseWeakReference(obj) ? ((WeakReference) obj).get() : obj;
    }

    private static Object wrapWeakReference(Object obj) {
        return shouldUseWeakReference(obj) ? new WeakReference(obj) : obj;
    }

    private static boolean shouldUseWeakReference(Object obj) {
        if (obj == null) return false;
        if (obj instanceof String) return false;
        if (obj instanceof Integer) return false;
        if (obj instanceof Boolean) return false;
        if (obj instanceof NativeFunction) return false;
        return true;
    }

    private Object getKeyAt(int index) {
        Object key = this.keys[index];
        return this.useWeakKeys ? unwrapWeakReference(key) : key;
    }

    private void setKeyAt(int index, Object key) {
        Object keyToStore = this.useWeakKeys ? wrapWeakReference(key) : key;
        this.keys[index] = keyToStore;
    }

    private Object getValueAt(int index) {
        Object value = this.values[index];
        return this.useWeakValues ? unwrapWeakReference(value) : value;
    }

    private int findKeyIndex(Object targetKey, int hash) {
        Object currentKey = this.getKeyAt(hash);
        if (currentKey == null) {
            return -1;
        }

        if (targetKey instanceof Integer) {
            return findIntegerKey((Integer) targetKey, hash, currentKey);
        }

        if (targetKey instanceof String) {
            return findStringKey(targetKey, hash, currentKey);
        }

        return findObjectKey(targetKey, hash, currentKey);
    }

    private int findIntegerKey(Integer targetValue, int hash, Object currentKey) {
        while (true) {
            if (currentKey instanceof Integer) {
                Integer currentValue = (Integer) currentKey;
                if (targetValue.equals(currentValue)) {
                    return hash;
                }
            }

            hash = this.chains[hash];
            if (hash == -1) {
                return -1;
            }

            currentKey = this.getKeyAt(hash);
        }
    }

    private int findStringKey(Object targetKey, int hash, Object currentKey) {
        while (!targetKey.equals(currentKey)) {
            hash = this.chains[hash];
            if (hash == -1) {
                return -1;
            }

            currentKey = this.getKeyAt(hash);
        }
        return hash;
    }

    private int findObjectKey(Object targetKey, int hash, Object currentKey) {
        while (targetKey != currentKey) {
            hash = this.chains[hash];
            if (hash == -1) {
                return -1;
            }

            currentKey = this.getKeyAt(hash);
        }
        return hash;
    }

    private int insertKey(Object key, int hash) throws Exception {
        Object existingKey;

        while ((existingKey = this.getKeyAt(hash)) != null) {
            if (needsTableResize()) {
                resizeTable();
                hash = this.hash(key);
                continue;
            }

            int existingHash = this.hash(existingKey);
            if (existingHash == hash) {
                return insertInChain(key, hash);
            }

            return relocateAndInsert(key, hash, existingHash);
        }

        // Insert at empty slot
        this.setKeyAt(hash, key);
        this.chains[hash] = -1;
        return hash;
    }

    private boolean needsTableResize() {
        try {
            while (this.getKeyAt(--this.nextFreeSlot) != null) {
                // Continue searching for free slot
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    private void resizeTable() throws Exception {
        // Save current state
        boolean originalWeakKeys = this.useWeakKeys;
        boolean originalWeakValues = this.useWeakValues;
        Object[] oldKeys = this.keys;
        Object[] oldValues = this.values;
        int oldLength = oldKeys.length;

        // Disable weak references temporarily
        this.setWeakReferenceMode(false, false);

        // Count valid entries
        int entryCount = countValidEntries(oldKeys, oldValues);

        // Create new table
        int newCapacity = 2 * nextPowerOfTwo(entryCount);
        this.keys = new Object[newCapacity];
        this.values = new Object[newCapacity];
        this.chains = new int[newCapacity];
        this.nextFreeSlot = newCapacity;

        // Restore data
        restoreEntries(oldKeys, oldValues, oldLength);

        // Restore weak reference mode
        this.setWeakReferenceMode(originalWeakKeys, originalWeakValues);
    }

    private int countValidEntries(Object[] oldKeys, Object[] oldValues) {
        int entryCount = 0;
        for (int i = oldKeys.length - 1; i >= 0; i--) {
            if (oldKeys[i] != null && oldValues[i] != null) {
                entryCount++;
            }
        }
        return entryCount;
    }

    private void restoreEntries(Object[] oldKeys, Object[] oldValues, int oldLength) throws Exception {
        for (int i = oldLength - 1; i >= 0; i--) {
            Object oldKey = oldKeys[i];
            Object oldValue = oldValues[i];

            if (oldKey != null && oldValue != null) {
                this.put(oldKey, oldValue);
            }
        }
    }

    private int insertInChain(Object key, int hash) {
        this.setKeyAt(this.nextFreeSlot, key);
        this.chains[this.nextFreeSlot] = this.chains[hash];
        this.chains[hash] = this.nextFreeSlot;
        return this.nextFreeSlot;
    }

    private int relocateAndInsert(Object key, int hash, int existingHash) {
        // Move existing entry to free slot
        this.keys[this.nextFreeSlot] = this.keys[hash];
        this.values[this.nextFreeSlot] = this.values[hash];
        this.chains[this.nextFreeSlot] = this.chains[hash];

        // Insert new key at current position
        this.setKeyAt(hash, key);
        this.chains[hash] = -1;

        // Update chain references
        updateChainReferences(existingHash, hash);

        return hash;
    }

    private void updateChainReferences(int existingHash, int hash) {
        int chainIndex = existingHash;
        while (this.chains[chainIndex] != hash) {
            chainIndex = this.chains[chainIndex];
        }
        this.chains[chainIndex] = this.nextFreeSlot;
    }

    private static void validateKey(Object key) throws Exception {
        StandardFunctions.assertError(key != null, "table index is nil");
    }

    public void setWeakReferenceMode(boolean useWeakKeys, boolean useWeakValues) {
        if (useWeakKeys != this.useWeakKeys) {
            this.convertArrayWeakMode(this.keys, useWeakKeys);
            this.useWeakKeys = useWeakKeys;
        }

        if (useWeakValues != this.useWeakValues) {
            this.convertArrayWeakMode(this.values, useWeakValues);
            this.useWeakValues = useWeakValues;
        }
    }

    private void convertArrayWeakMode(Object[] array, boolean useWeak) {
        for (int i = array.length - 1; i >= 0; i--) {
            Object element = array[i];
            array[i] = useWeak ? wrapWeakReference(element) : unwrapWeakReference(element);
        }
    }
}