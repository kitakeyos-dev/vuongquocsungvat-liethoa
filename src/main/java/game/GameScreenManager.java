package game;

import a.GameEngineBase;
import a.GameUtils;
import a.a.C30;
import a.a.C42;
import a.b.C64;
import a.b.C67;
import a.b.C68;
import c.DialogManager;

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
    private C33 tutorialManager = C33.B();
    public long gameStartTime = 0L;
    public long pauseStartTime = 0L;
    public long battleStartTime = 0L;
    public long storyStartTime = 0L;
    public long worldMapTime = 0L;
    public long currentTime = 0L;
    C42 backgroundEffect = null;
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
                    C30.a().C30_f472 = -1;
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
                    if (C25.B().C25_f286 != null) {
                        C25.B().C25_f286.I();
                    }
                    break;
                case 3:
                    loadingProgress = 0;
                    C30.a().c(0, 19);
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
                    C25.B().gameController.C9_f132 = true;
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
                    C30.a().c(0, 18);
                    C30.a().e();
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
        if (C25.B().C25_f342 != null) {
            C25.B().C25_f342.b();
        }

        if (this.currentState != 2) {
            this.changeState((byte) 2);
            this.showSoftKeys();
        }

    }

    private void resumeGame() {
        this.changeState(this.previousState);
        if (C25.B().C25_f342 != null) {
            C25.B().C25_f342.c();
        }

        this.showSoftKeys();
    }

    public boolean initializeGame() {
        this.startGameTimer();
        this.gameController = C9.a();
        this.dialogManager = DialogManager.getInstance();
        this.gameController.a(this);
        C67.a(50000);
        C64.a(1000);
        C67.a();
        setBackgroundColor(0);
        getDefaultFont();
        this.tutorialManager.initializeGame();
        Image var1;
        int[] var2 = GameUtils.extractImageRGB(var1 = GameUtils.loadImage("/data/img/", "img_22"));
        this.backgroundEffect = new C42();
        this.backgroundEffect.a(var2, var1.getWidth(), var1.getHeight());
        C25.B();
        C25.G();
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
        if (this.C8_f110) {
            this.A();
            switch (this.currentState) {
                case 2:
                    if (this.g(262144)) {
                        this.resumeGame();
                    }
                    break;
                case 3:
                    if (!isEngineStopped()) {
                        this.initializeGame();
                    }

                    if (C30.a().C30_f476 && isEngineStopped()) {
                        this.changeState((byte) 15);
                    }
                    break;
                case 4:
                    if (this.g(131072)) {
                        this.deactivate();
                    } else if (this.g(262144)) {
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
                    if (this.g(131072)) {
                        var2 = 1;
                        this.difficultyLevel = (byte) var2;
                        this.changeState((byte) 21);
                    } else if (this.g(262144)) {
                        var2 = 0;
                        this.difficultyLevel = (byte) var2;
                        this.changeState((byte) 21);
                    }
                    break;
                case 7:
                    this.cleanupCurrentScreen();
                    this.currentScreen = C13.B();
                    this.currentScreen.initializeGame();
                    this.a(this.currentScreen);
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
                    this.currentScreen = C25.B();
                    this.currentScreen.initializeGame();
                    this.a(this.currentScreen);
                    if (this.currentState == 9 || this.currentState == 22) {
                        this.changeState((byte) 11);
                    }

                    C53.C53_f798 = false;
                    break;
                case 10:
                    this.cleanupCurrentScreen();
                    this.currentScreen = C25.B();
                    ((C25) this.currentScreen).L();
                    this.a(this.currentScreen);
                    this.changeState((byte) 11);
                    break;
                case 12:
                    if (!isEngineStopped()) {
                        this.currentScreen = null;
                        this.currentScreen = C29.B();
                        this.currentScreen.initializeGame();
                        this.a(this.currentScreen);
                        if (((C29) this.currentScreen).C29_f398 == 0) {
                            C30.a().c(-2013265920, 6);
                        } else if (((C29) this.currentScreen).C29_f398 == 2) {
                            C30.a().c(-2013265920, 8);
                        } else if (((C29) this.currentScreen).C29_f398 == 1) {
                            C30.a().c(-2013265920, 7);
                        }
                    }

                    if (isEngineStopped()) {
                        C30.a().d();
                        C53.C53_f798 = false;
                    }

                    if (C30.a().C30_f476) {
                        ((C29) this.currentScreen).E();
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
                    if (this.currentScreen instanceof C25) {
                        var1 = 1;
                    } else {
                        var1 = 2;
                    }

                    this.currentScreen = C48.B();
                    this.currentScreen.initializeGame();
                    this.a(this.currentScreen);
                    this.currentScreen.changeState(var1);
                    this.changeState((byte) 20);
                    break;
                case 21:
                    C30.a().d();
                    if (C30.a().C30_f475 == -1 || this.g(65568) && C13.inputEnabled) {
                        C30.a().C30_f475 = -1;
                        C30.a().C30_f474 = -1;
                        C30.a().C30_f479 = 0;
                        C30.a().C30_f472 = -1;
                        this.currentImage = null;
                        this.backgroundEffect.C42_f671 = null;
                        this.backgroundEffect = null;
                        this.changeState((byte) 7);
                    }
                    break;
                case 23:
                    C30.a().d();
                    if (this.tutorialManager.C44_f698 == 1 && C30.a().C30_f477) {
                        this.tutorialManager.changeState((byte) 2);
                    } else if (this.tutorialManager.C44_f698 == 2) {
                        if (this.tutorialManager.C33_f547) {
                            this.cleanupCurrentScreen();
                            this.currentScreen = C25.B();
                            this.currentScreen.initializeGame();
                            this.a(this.currentScreen);
                            this.tutorialManager.changeState((byte) 3);
                        }
                    } else if (this.tutorialManager.C44_f698 == 3 && C30.a().C30_f477) {
                        C30.a().C30_f474 = -1;
                        this.tutorialManager.C();
                        this.changeState((byte) 11);
                    }
            }

            if (this.C44_f698 != 2) {
                if (C25.B().C25_f290 == 3 && C25.B().C25_f291 == 7 && this.battleStartTime == 0L && this.gameStartTime != 0L) {
                    this.pauseStartTime = System.currentTimeMillis();
                }

                this.worldMapTime = System.currentTimeMillis();
            }

        }
    }

    public final void renderPauseScreen(Graphics var1) {
        if (this.C8_f110) {
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
                    C30.a().a(var1);
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
                    if (C53.C53_f798) {
                        var1.setColor(0);
                        var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                        if (loadingProgress % 4 == 3) {
                            C53.p().a((byte) 1, (byte) -1, false);
                        } else {
                            C53.p().a((byte) (loadingProgress % 4), (byte) -1, false);
                        }

                        C53 var10000 = C53.p();
                        byte var4 = (byte) (loadingProgress % 4);
                        var10000.C60_f866 = var4;
                        C53.p().a(var1, C68.a().C68_f943, C68.a().C68_f944 - loadingProgress);
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

                    if (!C53.C53_f798) {
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
                    C25.B().C25_f285.a(var1);
                    if (isEngineStopped()) {
                        C30.a().a(var1);
                        C53.C53_f798 = false;
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
                        var1.drawRGB(this.backgroundEffect.C42_f671, 0, this.backgroundEffect.C42_f672, var2 * 10, 0, this.backgroundEffect.C42_f672, this.backgroundEffect.C42_f673, true);
                    }

                    C30.a().a(var1);
                    return;
                case 23:
                    this.tutorialManager.update();
                    this.tutorialManager.renderPauseScreen(var1);
                    C30.a().a(var1);
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
