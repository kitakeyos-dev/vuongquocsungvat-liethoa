package script;

import me.kitakeyos.ManagedInputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class LuaDecompiler {

    private static class DecompileContext {
        Object[] constants;
        int[] instructions;
        int pc = 0;
        StringBuilder output = new StringBuilder();
        String indent = "";
        Map<Integer, String> labels = new HashMap<>();

        void addIndent() { indent += "  "; }
        void removeIndent() { indent = indent.substring(2); }
        void emit(String code) { output.append(indent).append(code).append("\n"); }
        void emitRaw(String code) { output.append(code); }
    }

    public static String decompileFile(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            return decompileBytecode(inputStream);
        } catch (IOException e) {
            return "-- Error reading file: " + e.getMessage();
        }
    }

    public static String decompileBytecode(InputStream inputStream) throws IOException {
        DataInputStream dataStream = new DataInputStream(inputStream);

        // Skip header
        dataStream.read(); // signature
        for (int i = 0; i < 11; i++) {
            dataStream.read();
        }

        return decompileFunction(dataStream, "main", 0);
    }

    private static String decompileFunction(DataInputStream inputStream, String parentName, int depth) throws IOException {
        DecompileContext ctx = new DecompileContext();

        // Read function info
        String functionName = readString(inputStream);
        if (functionName.isEmpty()) {
            functionName = parentName + "_" + depth;
        }

        inputStream.readInt(); // lineDefined
        inputStream.readInt(); // lastLineDefined
        int upvalueCount = inputStream.read();
        int parameterCount = inputStream.read();
        int flags = inputStream.read();
        boolean hasVarArgs = (flags & 2) != 0;
        int maxStackSize = inputStream.read();

        if (depth > 0) {
            ctx.emit("local function " + functionName + "(");
        } else {
            ctx.emit("-- Main function");
            ctx.emit("-- Parameters: " + parameterCount + (hasVarArgs ? " + varargs" : ""));
            ctx.emit("-- Stack size: " + maxStackSize);
            ctx.emit("");
        }

        // Read instructions
        int instructionCount = inputStream.readInt();
        ctx.instructions = new int[instructionCount];
        for (int i = 0; i < instructionCount; i++) {
            ctx.instructions[i] = inputStream.readInt();
        }

        // Read constants
        int constantCount = inputStream.readInt();
        ctx.constants = new Object[constantCount];
        for (int i = 0; i < constantCount; i++) {
            int constantType = inputStream.read();
            switch (constantType) {
                case 0: // NIL
                    ctx.constants[i] = "nil";
                    break;
                case 1: // BOOLEAN
                    ctx.constants[i] = inputStream.read() == 0 ? "false" : "true";
                    break;
                case 3: // INTEGER
                    ctx.constants[i] = inputStream.readInt();
                    break;
                case 4: // STRING
                    ctx.constants[i] = readString(inputStream);
                    break;
                default:
                    throw new IOException("Unknown constant type: " + constantType);
            }
        }

        // Pre-scan for jump targets
        prescanJumps(ctx);

        // Decompile instructions
        ctx.addIndent();
        while (ctx.pc < ctx.instructions.length) {
            decompileInstruction(ctx);
        }

        // Read nested functions
        int nestedFunctionCount = inputStream.readInt();
        for (int i = 0; i < nestedFunctionCount; i++) {
            ctx.emitRaw("\n" + decompileFunction(inputStream, functionName, depth + 1));
        }

        // Skip debug info
        inputStream.readInt();
        inputStream.readInt();
        inputStream.readInt();

        if (depth > 0) {
            ctx.removeIndent();
            ctx.emit("end");
        }

        return ctx.output.toString();
    }

    private static void prescanJumps(DecompileContext ctx) {
        for (int i = 0; i < ctx.instructions.length; i++) {
            int instruction = ctx.instructions[i];
            int opcode = instruction & 63;

            if (opcode == 22) { // JMP
                int offset = ((instruction >>> 14) - 131071);
                int target = i + 1 + offset;
                if (target >= 0 && target < ctx.instructions.length) {
                    ctx.labels.put(target, "label_" + target);
                }
            }
        }
    }

    private static void decompileInstruction(DecompileContext ctx) {
        int instruction = ctx.instructions[ctx.pc];
        int opcode = instruction & 63;
        int regA = (instruction >>> 6) & 255;
        int regB = (instruction >>> 23) & 511;
        int regC = (instruction >>> 14) & 511;
        int bx = instruction >>> 14;
        int sbx = bx - 131071;

        // Check for labels
        if (ctx.labels.containsKey(ctx.pc)) {
            ctx.emit(":: " + ctx.labels.get(ctx.pc) + " ::");
        }

        switch (opcode) {
            case 0: // MOVE
                ctx.emit("R" + regA + " = R" + regB);
                break;

            case 1: // LOADK
                ctx.emit("R" + regA + " = " + formatConstant(ctx.constants[bx]));
                break;

            case 2: // LOADBOOL
                ctx.emit("R" + regA + " = " + (regB == 0 ? "false" : "true"));
                if (regC != 0) {
                    ctx.emit("-- skip next instruction");
                }
                break;

            case 3: // LOADNIL
                for (int i = regA; i <= regB; i++) {
                    ctx.emit("R" + i + " = nil");
                }
                break;

            case 4: // GETUPVAL
                ctx.emit("R" + regA + " = upvalue[" + regB + "]");
                break;

            case 5: // GETGLOBAL
                String globalName = (String) ctx.constants[bx];
                ctx.emit("R" + regA + " = " + globalName);
                break;

            case 6: // GETTABLE
                ctx.emit("R" + regA + " = R" + regB + "[" + formatRK(regC, ctx.constants) + "]");
                break;

            case 7: // SETGLOBAL
                String setGlobalName = (String) ctx.constants[bx];
                ctx.emit(setGlobalName + " = R" + regA);
                break;

            case 8: // SETUPVAL
                ctx.emit("upvalue[" + regB + "] = R" + regA);
                break;

            case 9: // SETTABLE
                ctx.emit("R" + regA + "[" + formatRK(regB, ctx.constants) + "] = " + formatRK(regC, ctx.constants));
                break;

            case 10: // NEWTABLE
                ctx.emit("R" + regA + " = {}");
                break;

            case 11: // SELF
                ctx.emit("R" + regA + " = R" + regB + "[" + formatRK(regC, ctx.constants) + "]");
                ctx.emit("R" + (regA + 1) + " = R" + regB);
                break;

            case 12: // ADD
                ctx.emit("R" + regA + " = " + formatRK(regB, ctx.constants) + " + " + formatRK(regC, ctx.constants));
                break;

            case 13: // SUB
                ctx.emit("R" + regA + " = " + formatRK(regB, ctx.constants) + " - " + formatRK(regC, ctx.constants));
                break;

            case 14: // MUL
                ctx.emit("R" + regA + " = " + formatRK(regB, ctx.constants) + " * " + formatRK(regC, ctx.constants));
                break;

            case 15: // DIV
                ctx.emit("R" + regA + " = " + formatRK(regB, ctx.constants) + " / " + formatRK(regC, ctx.constants));
                break;

            case 16: // MOD
                ctx.emit("R" + regA + " = " + formatRK(regB, ctx.constants) + " % " + formatRK(regC, ctx.constants));
                break;

            case 19: // NOT
                ctx.emit("R" + regA + " = not R" + regB);
                break;

            case 20: // LEN
                ctx.emit("R" + regA + " = #R" + regB);
                break;

            case 21: // CONCAT
                StringBuilder concat = new StringBuilder();
                for (int i = regB; i <= regC; i++) {
                    if (i > regB) concat.append(" .. ");
                    concat.append("R").append(i);
                }
                ctx.emit("R" + regA + " = " + concat.toString());
                break;

            case 22: // JMP
                int target = ctx.pc + 1 + sbx;
                ctx.emit("goto " + ctx.labels.get(target));
                break;

            case 23: // EQ
                ctx.emit("if (" + formatRK(regB, ctx.constants) + " == " + formatRK(regC, ctx.constants) + ") " +
                        (regA == 0 ? "~= true" : "== true") + " then skip next");
                break;

            case 24: // LT
                ctx.emit("if (" + formatRK(regB, ctx.constants) + " < " + formatRK(regC, ctx.constants) + ") " +
                        (regA == 0 ? "~= true" : "== true") + " then skip next");
                break;

            case 25: // LE
                ctx.emit("if (" + formatRK(regB, ctx.constants) + " <= " + formatRK(regC, ctx.constants) + ") " +
                        (regA == 0 ? "~= true" : "== true") + " then skip next");
                break;

            case 26: // TEST
                ctx.emit("if R" + regA + " " + (regC == 0 ? "then" : "else") + " skip next");
                break;

            case 27: // TESTSET
                ctx.emit("if R" + regB + " then R" + regA + " = R" + regB + " else skip next");
                break;

            case 28: // CALL
                StringBuilder call = new StringBuilder();
                if (regC > 1) {
                    for (int i = 0; i < regC - 1; i++) {
                        if (i > 0) call.append(", ");
                        call.append("R").append(regA + i);
                    }
                    call.append(" = ");
                }
                call.append("R").append(regA).append("(");
                if (regB > 1) {
                    for (int i = 1; i < regB; i++) {
                        if (i > 1) call.append(", ");
                        call.append("R").append(regA + i);
                    }
                } else if (regB == 0) {
                    call.append("...");
                }
                call.append(")");
                ctx.emit(call.toString());
                break;

            case 30: // RETURN
                if (regB == 0) {
                    ctx.emit("return ...");
                } else if (regB == 1) {
                    ctx.emit("return");
                } else {
                    StringBuilder ret = new StringBuilder("return ");
                    for (int i = 0; i < regB - 1; i++) {
                        if (i > 0) ret.append(", ");
                        ret.append("R").append(regA + i);
                    }
                    ctx.emit(ret.toString());
                }
                break;

            default:
                ctx.emit("-- Unknown opcode: " + opcode);
        }

        ctx.pc++;
    }

    private static String formatRK(int value, Object[] constants) {
        if (value >= 256) {
            return formatConstant(constants[value - 256]);
        } else {
            return "R" + value;
        }
    }

    private static String formatConstant(Object constant) {
        if (constant instanceof String) {
            return "\"" + constant + "\"";
        } else {
            return constant.toString();
        }
    }

    private static String readString(DataInputStream inputStream) throws IOException {
        short stringLength = inputStream.readShort();
        if (stringLength == 0) {
            return "";
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

        return new String(buffer, StandardCharsets.UTF_8);
    }

    // Main method để test
    public static void main(String[] args) throws IOException {
        InputStream inputStream = ManagedInputStream.openStream("/data/event/scene_13.mib");
        String luaCode = decompileBytecode(inputStream);
        System.out.println(luaCode);
    }
}