package a.b;

import javax.microedition.rms.RecordStore;
import java.io.ByteArrayOutputStream;

/**
 * Manager class for handling RecordStore operations in J2ME applications
 * Provides convenient methods for reading, writing, and managing persistent data
 */
public final class RecordStoreManager {
    private String storeName;                    // C59_f851 - Name of the record store
    private RecordStore recordStore;             // C59_f852 - The actual record store instance
    private int recordCount;                     // C59_f853 - Number of records in store
    private int nextRecordId = 1;               // C59_f854 - Next available record ID

    /**
     * Create a new RecordStore manager
     *
     * @param storeName  Name of the record store to manage
     * @param numRecords Number of empty records to create
     */
    public RecordStoreManager(String storeName, int numRecords) {
        this.storeName = storeName;

        // Initialize the record store
        if (this.getRecordCount() == 0) {
            this.initializeEmptyRecords(numRecords);
        } else {
            if (this.getRecordCount() != numRecords) {
                this.deleteStore();
                this.initializeEmptyRecords(numRecords);
            }
        }
    }

    /**
     * Initialize the record store with empty records
     *
     * @param numRecords Number of empty records to create
     */
    private void initializeEmptyRecords(int numRecords) {
        try {
            byte[] emptyRecord = new byte[]{0};

            for (int i = 0; i < numRecords; i++) {
                int dataLength = emptyRecord.length;
                this.performRecordOperation(this.nextRecordId, emptyRecord, 0, dataLength, RecordOperation.ADD_RECORD);
            }
        } catch (Exception e) {
            // Silently handle initialization errors
        }
    }

    /**
     * Get the current number of records in the store
     *
     * @return Number of records
     */
    private int getRecordCount() {
        this.performRecordOperation(this.nextRecordId, null, 0, 0, RecordOperation.GET_COUNT);
        return this.recordCount;
    }

    /**
     * Read data from the record store
     *
     * @return Byte array containing the stored data (without the version byte)
     */
    public final byte[] readData() {
        byte[] rawData = this.performRecordOperation(1, null, 0, 0, RecordOperation.GET_RECORD);

        if (rawData == null || rawData.length <= 1) {
            return new byte[0];
        }

        // Remove the version byte (first byte) and return actual data
        byte[] userData = new byte[rawData.length - 1];
        System.arraycopy(rawData, 1, userData, 0, userData.length);
        this.closeStore();
        return userData;
    }

    /**
     * Write data to the record store
     *
     * @param dataStream ByteArrayOutputStream containing the data to write
     */
    public final void writeData(ByteArrayOutputStream dataStream) {
        byte[] sourceData = dataStream.toByteArray();

        // Add version byte (1) at the beginning
        byte[] recordData = new byte[sourceData.length + 1];
        System.arraycopy(sourceData, 0, recordData, 1, sourceData.length);
        recordData[0] = 1; // Version marker

        int dataLength = recordData.length;
        this.performRecordOperation(this.nextRecordId, recordData, 0, dataLength, RecordOperation.SET_RECORD);
        this.closeStore();
    }

    /**
     * Close the record store
     */
    private void closeStore() {
        this.performRecordOperation(0, null, 0, 0, RecordOperation.CLOSE_STORE);
    }

    /**
     * Delete the entire record store
     */
    public final void deleteStore() {
        this.performRecordOperation(0, null, 0, 0, RecordOperation.DELETE_STORE);
    }

    /**
     * Enumeration for different record store operations
     */
    private static class RecordOperation {
        public static final int GET_COUNT = 0;      // Get number of records
        public static final int ADD_RECORD = 1;     // Add new record
        public static final int DELETE_RECORD = 2;  // Delete existing record
        public static final int GET_RECORD = 3;     // Read record data
        public static final int SET_RECORD = 4;     // Update record data
        public static final int CLOSE_STORE = 6;    // Close record store
        public static final int DELETE_STORE = 7;   // Delete entire store
    }

    /**
     * Perform a record store operation
     *
     * @param recordId  ID of the record to operate on
     * @param data      Data to write (for write operations)
     * @param offset    Offset in data array
     * @param length    Length of data to process
     * @param operation Type of operation to perform
     * @return Data read from store (for read operations) or null
     */
    private byte[] performRecordOperation(int recordId, byte[] data, int offset, int length, int operation) {
        try {
            // Handle store cleanup operations
            if (operation == RecordOperation.DELETE_STORE || operation == RecordOperation.CLOSE_STORE) {
                if (this.recordStore != null) {
                    this.recordStore.closeRecordStore();
                    this.recordStore = null;
                }

                if (operation == RecordOperation.DELETE_STORE) {
                    RecordStore.deleteRecordStore(this.storeName);
                }

                return null;
            }

            // Open store if not already open
            if (this.recordStore == null) {
                this.recordStore = RecordStore.openRecordStore(this.storeName, true);
            }

            // Perform the requested operation
            switch (operation) {
                case RecordOperation.GET_COUNT:
                    this.recordCount = this.recordStore.getNumRecords();
                    break;

                case RecordOperation.ADD_RECORD:
                    this.recordStore.addRecord(data, offset, length);
                    break;

                case RecordOperation.DELETE_RECORD:
                    this.recordStore.deleteRecord(recordId);
                    break;

                case RecordOperation.GET_RECORD:
                    return this.recordStore.getRecord(recordId);

                case RecordOperation.SET_RECORD:
                    this.recordStore.setRecord(recordId, data, offset, length);
                    break;

                default:
                    // Unknown operation
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Check if the record store exists and is accessible
     *
     * @return true if store is accessible
     */
    public final boolean isStoreAccessible() {
        try {
            int currentCount = getRecordCount();
            return currentCount >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get the name of this record store
     *
     * @return Store name
     */
    public final String getStoreName() {
        return this.storeName;
    }

    /**
     * Get the next available record ID
     *
     * @return Next record ID
     */
    public final int getNextRecordId() {
        return this.nextRecordId;
    }

    /**
     * Set the next record ID (for advanced usage)
     *
     * @param recordId New record ID to use
     */
    public final void setNextRecordId(int recordId) {
        this.nextRecordId = recordId;
    }

    /**
     * Check if the record store is currently open
     *
     * @return true if store is open
     */
    public final boolean isStoreOpen() {
        return this.recordStore != null;
    }

    /**
     * Force close and cleanup all resources
     */
    public final void cleanup() {
        try {
            if (this.recordStore != null) {
                this.recordStore.closeRecordStore();
                this.recordStore = null;
            }
        } catch (Exception e) {
            // Ignore cleanup errors
        }
    }

    /**
     * Get current record count without side effects
     *
     * @return Number of records in store
     */
    public final int getCurrentRecordCount() {
        return this.recordCount;
    }
}