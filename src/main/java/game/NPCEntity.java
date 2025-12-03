package game;

import engine.GameUtils;
import engine.entity.GameObject;
import engine.entity.TileMapRenderer;

import javax.microedition.lcdui.Graphics;

public final class NPCEntity extends GameObject {
    public byte npcType;
    public boolean isFacingPlayer;
    public byte npcSubType;
    public byte auraAnimationId;
    public short npcRotationX;
    public short npcRotationY;
    private int movementState;
    private int animationCounter;
    private int animationDuration;
    private int healthBarWidth;
    public byte dialogueState;
    public int dialogueProgress;
    public int dialogueTimeout;
    public byte followDistance;
    private short patrolPointX;
    private short patrolPointY;
    private short patrolRadius;
    public short triggerType;
    public short triggerAction;
    public short triggerValue;
    private boolean isInteractive;
    private byte[] directionMap = new byte[]{2, 3, 0, 1};
    private byte interactionState = 0;
    public GameObject auraObject;
    public GameObject effectObject;
    public short npcId = -1;
    private short[] statValues = new short[]{8, 9, 2, 96, 320, 0};

    public final void initializeNPC(short[] var1, int var2) {
        this.npcId = (short) var2;
        this.npcType = (byte) var1[0];
        this.sprite.loadSpriteSet(var1[1], false);
        this.sprite.applyColorEffects();
        this.npcSubType = (byte) var1[6];
        if (this.npcType == 0 && (this.npcSubType == 1 || this.npcSubType == 18)) {
            byte var4 = (byte) (var1[2] % 3);
            super.currentDirection = var4;
        }

        this.setDirection((byte) var1[2]);
        this.worldX = var1[3];
        this.worldY = var1[4];
        if (var1[5] == 1) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }

        switch (this.npcType) {
            case 0:
                this.layer = (byte) var1[7];
                this.auraAnimationId = (byte) var1[8];
                if (this.auraAnimationId != 0 && this.auraObject == null && this.isVisible()) {
                    this.auraObject = new GameObject();
                    this.auraObject.loadSpriteSet(259, false);
                    this.auraObject.sprite.applyColorEffects();
                    this.auraObject.setAnimation(this.auraAnimationId, (byte) -1, true);
                    this.auraObject.setWorldPosition(this.worldX, this.worldY - 40);
                    this.auraObject.followTarget = this;
                }

                this.activateAuraObject();
                this.checkPatrolState();
                if (var1[9] == 0) {
                    this.isFacingPlayer = false;
                } else {
                    this.isFacingPlayer = true;
                }

                this.npcRotationX = var1[11];
                this.npcRotationY = var1[12];
                this.movementState = 0;
                this.animationDuration = GameUtils.getRandomInRange(20, 40);
                this.dialogueState = 0;
                if (this.npcSubType == 12) {
                    this.currentDirection = 0;
                } else if (this.npcSubType == 13) {
                    this.currentDirection = 1;
                }

                if (this.npcSubType == 3) {
                    if (this.facingDirection == 4) {
                        this.currentDirection = 1;
                    }
                } else if (this.npcSubType == 2) {
                    if (this.facingDirection == 5) {
                        this.currentDirection = 2;
                    } else if (this.facingDirection == 3) {
                        this.currentDirection = 0;
                    }
                }

                if (this.npcSubType == 1 && this.sprite.spriteSetId != 226 || this.npcSubType == 2 || this.npcSubType == 3 || this.npcSubType == 17) {
                    if (this.attachedObject == null) {
                        this.attachedObject = new GameObject();
                        this.attachedObject.loadSpriteSet(337, false);
                    }

                    this.attachedObject.setWorldPosition(this.worldX, this.worldY);
                    if (this.sprite.spriteSetId == 4) {
                        this.attachedObject.setAnimation((byte) 0, (byte) 0, this.isInteractive);
                    } else {
                        this.attachedObject.setAnimation((byte) 1, (byte) 0, this.isInteractive);
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
                    WorldGameSession.getInstance().activeNpcList.addElement(this);
                }

                if (this.npcSubType == 3) {
                    this.isFacingPlayer = true;
                }

                this.followDistance = (byte) var1[7];
                this.patrolPointX = var1[8];
                this.patrolPointY = var1[9];
                this.patrolRadius = var1[10];
                break;
            case 2:
                if (var1[7] == 0) {
                    this.isInteractive = false;
                } else {
                    this.isInteractive = true;
                }
                break;
            case 3:
                this.layer = 1;
                this.triggerType = var1[7];
                this.triggerAction = var1[8];
                switch (this.triggerAction) {
                    case 9:
                        this.triggerAction = 1;
                        break;
                    case 10:
                        this.triggerAction = 0;
                        break;
                    case 11:
                        this.triggerAction = 2;
                        break;
                    case 12:
                        this.triggerAction = 3;
                }

                this.triggerValue = var1[9];
                this.isFacingPlayer = true;
        }

        this.primaryStates = new short[3];
        this.secondaryStates = new short[3];
    }

    public final void resetSprite() {
        if (this.sprite != null) {
            this.sprite.resetWithColorEffects();
        }

    }

    public final void setDirection(byte var1) {
        switch (this.npcType) {
            case 0:
                if (this.npcSubType == 8) {
                    this.setAnimation((byte) 0, (byte) -1, false);
                    this.movementState = 0;
                    this.facingDirection = var1;
                    return;
                }

                if (this.npcSubType != 1 && this.npcSubType != 18) {
                    this.setAnimation(var1, (byte) -1, false);
                    this.facingDirection = var1;
                    WorldGameSession.getInstance().setNPCState(this.npcId, 0, this.facingDirection, true);
                    return;
                }

                this.facingDirection = (byte) (var1 / 3);
                if (this.facingDirection == 0) {
                    if (this.currentDirection == 3) {
                        this.setAnimation((byte) 1, (byte) -1, false);
                        return;
                    }

                    this.setAnimation(this.currentDirection, (byte) -1, false);
                    return;
                }

                if (this.facingDirection == 1) {
                    if (this.currentDirection == 3) {
                        this.setAnimation((byte) (this.facingDirection * 3 + 1), (byte) -1, false);
                        return;
                    }

                    this.setAnimation((byte) (this.facingDirection * 3 + this.currentDirection), (byte) -1, false);
                    return;
                }
                break;
            case 1:
                if (this.npcSubType == 0) {
                    switch (var1) {
                        case 0:
                            this.setAnimation(var1, (byte) -1, false);
                            break;
                        case 1:
                            this.setAnimation(var1, (byte) -2, false);
                            break;
                        case 2:
                            this.setAnimation(var1, (byte) -1, false);
                            break;
                        case 3:
                            this.setAnimation(var1, (byte) -2, false);
                    }
                } else {
                    this.setAnimation(var1, (byte) -1, false);
                    if (this.npcSubType == 3) {
                        WorldGameSession.getInstance().setNPCState(this.npcId, 0, var1, true);
                    }
                }

                this.facingDirection = var1;
                return;
            case 2:
                this.facingDirection = var1;
                return;
            case 3:
                this.setAnimation(var1, (byte) -2, false);
                this.facingDirection = var1;
                WorldGameSession.getInstance().setNPCState(this.npcId, 0, this.facingDirection, true);
        }

    }

    public final void updateNPCBehavior() {
        switch (this.npcType) {
            case 0:
                this.updateNPCSubBehavior();
                break;
            case 1:
                if (this.npcSubType == 0) {
                    if (this.facingDirection == 0 && GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.getInstance().worldX, PlayerCharacter.getInstance().worldY, this.worldX, this.worldY, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
                        this.setDirection((byte) 1);
                    } else if (this.facingDirection == 2 && !GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.getInstance().worldX, PlayerCharacter.getInstance().worldY, this.worldX, this.worldY, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
                        this.setDirection((byte) 3);
                    } else if (this.facingDirection == 1 && this.sprite.isAtLastFrame()) {
                        this.setDirection((byte) 2);
                    } else if (this.facingDirection == 3 && this.sprite.isAtLastFrame()) {
                        this.setDirection((byte) 0);
                    }
                }

                byte var10001;
                if (this.npcSubType == 0 && this.sprite.isAtLastFrame() || this.npcSubType == 1 || this.npcSubType == 3 && this.getFacingDirection() == 2) {
                    if (this.sprite.spriteSetId == 320 && !this.isVisible()) {
                        return;
                    }

                    var10001 = this.directionMap[this.followDistance];
                    if ((PlayerCharacter.getInstance().visualState == var10001 && this.sprite.spriteSetId != 320 && this.sprite.spriteSetId != 310 || this.sprite.spriteSetId == 320 || this.sprite.spriteSetId == 310) && GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.getInstance().worldX, PlayerCharacter.getInstance().worldY, this.worldX, this.worldY, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameTriggers())) {
                        WorldGameSession.getInstance().currentRegionId = this.patrolPointX;
                        WorldGameSession.getInstance().currentAreaId = this.patrolPointY;
                        WorldGameSession.getInstance().lastInteractedNpcId = this.patrolRadius;
                        GameScreenManager.getInstance().changeState((byte) 9);
                    }
                } else if (this.npcSubType == 2) {
                    var10001 = this.directionMap[this.followDistance];
                    if ((PlayerCharacter.getInstance().visualState == var10001 && this.sprite.spriteSetId != 320 || this.sprite.spriteSetId == 320) && GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.getInstance().worldX, PlayerCharacter.getInstance().worldY, this.worldX, this.worldY, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameTriggers())) {
                        for (int var1 = 0; var1 < this.statValues.length / 6; ++var1) {
                            if (this.statValues[var1 * 6] == this.npcId && this.statValues[var1 * 6 + 1] == WorldGameSession.getInstance().currentRegionId && this.statValues[var1 * 6 + 2] == WorldGameSession.getInstance().currentAreaId) {
                                WorldGameSession.getInstance().spawnPositionX = this.statValues[var1 * 6 + 3];
                                WorldGameSession.getInstance().spawnPositionY = this.statValues[var1 * 6 + 4];
                                WorldGameSession.playerDirection = (byte) this.statValues[var1 * 6 + 5];
                                break;
                            }
                        }

                        WorldGameSession.getInstance().currentRegionId = this.patrolPointX;
                        WorldGameSession.getInstance().currentAreaId = this.patrolPointY;
                        WorldGameSession.getInstance().lastInteractedNpcId = -1;
                        GameScreenManager.getInstance().changeState((byte) 9);
                    }
                } else if (this.npcSubType == 4 && PlayerCharacter.getInstance().getFacingDirection() != 9 && PlayerCharacter.getInstance().getFacingDirection() != 10 && GameUtils.checkCollisionBetweenShortArrays(PlayerCharacter.getInstance().worldX, PlayerCharacter.getInstance().worldY, this.worldX, this.worldY, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameTriggers())) {
                    PlayerCharacter.getInstance().setWorldPosition(this.worldX, this.worldY);
                    PlayerCharacter.getInstance().attachedObject.setWorldPosition(this.worldX, this.worldY);
                    PlayerCharacter.getInstance().setFacingState((byte) 9, (byte) this.currentDirection);
                    WorldGameSession.getInstance().lastInteractedNpcId = this.patrolRadius;
                }
            case 2:
            case 3:
        }

        this.updateInteractableState();
    }

    private void updateNPCSubBehavior() {
        int var1;
        byte var2;
        byte[] var4;
        switch (this.npcSubType) {
            case 1:
                if (this.facingDirection == 1) {
                    var2 = 0;
                    this.moveInDirection((int) super.secondaryStates[var2]);
                    if (WorldGameSession.getInstance().interactionMarker != null && WorldGameSession.getInstance().interactionMarker.followTarget.equals(this) && !PlayerCharacter.getInstance().checkCollisionWithNPC(this, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
                        WorldGameSession.getInstance().removeInteractionMarker();
                    }
                }

                if (WorldGameSession.getInstance().interactionMarker != null && WorldGameSession.getInstance().interactionMarker.followTarget.equals(this) && (!this.isVisible() || !PlayerCharacter.getInstance().checkCollisionWithNPC(this, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents()))) {
                    WorldGameSession.currentInteractNpcId = -1;
                    WorldGameSession.getInstance().removeInteractionMarker();
                }

                if (this.auraObject != null && this.isVisible() && this.auraObject.followTarget.equals(this) && !PlayerCharacter.getInstance().checkCollisionWithNPC(this, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
                    this.activateAuraObject();
                }

                if (QuestManager.questEffectObjects != null && QuestManager.questEffectObjects.size() > 0 && this.interactionState == 1 && !PlayerCharacter.getInstance().checkCollisionWithNPC(this, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents())) {
                    for (var1 = 0; var1 < QuestManager.questEffectObjects.size(); ++var1) {
                        if (((GameObject) QuestManager.questEffectObjects.elementAt(var1)).followTarget.equals(this)) {
                            ((GameObject) QuestManager.questEffectObjects.elementAt(var1)).activate();
                            return;
                        }
                    }
                }
                break;
            case 2:
                if (this.updateAnimationCounter()) {
                    var4 = new byte[]{0, 1, 2, 3, 5};
                    this.setDirection(var4[GameUtils.getRandomInt(5)]);
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
                    if (this.movementState >= 64) {
                        this.setDirection((byte) 0);
                        return;
                    }

                    this.moveInDirection((int) 4);
                    this.movementState += 4;
                    return;
                }

                if (this.facingDirection == 5) {
                    if (this.movementState <= 0) {
                        this.setDirection((byte) 2);
                        return;
                    }

                    this.moveInDirection((int) 4);
                    this.movementState -= 4;
                    return;
                }
                break;
            case 3:
                if (this.updateAnimationCounter()) {
                    var4 = new byte[]{0, 1, 2, 4};
                    this.setDirection(var4[GameUtils.getRandomInt(4)]);
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
                        if (this.movementState >= 64) {
                            this.setDirection((byte) 0);
                            return;
                        }

                        this.moveInDirection((int) 4);
                        this.movementState += 4;
                        return;
                    }

                    if (this.currentDirection == 3) {
                        if (this.movementState <= 0) {
                            this.setDirection((byte) 2);
                            return;
                        }

                        this.moveInDirection((int) 4);
                        this.movementState -= 4;
                        return;
                    }
                }
                break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 15:
                if (this.effectObject != null && this.isVisible() && this.effectObject.followTarget.equals(this) && (this.facingDirection != 0 || !PlayerCharacter.getInstance().checkCollisionWithNPC(this, PlayerCharacter.getInstance().sprite.getCurrentFrameEvents(), this.sprite.getCurrentFrameEvents()))) {
                    this.deactivateEffectObject();
                }

                if (this.facingDirection == 1 && this.sprite.isAtLastFrame()) {
                    this.setDirection((byte) 2);
                    if (this.npcSubType == 6 && this.npcSubType == 7) {
                        WorldGameSession.getInstance().setNPCState(this.npcId, 0, this.facingDirection, false);
                    } else {
                        WorldGameSession.getInstance().setNPCState(this.npcId, 0, this.facingDirection, true);
                    }

                    if ((this.npcSubType == 7 || this.npcSubType == 6) && (var1 = GameUtils.getRandomInt(2)) > 0) {
                        PlayerCharacter.getInstance().addGold(var1);
                        int[] var3 = new int[]{var1, super.worldX, super.worldY - 20, 0};
                        PlayerCharacter.getInstance().miscData.addElement(var3);
                    }

                    QuestManager.isQuestReady = false;
                    return;
                }
                break;
            case 8:
                if (this.facingDirection == 1) {
                    if (this.movementState < 2 && this.checkTileCollision(this.currentDirection, 8, (byte) 0)) {
                        ++this.movementState;
                        this.moveInDirection((int) 8);
                        this.syncPositionState();
                        return;
                    }

                    this.setDirection((byte) 0);
                    return;
                }
                break;
            case 9:
            case 10:
                if (this.dialogueState == 1) {
                    if ((this.sprite.spriteSetId == 302 || this.sprite.spriteSetId == 298) && this.checkTileCollision(this.currentDirection, 4, (byte) 1)) {
                        this.moveInDirection((int) 4);
                        ((GameObject) this.followTarget).moveInDirection(4);
                        return;
                    }

                    if (this.checkTileCollision(this.currentDirection, 4, (byte) 2)) {
                        this.moveInDirection((int) 4);
                        ((GameObject) this.followTarget).moveInDirection(4);
                        return;
                    }

                    this.syncPositionState();
                    this.dialogueState = 2;
                    this.setDirection((byte) 0);
                    return;
                }
                break;
            case 11:
                return;
            case 12:
                if (this.isVisible()) {
                    if (!GameUtils.isPointInShortArrayRectangle(PlayerCharacter.getInstance().worldX, PlayerCharacter.getInstance().worldY, this.worldX, this.worldY, this.sprite.getCurrentFrameEvents())) {
                        if (this.checkTileCollision(this.currentDirection, 4, (byte) 0)) {
                            this.moveInDirection((int) 4);
                            return;
                        }

                        if (this.currentDirection == 2) {
                            this.currentDirection = 0;
                            return;
                        }

                        this.currentDirection = 2;
                        return;
                    }

                    if (PlayerCharacter.getInstance().getFacingDirection() != 8) {
                        PlayerCharacter.getInstance().setFacingState((byte) 8, (byte) this.currentDirection);
                        return;
                    }
                }
                break;
            case 13:
                if (this.isVisible()) {
                    if (!GameUtils.isPointInShortArrayRectangle(PlayerCharacter.getInstance().worldX, PlayerCharacter.getInstance().worldY, this.worldX, this.worldY, this.sprite.getCurrentFrameEvents())) {
                        if (this.checkTileCollision(this.currentDirection, 4, (byte) 0)) {
                            this.moveInDirection((int) 4);
                            return;
                        }

                        if (this.currentDirection == 3) {
                            this.currentDirection = 1;
                            return;
                        }

                        this.currentDirection = 3;
                        return;
                    }

                    if (PlayerCharacter.getInstance().getFacingDirection() != 8) {
                        PlayerCharacter.getInstance().setFacingState((byte) 8, (byte) this.currentDirection);
                        return;
                    }
                }
                break;
            case 14:
                this.findNearbyNPCsForDialogue();
                if (this.healthBarWidth < 4) {
                    ++this.healthBarWidth;
                    return;
                }

                this.healthBarWidth = 0;
                return;
            case 16:
                if (GameUtils.isPointInShortArrayRectangle(PlayerCharacter.getInstance().worldX, PlayerCharacter.getInstance().worldY, this.worldX, this.worldY, this.sprite.getCurrentFrameEvents()) && PlayerCharacter.getInstance().getFacingDirection() != 5) {
                    PlayerCharacter.getInstance().setFacingState((byte) 5, (byte) PlayerCharacter.getInstance().currentDirection);
                    return;
                }
        }

    }

    public final void setupDialogue() {
        byte var2;
        if (this.npcSubType == 9) {
            this.setDirection((byte) 1);
            if ((this.sprite.spriteSetId == 302 || this.sprite.spriteSetId == 298) && this.checkTileCollision((byte) 1, 4, (byte) 1)) {
                var2 = 1;
                super.currentDirection = var2;
            } else if (this.checkTileCollision((byte) 1, 4, (byte) 2)) {
                var2 = 1;
                super.currentDirection = var2;
            } else {
                var2 = 3;
                super.currentDirection = var2;
            }
        } else if ((this.sprite.spriteSetId == 302 || this.sprite.spriteSetId == 298) && this.checkTileCollision((byte) 2, 4, (byte) 1)) {
            this.setDirection((byte) 2);
            var2 = 2;
            super.currentDirection = var2;
        } else if (this.checkTileCollision((byte) 2, 4, (byte) 2)) {
            this.setDirection((byte) 2);
            var2 = 2;
            super.currentDirection = var2;
        } else {
            this.setDirection((byte) 1);
            var2 = 0;
            super.currentDirection = var2;
        }

        this.dialogueState = 1;
    }

    public final void findNearbyNPCsForDialogue() {
        this.dialogueProgress = 0;

        while (true) {
            boolean var10000;
            label86:
            {
                byte var4;
                byte var10001 = super.sprite.getCurrentAnimationId();
                int var10002 = 16 * (this.dialogueProgress + 1);
                boolean var1 = false;
                int var3 = var10002;
                byte var2 = var10001;
                NPCEntity var5 = this;
                var4 = 0;
                int var6;
                label83:
                switch (var2) {
                    case 0:
                        var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY + var3);
                        var6 = 0;

                        while (true) {
                            if (var6 >= WorldGameSession.getInstance().NPCs.length) {
                                break label83;
                            }

                            if (WorldGameSession.getInstance().NPCs[var6].npcSubType != var5.npcSubType && WorldGameSession.getInstance().NPCs[var6].sprite.getCurrentFrameEvents() != null && GameUtils.isPointInShortArrayRectangle(var5.worldX, var5.worldY + var3, WorldGameSession.getInstance().NPCs[var6].worldX, WorldGameSession.getInstance().NPCs[var6].worldY, WorldGameSession.getInstance().NPCs[var6].sprite.getCurrentFrameEvents())) {
                                WorldGameSession.getInstance().NPCs[var6].followTarget = var5;
                                var10000 = false;
                                break label86;
                            }

                            ++var6;
                        }
                    case 1:
                        var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX + var3, this.worldY);
                        var6 = 0;

                        while (true) {
                            if (var6 >= WorldGameSession.getInstance().NPCs.length) {
                                break label83;
                            }

                            if (WorldGameSession.getInstance().NPCs[var6].npcSubType != var5.npcSubType && WorldGameSession.getInstance().NPCs[var6].sprite.getCurrentFrameEvents() != null && GameUtils.isPointInShortArrayRectangle(var5.worldX + var3, var5.worldY, WorldGameSession.getInstance().NPCs[var6].worldX, WorldGameSession.getInstance().NPCs[var6].worldY, WorldGameSession.getInstance().NPCs[var6].sprite.getCurrentFrameEvents())) {
                                WorldGameSession.getInstance().NPCs[var6].followTarget = var5;
                                var10000 = false;
                                break label86;
                            }

                            ++var6;
                        }
                    case 2:
                        var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY - var3);
                        var6 = 0;

                        while (true) {
                            if (var6 >= WorldGameSession.getInstance().NPCs.length) {
                                break label83;
                            }

                            if (WorldGameSession.getInstance().NPCs[var6].npcSubType != var5.npcSubType && WorldGameSession.getInstance().NPCs[var6].sprite.getCurrentFrameEvents() != null && GameUtils.isPointInShortArrayRectangle(var5.worldX, var5.worldY - var3, WorldGameSession.getInstance().NPCs[var6].worldX, WorldGameSession.getInstance().NPCs[var6].worldY, WorldGameSession.getInstance().NPCs[var6].sprite.getCurrentFrameEvents())) {
                                WorldGameSession.getInstance().NPCs[var6].followTarget = var5;
                                var10000 = false;
                                break label86;
                            }

                            ++var6;
                        }
                    case 3:
                        var4 = TileMapRenderer.getInstance().getTileAt(0, this.worldX - var3, this.worldY);

                        for (var6 = 0; var6 < WorldGameSession.getInstance().NPCs.length; ++var6) {
                            if (WorldGameSession.getInstance().NPCs[var6].npcSubType != var5.npcSubType && WorldGameSession.getInstance().NPCs[var6].sprite.getCurrentFrameEvents() != null && GameUtils.isPointInShortArrayRectangle(var5.worldX - var3, var5.worldY, WorldGameSession.getInstance().NPCs[var6].worldX, WorldGameSession.getInstance().NPCs[var6].worldY, WorldGameSession.getInstance().NPCs[var6].sprite.getCurrentFrameEvents())) {
                                WorldGameSession.getInstance().NPCs[var6].followTarget = var5;
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

            ++this.dialogueProgress;
        }
    }

    private boolean updateAnimationCounter() {
        ++this.animationCounter;
        if (this.animationCounter >= this.animationDuration) {
            this.animationCounter = 0;
            this.animationDuration = GameUtils.getRandomInRange(20, 40);
            return true;
        } else {
            return false;
        }
    }

    private boolean checkTileCollision(byte var1, int var2, byte var3) {
        byte var4 = 0;
        switch (var1) {
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

    public final void drawHealthBar(Graphics var1, int var2, int var3) {
        switch (super.sprite.getCurrentAnimationId()) {
            case 0:
                var1.setColor(65280);
                var1.fillRect(this.worldX - var2 - (this.healthBarWidth + 5) / 2, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 + 20, this.healthBarWidth + 5, this.dialogueProgress + 1 << 4);
                var1.setColor(16777215);
                var1.fillRect(this.worldX - var2 - (this.healthBarWidth + 3) / 2, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 + 20, this.healthBarWidth + 3, this.dialogueProgress + 1 << 4);
                return;
            case 1:
                var1.setColor(65280);
                var1.fillRect(this.worldX - var2 + 7, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.healthBarWidth + 5) / 2 + 13, this.dialogueProgress << 4, this.healthBarWidth + 5);
                var1.setColor(16777215);
                var1.fillRect(this.worldX - var2 + 7, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.healthBarWidth + 3) / 2 + 13, this.dialogueProgress << 4, this.healthBarWidth + 3);
            default:
                return;
            case 2:
                var1.setColor(65280);
                var1.fillRect(this.worldX - var2 - (this.healthBarWidth + 5) / 2, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.dialogueProgress << 4) + 8, this.healthBarWidth + 5, this.dialogueProgress << 4);
                var1.setColor(16777215);
                var1.fillRect(this.worldX - var2 - (this.healthBarWidth + 3) / 2, this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.dialogueProgress << 4) + 8, this.healthBarWidth + 3, this.dialogueProgress << 4);
                return;
            case 3:
                var1.setColor(65280);
                var1.fillRect(this.worldX - var2 - 8 - (this.dialogueProgress << 4), this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.healthBarWidth + 5) / 2 + 13, this.dialogueProgress << 4, this.healthBarWidth + 5);
                var1.setColor(16777215);
                var1.fillRect(this.worldX - var2 - 8 - (this.dialogueProgress << 4), this.worldY - this.sprite.getCurrentFrameTriggers()[3] - var3 - (this.healthBarWidth + 3) / 2 + 13, this.dialogueProgress << 4, this.healthBarWidth + 3);
        }
    }

    public final void syncPositionState() {
        WorldGameSession.getInstance().setNPCPosition(this.npcId, 0, this.worldX);
        WorldGameSession.getInstance().setNPCPosition(this.npcId, 1, this.worldY);
    }

    public final void syncVisualState() {
        byte var1 = 0;
        if (this.isVisible()) {
            var1 = 1;
        }

        WorldGameSession.getInstance().setNPCState(this.npcId, 1, var1, true);
        WorldGameSession.getInstance().setNPCState(this.npcId, 0, this.facingDirection, true);
        WorldGameSession.getInstance().setNPCState(this.npcId, 2, this.currentDirection, true);
    }

    public final void moveInDirection(int var1) {
        int var10000 = this.worldX;
        var10000 = this.worldY;
        super.moveInDirection(var1);
    }

    public final void setInteractionState(byte var1) {
        this.interactionState = 1;
    }

    public final byte getInteractionState() {
        return this.interactionState;
    }

    public final void createEffectObject(int var1) {
        if (this.effectObject == null && this.isVisible()) {
            this.effectObject = new GameObject();
            this.effectObject.loadSpriteSet(259, false);
            this.effectObject.sprite.applyColorEffects();
            this.effectObject.setAnimation((byte) 7, (byte) -1, true);
            this.effectObject.setWorldPosition(this.worldX, this.worldY - var1);
            this.effectObject.followTarget = this;
        }

        if (this.effectObject != null) {
            this.effectObject.activate();
        }

    }

    public final void checkPatrolState() {
        if (WorldGameSession.getInstance().checkBadgeRequirement(this.npcId)) {
            this.deactivateAuraObject();
        }

    }

    private void activateAuraObject() {
        if (this.auraObject != null) {
            this.auraObject.activate();
        }

    }

    public final void deactivate() {
        super.deactivate();
        this.deactivateAuraObject();
        this.deactivateAttachedObject();
        this.deactivateEffectObject();
    }

    public final void deactivateAuraObject() {
        if (this.auraObject != null) {
            this.auraObject.deactivate();
        }

    }

    public final void deactivateEffectObject() {
        if (this.effectObject != null) {
            this.effectObject.deactivate();
        }

    }

    public final void setWorldPosition(int var1, int var2) {
        super.setWorldPosition(var1, var2);
        if (this.attachedObject != null && this.attachedObject.isVisible()) {
            this.attachedObject.setWorldPosition(var1, var2);
        }

    }
}
