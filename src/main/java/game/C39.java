package game;

import a.C26;
import a.C44;
import javax.microedition.lcdui.Graphics;

public final class C39 {
   private static int C39_f593 = 0;
   private char[] C39_f594;
   private int C39_f595;
   private int C39_f596;
   private int C39_f597;
   private int C39_f598;
   private int[] C39_f599;
   private int C39_f600;
   private int C39_f601;
   private int C39_f602;
   private int C39_f603;
   private int C39_f604;
   private int C39_f605;
   private int C39_f606;
   private int C39_f607;
   public static boolean C39_f608 = false;
   private int C39_f609;
   public static boolean C39_f610 = false;
   private byte C39_f611;
   private static C39 C39_f612;
   private boolean C39_f613;
   private int C39_f614;
   private int[][] C39_f615;
   private int C39_f616;
   private char[] C39_f617;
   private char[] C39_f618;
   private String[] C39_f619;
   private int C39_f620;
   private long C39_f621;
   private int C39_f622;

   public C39() {
      C44.o();
      this.C39_f596 = 0;
      this.C39_f597 = 0;
      this.C39_f598 = 0;
      this.C39_f599 = new int[10];
      this.C39_f600 = 0;
      this.C39_f601 = 0;
      this.C39_f602 = 2;
      this.C39_f603 = 0;
      this.C39_f606 = 0;
      this.C39_f607 = 2;
      this.C39_f609 = 0;
      this.C39_f611 = 0;
      this.C39_f614 = 0;
      this.C39_f615 = null;
      this.C39_f616 = 0;
      this.C39_f617 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
      this.C39_f618 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
      this.C39_f619 = new String[]{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
   }

   public static C39 a() {
      if (C39_f612 == null) {
         C39_f612 = new C39();
      }

      return C39_f612;
   }

   public final void b() {
      switch(C39_f593) {
      case 0:
         if (this.C39_f597 < this.C39_f594.length) {
            if (this.C39_f597 - this.C39_f596 >= this.C39_f607 * this.C39_f595 + this.C39_f598) {
               this.C39_f596 = this.C39_f597;
               this.C39_f598 = 0;
               this.C39_f599[0] = this.C39_f599[this.C39_f601];
               C39_f608 = false;
               return;
            }

            while(this.C39_f597 - this.C39_f596 < this.C39_f607 * this.C39_f595 + this.C39_f598 && this.C39_f597 < this.C39_f594.length) {
               if (this.C39_f594[this.C39_f597] == '#') {
                  this.C39_f598 += 7;
                  this.C39_f597 += 7;
               } else {
                  ++this.C39_f597;
               }
            }
         }

         C7.C7_f85 = null;
         this.C39_f599[0] = this.C39_f599[this.C39_f601];
         C39_f610 = false;
         this.C39_f596 = 0;
         this.C39_f597 = 0;
         this.C39_f598 = 0;
         C39_f608 = false;
         return;
      case 1:
         C39_f610 = false;
         this.C39_f596 = 0;
         this.C39_f597 = 0;
         this.C39_f598 = 0;
         return;
      case 2:
         return;
      case 3:
         if (this.C39_f609 < this.C39_f607 * (this.C39_f616 + 1)) {
            if (this.C39_f607 * (this.C39_f616 + 1) > this.C39_f615.length) {
               this.C39_f609 = this.C39_f615.length;
               return;
            } else {
               this.C39_f609 = this.C39_f607 * (this.C39_f616 + 1);
            }
         }
      default:
      }
   }

   public final void a(Graphics var1) {
      Graphics var2 = var1;
      C39 var14 = this;
      if (C39_f610) {
         label71:
         switch(C39_f593) {
         case 0:
            switch(this.C39_f611) {
            case 0:
               var1.setColor(16777215);
               this.a(var1, this.C39_f604, this.C39_f605);
               break label71;
            case 1:
               this.a(var1, this.C39_f604, this.C39_f605 - (C44.o() >> 1));
            default:
               break label71;
            }
         case 1:
         case 2:
            int var16 = C39_f593 == 1 ? 0 : this.C39_f603;
            int var15;
            switch(this.C39_f611) {
            case 0:
               var15 = 0;

               while(true) {
                  if (var15 >= C26.textChunks.length) {
                     break label71;
                  }

                  C4.a(var2, C26.textChunks[var15], var14.C39_f604, var14.C39_f605 + var15 * (C4.C4_f33 + 1) + var16, 16777215);
                  ++var15;
               }
            case 1:
               for(var15 = 0; var15 < C26.textChunks.length; ++var15) {
                  var2.setColor(16777215);
                  C4.b(var2, C26.textChunks[var15], var14.C39_f604, var14.C39_f605 + (C4.C4_f33 + 1) * (C26.textChunks.length >> var15 + 1) + var16, 17);
               }
            default:
               break label71;
            }
         case 3:
            int var6 = this.C39_f605;
            int var5 = this.C39_f604;
            Graphics var4 = var1;
            C39 var3 = this;
            int var7 = this.C39_f594.length;
            System.out.println("drawDialogRow: startLine=" + this.C39_f616 + ", m=" + this.C39_f607 + ", n=" + this.C39_f609);
            int var8 = this.C39_f616 * this.C39_f607;

            while(true) {
               if (var8 >= var3.C39_f609) {
                  var3.C39_f601 = var3.C39_f600;
                  var3.C39_f600 = 0;
                  break;
               }

               int var9 = C4.C4_f33 * (var8 - var3.C39_f616 * var3.C39_f607) + var6;
               int var10 = var5;

               for(int var11 = var3.C39_f615[var8][0]; var11 < var3.C39_f615[var8][1]; ++var11) {
                  while(var11 < var7 && var3.C39_f594[var11] == '#') {
                     String var12 = "";

                     for(int var13 = 0; var13 < 7; ++var13) {
                        var12 = var12 + var3.C39_f594[var11 + var13];
                     }

                     var3.C39_f600 = ++var3.C39_f600 >= var3.C39_f599.length ? 0 : var3.C39_f600;
                     var3.C39_f599[var3.C39_f600] = var3.a(var12);
                     var4.setColor(var3.C39_f599[var3.C39_f600]);
                     var11 += 7;
                  }

                  var4.setColor(var3.C39_f599[var3.C39_f600]);
                  if (var11 < var7) {
                     char var17 = var3.C39_f594[var11];
                     C4.a(var4, var17, var10, var9);
                     var10 += C4.a(var17);
                  }
               }

               ++var8;
            }
         }

         if (var14.C39_f613 && C39_f608 && var14.C39_f609 % 10 < 5) {
            var2.setColor(16777215);
            var2.drawString("Nhấn 0 để tiếp tục", C44.g() >> 1, C44.h() - 8, 33);
         }
      }

   }

   public final void c() {
      this.C39_f594 = null;
      C26.textChunks = null;
      this.C39_f609 = 0;
   }

   public final void a(byte var1, String var2, int var3) {
      this.C39_f594 = var2.toCharArray();
      C39_f593 = var1;
      switch(var1) {
      case 0:
         C39 var12 = this;
         int var13 = this.C39_f594.length;
         int var4 = 0;
         int var5 = 0;
         int var6;
         int var7 = (var6 = this.C39_f622 - 10) - C4.C4_f38;
         StringBuffer var8 = new StringBuffer();

         while(true) {
            while(var12.C39_f597 < var13) {
               char var9;
               if ((var9 = var12.C39_f594[var12.C39_f597]) == '#') {
                  var12.C39_f598 += 7;
                  var12.C39_f597 += 7;
               } else {
                  ++var12.C39_f597;
                  int var10 = C4.a(var9);
                  int var11 = var5 + var10;
                  var8.append(var9);
                  if (var5 > var6 || var9 == ' ' && var5 > var7) {
                     var11 = 0;
                     if (var9 != ' ') {
                        var11 = var10 + 0;
                     }

                     ++var4;
                     var8 = new StringBuffer();
                  }

                  var5 = var11;
               }
            }

            if (var5 > 0) {
               ++var4;
            }

            this.C39_f607 = var4;
            break;
         }
      case 1:
      case 2:
      default:
         break;
      case 3:
         this.C39_f616 = 0;
         this.f();
         this.a(0, 80);
      }

      this.C39_f596 = 0;
      this.C39_f597 = 0;
      this.C39_f598 = 0;
      C39_f610 = true;
      C39_f608 = false;
      boolean var14 = false;
      this.C39_f613 = var14;
      this.C39_f611 = (byte)var3;
   }

   public final void a(int var1, int var2) {
      this.C39_f604 = var1;
      this.C39_f605 = var2;
      this.C39_f622 = C44.g() - 2 * this.C39_f604;
   }

   private void f() {
      C39_f593 = 3;
      int var1 = 0;
      char[] var2;
      int var3 = (var2 = this.C39_f594).length;
      int[][] var4 = new int[50][2];
      int var5 = 0;
      int var6;
      int var7 = (var6 = this.C39_f622 - 10) - C4.C4_f38;
      int var8 = 0;
      int var9 = 0;

      while(true) {
         while(var9 < var3) {
            char var10;
            if ((var10 = var2[var9]) == '#') {
               var9 += 7;
            } else {
               int var11 = C4.a(var10);
               int var12 = var5 + var11;
               if (var5 > var6 || var10 == ' ' && var5 > var7) {
                  var12 = 0;
                  if (var10 != ' ') {
                     var12 = var11 + 0;
                  }

                  var4[var1][0] = var8;
                  var4[var1][1] = var9;
                  var8 = var9;
                  ++var1;
               }

               var5 = var12;
               ++var9;
            }
         }

         if (var5 > 0) {
            var4[var1][0] = var8;
            var4[var1][1] = this.C39_f603;
            ++var1;
         }

         this.C39_f615 = new int[var1][2];

         for(var9 = 0; var9 < var1; ++var9) {
            System.arraycopy(var4[var9], 0, this.C39_f615[var9], 0, 2);
         }

         return;
      }
   }

   public final void b(int var1, int var2) {
      this.C39_f622 = var1;
      int var3 = C4.a('w');
      this.C39_f595 = var1 / var3;
      int var10000 = this.C39_f595;
      this.C39_f607 = var2 / (C4.C4_f33 + 1);
   }

   private void a(Graphics var1, int var2, int var3) {
      this.C39_f606 = this.C39_f596;
      char[] var4;
      int var5 = (var4 = this.C39_f594).length;
      int var6 = var2;
      var3 = var3;
      int var7;
      int var8 = (var7 = this.C39_f622 + var2 - 10) - C4.C4_f38;

      for(int var9 = this.C39_f596; var9 < this.C39_f597; ++var9) {
         int var11;
         while(var9 < var5 && var4[var9] == '#') {
            String var10 = "";

            for(var11 = 0; var11 < 7; ++var11) {
               var10 = var10 + var4[var9 + var11];
            }

            this.C39_f600 = ++this.C39_f600 >= this.C39_f599.length ? 0 : this.C39_f600;
            this.C39_f599[this.C39_f600] = this.a(var10);
            var1.setColor(this.C39_f599[this.C39_f600]);
            var9 += 7;
         }

         var1.setColor(this.C39_f599[this.C39_f600]);
         if (var9 < var5) {
            char var13;
            var11 = C4.a(var13 = var4[var9]);
            int var12;
            if ((var12 = var6 + var11) > var7 || var13 == ' ' && var12 > var8) {
               var12 = var2;
               var6 = var2;
               if (var13 != ' ') {
                  var12 = var2 + var11;
               }

               var3 += C4.C4_f33 + 1;
            }

            C4.a(var1, var13, var6, var3);
            var6 = var12;
         }

         ++this.C39_f606;
      }

      this.C39_f601 = this.C39_f600;
      this.C39_f600 = 0;
   }

   private int a(String var1) {
      char[] var4 = var1.toCharArray();
      StringBuffer var2 = new StringBuffer();

      int var3;
      for(var3 = 0; var3 < var4.length; ++var3) {
         var2.append(this.a(var4[var3]));
      }

      var4 = var2.toString().toCharArray();
      var3 = 0;

      for(int var5 = 0; var5 < var4.length; ++var5) {
         if (var4[var5] == '1') {
            var3 += 1 << var4.length - var5 - 1;
         }
      }

      return var3;
   }

   private String a(char var1) {
      for(int var2 = 0; var2 < this.C39_f617.length; ++var2) {
         if (var1 == this.C39_f617[var2] || var1 == this.C39_f618[var2]) {
            return this.C39_f619[var2];
         }
      }

      return "0000";
   }

   public final void d() {
      C39 var1 = this;
      char[] var2 = this.C39_f594;
      if (C39_f610) {
         switch(C39_f593) {
         case 0:
            int var3 = var2.length;
            int var4;
            int var5 = (var4 = this.C39_f622 - 10) - C4.C4_f38;
            int var6 = 1;

            for(int var7 = 0; var7 < 2; ++var7) {
               if (var1.C39_f597 < var3) {
                  var1.C39_f621 = 0L;
                  if (var1.C39_f597 == 0) {
                     var1.C39_f620 = 0;
                  }

                  char var8;
                  if ((var8 = var2[var1.C39_f597]) == '#') {
                     var1.C39_f598 += 7;
                     var1.C39_f597 += 7;
                  } else {
                     int var9 = C4.a(var8);
                     int var10;
                     if ((var10 = var1.C39_f620 + var9) > var4 || var8 == ' ' && var10 > var5) {
                        var10 = 0;
                        var1.C39_f620 = 0;
                        if (var8 != ' ') {
                           var10 = var9 + 0;
                        }

                        ++var6;
                        if (var6 > var1.C39_f607) {
                           var6 = 0;
                           var1.C39_f596 = var1.C39_f597;
                        }
                     }

                     var1.C39_f620 = var10;
                     ++var1.C39_f597;
                  }
               } else if (var1.C39_f621 == 0L) {
                  var1.C39_f621 = System.currentTimeMillis() + 2500L;
               } else if (System.currentTimeMillis() > var1.C39_f621) {
                  if (!var1.C39_f613) {
                     var1.c();
                     C39_f610 = false;
                     if (C25.B().C44_f700.b("/data/ui/dialog.ui")) {
                        C25.B().C44_f701.aF();
                     }
                  }

                  C39_f608 = true;
                  ++var1.C39_f609;
               }
            }

            return;
         case 1:
            C39_f608 = true;
            ++this.C39_f609;
            return;
         case 2:
            this.C39_f603 += this.C39_f602;
            if (this.C39_f603 > C26.textChunks.length * (C44.h() + C44.h() / 2)) {
               this.C39_f603 = 0;
               C39_f608 = true;
               C39_f610 = false;
               ++this.C39_f609;
               return;
            }
            break;
         case 3:
            if (this.C39_f609 < this.C39_f615.length) {
               ++this.C39_f614;
               if (this.C39_f614 >= 20) {
                  ++this.C39_f609;
                  if (this.C39_f609 > this.C39_f607 * (this.C39_f616 + 1)) {
                     ++this.C39_f616;
                     this.C39_f599[0] = this.C39_f599[this.C39_f601];
                  }

                  this.C39_f614 = 0;
               }
            }
         }
      }

   }

   public final void a(boolean var1) {
      this.C39_f613 = var1;
   }

   public final boolean e() {
      return this.C39_f613;
   }
}
