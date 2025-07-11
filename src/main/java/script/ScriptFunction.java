package script;

public final class ScriptFunction {
   public FunctionData functionData;
   public CommandRegistry globalTable;
   public VariableReference[] upvalues;

   public ScriptFunction(FunctionData functionData, CommandRegistry globalTable) {
      this.functionData = functionData;
      this.globalTable = globalTable;
      this.upvalues = new VariableReference[functionData.upvalueCount];
   }

   public String toString() {
      return "";
   }
}