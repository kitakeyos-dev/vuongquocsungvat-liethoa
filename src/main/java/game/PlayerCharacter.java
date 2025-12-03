package game;

import engine.GameEngineBase;
import engine.GameUtils;
import engine.entity.*;
import engine.graphics.ScreenTransitionManager;

import java.util.Vector;

public final class PlayerCharacter extends GameObject {
    private static PlayerCharacter instance;
    public int currentVehicleType;
    private int movementSpeed;
    private int movementCounter;
    public byte outfitType;
    private int actionCounter;
    private boolean wildEncounterEnabled;
    public int encounterStepsRemaining;
    private int minEncounterSteps;
    private int maxEncounterSteps;
    public int effectDuration;
    public int brightnessEffectDuration;
    public boolean isInitialized;
    public PokemonEntity[] partyPokemon;
    public int partySize;
    public byte[][] badgeStates;
    public byte[][] creatureRegistry;
    public byte[][] creatureIndexes;
    public byte[] creatureCounts;
    public byte totalCreaturesSeen;
    public byte rareCreaturesSeen;
    public byte commonCreaturesSeen;
    public byte pokedexCount;
    public Vector equipmentInventory;
    public Vector consumableInventory;
    public Vector keyItems;
    public Vector specialItems;
    public Vector skills;
    public Vector pokemonStorage;
    public byte[] vehicleStates;
    public byte[] vehicleUsability;
    public short[] pokedexEntries;
    public Vector usableItems;
    public boolean[] gameFlags;
    public static boolean mapTransitionFlag;
    public Vector miscData;
    private int gold;
    private int badges;
    public int[] categoryOffsets = new int[]{0, 16, 32, 48, 64, 76, 88};
    public int[] categorySizes = new int[]{16, 16, 16, 16, 12, 12, 12};
    private static byte[][] mapTransitions = new byte[][]{{9, 2, 9, 3, 0}};
    private static short[][] spawnRanges = new short[][]{{0, 3, 112, 256}};
    private Vector followingNPCs = null;

    public static PlayerCharacter getInstance() {
        if (instance == null) {
            instance = new PlayerCharacter();
        }

        return instance;
    }

    public final void destroy() {
        this.partySize = 0;
        instance = null;
    }

    public PlayerCharacter() {
        this.primaryStates = new short[3];
        this.secondaryStates = new short[3];
        this.partyPokemon = new PokemonEntity[6];
        this.badgeStates = new byte[8][2];
        this.gameFlags = new boolean[21];
        this.equipmentInventory = new Vector();
        this.consumableInventory = new Vector();
        int[] initialItem = new int[]{0, 0, 1};
        this.consumableInventory.addElement(initialItem);
        this.keyItems = new Vector();
        this.specialItems = new Vector();
        this.skills = new Vector();
        this.pokemonStorage = new Vector();
        this.miscData = new Vector();
        this.creatureRegistry = new byte[7][];
        this.creatureCounts = new byte[7];
        this.creatureIndexes = new byte[7][];
        this.pokedexEntries = new short[]{-1, -1, -1, -1, -1};

        int var3;
        for (var3 = 0; var3 < this.creatureIndexes.length; ++var3) {
            this.creatureIndexes[var3] = new byte[this.categorySizes[var3]];

            for (int var2 = 0; var2 < this.creatureIndexes[var3].length; ++var2) {
                this.creatureIndexes[var3][var2] = -1;
            }
        }

        this.creatureRegistry[0] = new byte[16];
        this.creatureRegistry[1] = new byte[16];
        this.creatureRegistry[2] = new byte[16];
        this.creatureRegistry[3] = new byte[16];
        this.creatureRegistry[4] = new byte[12];
        this.creatureRegistry[5] = new byte[12];
        this.creatureRegistry[6] = new byte[12];
        this.vehicleStates = new byte[4];
        this.vehicleUsability = new byte[4];
        this.outfitType = 0;

        for (var3 = 0; var3 < 8; ++var3) {
            this.badgeStates[var3][0] = 0;
            this.badgeStates[var3][1] = 0;
        }

        for (var3 = 0; var3 < 4; ++var3) {
            this.vehicleStates[var3] = 0;
        }

        this.gold = 1000;
        this.badges = 0;
        this.currentVehicleType = -1;
        this.isInitialized = false;
    }

    public final void initializeFromData(short[] data) {
        if (this.currentVehicleType == -1) {
            this.loadSpriteSet(0, false);
        }

        this.worldX = data[0];
        this.worldY = data[1];
        this.activateVehicle(this.currentVehicleType);
        super.sprite.applyColorEffects();
        this.setFacingState((byte) 0, (byte) ((byte) data[2]));
        short stateValue = data[3];
        byte stateIndex = 0;
        super.primaryStates[stateIndex] = stateValue;
        stateValue = data[4];
        stateIndex = 1;
        super.primaryStates[stateIndex] = stateValue;
        stateValue = data[5];
        stateIndex = 2;
        super.primaryStates[stateIndex] = stateValue;
        if (this.currentVehicleType == -1) {
            this.saveCurrentStates();
        }

        this.layer = 1;
        this.minEncounterSteps = data[6];
        this.maxEncounterSteps = data[7];
        this.encounterStepsRemaining = this.getEncounterSteps();
        if (this.outfitType == 1) {
            this.replaceImage(0, 107, true);
        }

        if (this.attachedObject == null) {
            this.attachedObject = new GameObject();
            this.attachedObject.loadSpriteSet(337, false);
        }

        this.attachedObject.setWorldPosition(this.worldX, this.worldY);
        if (this.sprite.spriteSetId == 4) {
            this.attachedObject.setAnimation((byte) 0, (byte) 0, false);
        } else {
            this.attachedObject.setAnimation((byte) 1, (byte) 0, false);
        }

        this.attachedObject.activate();
        Object var5 = null;
        super.followTarget = (GameEntity) var5;
        this.isInitialized = true;
    }

    public final void updateMovement() {
        if (this.isTrailEnabled() && this.followTarget.getFacingDirection() != 0) {
            this.updateMovementTrail(((GameObject) this.followTarget).sprite, this.sprite);
        } else {
            int var1;
            int var2;
            switch (this.facingDirection) {
                case 0:
                    if (this.vehicleStates[2] != 2 && this.isInWater()) {
                        this.setFacingState((byte) 3, (byte) this.currentDirection);
                        return;
                    }
                    break;
                case 1:
                    if (this.vehicleStates[2] != 2) {
                        GameWorldManager.C25_f318 = this.checkNPCInteraction();
                        if (this.checkMovementCollision() && this.validateMovementPath()) {
                            this.moveInDirection((int) this.movementSpeed);
                            if (this.movementCounter < 8) {
                                this.movementCounter += this.movementSpeed;
                            } else {
                                this.movementCounter = 4;
                            }

                            this.updateEncounterCounter();
                            return;
                        }

                        this.movementCounter = 0;
                        return;
                    }

                    int var3 = this.worldY;
                    var2 = this.worldX;
                    boolean isOutOfBounds;
                    switch (this.currentDirection) {
                        case 0:
                            isOutOfBounds = TileMapRenderer.getInstance().isOutOfBounds(var2, var3 - 25 + this.movementSpeed);
                            break;
                        case 1:
                            isOutOfBounds = TileMapRenderer.getInstance().isOutOfBounds(var2 + this.movementSpeed, var3 - 25);
                            break;
                        case 2:
                            isOutOfBounds = TileMapRenderer.getInstance().isOutOfBounds(var2, var3 - 25 - this.movementSpeed);
                            break;
                        case 3:
                            isOutOfBounds = TileMapRenderer.getInstance().isOutOfBounds(var2 - this.movementSpeed, var3 - 25);
                            break;
                        default:
                            isOutOfBounds = false;
                    }

                    if (!isOutOfBounds) {
                        GameWorldManager.C25_f318 = this.checkNPCInteraction();
                        Object var5 = null;
                        super.followTarget = (GameEntity) var5;

                        for (var1 = 0; var1 < GameWorldManager.B().C25_f287.length; ++var1) {
                            this.checkNPCFollow(var1);
                        }

                        byte var6 = 0;
                        this.moveInDirection((int) super.secondaryStates[var6]);
                        if (this.movementCounter < 8) {
                            var6 = 0;
                            this.movementCounter += super.secondaryStates[var6];
                        } else {
                            this.movementCounter = 4;
                        }

                        this.updateEncounterCounter();
                        return;
                    }
                    break;
                case 2:
                    if (this.checkMovementCollision() && this.validateMovementPath()) {
                        this.moveInDirection((int) this.movementSpeed);
                        if (this.movementCounter < 8) {
                            this.movementCounter += this.movementSpeed;
                        } else {
                            this.movementCounter = 4;
                        }

                        this.updateEncounterCounter();
                        return;
                    }

                    this.movementCounter = 0;
                    return;
                case 3:
                default:
                    break;
                case 4:
                    return;
                case 5:
                    if (this.actionCounter < 16) {
                        switch (this.currentAnimationId) {
                            case 0:
                            case 2:
                                if (this.currentAnimationId == 2) {
                                    if (this.worldY > this.followTarget.getWorldY() - 16) {
                                        this.moveRelative(this.currentAnimationId, 4);
                                    }
                                } else if (this.worldY < this.followTarget.getWorldY() - 16) {
                                    this.moveRelative(this.currentAnimationId, 4);
                                }

                                if (this.targetX > this.followTarget.getWorldX()) {
                                    if (this.worldX <= this.followTarget.getWorldX()) {
                                        this.setWorldX(this.followTarget.getWorldX());
                                    } else {
                                        this.moveRelative(3, 4);
                                    }
                                } else if (this.targetX < this.followTarget.getWorldX()) {
                                    if (this.worldX >= this.followTarget.getWorldX()) {
                                        this.setWorldX(this.followTarget.getWorldX());
                                    } else {
                                        this.moveRelative(1, 4);
                                    }
                                }
                                break;
                            case 1:
                            case 3:
                                if (this.currentAnimationId == 3) {
                                    if (this.worldX > this.followTarget.worldX) {
                                        this.moveRelative(this.currentAnimationId, 4);
                                    }
                                } else if (this.worldX < this.followTarget.worldX) {
                                    this.moveRelative(this.currentAnimationId, 4);
                                }

                                if (this.targetY > this.followTarget.worldY - 16) {
                                    if (this.worldY <= this.followTarget.worldY - 16) {
                                        var2 = this.followTarget.worldY - 16;
                                        super.worldY = var2;
                                    } else {
                                        this.moveRelative(2, 4);
                                    }
                                } else if (this.targetY < this.followTarget.worldY - 16) {
                                    if (this.worldY >= this.followTarget.worldY - 16) {
                                        var2 = this.followTarget.worldY - 16;
                                        super.worldY = var2;
                                    } else {
                                        this.moveRelative(0, 4);
                                    }
                                }
                        }

                        if (this.actionCounter % 4 == 3) {
                            this.setAnimation((byte) 1, (byte) -1, false);
                        } else {
                            this.setAnimation((byte) (this.actionCounter % 4), (byte) -1, false);
                        }

                        this.setCurrentDirection((byte) (this.actionCounter % 4));
                        ++this.actionCounter;
                        return;
                    }

                    byte var4 = 0;

                    for (var2 = 0; var2 < mapTransitions.length; ++var2) {
                        byte[] var10001 = mapTransitions[var2];
                        if (GameWorldManager.B().C25_f290 == var10001[0]) {
                            var10001 = mapTransitions[var2];
                            if (GameWorldManager.B().C25_f291 == var10001[1]) {
                                GameWorldManager.B().C25_f290 = mapTransitions[var2][2];
                                GameWorldManager.B().C25_f291 = mapTransitions[var2][3];
                                var4 = mapTransitions[var2][4];
                                break;
                            }
                        }
                    }

                    for (var2 = 0; var2 < spawnRanges[var4].length / 4; ++var2) {
                        if (((NPCEntity) this.followTarget).npcId >= spawnRanges[var4][var2 << 2] && ((NPCEntity) this.followTarget).npcId <= spawnRanges[var4][(var2 << 2) + 1]) {
                            GameWorldManager.B().C25_f293 = spawnRanges[var4][(var2 << 2) + 2];
                            GameWorldManager.B().C25_f294 = spawnRanges[var4][(var2 << 2) + 3];
                            break;
                        }
                    }

                    mapTransitionFlag = true;
                    GameWorldManager.B().C25_f295 = -1;
                    GameScreenManager.getInstance().changeState((byte) 9);
                    return;
                case 6:
                    if (this.checkTilePassable()) {
                        this.moveInDirection((int) this.getPrimaryState((byte) 2));
                        return;
                    }

                    this.moveInDirection((int) this.getSecondaryState((byte) 1));
                    this.setFacingState((byte) 0, (byte) this.currentDirection);
                    return;
                case 7:
                    if (((NPCEntity) this.followTarget).dialogueState == 0) {
                        if (this.actionCounter < 7) {
                            this.moveInDirection((int) 4);
                            ++this.actionCounter;
                            return;
                        }

                        if (this.actionCounter == 7) {
                            if (this.currentDirection == 3) {
                                this.setAnimation((byte) 1, (byte) -1, false);
                            } else {
                                this.setAnimation(this.currentDirection, (byte) -1, false);
                            }

                            this.setCurrentDirection(this.currentDirection);
                            ((NPCEntity) this.followTarget).setupDialogue();
                            ++this.actionCounter;
                            return;
                        }
                    } else if (((NPCEntity) this.followTarget).dialogueState == 2) {
                        if (this.actionCounter < 8 && this.actionCounter > 0) {
                            this.moveInDirection((int) 4);
                            --this.actionCounter;
                            return;
                        }

                        if (this.actionCounter == 8) {
                            this.setFacingState((byte) 7, (byte) this.currentDirection);
                            --this.actionCounter;
                            return;
                        }

                        ((NPCEntity) this.followTarget).dialogueState = 0;
                        this.followTarget.setFollowTarget(null);
                        this.setFollowTarget(null);
                        this.setFacingState((byte) 0, this.currentDirection);
                        return;
                    }
                    break;
                case 8:
                    if (this.actionCounter < 16) {
                        if (this.actionCounter % 4 == 3) {
                            this.setAnimation((byte) 1, (byte) -1, false);
                        } else {
                            this.setAnimation((byte) (this.actionCounter % 4), (byte) -1, false);
                        }

                        this.setCurrentDirection((byte) (this.actionCounter % 4));
                        ++this.actionCounter;
                        return;
                    }

                    var1 = GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].worldX - GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].worldX % this.getPrimaryState((byte) 2);
                    var2 = GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].worldY - GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].worldY % this.getPrimaryState((byte) 2);
                    this.setWorldPosition(var1, var2);
                    this.attachedObject.setWorldPosition(var1, var2);
                    this.setFacingState((byte) 0, (byte) GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].followDistance);
                    this.moveInDirection((int) 32);
                    CameraController.getInstance().setCameraLag(8);
                    CameraController.getInstance().setLocked(false);
                    return;
                case 9:
                    if (this.actionCounter < 16) {
                        if (this.actionCounter % 4 == 3) {
                            this.setAnimation((byte) 1, (byte) -1, false);
                        } else {
                            this.setAnimation((byte) (this.actionCounter % 4), (byte) -1, false);
                        }

                        this.setCurrentDirection((byte) (this.actionCounter % 4));
                        ++this.actionCounter;
                        return;
                    }

                    var1 = GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].worldX - GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].worldX % this.getPrimaryState((byte) 2);
                    var2 = GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].worldY - GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].worldY % this.getPrimaryState((byte) 2);
                    this.setWorldPosition(var1, var2);
                    this.attachedObject.setWorldPosition(var1, var2);
                    this.setFacingState((byte) 10, this.currentDirection);
                    CameraController.getInstance().setCameraLag(8);
                    CameraController.getInstance().setLocked(false);
                    return;
                case 10:
                    if (this.actionCounter > 0) {
                        if (this.actionCounter % 4 == 3) {
                            this.setAnimation((byte) 1, (byte) -1, false);
                        } else {
                            this.setAnimation((byte) (this.actionCounter % 4), (byte) -1, false);
                        }

                        this.setCurrentDirection((byte) (this.actionCounter % 4));
                        --this.actionCounter;
                        return;
                    }

                    this.setFacingState((byte) 0, (byte) GameWorldManager.B().C25_f287[GameWorldManager.B().C25_f295].followDistance);
                    this.moveInDirection((int) 32);
            }

        }
    }

    public final void setFacingState(byte var1, byte var2) {
        while (true) {
            switch (var1) {
                case 0:
                    if (this.vehicleStates[2] != 2 && this.isInWater()) {
                        var1 = 3;
                        continue;
                    }

                    if (var2 == 3) {
                        this.setAnimation((byte) 1, (byte) -1, false);
                    } else {
                        this.setAnimation(var2, (byte) -1, false);
                    }

                    super.currentDirection = var2;
                    break;
                case 1:
                    if (this.vehicleStates[2] != 2 && this.isInWater()) {
                        var1 = 2;
                        continue;
                    }

                    if (super.sprite.getCurrentAnimationId() < 6) {
                        if (var2 == 3) {
                            this.setAnimationAndDirection((byte) 4, (byte) -1, var2);
                        } else {
                            this.setAnimationAndDirection((byte) ((byte) (var2 + 3)), (byte) -1, var2);
                        }
                    } else {
                        if (var2 == 3) {
                            this.setAnimation((byte) (var1 * 3 + 1), (byte) -1, false);
                        } else {
                            this.setAnimation((byte) (var1 * 3 + var2), (byte) -1, false);
                        }

                        super.currentDirection = var2;
                    }
                    break;
                case 2:
                    if (this.isInWater()) {
                        if (super.sprite.getCurrentAnimationId() < 9) {
                            if (var2 == 3) {
                                this.setAnimationAndDirection((byte) 7, (byte) -1, var2);
                            } else {
                                this.setAnimationAndDirection((byte) (var2 + 6), (byte) -1, var2);
                            }
                        } else if (var2 == 3) {
                            this.setAnimation((byte) (var1 * 3 + 1), (byte) -1, false);
                        } else {
                            this.setAnimation((byte) (var1 * 3 + var2), (byte) -1, false);
                        }
                    } else if (var2 == 3) {
                        this.setAnimation((byte) (this.facingDirection * 3 + 1), (byte) -1, false);
                    } else {
                        this.setAnimation((byte) (this.facingDirection * 3 + var2), (byte) -1, false);
                    }

                    super.currentDirection = var2;
                    break;
                case 3:
                    if (var2 == 3) {
                        this.setAnimation((byte) (var1 * 3 + 1), (byte) -1, false);
                    } else {
                        this.setAnimation((byte) (var1 * 3 + var2), (byte) -1, false);
                    }

                    super.currentDirection = var2;
                    break;
                case 4:
                    this.setAnimation((byte) (var1 * 3), (byte) -2, false);
                    super.currentDirection = var2;
                    this.movementCounter = 0;
                    break;
                case 5:
                    this.actionCounter = 0;
                    super.currentAnimationId = var2;
                    int var5 = this.worldY;
                    int var4 = this.worldX;
                    super.targetX = var4;
                    super.targetY = var5;
                    if (var2 == 3) {
                        this.setAnimation((byte) 1, (byte) -1, false);
                    } else {
                        this.setAnimation(var2, (byte) -1, false);
                    }

                    super.currentDirection = var2;
                    break;
                case 6:
                    if (var2 == 3) {
                        this.setAnimation((byte) 1, (byte) -1, false);
                    } else {
                        this.setAnimation(var2, (byte) -1, false);
                    }

                    super.currentDirection = var2;
                    break;
                case 7:
                    if (var2 == 3) {
                        this.setAnimation((byte) 4, (byte) -1, false);
                    } else {
                        this.setAnimation((byte) (var2 + 3), (byte) -1, false);
                    }

                    super.currentDirection = var2;
                    break;
                case 8:
                case 9:
                    this.actionCounter = 0;
                    super.currentDirection = var2;
            }

            this.facingDirection = var1;
            if (this.facingDirection != 0 && this.facingDirection != 1) {
                if (GameWorldManager.B().C25_f311 != null) {
                    GameWorldManager.B().C25_f311.setActive(false);
                }
            } else if (GameWorldManager.B().C25_f311 != null) {
                GameWorldManager.B().C25_f311.setActive(true);
                return;
            }

            return;
        }
    }

    public final boolean hasVehicle(int index) {
        return this.vehicleStates[index] != 0;
    }

    public final boolean isVehicleUsable(int index) {
        return this.vehicleUsability[index] != 1;
    }

    public final boolean canDismountVehicle() {
        return this.currentVehicleType != 2 || TileMapRenderer.getInstance().getTileAt(0, this.worldX + 7, this.worldY + 7) == 0 && TileMapRenderer.getInstance().getTileAt(0, this.worldX - 8, this.worldY - 8) == 0;
    }

    public final void activateVehicle(int vehicleIndex) {
        if (vehicleIndex != -1) {
            this.vehicleStates[vehicleIndex] = 2;
            this.sprite.forceCleanup();
            this.loadSpriteSet(vehicleIndex + 1, false);
            super.sprite.applyColorEffects();
            this.setFacingState((byte) 0, this.currentDirection);
            if (this.outfitType == 1) {
                this.replaceImage(1, 107, true);
            }

            // Set movement speed based on vehicle type
            if ((this.vehicleStates[vehicleIndex] != 2 || vehicleIndex != 0) && (this.vehicleStates[vehicleIndex] != 2 || vehicleIndex != 1)) {
                super.secondaryStates[0] = 4; // Normal speed
            } else {
                super.secondaryStates[0] = 8; // Fast speed for bike/surf
            }

            if (this.vehicleStates[2] == 2 && GameWorldManager.B().C25_f311 != null) {
                GameWorldManager.B().C25_f311.deactivate();
            }

            this.currentVehicleType = vehicleIndex;
            this.movementSpeed = super.secondaryStates[0];
        }
    }

    public final void resetToDefaultSprite() {
        this.sprite.forceCleanup();
        this.loadSpriteSet(0, false);
        super.sprite.applyColorEffects();

        for (int i = 0; i < 4; ++i) {
            if (this.vehicleStates[i] == 2) {
                this.vehicleStates[i] = 1;
            }
        }

        if (this.outfitType == 1) {
            this.replaceImage(0, 107, true);
        }

        if (GameWorldManager.B().C25_f311 != null) {
            GameWorldManager.B().C25_f311.activate();
        }

        byte var2 = 0;
        short var3 = super.primaryStates[var2];
        var2 = 0;
        super.secondaryStates[var2] = var3;
        this.currentVehicleType = -1;
    }

    public final void equipSpecialOutfit() {
        this.outfitType = 1;
        boolean var1 = false;

        for (int var2 = 0; var2 < 4; ++var2) {
            if (this.vehicleStates[var2] == 2) {
                var1 = true;
                break;
            }
        }

        if (var1) {
            this.replaceImage(1, 107, true);
        } else {
            this.replaceImage(0, 107, true);
        }
    }

    public final void unequipSpecialOutfit() {
        this.outfitType = 0;
        boolean var1 = false;

        for (int var2 = 0; var2 < 4; ++var2) {
            if (this.vehicleStates[var2] == 2) {
                var1 = true;
                break;
            }
        }

        if (var1) {
            this.replaceImage(1, 100, true);
        } else {
            this.replaceImage(0, 100, true);
        }
    }

    private short checkNPCInteraction() {
        for (short var1 = 0; var1 < GameWorldManager.B().C25_f287.length; ++var1) {
            if (GameWorldManager.B().C25_f287[var1].isVisible()) {
                if ((GameWorldManager.B().C25_f287[var1].sprite.spriteSetId <= 85 || GameWorldManager.B().C25_f287[var1].sprite.spriteSetId == 226 || GameWorldManager.B().C25_f287[var1].sprite.spriteSetId == 92 || GameWorldManager.B().C25_f287[var1].sprite.spriteSetId == 102 || GameWorldManager.B().C25_f287[var1].sprite.spriteSetId == 137) && GameWorldManager.B().C25_f287[var1].npcType == 0 && (GameWorldManager.B().C25_f287[var1].npcSubType == 1 || GameWorldManager.B().C25_f287[var1].npcSubType == 18) && this.checkCollisionWithNPC(GameWorldManager.B().C25_f287[var1], this.sprite.getCurrentFrameEvents(), GameWorldManager.B().C25_f287[var1].sprite.getCurrentFrameEvents())) {
                    if (GameWorldManager.B().C25_f287[var1].getInteractionState() == 0) {
                        GameWorldManager.B().a((byte) 13, GameWorldManager.B().C25_f287[var1].worldX, GameWorldManager.B().C25_f287[var1].worldY - 40, GameWorldManager.B().C25_f287[var1]);
                        if (GameWorldManager.B().C25_f287[var1].auraObject != null) {
                            GameWorldManager.B().C25_f287[var1].auraObject.deactivate();
                        }
                    } else if (GameWorldManager.B().C25_f287[var1].getInteractionState() == 1) {
                        GameWorldManager.B().a((byte) 13, GameWorldManager.B().C25_f287[var1].worldX, GameWorldManager.B().C25_f287[var1].worldY - 40, GameWorldManager.B().C25_f287[var1]);
                        if (QuestManager.questEffectObjects != null && QuestManager.questEffectObjects.size() > 0) {
                            for (int var2 = 0; var2 < QuestManager.questEffectObjects.size(); ++var2) {
                                if (((GameObject) QuestManager.questEffectObjects.elementAt(var2)).followTarget.equals(GameWorldManager.B().C25_f287[var1])) {
                                    ((GameObject) QuestManager.questEffectObjects.elementAt(var2)).deactivate();
                                    break;
                                }
                            }
                        }
                    } else {
                        GameWorldManager.B().a((byte) 13, GameWorldManager.B().C25_f287[var1].worldX, GameWorldManager.B().C25_f287[var1].worldY - 40, GameWorldManager.B().C25_f287[var1]);
                        if (GameWorldManager.B().C25_f287[var1].auraAnimationId != 0) {
                            GameWorldManager.B().C25_f287[var1].deactivateAuraObject();
                        }
                    }

                    return var1;
                }

                if (GameWorldManager.B().C25_f287[var1].npcType == 2 && this.checkCollisionWithNPC(GameWorldManager.B().C25_f287[var1], this.sprite.getCurrentFrameEvents(), GameWorldManager.B().C25_f287[var1].sprite.getCurrentFrameEvents())) {
                    GameWorldManager.B().m(var1);
                }
            }
        }

        GameWorldManager.B().D();
        QuestManager.isQuestTriggered = false;
        return -1;
    }

    private boolean checkNPCFollow(int var1) {
        switch (GameWorldManager.B().C25_f287[var1].npcType) {
            case 3:
                short[] var2;
                short var3 = (var2 = GameWorldManager.B().C25_f287[var1].sprite.getCurrentFrameEvents())[0];
                short var4 = var2[1];
                short var5 = (short) (var2[2] + 16);
                short var6 = (short) (var2[3] + 16);
                if (GameWorldManager.B().C25_f287[var1].isFacingPlayer && this.checkCollisionWithNPC(GameWorldManager.B().C25_f287[var1], this.sprite.getCurrentFrameEvents(), new short[]{var3, var4, var5, var6})) {
                    NPCEntity var7 = GameWorldManager.B().C25_f287[var1];
                    super.followTarget = var7;
                }
            default:
                return true;
        }
    }

    private boolean checkMovementCollision() {
        NPCEntity var4 = null;
        super.followTarget = var4;
        boolean var1 = true;
        if (this.followingNPCs != null) {
            this.followingNPCs.removeAllElements();
        }

        for (int var2 = 0; var2 < GameWorldManager.B().C25_f287.length; ++var2) {
            this.checkNPCFollow(var2);
            if (GameWorldManager.B().C25_f287[var2].isFacingPlayer && this.checkCollisionWithNPC(GameWorldManager.B().C25_f287[var2], this.sprite.getCurrentFrameEvents(), GameWorldManager.B().C25_f287[var2].sprite.getCurrentFrameEvents())) {
                switch (GameWorldManager.B().C25_f287[var2].npcType) {
                    case 0:
                        switch (GameWorldManager.B().C25_f287[var2].npcSubType) {
                            case 0:
                                return false;
                            case 1:
                            case 2:
                            case 3:
                            case 12:
                            case 13:
                            default:
                                continue;
                            case 4:
                            case 11:
                                if (GameWorldManager.B().C25_f287[var2].getFacingDirection() != 2 && GameWorldManager.B().C25_f287[var2].isVisible()) {
                                    var4 = GameWorldManager.B().C25_f287[var2];
                                    super.followTarget = var4;
                                    return false;
                                }
                                continue;
                            case 5:
                                if (GameWorldManager.B().C25_f287[var2].getFacingDirection() != 2) {
                                    if (this.badgeStates[5][0] == 2) {
                                        var4 = GameWorldManager.B().C25_f287[var2];
                                        super.followTarget = var4;
                                    }

                                    return false;
                                }
                            case 6:
                                if (GameWorldManager.B().C25_f287[var2].getFacingDirection() != 2) {
                                    if (this.vehicleStates[3] != 2) {
                                        if (this.badgeStates[2][0] == 2) {
                                            var4 = GameWorldManager.B().C25_f287[var2];
                                            super.followTarget = var4;
                                            if (this.followingNPCs == null) {
                                                this.followingNPCs = new Vector();
                                            }

                                            this.followingNPCs.addElement(GameWorldManager.B().C25_f287[var2]);
                                            GameWorldManager.B().C25_f287[var2].createEffectObject((int) 20);
                                        }

                                        var1 = false;
                                    } else {
                                        GameWorldManager.B().C25_f287[var2].setDirection((byte) 1);
                                        GameWorldManager.B().C25_f285.moveEntityToForeground(GameWorldManager.B().C25_f287[var2], 2);
                                    }
                                }
                                continue;
                            case 7:
                                if (GameWorldManager.B().C25_f287[var2].getFacingDirection() != 2) {
                                    if (this.vehicleStates[3] != 2) {
                                        if (this.badgeStates[1][0] == 2) {
                                            var4 = GameWorldManager.B().C25_f287[var2];
                                            super.followTarget = var4;
                                            if (this.followingNPCs == null) {
                                                this.followingNPCs = new Vector();
                                            }

                                            this.followingNPCs.addElement(GameWorldManager.B().C25_f287[var2]);
                                            GameWorldManager.B().C25_f287[var2].createEffectObject((int) 30);
                                        }

                                        var1 = false;
                                    } else {
                                        GameWorldManager.B().C25_f287[var2].setDirection((byte) 1);
                                        GameWorldManager.B().C25_f285.moveEntityToForeground(GameWorldManager.B().C25_f287[var2], 2);
                                    }
                                }
                                continue;
                            case 8:
                                if (GameWorldManager.B().C25_f287[var2].isVisible()) {
                                    if (GameWorldManager.B().C25_f287[var2].followTarget != null && ((NPCEntity) GameWorldManager.B().C25_f287[var2].followTarget).dialogueTimeout > ((NPCEntity) GameWorldManager.B().C25_f287[var2].followTarget).dialogueProgress) {
                                        return false;
                                    }

                                    for (int var5 = 0; var5 < GameWorldManager.B().C25_f287.length; ++var5) {
                                        if (GameWorldManager.B().C25_f287[var5].isFacingPlayer && !GameWorldManager.B().C25_f287[var5].equals(GameWorldManager.B().C25_f287[var2]) && GameWorldManager.B().C25_f287[var5].npcType == 0 && (GameWorldManager.B().C25_f287[var5].npcSubType == 8 || GameWorldManager.B().C25_f287[var5].npcSubType == 11)) {
                                            switch (this.currentDirection) {
                                                case 0:
                                                    if (GameUtils.checkCollisionBetweenShortArrays(GameWorldManager.B().C25_f287[var2].worldX, GameWorldManager.B().C25_f287[var2].worldY + 8, GameWorldManager.B().C25_f287[var5].worldX, GameWorldManager.B().C25_f287[var5].worldY, GameWorldManager.B().C25_f287[var2].sprite.getCurrentFrameEvents(), GameWorldManager.B().C25_f287[var5].sprite.getCurrentFrameEvents())) {
                                                        return false;
                                                    }
                                                    break;
                                                case 1:
                                                    if (GameUtils.checkCollisionBetweenShortArrays(GameWorldManager.B().C25_f287[var2].worldX + 8, GameWorldManager.B().C25_f287[var2].worldY, GameWorldManager.B().C25_f287[var5].worldX, GameWorldManager.B().C25_f287[var5].worldY, GameWorldManager.B().C25_f287[var2].sprite.getCurrentFrameEvents(), GameWorldManager.B().C25_f287[var5].sprite.getCurrentFrameEvents())) {
                                                        return false;
                                                    }
                                                    break;
                                                case 2:
                                                    if (GameUtils.checkCollisionBetweenShortArrays(GameWorldManager.B().C25_f287[var2].worldX, GameWorldManager.B().C25_f287[var2].worldY - 8, GameWorldManager.B().C25_f287[var5].worldX, GameWorldManager.B().C25_f287[var5].worldY, GameWorldManager.B().C25_f287[var2].sprite.getCurrentFrameEvents(), GameWorldManager.B().C25_f287[var5].sprite.getCurrentFrameEvents())) {
                                                        return false;
                                                    }
                                                    break;
                                                case 3:
                                                    if (GameUtils.checkCollisionBetweenShortArrays(GameWorldManager.B().C25_f287[var2].worldX - 8, GameWorldManager.B().C25_f287[var2].worldY, GameWorldManager.B().C25_f287[var5].worldX, GameWorldManager.B().C25_f287[var5].worldY, GameWorldManager.B().C25_f287[var2].sprite.getCurrentFrameEvents(), GameWorldManager.B().C25_f287[var5].sprite.getCurrentFrameEvents())) {
                                                        return false;
                                                    }
                                            }
                                        }
                                    }

                                    GameWorldManager.B().C25_f287[var2].setDirection((byte) 1);
                                    NPCEntity var10000 = GameWorldManager.B().C25_f287[var2];
                                    byte var6 = this.currentDirection;
                                    var10000.currentDirection = var6;
                                    return false;
                                }
                                continue;
                            case 9:
                                if (TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) != 2 && TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) != 1) {
                                    if (this.currentDirection == 3 || this.currentDirection == 1) {
                                        this.actionCounter = 0;
                                        this.setFacingState((byte) 7, (byte) this.currentDirection);
                                        GameWorldManager.B().C25_f287[var2].followTarget = this;
                                        var4 = GameWorldManager.B().C25_f287[var2];
                                        super.followTarget = var4;
                                        return false;
                                    }
                                    continue;
                                }

                                return false;
                            case 10:
                                if (TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) == 2 || TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) == 1) {
                                    return false;
                                }

                                if (this.currentDirection == 0 || this.currentDirection == 2) {
                                    this.actionCounter = 0;
                                    this.setFacingState((byte) 7, (byte) this.currentDirection);
                                    GameWorldManager.B().C25_f287[var2].followTarget = this;
                                    var4 = GameWorldManager.B().C25_f287[var2];
                                    super.followTarget = var4;
                                    return false;
                                }
                                continue;
                            case 14:
                                return false;
                            case 15:
                                if (GameWorldManager.B().C25_f287[var2].getFacingDirection() != 2) {
                                    if (this.gameFlags[6]) {
                                        var4 = GameWorldManager.B().C25_f287[var2];
                                        super.followTarget = var4;
                                        return false;
                                    }

                                    GameWorldManager.B().C25_f287[var2].setDirection((byte) 1);
                                    GameWorldManager.B().C25_f285.moveEntityToForeground(GameWorldManager.B().C25_f287[var2], 2);
                                }
                                continue;
                            case 16:
                                var4 = GameWorldManager.B().C25_f287[var2];
                                super.followTarget = var4;
                                continue;
                        }
                    case 1:
                        if (GameWorldManager.B().C25_f287[var2].npcSubType == 3) {
                            return false;
                        }
                    case 2:
                    default:
                        break;
                    case 3:
                        return false;
                }
            }
        }

        return var1;
    }

    private boolean validateMovementPath() {
        byte var7 = 0;
        this.movementSpeed = super.secondaryStates[var7];
        int var1 = this.worldX - 8;
        int var2 = this.worldY - 8;
        int var3 = this.worldX + 7;
        int var4 = this.worldY + 7;
        byte[] var5 = new byte[]{-1, -1, -1, -1, -1};
        switch (this.currentDirection) {
            case 2:
                if (TileMapRenderer.getInstance().isOutOfBounds(this.worldX, var2 - this.movementSpeed)) {
                    return false;
                } else {
                    var5[0] = TileMapRenderer.getInstance().getTileAt(0, var1, var2 - this.movementSpeed);
                    var5[1] = TileMapRenderer.getInstance().getTileAt(0, var3, var2 - this.movementSpeed);
                    var5[2] = TileMapRenderer.getInstance().getTileAt(0, this.worldX, var2 - this.movementSpeed);
                    if (this.isTileWalkable(var5[0]) || this.isTileWalkable(var5[1])) {
                        if (!this.isTileWalkable(var5[0])) {
                            var7 = 1;
                            this.movementSpeed = super.secondaryStates[var7];
                            return this.handleSpecialTile((byte) var5[1], (byte) 1);
                        } else {
                            if (!this.isTileWalkable(var5[1])) {
                                var7 = 1;
                                this.movementSpeed = super.secondaryStates[var7];
                                return this.handleSpecialTile((byte) var5[0], (byte) 3);
                            }

                            return this.handleSpecialTile((byte) var5[2], (byte) 2);
                        }
                    } else if (TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) == 3) {
                        return false;
                    } else {
                        var5[0] = TileMapRenderer.getInstance().getTileAt(0, this.worldX - 16, var2 - this.movementSpeed);
                        var5[1] = TileMapRenderer.getInstance().getTileAt(0, this.worldX + 16, var2 - this.movementSpeed);
                        var5[3] = TileMapRenderer.getInstance().getTileAt(0, this.worldX - 16, this.worldY);
                        var5[4] = TileMapRenderer.getInstance().getTileAt(0, this.worldX + 16, this.worldY);
                        var7 = 1;
                        this.movementSpeed = super.secondaryStates[var7];
                        if (!this.isTileWalkable(var5[0])) {
                            return this.handleSpecialTile((byte) var5[1], (byte) 1);
                        } else if (!this.isTileWalkable(var5[1])) {
                            return this.handleSpecialTile((byte) var5[0], (byte) 3);
                        } else if (this.isTileWalkable(var5[4])) {
                            return this.handleSpecialTile((byte) var5[1], (byte) 1);
                        } else if (this.isTileWalkable(var5[3])) {
                            return this.handleSpecialTile((byte) var5[0], (byte) 3);
                        }
                    }
                }
            case 0:
                if (TileMapRenderer.getInstance().isOutOfBounds(this.worldX, var4 + this.movementSpeed)) {
                    return false;
                } else {
                    var5[0] = TileMapRenderer.getInstance().getTileAt(0, var1, var4 + this.movementSpeed);
                    var5[1] = TileMapRenderer.getInstance().getTileAt(0, var3, var4 + this.movementSpeed);
                    var5[2] = TileMapRenderer.getInstance().getTileAt(0, this.worldX, var4 + this.movementSpeed);
                    if (this.isTileWalkable(var5[0]) || this.isTileWalkable(var5[1])) {
                        if (!this.isTileWalkable(var5[0])) {
                            var7 = 1;
                            this.movementSpeed = super.secondaryStates[var7];
                            return this.handleSpecialTile((byte) var5[1], (byte) 1);
                        } else if (!this.isTileWalkable(var5[1])) {
                            var7 = 1;
                            this.movementSpeed = super.secondaryStates[var7];
                            return this.handleSpecialTile((byte) var5[0], (byte) 3);
                        } else {
                            return this.handleSpecialTile((byte) var5[2], (byte) 0);
                        }
                    } else if (TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) == 3) {
                        return false;
                    } else {
                        var5[0] = TileMapRenderer.getInstance().getTileAt(0, var1 - 16, var4 + this.movementSpeed);
                        var5[1] = TileMapRenderer.getInstance().getTileAt(0, var3 + 16, var4 + this.movementSpeed);
                        var5[3] = TileMapRenderer.getInstance().getTileAt(0, this.worldX - 16, this.worldY);
                        var5[4] = TileMapRenderer.getInstance().getTileAt(0, this.worldX + 16, this.worldY);
                        var7 = 1;
                        this.movementSpeed = super.secondaryStates[var7];
                        if (!this.isTileWalkable(var5[0])) {
                            return this.handleSpecialTile((byte) var5[1], (byte) 1);
                        } else if (!this.isTileWalkable(var5[1])) {
                            return this.handleSpecialTile((byte) var5[0], (byte) 3);
                        } else if (this.isTileWalkable(var5[4])) {
                            return this.handleSpecialTile((byte) var5[1], (byte) 1);
                        } else if (this.isTileWalkable(var5[3])) {
                            return this.handleSpecialTile((byte) var5[0], (byte) 3);
                        }
                    }
                }
            case 3:
                if (TileMapRenderer.getInstance().isOutOfBounds(var1 - this.movementSpeed, this.worldY)) {
                    return false;
                } else {
                    var5[0] = TileMapRenderer.getInstance().getTileAt(0, var1 - this.movementSpeed, var2);
                    var5[1] = TileMapRenderer.getInstance().getTileAt(0, var1 - this.movementSpeed, var4);
                    var5[2] = TileMapRenderer.getInstance().getTileAt(0, var1 - this.movementSpeed, this.worldY);
                    if (this.isTileWalkable(var5[0]) || this.isTileWalkable(var5[1])) {
                        if (!this.isTileWalkable(var5[0])) {
                            var7 = 1;
                            this.movementSpeed = super.secondaryStates[var7];
                            return this.handleSpecialTile((byte) var5[1], (byte) 0);
                        } else {
                            if (!this.isTileWalkable(var5[1])) {
                                var7 = 1;
                                this.movementSpeed = super.secondaryStates[var7];
                                return this.handleSpecialTile((byte) var5[0], (byte) 2);
                            }

                            return this.handleSpecialTile((byte) var5[2], (byte) 3);
                        }
                    } else if (TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) == 3) {
                        return false;
                    } else {
                        var5[0] = TileMapRenderer.getInstance().getTileAt(0, var1 - this.movementSpeed, var2 - 16);
                        var5[1] = TileMapRenderer.getInstance().getTileAt(0, var1 - this.movementSpeed, var4 + 16);
                        var5[3] = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY - 16);
                        var5[4] = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY + 16);
                        var7 = 1;
                        this.movementSpeed = super.secondaryStates[var7];
                        if (!this.isTileWalkable(var5[0])) {
                            return this.handleSpecialTile((byte) var5[1], (byte) 0);
                        } else if (!this.isTileWalkable(var5[1])) {
                            return this.handleSpecialTile((byte) var5[0], (byte) 2);
                        } else if (this.isTileWalkable(var5[4])) {
                            return this.handleSpecialTile((byte) var5[1], (byte) 0);
                        } else if (this.isTileWalkable(var5[3])) {
                            return this.handleSpecialTile((byte) var5[0], (byte) 2);
                        }
                    }
                }
            case 1:
                if (TileMapRenderer.getInstance().isOutOfBounds(var3 + this.movementSpeed, this.worldY)) {
                    return false;
                } else {
                    var5[0] = TileMapRenderer.getInstance().getTileAt(0, var3 + this.movementSpeed, var2);
                    var5[1] = TileMapRenderer.getInstance().getTileAt(0, var3 + this.movementSpeed, var4);
                    var5[2] = TileMapRenderer.getInstance().getTileAt(0, var3 + this.movementSpeed, this.worldY);
                    if (this.isTileWalkable(var5[0]) || this.isTileWalkable(var5[1])) {
                        if (!this.isTileWalkable(var5[0])) {
                            var7 = 1;
                            this.movementSpeed = super.secondaryStates[var7];
                            return this.handleSpecialTile((byte) var5[1], (byte) 0);
                        } else {
                            if (!this.isTileWalkable(var5[1])) {
                                var7 = 1;
                                this.movementSpeed = super.secondaryStates[var7];
                                return this.handleSpecialTile((byte) var5[0], (byte) 2);
                            }

                            return this.handleSpecialTile((byte) var5[2], (byte) 1);
                        }
                    } else if (TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) == 3) {
                        return false;
                    } else {
                        var5[0] = TileMapRenderer.getInstance().getTileAt(0, var3 + this.movementSpeed, var2 - 16);
                        var5[1] = TileMapRenderer.getInstance().getTileAt(0, var3 + this.movementSpeed, var4 + 16);
                        var5[3] = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY - 16);
                        var5[4] = TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY + 16);
                        var7 = 1;
                        this.movementSpeed = super.secondaryStates[var7];
                        if (!this.isTileWalkable(var5[0])) {
                            return this.handleSpecialTile((byte) var5[1], (byte) 0);
                        } else if (!this.isTileWalkable(var5[1])) {
                            return this.handleSpecialTile((byte) var5[0], (byte) 2);
                        } else if (this.isTileWalkable(var5[4])) {
                            return this.handleSpecialTile((byte) var5[1], (byte) 0);
                        } else if (this.isTileWalkable(var5[3])) {
                            return this.handleSpecialTile((byte) var5[0], (byte) 2);
                        }
                    }
                }
            default:
                return true;
        }
    }

    private boolean isTileWalkable(byte tileType) {
        switch (tileType) {
            case 1:
                return false;
            case 2:
                if (this.badgeStates[3][0] != 2) {
                    return false;
                }
                return true;
            default:
                return true;
        }
    }

    public final boolean checkCollisionWithNPC(NPCEntity var1, short[] var2, short[] var3) {
        if (var3 == null) {
            return false;
        } else {
            byte var5;
            switch (this.currentDirection) {
                case 0:
                    if (var1.npcSubType == 14) {
                        var5 = 0;
                        return checkExtendedCollision(var1, var3, var2, this.worldX, this.worldY + super.secondaryStates[var5]);
                    }

                    var5 = 0;
                    if (GameUtils.checkCollisionBetweenShortArrays(this.worldX, this.worldY + super.secondaryStates[var5], var1.worldX, var1.worldY, var2, var3)) {
                        return true;
                    }
                    break;
                case 1:
                    if (var1.npcSubType == 14) {
                        var5 = 0;
                        return checkExtendedCollision(var1, var3, var2, this.worldX + super.secondaryStates[var5], this.worldY);
                    }

                    var5 = 0;
                    if (GameUtils.checkCollisionBetweenShortArrays(this.worldX + super.secondaryStates[var5], this.worldY, var1.worldX, var1.worldY, var2, var3)) {
                        return true;
                    }
                    break;
                case 2:
                    if (var1.npcSubType == 14) {
                        var5 = 0;
                        return checkExtendedCollision(var1, var3, var2, this.worldX, this.worldY - super.secondaryStates[var5]);
                    }

                    var5 = 0;
                    if (GameUtils.checkCollisionBetweenShortArrays(this.worldX, this.worldY - super.secondaryStates[var5], var1.worldX, var1.worldY, var2, var3)) {
                        return true;
                    }
                    break;
                case 3:
                    if (var1.npcSubType == 14) {
                        var5 = 0;
                        return checkExtendedCollision(var1, var3, var2, this.worldX - super.secondaryStates[var5], this.worldY);
                    }

                    var5 = 0;
                    if (GameUtils.checkCollisionBetweenShortArrays(this.worldX - super.secondaryStates[var5], this.worldY, var1.worldX, var1.worldY, var2, var3)) {
                        return true;
                    }
            }

            return false;
        }
    }

    private static boolean checkExtendedCollision(NPCEntity var0, short[] var1, short[] var2, int var3, int var4) {
        switch (var0.sprite.getCurrentAnimationId()) {
            case 0:
                if (GameUtils.checkCollisionWithShortArray(var0.worldX + var1[0], var0.worldY + var1[1], var1[2], var1[3] + (var0.dialogueProgress << 4), var3, var4, var2)) {
                    return true;
                }
                break;
            case 1:
                if (GameUtils.checkCollisionWithShortArray(var0.worldX + var1[0], var0.worldY + var1[1], var1[2] + (var0.dialogueProgress << 4), var1[3], var3, var4, var2)) {
                    return true;
                }
                break;
            case 2:
                if (GameUtils.checkCollisionWithShortArray(var0.worldX + var1[0], var0.worldY + var1[1] - (var0.dialogueProgress << 4), var1[2], var1[3] + (var0.dialogueProgress << 4), var3, var4, var2)) {
                    return true;
                }
                break;
            case 3:
                if (GameUtils.checkCollisionWithShortArray(var0.worldX + var1[0] - (var0.dialogueProgress << 4), var0.worldY + var1[1], var1[2] + (var0.dialogueProgress << 4), var1[3], var3, var4, var2)) {
                    return true;
                }
        }

        return false;
    }

    private boolean checkTilePassable() {
        boolean var1 = true;
        switch (this.currentDirection) {
            case 0:
                var1 = this.isTileWalkable(TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY + 16));
                break;
            case 1:
                var1 = this.isTileWalkable(TileMapRenderer.getInstance().getTileAt(0, this.worldX + 16, this.worldY));
                break;
            case 2:
                var1 = this.isTileWalkable(TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY - 16));
                break;
            case 3:
                var1 = this.isTileWalkable(TileMapRenderer.getInstance().getTileAt(0, this.worldX - 16, this.worldY));
        }

        return var1 && TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) != 3 ? false : var1;
    }

    private boolean isInWater() {
        boolean var1 = true;
        TileMapRenderer var10001;
        byte var2;
        switch (this.currentDirection) {
            case 0:
                var10001 = TileMapRenderer.getInstance();
                var2 = 0;
                var1 = this.isTileWalkable(var10001.getTileAt(0, this.worldX, this.worldY + super.secondaryStates[var2]));
                break;
            case 1:
                var10001 = TileMapRenderer.getInstance();
                var2 = 0;
                var1 = this.isTileWalkable(var10001.getTileAt(0, this.worldX + super.secondaryStates[var2], this.worldY));
                break;
            case 2:
                var10001 = TileMapRenderer.getInstance();
                var2 = 0;
                var1 = this.isTileWalkable(var10001.getTileAt(0, this.worldX, this.worldY - super.secondaryStates[var2]));
                break;
            case 3:
                var10001 = TileMapRenderer.getInstance();
                var2 = 0;
                var1 = this.isTileWalkable(var10001.getTileAt(0, this.worldX - super.secondaryStates[var2], this.worldY));
        }

        return var1 && TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) != 2 ? false : var1;
    }

    private boolean handleSpecialTile(byte var1, byte var2) {
        switch (var1) {
            case -1:
            case 0:
                this.setFacingState((byte) 1, (byte) var2);
                break;
            case 1:
                if (TileMapRenderer.getInstance().getTileAt(0, this.worldX, this.worldY) == 2) {
                    this.setFacingState((byte) 2, (byte) var2);
                }

                return false;
            case 2:
                if (!this.isTileWalkable((byte) 2)) {
                    return false;
                }

                this.setFacingState((byte) 2, (byte) var2);
                break;
            case 3:
                this.setFacingState((byte) 6, (byte) var2);
            case 4:
        }

        return true;
    }

    public final void openChest() {
        if (this.followTarget.getFacingDirection() == 1) {
            GameWorldManager.B().gameController.az();
            GameWorldManager.B().gameController.b("Bảo rương này đã trống");
        } else {
            String var1;
            if (((NPCEntity) this.followTarget).npcSubType == 0) {
                ((NPCEntity) this.followTarget).setDirection((byte) 1);
                if (this.canAddItem(((NPCEntity) this.followTarget).triggerValue, ((NPCEntity) this.followTarget).triggerType, (byte) ((NPCEntity) this.followTarget).triggerAction)) {
                    this.addItem(((NPCEntity) this.followTarget).triggerValue, ((NPCEntity) this.followTarget).triggerType, (byte) ((NPCEntity) this.followTarget).triggerAction);
                    var1 = null;
                    if (((NPCEntity) this.followTarget).triggerAction == 0) {
                        var1 = GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[4][((NPCEntity) this.followTarget).triggerValue][0]);
                    } else if (((NPCEntity) this.followTarget).triggerAction == 2) {
                        var1 = GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[3][((NPCEntity) this.followTarget).triggerValue][0]);
                    }

                    GameWorldManager.B().gameController.a((String) ("Đạt được: " + var1), ((NPCEntity) this.followTarget).triggerType);
                } else {
                    GameWorldManager.B().gameController.ay();
                }

                this.setFacingState((byte) 0, (byte) this.currentDirection);
            } else {
                if (((NPCEntity) this.followTarget).npcSubType == 1) {
                    if (this.hasItem((int) 17, (int) 1, (byte) 2)) {
                        ((NPCEntity) this.followTarget).setDirection((byte) 1);
                        this.removeItem(17, 1, (byte) 2);
                        if (this.canAddItem((int) ((NPCEntity) this.followTarget).triggerValue, ((NPCEntity) this.followTarget).triggerType, (byte) ((NPCEntity) this.followTarget).triggerAction)) {
                            this.addItem(((NPCEntity) this.followTarget).triggerValue, ((NPCEntity) this.followTarget).triggerType, (byte) ((NPCEntity) this.followTarget).triggerAction);
                            var1 = null;
                            if (((NPCEntity) this.followTarget).triggerAction == 0) {
                                var1 = GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[4][((NPCEntity) this.followTarget).triggerValue][0]);
                            } else if (((NPCEntity) this.followTarget).triggerAction == 2) {
                                var1 = GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[3][((NPCEntity) this.followTarget).triggerValue][0]);
                            }

                            GameWorldManager.B().gameController.a((String) ("Đạt được: " + var1), ((NPCEntity) this.followTarget).triggerType);
                        } else {
                            GameWorldManager.B().gameController.ay();
                        }
                    } else {
                        GameWorldManager.B().gameController.ax();
                    }

                    this.setFacingState((byte) 0, (byte) this.currentDirection);
                }

            }
        }
    }

    public final boolean startNPCInteraction() {
        if (this.followTarget == null) {
            return false;
        } else if (this.followTarget.getFacingDirection() != 0) {
            return false;
        } else {
            QuestManager.isQuestReady = true;
            if (((NPCEntity) this.followTarget).npcSubType != 7 && ((NPCEntity) this.followTarget).npcSubType != 6) {
                if (((NPCEntity) this.followTarget).npcSubType != 16) {
                    ((NPCEntity) this.followTarget).setDirection((byte) 1);
                }
            } else {
                for (int var1 = 0; var1 < this.followingNPCs.size(); ++var1) {
                    NPCEntity var2;
                    (var2 = (NPCEntity) this.followingNPCs.elementAt(var1)).setDirection((byte) 1);
                    var2.deactivateEffectObject();
                }

                this.followingNPCs.removeAllElements();
            }

            return true;
        }
    }

    private static boolean canAddToInventory(int var0, int var1, Vector var2) {
        for (int var4 = 0; var4 < var2.size(); ++var4) {
            int[] var3;
            if ((var3 = (int[]) var2.elementAt(var4))[0] == var0) {
                if (var3[1] < 99) {
                    return true;
                }

                return false;
            }
        }

        if (var1 > 99) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean hasInInventory(int var0, int var1, Vector var2) {
        for (int var4 = 0; var4 < var2.size(); ++var4) {
            int[] var3;
            if ((var3 = (int[]) var2.elementAt(var4))[0] == var0) {
                if (var3[1] - var1 >= 0) {
                    return true;
                }

                return false;
            }
        }

        return false;
    }

    private static boolean addToInventory(int var0, int var1, Vector var2) {
        int[] var3;
        for (int var4 = 0; var4 < var2.size(); ++var4) {
            if ((var3 = (int[]) var2.elementAt(var4))[0] == var0) {
                var3[1] += var1;
                if (var3[1] >= 99) {
                    var3[1] = 99;
                }

                return true;
            }
        }

        var3 = new int[]{var0, var1, 0};
        var2.addElement(var3);
        return false;
    }

    private static boolean removeFromInventory(int var0, int var1, Vector var2) {
        for (int var4 = 0; var4 < var2.size(); ++var4) {
            int[] var3;
            if ((var3 = (int[]) var2.elementAt(var4))[0] == var0) {
                var3[1] -= var1;
                if (var3[1] <= 0 && var3[2] == 0) {
                    var2.removeElementAt(var4);
                }

                return true;
            }
        }

        return false;
    }

    public final boolean hasOtherAlivePokemon(int var1) {
        int var2 = 0;

        for (int var3 = 0; var3 < this.partySize; ++var3) {
            if (var3 != var1 && this.partyPokemon[var3].isAlive()) {
                ++var2;
            }
        }

        if (var2 > 0) {
            return true;
        } else {
            return false;
        }
    }

    public final boolean canAddItem(int var1, int var2, byte var3) {
        switch (var3) {
            case 0:
                if (ResourceManager.gameDatabase[4][var1][5] == 0) {
                    return canAddToInventory(var1, var2, this.consumableInventory);
                }

                return canAddToInventory(var1, var2, this.equipmentInventory);
            case 2:
                if (var1 >= 12) {
                    return canAddToInventory(var1, var2, this.specialItems);
                } else {
                    var2 = var1;
                    PlayerCharacter var4 = this;

                    for (int var5 = 0; var5 < var4.keyItems.size(); ++var5) {
                        if (((int[]) var4.keyItems.elementAt(var5))[0] == var2) {
                            return false;
                        }
                    }

                    return true;
                }
            default:
                return false;
        }
    }

    public final boolean hasItem(int var1, int var2, byte var3) {
        switch (var3) {
            case 0:
                if (ResourceManager.gameDatabase[4][var1][5] == 0) {
                    return hasInInventory(var1, var2, this.consumableInventory);
                }

                return hasInInventory(var1, var2, this.equipmentInventory);
            case 2:
                return hasInInventory(var1, var2, this.specialItems);
            default:
                return false;
        }
    }

    public final boolean addItem(int var1, int var2, byte var3) {
        switch (var3) {
            case 0:
                if (ResourceManager.gameDatabase[4][var1][5] == 0) {
                    return addToInventory(var1, var2, this.consumableInventory);
                }

                return addToInventory(var1, var2, this.equipmentInventory);
            case 2:
                if (var1 >= 12) {
                    if (var1 == 17) {
                        return addToInventory(var1, var2 * 5, this.specialItems);
                    }

                    return addToInventory(var1, var2, this.specialItems);
                }

                int[] var4 = new int[]{var1, 0, 0};
                this.keyItems.addElement(var4);
                return true;
            default:
                return false;
        }
    }

    public final boolean removeItem(int var1, int var2, byte var3) {
        switch (var3) {
            case 0:
                if (ResourceManager.gameDatabase[4][var1][5] == 0) {
                    return removeFromInventory(var1, var2, this.consumableInventory);
                }

                return removeFromInventory(var1, var2, this.equipmentInventory);
            case 2:
                return removeFromInventory(var1, var2, this.specialItems);
            default:
                return false;
        }
    }

    public final int getItemCount(int var1, byte var2) {
        int var3;
        int[] var4;
        switch (var2) {
            case 0:
                if (ResourceManager.gameDatabase[4][var1][5] == 0) {
                    for (var3 = 0; var3 < this.consumableInventory.size(); ++var3) {
                        if ((var4 = (int[]) this.consumableInventory.elementAt(var3))[0] == var1) {
                            return var4[1];
                        }
                    }

                    return 0;
                } else {
                    for (var3 = 0; var3 < this.equipmentInventory.size(); ++var3) {
                        if ((var4 = (int[]) this.equipmentInventory.elementAt(var3))[0] == var1) {
                            return var4[1];
                        }
                    }

                    return 0;
                }
            case 2:
                for (var3 = 0; var3 < this.specialItems.size(); ++var3) {
                    if ((var4 = (int[]) this.specialItems.elementAt(var3))[0] == var1) {
                        return var4[1];
                    }
                }
        }

        return 0;
    }

    public final void refreshUsableItems() {
        if (this.usableItems == null) {
            this.usableItems = new Vector();
        } else {
            this.usableItems.removeAllElements();
        }

        int var1;
        int[] var2;
        for (var1 = 0; var1 < this.consumableInventory.size(); ++var1) {
            var2 = (int[]) this.consumableInventory.elementAt(var1);
            if (ResourceManager.gameDatabase[4][var2[0]][4] == 0) {
                this.usableItems.addElement(var2);
            }
        }

        for (var1 = 0; var1 < this.equipmentInventory.size(); ++var1) {
            var2 = (int[]) this.equipmentInventory.elementAt(var1);
            if (ResourceManager.gameDatabase[4][var2[0]][4] == 0) {
                this.usableItems.addElement(var2);
            }
        }

    }

    public final boolean learnSkill(int var1) {
        int[] var2;
        if (var1 == 0) {
            var2 = new int[]{var1, 0, 0};
        } else {
            var2 = new int[]{var1, 1, 0};
            if (var1 == 1 || var1 == 2 || var1 == 3 || var1 == 4) {
                this.vehicleStates[var1 - 1] = 1;
            }
        }

        this.skills.addElement(var2);
        return true;
    }

    public final void addSkillUses(int var1, int var2) {
        int[] var3;
        for (int var4 = 0; var4 < this.skills.size(); ++var4) {
            if ((var3 = (int[]) this.skills.elementAt(var4))[0] == var1) {
                var3[2] += var2;
                if (var3[2] >= 99) {
                    var3[2] = 99;
                }

                return;
            }
        }

        var3 = new int[]{var1, 0, var2};
        this.skills.addElement(var3);
    }

    public final int checkSkillUsage(int var1, int var2) {
        if (0 >= this.skills.size()) {
            return var2 > 99 ? 1 : -1;
        } else {
            int[] var3;
            if ((var3 = (int[]) this.skills.elementAt(0))[0] != var1 || var1 != 7 && var1 != 9 && var1 != 8) {
                return -1;
            } else {
                return var3[1] + var2 <= 99 ? 0 : 1;
            }
        }
    }

    public final boolean useSkillOnPokemon(int var1, int var2) {
        for (int var4 = 0; var4 < this.skills.size(); ++var4) {
            int[] var3;
            if ((var3 = (int[]) this.skills.elementAt(var4))[0] == var1 && (var1 == 7 || var1 == 9 || var1 == 8)) {
                this.partyPokemon[var2].applyNature((byte) var1);
                if (var3[2] > 0) {
                    int var10002 = var3[2]--;
                    this.skills.setElementAt(new int[]{var3[0], 0, var3[2]}, var4);
                } else if (var3[2] <= 0) {
                    this.skills.removeElementAt(var4);
                }

                return true;
            }

            if (var3[0] == var1 && var3[1] == 0) {
                this.skills.setElementAt(new int[]{var3[0], 1, var3[2]}, var4);
                break;
            }
        }

        return false;
    }

    public final void resetSkillUsage(int var1) {
        for (int var3 = 0; var3 < this.skills.size(); ++var3) {
            int[] var2;
            if ((var2 = (int[]) this.skills.elementAt(var3))[0] == var1 && var2[1] == 1) {
                this.skills.setElementAt(new int[]{var2[0], 0, var2[2]}, var3);
                return;
            }
        }

    }

    public final boolean isSkillActive(int var1) {
        for (int var2 = 0; var2 < this.skills.size(); ++var2) {
            int[] var3;
            if ((var3 = (int[]) this.skills.elementAt(var2))[0] == var1 && var3[1] == 1) {
                return true;
            }
        }

        return false;
    }

    public final boolean deactivateSkill(int var1) {
        if (var1 == -1) {
            return false;
        } else {
            for (int var2 = 0; var2 < this.keyItems.size(); ++var2) {
                int[] var3;
                if ((var3 = (int[]) this.keyItems.elementAt(var2))[0] == var1) {
                    var3[1] = 0;
                    return true;
                }
            }

            return false;
        }
    }

    public final void assignSkillToPokemon(int var1, int var2) {
        PokemonEntity var10000 = this.partyPokemon[var2];
        byte var6 = 5;
        byte var3;
        if (var10000.primaryStates[var6] >= 0) {
            PokemonEntity var10001 = this.partyPokemon[var2];
            var6 = 5;
            this.deactivateSkill(var10001.primaryStates[var6]);
            var10000 = this.partyPokemon[var2];
            var3 = -1;
            var6 = 5;
            var10000.primaryStates[var6] = var3;
        }

        int var4 = var1;
        PlayerCharacter var7 = this;
        int var5 = 0;

        int[] var11;
        boolean var12;
        while (true) {
            if (var5 >= var7.keyItems.size()) {
                var12 = false;
                break;
            }

            if ((var11 = (int[]) var7.keyItems.elementAt(var5))[0] == var4 && var11[1] == 1) {
                var12 = true;
                break;
            }

            ++var5;
        }

        if (var12) {
            this.deactivateSkill(var1);
            boolean var8 = false;

            for (var4 = 0; var4 < this.partySize; ++var4) {
                var10000 = this.partyPokemon[var4];
                var6 = 5;
                if (var10000.primaryStates[var6] == var1) {
                    var10000 = this.partyPokemon[var4];
                    var3 = -1;
                    var6 = 5;
                    var10000.primaryStates[var6] = var3;
                    var8 = true;
                    break;
                }
            }

            if (!var8) {
                for (var4 = 0; var4 < this.pokemonStorage.size(); ++var4) {
                    int[] var9;
                    if ((var9 = (int[]) this.pokemonStorage.elementAt(var2))[2] == var1) {
                        var9[2] = -1;
                        break;
                    }
                }
            }
        }

        var4 = var1;
        var7 = this;
        var5 = 0;

        while (true) {
            if (var5 >= var7.keyItems.size()) {
                var12 = false;
                break;
            }

            if ((var11 = (int[]) var7.keyItems.elementAt(var5))[0] == var4) {
                var11[1] = 1;
                var12 = true;
                break;
            }

            ++var5;
        }

        var10000 = this.partyPokemon[var2];
        short var10 = (short) var1;
        var6 = 5;
        var10000.primaryStates[var6] = var10;
    }

    public final byte getStorageStatus() {
        if (this.partySize < 6) {
            return 0;
        } else {
            return (byte) (this.pokemonStorage.size() < 100 ? 1 : 2);
        }
    }

    public final boolean hasStorageSpace() {
        return this.pokemonStorage.size() < 100;
    }

    public final void createPokemon(int var1, int var2, short var3, byte var4, short var5, byte var6, int[] var7) {
        this.partyPokemon[this.partySize] = new PokemonEntity();
        this.partyPokemon[this.partySize].initialize(var1, var2, var3, var4, var5, var6);
        this.partyPokemon[this.partySize].loadSkillData(var7);
        this.registerCreature((byte) ((byte) this.partyPokemon[this.partySize].getDatabaseValue((byte) 1)), var1, (byte) 2);
        ++this.partySize;
    }

    public final void createPokemonAtIndex(int var1, int var2, int var3, short var4, byte var5, short var6, byte var7, int[] var8) {
        this.partyPokemon[this.partySize] = new PokemonEntity();
        System.arraycopy(this.partyPokemon, var1, this.partyPokemon, var1 + 1, this.partySize - var1);
        this.partyPokemon[var1] = null;
        this.partyPokemon[var1] = new PokemonEntity();
        this.partyPokemon[var1].initialize(var2, var3, var4, var5, var6, var7);
        this.partyPokemon[var1].loadSkillData(var8);
        ++this.partySize;
    }

    public final void createPokemonFromData(int[] var1) {
        this.partyPokemon[this.partySize] = new PokemonEntity();
        this.partyPokemon[this.partySize].initialize(var1[0], var1[1], (short) var1[2], (byte) var1[3], (short) var1[4], (byte) var1[5]);
        this.partyPokemon[this.partySize].setExpAndFriendship((short) var1[6], var1[7], var1[8]);
        int[] var2 = new int[var1.length - 9];

        for (int var3 = 0; var3 < var2.length; ++var3) {
            var2[var3] = var1[var3 + 9];
        }

        this.partyPokemon[this.partySize].loadSkillData(var2);
        this.registerCreature((byte) ((byte) this.partyPokemon[this.partySize].getDatabaseValue((byte) 1)), var1[0], (byte) 2);
        ++this.partySize;
    }

    public final void removePokemonAt(int var1) {
        for (this.partyPokemon[var1] = null; var1 < this.partySize - 1; ++var1) {
            this.partyPokemon[var1] = this.partyPokemon[var1 + 1];
            this.partyPokemon[var1 + 1] = null;
        }

        --this.partySize;
    }

    public final void removePokemonById(int var1) {
        for (int var2 = 0; var2 < this.partySize; ++var2) {
            if (this.partyPokemon[var2].getSpeciesId() == var1) {
                this.removePokemonAt(var2);
                return;
            }
        }

    }

    public final int getMaxHealthDifference() {
        int[] var1 = new int[this.partySize];

        int var2;
        for (var2 = 0; var2 < this.partySize; ++var2) {
            PokemonEntity var10002 = this.partyPokemon[var2];
            byte var4 = 1;
            short var5 = var10002.primaryStates[var4];
            PokemonEntity var10003 = this.partyPokemon[var2];
            var4 = 1;
            var1[var2] = var5 - var10003.secondaryStates[var4];
        }

        var2 = var1[0];

        for (int stateIndex = 1; stateIndex < var1.length; ++stateIndex) {
            if (var2 < var1[stateIndex]) {
                var2 = var1[stateIndex];
            }
        }

        if (var2 == 0) {
            return -1;
        } else {
            return var2;
        }
    }

    public final void movePokemonToFront(int var1) {
        PokemonEntity var2;
        for (var2 = this.partyPokemon[var1]; var1 > 0; --var1) {
            this.partyPokemon[var1] = this.partyPokemon[var1 - 1];
        }

        this.partyPokemon[0] = var2;
    }

    public final void addPokemonToStorage(int var1, int var2, short var3, byte var4, short var5, byte var6, int var7, int var8, int var9, int[] var10) {
        int[] var11 = new int[9 + var10.length];
        var11[0] = var1;
        var11[1] = var2;
        var11[2] = var3;
        var11[3] = var4;
        var11[4] = var5;
        var11[5] = var6;
        var11[6] = var7;
        var11[7] = var8;
        var11[8] = var9;
        System.arraycopy(var10, 0, var11, 9, var10.length);
        this.pokemonStorage.addElement(var11);
        this.registerCreature((byte) ResourceManager.gameDatabase[0][var1][1], var1, (byte) 2);
    }

    public final void addPokemonDataToStorage(int[] var1) {
        this.pokemonStorage.addElement(var1);
        this.registerCreature((byte) ResourceManager.gameDatabase[0][var1[0]][1], var1[0], (byte) 2);
    }

    public final void removeFromStorage(int index) {
        this.pokemonStorage.removeElementAt(index);
    }

    public final void withdrawFromStorage(int var1) {
        PokemonEntity[] var10000 = this.partyPokemon;
        int var10001 = this.partySize;
        PokemonEntity var4 = new PokemonEntity();
        int[] var3 = (int[]) this.pokemonStorage.elementAt(var1);
        var4.initialize(var3[0], var3[1], (short) var3[2], (byte) var3[3], (short) var3[4], (byte) var3[5]);
        var4.setExpAndFriendship((short) var3[6], var3[7], var3[8]);
        int[] var2 = new int[var3.length - 9];

        for (int var5 = 0; var5 < var2.length; ++var5) {
            var2[var5] = var3[var5 + 9];
        }

        var4.loadSkillData(var2);
        var10000[var10001] = var4;
        ++this.partySize;
        this.removeFromStorage(var1);
    }

    public final void addToPokedex(short entryId) {
        this.pokedexEntries[this.pokedexCount] = entryId;
        ++this.pokedexCount;
    }

    public final void setBadgeState(byte var1, byte var2, byte var3) {
        this.badgeStates[var1][var2] = var3;
        if (this.badgeStates[0][0] == 2) {
            ScreenTransitionManager.a().a(ResourceManager.getDatabaseValue((byte) 2, (short) 0, (byte) 5) / 2, ResourceManager.getDatabaseValue((byte) 2, (short) 0, (byte) 5) / 2);
        }

    }

    public final byte getBadgeState(byte badgeId, byte slot) {
        return this.badgeStates[badgeId][slot];
    }

    public final void registerCreature(byte var1, int var2, byte var3) {
        int var6 = var2;
        byte var5 = var1;
        PlayerCharacter var4 = this;
        int var7 = 0;

        boolean var10000;
        while (true) {
            if (var7 >= var4.creatureCounts[var5]) {
                var10000 = true;
                break;
            }

            if (var4.creatureIndexes[var5][var7] == var6) {
                var10000 = false;
                break;
            }

            ++var7;
        }

        if (var10000) {
            this.creatureIndexes[var1][this.creatureCounts[var1]] = (byte) var2;
            ++this.creatureCounts[var1];
            if (var3 == 2) {
                ++this.totalCreaturesSeen;
                if (ResourceManager.gameDatabase[0][var2][22] == 2) {
                    ++this.rareCreaturesSeen;
                } else if (ResourceManager.gameDatabase[0][var2][22] == 1) {
                    ++this.commonCreaturesSeen;
                }
            }

            this.creatureRegistry[var1][var2 - this.categoryOffsets[var1]] = var3;
        } else {
            if (this.getCreatureStatus(var1, var2) <= 1) {
                if (var3 == 2) {
                    ++this.totalCreaturesSeen;
                    if (ResourceManager.gameDatabase[0][var2][22] == 2) {
                        ++this.rareCreaturesSeen;
                    } else if (ResourceManager.gameDatabase[0][var2][22] == 1) {
                        ++this.commonCreaturesSeen;
                    }
                }

                this.creatureRegistry[var1][var2 - this.categoryOffsets[var1]] = var3;
            }

        }
    }

    public final byte getCreatureStatus(byte categoryId, int creatureId) {
        return this.creatureRegistry[categoryId][creatureId - this.categoryOffsets[categoryId]];
    }

    public final void enableWildEncounter() {
        this.wildEncounterEnabled = true;
    }

    public final int getEncounterSteps() {
        return GameWorldManager.B().C25_f290 == 4 && GameWorldManager.B().C25_f291 == 1 ? GameUtils.getRandomInRange(4, 8) : GameUtils.getRandomInRange(this.minEncounterSteps, this.maxEncounterSteps);
    }

    private void updateEncounterCounter() {
        if (!GameWorldManager.B().C25_f348.hasActiveEvent() && QuestManager.questDialogState != 0) {
            --this.effectDuration;
            if (this.effectDuration <= 0) {
                GameWorldManager.B().e(false);
                this.effectDuration = 0;
            }

            --this.brightnessEffectDuration;
            if (this.brightnessEffectDuration == 0) {
                this.sprite.applyBrightnessEffect(0);
                this.brightnessEffectDuration = -1;
            }

            if (GameWorldManager.B().C() && this.brightnessEffectDuration <= 0) {
                if (this.wildEncounterEnabled && this.encounterStepsRemaining > 0 && this.vehicleStates[1] != 2 && this.vehicleStates[3] != 2) {
                    --this.encounterStepsRemaining;
                }

            }
        }
    }

    public final boolean shouldTriggerEncounter() {
        return this.encounterStepsRemaining <= 0;
    }

    public final int getGold() {
        return this.gold;
    }

    public final void addGold(int amount) {
        this.gold += amount;
    }

    public final void setGold(int amount) {
        this.gold = amount;
    }

    public final boolean hasEnoughGold(int amount) {
        return this.gold >= amount;
    }

    public final int getBadges() {
        return this.badges;
    }

    public final void addBadges(int amount) {
        this.badges += amount;
    }

    public final void setBadges(int amount) {
        this.badges = amount;
    }

    public final boolean hasEnoughBadges(int amount) {
        return this.badges >= amount;
    }

    public final boolean canAfford(int itemId, int cost, int databaseIndex) {
        return ResourceManager.gameDatabase[databaseIndex][itemId][4] == 0 ? this.hasEnoughGold(cost) : this.hasEnoughBadges(cost);
    }

    public final void addStarterPokemon() {
        this.createPokemon(68, 7, (short) -1, (byte) 2, (short) 2, (byte) -1, new int[]{1, 40, 45});
        this.learnSkill(0);
    }

    public final boolean loadSpriteSet(int spriteId, boolean forceReload) {
        super.loadSpriteSet(spriteId, forceReload);
        if (this.brightnessEffectDuration > 0) {
            this.sprite.applyBrightnessEffect(1);
        }

        return true;
    }

    public final void refreshAnimation() {
        this.setFacingState((byte) 0, this.currentDirection);
    }
}