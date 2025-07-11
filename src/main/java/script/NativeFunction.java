package script;

public interface NativeFunction {
   int execute(ScriptContext context, int paramCount) throws Exception;
}