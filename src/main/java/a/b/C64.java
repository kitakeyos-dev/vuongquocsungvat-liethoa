package a.b;

import a.GameUtils;

public final class C64 {
   private static C37[] C64_f892;

   public static void a(int var0) {
      C64_f892 = new C37[1000];
   }

   public static C37 b(int var0) {
      if (C64_f892[var0] == null) {
         C64_f892[var0] = new C37();
         byte[] var1 = new byte[20000];
         int[] var2 = new int[]{0};
         GameUtils.loadStreamToByteArray(var1, "/data/spr/spr_" + var0 + "_all(r)", 0);
         C37 var10000 = C64_f892[var0];
         short[] var10002 = C64_f892[var0].C37_f568;
         var10000.C37_f568 = GameUtils.readShortArray(var1, var2);
         var10000 = C64_f892[var0];
         short[][] var3 = C64_f892[var0].C37_f571;
         var10000.C37_f571 = GameUtils.readShortMatrix(var1, var2);
         var10000 = C64_f892[var0];
         var3 = C64_f892[var0].C37_f572;
         var10000.C37_f572 = GameUtils.readShortMatrix(var1, var2);
         C64_f892[var0].C37_f570 = a(GameUtils.readShortArray(var1, var2), C64_f892[var0].C37_f571.length);
         C64_f892[var0].C37_f569 = a(GameUtils.readShortArray(var1, var2), C64_f892[var0].C37_f571.length);
      }

      ++C64_f892[var0].C37_f567;
      return C64_f892[var0];
   }

   private static short[][] a(short[] var0, int var1) {
      if (var0 == null) {
         return null;
      } else {
         short[][] var3 = new short[var1][];

         for(int var2 = 0; var2 < var0.length / 5; ++var2) {
            var3[var0[var2 * 5]] = GameUtils.concatenateShortArrays(var3[var0[var2 * 5]], new short[]{var0[var2 * 5 + 1], var0[var2 * 5 + 2], var0[var2 * 5 + 3], var0[var2 * 5 + 4]});
         }

         return var3;
      }
   }

   public static void c(int var0) {
      --C64_f892[var0].C37_f567;
      if (C64_f892[var0].C37_f567 <= 0) {
         C64_f892[var0].C37_f567 = 0;
      }

   }

   public static boolean d(int var0) {
      if (C64_f892[var0].C37_f567 <= 0) {
         C64_f892[var0].C37_f567 = 0;
         C64_f892[var0] = null;
         return true;
      } else {
         return false;
      }
   }
}
