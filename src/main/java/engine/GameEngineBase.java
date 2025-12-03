package engine;

import engine.entity.ResourceManager;
import layout.DialogManager;
import payment.PaymentCallback;
import payment.SmsPaymentScreen;
import callback.StateChangeCallback;
import game.*;
import me.kitakeyos.SmsConfigLoader;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public abstract class GameEngineBase extends InputStateManager implements PaymentCallback, StateChangeCallback {
    private static short screenWidth;
    private static short screenHeight;
    private static int frameRate;
    private static Font defaultFont;
    private static Font largeFont;
    private static int backgroundColor;
    public byte currentState;
    public byte previousState;
    public DialogManager dialogManager;
    public GameUIController gameController;
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
    private static byte[] paymentCounts = new byte[] { 0, 0, 0, 0, 0 };
    private byte currentPaymentIndex;
    private byte currentPaymentCount;
    private byte totalPaymentCount;
    private String[] paymentCodes = new String[] { "01", "02", "03", "04", "05" };
    private byte[][] paymentSettings = new byte[][] { { 4, 1, 0 }, { 2, 1, 1 }, { 2, 1, 2 }, { 2, 1, 3 }, { 2, 1, 4 } };
    private String[][] paymentDescriptions = new String[][] { { "Kích hoạt trò chơi",
            "Bạn muốn khám phá bí mật của vương quốc sủng vật, dẫn dắt thú yêu chiến đấu, tiến hóa, ấp trứng? Chỉ cần 1 tin nhắn 0đ để kích hoạt trò chơi, chỉ nhắn tin 1 lần cho tất cả các lượt chơi. Bạn có muốn nhắn tin không?" },
            { "Tất trúng cầu",
                    "Chỉ cần nhắn 1 tin nhắn 0đ, bạn sẽ sở hữu 1 tất trúng cầu, tỷ lệ 100% bắt được sủng vật? Bạn có muốn nhắn tin không?" },
            { "Mua kim tiền",
                    "Kiếm tiền vất vả, vật phẩm đắt đỏ? Chỉ cần nhắn 1 tin nhắn 0đ bạn sẽ đạt được 10000 kim tiền. Bạn có muốn nhắn tin không?" },
            { "Mua đẳng cấp",
                    "Thăng cấp chậm chạp, kẻ địch lại quá mạnh? Chỉ cần 1 tin nhắn 0đ, tất cả sủng vật trong ba lô của bạn đều được thăng lên 5 cấp. Bạn có muốn nhắn tin không?" },
            { "Mua huy hiệu",
                    "Kiếm huy hiệu khó khăn? Chỉ cần 1 tin nhắn 0đ, bạn sẽ đạt được 10 huy hiệu. Bạn có muốn nhắn tin không?" } };
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
                gameTimerTask = new RepaintTimerTask();
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
        return var0 == 0 ? "" : ResourceManager.localizedStrings[var0];
    }

    public static String formatString(int template, int[] parameters) {
        if (template == 0) {
            return "";
        } else {
            int var2 = 0;
            String var3 = "";
            int var4 = getLocalizedText(template).indexOf("%s", 0);
            if (var4 == -1) {
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

    public static String formatString(String var0, int[] var1) {
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

    public static String formatString(int var0, String[] var1) {
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

    public static void drawTextRegion(Graphics var0, Image var1, String var2, int var3, int var4, int var5, int var6,
            int var7) {
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

            var0.drawRegion(var1, var8 * var5, 0, var5, var6, 0, var3 - ((var2.length() - 1 - (var7 << 1)) * var5 >> 1),
                    var4, 20);
        }

    }

    public static boolean isActionActive() {
        return actionType != -1;
    }

    public void updateActionSequence() {
    }

    public void handleActionResponse() {
    }

    public static boolean isConfirmAllowed() {
        if (actionType == -1) {
            return true;
        } else {
            return actionData[actionType][0] == 1;
        }
    }

    public static boolean isCancelAllowed() {
        if (actionType == -1) {
            return true;
        } else {
            return actionData[actionType][0] == 2;
        }
    }

    public static boolean isActionBlocked(int var0, int var1) {
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

    public static void setActionData(int var0, int var1) {
        if (actionType != -1) {
            if (var1 == -1) {
                actionData[actionType][2] = 0;
            }

            actionData[actionType][var0] = (byte) var1;
        }
    }

    public static byte getActionData(int var0) {
        return actionType == -1 ? -1 : actionData[actionType][var0];
    }

    public static void resetAction() {
        setActionData(1, -1);
        setActionData(0, 0);
        actionType = -1;
        currentAction = 0;
    }

    public final void onStateChange(boolean state) {
        if (this.paymentType == 4) {
            if (state) {
                ++this.currentPaymentCount;
                ++paymentCounts[this.currentPaymentIndex];
                System.out.println(" curNum = " + this.currentPaymentCount + " tolNum = " + this.totalPaymentCount);
                if (this.currentPaymentCount >= this.totalPaymentCount) {
                    switch (this.currentPaymentIndex) {
                        case 0:
                            paymentActive = true;
                            PlayerCharacter.getInstance().addGold(2000);
                            PlayerCharacter.getInstance().addItem(1, 5, (byte) 0);
                            PlayerCharacter.getInstance().addItem(4, 5, (byte) 0);
                            PlayerCharacter.getInstance().addItem(11, 2, (byte) 0);
                            PlayerCharacter.getInstance().addBadges(5);
                            QuestManager.getInstance().questStates[WorldGameSession.getAreaIndex(9, 0)][5] = 3;
                            QuestManager.getInstance().eventScripts[5].setExecutionState((byte) 3);
                            break;
                        case 1:
                            PlayerCharacter.getInstance().addItem(0, 1, (byte) 0);
                            break;
                        case 2:
                            PlayerCharacter.getInstance().addGold(10000);
                            break;
                        case 3:
                            WorldGameSession.tutorialStep = 0;
                            if (WorldGameSession.evolutionQueue == null) {
                                WorldGameSession.evolutionQueue = new Vector();
                            }

                            if (WorldGameSession.battlePartyBackup == null) {
                                WorldGameSession.battlePartyBackup = new Vector();
                            }

                            WorldGameSession.evolutionQueue.removeAllElements();
                            WorldGameSession.battlePartyBackup.removeAllElements();

                            for (int var2 = 0; var2 < PlayerCharacter.getInstance().partySize; ++var2) {
                                if (PlayerCharacter.getInstance().partyPokemon[var2].getLevel() == 50) {
                                    PlayerCharacter.getInstance().partyPokemon[var2].checkEvolution();
                                } else {
                                    PlayerCharacter.getInstance().partyPokemon[var2].cacheCurrentStats();
                                    if (PlayerCharacter.getInstance().partyPokemon[var2].getLevel() + 5 >= 50) {
                                        PlayerCharacter.getInstance().partyPokemon[var2]
                                                .addLevels(50 - PlayerCharacter.getInstance().partyPokemon[var2].getLevel());
                                    } else {
                                        PlayerCharacter.getInstance().partyPokemon[var2].addLevels((int) 5);
                                    }

                                    PlayerCharacter.getInstance().partyPokemon[var2].fullRestore();
                                    if (PlayerCharacter.getInstance().partyPokemon[var2].getSkillCount() < 5 && PlayerCharacter.getInstance().partyPokemon[var2]
                                            .getSkillCount() < PlayerCharacter.getInstance().partyPokemon[var2].getLevel() / 10 + 1) {
                                        WorldGameSession.battlePartyBackup.addElement(PlayerCharacter.getInstance().partyPokemon[var2]);
                                        WorldGameSession.evolutionQueue.addElement("" + var2);
                                    }
                                }
                            }

                            if (WorldGameSession.battlePartyBackup.size() <= 0) {
                                WorldGameSession.tutorialStep = 2;
                            } else {
                                WorldGameSession.tutorialStep = 1;
                            }
                            break;
                        case 4:
                            PlayerCharacter.getInstance().addBadges(10);
                    }
                }

                this.setPaymentState((byte) 2);
                return;
            }

            this.setPaymentState((byte) 3);
        }

    }

    private boolean processPaymentByIndex() {
        switch (this.currentPaymentIndex) {
            case 0:
                this.showSmsPaymentScreen(this.currentPaymentIndex);
                break;
            case 1:
                this.showSmsPaymentScreen(this.currentPaymentIndex);
                break;
            case 2:
                this.showSmsPaymentScreen(this.currentPaymentIndex);
                break;
            case 3:
                this.showSmsPaymentScreen(this.currentPaymentIndex);
                break;
            case 4:
                this.showSmsPaymentScreen(this.currentPaymentIndex);
        }

        return true;
    }

    public final boolean initPayment(byte index) {
        this.currentPaymentIndex = index;
        switch (index) {
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

    public final void setPaymentState(byte state) {
        while (true) {
            if (state != 5 && state != 0) {
                this.gameController.aM();
            }

            switch (state) {
                case 1:
                    System.out.println(" " + formatString(513,
                            new int[] { this.totalPaymentCount, this.currentPaymentCount }));
                    this.gameController.showTutorialMessage(formatString(513,
                            new int[] { this.totalPaymentCount, this.currentPaymentCount }));
                    break;
                case 2:
                    if (this.isPaymentComplete()) {
                        if (this.currentPaymentIndex == 0) {
                            this.gameController.showTutorialMessage(getLocalizedText((int) 515) + getLocalizedText((int) 633));
                        } else {
                            this.gameController.showTutorialMessage(getLocalizedText((int) 515));
                        }
                    } else {
                        this.gameController.showTutorialMessage(getLocalizedText((int) 516));
                        System.out.println(" " + getLocalizedText((int) 516));
                    }
                    break;
                case 3:
                    System.out.println(" " + getLocalizedText((int) 516));
                    this.gameController.showTutorialMessage(getLocalizedText((int) 516));
                    break;
                case 4:
                    System.out.println(" " + getLocalizedText((int) 514));
                    this.gameController.showTutorialMessage(getLocalizedText((int) 514));
                    break;
                case 5:
                    gamePaused = false;
                    this.gameController.aN();
            }

            this.paymentType = state;
            if (state != 5) {
                return;
            }

            state = 0;
        }
    }

    public final int getCurrentPaymentIndex() {
        return this.currentPaymentIndex;
    }

    public final boolean isPaymentComplete() {
        return this.currentPaymentCount >= this.totalPaymentCount;
    }

    public final byte getPaymentType() {
        return this.paymentType;
    }

    public final byte getTotalPaymentCount() {
        return this.totalPaymentCount;
    }

    public final void handlePaymentAction(int action) {
        gamePaused = true;
        if (action == 1) {
            this.setPaymentState((byte) 4);
            if (!this.processPaymentByIndex()) {
                this.setPaymentState((byte) 3);
                return;
            }
        } else if (action == 2) {
            this.setPaymentState((byte) 5);
        }

    }

    public final void handlePaymentDialogResponse(int response) {
        switch (this.paymentType) {
            case 1:
                this.handlePaymentAction(response);
                return;
            case 3:
                if (response == 1 || response == 2) {
                    this.setPaymentState((byte) 5);
                }
            case 2:
            case 4:
            default:
        }
    }

    private void showSmsPaymentScreen(int index) {
        SmsConfigLoader.loadSmsConfiguration(GameMIDLet.C15_f217);
        SmsConfigLoader.prepareSmsData(index);
        this.paymentSettings[index][0] = 1;
        this.paymentScreen = new SmsPaymentScreen(GameMIDLet.C15_f217, GameCanvas.publicInstance, "", "",
                this.paymentCodes[index], null, this.paymentSettings[index][0], this.paymentDescriptions[index][0],
                SmsConfigLoader.smsDescriptions[index], "");
        Display.getDisplay(GameMIDLet.C15_f217).setCurrent(this.paymentScreen);
        this.paymentScreen.setPaymentCallback(this);
    }

    public final void onPaymentCompleted(boolean var1) {
        this.onStateChange(var1);
    }
}
