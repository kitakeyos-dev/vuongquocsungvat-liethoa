package a.a;

import a.GameUtils;
import a.GameEngineBase;
import a.b.GameEntity;
import a.b.AnimatedSprite;
import game.C25;
import javax.microedition.lcdui.Graphics;

public class C20 extends GameEntity {
   public AnimatedSprite C20_f261 = new AnimatedSprite();
   public C20 C20_f262 = null;
   protected boolean C20_f263;

   public boolean a(int var1, boolean var2) {
      return this.C20_f261.loadSpriteSet(var1, var2);
   }

   public final void a(int var1, int var2, boolean var3) {
      this.C20_f261.replaceImage(var1, var2, true);
   }

   public final boolean a(byte var1, byte var2, boolean var3) {
      return this.C20_f261.setAnimation(var1, var2, var3);
   }

   public final void a(byte var1, byte var2, byte var3) {
      this.C20_f261.setAnimationProperties(var1, var2);
      super.currentDirection = var3;
   }

   public final boolean a() {
      return !this.isVisible ? false : this.C20_f261.updateAnimation();
   }

   public final boolean b() {
      return this.C20_f261.isAtLastFrame();
   }

   public final void a(Graphics var1, int var2, int var3) {
      if (this.isVisible) {
         if (this.currentDirection == 3) {
            this.C20_f261.renderCurrentFrame(var1, this.worldX - var2, this.worldY - var3, (byte)1);
         } else {
            this.C20_f261.renderCurrentFrame(var1, this.worldX - var2, this.worldY - var3, (byte)0);
         }
      }
   }

   public final void b(Graphics var1, int var2, int var3) {
      if (this.C20_f263) {
         if (this.currentDirection == 3) {
            this.C20_f261.renderCurrentFrame(var1, this.worldX - var2, this.worldY - var3, (byte)3);
         } else {
            this.C20_f261.renderCurrentFrame(var1, this.worldX - var2, this.worldY - var3, (byte)4);
         }
      }
   }

   public void c() {
      this.setActive(true);
      this.setVisible(true);
      this.setInteractable(true);
   }

   public void d() {
      this.setActive(false);
      this.setVisible(false);
      this.setInteractable(false);
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
      switch(this.currentDirection) {
      case 0:
         this.moveY(var1);
         break;
      case 1:
         this.moveX(var1);
         break;
      case 2:
         this.moveY(-var1);
         break;
      case 3:
         this.moveX(-var1);
      }

      if (this.C20_f262 != null) {
         this.C20_f262.setWorldPosition(this.worldX, this.worldY);
      }

   }

   public final void moveRelative(int var1, int var2) {
      switch(var1) {
      case 0:
         this.moveY(4);
         break;
      case 1:
         this.moveX(4);
         break;
      case 2:
         this.moveY(-4);
         break;
      case 3:
         this.moveX(-4);
      }

      if (this.C20_f262 != null) {
         this.C20_f262.setWorldPosition(this.worldX, this.worldY);
      }

   }

   public final void f() {
      if (this.C20_f261.isInvalid()) {
         this.setInteractable(true);
      } else if (GameUtils.checkCollisionWithShortArray(C25.B().C25_f283.cameraX, C25.B().C25_f283.cameraY, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight(), this.worldX, this.worldY, this.C20_f261.getCurrentFrameTriggers())) {
         this.setInteractable(true);
      } else {
         this.setInteractable(false);
      }
   }

   public final void g() {
      this.C20_f261.applyColorEffects();
   }

   public final void b(int var1) {
      this.C20_f261.applyBrightnessEffect(var1);
   }
}
