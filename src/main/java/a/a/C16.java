package a.a;

import java.util.Vector;

public final class C16 {
   private static Vector C16_f218 = new Vector();

   public static void a(Throwable var0, String var1) {
      String[] var2 = new String[]{"", ""};
      if (var1 != null) {
         var2[0] = var1;
         System.out.println(var2[0]);
      } else {
         var2[0] = "";
         System.out.println(var2[0]);
      }

      if (var0 != null) {
         var2[1] = var0.toString();
      } else {
         var2[1] = "";
      }

      C16_f218.addElement(var2);
   }
}
