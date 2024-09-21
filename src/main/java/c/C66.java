package c;

import javax.microedition.lcdui.Graphics;

public final class C66 implements IComponent {
   private int C66_f893;
   private int C66_f894;
   private int C66_f895;
   private int C66_f896;
   private int C66_f897;
   private int C66_f898;
   private int C66_f899;
   private int C66_f900;
   private int C66_f901;
   private int C66_f902;
   private int C66_f903;
   private int C66_f904;
   private int C66_f905;
   private int C66_f906;
   private int C66_f907;
   private int C66_f908;
   private int C66_f909;
   private int C66_f910;
   private int C66_f911;
   public int C66_f912;
   public C17 C66_f913;
   public C17 C66_f914;
   public boolean C66_f915;
   private boolean C66_f916 = true;
   private int C66_f917;
   private int C66_f918;
   private int C66_f919;
   C49[] C66_f920;

   public final void a(int var1) {
      this.C66_f895 = var1;
   }

   public final void b(int var1) {
      this.C66_f896 = var1;
   }

   public final void c(int var1) {
      this.C66_f897 = var1;
   }

   public final void d(int var1) {
      this.C66_f898 = var1;
   }

   public final int a() {
      return this.C66_f899;
   }

   public final void e(int var1) {
      this.C66_f899 = var1;
   }

   public final int m() {
      return this.C66_f900;
   }

   public final void f(int var1) {
      this.C66_f900 = var1;
   }

   public final void g(int var1) {
      if (var1 > 0 && var1 <= this.C66_f899) {
         this.C66_f903 = var1;
      } else {
         this.C66_f903 = this.C66_f899;
      }
   }

   public final void h(int var1) {
      if (var1 > 0 && var1 <= this.C66_f900) {
         this.C66_f905 = var1;
      } else {
         this.C66_f905 = this.C66_f900;
      }
   }

   public final void i(int var1) {
      this.C66_f904 = var1;
   }

   public final void j(int var1) {
      this.C66_f906 = var1;
   }

   private int o() {
      return this.C66_f906 + this.C66_f905 - 1;
   }

   private int p() {
      return this.C66_f904 + this.C66_f903 - 1;
   }

   public final void k(int var1) {
      this.C66_f901 = var1;
   }

   public final void l(int var1) {
      this.C66_f902 = var1;
   }

   public final void m(int var1) {
      this.C66_f907 = var1;
   }

   public final void n(int var1) {
      this.C66_f909 = var1;
   }

   public final void o(int var1) {
      this.C66_f908 = var1;
   }

   public final void p(int var1) {
      this.C66_f910 = var1;
   }

   public final C49[] n() {
      int var10000 = this.C66_f899;
      int var3 = this.C66_f900;
      int var2 = var10000;
      C49[] var5 = new C49[var10000 * var3];

      int var6;
      C49 var7;
      for(var6 = 0; var6 != var2 * var3; ++var6) {
         var7 = new C49(var6 - var2, var6 + var2, var6 - 1, var6 + 1);
         var5[var6] = var7;
      }

      int var18 = this.C66_f900;
      int var4 = this.C66_f899;
      C49[] var17 = var5;
      C66 var16 = this;

      C49 var8;
      int var9;
      int var10;
      int var11;
      int var12;
      int var13;
      for(var6 = 0; var6 != var18; ++var6) {
         var7 = new C49(-1, (byte)0, 0, 0, 0, 0, 0);
         var8 = new C49(-1, (byte)0, 0, 0, 0, 0, 0);
         var9 = -1;
         var10 = -1;

         for(var11 = 0; var11 != var4; ++var11) {
            if (var7.a() == 0 && var17[var6 * var4 + var11].a() == 1) {
               var7 = var17[var6 * var4 + var11];
               var9 = var6 * var4 + var11;
            }

            if (var8.a() == 0 && var17[(var6 + 1) * var4 - var11 - 1].a() == 1) {
               var8 = var17[(var6 + 1) * var4 - var11 - 1];
               var10 = (var6 + 1) * var4 - var11 - 1;
            }
         }

         if (var7.a() == 1) {
            if (var16.C66_f907 == 0) {
               var7.c(var9);
               var8.d(var10);
            } else {
               var9 = var9;
               C49[] var19 = var17;
               var11 = -1;

               for(var12 = 0; var12 != var19.length; ++var12) {
                  if ((var13 = var9 - var12 - 1) < 0) {
                     var13 += var4 * var18;
                  }

                  if (var11 == -1 && var19[var13].a() == 1) {
                     var11 = var13;
                  }
               }

               var7.c(var11);
               var9 = var10;
               var19 = var17;
               var11 = -1;

               for(var12 = 0; var12 != var19.length; ++var12) {
                  if ((var13 = var9 + var12 + 1) >= var4 * var18) {
                     var13 -= var4 * var18;
                  }

                  if (var11 == -1 && var19[var13].a() == 1) {
                     var11 = var13;
                  }
               }

               var8.d(var11);
            }
         }
      }

      var18 = this.C66_f900;
      var4 = this.C66_f899;
      var17 = var5;
      var16 = this;

      for(var6 = 0; var6 != var4; ++var6) {
         var7 = new C49(-1, (byte)0, 0, 0, 0, 0, 0);
         var8 = new C49(-1, (byte)0, 0, 0, 0, 0, 0);
         var9 = -1;
         var10 = -1;

         for(var11 = 0; var11 != var18; ++var11) {
            if (var7.a() == 0 && var17[var6 + var4 * var11].a() == 1) {
               var7 = var17[var6 + var4 * var11];
               var9 = var6 + var4 * var11;
            }

            if (var8.a() == 0 && var17[var6 + var4 * (var18 - var11 - 1)].a() == 1) {
               var8 = var17[var6 + var4 * (var18 - var11 - 1)];
               var10 = var6 + var4 * (var18 - var11 - 1);
            }
         }

         if (var7.a() == 1) {
            if (var16.C66_f909 == 0) {
               var7.a(var9);
               var8.b(var10);
            } else {
               var11 = var9;
               C49[] var21 = var17;
               C66 var20 = var16;
               var12 = -1;
               var13 = var9 % var4;

               int var14;
               int var15;
               for(var14 = 0; var14 != var18; ++var14) {
                  if ((var15 = var11 / var4 - 1 - var14) < 0) {
                     var15 += var18;
                  }

                  if (var12 == -1 && var21[var15 * var20.C66_f899 + var13].a() == 1) {
                     var12 = var15 * var20.C66_f899 + var13;
                  }
               }

               var7.a(var12);
               var11 = var10;
               var21 = var17;
               var20 = var16;
               var12 = -1;
               var13 = var10 % var4;

               for(var14 = 0; var14 != var18; ++var14) {
                  if ((var15 = var11 / var4 + 1 + var14) >= var18) {
                     var15 -= var18;
                  }

                  if (var12 == -1 && var21[var15 * var20.C66_f899 + var13].a() == 1) {
                     var12 = var15 * var20.C66_f899 + var13;
                  }
               }

               var8.b(var12);
            }
         }
      }

      return var5;
   }

   public static C49[] a(int var0, int var1) {
      C49[] var2 = new C49[var0 * var1];

      for(int var3 = 0; var3 != var0 * var1; ++var3) {
         var2[var3] = new C49();
      }

      return var2;
   }

   public final boolean a(byte var1) {
      boolean var2 = false;
      int var3 = this.C66_f911;
      if (var1 == 0) {
         if (this.C66_f920[this.C66_f911].a() == 1 && var3 != this.C66_f920[this.C66_f911].b()) {
            var3 = this.C66_f920[this.C66_f911].b();
            var2 = true;
         }
      } else if (var1 == 1) {
         if (this.C66_f920[this.C66_f911].a() == 1 && var3 != this.C66_f920[this.C66_f911].c()) {
            var3 = this.C66_f920[this.C66_f911].c();
            var2 = true;
         }
      } else if (var1 == 2) {
         if (this.C66_f920[this.C66_f911].a() == 1 && var3 != this.C66_f920[this.C66_f911].d()) {
            var3 = this.C66_f920[this.C66_f911].d();
            var2 = true;
         }
      } else if (var1 == 3 && this.C66_f920[this.C66_f911].a() == 1 && var3 != this.C66_f920[this.C66_f911].e()) {
         var3 = this.C66_f920[this.C66_f911].e();
         var2 = true;
      }

      this.r(var3);
      return var2;
   }

   public C66() {
      this.C66_f893 = this.C66_f894 = 0;
      this.C66_f895 = this.C66_f896 = this.C66_f897 = this.C66_f898 = this.C66_f899 = this.C66_f900 = 0;
      this.C66_f901 = this.C66_f902 = 0;
      this.C66_f903 = this.C66_f904 = 0;
      this.C66_f905 = this.C66_f906 = 0;
      this.C66_f918 = 2;
      this.C66_f917 = this.C66_f919 = -1;
      this.C66_f911 = 0;
      this.C66_f920 = new C49[0];
      this.C66_f914 = null;
      this.C66_f915 = false;
      this.C66_f916 = true;
   }

   public final void render(Graphics var1, boolean var2, boolean var3, IComponent var4, int[] var5) {
      if (this.C66_f916) {
         if (var1 != null) {
            var1.setColor(this.C66_f912);
            var1.fillRect(this.C66_f893, this.C66_f894, this.getWidth(), this.getHeight());
            C35 var8 = new C35(this.C66_f893, this.C66_f894, this.getWidth(), this.getHeight());
            if (this.C66_f913 != null && var1 != null) {
               this.C66_f913.a(var1, var8, 0);
            }

            int var9 = this.o();
            int var10 = this.p();

            for(int var11 = this.C66_f906; var11 <= var9; ++var11) {
               for(int var6 = this.C66_f904; var6 <= var10; ++var6) {
                  var8 = new C35(this.C66_f893 + this.C66_f897 + (var6 - this.C66_f904) * (this.C66_f897 + this.C66_f895), this.C66_f894 + this.C66_f898 + (var11 - this.C66_f906) * (this.C66_f898 + this.C66_f896), this.C66_f895, this.C66_f896);
                  C17 var7;
                  if ((var7 = this.C66_f920[var11 * this.C66_f899 + var6].C49_f748) != null && var1 != null) {
                     var7.a(var1, var8, 0);
                  }
               }
            }

            var8 = new C35(this.C66_f893 + this.C66_f897 + (this.C66_f911 % this.C66_f899 - this.C66_f904) * (this.C66_f897 + this.C66_f895) + this.C66_f901, this.C66_f894 + this.C66_f898 + (this.C66_f911 / this.C66_f899 - this.C66_f906) * (this.C66_f898 + this.C66_f896) + this.C66_f902, this.getWidth(), this.getHeight());
            if (this.C66_f915 && this.C66_f914 != null && var1 != null) {
               this.C66_f914.a(var1, var8, 0);
            }
         }

      }
   }

   public final void update(boolean var1, boolean var2, IComponent var3, int[] var4) {
      if (this.C66_f913 != null) {
         this.C66_f913.b();
      }

      for(int var5 = 0; var5 < this.C66_f920.length; ++var5) {
         if (this.C66_f920[var5].C49_f748 != null) {
            this.C66_f920[var5].C49_f748.b();
         }
      }

      if (this.C66_f915 && this.C66_f914 != null) {
         this.C66_f914.b();
      }

   }

   public final int getSelectedComponentId() {
      return this.C66_f917;
   }

   public final void q(int var1) {
      this.C66_f917 = var1;
   }

   public final int getOffsetX() {
      return this.C66_f893;
   }

   public final void setOffsetX(int var1, IComponent var2) {
      this.C66_f893 = var1;
   }

   public final int getOffsetY() {
      return this.C66_f894;
   }

   public final void setOffsetY(int var1, IComponent var2) {
      this.C66_f894 = var1;
   }

   public final int getWidth() {
      return this.C66_f903 > 0 && this.C66_f903 <= this.C66_f899 ? (this.C66_f895 + this.C66_f897) * this.C66_f903 + this.C66_f897 : (this.C66_f895 + this.C66_f897) * this.C66_f899 + this.C66_f897;
   }

   public final void setWidth(int var1, IComponent var2) {
   }

   public final int getHeight() {
      return this.C66_f905 > 0 && this.C66_f905 <= this.C66_f900 ? (this.C66_f896 + this.C66_f898) * this.C66_f905 + this.C66_f898 : (this.C66_f896 + this.C66_f898) * this.C66_f900 + this.C66_f898;
   }

   public final void setHeight(int var1, IComponent var2) {
   }

   public final void r(int var1) {
      if (this.C66_f920 != null && var1 > -2 && var1 < this.C66_f920.length) {
         this.C66_f911 = var1;
         int var3 = this.C66_f911 / this.C66_f899;
         if (this.C66_f910 == 1) {
            this.C66_f906 = var3 / this.C66_f905 * this.C66_f905;
         } else if (var3 < this.C66_f906) {
            this.C66_f906 = var3;
         } else if (var3 > this.o()) {
            this.C66_f906 = var3 - this.C66_f905 + 1;
         }

         var3 = this.C66_f911 % this.C66_f899;
         if (this.C66_f908 == 1) {
            this.C66_f904 = var3 / this.C66_f903 * this.C66_f903;
            return;
         }

         if (var3 < this.C66_f904) {
            this.C66_f904 = var3;
            return;
         }

         if (var3 > this.p()) {
            this.C66_f904 = var3 - this.C66_f903 + 1;
         }
      }

   }

   public final Component getChildComponent() {
      return null;
   }

   public final IComponent[] getComponents() {
      return null;
   }

   public final DialogData getComponentData() {
      return null;
   }

   public final void setComponentData(DialogData var1) {
   }

   public final int getZIndex() {
      return this.C66_f918;
   }

   public final int getActiveComponentId() {
      return this.C66_f919;
   }

   public final void s(int var1) {
      this.C66_f919 = var1;
   }

   public final void setActiveComponent(IComponent var1) {
   }

   public final void cleanUp() {
      if (this.C66_f914 != null) {
         this.C66_f914.c();
         this.C66_f914 = null;
      }

      if (this.C66_f913 != null) {
         this.C66_f913.c();
         this.C66_f913 = null;
      }

      if (this.C66_f920 != null) {
         for(int var1 = 0; var1 < this.C66_f920.length; ++var1) {
            if (this.C66_f920[var1] != null) {
               this.C66_f920[var1].C49_f748.c();
               this.C66_f920[var1] = null;
            }
         }

         this.C66_f920 = null;
      }

   }

   public final void setVisible(boolean var1) {
      this.C66_f916 = var1;
   }
}
