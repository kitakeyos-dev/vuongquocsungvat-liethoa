package me.kitakeyos;

import game.GameMIDLet;
import java.io.DataInputStream;
import java.nio.charset.StandardCharsets;
import javax.microedition.midlet.MIDlet;

public final class C50 {
   public static String C50_f754;
   public static String C50_f755;
   private static String[] C50_f756;
   private static String[] C50_f757;
   public static String[] C50_f758;
   private static int[] C50_f759;
   private static boolean C50_f760 = false;

   public static final void a(int var0) {
      if (C50_f756 != null && C50_f757 != null) {
         if (var0 < C50_f757.length) {
            String var1;
            if ((var1 = GameMIDLet.C15_f217.getAppProperty("uid")) == null) {
               var1 = "0";
            }

            var1 = a(C50_f757[var0], "%1", var1);
            String var2;
            if ((var2 = GameMIDLet.C15_f217.getAppProperty("Term")) == null) {
               var2 = "";
            }

            var1 = a(var1, "%2", var2);
            if ((var2 = GameMIDLet.C15_f217.getAppProperty("RefCode")) == null) {
               var2 = "";
            }

            var1 = a(a(var1, "%cp", var2).trim(), "  ", " ");
            C50_f754 = C50_f756[var0];
            C50_f755 = var1;
         }
      }
   }

   private static byte a(byte var0) {
      return (byte)(var0 <= 57 ? var0 - 48 : var0 + 10 - 97);
   }

   private static byte[] a(byte[] var0) {
      int var1;
      byte[] var2 = new byte[(var1 = var0.length) / 2];
      int var3 = 0;

      for(int var4 = 0; var3 < var1; ++var4) {
         var2[var4] |= a(var0[var3]);
         var2[var4] = (byte)(var2[var4] | a(var0[var3 + 1]) << 4);
         var3 += 2;
      }

      return var2;
   }

   private static String a(String var0, String var1, String var2) {
      StringBuffer var3 = new StringBuffer();
      int var4 = var0.indexOf(var1);
      int var5 = 0;

      for(int var6 = var1.length(); var4 != -1; var4 = var0.indexOf(var1, var5)) {
         var3.append(var0.substring(var5, var4)).append(var2);
         var5 = var4 + var6;
      }

      var3.append(var0.substring(var5, var0.length()));
      return var3.toString();
   }

   private static void a(byte[] var0, byte[] var1) {
      int var2 = var0.length;
      int var3 = var1.length;

      for(int var4 = 0; var4 < var2; ++var4) {
         int var5 = var3 - var4 % 3;

         for(int var6 = 0; var6 < var5; ++var6) {
            var0[var4] ^= var1[var6];
         }
      }

   }

   private static String b(byte[] var0) {
      String var1;
      try {
         var1 = new String(var0, StandardCharsets.UTF_8);
      } catch (Exception var3) {
         try {
            var1 = new String(var0, StandardCharsets.UTF_8);
         } catch (Exception var2) {
            var1 = new String(var0);
         }
      }

      return var1;
   }

   private static String[] a(String var0, String var1) {
      if (var0 == null) {
         System.out.println(" split. aStr == null");
         return null;
      } else if (!var0.isEmpty() && var1 != null) {
         if (var1.length() <= 0) {
            return new String[]{var0};
         } else {
            String[] var2 = null;
            String var3 = var0;
            var1 = var1;
            int[] var4 = new int[var0.length()];
            int[] var5 = new int[var0.length()];
            int var6 = 0;
            boolean var7 = false;
            int var8 = 0;
            boolean var9 = false;

            do {
               boolean var10 = false;
               int var11;
               if ((var11 = var8 + var1.length()) <= var3.length() && var3.substring(var8, var11).equals(var1)) {
                  var10 = true;
               }

               if (var10) {
                  if (var7) {
                     var7 = false;
                     var5[var6++] = var8;
                  }

                  var8 = var11;
               } else {
                  if (!var7) {
                     var7 = true;
                     var4[var6] = var8;
                  }

                  ++var8;
               }
            } while(var8 < var3.length());

            if (var7) {
               var5[var6++] = var3.length();
            }

            if (var6 > 0) {
               var2 = new String[var6];

               for(int var12 = 0; var12 < var6; ++var12) {
                  var2[var12] = var0.substring(var4[var12], var5[var12]);
               }
            }

            return var2;
         }
      } else {
         return new String[]{var0};
      }
   }

   public static void a(MIDlet var0) {
      if (!C50_f760) {
         C50_f760 = true;
         String var1 = var0.getAppProperty("sr");
         boolean var2 = true;
         String[] var3 = null;
         byte[] var8;
         if (var1 == null) {
            try {
               DataInputStream var4;
               var8 = new byte[(var4 = new DataInputStream("".getClass().getResourceAsStream("/l2.bin"))).available()];
               var4.read(var8);
               var4.close();
               var8 = (var3 = a(b(var8), String.valueOf('\n')))[0].trim().getBytes();
            } catch (Exception var7) {
               var7.printStackTrace();
               return;
            }

            var2 = false;
         } else {
            var8 = var1.getBytes();
         }

         byte[] var10 = new byte[6];
         int var5 = var8.length;
         System.arraycopy(var8, 0, var10, 0, 3);
         System.arraycopy(var8, var5 - 3, var10, 3, 3);
         byte[] var6 = new byte[var5 - 6];
         System.arraycopy(var8, 3, var6, 0, var5 - 6);
         a(var6 = a(var6), var10);
         var8 = new byte[]{1, 2, 5, 7, 4};
         var5 = var6.length - 5;

         int var11;
         for(var11 = 0; var11 < var5; ++var11) {
            var8[var11 % 5] ^= var6[var11];
         }

         for(var11 = 0; var11 < 5; ++var11) {
            if (var8[var11] != var6[var5 + var11]) {
               return;
            }
         }

         var8 = var6;
         var6 = new byte[var5];
         System.arraycopy(var8, 0, var6, 0, var5);
         String[] var12;
         C50_f756 = new String[var5 = Integer.parseInt((var12 = a(b(var6), "|"))[0])];
         C50_f757 = new String[var5];
         C50_f759 = new int[var5];

         int var9;
         for(var9 = 0; var9 < var5; ++var9) {
            C50_f756[var9] = var12[var9 + 1];
            C50_f759[var9] = Integer.parseInt(var12[var9 + var5 + 1]);
            C50_f757[var9] = var12[var9 + var5 + var5 + 1];
         }

         C50_f758 = new String[var5];
         if (var2) {
            for(var9 = 0; var9 < var5; ++var9) {
               C50_f758[var9] = var0.getAppProperty("sr" + (var9 + 1));
            }

         } else {
            for(var9 = 0; var9 < var5; ++var9) {
               C50_f758[var9] = var3[var9 + 1].trim();
            }

         }
      }
   }
}
