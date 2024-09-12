package c;

import a.C26;
import a.C44;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public final class C12 {
   public String C12_f179 = "";
   private int[] C12_f180 = new int[2];
   private int C12_f181;
   public int C12_f182;
   public int C12_f183;
   public boolean C12_f184;
   public int C12_f185;
   public int C12_f186;
   public int C12_f187;
   private boolean C12_f188 = false;
   private int C12_f189 = 0;
   public byte C12_f190 = -1;
   public C17 C12_f191;
   public int C12_f192;
   public int C12_f193;
   public int C12_f194;
   public C17 C12_f195;
   private C35 C12_f196 = null;
   private String C12_f197 = "";
   private boolean[] C12_f198 = new boolean[]{false, false};

   public C12() {
      this.C12_f180[1] = this.C12_f180[0] = 0;
      this.C12_f181 = 2;
      this.C12_f182 = 4;
      this.C12_f183 = 4;
      this.C12_f184 = false;
      this.C12_f185 = 16777215;
      this.C12_f186 = 16777215;
      this.C12_f187 = 16777215;
      this.C12_f191 = null;
      this.C12_f192 = 16777215;
      this.C12_f193 = 16777215;
      this.C12_f194 = 16777215;
      this.C12_f195 = null;
      this.C12_f188 = false;
      this.C12_f190 = -1;
      this.C12_f189 = 0;
   }

   private static void a(Graphics var0, C35 var1, int var2) {
      if (var0 != null && var2 >> 24 != 0) {
         var0.setColor(var2);
         var0.fillRect(var1.C35_f557, var1.C35_f558, var1.C35_f559, var1.C35_f560);
      }
   }

   public final void a(C35 var1) {
      this.C12_f196 = var1;
   }

   public final void a() {
      this.C12_f180[1] = -this.C12_f196.C35_f560;
      this.C12_f180[0] = -this.C12_f196.C35_f559 / 2;
      this.C12_f198[0] = this.C12_f198[1] = false;
   }

   public final boolean b() {
      return this.C12_f198[1];
   }

   private void a(Graphics var1, C35 var2, String var3, int var4, int var5, boolean var6, byte var7, C31 var8, byte var9) {
      if (var1 != null && var4 >> 24 != 0) {
         if (var3.startsWith("#P") && var3.length() > 2) {
            boolean var10 = false;
            int var11 = 0;

            try {
               var11 = Integer.parseInt(var3.substring(2).trim());
            } catch (Exception var12) {
               var10 = true;
            }

            if (!var10 && var11 >= 0 && var11 <= 100) {
               int var13 = var11 * var2.C35_f559 / 100;
               var1.setColor(var4);
               var1.fillRect(var2.C35_f557 + 1, var2.C35_f558 + 1, var13 - 1, var2.C35_f560 - 1);
               return;
            }
         }

         Font var14 = Font.getFont(0, 0, 8);
         if (!this.C12_f197.equals(var3)) {
            this.C12_f180[1] = -var2.C35_f560;
            this.C12_f180[0] = -var2.C35_f559 / 2;
         }

         this.C12_f197 = var3;
         C26.a(var1, var3, var4, var2.C35_f557, var2.C35_f558, var14.getHeight(), var2.C35_f559, var2.C35_f560, var14, var6, var5, this.C12_f180, this.C12_f181, var7, var8, var9, this.C12_f198);
      }
   }

   private static void b(Graphics var0, C35 var1, int var2) {
      if (var0 != null && var2 >> 24 != 0) {
         var0.setColor(var2);
         var0.drawRect(var1.C35_f557, var1.C35_f558, var1.C35_f559, var1.C35_f560);
      }
   }

   public final void a(Graphics var1, int var2, int var3, int var4, int var5, boolean var6, byte var7, byte var8, C31 var9) {
      if (this.C12_f188 != var6 || this.C12_f189 == 0) {
         this.C12_f189 = -1;
         this.C12_f188 = var6;
      }

      C35 var10 = new C35(var2, var3, var4, var5);
      this.C12_f196 = var10;
      var1.setClip(0, 0, C44.g(), C44.h());
      if (var6) {
         a(var1, this.C12_f196, this.C12_f185);
         b(var1, this.C12_f196, this.C12_f186);
         if (this.C12_f191 != null) {
            this.C12_f191.a(var1, this.C12_f196, this.C12_f183);
         }

         this.a(var1, this.C12_f196, this.C12_f179, this.C12_f187, this.C12_f182, this.C12_f184, var7, var9, var8);
      } else {
         a(var1, this.C12_f196, this.C12_f192);
         b(var1, this.C12_f196, this.C12_f193);
         if (this.C12_f195 != null) {
            this.C12_f195.a(var1, this.C12_f196, this.C12_f183);
         }

         this.a(var1, this.C12_f196, this.C12_f179, this.C12_f194, this.C12_f182, this.C12_f184, var7, var9, var8);
      }
   }

   public final void c() {
      if (this.C12_f191 != null) {
         this.C12_f191.c();
         this.C12_f191 = null;
      }

      if (this.C12_f195 != null) {
         this.C12_f195.c();
         this.C12_f195 = null;
      }

   }
}
