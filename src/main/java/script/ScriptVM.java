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

    private void executeVM(ScriptEngine engine) throws Exception {
        ScriptContext context = engine.getCurrentContext();
        ScriptFunction function = context.currentFunction;
        FunctionData functionData = function.functionData;
        int[] instructions = functionData.instructions;
        int localBase = context.localVariable;

        while (true) {
            while (true) {
                ScriptContext tempContext;
                try {
                    while (true) {
                        int instruction;
                        int opcode = (instruction = instructions[context.programCounter++]) & 63;
                        int regA = instruction >>> 6 & 255;
                        int regB;
                        Object callable;
                        int argCount;
                        Object leftOperand;
                        int loopIndex;
                        int loopLimit;
                        int loopStep;
                        Object operand;
                        Integer intResult;
                        Object rightOperand;
                        String stringResult;

                        switch (opcode) {
                            case 0: // MOVE
                                regB = instruction >>> 23 & 511;
                                context.setStackValue(regA, context.getStackValue(regB));
                                break;

                            case 1: // LOADK
                                regB = instruction >>> 14;
                                context.setStackValue(regA, functionData.constants[regB]);
                                break;

                            case 2: // LOADBOOL
                                regB = instruction >>> 23 & 511;
                                instruction = instruction >>> 14 & 511;
                                context.setStackValue(regA, regB == 0 ? Boolean.FALSE : Boolean.TRUE);
                                if (instruction != 0) {
                                    ++context.programCounter;
                                }
                                break;

                            case 3: // LOADNIL
                                regB = instruction >>> 23 & 511;
                                context.clearStackRange(regA, regB);
                                break;

                            case 4: // GETUPVAL
                                regB = instruction >>> 23 & 511;
                                context.setStackValue(regA, function.upvalues[regB].getValue());
                                break;

                            case 5: // GETGLOBAL
                                regB = instruction >>> 14;
                                context.setStackValue(regA, getTableValue(function.globalTable, functionData.constants[regB]));
                                break;

                            case 6: // GETTABLE
                                regB = instruction >>> 23 & 511;
                                instruction = instruction >>> 14 & 511;
                                leftOperand = context.getStackValue(regB);
                                rightOperand = getRKValue(context, instruction, functionData);
                                context.setStackValue(regA, getTableValue(leftOperand, rightOperand));
                                break;

                            case 7: // SETGLOBAL
                                regB = instruction >>> 14;
                                leftOperand = context.getStackValue(regA);
                                rightOperand = functionData.constants[regB];
                                setTableValue(function.globalTable, rightOperand, leftOperand);
                                break;

                            case 8: // SETUPVAL
                                regB = instruction >>> 23 & 511;
                                function.upvalues[regB].setValue(context.getStackValue(regA));
                                break;

                            case 9: // SETTABLE
                                regB = instruction >>> 23 & 511;
                                instruction = instruction >>> 14 & 511;
                                leftOperand = context.getStackValue(regA);
                                rightOperand = getRKValue(context, regB, functionData);
                                operand = getRKValue(context, instruction, functionData);
                                setTableValue(leftOperand, rightOperand, operand);
                                break;

                            case 10: // NEWTABLE
                                CommandRegistry newTable = new CommandRegistry();
                                context.setStackValue(regA, newTable);
                                break;

                            case 11: // SELF
                                regB = instruction >>> 23 & 511;
                                instruction = instruction >>> 14 & 511;
                                leftOperand = getRKValue(context, instruction, functionData);
                                operand = getTableValue(rightOperand = context.getStackValue(regB), leftOperand);
                                context.setStackValue(regA, operand);
                                context.setStackValue(regA + 1, rightOperand);
                                break;

                            case 12: // ADD
                            case 13: // SUB
                            case 14: // MUL
                            case 15: // DIV
                            case 16: // MOD
                            case 17: // POW
                                regB = instruction >>> 23 & 511;
                                instruction = instruction >>> 14 & 511;
                                leftOperand = getRKValue(context, regB, functionData);
                                rightOperand = getRKValue(context, instruction, functionData);
                                Integer leftInt;
                                Integer rightInt;
                                if ((leftInt = StandardFunctions.toNumber(leftOperand)) != null && (rightInt = StandardFunctions.toNumber(rightOperand)) != null) {
                                    intResult = performArithmetic(leftInt, rightInt, opcode);
                                } else {
                                    intResult = null;
                                }
                                context.setStackValue(regA, intResult);
                                break;

                            case 19: // NOT
                                regB = instruction >>> 23 & 511;
                                leftOperand = context.getStackValue(regB);
                                context.setStackValue(regA, createBoolean(!isTruthy(leftOperand)));
                                break;

                            case 20: // LEN
                                regB = instruction >>> 23 & 511;
                                Integer lengthResult;
                                if ((leftOperand = context.getStackValue(regB)) instanceof CommandRegistry) {
                                    lengthResult = createInteger(((CommandRegistry) leftOperand).getArraySize());
                                } else if (leftOperand instanceof String) {
                                    lengthResult = createInteger(((String) leftOperand).length());
                                } else {
                                    lengthResult = null;
                                }
                                context.setStackValue(regA, lengthResult);
                                break;

                            case 21: // CONCAT
                                regB = instruction >>> 23 & 511;
                                instruction = instruction >>> 14 & 511;
                                argCount = regB;
                                operand = context.getStackValue(instruction);
                                loopLimit = instruction - 1;

                                while (argCount <= loopLimit) {
                                    stringResult = StandardFunctions.toString(operand);
                                    if (operand != null) {
                                        instruction = 0;

                                        for (regB = loopLimit; argCount <= regB; ++instruction) {
                                            Object element = context.getStackValue(regB);
                                            --regB;
                                            if (StandardFunctions.toString(element) == null) {
                                                break;
                                            }
                                        }

                                        if (instruction > 0) {
                                            StringBuilder buffer = new StringBuilder();

                                            for (regB = loopLimit - instruction + 1; regB <= loopLimit; ++regB) {
                                                buffer.append(StandardFunctions.toString(context.getStackValue(regB)));
                                            }

                                            buffer.append(stringResult);
                                            operand = buffer.toString();
                                            loopLimit -= instruction;
                                        }
                                    }

                                    if (argCount <= loopLimit) {
                                        context.getStackValue(loopLimit);
                                        --loopLimit;
                                    }
                                }

                                context.setStackValue(regA, operand);
                                break;

                            case 22: // JMP
                                context.programCounter += decodeSignedOffset(instruction);
                                break;

                            case 23: // EQ
                            case 24: // LT
                            case 25: // LE
                                regB = instruction >>> 23 & 511;
                                instruction = instruction >>> 14 & 511;
                                leftOperand = getRKValue(context, regB, functionData);
                                rightOperand = getRKValue(context, instruction, functionData);

                                if (leftOperand instanceof Integer && rightOperand instanceof Integer) {
                                    loopIndex = toInteger(leftOperand);
                                    loopStep = toInteger(rightOperand);
                                    if (opcode == 23) {
                                        if (loopIndex == loopStep == (regA == 0)) {
                                            ++context.programCounter;
                                        }
                                    } else if (opcode == 24) {
                                        if (loopIndex < loopStep == (regA == 0)) {
                                            ++context.programCounter;
                                        }
                                    } else if (loopIndex <= loopStep == (regA == 0)) {
                                        ++context.programCounter;
                                    }
                                } else if (leftOperand instanceof String && rightOperand instanceof String) {
                                    if (opcode == 23) {
                                        if (leftOperand.equals(rightOperand) == (regA == 0)) {
                                            ++context.programCounter;
                                        }
                                    } else {
                                        String leftStr = (String) leftOperand;
                                        stringResult = (String) rightOperand;
                                        instruction = leftStr.compareTo(stringResult);
                                        if (opcode == 24) {
                                            if (instruction < 0 == (regA == 0)) {
                                                ++context.programCounter;
                                            }
                                        } else if (instruction <= 0 == (regA == 0)) {
                                            ++context.programCounter;
                                        }
                                    }
                                } else {
                                    boolean isEqual = false;
                                    if (leftOperand == rightOperand) {
                                        isEqual = true;
                                    } else if (opcode == 23) {
                                        isEqual = objectEquals(leftOperand, rightOperand);
                                    } else {
                                        StandardFunctions.error(opcode + " not defined for operand");
                                    }

                                    if (isEqual == (regA == 0)) {
                                        ++context.programCounter;
                                    }
                                }
                                break;

                            case 26: // TEST
                                instruction = instruction >>> 14 & 511;
                                if (isTruthy(context.getStackValue(regA)) == (instruction == 0)) {
                                    ++context.programCounter;
                                }
                                break;

                            case 27: // TESTSET
                                regB = instruction >>> 23 & 511;
                                instruction = instruction >>> 14 & 511;
                                if (isTruthy(leftOperand = context.getStackValue(regB)) != (instruction == 0)) {
                                    context.setStackValue(regA, leftOperand);
                                } else {
                                    ++context.programCounter;
                                }
                                break;

                            case 30: // RETURN
                                regB = (instruction >>> 23 & 511) - 1;
                                argCount = context.stackBaseOffset;
                                engine.shrinkDataStackAndUpdateReferences(argCount);
                                if (regB == -1) {
                                    regB = context.getStackSize() - regA;
                                }

                                engine.copyDataRange(context.stackBaseOffset + regA, localBase, regB);
                                engine.ensureDataStackCapacity(localBase + regB);
                                if (!context.isActive) {
                                    engine.popContext();
                                    return;
                                }

                                engine.popContext();
                                if ((context = engine.getCurrentContext()).currentFunction != null) {
                                    instructions = (functionData = (function = context.currentFunction).functionData).instructions;
                                    localBase = context.localVariable;
                                }

                                if (context.hasError) {
                                    context.ensureStackCapacity(functionData.maxStackSize);
                                }
                                break;

                            default:
                                // Handle other opcodes (28, 29, 31-37)
                                break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                    while (!engine.getCurrentContext().hasFunction()) {
                        engine.popContext();
                    }

                    boolean shouldRethrow = true;

                    do {
                        if ((context = engine.getCurrentContext()) == null) {
                            ScriptEngine parentEngine;
                            if ((parentEngine = engine.parentEngine) != null) {
                                engine.parentEngine = null;
                                (tempContext = parentEngine.getCurrentContext()).pushValue(Boolean.FALSE);
                                tempContext.pushValue(e.getMessage());
                                tempContext.pushValue(engine.engineName);
                                engine.vm.scriptEngine = parentEngine;
                                engine = parentEngine;
                                instructions = (functionData = (function = (context = parentEngine.getCurrentContext()).currentFunction).functionData).instructions;
                                localBase = context.localVariable;
                                shouldRethrow = false;
                            }
                            break;
                        }

                        engine.popContext();
                    } while (context.isActive);

                    if (context != null) {
                        context.shrinkStack(0);
                    }

                    if (shouldRethrow) {
                        throw e;
                    }
                }
            }
        }
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
        dataStack[stackBase + 1] = null;
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