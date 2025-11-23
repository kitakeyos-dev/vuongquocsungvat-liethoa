package game;

import engine.GameUtils;
import engine.entity.GameObject;
import engine.entity.TileMapRenderer;
import javax.microedition.lcdui.Graphics;

public final class NPCEntity extends GameObject {
   public byte C18_f223;
   public boolean C18_f224;
   public byte C18_f225;
   public byte C18_f226;
   public short C18_f227;
   public short C18_f228;
   private int C18_f229;
   private int C18_f230;
   private int C18_f231;
   private int C18_f232;
   public byte C18_f233;
   public int C18_f234;
   public int C18_f235;
   public byte C18_f236;
   private short C18_f237;
   private short C18_f238;
   private short C18_f239;
   public short C18_f240;
   public short C18_f241;
   public short C18_f242;
   private boolean C18_f243;
   private byte[] C18_f244 = new byte[]{2, 3, 0, 1};
   private byte C18_f245 = 0;
   public GameObject C18_f246;
   public GameObject C18_f247;
   public short C18_f248 = -1;
   private short[] C18_f249 = new short[]{8, 9, 2, 96, 320, 0};

   public final void a(short[] var1, int var2) {
      this.C18_f248 = (short)var2;
      this.C18_f223 = (byte)var1[0];
      this.sprite.loadSpriteSet(var1[1], false);
      this.sprite.applyColorEffects();
      this.C18_f225 = (byte)var1[6];
      if (this.C18_f223 == 0 && (this.C18_f225 == 1 || this.C18_f225 == 18)) {
         byte var4 = (byte)(var1[2] % 3);
         super.currentDirection = var4;
      }

      this.a((byte)var1[2]);
      this.worldX = var1[3];
      this.worldY = var1[4];
      if (var1[5] == 1) {
         this.setVisible(true);
      } else {
         this.setVisible(false);
      }

      switch(this.C18_f223) {
      case 0:
         this.layer = (byte)var1[7];
         this.C18_f226 = (byte)var1[8];
         if (this.C18_f226 != 0 && this.C18_f246 == null && this.isVisible()) {
            this.C18_f246 = new GameObject();
            this.C18_f246.loadSpriteSet(259, false);
            this.C18_f246.sprite.applyColorEffects();
            this.C18_f246.setAnimation(this.C18_f226, (byte)-1, true);
            this.C18_f246.setWorldPosition(this.worldX, this.worldY - 40);
            this.C18_f246.followTarget = this;
         }

         this.B();
         this.w();
         if (var1[9] == 0) {
            this.C18_f224 = false;
         } else {
            this.C18_f224 = true;
         }

         this.C18_f227 = var1[11];
         this.C18_f228 = var1[12];
         this.C18_f229 = 0;
         this.C18_f231 = GameUtils.getRandomInRange(20, 40);
         this.C18_f233 = 0;
         if (this.C18_f225 == 12) {
            this.currentDirection = 0;
         } else if (this.C18_f225 == 13) {
            this.currentDirection = 1;
         }

         if (this.C18_f225 == 3) {
            if (this.facingDirection == 4) {
               this.currentDirection = 1;
            }
         } else if (this.C18_f225 == 2) {
            if (this.facingDirection == 5) {
               this.currentDirection = 2;
            } else if (this.facingDirection == 3) {
               this.currentDirection = 0;
            }
         }

         if (this.C18_f225 == 1 && this.sprite.spriteSetId != 226 || this.C18_f225 == 2 || this.C18_f225 == 3 || this.C18_f225 == 17) {
            if (this.attachedObject == null) {
               this.attachedObject = new GameObject();
               this.attachedObject.loadSpriteSet(337, false);
            }

            this.attachedObject.setWorldPosition(this.worldX, this.worldY);
            if (this.sprite.spriteSetId == 4) {
               this.attachedObject.setAnimation((byte)0, (byte)0, this.C18_f243);
            } else {
               this.attachedObject.setAnimation((byte)1, (byte)0, this.C18_f243);
            }

            this.attachedObject.activate();
         }
         break;
      case 1:
         if (var1[1] == 320) {
            this.layer = 2;
         } else {
            this.layer = 1;
         }

         if (this.isVisible() && var1[0] > 0 && var1[0] <= 3) {
            GameWorldManager.B().C25_f288.addElement(this);
         }

         if (this.C18_f225 == 3) {
            this.C18_f224 = true;
         }

         this.C18_f236 = (byte)var1[7];
         this.C18_f237 = var1[8];
         this.C18_f238 = var1[9];
         this.C18_f239 = var1[10];
         break;
      case 2:
         if (var1[7] == 0) {
            this.C18_f243 = false;
         } else {
            this.C18_f243 = true;
         }
         break;
      case 3:
         this.layer = 1;
         this.C18_f240 = var1[7];
         this.C18_f241 = var1[8];
         switch(this.C18_f241) {
         case 9:
            this.C18_f241 = 1;
            break;
         case 10:
            this.C18_f241 = 0;
            break;
         case 11:
            this.C18_f241 = 2;
            break;
         case 12:
            this.C18_f241 = 3;
         }

         this.C18_f242 = var1[9];
         this.C18_f224 = true;
      }

      this.primaryStates = new short[3];
      this.secondaryStates = new short[3];
   }

   public final void p() {
      if (this.sprite != null) {
         this.sprite.resetWithColorEffects();
      }

   }

   public final void a(byte var1) {
      switch(this.C18_f223) {
      case 0:
         if (this.C18_f225 == 8) {
            this.setAnimation((byte)0, (byte)-1, false);
            this.C18_f229 = 0;
            this.facingDirection = var1;
            return;
         }

         if (this.C18_f225 != 1 && this.C18_f225 != 18) {
            this.setAnimation(var1, (byte)-1, false);
            this.facingDirection = var1;
            GameWorldManager.B().a(this.C18_f248, 0, this.facingDirection, true);
            return;
         }

         this.facingDirection = (byte)(var1 / 3);
         if (this.facingDirection == 0) {
            if (this.currentDirection == 3) {
               this.setAnimation((byte)1, (byte)-1, false);
               return;
            }

            this.setAnimation(this.currentDirection, (byte)-1, false);
            return;
         }

         if (this.facingDirection == 1) {
            if (this.currentDirection == 3) {
               this.setAnimation((byte)(this.facingDirection * 3 + 1), (byte)-1, false);
               return;
            }

            this.setAnimation((byte)(this.facingDirection * 3 + this.currentDirection), (byte)-1, false);
            return;
         }
         break;
      case 1:
         if (this.C18_f225 == 0) {
            switch(var1) {
            case 0:
               this.setAnimation(var1, (byte)-1, false);
               break;
            case 1:
               this.setAnimation(var1, (byte)-2, false);
               break;
            case 2:
               this.setAnimation(var1, (byte)-1, false);
               break;
            case 3:
               this.setAnimation(var1, (byte)-2, false);
            }
         } else {
            this.setAnimation(var1, (byte)-1, false);
            if (this.C18_f225 == 3) {
               GameWorldManager.B().a(this.C18_f248, 0, var1, true);
            }
         }

         this.facingDirection = var1;
         return;
      case 2:
         this.facingDirection = var1;
         return;
      case 3:
         this.setAnimation(var1, (byte)-2, false);
         this.facingDirection = var1;
         GameWorldManager.B().a(this.C18_f248, 0, this.facingDirection, true);
      }

   }

   public final void q() {
      switch(this.C18_f223) {
      case 0:
         this.z();
         break;
      case 1:
         if (this.C18_f225 == 0) {
            if (this.facingDirection == 0 && GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.p().worldX, PlayerCharacter.p().worldY, this.worldX, this.worldY, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
               this.a((byte)1);
            } else if (this.facingDirection == 2 && !GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.p().worldX, PlayerCharacter.p().worldY, this.worldX, this.worldY, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
               this.a((byte)3);
            } else if (this.facingDirection == 1 && this.sprite.isAtLastFrame()) {
               this.a((byte)2);
            } else if (this.facingDirection == 3 && this.sprite.isAtLastFrame()) {
               this.a((byte)0);
            }
         }

         byte var10001;
         if (this.C18_f225 == 0 && this.sprite.isAtLastFrame() || this.C18_f225 == 1 || this.C18_f225 == 3 && this.getFacingDirection() == 2) {
            if (this.sprite.spriteSetId == 320 && !this.isVisible()) {
               return;
            }

            var10001 = this.C18_f244[this.C18_f236];
            if ((PlayerCharacter.p().visualState == var10001 && this.sprite.spriteSetId != 320 && this.sprite.spriteSetId != 310 || this.sprite.spriteSetId == 320 || this.sprite.spriteSetId == 310) && GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.p().worldX, PlayerCharacter.p().worldY, this.worldX, this.worldY, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameTriggers())) {
               GameWorldManager.B().C25_f290 = this.C18_f237;
               GameWorldManager.B().C25_f291 = this.C18_f238;
               GameWorldManager.B().C25_f295 = this.C18_f239;
               GameScreenManager.getInstance().changeState((byte)9);
            }
         } else if (this.C18_f225 == 2) {
            var10001 = this.C18_f244[this.C18_f236];
            if ((PlayerCharacter.p().visualState == var10001 && this.sprite.spriteSetId != 320 || this.sprite.spriteSetId == 320) && GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.p().worldX, PlayerCharacter.p().worldY, this.worldX, this.worldY, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameTriggers())) {
               for(int var1 = 0; var1 < this.C18_f249.length / 6; ++var1) {
                  if (this.C18_f249[var1 * 6] == this.C18_f248 && this.C18_f249[var1 * 6 + 1] == GameWorldManager.B().C25_f290 && this.C18_f249[var1 * 6 + 2] == GameWorldManager.B().C25_f291) {
                     GameWorldManager.B().C25_f293 = this.C18_f249[var1 * 6 + 3];
                     GameWorldManager.B().C25_f294 = this.C18_f249[var1 * 6 + 4];
                     GameWorldManager.C25_f320 = (byte)this.C18_f249[var1 * 6 + 5];
                     break;
                  }
               }

               GameWorldManager.B().C25_f290 = this.C18_f237;
               GameWorldManager.B().C25_f291 = this.C18_f238;
               GameWorldManager.B().C25_f295 = -1;
               GameScreenManager.getInstance().changeState((byte)9);
            }
         } else if (this.C18_f225 == 4 && PlayerCharacter.p().getFacingDirection() != 9 && PlayerCharacter.p().getFacingDirection() != 10 && GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.p().worldX, PlayerCharacter.p().worldY, this.worldX, this.worldY, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameTriggers())) {
            PlayerCharacter.p().setWorldPosition(this.worldX, this.worldY);
            PlayerCharacter.p().attachedObject.setWorldPosition(this.worldX, this.worldY);
            PlayerCharacter.p().a((byte)9, (byte)this.currentDirection);
            GameWorldManager.B().C25_f295 = this.C18_f239;
         }
      case 2:
      case 3:
      }

      this.updateInteractableState();
   }

   private void z() {
      int var1;
      byte var2;
      byte[] var4;
      switch(this.C18_f225) {
      case 1:
         if (this.facingDirection == 1) {
            var2 = 0;
            this.moveInDirection((int)super.secondaryStates[var2]);
            if (GameWorldManager.B().C25_f313 != null && GameWorldManager.B().C25_f313.followTarget.equals(this) && !PlayerCharacter.p().a(this, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
               GameWorldManager.B().D();
            }
         }

         if (GameWorldManager.B().C25_f313 != null && GameWorldManager.B().C25_f313.followTarget.equals(this) && (!this.isVisible() || !PlayerCharacter.p().a(this, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents()))) {
            GameWorldManager.C25_f318 = -1;
            GameWorldManager.B().D();
         }

         if (this.C18_f246 != null && this.isVisible() && this.C18_f246.followTarget.equals(this) && !PlayerCharacter.p().a(this, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
            this.B();
         }

         if (QuestManager.questEffectObjects != null && QuestManager.questEffectObjects.size() > 0 && this.C18_f245 == 1 && !PlayerCharacter.p().a(this, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
            for(var1 = 0; var1 < QuestManager.questEffectObjects.size(); ++var1) {
               if (((GameObject)QuestManager.questEffectObjects.elementAt(var1)).followTarget.equals(this)) {
                  ((GameObject)QuestManager.questEffectObjects.elementAt(var1)).activate();
                  return;
               }
            }
         }
         break;
      case 2:
         if (this.A()) {
            var4 = new byte[]{0, 1, 2, 3, 5};
            this.a(var4[GameUtils.getRandomInt(5)]);
            if (this.facingDirection != 3 && this.facingDirection != 0) {
               if (this.facingDirection != 5 && this.facingDirection != 2) {
                  if (GameUtils.getRandomInt(2) == 0) {
                     var2 = 3;
                     super.currentDirection = var2;
                  } else {
                     var2 = 1;
                     super.currentDirection = var2;
                  }
               } else {
                  var2 = 2;
                  super.currentDirection = var2;
               }
            } else {
               var2 = 0;
               super.currentDirection = var2;
            }
         }

         if (this.facingDirection == 3) {
            if (this.C18_f229 >= 64) {
               this.a((byte)0);
               return;
            }

            this.moveInDirection((int)4);
            this.C18_f229 += 4;
            return;
         }

         if (this.facingDirection == 5) {
            if (this.C18_f229 <= 0) {
               this.a((byte)2);
               return;
            }

            this.moveInDirection((int)4);
            this.C18_f229 -= 4;
            return;
         }
         break;
      case 3:
         if (this.A()) {
            var4 = new byte[]{0, 1, 2, 4};
            this.a(var4[GameUtils.getRandomInt(4)]);
            if (this.facingDirection == 0) {
               var2 = 0;
               super.currentDirection = var2;
            } else if (this.facingDirection == 2) {
               var2 = 2;
               super.currentDirection = var2;
            } else if (GameUtils.getRandomInt(2) == 0) {
               var2 = 3;
               super.currentDirection = var2;
            } else {
               var2 = 1;
               super.currentDirection = var2;
            }
         }

         if (this.facingDirection == 4) {
            if (this.currentDirection == 1) {
               if (this.C18_f229 >= 64) {
                  this.a((byte)0);
                  return;
               }

               this.moveInDirection((int)4);
               this.C18_f229 += 4;
               return;
            }

            if (this.currentDirection == 3) {
               if (this.C18_f229 <= 0) {
                  this.a((byte)2);
                  return;
               }

               this.moveInDirection((int)4);
               this.C18_f229 -= 4;
               return;
            }
         }
         break;
      case 4:
      case 5:
      case 6:
      case 7:
      case 15:
         if (this.C18_f247 != null && this.isVisible() && this.C18_f247.followTarget.equals(this) && (this.facingDirection != 0 || !PlayerCharacter.p().a(this, PlayerCharacter.p().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents()))) {
            this.y();
         }

         if (this.facingDirection == 1 && this.sprite.isAtLastFrame()) {
            this.a((byte)2);
            if (this.C18_f225 == 6 && this.C18_f225 == 7) {
               GameWorldManager.B().a(this.C18_f248, 0, this.facingDirection, false);
            } else {
               GameWorldManager.B().a(this.C18_f248, 0, this.facingDirection, true);
            }

            if ((this.C18_f225 == 7 || this.C18_f225 == 6) && (var1 = GameUtils.getRandomInt(2)) > 0) {
               PlayerCharacter.p().s(var1);
               int[] var3 = new int[]{var1, super.worldX, super.worldY - 20, 0};
               PlayerCharacter.p().C53_f799.addElement(var3);
            }

            QuestManager.isQuestReady = false;
            return;
         }
         break;
      case 8:
         if (this.facingDirection == 1) {
            if (this.C18_f229 < 2 && this.a(this.currentDirection, 8, (byte)0)) {
               ++this.C18_f229;
               this.moveInDirection((int)8);
               this.t();
               return;
            }

            this.a((byte)0);
            return;
         }
         break;
      case 9:
      case 10:
         if (this.C18_f233 == 1) {
            if ((this.sprite.spriteSetId == 302 || this.sprite.spriteSetId == 298) && this.a(this.currentDirection, 4, (byte)1)) {
               this.moveInDirection((int)4);
               ((GameObject)this.followTarget).moveInDirection(4);
               return;
            }

            if (this.a(this.currentDirection, 4, (byte)2)) {
               this.moveInDirection((int)4);
               ((GameObject)this.followTarget).moveInDirection(4);
               return;
            }

            this.t();
            this.C18_f233 = 2;
            this.a((byte)0);
            return;
         }
         break;
      case 11:
         return;
      case 12:
         if (this.isVisible()) {
            if (!GameUtils.isPointInShortArrayRectangle(PlayerCharacter.p().worldX, PlayerCharacter.p().worldY, this.worldX, this.worldY, this.sprite.getCurrentFrameEvents())) {
               if (this.a(this.currentDirection, 4, (byte)0)) {
                  this.moveInDirection((int)4);
                  return;
               }

               if (this.currentDirection == 2) {
                  this.currentDirection = 0;
                  return;
               }

               this.currentDirection = 2;
               return;
            }

            if (PlayerCharacter.p().getFacingDirection() != 8) {
               PlayerCharacter.p().a((byte)8, (byte)this.currentDirection);
               return;
            }
         }
         break;
      case 13:
         if (this.isVisible()) {
            if (!GameUtils.isPointInShortArrayRectangle(PlayerCharacter.p().worldX, PlayerCharacter.p().worldY, this.worldX, this.worldY, this.sprite.getCurrentFrameEvents())) {
               if (this.a(this.currentDirection, 4, (byte)0)) {
                  this.moveInDirection((int)4);
                  return;
               }

               if (this.currentDirection == 3) {
                  this.currentDirection = 1;
                  return;
               }

               this.currentDirection = 3;
               return;
            }

            if (PlayerCharacter.p().getFacingDirection() != 8) {
               PlayerCharacter.p().a((byte)8, (byte)this.currentDirection);
               return;
            }
         }
         break;
      case 14:
         this.s();
         if (this.C18_f232 < 4) {
            ++this.C18_f232;
            return;
         }

         this.C18_f232 = 0;
         return;
      case 16:
         if (GameUtils.isPointInShortArrayRectangle(PlayerCharacter.p().worldX, PlayerCharacter.p().worldY, this.worldX, this.worldY, this.sprite.getCurrentFrameEvents()) && PlayerCharacter.p().getFacingDirection() != 5) {
            PlayerCharacter.p().a((byte)5, (byte) PlayerCharacter.p().currentDirection);
            return;
         }
      }

   }

   public final void r() {
      byte var2;
      if (this.C18_f225 == 9) {
         this.a((byte)1);
         if ((this.sprite.spriteSetId == 302 || this.sprite.spriteSetId == 298) && this.a((byte)1, 4, (byte)1)) {
            var2 = 1;
            super.currentDirection = var2;
         } else if (this.a((byte)1, 4, (byte)2)) {
            var2 = 1;
            super.currentDirection = var2;
         } else {
            var2 = 3;
            super.currentDirection = var2;
         }
      } else if ((this.sprite.spriteSetId == 302 || this.sprite.spriteSetId == 298) && this.a((byte)2, 4, (byte)1)) {
         this.a((byte)2);
         var2 = 2;
         super.currentDirection = var2;
      } else if (this.a((byte)2, 4, (byte)2)) {
         this.a((byte)2);
         var2 = 2;
         super.currentDirection = var2;
      } else {
         this.a((byte)1);
         var2 = 0;
         super.currentDirection = var2;
      }

      this.C18_f233 = 1;
   }

   public final void s() {
      this.C18_f234 = 0;

      while(true) {
         boolean var10000;
         label86: {
            byte var4;
            byte var10001 = super.sprite.getCurrentAnimationId();
            int var10002 = 16 * (this.C18_f234 + 1);
            boolean var1 = false;
            int var3 = var10002;
            byte var2 = var10001;
            NPCEntity var5 = this;
            var4 = 0;
            int var6;
            label83:
            switch(var2) {
            case 0:
               var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY + var3);
               var6 = 0;

               while(true) {
                  if (var6 >= GameWorldManager.B().C25_f287.length) {
                     break label83;
                  }

                  if (GameWorldManager.B().C25_f287[var6].C18_f225 != var5.C18_f225 && GameWorldManager.B().C25_f287[var6].sprite.getCurrentFrameEvents() != null && GameUtils.isPointInShortArrayRectangle(var5.worldX, var5.worldY + var3, GameWorldManager.B().C25_f287[var6].worldX, GameWorldManager.B().C25_f287[var6].worldY, GameWorldManager.B().C25_f287[var6].sprite.getCurrentFrameEvents())) {
                     GameWorldManager.B().C25_f287[var6].followTarget = var5;
                     var10000 = false;
                     break label86;
                  }

                  ++var6;
               }
            case 1:
               var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX + var3, this.worldY);
               var6 = 0;

               while(true) {
                  if (var6 >= GameWorldManager.B().C25_f287.length) {
                     break label83;
                  }

                  if (GameWorldManager.B().C25_f287[var6].C18_f225 != var5.C18_f225 && GameWorldManager.B().C25_f287[var6].sprite.getCurrentFrameEvents() != null && GameUtils.isPointInShortArrayRectangle(var5.worldX + var3, var5.worldY, GameWorldManager.B().C25_f287[var6].worldX, GameWorldManager.B().C25_f287[var6].worldY, GameWorldManager.B().C25_f287[var6].sprite.getCurrentFrameEvents())) {
                     GameWorldManager.B().C25_f287[var6].followTarget = var5;
                     var10000 = false;
                     break label86;
                  }

                  ++var6;
               }
            case 2:
               var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY - var3);
               var6 = 0;

               while(true) {
                  if (var6 >= GameWorldManager.B().C25_f287.length) {
                     break label83;
                  }

                  if (GameWorldManager.B().C25_f287[var6].C18_f225 != var5.C18_f225 && GameWorldManager.B().C25_f287[var6].sprite.getCurrentFrameEvents() != null && GameUtils.isPointInShortArrayRectangle(var5.worldX, var5.worldY - var3, GameWorldManager.B().C25_f287[var6].worldX, GameWorldManager.B().C25_f287[var6].worldY, GameWorldManager.B().C25_f287[var6].sprite.getCurrentFrameEvents())) {
                     GameWorldManager.B().C25_f287[var6].followTarget = var5;
                     var10000 = false;
                     break label86;
                  }

                  ++var6;
               }
            case 3:
               var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX - var3, this.worldY);

               for(var6 = 0; var6 < GameWorldManager.B().C25_f287.length; ++var6) {
                  if (GameWorldManager.B().C25_f287[var6].C18_f225 != var5.C18_f225 && GameWorldManager.B().C25_f287[var6].sprite.getCurrentFrameEvents() != null && GameUtils.isPointInShortArrayRectangle(var5.worldX - var3, var5.worldY, GameWorldManager.B().C25_f287[var6].worldX, GameWorldManager.B().C25_f287[var6].worldY, GameWorldManager.B().C25_f287[var6].sprite.getCurrentFrameEvents())) {
                     GameWorldManager.B().C25_f287[var6].followTarget = var5;
                     var10000 = false;
                     break label86;
                  }
               }
            }

            var10000 = var4 == 0;
         }

         if (!var10000) {
            return;
         }

         ++this.C18_f234;
      }
   }

   private boolean A() {
      ++this.C18_f230;
      if (this.C18_f230 >= this.C18_f231) {
         this.C18_f230 = 0;
         this.C18_f231 = GameUtils.getRandomInRange(20, 40);
         return true;
      } else {
         return false;
      }
   }

   private boolean a(byte var1, int var2, byte var3) {
      byte var4 = 0;
      switch(var1) {
      case 0:
         var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY + var2);
         break;
      case 1:
         var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX + var2 + this.sprite.getCurrentFrameEvents()[2] / 2, this.worldY);
         break;
      case 2:
         var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY - this.sprite.getCurrentFrameEvents()[3] - var2);
         break;
      case 3:
         var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX - var2 - this.sprite.getCurrentFrameEvents()[2] / 2, this.worldY);
      }

      return var4 == var3;
   }

   public final void c(Graphics var1, int var2, int var3) {
      switch(super.sprite.getCurrentAnimationId()) {
      case 0:
         var1.setColor(65280);
         var1.fillRect(this.worldX - var2 - (this.C18_f232 + 5) / 2, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 + 20, this.C18_f232 + 5, this.C18_f234 + 1 << 4);
         var1.setColor(16777215);
         var1.fillRect(this.worldX - var2 - (this.C18_f232 + 3) / 2, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 + 20, this.C18_f232 + 3, this.C18_f234 + 1 << 4);
         return;
      case 1:
         var1.setColor(65280);
         var1.fillRect(this.worldX - var2 + 7, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.C18_f232 + 5) / 2 + 13, this.C18_f234 << 4, this.C18_f232 + 5);
         var1.setColor(16777215);
         var1.fillRect(this.worldX - var2 + 7, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.C18_f232 + 3) / 2 + 13, this.C18_f234 << 4, this.C18_f232 + 3);
      default:
         return;
      case 2:
         var1.setColor(65280);
         var1.fillRect(this.worldX - var2 - (this.C18_f232 + 5) / 2, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.C18_f234 << 4) + 8, this.C18_f232 + 5, this.C18_f234 << 4);
         var1.setColor(16777215);
         var1.fillRect(this.worldX - var2 - (this.C18_f232 + 3) / 2, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.C18_f234 << 4) + 8, this.C18_f232 + 3, this.C18_f234 << 4);
         return;
      case 3:
         var1.setColor(65280);
         var1.fillRect(this.worldX - var2 - 8 - (this.C18_f234 << 4), this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.C18_f232 + 5) / 2 + 13, this.C18_f234 << 4, this.C18_f232 + 5);
         var1.setColor(16777215);
         var1.fillRect(this.worldX - var2 - 8 - (this.C18_f234 << 4), this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.C18_f232 + 3) / 2 + 13, this.C18_f234 << 4, this.C18_f232 + 3);
      }
   }

   public final void t() {
      GameWorldManager.B().a(this.C18_f248, 0, this.worldX);
      GameWorldManager.B().a(this.C18_f248, 1, this.worldY);
   }

   public final void u() {
      byte var1 = 0;
      if (this.isVisible()) {
         var1 = 1;
      }

      GameWorldManager.B().a(this.C18_f248, 1, var1, true);
      GameWorldManager.B().a(this.C18_f248, 0, this.facingDirection, true);
      GameWorldManager.B().a(this.C18_f248, 2, this.currentDirection, true);
   }

   public final void moveInDirection(int var1) {
      int var10000 = this.worldX;
      var10000 = this.worldY;
      super.moveInDirection(var1);
   }

   public final void f(byte var1) {
      this.C18_f245 = 1;
   }

   public final byte v() {
      return this.C18_f245;
   }

   public final void f(int var1) {
      if (this.C18_f247 == null && this.isVisible()) {
         this.C18_f247 = new GameObject();
         this.C18_f247.loadSpriteSet(259, false);
         this.C18_f247.sprite.applyColorEffects();
         this.C18_f247.setAnimation((byte)7, (byte)-1, true);
         this.C18_f247.setWorldPosition(this.worldX, this.worldY - var1);
         this.C18_f247.followTarget = this;
      }

      if (this.C18_f247 != null) {
         this.C18_f247.activate();
      }

   }

   public final void w() {
      if (GameWorldManager.B().n(this.C18_f248)) {
         this.x();
      }

   }

   private void B() {
      if (this.C18_f246 != null) {
         this.C18_f246.activate();
      }

   }

   public final void deactivate() {
      super.deactivate();
      this.x();
      this.deactivateAttachedObject();
      this.y();
   }

   public final void x() {
      if (this.C18_f246 != null) {
         this.C18_f246.deactivate();
      }

   }

   public final void y() {
      if (this.C18_f247 != null) {
         this.C18_f247.deactivate();
      }

   }

   public final void setWorldPosition(int var1, int var2) {
      super.setWorldPosition(var1, var2);
      if (this.attachedObject != null && this.attachedObject.isVisible()) {
         this.attachedObject.setWorldPosition(var1, var2);
      }

   }
}
