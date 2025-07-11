package layout;

import a.GameUtils;
import a.GameEngineBase;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public final class DialogData {
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
   public SpriteRenderer C12_f191;
   public int C12_f192;
   public int C12_f193;
   public int C12_f194;
   public SpriteRenderer C12_f195;
   private Rectangle C12_f196 = null;
   private String C12_f197 = "";
   private boolean[] C12_f198 = new boolean[]{false, false};

   public DialogData() {
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

   private static void a(Graphics var0, Rectangle var1, int var2) {
      if (var0 != null && var2 >> 24 != 0) {
         var0.setColor(var2);
         var0.fillRect(var1.x, var1.y, var1.width, var1.height);
      }
   }

   public final void a(Rectangle var1) {
      this.C12_f196 = var1;
   }

   public final void a() {
      this.C12_f180[1] = -this.C12_f196.height;
      this.C12_f180[0] = -this.C12_f196.width / 2;
      this.C12_f198[0] = this.C12_f198[1] = false;
   }

   public final boolean b() {
      return this.C12_f198[1];
   }

   private void a(Graphics var1, Rectangle var2, String var3, int var4, int var5, boolean var6, byte var7, DialogConfig var8, byte var9) {
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
               int var13 = var11 * var2.width / 100;
               var1.setColor(var4);
               var1.fillRect(var2.x + 1, var2.y + 1, var13 - 1, var2.height - 1);
               return;
            }
         }

         Font var14 = Font.getFont(0, 0, 8);
         if (!this.C12_f197.equals(var3)) {
            this.C12_f180[1] = -var2.height;
            this.C12_f180[0] = -var2.width / 2;
         }

         this.C12_f197 = var3;
         GameUtils.a(var1, var3, var4, var2.x, var2.y, var14.getHeight(), var2.width, var2.height, var14, var6, var5, this.C12_f180, this.C12_f181, var7, var8, var9, this.C12_f198);
      }
   }

   private static void b(Graphics var0, Rectangle var1, int var2) {
      if (var0 != null && var2 >> 24 != 0) {
         var0.setColor(var2);
         var0.drawRect(var1.x, var1.y, var1.width, var1.height);
      }
   }

   public final void a(Graphics var1, int var2, int var3, int var4, int var5, boolean var6, byte var7, byte var8, DialogConfig var9) {
      if (this.C12_f188 != var6 || this.C12_f189 == 0) {
         this.C12_f189 = -1;
         this.C12_f188 = var6;
      }

      Rectangle var10 = new Rectangle(var2, var3, var4, var5);
      this.C12_f196 = var10;
      var1.setClip(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
      if (var6) {
         a(var1, this.C12_f196, this.C12_f185);
         b(var1, this.C12_f196, this.C12_f186);
         if (this.C12_f191 != null) {
            this.C12_f191.render(var1, this.C12_f196, this.C12_f183);
         }

         this.a(var1, this.C12_f196, this.C12_f179, this.C12_f187, this.C12_f182, this.C12_f184, var7, var9, var8);
      } else {
         a(var1, this.C12_f196, this.C12_f192);
         b(var1, this.C12_f196, this.C12_f193);
         if (this.C12_f195 != null) {
            this.C12_f195.render(var1, this.C12_f196, this.C12_f183);
         }

         this.a(var1, this.C12_f196, this.C12_f179, this.C12_f194, this.C12_f182, this.C12_f184, var7, var9, var8);
      }
   }

   public final void c() {
      if (this.C12_f191 != null) {
         this.C12_f191.cleanup();
         this.C12_f191 = null;
      }

      if (this.C12_f195 != null) {
         this.C12_f195.cleanup();
         this.C12_f195 = null;
      }

   }
}
