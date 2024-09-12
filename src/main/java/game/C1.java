package game;

import a.C26;
import a.C44;
import a.C65;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public final class C1 extends Canvas implements Runnable {
   private static C1 C1_f1 = null;
   private static GameMIDLet C1_f2;
   private static C65 C1_f3;
   private C55 C1_f4;
   public static C1 C1_f5;
   private long C1_f6 = 0L;
   private long C1_f7 = 0L;
   private long C1_f8 = 0L;
   private int C1_f9 = 0;

   public static C1 a(GameMIDLet var0) {
      if (C1_f1 == null) {
         C1_f1 = new C1(var0);
      }

      return C1_f1;
   }

   public static C1 a() {
      return C1_f1;
   }

   private C1(GameMIDLet var1) {
      C1_f5 = this;
      this.setFullScreenMode(true);
      C1_f2 = var1;
      C44.a((int)66);
      C44.a((short)this.getWidth(), (short)this.getHeight());
      this.C1_f4 = C55.B();
      this.C1_f4.C();
      C55 var2 = this.C1_f4;
      if (C1_f3 != null) {
         C1_f3.d(false);
         C1_f3 = null;
      }

      if (var2 != null) {
         var2.d(true);
         C1_f3 = var2;
      }

      (new Thread(this)).start();
   }

   protected final void hideNotify() {
      if (!C44.C44_f703 && this.C1_f4.D() > 1) {
         this.C1_f4.E();
      }

   }

   protected final void paint(Graphics var1) {
      if (this.C1_f4.D() > 1) {
         this.C1_f4.a(var1);
      }

   }

   public final void run() {
      while(this.C1_f4.D() > 1) {
         this.C1_f6 = System.currentTimeMillis();
         this.C1_f4.a();
         this.repaint();
         this.serviceRepaints();
         this.C1_f7 = System.currentTimeMillis();
         this.C1_f8 = this.C1_f7 - this.C1_f6;
         if (this.C1_f8 > (long)C44.k()) {
            this.C1_f8 = (long)C44.k();
         }

         try {
            Thread.sleep((long)C44.k() - this.C1_f8);
         } catch (InterruptedException var1) {
         }
      }

      C1_f2.destroyApp(true);
   }

   protected final void keyPressed(int var1) {
      if (this.C1_f4 != null) {
         this.C1_f4.j(var1);
      }

   }

   protected final void keyReleased(int var1) {
      if (this.C1_f4 != null) {
         this.C1_f4.k(var1);
      }

   }

   protected final void pointerPressed(int var1, int var2) {
      this.C1_f9 = 0;
      if (this.C1_f4.D() == 13) {
         if (C26.a(var1, var2, 38, 225, 50, 40)) {
            this.C1_f9 = -6;
         } else if (C26.a(var1, var2, 150, 225, 50, 40)) {
            this.C1_f9 = -7;
         }
      } else if (C26.a(var1, var2, 0, 280, 40, 40)) {
         this.C1_f9 = -6;
      } else if (C26.a(var1, var2, 200, 280, 40, 40)) {
         this.C1_f9 = -7;
      }

      this.keyPressed(this.C1_f9);
      if (this.C1_f4 != null) {
         this.C1_f4.c(var1, var2);
      }

   }

   protected final void pointerReleased(int var1, int var2) {
      this.keyReleased(this.C1_f9);
      if (this.C1_f4 != null) {
         this.C1_f4.d(var1, var2);
      }

   }
}
