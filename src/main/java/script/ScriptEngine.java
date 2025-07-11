package script;

import java.util.Vector;

public final class ScriptEngine {
   public CommandRegistry commandRegistry;
   public ScriptEngine parentEngine;
   public String engineName = "";
   public Vector<VariableReference> variableReferences;
   public Object[] dataStack;
   public int dataStackSize;
   private ScriptContext[] contextStack;
   private int contextStackSize;
   public ScriptVM vm;

   public ScriptEngine(ScriptVM vm, CommandRegistry commandRegistry) {
      this.vm = vm;
      this.commandRegistry = commandRegistry;
      this.dataStack = new Object[10];
      this.contextStack = new ScriptContext[10];
      this.variableReferences = new Vector<>();
   }

   public ScriptContext pushContext(ScriptFunction function, int stackBase, int localBase,
                                    int paramCount, boolean isActive, boolean isRunning) throws Exception {
      this.setContextStackSize(this.contextStackSize + 1);

      ScriptContext context = this.getCurrentContext();
      configureContext(context, function, stackBase, localBase, paramCount, isActive, isRunning);

      return context;
   }

   private void configureContext(ScriptContext context, ScriptFunction function, int stackBase,
                                 int localBase, int paramCount, boolean isActive, boolean isRunning) {
      context.stackBaseOffset = stackBase;
      context.localVariable = localBase;
      context.parameterCount = paramCount;
      context.isActive = isActive;
      context.isRunning = isRunning;
      context.currentFunction = function;
   }

   public void popContext() throws Exception {
      if (this.isContextStackEmpty()) {
         throw new Exception("Stack underflow");
      }

      this.setContextStackSize(this.contextStackSize - 1);
   }

   private void setContextStackSize(int newSize) throws Exception {
      if (newSize > this.contextStackSize) {
         expandContextStack(newSize);
      } else {
         shrinkContextStack(newSize);
      }

      this.contextStackSize = newSize;
   }

   private void expandContextStack(int newSize) throws Exception {
      if (newSize > 100) {
         throw new Exception("Stack overflow");
      }

      int targetSize = calculateExpandedSize(this.contextStack.length, newSize);
      if (targetSize > this.contextStack.length) {
         reallocateContextStack(targetSize);
      }
   }

   private void shrinkContextStack(int newSize) {
      this.clearContextRange(newSize, this.contextStackSize - 1);
   }

   private int calculateExpandedSize(int currentSize, int requiredSize) {
      int targetSize = currentSize;
      while (targetSize <= requiredSize) {
         targetSize <<= 1;
      }
      return targetSize;
   }

   private void reallocateContextStack(int newSize) {
      ScriptContext[] newStack = new ScriptContext[newSize];
      System.arraycopy(this.contextStack, 0, newStack, 0, this.contextStack.length);
      this.contextStack = newStack;
   }

   private void clearContextRange(int startIndex, int endIndex) {
      for (int i = startIndex; i <= endIndex; i++) {
         if (this.contextStack[i] != null) {
            this.contextStack[i].currentFunction = null;
         }
      }
   }

   public final void ensureDataStackCapacity(int requiredSize) throws Exception {
      if (this.dataStackSize < requiredSize) {
         expandDataStack(requiredSize);
      } else {
         shrinkDataStack(requiredSize);
      }

      this.dataStackSize = requiredSize;
   }

   private void expandDataStack(int requiredSize) throws Exception {
      if (requiredSize > 1000) {
         throw new Exception("Stack overflow");
      }

      int targetSize = calculateExpandedSize(this.dataStack.length, requiredSize);
      if (targetSize > this.dataStack.length) {
         reallocateDataStack(targetSize);
      }
   }

   private void shrinkDataStack(int requiredSize) {
      this.clearDataRange(requiredSize, this.dataStackSize - 1);
   }

   private void reallocateDataStack(int newSize) {
      Object[] newStack = new Object[newSize];
      System.arraycopy(this.dataStack, 0, newStack, 0, this.dataStack.length);
      this.dataStack = newStack;
   }

   public void copyDataRange(int fromIndex, int toIndex, int count) {
      boolean shouldCopy = count > 0 && fromIndex != toIndex;
      if (shouldCopy) {
         System.arraycopy(this.dataStack, fromIndex, this.dataStack, toIndex, count);
      }
   }

   private void clearDataRange(int startIndex, int endIndex) {
      for (int i = startIndex; i <= endIndex; i++) {
         this.dataStack[i] = null;
      }
   }

   public void shrinkDataStackAndUpdateReferences(int newSize) {
      int refIndex = this.variableReferences.size();

      while (--refIndex >= 0) {
         VariableReference varRef = (VariableReference) this.variableReferences.elementAt(refIndex);

         if (varRef.stackIndex < newSize) {
            return;
         }

         // Move reference from stack to local storage
         varRef.localValue = this.dataStack[varRef.stackIndex];
         varRef.engine = null;
         this.variableReferences.removeElementAt(refIndex);
      }
   }

   public ScriptContext getCurrentContext() {
      if (this.isContextStackEmpty()) {
         return null;
      }

      return getOrCreateContext(this.contextStackSize - 1);
   }

   private ScriptContext getOrCreateContext(int index) {
      ScriptContext context = this.contextStack[index];

      if (context == null) {
         context = new ScriptContext(this);
         this.contextStack[index] = context;
      }

      return context;
   }

   public ScriptContext getContextAtLevel(int level) throws Exception {
      StandardFunctions.assertError(level >= 0, "Level must be non-negative");

      int actualLevel = this.contextStackSize - level - 1;
      StandardFunctions.assertError(actualLevel >= 0, "Level too high");

      return this.contextStack[actualLevel];
   }

   public boolean isContextStackEmpty() {
      return this.contextStackSize == 0;
   }
}