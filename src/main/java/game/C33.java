package game;

import a.C44;
import a.a.C20;
import a.a.C30;
import a.a.C46;
import a.b.C6;
import a.b.C60;
import a.b.C68;
import javax.microedition.lcdui.Graphics;

public final class C33 extends C44 {
   private C20 C33_f540;
   private C46 C33_f541 = null;
   private static C33 C33_f542 = null;
   private C68 C33_f543 = null;
   private C6 C33_f544 = null;
   private boolean C33_f545 = false;
   private byte C33_f546 = 0;
   public boolean C33_f547 = false;

   public static C33 B() {
      if (C33_f542 == null) {
         C33_f542 = new C33();
      }

      return C33_f542;
   }

   public C33() {
      this.C33_f544 = new C6();
      this.C33_f543 = new C68();
      this.C33_f541 = new C46();
      this.C33_f540 = new C20();
   }

   public final void C() {
      this.C33_f545 = false;
   }

   public final void a() {
      if (this.C33_f545) {
         byte var2;
         switch(this.C33_f546) {
         case 0:
            var2 = 0;
            this.C33_f540.a(this.C33_f540.C60_f855[var2]);
            if (this.C33_f540.C60_f862 - this.C33_f543.C68_f944 > h()) {
               this.C33_f547 = true;
            }
            break;
         case 2:
            var2 = 0;
            this.C33_f540.a(this.C33_f540.C60_f855[var2]);
            if (this.C33_f540.C60_f862 - this.C33_f543.C68_f944 < 0) {
               this.C33_f547 = true;
            }
         }

         this.C33_f541.b();
      }
   }

   public final void a(Graphics var1) {
      this.C33_f541.a(var1);
   }

   public final boolean b() {
      this.C33_f540.C60_f855 = new short[3];
      byte var4 = 5;
      byte var3 = 0;
      this.C33_f540.C60_f855[var3] = var4;
      this.C33_f540.a(343, false);
      this.d(this.C33_f546);
      this.C33_f540.c();
      this.C33_f541.a((C60)this.C33_f540);
      this.C33_f543.a(101);
      this.C33_f541.a(this.C33_f543);
      this.C33_f544.a(this.C33_f540, g(), h(), true);
      this.C33_f541.a(this.C33_f544);
      this.C33_f541.b();
      return true;
   }

   public final void c() {
      this.C33_f540 = null;
      this.C33_f541 = null;
      C33_f542 = null;
      this.C33_f543 = null;
      this.C33_f544 = null;
   }

   public final void a(byte var1) {
      this.C44_f699 = this.C44_f698;
      switch(var1) {
      case 1:
         C30.a().c(0, 13);
         C30.a().a(5, 1, g(), 30, 30);
         break;
      case 2:
         this.C33_f547 = false;
         this.C33_f545 = true;
         break;
      case 3:
         this.C33_f547 = false;
         C30.a().c(0, 12);
         C30.a().a(5, 1, g(), 30, 30);
      }

      this.C44_f698 = var1;
   }

   public final void d(byte var1) {
      this.C33_f546 = var1;
      this.C33_f540.C60_f866 = var1;
      short var2 = (short)(g() >> 1);
      short var3 = 0;
      switch(var1) {
      case 0:
         var2 = (short)(g() >> 1);
         var3 = 10;
         break;
      case 2:
         var2 = (short)(g() >> 1);
         var3 = (short)(h() - 10);
      }

      this.C33_f540.b(var2, var3);
      this.C33_f540.a(var1, (byte)-1, false);
   }
}
