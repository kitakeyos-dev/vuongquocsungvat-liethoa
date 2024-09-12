package c;

import a.b.C62;
import javax.microedition.lcdui.Graphics;

public final class C17 {
   private C62 C17_f219 = null;
   private byte C17_f220 = 0;
   private short C17_f221 = -1;
   public byte C17_f222 = 4;

   public final void a(int var1, boolean var2, byte var3) {
      if (var1 != -1) {
         this.C17_f219 = new C62();
         this.C17_f219.a(var1, var2);
         switch(this.C17_f222) {
         case 3:
            this.C17_f219.a((byte)this.C17_f221, var3, true);
         }
      }

   }

   public final void a(byte var1, byte var2) {
      this.C17_f221 = (short)var1;
      this.C17_f219.a(var1, var2, true);
   }

   public final C62 a() {
      return this.C17_f219;
   }

   public final void a(int var1) {
      this.C17_f221 = (short)var1;
   }

   public final void a(byte var1) {
      this.C17_f220 = 1;
   }

   public C17() {
      this.C17_f219 = null;
      this.C17_f221 = -1;
      this.C17_f222 = 4;
   }

   public final void a(Graphics var1, C35 var2, int var3) {
      if (this.C17_f219 != null) {
         boolean var4 = false;
         C35 var10000;
         if (this.C17_f221 == -1) {
            var10000 = null;
         } else {
            int[] var5 = new int[4];
            switch(this.C17_f222) {
            case 2:
               var5 = this.C17_f219.b(this.C17_f221, (byte)0);
               break;
            case 3:
               var5 = this.C17_f219.a((int)this.C17_f221, (byte)0);
            }

            var10000 = new C35(var5[0], var5[1], var5[2], var5[3]);
         }

         C35 var7 = var10000;
         if (var10000 == null) {
            return;
         }

         int var8 = var2.C35_f557;
         int var6 = var2.C35_f558;
         switch(var3) {
         case 0:
            var8 = var2.C35_f557 - var7.C35_f557;
            var6 = var2.C35_f558 - var7.C35_f558;
            break;
         case 1:
            var8 = var2.C35_f557 + (var2.C35_f559 - var7.C35_f559) / 2 - var7.C35_f557;
            var6 = var2.C35_f558 - var7.C35_f558;
            break;
         case 2:
            var8 = var2.C35_f557 + (var2.C35_f559 - var7.C35_f559) - var7.C35_f557;
            var6 = var2.C35_f558 - var7.C35_f558;
            break;
         case 3:
            var8 = var2.C35_f557 - var7.C35_f557;
            var6 = var2.C35_f558 + (var2.C35_f560 - var7.C35_f560) / 2 - var7.C35_f558;
            break;
         case 4:
            var8 = var2.C35_f557 + (var2.C35_f559 - var7.C35_f559) / 2 - var7.C35_f557;
            var6 = var2.C35_f558 + (var2.C35_f560 - var7.C35_f560) / 2 - var7.C35_f558;
            break;
         case 5:
            var8 = var2.C35_f557 + (var2.C35_f559 - var7.C35_f559) - var7.C35_f557;
            var6 = var2.C35_f558 + (var2.C35_f560 - var7.C35_f560) / 2 - var7.C35_f558;
            break;
         case 6:
            var8 = var2.C35_f557 - var7.C35_f557;
            var6 = var2.C35_f558 + (var2.C35_f560 - var7.C35_f560) - var7.C35_f558;
            break;
         case 7:
            var8 = var2.C35_f557 + (var2.C35_f559 - var7.C35_f559) / 2 - var7.C35_f557;
            var6 = var2.C35_f558 + (var2.C35_f560 - var7.C35_f560) - var7.C35_f558;
            break;
         case 8:
            var8 = var2.C35_f557 + (var2.C35_f559 - var7.C35_f559) - var7.C35_f557;
            var6 = var2.C35_f558 + (var2.C35_f560 - var7.C35_f560) - var7.C35_f558;
         }

         if (this.C17_f222 == 3) {
            this.C17_f219.a(var1, var8, var6, this.C17_f220);
            return;
         }

         if (this.C17_f222 == 2) {
            this.C17_f219.a(var1, this.C17_f221, var8, var6, (byte)0, 20);
         }
      }

   }

   public final void b() {
      if (this.C17_f222 == 3) {
         this.C17_f219.e();
      }

   }

   public final void c() {
      if (this.C17_f219 != null) {
         this.C17_f219.b();
         this.C17_f219 = null;
      }

   }
}
