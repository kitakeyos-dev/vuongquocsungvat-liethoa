package c;

import a.C26;
import javax.microedition.lcdui.Graphics;

public final class C40 implements C23 {
   private int C40_f623 = -1;
   private int C40_f624 = 0;
   private int C40_f625 = 0;
   private int C40_f626 = 0;
   private int C40_f627 = 0;
   private int C40_f628 = 1;
   private C12 C40_f629 = new C12();
   private int C40_f630 = -1;
   private byte C40_f631 = 9;
   private C38 C40_f632 = null;
   private C23[] C40_f633 = null;
   public byte C40_f634 = -1;
   public byte C40_f635 = -1;
   C31 C40_f636;
   private boolean C40_f637 = true;

   public C40() {
      this.C40_f637 = true;
   }

   public final void a() {
      C35 var1 = new C35(this.C40_f624, this.C40_f625, this.C40_f626, this.C40_f627);
      this.C40_f629.a(var1);
      this.C40_f629.a();
   }

   public final void a(Graphics var1, boolean var2, boolean var3, C23 var4, int[] var5) {
      if (this.C40_f637) {
         if (this.C40_f629 != null) {
            this.C40_f629.a(var1, this.C40_f624, this.C40_f625, this.C40_f626, this.C40_f627, var2, this.C40_f634, this.C40_f635, this.C40_f636);
         }

      }
   }

   public final void a(boolean var1, boolean var2, C23 var3, int[] var4) {
      if (this.C40_f629 != null) {
         var2 = var1;
         C12 var5 = this.C40_f629;
         if (var2) {
            if (var5.C12_f191 != null) {
               var5.C12_f191.b();
               return;
            }
         } else if (var5.C12_f195 != null) {
            var5.C12_f195.b();
         }
      }

   }

   public final void a(boolean var1) {
      this.C40_f637 = var1;
   }

   public final int b() {
      return this.C40_f623;
   }

   public final void a(int var1) {
      this.C40_f623 = var1;
   }

   public final int c() {
      return this.C40_f624;
   }

   public final void a(int var1, C23 var2) {
      this.C40_f624 = var1;
      this.a(var2);
   }

   public final int d() {
      return this.C40_f625;
   }

   public final void b(int var1, C23 var2) {
      this.C40_f625 = var1;
      this.a(var2);
   }

   public final int e() {
      return this.C40_f626;
   }

   public final void c(int var1, C23 var2) {
      this.C40_f626 = var1;
      this.a(var2);
   }

   public final int f() {
      return this.C40_f627;
   }

   public final void d(int var1, C23 var2) {
      this.C40_f627 = var1;
      this.a(var2);
   }

   public final C38 g() {
      return this.C40_f632;
   }

   public final void a(C38 var1) {
      this.C40_f632 = var1;
   }

   public final C23[] h() {
      return null;
   }

   public final C12 i() {
      return this.C40_f629;
   }

   public final void a(C12 var1) {
      this.C40_f629 = var1;
   }

   public final int j() {
      return this.C40_f628;
   }

   public final void b(int var1) {
      this.C40_f628 = var1;
   }

   public final int k() {
      return this.C40_f630;
   }

   public final void c(int var1) {
      this.C40_f630 = var1;
   }

   public final void a(C23 var1) {
      if (var1 != null && this.C40_f630 > 0 && this.C40_f631 != 9) {
         var1 = C26.a(var1, this.C40_f630);
         switch(this.C40_f631) {
         case 0:
            this.C40_f624 = var1.c();
            this.C40_f625 = var1.d();
            return;
         case 1:
            this.C40_f624 = var1.c() + (var1.e() - this.C40_f626) / 2;
            this.C40_f625 = var1.d();
            this.C40_f626 = var1.e();
            break;
         case 2:
            this.C40_f624 = var1.c() + (var1.e() - this.C40_f626);
            this.C40_f625 = var1.d();
            return;
         case 3:
            this.C40_f624 = var1.c();
            this.C40_f625 = var1.d() + (var1.f() - this.C40_f627) / 2;
            this.C40_f627 = var1.f();
            return;
         case 4:
            this.C40_f624 = var1.c();
            this.C40_f625 = var1.d();
            this.C40_f626 = var1.e();
            this.C40_f627 = var1.f();
            return;
         case 5:
            this.C40_f624 = var1.c() + (var1.e() - this.C40_f626);
            this.C40_f625 = var1.d() + (var1.f() - this.C40_f627) / 2;
            this.C40_f627 = var1.f();
            return;
         case 6:
            this.C40_f624 = var1.c();
            this.C40_f625 = var1.d() + (var1.f() - this.C40_f627);
            return;
         case 7:
            this.C40_f624 = var1.c() + (var1.e() - this.C40_f626) / 2;
            this.C40_f625 = var1.d() + (var1.f() - this.C40_f627);
            this.C40_f626 = var1.e();
            return;
         case 8:
            this.C40_f624 = var1.c() + (var1.e() - this.C40_f626);
            this.C40_f625 = var1.d() + (var1.f() - this.C40_f627);
            return;
         }
      }

   }

   public final void l() {
      if (this.C40_f632 != null) {
         this.C40_f632 = null;
      }

      if (this.C40_f629 != null) {
         this.C40_f629.c();
         this.C40_f629 = null;
      }

      if (this.C40_f636 != null) {
         this.C40_f636 = null;
      }

   }
}
