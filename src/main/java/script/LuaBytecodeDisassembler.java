package script;

import me.kitakeyos.ManagedInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class LuaBytecodeDisassembler {

    private static final Map<Integer, String> OPCODES = new HashMap<>();
    static {
        OPCODES.put(0, "MOVE");
        OPCODES.put(1, "LOADK");
        OPCODES.put(2, "LOADBOOL");
        OPCODES.put(3, "LOADNIL");
        OPCODES.put(4, "GETUPVAL");
        OPCODES.put(5, "GETGLOBAL");
        OPCODES.put(6, "GETTABLE");
        OPCODES.put(7, "SETGLOBAL");
        OPCODES.put(8, "SETUPVAL");
        OPCODES.put(9, "SETTABLE");
        OPCODES.put(10, "NEWTABLE");
        OPCODES.put(11, "SELF");
        OPCODES.put(12, "ADD");
        OPCODES.put(13, "SUB");
        OPCODES.put(14, "MUL");
        OPCODES.put(15, "DIV");
        OPCODES.put(16, "MOD");
        OPCODES.put(17, "POW");
        OPCODES.put(18, "UNM");
        OPCODES.put(19, "NOT");
        OPCODES.put(20, "LEN");
        OPCODES.put(21, "CONCAT");
        OPCODES.put(22, "JMP");
        OPCODES.put(23, "EQ");
        OPCODES.put(24, "LT");
        OPCODES.put(25, "LE");
        OPCODES.put(26, "TEST");
        OPCODES.put(27, "TESTSET");
        OPCODES.put(28, "CALL");
        OPCODES.put(29, "TAILCALL");
        OPCODES.put(30, "RETURN");
        OPCODES.put(31, "FORLOOP");
        OPCODES.put(32, "FORPREP");
        OPCODES.put(33, "TFORLOOP");
        OPCODES.put(34, "SETLIST");
        OPCODES.put(35, "CLOSE");
        OPCODES.put(36, "CLOSURE");
        OPCODES.put(37, "VARARG");
    }

    public static void disassembleFile(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            disassembleBytecode(inputStream);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public static void disassembleBytecode(InputStream inputStream) throws IOException {
        DataInputStream dataStream = new DataInputStream(inputStream);

        // Read and validate header
        System.out.println("=== LUA BYTECODE DISASSEMBLY ===");
        System.out.println();

        // Read signature
        int signature = dataStream.read();
        System.out.println("Signature: 0x" + Integer.toHexString(signature));

        if (signature != 27) {
            throw new IOException("Invalid Lua bytecode signature");
        }

        // Skip header (11 bytes)
        System.out.print("Header: ");
        for (int i = 0; i < 11; i++) {
            int b = dataStream.read();
            System.out.print("0x" + Integer.toHexString(b) + " ");
        }
        System.out.println();
        System.out.println();

        // Disassemble main function
        disassembleFunction(dataStream, "main", 0);
    }

    private static void disassembleFunction(DataInputStream inputStream, String parentName, int depth) throws IOException {
        String indent = "  ".repeat(depth);

        // Read function info
        String functionName = readString(inputStream);
        if (functionName.isEmpty()) {
            functionName = parentName + "_" + depth;
        }

        System.out.println(indent + "function " + functionName + " {");

        int lineDefined = inputStream.readInt();
        int lastLineDefined = inputStream.readInt();
        int upvalueCount = inputStream.read();
        int parameterCount = inputStream.read();
        int flags = inputStream.read();
        boolean hasVarArgs = (flags & 2) != 0;
        int maxStackSize = inputStream.read();

        System.out.println(indent + "  ; line defined: " + lineDefined);
        System.out.println(indent + "  ; last line defined: " + lastLineDefined);
        System.out.println(indent + "  ; upvalue count: " + upvalueCount);
        System.out.println(indent + "  ; parameter count: " + parameterCount);
        System.out.println(indent + "  ; has varargs: " + hasVarArgs);
        System.out.println(indent + "  ; max stack size: " + maxStackSize);
        System.out.println();

        // Read instructions
        int instructionCount = inputStream.readInt();
        int[] instructions = new int[instructionCount];

        System.out.println(indent + "  ; === INSTRUCTIONS ===");
        for (int i = 0; i < instructionCount; i++) {
            instructions[i] = inputStream.readInt();
            disassembleInstruction(instructions[i], i, indent + "  ");
        }
        System.out.println();

        // Read constants
        int constantCount = inputStream.readInt();
        Object[] constants = new Object[constantCount];

        System.out.println(indent + "  ; === CONSTANTS ===");
        for (int i = 0; i < constantCount; i++) {
            int constantType = inputStream.read();
            Object constant = null;

            switch (constantType) {
                case 0: // NIL
                    constant = "nil";
                    break;
                case 1: // BOOLEAN
                    constant = inputStream.read() == 0 ? "false" : "true";
                    break;
                case 3: // INTEGER
                    constant = inputStream.readInt();
                    break;
                case 4: // STRING
                    constant = "\"" + readString(inputStream) + "\"";
                    break;
                default:
                    throw new IOException("Unknown constant type: " + constantType);
            }

            constants[i] = constant;
            System.out.println(indent + "  K" + i + " = " + constant);
        }
        System.out.println();

        // Read nested functions
        int nestedFunctionCount = inputStream.readInt();
        System.out.println(indent + "  ; === NESTED FUNCTIONS ===");
        for (int i = 0; i < nestedFunctionCount; i++) {
            disassembleFunction(inputStream, functionName, depth + 1);
        }

        // Skip debug info
        inputStream.readInt(); // debug info
        inputStream.readInt(); // debug info
        inputStream.readInt(); // debug info

        System.out.println(indent + "}");
        System.out.println();
    }

    private static void disassembleInstruction(int instruction, int pc, String indent) {
        int opcode = instruction & 63;
        int regA = (instruction >>> 6) & 255;
        int regB = (instruction >>> 23) & 511;
        int regC = (instruction >>> 14) & 511;
        int bx = instruction >>> 14;
        int sbx = bx - 131071;

        String opName = OPCODES.getOrDefault(opcode, "UNK_" + opcode);

        System.out.print(String.format("%s[%3d] %-8s ", indent, pc, opName));

        switch (opcode) {
            case 0: // MOVE
                System.out.println("R" + regA + " R" + regB);
                break;
            case 1: // LOADK
                System.out.println("R" + regA + " K" + bx);
                break;
            case 2: // LOADBOOL
                System.out.println("R" + regA + " " + regB + " " + regC);
                break;
            case 3: // LOADNIL
                System.out.println("R" + regA + " R" + regB);
                break;
            case 4: // GETUPVAL
                System.out.println("R" + regA + " U" + regB);
                break;
            case 5: // GETGLOBAL
                System.out.println("R" + regA + " K" + bx);
                break;
            case 6: // GETTABLE
                System.out.println("R" + regA + " R" + regB + " " + formatRK(regC));
                break;
            case 7: // SETGLOBAL
                System.out.println("K" + bx + " R" + regA);
                break;
            case 8: // SETUPVAL
                System.out.println("U" + regB + " R" + regA);
                break;
            case 9: // SETTABLE
                System.out.println("R" + regA + " " + formatRK(regB) + " " + formatRK(regC));
                break;
            case 22: // JMP
                System.out.println("-> " + (pc + 1 + sbx));
                break;
            case 28: // CALL
                System.out.println("R" + regA + " " + regB + " " + regC);
                break;
            case 30: // RETURN
                System.out.println("R" + regA + " " + regB);
                break;
            default:
                System.out.println("R" + regA + " " + regB + " " + regC);
        }
    }

    private static String formatRK(int value) {
        if (value >= 256) {
            return "K" + (value - 256);
        } else {
            return "R" + value;
        }
    }

    private static String readString(DataInputStream inputStream) throws IOException {
        short stringLength = inputStream.readShort();
        if (stringLength == 0) {
            return "";
        }

        if (stringLength >= 65536) {
            throw new IOException("String too long: " + stringLength);
        }

        byte[] buffer = new byte[stringLength];
        int bytesRead = 0;
        int remaining = stringLength;

        for (int attempts = 0; attempts < 100 && remaining > 0; attempts++) {
            int readCount = inputStream.read(buffer, bytesRead, remaining);
            if (readCount == -1) break;
            bytesRead += readCount;
            remaining -= readCount;
        }

        if (remaining != 0) {
            throw new IOException("Could not read complete string");
        }

        return new String(buffer, StandardCharsets.UTF_8);
    }

    // Main method để test
    public static void main(String[] args) throws IOException {
        InputStream inputStream = ManagedInputStream.openStream("/data/event/scene_13.mib");
        disassembleBytecode(inputStream);
    }
}