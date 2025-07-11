package a.a;

import a.GameUtils;
import a.GameEngineBase;
import a.b.C60;
import a.b.C62;
import game.C25;
import javax.microedition.lcdui.Graphics;

public class C20 extends C60 {
   public C62 C20_f261 = new C62();
   public C20 C20_f262 = null;
   protected boolean C20_f263;

   public boolean a(int var1, boolean var2) {
      return this.C20_f261.a(var1, var2);
   }

   public final void a(int var1, int var2, boolean var3) {
      this.C20_f261.a(var1, var2, true);
   }

   public final boolean a(byte var1, byte var2, boolean var3) {
      return this.C20_f261.a(var1, var2, var3);
   }

   public final void a(byte var1, byte var2, byte var3) {
      this.C20_f261.a(var1, (byte)-1);
      super.C60_f866 = var3;
   }

   public final boolean a() {
      return !this.C60_f858 ? false : this.C20_f261.e();
   }

   public final boolean b() {
      return this.C20_f261.f();
   }

   public final void a(Graphics var1, int var2, int var3) {
      if (this.C60_f858) {
         if (this.C60_f866 == 3) {
            this.C20_f261.a(var1, this.C60_f861 - var2, this.C60_f862 - var3, (byte)1);
         } else {
            this.C20_f261.a(var1, this.C60_f861 - var2, this.C60_f862 - var3, (byte)0);
         }
      }
   }

   public final void b(Graphics var1, int var2, int var3) {
      if (this.C20_f263) {
         if (this.C60_f866 == 3) {
            this.C20_f261.a(var1, this.C60_f861 - var2, this.C60_f862 - var3, (byte)3);
         } else {
            this.C20_f261.a(var1, this.C60_f861 - var2, this.C60_f862 - var3, (byte)4);
         }
      }
   }

   public void c() {
      this.b(true);
      this.c(true);
      this.d(true);
   }

   public void d() {
      this.b(false);
      this.c(false);
      this.d(false);
   }

   public final void e() {
      if (this.C20_f262 != null) {
         this.C20_f262.d();
      }

   }

   public final void a(boolean var1) {
      this.C20_f263 = var1;
   }

   public void a(int var1) {
      switch(this.C60_f866) {
      case 0:
         this.e(var1);
         break;
      case 1:
         this.d(var1);
         break;
      case 2:
         this.e(-var1);
         break;
      case 3:
         this.d(-var1);
      }

      if (this.C20_f262 != null) {
         this.C20_f262.b(this.C60_f861, this.C60_f862);
      }

   }

   public final void a(int var1, int var2) {
      switch(var1) {
      case 0:
         this.e(4);
         break;
      case 1:
         this.d(4);
         break;
      case 2:
         this.e(-4);
         break;
      case 3:
         this.d(-4);
      }

      if (this.C20_f262 != null) {
         this.C20_f262.b(this.C60_f861, this.C60_f862);
      }

   }

   public final void f() {
      if (this.C20_f261.i()) {
         this.d(true);
      } else if (GameUtils.checkCollisionWithShortArray(C25.B().C25_f283.C68_f943, C25.B().C25_f283.C68_f944, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight(), this.C60_f861, this.C60_f862, this.C20_f261.j())) {
         this.d(true);
      } else {
         this.d(false);
      }
   }

   public final void g() {
      this.C20_f261.c();
   }

   public final void b(int var1) {
      this.C20_f261.a(var1);
   }
}
