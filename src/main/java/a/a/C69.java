package a.a;

import a.GameUtils;
import a.b.C62;
import javax.microedition.lcdui.Image;

public final class C69 {
   private static byte C69_f956 = 1;
   private static byte C69_f957 = 2;

   public static C42 a(Image var0, C42 var1) {
      int var2 = var0.getWidth();
      int var3 = var0.getHeight();
      int[] var4 = new int[var2 * var3];
      var0.getRGB(var4, 0, var2, 0, 0, var2, var3);

      for(int var5 = 0; var5 < var4.length; ++var5) {
         if (var4[var5] == -1 || var4[var5] == -16777216) {
            var4[var5] = 16777215;
         }
      }

      var1.a(var4, var2, var3);
      return var1;
   }

   public static C42 a(C62 var0, int var1, int[] var2, byte var3, C42 var4) {
      Image var5;
      (var5 = Image.createImage(var2[2], var2[3])).getGraphics().setColor(0);
      var5.getGraphics().fillRect(0, 0, var2[2], var2[3]);
      var0.a(var5.getGraphics(), var1, -var2[0], -var2[1], (byte)var3, 20);
      var4.C42_f674 = var2[0];
      var4.C42_f675 = var2[1];
      return a(var5, var4);
   }

   public static C42 a(C42 var0, int var1, int var2) {
      int[] var3 = new int[var1 * var2];

      for(int var4 = 0; var4 < var2; ++var4) {
         for(int var5 = 0; var5 < var1; ++var5) {
            int var6 = var5 * var0.C42_f672 / var1;
            int var7 = var4 * var0.C42_f673 / var2;
            var3[var5 + var4 * var1] = var0.C42_f671[var6 + var7 * var0.C42_f672];
         }
      }

      var0.a(var3, var1, var2);
      var0.C42_f674 = var0.C42_f674 * var1 / var0.C42_f672 / 10;
      var0.C42_f675 = var0.C42_f675 * var1 / var0.C42_f672 / 10;
      return var0;
   }

   public static C42 a(C42 var0, int var1) {
      int var2 = var0.C42_f672 * var1 / 10;
      int var3 = var0.C42_f673 * var1 / 10;
      int[] var4 = new int[var2 * var3];

      for(int var5 = 0; var5 < var3; ++var5) {
         for(int var6 = 0; var6 < var2; ++var6) {
            int var7 = var6 * var0.C42_f672 / var2;
            int var8 = var5 * var0.C42_f673 / var3;
            var4[var6 + var5 * var2] = var0.C42_f671[var7 + var8 * var0.C42_f672];
         }
      }

      var0.a(var4, var2, var3);
      var0.C42_f674 = var0.C42_f674 * var1 / 10;
      var0.C42_f675 = var0.C42_f675 * var1 / 10;
      return var0;
   }

   public static C42 b(C42 var0, int var1, int var2) {
      for(int var7 = 0; var7 < var0.C42_f673; ++var7) {
         for(int var8 = 0; var8 < var0.C42_f672; ++var8) {
            int var5;
            int var6 = (var5 = var0.C42_f671[var7 * var0.C42_f672 + var8]) >> 24;
            int var3 = var5 >> 16 & 255;
            int var4 = var5 >> 8 & 255;
            var5 &= 255;
            var3 = var3 * var1 + var2;
            var4 = var4 * var1 + var2;
            var5 = var5 * var1 + var2;
            if (var3 > 255) {
               var3 = 255;
            } else if (var3 < 0) {
               var3 = 0;
            }

            if (var4 > 255) {
               var4 = 255;
            } else if (var4 < 0) {
               var4 = 0;
            }

            if (var5 > 255) {
               var5 = 255;
            } else if (var5 < 0) {
               var5 = 0;
            }

            var0.C42_f671[var7 * var0.C42_f672 + var8] = var6 << 24 | var3 << 16 | var4 << 8 | var5;
         }
      }

      return var0;
   }

   public static C42 b(C42 var0, int var1) {
      if (var1 >= 0 && var1 <= 255) {
         for(int var2 = 0; var2 < var0.C42_f676; ++var2) {
            if (var0.C42_f671[var2] != 16777215 && var0.C42_f671[var2] != 0) {
               if (var0.C42_f671[var2] == -16777216) {
                  var0.C42_f671[var2] = 0;
               } else {
                  var0.C42_f671[var2] = var1 << 24 | var0.C42_f671[var2] & 16777215;
               }
            }
         }

         return var0;
      } else {
         return var0;
      }
   }

   public static Image a(Image var0, int var1) {
      C42 var5 = new C42();
      int var2 = var0.getWidth();
      int var3 = var0.getHeight();
      Image var4;
      (var4 = Image.createImage(var2, var3)).getGraphics().setColor(0);
      var4.getGraphics().fillRect(0, 0, var2, var3);
      var4.getGraphics().drawImage(var0, 0, 0, 20);
      var5.a(GameUtils.extractImageRGB(var4), var2, var3);
      b(var5, 100);
      var0 = GameUtils.createRGBImage(var5.C42_f671, var5.C42_f672, var5.C42_f673, true);
      var5.C42_f671 = null;
      return var0;
   }

   public static C42 a(C42 var0, int var1, int var2, int var3, int var4) {
      for(int var5 = 0; var5 < var0.C42_f671.length; ++var5) {
         if (var0.C42_f671[var5] != 16777215) {
            if (var1 >= 0 && var1 <= 255) {
               var0.C42_f671[var5] = var1 << 24 | var2 << 16 | var3 << 8 | var4;
            } else {
               var0.C42_f671[var5] = var2 << 16 | var3 << 8 | var4;
            }
         }
      }

      return var0;
   }

   public static C42 a(C42 var0, C42 var1, byte var2) {
      int var5 = 0;
      int var6 = 0;
      if (var2 == 0) {
         var0 = b(var0, 5, 5);
      }

      for(int var7 = 0; var7 < var0.C42_f673; ++var7) {
         for(int var8 = 0; var8 < var0.C42_f672; ++var8) {
            int var3 = var0.C42_f671[var7 * var0.C42_f672 + var8];
            int var4 = var1.C42_f671[var6 * var1.C42_f672 + var5];
            if (var3 >> 24 != 0) {
               if (var2 == 0) {
                  var0.C42_f671[var7 * var0.C42_f672 + var8] = var3 & var4;
               } else if (var2 == C69_f956) {
                  var0.C42_f671[var7 * var0.C42_f672 + var8] = var3 | var4;
               } else if (var2 == C69_f957) {
                  var0.C42_f671[var7 * var0.C42_f672 + var8] = var4;
               }
            }

            if (var5 < var1.C42_f672 - 1) {
               ++var5;
            } else {
               var5 = 0;
            }
         }

         if (var6 < var1.C42_f673 - 1) {
            ++var6;
         } else {
            var6 = 0;
         }

         var5 = 0;
      }

      return var0;
   }

   public static Image a(Image var0) {
      C42 var4;
      (var4 = new C42()).a(GameUtils.extractImageRGB(var0), var0.getWidth(), var0.getHeight());

      for(int var5 = 0; var5 < var4.C42_f673; ++var5) {
         for(int var6 = 0; var6 < var4.C42_f672; ++var6) {
            int var2;
            int var3 = (var2 = var4.C42_f671[var5 * var4.C42_f672 + var6]) >> 24;
            int var7 = var2 >> 16 & 255;
            int var1 = var2 >> 8 & 255;
            var2 &= 255;
            var7 = var2 = (int)(0.299D * (double)var7 + 0.587D * (double)var1 + 0.114D * (double)var2);
            if (var4.C42_f671[var5 * var4.C42_f672 + var6] != -1 && var4.C42_f671[var5 * var4.C42_f672 + var6] != -16777216) {
               var4.C42_f671[var5 * var4.C42_f672 + var6] = var3 << 24 | var7 << 16 | var2 << 8 | var2;
            } else {
               int[] var10000 = var4.C42_f671;
               int var10001 = var5 * var4.C42_f672 + var6;
               var10000[var10001] &= 16777215;
            }
         }
      }

      var0 = GameUtils.createRGBImage(var4.C42_f671, var4.C42_f672, var4.C42_f673, true);
      var4.C42_f671 = null;
      return var0;
   }
}
