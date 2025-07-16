package a.a;

import a.GameUtils;
import a.GameEngineBase;
import a.b.CameraController;
import a.b.ResourceManager;
import layout.DialogManager;
import game.C25;
import game.C29;
import game.C7;
import game.C9;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class C30 {
    private static C30 C30_f471;
    public int C30_f472 = -1;
    private int C30_f473 = -1;
    public int C30_f474 = -1;
    public int C30_f475 = -1;
    public boolean C30_f476;
    public boolean C30_f477;
    private int C30_f478;
    public int C30_f479;
    private int C30_f480 = 0;
    private int C30_f481 = 20;
    public int C30_f482 = 5;
    private int C30_f483 = -2013265920;
    private int[] C30_f484 = null;
    private int[] C30_f485 = null;
    private int[] C30_f486 = null;
    private int C30_f487 = 16;
    private int C30_f488 = 4;
    private int C30_f489 = 0;
    private int[][] C30_f490;
    private int[] C30_f491;
    private int C30_f492 = 20;
    private int C30_f493;
    private static DialogManager C30_f494;
    private static C9 C30_f495;
    private static short[][] C30_f496 = null;
    private short[][] C30_f497 = new short[][]{{-20, 20, 20, -20, -15, 15, -15, 15, -5, 5, -5, 5}, {-5, 5, 5, -5}, {-5, 10, -5}};
    private int C30_f498 = 0;
    private int C30_f499 = 0;
    private int C30_f500 = 0;
    private int C30_f501 = 0;
    private int C30_f502 = 0;
    private int C30_f503 = 0;
    private int C30_f504;
    private int C30_f505;
    private int C30_f506;
    private int C30_f507 = 0;
    private int C30_f508 = 0;
    private Image C30_f509 = null;
    private ImageData C30_f510 = null;
    private int C30_f511;
    private int C30_f512;
    private int C30_f513 = 0;
    private int C30_f514 = 0;
    private int C30_f515 = 0;
    private int C30_f516 = 0;
    private int[] C30_f517 = new int[]{16777215, 9115396};
    private static int C30_f518 = 0;
    private static int[] C30_f519 = new int[5];
    private C21 C30_f520;
    private int C30_f521 = 0;
    private int C30_f522 = 0;
    public byte C30_f523 = -1;
    private int C30_f524;
    private int C30_f525;
    private int C30_f526;
    private int C30_f527;
    private int C30_f528;
    private int C30_f529;
    private static final int[][] C30_f530;
    private byte C30_f531 = 50;
    private Image[] C30_f532;
    private Image C30_f533 = null;
    private int[][] C30_f534;
    private int C30_f535 = 0;
    private int C30_f536 = 0;
    private byte C30_f537 = 0;
    private boolean C30_f538 = false;
    private boolean C30_f539 = false;

    public static C30 a() {
        if (C30_f471 == null) {
            C30_f471 = new C30();
        }

        C30_f494 = DialogManager.getInstance();
        C30_f495 = C9.a();
        return C30_f471;
    }

    public static void b() {
        C30_f496 = null;
        C30_f496 = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/battleNpc.mid"));
    }

    public static void c() {
        C30_f496 = null;
    }

    public final void a(Graphics var1) {
        if (this.C30_f472 != -1 || this.C30_f474 != -1 || this.C30_f475 != -1) {
            if (this.C30_f475 == 18) {
                C30_f494.render(var1);
                if (this.C30_f478 >= C30_f496.length) {
                    this.C30_f478 = 0;
                    this.C30_f475 = -1;
                    this.C30_f476 = true;
                    C30_f496 = null;
                    C30_f494.removeDialog("/data/ui/menu1.ui");
                    return;
                }
            }

            C30 var2;
            int var4;
            int var5;
            int var7;
            int var8;
            boolean var10001;
            switch (this.C30_f472) {
                case 0:
                case 1:
                case 2:
                    Graphics var16 = var1;
                    var2 = this;
                    var4 = this.C30_f483 & 16777215;
                    boolean var20 = false;
                    var5 = 0;
                    if (this.C30_f472 == 0) {
                        var5 = this.C30_f483;
                    } else if (this.C30_f472 == 1) {
                        if (255 - this.C30_f480 * this.C30_f482 < 0) {
                            --this.C30_f480;
                            this.C30_f472 = -1;
                            var20 = true;
                        }

                        var5 = 255 - this.C30_f480 * this.C30_f482 << 24;
                        var5 |= var4;
                    } else if (this.C30_f472 == 2) {
                        if (this.C30_f480 * this.C30_f482 > 255) {
                            --this.C30_f480;
                            var20 = true;
                        }

                        var5 = this.C30_f480 * this.C30_f482 << 24;
                        var5 |= var4;
                    }

                    var4 = this.C30_f492;
                    var7 = GameEngineBase.getScreenHeight() / var4 + 1;
                    var8 = GameEngineBase.getScreenWidth() * var7;
                    if (this.C30_f484 == null || this.C30_f484.length != var8) {
                        this.C30_f484 = new int[var8];
                    }

                    if (this.C30_f484[0] != var5) {
                        for (var8 = 0; var8 < var2.C30_f484.length; ++var8) {
                            var2.C30_f484[var8] = var5;
                        }
                    }

                    for (var8 = 0; var8 < var4; ++var8) {
                        var16.drawRGB(var2.C30_f484, 0, GameEngineBase.getScreenWidth(), 0, var8 * var7, GameEngineBase.getScreenWidth(), var7, true);
                    }

                    if (var20) {
                        var2.f();
                        var10001 = true;
                    } else {
                        ++var2.C30_f480;
                        var10001 = false;
                    }

                    this.C30_f476 = var10001;
                    break;
                case 3:
                    var2 = this;
                    short var18 = GameEngineBase.getScreenWidth();
                    short var19 = GameEngineBase.getScreenHeight();
                    int var6 = this.C30_f483 & 16777215;
                    var7 = this.C30_f481;
                    var8 = 255 / var7 / 2;
                    int var9 = var18 / 2;
                    var5 = var19 / 2;
                    int var10;
                    int var21 = var10 = var9 * 200 / 120;
                    int var11 = var21 * var21;
                    boolean var12 = false;
                    if (this.C30_f486 == null) {
                        this.C30_f486 = new int[var10];
                    }

                    int var13 = var10 + var7 - (this.C30_f480 << 1);
                    if (this.C30_f480 <= 0) {
                        var12 = true;
                        var13 = var10 + var7 - (--this.C30_f480 << 1);
                    }

                    if (this.C30_f484 == null || this.C30_f484.length != var9 * var5) {
                        this.C30_f484 = new int[var9 * var5];
                    }

                    if (this.C30_f485 == null || this.C30_f485.length != var9 * var5) {
                        this.C30_f485 = new int[var9 * var5];
                    }

                    int var14;
                    for (var14 = 0; var14 < var2.C30_f486.length; ++var14) {
                        if ((var4 = var14 - var13) > var7) {
                            var4 = 255;
                        } else if (var4 < -var7) {
                            var4 = 0;
                        } else {
                            var4 = 127 + var4 * var8;
                        }

                        var2.C30_f486[var14] = var6 | var4 << 24;
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
                                var2.C30_f484[var13 + var15] = -16777216;
                            } else if (var8 < var6) {
                                var2.C30_f484[var13 + var15] = 0;
                            } else {
                                var2.C30_f484[var13 + var15] = var2.C30_f486[var14 * var8 / var11];
                            }
                        }
                    }

                    if (var2.C30_f484 != null) {
                        var1.drawRGB(var2.C30_f484, 0, var9, var9, var5, var9, var5, true);
                        var1.drawRGB(a(var2.C30_f484, var2.C30_f485, var9, var5, (byte) 2), 0, var9, 0, var5, var9, var5, true);
                        var1.drawRGB(a(var2.C30_f484, var2.C30_f485, var9, var5, (byte) 3), 0, var9, 0, 0, var9, var5, true);
                        var1.drawRGB(a(var2.C30_f484, var2.C30_f485, var9, var5, (byte) 1), 0, var9, var9, 0, var9, var5, true);
                    }

                    if (var12) {
                        var2.f();
                        var10001 = true;
                    } else {
                        var2.C30_f480 -= 10;
                        var10001 = false;
                    }

                    this.C30_f476 = var10001;
                    break;
                case 4:
                    if (this.C30_f476) {
                        this.C30_f472 = -1;
                        var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                    } else {
                        this.C30_f476 = this.e(var1);
                    }
                    break;
                case 5:
                    if (!this.C30_f476) {
                        this.C30_f476 = this.e(var1);
                    }
                    break;
                case 6:
                    if (this.C30_f476) {
                        this.C30_f472 = -1;
                        var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                    } else {
                        this.C30_f476 = this.d(var1);
                    }
                    break;
                case 7:
                    if (this.C30_f478 < GameEngineBase.getScreenHeight()) {
                        var1.drawRGB(this.C30_f484, 0, GameEngineBase.getScreenWidth(), 0, 0, GameEngineBase.getScreenWidth(), this.C30_f478, true);
                        var1.drawRGB(this.C30_f485, 0, GameEngineBase.getScreenWidth(), 0, GameEngineBase.getScreenHeight() - this.C30_f478, GameEngineBase.getScreenWidth(), this.C30_f478, true);
                        this.C30_f478 += 20;
                    } else {
                        var1.drawRGB(this.C30_f484, 0, GameEngineBase.getScreenWidth(), 0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight(), true);
                        var1.drawRGB(this.C30_f485, 0, GameEngineBase.getScreenWidth(), 0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight(), true);
                        if (this.C30_f520 == null) {
                            this.a((int) 0);
                        }
                    }

                    if (this.C30_f520 != null) {
                        this.C30_f520.a(var1, 0, 0);
                    }
                    break;
                case 8:
                    if (this.C30_f478 >= 5) {
                        var1.drawImage(C29.B().C29_f400, 0, 0, 20);
                    }

                    C30_f494.render(var1);
                    if (this.C30_f478 >= C30_f496.length) {
                        this.C30_f478 = 0;
                        this.C30_f472 = -1;
                        this.C30_f476 = true;
                        C30_f494.removeDialog("/data/ui/npcEnemy.ui");
                        return;
                    }
                    break;
                case 9:
                    var1.setColor(this.C30_f483);
                    var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                    break;
                case 10:
                    if (this.C30_f478 <= this.C30_f507) {
                        if (this.C30_f478 % 3 / (this.C30_f508 + 1) == 0) {
                            var1.setColor(16777215);
                            var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                        } else if (this.C30_f478 % 3 / (this.C30_f508 + 1) == 1) {
                            var1.setColor(0);
                            var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                        }

                        ++this.C30_f478;
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
                    if (this.C30_f472 == 15) {
                        if ((var5 = this.C30_f480) >= 255) {
                            var5 = 255;
                            this.C30_f480 = 255;
                            var17 = true;
                        }
                    } else if (this.C30_f472 == 14 && (var5 = 255 - this.C30_f480) <= 0) {
                        var5 = 0;
                        this.C30_f472 = -1;
                        var17 = true;
                    }

                    if (this.C30_f510 != null) {
                        this.C30_f510 = ImageProcessor.applyAlpha(this.C30_f510, var5);
                        var1.drawRGB(this.C30_f510.pixels, 0, this.C30_f510.width, this.C30_f511 - this.C30_f510.width / 2, this.C30_f512 - this.C30_f510.height / 2, this.C30_f510.width, this.C30_f510.height, true);
                    }

                    if (var17) {
                        this.C30_f472 = -1;
                        this.f();
                        var10001 = true;
                    } else {
                        this.C30_f480 += this.C30_f482;
                        var10001 = false;
                    }

                    this.C30_f476 = var10001;
                    break;
                case 17:
                    var1.setColor(this.C30_f517[this.C30_f483]);
                    var5 = this.C30_f513;
                    var4 = this.C30_f516;
                    int var3 = this.C30_f515;
                    var1.fillArc(var3 - var5, var4 - var5, var5 << 1, var5 << 1, 0, 360);
                    break;
                case 19:
                case 20:
                    this.C30_f476 = this.f(var1);
            }

            switch (this.C30_f474) {
                case 12:
                    var1.setColor(0);
                    var1.fillRect(0, 0, this.C30_f504, this.C30_f505 - this.C30_f479 * this.C30_f505 / this.C30_f502);
                    var1.fillRect(0, GameEngineBase.getScreenHeight() - this.C30_f506 + this.C30_f479 * this.C30_f506 / this.C30_f502, this.C30_f504, this.C30_f506 - this.C30_f479 * this.C30_f506 / this.C30_f502);
                    return;
                case 13:
                    var1.setColor(0);
                    var1.fillRect(0, 0, this.C30_f504, this.C30_f479 * this.C30_f505 / this.C30_f502);
                    var1.fillRect(0, GameEngineBase.getScreenHeight() - this.C30_f479 * this.C30_f506 / this.C30_f502, this.C30_f504, this.C30_f479 * this.C30_f506 / this.C30_f502);
                default:
            }
        }
    }

    private void a(int var1) {
        this.C30_f522 = var1;
        switch (this.C30_f472) {
            case 7:
                short var2 = ResourceManager.gameDatabase[0][C29.B().p(0)][17];
                short[] var3;
                switch (this.C30_f522) {
                    case 0:
                        var3 = new short[]{8, 118, 160, var2, 0, 1, 0, 4, 0, 2, 1, 8, 0, -16, 10, 0, 0};
                        this.C30_f520 = new C21();
                        this.C30_f520.a(var3);
                        this.C30_f520.setInteractable(true);
                        this.C30_f520.a();
                        return;
                    case 1:
                        var3 = new short[]{17, 118, 160, var2, 0, 1, 100, 255, 255, 255, 12, 0, 1, 1, 9};
                        this.C30_f520.a(var3);
                        this.C30_f520.a();
                        return;
                    case 2:
                        var3 = new short[]{17, 118, 160, var2, 0, 1, 255, 255, 255, 255, 15, 0, 1, 1, 13};
                        this.C30_f520.a(var3);
                        this.C30_f520.a();
                        return;
                    case 3:
                        var3 = new short[]{9, 118, 160, var2, 0, 1, 160, 255, 255, 255, 0, 4, 1};
                        this.C30_f520.a(var3);
                        this.C30_f520.a();
                    default:
                        return;
                }
            default:
                this.C30_f521 = C30_f496[this.C30_f478].length;
        }
    }

    public final void d() {
        if (this.C30_f472 != -1 || this.C30_f474 != -1 || this.C30_f475 != -1) {
            switch (this.C30_f472) {
                case 7:
                    if (this.C30_f520 != null && !this.C30_f520.d()) {
                        ++this.C30_f522;
                        if (this.C30_f522 >= 4) {
                            this.C30_f476 = true;
                            this.C30_f472 = -1;
                            this.C30_f478 = 0;
                            this.C30_f520 = null;
                            return;
                        }

                        this.a(this.C30_f522);
                    }
                    break;
                case 8:
                    if (this.C30_f522 < this.C30_f521) {
                        C30_f495.c(this.C30_f478, C30_f496[this.C30_f478][this.C30_f522]);
                        ++this.C30_f522;
                    } else {
                        ++this.C30_f478;
                        if (this.C30_f478 < C30_f496.length) {
                            this.a((int) 0);
                        }
                    }

                    C30_f494.closeCurrentDialog();
                case 9:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                default:
                    break;
                case 10:
                    if (this.C30_f478 > this.C30_f507) {
                        this.C30_f476 = true;
                        this.C30_f472 = -1;
                        this.C30_f478 = 0;
                    }
                    break;
                case 11:
                    if (this.C30_f498 == 0) {
                        if (this.C30_f501 >= this.C30_f497[this.C30_f499].length * this.C30_f500) {
                            this.C30_f472 = -1;
                            this.C30_f476 = true;
                            this.C30_f501 = 0;
                            CameraController.getInstance().cameraMode = 1;
                            return;
                        }

                        CameraController.getInstance().moveX(this.C30_f497[this.C30_f499][this.C30_f501 % this.C30_f497[this.C30_f499].length]);
                        ++this.C30_f501;
                    } else {
                        if (this.C30_f501 >= this.C30_f497[this.C30_f499].length * this.C30_f500) {
                            this.C30_f472 = -1;
                            this.C30_f476 = true;
                            this.C30_f501 = 0;
                            CameraController.getInstance().cameraMode = 1;
                            return;
                        }

                        CameraController.getInstance().moveY(this.C30_f497[this.C30_f499][this.C30_f501 % this.C30_f497[this.C30_f499].length]);
                        ++this.C30_f501;
                    }
                    break;
                case 17:
                    ++this.C30_f478;
                    if (this.C30_f514 == 0) {
                        if ((GameEngineBase.getScreenWidth() - this.C30_f515) * (GameEngineBase.getScreenWidth() - this.C30_f515) + (GameEngineBase.getScreenHeight() - this.C30_f516) * (GameEngineBase.getScreenHeight() - this.C30_f516) < this.C30_f513 * this.C30_f513) {
                            this.C30_f478 = 0;
                            this.C30_f476 = true;
                        }

                        this.C30_f513 += 10;
                    } else if (this.C30_f514 == 1) {
                        this.C30_f513 -= 10;
                        if (this.C30_f513 <= 0) {
                            this.C30_f478 = 0;
                            this.C30_f472 = -1;
                            this.C30_f476 = true;
                        }
                    } else if (this.C30_f478 <= 10) {
                        this.C30_f513 += 10;
                    } else if (this.C30_f478 > 10 && this.C30_f478 <= 20) {
                        this.C30_f513 -= 10;
                    } else {
                        this.C30_f478 = 0;
                        this.C30_f476 = true;
                        this.C30_f472 = -1;
                    }
            }

            switch (this.C30_f474) {
                case 12:
                    this.C30_f479 += this.C30_f503;
                    if (this.C30_f479 > this.C30_f502) {
                        this.C30_f479 = 0;
                        this.C30_f477 = true;
                        this.C30_f474 = -1;
                    }
                    break;
                case 13:
                    this.C30_f479 += this.C30_f503;
                    if (this.C30_f479 > this.C30_f502) {
                        this.C30_f479 = this.C30_f502;
                        this.C30_f477 = true;
                    }
            }

            switch (this.C30_f475) {
                case 18:
                    if (this.C30_f522 < this.C30_f521) {
                        C30_f495.a((int) this.C30_f478, (int) C30_f496[this.C30_f478][this.C30_f522]);
                        ++this.C30_f522;
                    } else {
                        ++this.C30_f478;
                        if (this.C30_f478 < C30_f496.length) {
                            this.a((int) 0);
                        }
                    }

                    C30_f494.closeCurrentDialog();
                default:
            }
        }
    }

    private boolean d(Graphics var1) {
        if (this.C30_f478 < 10) {
            if (this.C30_f478 % 3 == 1) {
                var1.setColor(16777215);
                var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
            } else {
                C25.B();
                C25.b(var1);
                C25.B().C25_f285.renderWorld(var1);
            }

            ++this.C30_f478;
        } else {
            if (this.C30_f478 >= GameEngineBase.getScreenWidth()) {
                var1.setColor(0);
                var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
                return true;
            }

            boolean var2;
            int var3;
            switch (this.C30_f493) {
                case 0:
                    var1.setColor(0);
                    var1.fillRect(0, 0, this.C30_f478, GameEngineBase.getScreenCenterY());

                    int var4;
                    for (var4 = 1; var4 < 6; ++var4) {
                        var1.fillRect(this.C30_f478 + var4 * 15, 0, 15 - var4 * 3, GameEngineBase.getScreenCenterY());
                    }

                    var1.fillRect(GameEngineBase.getScreenWidth() - this.C30_f478, GameEngineBase.getScreenCenterY(), this.C30_f478, GameEngineBase.getScreenCenterY());

                    for (var4 = 1; var4 < 6; ++var4) {
                        var1.fillRect(GameEngineBase.getScreenWidth() - this.C30_f478 - var4 * 15, GameEngineBase.getScreenCenterY(), 15 - var4 * 3, GameEngineBase.getScreenCenterY());
                    }

                    this.C30_f478 += 15;
                    break;
                case 1:
                    var1.setColor(0);
                    var2 = false;

                    for (var3 = 0; var3 < GameEngineBase.getScreenHeight(); var3 += 10) {
                        if (var2) {
                            var1.fillRect(0, var3, this.C30_f478, 10);
                            var2 = false;
                        } else {
                            var1.fillRect(GameEngineBase.getScreenWidth() - this.C30_f478, var3, this.C30_f478, 10);
                            var2 = true;
                        }
                    }

                    this.C30_f478 += 15;
                    break;
                case 2:
                    var1.setColor(0);
                    var2 = false;

                    for (var3 = 0; var3 < GameEngineBase.getScreenWidth(); var3 += 10) {
                        if (var2) {
                            var1.fillRect(var3, 0, 10, this.C30_f478);
                            var2 = false;
                        } else {
                            var1.fillRect(var3, GameEngineBase.getScreenHeight() - this.C30_f478, 10, this.C30_f478);
                            var2 = true;
                        }
                    }

                    this.C30_f478 += 15;
            }
        }

        return false;
    }

    private boolean e(Graphics var1) {
        int var2 = this.C30_f483 & 16777215;
        int var3 = 0;
        int var4 = this.C30_f488;
        int var5 = 255 / ((var4 << 1) + 1);
        int var6;
        int var7;
        int var10000 = var7 = (var6 = this.C30_f487 / 2) * 200 / 120;
        int var8 = var10000 * var10000;
        int var10 = this.C30_f489 / this.C30_f487 + 1;
        int var11 = GameEngineBase.getScreenWidth() / this.C30_f487;
        int var12 = GameEngineBase.getScreenHeight() / this.C30_f487;
        int var13 = var11 / 2;
        int var14 = var12 / 2;
        int[] var15 = new int[var7];
        if (this.C30_f490 == null) {
            this.C30_f490 = new int[GameUtils.fastSqrt(var13 * var13 + var14 * var14, 0)][];
        }

        if (this.C30_f491 == null) {
            this.C30_f491 = new int[this.C30_f490.length];
        }

        if (var10 > this.C30_f490.length) {
            var10 = this.C30_f490.length;
        }

        int var9;
        int var17;
        int var18;
        int var20;
        for (int var16 = 0; var16 < var10; ++var16) {
            if (this.C30_f490[var16] == null) {
                this.C30_f490[var16] = new int[this.C30_f487 * this.C30_f487];
            }

            if (this.C30_f491[var16] < var7 + var4) {
                var9 = -var4 + this.C30_f491[var16];

                for (var17 = 0; var17 < var15.length; ++var17) {
                    if ((var18 = var17 - var9) > var4) {
                        var3 = this.C30_f472 == 4 ? 0 : 255;
                    } else if (var18 < -var4) {
                        var3 = this.C30_f472 == 4 ? 255 : 0;
                    } else if (this.C30_f472 == 4) {
                        var3 = 127 - var18 * var5;
                    } else if (this.C30_f472 == 5) {
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

                for (var9 = 0; var9 < this.C30_f487; ++var9) {
                    var18 = (var9 - var6) * (var9 - var6);
                    int var21 = var9 * this.C30_f487;

                    for (int var23 = 0; var23 < this.C30_f487; ++var23) {
                        int var22 = GameUtils.fastSqrt(var17 = var18 + (var23 - var6) * (var23 - var6), 1);
                        if (var17 > var19) {
                            this.C30_f490[var16][var21 + var23] = this.C30_f472 == 4 ? 0 : -16777216;
                        } else if (var17 < var20) {
                            this.C30_f490[var16][var21 + var23] = this.C30_f472 == 4 ? -16777216 : 0;
                        } else {
                            this.C30_f490[var16][var21 + var23] = var15[var22];
                        }
                    }
                }

                int var10002 = this.C30_f491[var16]++;
            }
        }

        boolean var24 = true;
        var1.setColor(var2);

        for (var17 = 0; var17 < var12; ++var17) {
            var18 = (var17 - var14) * (var17 - var14);

            for (var20 = 0; var20 < var11; ++var20) {
                var9 = GameUtils.fastSqrt(var18 + (var20 - var13) * (var20 - var13), 1);
                if (this.C30_f490[var9] == null) {
                    if (this.C30_f472 == 5) {
                        var1.fillRect(var20 * this.C30_f487, var17 * this.C30_f487, this.C30_f487, this.C30_f487);
                    }
                } else if (this.C30_f491[var9] >= var7 + var4) {
                    if (this.C30_f472 == 4) {
                        var1.fillRect(var20 * this.C30_f487, var17 * this.C30_f487, this.C30_f487, this.C30_f487);
                    }
                } else {
                    var24 = false;
                    var1.drawRGB(this.C30_f490[var9], 0, this.C30_f487, var20 * this.C30_f487, var17 * this.C30_f487, this.C30_f487, this.C30_f487, true);
                }
            }
        }

        if (var24) {
            this.f();
            return true;
        } else {
            this.C30_f489 += 15;
            return false;
        }
    }

    private static int[] a(int[] var0, int[] var1, int var2, int var3, byte var4) {
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

    public final void a(byte var1) {
        this.C30_f523 = -1;
        this.C30_f510 = null;
    }

    public final void a(byte var1, int var2, int var3, int var4, int var5, int var6, int var7) {
        this.C30_f523 = 0;
        this.b(var2, var3);
        this.C30_f526 = var4;
        this.C30_f527 = var5;
        this.C30_f528 = var6;
        this.C30_f529 = var7;
    }

    public final void a(int var1, int var2) {
        this.C30_f528 = var1;
        this.C30_f529 = var2;
    }

    public final void b(int var1, int var2) {
        int var10000 = this.C30_f524;
        var10000 = this.C30_f525;
        this.C30_f524 = var1;
        this.C30_f525 = var2;
    }

    public final void a(Graphics var1, int var2, int var3) {
        var1.setColor(0);
        var1.fillRect(0, 0, this.C30_f526, this.C30_f525 - this.C30_f529);
        var1.fillRect(0, this.C30_f525 - this.C30_f529, this.C30_f524 - this.C30_f528, this.C30_f529 << 1);
        var1.fillRect(0, this.C30_f525 + this.C30_f529, this.C30_f526, this.C30_f527 - (this.C30_f525 + this.C30_f529));
        var1.fillRect(this.C30_f524 + this.C30_f528, this.C30_f525 - this.C30_f529, this.C30_f526 - (this.C30_f524 + this.C30_f528), this.C30_f529 << 1);
    }

    public final void c(int var1, int var2) {
        this.C30_f480 = 0;
        this.C30_f483 = var1;
        if (var2 != 12 && var2 != 13) {
            if (var2 == 18) {
                this.C30_f475 = var2;
            } else {
                this.C30_f472 = var2;
            }
        } else {
            this.C30_f474 = var2;
            this.C30_f477 = false;
        }

        this.C30_f476 = false;
        switch (this.C30_f472) {
            case 1:
            case 2:
                this.C30_f482 = 17;
                return;
            case 3:
                this.C30_f480 = GameEngineBase.getScreenCenterX();
                this.C30_f481 = 20;
                return;
            case 4:
            case 5:
                this.C30_f489 = 0;
                return;
            case 6:
                this.C30_f478 = 0;
                this.C30_f493 = GameUtils.getRandomInt(2);
                return;
            case 7:
                this.C30_f478 = 0;
                this.C30_f484 = null;
                this.C30_f485 = null;
                this.C30_f484 = new int[GameEngineBase.getScreenWidth() * GameEngineBase.getScreenHeight()];
                this.C30_f485 = new int[this.C30_f484.length];

                for (var1 = 0; var1 < this.C30_f484.length; ++var1) {
                    int[] var10000;
                    if (var1 % GameEngineBase.getScreenWidth() / 10 % 2 == 0) {
                        this.C30_f484[var1] = -2013265920 | this.C30_f484[var1] & 16777215;
                        var10000 = this.C30_f485;
                        var10000[var1] &= 16777215;
                    } else {
                        this.C30_f485[var1] = -2013265920 | this.C30_f485[var1] & 16777215;
                        var10000 = this.C30_f484;
                        var10000[var1] &= 16777215;
                    }
                }

                return;
            case 8:
                this.C30_f478 = 0;
                C30_f495.aw();
                this.a((int) 0);
                return;
            case 10:
            case 17:
                this.C30_f478 = 0;
                return;
            case 19:
                this.c(-1);
                return;
            case 20:
                this.c(1);
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

    public final void a(int var1, int var2, int var3) {
        CameraController.getInstance().cameraMode = 3;
        this.C30_f498 = var1;
        this.C30_f499 = var2;
        this.C30_f500 = var3;
    }

    public final void a(int var1, int var2, int var3, int var4, int var5) {
        this.C30_f479 = 0;
        this.C30_f502 = var1;
        this.C30_f503 = var2;
        this.C30_f504 = var3;
        this.C30_f505 = var4;
        this.C30_f506 = var5;
    }

    public final void d(int var1, int var2) {
        this.C30_f507 = var1;
        this.C30_f508 = var2;
    }

    public final void a(int var1, int var2, int var3, int var4) {
        this.C30_f514 = var1;
        this.C30_f515 = var2;
        this.C30_f516 = var3;
        this.C30_f513 = var4;
    }

    public final void a(String var1, int var2, int var3, int var4) {
        if (C7.C7_f51 == 1) {
            this.C30_f509 = GameUtils.loadImage("/data/tex/", var1);
            this.C30_f509 = ImageProcessor.convertToGrayscale(this.C30_f509);
        } else {
            this.C30_f509 = GameUtils.loadImage("/data/tex/", var1);
        }

        this.C30_f510 = new ImageData();
        this.C30_f510 = ImageProcessor.convertImageWithColorCorrection(this.C30_f509, this.C30_f510);
        this.C30_f511 = var2;
        this.C30_f512 = var3;
        this.C30_f482 = var4;
    }

    public final void e() {
        this.C30_f478 = 0;
        C30_f496 = new short[][]{{7}, {8}, {10}, {12}, {16}, {18}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {20}, {200, 200, 100, 100, 50, 50, 0, 0}, {38, 38, 39, 39, 40, 40, 41, 41, 42, 42}, {43, 43, 44, 44, 45, 45, 46, 46, 47, 47, 48}};
        C30_f495.v();
        this.a((int) 0);
    }

    private void f() {
        this.C30_f484 = null;
        this.C30_f485 = null;
        this.C30_f486 = null;
        this.C30_f490 = null;
        this.C30_f491 = null;
        this.C30_f509 = null;
    }

    public final void a(int var1, int var2, byte var3, byte var4, Image var5, String[] var6) {
        this.C30_f473 = var1;
        if (this.C30_f472 != 17 || this.C30_f514 != 0) {
            this.C30_f472 = -1;
            if (this.C30_f473 >= 16) {
                this.C30_f531 = var3;
                this.C30_f535 = var1;
                this.C30_f536 = 0;
                this.C30_f537 = var4;
                this.C30_f533 = var5;
                this.C30_f532 = null;
                this.C30_f532 = new Image[var6.length];

                for (var1 = 0; var1 < var6.length; ++var1) {
                    this.C30_f532[var1] = GameUtils.loadImage("/data/tex/", var6[var1]);
                }

                this.C30_f534 = new int[var3][5];

                for (var1 = 0; var1 < var3; ++var1) {
                    this.b(var1);
                }

            }
        }
    }

    private void b(int var1) {
        int var2;
        if ((var2 = GameUtils.getRandomInt(100)) < 3) {
            this.C30_f534[var1][0] = this.C30_f532.length - 1;
        } else if (var2 < 15) {
            this.C30_f534[var1][0] = this.C30_f532.length - 2;
        } else if (var2 < 50) {
            this.C30_f534[var1][0] = this.C30_f532.length - 3;
        } else {
            this.C30_f534[var1][0] = 0;
        }

        this.C30_f534[var1][1] = GameUtils.getRandomInt(GameEngineBase.getScreenWidth());
        this.C30_f534[var1][2] = GameUtils.getRandomInt(GameEngineBase.getScreenHeight());
        this.C30_f534[var1][3] = GameUtils.getRandomInt(C30_f530[this.C30_f534[var1][0]][1] - C30_f530[this.C30_f534[var1][0]][0]) + C30_f530[this.C30_f534[var1][0]][0];
        this.C30_f534[var1][4] = GameUtils.getRandomOffset(2);
    }

    public final void b(Graphics var1) {
        if (this.C30_f473 >= 16) {
            if (this.C30_f533 == null) {
                var1.setColor(0);
                var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
            } else {
                int var2 = this.C30_f533.getWidth();

                for (int var3 = 0; var3 < GameEngineBase.getScreenWidth() / var2; ++var3) {
                    var1.drawImage(this.C30_f533, var3 * var2, 0, 20);
                }
            }

            this.C30_f539 = false;
        }
    }

    public final void c(Graphics var1) {
        if (this.C30_f473 >= 16 || this.C30_f539) {
            for (int var2 = 0; var2 < this.C30_f531; ++var2) {
                if (this.C30_f534[var2][1] < GameEngineBase.getScreenWidth() && this.C30_f534[var2][2] < GameEngineBase.getScreenHeight()) {
                    var1.drawImage(this.C30_f532[this.C30_f534[var2][0]], this.C30_f534[var2][1], this.C30_f534[var2][2], 20);
                }

                int[] var10000;
                switch (this.C30_f537) {
                    case 0:
                        var10000 = this.C30_f534[var2];
                        var10000[2] -= this.C30_f534[var2][3];
                        break;
                    case 1:
                        var10000 = this.C30_f534[var2];
                        var10000[1] += this.C30_f534[var2][3];
                        var10000 = this.C30_f534[var2];
                        var10000[2] -= this.C30_f534[var2][3];
                        break;
                    case 2:
                        var10000 = this.C30_f534[var2];
                        var10000[1] += this.C30_f534[var2][3];
                        break;
                    case 3:
                        var10000 = this.C30_f534[var2];
                        var10000[1] += this.C30_f534[var2][3];
                        var10000 = this.C30_f534[var2];
                        var10000[2] += this.C30_f534[var2][3];
                        break;
                    case 4:
                        var10000 = this.C30_f534[var2];
                        var10000[2] += this.C30_f534[var2][3];
                        break;
                    case 5:
                        var10000 = this.C30_f534[var2];
                        var10000[1] -= this.C30_f534[var2][3];
                        var10000 = this.C30_f534[var2];
                        var10000[2] += this.C30_f534[var2][3];
                        break;
                    case 6:
                        var10000 = this.C30_f534[var2];
                        var10000[1] -= this.C30_f534[var2][3];
                        break;
                    case 7:
                        var10000 = this.C30_f534[var2];
                        var10000[1] -= this.C30_f534[var2][3];
                        var10000 = this.C30_f534[var2];
                        var10000[2] -= this.C30_f534[var2][3];
                }

                if (this.C30_f534[var2][1] < this.C30_f535 - this.C30_f532[this.C30_f534[var2][0]].getWidth() || this.C30_f534[var2][2] < this.C30_f536 - this.C30_f532[this.C30_f534[var2][0]].getHeight()) {
                    this.b(var2);
                }
            }

        }
    }

    private void c(int var1) {
        C30_f519[0] = 20;
        C30_f518 = GameEngineBase.getScreenWidth() / C30_f519[0];
        C30_f519[1] = (GameEngineBase.getScreenHeight() - 1) / C30_f519[0] + 1;
        C30_f519[2] = var1;
        C30_f519[3] = GameUtils.getRandomInRange(0, 7);
        C30_f519[4] = 0;
        this.C30_f476 = false;
    }

    private boolean f(Graphics var1) {
        var1.setColor(this.C30_f483);
        int var2 = 0;

        for (int var3 = 0; var3 < C30_f518; ++var3) {
            for (int var4 = 0; var4 < C30_f519[1]; ++var4) {
                switch (C30_f519[2]) {
                    case -1:
                        var2 = 0;
                        break;
                    case 1:
                        var2 = C30_f519[0];
                }

                switch (C30_f519[3]) {
                    case 0:
                        var2 = (var2 += (var4 - C30_f519[4]) * C30_f519[2]) < 0 ? 0 : (var2 > C30_f519[0] ? C30_f519[0] : var2);
                        break;
                    case 1:
                        var2 = (var2 += (C30_f519[1] - var4 - C30_f519[4]) * C30_f519[2]) < 0 ? 0 : (var2 > C30_f519[0] ? C30_f519[0] : var2);
                        break;
                    case 2:
                        var2 = (var2 += (var3 - C30_f519[4]) * C30_f519[2]) < 0 ? 0 : (var2 > C30_f519[0] ? C30_f519[0] : var2);
                        break;
                    case 3:
                        var2 = (var2 += (8 - var3 - C30_f519[4]) * C30_f519[2]) < 0 ? 0 : (var2 > C30_f519[0] ? C30_f519[0] : var2);
                        break;
                    case 4:
                        var2 = (var2 += ((var3 + var4 >> 1) - C30_f519[4]) * C30_f519[2]) < 0 ? 0 : (var2 > C30_f519[0] ? C30_f519[0] : var2);
                        break;
                    case 5:
                        var2 = (var2 += ((8 - var3 + var4 >> 1) - C30_f519[4]) * C30_f519[2]) < 0 ? 0 : (var2 > C30_f519[0] ? C30_f519[0] : var2);
                        break;
                    case 6:
                        var2 = (var2 += ((var3 + C30_f519[1] - var4 >> 1) - C30_f519[4]) * C30_f519[2]) < 0 ? 0 : (var2 > C30_f519[0] ? C30_f519[0] : var2);
                        break;
                    case 7:
                        var2 = (var2 += ((8 - var3 + C30_f519[1] - var4 >> 1) - C30_f519[4]) * C30_f519[2]) < 0 ? 0 : (var2 > C30_f519[0] ? C30_f519[0] : var2);
                }

                var1.fillRect(C30_f519[0] * var3 + (C30_f519[0] - var2 >> 1), C30_f519[0] * var4 + (C30_f519[0] - var2 >> 1), var2, var2);
            }
        }

        int[] var10000 = C30_f519;
        var10000[4] += 2;
        if (C30_f519[4] > 40) {
            if (this.C30_f472 == 20) {
                this.C30_f472 = -1;
            }

            if (this.C30_f472 == 19) {
                var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
            }

            return true;
        } else {
            return false;
        }
    }

    static {
        byte[] var10000 = new byte[]{0, 5, 3, 6, 2, 7, 1, 4};
        C30_f530 = new int[][]{{1, 3}, {1, 4}, {2, 5}, {2, 6}};
    }
}
