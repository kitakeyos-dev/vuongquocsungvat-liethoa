package script;

import java.io.PrintStream;
import java.util.Random;

public final class ScriptVM {
    public ScriptEngine scriptEngine;
    public PrintStream debugOutput;

    public ScriptVM() {

    }

    private ScriptVM(PrintStream debugOutput) throws Exception {
        new Random();
        this.debugOutput = debugOutput;
        this.scriptEngine = new ScriptEngine(this, new CommandRegistry());
        try {
            this.scriptEngine.commandRegistry.put("_G", this.scriptEngine.commandRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        StandardFunctions.registerStandardFunctions(this);
    }

    public int callFunction(ScriptEngine engine, int paramCount) throws Exception {
        return this.callFunctionInternal(engine, paramCount);
    }

    private int callFunctionInternal(ScriptEngine engine, int paramCount) throws Exception {
        int functionIndex = engine.dataStackSize - paramCount - 1;
        Object callable = engine.dataStack[functionIndex];
        if (callable == null) {
            throw new Exception("call nil");
        } else if (callable instanceof NativeFunction) {
            return callNativeFunction(engine, (NativeFunction) callable, functionIndex + 1, functionIndex, paramCount);
        } else if (!(callable instanceof ScriptFunction)) {
            throw new Exception("call a non-func");
        } else {
            engine.pushContext((ScriptFunction) callable, functionIndex + 1, functionIndex, paramCount, false, false).enterFunction();
            this.executeVM(engine);
            paramCount = engine.dataStackSize - functionIndex;
            engine.engineName = "";
            return paramCount;
        }
    }

    /**
     * Executes the virtual machine for script interpretation.
     * This is the main execution loop that processes Lua bytecode instructions.
     */
    private void executeVM(ScriptEngine engine) throws Exception {
        boolean vmRunning = true;

        while (vmRunning) {
            ScriptContext context = engine.getCurrentContext();
            if (context == null || context.currentFunction == null) {
                break; // No more contexts to execute
            }

            ScriptFunction function = context.currentFunction;
            FunctionData functionData = function.functionData;
            int[] instructions = functionData.instructions;
            int localVariableBase = context.localVariable;

            try {
                // Execute instructions until function returns or exception
                boolean functionRunning = true;

                while (functionRunning && context.programCounter < instructions.length) {
                    int instruction = instructions[context.programCounter++];
                    int opcode = instruction & 63;  // Extract opcode (6 bits)
                    int regA = (instruction >>> 6) & 255;  // Register A (8 bits)

                    switch (opcode) {
                        case 0: // MOVE - Copy value between registers
                            handleMoveInstruction(context, instruction, regA);
                            break;

                        case 1: // LOADK - Load constant
                            handleLoadConstantInstruction(context, functionData, instruction, regA);
                            break;

                        case 2: // LOADBOOL - Load boolean value
                            handleLoadBooleanInstruction(context, instruction, regA);
                            break;

                        case 3: // LOADNIL - Load nil values
                            handleLoadNilInstruction(context, instruction, regA);
                            break;

                        case 4: // GETUPVAL - Get upvalue
                            handleGetUpvalueInstruction(context, function, instruction, regA);
                            break;

                        case 5: // GETGLOBAL - Get global variable
                            handleGetGlobalInstruction(context, function, functionData, instruction, regA);
                            break;

                        case 6: // GETTABLE - Get table element
                            handleGetTableInstruction(context, functionData, instruction, regA);
                            break;

                        case 7: // SETGLOBAL - Set global variable
                            handleSetGlobalInstruction(context, function, functionData, instruction, regA);
                            break;

                        case 8: // SETUPVAL - Set upvalue
                            handleSetUpvalueInstruction(context, function, instruction, regA);
                            break;

                        case 9: // SETTABLE - Set table element
                            handleSetTableInstruction(context, functionData, instruction, regA);
                            break;

                        case 10: // NEWTABLE - Create new table
                            handleNewTableInstruction(context, regA);
                            break;

                        case 11: // SELF - Prepare method call
                            handleSelfInstruction(context, functionData, instruction, regA);
                            break;

                        case 12: // ADD
                        case 13: // SUB
                        case 14: // MUL
                        case 15: // DIV
                        case 16: // MOD
                        case 17: // POW
                            handleArithmeticInstruction(context, functionData, instruction, regA, opcode);
                            break;

                        case 19: // NOT - Logical NOT
                            handleNotInstruction(context, instruction, regA);
                            break;

                        case 20: // LEN - Length operator
                            handleLengthInstruction(context, instruction, regA);
                            break;

                        case 21: // CONCAT - String concatenation
                            handleConcatInstruction(context, instruction, regA);
                            break;

                        case 22: // JMP - Unconditional jump
                            handleJumpInstruction(context, instruction);
                            break;

                        case 23: // EQ - Equality comparison
                        case 24: // LT - Less than comparison
                        case 25: // LE - Less than or equal comparison
                            handleComparisonInstruction(context, functionData, instruction, regA, opcode);
                            break;

                        case 26: // TEST - Test register and jump
                            handleTestInstruction(context, instruction, regA);
                            break;

                        case 27: // TESTSET - Test register, set if true, jump if false
                            handleTestSetInstruction(context, instruction, regA);
                            break;

                        case 30: // RETURN - Return from function
                            boolean vmShouldExit = handleReturnInstruction(engine, context, functionData, instruction, regA, localVariableBase);
                            functionRunning = false; // Exit current function
                            if (vmShouldExit) {
                                vmRunning = false; // Exit entire VM
                            }
                            break;

                        default:
                            // Handle other opcodes (28, 29, 31-37) - not implemented
                            throw new Exception("Unimplemented opcode: " + opcode);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();

                // Handle exception by unwinding to nearest function context
                boolean shouldContinue = handleVMException(engine, e);
                if (!shouldContinue) {
                    throw e; // Rethrow if cannot handle
                }
                // Continue with next iteration to process remaining contexts
            }
        }
    }

    private void handleMoveInstruction(ScriptContext context, int instruction, int regA) {
        int regB = (instruction >>> 23) & 511;
        context.setStackValue(regA, context.getStackValue(regB));
    }

    private void handleLoadConstantInstruction(ScriptContext context, FunctionData functionData, int instruction, int regA) {
        int constantIndex = instruction >>> 14;
        context.setStackValue(regA, functionData.constants[constantIndex]);
    }

    private void handleLoadBooleanInstruction(ScriptContext context, int instruction, int regA) {
        int boolValue = (instruction >>> 23) & 511;
        int skipNext = (instruction >>> 14) & 511;
        context.setStackValue(regA, boolValue == 0 ? Boolean.FALSE : Boolean.TRUE);
        if (skipNext != 0) {
            context.programCounter++;
        }
    }

    private void handleLoadNilInstruction(ScriptContext context, int instruction, int regA) {
        int regB = (instruction >>> 23) & 511;
        context.clearStackRange(regA, regB);
    }

    private void handleGetUpvalueInstruction(ScriptContext context, ScriptFunction function, int instruction, int regA) {
        int upvalueIndex = (instruction >>> 23) & 511;
        context.setStackValue(regA, function.upvalues[upvalueIndex].getValue());
    }

    private void handleGetGlobalInstruction(ScriptContext context, ScriptFunction function, FunctionData functionData, int instruction, int regA) throws Exception {
        int constantIndex = instruction >>> 14;
        Object key = functionData.constants[constantIndex];
        Object value = getTableValue(function.globalTable, key);
        context.setStackValue(regA, value);
    }

    private void handleGetTableInstruction(ScriptContext context, FunctionData functionData, int instruction, int regA) throws Exception {
        int regB = (instruction >>> 23) & 511;
        int regC = (instruction >>> 14) & 511;
        Object table = context.getStackValue(regB);
        Object key = getRKValue(context, regC, functionData);
        Object value = getTableValue(table, key);
        context.setStackValue(regA, value);
    }

    private void handleSetGlobalInstruction(ScriptContext context, ScriptFunction function, FunctionData functionData, int instruction, int regA) {
        int constantIndex = instruction >>> 14;
        Object value = context.getStackValue(regA);
        Object key = functionData.constants[constantIndex];
        setTableValue(function.globalTable, key, value);
    }

    private void handleSetUpvalueInstruction(ScriptContext context, ScriptFunction function, int instruction, int regA) {
        int upvalueIndex = (instruction >>> 23) & 511;
        function.upvalues[upvalueIndex].setValue(context.getStackValue(regA));
    }

    private void handleSetTableInstruction(ScriptContext context, FunctionData functionData, int instruction, int regA) {
        int regB = (instruction >>> 23) & 511;
        int regC = (instruction >>> 14) & 511;
        Object table = context.getStackValue(regA);
        Object key = getRKValue(context, regB, functionData);
        Object value = getRKValue(context, regC, functionData);
        setTableValue(table, key, value);
    }

    private void handleNewTableInstruction(ScriptContext context, int regA) {
        CommandRegistry newTable = new CommandRegistry();
        context.setStackValue(regA, newTable);
    }

    private void handleSelfInstruction(ScriptContext context, FunctionData functionData, int instruction, int regA) throws Exception {
        int regB = (instruction >>> 23) & 511;
        int regC = (instruction >>> 14) & 511;
        Object key = getRKValue(context, regC, functionData);
        Object table = context.getStackValue(regB);
        Object method = getTableValue(table, key);
        context.setStackValue(regA, method);
        context.setStackValue(regA + 1, table);
    }

    private void handleArithmeticInstruction(ScriptContext context, FunctionData functionData, int instruction, int regA, int opcode) throws Exception {
        int regB = (instruction >>> 23) & 511;
        int regC = (instruction >>> 14) & 511;
        Object leftOperand = getRKValue(context, regB, functionData);
        Object rightOperand = getRKValue(context, regC, functionData);

        Integer leftInt = StandardFunctions.toNumber(leftOperand);
        Integer rightInt = StandardFunctions.toNumber(rightOperand);

        Integer result = null;
        if (leftInt != null && rightInt != null) {
            result = performArithmetic(leftInt, rightInt, opcode);
        }
        context.setStackValue(regA, result);
    }

    private void handleNotInstruction(ScriptContext context, int instruction, int regA) {
        int regB = (instruction >>> 23) & 511;
        Object operand = context.getStackValue(regB);
        context.setStackValue(regA, createBoolean(!isTruthy(operand)));
    }

    private void handleLengthInstruction(ScriptContext context, int instruction, int regA) throws Exception {
        int regB = (instruction >>> 23) & 511;
        Object operand = context.getStackValue(regB);

        Integer lengthResult = null;
        if (operand instanceof CommandRegistry) {
            lengthResult = createInteger(((CommandRegistry) operand).getArraySize());
        } else if (operand instanceof String) {
            lengthResult = createInteger(((String) operand).length());
        }
        context.setStackValue(regA, lengthResult);
    }

    private void handleConcatInstruction(ScriptContext context, int instruction, int regA) {
        int regB = (instruction >>> 23) & 511;
        int regC = (instruction >>> 14) & 511;

        int startReg = regB;
        Object result = context.getStackValue(regC);
        int endReg = regC - 1;

        while (startReg <= endReg) {
            String currentStr = StandardFunctions.toString(result);
            if (result != null) {
                int consecutiveStrings = 0;

                // Count consecutive string values from end
                for (int i = endReg; startReg <= i; consecutiveStrings++) {
                    Object element = context.getStackValue(i);
                    i--;
                    if (StandardFunctions.toString(element) == null) {
                        break;
                    }
                }

                // Concatenate consecutive strings
                if (consecutiveStrings > 0) {
                    StringBuilder buffer = new StringBuilder();
                    for (int i = endReg - consecutiveStrings + 1; i <= endReg; i++) {
                        buffer.append(StandardFunctions.toString(context.getStackValue(i)));
                    }
                    buffer.append(currentStr);
                    result = buffer.toString();
                    endReg -= consecutiveStrings;
                }
            }

            if (startReg <= endReg) {
                context.getStackValue(endReg);
                endReg--;
            }
        }

        context.setStackValue(regA, result);
    }

    private void handleJumpInstruction(ScriptContext context, int instruction) {
        context.programCounter += decodeSignedOffset(instruction);
    }

    private void handleComparisonInstruction(ScriptContext context, FunctionData functionData, int instruction, int regA, int opcode) throws Exception {
        int regB = (instruction >>> 23) & 511;
        int regC = (instruction >>> 14) & 511;
        Object leftOperand = getRKValue(context, regB, functionData);
        Object rightOperand = getRKValue(context, regC, functionData);

        boolean conditionMet = false;

        if (leftOperand instanceof Integer && rightOperand instanceof Integer) {
            int leftValue = toInteger(leftOperand);
            int rightValue = toInteger(rightOperand);

            switch (opcode) {
                case 23: // EQ
                    conditionMet = (leftValue == rightValue);
                    break;
                case 24: // LT
                    conditionMet = (leftValue < rightValue);
                    break;
                case 25: // LE
                    conditionMet = (leftValue <= rightValue);
                    break;
            }
        } else if (leftOperand instanceof String && rightOperand instanceof String) {
            String leftStr = (String) leftOperand;
            String rightStr = (String) rightOperand;

            switch (opcode) {
                case 23: // EQ
                    conditionMet = leftStr.equals(rightStr);
                    break;
                case 24: // LT
                    conditionMet = (leftStr.compareTo(rightStr) < 0);
                    break;
                case 25: // LE
                    conditionMet = (leftStr.compareTo(rightStr) <= 0);
                    break;
            }
        } else {
            if (opcode == 23) { // EQ
                conditionMet = (leftOperand == rightOperand) || objectEquals(leftOperand, rightOperand);
            } else {
                StandardFunctions.error(opcode + " not defined for operand");
            }
        }

        if (conditionMet == (regA == 0)) {
            context.programCounter++;
        }
    }

    private void handleTestInstruction(ScriptContext context, int instruction, int regA) {
        int condition = (instruction >>> 14) & 511;
        if (isTruthy(context.getStackValue(regA)) == (condition == 0)) {
            context.programCounter++;
        }
    }

    private void handleTestSetInstruction(ScriptContext context, int instruction, int regA) {
        int regB = (instruction >>> 23) & 511;
        int condition = (instruction >>> 14) & 511;
        Object operand = context.getStackValue(regB);

        if (isTruthy(operand) != (condition == 0)) {
            context.setStackValue(regA, operand);
        } else {
            context.programCounter++;
        }
    }

    private boolean handleReturnInstruction(ScriptEngine engine, ScriptContext context, FunctionData functionData, int instruction, int regA, int localVariableBase) throws Exception {
        int returnCount = ((instruction >>> 23) & 511) - 1;
        int stackBaseOffset = context.stackBaseOffset;

        engine.shrinkDataStackAndUpdateReferences(stackBaseOffset);

        if (returnCount == -1) {
            returnCount = context.getStackSize() - regA;
        }

        engine.copyDataRange(context.stackBaseOffset + regA, localVariableBase, returnCount);
        engine.ensureDataStackCapacity(localVariableBase + returnCount);

        if (!context.isActive) {
            engine.popContext();
            return true; // Signal to exit VM completely
        }

        engine.popContext();
        ScriptContext newContext = engine.getCurrentContext();

        if (newContext != null && newContext.hasError) {
            newContext.ensureStackCapacity(functionData.maxStackSize);
        }

        return newContext == null; // Exit VM if no more contexts
    }

    private boolean handleVMException(ScriptEngine engine, Exception exception) throws Exception {
        // Unwind stack to nearest function context
        while (engine.getCurrentContext() != null && !engine.getCurrentContext().hasFunction()) {
            engine.popContext();
        }

        ScriptContext context;

        do {
            context = engine.getCurrentContext();
            if (context == null) {
                ScriptEngine parentEngine = engine.parentEngine;
                if (parentEngine != null) {
                    engine.parentEngine = null;
                    ScriptContext parentContext = parentEngine.getCurrentContext();
                    parentContext.pushValue(Boolean.FALSE);
                    parentContext.pushValue(exception.getMessage());
                    parentContext.pushValue(engine.engineName);
                    engine.vm.scriptEngine = parentEngine;
                    return true; // Continue execution with parent engine
                }
                return false; // No parent, should rethrow
            }
            engine.popContext();
        } while (context.isActive);

        context.shrinkStack(0);

        return engine.getCurrentContext() != null; // Continue if there are more contexts
    }

    private static int callNativeFunction(ScriptEngine engine, NativeFunction nativeFunc, int stackBase, int localBase, int paramCount) throws Exception {
        int returnCount = 0;
        ScriptContext context = engine.pushContext(null, stackBase, localBase, paramCount, false, false);
        engine.popContext();

        try {
            returnCount = nativeFunc.execute(context, paramCount);
        } catch (ClassCastException e) {
            e.printStackTrace();
            System.out.println("func");
        }

        int currentStackSize = context.getStackSize();
        int resultOffset = currentStackSize - returnCount;
        context.copyStackRange(resultOffset, -1, returnCount);
        context.ensureStackCapacity(returnCount - 1);
        return returnCount;
    }

    public Object executeScript(Object function, Object param) throws Exception {
        int stackBase = this.scriptEngine.dataStackSize;
        this.scriptEngine.ensureDataStackCapacity(stackBase + 1 + 3);
        Object[] dataStack = this.scriptEngine.dataStack;
        dataStack[stackBase] = function;
        dataStack[stackBase + 1] = param;
        dataStack[stackBase + 2] = null;
        dataStack[stackBase + 3] = null;
        int returnCount = this.callFunctionInternal(this.scriptEngine, 3);
        Object result = null;
        if (returnCount > 0) {
            result = this.scriptEngine.dataStack[stackBase];
        }

        this.scriptEngine.ensureDataStackCapacity(stackBase);
        return result;
    }

    public static Object getTableValue(Object table, Object key) throws Exception {
        if (!(table instanceof CommandRegistry)) {
            if (table instanceof String && key instanceof Integer) {
                int index = (Integer) key;
                return ((String) table).substring(index - 1, index);
            } else {
                return null;
            }
        } else {
            if (key instanceof Integer) {
                int intKey = (Integer) key;
                Object value;
                if ((value = ((CommandRegistry) table).get(intKey)) != null) {
                    return value;
                }
            }

            return ((CommandRegistry) table).get(key);
        }
    }

    private static void setTableValue(Object table, Object key, Object value) {
        try {
            ((CommandRegistry) table).put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(key);
            System.out.println(value);
            System.out.println(table);
        }
    }

    public final int protectedCall(ScriptEngine engine, int paramCount) throws Exception {
        ScriptContext originalContext = engine.getCurrentContext();
        engine.engineName = "";
        int stackBase = engine.dataStackSize - paramCount - 1;

        String errorMessage;
        Object errorObject;
        try {
            int returnCount = this.callFunctionInternal(engine, paramCount);
            int resultEnd = stackBase + returnCount + 1;
            engine.ensureDataStackCapacity(resultEnd);
            engine.copyDataRange(stackBase, stackBase + 1, returnCount);
            engine.dataStack[stackBase] = Boolean.TRUE;
            return returnCount + 1;
        } catch (Exception e) {
            errorObject = e;
            errorMessage = null;
            e.printStackTrace();
        } catch (Throwable t) {
            errorObject = t;
            errorMessage = t.getMessage();
            t.printStackTrace();
        }

        StandardFunctions.assertError(true, "Thread changed in pcall");
        if (originalContext != null) {
            originalContext.shrinkStack(0);
        }

        ScriptContext context;
        while ((context = engine.getCurrentContext()) != null && context != originalContext) {
            engine.popContext();
        }

        engine.ensureDataStackCapacity(stackBase + 4);
        engine.dataStack[stackBase] = Boolean.FALSE;
        engine.dataStack[stackBase + 1] = errorMessage;
        engine.dataStack[stackBase + 2] = engine.engineName;
        engine.dataStack[stackBase + 3] = errorObject;
        engine.engineName = "";
        return 4;
    }

    public static boolean objectEquals(Object obj1, Object obj2) {
        if (obj1 != null && obj2 != null) {
            if (obj1 instanceof Integer && obj2 instanceof Integer) {
                Integer int1 = (Integer) obj1;
                Integer int2 = (Integer) obj2;
                return int1.equals(int2);
            } else {
                return obj1 == obj2;
            }
        } else {
            return obj1 == obj2;
        }
    }

    private static int toInteger(Object obj) {
        return (Integer) obj;
    }

    public static Integer createInteger(int value) {
        return new Integer(value);
    }

    private static boolean isTruthy(Object obj) {
        return obj != null && obj != Boolean.FALSE;
    }

    private static Boolean createBoolean(boolean value) {
        return value ? Boolean.TRUE : Boolean.FALSE;
    }

    private static final Object getRKValue(ScriptContext context, int index, FunctionData functionData) {
        int constantIndex;
        return (constantIndex = index - 256) < 0 ? context.getStackValue(index) : functionData.constants[constantIndex];
    }

    private static final int decodeSignedOffset(int instruction) {
        return (instruction >>> 14) - 131071;
    }

    private static final Integer performArithmetic(Integer left, Integer right, int opcode) {
        int leftValue = left;
        int rightValue = right;
        int result = 0;
        switch (opcode) {
            case 12:
                result = leftValue + rightValue;
                break;
            case 13:
                result = leftValue - rightValue;
                break;
            case 14:
                result = leftValue * rightValue;
                break;
            case 15:
                result = leftValue / rightValue;
                break;
            case 16:
                result = leftValue % rightValue;
        }

        return createInteger(result);
    }
}