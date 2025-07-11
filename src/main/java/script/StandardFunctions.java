package script;

import javax.wireless.messaging.TextMessage;
import java.util.Random;

public final class StandardFunctions implements NativeFunction {
    private static final Object DEFAULT_LEVEL;
    private int functionId;
    private static StandardFunctions[] instances;
    private static long baseTime;
    private static Class connectorClass;

    static {
        Runtime.getRuntime();
        DEFAULT_LEVEL = new Integer(1);
    }

    private StandardFunctions(int functionId) {
        this.functionId = functionId;
    }

    public static void registerStandardFunctions(ScriptVM scriptVM) throws Exception {
        String[] functionNames = new String[23];
        functionNames[0] = "pcall";
        functionNames[1] = "print";
        functionNames[2] = "select";
        functionNames[3] = "type";
        functionNames[4] = "tostring";
        functionNames[5] = "tonumber";
        functionNames[6] = "error";
        functionNames[7] = "unpack";
        functionNames[8] = "next";
        functionNames[9] = "setfenv";
        functionNames[10] = "getfenv";
        functionNames[11] = "rawequal";
        functionNames[12] = "_s";
        functionNames[13] = "_ss";
        functionNames[14] = "_t";
        functionNames[15] = "_a";
        functionNames[16] = "rp";
        functionNames[17] = "_cn";
        functionNames[18] = "_r";
        functionNames[19] = "_o";
        functionNames[20] = "_c";
        functionNames[21] = "_sp";
        functionNames[22] = "nc";

        if (instances == null) {
            instances = new StandardFunctions[23];

            for (int i = 0; i < 23; ++i) {
                instances[i] = new StandardFunctions(i);
            }
        }

        for (int i = 0; i < 23; ++i) {
            scriptVM.scriptEngine.commandRegistry.put(functionNames[i], instances[i]);
        }

        CommandRegistry commandRegistry = scriptVM.scriptEngine.commandRegistry;
        Class connectorClassRef = connectorClass;
        if (connectorClassRef == null) {
            try {
                connectorClassRef = Class.forName("javax.microedition.io.Connector");
            } catch (ClassNotFoundException e) {
                throw new NoClassDefFoundError(e.getMessage());
            }

            connectorClass = connectorClassRef;
        }

        commandRegistry.put("_co", connectorClassRef);
    }

    public final int execute(ScriptContext context, int paramCount) throws Exception {
        String stringResult;
        Object value;
        Integer intValue;
        int intParam;
        Object obj;
        Object obj2;
        Object obj3;
        CommandRegistry table;

        switch (this.functionId) {
            case 0: // pcall
                return context.scriptEngine.vm.protectedCall(context.scriptEngine, paramCount - 1);

            case 1: // print
                ScriptVM scriptVM = context.scriptEngine.vm;
                Object toStringFunc = ScriptVM.getTableValue(scriptVM.scriptEngine.commandRegistry, "tostring");
                StringBuffer output = new StringBuffer();

                for (int i = 0; i < paramCount; ++i) {
                    Object arg = context.getStackValue(i);
                    int stackBase = scriptVM.scriptEngine.dataStackSize;
                    scriptVM.scriptEngine.ensureDataStackCapacity(stackBase + 1 + 3);
                    Object[] dataStack = scriptVM.scriptEngine.dataStack;
                    dataStack[stackBase] = toStringFunc;
                    dataStack[stackBase + 1] = arg;
                    dataStack[stackBase + 2] = null;
                    dataStack[stackBase + 3] = null;
                    int returnCount = scriptVM.callFunction(scriptVM.scriptEngine, 3);
                    arg = null;
                    if (returnCount > 0) {
                        arg = scriptVM.scriptEngine.dataStack[stackBase];
                    }

                    scriptVM.scriptEngine.ensureDataStackCapacity(stackBase);
                    output.append(arg);
                    output.append("\t");
                }

                scriptVM.debugOutput.println(output);
                return 0;

            case 2: // select
                return 0;

            case 3: // type
                assertError(paramCount > 0, "Not enough arguments");
                obj = context.getStackValue(0);

                // Simplified type determination
                String typeResult;
                if (obj == null) {
                    typeResult = "nil";
                } else if (obj instanceof String) {
                    typeResult = "string";
                } else if (obj instanceof Integer) {
                    typeResult = "number";
                } else if (obj instanceof Boolean) {
                    typeResult = "boolean";
                } else if (obj instanceof NativeFunction || obj instanceof ScriptFunction) {
                    typeResult = "function";
                } else if (obj instanceof CommandRegistry) {
                    typeResult = "table";
                } else if (obj instanceof ScriptEngine) {
                    typeResult = "thread";
                } else {
                    typeResult = "userdata";
                }

                context.pushValue(typeResult);
                return 1;

            case 4: // tostring
                assertError(paramCount > 0, "Not enough arguments");
                Object targetObj = context.getStackValue(0);

                // Simplified string conversion
                if (targetObj == null) {
                    stringResult = "nil";
                } else if (targetObj instanceof String) {
                    stringResult = (String) targetObj;
                } else if (targetObj instanceof Boolean) {
                    stringResult = targetObj.equals(Boolean.TRUE) ? "true" : "false";
                } else if (targetObj instanceof NativeFunction || targetObj instanceof ScriptFunction) {
                    stringResult = "function 0x" + Integer.toHexString(System.identityHashCode(targetObj));
                } else if (targetObj instanceof CommandRegistry) {
                    stringResult = "table 0x" + Integer.toHexString(System.identityHashCode(targetObj));
                } else {
                    stringResult = targetObj.toString();
                }

                context.pushValue(stringResult);
                return 1;

            case 5: // tonumber
                assertError(paramCount > 0, "Not enough arguments");
                obj = context.getStackValue(0);
                if (paramCount == 1) {
                    context.pushValue(toNumber(obj));
                    return 1;
                }

                stringResult = (String) obj;
                assertError((intValue = toNumber(context.getStackValue(1))) != null, "Argument 2 must be a number");
                intParam = intValue;
                Integer result = parseInteger(stringResult, intParam);
                context.pushValue(result);
                return 1;

            case 6: // error
                return 0;

            case 7: // unpack
                return unpackArray(context, paramCount);

            case 8: // next
                assertError(paramCount > 0, "Not enough arguments");
                table = (CommandRegistry) context.getStackValue(0);
                value = null;
                if (paramCount >= 2) {
                    value = context.getStackValue(1);
                }

                if ((obj3 = table.getNextKey(value)) == null) {
                    context.ensureStackCapacity(1);
                    context.setStackValue(0, null);
                    return 1;
                }

                Object nextValue = table.get(obj3);
                context.ensureStackCapacity(2);
                context.setStackValue(0, obj3);
                context.setStackValue(1, nextValue);
                return 2;

            case 9: // setfenv
                assertError(paramCount >= 2, "Not enough arguments");
                assertError((table = (CommandRegistry) context.getStackValue(1)) != null, "expected a table");
                ScriptFunction targetFunction;
                if ((obj3 = context.getStackValue(0)) instanceof ScriptFunction) {
                    targetFunction = (ScriptFunction) obj3;
                } else {
                    assertError((intValue = toNumber(obj3)) != null, "expected a function or a number");
                    if ((intParam = intValue) == 0) {
                        context.scriptEngine.commandRegistry = table;
                        return 0;
                    }

                    ScriptContext targetContext;
                    if (!(targetContext = context.scriptEngine.getContextAtLevel(intParam)).hasFunction()) {
                        error("No closure found at this level: " + intParam);
                    }

                    targetFunction = targetContext.currentFunction;
                }

                targetFunction.globalTable = table;
                context.ensureStackCapacity(1);
                return 1;

            case 10: // getfenv
                obj2 = DEFAULT_LEVEL;
                if (paramCount > 0) {
                    obj2 = context.getStackValue(0);
                }

                CommandRegistry envTable;
                if (obj2 != null && !(obj2 instanceof NativeFunction)) {
                    if (obj2 instanceof ScriptFunction) {
                        envTable = ((ScriptFunction) obj2).globalTable;
                    } else {
                        assertError((intValue = toNumber(obj2)) != null, "Expected number");
                        assertError((intParam = intValue) >= 0, "level must be non-negative");
                        ScriptContext levelContext;
                        envTable = (levelContext = context.scriptEngine.getContextAtLevel(intParam)).hasFunction() ?
                                levelContext.currentFunction.globalTable : levelContext.scriptEngine.commandRegistry;
                    }
                } else {
                    envTable = context.scriptEngine.commandRegistry;
                }

                context.pushValue(envTable);
                return 1;

            case 11: // rawequal
                assertError(paramCount >= 2, "Not enough arguments");
                obj2 = context.getStackValue(0);
                value = context.getStackValue(1);
                context.pushValue(ScriptVM.objectEquals(obj2, value) ? Boolean.TRUE : Boolean.FALSE);
                return 1;

            case 14: // _t (time)
                context.pushValue(new Integer((int) (System.currentTimeMillis() % 2147483647L)));
                return 1;

            case 15: // _a (address)
                if (paramCount == 1 && (obj2 = context.getStackValue(0)) instanceof TextMessage) {
                    context.pushValue(((TextMessage) obj2).getAddress());
                    return 1;
                }
                return 0;

            case 16: // rp (replace)
                context.pushValue(stringReplace((String) context.getStackValue(0), (String) context.getStackValue(1), (String) context.getStackValue(2)));
                return 1;

            case 17: // _cn (class name)
                context.pushValue(context.getStackValue(0).getClass());
                return 1;

            case 18: // _r (random)
                int minValue = (Integer) context.getStackValue(0);
                int maxValue = (Integer) context.getStackValue(1);
                context.pushValue((new Random()).nextInt() % (maxValue - minValue + 1) + minValue);
                return 1;

            case 19: // _o (char to number)
                context.pushValue((int) ((String) context.getStackValue(0)).charAt(0));
                return 1;

            case 20: // _c (numbers to chars)
                StringBuilder charBuffer = new StringBuilder();
                for (int i = 0; i < paramCount; ++i) {
                    charBuffer.append((char) ((Integer) context.getStackValue(i)).intValue());
                }
                context.pushValue(charBuffer.toString());
                return 1;

            case 21: // _sp (system property)
                stringResult = System.getProperty((String) context.getStackValue(0));
                if (stringResult != null) {
                    stringResult = stringResult.toLowerCase();
                }
                context.pushValue(stringResult);
                return 1;

            case 22: // nc (time + offset)
                context.pushValue((int) (baseTime + 10L));
                return 1;

            default:
                return 0;
        }
    }

    private static int unpackArray(ScriptContext context, int paramCount) throws Exception {
        assertError(paramCount > 0, "Not enough arguments");
        CommandRegistry table = (CommandRegistry) context.getStackValue(0);
        Object startObj = null;
        Object endObj = null;

        if (paramCount >= 2) {
            startObj = context.getStackValue(1);
        }

        if (paramCount >= 3) {
            endObj = context.getStackValue(2);
        }

        int startIndex;
        if (startObj != null) {
            startIndex = (Integer) startObj;
        } else {
            startIndex = 1;
        }

        int endIndex;
        if (endObj != null) {
            endIndex = (Integer) endObj;
        } else {
            endIndex = table.getArraySize();
        }

        int resultCount = endIndex + 1 - startIndex;
        if (resultCount <= 0) {
            context.ensureStackCapacity(0);
            return 0;
        } else {
            context.ensureStackCapacity(resultCount);

            for (int i = 0; i < resultCount; ++i) {
                context.setStackValue(i, table.get(ScriptVM.createInteger(startIndex + i)));
            }

            return resultCount;
        }
    }

    public static void assertError(boolean condition, String message) throws Exception {
        if (!condition) {
            error(message);
        }
    }

    public static void error(String message) throws Exception {
        throw new Exception(message);
    }

    private static Integer parseInteger(String str, int base) throws Exception {
        if (base >= 2 && base <= 36) {
            try {
                return base == 10 ? Integer.valueOf(str) : ScriptVM.createInteger(Integer.parseInt(str, base));
            } catch (NumberFormatException e) {
                return null;
            }
        } else {
            throw new Exception("base out of range");
        }
    }

    public static String toString(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        } else {
            return obj instanceof Integer ? ((Integer) obj).toString() : obj.toString();
        }
    }

    public static Integer toNumber(Object obj) throws Exception {
        if (obj instanceof Integer) {
            return (Integer) obj;
        } else {
            return obj instanceof String ? parseInteger((String) obj, 10) : null;
        }
    }

    private static String stringReplace(String text, String find, String replace) {
        StringBuilder result = new StringBuilder();
        int foundIndex = text.indexOf(find);
        int currentIndex = 0;

        int findLength = find.length();
        while (foundIndex != -1) {
            result.append(text, currentIndex, foundIndex).append(replace);
            currentIndex = foundIndex + findLength;
            foundIndex = text.indexOf(find, currentIndex);
        }

        result.append(text.substring(currentIndex));
        return result.toString();
    }
}