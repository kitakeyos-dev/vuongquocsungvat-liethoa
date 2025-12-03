package game;

import engine.GameEngineBase;
import engine.GameUtils;
import layout.DialogManager;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class MainMenuScreen extends GameEngineBase {
    private static MainMenuScreen instance;
    private static int[] menuItems = null;
    private static int selectedMenuIndex;
    private byte menuConfirmState = 0;
    private byte textColorIndex = 0;
    private Image particleImage;
    int animationCounter = 0;
    private byte MAX_PARTICLES = 10;
    private int[][] particleData;
    private int[] particleSourceData;
    private byte maxHorizontalOffset;
    private byte maxVerticalOffset;
    private boolean particleAnimationComplete;
    private int animationTimer;
    private int[] particleCompletionFlags;
    private int[] textColors;

    public MainMenuScreen() {
        this.particleData = new int[this.MAX_PARTICLES][5];
        this.particleSourceData = new int[] { 28, 3, 21, 22, 50, 5, 17, 17 };
        this.maxHorizontalOffset = 30;
        this.maxVerticalOffset = 30;
        this.particleAnimationComplete = false;
        this.animationTimer = 0;
        this.particleCompletionFlags = new int[this.MAX_PARTICLES];
        this.textColors = new int[] { 3958719, 3958719, 3958719, 7248110, 7248110, 9943031 };
    }

    public static MainMenuScreen getInstance() {
        if (instance == null) {
            instance = new MainMenuScreen();
        }

        return instance;
    }

    public final boolean initializeGame() {
        this.gameController = DialogUIManager.a();
        this.dialogManager = DialogManager.getInstance();
        this.gameController.a(this);
        selectedMenuIndex = 0;
        if (inputEnabled) {
            menuItems = new int[] { 504, 503, 505, 506, 507, 508 };
        } else {
            menuItems = new int[] { 503, 505, 506, 507, 508 };
        }

        if (this.particleImage == null) {
            this.particleImage = GameUtils.loadImage("/data/img/", "img_833");
        }

        this.initializeParticleSystem();
        this.changeState((byte) 0);
        this.animationCounter = 0;
        if (WorldGameSession.getInstance().audioManager != null) {
            this.animationCounter = GameScreenManager.getInstance().difficultyLevel;
            GameScreenManager.getInstance().setDifficultyLevel(0);
            WorldGameSession.getInstance().audioManager.deallocateAudioResources((byte) 1);
            WorldGameSession.getInstance().audioManager.cleanup();
            WorldGameSession.getInstance().audioManager = null;
        }

        return true;
    }

    private void initializeParticleSystem() {
        for (int var1 = 0; var1 < this.MAX_PARTICLES; ++var1) {
            this.particleData[var1][0] = -GameUtils.getRandomInt(this.maxHorizontalOffset);
            this.particleData[var1][1] = getScreenHeight() + GameUtils.getRandomInt(this.maxVerticalOffset);
            this.particleData[var1][2] = GameUtils.getRandomInt(2);
            this.particleData[var1][3] = GameUtils.getRandomInRange(1, 5);
            this.particleData[var1][4] = GameUtils.getRandomInRange(3, 5);
        }

    }

    private void startNewGame() {
        WorldGameSession.isGameLoaded = false;
        WorldGameSession.hasPurchasedSms = false;
        WorldGameSession.tutorialStep = 0;
        QuestManager.isQuestActive = true;
        WorldGameSession.getInstance().currentRegionId = 0;
        WorldGameSession.getInstance().currentAreaId = 0;
        if (WorldGameSession.getInstance().questManager != null) {
            WorldGameSession.getInstance().questManager.resetManager();
        }

        if (WorldGameSession.getInstance().player != null) {
            WorldGameSession.getInstance().player.destroy();
        }

        if (this.gameController != null) {
            this.gameController.b();
        }

        this.menuConfirmState = 0;
        PlayerCharacter.getInstance().isInitialized = false;
        inputEnabled = false;
        GameScreenManager.getInstance().changeState((byte) 9);
        if (this.animationCounter > 0 && GameScreenManager.getInstance().difficultyLevel <= 0) {
            GameScreenManager.getInstance().setDifficultyLevel(this.animationCounter);
        }

    }

    public final void update() {
        if (this.isActive) {
            this.updateInputState();
            switch (this.currentState) {
                case 0:
                    if (this.menuConfirmState == 0 && this.isKeyPressed(16400)) {
                        if (--selectedMenuIndex < 0) {
                            selectedMenuIndex = menuItems.length - 1;
                        }
                    } else if (this.menuConfirmState == 0 && this.isKeyPressed(32832)) {
                        if (++selectedMenuIndex > menuItems.length - 1) {
                            selectedMenuIndex = 0;
                        }
                    } else if (this.isKeyPressed(196640)) {
                        if (inputEnabled) {
                            switch (selectedMenuIndex) {
                                case 0:
                                    if (this.menuConfirmState == 0) {
                                        GameUtils.a();
                                        this.removeMenuDialog();
                                        if (WorldGameSession.getInstance().questManager != null) {
                                            WorldGameSession.getInstance().questManager.resetManager();
                                        }

                                        if (WorldGameSession.getInstance().player != null) {
                                            WorldGameSession.getInstance().player.destroy();
                                        }

                                        if (this.gameController != null) {
                                            this.gameController.b();
                                        }

                                        this.menuConfirmState = 0;
                                        PlayerCharacter.getInstance().isInitialized = false;
                                        GameScreenManager.getInstance().changeState((byte) 9);
                                        GameScreenManager.getInstance().changeState((byte) 9);
                                        if (this.animationCounter > 0
                                                && GameScreenManager.getInstance().difficultyLevel <= 0) {
                                            GameScreenManager.getInstance().setDifficultyLevel(this.animationCounter);
                                        }
                                    } else if (this.menuConfirmState == 1) {
                                        this.menuConfirmState = 0;
                                        this.gameController.az();
                                    }
                                    break;
                                case 1:
                                    if (this.menuConfirmState == 0) {
                                        GameUtils.a();
                                        this.changeState((byte) 5);
                                    } else if (this.menuConfirmState == 1) {
                                        this.menuConfirmState = 0;
                                        this.gameController.az();
                                    }
                                    break;
                                case 2:
                                    this.changeState((byte) 1);
                                    break;
                                case 3:
                                    this.changeState((byte) 2);
                                    break;
                                case 4:
                                    this.changeState((byte) 3);
                                    break;
                                case 5:
                                    this.changeState((byte) 4);
                            }
                        } else {
                            switch (selectedMenuIndex) {
                                case 0:
                                    if (this.menuConfirmState == 0) {
                                        GameUtils.a();
                                        this.removeMenuDialog();
                                        this.startNewGame();
                                        GameScreenManager.getInstance().changeState((byte) 9);
                                    } else if (this.menuConfirmState == 1) {
                                        this.menuConfirmState = 0;
                                        this.gameController.az();
                                    }
                                    break;
                                case 1:
                                    this.changeState((byte) 1);
                                    break;
                                case 2:
                                    this.changeState((byte) 2);
                                    break;
                                case 3:
                                    this.changeState((byte) 3);
                                    break;
                                case 4:
                                    this.changeState((byte) 4);
                            }
                        }
                    }

                    if (this.gameController.f()) {
                        this.menuConfirmState = 0;
                    }

                    MainMenuScreen var1 = this;
                    if (this.particleAnimationComplete) {
                        ++this.animationTimer;
                        if (this.animationTimer >= 100) {
                            for (int var2 = 0; var2 < var1.particleCompletionFlags.length; ++var2) {
                                var1.particleCompletionFlags[var2] = 0;
                            }

                            var1.animationTimer = 0;
                            var1.initializeParticleSystem();
                            var1.particleAnimationComplete = false;
                        }
                    }
                    break;
                case 1:
                    this.gameController.t();
                    break;
                case 2:
                    this.gameController.p();
                    break;
                case 3:
                    this.gameController.r();
                    break;
                case 4:
                    if (this.isKeyPressed(131072)) {
                        GameScreenManager.getInstance().changeState((byte) 1);
                    } else if (this.isKeyPressed(262144)) {
                        this.changeState((byte) 0);
                    }
                    break;
                case 5:
                    if (this.isKeyPressed(131104)) {
                        WorldGameSession.getInstance();
                        WorldGameSession.deleteAllSaveData();
                        this.startNewGame();
                    } else if (this.isKeyPressed(262144)) {
                        this.changeState((byte) 0);
                        this.dialogManager.removeDialog("/data/ui/msgtip.ui");
                    }
            }

            this.dialogManager.closeCurrentDialog();
        }
    }

    public final void renderPauseScreen(Graphics graphics) {
        int var4;
        switch (this.currentState) {
            case 1:
            case 2:
            case 3:

                for (var4 = 0; var4 < getScreenHeight() / 20; ++var4) {
                    if (var4 % 2 == 0) {
                        graphics.setColor(10440998);
                    } else {
                        graphics.setColor(12082732);
                    }

                    graphics.fillRect(0, var4 * 20, getScreenWidth(), 20);
                }
            default:
                this.dialogManager.render(graphics);
                if (this.currentState == 0) {
                    String var10002 = getLocalizedText(menuItems[selectedMenuIndex]);
                    int var10003 = (getScreenWidth()
                            - getDefaultFont().stringWidth(getLocalizedText(menuItems[selectedMenuIndex]))) / 2 + 4;
                    int var10004 = getScreenHeight() - 20;
                    boolean var7 = true;
                    graphics.setColor(this.textColors[this.textColorIndex]);
                    graphics.drawString(var10002, var10003, var10004 - 1, 36);
                    graphics.drawString(var10002, var10003, var10004 + 1, 36);
                    graphics.drawString(var10002, var10003 - 1, var10004, 36);
                    graphics.drawString(var10002, var10003 + 1, var10004, 36);
                    graphics.setColor(16777215);
                    graphics.drawString(var10002, var10003, var10004, 36);
                    ++this.textColorIndex;
                    if (this.textColorIndex >= 6) {
                        this.textColorIndex = 0;
                    }

                    MainMenuScreen var8 = this;
                    if (!this.particleAnimationComplete) {
                        for (var4 = 0; var4 < var8.MAX_PARTICLES; ++var4) {
                            graphics.drawRegion(var8.particleImage,
                                    var8.particleSourceData[var8.particleData[var4][2] << 2],
                                    var8.particleSourceData[(var8.particleData[var4][2] << 2) + 1],
                                    var8.particleSourceData[(var8.particleData[var4][2] << 2) + 2],
                                    var8.particleSourceData[(var8.particleData[var4][2] << 2) + 3], 0,
                                    var8.particleData[var4][0], var8.particleData[var4][1], 20);
                            int[] var10000 = var8.particleData[var4];
                            var10000[0] += var8.particleData[var4][3];
                            var10000 = var8.particleData[var4];
                            var10000[1] -= var8.particleData[var4][4];
                            if (var8.particleData[var4][0] > getScreenWidth() || var8.particleData[var4][1] < 0) {
                                int var10 = var8.particleCompletionFlags[var4]++;
                            }
                        }

                        for (var4 = 0; var4 < var8.particleCompletionFlags.length
                                && var8.particleCompletionFlags[var4] > 0; ++var4) {
                        }

                        if (var4 >= var8.particleCompletionFlags.length) {
                            var8.particleAnimationComplete = true;
                        }
                    }

                } else {
                    if (this.currentState == 4) {
                        graphics.setColor(0);
                        graphics.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                        graphics.setColor(16777215);
                        graphics.drawString("Bạn có muốn thoát không?", getScreenCenterX(), getScreenCenterY() - 10,
                                17);
                        graphics.drawString("", 2, getScreenHeight(), 36);
                        graphics.drawString("Không", getScreenWidth() - 2, getScreenHeight(), 40);
                    }

                }
        }
    }

    public final void cleanupCurrentScreen() {
        this.particleImage = null;
        this.dialogManager.clearAllDialogs();
    }

    private void removeMenuDialog() {
        this.dialogManager.removeDialog("/data/ui/menu.ui");
    }

    public final void changeState(byte var1) {
        this.currentState = var1;
        switch (var1) {
            case 0:
                this.gameController.u();
                this.gameController.w();
                return;
            case 1:
                if (this.animationCounter > 0) {
                    GameScreenManager.getInstance().difficultyLevel = (byte) this.animationCounter;
                }

                this.gameController.s();
                this.removeMenuDialog();
                return;
            case 2:
                this.gameController.o();
                this.removeMenuDialog();
                return;
            case 3:
                this.gameController.q();
                this.removeMenuDialog();
                return;
            case 5:
                this.gameController.K();
                this.gameController.a("Có chắc chắn xóa dữ liệu cũ để chơi mới không?");
            case 4:
            default:
        }
    }
}
