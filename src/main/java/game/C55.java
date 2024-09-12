package game;

import a.C26;
import a.C44;
import a.a.C30;
import a.a.C42;
import a.b.C64;
import a.b.C67;
import a.b.C68;
import c.C54;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class C55 extends C44 {
   private static C55 C55_f813 = null;
   private byte C55_f814;
   private byte C55_f815;
   private int C55_f816;
   private Image C55_f817;
   private C44 C55_f818;
   private static int C55_f819 = 0;
   private static int C55_f820 = 10;
   private String[] C55_f821 = new String[]{"Hỏa hệ khắc mộc hệ", "Mộc hệ khắc thổ hệ", "Thổ hệ khắc thủy hệ", "Thủy hệ khắc hỏa hệ", "Quỷ hệ khắc phong hệ", "Phong hệ khắc điện hệ", "Điện hệ khắc quỷ hệ"};
   private C33 C55_f822 = C33.B();
   public long C55_f823 = 0L;
   public long C55_f824 = 0L;
   public long C55_f825 = 0L;
   public long C55_f826 = 0L;
   public long C55_f827 = 0L;
   public long C55_f828 = 0L;
   C42 C55_f829 = null;
   private String C55_f830 = "";
   public byte C55_f831 = 0;

   public static C55 B() {
      if (C55_f813 == null) {
         C55_f813 = new C55();
      }

      return C55_f813;
   }

   public final void d(boolean var1) {
      if (var1) {
         this.C();
      } else {
         this.H();
      }
   }

   public final void C() {
      this.C55_f828 = System.currentTimeMillis();
      this.a((byte)3);
      super.d(true);
   }

   private void H() {
      this.a((byte)1);
      super.d(false);
   }

   public final void a(byte var1) {
      if (var1 < 24) {
         this.C55_f815 = this.C55_f814;
         switch(this.C55_f814) {
         case 2:
         case 4:
         case 5:
         case 6:
         case 8:
         case 10:
         case 11:
         case 13:
         case 14:
         case 15:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         case 21:
         default:
            break;
         case 3:
            C30.a().C30_f472 = -1;
            break;
         case 7:
         case 9:
         case 12:
         case 22:
         case 23:
            C55_f819 = 0;
         }

         this.C55_f814 = var1;
         switch(var1) {
         case 2:
            if (C25.B().C25_f286 != null) {
               C25.B().C25_f286.I();
            }
            break;
         case 3:
            C55_f819 = 0;
            C30.a().c(0, 19);
         case 4:
         case 5:
         case 7:
         case 8:
         case 10:
         case 13:
         case 14:
         case 16:
         case 17:
         case 18:
         case 19:
         case 20:
         default:
            break;
         case 6:
            this.C55_f817 = null;
            this.C55_f817 = C26.loadImage("/data/img/", "img_831");
            break;
         case 9:
            a(false);
            int var2 = C26.a(this.C55_f821.length);
            this.C55_f830 = this.C55_f821[var2];
            break;
         case 11:
            C25.B().C44_f701.C9_f132 = true;
            break;
         case 12:
         case 22:
            a(false);
            C55_f819 = 0;
            break;
         case 15:
            this.C55_f817 = null;
            this.C55_f817 = C26.a("/data/logo/", "0");
            break;
         case 21:
            C30.a().c(0, 18);
            C30.a().e();
            break;
         case 23:
            a(false);
            C55_f819 = 0;
            this.C55_f822.a((byte)1);
         }

         this.C55_f816 = 0;
      }
   }

   public final byte D() {
      return this.C55_f814;
   }

   public final synchronized void E() {
      if (C25.B().C25_f342 != null) {
         C25.B().C25_f342.b();
      }

      if (this.C55_f814 != 2) {
         this.a((byte)2);
         this.z();
      }

   }

   private void I() {
      this.a(this.C55_f815);
      if (C25.B().C25_f342 != null) {
         C25.B().C25_f342.c();
      }

      this.z();
   }

   public final boolean b() {
      this.d();
      this.C44_f701 = C9.a();
      this.C44_f700 = C54.a();
      this.C44_f701.a((C44)this);
      C67.a(50000);
      C64.a(1000);
      C67.a();
      b(0);
      m();
      this.C55_f822.b();
      Image var1;
      int[] var2 = C26.a(var1 = C26.loadImage("/data/img/", "img_22"));
      this.C55_f829 = new C42();
      this.C55_f829.a(var2, var1.getWidth(), var1.getHeight());
      C25.B();
      C25.G();
      e();
      return true;
   }

   public final void c() {
      if (this.C55_f818 != null) {
         this.C55_f818.c();
         this.C55_f818 = null;
      }

   }

   public final void a() {
      if (this.C8_f110) {
         this.A();
         switch(this.C55_f814) {
         case 2:
            if (this.g(262144)) {
               this.I();
            }
            break;
         case 3:
            if (!f()) {
               this.b();
            }

            if (C30.a().C30_f476 && f()) {
               this.a((byte)15);
            }
            break;
         case 4:
            if (this.g(131072)) {
               this.H();
            } else if (this.g(262144)) {
               this.I();
            }
         case 5:
         case 14:
         case 17:
         case 18:
         default:
            break;
         case 6:
            byte var2;
            if (this.g(131072)) {
               var2 = 1;
               this.C55_f831 = (byte)var2;
               this.a((byte)21);
            } else if (this.g(262144)) {
               var2 = 0;
               this.C55_f831 = (byte)var2;
               this.a((byte)21);
            }
            break;
         case 7:
            this.c();
            this.C55_f818 = C13.B();
            this.C55_f818.b();
            this.a(this.C55_f818);
            this.a((byte)8);
            break;
         case 8:
         case 11:
         case 13:
         case 20:
            if (this.C55_f818 != null) {
               this.C55_f818.a();
            }
            break;
         case 9:
         case 22:
            this.c();
            this.C55_f818 = C25.B();
            this.C55_f818.b();
            this.a(this.C55_f818);
            if (this.C55_f814 == 9 || this.C55_f814 == 22) {
               this.a((byte)11);
            }

            C53.C53_f798 = false;
            break;
         case 10:
            this.c();
            this.C55_f818 = C25.B();
            ((C25)this.C55_f818).L();
            this.a(this.C55_f818);
            this.a((byte)11);
            break;
         case 12:
            if (!f()) {
               this.C55_f818 = null;
               this.C55_f818 = C29.B();
               this.C55_f818.b();
               this.a(this.C55_f818);
               if (((C29)this.C55_f818).C29_f398 == 0) {
                  C30.a().c(-2013265920, 6);
               } else if (((C29)this.C55_f818).C29_f398 == 2) {
                  C30.a().c(-2013265920, 8);
               } else if (((C29)this.C55_f818).C29_f398 == 1) {
                  C30.a().c(-2013265920, 7);
               }
            }

            if (f()) {
               C30.a().d();
               C53.C53_f798 = false;
            }

            if (C30.a().C30_f476) {
               ((C29)this.C55_f818).E();
               this.a((byte)13);
            }
            break;
         case 15:
            ++this.C55_f816;
            if (this.C55_f816 >= 10) {
               this.C55_f816 = 0;
               this.C55_f817 = null;
               this.C55_f817 = C26.a("/data/logo/", "cwalogo");
               this.a((byte)16);
            }
            break;
         case 16:
            ++this.C55_f816;
            if (this.C55_f816 >= 10) {
               this.a((byte)6);
            }
            break;
         case 19:
            byte var1;
            if (this.C55_f818 instanceof C25) {
               var1 = 1;
            } else {
               var1 = 2;
            }

            this.C55_f818 = C48.B();
            this.C55_f818.b();
            this.a(this.C55_f818);
            this.C55_f818.a(var1);
            this.a((byte)20);
            break;
         case 21:
            C30.a().d();
            if (C30.a().C30_f475 == -1 || this.g(65568) && C13.C44_f710) {
               C30.a().C30_f475 = -1;
               C30.a().C30_f474 = -1;
               C30.a().C30_f479 = 0;
               C30.a().C30_f472 = -1;
               this.C55_f817 = null;
               this.C55_f829.C42_f671 = null;
               this.C55_f829 = null;
               this.a((byte)7);
            }
            break;
         case 23:
            C30.a().d();
            if (this.C55_f822.C44_f698 == 1 && C30.a().C30_f477) {
               this.C55_f822.a((byte)2);
            } else if (this.C55_f822.C44_f698 == 2) {
               if (this.C55_f822.C33_f547) {
                  this.c();
                  this.C55_f818 = C25.B();
                  this.C55_f818.b();
                  this.a(this.C55_f818);
                  this.C55_f822.a((byte)3);
               }
            } else if (this.C55_f822.C44_f698 == 3 && C30.a().C30_f477) {
               C30.a().C30_f474 = -1;
               this.C55_f822.C();
               this.a((byte)11);
            }
         }

         if (this.C44_f698 != 2) {
            if (C25.B().C25_f290 == 3 && C25.B().C25_f291 == 7 && this.C55_f825 == 0L && this.C55_f823 != 0L) {
               this.C55_f824 = System.currentTimeMillis();
            }

            this.C55_f827 = System.currentTimeMillis();
         }

      }
   }

   public final void a(Graphics var1) {
      if (this.C8_f110) {
         var1.setFont(m());
         switch(this.C55_f814) {
         case 2:
            var1.setColor(0);
            var1.fillRect(0, 0, g(), h());
            var1.setColor(16777215);
            var1.drawString("Trò chơi tạm dừng", g() >> 1, j(), 33);
            var1.drawString("Quay lại", g() - 2, h() - 2, 40);
            return;
         case 3:
            var1.setColor(16777215);
            var1.fillRect(0, 0, g(), h());
            C30.a().a(var1);
            return;
         case 4:
            return;
         case 5:
         case 14:
         case 17:
         case 18:
         case 19:
         case 22:
         default:
            break;
         case 6:
            var1.setColor(0);
            var1.fillRect(0, 0, g(), h());
            var1.setFont(m());
            var1.setColor(16777215);
            var1.drawString(c(8), g() >> 1, 144, 17);
            var1.drawString(c(4), 2, h() - 2, 36);
            var1.drawString(c(5), g() - 2, h() - 2, 40);
            var1.setColor(16739328);
            var1.drawString(c(9), g() >> 1, 166, 17);
            return;
         case 7:
            return;
         case 8:
         case 11:
         case 13:
         case 20:
            if (this.C55_f818 != null) {
               this.C55_f818.a(var1);
            }
            break;
         case 9:
            if (C53.C53_f798) {
               var1.setColor(0);
               var1.fillRect(0, 0, g(), h());
               if (C55_f819 % 4 == 3) {
                  C53.p().a((byte)1, (byte)-1, false);
               } else {
                  C53.p().a((byte)(C55_f819 % 4), (byte)-1, false);
               }

               C53 var10000 = C53.p();
               byte var4 = (byte)(C55_f819 % 4);
               var10000.C60_f866 = var4;
               C53.p().a(var1, C68.a().C68_f943, C68.a().C68_f944 - C55_f819);
            } else {
               var1.setColor(0);
               var1.fillRect(0, 0, g(), h());
            }

            if (C55_f819 < 148) {
               C55_f819 += C55_f820;
            }

            if (C55_f819 > 148) {
               C55_f819 = 148;
            }

            if (!C53.C53_f798) {
               var1.setColor(0);
               var1.fillRect(45, h() - 48, 150, 5);
               var1.setColor(7877410);
               var1.fillRect(46, h() - 47, 148, 3);
               var1.setColor(16707204);
               var1.fillRect(46, h() - 47, C55_f819, 3);
               var1.setColor(16777215);
               var1.drawString(this.C55_f830, g() >> 1, h() - 70, 17);
            }

            return;
         case 10:
            return;
         case 12:
            C25.B().C25_f285.a(var1);
            if (f()) {
               C30.a().a(var1);
               C53.C53_f798 = false;
               return;
            }
            break;
         case 15:
            var1.setColor(16777215);
            var1.fillRect(0, 0, g(), h());
            var1.setColor(16777215);
            var1.setFont(m());
            if (this.C55_f817 != null) {
               var1.drawImage(this.C55_f817, i(), j(), 3);
               return;
            }
            break;
         case 16:
            var1.setColor(l());
            var1.fillRect(0, 0, g(), h());
            if (this.C55_f817 != null) {
               var1.drawImage(this.C55_f817, (g() - this.C55_f817.getWidth()) / 2, (h() - this.C55_f817.getHeight()) / 2, 20);
               return;
            }
            break;
         case 21:
            var1.drawImage(this.C55_f817, 0, 0, 20);

            for(int var2 = 0; var2 < g() / 10; ++var2) {
               var1.drawRGB(this.C55_f829.C42_f671, 0, this.C55_f829.C42_f672, var2 * 10, 0, this.C55_f829.C42_f672, this.C55_f829.C42_f673, true);
            }

            C30.a().a(var1);
            return;
         case 23:
            this.C55_f822.a();
            this.C55_f822.a(var1);
            C30.a().a(var1);
         }

      }
   }

   public final void l(int var1) {
      this.C55_f831 = (byte)var1;
   }

   public final void F() {
      ++this.C55_f831;
      if (this.C55_f831 > 3) {
         this.C55_f831 = 3;
      }

   }

   public final void G() {
      --this.C55_f831;
      if (this.C55_f831 < 0) {
         this.C55_f831 = 0;
      }

   }
}
