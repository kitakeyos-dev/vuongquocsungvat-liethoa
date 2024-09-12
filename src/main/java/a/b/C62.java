package a.b;

import a.a.C69;
import game.C7;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class C62 {
   private static int[] C62_f875 = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
   private static int[] C62_f876 = new int[]{2, 4, 1, 7, 0, 5, 3, 6};
   private static int[] C62_f877 = new int[]{3, 6, 0, 5, 1, 7, 2, 4};
   private static int[] C62_f878 = new int[]{1, 7, 2, 4, 3, 6, 0, 5};
   private Image[] C62_f879;
   private C37 C62_f880;
   private int[] C62_f881;
   public int C62_f882;
   private int C62_f883;
   private int C62_f884;
   private byte C62_f885;
   private int C62_f886;
   private int C62_f887;
   protected byte C62_f888;
   private boolean C62_f889 = true;

   public final boolean a(int var1, boolean var2) {
      this.C62_f881 = new int[C67.C67_f921[var1].length - 1];
      this.C62_f879 = new Image[C67.C67_f921[var1].length - 1];

      for(int var3 = 0; var3 < this.C62_f879.length; ++var3) {
         this.C62_f881[var3] = C67.C67_f921[var1][var3 + 1];
         this.C62_f879[var3] = C67.b(this.C62_f881[var3]);
      }

      this.C62_f882 = var1;
      this.C62_f880 = C64.b(C67.C67_f921[var1][0]);
      this.C62_f880.C37_f573 = var2;
      this.c(0);
      return true;
   }

   public final void a() {
      for(int var1 = 0; var1 < this.C62_f881.length; ++var1) {
         this.C62_f879[var1] = null;
         C67.c(this.C62_f881[var1]);
      }

      this.C62_f881 = null;
      this.C62_f880 = null;
      C64.c(this.C62_f882);
   }

   public final void b() {
      if (this.C62_f881 != null) {
         for(int var1 = 0; var1 < this.C62_f881.length; ++var1) {
            this.C62_f879[var1] = null;
            C67.d(this.C62_f881[var1]);
         }

         this.C62_f881 = null;
         this.C62_f880 = null;
         C64.d(this.C62_f882);
      }
   }

   public final void a(int var1, int var2, boolean var3) {
      this.C62_f879[var1] = null;
      if (var3) {
         C67.d(this.C62_f881[var1]);
      } else {
         C67.c(this.C62_f881[var1]);
      }

      this.C62_f881[var1] = var2;
      this.C62_f879[var1] = C67.b(this.C62_f881[var1]);
   }

   public final void c() {
      if (C7.C7_f51 == 1) {
         for(int var1 = 0; var1 < this.C62_f879.length; ++var1) {
            this.C62_f879[var1] = C69.a(C67.b(this.C62_f881[var1]));
         }
      }

   }

   public final void d() {
      for(int var1 = 0; var1 < this.C62_f879.length; ++var1) {
         if (C7.C7_f51 == 1) {
            this.C62_f879[var1] = C69.a(C67.b(this.C62_f881[var1]));
         } else {
            this.b();
         }
      }

      if (C7.C7_f51 == 0) {
         this.a(this.C62_f882, false);
      }

   }

   public final void a(int var1) {
      for(int var2 = 0; var2 < this.C62_f879.length; ++var2) {
         if (var1 == 1) {
            this.C62_f879[var2] = C69.a((Image)C67.b(this.C62_f881[var2]), 100);
         } else {
            this.b();
         }
      }

      if (var1 == 0) {
         this.a(this.C62_f882, false);
      }

   }

   public final boolean a(byte var1, byte var2, boolean var3) {
      if (this.C62_f888 == var1 && !var3) {
         this.C62_f888 = var1;
      } else {
         this.C62_f888 = var1;
         this.c(0);
      }

      this.C62_f885 = var2;
      return true;
   }

   public final void a(byte var1, byte var2) {
      this.C62_f888 = var1;
      this.C62_f885 = var2;
   }

   private void c(int var1) {
      this.C62_f886 = var1;
      if (this.C62_f880.C37_f572 != null) {
         if (this.C62_f880.C37_f573) {
            this.C62_f883 = this.C62_f880.C37_f572[this.C62_f888][this.C62_f886 << 2];
            this.C62_f887 = this.C62_f880.C37_f572[this.C62_f888].length / 4;
         } else {
            this.C62_f883 = this.C62_f880.C37_f572[this.C62_f888][this.C62_f886 << 1];
            this.C62_f887 = this.C62_f880.C37_f572[this.C62_f888].length / 2;
         }

         this.C62_f884 = 0;
         if (this.C62_f883 > 0) {
            --this.C62_f883;
         } else {
            if (this.C62_f884 > 0) {
               --this.C62_f884;
            }

         }
      }
   }

   public final boolean e() {
      if (!this.C62_f889) {
         return false;
      } else {
         if (this.C62_f883 > 0) {
            --this.C62_f883;
         } else if (this.C62_f884 > 0) {
            --this.C62_f884;
         } else {
            ++this.C62_f886;
            if (this.C62_f886 >= this.C62_f887) {
               if (this.C62_f885 >= 0) {
                  this.a(this.C62_f885, (byte)-1, true);
               } else if (this.C62_f885 == -2) {
                  --this.C62_f886;
                  this.c(this.C62_f886);
               } else if (this.C62_f885 == -1) {
                  this.c(0);
               }

               return true;
            }

            this.c(this.C62_f886);
         }

         return false;
      }
   }

   public final boolean f() {
      return this.C62_f886 >= this.C62_f887 - 1;
   }

   public final boolean b(int var1) {
      return this.C62_f886 == var1;
   }

   public final byte g() {
      return this.C62_f888;
   }

   public final int h() {
      return this.C62_f886;
   }

   public final int[] a(int var1, byte var2) {
      if (var1 >= 0 && var1 < this.C62_f880.C37_f572.length) {
         int[] var3;
         int var4 = (var3 = this.b(this.C62_f880.C37_f572[var1][1], var2))[0];
         int var5 = var3[0] + var3[2];
         int var6 = var3[1];
         int var7 = var3[1] + var3[3];
         int var8;
         if (this.C62_f880.C37_f573) {
            for(var8 = 1; var8 != this.C62_f880.C37_f572[var1].length / 4; ++var8) {
               if ((var3 = this.b(this.C62_f880.C37_f572[var1][(var8 << 2) + 1], var2)) != null) {
                  if (var4 > var3[0]) {
                     var4 = var3[0];
                  }

                  if (var5 < var3[0] + var3[2]) {
                     var5 = var3[0] + var3[2];
                  }

                  if (var6 > var3[1]) {
                     var6 = var3[1];
                  }

                  if (var7 < var3[1] + var3[3]) {
                     var7 = var3[1] + var3[3];
                  }
               }
            }
         } else {
            for(var8 = 1; var8 != this.C62_f880.C37_f572[var1].length / 2; ++var8) {
               if ((var3 = this.b(this.C62_f880.C37_f572[var1][(var8 << 1) + 1], var2)) != null) {
                  if (var4 > var3[0]) {
                     var4 = var3[0];
                  }

                  if (var5 < var3[0] + var3[2]) {
                     var5 = var3[0] + var3[2];
                  }

                  if (var6 > var3[1]) {
                     var6 = var3[1];
                  }

                  if (var7 < var3[1] + var3[3]) {
                     var7 = var3[1] + var3[3];
                  }
               }
            }
         }

         return new int[]{var4, var6, var5 - var4, var7 - var6};
      } else {
         return null;
      }
   }

   public final int[] b(int var1, byte var2) {
      if (this.C62_f880.C37_f571[var1].length <= 0) {
         return null;
      } else {
         short var3 = this.C62_f880.C37_f571[var1][0];
         int var4 = this.C62_f880.C37_f571[var1][1];
         int var5 = this.C62_f880.C37_f571[var1][2];

         for(int var6 = 0; var6 < this.C62_f880.C37_f571[var1].length; var6 += 4) {
            var3 = this.C62_f880.C37_f571[var1][var6];
            if (this.C62_f880.C37_f571[var1][var6 + 1] < var4) {
               var4 = this.C62_f880.C37_f571[var1][var6 + 1];
            }

            if (this.C62_f880.C37_f571[var1][var6 + 2] < var5) {
               var5 = this.C62_f880.C37_f571[var1][var6 + 2];
            }
         }

         int[] var10 = new int[2];
         int var7 = (var10 = this.a(0, var3, var1, var4, var5, var10))[0];
         int var8 = var10[1];

         int var9;
         for(var9 = 0; var9 < this.C62_f880.C37_f571[var1].length; var9 += 4) {
            var3 = this.C62_f880.C37_f571[var1][var9];
            if ((var10 = this.a(var9, var3, var1, var4, var5, var10))[0] > var7) {
               var7 = var10[0];
            }

            if (var10[1] > var8) {
               var8 = var10[1];
            }
         }

         switch(var2) {
         case 1:
            var3 = this.C62_f880.C37_f571[var1][0];
            if (this.C62_f880.C37_f571[var1][3] % 2 == 1) {
               var4 = -this.C62_f880.C37_f571[var1][1] - this.C62_f880.C37_f568[var3 * 5 + 4];
            } else {
               var4 = -this.C62_f880.C37_f571[var1][1] - this.C62_f880.C37_f568[var3 * 5 + 3];
            }

            for(var9 = 0; var9 < this.C62_f880.C37_f571[var1].length; var9 += 4) {
               var3 = this.C62_f880.C37_f571[var1][var9];
               if (this.C62_f880.C37_f571[var1][var9 + 3] % 2 == 1) {
                  if (-this.C62_f880.C37_f571[var1][var9 + 1] - this.C62_f880.C37_f568[var3 * 5 + 4] < var4) {
                     var4 = -this.C62_f880.C37_f571[var1][var9 + 1] - this.C62_f880.C37_f568[var3 * 5 + 4];
                  }
               } else if (-this.C62_f880.C37_f571[var1][var9 + 1] - this.C62_f880.C37_f568[var3 * 5 + 3] < var4) {
                  var4 = -this.C62_f880.C37_f571[var1][var9 + 1] - this.C62_f880.C37_f568[var3 * 5 + 3];
               }
            }
         case 2:
         default:
            break;
         case 3:
            var3 = this.C62_f880.C37_f571[var1][0];
            if (this.C62_f880.C37_f571[var1][3] % 2 == 1) {
               var4 = -this.C62_f880.C37_f571[var1][1] - this.C62_f880.C37_f568[var3 * 5 + 4];
               var5 = -this.C62_f880.C37_f571[var1][2] - this.C62_f880.C37_f568[var3 * 5 + 3];
            } else {
               var4 = -this.C62_f880.C37_f571[var1][1] - this.C62_f880.C37_f568[var3 * 5 + 3];
               var5 = -this.C62_f880.C37_f571[var1][2] - this.C62_f880.C37_f568[var3 * 5 + 4];
            }

            for(var9 = 0; var9 < this.C62_f880.C37_f571[var1].length; var9 += 4) {
               var3 = this.C62_f880.C37_f571[var1][var9];
               if (this.C62_f880.C37_f571[var1][var9 + 3] % 2 == 1) {
                  if (-this.C62_f880.C37_f571[var1][var9 + 1] - this.C62_f880.C37_f568[var3 * 5 + 4] < var4) {
                     var4 = -this.C62_f880.C37_f571[var1][var9 + 1] - this.C62_f880.C37_f568[var3 * 5 + 4];
                  }

                  if (-this.C62_f880.C37_f571[var1][var9 + 2] - this.C62_f880.C37_f568[var3 * 5 + 3] < var5) {
                     var5 = -this.C62_f880.C37_f571[var1][var9 + 2] - this.C62_f880.C37_f568[var3 * 5 + 3];
                  }
               } else {
                  if (-this.C62_f880.C37_f571[var1][var9 + 1] - this.C62_f880.C37_f568[var3 * 5 + 3] < var4) {
                     var4 = -this.C62_f880.C37_f571[var1][var9 + 1] - this.C62_f880.C37_f568[var3 * 5 + 3];
                  }

                  if (-this.C62_f880.C37_f571[var1][var9 + 2] - this.C62_f880.C37_f568[var3 * 5 + 4] < var5) {
                     var5 = -this.C62_f880.C37_f571[var1][var9 + 2] - this.C62_f880.C37_f568[var3 * 5 + 4];
                  }
               }
            }

            return new int[]{var4, var5, var7, var8};
         case 4:
            var3 = this.C62_f880.C37_f571[var1][0];
            if (this.C62_f880.C37_f571[var1][3] % 2 == 1) {
               var5 = -this.C62_f880.C37_f571[var1][2] - this.C62_f880.C37_f568[var3 * 5 + 3];
            } else {
               var5 = -this.C62_f880.C37_f571[var1][2] - this.C62_f880.C37_f568[var3 * 5 + 4];
            }

            for(var9 = 0; var9 < this.C62_f880.C37_f571[var1].length; var9 += 4) {
               var3 = this.C62_f880.C37_f571[var1][var9];
               if (this.C62_f880.C37_f571[var1][var9 + 3] % 2 == 1) {
                  if (-this.C62_f880.C37_f571[var1][var9 + 2] - this.C62_f880.C37_f568[var3 * 5 + 3] < var5) {
                     var5 = -this.C62_f880.C37_f571[var1][var9 + 2] - this.C62_f880.C37_f568[var3 * 5 + 3];
                  }
               } else if (-this.C62_f880.C37_f571[var1][var9 + 2] - this.C62_f880.C37_f568[var3 * 5 + 4] < var5) {
                  var5 = -this.C62_f880.C37_f571[var1][var9 + 2] - this.C62_f880.C37_f568[var3 * 5 + 4];
               }
            }
         }

         return new int[]{var4, var5, var7, var8};
      }
   }

   private int[] a(int var1, int var2, int var3, int var4, int var5, int[] var6) {
      if (this.C62_f880.C37_f571[var3][var1 + 3] % 2 == 1) {
         var6[1] = this.C62_f880.C37_f571[var3][var1 + 2] - var5 + this.C62_f880.C37_f568[var2 * 5 + 3];
         var6[0] = this.C62_f880.C37_f571[var3][var1 + 1] - var4 + this.C62_f880.C37_f568[var2 * 5 + 4];
      } else {
         var6[0] = this.C62_f880.C37_f571[var3][var1 + 1] - var4 + this.C62_f880.C37_f568[var2 * 5 + 3];
         var6[1] = this.C62_f880.C37_f571[var3][var1 + 2] - var5 + this.C62_f880.C37_f568[var2 * 5 + 4];
      }

      return var6;
   }

   public final void a(Graphics var1, int var2, int var3, byte var4) {
      if (this.C62_f880.C37_f573) {
         this.a(var1, this.C62_f880.C37_f572[this.C62_f888][(this.C62_f886 << 2) + 1], var2, var3, (byte)var4, 20);
      } else {
         this.a(var1, this.C62_f880.C37_f572[this.C62_f888][(this.C62_f886 << 1) + 1], var2, var3, (byte)var4, 20);
      }
   }

   public final void a(Graphics var1, int var2, int var3, int var4, byte var5, int var6) {
      if (this.C62_f880.C37_f571[var2].length > 0) {
         int var7;
         switch(var5) {
         case 0:
            for(var7 = 0; var7 < this.C62_f880.C37_f571[var2].length; var7 += 4) {
               this.a(var1, this.C62_f880.C37_f571[var2][var7], var3 + this.C62_f880.C37_f571[var2][var7 + 1], var4 + this.C62_f880.C37_f571[var2][var7 + 2], (int)C62_f875[this.C62_f880.C37_f571[var2][var7 + 3]], 20);
            }

            return;
         case 1:
            for(var7 = 0; var7 < this.C62_f880.C37_f571[var2].length; var7 += 4) {
               if (this.C62_f880.C37_f571[var2][var7 + 3] % 2 == 1) {
                  this.a(var1, this.C62_f880.C37_f571[var2][var7], var3 - this.C62_f880.C37_f571[var2][var7 + 1] - this.C62_f880.C37_f568[this.C62_f880.C37_f571[var2][var7] * 5 + 4], var4 + this.C62_f880.C37_f571[var2][var7 + 2], (int)C62_f876[this.C62_f880.C37_f571[var2][var7 + 3]], 20);
               } else {
                  this.a(var1, this.C62_f880.C37_f571[var2][var7], var3 - this.C62_f880.C37_f571[var2][var7 + 1] - this.C62_f880.C37_f568[this.C62_f880.C37_f571[var2][var7] * 5 + 3], var4 + this.C62_f880.C37_f571[var2][var7 + 2], (int)C62_f876[this.C62_f880.C37_f571[var2][var7 + 3]], 20);
               }
            }

            return;
         case 3:
            for(var7 = 0; var7 < this.C62_f880.C37_f571[var2].length; var7 += 4) {
               if (this.C62_f880.C37_f571[var2][var7 + 3] % 2 == 1) {
                  this.a(var1, this.C62_f880.C37_f571[var2][var7], var3 - this.C62_f880.C37_f571[var2][var7 + 1] - this.C62_f880.C37_f568[this.C62_f880.C37_f571[var2][var7] * 5 + 4], var4 - this.C62_f880.C37_f571[var2][var7 + 2] - this.C62_f880.C37_f568[this.C62_f880.C37_f571[var2][var7] * 5 + 3], (int)C62_f877[this.C62_f880.C37_f571[var2][var7 + 3]], 20);
               } else {
                  this.a(var1, this.C62_f880.C37_f571[var2][var7], var3 - this.C62_f880.C37_f571[var2][var7 + 1] - this.C62_f880.C37_f568[this.C62_f880.C37_f571[var2][var7] * 5 + 3], var4 - this.C62_f880.C37_f571[var2][var7 + 2] - this.C62_f880.C37_f568[this.C62_f880.C37_f571[var2][var7] * 5 + 4], (int)C62_f877[this.C62_f880.C37_f571[var2][var7 + 3]], 20);
               }
            }

            return;
         case 4:
            for(var7 = 0; var7 < this.C62_f880.C37_f571[var2].length; var7 += 4) {
               if (this.C62_f880.C37_f571[var2][var7 + 3] % 2 == 1) {
                  this.a(var1, this.C62_f880.C37_f571[var2][var7], var3 + this.C62_f880.C37_f571[var2][var7 + 1], var4 - this.C62_f880.C37_f571[var2][var7 + 2] - this.C62_f880.C37_f568[this.C62_f880.C37_f571[var2][var7] * 5 + 3], (int)C62_f878[this.C62_f880.C37_f571[var2][var7 + 3]], 20);
               } else {
                  this.a(var1, this.C62_f880.C37_f571[var2][var7], var3 + this.C62_f880.C37_f571[var2][var7 + 1], var4 - this.C62_f880.C37_f571[var2][var7 + 2] - this.C62_f880.C37_f568[this.C62_f880.C37_f571[var2][var7] * 5 + 4], (int)C62_f878[this.C62_f880.C37_f571[var2][var7 + 3]], 20);
               }
            }
         case 2:
         default:
         }
      }
   }

   private void a(Graphics var1, int var2, int var3, int var4, int var5, int var6) {
      var1.drawRegion(this.C62_f879[this.C62_f880.C37_f568[var2 * 5]], this.C62_f880.C37_f568[var2 * 5 + 1], this.C62_f880.C37_f568[var2 * 5 + 2], this.C62_f880.C37_f568[var2 * 5 + 3], this.C62_f880.C37_f568[var2 * 5 + 4], var5, var3, var4, var6);
   }

   public final boolean i() {
      return this.C62_f880 == null || this.C62_f880.C37_f569 == null;
   }

   public final short[] j() {
      if (this.C62_f880.C37_f569 == null) {
         return null;
      } else {
         return this.C62_f880.C37_f573 ? this.C62_f880.C37_f569[this.C62_f880.C37_f572[this.C62_f888][(this.C62_f886 << 2) + 1]] : this.C62_f880.C37_f569[this.C62_f880.C37_f572[this.C62_f888][(this.C62_f886 << 1) + 1]];
      }
   }

   public final short[] k() {
      if (this.C62_f880.C37_f570 == null) {
         return null;
      } else {
         return this.C62_f880.C37_f573 ? this.C62_f880.C37_f570[this.C62_f880.C37_f572[this.C62_f888][(this.C62_f886 << 2) + 1]] : this.C62_f880.C37_f570[this.C62_f880.C37_f572[this.C62_f888][(this.C62_f886 << 1) + 1]];
      }
   }
}
