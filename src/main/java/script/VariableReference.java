package script;

public final class VariableReference {
   public ScriptEngine engine;
   public int stackIndex;
   public Object localValue;

   public Object getValue() {
      return this.engine == null ? this.localValue : this.engine.dataStack[this.stackIndex];
   }

   public void setValue(Object value) {
      if (this.engine == null) {
         this.localValue = value;
      } else {
         this.engine.dataStack[this.stackIndex] = value;
      }
   }
}