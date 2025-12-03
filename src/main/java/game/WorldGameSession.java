package game;

import engine.GameEngineBase;
import engine.GameUtils;
import engine.entity.*;
import engine.graphics.*;
import layout.DialogManager;
import me.kitakeyos.ManagedInputStream;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.*;
import java.util.Vector;

public final class WorldGameSession extends GameEngineBase {
    private static WorldGameSession instance;
    public TileMapRenderer tileMapRenderer;
    private CameraController cameraController;
    public WorldRenderer worldRenderer;
    public PlayerCharacter player;
    public NPCEntity[] NPCs;
    public Vector activeNpcList;
    private int currentMapId;
    public int currentRegionId;
    public int currentAreaId;
    private int evolutionNotifyIndex;
    public short spawnPositionX;
    public short spawnPositionY;
    public int lastInteractedNpcId;
    public String areaName;
    public static int[] REGION_OFFSETS = new int[]{0, 2, 9, 17, 25, 38, 45, 47, 60, 67, 75, 90};
    public static Image backgroundImage = null;
    private static Image goldIcon = null;
    public static EffectEntity activeEffect = null;
    private static RecordStoreManager[] recordStores = new RecordStoreManager[10];
    private static byte[][][] npcStateData = null;
    private static short[][][] npcPositionData = null;
    private static boolean[][] areaVisitedFlags = null;
    private static String[] npcDialogNames;
    private static short[][] petAreaData;
    private static Vector grassEncounterPool = new Vector();
    private static Vector waterEncounterPool = new Vector();
    private static Vector caveEncounterPool = new Vector();
    private static Vector specialEncounterPool = new Vector();
    public GameObject followingPet;
    private GameObject followTarget;
    public GameObject interactionMarker;
    public static int totalBattleCount = 0;
    public static byte eggProductionNotified = 0;
    public static byte dialogPortraitId;
    public static byte dialogTextId;
    public static short currentInteractNpcId = -1;
    public static short previousInteractNpcId = -1;
    public static byte playerDirection = 0;
    public static boolean isGameLoaded = false;
    public static int cameraFollowNpcId = -1;
    public static int cameraFixedX = 0;
    public static int cameraFixedY = 0;
    public static int screenWidth = getScreenWidth();
    public static int screenHeight = getScreenHeight();
    private static byte[] NO_MAP_REGIONS = new byte[]{9, 10, 11};
    private int[] vehicleOffsets;
    private int[] badgeCheckData;
    private short[] portLocationData;
    private static String[] RMS_NAMES = new String[]{"PK6_RMS_ACTOR", "PK6_RMS_WORLD", "PK6_RMS_EVENT", "PK6_RMS_RMS",
            "PK6_RMS_SMS", "PK6_RMS_CNTSMS", "PK6_RMS_GOLD", "PK6_RMS_POKPET", "PK6_RMS_CONITEM", "PK6_RMS_PETBALL"};
    public static boolean hasPurchasedSms = false;
    public static Vector battlePartyBackup = null;
    public static Vector evolutionQueue;
    public static byte tutorialStep;
    public static Vector pendingEvolutions = null;
    public static byte evolutionProcessState = 0;
    public static boolean showStatusBar = false;
    protected static boolean isTutorialActive = false;
    public static byte[] tutorialTargetPokemon = new byte[2];
    private Image[] diveImages;
    public AudioManager audioManager;
    private static byte[][] mediaConfigData = null;
    public static byte defaultMusicTrack = -1;
    public static boolean hasEventMusic;
    private static byte[] eventMusicMapping = null;
    private static byte eventMusicCount = -1;
    public QuestManager questManager;
    public static String[] dialogTexts;
    private int loadingStep;
    private String tutorialHintText;
    private byte currentWorldMapId;
    private byte gymLocationIndex;
    private byte portLocationIndex;
    private byte selectedMapNode;
    private int worldMapScrollX;
    private int worldMapScrollY;
    private int worldMapTargetX;
    private int worldMapTargetY;
    private boolean isWorldMapScrolling;
    private GameObject mapCursor;
    private GameObject[] mapScrollArrows;
    private int mapScrollSpeed;
    private int[] gymLocations;
    private int[] portLocations;
    private int[] worldMapColors;
    private int[][] worldMapNodes;
    private int[] mapNodeSizes;
    public static byte currentTileType = -1;
    private boolean wildEncounterEnabled;
    private boolean[] badgeCollected;
    private byte[] badgeRequirements;
    private byte currentBadgeTier;
    private byte achievedBadgeTier;
    private boolean badgeRewardPending;

    public static WorldGameSession getInstance() {
        if (instance == null) {
            instance = new WorldGameSession();
        }

        return instance;
    }

    public WorldGameSession() {
        TextRenderingEngine.a();
        this.activeNpcList = new Vector();
        this.currentRegionId = 0;
        this.currentAreaId = 0;
        this.evolutionNotifyIndex = 0;
        this.spawnPositionX = 224;
        this.spawnPositionY = 496;
        this.lastInteractedNpcId = -1;
        this.areaName = "Gỗ thô";
        this.followingPet = null;
        this.followTarget = null;
        this.interactionMarker = null;
        this.vehicleOffsets = new int[]{21, 35, 50, 0, 45};
        this.badgeCheckData = new int[]{9, 0, 20, 3, 9, 1, 17, 1, 9, 2, 9, 4, 9, 6, 86, 5, 9, 6, 58, 6, 9, 5, 21, 2, 9, 4, 3,
                0};
        this.portLocationData = new short[]{1, 5, 0, 616, 3, 6, 0, 617, 4, 0, 0, 618, 5, 2, 0, 619, 6, 0, 1, 620};
        this.audioManager = null;
        this.loadingStep = 0;
        this.tutorialHintText = "Ngoại trừ tiến hóa, sủng vật còn có thể dị hoá, dị hoá sau sủng vật đem càng cụ tính công kích. Mặt khác từng chủ thành liên minh huấn luyện sư cũng sẽ cung cấp tiến hóa cùng dị hoá phục vụ, ngươi có thể thường đi xem.";
        this.isWorldMapScrolling = false;
        this.mapCursor = null;
        this.mapScrollArrows = null;
        this.mapScrollSpeed = 8;
        this.gymLocations = new int[]{2, 1, 73, 158, 3, 3, 216, 165, 4, 5, 161, 338, 5, 3, 111, 385, 5, 5, 112, 124, 6, 1,
                140, 100, 7, 2, 48, 58};
        this.portLocations = new int[]{1, 5, 265, 113, 3, 6, 281, 192, 4, 0, 24, 144, 5, 2, 88, 175, 6, 0, 55, 190};
        this.worldMapColors = new int[]{16735795, 5708544, 5693667, 28273, 7796622, 1924393, 16774529, 7760896, 3291479,
                10268671, 2038828, 13341951, 4443391, 16777215, 1862959, 13886935};
        this.worldMapNodes = new int[][]{
                {0, 0, 1, 0, 386, 5, 5, 5, 0, 1, 1, 387, 5, 5, 0, 5, 1, 2, 388, 5, 5, 0, 10, 1, 3, 389, 5, 5, 5, 10, 1, 4,
                        390, 5, 5, 10, 10, 1, 5, 391, 5, 5, 10, 15, 1, 6, 392, 5, 5, 10, 20, -1, -1, 518, 5, 5},
                {0, 0, -1, -1, 517, 5, 5, 0, 5, 2, 0, 393, 5, 5, 0, 10, 2, 1, 394, 5, 5, 0, 15, 2, 2, 395, 5, 5, 5, 10, 2,
                        3, 396, 5, 5, 5, 5, 2, 4, 397, 5, 5, 5, 15, 2, 5, 398, 5, 5, 5, 20, 2, 6, 399, 5, 5, 5, 25, 2, 7, 400,
                        5, 5},
                {15, 0, -1, -1, 518, 5, 5, 15, 5, 3, 0, 401, 5, 5, 15, 10, 3, 1, 402, 5, 5, 10, 10, 3, 2, 403, 5, 5, 10,
                        15, 3, 3, 404, 5, 5, 5, 15, 3, 4, 405, 5, 5, 0, 15, 3, 5, 406, 5, 5, 15, 15, 3, 6, 407, 5, 5, 15, 20,
                        3, 7, 408, 5, 5},
                {0, 15, 4, 0, 409, 5, 5, 5, 15, 4, 1, 410, 5, 5, 10, 15, 4, 5, 414, 5, 5, 10, 20, 4, 6, 415, 5, 5, 15, 20,
                        4, 7, 416, 5, 5, 20, 20, 4, 8, 417, 5, 5, 15, 15, 4, 9, 418, 5, 5, 20, 15, 4, 10, 419, 5, 5, 15, 10,
                        4, 11, 420, 5, 5, 15, 5, 4, 12, 421, 5, 5, 0, 10, 4, 2, 411, 5, 5, 5, 10, 4, 3, 412, 5, 5, 10, 10, 4,
                        4, 413, 5, 5, 15, 0, -1, -1, 524, 5, 5},
                {10, 5, 5, 0, 422, 5, 5, 5, 5, 5, 1, 423, 5, 5, 0, 5, 5, 2, 424, 5, 5, 5, 0, 5, 3, 425, 5, 5, 15, 5, 5, 4,
                        426, 5, 5, 20, 5, 5, 5, 427, 5, 5, 18, 0, 5, 6, 428, 5, 5, 10, 10, -1, -1, 522, 5, 5},
                {0, 5, 6, 0, 429, 5, 5, 0, 0, 6, 1, 430, 5, 5},
                {5, 15, 7, 0, 431, 5, 5, 5, 10, 7, 1, 432, 5, 5, 5, 5, 7, 2, 433, 5, 5, 0, 5, 7, 3, 434, 5, 5, 0, 0, 7, 4,
                        435, 5, 5, 0, 10, 7, 5, 436, 5, 5, 0, 15, 7, 6, 437, 5, 5, 10, 5, 7, 7, 438, 5, 5, 10, 0, 7, 8, 439,
                        5, 5, 15, 0, 7, 9, 440, 5, 5, 15, 5, 7, 10, 441, 5, 5, 10, 10, 7, 11, 442, 5, 5, 10, 15, 7, 12, 443,
                        5, 5},
                {5, 10, 8, 0, 444, 5, 5, 5, 15, 8, 1, 445, 5, 5, 0, 15, 8, 2, 446, 5, 5, 0, 10, 8, 3, 447, 5, 5, 0, 5, 8,
                        4, 448, 5, 5, 5, 5, 8, 5, 449, 5, 5, 5, 0, 8, 6, 450, 5, 5}};
        this.mapNodeSizes = new int[]{3, 5, 2, 6, 4, 5, 5, 5, 5, 3, 1, 2, 4, 4, 2, 4};
        this.wildEncounterEnabled = false;
        this.badgeCollected = new boolean[]{false, false, false, false, false, false, false};
        this.badgeRequirements = new byte[]{10, 15, 20, 30, 40, 50, 100};
        this.badgeRewardPending = false;
        this.tileMapRenderer = TileMapRenderer.getInstance();
        this.cameraController = CameraController.getInstance();
        this.worldRenderer = new WorldRenderer();
    }

    public final boolean hasWildEncounterData() {
        return petAreaData[REGION_OFFSETS[this.currentRegionId] + this.currentAreaId][2] != -1;
    }

    public static byte getEventMusicByIndex(byte var0, byte var1) {
        if (eventMusicCount != -1) {
            for (int var2 = 0; var2 < eventMusicCount; ++var2) {
                if (eventMusicMapping[var2 * 3] == var0) {
                    switch (var1) {
                        case 0:
                            return eventMusicMapping[var2 * 3 + 1];
                        case 1:
                            return eventMusicMapping[var2 * 3 + 2];
                    }
                }
            }
        }

        return -1;
    }

    public final boolean initializeGame() {
        try {
            this.startGameTimer();
            if (recordStores == null) {
                recordStores = new RecordStoreManager[10];
            }

            for (int var2 = 0; var2 < recordStores.length; ++var2) {
                if (recordStores[var2] == null) {
                    recordStores[var2] = new RecordStoreManager(RMS_NAMES[var2], 1);
                }
            }

            this.questManager = QuestManager.getInstance();
            this.questManager.setGameEngine((GameEngineBase) this);
            this.player = PlayerCharacter.getInstance();
            tutorialTargetPokemon[0] = tutorialTargetPokemon[1] = -1;
            if (QuestManager.questState != 0) {
                ResourceManager.clearImageCache();
            }

            if (npcStateData == null) {
                npcStateData = new byte[127][][];
                npcPositionData = new short[127][][];
                areaVisitedFlags = new boolean[127][2];
            }

            if (!this.player.isInitialized) {
                if (inputEnabled) {
                    this.loadWorldState();
                    this.loadQuestState();
                }

                loadPaymentStatus();
            }

            this.a(this.currentRegionId, this.currentAreaId, "/data/event/");
            ScreenTransitionManager.loadBattleNpcData();
            WorldGameSession var1 = this;
            petAreaData = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/petArea.mid"));
            int var3;
            if (this.hasWildEncounterData()) {
                int[] var7 = new int[petAreaData[REGION_OFFSETS[this.currentRegionId] + this.currentAreaId].length - 5];

                for (var3 = 0; var3 < var7.length; ++var3) {
                    var7[var3] = petAreaData[REGION_OFFSETS[var1.currentRegionId] + var1.currentAreaId][var3 + 5];
                }

                for (var3 = 0; var3 < var7.length / 4; ++var3) {
                    int[] var6 = new int[4];
                    switch (var7[(var3 << 2) + 1]) {
                        case 0:
                            System.arraycopy(var7, var3 << 2, var6, 0, var6.length);
                            grassEncounterPool.addElement(var6);
                            break;
                        case 1:
                            System.arraycopy(var7, var3 << 2, var6, 0, var6.length);
                            waterEncounterPool.addElement(var6);
                            break;
                        case 2:
                            System.arraycopy(var7, var3 << 2, var6, 0, var6.length);
                            caveEncounterPool.addElement(var6);
                        case 3:
                        default:
                            break;
                        case 4:
                            System.arraycopy(var7, var3 << 2, var6, 0, var6.length);
                            specialEncounterPool.addElement(var6);
                    }
                }
            }

            var1 = this;
            byte[][] var10000 = mediaConfigData = GameUtils.readByteMatrix(GameUtils.openInputStream("/data/script/media.mid"));
            int var4 = this.currentAreaId;
            var3 = this.currentRegionId;
            byte var9;
            if ((var9 = var10000[REGION_OFFSETS[var3] + var4][0]) == -1) {
                hasEventMusic = false;
            } else {
                var4 = this.currentAreaId;
                var3 = this.currentRegionId;
                if (this.questManager.questStates[REGION_OFFSETS[var3] + var4][var9] == 3) {
                    hasEventMusic = false;
                } else {
                    hasEventMusic = true;
                }
            }

            var4 = this.currentAreaId;
            var3 = this.currentRegionId;
            defaultMusicTrack = mediaConfigData[REGION_OFFSETS[var3] + var4][1];
            var4 = this.currentAreaId;
            var3 = this.currentRegionId;
            int var14 = REGION_OFFSETS[var3] + var4;
            byte[] var11;
            if ((eventMusicCount = mediaConfigData[getAreaIndex(this.currentRegionId, this.currentAreaId)][3]) == -1) {
                if (!this.hasWildEncounterData()) {
                    (var11 = new byte[1])[0] = defaultMusicTrack;
                } else {
                    (var11 = new byte[2])[0] = defaultMusicTrack;
                    var11[1] = defaultMusicTrack;
                }
            } else {
                if (!this.hasWildEncounterData()) {
                    var11 = new byte[(eventMusicCount << 1) + 1];

                    for (var3 = 0; var3 < eventMusicCount; ++var3) {
                        var11[var3 << 1] = mediaConfigData[getAreaIndex(var1.currentRegionId, var1.currentAreaId)][4 + var3 * 3 + 1];
                        var11[(var3 << 1) + 1] = mediaConfigData[getAreaIndex(var1.currentRegionId, var1.currentAreaId)][4 + var3 * 3 + 2];
                    }

                    var11[eventMusicCount << 1] = defaultMusicTrack;
                } else {
                    var11 = new byte[(eventMusicCount << 1) + 2];

                    for (var3 = 0; var3 < eventMusicCount; ++var3) {
                        var11[var3 << 1] = mediaConfigData[getAreaIndex(var1.currentRegionId, var1.currentAreaId)][4 + var3 * 3 + 1];
                        var11[(var3 << 1) + 1] = mediaConfigData[getAreaIndex(var1.currentRegionId, var1.currentAreaId)][4 + var3 * 3 + 2];
                    }

                    var11[eventMusicCount << 1] = defaultMusicTrack;
                    var11[(eventMusicCount << 1) + 1] = 4;
                }

                eventMusicMapping = new byte[eventMusicCount * 3];
                System.arraycopy(mediaConfigData[getAreaIndex(var1.currentRegionId, var1.currentAreaId)], 4, eventMusicMapping, 0, eventMusicCount * 3);
            }

            if (var1.audioManager == null) {
                var1.audioManager = new AudioManager(7, -1, (byte) 0, "/data/sound/");
            }

            var1.audioManager.preloadMusicTracks(var11);
            var1.audioManager.setMasterVolume(GameScreenManager.getInstance().difficultyLevel);
            var1 = this;
            InputStream var12 = GameUtils.openInputStream("/data/script/petRide.mid");
            this.player.vehicleUsability = GameUtils.readByteMatrix(var12)[REGION_OFFSETS[this.currentRegionId] + this.currentAreaId];
            if (this.player.currentVehicleType >= 0 && !this.player.isVehicleUsable(this.player.currentVehicleType)) {
                this.player.resetToDefaultSprite();
            }

            this.currentWorldMapId = this.player.vehicleUsability[4];
            this.gymLocationIndex = -1;

            for (var9 = 0; var9 < var1.gymLocations.length / 4; ++var9) {
                if (var1.currentRegionId == var1.gymLocations[var9 << 2] && var1.currentAreaId == var1.gymLocations[(var9 << 2) + 1]) {
                    var1.gymLocationIndex = var9;
                    break;
                }
            }

            var1.portLocationIndex = -1;

            for (var9 = 0; var9 < var1.portLocations.length / 4; ++var9) {
                if (var1.currentRegionId == var1.portLocations[var9 << 2] && var1.currentAreaId == var1.portLocations[(var9 << 2) + 1]) {
                    var1.portLocationIndex = var9;
                    break;
                }
            }

            var1 = this;
            backgroundImage = null;
            short[][] var13 = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/backPic.mid"));

            for (var3 = 0; var3 < var13.length; ++var3) {
                if (var13[var3][0] == var1.currentRegionId && var13[var3][1] == var1.currentAreaId) {
                    if (var13[var3][2] == 0) {
                        backgroundImage = GameUtils.loadImage("/data/img/", "img_" + var13[var3][3]);
                    } else if (var13[var3][2] == 1) {
                        GameEngineBase.setBackgroundColor(var13[var3][3] << 16 | var13[var3][4] << 8 | var13[var3][5]);
                    }
                    break;
                }

                GameEngineBase.setBackgroundColor(2996676);
            }

            goldIcon = GameUtils.loadImage("/data/tex/", "gold");
            GameUtils.loadImage("/data/img/", "img_10023");
            if (this.player.effectDuration > 0) {
                this.setWildPokemonVisible(true);
            }

            this.areaName = getLocalizedText(384 + REGION_OFFSETS[this.currentRegionId] + this.currentAreaId);
            this.tileMapRenderer.loadMap(this.currentMapId);
            this.tileMapRenderer.setCameraPosition(0, 0);
            this.worldRenderer.setTileMapRenderer(this.tileMapRenderer);
            this.initializeNPCPatrolRoutes();
            if (isGameLoaded) {
                QuestManager.isQuestActive = false;
                this.spawnPlayer();
                this.cameraController.setFollowEntity(this.player, true);
                this.worldRenderer.setCameraController(this.cameraController);
                this.worldRenderer.updateWorld();
            } else {
                if (!this.player.isInitialized) {
                    short[] var8 = new short[]{this.spawnPositionX, this.spawnPositionY, (short) playerDirection, 4, 4, 8, 40, 100, 0};
                    this.player.initializeFromData(var8);
                    this.player.addStarterPokemon();
                }

                if (cameraFollowNpcId == -1) {
                    this.cameraController.setFixedPosition(cameraFixedX, cameraFixedY, true);
                    this.worldRenderer.setCameraController(this.cameraController);
                    this.worldRenderer.updateWorld();
                } else {
                    this.cameraController.setFollowEntity(this.NPCs[cameraFollowNpcId], true);
                    this.worldRenderer.setCameraController(this.cameraController);
                    this.worldRenderer.updateWorld();
                }

                this.player.setAlternativeRender(false);
                isGameLoaded = true;
            }

            this.registerNPCsToWorld();
            if (this.currentRegionId == 3 && this.currentAreaId == 7) {
                if (this.player.brightnessEffectDuration > 0) {
                    this.player.brightnessEffectDuration = 0;
                    this.player.applyBrightnessEffect(0);
                }

                this.diveImages = new Image[4];

                for (int var10 = 0; var10 < this.diveImages.length; ++var10) {
                    this.diveImages[var10] = GameUtils.loadImage("/data/tex/", "down" + var10);
                }

                this.player.resetToDefaultSprite();
                this.player.activateVehicle(0);
            }

            if (this.currentRegionId == 5 && this.currentAreaId == 6
                    || this.currentRegionId == 4 && (this.currentAreaId == 3 || this.currentAreaId == 4)) {
                if (this.player.badgeStates[0][0] == 2) {
                    ScreenTransitionManager.getInstance().initSpotlight((byte) 0, this.cameraController.getWorldX(),
                            this.cameraController.getWorldY() - this.vehicleOffsets[this.player.currentVehicleType + 1], getScreenWidth(),
                            getScreenHeight(), 110, 110);
                } else {
                    ScreenTransitionManager.getInstance().initSpotlight((byte) 0, this.cameraController.getWorldX(),
                            this.cameraController.getWorldY() - this.vehicleOffsets[this.player.currentVehicleType + 1], getScreenWidth(),
                            getScreenHeight(), 50, 50);
                }
            } else {
                ScreenTransitionManager.getInstance().disableSpotlight((byte) -1);
            }

            this.gameController = DialogUIManager.a();
            this.gameController.a((GameEngineBase) this);
            this.dialogManager = DialogManager.getInstance();
            this.questManager.updateQuestEffects();
            this.questManager.update();
            showStatusBar = true;
            this.changeState((byte) 0);
            if (QuestManager.questState == 2) {
                QuestManager.questState = 0;
            }

            if (!hasEventMusic) {
                this.audioManager.playBackgroundMusic(defaultMusicTrack, 1);
            }

            stopGameTimer();
        } catch (Exception var5) {
            DebugLogger.logError(var5, "init");
        }

        return true;
    }

    private void registerNPCsToWorld() {
        for (int var1 = 0; var1 < this.NPCs.length; ++var1) {
            this.NPCs[var1].updateInteractableState();
            this.worldRenderer.addEntity((GameEntity) this.NPCs[var1]);
        }

    }

    public final void createInteractionMarker(byte var1, int var2, int var3, GameObject var4) {
        if (this.interactionMarker == null) {
            this.interactionMarker = new GameObject();
            this.interactionMarker.loadSpriteSet(259, false);
            this.interactionMarker.sprite.setAnimationProperties(var1, (byte) -1);
            this.worldRenderer.addEntity(this.interactionMarker);
            this.interactionMarker.sprite.applyColorEffects();
            this.interactionMarker.layer = 0;
            this.interactionMarker.activate();
        }

        this.interactionMarker.setWorldPosition(var2, var3);
        this.interactionMarker.followTarget = var4;
    }

    public final void removeInteractionMarker() {
        if (this.interactionMarker != null) {
            this.worldRenderer.removeEntity(this.interactionMarker);
            this.interactionMarker = null;
        }

    }

    public final void loadFollowingPet(int var1) {
        if (this.followingPet == null) {
            this.followingPet = new GameObject();
            this.followingPet.loadSpriteSet(var1, false);
            this.followingPet.sprite.applyColorEffects();
            this.followingPet.layer = 1;
        }

    }

    public final void attachFollowingPet(GameObject var1) {
        if (this.followingPet != null) {
            this.followTarget = var1;
            this.followingPet.followTarget = var1;
            this.followingPet.initializeMovementTrail(var1.sprite.getCurrentAnimationId());
            this.followingPet.activate();
            this.worldRenderer.addEntity(this.followingPet);
            if (backgroundImage != null) {
                this.followingPet.setAlternativeRender(true);
            } else {
                this.followingPet.setAlternativeRender(false);
            }
        }
    }

    public final void removeFollowingPet() {
        if (this.followingPet != null) {
            this.worldRenderer.removeEntity(this.followingPet);
            this.followingPet = null;
        }

    }

    private boolean savePlayerData(PlayerCharacter var1) {
        try {
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            DataOutputStream var3 = new DataOutputStream(var2);
            if (this.currentRegionId == 9) {
                var3.writeShort(super.gameController.C9_f158[(this.currentAreaId << 2) + 2]);
                var3.writeShort(super.gameController.C9_f158[(this.currentAreaId << 2) + 3]);
                var3.writeByte(2);
            } else if (this.currentRegionId == 3 && this.currentAreaId == 7) {
                var3.writeShort(240);
                var3.writeShort(40);
                var3.writeByte(0);
            } else {
                var3.writeShort(var1.worldX);
                var3.writeShort(var1.worldY);
                var3.writeByte(var1.currentDirection);
            }

            int var4;
            int var5;
            for (var4 = 0; var4 < var1.badgeStates.length; ++var4) {
                for (var5 = 0; var5 < var1.badgeStates[var4].length; ++var5) {
                    var3.writeByte(var1.badgeStates[var4][var5]);
                }
            }

            for (var4 = 0; var4 < var1.vehicleStates.length; ++var4) {
                var3.writeByte(var1.vehicleStates[var4]);
            }

            for (var4 = 0; var4 < var1.creatureRegistry.length; ++var4) {
                for (var5 = 0; var5 < var1.creatureRegistry[var4].length; ++var5) {
                    var3.writeByte(var1.creatureRegistry[var4][var5]);
                }
            }

            for (var4 = 0; var4 < var1.creatureCounts.length; ++var4) {
                var3.writeByte(var1.creatureCounts[var4]);
            }

            for (var4 = 0; var4 < var1.creatureIndexes.length; ++var4) {
                for (var5 = 0; var5 < var1.creatureIndexes[var4].length; ++var5) {
                    var3.writeByte(this.player.creatureIndexes[var4][var5]);
                }
            }

            var3.writeByte(this.player.commonCreaturesSeen);
            var3.writeByte(this.player.rareCreaturesSeen);
            var3.writeByte(this.player.totalCreaturesSeen);
            var3.writeByte(this.player.pokedexCount);

            for (var4 = 0; var4 < this.player.pokedexEntries.length; ++var4) {
                var3.writeByte(this.player.pokedexEntries[var4]);
            }

            if (!this.savePartyPokemon()) {
                return false;
            } else if (!this.saveEquipmentInventory()) {
                return false;
            } else if (!this.saveConsumableInventory()) {
                return false;
            } else {
                var3.writeInt(var1.keyItems.size());

                int var6;
                int[] var12;
                for (var4 = 0; var4 < var1.keyItems.size(); ++var4) {
                    var12 = (int[]) var1.keyItems.elementAt(var4);
                    var3.writeInt(var12.length);

                    for (var6 = 0; var6 < var12.length; ++var6) {
                        var3.writeInt(var12[var6]);
                    }
                }

                var3.writeInt(var1.specialItems.size());

                for (var4 = 0; var4 < var1.specialItems.size(); ++var4) {
                    var12 = (int[]) var1.specialItems.elementAt(var4);
                    var3.writeInt(var12.length);

                    for (var6 = 0; var6 < var12.length; ++var6) {
                        var3.writeInt(var12[var6]);
                    }
                }

                var3.writeInt(var1.skills.size());

                for (var4 = 0; var4 < var1.skills.size(); ++var4) {
                    var12 = (int[]) var1.skills.elementAt(var4);
                    var3.writeInt(var12.length);

                    for (var6 = 0; var6 < var12.length; ++var6) {
                        var3.writeInt(var12[var6]);
                    }
                }

                for (var4 = 0; var4 < var1.gameFlags.length; ++var4) {
                    var3.writeBoolean(var1.gameFlags[var4]);
                }

                if (!this.saveGoldAndBadges()) {
                    return false;
                } else {
                    if (evolutionQueue == null) {
                        evolutionQueue = new Vector();
                    }

                    var3.writeByte(evolutionQueue.size());

                    for (var4 = 0; var4 < evolutionQueue.size(); ++var4) {
                        String var13 = (String) evolutionQueue.elementAt(var4);
                        var3.writeByte(GameUtils.parseByte(var13));
                    }

                    for (var4 = 0; var4 < this.badgeCollected.length; ++var4) {
                        var3.writeBoolean(this.badgeCollected[var4]);
                    }

                    if (this.followingPet == null) {
                        var3.writeByte(-1);
                    } else {
                        var3.writeByte(this.followingPet.sprite.spriteSetId);
                    }

                    var3.write(this.player.outfitType);
                    var3.writeInt(totalBattleCount);
                    var3.writeBoolean(isTutorialActive);
                    long var9 = GameScreenManager.getInstance().storyStartTime + GameScreenManager.getInstance().worldMapTime
                            - GameScreenManager.getInstance().currentTime;
                    var3.writeLong(var9);
                    var3.writeByte(this.player.currentVehicleType);
                    var3.writeInt(var1.pokemonStorage.size());

                    for (var4 = 0; var4 < var1.pokemonStorage.size(); ++var4) {
                        int[] var14 = (int[]) var1.pokemonStorage.elementAt(var4);
                        var3.writeInt(var14.length);

                        for (var5 = 0; var5 < var14.length; ++var5) {
                            var3.writeInt(var14[var5]);
                        }
                    }

                    recordStores[0].writeData(var2);
                    var2.close();
                    var3.close();
                    return true;
                }
            }
        } catch (Exception var11) {
            System.out.println(" savePlayer ex = " + var11);
            return false;
        }
    }

    private boolean loadPlayerData(PlayerCharacter var1) {
        try {
            ByteArrayInputStream var2 = new ByteArrayInputStream(recordStores[0].readData());
            DataInputStream var3 = new DataInputStream(var2);
            this.spawnPositionX = var3.readShort();
            this.spawnPositionY = var3.readShort();
            short var4 = var3.readByte();

            int var5;
            int var6;
            for (var5 = 0; var5 < var1.badgeStates.length; ++var5) {
                for (var6 = 0; var6 < var1.badgeStates[var5].length; ++var6) {
                    var1.badgeStates[var5][var6] = var3.readByte();
                }
            }

            for (var5 = 0; var5 < var1.vehicleStates.length; ++var5) {
                var1.vehicleStates[var5] = var3.readByte();
            }

            for (var5 = 0; var5 < var1.creatureRegistry.length; ++var5) {
                for (var6 = 0; var6 < var1.creatureRegistry[var5].length; ++var6) {
                    var1.creatureRegistry[var5][var6] = var3.readByte();
                }
            }

            for (var5 = 0; var5 < var1.creatureCounts.length; ++var5) {
                var1.creatureCounts[var5] = var3.readByte();
            }

            for (var5 = 0; var5 < var1.creatureIndexes.length; ++var5) {
                for (var6 = 0; var6 < var1.creatureIndexes[var5].length; ++var6) {
                    this.player.creatureIndexes[var5][var6] = var3.readByte();
                }
            }

            this.player.commonCreaturesSeen = var3.readByte();
            this.player.rareCreaturesSeen = var3.readByte();
            this.player.totalCreaturesSeen = var3.readByte();
            this.player.pokedexCount = var3.readByte();

            for (var5 = 0; var5 < this.player.pokedexEntries.length; ++var5) {
                this.player.pokedexEntries[var5] = (short) var3.readByte();
            }

            this.loadPartyPokemon();
            this.loadEquipmentInventory();
            this.loadConsumableInventory();
            var5 = var3.readInt();
            var1.keyItems.removeAllElements();

            int[] var7;
            int var8;
            for (var6 = 0; var6 < var5; ++var6) {
                var7 = new int[var3.readInt()];

                for (var8 = 0; var8 < var7.length; ++var8) {
                    var7[var8] = var3.readInt();
                }

                var1.keyItems.addElement(var7);
            }

            var5 = var3.readInt();
            var1.specialItems.removeAllElements();

            for (var6 = 0; var6 < var5; ++var6) {
                var7 = new int[var3.readInt()];

                for (var8 = 0; var8 < var7.length; ++var8) {
                    var7[var8] = var3.readInt();
                }

                var1.specialItems.addElement(var7);
            }

            var5 = var3.readInt();
            var1.skills.removeAllElements();

            for (var6 = 0; var6 < var5; ++var6) {
                var7 = new int[var3.readInt()];

                for (var8 = 0; var8 < var7.length; ++var8) {
                    var7[var8] = var3.readInt();
                }

                var1.skills.addElement(var7);
            }

            for (var6 = 0; var6 < var1.gameFlags.length; ++var6) {
                var1.gameFlags[var6] = var3.readBoolean();
            }

            this.loadGoldAndBadges();
            if (battlePartyBackup == null) {
                battlePartyBackup = new Vector();
            }

            battlePartyBackup.removeAllElements();
            byte var11;
            var7 = new int[var11 = var3.readByte()];

            for (var6 = 0; var6 < var11; ++var6) {
                var7[var6] = var3.readByte();
                if (this.player.partyPokemon[var7[var6]] != null) {
                    this.player.partyPokemon[var7[var6]].cacheOldStats();
                    battlePartyBackup.addElement(this.player.partyPokemon[var7[var6]]);
                }
            }

            for (var6 = 0; var6 < this.badgeCollected.length; ++var6) {
                this.badgeCollected[var6] = var3.readBoolean();
            }

            byte var12;
            if ((var12 = var3.readByte()) != -1) {
                this.loadFollowingPet(var12);
            }

            var1.outfitType = var3.readByte();
            totalBattleCount = var3.readInt();
            isTutorialActive = var3.readBoolean();
            GameScreenManager var10000 = GameScreenManager.getInstance();
            var10000.storyStartTime += var3.readLong();
            var1.currentVehicleType = var3.readByte();
            var1.initializeFromData(new short[]{this.spawnPositionX, this.spawnPositionY, (short) var4, 4, 4, 8, 40, 100, 0});
            if ((var6 = var3.readInt()) > 0) {
                var1.pokemonStorage.removeAllElements();

                for (int var10 = 0; var10 < var6; ++var10) {
                    var7 = new int[var3.readInt()];

                    for (var5 = 0; var5 < var7.length; ++var5) {
                        var7[var5] = var3.readInt();
                    }

                    var1.pokemonStorage.addElement(var7);
                }
            }

            var2.close();
            var3.close();
            return true;
        } catch (Exception var9) {
            System.out.println(" loadPlayer ex = " + var9);
            return false;
        }
    }

    private boolean saveWorldState() {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            DataOutputStream var2;
            (var2 = new DataOutputStream(var1)).writeInt(this.currentRegionId);
            var2.writeInt(this.currentAreaId);

            int var3;
            int var4;
            int var5;
            for (var3 = 0; var3 < npcStateData.length; ++var3) {
                if (npcStateData[var3] == null) {
                    var2.writeShort(-1);
                } else {
                    var2.writeShort(npcStateData[var3].length);

                    for (var4 = 0; var4 < npcStateData[var3].length; ++var4) {
                        if (npcStateData[var3][var4] == null) {
                            var2.writeByte(-1);
                        } else {
                            var2.writeByte(npcStateData[var3][var4].length);

                            for (var5 = 0; var5 < npcStateData[var3][var4].length; ++var5) {
                                var2.writeByte(npcStateData[var3][var4][var5]);
                            }
                        }
                    }
                }
            }

            for (var3 = 0; var3 < npcPositionData.length; ++var3) {
                if (npcPositionData[var3] == null) {
                    var2.writeShort(-1);
                } else {
                    var2.writeShort(npcPositionData[var3].length);

                    for (var4 = 0; var4 < npcPositionData[var3].length; ++var4) {
                        if (npcPositionData[var3][var4] == null) {
                            var2.writeByte(-1);
                        } else {
                            var2.writeByte(npcPositionData[var3][var4].length);

                            for (var5 = 0; var5 < npcPositionData[var3][var4].length; ++var5) {
                                var2.writeShort(npcPositionData[var3][var4][var5]);
                            }
                        }
                    }
                }
            }

            recordStores[1].writeData(var1);
            var1.close();
            var2.close();
            return true;
        } catch (IOException var6) {
            return false;
        }
    }

    private boolean loadWorldState() {
        try {
            ByteArrayInputStream var1 = new ByteArrayInputStream(recordStores[1].readData());
            DataInputStream var2 = new DataInputStream(var1);
            this.currentRegionId = var2.readInt();
            this.currentAreaId = var2.readInt();

            short var3;
            int var4;
            int var5;
            byte var7;
            int var8;
            for (var4 = 0; var4 < npcStateData.length; ++var4) {
                if ((var3 = var2.readShort()) == -1) {
                    npcStateData[var4] = null;
                } else {
                    npcStateData[var4] = new byte[var3][];

                    for (var5 = 0; var5 < npcStateData[var4].length; ++var5) {
                        if ((var7 = var2.readByte()) == -1) {
                            npcStateData[var4][var5] = null;
                        } else {
                            npcStateData[var4][var5] = new byte[var7];

                            for (var8 = 0; var8 < npcStateData[var4][var5].length; ++var8) {
                                npcStateData[var4][var5][var8] = var2.readByte();
                            }
                        }
                    }
                }
            }

            for (var4 = 0; var4 < npcPositionData.length; ++var4) {
                if ((var3 = var2.readShort()) == -1) {
                    npcPositionData[var4] = null;
                } else {
                    npcPositionData[var4] = new short[var3][];

                    for (var5 = 0; var5 < npcPositionData[var4].length; ++var5) {
                        if ((var7 = var2.readByte()) == -1) {
                            npcPositionData[var4][var5] = null;
                        } else {
                            npcPositionData[var4][var5] = new short[var7];

                            for (var8 = 0; var8 < npcPositionData[var4][var5].length; ++var8) {
                                npcPositionData[var4][var5][var8] = var2.readShort();
                            }
                        }
                    }
                }
            }

            var1.close();
            var2.close();
            return true;
        } catch (IOException var6) {
            System.out.println(" sceneId ex = " + var6);
            return false;
        }
    }

    private boolean saveQuestState() {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            DataOutputStream var2 = new DataOutputStream(var1);

            int var3;
            int var4;
            for (var3 = 0; var3 < this.questManager.questStates.length; ++var3) {
                if (this.questManager.questStates[var3] == null) {
                    var2.writeByte(-1);
                } else {
                    var2.writeByte(this.questManager.questStates[var3].length);

                    for (var4 = 0; var4 < this.questManager.questStates[var3].length; ++var4) {
                        var2.writeByte(this.questManager.questStates[var3][var4]);
                    }
                }
            }

            var2.writeByte(QuestManager.questDialogState);
            var2.writeByte(QuestManager.questFlagCount);

            for (var3 = 0; var3 < QuestManager.questFlagCount; ++var3) {
                var2.writeShort(QuestManager.questFlags[var3][0]);
                var2.writeShort(QuestManager.questFlags[var3][1]);
            }

            int[] var6;
            if ((var6 = this.questManager.getSavedTimeArray()) == null) {
                var2.writeByte(-1);
            } else {
                var2.writeByte(var6.length);

                for (var4 = 0; var4 < var6.length; ++var4) {
                    var2.writeInt(var6[var4]);
                }

                var2.writeByte(this.questManager.questProgress);
            }

            recordStores[2].writeData(var1);
            var1.close();
            var2.close();
            return true;
        } catch (IOException var5) {
            return false;
        }
    }

    private boolean loadQuestState() {
        try {
            ByteArrayInputStream var1 = new ByteArrayInputStream(recordStores[2].readData());
            DataInputStream var2 = new DataInputStream(var1);

            int var4;
            for (var4 = 0; var4 < this.questManager.questStates.length; ++var4) {
                byte var3;
                if ((var3 = var2.readByte()) == -1) {
                    this.questManager.questStates[var4] = null;
                } else {
                    this.questManager.questStates[var4] = new byte[var3];

                    for (int var8 = 0; var8 < this.questManager.questStates[var4].length; ++var8) {
                        this.questManager.questStates[var4][var8] = var2.readByte();
                    }
                }
            }

            QuestManager.questDialogState = var2.readByte();
            QuestManager.questFlagCount = var2.readByte();

            for (var4 = 0; var4 < QuestManager.questFlagCount; ++var4) {
                QuestManager.questFlags[var4][0] = var2.readShort();
                QuestManager.questFlags[var4][1] = var2.readShort();
            }

            byte var10;
            if ((var10 = var2.readByte()) != -1) {
                int[] var9 = new int[var10];
                int[] var5 = this.questManager.getCurrentTimeArray();

                for (int var6 = 0; var6 < var10; ++var6) {
                    var9[var6] = var2.readInt();
                }

                this.questManager.setSavedTimeArray(var9);
                boolean var11 = false;
                if (var5[0] > var9[0] || var5[1] > var9[1] || var5[2] > var9[2] || var5[3] - var9[3] >= 20) {
                    var11 = true;
                }

                this.questManager.questProgress = var2.readByte();
                if (var11) {
                    this.questManager.questProgress = 0;
                }
            }

            isGameLoaded = true;
            var1.close();
            var2.close();
            return true;
        } catch (IOException var7) {
            return false;
        }
    }

    public static boolean saveGameFlags() {
        try {
            ByteArrayOutputStream var0 = new ByteArrayOutputStream();
            DataOutputStream var1 = new DataOutputStream(var0);
            inputEnabled = true;
            var1.writeBoolean(inputEnabled);
            var1.writeBoolean(isGameLoaded);
            var1.writeBoolean(hasPurchasedSms);
            var1.writeByte(tutorialStep);
            recordStores[3].writeData(var0);
            var0.close();
            var1.close();
            return true;
        } catch (IOException var2) {
            return false;
        }
    }

    public static boolean loadGameFlags() {
        if (recordStores[3] == null) {
            recordStores[3] = new RecordStoreManager(RMS_NAMES[3], 1);
        }

        try {
            ByteArrayInputStream var0 = new ByteArrayInputStream(recordStores[3].readData());
            DataInputStream var1;
            inputEnabled = (var1 = new DataInputStream(var0)).readBoolean();
            isGameLoaded = var1.readBoolean();
            hasPurchasedSms = var1.readBoolean();
            tutorialStep = var1.readByte();
            var0.close();
            var1.close();
            return true;
        } catch (IOException var2) {
            inputEnabled = false;
            System.out.println(" isHaveSms = " + inputEnabled);
            return false;
        }
    }

    private static boolean savePaymentStatus() {
        try {
            ByteArrayOutputStream var0 = new ByteArrayOutputStream();
            DataOutputStream var1;
            (var1 = new DataOutputStream(var0)).writeBoolean(paymentActive);
            recordStores[4].writeData(var0);
            var0.close();
            var1.close();
            return true;
        } catch (IOException var2) {
            return false;
        }
    }

    private static boolean loadPaymentStatus() {
        try {
            ByteArrayInputStream var0 = new ByteArrayInputStream(recordStores[4].readData());
            DataInputStream var1;
            paymentActive = (var1 = new DataInputStream(var0)).readBoolean();
            var0.close();
            var1.close();
            return true;
        } catch (IOException var2) {
            return false;
        }
    }

    private boolean saveGoldAndBadges() {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            DataOutputStream var2;
            (var2 = new DataOutputStream(var1)).writeInt(this.player.getGold());
            var2.writeInt(this.player.getBadges());
            recordStores[6].writeData(var1);
            var1.close();
            var2.close();
            return true;
        } catch (IOException var3) {
            return false;
        }
    }

    private boolean loadGoldAndBadges() {
        try {
            ByteArrayInputStream var1 = new ByteArrayInputStream(recordStores[6].readData());
            DataInputStream var2 = new DataInputStream(var1);
            this.player.setGold(0);
            this.player.setBadges(0);
            this.player.addGold(var2.readInt());
            this.player.addBadges(var2.readInt());
            var1.close();
            var2.close();
            return true;
        } catch (IOException var3) {
            return false;
        }
    }

    private boolean saveConsumableInventory() {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            DataOutputStream var2;
            (var2 = new DataOutputStream(var1)).writeInt(this.player.consumableInventory.size());

            for (int var3 = 0; var3 < this.player.consumableInventory.size(); ++var3) {
                int[] var4 = (int[]) this.player.consumableInventory.elementAt(var3);
                var2.writeInt(var4.length);

                for (int var5 = 0; var5 < var4.length; ++var5) {
                    var2.writeInt(var4[var5]);
                }
            }

            recordStores[9].writeData(var1);
            var1.close();
            var2.close();
            return true;
        } catch (IOException var6) {
            return false;
        }
    }

    private boolean loadConsumableInventory() {
        try {
            ByteArrayInputStream var1 = new ByteArrayInputStream(recordStores[9].readData());
            DataInputStream var2;
            int var3 = (var2 = new DataInputStream(var1)).readInt();
            this.player.consumableInventory.removeAllElements();

            for (int var4 = 0; var4 < var3; ++var4) {
                int[] var5 = new int[var2.readInt()];

                for (int var6 = 0; var6 < var5.length; ++var6) {
                    var5[var6] = var2.readInt();
                }

                this.player.consumableInventory.addElement(var5);
            }

            var1.close();
            var2.close();
            return true;
        } catch (IOException var7) {
            return false;
        }
    }

    private boolean saveEquipmentInventory() {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            DataOutputStream var2;
            (var2 = new DataOutputStream(var1)).writeInt(this.player.equipmentInventory.size());

            for (int var3 = 0; var3 < this.player.equipmentInventory.size(); ++var3) {
                int[] var4 = (int[]) this.player.equipmentInventory.elementAt(var3);
                var2.writeInt(var4.length);

                for (int var5 = 0; var5 < var4.length; ++var5) {
                    var2.writeInt(var4[var5]);
                }
            }

            recordStores[8].writeData(var1);
            var1.close();
            var2.close();
            return true;
        } catch (IOException var6) {
            return false;
        }
    }

    private boolean loadEquipmentInventory() {
        try {
            ByteArrayInputStream var1 = new ByteArrayInputStream(recordStores[8].readData());
            DataInputStream var2;
            int var3 = (var2 = new DataInputStream(var1)).readInt();
            this.player.equipmentInventory.removeAllElements();

            for (int var4 = 0; var4 < var3; ++var4) {
                int[] var5 = new int[var2.readInt()];

                for (int var6 = 0; var6 < var5.length; ++var6) {
                    var5[var6] = var2.readInt();
                }

                this.player.equipmentInventory.addElement(var5);
            }

            var1.close();
            var2.close();
            return true;
        } catch (IOException var7) {
            return false;
        }
    }

    public final boolean savePartyPokemon() {
        try {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            DataOutputStream var2;
            (var2 = new DataOutputStream(var1)).writeByte(this.player.partySize);

            for (int var3 = 0; var3 < this.player.partySize; ++var3) {
                int[] var4 = this.player.partyPokemon[var3].toSaveData();
                var2.writeInt(var4.length);

                for (int var5 = 0; var5 < var4.length; ++var5) {
                    var2.writeInt(var4[var5]);
                }
            }

            recordStores[7].writeData(var1);
            var1.close();
            var2.close();
            return true;
        } catch (IOException var6) {
            return false;
        }
    }

    private boolean loadPartyPokemon() {
        try {
            ByteArrayInputStream var1 = new ByteArrayInputStream(recordStores[7].readData());
            DataInputStream var2;
            byte var3 = (var2 = new DataInputStream(var1)).readByte();

            int var4;
            for (var4 = 0; var4 < this.player.partySize; ++var4) {
                this.player.partyPokemon[var4] = null;
            }

            this.player.partySize = 0;

            for (var4 = 0; var4 < var3; ++var4) {
                int[] var5 = new int[var2.readInt()];

                for (int var6 = 0; var6 < var5.length; ++var6) {
                    var5[var6] = var2.readInt();
                }

                this.player.createPokemonFromData(var5);
            }

            var1.close();
            var2.close();
            return true;
        } catch (IOException var7) {
            return false;
        }
    }

    public final boolean saveAllGameData() {
        if (!this.savePlayerData(this.player)) {
            return false;
        } else if (!this.saveWorldState()) {
            return false;
        } else if (!this.saveQuestState()) {
            return false;
        } else if (!saveGameFlags()) {
            return false;
        } else {
            return savePaymentStatus();
        }
    }

    public final boolean saveEssentialData() {
        if (!savePaymentStatus()) {
            return false;
        } else if (!this.saveGoldAndBadges()) {
            return false;
        } else {
            return this.saveConsumableInventory();
        }
    }

    public static void deleteAllSaveData() {
        npcStateData = null;
        npcPositionData = null;

        for (int var0 = 0; var0 < 10; ++var0) {
            if (var0 != 4 && recordStores[var0] != null) {
                recordStores[var0].deleteStore();
                recordStores[var0] = null;
            }
        }

        recordStores = null;
    }

    public final boolean returnFromBattle() {
        this.questManager.setGameEngine(this);
        this.gameController.a(this);
        QuestManager.isQuestActive = false;
        showStatusBar = true;
        this.changeState((byte) 0);
        if (hasEventMusic) {
            this.audioManager.playBackgroundMusic(getEventMusicByIndex(this.questManager.lastEventIndex, (byte) 0), 1);
        } else {
            this.audioManager.playBackgroundMusic(defaultMusicTrack, 1);
        }

        this.dialogManager.removeDialog("/data/ui/battle.ui");
        return true;
    }

    private void spawnPlayer() {
        short[] var1;
        if (!this.player.isInitialized) {
            if (inputEnabled) {
                this.loadPlayerData(this.player);
            } else {
                var1 = new short[]{this.spawnPositionX, this.spawnPositionY, (short) playerDirection, 4, 4, 8, 40, 100, 0};
                this.player.initializeFromData(var1);
            }
        } else if (this.lastInteractedNpcId >= 0) {
            byte var3 = 2;
            int var4 = this.NPCs[this.lastInteractedNpcId].worldX
                    - this.NPCs[this.lastInteractedNpcId].worldX % this.player.primaryStates[var3];
            var3 = 2;
            int var2 = this.NPCs[this.lastInteractedNpcId].worldY
                    - this.NPCs[this.lastInteractedNpcId].worldY % this.player.primaryStates[var3];
            this.player.setWorldPosition(var4, var2);
            this.player.attachedObject.setWorldPosition(var4, var2);
            this.player.setFacingState((byte) 0, (byte) this.NPCs[this.lastInteractedNpcId].followDistance);
            if (this.NPCs[this.lastInteractedNpcId].sprite.spriteSetId == 222) {
                this.player.moveInDirection((int) 24);
            } else {
                this.player.moveInDirection((int) 32);
            }
        } else {
            var1 = new short[]{this.spawnPositionX, this.spawnPositionY, (short) playerDirection, 4, 4, 8, 40, 100, 0};
            this.player.initializeFromData(var1);
        }

        this.worldRenderer.addEntity((GameEntity) this.player);
        this.player.enableWildEncounter();
        this.player.activate();
        this.attachFollowingPet((GameObject) this.player);
        if (backgroundImage != null) {
            this.player.setAlternativeRender(false);
        } else {
            this.player.setAlternativeRender(true);
        }
    }

    private void initializeNPCPatrolRoutes() {
        for (int var1 = 0; var1 < this.NPCs.length; ++var1) {
            if (this.NPCs[var1].npcType == 0 && this.NPCs[var1].npcSubType == 14) {
                NPCEntity var2;
                (var2 = this.NPCs[var1]).dialogueProgress = 0;

                while (true) {
                    byte var10001 = var2.sprite.getCurrentAnimationId();
                    int var10002 = 16 * (var2.dialogueProgress + 1);
                    boolean var3 = false;
                    int var5 = var10002;
                    byte var4 = var10001;
                    byte var6 = 0;
                    switch (var4) {
                        case 0:
                            var6 = TileMapRenderer.getInstance().getTileAt(0, var2.worldX, var2.worldY + var5);
                            break;
                        case 1:
                            var6 = TileMapRenderer.getInstance().getTileAt(0, var2.worldX + var5, var2.worldY);
                            break;
                        case 2:
                            var6 = TileMapRenderer.getInstance().getTileAt(0, var2.worldX, var2.worldY - var5);
                            break;
                        case 3:
                            var6 = TileMapRenderer.getInstance().getTileAt(0, var2.worldX - var5, var2.worldY);
                    }

                    if (var6 != 0) {
                        var2.dialogueTimeout = var2.dialogueProgress;
                        var2.dialogueProgress = 0;
                        break;
                    }

                    ++var2.dialogueProgress;
                }
            }
        }

    }

    private void a(int var1, int var2, String var3) {
        var3 = var3 + "scene_" + var1 + ".mid";

        try {
            InputStream var16 = ManagedInputStream.openStream(var3);
            DataInputStream var4;
            short var5;
            short[] var6 = new short[var5 = (var4 = new DataInputStream(var16)).readShort()];

            int var7;
            for (var7 = 0; var7 < var5; ++var7) {
                var6[var7] = var4.readShort();
            }

            var7 = 0;

            int var17;
            for (var17 = 0; var17 < var2; ++var17) {
                var7 += var6[var17];
            }

            var4.skipBytes(var7);
            var5 = var4.readShort();
            String[] var18 = null;
            if (var5 > 0) {
                var18 = new String[var5];

                for (var7 = 0; var7 < var5; ++var7) {
                    short var8 = var4.readShort();
                    StringBuffer var9 = new StringBuffer();

                    for (int var10 = 0; var10 < var8; ++var10) {
                        var9.append((char) (var4.read() << 8 | var4.read() & 255));
                    }

                    var18[var7] = var9.toString();
                }
            }

            byte var22 = var4.readByte();
            StringBuffer var20 = new StringBuffer();

            for (int var24 = 0; var24 < var22; ++var24) {
                var20.append((char) (var4.read() << 8 | var4.read() & 255));
            }

            this.currentMapId = var4.readShort();
            npcDialogNames = null;
            var4.readShort();
            short var26 = var4.readShort();
            boolean[] var19 = new boolean[2];
            if (npcStateData[REGION_OFFSETS[var1] + var2] == null) {
                npcStateData[REGION_OFFSETS[var1] + var2] = new byte[var26][3];
                var19[0] = true;
            }

            if (this.currentRegionId == 9) {
                npcPositionData[REGION_OFFSETS[var1] + var2] = null;
            }

            if (npcPositionData[REGION_OFFSETS[var1] + var2] == null) {
                npcPositionData[REGION_OFFSETS[var1] + var2] = new short[var26][2];
                var19[1] = true;
            }

            if (var26 > 0) {
                this.NPCs = new NPCEntity[var26];

                for (var7 = 0; var7 < var26; ++var7) {
                    try {
                        this.NPCs[var7] = new NPCEntity();
                        short[] var21;
                        (var21 = new short[var4.readShort()])[0] = (short) var4.readByte();
                        var21[1] = var4.readShort();
                        var21[2] = var4.readShort();
                        var21[3] = var4.readShort();
                        var21[4] = var4.readShort();
                        var21[5] = (short) var4.readByte();
                        var21[6] = (short) var4.readByte();
                        switch (var21[0]) {
                            case 0:
                                var21[7] = (short) var4.readByte();
                                var21[8] = (short) var4.readByte();
                                var21[9] = (short) var4.readByte();
                                var21[10] = (short) var4.readByte();
                                var21[11] = var4.readShort();
                                var21[12] = var4.readShort();
                                if (var19[0]) {
                                    npcStateData[REGION_OFFSETS[var1] + var2][var7][0] = (byte) var21[2];
                                    npcStateData[REGION_OFFSETS[var1] + var2][var7][1] = (byte) var21[5];
                                } else {
                                    if (var21[6] != 7 && var21[6] != 6) {
                                        var21[2] = (short) npcStateData[REGION_OFFSETS[var1] + var2][var7][0];
                                    }

                                    var21[5] = (short) npcStateData[REGION_OFFSETS[var1] + var2][var7][1];
                                }

                                if (var19[1]) {
                                    npcPositionData[REGION_OFFSETS[var1] + var2][var7][0] = var21[3];
                                    npcPositionData[REGION_OFFSETS[var1] + var2][var7][1] = var21[4];
                                } else {
                                    var21[3] = npcPositionData[REGION_OFFSETS[var1] + var2][var7][0];
                                    var21[4] = npcPositionData[REGION_OFFSETS[var1] + var2][var7][1];
                                }

                                this.NPCs[var7].initializeNPC(var21, var7);
                                if (!var19[0]) {
                                    if (var21[6] == 1) {
                                        if (var21[6] != 7 && var21[6] != 6) {
                                            var21[2] = (short) npcStateData[REGION_OFFSETS[var1] + var2][var7][0];
                                        }

                                        NPCEntity var10000 = this.NPCs[var7];
                                        byte var11 = npcStateData[REGION_OFFSETS[var1] + var2][var7][2];
                                        var10000.currentDirection = var11;
                                        this.NPCs[var7].setDirection((byte) var21[2]);
                                    }
                                } else {
                                    npcStateData[REGION_OFFSETS[var1] + var2][var7][2] = this.NPCs[var7].currentDirection;
                                }
                                break;
                            case 1:
                                var21[7] = (short) var4.readByte();
                                var21[8] = var4.readShort();
                                var21[9] = var4.readShort();
                                var21[10] = var4.readShort();
                                if (var19[0] && var21[6] == 3) {
                                    npcStateData[REGION_OFFSETS[var1] + var2][var7][0] = (byte) var21[2];
                                    npcStateData[REGION_OFFSETS[var1] + var2][var7][1] = (byte) var21[5];
                                }

                                if (var21[6] == 3) {
                                    var21[2] = (short) npcStateData[REGION_OFFSETS[var1] + var2][var7][0];
                                    var21[5] = (short) npcStateData[REGION_OFFSETS[var1] + var2][var7][1];
                                }

                                this.NPCs[var7].initializeNPC(var21, var7);
                                break;
                            case 2:
                                var21[7] = var4.readShort();
                                if (var21[7] == 1) {
                                    var21[8] = (short) var4.readByte();
                                    var21[9] = (short) var4.readByte();
                                    var21[10] = (short) var4.readByte();
                                    var21[11] = (short) var4.readByte();
                                    var21[12] = (short) var4.readByte();
                                }

                                this.NPCs[var7].initializeNPC(var21, var7);
                                break;
                            case 3:
                                var21[7] = (short) var4.readByte();
                                var21[8] = (short) var4.readByte();
                                var21[9] = (short) var4.readByte();
                                var21[10] = var4.readShort();
                                var21[11] = var4.readShort();
                                if (var19[0]) {
                                    npcStateData[REGION_OFFSETS[var1] + var2][var7][0] = (byte) var21[2];
                                } else {
                                    var21[2] = (short) npcStateData[REGION_OFFSETS[var1] + var2][var7][0];
                                }

                                this.NPCs[var7].initializeNPC(var21, var7);
                        }
                    } catch (Exception var12) {
                        System.out.println(" k = " + var7 + " e = " + var12);
                    }
                }

                short var25;
                npcDialogNames = new String[var25 = var4.readShort()];

                for (int var23 = 0; var23 < var25; ++var23) {
                    StringBuffer var14 = new StringBuffer();
                    byte var15 = var4.readByte();

                    for (var17 = 0; var17 < var15; ++var17) {
                        var14.append((char) (var4.readByte() << 8 | var4.readByte() & 255));
                    }

                    npcDialogNames[var23] = var14.toString();
                }
            }

            this.loadingStep = 1;
            var5 = var4.readShort();
            this.loadingStep = 2;
            if (var5 > 0) {
                this.questManager.loadQuests(var4, this.currentRegionId, this.currentAreaId, var5, var18);
            }

            var4.close();
            var16.close();
        } catch (Exception var13) {
            System.out.println(" initRoom = " + var13 + " bug = " + this.loadingStep);
        }
    }

    public final void cleanupCurrentScreen() {
        this.worldRenderer.clearWorld();
        this.tileMapRenderer.clearMapData();
        if (this.NPCs != null) {
            for (int var1 = 0; var1 < this.NPCs.length; ++var1) {
                NPCEntity var2;
                (var2 = this.NPCs[var1]).deactivateAttachedObject();
                if (var2.attachedObject != null) {
                    var2.attachedObject.sprite.forceCleanup();
                    var2.attachedObject = null;
                }

                var2.sprite.forceCleanup();
                var2.sprite = null;
                if (var2.auraObject != null) {
                    var2.auraObject.sprite.forceCleanup();
                    var2.auraObject = null;
                }

                if (var2.effectObject != null) {
                    var2.effectObject.sprite.forceCleanup();
                    var2.effectObject = null;
                }

                var2.npcId = -1;
                this.NPCs[var1] = null;
            }

            this.NPCs = null;
        }

        backgroundImage = null;
        goldIcon = null;
        petAreaData = null;
        mediaConfigData = null;
        this.activeNpcList.removeAllElements();
        grassEncounterPool.removeAllElements();
        caveEncounterPool.removeAllElements();
        specialEncounterPool.removeAllElements();
        waterEncounterPool.removeAllElements();
        if (pendingEvolutions != null) {
            pendingEvolutions.removeAllElements();
            pendingEvolutions = null;
        }

        this.dialogManager.clearAllDialogs();
        this.questManager.cleanupCurrentScreen();
        currentInteractNpcId = -1;
        ScreenTransitionManager.clearBattleNpcData();
    }

    public final void changeState(byte var1) {
        this.previousState = this.currentState;
        switch (var1) {
            case 0:
                GameEngineBase.resetAction();
                if (!QuestManager.isQuestActive) {
                    if (showStatusBar) {
                        this.gameController.c();
                    } else {
                        this.gameController.d();
                    }
                }

                this.player.setFacingState((byte) 0, this.player.currentDirection);
                break;
            case 1:
                this.gameController.C9_f141 = 1;
                this.gameController.F();
                break;
            case 2:
                if (currentInteractNpcId != -1 && this.NPCs[currentInteractNpcId] != null && this.NPCs[currentInteractNpcId].sprite.spriteSetId == 24) {
                    this.gameController.a((int) 4, (byte) 0);
                } else if (currentInteractNpcId != -1 && this.NPCs[currentInteractNpcId] != null
                        && this.NPCs[currentInteractNpcId].sprite.spriteSetId == 20) {
                    this.gameController.a((int) 3, (byte) 2);
                }
                break;
            case 3:
                this.gameController.O();
            case 4:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 103:
            default:
                break;
            case 5:
                this.gameController.ag();
                break;
            case 6:
                this.gameController.k();
                break;
            case 7:
                this.gameController.C9_f126 = 0;
                this.gameController.Z();
                break;
            case 8:
                this.gameController.ab();
                break;
            case 9:
                this.gameController.Q();
                break;
            case 10:
                this.gameController.U();
                break;
            case 11:
                this.gameController.S();
                break;
            case 12:
                this.gameController.W();
                break;
            case 13:
                this.gameController.m();
                break;
            case 14:
                this.gameController.aC();
                break;
            case 15:
                this.gameController.B();
                break;
            case 16:
                this.gameController.D();
                break;
            case 17:
                this.gameController.C9_f150 = false;
            case 18:
            case 19:
                this.gameController.C9_f126 = 0;
                this.gameController.Z();
                break;
            case 20:
                this.gameController.x();
                break;
            case 21:
                this.gameController.z();
                break;
            case 22:
                this.gameController.K();
                this.gameController.a("Có lưu dữ liệu không?");
                break;
            case 23:
                if (this.previousState == 7) {
                    this.gameController.a((String) "", (String) this.tutorialHintText, -1, -1);
                } else if (this.NPCs != null) {
                    if (this.NPCs[currentInteractNpcId].sprite.spriteSetId == 68) {
                        this.gameController.a((String) npcDialogNames[this.NPCs[currentInteractNpcId].npcRotationY],
                                (String) "Muốn lên thuyền đi đâu?", 1, -1);
                    } else if (this.NPCs[currentInteractNpcId].npcRotationX < 0) {
                        this.gameController.a((String) npcDialogNames[this.NPCs[currentInteractNpcId].npcRotationY], (String) dialogTexts[0], 1,
                                -1);
                    } else {
                        this.gameController.a((String) npcDialogNames[this.NPCs[currentInteractNpcId].npcRotationY],
                                (String) dialogTexts[this.NPCs[currentInteractNpcId].npcRotationX], 1, -1);
                    }
                }

                this.player.setFacingState((byte) 0, (byte) this.player.currentDirection);
                break;
            case 24:
                this.gameController.h();
                break;
            case 25:
                this.gameController.au();
                break;
            case 26:
                this.gameController.C9_f141 = 2;
                this.gameController.a((int) 4, (byte) 0);
                break;
            case 27:
                this.gameController.aS();
                break;
            case 28:
                byte var6;
                for (var6 = 0; var6 < this.portLocationData.length / 4 && (this.portLocationData[var6 << 2] != this.currentRegionId
                        || this.portLocationData[(var6 << 2) + 1] != this.currentAreaId); ++var6) {
                }

                this.gameController.a((byte) var6, this.portLocationData[(var6 << 2) + 2], this.portLocationData[(var6 << 2) + 3]);
                break;
            case 29:
                ScreenTransitionManager.getInstance().startTransition(0, 2);
                break;
            case 30:
                this.gameController.aQ();
                break;
            case 31:
                this.gameController.C9_f131 = 0;
                boolean var3 = false;
                this.calculateBadgeProgress();
                if (this.achievedBadgeTier >= this.currentBadgeTier) {
                    var3 = true;
                }

                this.badgeRewardPending = var3;
                if (this.badgeRewardPending) {
                    if (this.currentBadgeTier == this.badgeRequirements.length - 1) {
                        this.gameController.a((String) npcDialogNames[this.NPCs[currentInteractNpcId].npcRotationY],
                                (String) getLocalizedText(613), 1, -1);
                    } else if (this.currentBadgeTier == this.badgeRequirements.length - 2) {
                        this.gameController.a((String) npcDialogNames[this.NPCs[currentInteractNpcId].npcRotationY],
                                (String) getLocalizedText(612), 1, -1);
                    } else {
                        int[] var2 = new int[]{this.badgeRequirements[this.currentBadgeTier], this.badgeRequirements[this.currentBadgeTier + 1]};
                        this.gameController.a((String) npcDialogNames[this.NPCs[currentInteractNpcId].npcRotationY],
                                (String) formatString(611, var2), 1, -1);
                    }
                } else if (this.currentBadgeTier < this.badgeRequirements.length) {
                    String var10001 = npcDialogNames[this.NPCs[currentInteractNpcId].npcRotationY];
                    byte var7 = this.badgeRequirements[this.currentBadgeTier];
                    boolean var4 = true;
                    int var5;
                    this.gameController.a((String) var10001,
                            (String) ((var5 = GameEngineBase.getLocalizedText((int) 614).indexOf("%s")) == -1
                                    ? GameEngineBase.getLocalizedText((int) 614)
                                    : GameEngineBase.getLocalizedText((int) 614).substring(0, var5) + var7
                                    + GameEngineBase.getLocalizedText((int) 614).substring(var5 + 2)),
                            1, -1);
                } else {
                    this.gameController.a((String) npcDialogNames[this.NPCs[currentInteractNpcId].npcRotationY],
                            (String) getLocalizedText(615), 1, -1);
                }
                break;
            case 32:
                this.gameController.C9_f141 = 3;
                this.gameController.a((int) 3, (byte) 2);
                break;
            case 100:
                this.gameController.d(0);
                break;
            case 101:
                this.gameController.aJ();
                break;
            case 102:
                this.gameController.aL();
                break;
            case 104:
                this.gameController.aK();
        }

        this.gameController.C9_f132 = true;
        this.currentState = var1;
        this.showSoftKeys();
    }

    public final void update() {
        if (this.isActive) {
            this.updateInputState();
            label203:
            switch (this.currentState) {
                case 0:
                    this.updateExplorationMode();
                    break;
                case 1:
                    this.gameController.G();
                    break;
                case 2:
                    if ((currentInteractNpcId == -1 || this.NPCs[currentInteractNpcId] == null
                            || this.NPCs[currentInteractNpcId].sprite.spriteSetId != 24) && this.questManager.questChangeState != 0) {
                        if (currentInteractNpcId != -1 && this.NPCs[currentInteractNpcId] != null
                                && this.NPCs[currentInteractNpcId].sprite.spriteSetId == 20 || this.questManager.questChangeState == 1) {
                            this.gameController.a((byte) 3, (byte) 2);
                        }
                    } else {
                        this.gameController.a((byte) 4, (byte) 0);
                    }
                    break;
                case 3:
                    this.gameController.P();
                    break;
                case 4:
                    int var1;
                    if (this.isWorldMapScrolling) {
                        if (this.worldMapScrollX == this.worldMapTargetX && this.worldMapScrollY == this.worldMapTargetY) {
                            this.isWorldMapScrolling = false;
                        }

                        if ((var1 = GameUtils.calculateEuclideanDistance(this.worldMapScrollX, this.worldMapScrollY, this.worldMapTargetX,
                                this.worldMapTargetY)) < this.mapScrollSpeed) {
                            this.worldMapScrollX = this.worldMapTargetX;
                            this.worldMapScrollY = this.worldMapTargetY;
                        } else {
                            this.worldMapScrollX += (this.worldMapTargetX - this.worldMapScrollX) * this.mapScrollSpeed / var1;
                            this.worldMapScrollY += (this.worldMapTargetY - this.worldMapScrollY) * this.mapScrollSpeed / var1;
                        }
                    }

                    if (!this.isWorldMapScrolling) {
                        if (this.isKeyHeld(16400)) {
                            if (this.worldMapScrollX < 0) {
                                this.worldMapScrollX += this.mapScrollSpeed;
                            }
                        } else if (this.isKeyHeld(32832)) {
                            if (this.worldMapScrollX + (this.mapNodeSizes[this.currentWorldMapId << 1] << 4) * 5 > getScreenWidth()) {
                                this.worldMapScrollX -= this.mapScrollSpeed;
                            }
                        } else if (this.isKeyHeld(4100)) {
                            if (this.worldMapScrollY < 0) {
                                this.worldMapScrollY += this.mapScrollSpeed;
                            }
                        } else if (this.isKeyHeld(8448)) {
                            if (this.worldMapScrollY + (this.mapNodeSizes[(this.currentWorldMapId << 1) + 1] << 3) * 5 > getScreenHeight() - 30) {
                                this.worldMapScrollY -= this.mapScrollSpeed;
                            }
                        } else if (this.isKeyPressed(262145) || this.isPointerClickInBounds(0, 270, getScreenWidth(), 30)) {
                            BattleSystemManager.B().C29_f400 = null;
                            this.changeState((byte) 0);
                        }
                    }

                    this.mapCursor.updateAnimation();
                    var1 = 0;

                    while (true) {
                        if (var1 >= this.mapScrollArrows.length) {
                            break label203;
                        }

                        this.updateScrollArrowVisibility(var1);
                        this.mapScrollArrows[var1].updateAnimation();
                        ++var1;
                    }
                case 5:
                    this.gameController.ah();
                    break;
                case 6:
                    this.gameController.l();
                    break;
                case 7:
                    this.gameController.aa();
                    this.updateActionSequence();
                    break;
                case 8:
                    this.gameController.af();
                    break;
                case 9:
                    this.gameController.R();
                    break;
                case 10:
                    this.gameController.V();
                    break;
                case 11:
                    this.gameController.T();
                    break;
                case 12:
                    this.gameController.X();
                    break;
                case 13:
                    this.gameController.n();
                    break;
                case 14:
                    this.gameController.aD();
                    break;
                case 15:
                    this.gameController.C();
                    break;
                case 16:
                    this.gameController.E();
                    break;
                case 17:
                    this.gameController.ac();
                    break;
                case 18:
                    this.gameController.ad();
                    break;
                case 19:
                    this.gameController.ae();
                    break;
                case 20:
                    this.gameController.y();
                    break;
                case 21:
                    this.gameController.A();
                    break;
                case 22:
                    this.gameController.N();
                    break;
                case 23:
                    if (this.gameController.d(dialogTextId, dialogPortraitId) && this.isKeyPressed(196640)) {
                        if (GameUtils.pageCount < GameUtils.b()) {
                            GameUtils.c();
                            this.gameController.b(GameUtils.pageCount);
                        } else {
                            label227:
                            {
                                this.gameController.aF();
                                if (this.previousState != 7) {
                                    if (this.NPCs[currentInteractNpcId].sprite.spriteSetId <= 85) {
                                        NPCEntity var10000 = this.NPCs[currentInteractNpcId];
                                        byte var3 = this.NPCs[currentInteractNpcId].currentAnimationId;
                                        var10000.currentDirection = var3;
                                    }

                                    this.NPCs[currentInteractNpcId].setDirection((byte) 0);
                                    this.player.setFacingState((byte) 0, (byte) this.player.currentDirection);
                                    if (this.NPCs[currentInteractNpcId].sprite.spriteSetId == 24
                                            || this.NPCs[currentInteractNpcId].sprite.spriteSetId == 20) {
                                        this.changeState((byte) 1);
                                        break label227;
                                    }

                                    if (this.NPCs[currentInteractNpcId].sprite.spriteSetId == 25) {
                                        this.changeState((byte) 16);
                                        break label227;
                                    }

                                    if (this.NPCs[currentInteractNpcId].sprite.spriteSetId == 68) {
                                        this.changeState((byte) 28);
                                        break label227;
                                    }

                                    if (currentInteractNpcId != -1) {
                                        getInstance().createInteractionMarker((byte) 13, getInstance().NPCs[currentInteractNpcId].worldX, getInstance().NPCs[currentInteractNpcId].worldY - 40,
                                                getInstance().NPCs[currentInteractNpcId]);
                                    }
                                }

                                this.changeState((byte) 0);
                            }
                        }
                    }

                    this.worldRenderer.updateWorld();
                    break;
                case 24:
                    this.gameController.i();
                    break;
                case 25:
                    this.gameController.av();
                    break;
                case 26:
                    this.gameController.a((byte) 4, (byte) 0);
                    break;
                case 27:
                    this.gameController.aT();
                    break;
                case 28:
                    this.gameController.aP();
                    break;
                case 29:
                    ScreenTransitionManager.getInstance().updateTransition();
                    if (ScreenTransitionManager.getInstance().isTransitionComplete) {
                        ScreenTransitionManager.getInstance().primaryTransitionType = -1;
                        GameScreenManager.getInstance().changeState((byte) 23);
                    }
                    break;
                case 30:
                    this.gameController.aR();
                    break;
                case 31:
                    this.updateBadgeDialogMode();
                    break;
                case 32:
                    this.gameController.a((byte) 3, (byte) 2);
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                case 68:
                case 69:
                case 70:
                case 71:
                case 72:
                case 73:
                case 74:
                case 75:
                case 76:
                case 77:
                case 78:
                case 79:
                case 80:
                case 81:
                case 82:
                case 83:
                case 84:
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 103:
                default:
                    break;
                case 100:
                case 101:
                case 102:
                case 104:
                    this.gameController.aO();
            }

            if (this.currentState == 0 && !this.questManager.hasActiveEvent() && evolutionProcessState == 0 && pendingEvolutions != null
                    && pendingEvolutions.size() > 0) {
                if (this.evolutionNotifyIndex >= pendingEvolutions.size()) {
                    pendingEvolutions.removeAllElements();
                    this.evolutionNotifyIndex = 0;
                    evolutionProcessState = 1;
                } else if (this.gameController.aA()) {
                    int[] var4 = (int[]) pendingEvolutions.elementAt(this.evolutionNotifyIndex);
                    String var2 = "Tiến hóa";
                    if (ResourceManager.gameDatabase[0][ResourceManager.getDatabaseValue((byte) 0, (short) var4[0],
                            (byte) 19)][2] == 3) {
                        var2 = "Dị hoá";
                    }

                    if (!isTutorialActive && tutorialTargetPokemon[0] != -1) {
                        if (this.evolutionNotifyIndex == pendingEvolutions.size() - 1) {
                            this.gameController.H();
                            this.gameController.a(
                                    "Nhấn #2" + getLocalizedText(var4[1]) + "#0 đạt tới có thể" + var2 + " điều kiện",
                                    "Nhấn nút 5 tiếp tục");
                        } else {
                            this.gameController.b("#2" + getLocalizedText(var4[1]) + "#0 có thể" + var2);
                        }
                    } else {
                        this.gameController.b("#2" + getLocalizedText(var4[1]) + "#0 có thể" + var2);
                    }

                    ++this.evolutionNotifyIndex;
                }
            }

            this.dialogManager.closeCurrentDialog();
            if (activeEffect != null) {
                activeEffect.updateEffect();
            }

        }
    }

    public static void drawBackground(Graphics var0) {
        if (backgroundImage == null) {
            var0.setColor(getBackgroundColor());
            var0.fillRect(0, 0, getScreenWidth(), getScreenHeight());
        } else {
            int var1 = backgroundImage.getWidth();

            for (int var2 = 0; var2 < GameEngineBase.getScreenWidth() / var1; ++var2) {
                var0.drawImage(backgroundImage, var2 * var1, 0, 20);
            }

        }
    }

    private void updateScrollArrowVisibility(int var1) {
        switch (var1) {
            case 0:
                if (this.worldMapScrollY >= 0) {
                    this.mapScrollArrows[var1].deactivate();
                    return;
                }

                this.mapScrollArrows[var1].activate();
                return;
            case 1:
                if (this.worldMapScrollY + (this.mapNodeSizes[(this.currentWorldMapId << 1) + 1] << 3) * 5 <= getScreenHeight()) {
                    this.mapScrollArrows[var1].deactivate();
                    return;
                }

                this.mapScrollArrows[var1].activate();
                return;
            case 2:
                if (this.worldMapScrollX >= 0) {
                    this.mapScrollArrows[var1].deactivate();
                    return;
                }

                this.mapScrollArrows[var1].activate();
                return;
            case 3:
                if (this.worldMapScrollX + (this.mapNodeSizes[this.currentWorldMapId << 1] << 4) * 5 <= getScreenWidth()) {
                    this.mapScrollArrows[var1].deactivate();
                    return;
                } else {
                    this.mapScrollArrows[var1].activate();
                }
            default:
        }
    }

    public final void renderPauseScreen(Graphics var1) {
        WorldGameSession var2;
        Graphics var3;
        int var4;
        int[] var5;
        if (this.currentState == 4) {
            var3 = var1;
            var2 = this;
            var1.drawImage(BattleSystemManager.B().C29_f400, 0, 0, 20);
            int var11 = (var5 = this.worldMapNodes[this.currentWorldMapId]).length;
            int var6 = this.selectedMapNode * 7;

            int var9;
            for (var4 = 0; var4 < var11; var4 += 7) {
                if (var2.currentRegionId == var5[var4 + 2] && var2.currentAreaId == var5[var4 + 3]) {
                    var3.setColor(188, 122, 255);
                } else {
                    var3.setColor(var2.worldMapColors[var2.currentWorldMapId << 1]);
                }

                var3.fillRoundRect(var2.worldMapScrollX + (var5[var4] << 4), var2.worldMapScrollY + (var5[var4 + 1] << 3),
                        var5[var4 + 5] << 4, var5[var4 + 6] << 3, 12, 12);
                var3.setColor(0);
                var3.drawRoundRect(var2.worldMapScrollX + (var5[var4] << 4), var2.worldMapScrollY + (var5[var4 + 1] << 3),
                        var5[var4 + 5] << 4, var5[var4 + 6] << 3, 12, 12);
                if (var4 == var6) {
                    var2.mapCursor.setWorldPosition(var2.worldMapScrollX + (var5[var4] << 4) + 16 * var5[var4 + 5] / 2,
                            var2.worldMapScrollY + (var5[var4 + 1] << 3) + 8 * var5[var4 + 6] / 2 + 20);
                    var2.mapCursor.render(var3, 0, 0);
                }

                String[] var7;
                int var8 = (var7 = BitmapFontRenderer.wrapText(GameEngineBase.getLocalizedText(var5[var4 + 4]),
                        var5[var4 + 5] << 4)).length;

                for (var9 = 0; var9 < var8; ++var9) {
                    GameUtils.a(var3, var7[var9], var2.worldMapColors[(var2.currentWorldMapId << 1) + 1],
                            var2.worldMapScrollX + (var5[var4] << 4) + 16 * var5[var4 + 5] / 2,
                            var2.worldMapScrollY + (var5[var4 + 1] << 3) + 8 * var5[var4 + 6] / 2
                                    + (var9 - var8 / 2) * (BitmapFontRenderer.fontHeight + 1),
                            17, 17, var2.dialogManager.textRenderer, -1);
                }
            }

            var3.setColor(65280);
            var11 = var2.activeNpcList.size();
            TileMapRenderer var13 = TileMapRenderer.getInstance();

            int var12;
            for (var9 = 0; var9 < var11; ++var9) {
                NPCEntity var10;
                var4 = ((var10 = (NPCEntity) var2.activeNpcList.elementAt(var9)).worldX * var5[var6 + 5] << 4)
                        / var13.mapPixelWidth + (var5[var6] << 4) + var2.worldMapScrollX;
                var12 = (var10.worldY * var5[var6 + 6] << 3) / var13.mapPixelHeight + (var5[var6 + 1] << 3) + var2.worldMapScrollY;
                if (var10.getFacingDirection() != 0
                        && ((NPCEntity) var2.activeNpcList.elementAt(var9)).getFacingDirection() != 1) {
                    var3.fillRect(var4, var12 - 5, 3, 9);
                } else {
                    var3.fillRect(var4, var12 - 2, 9, 3);
                }
            }

            if (var2.gymLocationIndex != -1) {
                var4 = (var2.gymLocations[(var2.gymLocationIndex << 2) + 2] * var5[var6 + 5] << 4) / var13.mapPixelWidth
                        + (var5[var6] << 4) + var2.worldMapScrollX;
                var12 = (var2.gymLocations[(var2.gymLocationIndex << 2) + 3] * var5[var6 + 6] << 3) / var13.mapPixelHeight
                        + (var5[var6 + 1] << 3) + var2.worldMapScrollY;
                var3.setColor(16711680);
                var3.fillRect(var4, var12, 6, 6);
            }

            if (var2.portLocationIndex != -1) {
                var4 = (var2.portLocations[(var2.portLocationIndex << 2) + 2] * var5[var6 + 5] << 4) / var13.mapPixelWidth
                        + (var5[var6] << 4) + var2.worldMapScrollX;
                var12 = (var2.portLocations[(var2.portLocationIndex << 2) + 3] * var5[var6 + 6] << 3) / var13.mapPixelHeight
                        + (var5[var6 + 1] << 3) + var2.worldMapScrollY;
                var3.setColor(2758133);
                var3.fillRect(var4, var12, 6, 6);
            }

            for (var9 = 0; var9 < var2.mapScrollArrows.length; ++var9) {
                var2.mapScrollArrows[var9].render(var3, 0, 0);
            }

            var3.setColor(1862801);
            var3.fillRect(0, GameEngineBase.getScreenHeight() - 30, GameEngineBase.getScreenWidth(), 30);
            var3.setColor(65280);
            var3.fillRect(15, GameEngineBase.getScreenHeight() - 22, 16, 16);
            BitmapFontRenderer.drawText(var3, "Cửa ra vào", 35, GameEngineBase.getScreenHeight() - 18);
            var3.setColor(2758133);
            var3.fillRect(90, GameEngineBase.getScreenHeight() - 22, 16, 16);
            BitmapFontRenderer.drawText(var3, "Bến tàu", 110, GameEngineBase.getScreenHeight() - 18);
            var3.setColor(16711680);
            var3.fillRect(150, GameEngineBase.getScreenHeight() - 22, 16, 16);
            BitmapFontRenderer.drawText(var3, "Cửa Đạo quán", 168, GameEngineBase.getScreenHeight() - 18);
        } else {
            if (this.currentState == 0 || this.currentState == 23 || this.gameController.C9_f132) {
                drawBackground(var1);
                ScreenTransitionManager.getInstance().renderParticleBackground(var1);
                this.worldRenderer.renderWorld(var1);
                ScreenTransitionManager.getInstance().renderParticles(var1);
                if (this.gameController.C9_f132) {
                    this.gameController.C9_f132 = false;
                }
            }

            if (ScreenTransitionManager.getInstance().spotlightMode != -1) {
                if (this.cameraController.followTarget instanceof PlayerCharacter) {
                    ScreenTransitionManager.getInstance().setSpotlightPosition(this.cameraController.worldX - TileMapRenderer.getInstance().cameraX,
                            this.cameraController.worldY - TileMapRenderer.getInstance().cameraY
                                    - this.vehicleOffsets[this.player.currentVehicleType + 1]);
                } else {
                    ScreenTransitionManager.getInstance().setSpotlightPosition(this.cameraController.worldX - TileMapRenderer.getInstance().cameraX,
                            this.cameraController.worldY - TileMapRenderer.getInstance().cameraY - 20);
                }

                ScreenTransitionManager.getInstance().renderSpotlight(var1, 0, 0);
            }

            QuestManager var10000 = this.questManager;
            QuestManager.renderEffects(var1);
            this.dialogManager.render(var1);
            if (activeEffect != null) {
                activeEffect.render(var1);
            }

            var3 = var1;
            var2 = this;

            for (var4 = 0; var4 < var2.player.miscData.size(); ++var4) {
                var5 = (int[]) var2.player.miscData.elementAt(var4);
                GameUtils.a(var3, "+" + var5[0], 16704699, var5[1] + 12 - var2.tileMapRenderer.cameraX,
                        var5[2] - var5[3] - var2.tileMapRenderer.cameraY, 17, 17, var2.dialogManager.textRenderer, 2);
                var3.drawImage(goldIcon, var5[1] - var2.tileMapRenderer.cameraX - 6, var5[2] - var5[3] - var2.tileMapRenderer.cameraY,
                        20);
            }

            if (!this.gameController.j() && !isActionActive()) {
                this.questManager.renderPauseScreen(var1);
            }

            if (this.currentRegionId == 3 && this.currentAreaId == 7 && this.currentState == 0) {
                if (this.questManager.questEffectCounter > 0) {
                    if (this.diveImages != null) {
                        var1.drawImage(this.diveImages[this.questManager.questEffectCounter - 1], getScreenWidth() >> 1,
                                getScreenHeight() >> 1, 3);
                        return;
                    }
                } else if (GameScreenManager.getInstance().pauseStartTime != 0L) {
                    var1.setColor(896);
                    var1.setFont(getLargeFont());
                    var1.drawString(formatTimeString(
                                    GameScreenManager.getInstance().pauseStartTime - GameScreenManager.getInstance().gameStartTime)[0],
                            10, 40, 20);
                }
            }

        }
    }

    private void updateExplorationMode() {
        WorldGameSession var1;
        if (!this.questManager.hasActiveEvent() && this.player.getFacingDirection() < 5 && !this.gameController.j()
                && this.gameController.J()) {
            if (this.isKeyHeld(4100)) {
                this.player.setFacingState((byte) 1, (byte) 2);
            } else if (this.isKeyHeld(8448)) {
                this.player.setFacingState((byte) 1, (byte) 0);
            } else if (this.isKeyHeld(16400)) {
                this.player.setFacingState((byte) 1, (byte) 3);
            } else if (this.isKeyHeld(32832)) {
                this.player.setFacingState((byte) 1, (byte) 1);
            }

            if (this.isKeyPressed(65568)) {
                if (currentInteractNpcId != -1) {
                    this.player.setFacingState((byte) 0, (byte) this.player.currentDirection);
                    if (QuestManager.isQuestTriggered) {
                        QuestManager.isQuestReady = true;
                        QuestManager.isQuestTriggered = false;
                    } else {
                        if (this.NPCs[currentInteractNpcId].sprite.spriteSetId <= 85) {
                            this.NPCs[currentInteractNpcId].currentAnimationId = this.NPCs[currentInteractNpcId].currentDirection;
                            byte var2;
                            NPCEntity var10000;
                            switch (this.player.currentDirection) {
                                case 0:
                                    var10000 = this.NPCs[currentInteractNpcId];
                                    var2 = 2;
                                    var10000.currentDirection = var2;
                                    break;
                                case 1:
                                    var10000 = this.NPCs[currentInteractNpcId];
                                    var2 = 3;
                                    var10000.currentDirection = var2;
                                    break;
                                case 2:
                                    var10000 = this.NPCs[currentInteractNpcId];
                                    var2 = 0;
                                    var10000.currentDirection = var2;
                                    break;
                                case 3:
                                    var10000 = this.NPCs[currentInteractNpcId];
                                    var2 = 1;
                                    var10000.currentDirection = var2;
                            }

                            this.NPCs[currentInteractNpcId].setDirection((byte) 0);
                        }

                        if (this.NPCs[currentInteractNpcId].sprite.spriteSetId == 17) {
                            this.gameController.C9_f128 = 0;
                            this.changeState((byte) 27);
                        } else {
                            this.changeState((byte) 23);
                        }
                    }

                    getInstance().removeInteractionMarker();
                } else if ((NPCEntity) this.player.followTarget != null
                        && ((NPCEntity) this.player.followTarget).npcType == 3) {
                    this.player.openChest();
                } else {
                    this.player.startNPCInteraction();
                }
            }

            if (this.isKeyReleased(61780)) {
                this.player.setFacingState((byte) 0, (byte) this.player.currentDirection);
            }

            if (this.isKeyPressed(262144)) {
                this.handleActionResponse();
                this.gameController.C9_f125 = 0;
                this.changeState((byte) 6);
            } else if (this.isKeyPressed(131072)) {
                this.gameController.C9_f125 = 0;
                this.changeState((byte) 13);
            } else if (this.isKeyPressed(1)) {
                var1 = this;
                boolean var5 = true;

                for (int var3 = 0; var3 < NO_MAP_REGIONS.length; ++var3) {
                    if (NO_MAP_REGIONS[var3] == var1.currentRegionId) {
                        var5 = false;
                        break;
                    }
                }

                if (var5) {
                    byte var6;
                    for (var6 = 0; var6 < var1.worldMapNodes[var1.currentWorldMapId].length / 7; ++var6) {
                        if (var1.worldMapNodes[var1.currentWorldMapId][var6 * 7 + 2] == var1.currentRegionId
                                && var1.worldMapNodes[var1.currentWorldMapId][var6 * 7 + 3] == var1.currentAreaId) {
                            var1.selectedMapNode = var6;
                            break;
                        }
                    }

                    var1.worldMapTargetX = (getScreenWidth() >> 1) - (var1.worldMapNodes[var1.currentWorldMapId][var1.selectedMapNode * 7] << 4) - 40;
                    var1.worldMapTargetY = (getScreenHeight() >> 1) - (var1.worldMapNodes[var1.currentWorldMapId][var1.selectedMapNode * 7 + 1] << 3)
                            - 20;
                    var1.isWorldMapScrolling = true;
                    if (var1.mapCursor == null) {
                        var1.mapCursor = new GameObject();
                        var1.mapCursor.loadSpriteSet(0, false);
                        var1.mapCursor.setAnimation((byte) 3, (byte) -1, false);
                        var1.mapCursor.activate();
                    }

                    if (var1.mapScrollArrows == null) {
                        var1.mapScrollArrows = new GameObject[4];

                        for (var6 = 0; var6 < var1.mapScrollArrows.length; ++var6) {
                            var1.mapScrollArrows[var6] = new GameObject();
                            var1.mapScrollArrows[var6].loadSpriteSet(223, false);
                            if (var6 <= 1) {
                                var1.mapScrollArrows[var6].setWorldPosition(getScreenWidth() >> 1,
                                        20 + var6 * (getScreenHeight() - 20));
                            } else {
                                var1.mapScrollArrows[var6].setWorldPosition(10 + var6 % 2 * (getScreenWidth() - 20),
                                        getScreenHeight() >> 1);
                            }

                            var1.mapScrollArrows[var6].setAnimation(var6, (byte) -1, false);
                            var1.updateScrollArrowVisibility(var6);
                        }
                    }

                    BattleSystemManager.B().C29_f400 = Image.createImage(GameEngineBase.getScreenWidth(),
                            GameEngineBase.getScreenHeight());
                    Graphics var8 = BattleSystemManager.B().C29_f400.getGraphics();
                    var1.player.setFacingState((byte) 0, (byte) var1.player.currentDirection);
                    var1.worldRenderer.renderCutsceneMode(var8);
                    var1.changeState((byte) 4);
                } else {
                    var1.gameController.b("Khu này không có bản đồ");
                }
            } else if (this.isKeyPressed(2)) {
                this.gameController.C9_f125 = 0;
                this.changeState((byte) 10);
            } else if (this.isKeyPressed(8)) {
                this.gameController.C9_f125 = 1;
                this.changeState((byte) 10);
            } else if (this.isKeyPressed(512)) {
                label235:
                {
                    boolean var10;
                    if (this.currentRegionId != 3 || this.currentAreaId != 7) {
                        if (this.player.currentVehicleType >= 0 && this.questManager.isEventVisible) {
                            if (this.player.canDismountVehicle()) {
                                this.player.resetToDefaultSprite();
                            }

                            var10 = false;
                            break label235;
                        }

                        if (this.questManager.isEventVisible) {
                            this.changeState((byte) 5);
                            var10 = true;
                            break label235;
                        }
                    }

                    var10 = false;
                }
            }
        }

        this.player.updateMovement();

        for (int var4 = 0; var4 < this.NPCs.length; ++var4) {
            this.NPCs[var4].updateNPCBehavior();
        }

        if (this.followingPet != null && this.followingPet.isActive()) {
            this.followingPet.updateMovementTrail(this.followTarget.sprite, this.followingPet.sprite);
        }

        this.player.visualState = this.player.currentDirection;
        this.worldRenderer.updateWorld();
        this.checkWildEncounter();
        if (tutorialStep == 1 && paymentActive) {
            this.changeState((byte) 25);
        }

        if (!this.questManager.hasActiveEvent() && !this.gameController.J() && !isTutorialActive && tutorialTargetPokemon[0] != -1
                && this.isKeyPressed(65568)) {
            actionType = 4;
            isTutorialActive = true;
            this.gameController.C9_f126 = 0;
            this.changeState((byte) 7);
            this.gameController.I();
        }

        if (!this.gameController.j() && eggProductionNotified == 0 && this.canProduceEgg()) {
            this.gameController.b("Có thể tiến hành sản xuất trứng sủng vật");
            eggProductionNotified = 1;
        }

        this.questManager.updateEffects();
        this.gameController.e();
        var1 = this;

        for (int var7 = 0; var7 < var1.player.miscData.size(); ++var7) {
            int[] var9;
            int[] var11 = var9 = (int[]) var1.player.miscData.elementAt(var7);
            var11[3] += 5;
            if (var9[3] > 30) {
                var1.player.miscData.removeElementAt(var7);
                --var7;
            }
        }

        if (!this.gameController.j()) {
            this.questManager.update();
            this.updateActionSequence();
        }

    }

    private void updateBadgeDialogMode() {
        if (this.gameController.d(dialogTextId, dialogPortraitId) && !this.gameController.j() && this.isKeyPressed(196640)) {
            if (GameUtils.pageCount < GameUtils.b()) {
                GameUtils.c();
                this.gameController.b(GameUtils.pageCount);
            } else {
                this.gameController.aF();
                if (this.NPCs[currentInteractNpcId].sprite.spriteSetId <= 85) {
                    NPCEntity var10000 = this.NPCs[currentInteractNpcId];
                    byte var2 = this.NPCs[currentInteractNpcId].currentAnimationId;
                    var10000.currentDirection = var2;
                }

                this.NPCs[currentInteractNpcId].setDirection((byte) 0);
                this.player.setFacingState((byte) 0, (byte) this.player.currentDirection);
                this.gameController.C9_f131 = 1;
                if (this.badgeRewardPending) {
                    this.badgeCollected[this.currentBadgeTier] = true;
                    if (this.currentBadgeTier < this.badgeRequirements.length - 1) {
                        this.player.addBadges(1);
                        this.gameController.b("Đạt được 1 huy hiệu");
                    } else if (this.player.getBadgeState((byte) 7, (byte) 0) == 0) {
                        this.gameController.b("Đạt được hoàng kim huy hiệu");
                        this.player.setBadgeState((byte) 7, (byte) 0, (byte) 2);
                        QuestManager.questDialogState = (byte) (QuestManager.questDescriptions.length / 2);
                    }
                }
            }
        }

        this.gameController.f();
        if (this.gameController.C9_f131 == 1 && this.gameController.aA()) {
            this.gameController.C9_f128 = 0;
            this.changeState((byte) 27);
        }

        this.worldRenderer.updateWorld();
    }

    private boolean triggerWildEncounter(int[] var1) {
        int var2 = -1;
        if (var1[2] != -1) {
            var2 = GameUtils.getRandomInRange(var1[2], var1[3]);
        }

        if (!this.hasWildEncounterData()) {
            return false;
        } else {
            int var3 = GameUtils.getRandomInRange(petAreaData[REGION_OFFSETS[this.currentRegionId] + this.currentAreaId][3],
                    petAreaData[REGION_OFFSETS[this.currentRegionId] + this.currentAreaId][4]);
            this.player.registerCreature((byte) ResourceManager.gameDatabase[0][var1[0]][1], var1[0], (byte) 1);
            BattleSystemManager.B().a(new int[][]{{var1[0], var3, var2}});
            return true;
        }
    }

    public final void triggerSpecificEncounter(int var1) {
        int var2 = 0;

        while (true) {
            int var4 = this.currentAreaId;
            int var3 = this.currentRegionId;
            if (var2 >= (petAreaData[REGION_OFFSETS[var3] + var4].length - 5) / 4) {
                return;
            }

            var4 = this.currentAreaId;
            var3 = this.currentRegionId;
            if (var1 == petAreaData[REGION_OFFSETS[var3] + var4][5 + (var2 << 2) + 4]) {
                int[] var5 = new int[4];
                var4 = this.currentAreaId;
                var3 = this.currentRegionId;
                System.arraycopy(petAreaData[REGION_OFFSETS[var3] + var4], 5 + (var2 << 2), var5, 0, var5.length);
                this.triggerWildEncounter(var5);
                this.startBattle();
                return;
            }

            ++var2;
        }
    }

    public final void setWildPokemonVisible(boolean var1) {
        if (this.wildEncounterEnabled != var1) {
            if (this.hasWildEncounterData()) {
                this.wildEncounterEnabled = var1;
                int var3 = 0;

                while (true) {
                    int var4 = this.currentAreaId;
                    int var2 = this.currentRegionId;
                    if (var3 >= (petAreaData[REGION_OFFSETS[var2] + var4].length - 5) / 4) {
                        return;
                    }

                    var4 = this.currentAreaId;
                    var2 = this.currentRegionId;
                    short var5 = petAreaData[REGION_OFFSETS[var2] + var4][5 + (var3 << 2) + 4];
                    if (var1) {
                        this.NPCs[var5].activate();
                    } else {
                        this.NPCs[var5].deactivate();
                    }

                    ++var3;
                }
            }
        }
    }

    public final void checkWildEncounter() {
        if (this.player.shouldTriggerEncounter()) {
            boolean var10000;
            label47:
            {
                byte var2 = TileMapRenderer.getInstance().getTileAt(0, PlayerCharacter.getInstance().worldX,
                        PlayerCharacter.getInstance().worldY);
                int[] var3 = null;
                currentTileType = var2;
                switch (var2) {
                    case 0:
                        if (grassEncounterPool.size() <= 0) {
                            var10000 = false;
                            break label47;
                        }

                        var3 = (int[]) grassEncounterPool.elementAt(GameUtils.getRandomInt(grassEncounterPool.size()));
                        break;
                    case 1:
                        if (waterEncounterPool.size() <= 0) {
                            var10000 = false;
                            break label47;
                        }

                        var3 = (int[]) waterEncounterPool.elementAt(GameUtils.getRandomInt(waterEncounterPool.size()));
                        break;
                    case 2:
                        if (caveEncounterPool.size() <= 0) {
                            var10000 = false;
                            break label47;
                        }

                        var3 = (int[]) caveEncounterPool.elementAt(GameUtils.getRandomInt(caveEncounterPool.size()));
                        break;
                    case 3:
                        var10000 = false;
                        break label47;
                    case 4:
                        if (specialEncounterPool.size() <= 0) {
                            var10000 = false;
                            break label47;
                        }

                        var3 = (int[]) specialEncounterPool.elementAt(GameUtils.getRandomInt(specialEncounterPool.size()));
                }

                var10000 = this.triggerWildEncounter(var3);
            }

            if (!var10000) {
                this.player.encounterStepsRemaining = this.player.getEncounterSteps();
                return;
            }

            if (this.currentRegionId == 3 && this.currentAreaId == 7) {
                QuestManager.isMapControl = false;
            } else {
                QuestManager.isMapControl = true;
            }

            this.startBattle();
        }

    }

    public final void captureScreenForBattle() {
        BattleSystemManager.B().C29_f400 = Image.createImage(GameEngineBase.getScreenWidth(),
                GameEngineBase.getScreenHeight());
        Graphics var1 = BattleSystemManager.B().C29_f400.getGraphics();
        this.worldRenderer.renderCutsceneMode(var1);
    }

    private void startBattle() {
        BattleSystemManager var10000 = BattleSystemManager.B();
        BattleSystemManager.B().getClass();
        var10000.C29_f397 = 0;
        BattleSystemManager.B().C29_f398 = 0;
        this.captureScreenForBattle();
        this.player.setFacingState((byte) 0, (byte) this.player.currentDirection);
        this.player.encounterStepsRemaining = this.player.getEncounterSteps();
        GameScreenManager.getInstance().changeState((byte) 12);
        this.audioManager.playBackgroundMusic(4, 1);
    }

    public static int getAreaIndex(int var0, int var1) {
        return REGION_OFFSETS[var0] + var1;
    }

    public final void setNPCState(int var1, int var2, byte var3, boolean var4) {
        int var6 = this.currentAreaId;
        int var5 = this.currentRegionId;
        if (npcStateData[REGION_OFFSETS[var5] + var6] != null) {
            var6 = this.currentAreaId;
            var5 = this.currentRegionId;
            if (npcStateData[REGION_OFFSETS[var5] + var6][var1] != null) {
                var6 = this.currentAreaId;
                var5 = this.currentRegionId;
                areaVisitedFlags[REGION_OFFSETS[var5] + var6][0] = var4;
                var6 = this.currentAreaId;
                var5 = this.currentRegionId;
                npcStateData[REGION_OFFSETS[var5] + var6][var1][var2] = var3;
            }
        }

    }

    public final void setNPCPosition(int var1, int var2, int var3) {
        int var5 = this.currentAreaId;
        int var4 = this.currentRegionId;
        if (npcPositionData[REGION_OFFSETS[var4] + var5] != null) {
            var5 = this.currentAreaId;
            var4 = this.currentRegionId;
            if (npcPositionData[REGION_OFFSETS[var4] + var5][var1] != null) {
                var5 = this.currentAreaId;
                var4 = this.currentRegionId;
                npcPositionData[REGION_OFFSETS[var4] + var5][var1][var2] = (short) var3;
            }
        }

    }

    public final boolean checkBadgeRequirement(int var1) {
        for (int var2 = 0; var2 < this.badgeCheckData.length / 4; ++var2) {
            if (this.badgeCheckData[var2 << 2] == this.currentRegionId && this.badgeCheckData[(var2 << 2) + 1] == this.currentAreaId
                    && var1 == this.badgeCheckData[(var2 << 2) + 2]
                    && this.player.badgeStates[this.badgeCheckData[(var2 << 2) + 3]][0] == 2) {
                return true;
            }
        }

        return false;
    }

    public final boolean canProduceEgg() {
        return this.player.pokedexCount == 0 && totalBattleCount >= 10 || this.player.pokedexCount > 0 && totalBattleCount >= 30;
    }

    private void calculateBadgeProgress() {
        this.currentBadgeTier = (byte) this.badgeCollected.length;

        for (int var1 = 0; var1 < this.badgeCollected.length; ++var1) {
            if (!this.badgeCollected[var1]) {
                this.currentBadgeTier = (byte) var1;
                break;
            }
        }

        boolean var3 = false;

        for (int var2 = this.badgeRequirements.length - 1; var2 >= 0; --var2) {
            if (this.player.totalCreaturesSeen >= this.badgeRequirements[var2]) {
                this.achievedBadgeTier = (byte) var2;
                var3 = true;
                break;
            }
        }

        if (!var3) {
        }

    }

    public final void updateActionSequence() {
        String var2;
        switch (actionType) {
            case 1:
                if (currentAction == 0) {
                    setActionData(0, 1);
                    if (paymentActive) {
                        setActionData(1, 1);
                    } else {
                        setActionData(1, 0);
                    }

                    ++currentAction;
                    this.changeState((byte) 6);
                    return;
                }

                if (currentAction == 1) {
                    ++currentAction;
                    this.gameController.c("Hãy lựa chọn #2Sủng vật");
                    return;
                }

                if (currentAction == 3) {
                    setActionData(1, 0);
                    var2 = getLocalizedText(
                            ResourceManager.gameDatabase[0][this.player.partyPokemon[GameEngineBase.getActionData(1)].getSpeciesId()][0]);
                    ++currentAction;
                    this.gameController.c("Hãy lựa chọn #2" + var2);
                    return;
                }

                if (currentAction == 4) {
                    if (this.gameController.aB()
                            && GameEngineBase.isActionBlocked((int) this.gameController.C9_f125, (int) 0)) {
                        ++currentAction;
                        this.gameController.c("Hãy nhấn #2nút 5");
                        return;
                    }
                } else {
                    if (currentAction == 6) {
                        ++currentAction;
                        this.gameController.c("Hãy lựa chọn #2Vật phẩm trang sức");
                        return;
                    }

                    if (currentAction == 8) {
                        setActionData(1, 0);
                        ++currentAction;
                        this.gameController.c("Nhấn #2nút 5#1 trang thượng vật phẩm trang sức");
                        return;
                    }

                    if (currentAction == 10) {
                        this.gameController.c("Nhấn #2nút mềm phải#0 để quay lại");
                        setActionData(1, -1);
                        setActionData(0, 2);
                        ++currentAction;
                        return;
                    }
                }
            case 2:
            case 5:
            default:
                break;
            case 3:
                if (currentAction == 0) {
                    ++currentAction;
                    setActionData(1, 0);
                    setActionData(0, 1);
                    this.changeState((byte) 1);
                    return;
                }

                if (currentAction == 1) {
                    if (isActionBlocked(this.gameController.C9_f125, 0)) {
                        ++currentAction;
                        this.gameController.c("Hãy nhấn vào mục #2Mua sắm");
                        return;
                    }
                } else {
                    if (currentAction == 3) {
                        ++currentAction;
                        this.gameController.c("Trước tiên hãy mua #2Hồng sắc ốc biển#1");
                        return;
                    }

                    if (currentAction == 4) {
                        if (this.gameController.aB()) {
                            setActionData(1, 1);
                            ++currentAction;
                            return;
                        }
                    } else if (currentAction == 5) {
                        if (isActionBlocked(this.gameController.C9_f125, 0)) {
                            ++currentAction;
                            this.gameController.c("Nhấn #2nút 5#1 mua sắm");
                            return;
                        }
                    } else if (currentAction == 7) {
                        this.gameController.c("Hãy nhấn #2nút mềm phải#1 để quay lại");
                        setActionData(1, -1);
                        setActionData(0, 2);
                        ++currentAction;
                        return;
                    }
                }
                break;
            case 4:
                if (currentAction == 0) {
                    setActionData(0, 1);

                    for (int var1 = 0; var1 < this.player.partySize; ++var1) {
                        if (this.player.partyPokemon[var1].getLevel() == tutorialTargetPokemon[0]
                                && this.player.partyPokemon[var1].getSpeciesId() == tutorialTargetPokemon[1]) {
                            setActionData(1, var1);
                            break;
                        }
                    }

                    ++currentAction;
                    var2 = getLocalizedText(ResourceManager.getDatabaseValue((byte) 0,
                            (short) this.player.partyPokemon[GameEngineBase.getActionData(1)].getSpeciesId(), (byte) 0));
                    this.gameController.c("Hãy lựa chọn #2" + var2 + "#0 tiến hành tiến hóa");
                    return;
                }

                if (currentAction == 1) {
                    if (isActionBlocked(this.gameController.C9_f125, 0) && this.gameController.aB()) {
                        ++currentAction;
                        this.gameController.c("Hãy nhấn #2nút 5#0 để tiếp tục");
                        return;
                    }
                } else if (currentAction == 3) {
                    if (isActionBlocked(this.gameController.C9_f126, 0)) {
                        ++currentAction;
                        this.gameController.c("Nhấn #2nút 5#0 để vào mục Tiến hóa");
                        return;
                    }
                } else if (currentAction == 5) {
                    ++currentAction;
                    this.gameController.c("Nhấn #2nút mềm trái#0 để Tiến hóa");
                    return;
                }
                break;
            case 6:
                if (currentAction == 0) {
                    ++currentAction;
                    setActionData(0, 1);
                    if (paymentActive) {
                        setActionData(1, 2);
                    } else {
                        setActionData(1, 1);
                    }

                    this.changeState((byte) 6);
                    return;
                }

                if (currentAction == 1) {
                    ++currentAction;
                    this.gameController.c("Hãy lựa chọn #2Ba lô#0");
                    return;
                }

                if (currentAction == 2) {
                    if (this.gameController.aB() && isActionBlocked(this.gameController.C9_f125, 0)) {
                        this.gameController.c("Nhấn #2nút mềm trái#0 vào Tuyển hạng");
                        ++currentAction;
                        return;
                    }
                } else {
                    if (currentAction == 4) {
                        ++currentAction;
                        this.gameController.c("Hãy sử dụng #2Gia tốc dược#0");
                        return;
                    }

                    if (currentAction == 5) {
                        if (this.gameController.aB() && isActionBlocked(this.gameController.C9_f136, 0)) {
                            ++currentAction;
                            this.gameController.c("Nhấn #2nút mềm trái#0 sử dụng");
                            return;
                        }
                    } else {
                        if (currentAction == 7) {
                            ++currentAction;
                            setActionData(0, 3);
                            setActionData(2, 1);
                            setActionData(1, 3);
                            this.gameController.c("Hãy lựa chọn #2Đặc thù đạo cụ#0 ấp trứng trứng sủng vật");
                            return;
                        }

                        if (currentAction == 9) {
                            if (this.gameController.aB() && isActionBlocked(this.gameController.C9_f136, 0)) {
                                setActionData(0, 1);
                                this.gameController.c("Nhấn #2nút mềm trái#0 để Ấp trứng");
                                ++currentAction;
                                return;
                            }
                        } else if (currentAction == 11) {
                            ++currentAction;
                            setActionData(0, 2);
                            setActionData(1, -1);
                            this.gameController.c("Nhấn #2nút mềm phải#0 để quay lại");
                        }
                    }
                }
        }

    }

    public final void handleActionResponse() {
        int var1;
        switch (actionType) {
            case 1:
                if (currentAction == 2 || currentAction == 7) {
                    ++currentAction;
                    return;
                }

                if (currentAction == 5) {
                    setActionData(1, 2);
                    ++currentAction;
                    return;
                }

                if (currentAction == 9) {
                    ++currentAction;
                    int var2 = this.currentAreaId;
                    var1 = this.currentRegionId;
                    this.questManager.questStates[REGION_OFFSETS[var1] + var2][this.questManager.getCurrentEventIndex()] = 3;
                    if (this.questManager.eventScripts != null) {
                        this.questManager.eventScripts[this.questManager.getCurrentEventIndex()].setExecutionState((byte) 3);
                        return;
                    }
                }
            case 2:
            case 5:
            default:
                break;
            case 3:
                if (currentAction != 6 && currentAction != 2) {
                    break;
                }

                ++currentAction;
                return;
            case 4:
                if (currentAction == 2) {
                    setActionData(1, 5);
                    ++currentAction;
                    return;
                }

                if (currentAction == 4) {
                    ++currentAction;
                    return;
                }

                if (currentAction == 6) {
                    this.gameController.c("Nhấn #2nút mềm phải#0 để quay lại");
                    setActionData(1, -1);
                    setActionData(0, 2);
                    ++currentAction;
                    return;
                }
                break;
            case 6:
                if (currentAction == 3) {
                    for (var1 = 0; var1 < this.player.equipmentInventory.size() + this.player.consumableInventory.size(); ++var1) {
                        if (var1 >= this.player.consumableInventory.size()) {
                            if (this.player.equipmentInventory.size() <= 0) {
                                break;
                            }

                            if (((int[]) this.player.equipmentInventory.elementAt(var1 - this.player.consumableInventory.size()))[0] == 14) {
                                setActionData(1, var1);
                                break;
                            }
                        }
                    }

                    ++currentAction;
                    return;
                }

                if (currentAction == 6 || currentAction == 10) {
                    ++currentAction;
                    return;
                }

                if (currentAction == 8 && isActionBlocked(this.gameController.C9_f125, 1)) {
                    this.gameController.c("Hãy lựa chọn #2Trứng sủng vật#0 để ấp trứng");
                    setActionData(2, 0);
                    setActionData(1, 0);
                    ++currentAction;
                }
        }

    }

    protected static String[] formatTimeString(long var0) {
        String[] var2;
        (var2 = new String[2])[1] = var2[0] = "0'00\"000";
        long var3 = var0 % 1000L;
        long var5 = (var0 /= 1000L) % 60L;
        long var7 = (var0 /= 60L) % 60L;
        long var9 = var0 / 60L;
        String var12;
        if (var3 < 10L) {
            var12 = "00" + var3;
        } else if (var3 < 100L) {
            var12 = "0" + var3;
        } else {
            var12 = "" + var3;
        }

        String var4;
        if (var5 < 10L) {
            var4 = "0" + var5 + "\"";
        } else {
            var4 = var5 + "\"";
        }

        String var11 = var0 + "'";
        var2[0] = var11 + var4 + var12;
        String var1 = var9 + "'";
        var11 = var7 + "\"";
        if (var5 < 10L) {
            var4 = "0" + var5;
        } else {
            var4 = "" + var5;
        }

        var2[1] = var1 + var11 + var4;
        return var2;
    }
}
