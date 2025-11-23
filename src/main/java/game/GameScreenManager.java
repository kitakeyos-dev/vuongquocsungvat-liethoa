package game;

import engine.GameEngineBase;
import engine.GameUtils;
import engine.graphics.ScreenTransitionManager;
import engine.graphics.ImageData;
import engine.entity.SpriteDataCache;
import engine.entity.ResourceManager;
import engine.entity.TileMapRenderer;
import layout.DialogManager;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class GameScreenManager extends GameEngineBase {
    private static GameScreenManager instance = null;
    private byte currentState;
    private byte previousState;
    private int stateCounter;
    private Image currentImage;
    private GameEngineBase currentScreen;
    private static int loadingProgress = 0;
    private static int loadingSpeed = 10;
    private String[] elementalDescriptions = new String[]{"Hỏa hệ khắc mộc hệ", "Mộc hệ khắc thổ hệ", "Thổ hệ khắc thủy hệ", "Thủy hệ khắc hỏa hệ", "Quỷ hệ khắc phong hệ", "Phong hệ khắc điện hệ", "Điện hệ khắc quỷ hệ"};
    private TransitionScreen tutorialManager = TransitionScreen.getInstance();
    public long gameStartTime = 0L;
    public long pauseStartTime = 0L;
    public long battleStartTime = 0L;
    public long storyStartTime = 0L;
    public long worldMapTime = 0L;
    public long currentTime = 0L;
    ImageData backgroundEffect = null;
    private String currentMessage = "";
    public byte difficultyLevel = 0;

    public static GameScreenManager getInstance() {
        if (instance == null) {
            instance = new GameScreenManager();
        }

        return instance;
    }

    public final void setActive(boolean active) {
        if (active) {
            this.activate();
        } else {
            this.deactivate();
        }
    }

    public final void activate() {
        this.currentTime = System.currentTimeMillis();
        this.changeState((byte) 3);
        super.setActive(true);
    }

    private void deactivate() {
        this.changeState((byte) 1);
        super.setActive(false);
    }

    public final void changeState(byte var1) {
        if (var1 < 24) {
            this.previousState = this.currentState;
            switch (this.currentState) {
                case 2:
                case 4:
                case 5:
                case 6:
                case 8:
                case 10:
                case 11:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                default:
                    break;
                case 3:
                    ScreenTransitionManager.a().C30_f472 = -1;
                    break;
                case 7:
                case 9:
                case 12:
                case 22:
                case 23:
                    loadingProgress = 0;
            }

            this.currentState = var1;
            switch (var1) {
                case 2:
                    if (GameWorldManager.B().C25_f286 != null) {
                        GameWorldManager.B().C25_f286.I();
                    }
                    break;
                case 3:
                    loadingProgress = 0;
                    ScreenTransitionManager.a().c(0, 19);
                case 4:
                case 5:
                case 7:
                case 8:
                case 10:
                case 13:
                case 14:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                default:
                    break;
                case 6:
                    this.currentImage = null;
                    this.currentImage = GameUtils.loadImage("/data/img/", "img_831");
                    break;
                case 9:
                    resetEngine(false);
                    int var2 = GameUtils.getRandomInt(this.elementalDescriptions.length);
                    this.currentMessage = this.elementalDescriptions[var2];
                    break;
                case 11:
                    GameWorldManager.B().gameController.C9_f132 = true;
                    break;
                case 12:
                case 22:
                    resetEngine(false);
                    loadingProgress = 0;
                    break;
                case 15:
                    this.currentImage = null;
                    this.currentImage = GameUtils.loadPNG("/data/logo/", "0");
                    break;
                case 21:
                    ScreenTransitionManager.a().c(0, 18);
                    ScreenTransitionManager.a().e();
                    break;
                case 23:
                    resetEngine(false);
                    loadingProgress = 0;
                    this.tutorialManager.changeState((byte) 1);
            }

            this.stateCounter = 0;
        }
    }

    public final byte getCurrentState() {
        return this.currentState;
    }

    public final synchronized void pauseGame() {
        if (GameWorldManager.B().C25_f342 != null) {
            GameWorldManager.B().C25_f342.stopAllAudio();
        }

        if (this.currentState != 2) {
            this.changeState((byte) 2);
            this.showSoftKeys();
        }

    }

    private void resumeGame() {
        this.changeState(this.previousState);
        if (GameWorldManager.B().C25_f342 != null) {
            GameWorldManager.B().C25_f342.resumeAllAudio();
        }

        this.showSoftKeys();
    }

    public boolean initializeGame() {
        this.startGameTimer();
        this.gameController = DialogUIManager.a();
        this.dialogManager = DialogManager.getInstance();
        this.gameController.a(this);
        ResourceManager.initializeImageCache(50000);
        SpriteDataCache.initialize(1000);
        ResourceManager.initializeAllResources();
        setBackgroundColor(0);
        getDefaultFont();
        this.tutorialManager.initializeGame();
        Image var1;
        int[] var2 = GameUtils.extractImageRGB(var1 = GameUtils.loadImage("/data/img/", "img_22"));
        this.backgroundEffect = new ImageData();
        this.backgroundEffect.setPixelData(var2, var1.getWidth(), var1.getHeight());
        GameWorldManager.B();
        GameWorldManager.G();
        stopGameTimer();
        return true;
    }

    public final void cleanupCurrentScreen() {
        if (this.currentScreen != null) {
            this.currentScreen.cleanupCurrentScreen();
            this.currentScreen = null;
        }

    }

    public final void update() {
        if (this.isActive) {
            this.updateInputState();
            switch (this.currentState) {
                case 2:
                    if (this.isKeyPressed(262144)) {
                        this.resumeGame();
                    }
                    break;
                case 3:
                    if (!isEngineStopped()) {
                        this.initializeGame();
                    }

                    if (ScreenTransitionManager.a().C30_f476 && isEngineStopped()) {
                        this.changeState((byte) 15);
                    }
                    break;
                case 4:
                    if (this.isKeyPressed(131072)) {
                        this.deactivate();
                    } else if (this.isKeyPressed(262144)) {
                        this.resumeGame();
                    }
                case 5:
                case 14:
                case 17:
                case 18:
                default:
                    break;
                case 6:
                    byte var2;
                    if (this.isKeyPressed(131072)) {
                        var2 = 1;
                        this.difficultyLevel = (byte) var2;
                        this.changeState((byte) 21);
                    } else if (this.isKeyPressed(262144)) {
                        var2 = 0;
                        this.difficultyLevel = (byte) var2;
                        this.changeState((byte) 21);
                    }
                    break;
                case 7:
                    this.cleanupCurrentScreen();
                    this.currentScreen = MainMenuScreen.getInstance();
                    this.currentScreen.initializeGame();
                    this.setChildHandler(this.currentScreen);
                    this.changeState((byte) 8);
                    break;
                case 8:
                case 11:
                case 13:
                case 20:
                    if (this.currentScreen != null) {
                        this.currentScreen.update();
                    }
                    break;
                case 9:
                case 22:
                    this.cleanupCurrentScreen();
                    this.currentScreen = GameWorldManager.B();
                    this.currentScreen.initializeGame();
                    this.setChildHandler(this.currentScreen);
                    if (this.currentState == 9 || this.currentState == 22) {
                        this.changeState((byte) 11);
                    }

                    PlayerCharacter.C53_f798 = false;
                    break;
                case 10:
                    this.cleanupCurrentScreen();
                    this.currentScreen = GameWorldManager.B();
                    ((GameWorldManager) this.currentScreen).L();
                    this.setChildHandler(this.currentScreen);
                    this.changeState((byte) 11);
                    break;
                case 12:
                    if (!isEngineStopped()) {
                        this.currentScreen = null;
                        this.currentScreen = BattleSystemManager.B();
                        this.currentScreen.initializeGame();
                        this.setChildHandler(this.currentScreen);
                        if (((BattleSystemManager) this.currentScreen).C29_f398 == 0) {
                            ScreenTransitionManager.a().c(-2013265920, 6);
                        } else if (((BattleSystemManager) this.currentScreen).C29_f398 == 2) {
                            ScreenTransitionManager.a().c(-2013265920, 8);
                        } else if (((BattleSystemManager) this.currentScreen).C29_f398 == 1) {
                            ScreenTransitionManager.a().c(-2013265920, 7);
                        }
                    }

                    if (isEngineStopped()) {
                        ScreenTransitionManager.a().d();
                        PlayerCharacter.C53_f798 = false;
                    }

                    if (ScreenTransitionManager.a().C30_f476) {
                        ((BattleSystemManager) this.currentScreen).E();
                        this.changeState((byte) 13);
                    }
                    break;
                case 15:
                    ++this.stateCounter;
                    if (this.stateCounter >= 10) {
                        this.stateCounter = 0;
                        this.currentImage = null;
                        this.currentImage = GameUtils.loadPNG("/data/logo/", "cwalogo");
                        this.changeState((byte) 16);
                    }
                    break;
                case 16:
                    ++this.stateCounter;
                    if (this.stateCounter >= 10) {
                        this.changeState((byte) 6);
                    }
                    break;
                case 19:
                    byte var1;
                    if (this.currentScreen instanceof GameWorldManager) {
                        var1 = 1;
                    } else {
                        var1 = 2;
                    }

                    this.currentScreen = LocationSelectionManager.B();
                    this.currentScreen.initializeGame();
                    this.setChildHandler(this.currentScreen);
                    this.currentScreen.changeState(var1);
                    this.changeState((byte) 20);
                    break;
                case 21:
                    ScreenTransitionManager.a().d();
                    if (ScreenTransitionManager.a().C30_f475 == -1 || this.isKeyPressed(65568) && MainMenuScreen.inputEnabled) {
                        ScreenTransitionManager.a().C30_f475 = -1;
                        ScreenTransitionManager.a().C30_f474 = -1;
                        ScreenTransitionManager.a().C30_f479 = 0;
                        ScreenTransitionManager.a().C30_f472 = -1;
                        this.currentImage = null;
                        this.backgroundEffect.pixels = null;
                        this.backgroundEffect = null;
                        this.changeState((byte) 7);
                    }
                    break;
                case 23:
                    ScreenTransitionManager.a().d();
                    if (this.tutorialManager.C44_f698 == 1 && ScreenTransitionManager.a().C30_f477) {
                        this.tutorialManager.changeState((byte) 2);
                    } else if (this.tutorialManager.C44_f698 == 2) {
                        if (this.tutorialManager.transitionComplete) {
                            this.cleanupCurrentScreen();
                            this.currentScreen = GameWorldManager.B();
                            this.currentScreen.initializeGame();
                            this.setChildHandler(this.currentScreen);
                            this.tutorialManager.changeState((byte) 3);
                        }
                    } else if (this.tutorialManager.C44_f698 == 3 && ScreenTransitionManager.a().C30_f477) {
                        ScreenTransitionManager.a().C30_f474 = -1;
                        this.tutorialManager.stopAnimation();
                        this.changeState((byte) 11);
                    }
            }

            if (this.C44_f698 != 2) {
                if (GameWorldManager.B().C25_f290 == 3 && GameWorldManager.B().C25_f291 == 7 && this.battleStartTime == 0L && this.gameStartTime != 0L) {
                    this.pauseStartTime = System.currentTimeMillis();
                }

                this.worldMapTime = System.currentTimeMillis();
            }

        }
    }

    public final void renderPauseScreen(Graphics var1) {
        if (this.isActive) {
            var1.setFont(getDefaultFont());
            switch (this.currentState) {
                case 2:
                    var1.setColor(0);
                    var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                    var1.setColor(16777215);
                    var1.drawString("Trò chơi tạm dừng", getScreenWidth() >> 1, getScreenCenterY(), 33);
                    var1.drawString("Quay lại", getScreenWidth() - 2, getScreenHeight() - 2, 40);
                    return;
                case 3:
                    var1.setColor(16777215);
                    var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                    ScreenTransitionManager.a().a(var1);
                    return;
                case 4:
                    return;
                case 5:
                case 14:
                case 17:
                case 18:
                case 19:
                case 22:
                default:
                    break;
                case 6:
                    var1.setColor(0);
                    var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                    var1.setFont(getDefaultFont());
                    var1.setColor(16777215);
                    var1.drawString(getLocalizedText(8), getScreenWidth() >> 1, 144, 17);
                    var1.drawString(getLocalizedText(4), 2, getScreenHeight() - 2, 36);
                    var1.drawString(getLocalizedText(5), getScreenWidth() - 2, getScreenHeight() - 2, 40);
                    var1.setColor(16739328);
                    var1.drawString(getLocalizedText(9), getScreenWidth() >> 1, 166, 17);
                    return;
                case 7:
                    return;
                case 8:
                case 11:
                case 13:
                case 20:
                    if (this.currentScreen != null) {
                        this.currentScreen.renderPauseScreen(var1);
                    }
                    break;
                case 9:
                    if (PlayerCharacter.C53_f798) {
                        var1.setColor(0);
                        var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                        if (loadingProgress % 4 == 3) {
                            PlayerCharacter.p().setAnimation((byte) 1, (byte) -1, false);
                        } else {
                            PlayerCharacter.p().setAnimation((byte) (loadingProgress % 4), (byte) -1, false);
                        }

                        PlayerCharacter var10000 = PlayerCharacter.p();
                        byte var4 = (byte) (loadingProgress % 4);
                        var10000.currentDirection = var4;
                        PlayerCharacter.p().render(var1, TileMapRenderer.getInstance().cameraX, TileMapRenderer.getInstance().cameraY - loadingProgress);
                    } else {
                        var1.setColor(0);
                        var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                    }

                    if (loadingProgress < 148) {
                        loadingProgress += loadingSpeed;
                    }

                    if (loadingProgress > 148) {
                        loadingProgress = 148;
                    }

                    if (!PlayerCharacter.C53_f798) {
                        var1.setColor(0);
                        var1.fillRect(45, getScreenHeight() - 48, 150, 5);
                        var1.setColor(7877410);
                        var1.fillRect(46, getScreenHeight() - 47, 148, 3);
                        var1.setColor(16707204);
                        var1.fillRect(46, getScreenHeight() - 47, loadingProgress, 3);
                        var1.setColor(16777215);
                        var1.drawString(this.currentMessage, getScreenWidth() >> 1, getScreenHeight() - 70, 17);
                    }

                    return;
                case 10:
                    return;
                case 12:
                    GameWorldManager.B().C25_f285.renderWorld(var1);
                    if (isEngineStopped()) {
                        ScreenTransitionManager.a().a(var1);
                        PlayerCharacter.C53_f798 = false;
                        return;
                    }
                    break;
                case 15:
                    var1.setColor(16777215);
                    var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                    var1.setColor(16777215);
                    var1.setFont(getDefaultFont());
                    if (this.currentImage != null) {
                        var1.drawImage(this.currentImage, getScreenCenterX(), getScreenCenterY(), 3);
                        return;
                    }
                    break;
                case 16:
                    var1.setColor(getBackgroundColor());
                    var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                    if (this.currentImage != null) {
                        var1.drawImage(this.currentImage, (getScreenWidth() - this.currentImage.getWidth()) / 2, (getScreenHeight() - this.currentImage.getHeight()) / 2, 20);
                        return;
                    }
                    break;
                case 21:
                    var1.drawImage(this.currentImage, 0, 0, 20);

                    for (int var2 = 0; var2 < getScreenWidth() / 10; ++var2) {
                        var1.drawRGB(this.backgroundEffect.pixels, 0, this.backgroundEffect.width, var2 * 10, 0, this.backgroundEffect.width, this.backgroundEffect.height, true);
                    }

                    ScreenTransitionManager.a().a(var1);
                    return;
                case 23:
                    this.tutorialManager.update();
                    this.tutorialManager.renderPauseScreen(var1);
                    ScreenTransitionManager.a().a(var1);
            }

        }
    }

    public final void setDifficultyLevel(int var1) {
        this.difficultyLevel = (byte) var1;
    }

    public final void increaseDifficulty() {
        ++this.difficultyLevel;
        if (this.difficultyLevel > 3) {
            this.difficultyLevel = 3;
        }

    }

    public final void decreaseDifficulty() {
        --this.difficultyLevel;
        if (this.difficultyLevel < 0) {
            this.difficultyLevel = 0;
        }

    }
}
