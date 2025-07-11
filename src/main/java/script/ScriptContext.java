package script;

public final class ScriptContext {
    public ScriptEngine scriptEngine;
    public ScriptFunction currentFunction;
    public int programCounter;
    public int stackBaseOffset;
    int localVariable;
    public int parameterCount;
    boolean isActive;
    public boolean isRunning;
    boolean hasError;

    public ScriptContext(ScriptEngine scriptEngine) {
        this.scriptEngine = scriptEngine;
    }

    public final void setStackValue(int index, Object value) {
        this.scriptEngine.dataStack[this.stackBaseOffset + index] = value;
    }

    public final Object getStackValue(int index) {
        return this.scriptEngine.dataStack[this.stackBaseOffset + index];
    }

    public final int pushValue(Object value) throws Exception {
        int stackSize = this.getStackSize();
        this.ensureStackCapacity(stackSize + 1);
        this.setStackValue(stackSize, value);
        return 1;
    }

    public final void copyStackRange(int fromIndex, int toIndex, int count) {
        this.scriptEngine.copyDataRange(this.stackBaseOffset + fromIndex, this.stackBaseOffset + toIndex, count);
    }

    public final void clearStackRange(int startIndex, int endIndex) {
        while (startIndex <= endIndex) {
            this.scriptEngine.dataStack[this.stackBaseOffset + startIndex] = null;
            ++startIndex;
        }
    }

    public final void removeTopElements(int keepCount) throws Exception {
        if (this.getStackSize() < keepCount) {
            this.ensureStackCapacity(keepCount);
        }
        this.clearStackRange(keepCount, this.getStackSize() - 1);
    }

    public final void ensureStackCapacity(int requiredSize) throws Exception {
        this.scriptEngine.ensureDataStackCapacity(this.stackBaseOffset + requiredSize);
    }

    public final void shrinkStack(int newSize) {
        this.scriptEngine.shrinkDataStackAndUpdateReferences(this.stackBaseOffset + newSize);
    }

    public final VariableReference getLocalVariable(int index) {
        ScriptEngine engine = this.scriptEngine;
        int stackIndex = this.stackBaseOffset + index;
        int refCount = engine.variableReferences.size();

        while (--refCount >= 0) {
            VariableReference varRef;
            if ((varRef = engine.variableReferences.elementAt(refCount)).stackIndex == stackIndex) {
                return varRef;
            }
            if (varRef.stackIndex >= stackIndex) {
                continue;
            }
            break;
        }

        VariableReference newRef = new VariableReference();
        newRef.engine = engine;
        newRef.stackIndex = stackIndex;
        engine.variableReferences.insertElementAt(newRef, refCount + 1);
        return newRef;
    }

    public final int getStackSize() {
        return this.scriptEngine.dataStackSize - this.stackBaseOffset;
    }

    public final void enterFunction() throws Exception {
        if (this.hasFunction()) {
            this.programCounter = 0;
            if (this.currentFunction.functionData.hasVarArgs) {
                this.stackBaseOffset += this.parameterCount;
                this.ensureStackCapacity(this.currentFunction.functionData.maxStackSize);
                this.copyStackRange(-this.parameterCount, 0, Math.min(this.parameterCount, this.currentFunction.functionData.parameterCount));
                return;
            }
            this.ensureStackCapacity(this.currentFunction.functionData.maxStackSize);
        }
    }

    public final void exitFunction() throws Exception {
        if (this.hasFunction()) {
            this.ensureStackCapacity(this.currentFunction.functionData.maxStackSize);
        }
    }

    public final void setupParameters(int targetIndex, int requestedParamCount) throws Exception {
        int expectedParamCount = this.currentFunction.functionData.parameterCount;
        int extraParams;
        if ((extraParams = this.parameterCount - expectedParamCount) < 0) {
            extraParams = 0;
        }
        if (requestedParamCount == -1) {
            requestedParamCount = extraParams;
            this.ensureStackCapacity(targetIndex + requestedParamCount);
        }
        if (extraParams > requestedParamCount) {
            extraParams = requestedParamCount;
        }
        this.copyStackRange(-this.parameterCount + expectedParamCount, targetIndex, extraParams);
        if (requestedParamCount - extraParams > 0) {
            this.clearStackRange(targetIndex + extraParams, targetIndex + requestedParamCount - 1);
        }
    }

    public final boolean isInactive() {
        return !this.hasFunction();
    }

    public final boolean hasFunction() {
        return this.currentFunction != null;
    }
}