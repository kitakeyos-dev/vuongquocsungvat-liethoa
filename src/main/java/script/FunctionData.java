package script;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public final class FunctionData {
    public int[] instructions;
    public Object[] constants;
    public FunctionData[] nestedFunctions;
    public int parameterCount;
    public boolean hasVarArgs;
    private String functionName;
    public int upvalueCount;
    public int maxStackSize;

    private FunctionData(DataInputStream inputStream, String parentName) {
        this.functionName = readString(inputStream);
        try {
            inputStream.readInt(); // lineDefined
            inputStream.readInt(); // lastLineDefined
            this.upvalueCount = inputStream.read();
            this.parameterCount = inputStream.read();
            int flags = inputStream.read();
            this.hasVarArgs = (flags & 2) != 0;
            this.maxStackSize = inputStream.read();

            int instructionCount = inputStream.readInt();
            this.instructions = new int[instructionCount];

            for (int i = 0; i < instructionCount; ++i) {
                this.instructions[i] = inputStream.readInt();
            }

            int constantCount = inputStream.readInt();
            this.constants = new Object[constantCount];

            for (int i = 0; i < constantCount; ++i) {
                Object constant = null;
                int constantType = inputStream.read();
                switch (constantType) {
                    case 0: // NIL
                        break;
                    case 1: // BOOLEAN
                        constant = inputStream.read() == 0 ? Boolean.FALSE : Boolean.TRUE;
                        break;
                    case 2:
                    default:
                        throw new IOException("unknown constant type: " + constantType);
                    case 3: // INTEGER
                        constant = ScriptVM.createInteger(inputStream.readInt());
                        break;
                    case 4: // STRING
                        constant = readString(inputStream);
                }

                this.constants[i] = constant;
            }

            int nestedFunctionCount = inputStream.readInt();
            this.nestedFunctions = new FunctionData[nestedFunctionCount];

            for (int i = 0; i < nestedFunctionCount; ++i) {
                this.nestedFunctions[i] = new FunctionData(inputStream, this.functionName);
            }

            inputStream.readInt(); // debug info
            inputStream.readInt(); // debug info
            inputStream.readInt(); // debug info
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final String toString() {
        return this.functionName;
    }

    private static String readString(DataInputStream inputStream) {
        try {
            short stringLength;
            if ((stringLength = inputStream.readShort()) == 0) {
                return "";
            } else {
                validateOrThrow(stringLength < 65536, "Too long str: " + stringLength);
                byte[] buffer = new byte[stringLength];
                int bytesRead = 0;
                int remaining = stringLength;

                for (int attempts = 0; attempts < 100 && remaining > 0; ++attempts) {
                    int readCount = inputStream.read(buffer, bytesRead, remaining);
                    bytesRead += readCount;
                    remaining -= readCount;
                }

                validateOrThrow(remaining == 0, "strload");
                return new String(buffer, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void validateOrThrow(boolean condition, String message) throws IOException {
        if (!condition) {
            throw new IOException("Couldn't load bytecode:" + message);
        }
    }

    public static ScriptFunction loadBytecode(InputStream inputStream, CommandRegistry commandRegistry) {
        if (!(inputStream instanceof DataInputStream)) {
            inputStream = new DataInputStream(inputStream);
        }

        DataInputStream dataStream = null;
        try {
            validateOrThrow((dataStream = (DataInputStream) inputStream).read() == 27, "Signature 1");
            // Skip header bytes
            for (int i = 0; i < 11; i++) {
                dataStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        FunctionData functionData = new FunctionData(dataStream, null);
        return new ScriptFunction(functionData, commandRegistry);
    }
}