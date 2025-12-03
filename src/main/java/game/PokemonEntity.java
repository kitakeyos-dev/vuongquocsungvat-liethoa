package game;

import engine.GameUtils;
import engine.entity.GameObject;
import engine.graphics.EffectEntity;
import engine.entity.Sprite;
import engine.entity.ResourceManager;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class PokemonEntity extends GameObject {
   private static short[] QUALITY_MULTIPLIERS = new short[]{90, 95, 100, 110, 125};
   public static final byte[] EVOLUTION_LEVEL_REQUIREMENTS = new byte[]{12, 30, 5};
   EffectEntity skillEffect;
   short[][] buffs;
   short[][] debuffs;
   byte[][] activeStatusEffects;
   private byte[] statusEffectCounts;
   short[] skillPP;
   byte[] skills;
   private byte skillCount;
   private short[] cachedStats;
   protected int animationTimer = 0;
   private int displayHp;
   private int damageReceived = 0;
   private int currentExp = 0;
   protected int battleTimer = 0;
   private int level = 0;
   protected short spriteId;
   private byte animationState = 0;
   private int speciesId = 0;
   private byte nature;
   protected byte currentSkillId;
   private int battlePosition = 0;
   private boolean isCaptured;
   protected short friendship;
   protected byte rageLevel;
   protected Vector buffList = new Vector();
   protected Vector debuffList = new Vector();
   public byte statusFlags = 0;
   protected boolean isInBattle;
   protected short[] skillParams;
   protected EffectEntity specialEffect = null;
   private byte effectRenderOrder = 0;

   public PokemonEntity() {
      this.primaryStates = new short[23];
      this.secondaryStates = new short[23];
      this.skillPP = new short[5];
      this.skills = new byte[5];
      this.cachedStats = new short[4];

      for(int i = 0; i < this.skills.length; ++i) {
         this.skills[i] = -1;
      }

      this.skillParams = new short[16];
      this.buffs = new short[16][5];
      this.debuffs = new short[11][5];
      this.activeStatusEffects = new byte[][]{{-1, -1, -1}, {-1, -1, -1}};
      this.statusEffectCounts = new byte[2];
      this.useAlternativeRender = false;
   }

   public final void initialize(int speciesId, int level, short equippedItem, byte gender, short quality, byte nature) {
      this.speciesId = speciesId;
      this.level = level;
      short statValue;
      int baseStat;

      // Set quality (primaryStates[0])
      if (quality == -1) {
         statValue = (short) GameUtils.getRandomInRange(ResourceManager.gameDatabase[0][this.speciesId][3], ResourceManager.gameDatabase[0][this.speciesId][3]);
      } else {
         statValue = quality;
      }
      super.primaryStates[0] = statValue;

      // Calculate and set Max HP (primaryStates[1])
      baseStat = ResourceManager.gameDatabase[0][this.speciesId][5] + ResourceManager.gameDatabase[0][this.speciesId][6] * level + ResourceManager.gameDatabase[0][this.speciesId][7];
      statValue = (short)(baseStat * QUALITY_MULTIPLIERS[super.primaryStates[0] - 1] / 100);
      super.primaryStates[1] = statValue;

      // Calculate and set Attack (primaryStates[2])
      baseStat = ResourceManager.gameDatabase[0][this.speciesId][8] + ResourceManager.gameDatabase[0][this.speciesId][9] * level + ResourceManager.gameDatabase[0][this.speciesId][10];
      statValue = (short)(baseStat * QUALITY_MULTIPLIERS[super.primaryStates[0] - 1] / 100);
      super.primaryStates[2] = statValue;

      // Calculate and set Defense (primaryStates[3])
      baseStat = ResourceManager.gameDatabase[0][this.speciesId][11] + ResourceManager.gameDatabase[0][this.speciesId][12] * level / 10 + ResourceManager.gameDatabase[0][this.speciesId][13];
      statValue = (short)(baseStat * QUALITY_MULTIPLIERS[super.primaryStates[0] - 1] / 100);
      super.primaryStates[3] = statValue;

      // Calculate and set Speed (primaryStates[4])
      baseStat = ResourceManager.gameDatabase[0][this.speciesId][14] + ResourceManager.gameDatabase[0][this.speciesId][15] * level / 10 + ResourceManager.gameDatabase[0][this.speciesId][16];
      statValue = (short)(baseStat * QUALITY_MULTIPLIERS[super.primaryStates[0] - 1] / 100);
      super.primaryStates[4] = statValue;

      // Set equipped item (primaryStates[5])
      super.primaryStates[5] = equippedItem;

      // Set gender (primaryStates[6])
      super.primaryStates[6] = (short)gender;

      this.applyNature(nature);
      this.saveCurrentStates();
      this.spriteId = ResourceManager.gameDatabase[0][this.speciesId][17];
      this.setDisplayHp(super.secondaryStates[1]);
   }

   public final void initializeFromData(int[] data) {
      this.initialize(data[0], data[1], (short)data[2], (byte)data[3], (short)data[4], (byte)data[5]);
      this.applyBadgeBonuses();
      this.setExpAndFriendship((short)data[6], data[7], data[8]);
      int[] skillData = new int[data.length - 9];

      for(int i = 0; i < skillData.length; ++i) {
         skillData[i] = data[i + 9];
      }

      this.loadSkillData(skillData);
   }

   public final void setExpAndFriendship(short currentHp, int exp, int friendshipValue) {
      this.applyBadgeBonuses();
      super.secondaryStates[1] = currentHp;
      this.setDisplayHp(super.secondaryStates[1]);
      this.currentExp = exp;
      this.friendship = (byte)friendshipValue;
   }

   public final void activate() {
      super.activate();
      if (this.sprite == null) {
         this.sprite = new Sprite();
      }

      this.sprite.loadSpriteSet(this.spriteId, false);
      this.setAnimationState((byte)0, true);
   }

   public final void deactivate() {
      super.deactivate();
      if (this.sprite != null) {
         this.sprite.forceCleanup();
         this.sprite = null;
      }

   }

   public final void createSkillEffect(short effectId, byte effectType) {
      byte direction = this.currentDirection;
      this.skillEffect = null;
      this.skillEffect = new EffectEntity();
      this.skillEffect.initializeEffect(new short[]{effectId, (short)effectType, (short)direction});
      this.skillEffect.setWorldPosition(this.worldX, this.worldY);
      if (effectId == 20 && effectType == 3 || effectId == 22 && effectType == 4) {
         int[] bounds = this.sprite.getSpritePartBounds(0, direction);
         this.skillEffect.setWorldPosition(this.worldX, this.worldY - bounds[3]);
      }

      this.skillEffect.setInteractable(true);
   }

   private void createSpecialEffect(int effectIndex) {
      this.specialEffect = new EffectEntity();
      short[] sourceData = BattleSystemManager.C29_f450[effectIndex];
      short[] effectData = new short[sourceData.length + 5];
      System.arraycopy(sourceData, 1, effectData, 6, sourceData.length - 1);
      short[] header = new short[]{sourceData[0], (short)super.worldX, (short)super.worldY, ResourceManager.gameDatabase[0][this.speciesId][17], 0, (short)this.currentDirection};
      System.arraycopy(header, 0, effectData, 0, header.length);
      this.specialEffect.initializeEffect(effectData);
      this.specialEffect.setInteractable(true);
   }

   public final void setAnimationState(byte state, boolean restartIfSame) {
      label31:
      switch(state) {
         case 0:
            this.sprite.setAnimation(state, (byte)-1, restartIfSame);
            break;
         case 1:
            this.sprite.setAnimation(state, (byte)0, restartIfSame);
            switch(this.speciesId) {
               case 0:
                  this.createSpecialEffect(27);
                  break label31;
               case 10:
                  this.effectRenderOrder = 1;
                  this.createSpecialEffect(28);
                  break label31;
               case 60:
               case 61:
                  this.createSpecialEffect(22);
                  break label31;
               case 62:
                  this.createSpecialEffect(24);
                  break label31;
               case 67:
                  this.effectRenderOrder = 1;
                  this.createSpecialEffect(30);
                  break label31;
               case 68:
                  this.effectRenderOrder = 1;
                  this.createSpecialEffect(31);
                  break label31;
               case 70:
                  this.effectRenderOrder = 1;
                  this.createSpecialEffect(29);
                  break label31;
               case 71:
                  this.effectRenderOrder = 1;
                  this.createSpecialEffect(32);
                  break label31;
               case 72:
                  this.effectRenderOrder = 1;
                  this.createSpecialEffect(33);
                  break label31;
               case 75:
                  this.createSpecialEffect(20);
                  break label31;
               case 87:
                  this.createSpecialEffect(21);
                  break label31;
               case 91:
                  this.createSpecialEffect(26);
                  break label31;
               case 92:
                  this.createSpecialEffect(25);
                  break label31;
               case 97:
               case 98:
                  this.createSpecialEffect(23);
               default:
                  break label31;
            }
         case 2:
            this.sprite.setAnimation(state, (byte)0, restartIfSame);
            break;
         case 3:
            if (BattleSystemManager.B().C29_f398 == 0) {
               this.deactivate();
               short[] var3 = new short[]{16, 0, 0, 4};
               this.specialEffect = new EffectEntity();
               short[] var4 = new short[var3.length + 5];
               System.arraycopy(var3, 1, var4, 6, var3.length - 1);
               System.arraycopy(var3 = new short[]{var3[0], (short)super.worldX, (short)super.worldY, ResourceManager.gameDatabase[0][this.speciesId][17], 0, (short)this.currentDirection}, 0, var4, 0, var3.length);
               this.specialEffect.initializeEffect(var4);
               this.specialEffect.setInteractable(true);
               this.specialEffect.activateEffect();
            }
            break;
         case 4:
            this.sprite.setAnimation(state, (byte)-1, restartIfSame);
      }

      this.animationState = state;
   }

   public final void update() {
      this.updateAnimation();
      if (this.skillEffect != null) {
         this.skillEffect.updateEffect();
      }

      if (this.specialEffect != null) {
         this.specialEffect.updateEffect();
      }

   }

   public final void render(Graphics g) {
      if (this.specialEffect != null && this.animationState == 1) {
         switch(this.speciesId) {
            case 0:
               if (this.sprite.isAtFrame(5)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 10:
               if (this.sprite.isAtFrame(5)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 60:
            case 61:
               if (this.sprite.isAtFrame(3)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 62:
               if (this.sprite.isAtFrame(8)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 67:
               if (this.sprite.isAtFrame(1)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 68:
               if (this.sprite.isAtFrame(3)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 70:
               if (this.sprite.isAtFrame(9)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 71:
               if (this.sprite.isAtFrame(7)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 72:
               if (this.sprite.isAtFrame(4)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 75:
               if (this.sprite.isAtFrame(15)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 87:
               if (this.sprite.isAtFrame(1)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 91:
               if (this.sprite.isAtFrame(2)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 92:
               if (this.sprite.isAtFrame(4)) {
                  this.specialEffect.activateEffect();
               }
               break;
            case 97:
            case 98:
               if (this.sprite.isAtFrame(8)) {
                  this.specialEffect.activateEffect();
               }
         }
      }

      if (this.specialEffect != null && this.effectRenderOrder == 0) {
         this.specialEffect.render(g);
      }

      if (this.skillEffect != null && BattleSystemManager.C29_f448[this.skillEffect.effectType - 20][this.skillEffect.sprite.getCurrentAnimationId()][this.skillEffect.sprite.getCurrentFrameIndex()] == 0) {
         this.skillEffect.render(g);
      }

      if (this.isVisible) {
         this.sprite.renderCurrentFrame(g, this.worldX, this.worldY, this.currentDirection);
      }

      if (this.specialEffect != null && this.effectRenderOrder == 1) {
         this.specialEffect.render(g);
      }

      if (this.skillEffect != null && BattleSystemManager.C29_f448[this.skillEffect.effectType - 20][this.skillEffect.sprite.getCurrentAnimationId()][this.skillEffect.sprite.getCurrentFrameIndex()] == 1) {
         this.skillEffect.render(g);
      }

   }

   public final byte getAnimationState() {
      return this.animationState;
   }

   public final int getSpeciesId() {
      return this.speciesId;
   }

   public final void setBattlePosition(int position) {
      this.battlePosition = position;
   }

   public final int getBattlePosition() {
      return this.battlePosition;
   }

   public final int getLevel() {
      return this.level;
   }

   public final boolean isMaxLevel() {
      return this.level == 50;
   }

   protected final void addExp(int amount) {
      if (this.level < 50) {
         this.currentExp += amount;
         if (this.currentExp < 0) {
            this.currentExp = 0;
         }
      }
   }

   public final int getExpToNextLevel() {
      return this.level >= 50 ? calculateExpForLevel(50) : calculateExpForLevel(this.level + 1);
   }

   public final void levelUp() {
      ++this.level;
      this.addExp(-calculateExpForLevel(this.level));
      this.checkEvolution();

      for(int i = 0; i < this.skills.length; ++i) {
         if (this.skills[i] != -1) {
            this.skillPP[i] = ResourceManager.gameDatabase[1][this.skills[i]][5];
         }
      }

      this.recalculateStats();
   }

   public final void addLevels(int count) {
      this.level += count;
      this.checkEvolution();
      this.recalculateStats();
   }

   private static int calculateExpForLevel(int level) {
      return level * 15 * level - 200;
   }

   private void applyBadgeBonuses() {
      short var1;
      byte var3;
      if (PlayerCharacter.getInstance().getBadgeState((byte)1, (byte)0) == 2 && PlayerCharacter.getInstance().getBadgeState((byte)1, (byte)1) == 1) {
         var3 = 1;
         var1 = (short)(super.primaryStates[var3] * ResourceManager.gameDatabase[2][1][6] / 100);
         var3 = 1;
         var1 += super.primaryStates[var3];
         var3 = 1;
         super.primaryStates[var3] = var1;
      }

      if (PlayerCharacter.getInstance().getBadgeState((byte)2, (byte)0) == 2 && PlayerCharacter.getInstance().getBadgeState((byte)2, (byte)1) == 1) {
         var3 = 3;
         var1 = (short)(super.primaryStates[var3] * ResourceManager.gameDatabase[2][2][6] / 100);
         var3 = 3;
         var1 += super.primaryStates[var3];
         var3 = 3;
         super.primaryStates[var3] = var1;
      }

   }

   private void recalculateStats() {
      int var10002 = ResourceManager.gameDatabase[0][this.speciesId][5] + ResourceManager.gameDatabase[0][this.speciesId][6] * this.level + ResourceManager.gameDatabase[0][this.speciesId][7];
      byte var2 = 0;
      short var3 = (short)(var10002 * QUALITY_MULTIPLIERS[super.primaryStates[var2] - 1] / 100);
      var2 = 1;
      super.primaryStates[var2] = var3;
      var10002 = ResourceManager.gameDatabase[0][this.speciesId][8] + ResourceManager.gameDatabase[0][this.speciesId][9] * this.level + ResourceManager.gameDatabase[0][this.speciesId][10];
      var2 = 0;
      var3 = (short)(var10002 * QUALITY_MULTIPLIERS[super.primaryStates[var2] - 1] / 100);
      var2 = 2;
      super.primaryStates[var2] = var3;
      var10002 = ResourceManager.gameDatabase[0][this.speciesId][11] + ResourceManager.gameDatabase[0][this.speciesId][12] * this.level / 10 + ResourceManager.gameDatabase[0][this.speciesId][13];
      var2 = 0;
      var3 = (short)(var10002 * QUALITY_MULTIPLIERS[super.primaryStates[var2] - 1] / 100);
      var2 = 3;
      super.primaryStates[var2] = var3;
      var10002 = ResourceManager.gameDatabase[0][this.speciesId][14] + ResourceManager.gameDatabase[0][this.speciesId][15] * this.level / 10 + ResourceManager.gameDatabase[0][this.speciesId][16];
      var2 = 0;
      var3 = (short)(var10002 * QUALITY_MULTIPLIERS[super.primaryStates[var2] - 1] / 100);
      var2 = 4;
      super.primaryStates[var2] = var3;
      this.saveCurrentStates();
      var2 = 1;
      this.setDisplayHp(super.secondaryStates[var2]);
   }

   public static short calculateMaxHp(int var0, int var1, int var2) {
      return (short)((ResourceManager.gameDatabase[0][var0][5] + ResourceManager.gameDatabase[0][var0][6] * var1 + ResourceManager.gameDatabase[0][var0][7]) * QUALITY_MULTIPLIERS[var2 - 1] / 100);
   }

   public final void cacheOldStats() {
      for(int var1 = 0; var1 < this.cachedStats.length; ++var1) {
         int var10003 = this.level - 5;
         byte var3 = 0;
         this.cachedStats[var1] = calculateStat(this.speciesId, var10003, super.primaryStates[var3], var1 + 1);
      }

   }

   public final void cacheCurrentStats() {
      for(int var1 = 0; var1 < this.cachedStats.length; ++var1) {
         byte var3 = (byte)(var1 + 1);
         this.cachedStats[var1] = super.primaryStates[var3];
      }

   }

   public final short getCachedStat(int var1) {
      return this.cachedStats[var1];
   }

   public final void applyRageBoost() {
      if (this.rageLevel < 20) {
         ++this.rageLevel;
         byte var2 = 2;
         short var10002 = super.primaryStates[var2];
         var2 = 2;
         short var3 = (short)(var10002 + super.primaryStates[var2] * this.rageLevel / 100);
         var2 = 2;
         super.secondaryStates[var2] = var3;
         var2 = 3;
         var10002 = super.primaryStates[var2];
         var2 = 3;
         var3 = (short)(var10002 + super.primaryStates[var2] * this.rageLevel / 100);
         var2 = 3;
         super.secondaryStates[var2] = var3;
         var2 = 4;
         var10002 = super.primaryStates[var2];
         var2 = 4;
         var3 = (short)(var10002 + super.primaryStates[var2] * this.rageLevel / 100);
         var2 = 4;
         super.secondaryStates[var2] = var3;
      }

   }

   public final int getCurrentExp() {
      return this.currentExp;
   }

   public final void setDamageReceived(int var1) {
      this.damageReceived = var1;
   }

   public final int getDamageReceived() {
      return this.damageReceived;
   }

   public final int calculateBaseDamage() {
      byte var3;
      if (((PokemonEntity)this.followTarget).battlePosition == 0 && PlayerCharacter.getInstance().getBadgeState((byte)4, (byte)0) == 2) {
         var3 = 3;
         short var1 = (short)(this.followTarget.primaryStates[var3] * (100 + ResourceManager.gameDatabase[2][4][5]) / 100);
         var3 = 3;
         this.followTarget.secondaryStates[var3] = var1;
      }

      short var10000;
      int var4;
      if (((PokemonEntity)this.followTarget).hasEquippedItem((byte)2)) {
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
      if (this.hasEquippedItem((byte)0)) {
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
      } else if (this.hasEquippedItem((byte)1)) {
         var3 = 2;
         var5 = super.secondaryStates[var3] * (100 + ResourceManager.gameDatabase[3][1][5]) / 100;
         var3 = 3;
         var4 = var5 - this.followTarget.secondaryStates[var3];
      }

      return var4;
   }

   public final int getStatWithBuffs(byte statIndex) {
      int result = super.primaryStates[statIndex];
      switch(statIndex) {
         case 2: // Attack
            if (this.hasEquippedItem((byte)0)) {
               if (super.secondaryStates[1] <= ResourceManager.gameDatabase[3][0][5] * super.primaryStates[1] / 100) {
                  result = super.primaryStates[2] * (100 + ResourceManager.gameDatabase[3][0][6]) / 100;
               }
            } else if (this.hasEquippedItem((byte)1)) {
               result = super.primaryStates[2] * (100 + ResourceManager.gameDatabase[3][1][5]) / 100;
            }
            break;
         case 3: // Defense
            if (this.hasEquippedItem((byte)2)) {
               result = super.secondaryStates[3] * (100 + ResourceManager.gameDatabase[3][2][5]) / 100;
            }
         case 4: // Speed
      }
      return result;
   }

   public final void takeDamage(int amount) {
      int damage = amount;
      if (amount <= 0) {
         damage = 1;
      }

      this.setDisplayHp(super.secondaryStates[1]);
      super.secondaryStates[1] = (short)(super.secondaryStates[1] - damage);
      if (super.secondaryStates[1] <= 0) {
         super.secondaryStates[1] = 0;
      }
   }

   public final void heal(int amount) {
      super.secondaryStates[1] = (short)(super.secondaryStates[1] + amount);
      if (super.secondaryStates[1] >= super.primaryStates[1]) {
         super.secondaryStates[1] = super.primaryStates[1];
      }
   }

   private void restoreAllSkillPP(int amount) {
      for(int i = 0; i < this.skills.length; ++i) {
         if (this.skills[i] != -1) {
            this.skillPP[i] = (short)(this.skillPP[i] + amount);
            if (this.skillPP[i] >= ResourceManager.gameDatabase[1][this.skills[i]][5]) {
               this.skillPP[i] = ResourceManager.gameDatabase[1][this.skills[i]][5];
            }
         }
      }
   }

   public final boolean hasBuff(int buffId) {
      return this.buffs[buffId][4] == 1;
   }

   public final int applyBuff(byte var1, int var2, int var3) {
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
               var10000 = this.buffs[var1];
               var7 = 3;
               var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
               this.buffs[var1][2] = (short)(ResourceManager.gameDatabase[6][var1][4] * this.calculateBaseDamage() / 100);
               var7 = 3;
               var8 = (short)(super.primaryStates[var7] + this.buffs[var1][1]);
               var7 = 3;
               super.secondaryStates[var7] = var8;
               break;
            case 1:
               var10000 = this.buffs[var1];
               var7 = 3;
               var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
               this.buffs[var1][2] = ResourceManager.gameDatabase[6][var1][4];
               var7 = 3;
               var8 = (short)(super.primaryStates[var7] - this.buffs[var1][1]);
               var7 = 3;
               super.secondaryStates[var7] = var8;
               break;
            case 2:
               var10000 = this.buffs[var1];
               var7 = 3;
               var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
               this.buffs[var1][2] = ResourceManager.gameDatabase[6][var1][4];
               var7 = 3;
               var8 = (short)(super.primaryStates[var7] + this.buffs[var1][1]);
               var7 = 3;
               super.secondaryStates[var7] = var8;
               break;
            case 3:
               var10000 = this.buffs[var1];
               var7 = 1;
               var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
               var4 = this.buffs[var1][1];
               var7 = 1;
               this.setDisplayHp(super.secondaryStates[var7]);
               this.heal(this.buffs[var1][1]);
               break;
            case 4:
               this.skillParams[4] = (short)var3;
               var10000 = this.buffs[var1];
               var7 = 3;
               var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[1][var3][8] / 100);
               var7 = 3;
               var8 = (short)(super.primaryStates[var7] + this.buffs[var1][1]);
               var7 = 3;
               super.secondaryStates[var7] = var8;
               break;
            case 5:
               this.buffs[var1][1] = ResourceManager.gameDatabase[6][var1][3];
               break;
            case 6:
               this.buffs[var1][1] = ResourceManager.gameDatabase[6][var1][3];
               this.buffs[var1][2] = ResourceManager.gameDatabase[6][var1][4];
               break;
            case 7:
               this.skillParams[7] = (short)var3;
               var10000 = this.buffs[var1];
               var7 = 4;
               var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[1][var3][8] / 100);
               var7 = 4;
               var8 = (short)(super.primaryStates[var7] + this.buffs[var1][1]);
               var7 = 4;
               super.secondaryStates[var7] = var8;
               break;
            case 8:
               this.buffs[var1][1] = ResourceManager.gameDatabase[6][var1][3];
               break;
            case 9:
               var10000 = this.buffs[var1];
               var7 = 4;
               var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
               var10000 = this.buffs[var1];
               var7 = 3;
               var10000[2] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][4] / 100);
               var7 = 4;
               var8 = (short)(super.primaryStates[var7] + this.buffs[var1][1]);
               var7 = 4;
               super.secondaryStates[var7] = var8;
               var7 = 3;
               var8 = (short)(super.primaryStates[var7] - this.buffs[var1][2]);
               var7 = 3;
               super.secondaryStates[var7] = var8;
               break;
            case 10:
               var10000 = this.buffs[var1];
               var7 = 2;
               var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
               var7 = 2;
               var8 = (short)(super.primaryStates[var7] + this.buffs[var1][1]);
               var7 = 2;
               super.secondaryStates[var7] = var8;
               break;
            case 11:
               this.buffs[var1][1] = (short)var2;
               PokemonEntity var9 = BattleSystemManager.B().C29_f402[var2];
               int var5 = 0;

               while(true) {
                  var7 = 0;
                  if (var5 >= var9.statusEffectCounts[var7]) {
                     var9.clearAllBuffs();
                     break label34;
                  }

                  this.applyBuff((byte)var9.activeStatusEffects[0][var5], var9.buffs[var9.activeStatusEffects[0][var5]][1], BattleSystemManager.B().C29_f402[var2].skillParams[var5]);
                  ++var5;
               }
            case 12:
               this.skillParams[12] = 1;
               break;
            case 13:
               var10000 = this.buffs[var1];
               var7 = 1;
               var10000[1] = (short)(super.primaryStates[var7] * ResourceManager.gameDatabase[6][var1][3] / 100);
               var4 = this.buffs[var1][1];
               var7 = 1;
               this.setDisplayHp(super.secondaryStates[var7]);
               this.heal(this.buffs[var1][1]);
               this.clearAllDebuffs();
               break;
            case 14:
               this.clearAllDebuffs();
               break;
            case 15:
               this.buffs[var1][1] = (short)(var2 * ResourceManager.gameDatabase[6][var1][3]);
         }

         this.addStatusEffect((int)0, (byte)var1);
         this.buffs[var1][0] = ResourceManager.gameDatabase[6][var1][2];
         this.buffs[var1][4] = 1;
         return var4;
      }
   }

   public final void removeBuff(int var1) {
      this.buffs[var1][4] = 0;

      for(byte var5 = 2; var5 <= 4; ++var5) {
         short var4 = super.primaryStates[var5];
         super.secondaryStates[var5] = var4;
      }

   }

   public final int tickBuff(int var1) {
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
            var7 = (short)(super.primaryStates[var5] - this.buffs[var1][1]);
            var5 = 3;
            super.secondaryStates[var5] = var7;
            break;
         case 2:
            var5 = 3;
            var7 = (short)(super.primaryStates[var5] + this.buffs[var1][1]);
            var5 = 3;
            super.secondaryStates[var5] = var7;
            break;
         case 3:
            var2 = this.buffs[var1][1];
            var5 = 1;
            this.setDisplayHp(super.secondaryStates[var5]);
            this.heal(this.buffs[var1][1]);
            break;
         case 4:
            var5 = 3;
            var7 = (short)(super.secondaryStates[var5] + this.buffs[var1][1]);
            var5 = 3;
            super.secondaryStates[var5] = var7;
            break;
         case 7:
            var5 = 4;
            var7 = (short)(super.primaryStates[var5] + this.buffs[var1][1]);
            var5 = 4;
            super.secondaryStates[var5] = var7;
            break;
         case 9:
            var5 = 4;
            var7 = (short)(super.primaryStates[var5] + this.buffs[var1][1]);
            var5 = 4;
            super.secondaryStates[var5] = var7;
            var5 = 3;
            var7 = (short)(super.primaryStates[var5] - this.buffs[var1][2]);
            var5 = 3;
            super.secondaryStates[var5] = var7;
            break;
         case 10:
            var5 = 2;
            var7 = (short)(super.primaryStates[var5] + this.buffs[var1][1]);
            var5 = 2;
            super.secondaryStates[var5] = var7;
            break;
         case 11:
            short[] var10001 = this.buffs[11];
            PokemonEntity var6 = BattleSystemManager.B().C29_f402[var10001[1]];
            int var3 = 0;

            while(true) {
               var5 = 0;
               if (var3 >= var6.statusEffectCounts[var5]) {
                  var6.clearAllBuffs();
                  return var2;
               }

               byte var8 = var6.activeStatusEffects[0][var3];
               short var10002 = var6.buffs[var6.activeStatusEffects[0][var3]][1];
               short[] var10004 = this.buffs[11];
               this.applyBuff((byte)var8, var10002, BattleSystemManager.B().C29_f402[var10004[1]].skillParams[var3]);
               ++var3;
            }
         case 12:
            this.skillParams[12] = 2;
            break;
         case 13:
            var2 = this.buffs[var1][1];
            var5 = 1;
            this.setDisplayHp(super.secondaryStates[var5]);
            this.heal(this.buffs[var1][1]);
      }

      return var2;
   }

   public final boolean hasDebuff(int debuffId) {
      return this.debuffs[debuffId][4] == 1;
   }

   public final void clearAllDebuffs() {
      int var1;
      for(var1 = 0; var1 < 11; ++var1) {
         if (this.debuffs[var1][4] == 1) {
            this.removeDebuff(var1);
         }
      }

      for(var1 = 0; var1 < 3; ++var1) {
         this.removeStatusEffect(1, var1);
      }

   }

   public final void clearAllBuffs() {
      int var1;
      for(var1 = 0; var1 < 16; ++var1) {
         if (this.buffs[var1][4] == 1) {
            this.removeBuff(var1);
         }
      }

      for(var1 = 0; var1 < 3; ++var1) {
         this.removeStatusEffect(0, var1);
      }

   }

   private void removeDebuff(int var1) {
      this.debuffs[var1][4] = 0;

      for(byte var5 = 2; var5 <= 4; ++var5) {
         short var4 = super.primaryStates[var5];
         super.secondaryStates[var5] = var4;
      }

   }

   private void removeStatusEffect(int var1, int var2) {
      this.activeStatusEffects[var1][var2] = -1;
      if (this.statusEffectCounts[var1] > 0) {
         --this.statusEffectCounts[var1];
      }

   }

   public final void processDebuffDamage(int var1) {
      short var2;
      byte var3;
      short var4;
      switch(var1) {
         case 0:
            var2 = this.debuffs[0][1];
            var4 = ResourceManager.gameDatabase[1][this.debuffs[0][3]][8];
            this.takeDamage(var2 / var4);
            if (!this.isAlive()) {
               this.setAnimationState((byte)3, true);
            }

            return;
         case 1:
            return;
         case 2:
            return;
         case 3:
            if (this.debuffs[var1][0] <= 1) {
               var2 = this.debuffs[var1][1];
               var4 = ResourceManager.gameDatabase[1][this.debuffs[var1][3]][8];
               this.takeDamage(var2 * var4 / 100);
               if (!this.isAlive()) {
                  this.setAnimationState((byte)3, true);
               }

               return;
            }
            break;
         case 4:
            return;
         case 5:
            var3 = 4;
            var4 = (short)(super.primaryStates[var3] - this.debuffs[var1][1]);
            var3 = 4;
            super.secondaryStates[var3] = var4;
            return;
         case 6:
            return;
         case 7:
            var3 = 3;
            var4 = (short)(super.primaryStates[var3] - this.debuffs[var1][1]);
            var3 = 3;
            super.secondaryStates[var3] = var4;
         case 8:
         case 9:
         case 10:
      }

   }

   public final void tickDebuff(int var1, int var2) {
      if (this.hasDebuff(var1)) {
         if (this.debuffs[var1][0] > 0) {
            --this.debuffs[var1][0];
         }

         if (this.debuffs[var1][0] <= 0) {
            this.removeDebuff(var1);
            this.removeStatusEffect(1, var2);
         }
      }

   }

   public final void tickBuffDuration(int var1, int var2) {
      if (this.hasBuff(var1)) {
         if (this.buffs[var1][0] > 0) {
            --this.buffs[var1][0];
         }

         if (this.buffs[var1][0] <= 0) {
            this.removeBuff(var1);
            this.removeStatusEffect(0, var2);
         }
      }

   }

   private void addStatusEffect(int var1, byte var2) {
      int var3;
      for(var3 = 0; var3 < 3; ++var3) {
         if (this.activeStatusEffects[var1][var3] == -1) {
            int var4;
            for(var4 = 0; var4 < 3; ++var4) {
               if (this.activeStatusEffects[var1][var4] == var2) {
                  return;
               }
            }

            if (var4 >= 3) {
               this.activeStatusEffects[var1][var3] = var2;
               if (this.statusEffectCounts[var1] < 3) {
                  ++this.statusEffectCounts[var1];
               }
               break;
            }
         }
      }

      if (var3 >= 3) {
         this.activeStatusEffects[var1][0] = var2;
      }

   }

   public final byte getStatusEffectCount(int type) {
      return this.statusEffectCounts[type];
   }

   public final boolean hasEquippedItem(byte itemId) {
      return super.primaryStates[5] == itemId;
   }

   public final int getSkillCount() {
      return this.skillCount;
   }

   public final int[] getLearnableSkills() {
      int[] var1 = null;
      Vector var2 = new Vector();
      short var4 = ResourceManager.gameDatabase[0][this.speciesId][18];
      short var5 = ResourceManager.gameDatabase[0][this.speciesId][1];
      int var6 = this.getMaxSkillSlots();

      int var7;
      for(var7 = var5 * 10; var7 < var5 * 10 + 10; ++var7) {
         short var10000 = ResourceManager.gameDatabase[1][var7][4];
         boolean var3 = false;
         if (var10000 <= ResourceManager.gameDatabase[8][var4][var6]) {
            int var8;
            for(var8 = 0; var8 < this.skills.length && var7 != this.skills[var8]; ++var8) {
            }

            if (var8 >= this.skills.length) {
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

   public final void learnSkill(byte skillId) {
      for(int i = 0; i < this.skills.length; ++i) {
         if (this.skills[i] == -1) {
            this.skills[i] = skillId;
            ++this.skillCount;
            this.skillPP[i] = ResourceManager.gameDatabase[1][skillId][5];
            return;
         }
      }
   }

   public final void tryLearnNewSkill() {
      short var1 = ResourceManager.gameDatabase[0][this.speciesId][1];
      int var3;
      int var6;
      if (this.level <= 5) {
         var6 = var1 * 10;
         boolean var7 = true;

         for(var3 = 0; var3 < this.skills.length; ++var3) {
            if (var6 == this.skills[var3]) {
               var7 = false;
               break;
            }
         }

         if (var7) {
            this.learnSkill((byte)var6);
         }

      } else if (this.skillCount < this.getMaxSkillSlots() + 1) {
         int[] var2;
         var3 = (var2 = this.getLearnableSkills()).length;

         for(int var4 = 0; var4 < var2.length; ++var4) {
            var6 = GameUtils.getRandomInt(var3);
            this.learnSkill((byte)var2[var6]);
            if (this.skillCount >= this.level / 10 + 1) {
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

   public final boolean hasSkillPP(int skillIndex) {
      if (skillIndex == -1) {
         return false;
      } else {
         return this.skillPP[skillIndex] > 0;
      }
   }

   public final void useSkill(byte var1, PokemonEntity var2) {
      super.followTarget = var2;
      this.currentSkillId = var1;

      for(int var4 = 0; var4 < this.skills.length; ++var4) {
         if (this.skills[var4] == var1) {
            --this.skillPP[var4];
            if (this.hasBuff(12) && this.skillParams[12] == 1) {
               ++this.skillPP[var4];
            }

            if (this.hasBuff(8)) {
               --this.skillPP[var4];
            }
         }
      }

   }

   public final void loadSkillData(int[] var1) {
      this.skillCount = (byte)var1[0];

      for(int var2 = 0; var2 < var1[0]; ++var2) {
         this.skills[var2] = (byte)var1[var2 + 1];
         this.skillPP[var2] = (short)var1[var1[0] + 1 + var2];
      }

   }

   public final byte getSkillAt(int var1) {
      return var1 <= this.skills.length - 1 && var1 >= 0 ? this.skills[var1] : -1;
   }

   public static short getSkillProperty(byte var0, byte var1) {
      return ResourceManager.gameDatabase[1][var0][var1];
   }

   private int getMaxSkillSlots() {
      int[] var1 = new int[]{5, 10, 20, 30, 40};
      int var2 = 0;

      for(int var3 = 0; var3 < var1.length; ++var3) {
         if (this.level >= var1[var3]) {
            var2 = var3;
         }
      }

      return var2;
   }

   public final void setCurrentSkill(byte skillId) {
      this.currentSkillId = skillId;
   }

   public final byte getCurrentSkill() {
      return this.currentSkillId;
   }

   public final void saveCurrentStates() {
      this.applyBadgeBonuses();
      super.saveCurrentStates();
      byte var2 = 1;
      this.setDisplayHp(super.primaryStates[var2]);
   }

   public final void fullRestore() {
      for(int i = 0; i < this.skills.length; ++i) {
         if (this.skills[i] != -1) {
            this.skillPP[i] = ResourceManager.gameDatabase[1][this.skills[i]][5];
         }
      }

      this.saveCurrentStates();
      this.activate();
   }

   public final void checkEvolution() {
      if (WorldGameSession.pendingEvolutions == null) {
         WorldGameSession.pendingEvolutions = new Vector();
      }

      short var1;
      if ((var1 = ResourceManager.getDatabaseValue((byte)0, (short)this.speciesId, (byte)19)) != -1) {
         short var2 = ResourceManager.getDatabaseValue((byte)0, (short)this.speciesId, (byte)21);
         int var3 = ResourceManager.getDatabaseValue((byte)0, (short)this.speciesId, (byte)20) + 12;
         boolean var4 = false;
         if (!WorldGameSession.isTutorialActive && this.getEvolutionTier() > 0 && this.level >= EVOLUTION_LEVEL_REQUIREMENTS[ResourceManager.getDatabaseValue((byte)0, var1, (byte)2) - 1] && PlayerCharacter.getInstance().getItemCount((int)var3, (byte)2) >= var2) {
            var4 = true;
         } else if (this.getEvolutionTier() > 0 && this.level >= EVOLUTION_LEVEL_REQUIREMENTS[ResourceManager.getDatabaseValue((byte)0, var1, (byte)2) - 1]) {
            var4 = true;
         }

         if (var4) {
            int[] var6 = new int[]{this.speciesId, ResourceManager.gameDatabase[0][this.speciesId][0]};
            WorldGameSession.pendingEvolutions.addElement(var6);
            WorldGameSession.tutorialTargetPokemon[0] = (byte)this.level;
            WorldGameSession.tutorialTargetPokemon[1] = (byte)this.speciesId;
            WorldGameSession.evolutionProcessState = 0;
         }

      }
   }

   public final void applyNature(byte var1) {
      this.nature = var1;
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

   public final boolean isCaptured() {
      return this.isCaptured;
   }

   public final void setCaptured(boolean captured) {
      this.isCaptured = captured;
   }

   public final int getDatabaseValue(byte index) {
      return ResourceManager.gameDatabase[0][this.speciesId][index];
   }

   public static short calculateStat(int var0, int var1, int var2, int var3) {
      switch(var3) {
         case 1:
            return (short)((ResourceManager.gameDatabase[0][var0][5] + ResourceManager.gameDatabase[0][var0][6] * var1 + ResourceManager.gameDatabase[0][var0][7]) * QUALITY_MULTIPLIERS[var2 - 1] / 100);
         case 2:
            return (short)((ResourceManager.gameDatabase[0][var0][8] + ResourceManager.gameDatabase[0][var0][9] * var1 + ResourceManager.gameDatabase[0][var0][10]) * QUALITY_MULTIPLIERS[var2 - 1] / 100);
         case 3:
            return (short)((ResourceManager.gameDatabase[0][var0][11] + ResourceManager.gameDatabase[0][var0][12] * var1 / 10 + ResourceManager.gameDatabase[0][var0][13]) * QUALITY_MULTIPLIERS[var2 - 1] / 100);
         case 4:
            return (short)((ResourceManager.gameDatabase[0][var0][14] + ResourceManager.gameDatabase[0][var0][15] * var1 / 10 + ResourceManager.gameDatabase[0][var0][16]) * QUALITY_MULTIPLIERS[var2 - 1] / 100);
         default:
            return 0;
      }
   }

   public final int getHpPercent() {
      return super.secondaryStates[1] * 100 / super.primaryStates[1];
   }

   public final int getDisplayHpPercent() {
      int var10000 = this.displayHp * 100;
      byte var2 = 1;
      return var10000 / super.primaryStates[var2];
   }

   public final int getDisplayHp() {
      return this.displayHp;
   }

   public final void setDisplayHp(int var1) {
      byte var3 = 1;
      if (var1 >= super.primaryStates[var3]) {
         var3 = 1;
         this.displayHp = super.primaryStates[var3];
      } else {
         this.displayHp = var1;
      }
   }

   public final int getExpPercent() {
      return this.currentExp * 100 / this.getExpToNextLevel();
   }

   public final int calculateExpPercent(int var1) {
      return var1 * 100 / this.getExpToNextLevel();
   }

   public static int calculateExpPercentStatic(short var0, short var1) {
      int var2;
      if (var1 >= 50) {
         var2 = 37300;
      } else {
         var2 = var1 * 15 * var1 - 200;
      }

      return var0 * 100 / var2;
   }

   public final int[] toSaveData() {
      int[] var1;
      (var1 = new int[9 + (this.skillCount << 1) + 1])[0] = this.speciesId;
      var1[1] = this.level;
      byte var2 = 5;
      var1[2] = super.primaryStates[var2];
      var2 = 6;
      var1[3] = super.secondaryStates[var2];
      var2 = 0;
      var1[4] = super.primaryStates[var2];
      var1[5] = this.nature;
      var2 = 1;
      var1[6] = super.secondaryStates[var2];
      var1[7] = this.currentExp;
      var1[8] = this.friendship;
      var1[9] = this.skillCount;

      for(int var4 = 0; var4 < this.skillCount; ++var4) {
         var1[var4 + 10] = this.skills[var4];
         var1[10 + var1[9] + var4] = this.skillPP[var4];
      }

      return var1;
   }

   public final int[] getSkillSaveData() {
      int[] var1;
      (var1 = new int[(this.skillCount << 1) + 1])[0] = this.skillCount;

      for(int var2 = 0; var2 < this.skillCount; ++var2) {
         var1[var2 + 1] = this.skills[var2];
         var1[var1[0] + var2 + 1] = this.skillPP[var2];
      }

      return var1;
   }

   public final int getEvolutionTier() {
      if (this.getDatabaseValue((byte)19) == -1) {
         return 0;
      } else if (ResourceManager.gameDatabase[0][this.getDatabaseValue((byte)19)][2] == 1) {
         return 1;
      } else if (ResourceManager.gameDatabase[0][this.getDatabaseValue((byte)19)][2] == 2) {
         return 1;
      } else {
         return ResourceManager.gameDatabase[0][this.getDatabaseValue((byte)19)][2] == 3 ? 2 : 0;
      }
   }

   public final void useItem(int var1) {
      short var3;
      byte var5;
      short var6;
      switch(ResourceManager.gameDatabase[4][var1][5]) {
         case 1:
            var5 = 1;
            var6 = (short)(super.primaryStates[var5] * ResourceManager.gameDatabase[4][var1][6] / 100 + ResourceManager.gameDatabase[4][var1][7]);
            var5 = 1;
            this.setDisplayHp(super.secondaryStates[var5] + var6);
            this.heal(var6);
            break;
         case 2:
            var3 = ResourceManager.gameDatabase[4][var1][6];
            this.restoreAllSkillPP(var3);
            break;
         case 3:
            var5 = 1;
            var6 = (short)(super.primaryStates[var5] * ResourceManager.gameDatabase[4][var1][6] / 100 + ResourceManager.gameDatabase[4][var1][7]);
            var3 = ResourceManager.gameDatabase[4][var1][8];
            var5 = 1;
            this.setDisplayHp(super.secondaryStates[var5] + var6);
            this.heal(var6);
            this.restoreAllSkillPP(var3);
            break;
         case 4:
            this.activate();
            var5 = 1;
            var6 = (short)(super.primaryStates[var5] * ResourceManager.gameDatabase[4][var1][6] / 100 + ResourceManager.gameDatabase[4][var1][7]);
            var3 = ResourceManager.gameDatabase[4][var1][8];
            this.setDisplayHp(var6);
            this.heal(var6);
            this.restoreAllSkillPP(var3);
            break;
         case 5:
            this.clearAllDebuffs();
            break;
         case 6:
            byte var2 = 2;
            var5 = 6;
            super.secondaryStates[var5] = var2;
      }

      PlayerCharacter.getInstance().removeItem(var1, 1, (byte)0);
   }

   public final int canUseItem(int var1) {
      if (!this.isAlive() && ResourceManager.gameDatabase[4][var1][5] != 4) {
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
               var4 = this.skillCount;

               for(int var5 = 0; var5 < var4; ++var5) {
                  if (this.skillPP[var5] < getSkillProperty((byte)this.skills[var5], (byte)5)) {
                     return -1;
                  }
               }

               return 3;
            case 3:
               var4 = -1;
               var3 = 1;
               var10000 = super.primaryStates[var3];
               var3 = 1;
               if (var10000 == super.secondaryStates[var3] || !this.isAlive()) {
                  var4 = 2;
               }

               byte var2 = this.skillCount;

               for(int var6 = 0; var6 < var2; ++var6) {
                  if (this.skillPP[var6] < getSkillProperty((byte)this.skills[var6], (byte)5)) {
                     return -1;
                  }
               }

               if (var4 == 2) {
                  return 7;
               }
               break;
            case 4:
               if (this.isAlive()) {
                  return 1;
               }
               break;
            case 5:
               for(var1 = 0; var1 < this.debuffs.length; ++var1) {
                  if (this.hasDebuff(var1)) {
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

   public final boolean isAlive() {
      return super.secondaryStates[1] > 0;
   }

   public final byte getTypeAdvantage(PokemonEntity var1) {
      short var2;
      short var3;
      boolean var5;
      boolean var6;
      label130: {
         var2 = ResourceManager.gameDatabase[0][this.speciesId][1];
         var3 = ResourceManager.gameDatabase[0][var1.speciesId][1];
         short var4 = ResourceManager.gameDatabase[0][this.speciesId][22];
         short var7 = ResourceManager.gameDatabase[0][var1.speciesId][22];
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

   public final int[] calculateDamageResult(PokemonEntity var1) {
      byte var2 = 0;
      byte var3 = 5;
      int var4 = this.calculateBaseDamage();
      short var5 = ResourceManager.gameDatabase[0][this.speciesId][1];
      if (this.spriteId == PlayerCharacter.getInstance().categoryOffsets[var5] + PlayerCharacter.getInstance().categorySizes[var5] - 1) {
         var3 = 30;
      }

      byte var9 = 4;
      int var10 = var3 + super.secondaryStates[var9] / 2;
      if (this.hasEquippedItem((byte)4)) {
         var10 += ResourceManager.gameDatabase[3][4][5];
      }

      if (GameUtils.getRandomInt(100) <= var10) {
         var4 = var4 * 3 / 2;
         var2 = 1;
      }

      byte var12 = (byte) ResourceManager.gameDatabase[1][this.currentSkillId][7];
      short var11 = -1;
      int var6 = var4;
      switch(this.currentSkillId) {
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
            var4 = var4 * ResourceManager.gameDatabase[1][this.currentSkillId][3] / 100;
            break;
         case 1:
         case 7:
            var4 = var4 * ResourceManager.gameDatabase[1][this.currentSkillId][3] / 100 + var4 / ResourceManager.gameDatabase[1][this.currentSkillId][8];
            break;
         case 2:
         case 8:
         case 22:
         case 28:
         case 41:
         case 47:
            var4 = var4 * ResourceManager.gameDatabase[1][this.currentSkillId][3] / 100;
            var11 = ResourceManager.gameDatabase[1][this.currentSkillId][8];
            break;
         case 3:
         case 9:
            if (var1.hasDebuff(0)) {
               var4 = var4 * ResourceManager.gameDatabase[1][this.currentSkillId][8] / 100;
            } else {
               var4 = var4 * ResourceManager.gameDatabase[1][this.currentSkillId][3] / 100;
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
            if (var1.hasDebuff(1)) {
               var4 = var4 * ResourceManager.gameDatabase[1][this.currentSkillId][8] / 100;
            } else {
               var4 = var4 * ResourceManager.gameDatabase[1][this.currentSkillId][3] / 100;
            }
            break;
         case 43:
         case 49:
            var4 = var4 * ResourceManager.gameDatabase[1][this.currentSkillId][3] / 100;
            var1.clearAllBuffs();
            break;
         case 53:
         case 59:
            var9 = 1;
            int var10000 = super.secondaryStates[var9] * 100;
            var9 = 1;
            int var7 = var10000 / super.primaryStates[var9];
            var4 = var4 * (ResourceManager.gameDatabase[1][this.currentSkillId][8] - var7) / 100;
      }

      if (var4 <= 0) {
         var6 = 1;
      }

      short var10002 = (short)var6;
      short var15 = (short)this.currentSkillId;
      short var14 = var10002;
      byte var17;
      if (var12 == -1) {
         var17 = -1;
      } else {
         label154: {
            if (var1.hasEquippedItem((byte)3)) {
               if (GameUtils.getRandomInt(100) > var11 * (100 - ResourceManager.gameDatabase[3][3][5]) / 100) {
                  var17 = -1;
                  break label154;
               }
            } else {
               if (var1.hasBuff(14)) {
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
                  var1.debuffs[var12][1] = var14;
               case 1:
               case 2:
               case 8:
               case 9:
               case 10:
               default:
                  break;
               case 3:
                  var1.debuffs[var12][1] = var14;
                  break;
               case 4:
                  var1.debuffs[var12][1] = ResourceManager.gameDatabase[1][var15][8];
                  break;
               case 5:
                  var18 = var1.debuffs[var12];
                  var9 = 4;
                  var18[1] = (short)(var1.primaryStates[var9] * ResourceManager.gameDatabase[1][var15][8] / 100);
                  var9 = 4;
                  var14 = (short)(var1.primaryStates[var9] - var1.debuffs[var12][1]);
                  var9 = 4;
                  var1.secondaryStates[var9] = var14;
                  break;
               case 6:
                  var1.debuffs[var12][1] = ResourceManager.gameDatabase[1][var15][8];
                  break;
               case 7:
                  var18 = var1.debuffs[var12];
                  var9 = 3;
                  var18[1] = (short)(var1.primaryStates[var9] * ResourceManager.gameDatabase[1][var15][8] / 100);
                  var9 = 3;
                  var14 = (short)(var1.primaryStates[var9] - var1.debuffs[var12][1]);
                  var9 = 3;
                  var1.secondaryStates[var9] = var14;
            }

            var1.addStatusEffect((int)1, (byte)var12);
            if (var1.battlePosition == 0 && PlayerCharacter.getInstance().getBadgeState((byte)6, (byte)0) == 2 && PlayerCharacter.getInstance().getBadgeState((byte)6, (byte)1) == 1) {
               var1.debuffs[var12][0] = (short)(ResourceManager.gameDatabase[7][var12][2] / 2);
            } else {
               var1.debuffs[var12][0] = ResourceManager.gameDatabase[7][var12][2];
            }

            var1.debuffs[var12][3] = var15;
            var1.debuffs[var12][4] = 1;
            var17 = var12;
         }
      }

      byte var16 = var17;
      if (this.hasBuff(0) && this.buffs[0][0] == 0) {
         var4 += this.buffs[0][2];
      }

      if (this.hasBuff(1)) {
         var4 += var4 * this.buffs[1][2] / 100;
      }

      if (this.hasDebuff(6)) {
         var4 -= var4 * this.debuffs[6][1] / 100;
      }

      if (var1.hasBuff(6) && GameUtils.getRandomInt(100) <= this.buffs[6][1]) {
         var4 = var4 * this.buffs[6][2] / 100;
      }

      if (this.hasBuff(8)) {
         var4 += var4 * this.buffs[8][1] / 100;
      }

      if (this.battlePosition == 0 && PlayerCharacter.getInstance().getBadgeState((byte)3, (byte)0) == 2 && PlayerCharacter.getInstance().getBadgeState((byte)3, (byte)1) == 1 && WorldGameSession.currentTileType == 2) {
         var4 += var4 * ResourceManager.gameDatabase[2][3][5] / 100;
      }

      if (this.battlePosition == 0 && PlayerCharacter.getInstance().getBadgeState((byte)6, (byte)0) == 2) {
         var4 += var4 * ResourceManager.gameDatabase[2][6][5] / 100;
      }

      if (this.getTypeAdvantage(var1) == 0) {
         var4 *= 3;
      } else if (this.getTypeAdvantage(var1) == 1) {
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

      if (var1.hasBuff(5) && GameUtils.getRandomInt(100) <= var1.buffs[5][1]) {
         this.skillParams[5] = (short)var4;
         return new int[]{var4, var2, var16};
      } else {
         return new int[]{var4, var2, var16};
      }
   }

   public final String getTypeName() {
      String[] var1 = new String[]{"Mộc hệ", "Thổ hệ", "Thủy hệ", "Hỏa hệ", "Quỷ hệ", "Phong hệ", "Điện hệ"};
      short var2 = ResourceManager.gameDatabase[0][this.speciesId][1];
      return var1[var2];
   }

   public static String getTypeNameById(int speciesId) {
      String[] typeNames = new String[]{"Mộc hệ", "Thổ hệ", "Thủy hệ", "Hỏa hệ", "Quỷ hệ", "Phong hệ", "Điện hệ"};
      short typeIndex = ResourceManager.gameDatabase[0][speciesId][1];
      return typeNames[typeIndex];
   }
}