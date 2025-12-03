package engine.graphics;

import engine.GameEngineBase;
import engine.GameUtils;
import engine.entity.CameraController;
import engine.entity.ResourceManager;
import game.BattleSystemManager;
import game.GameUIController;
import game.QuestManager;
import game.WorldGameSession;
import layout.DialogManager;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class ScreenTransitionManager {
    private static ScreenTransitionManager instance;
    public int primaryTransitionType = -1;
    private int backgroundTransitionType = -1;
    public int secondaryTransitionType = -1;
    public int tertiaryTransitionType = -1;
    public boolean isTransitionComplete;
    public boolean isSecondaryComplete;
    private int animationFrame;
    public int secondaryProgress;
    private int fadeProgress = 0;
    private int fadeSpeed = 20;
    public int alphaStep = 5;
    private int transitionColor = -2013265920;
    private int[] pixelBuffer1 = null;
    private int[] pixelBuffer2 = null;
    private int[] circleGradientBuffer = null;
    private int tileSize = 16;
    private int gradientWidth = 4;
    private int tileProgress = 0;
    private int[][] tileBuffers;
    private int[] tileProgresses;
    private int rowHeight = 20;
    private int wipeDirection;
    private static DialogManager dialogManager;
    private static GameUIController dialogUIManager;
    private static short[][] battleNpcData = null;
    private short[][] shakePatterns = new short[][]{{-20, 20, 20, -20, -15, 15, -15, 15, -5, 5, -5, 5}, {-5, 5, 5, -5}, {-5, 10, -5}};
    private int shakeAxis = 0;
    private int shakePatternIndex = 0;
    private int shakeLoopCount = 0;
    private int shakeCurrentFrame = 0;
    private int scrollMaxDistance = 0;
    private int scrollSpeed = 0;
    private int scrollParam1;
    private int scrollParam2;
    private int scrollParam3;
    private int flashDuration = 0;
    private int flashIntensity = 0;
    private Image overlayImage = null;
    private ImageData overlayImageData = null;
    private int overlayX;
    private int overlayY;
    private int circleRadius = 0;
    private int circleMode = 0;
    private int circleCenterX = 0;
    private int circleCenterY = 0;
    private int[] circleColors = new int[]{16777215, 9115396};
    private static int gridColumns = 0;
    private static int[] gridParams = new int[5];
    private EffectEntity battleEffect;
    private int battleScriptLength = 0;
    private int battleScriptIndex = 0;
    public byte spotlightMode = -1;
    private int spotlightX;
    private int spotlightY;
    private int viewportWidth;
    private int viewportHeight;
    private int spotlightRadiusX;
    private int spotlightRadiusY;
    private static final int[][] PARTICLE_SPEED_RANGES;
    private byte particleCount = 50;
    private Image[] particleImages;
    private Image particleBackground = null;
    private int[][] particleData;
    private int particleBoundX = 0;
    private int particleBoundY = 0;
    private byte particleDirection = 0;
    private boolean particleFlag1 = false;
    private boolean shouldRenderParticles = false;

    public static ScreenTransitionManager getInstance() {
        if (instance == null) {
            instance = new ScreenTransitionManager();
        }

        dialogManager = DialogManager.getInstance();
        dialogUIManager = GameUIController.getInstance();
        return instance;
    }

    public static void loadBattleNpcData() {
        battleNpcData = null;
        battleNpcData = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/battleNpc.mid"));
    }

    public static void clearBattleNpcData() {
        battleNpcData = null;
    }

    public final void renderTransition(Graphics var1) {
        if (this.primaryTransitionType != -1 || this.secondaryTransitionType != -1 || this.tertiaryTransitionType != -1) {
            if (this.tertiaryTransitionType == 18) {
                dialogManager.render(var1);
                if (this.animationFrame >= battleNpcData.length) {
                    this.animationFrame = 0;
                    this.tertiaryTransitionType = -1;
                    this.isTransitionComplete = true;
                    battleNpcData = null;
                    dialogManager.removeDialog("/data/ui/menu1.ui");
                    return;
                }
            }

            ScreenTransitionManager var2;
            int var4;
            int var5;
            int var7;
            int var8;
            boolean var10001;
            switch (this.primaryTransitionType) {
                case 0:
                case 1:
                case 2:
                    Graphics var16 = var1;
                    var2 = this;
                    var4 = this.transitionColor & 16777215;
                    boolean var20 = false;
                    var5 = 0;
                    if (this.primaryTransitionType == 0) {
                        var5 = this.transitionColor;
                    } else if (this.primaryTransitionType == 1) {
                        if (255 - this.fadeProgress * this.alphaStep < 0) {
                            --this.fadeProgress;
                            this.primaryTransitionType = -1;
                            var20 = true;
                        }

                        var5 = 255 - this.fadeProgress * this.alphaStep << 24;
                        var5 |= var4;
                    } else if (this.primaryTransitionType == 2) {
                        if (this.fadeProgress * this.alphaStep > 255) {
                            --this.fadeProgress;
                            var20 = true;
                        }

                        var5 = this.fadeProgress * this.alphaStep << 24;
                        var5 |= var4;
                    }

                    var4 = this.rowHeight;
                    var7 = GameEngineBase.getScreenHeight() / var4 + 1;
                    var8 = GameEngineBase.getScreenWidth() * var7;
                    if (this.pixelBuffer1 == null || this.pixelBuffer1.length != var8) {
                        this.pixelBuffer1 = new int[var8];
                    }

                    if (this.pixelBuffer1[0] != var5) {
                        for (var8 = 0; var8 < var2.pixelBuffer1.length; ++var8) {
                            var2.pixelBuffer1[var8] = var5;
                        }
                    }

                    for (var8 = 0; var8 < var4; ++var8) {
                        var16.drawRGB(var2.pixelBuffer1, 0, GameEngineBase.getScreenWidth(), 0, var8 * var7, GameEngineBase.getScreenWidth(), var7, true);
                    }

                    if (var20) {
                        var2.cleanupBuffers();
                        var10001 = true;
                    } else {
                        ++var2.fadeProgress;
                        var10001 = false;
                    }

                    this.isTransitionComplete = var10001;
                    break;
                case 3:
                    var2 = this;
                    short var18 = GameEngineBase.getScreenWidth();
                    short var19 = GameEngineBase.getScreenHeight();
                    int var6 = this.transitionColor & 16777215;
                    var7 = this.fadeSpeed;
                    var8 = 255 / var7 / 2;
                    int var9 = var18 / 2;
                    var5 = var19 / 2;
                    int var10;
                    int var21 = var10 = var9 * 200 / 120;
                    int var11 = var21 * var21;
                    boolean var12 = false;
                    if (this.circleGradientBuffer == null) {
                        this.circleGradientBuffer = new int[var10];
                    }

                    int var13 = var10 + var7 - (this.fadeProgress << 1);
                    if (this.fadeProgress <= 0) {
                        var12 = true;
                        var13 = var10 + var7 - (--this.fadeProgress << 1);
                    }

                    if (this.pixelBuffer1 == null || this.pixelBuffer1.length != var9 * var5) {
                        this.pixelBuffer1 = new int[var9 * var5];
                    }

                    if (this.pixelBuffer2 == null || this.pixelBuffer2.length != var9 * var5) {
                        this.pixelBuffer2 = new int[var9 * var5];
                    }

                    int var14;
                    for (var14 = 0; var14 < var2.circleGradientBuffer.length; ++var14) {
                        if ((var4 = var14 - var13) > var7) {
                            var4 = 255;
                        } else if (var4 < -var7) {
                            var4 = 0;
                        } else {
                            var4 = 127 + var4 * var8;
                        }

                        var2.circleGradientBuffer[var14] = var6 | var4 << 24;
                    }

                    var14 = var10 - 1;
                    var4 = var13 + var7;
                    if ((var6 = var13 - var7) < 0) {
                        var6 = 0;
                    }

                    var4 = var4 * var11 / var14;
                    var6 = var6 * var11 / var14;

                    for (var7 = 0; var7 < var5; ++var7) {
                        var10 = var7 * var7;
                        var13 = var7 * var9;

                        for (int var15 = 0; var15 < var9; ++var15) {
                            if ((var8 = var10 + var15 * var15) > var4) {
                                var2.pixelBuffer1[var13 + var15] = -16777216;
                            } else if (var8 < var6) {
                                var2.pixelBuffer1[var13 + var15] = 0;
                            } else {
                                var2.pixelBuffer1[var13 + var15] = var2.circleGradientBuffer[var14 * var8 / var11];
                            }
                        }
                    }

                    if (var2.pixelBuffer1 != null) {
                        var1.drawRGB(var2.pixelBuffer1, 0, var9, var9, var5, var9, var5, true);
                        var1.drawRGB(flipPixelBuffer(var2.pixelBuffer1, var2.pixelBuffer2, var9, var5, (byte) 2), 0, var9, 0, var5, var9, var5, true);
                        var1.drawRGB(flipPixelBuffer(var2.pixelBuffer1, var2.pixelBuffer2, var9, var5, (byte) 3), 0, var9, 0, 0, var9, var5, true);
                        var1.drawRGB(flipPixelBuffer(var2.pixelBuffer1, var2.pixelBuffer2, var9, var5, (byte) 1), 0, var9, var9, 0, var9, var5, true);
                    }

                    if (var12) {
                        var2.cleanupBuffers();
                        var10001 = true;
                    } else {
                        var2.fadeProgress -= 10;
                        var10001 = false;
                    }

                    this.isTransitionComplete = var10001;
                    break;
                case 4:
                    if (this.isTransitionComplete) {
                        this.primaryTransitionType = -1;
                        var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                    } else {
                        this.isTransitionComplete = this.renderTileTransition(var1);
                    }
                    break;
                case 5:
                    if (!this.isTransitionComplete) {
                        this.isTransitionComplete = this.renderTileTransition(var1);
                    }
                    break;
                case 6:
                    if (this.isTransitionComplete) {
                        this.primaryTransitionType = -1;
                        var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                    } else {
                        this.isTransitionComplete = this.renderWipeTransition(var1);
                    }
                    break;
                case 7:
                    if (this.animationFrame < GameEngineBase.getScreenHeight()) {
                        var1.drawRGB(this.pixelBuffer1, 0, GameEngineBase.getScreenWidth(), 0, 0, GameEngineBase.getScreenWidth(), this.animationFrame, true);
                        var1.drawRGB(this.pixelBuffer2, 0, GameEngineBase.getScreenWidth(), 0, GameEngineBase.getScreenHeight() - this.animationFrame, GameEngineBase.getScreenWidth(), this.animationFrame, true);
                        this.animationFrame += 20;
                    } else {
                        var1.drawRGB(this.pixelBuffer1, 0, GameEngineBase.getScreenWidth(), 0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight(), true);
                        var1.drawRGB(this.pixelBuffer2, 0, GameEngineBase.getScreenWidth(), 0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight(), true);
                        if (this.battleEffect == null) {
                            this.initBattleEffect((int) 0);
                        }
                    }

                    if (this.battleEffect != null) {
                        this.battleEffect.render(var1);
                    }
                    break;
                case 8:
                    if (this.animationFrame >= 5) {
                        var1.drawImage(BattleSystemManager.B().C29_f400, 0, 0, 20);
                    }

                    dialogManager.render(var1);
                    if (this.animationFrame >= battleNpcData.length) {
                        this.animationFrame = 0;
                        this.primaryTransitionType = -1;
                        this.isTransitionComplete = true;
                        dialogManager.removeDialog("/data/ui/npcEnemy.ui");
                        return;
                    }
                    break;
                case 9:
                    var1.setColor(this.transitionColor);
                    var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                    break;
                case 10:
                    if (this.animationFrame <= this.flashDuration) {
                        if (this.animationFrame % 3 / (this.flashIntensity + 1) == 0) {
                            var1.setColor(16777215);
                            var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                        } else if (this.animationFrame % 3 / (this.flashIntensity + 1) == 1) {
                            var1.setColor(0);
                            var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                        }

                        ++this.animationFrame;
                    }
                case 11:
                case 12:
                case 13:
                case 16:
                case 18:
                default:
                    break;
                case 14:
                case 15:
                    boolean var17 = false;
                    var5 = 0;
                    if (this.primaryTransitionType == 15) {
                        if ((var5 = this.fadeProgress) >= 255) {
                            var5 = 255;
                            this.fadeProgress = 255;
                            var17 = true;
                        }
                    } else if (this.primaryTransitionType == 14 && (var5 = 255 - this.fadeProgress) <= 0) {
                        var5 = 0;
                        this.primaryTransitionType = -1;
                        var17 = true;
                    }

                    if (this.overlayImageData != null) {
                        this.overlayImageData = ImageProcessor.applyAlpha(this.overlayImageData, var5);
                        var1.drawRGB(this.overlayImageData.pixels, 0, this.overlayImageData.width, this.overlayX - this.overlayImageData.width / 2, this.overlayY - this.overlayImageData.height / 2, this.overlayImageData.width, this.overlayImageData.height, true);
                    }

                    if (var17) {
                        this.primaryTransitionType = -1;
                        this.cleanupBuffers();
                        var10001 = true;
                    } else {
                        this.fadeProgress += this.alphaStep;
                        var10001 = false;
                    }

                    this.isTransitionComplete = var10001;
                    break;
                case 17:
                    var1.setColor(this.circleColors[this.transitionColor]);
                    var5 = this.circleRadius;
                    var4 = this.circleCenterY;
                    int var3 = this.circleCenterX;
                    var1.fillArc(var3 - var5, var4 - var5, var5 << 1, var5 << 1, 0, 360);
                    break;
                case 19:
                case 20:
                    this.isTransitionComplete = this.renderGridTransition(var1);
            }

            switch (this.secondaryTransitionType) {
                case 12:
                    var1.setColor(0);
                    var1.fillRect(0, 0, this.scrollParam1, this.scrollParam2 - this.secondaryProgress * this.scrollParam2 / this.scrollMaxDistance);
                    var1.fillRect(0, GameEngineBase.getScreenHeight() - this.scrollParam3 + this.secondaryProgress * this.scrollParam3 / this.scrollMaxDistance, this.scrollParam1, this.scrollParam3 - this.secondaryProgress * this.scrollParam3 / this.scrollMaxDistance);
                    return;
                case 13:
                    var1.setColor(0);
                    var1.fillRect(0, 0, this.scrollParam1, this.secondaryProgress * this.scrollParam2 / this.scrollMaxDistance);
                    var1.fillRect(0, GameEngineBase.getScreenHeight() - this.secondaryProgress * this.scrollParam3 / this.scrollMaxDistance, this.scrollParam1, this.secondaryProgress * this.scrollParam3 / this.scrollMaxDistance);
                default:
            }
        }
    }

    private void initBattleEffect(int var1) {
        this.battleScriptIndex = var1;
        switch (this.primaryTransitionType) {
            case 7:
                short var2 = ResourceManager.gameDatabase[0][BattleSystemManager.B().p(0)][17];
                short[] var3;
                switch (this.battleScriptIndex) {
                    case 0:
                        var3 = new short[]{8, 118, 160, var2, 0, 1, 0, 4, 0, 2, 1, 8, 0, -16, 10, 0, 0};
                        this.battleEffect = new EffectEntity();
                        this.battleEffect.initializeEffect(var3);
                        this.battleEffect.setInteractable(true);
                        this.battleEffect.activateEffect();
                        return;
                    case 1:
                        var3 = new short[]{17, 118, 160, var2, 0, 1, 100, 255, 255, 255, 12, 0, 1, 1, 9};
                        this.battleEffect.initializeEffect(var3);
                        this.battleEffect.activateEffect();
                        return;
                    case 2:
                        var3 = new short[]{17, 118, 160, var2, 0, 1, 255, 255, 255, 255, 15, 0, 1, 1, 13};
                        this.battleEffect.initializeEffect(var3);
                        this.battleEffect.activateEffect();
                        return;
                    case 3:
                        var3 = new short[]{9, 118, 160, var2, 0, 1, 160, 255, 255, 255, 0, 4, 1};
                        this.battleEffect.initializeEffect(var3);
                        this.battleEffect.activateEffect();
                    default:
                        return;
                }
            default:
                this.battleScriptLength = battleNpcData[this.animationFrame].length;
        }
    }

    public final void updateTransition() {
        if (this.primaryTransitionType != -1 || this.secondaryTransitionType != -1 || this.tertiaryTransitionType != -1) {
            switch (this.primaryTransitionType) {
                case 7:
                    if (this.battleEffect != null && !this.battleEffect.updateEffect()) {
                        ++this.battleScriptIndex;
                        if (this.battleScriptIndex >= 4) {
                            this.isTransitionComplete = true;
                            this.primaryTransitionType = -1;
                            this.animationFrame = 0;
                            this.battleEffect = null;
                            return;
                        }

                        this.initBattleEffect(this.battleScriptIndex);
                    }
                    break;
                case 8:
                    if (this.battleScriptIndex < this.battleScriptLength) {
                        dialogUIManager.updateBattleNPCScript(this.animationFrame, battleNpcData[this.animationFrame][this.battleScriptIndex]);
                        ++this.battleScriptIndex;
                    } else {
                        ++this.animationFrame;
                        if (this.animationFrame < battleNpcData.length) {
                            this.initBattleEffect((int) 0);
                        }
                    }

                    dialogManager.closeCurrentDialog();
                case 9:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                default:
                    break;
                case 10:
                    if (this.animationFrame > this.flashDuration) {
                        this.isTransitionComplete = true;
                        this.primaryTransitionType = -1;
                        this.animationFrame = 0;
                    }
                    break;
                case 11:
                    if (this.shakeAxis == 0) {
                        if (this.shakeCurrentFrame >= this.shakePatterns[this.shakePatternIndex].length * this.shakeLoopCount) {
                            this.primaryTransitionType = -1;
                            this.isTransitionComplete = true;
                            this.shakeCurrentFrame = 0;
                            CameraController.getInstance().cameraMode = 1;
                            return;
                        }

                        CameraController.getInstance().moveX(this.shakePatterns[this.shakePatternIndex][this.shakeCurrentFrame % this.shakePatterns[this.shakePatternIndex].length]);
                        ++this.shakeCurrentFrame;
                    } else {
                        if (this.shakeCurrentFrame >= this.shakePatterns[this.shakePatternIndex].length * this.shakeLoopCount) {
                            this.primaryTransitionType = -1;
                            this.isTransitionComplete = true;
                            this.shakeCurrentFrame = 0;
                            CameraController.getInstance().cameraMode = 1;
                            return;
                        }

                        CameraController.getInstance().moveY(this.shakePatterns[this.shakePatternIndex][this.shakeCurrentFrame % this.shakePatterns[this.shakePatternIndex].length]);
                        ++this.shakeCurrentFrame;
                    }
                    break;
                case 17:
                    ++this.animationFrame;
                    if (this.circleMode == 0) {
                        if ((GameEngineBase.getScreenWidth() - this.circleCenterX) * (GameEngineBase.getScreenWidth() - this.circleCenterX) + (GameEngineBase.getScreenHeight() - this.circleCenterY) * (GameEngineBase.getScreenHeight() - this.circleCenterY) < this.circleRadius * this.circleRadius) {
                            this.animationFrame = 0;
                            this.isTransitionComplete = true;
                        }

                        this.circleRadius += 10;
                    } else if (this.circleMode == 1) {
                        this.circleRadius -= 10;
                        if (this.circleRadius <= 0) {
                            this.animationFrame = 0;
                            this.primaryTransitionType = -1;
                            this.isTransitionComplete = true;
                        }
                    } else if (this.animationFrame <= 10) {
                        this.circleRadius += 10;
                    } else if (this.animationFrame > 10 && this.animationFrame <= 20) {
                        this.circleRadius -= 10;
                    } else {
                        this.animationFrame = 0;
                        this.isTransitionComplete = true;
                        this.primaryTransitionType = -1;
                    }
            }

            switch (this.secondaryTransitionType) {
                case 12:
                    this.secondaryProgress += this.scrollSpeed;
                    if (this.secondaryProgress > this.scrollMaxDistance) {
                        this.secondaryProgress = 0;
                        this.isSecondaryComplete = true;
                        this.secondaryTransitionType = -1;
                    }
                    break;
                case 13:
                    this.secondaryProgress += this.scrollSpeed;
                    if (this.secondaryProgress > this.scrollMaxDistance) {
                        this.secondaryProgress = this.scrollMaxDistance;
                        this.isSecondaryComplete = true;
                    }
            }

            switch (this.tertiaryTransitionType) {
                case 18:
                    if (this.battleScriptIndex < this.battleScriptLength) {
                        dialogUIManager.updateBattleUIElement((int) this.animationFrame, (int) battleNpcData[this.animationFrame][this.battleScriptIndex]);
                        ++this.battleScriptIndex;
                    } else {
                        ++this.animationFrame;
                        if (this.animationFrame < battleNpcData.length) {
                            this.initBattleEffect((int) 0);
                        }
                    }

                    dialogManager.closeCurrentDialog();
                default:
            }
        }
    }

    private boolean renderWipeTransition(Graphics var1) {
        if (this.animationFrame < 10) {
            if (this.animationFrame % 3 == 1) {
                var1.setColor(16777215);
                var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
            } else {
                WorldGameSession.getInstance();
                WorldGameSession.drawBackground(var1);
                WorldGameSession.getInstance().worldRenderer.renderWorld(var1);
            }

            ++this.animationFrame;
        } else {
            if (this.animationFrame >= GameEngineBase.getScreenWidth()) {
                var1.setColor(0);
                var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                return true;
            }

            boolean var2;
            int var3;
            switch (this.wipeDirection) {
                case 0:
                    var1.setColor(0);
                    var1.fillRect(0, 0, this.animationFrame, GameEngineBase.getScreenCenterY());

                    int var4;
                    for (var4 = 1; var4 < 6; ++var4) {
                        var1.fillRect(this.animationFrame + var4 * 15, 0, 15 - var4 * 3, GameEngineBase.getScreenCenterY());
                    }

                    var1.fillRect(GameEngineBase.getScreenWidth() - this.animationFrame, GameEngineBase.getScreenCenterY(), this.animationFrame, GameEngineBase.getScreenCenterY());

                    for (var4 = 1; var4 < 6; ++var4) {
                        var1.fillRect(GameEngineBase.getScreenWidth() - this.animationFrame - var4 * 15, GameEngineBase.getScreenCenterY(), 15 - var4 * 3, GameEngineBase.getScreenCenterY());
                    }

                    this.animationFrame += 15;
                    break;
                case 1:
                    var1.setColor(0);
                    var2 = false;

                    for (var3 = 0; var3 < GameEngineBase.getScreenHeight(); var3 += 10) {
                        if (var2) {
                            var1.fillRect(0, var3, this.animationFrame, 10);
                            var2 = false;
                        } else {
                            var1.fillRect(GameEngineBase.getScreenWidth() - this.animationFrame, var3, this.animationFrame, 10);
                            var2 = true;
                        }
                    }

                    this.animationFrame += 15;
                    break;
                case 2:
                    var1.setColor(0);
                    var2 = false;

                    for (var3 = 0; var3 < GameEngineBase.getScreenWidth(); var3 += 10) {
                        if (var2) {
                            var1.fillRect(var3, 0, 10, this.animationFrame);
                            var2 = false;
                        } else {
                            var1.fillRect(var3, GameEngineBase.getScreenHeight() - this.animationFrame, 10, this.animationFrame);
                            var2 = true;
                        }
                    }

                    this.animationFrame += 15;
            }
        }

        return false;
    }

    private boolean renderTileTransition(Graphics var1) {
        int var2 = this.transitionColor & 16777215;
        int var3 = 0;
        int var4 = this.gradientWidth;
        int var5 = 255 / ((var4 << 1) + 1);
        int var6;
        int var7;
        int var10000 = var7 = (var6 = this.tileSize / 2) * 200 / 120;
        int var8 = var10000 * var10000;
        int var10 = this.tileProgress / this.tileSize + 1;
        int var11 = GameEngineBase.getScreenWidth() / this.tileSize;
        int var12 = GameEngineBase.getScreenHeight() / this.tileSize;
        int var13 = var11 / 2;
        int var14 = var12 / 2;
        int[] var15 = new int[var7];
        if (this.tileBuffers == null) {
            this.tileBuffers = new int[GameUtils.fastSqrt(var13 * var13 + var14 * var14, 0)][];
        }

        if (this.tileProgresses == null) {
            this.tileProgresses = new int[this.tileBuffers.length];
        }

        if (var10 > this.tileBuffers.length) {
            var10 = this.tileBuffers.length;
        }

        int var9;
        int var17;
        int var18;
        int var20;
        for (int var16 = 0; var16 < var10; ++var16) {
            if (this.tileBuffers[var16] == null) {
                this.tileBuffers[var16] = new int[this.tileSize * this.tileSize];
            }

            if (this.tileProgresses[var16] < var7 + var4) {
                var9 = -var4 + this.tileProgresses[var16];

                for (var17 = 0; var17 < var15.length; ++var17) {
                    if ((var18 = var17 - var9) > var4) {
                        var3 = this.primaryTransitionType == 4 ? 0 : 255;
                    } else if (var18 < -var4) {
                        var3 = this.primaryTransitionType == 4 ? 255 : 0;
                    } else if (this.primaryTransitionType == 4) {
                        var3 = 127 - var18 * var5;
                    } else if (this.primaryTransitionType == 5) {
                        var3 = 127 + var18 * var5;
                    }

                    var15[var17] = var2 | var3 << 24;
                }

                var17 = var7 - 1;
                var18 = var9 + var4;
                if ((var9 -= var4) < 0) {
                    var9 = 0;
                }

                int var19 = var18 * var8 / var17;
                var20 = var9 * var8 / var17;

                for (var9 = 0; var9 < this.tileSize; ++var9) {
                    var18 = (var9 - var6) * (var9 - var6);
                    int var21 = var9 * this.tileSize;

                    for (int var23 = 0; var23 < this.tileSize; ++var23) {
                        int var22 = GameUtils.fastSqrt(var17 = var18 + (var23 - var6) * (var23 - var6), 1);
                        if (var17 > var19) {
                            this.tileBuffers[var16][var21 + var23] = this.primaryTransitionType == 4 ? 0 : -16777216;
                        } else if (var17 < var20) {
                            this.tileBuffers[var16][var21 + var23] = this.primaryTransitionType == 4 ? -16777216 : 0;
                        } else {
                            this.tileBuffers[var16][var21 + var23] = var15[var22];
                        }
                    }
                }

                int var10002 = this.tileProgresses[var16]++;
            }
        }

        boolean var24 = true;
        var1.setColor(var2);

        for (var17 = 0; var17 < var12; ++var17) {
            var18 = (var17 - var14) * (var17 - var14);

            for (var20 = 0; var20 < var11; ++var20) {
                var9 = GameUtils.fastSqrt(var18 + (var20 - var13) * (var20 - var13), 1);
                if (this.tileBuffers[var9] == null) {
                    if (this.primaryTransitionType == 5) {
                        var1.fillRect(var20 * this.tileSize, var17 * this.tileSize, this.tileSize, this.tileSize);
                    }
                } else if (this.tileProgresses[var9] >= var7 + var4) {
                    if (this.primaryTransitionType == 4) {
                        var1.fillRect(var20 * this.tileSize, var17 * this.tileSize, this.tileSize, this.tileSize);
                    }
                } else {
                    var24 = false;
                    var1.drawRGB(this.tileBuffers[var9], 0, this.tileSize, var20 * this.tileSize, var17 * this.tileSize, this.tileSize, this.tileSize, true);
                }
            }
        }

        if (var24) {
            this.cleanupBuffers();
            return true;
        } else {
            this.tileProgress += 15;
            return false;
        }
    }

    private static int[] flipPixelBuffer(int[] var0, int[] var1, int var2, int var3, byte var4) {
        int var5;
        int var6;
        int var7;
        int var8;
        int var9;
        if (var4 == 5) {
            for (var9 = 0; var9 < var3; ++var9) {
                var5 = var9 * var2;
                var6 = -1 - var9;
                var7 = 0;

                for (var8 = 1; var7 < var2; ++var8) {
                    var1[var8 * var3 + var6] = var0[var5 + var7];
                    ++var7;
                }
            }
        } else if (var4 == 3) {
            var9 = var2 * var3 - 1;

            for (var5 = 0; var5 < var3; ++var5) {
                var6 = var5 * var2;
                var7 = var9 - var5 * var2;

                for (var8 = 0; var8 < var2; ++var8) {
                    var1[var7 - var8] = var0[var6 + var8];
                }
            }
        } else if (var4 == 6) {
            var9 = var2 - 1;

            for (var5 = 0; var5 < var3; ++var5) {
                var6 = var9 * var3 + var5;
                var7 = var5 * var2;

                for (var8 = 0; var8 < var2; ++var8) {
                    var1[var6 - var8 * var3] = var0[var7 + var8];
                }
            }
        } else if (var4 != 0 && var4 != 7) {
            if (var4 == 1) {
                var9 = var3 - 1;

                for (var5 = 0; var5 < var3; ++var5) {
                    var6 = var5 * var2;
                    var7 = (var9 - var5) * var2;

                    for (var8 = 0; var8 < var2; ++var8) {
                        var1[var7 + var8] = var0[var6 + var8];
                    }
                }
            } else if (var4 != 4 && var4 == 2) {
                var9 = var2 - 1;

                for (var5 = 0; var5 < var3; ++var5) {
                    var7 = (var6 = var5 * var2) + var9;

                    for (var8 = 0; var8 < var2; ++var8) {
                        var1[var7 - var8] = var0[var6 + var8];
                    }
                }
            }
        }

        return var1;
    }

    public final void disableSpotlight(byte var1) {
        this.spotlightMode = var1;
        this.overlayImageData = null;
    }

    public final void initSpotlight(byte var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        this.spotlightMode = var1;
        this.setSpotlightPosition(var2, var3);
        this.viewportWidth = var4;
        this.viewportHeight = var5;
        this.spotlightRadiusX = var6;
        this.spotlightRadiusY = var7;
    }

    public final void setSpotlightRadius(int var1, int var2) {
        this.spotlightRadiusX = var1;
        this.spotlightRadiusY = var2;
    }

    public final void setSpotlightPosition(int var1, int var2) {
        this.spotlightX = var1;
        this.spotlightY = var2;
    }

    public final void renderSpotlight(Graphics var1, int var2, int var3) {
        var1.setColor(0);
        var1.fillRect(var2, var3, this.viewportWidth, this.spotlightY - this.spotlightRadiusY);
        var1.fillRect(var2, this.spotlightY - this.spotlightRadiusY, this.spotlightX - this.spotlightRadiusX, this.spotlightRadiusY << 1);
        var1.fillRect(var2, this.spotlightY + this.spotlightRadiusY, this.viewportWidth, this.viewportHeight - (this.spotlightY + this.spotlightRadiusY));
        var1.fillRect(this.spotlightX + this.spotlightRadiusX, this.spotlightY - this.spotlightRadiusY, this.viewportWidth - (this.spotlightX + this.spotlightRadiusX), this.spotlightRadiusY << 1);
    }

    public final void startTransition(int var1, int var2) {
        this.fadeProgress = 0;
        this.transitionColor = var1;
        if (var2 != 12 && var2 != 13) {
            if (var2 == 18) {
                this.tertiaryTransitionType = var2;
            } else {
                this.primaryTransitionType = var2;
            }
        } else {
            this.secondaryTransitionType = var2;
            this.isSecondaryComplete = false;
        }

        this.isTransitionComplete = false;
        switch (this.primaryTransitionType) {
            case 1:
            case 2:
                this.alphaStep = 17;
                return;
            case 3:
                this.fadeProgress = GameEngineBase.getScreenCenterX();
                this.fadeSpeed = 20;
                return;
            case 4:
            case 5:
                this.tileProgress = 0;
                return;
            case 6:
                this.animationFrame = 0;
                this.wipeDirection = GameUtils.getRandomInt(2);
                return;
            case 7:
                this.animationFrame = 0;
                this.pixelBuffer1 = null;
                this.pixelBuffer2 = null;
                this.pixelBuffer1 = new int[GameEngineBase.getScreenWidth() * GameEngineBase.getScreenHeight()];
                this.pixelBuffer2 = new int[this.pixelBuffer1.length];

                for (var1 = 0; var1 < this.pixelBuffer1.length; ++var1) {
                    int[] var10000;
                    if (var1 % GameEngineBase.getScreenWidth() / 10 % 2 == 0) {
                        this.pixelBuffer1[var1] = -2013265920 | this.pixelBuffer1[var1] & 16777215;
                        var10000 = this.pixelBuffer2;
                        var10000[var1] &= 16777215;
                    } else {
                        this.pixelBuffer2[var1] = -2013265920 | this.pixelBuffer2[var1] & 16777215;
                        var10000 = this.pixelBuffer1;
                        var10000[var1] &= 16777215;
                    }
                }

                return;
            case 8:
                this.animationFrame = 0;
                dialogUIManager.aw();
                this.initBattleEffect((int) 0);
                return;
            case 10:
            case 17:
                this.animationFrame = 0;
                return;
            case 19:
                this.initGridParams(-1);
                return;
            case 20:
                this.initGridParams(1);
            case 9:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 18:
            default:
        }
    }

    public final void initShakeEffect(int var1, int var2, int var3) {
        CameraController.getInstance().cameraMode = 3;
        this.shakeAxis = var1;
        this.shakePatternIndex = var2;
        this.shakeLoopCount = var3;
    }

    public final void initScrollEffect(int var1, int var2, int var3, int var4, int var5) {
        this.secondaryProgress = 0;
        this.scrollMaxDistance = var1;
        this.scrollSpeed = var2;
        this.scrollParam1 = var3;
        this.scrollParam2 = var4;
        this.scrollParam3 = var5;
    }

    public final void initFlashEffect(int var1, int var2) {
        this.flashDuration = var1;
        this.flashIntensity = var2;
    }

    public final void initCircleTransition(int var1, int var2, int var3, int var4) {
        this.circleMode = var1;
        this.circleCenterX = var2;
        this.circleCenterY = var3;
        this.circleRadius = var4;
    }

    public final void initImageOverlay(String var1, int var2, int var3, int var4) {
        if (QuestManager.questState == 1) {
            this.overlayImage = GameUtils.loadImage("/data/tex/", var1);
            this.overlayImage = ImageProcessor.convertToGrayscale(this.overlayImage);
        } else {
            this.overlayImage = GameUtils.loadImage("/data/tex/", var1);
        }

        this.overlayImageData = new ImageData();
        this.overlayImageData = ImageProcessor.convertImageWithColorCorrection(this.overlayImage, this.overlayImageData);
        this.overlayX = var2;
        this.overlayY = var3;
        this.alphaStep = var4;
    }

    public final void initMenuTransition() {
        this.animationFrame = 0;
        battleNpcData = new short[][]{{7}, {8}, {10}, {12}, {16}, {18}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {200, 200, 100, 100, 50, 50, 0, 0}, {38, 38, 39, 39, 40, 40, 41, 41, 42, 42}, {43, 43, 44, 44, 45, 45, 46, 46, 47, 47, 48}};
        dialogUIManager.showBattleMenu2();
        this.initBattleEffect(0);
    }

    private void cleanupBuffers() {
        this.pixelBuffer1 = null;
        this.pixelBuffer2 = null;
        this.circleGradientBuffer = null;
        this.tileBuffers = null;
        this.tileProgresses = null;
        this.overlayImage = null;
    }

    public final void initParticleEffect(int var1, int var2, byte var3, byte var4, Image var5, String[] var6) {
        this.backgroundTransitionType = var1;
        if (this.primaryTransitionType != 17 || this.circleMode != 0) {
            this.primaryTransitionType = -1;
            if (this.backgroundTransitionType >= 16) {
                this.particleCount = var3;
                this.particleBoundX = var1;
                this.particleBoundY = var2;
                this.particleDirection = var4;
                this.particleBackground = var5;
                this.particleImages = null;
                this.particleImages = new Image[var6.length];

                for (var1 = 0; var1 < var6.length; ++var1) {
                    this.particleImages[var1] = GameUtils.loadImage("/data/tex/", var6[var1]);
                }

                this.particleData = new int[var3][5];

                for (var1 = 0; var1 < var3; ++var1) {
                    this.resetParticle(var1);
                }

            }
        }
    }

    private void resetParticle(int var1) {
        int var2;
        if ((var2 = GameUtils.getRandomInt(100)) < 3) {
            this.particleData[var1][0] = this.particleImages.length - 1;
        } else if (var2 < 15) {
            this.particleData[var1][0] = this.particleImages.length - 2;
        } else if (var2 < 50) {
            this.particleData[var1][0] = this.particleImages.length - 3;
        } else {
            this.particleData[var1][0] = 0;
        }

        this.particleData[var1][1] = GameUtils.getRandomInt(GameEngineBase.getScreenWidth());
        this.particleData[var1][2] = GameUtils.getRandomInt(GameEngineBase.getScreenHeight());
        this.particleData[var1][3] = GameUtils.getRandomInt(PARTICLE_SPEED_RANGES[this.particleData[var1][0]][1] - PARTICLE_SPEED_RANGES[this.particleData[var1][0]][0]) + PARTICLE_SPEED_RANGES[this.particleData[var1][0]][0];
        this.particleData[var1][4] = GameUtils.getRandomOffset(2);
    }

    public final void renderParticleBackground(Graphics var1) {
        if (this.backgroundTransitionType >= 16) {
            if (this.particleBackground == null) {
                var1.setColor(0);
                var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
            } else {
                int var2 = this.particleBackground.getWidth();

                for (int var3 = 0; var3 < GameEngineBase.getScreenWidth() / var2; ++var3) {
                    var1.drawImage(this.particleBackground, var3 * var2, 0, 20);
                }
            }

            this.shouldRenderParticles = false;
        }
    }

    public final void renderParticles(Graphics var1) {
        if (this.backgroundTransitionType >= 16 || this.shouldRenderParticles) {
            for (int var2 = 0; var2 < this.particleCount; ++var2) {
                if (this.particleData[var2][1] < GameEngineBase.getScreenWidth() && this.particleData[var2][2] < GameEngineBase.getScreenHeight()) {
                    var1.drawImage(this.particleImages[this.particleData[var2][0]], this.particleData[var2][1], this.particleData[var2][2], 20);
                }

                int[] var10000;
                switch (this.particleDirection) {
                    case 0:
                        var10000 = this.particleData[var2];
                        var10000[2] -= this.particleData[var2][3];
                        break;
                    case 1:
                        var10000 = this.particleData[var2];
                        var10000[1] += this.particleData[var2][3];
                        var10000 = this.particleData[var2];
                        var10000[2] -= this.particleData[var2][3];
                        break;
                    case 2:
                        var10000 = this.particleData[var2];
                        var10000[1] += this.particleData[var2][3];
                        break;
                    case 3:
                        var10000 = this.particleData[var2];
                        var10000[1] += this.particleData[var2][3];
                        var10000 = this.particleData[var2];
                        var10000[2] += this.particleData[var2][3];
                        break;
                    case 4:
                        var10000 = this.particleData[var2];
                        var10000[2] += this.particleData[var2][3];
                        break;
                    case 5:
                        var10000 = this.particleData[var2];
                        var10000[1] -= this.particleData[var2][3];
                        var10000 = this.particleData[var2];
                        var10000[2] += this.particleData[var2][3];
                        break;
                    case 6:
                        var10000 = this.particleData[var2];
                        var10000[1] -= this.particleData[var2][3];
                        break;
                    case 7:
                        var10000 = this.particleData[var2];
                        var10000[1] -= this.particleData[var2][3];
                        var10000 = this.particleData[var2];
                        var10000[2] -= this.particleData[var2][3];
                }

                if (this.particleData[var2][1] < this.particleBoundX - this.particleImages[this.particleData[var2][0]].getWidth() || this.particleData[var2][2] < this.particleBoundY - this.particleImages[this.particleData[var2][0]].getHeight()) {
                    this.resetParticle(var2);
                }
            }

        }
    }

    private void initGridParams(int var1) {
        gridParams[0] = 20;
        gridColumns = GameEngineBase.getScreenWidth() / gridParams[0];
        gridParams[1] = (GameEngineBase.getScreenHeight() - 1) / gridParams[0] + 1;
        gridParams[2] = var1;
        gridParams[3] = GameUtils.getRandomInRange(0, 7);
        gridParams[4] = 0;
        this.isTransitionComplete = false;
    }

    private boolean renderGridTransition(Graphics var1) {
        var1.setColor(this.transitionColor);
        int var2 = 0;

        for (int var3 = 0; var3 < gridColumns; ++var3) {
            for (int var4 = 0; var4 < gridParams[1]; ++var4) {
                switch (gridParams[2]) {
                    case -1:
                        var2 = 0;
                        break;
                    case 1:
                        var2 = gridParams[0];
                }

                switch (gridParams[3]) {
                    case 0:
                        var2 = (var2 += (var4 - gridParams[4]) * gridParams[2]) < 0 ? 0 : (var2 > gridParams[0] ? gridParams[0] : var2);
                        break;
                    case 1:
                        var2 = (var2 += (gridParams[1] - var4 - gridParams[4]) * gridParams[2]) < 0 ? 0 : (var2 > gridParams[0] ? gridParams[0] : var2);
                        break;
                    case 2:
                        var2 = (var2 += (var3 - gridParams[4]) * gridParams[2]) < 0 ? 0 : (var2 > gridParams[0] ? gridParams[0] : var2);
                        break;
                    case 3:
                        var2 = (var2 += (8 - var3 - gridParams[4]) * gridParams[2]) < 0 ? 0 : (var2 > gridParams[0] ? gridParams[0] : var2);
                        break;
                    case 4:
                        var2 = (var2 += ((var3 + var4 >> 1) - gridParams[4]) * gridParams[2]) < 0 ? 0 : (var2 > gridParams[0] ? gridParams[0] : var2);
                        break;
                    case 5:
                        var2 = (var2 += ((8 - var3 + var4 >> 1) - gridParams[4]) * gridParams[2]) < 0 ? 0 : (var2 > gridParams[0] ? gridParams[0] : var2);
                        break;
                    case 6:
                        var2 = (var2 += ((var3 + gridParams[1] - var4 >> 1) - gridParams[4]) * gridParams[2]) < 0 ? 0 : (var2 > gridParams[0] ? gridParams[0] : var2);
                        break;
                    case 7:
                        var2 = (var2 += ((8 - var3 + gridParams[1] - var4 >> 1) - gridParams[4]) * gridParams[2]) < 0 ? 0 : (var2 > gridParams[0] ? gridParams[0] : var2);
                }

                var1.fillRect(gridParams[0] * var3 + (gridParams[0] - var2 >> 1), gridParams[0] * var4 + (gridParams[0] - var2 >> 1), var2, var2);
            }
        }

        int[] var10000 = gridParams;
        var10000[4] += 2;
        if (gridParams[4] > 40) {
            if (this.primaryTransitionType == 20) {
                this.primaryTransitionType = -1;
            }

            if (this.primaryTransitionType == 19) {
                var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
            }

            return true;
        } else {
            return false;
        }
    }

    static {
        byte[] var10000 = new byte[]{0, 5, 3, 6, 2, 7, 1, 4};
        PARTICLE_SPEED_RANGES = new int[][]{{1, 3}, {1, 4}, {2, 5}, {2, 6}};
    }
}
