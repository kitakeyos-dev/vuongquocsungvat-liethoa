package a.b;

import a.GameUtils;
import b.C57;
import game.C25;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;
import me.kitakeyos.ManagedInputStream;

public class C67 {
   public static short[][] C67_f921;
   public static short[][] C67_f922;
   public static short[][][] C67_f923;
   public static String[] C67_f924;
   private static C57[] C67_f925;
   public static int[][] C67_f926;
   public static Image C67_f927;
   private static Image[] C67_f928;
   private static byte[] C67_f929;
   private static int C67_f930;

   public static void a() {
      String var0 = null;
      C67_f921 = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/sprite.mid"));
      var0 = "/data/mod/modInfo.mid";

      try {
         InputStream var8 = ManagedInputStream.openStream(var0);
         DataInputStream var1;
         byte var2;
         C67_f922 = new short[var2 = (var1 = new DataInputStream(var8)).readByte()][];

         for(int var3 = 0; var3 < var2; ++var3) {
            byte var4 = var1.readByte();
            C67_f922[var3] = new short[var4];

            for(int var5 = 0; var5 < var4; ++var5) {
               short var6 = var1.readShort();
               C67_f922[var3][var5] = var6;
            }
         }

         var1.close();
         var8.close();
      } catch (IOException var7) {
         var7.printStackTrace();
      }

      c("/data/script/chs.mid");
      a("/data/script/npcDialog.mid");
      c();
      d("/data/event/worldEvt.mid");
      b("/data/script/db.mid");
      C67_f927 = GameUtils.loadImage("/data/tex/", "bk");
   }

   private static void a(String var0) {
      String[][] var2;
      C25.C25_f349 = new String[(var2 = GameUtils.readStringMatrix(GameUtils.openInputStream(var0))).length];

      for(int var1 = 0; var1 < var2.length; ++var1) {
         System.arraycopy(var2[var1], 0, C25.C25_f349, var1, var2[var1].length);
      }

   }

   public static short a(byte var0, short var1, byte var2) {
      return C67_f923[var0][var1][var2];
   }

   private static void b(String var0) {
      InputStream var2 = GameUtils.openInputStream(var0);
      C67_f923 = new short[9][][];

      for(int var1 = 0; var1 < 9; ++var1) {
         C67_f923[var1] = GameUtils.readShortMatrix(var2);
      }

   }

   private static void c(String var0) {
      String[][] var4 = GameUtils.readStringMatrix(GameUtils.openInputStream(var0));
      C67_f924 = new String[var4.length];
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 0; var2 < var4.length; ++var2) {
         var1.delete(0, var1.length());

         for(int var3 = 0; var3 < var4[var2].length; ++var3) {
            var1.append(var4[var2][var3]);
         }

         C67_f924[var2] = var1.toString();
      }

   }

   private static void c() {
      C67_f926 = new int[4][];

      for(int var0 = 0; var0 < 4; ++var0) {
         C67_f926[var0] = GameUtils.extractImageRGB(GameUtils.loadImage("/data/tex/", "tex_" + var0));
      }

   }

   private static void d(String var0) {
      try {
         "".getClass();
         InputStream var9 = ManagedInputStream.openStream(var0);
         DataInputStream var1;
         short var2 = (var1 = new DataInputStream(var9)).readShort();
         String[] var3 = null;
         if (var2 > 0) {
            var3 = new String[var2];

            for(int var4 = 0; var4 < var2; ++var4) {
               byte var5 = var1.readByte();
               StringBuffer var6 = new StringBuffer();

               for(int var7 = 0; var7 < var5; ++var7) {
                  var6.append((char)(var1.read() << 8 | var1.read()));
               }

               var3[var4] = var6.toString();
            }
         }

         if ((var2 = var1.readByte()) > 0) {
            C67_f925 = new C57[var2];

            for(byte var10 = 0; var10 < var2; ++var10) {
               C67_f925[var10] = new C57();
               C67_f925[var10].a(var1, var10, -1, var3);
            }
         }

         var1.close();
         var9.close();
      } catch (Exception var8) {
      }
   }

   public static void a(int var0) {
      C67_f930 = 50000;
      C67_f928 = new Image['썐'];
      C67_f929 = new byte[C67_f930];
   }

   public static Image b(int var0) {
      if (C67_f928[var0] == null) {
         C67_f928[var0] = GameUtils.loadImage("/data/img/", "img_" + var0);
      }

      ++C67_f929[var0];
      return C67_f928[var0];
   }

   public static void c(int var0) {
      --C67_f929[var0];
      if (C67_f929[var0] <= 0) {
         C67_f929[var0] = 0;
      }

   }

   public static boolean d(int var0) {
      --C67_f929[var0];
      if (C67_f929[var0] <= 0) {
         C67_f929[var0] = 0;
         C67_f928[var0] = null;
         return true;
      } else {
         return false;
      }
   }

   public static void b() {
      for(int var0 = 0; var0 < C67_f930; ++var0) {
         C67_f929[var0] = 0;
         C67_f928[var0] = null;
      }

   }
}
