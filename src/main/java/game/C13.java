package game;

import a.C26;
import a.C44;
import c.C54;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class C13 extends C44 {
   private static C13 C13_f199;
   private static int[] C13_f200 = null;
   private static int C13_f201;
   private byte C13_f202 = 0;
   private byte C13_f203 = 0;
   private Image C13_f204;
   int C13_f205 = 0;
   private byte C13_f206 = 10;
   private int[][] C13_f207;
   private int[] C13_f208;
   private byte C13_f209;
   private byte C13_f210;
   private boolean C13_f211;
   private int C13_f212;
   private int[] C13_f213;
   private int[] C13_f214;

   public C13() {
      this.C13_f207 = new int[this.C13_f206][5];
      this.C13_f208 = new int[]{28, 3, 21, 22, 50, 5, 17, 17};
      this.C13_f209 = 30;
      this.C13_f210 = 30;
      this.C13_f211 = false;
      this.C13_f212 = 0;
      this.C13_f213 = new int[this.C13_f206];
      this.C13_f214 = new int[]{3958719, 3958719, 3958719, 7248110, 7248110, 9943031};
   }

   public static C13 B() {
      if (C13_f199 == null) {
         C13_f199 = new C13();
      }

      return C13_f199;
   }

   public final boolean b() {
      this.C44_f701 = C9.a();
      this.C44_f700 = C54.a();
      this.C44_f701.a((C44)this);
      C13_f201 = 0;
      if (C44_f710) {
         C13_f200 = new int[]{504, 503, 505, 506, 507, 508};
      } else {
         C13_f200 = new int[]{503, 505, 506, 507, 508};
      }

      if (this.C13_f204 == null) {
         this.C13_f204 = C26.loadImage("/data/img/", "img_833");
      }

      this.C();
      this.a((byte)0);
      this.C13_f205 = 0;
      if (C25.B().C25_f342 != null) {
         this.C13_f205 = C55.B().C55_f831;
         C55.B().l(0);
         C25.B().C25_f342.a((byte)1);
         C25.B().C25_f342.a();
         C25.B().C25_f342 = null;
      }

      return true;
   }

   private void C() {
      for(int var1 = 0; var1 < this.C13_f206; ++var1) {
         this.C13_f207[var1][0] = -C26.a(this.C13_f209);
         this.C13_f207[var1][1] = h() + C26.a(this.C13_f210);
         this.C13_f207[var1][2] = C26.a(2);
         this.C13_f207[var1][3] = C26.b(1, 5);
         this.C13_f207[var1][4] = C26.b(3, 5);
      }

   }

   private void D() {
      C25.C25_f321 = false;
      C25.C25_f332 = false;
      C25.C25_f335 = 0;
      C7.C7_f67 = true;
      C25.B().C25_f290 = 0;
      C25.B().C25_f291 = 0;
      if (C25.B().C25_f348 != null) {
         C25.B().C25_f348.D();
      }

      if (C25.B().C25_f286 != null) {
         C25.B().C25_f286.q();
      }

      if (this.C44_f701 != null) {
         this.C44_f701.b();
      }

      this.C13_f202 = 0;
      C53.p().C53_f776 = false;
      C44_f710 = false;
      C55.B().a((byte)9);
      if (this.C13_f205 > 0 && C55.B().C55_f831 <= 0) {
         C55.B().l(this.C13_f205);
      }

   }

   public final void a() {
      if (this.C8_f110) {
         this.A();
         switch(this.C44_f698) {
         case 0:
            if (this.C13_f202 == 0 && this.g(16400)) {
               if (--C13_f201 < 0) {
                  C13_f201 = C13_f200.length - 1;
               }
            } else if (this.C13_f202 == 0 && this.g(32832)) {
               if (++C13_f201 > C13_f200.length - 1) {
                  C13_f201 = 0;
               }
            } else if (this.g(196640)) {
               if (C44_f710) {
                  switch(C13_f201) {
                  case 0:
                     if (this.C13_f202 == 0) {
                        C26.a();
                        this.E();
                        if (C25.B().C25_f348 != null) {
                           C25.B().C25_f348.D();
                        }

                        if (C25.B().C25_f286 != null) {
                           C25.B().C25_f286.q();
                        }

                        if (this.C44_f701 != null) {
                           this.C44_f701.b();
                        }

                        this.C13_f202 = 0;
                        C53.p().C53_f776 = false;
                        C55.B().a((byte)9);
                        C55.B().a((byte)9);
                        if (this.C13_f205 > 0 && C55.B().C55_f831 <= 0) {
                           C55.B().l(this.C13_f205);
                        }
                     } else if (this.C13_f202 == 1) {
                        this.C13_f202 = 0;
                        this.C44_f701.az();
                     }
                     break;
                  case 1:
                     if (this.C13_f202 == 0) {
                        C26.a();
                        this.a((byte)5);
                     } else if (this.C13_f202 == 1) {
                        this.C13_f202 = 0;
                        this.C44_f701.az();
                     }
                     break;
                  case 2:
                     this.a((byte)1);
                     break;
                  case 3:
                     this.a((byte)2);
                     break;
                  case 4:
                     this.a((byte)3);
                     break;
                  case 5:
                     this.a((byte)4);
                  }
               } else {
                  switch(C13_f201) {
                  case 0:
                     if (this.C13_f202 == 0) {
                        C26.a();
                        this.E();
                        this.D();
                        C55.B().a((byte)9);
                     } else if (this.C13_f202 == 1) {
                        this.C13_f202 = 0;
                        this.C44_f701.az();
                     }
                     break;
                  case 1:
                     this.a((byte)1);
                     break;
                  case 2:
                     this.a((byte)2);
                     break;
                  case 3:
                     this.a((byte)3);
                     break;
                  case 4:
                     this.a((byte)4);
                  }
               }
            }

            if (this.C44_f701.f()) {
               this.C13_f202 = 0;
            }

            C13 var1 = this;
            if (this.C13_f211) {
               ++this.C13_f212;
               if (this.C13_f212 >= 100) {
                  for(int var2 = 0; var2 < var1.C13_f213.length; ++var2) {
                     var1.C13_f213[var2] = 0;
                  }

                  var1.C13_f212 = 0;
                  var1.C();
                  var1.C13_f211 = false;
               }
            }
            break;
         case 1:
            this.C44_f701.t();
            break;
         case 2:
            this.C44_f701.p();
            break;
         case 3:
            this.C44_f701.r();
            break;
         case 4:
            if (this.g(131072)) {
               C55.B().a((byte)1);
            } else if (this.g(262144)) {
               this.a((byte)0);
            }
            break;
         case 5:
            if (this.g(131104)) {
               C25.B();
               C25.K();
               this.D();
            } else if (this.g(262144)) {
               this.a((byte)0);
               this.C44_f700.a("/data/ui/msgtip.ui");
            }
         }

         this.C44_f700.c();
      }
   }

   public final void a(Graphics var1) {
      int var4;
      switch(this.C44_f698) {
      case 1:
      case 2:
      case 3:
         Graphics var2 = var1;

         for(var4 = 0; var4 < h() / 20; ++var4) {
            if (var4 % 2 == 0) {
               var2.setColor(10440998);
            } else {
               var2.setColor(12082732);
            }

            var2.fillRect(0, var4 * 20, g(), 20);
         }
      default:
         this.C44_f700.a(var1);
         if (this.C44_f698 == 0) {
            String var10002 = c(C13_f200[C13_f201]);
            int var10003 = (g() - m().stringWidth(c(C13_f200[C13_f201]))) / 2 + 4;
            int var10004 = h() - 20;
            boolean var7 = true;
            int var6 = var10004;
            int var5 = var10003;
            String var9 = var10002;
            var1.setColor(this.C13_f214[this.C13_f203]);
            var1.drawString(var9, var5, var6 - 1, 36);
            var1.drawString(var9, var5, var6 + 1, 36);
            var1.drawString(var9, var5 - 1, var6, 36);
            var1.drawString(var9, var5 + 1, var6, 36);
            var1.setColor(16777215);
            var1.drawString(var9, var5, var6, 36);
            ++this.C13_f203;
            if (this.C13_f203 >= 6) {
               this.C13_f203 = 0;
            }

            Graphics var3 = var1;
            C13 var8 = this;
            if (!this.C13_f211) {
               for(var4 = 0; var4 < var8.C13_f206; ++var4) {
                  var3.drawRegion(var8.C13_f204, var8.C13_f208[var8.C13_f207[var4][2] << 2], var8.C13_f208[(var8.C13_f207[var4][2] << 2) + 1], var8.C13_f208[(var8.C13_f207[var4][2] << 2) + 2], var8.C13_f208[(var8.C13_f207[var4][2] << 2) + 3], 0, var8.C13_f207[var4][0], var8.C13_f207[var4][1], 20);
                  int[] var10000 = var8.C13_f207[var4];
                  var10000[0] += var8.C13_f207[var4][3];
                  var10000 = var8.C13_f207[var4];
                  var10000[1] -= var8.C13_f207[var4][4];
                  if (var8.C13_f207[var4][0] > g() || var8.C13_f207[var4][1] < 0) {
                     int var10 = var8.C13_f213[var4]++;
                  }
               }

               for(var4 = 0; var4 < var8.C13_f213.length && var8.C13_f213[var4] > 0; ++var4) {
               }

               if (var4 >= var8.C13_f213.length) {
                  var8.C13_f211 = true;
               }
            }

         } else {
            if (this.C44_f698 == 4) {
               var1.setColor(0);
               var1.fillRect(0, 0, g(), h());
               var1.setColor(16777215);
               var1.drawString("Bạn có muốn thoát không?", i(), j() - 10, 17);
               var1.drawString("", 2, h(), 36);
               var1.drawString("Không", g() - 2, h(), 40);
            }

         }
      }
   }

   public final void c() {
      this.C13_f204 = null;
      this.C44_f700.b();
   }

   private void E() {
      this.C44_f700.a("/data/ui/menu.ui");
   }

   public final void a(byte var1) {
      this.C44_f698 = var1;
      switch(var1) {
      case 0:
         this.C44_f701.u();
         this.C44_f701.w();
         return;
      case 1:
         if (this.C13_f205 > 0) {
            C55.B().C55_f831 = (byte)this.C13_f205;
         }

         this.C44_f701.s();
         this.E();
         return;
      case 2:
         this.C44_f701.o();
         this.E();
         return;
      case 3:
         this.C44_f701.q();
         this.E();
         return;
      case 5:
         this.C44_f701.K();
         this.C44_f701.a("Có chắc chắn xóa dữ liệu cũ để chơi mới không?");
      case 4:
      default:
      }
   }
}
