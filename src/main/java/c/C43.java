package c;

import a.C26;
import javax.microedition.lcdui.Graphics;

public final class C43 implements C23 {
   private int C43_f677 = -1;
   private int C43_f678 = 0;
   private int C43_f679 = 0;
   private int C43_f680 = 0;
   private int C43_f681 = 0;
   private int C43_f682 = 0;
   private byte[][] C43_f683 = null;
   private C12 C43_f684 = new C12();
   private int C43_f685 = -1;
   private byte C43_f686 = 9;
   private C38 C43_f687 = null;
   private C23[] C43_f688 = new C23[60];
   public C38 C43_f689 = null;
   public C38 C43_f690 = null;
   private boolean C43_f691 = true;

   public C43() {
      this.C43_f691 = true;
   }

   public final void a(boolean var1) {
      this.C43_f691 = var1;
   }

   public final void a(byte[][] var1) {
      this.C43_f683 = var1;
   }

   public final byte[][] a() {
      return this.C43_f683;
   }

   public final void a(Graphics var1, boolean var2, boolean var3, C23 var4, int[] var5) {
      if (this.C43_f691) {
         for(int var6 = 0; var6 < this.C43_f688.length && this.C43_f688[var6] != null; ++var6) {
            if (this.C43_f688[var6].g() != null) {
               boolean var7 = false;
               int var8;
               if ((var8 = C26.a(var5)) > 0 && var5[var8 - 1] == this.C43_f677) {
                  var7 = true;
               }

               this.C43_f688[var6].g().a(var1, this.C43_f688[var6].b(), var7, var5, var3, var4);
            } else {
               this.C43_f688[var6].a(var1, var2, var3, var4, var5);
            }
         }

      }
   }

   public final void a(boolean var1, boolean var2, C23 var3, int[] var4) {
      for(int var5 = 0; var5 < this.C43_f688.length && this.C43_f688[var5] != null; ++var5) {
         if (this.C43_f688[var5].g() != null) {
            if (C26.a(var4) > 0) {
               int var10000 = this.C43_f677;
            }

            this.C43_f688[var5].g().a(this.C43_f688[var5].b(), var4, var2, var3);
         } else {
            this.C43_f688[var5].a(var1, var2, var3, var4);
         }
      }

   }

   public final int b() {
      return this.C43_f677;
   }

   public final void a(int var1) {
      this.C43_f677 = var1;
   }

   public final int c() {
      return this.C43_f678;
   }

   public final void a(int var1, C23 var2) {
      this.C43_f678 = var1;
      this.a(var2);
   }

   public final int d() {
      return this.C43_f679;
   }

   public final void b(int var1, C23 var2) {
      this.C43_f679 = var1;
      this.a(var2);
   }

   public final int e() {
      return this.C43_f680;
   }

   public final void c(int var1, C23 var2) {
      this.C43_f680 = var1;
      this.a(var2);
   }

   public final int f() {
      return this.C43_f681;
   }

   public final void d(int var1, C23 var2) {
      this.C43_f681 = var1;
      this.a(var2);
   }

   public final C38 g() {
      return this.C43_f687;
   }

   public final void a(C38 var1) {
      this.C43_f687 = var1;
   }

   public final C23[] h() {
      return this.C43_f688;
   }

   public final C12 i() {
      return this.C43_f684;
   }

   public final void a(C12 var1) {
      this.C43_f684 = var1;
   }

   public final int j() {
      return this.C43_f682;
   }

   public final void b(int var1) {
      this.C43_f682 = var1;
   }

   public final int k() {
      return this.C43_f685;
   }

   public final void c(int var1) {
      this.C43_f685 = var1;
   }

   public final void a(C23 var1) {
      if (var1 != null) {
         if (this.C43_f685 > 0 && this.C43_f686 != 9) {
            C23 var2 = C26.a(var1, this.C43_f685);
            switch(this.C43_f686) {
            case 0:
               this.C43_f678 = var2.c();
               this.C43_f679 = var2.d();
               break;
            case 1:
               this.C43_f678 = var2.c() + (var2.e() - this.C43_f680) / 2;
               this.C43_f679 = var2.d();
               this.C43_f680 = var2.e();
               break;
            case 2:
               this.C43_f678 = var2.c() + (var2.e() - this.C43_f680);
               this.C43_f679 = var2.d();
               break;
            case 3:
               this.C43_f678 = var2.c();
               this.C43_f679 = var2.d() + (var2.f() - this.C43_f681) / 2;
               this.C43_f681 = var2.f();
               break;
            case 4:
               this.C43_f678 = var2.c();
               this.C43_f679 = var2.d();
               this.C43_f680 = var2.e();
               this.C43_f681 = var2.f();
               break;
            case 5:
               this.C43_f678 = var2.c() + (var2.e() - this.C43_f680);
               this.C43_f679 = var2.d() + (var2.f() - this.C43_f681) / 2;
               this.C43_f681 = var2.f();
               break;
            case 6:
               this.C43_f678 = var2.c();
               this.C43_f679 = var2.d() + (var2.f() - this.C43_f681);
               break;
            case 7:
               this.C43_f678 = var2.c() + (var2.e() - this.C43_f680) / 2;
               this.C43_f679 = var2.d() + (var2.f() - this.C43_f681);
               this.C43_f680 = var2.e();
               break;
            case 8:
               this.C43_f678 = var2.c() + (var2.e() - this.C43_f680);
               this.C43_f679 = var2.d() + (var2.f() - this.C43_f681);
            }
         }

         if (this.C43_f688 != null) {
            for(int var3 = 0; var3 < this.C43_f688.length && this.C43_f688[var3] != null; ++var3) {
               this.C43_f688[var3].a(var1);
            }
         }
      }

   }

   public final void l() {
      if (this.C43_f690 != null) {
         this.C43_f690.a();
         this.C43_f690 = null;
      }

      if (this.C43_f689 != null) {
         this.C43_f689.a();
         this.C43_f689 = null;
      }

      if (this.C43_f688 != null) {
         for(int var1 = 0; var1 < this.C43_f688.length; ++var1) {
            if (this.C43_f688[var1] != null) {
               this.C43_f688[var1].l();
            }

            this.C43_f688[var1] = null;
         }

         this.C43_f688 = null;
      }

      if (this.C43_f687 != null) {
         this.C43_f687 = null;
      }

      if (this.C43_f683 != null) {
         this.C43_f683 = null;
      }

      if (this.C43_f684 != null) {
         this.C43_f684.c();
         this.C43_f684 = null;
      }

   }
}
