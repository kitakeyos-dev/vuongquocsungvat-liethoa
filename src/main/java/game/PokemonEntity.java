package game;

import engine.GameUtils;
import engine.entity.GameObject;
import engine.graphics.EffectEntity;
import engine.entity.Sprite;
import engine.entity.ResourceManager;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class PokemonEntity extends GameObject {
   private static short[] C41_f638 = new short[]{90, 95, 100, 110, 125};
   public static final byte[] C41_f639 = new byte[]{12, 30, 5};
   EffectEntity C41_f640;
   short[][] C41_f641;
   short[][] C41_f642;
   byte[][] C41_f643;
   private byte[] C41_f644;
   short[] C41_f645;
   byte[] C41_f646;
   private byte C41_f647;
   private short[] C41_f648;
   protected int C41_f649 = 0;
   private int C41_f650;
   private int C41_f651 = 0;
   private int C41_f652 = 0;
   protected int C41_f653 = 0;
   private int C41_f654 = 0;
   protected short C41_f655;
   private byte C41_f656 = 0;
   private int C41_f657 = 0;
   private byte C41_f658;
   protected byte C41_f659;
   private int C41_f660 = 0;
   private boolean C41_f661;
   protected short C41_f662;
   protected byte C41_f663;
   protected Vector C41_f664 = new Vector();
   protected Vector C41_f665 = new Vector();
   public byte C41_f666 = 0;
   protected boolean C41_f667;
   protected short[] C41_f668;
   protected EffectEntity C41_f669 = null;
   private byte C41_f670 = 0;

   public PokemonEntity() {
      this.primaryStates = new short[23];
      this.secondaryStates = new short[23];
      this.C41_f645 = new short[5];
      this.C41_f646 = new byte[5];
      this.C41_f648 = new short[4];

      for(int var1 = 0; var1 < this.C41_f646.length; ++var1) {
         this.C41_f646[var1] = -1;
      }

      this.C41_f668 = new short[16];
      this.C41_f641 = new short[16][5];
      this.C41_f642 = new short[11][5];
      this.C41_f643 = new byte[][]{{-1, -1, -1}, {-1, -1, -1}};
      this.C41_f644 = new byte[2];
      this.useAlternativeRender = false;
   }

   public final void a(int var1, int var2, short var3, byte var4, short var5, byte var6) {
      this.C41_f657 = var1;
      this.C41_f654 = var2;
      short var7;
      byte var8;
      if (var5 == -1) {
         var7 = (short) GameUtils.getRandomInRange(ResourceManager.gameDatabase[0][this.C41_f657][3], ResourceManager.gameDatabase[0][this.C41_f657][3]);
         var8 = 0;
         super.primaryStates[var8] = var7;
      } else {
         var7 = var5;
         var8 = 0;
         super.primaryStates[var8] = var7;
      }

      int var10002 = ResourceManager.gameDatabase[0][this.C41_f657][5] + ResourceManager.gameDatabase[0][this.C41_f657][6] * var2 + ResourceManager.gameDatabase[0][this.C41_f657][7];
      var8 = 0;
      var7 = (short)(var10002 * C41_f638[super.primaryStates[var8] - 1] / 100);
      var8 = 1;
      super.primaryStates[var8] = var7;
      var10002 = ResourceManager.gameDatabase[0][this.C41_f657][8] + ResourceManager.gameDatabase[0][this.C41_f657][9] * var2 + ResourceManager.gameDatabase[0][this.C41_f657][10];
      var8 = 0;
      var7 = (short)(var10002 * C41_f638[super.primaryStates[var8] - 1] / 100);
      var8 = 2;
      super.primaryStates[var8] = var7;
      var10002 = ResourceManager.gameDatabase[0][this.C41_f657][11] + ResourceManager.gameDatabase[0][this.C41_f657][12] * var2 / 10 + ResourceManager.gameDatabase[0][this.C41_f657][13];
      var8 = 0;
      var7 = (short)(var10002 * C41_f638[super.primaryStates[var8] - 1] / 100);
      var8 = 3;
      super.primaryStates[var8] = var7;
      var10002 = ResourceManager.gameDatabase[0][this.C41_f657][14] + ResourceManager.gameDatabase[0][this.C41_f657][15] * var2 / 10 + ResourceManager.gameDatabase[0][this.C41_f657][16];
      var8 = 0;
      var7 = (short)(var10002 * C41_f638[super.primaryStates[var8] - 1] / 100);
      var8 = 4;
      super.primaryStates[var8] = var7;
      var8 = 5;
      super.primaryStates[var8] = var3;
      var7 = (short)var4;
      var8 = 6;
      super.primaryStates[var8] = var7;
      this.i(var6);
      this.saveCurrentStates();
      this.C41_f655 = ResourceManager.gameDatabase[0][this.C41_f657][17];
      var8 = 1;
      this.u(super.secondaryStates[var8]);
   }

   public final void a(int[] var1) {
      this.a(var1[0], var1[1], (short)var1[2], (byte)var1[3], (short)var1[4], (byte)var1[5]);
      this.V();
      this.a((short)var1[6], var1[7], var1[8]);
      int[] var2 = new int[var1.length - 9];

      for(int var3 = 0; var3 < var2.length; ++var3) {
         var2[var3] = var1[var3 + 9];
      }

      this.b(var2);
   }

   public final void a(short var1, int var2, int var3) {
      this.V();
      byte var4 = 1;
      super.secondaryStates[var4] = var1;
      var4 = 1;
      this.u(super.secondaryStates[var4]);
      this.C41_f652 = var2;
      this.C41_f662 = (byte)var3;
   }

   public final void activate() {
      super.activate();
      if (this.sprite == null) {
         this.sprite = new Sprite();
      }

      this.sprite.loadSpriteSet(this.C41_f655, false);
      this.a((byte)0, true);
   }

   public final void deactivate() {
      super.deactivate();
      if (this.sprite != null) {
         this.sprite.forceCleanup();
         this.sprite = null;
      }

   }

   public final void a(short var1, byte var2) {
      byte var3 = this.currentDirection;
      this.C41_f640 = null;
      this.C41_f640 = new EffectEntity();
      this.C41_f640.initializeEffect(new short[]{var1, (short)var2, (short)var3});
      this.C41_f640.setWorldPosition(this.worldX, this.worldY);
      if (var1 == 20 && var2 == 3 || var1 == 22 && var2 == 4) {
         int[] var4 = this.sprite.getSpritePartBounds(0, var3);
         this.C41_f640.setWorldPosition(this.worldX, this.worldY - var4[3]);
      }

      this.C41_f640.setInteractable(true);
   }

   private void z(int var1) {
      this.C41_f669 = new EffectEntity();
      short[] var3;
      short[] var2 = new short[(var3 = BattleSystemManager.C29_f450[var1]).length + 5];
      System.arraycopy(var3, 1, var2, 6, var3.length - 1);
      System.arraycopy(var3 = new short[]{var3[0], (short)super.worldX, (short)super.worldY, ResourceManager.gameDatabase[0][this.C41_f657][17], 0, (short)this.currentDirection}, 0, var2, 0, var3.length);
      this.C41_f669.initializeEffect(var2);
      this.C41_f669.setInteractable(true);
   }

   public final void a(byte var1, boolean var2) {
      label31:
      switch(var1) {
      case 0:
         this.sprite.setAnimation(var1, (byte)-1, true);
         break;
      case 1:
         this.sprite.setAnimation((byte)var1, (byte)0, true);
         switch(this.C41_f657) {
         case 0:
            this.z(27);
            break label31;
         case 10:
            this.C41_f670 = 1;
            this.z(28);
            break label31;
         case 60:
         case 61:
            this.z(22);
            break label31;
         case 62:
            this.z(24);
            break label31;
         case 67:
            this.C41_f670 = 1;
            this.z(30);
            break label31;
         case 68:
            this.C41_f670 = 1;
            this.z(31);
            break label31;
         case 70:
            this.C41_f670 = 1;
            this.z(29);
            break label31;
         case 71:
            this.C41_f670 = 1;
            this.z(32);
            break label31;
         case 72:
            this.C41_f670 = 1;
            this.z(33);
            break label31;
         case 75:
            this.z(20);
            break label31;
         case 87:
            this.z(21);
            break label31;
         case 91:
            this.z(26);
            break label31;
         case 92:
            this.z(25);
            break label31;
         case 97:
         case 98:
            this.z(23);
         default:
            break label31;
         }
      case 2:
         this.sprite.setAnimation((byte)var1, (byte)0, true);
         break;
      case 3:
         if (BattleSystemManager.B().C29_f398 == 0) {
            this.deactivate();
            short[] var3 = new short[]{16, 0, 0, 4};
            this.C41_f669 = new EffectEntity();
            short[] var4 = new short[var3.length + 5];
            System.arraycopy(var3, 1, var4, 6, var3.length - 1);
            System.arraycopy(var3 = new short[]{var3[0], (short)super.worldX, (short)super.worldY, ResourceManager.gameDatabase[0][this.C41_f657][17], 0, (short)this.currentDirection}, 0, var4, 0, var3.length);
            this.C41_f669.initializeEffect(var4);
            this.C41_f669.setInteractable(true);
            this.C41_f669.activateEffect();
         }
         break;
      case 4:
         this.sprite.setAnimation(var1, (byte)-1, true);
      }

      this.C41_f656 = var1;
   }

   public final void p() {
      this.updateAnimation();
      if (this.C41_f640 != null) {
         this.C41_f640.updateEffect();
      }

      if (this.C41_f669 != null) {
         this.C41_f669.updateEffect();
      }

   }

   public final void a(Graphics var1) {
      if (this.C41_f669 != null && this.C41_f656 == 1) {
         switch(this.C41_f657) {
         case 0:
            if (this.sprite.isAtFrame(5)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 10:
            if (this.sprite.isAtFrame(5)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 60:
         case 61:
            if (this.sprite.isAtFrame(3)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 62:
            if (this.sprite.isAtFrame(8)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 67:
            if (this.sprite.isAtFrame(1)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 68:
            if (this.sprite.isAtFrame(3)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 70:
            if (this.sprite.isAtFrame(9)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 71:
            if (this.sprite.isAtFrame(7)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 72:
            if (this.sprite.isAtFrame(4)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 75:
            if (this.sprite.isAtFrame(15)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 87:
            if (this.sprite.isAtFrame(1)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 91:
            if (this.sprite.isAtFrame(2)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 92:
            if (this.sprite.isAtFrame(4)) {
               this.C41_f669.activateEffect();
            }
            break;
         case 97:
         case 98:
            if (this.sprite.isAtFrame(8)) {
               this.C41_f669.activateEffect();
            }
         }
      }

      if (this.C41_f669 != null && this.C41_f670 == 0) {
         this.C41_f669.render(var1);
      }

      if (this.C41_f640 != null && BattleSystemManager.C29_f448[this.C41_f640.effectType - 20][this.C41_f640.sprite.getCurrentAnimationId()][this.C41_f640.sprite.getCurrentFrameIndex()] == 0) {
         this.C41_f640.render(var1);
      }

      if (this.isVisible) {
         this.sprite.renderCurrentFrame(var1, this.worldX, this.worldY, this.currentDirection);
      }

      if (this.C41_f669 != null && this.C41_f670 == 1) {
         this.C41_f669.render(var1);
      }

      if (this.C41_f640 != null && BattleSystemManager.C29_f448[this.C41_f640.effectType - 20][this.C41_f640.sprite.getCurrentAnimationId()][this.C41_f640.sprite.getCurrentFrameIndex()] == 1) {
         this.C41_f640.render(var1);
      }

   }

   public final byte q() {
      return this.C41_f656;
   }

   public final int r() {
      return this.C41_f657;
   }

   public final void f(int var1) {
      this.C41_f660 = var1;
   }

   public final int s() {
      return this.C41_f660;
   }

   public final int t() {
      return this.C41_f654;
   }

   public final boolean u() {
      return this.C41_f654 == 50;
   }

   protected final void g(int var1) {
      if (this.C41_f654 < 50) {
         this.C41_f652 += var1;
         if (this.C41_f652 < 0) {
            this.C41_f652 = 0;
         }

      }
   }

   public final int v() {
      return this.C41_f654 >= 50 ? A(50) : A(this.C41_f654 + 1);
   }

   public final void w() {
      ++this.C41_f654;
      this.g(-A(this.C41_f654));
      this.K();

      for(int var1 = 0; var1 < this.C41_f646.length; ++var1) {
         if (this.C41_f646[var1] != -1) {
            this.C41_f645[var1] = ResourceManager.gameDatabase[1][this.C41_f646[var1]][5];
         }
      }

      this.W();
   }

   public final void h(int var1) {
      this.C41_f654 += var1;
      this.K();
      this.W();
   }

   private static int A(int var0) {
      return var0 * 15 * var0 - 200;
   }

   private void V() {
      short var1;
      byte var3;
      if (PlayerCharacter.p().b((byte)1, (byte)0) == 2 && PlayerCharacter.p().b((byte)1, (byte)1) == 1) {
         var3 = 1;
         var1 = (short)(super.primaryStates[var3] * ResourceManager.gameDatabase[2][1][6] / 100);
         var3 = 1;
         var1 += super.primaryStates[var3];
         var3 = 1;
         super.primaryStates[var3] = var1;
      }

      if (PlayerCharacter.p().b((byte)2, (byte)0) == 2 && PlayerCharacter.p().b((byte)2, (byte)1) == 1) {
         var3 = 3;
         var1 = (short)(super.primaryStates[var3] * ResourceManager.gameDatabase[2][2][6] / 100);
         var3 = 3;
         var1 += super.primaryStates[var3];
         var3 = 3;
         super.primaryStates[var3] = var1;
      }

   }

   private void W() {
      int var10002 = ResourceManager.gameDatabase[0][this.C41_f657][5] + ResourceManager.gameDatabase[0][this.C41_f657][6] * this.C41_f654 + ResourceManager.gameDatabase[0][this.C41_f657][7];
      byte var2 = 0;
      short var3 = (short)(var10002 * C41_f638[super.primaryStates[var2] - 1] / 100);
      var2 = 1;
      super.primaryStates[var2] = var3;
      var10002 = ResourceManager.gameDatabase[0][this.C41_f657][8] + ResourceManager.gameDatabase[0][this.C41_f657][9] * this.C41_f654 + ResourceManager.gameDatabase[0][this.C41_f657][10];
      var2 = 0;
      var3 = (short)(var10002 * C41_f638[super.primaryStates[var2] - 1] / 100);
      var2 = 2;
      super.primaryStates[var2] = var3;
      var10002 = ResourceManager.gameDatabase[0][this.C41_f657][11] + ResourceManager.gameDatabase[0][this.C41_f657][12] * this.C41_f654 / 10 + ResourceManager.gameDatabase[0][this.C41_f657][13];
      var2 = 0;
      var3 = (short)(var10002 * C41_f638[super.primaryStates[var2] - 1] / 100);
      var2 = 3;
      super.primaryStates[var2] = var3;
      var10002 = ResourceManager.gameDatabase[0][this.C41_f657][14] + ResourceManager.gameDatabase[0][this.C41_f657][15] * this.C41_f654 / 10 + ResourceManager.gameDatabase[0][this.C41_f657][16];
      var2 = 0;
      var3 = (short)(var10002 * C41_f638[super.primaryStates[var2] - 1] / 100);
      var2 = 4;
      super.primaryStates[var2] = var3;
      this.saveCurrentStates();
      var2 = 1;
      this.u(super.secondaryStates[var2]);
   }

   public static short b(int var0, int var1, int var2) {
      return (short)((ResourceManager.gameDatabase[0][var0][5] + ResourceManager.gameDatabase[0][var0][6] * var1 + ResourceManager.gameDatabase[0][var0][7]) * C41_f638[var2 - 1] / 100);
   }

   public final void x() {
      for(int var1 = 0; var1 < this.C41_f648.length; ++var1) {
         int var10003 = this.C41_f654 - 5;
         byte var3 = 0;
         this.C41_f648[var1] = a(this.C41_f657, var10003, super.primaryStates[var3], var1 + 1);
      }

   }

   public final void y() {
      for(int var1 = 0; var1 < this.C41_f648.length; ++var1) {
         byte var3 = (byte)(var1 + 1);
         this.C41_f648[var1] = super.primaryStates[var3];
      }

   }

   public final short i(int var1) {
      return this.C41_f648[var1];
   }

   public final void z() {
      if (this.C41_f663 < 20) {
         ++this.C41_f663;
         byte var2 = 2;
         short var10002 = super.primaryStates[var2];
         var2 = 2;
         short var3 = (short)(var10002 + super.primaryStates[var2] * this.C41_f663 / 100);
         var2 = 2;
         super.secondaryStates[var2] = var3;
         var2 = 3;
         var10002 = super.primaryStates[var2];
         var2 = 3;
         var3 = (short)(var10002 + super.primaryStates[var2] * this.C41_f663 / 100);
         var2 = 3;
         super.secondaryStates[var2] = var3;
         var2 = 4;
         var10002 = super.primaryStates[var2];
         var2 = 4;
         var3 = (short)(var10002 + super.primaryStates[var2] * this.C41_f663 / 100);
         var2 = 4;
         super.secondaryStates[var2] = var3;
      }

   }

   public final int A() {
      return this.C41_f652;
   }

   public final void j(int var1) {
      this.C41_f651 = var1;
   }

   public final int B() {
      return this.C41_f651;
   }

   public final int C() {
      byte var3;
      if (((PokemonEntity)this.followTarget).C41_f660 == 0 && PlayerCharacter.p().b((byte)4, (byte)0) == 2) {
         var3 = 3;
         short var1 = (short)(this.followTarget.primaryStates[var3] * (100 + ResourceManager.gameDatabase[2][4][5]) / 100);
         var3 = 3;
         this.followTarget.secondaryStates[var3] = var1;
      }

      short var10000;
      int var4;
      if (((PokemonEntity)this.followTarget).f((byte)2)) {
         var3 = 2;
         var10000 = super.secondaryStates[var3];
         var3 = 3;
         var4 = var10000 - this.followTarget.secondaryStates[var3] * (100 + ResourceManager.gameDatabase[3][2][5]) / 100;
      } else {
         var3 = 2;
         var10000 = super.secondaryStates[var3];
         var3 = 3;
         var4 = var10000 - this.followTarget.secondaryStates[var3];
      }

      int var5;
      if (this.f((byte)0)) {
         var3 = 1;
         var10000 = super.secondaryStates[var3];
         short var10001 = ResourceManager.gameDatabase[3][0][5];
         var3 = 1;
         if (var10000 <= var10001 * super.primaryStates[var3] / 100) {
            var3 = 2;
            var5 = super.secondaryStates[var3] * (100 + ResourceManager.gameDatabase[3][0][6]) / 100;
            var3 = 3;
            var4 = var5 - this.followTarget.secondaryStates[var3];
         }
      } else if (this.f((byte)1)) {
         var3 = 2;
         var5 = super.secondaryStates[var3] * (100 + ResourceManager.gameDatabase[3][1][5]) / 100;
         var3 = 3;
         var4 = var5 - this.followTarget.secondaryStates[var3];
      }

      return var4;
   }

   public final int a(byte var1) {
      int var2 = super.primaryStates[var1];
      byte var4;
      switch(var1) {
      case 2:
         if (this.f((byte)0)) {
            var4 = 1;
            short var10000 = super.secondaryStates[var4];
            short var10001 = ResourceManager.gameDatabase[3][0][5];
            var4 = 1;
            if (var10000 <= var10001 * super.primaryStates[var4] / 100) {
               var4 = 2;
               var2 = super.primaryStates[var4] * (100 + ResourceManager.gameDatabase[3][0][6]) / 100;
            }
         } else if (this.f((byte)1)) {
            var4 = 2;
            var2 = super.primaryStates[var4] * (100 + ResourceManager.gameDatabase[3][1][5]) / 100;
         }
         break;
      case 3:
         if (this.f((byte)2)) {
            var4 = 3;
            var2 = super.secondaryStates[var4] * (100 + ResourceManager.gameDatabase[3][2][5]) / 100;
         }
      case 4:
      }

      return var2;
   }

   public final void k(int var1) {
      int var2 = var1;
      if (var1 <= 0) {
         var2 = 1;
      }

      byte var3 = 1;
      this.u(super.secondaryStates[var3]);
      var3 = 1;
      short var4 = (short)(super.secondaryStates[var3] - var2);
      var3 = 1;
      super.secondaryStates[var3] = var4;
      var3 = 1;
      if (super.secondaryStates[var3] <= 0) {
         byte var5 = 0;
         var3 = 1;
         super.secondaryStates[var3] = var5;
      }

   }

   public final void l(int var1) {
      byte var3 = 1;
      short var4 = (short)(super.secondaryStates[var3] + var1);
      var3 = 1;
      super.secondaryStates[var3] = var4;
      var3 = 1;
      short var10000 = super.secondaryStates[var3];
      var3 = 1;
      if (var10000 >= super.primaryStates[var3]) {
         var3 = 1;
         var4 = super.primaryStates[var3];
         var3 = 1;
         super.secondaryStates[var3] = var4;
      }

   }

   private void B(int var1) {
      for(int var2 = 0; var2 < this.C41_f646.length; ++var2) {
         if (this.C41_f646[var2] != -1) {
            short[] var10000 = this.C41_f645;
            var10000[var2] = (short)(var10000[var2] + var1);
            if (this.C41_f645[var2] >= ResourceManager.gameDatabase[1][this.C41_f646[var2]][5]) {
               this.C41_f645[var2] = ResourceManager.gameDatabase[1][this.C41_f646[var2]][5];
            }
         }
      }

   }

   public final boolean m(int var1) {
      return this.C41_f641[var1][4] == 1;
   }

   public final int a(byte var1, int var2, int var3) {
      short var4 = 0;
      if (var1 == -1) {
         return 0;
      } else {
         short[] var10000;
         byte var7;
         short var8;
         label34:
         switch(var1) {
         case 0:
            var10000 = this.C41_f641[var1];
            var7 = 3;
            var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
            this.C41_f641[var1][2] = (short)(ResourceManager.gameDatabase[6][var1][4] * this.C() / 100);
            var7 = 3;
            var8 = (short)(super.primaryStates[var7] + this.C41_f641[var1][1]);
            var7 = 3;
            super.secondaryStates[var7] = var8;
            break;
         case 1:
            var10000 = this.C41_f641[var1];
            var7 = 3;
            var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
            this.C41_f641[var1][2] = ResourceManager.gameDatabase[6][var1][4];
            var7 = 3;
            var8 = (short)(super.primaryStates[var7] - this.C41_f641[var1][1]);
            var7 = 3;
            super.secondaryStates[var7] = var8;
            break;
         case 2:
            var10000 = this.C41_f641[var1];
            var7 = 3;
            var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
            this.C41_f641[var1][2] = ResourceManager.gameDatabase[6][var1][4];
            var7 = 3;
            var8 = (short)(super.primaryStates[var7] + this.C41_f641[var1][1]);
            var7 = 3;
            super.secondaryStates[var7] = var8;
            break;
         case 3:
            var10000 = this.C41_f641[var1];
            var7 = 1;
            var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
            var4 = this.C41_f641[var1][1];
            var7 = 1;
            this.u(super.secondaryStates[var7]);
            this.l(this.C41_f641[var1][1]);
            break;
         case 4:
            this.C41_f668[4] = (short)var3;
            var10000 = this.C41_f641[var1];
            var7 = 3;
            var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[1][var3][8] / 100);
            var7 = 3;
            var8 = (short)(super.primaryStates[var7] + this.C41_f641[var1][1]);
            var7 = 3;
            super.secondaryStates[var7] = var8;
            break;
         case 5:
            this.C41_f641[var1][1] = ResourceManager.gameDatabase[6][var1][3];
            break;
         case 6:
            this.C41_f641[var1][1] = ResourceManager.gameDatabase[6][var1][3];
            this.C41_f641[var1][2] = ResourceManager.gameDatabase[6][var1][4];
            break;
         case 7:
            this.C41_f668[7] = (short)var3;
            var10000 = this.C41_f641[var1];
            var7 = 4;
            var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[1][var3][8] / 100);
            var7 = 4;
            var8 = (short)(super.primaryStates[var7] + this.C41_f641[var1][1]);
            var7 = 4;
            super.secondaryStates[var7] = var8;
            break;
         case 8:
            this.C41_f641[var1][1] = ResourceManager.gameDatabase[6][var1][3];
            break;
         case 9:
            var10000 = this.C41_f641[var1];
            var7 = 4;
            var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
            var10000 = this.C41_f641[var1];
            var7 = 3;
            var10000[2] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][4] / 100);
            var7 = 4;
            var8 = (short)(super.primaryStates[var7] + this.C41_f641[var1][1]);
            var7 = 4;
            super.secondaryStates[var7] = var8;
            var7 = 3;
            var8 = (short)(super.primaryStates[var7] - this.C41_f641[var1][2]);
            var7 = 3;
            super.secondaryStates[var7] = var8;
            break;
         case 10:
            var10000 = this.C41_f641[var1];
            var7 = 2;
            var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
            var7 = 2;
            var8 = (short)(super.primaryStates[var7] + this.C41_f641[var1][1]);
            var7 = 2;
            super.secondaryStates[var7] = var8;
            break;
         case 11:
            this.C41_f641[var1][1] = (short)var2;
            PokemonEntity var9 = BattleSystemManager.B().C29_f402[var2];
            int var5 = 0;

            while(true) {
               var7 = 0;
               if (var5 >= var9.C41_f644[var7]) {
                  var9.E();
                  break label34;
               }

               this.a((byte)var9.C41_f643[0][var5], var9.C41_f641[var9.C41_f643[0][var5]][1], BattleSystemManager.B().C29_f402[var2].C41_f668[var5]);
               ++var5;
            }
         case 12:
            this.C41_f668[12] = 1;
            break;
         case 13:
            var10000 = this.C41_f641[var1];
            var7 = 1;
            var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
            var4 = this.C41_f641[var1][1];
            var7 = 1;
            this.u(super.secondaryStates[var7]);
            this.l(this.C41_f641[var1][1]);
            this.D();
            break;
         case 14:
            this.D();
            break;
         case 15:
            this.C41_f641[var1][1] = (short)(var2 * ResourceManager.gameDatabase[6][var1][3]);
         }

         this.a((int)0, (byte)var1);
         this.C41_f641[var1][0] = ResourceManager.gameDatabase[6][var1][2];
         this.C41_f641[var1][4] = 1;
         return var4;
      }
   }

   public final void n(int var1) {
      this.C41_f641[var1][4] = 0;

      for(byte var5 = 2; var5 <= 4; ++var5) {
         short var4 = super.primaryStates[var5];
         super.secondaryStates[var5] = var4;
      }

   }

   public final int o(int var1) {
      short var2 = 0;
      byte var5;
      short var7;
      switch(var1) {
      case 0:
      case 5:
      case 6:
      case 8:
      case 14:
      case 15:
      default:
         break;
      case 1:
         var5 = 3;
         var7 = (short)(super.primaryStates[var5] - this.C41_f641[var1][1]);
         var5 = 3;
         super.secondaryStates[var5] = var7;
         break;
      case 2:
         var5 = 3;
         var7 = (short)(super.primaryStates[var5] + this.C41_f641[var1][1]);
         var5 = 3;
         super.secondaryStates[var5] = var7;
         break;
      case 3:
         var2 = this.C41_f641[var1][1];
         var5 = 1;
         this.u(super.secondaryStates[var5]);
         this.l(this.C41_f641[var1][1]);
         break;
      case 4:
         var5 = 3;
         var7 = (short)(super.secondaryStates[var5] + this.C41_f641[var1][1]);
         var5 = 3;
         super.secondaryStates[var5] = var7;
         break;
      case 7:
         var5 = 4;
         var7 = (short)(super.primaryStates[var5] + this.C41_f641[var1][1]);
         var5 = 4;
         super.secondaryStates[var5] = var7;
         break;
      case 9:
         var5 = 4;
         var7 = (short)(super.primaryStates[var5] + this.C41_f641[var1][1]);
         var5 = 4;
         super.secondaryStates[var5] = var7;
         var5 = 3;
         var7 = (short)(super.primaryStates[var5] - this.C41_f641[var1][2]);
         var5 = 3;
         super.secondaryStates[var5] = var7;
         break;
      case 10:
         var5 = 2;
         var7 = (short)(super.primaryStates[var5] + this.C41_f641[var1][1]);
         var5 = 2;
         super.secondaryStates[var5] = var7;
         break;
      case 11:
         short[] var10001 = this.C41_f641[11];
         PokemonEntity var6 = BattleSystemManager.B().C29_f402[var10001[1]];
         int var3 = 0;

         while(true) {
            var5 = 0;
            if (var3 >= var6.C41_f644[var5]) {
               var6.E();
               return var2;
            }

            byte var8 = var6.C41_f643[0][var3];
            short var10002 = var6.C41_f641[var6.C41_f643[0][var3]][1];
            short[] var10004 = this.C41_f641[11];
            this.a((byte)var8, var10002, BattleSystemManager.B().C29_f402[var10004[1]].C41_f668[var3]);
            ++var3;
         }
      case 12:
         this.C41_f668[12] = 2;
         break;
      case 13:
         var2 = this.C41_f641[var1][1];
         var5 = 1;
         this.u(super.secondaryStates[var5]);
         this.l(this.C41_f641[var1][1]);
      }

      return var2;
   }

   public final boolean p(int var1) {
      return this.C41_f642[var1][4] == 1;
   }

   public final void D() {
      int var1;
      for(var1 = 0; var1 < 11; ++var1) {
         if (this.C41_f642[var1][4] == 1) {
            this.C(var1);
         }
      }

      for(var1 = 0; var1 < 3; ++var1) {
         this.e(1, var1);
      }

   }

   public final void E() {
      int var1;
      for(var1 = 0; var1 < 16; ++var1) {
         if (this.C41_f641[var1][4] == 1) {
            this.n(var1);
         }
      }

      for(var1 = 0; var1 < 3; ++var1) {
         this.e(0, var1);
      }

   }

   private void C(int var1) {
      this.C41_f642[var1][4] = 0;

      for(byte var5 = 2; var5 <= 4; ++var5) {
         short var4 = super.primaryStates[var5];
         super.secondaryStates[var5] = var4;
      }

   }

   private void e(int var1, int var2) {
      this.C41_f643[var1][var2] = -1;
      if (this.C41_f644[var1] > 0) {
         --this.C41_f644[var1];
      }

   }

   public final void q(int var1) {
      short var2;
      byte var3;
      short var4;
      switch(var1) {
      case 0:
         var2 = this.C41_f642[0][1];
         var4 = ResourceManager.gameDatabase[1][this.C41_f642[0][3]][8];
         this.k(var2 / var4);
         if (!this.T()) {
            this.a((byte)3, true);
         }

         return;
      case 1:
         return;
      case 2:
         return;
      case 3:
         if (this.C41_f642[var1][0] <= 1) {
            var2 = this.C41_f642[var1][1];
            var4 = ResourceManager.gameDatabase[1][this.C41_f642[var1][3]][8];
            this.k(var2 * var4 / 100);
            if (!this.T()) {
               this.a((byte)3, true);
            }

            return;
         }
         break;
      case 4:
         return;
      case 5:
         var3 = 4;
         var4 = (short)(super.primaryStates[var3] - this.C41_f642[var1][1]);
         var3 = 4;
         super.secondaryStates[var3] = var4;
         return;
      case 6:
         return;
      case 7:
         var3 = 3;
         var4 = (short)(super.primaryStates[var3] - this.C41_f642[var1][1]);
         var3 = 3;
         super.secondaryStates[var3] = var4;
      case 8:
      case 9:
      case 10:
      }

   }

   public final void c(int var1, int var2) {
      if (this.p(var1)) {
         if (this.C41_f642[var1][0] > 0) {
            --this.C41_f642[var1][0];
         }

         if (this.C41_f642[var1][0] <= 0) {
            this.C(var1);
            this.e(1, var2);
         }
      }

   }

   public final void d(int var1, int var2) {
      if (this.m(var1)) {
         if (this.C41_f641[var1][0] > 0) {
            --this.C41_f641[var1][0];
         }

         if (this.C41_f641[var1][0] <= 0) {
            this.n(var1);
            this.e(0, var2);
         }
      }

   }

   private void a(int var1, byte var2) {
      int var3;
      for(var3 = 0; var3 < 3; ++var3) {
         if (this.C41_f643[var1][var3] == -1) {
            int var4;
            for(var4 = 0; var4 < 3; ++var4) {
               if (this.C41_f643[var1][var4] == var2) {
                  return;
               }
            }

            if (var4 >= 3) {
               this.C41_f643[var1][var3] = var2;
               if (this.C41_f644[var1] < 3) {
                  ++this.C41_f644[var1];
               }
               break;
            }
         }
      }

      if (var3 >= 3) {
         this.C41_f643[var1][0] = var2;
      }

   }

   public final byte r(int var1) {
      return this.C41_f644[var1];
   }

   public final boolean f(byte var1) {
      byte var3 = 5;
      return super.primaryStates[var3] == var1;
   }

   public final int F() {
      return this.C41_f647;
   }

   public final int[] G() {
      int[] var1 = null;
      Vector var2 = new Vector();
      short var4 = ResourceManager.gameDatabase[0][this.C41_f657][18];
      short var5 = ResourceManager.gameDatabase[0][this.C41_f657][1];
      int var6 = this.X();

      int var7;
      for(var7 = var5 * 10; var7 < var5 * 10 + 10; ++var7) {
         short var10000 = ResourceManager.gameDatabase[1][var7][4];
         boolean var3 = false;
         if (var10000 <= ResourceManager.gameDatabase[8][var4][var6]) {
            int var8;
            for(var8 = 0; var8 < this.C41_f646.length && var7 != this.C41_f646[var8]; ++var8) {
            }

            if (var8 >= this.C41_f646.length) {
               var2.addElement(String.valueOf(var7));
            }
         }
      }

      if (var2.size() > 0) {
         var1 = new int[var2.size()];

         for(var7 = 0; var7 < var1.length; ++var7) {
            var1[var7] = Integer.parseInt((String)var2.elementAt(var7));
         }
      }

      return var1;
   }

   public final void g(byte var1) {
      for(int var2 = 0; var2 < this.C41_f646.length; ++var2) {
         if (this.C41_f646[var2] == -1) {
            this.C41_f646[var2] = var1;
            ++this.C41_f647;
            this.C41_f645[var2] = ResourceManager.gameDatabase[1][var1][5];
            return;
         }
      }

   }

   public final void H() {
      short var1 = ResourceManager.gameDatabase[0][this.C41_f657][1];
      int var3;
      int var6;
      if (this.C41_f654 <= 5) {
         var6 = var1 * 10;
         boolean var7 = true;

         for(var3 = 0; var3 < this.C41_f646.length; ++var3) {
            if (var6 == this.C41_f646[var3]) {
               var7 = false;
               break;
            }
         }

         if (var7) {
            this.g((byte)var6);
         }

      } else if (this.C41_f647 < this.X() + 1) {
         int[] var2;
         var3 = (var2 = this.G()).length;

         for(int var4 = 0; var4 < var2.length; ++var4) {
            var6 = GameUtils.getRandomInt(var3);
            this.g((byte)var2[var6]);
            if (this.C41_f647 >= this.C41_f654 / 10 + 1) {
               break;
            }

            while(var6 < var3 - 1) {
               var2[var6] = var2[var6 + 1];
               ++var6;
            }

            --var3;
         }

      }
   }

   public final boolean s(int var1) {
      if (var1 == -1) {
         return false;
      } else {
         return this.C41_f645[var1] > 0;
      }
   }

   public final void a(byte var1, PokemonEntity var2) {
      super.followTarget = var2;
      this.C41_f659 = var1;

      for(int var4 = 0; var4 < this.C41_f646.length; ++var4) {
         if (this.C41_f646[var4] == var1) {
            --this.C41_f645[var4];
            if (this.m(12) && this.C41_f668[12] == 1) {
               ++this.C41_f645[var4];
            }

            if (this.m(8)) {
               --this.C41_f645[var4];
            }
         }
      }

   }

   public final void b(int[] var1) {
      this.C41_f647 = (byte)var1[0];

      for(int var2 = 0; var2 < var1[0]; ++var2) {
         this.C41_f646[var2] = (byte)var1[var2 + 1];
         this.C41_f645[var2] = (short)var1[var1[0] + 1 + var2];
      }

   }

   public final byte t(int var1) {
      return var1 <= this.C41_f646.length - 1 && var1 >= 0 ? this.C41_f646[var1] : -1;
   }

   public static short a(byte var0, byte var1) {
      return ResourceManager.gameDatabase[1][var0][var1];
   }

   private int X() {
      int[] var1 = new int[]{5, 10, 20, 30, 40};
      int var2 = 0;

      for(int var3 = 0; var3 < var1.length; ++var3) {
         if (this.C41_f654 >= var1[var3]) {
            var2 = var3;
         }
      }

      return var2;
   }

   public final void h(byte var1) {
      this.C41_f659 = var1;
   }

   public final byte I() {
      return this.C41_f659;
   }

   public final void saveCurrentStates() {
      this.V();
      super.saveCurrentStates();
      byte var2 = 1;
      this.u(super.primaryStates[var2]);
   }

   public final void J() {
      for(int var1 = 0; var1 < this.C41_f646.length; ++var1) {
         if (this.C41_f646[var1] != -1) {
            this.C41_f645[var1] = ResourceManager.gameDatabase[1][this.C41_f646[var1]][5];
         }
      }

      this.saveCurrentStates();
      this.activate();
   }

   public final void K() {
      if (GameWorldManager.C25_f336 == null) {
         GameWorldManager.C25_f336 = new Vector();
      }

      short var1;
      if ((var1 = ResourceManager.getDatabaseValue((byte)0, (short)this.C41_f657, (byte)19)) != -1) {
         short var2 = ResourceManager.getDatabaseValue((byte)0, (short)this.C41_f657, (byte)21);
         int var3 = ResourceManager.getDatabaseValue((byte)0, (short)this.C41_f657, (byte)20) + 12;
         boolean var4 = false;
         if (!GameWorldManager.C25_f339 && this.S() > 0 && this.C41_f654 >= C41_f639[ResourceManager.getDatabaseValue((byte)0, var1, (byte)2) - 1] && PlayerCharacter.p().a((int)var3, (byte)2) >= var2) {
            var4 = true;
         } else if (this.S() > 0 && this.C41_f654 >= C41_f639[ResourceManager.getDatabaseValue((byte)0, var1, (byte)2) - 1]) {
            var4 = true;
         }

         if (var4) {
            int[] var6 = new int[]{this.C41_f657, ResourceManager.gameDatabase[0][this.C41_f657][0]};
            GameWorldManager.C25_f336.addElement(var6);
            GameWorldManager.C25_f340[0] = (byte)this.C41_f654;
            GameWorldManager.C25_f340[1] = (byte)this.C41_f657;
            GameWorldManager.C25_f337 = 0;
         }

      }
   }

   public final void i(byte var1) {
      this.C41_f658 = var1;
      byte var2;
      short var3;
      switch(var1) {
      case 7:
         var2 = 2;
         var3 = (short)(super.primaryStates[var2] * 90 / 100);
         var2 = 2;
         super.primaryStates[var2] = var3;
         var2 = 4;
         var3 = (short)(super.primaryStates[var2] + 7);
         var2 = 4;
         super.primaryStates[var2] = var3;
         var2 = 1;
         var3 = (short)(super.primaryStates[var2] * 80 / 100);
         var2 = 1;
         super.primaryStates[var2] = var3;
         return;
      case 8:
         var2 = 2;
         var3 = (short)(super.primaryStates[var2] * 130 / 100);
         var2 = 2;
         super.primaryStates[var2] = var3;
         var2 = 4;
         var3 = (short)(super.primaryStates[var2] + -2);
         var2 = 4;
         super.primaryStates[var2] = var3;
         var2 = 1;
         var3 = (short)(super.primaryStates[var2] * 80 / 100);
         var2 = 1;
         super.primaryStates[var2] = var3;
         return;
      case 9:
         var2 = 2;
         var3 = (short)(super.primaryStates[var2] * 90 / 100);
         var2 = 2;
         super.primaryStates[var2] = var3;
         var2 = 4;
         var3 = (short)(super.primaryStates[var2] + -2);
         var2 = 4;
         super.primaryStates[var2] = var3;
         var2 = 1;
         var3 = (short)(super.primaryStates[var2] * 130 / 100);
         var2 = 1;
         super.primaryStates[var2] = var3;
      default:
      }
   }

   public final boolean L() {
      return this.C41_f661;
   }

   public final void e(boolean var1) {
      this.C41_f661 = var1;
   }

   public final int j(byte var1) {
      return ResourceManager.gameDatabase[0][this.C41_f657][var1];
   }

   public static short a(int var0, int var1, int var2, int var3) {
      switch(var3) {
      case 1:
         return (short)((ResourceManager.gameDatabase[0][var0][5] + ResourceManager.gameDatabase[0][var0][6] * var1 + ResourceManager.gameDatabase[0][var0][7]) * C41_f638[var2 - 1] / 100);
      case 2:
         return (short)((ResourceManager.gameDatabase[0][var0][8] + ResourceManager.gameDatabase[0][var0][9] * var1 + ResourceManager.gameDatabase[0][var0][10]) * C41_f638[var2 - 1] / 100);
      case 3:
         return (short)((ResourceManager.gameDatabase[0][var0][11] + ResourceManager.gameDatabase[0][var0][12] * var1 / 10 + ResourceManager.gameDatabase[0][var0][13]) * C41_f638[var2 - 1] / 100);
      case 4:
         return (short)((ResourceManager.gameDatabase[0][var0][14] + ResourceManager.gameDatabase[0][var0][15] * var1 / 10 + ResourceManager.gameDatabase[0][var0][16]) * C41_f638[var2 - 1] / 100);
      default:
         return 0;
      }
   }

   public final int M() {
      byte var2 = 1;
      int var10000 = super.secondaryStates[var2] * 100;
      var2 = 1;
      return var10000 / super.primaryStates[var2];
   }

   public final int N() {
      int var10000 = this.C41_f650 * 100;
      byte var2 = 1;
      return var10000 / super.primaryStates[var2];
   }

   public final int O() {
      return this.C41_f650;
   }

   public final void u(int var1) {
      byte var3 = 1;
      if (var1 >= super.primaryStates[var3]) {
         var3 = 1;
         this.C41_f650 = super.primaryStates[var3];
      } else {
         this.C41_f650 = var1;
      }
   }

   public final int P() {
      return this.C41_f652 * 100 / this.v();
   }

   public final int v(int var1) {
      return var1 * 100 / this.v();
   }

   public static int a(short var0, short var1) {
      int var2;
      if (var1 >= 50) {
         var2 = 37300;
      } else {
         var2 = var1 * 15 * var1 - 200;
      }

      return var0 * 100 / var2;
   }

   public final int[] Q() {
      int[] var1;
      (var1 = new int[9 + (this.C41_f647 << 1) + 1])[0] = this.C41_f657;
      var1[1] = this.C41_f654;
      byte var2 = 5;
      var1[2] = super.primaryStates[var2];
      var2 = 6;
      var1[3] = super.secondaryStates[var2];
      var2 = 0;
      var1[4] = super.primaryStates[var2];
      var1[5] = this.C41_f658;
      var2 = 1;
      var1[6] = super.secondaryStates[var2];
      var1[7] = this.C41_f652;
      var1[8] = this.C41_f662;
      var1[9] = this.C41_f647;

      for(int var4 = 0; var4 < this.C41_f647; ++var4) {
         var1[var4 + 10] = this.C41_f646[var4];
         var1[10 + var1[9] + var4] = this.C41_f645[var4];
      }

      return var1;
   }

   public final int[] R() {
      int[] var1;
      (var1 = new int[(this.C41_f647 << 1) + 1])[0] = this.C41_f647;

      for(int var2 = 0; var2 < this.C41_f647; ++var2) {
         var1[var2 + 1] = this.C41_f646[var2];
         var1[var1[0] + var2 + 1] = this.C41_f645[var2];
      }

      return var1;
   }

   public final int S() {
      if (this.j((byte)19) == -1) {
         return 0;
      } else if (ResourceManager.gameDatabase[0][this.j((byte)19)][2] == 1) {
         return 1;
      } else if (ResourceManager.gameDatabase[0][this.j((byte)19)][2] == 2) {
         return 1;
      } else {
         return ResourceManager.gameDatabase[0][this.j((byte)19)][2] == 3 ? 2 : 0;
      }
   }

   public final void w(int var1) {
      short var3;
      byte var5;
      short var6;
      switch(ResourceManager.gameDatabase[4][var1][5]) {
      case 1:
         var5 = 1;
         var6 = (short)(super.primaryStates[var5] * ResourceManager.gameDatabase[4][var1][6] / 100 + ResourceManager.gameDatabase[4][var1][7]);
         var5 = 1;
         this.u(super.secondaryStates[var5] + var6);
         this.l(var6);
         break;
      case 2:
         var3 = ResourceManager.gameDatabase[4][var1][6];
         this.B(var3);
         break;
      case 3:
         var5 = 1;
         var6 = (short)(super.primaryStates[var5] * ResourceManager.gameDatabase[4][var1][6] / 100 + ResourceManager.gameDatabase[4][var1][7]);
         var3 = ResourceManager.gameDatabase[4][var1][8];
         var5 = 1;
         this.u(super.secondaryStates[var5] + var6);
         this.l(var6);
         this.B(var3);
         break;
      case 4:
         this.activate();
         var5 = 1;
         var6 = (short)(super.primaryStates[var5] * ResourceManager.gameDatabase[4][var1][6] / 100 + ResourceManager.gameDatabase[4][var1][7]);
         var3 = ResourceManager.gameDatabase[4][var1][8];
         this.u(var6);
         this.l(var6);
         this.B(var3);
         break;
      case 5:
         this.D();
         break;
      case 6:
         byte var2 = 2;
         var5 = 6;
         super.secondaryStates[var5] = var2;
      }

      PlayerCharacter.p().d(var1, 1, (byte)0);
   }

   public final int x(int var1) {
      if (!this.T() && ResourceManager.gameDatabase[4][var1][5] != 4) {
         return 8;
      } else {
         short var10000;
         byte var3;
         byte var4;
         switch(ResourceManager.gameDatabase[4][var1][5]) {
         case 0:
            return 6;
         case 1:
            var3 = 1;
            var10000 = super.primaryStates[var3];
            var3 = 1;
            if (var10000 == super.secondaryStates[var3]) {
               return 2;
            }
            break;
         case 2:
            var4 = this.C41_f647;

            for(int var5 = 0; var5 < var4; ++var5) {
               if (this.C41_f645[var5] < a((byte)this.C41_f646[var5], (byte)5)) {
                  return -1;
               }
            }

            return 3;
         case 3:
            var4 = -1;
            var3 = 1;
            var10000 = super.primaryStates[var3];
            var3 = 1;
            if (var10000 == super.secondaryStates[var3] || !this.T()) {
               var4 = 2;
            }

            byte var2 = this.C41_f647;

            for(int var6 = 0; var6 < var2; ++var6) {
               if (this.C41_f645[var6] < a((byte)this.C41_f646[var6], (byte)5)) {
                  return -1;
               }
            }

            if (var4 == 2) {
               return 7;
            }
            break;
         case 4:
            if (this.T()) {
               return 1;
            }
            break;
         case 5:
            for(var1 = 0; var1 < this.C41_f642.length; ++var1) {
               if (this.p(var1)) {
                  return -1;
               }
            }

            return 4;
         case 6:
            var3 = 6;
            if (super.secondaryStates[var3] >= 2) {
               return 5;
            }
         }

         return -1;
      }
   }

   public final boolean T() {
      byte var2 = 1;
      return super.secondaryStates[var2] > 0;
   }

   public final byte a(PokemonEntity var1) {
      short var2;
      short var3;
      boolean var5;
      boolean var6;
      label130: {
         var2 = ResourceManager.gameDatabase[0][this.C41_f657][1];
         var3 = ResourceManager.gameDatabase[0][var1.C41_f657][1];
         short var4 = ResourceManager.gameDatabase[0][this.C41_f657][22];
         short var7 = ResourceManager.gameDatabase[0][var1.C41_f657][22];
         var5 = false;
         var6 = false;
         if (var4 != 2 || var7 != 2) {
            if (var4 == 2 && var7 != 2) {
               var5 = true;
               break label130;
            }

            if (var4 != 2 && var7 == 2) {
               var6 = true;
               break label130;
            }
         }

         var5 = true;
         var6 = true;
      }

      if (var5 && (var2 == 0 && var3 == 1 || var2 == 1 && var3 == 2 || var2 == 2 && var3 == 3 || var2 == 3 && var3 == 0 || var2 == 5 && var3 == 6 || var2 == 6 && var3 == 4 || var2 == 4 && var3 == 5)) {
         return 0;
      } else {
         return (byte)(!var6 || (var3 != 0 || var2 != 1) && (var3 != 1 || var2 != 2) && (var3 != 2 || var2 != 3) && (var3 != 3 || var2 != 0) && (var3 != 5 || var2 != 6) && (var3 != 6 || var2 != 4) && (var3 != 4 || var2 != 5) ? -1 : 1);
      }
   }

   public final int[] b(PokemonEntity var1) {
      byte var2 = 0;
      byte var3 = 5;
      int var4 = this.C();
      short var5 = ResourceManager.gameDatabase[0][this.C41_f657][1];
      if (this.C41_f655 == PlayerCharacter.p().C53_f802[var5] + PlayerCharacter.p().C53_f803[var5] - 1) {
         var3 = 30;
      }

      byte var9 = 4;
      int var10 = var3 + super.secondaryStates[var9] / 2;
      if (this.f((byte)4)) {
         var10 += ResourceManager.gameDatabase[3][4][5];
      }

      if (GameUtils.getRandomInt(100) <= var10) {
         var4 = var4 * 3 / 2;
         var2 = 1;
      }

      byte var12 = (byte) ResourceManager.gameDatabase[1][this.C41_f659][7];
      short var11 = -1;
      int var6 = var4;
      switch(this.C41_f659) {
      case 0:
      case 6:
      case 10:
      case 11:
      case 12:
      case 13:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 26:
      case 30:
      case 31:
      case 32:
      case 33:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 46:
      case 50:
      case 51:
      case 52:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 60:
      case 61:
      case 63:
      case 66:
      case 68:
      case 69:
         var4 = var4 * ResourceManager.gameDatabase[1][this.C41_f659][3] / 100;
         break;
      case 1:
      case 7:
         var4 = var4 * ResourceManager.gameDatabase[1][this.C41_f659][3] / 100 + var4 / ResourceManager.gameDatabase[1][this.C41_f659][8];
         break;
      case 2:
      case 8:
      case 22:
      case 28:
      case 41:
      case 47:
         var4 = var4 * ResourceManager.gameDatabase[1][this.C41_f659][3] / 100;
         var11 = ResourceManager.gameDatabase[1][this.C41_f659][8];
         break;
      case 3:
      case 9:
         if (var1.p(0)) {
            var4 = var4 * ResourceManager.gameDatabase[1][this.C41_f659][8] / 100;
         } else {
            var4 = var4 * ResourceManager.gameDatabase[1][this.C41_f659][3] / 100;
         }
         break;
      case 4:
      case 5:
      case 14:
      case 15:
      case 21:
      case 24:
      case 25:
      case 27:
      case 34:
      case 35:
      case 42:
      case 44:
      case 45:
      case 48:
      case 62:
      case 64:
      case 65:
      case 67:
      default:
         var12 = -1;
         break;
      case 23:
      case 29:
         if (var1.p(1)) {
            var4 = var4 * ResourceManager.gameDatabase[1][this.C41_f659][8] / 100;
         } else {
            var4 = var4 * ResourceManager.gameDatabase[1][this.C41_f659][3] / 100;
         }
         break;
      case 43:
      case 49:
         var4 = var4 * ResourceManager.gameDatabase[1][this.C41_f659][3] / 100;
         var1.E();
         break;
      case 53:
      case 59:
         var9 = 1;
         int var10000 = super.secondaryStates[var9] * 100;
         var9 = 1;
         int var7 = var10000 / super.primaryStates[var9];
         var4 = var4 * (ResourceManager.gameDatabase[1][this.C41_f659][8] - var7) / 100;
      }

      if (var4 <= 0) {
         var6 = 1;
      }

      short var10002 = (short)var6;
      short var15 = (short)this.C41_f659;
      short var14 = var10002;
      byte var17;
      if (var12 == -1) {
         var17 = -1;
      } else {
         label154: {
            if (var1.f((byte)3)) {
               if (GameUtils.getRandomInt(100) > var11 * (100 - ResourceManager.gameDatabase[3][3][5]) / 100) {
                  var17 = -1;
                  break label154;
               }
            } else {
               if (var1.m(14)) {
                  var17 = -1;
                  break label154;
               }

               if (var11 != -1 && GameUtils.getRandomInt(100) > var11) {
                  var17 = -1;
                  break label154;
               }
            }

            short[] var18;
            switch(var12) {
            case 0:
               var1.C41_f642[var12][1] = var14;
            case 1:
            case 2:
            case 8:
            case 9:
            case 10:
            default:
               break;
            case 3:
               var1.C41_f642[var12][1] = var14;
               break;
            case 4:
               var1.C41_f642[var12][1] = ResourceManager.gameDatabase[1][var15][8];
               break;
            case 5:
               var18 = var1.C41_f642[var12];
               var9 = 4;
               var18[1] = (short)(var1.primaryStates[var9] * ResourceManager.gameDatabase[1][var15][8] / 100);
               var9 = 4;
               var14 = (short)(var1.primaryStates[var9] - var1.C41_f642[var12][1]);
               var9 = 4;
               var1.secondaryStates[var9] = var14;
               break;
            case 6:
               var1.C41_f642[var12][1] = ResourceManager.gameDatabase[1][var15][8];
               break;
            case 7:
               var18 = var1.C41_f642[var12];
               var9 = 3;
               var18[1] = (short)(var1.primaryStates[var9] * ResourceManager.gameDatabase[1][var15][8] / 100);
               var9 = 3;
               var14 = (short)(var1.primaryStates[var9] - var1.C41_f642[var12][1]);
               var9 = 3;
               var1.secondaryStates[var9] = var14;
            }

            var1.a((int)1, (byte)var12);
            if (var1.C41_f660 == 0 && PlayerCharacter.p().b((byte)6, (byte)0) == 2 && PlayerCharacter.p().b((byte)6, (byte)1) == 1) {
               var1.C41_f642[var12][0] = (short)(ResourceManager.gameDatabase[7][var12][2] / 2);
            } else {
               var1.C41_f642[var12][0] = ResourceManager.gameDatabase[7][var12][2];
            }

            var1.C41_f642[var12][3] = var15;
            var1.C41_f642[var12][4] = 1;
            var17 = var12;
         }
      }

      byte var16 = var17;
      if (this.m(0) && this.C41_f641[0][0] == 0) {
         var4 += this.C41_f641[0][2];
      }

      if (this.m(1)) {
         var4 += var4 * this.C41_f641[1][2] / 100;
      }

      if (this.p(6)) {
         var4 -= var4 * this.C41_f642[6][1] / 100;
      }

      if (var1.m(6) && GameUtils.getRandomInt(100) <= this.C41_f641[6][1]) {
         var4 = var4 * this.C41_f641[6][2] / 100;
      }

      if (this.m(8)) {
         var4 += var4 * this.C41_f641[8][1] / 100;
      }

      if (this.C41_f660 == 0 && PlayerCharacter.p().b((byte)3, (byte)0) == 2 && PlayerCharacter.p().b((byte)3, (byte)1) == 1 && GameWorldManager.C25_f369 == 2) {
         var4 += var4 * ResourceManager.gameDatabase[2][3][5] / 100;
      }

      if (this.C41_f660 == 0 && PlayerCharacter.p().b((byte)6, (byte)0) == 2) {
         var4 += var4 * ResourceManager.gameDatabase[2][6][5] / 100;
      }

      if (this.a(var1) == 0) {
         var4 *= 3;
      } else if (this.a(var1) == 1) {
         var4 = var4 * 60 / 100;
      }

      if (var4 <= 0) {
         var4 = 1;
      } else {
         var10 = GameUtils.getRandomInt(100);
         int var13 = (var4 << 1) / 100;
         if (var10 > 50) {
            if (var13 <= 0) {
               ++var4;
            }
         } else if (var13 <= 0) {
            --var4;
         }

         if (var4 <= 0) {
            var4 = 1;
         }
      }

      if (var1.m(5) && GameUtils.getRandomInt(100) <= var1.C41_f641[5][1]) {
         this.C41_f668[5] = (short)var4;
         return new int[]{var4, var2, var16};
      } else {
         return new int[]{var4, var2, var16};
      }
   }

   public final String U() {
      String[] var1 = new String[]{"Mộc hệ", "Thổ hệ", "Thủy hệ", "Hỏa hệ", "Quỷ hệ", "Phong hệ", "Điện hệ"};
      short var2 = ResourceManager.gameDatabase[0][this.C41_f657][1];
      return var1[var2];
   }

   public static String y(int var0) {
      String[] var1 = new String[]{"Mộc hệ", "Thổ hệ", "Thủy hệ", "Hỏa hệ", "Quỷ hệ", "Phong hệ", "Điện hệ"};
      short var2 = ResourceManager.gameDatabase[0][var0][1];
      return var1[var2];
   }
}
