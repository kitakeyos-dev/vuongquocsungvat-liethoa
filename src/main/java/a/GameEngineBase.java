package a;

import a.b.C67;
import c.DialogManager;
import e.a.a.a.payment.PaymentCallback;
import e.a.a.a.payment.SmsPaymentScreen;
import e.b.a.a.C2;
import game.*;
import me.kitakeyos.C50;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public abstract class GameEngineBase extends C8 implements PaymentCallback, C2 {
    private static short screenWidth;
    private static short screenHeight;
    private static int frameRate;
    private static Font defaultFont;
    private static Font largeFont;
    private static int backgroundColor;
    public byte C44_f698;
    public byte C44_f699;
    public DialogManager dialogManager;
    public C9 gameController;
    private static boolean timerStarted = false;
    public static boolean gamePaused = false;
    public static byte actionType = -1;
    public static byte currentAction = 0;
    private static byte[][] actionData = new byte[7][3];
    private static Timer gameTimer;
    private static TimerTask gameTimerTask;
    private static boolean engineStopped = false;
    public static boolean inputEnabled;
    public static boolean paymentActive = false;
    private byte paymentType;
    private static byte[] paymentCounts = new byte[]{0, 0, 0, 0, 0};
    private byte currentPaymentIndex;
    private byte currentPaymentCount;
    private byte totalPaymentCount;
    private String[] paymentCodes = new String[]{"01", "02", "03", "04", "05"};
    private byte[][] paymentSettings = new byte[][]{{4, 1, 0}, {2, 1, 1}, {2, 1, 2}, {2, 1, 3}, {2, 1, 4}};
    private String[][] paymentDescriptions = new String[][]{{"Kích hoạt trò chơi", "Bạn muốn khám phá bí mật của vương quốc sủng vật, dẫn dắt thú yêu chiến đấu, tiến hóa, ấp trứng? Chỉ cần 1 tin nhắn 0đ để kích hoạt trò chơi, chỉ nhắn tin 1 lần cho tất cả các lượt chơi. Bạn có muốn nhắn tin không?"}, {"Tất trúng cầu", "Chỉ cần nhắn 1 tin nhắn 0đ, bạn sẽ sở hữu 1 tất trúng cầu, tỷ lệ 100% bắt được sủng vật? Bạn có muốn nhắn tin không?"}, {"Mua kim tiền", "Kiếm tiền vất vả, vật phẩm đắt đỏ? Chỉ cần nhắn 1 tin nhắn 0đ bạn sẽ đạt được 10000 kim tiền. Bạn có muốn nhắn tin không?"}, {"Mua đẳng cấp", "Thăng cấp chậm chạp, kẻ địch lại quá mạnh? Chỉ cần 1 tin nhắn 0đ, tất cả sủng vật trong ba lô của bạn đều được thăng lên 5 cấp. Bạn có muốn nhắn tin không?"}, {"Mua huy hiệu", "Kiếm huy hiệu khó khăn? Chỉ cần 1 tin nhắn 0đ, bạn sẽ đạt được 10 huy hiệu. Bạn có muốn nhắn tin không?"}};
    private SmsPaymentScreen paymentScreen;

    public abstract void update();

    public abstract void renderPauseScreen(Graphics var1);

    public abstract boolean initializeGame();

    public abstract void cleanupCurrentScreen();

    public abstract void changeState(byte var1);

    public final void startGameTimer() {
        if (!timerStarted) {
            if (gameTimer == null || gameTimerTask == null) {
                gameTimer = new Timer();
                gameTimerTask = new C45();
            }

            gameTimer.schedule(gameTimerTask, 10L, 200L);
            timerStarted = true;
        }
    }

    protected static void stopGameTimer() {
        if (gameTimerTask != null) {
            gameTimerTask.cancel();
            gameTimerTask = null;
        }

        if (gameTimer != null) {
            gameTimer.cancel();
            gameTimer = null;
            System.gc();
        }

        timerStarted = false;
        engineStopped = true;
    }

    public static void resetEngine(boolean var0) {
        engineStopped = var0;
    }

    public static boolean isEngineStopped() {
        return engineStopped;
    }

    public static void initializeDisplay(short width, short height) {
        screenWidth = width;
        screenHeight = height;
    }

    public static short getScreenWidth() {
        return screenWidth;
    }

    public static short getScreenHeight() {
        return screenHeight;
    }

    public static short getScreenCenterX() {
        return (short) (screenWidth / 2);
    }

    public static short getScreenCenterY() {
        return (short) (screenHeight / 2);
    }

    public static void setFrameRate(int var0) {
        frameRate = var0;
    }

    public static int getFrameRate() {
        return frameRate;
    }

    public static void setBackgroundColor(int var0) {
        backgroundColor = var0;
    }

    public static int getBackgroundColor() {
        return backgroundColor;
    }

    public static Font getDefaultFont() {
        if (defaultFont == null) {
            defaultFont = Font.getFont(0, 0, 8);
        }

        return defaultFont;
    }

    public static Font getLargeFont() {
        if (largeFont == null) {
            largeFont = Font.getFont(0, 0, 16);
        }

        return largeFont;
    }

    public static int getFontHeight() {
        return defaultFont.getHeight();
    }

    public static String getLocalizedText(int var0) {
        return var0 == 0 ? "" : C67.C67_f924[var0];
    }

    public static String formatString(int template, int[] parameters) {
        if (template == 0) {
            return "";
        } else {
            int var2 = 0;
            String var3 = "";
            int var4;
            if ((var4 = getLocalizedText(template).indexOf("%s", 0)) == -1) {
                return getLocalizedText(template);
            } else {
                int var5;
                for (var5 = 0; var4 != -1; var4 = getLocalizedText(template).indexOf("%s", var5)) {
                    var3 = var3 + getLocalizedText(template).substring(var5, var4) + parameters[var2];
                    ++var2;
                    var5 = var4 + 2;
                }

                return var3 + getLocalizedText(template).substring(var5);
            }
        }
    }

    public static String a(String var0, int[] var1) {
        if (var0.equals("")) {
            return "";
        } else {
            int var2 = 0;
            String var3 = "";
            int var4;
            if ((var4 = var0.indexOf("%s", 0)) == -1) {
                return var0;
            } else {
                int var5;
                for (var5 = 0; var4 != -1; var4 = var0.indexOf("%s", var5)) {
                    var3 = var3 + var0.substring(var5, var4) + var1[var2];
                    ++var2;
                    var5 = var4 + 2;
                }

                return var3 + var0.substring(var5);
            }
        }
    }

    public static String a(int var0, String[] var1) {
        if (var0 == 0) {
            return "";
        } else {
            int var2 = 0;
            String var3 = "";
            int var4;
            if ((var4 = getLocalizedText(var0).indexOf("%s", 0)) == -1) {
                return getLocalizedText(var0);
            } else {
                int var5;
                for (var5 = 0; var4 != -1; var4 = getLocalizedText(var0).indexOf("%s", var5)) {
                    var3 = var3 + getLocalizedText(var0).substring(var5, var4) + var1[var2];
                    ++var2;
                    var5 = var4 + 2;
                }

                return var3 + getLocalizedText(var0).substring(var5);
            }
        }
    }

    public static void a(Graphics var0, Image var1, String var2, int var3, int var4, int var5, int var6, int var7) {
        for (var7 = 0; var7 < var2.length(); ++var7) {
            char var8;
            if (Character.isDigit(var8 = var2.charAt(var7))) {
                var8 = (char) (var8 - 48);
            } else {
                switch (var8) {
                    case '+':
                        var8 = '\n';
                        break;
                    case '-':
                        var8 = '\n';
                }
            }

            var0.drawRegion(var1, var8 * var5, 0, var5, var6, 0, var3 - ((var2.length() - 1 - (var7 << 1)) * var5 >> 1), var4, 20);
        }

    }

    public static boolean p() {
        return actionType != -1;
    }

    public void q() {
    }

    public void r() {
    }

    public static boolean s() {
        if (actionType == -1) {
            return true;
        } else {
            return actionData[actionType][0] == 1;
        }
    }

    public static boolean t() {
        if (actionType == -1) {
            return true;
        } else {
            return actionData[actionType][0] == 2;
        }
    }

    public static boolean a(int var0, int var1) {
        if (actionType == -1) {
            return false;
        } else if (var1 != actionData[actionType][2]) {
            return true;
        } else if (actionData[actionType][1] == -1) {
            return true;
        } else {
            return actionData[actionType][1] == var0;
        }
    }

    public static void b(int var0, int var1) {
        if (actionType != -1) {
            if (var1 == -1) {
                actionData[actionType][2] = 0;
            }

            actionData[actionType][var0] = (byte) var1;
        }
    }

    public static byte d(int var0) {
        return actionType == -1 ? -1 : actionData[actionType][1];
    }

    public static void u() {
        b(1, -1);
        b(0, 0);
        actionType = -1;
        currentAction = 0;
    }

    public final void b(boolean var1) {
        if (this.paymentType == 4) {
            if (var1) {
                ++this.currentPaymentCount;
                ++paymentCounts[this.currentPaymentIndex];
                System.out.println(" curNum = " + this.currentPaymentCount + " tolNum = " + this.totalPaymentCount);
                if (this.currentPaymentCount >= this.totalPaymentCount) {
                    switch (this.currentPaymentIndex) {
                        case 0:
                            paymentActive = true;
                            C53.p().s(2000);
                            C53.p().c(1, 5, (byte) 0);
                            C53.p().c(4, 5, (byte) 0);
                            C53.p().c(11, 2, (byte) 0);
                            C53.p().v(5);
                            C7.B().C7_f60[C25.e(9, 0)][5] = 3;
                            C7.B().C7_f56[5].a((byte) 3);
                            break;
                        case 1:
                            C53.p().c(0, 1, (byte) 0);
                            break;
                        case 2:
                            C53.p().s(10000);
                            break;
                        case 3:
                            C25.C25_f335 = 0;
                            if (C25.C25_f334 == null) {
                                C25.C25_f334 = new Vector();
                            }

                            if (C25.C25_f333 == null) {
                                C25.C25_f333 = new Vector();
                            }

                            C25.C25_f334.removeAllElements();
                            C25.C25_f333.removeAllElements();

                            for (int var2 = 0; var2 < C53.p().C53_f778; ++var2) {
                                if (C53.p().C53_f777[var2].t() == 50) {
                                    C53.p().C53_f777[var2].K();
                                } else {
                                    C53.p().C53_f777[var2].y();
                                    if (C53.p().C53_f777[var2].t() + 5 >= 50) {
                                        C53.p().C53_f777[var2].h(50 - C53.p().C53_f777[var2].t());
                                    } else {
                                        C53.p().C53_f777[var2].h((int) 5);
                                    }

                                    C53.p().C53_f777[var2].J();
                                    if (C53.p().C53_f777[var2].F() < 5 && C53.p().C53_f777[var2].F() < C53.p().C53_f777[var2].t() / 10 + 1) {
                                        C25.C25_f333.addElement(C53.p().C53_f777[var2]);
                                        C25.C25_f334.addElement("" + var2);
                                    }
                                }
                            }

                            if (C25.C25_f333.size() <= 0) {
                                C25.C25_f335 = 2;
                            } else {
                                C25.C25_f335 = 1;
                            }
                            break;
                        case 4:
                            C53.p().v(10);
                    }
                }

                this.c((byte) 2);
                return;
            }

            this.c((byte) 3);
        }

    }

    private boolean B() {
        switch (this.currentPaymentIndex) {
            case 0:
                this.l(this.currentPaymentIndex);
                break;
            case 1:
                this.l(this.currentPaymentIndex);
                break;
            case 2:
                this.l(this.currentPaymentIndex);
                break;
            case 3:
                this.l(this.currentPaymentIndex);
                break;
            case 4:
                this.l(this.currentPaymentIndex);
        }

        return true;
    }

    public final boolean b(byte var1) {
        this.currentPaymentIndex = var1;
        switch (var1) {
            case 0:
                this.totalPaymentCount = 1;
                break;
            case 1:
                this.totalPaymentCount = 1;
                break;
            case 2:
                this.totalPaymentCount = 1;
                break;
            case 3:
                this.totalPaymentCount = 1;
                break;
            case 4:
                this.totalPaymentCount = 1;
        }

        this.currentPaymentCount = 0;
        return true;
    }

    public final void c(byte var1) {
        while (true) {
            byte var10000 = this.paymentType;
            if (var1 != 5 && var1 != 0) {
                this.gameController.aM();
            }

            switch (var1) {
                case 1:
                    System.out.println(" " + formatString(513, (int[]) (new int[]{this.totalPaymentCount, this.currentPaymentCount})));
                    this.gameController.d(formatString(513, (int[]) (new int[]{this.totalPaymentCount, this.currentPaymentCount})));
                    break;
                case 2:
                    if (this.w()) {
                        if (this.currentPaymentIndex == 0) {
                            this.gameController.d(getLocalizedText((int) 515) + getLocalizedText((int) 633));
                        } else {
                            this.gameController.d(getLocalizedText((int) 515));
                        }
                    } else {
                        this.gameController.d(getLocalizedText((int) 516));
                        System.out.println(" " + getLocalizedText((int) 516));
                    }
                    break;
                case 3:
                    System.out.println(" " + getLocalizedText((int) 516));
                    this.gameController.d(getLocalizedText((int) 516));
                    break;
                case 4:
                    System.out.println(" " + getLocalizedText((int) 514));
                    this.gameController.d(getLocalizedText((int) 514));
                    break;
                case 5:
                    gamePaused = false;
                    this.gameController.aN();
            }

            this.paymentType = var1;
            if (var1 != 5) {
                return;
            }

            var1 = 0;
        }
    }

    public final int v() {
        return this.currentPaymentIndex;
    }

    public final boolean w() {
        return this.currentPaymentCount >= this.totalPaymentCount;
    }

    public final byte x() {
        return this.paymentType;
    }

    public final byte y() {
        return this.totalPaymentCount;
    }

    public final void e(int var1) {
        gamePaused = true;
        if (var1 == 1) {
            this.c((byte) 4);
            if (!this.B()) {
                this.c((byte) 3);
                return;
            }
        } else if (var1 == 2) {
            this.c((byte) 5);
        }

    }

    public final void f(int var1) {
        switch (this.paymentType) {
            case 1:
                this.e(var1);
                return;
            case 3:
                if (var1 == 1 || var1 == 2) {
                    this.c((byte) 5);
                }
            case 2:
            case 4:
            default:
        }
    }

    private void l(int var1) {
        C50.a((MIDlet) GameMIDLet.C15_f217);
        C50.a(var1);
        this.paymentSettings[var1][0] = 1;
        this.paymentScreen = new SmsPaymentScreen(GameMIDLet.C15_f217, GameCanvas.publicInstance, "", "", this.paymentCodes[var1], (String) null, this.paymentSettings[var1][0], this.paymentDescriptions[var1][0], C50.smsDescriptions[var1], "");
        Display.getDisplay(GameMIDLet.C15_f217).setCurrent(this.paymentScreen);
        this.paymentScreen.setPaymentCallback((PaymentCallback) this);
    }

    public final void onPaymentCompleted(boolean var1) {
        this.b(var1);
    }
}
