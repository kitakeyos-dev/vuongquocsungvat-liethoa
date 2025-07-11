package e.a.a.a.payment;

import a.a.C42;
import script.*;
import e.a.a.a.config.MessageConfig;
import me.kitakeyos.SmsConfigLoader;
import me.kitakeyos.ManagedInputStream;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;
import java.io.InputStream;

public final class SmsPaymentScreen extends Canvas implements Runnable {
    private static int screenWidth = 240;
    private static int screenHeight = 320;
    private int[][] buttonBounds = new int[4][4];
    private static final String[] SUPPORTED_PLATFORMS = new String[]{"Nokia", "Motorokr", "Motorola", "SonyEricsson", "Samsung", "j2me", "SunMicrosystems_wtk", "MX6", "MotoA668"};
    private String platformName = null;
    private MessageConfig messageConfig;
    private String displayText = null;
    private MIDlet midlet = null;
    private Displayable previousScreen;
    private int scrollPosition = 0;
    private int maxScrollPosition = 0;
    private String leftButtonText = "Gửi tin";
    private String rightButtonText = "Quay lại";
    private String itemName = null;
    private String itemDescription = null;
    private String itemCode = null;
    private String paymentCode = null;
    private int requiredPayments = 0;
    private String additionalData1 = null;
    private String additionalData2 = null;
    private String headerText = "Đặt hàng";
    private PaymentCallback paymentCallback = null;
    private int scrollState = -1;

    public SmsPaymentScreen(MIDlet var1, Displayable var2, String var3, String var4, String var5, String var6, int var7, String var8, String var9, String var10) {
        this.setFullScreenMode(true);

        try {
            this.midlet = var1;
            this.previousScreen = var2;
            this.itemName = var3;
            this.itemDescription = var4;
            if (var5 == null || var5.isEmpty()) {
                var5 = "00";
            }

            this.itemCode = var5;
            this.paymentCode = "000";
            this.requiredPayments = var7;
            this.additionalData1 = var8;
            this.additionalData2 = var9;
            this.detectPlatform(var1);
            screenWidth = this.getWidth();
            screenHeight = this.getHeight();
            this.calculateButtonBounds();
            this.leftButtonText = "Gửi tin";
            this.rightButtonText = "Quay lại";
            MessageConfig var12 = new MessageConfig();
            var12.setPaidNumber(SmsConfigLoader.currentSmsNumber);
            var12.setPaidContent(SmsConfigLoader.currentSmsContent);
            var12.setPaidPrice(2);
            var12.setCounter1(0);
            var12.setPaidReminder("eeee");
            var12.setPaidCondition(1);
            var12.setFreeNumber(var6);
            var12.setFreeContent(var10);
            var12.setSomeFlag(false);
            this.messageConfig = var12;
            this.initializePaymentFlow();
        } catch (Exception var11) {
            var11.printStackTrace();
        }
    }

    protected final void sizeChanged(int var1, int var2) {
        super.sizeChanged(var1, var2);
        screenWidth = this.getWidth();
        screenHeight = this.getHeight();
        this.calculateButtonBounds();
    }

    private void calculateButtonBounds() {
        Font var1 = Font.getFont(0, 1, 0);
        int var2 = var1.getHeight() + 4;
        int var3 = C42.a("Gửi tin", var1) + 4;
        this.buttonBounds[0][0] = 2;
        this.buttonBounds[0][1] = screenHeight - var2;
        this.buttonBounds[0][2] = var3;
        this.buttonBounds[0][3] = screenHeight;
        this.buttonBounds[1][0] = screenWidth - var3;
        this.buttonBounds[1][1] = screenHeight - var2;
        this.buttonBounds[1][2] = screenWidth;
        this.buttonBounds[1][3] = screenHeight;
        var3 <<= 1;
        this.buttonBounds[2][0] = screenWidth / 2 - var3;
        this.buttonBounds[2][1] = screenHeight - var2;
        this.buttonBounds[2][2] = screenWidth / 2;
        this.buttonBounds[2][3] = screenHeight;
        this.buttonBounds[3][0] = screenWidth / 2;
        this.buttonBounds[3][1] = screenHeight - var2;
        this.buttonBounds[3][2] = screenWidth / 2 + var3;
        this.buttonBounds[3][3] = screenHeight;
    }

    private void detectPlatform(MIDlet var1) {
        String var2 = null;

        try {
            if ((var2 = var1.getAppProperty("Platform")) == null) {
                var2 = System.getProperty("microedition.platform");
            }
        } catch (Exception var4) {
        }

        if (var2 != null) {
            var2 = var2.toLowerCase();
            String var3 = null;

            for (int var5 = 0; var5 < SUPPORTED_PLATFORMS.length; ++var5) {
                var3 = SUPPORTED_PLATFORMS[var5].toLowerCase();
                if (var2.length() >= var3.length() && var2.startsWith(var3)) {
                    break;
                }
            }

            this.platformName = var3;
        }

    }

    private void initializePaymentFlow() {
        if (this.messageConfig.getCounter2() == 0) {
            this.showMessage(this.additionalData2);

        } else {
            this.updatePaymentStatus();
        }
    }

    private void updatePaymentStatus() {
        if (this.messageConfig.getSomeFlag()) {
            this.showMessage("Hãy nhấn xác nhận để gửi tin nhắn");
        } else {
            int var1 = this.messageConfig.getPaidCondition();
            if (this.messageConfig.getFreeContent() != null && !this.messageConfig.getFreeContent().isEmpty()) {
                ++var1;
            }

            this.showMessage("Bạn đã gửi " + this.messageConfig.getCounter2() + " tin nhắn, còn " + (var1 - this.messageConfig.getCounter2()) + " tin");
        }
    }

    public void run() {
        if (this.messageConfig.getFreeNumber() != null && !this.messageConfig.getFreeContent().isEmpty() && (this.messageConfig.getTimestamp() == 0L || this.messageConfig.getSomeFlag() && this.messageConfig.getCounter2() % 2 == 0)) {
            this.messageConfig.setTimestamp(System.currentTimeMillis());
            this.messageConfig.setCounter2(this.messageConfig.getCounter2() + 1);
        }

        ScriptVM vm = new ScriptVM();
        CommandRegistry registry = vm.scriptEngine.commandRegistry;
        try {
            registry.put("ca", new PaymentScriptHandler(this, 0));
            registry.put("gi", new PaymentScriptHandler(this, 1));
            registry.put("gs", new PaymentScriptHandler(this, 2));
            registry.put("gg", new PaymentScriptHandler(this, 3));
            registry.put("gj", new PaymentScriptHandler(this, 4));
            registry.put("yc", new PaymentScriptHandler(this, 5));
            registry.put("m", new PaymentScriptHandler(this, 6));
            registry.put("n", new PaymentScriptHandler(this, 7));
            registry.put("as", new PaymentScriptHandler(this, 8));
            registry.put("_fc", new PaymentScriptHandler(this, 9));
            registry.put("_fb", new PaymentScriptHandler(this, 10));
            registry.put("ts", new PaymentScriptHandler(this, 11));
            registry.put("aa", new PaymentScriptHandler(this, 12));

        } catch (Exception e) {

        }

        try {
            InputStream inputStream = ManagedInputStream.openStream("/data/event/scene_13.mib");
            ScriptFunction scriptFunction = FunctionData.loadBytecode(inputStream, vm.scriptEngine.commandRegistry);
            inputStream.close();
            vm.executeScript(scriptFunction, null);
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    private void showMessage(String var1) {
        this.scrollPosition = 0;
        this.displayText = var1;
        this.repaint();
    }

    private void notifyPaymentComplete() {
        if (this.paymentCallback != null) {
            this.paymentCallback.onPaymentCompleted(this.messageConfig.getCounter1() >= this.requiredPayments);
        }

    }

    protected final void keyPressed(int var1) {
        int var10000;
        label28:
        {
            if ("Motorola".equals(this.platformName)) {
                switch (var1) {
                    case -22:
                    case 22:
                        var10000 = -7;
                        break label28;
                    case -21:
                    case 21:
                        var10000 = -6;
                        break label28;
                    case -6:
                        var10000 = 56;
                        break label28;
                }
            }

            var10000 = var1;
        }

        var1 = var10000;
        switch (var10000) {
            case -203:
            case -22:
            case -7:
            case 22:
                this.handleRightButton();
                break;
            case -202:
            case -21:
            case -6:
            case 21:
                this.handleLeftButton();
                break;
            case 50:
                this.scrollUp();
                break;
            case 56:
                this.scrollDown();
                break;
            default:
                System.out.println(var1);
        }

        switch (this.getGameAction(var1)) {
            case 1:
                this.scrollUp();
                return;
            case 6:
                this.scrollDown();
                return;
            default:
        }
    }

    private boolean isPointInButton(int var1, int var2, int var3) {
        return var1 > this.buttonBounds[var3][0] && var1 < this.buttonBounds[var3][2] && var2 > this.buttonBounds[var3][1] && var2 < this.buttonBounds[var3][3];
    }

    protected final void pointerPressed(int var1, int var2) {
        if (this.isPointInButton(var1, var2, 0)) {
            this.handleLeftButton();
        } else if (this.isPointInButton(var1, var2, 1)) {
            this.handleRightButton();
        } else {
            if (this.isPointInButton(var1, var2, 2)) {
                if (this.scrollState == 0 || this.scrollState == 1) {
                    this.scrollUp();
                    return;
                }
            } else if (this.isPointInButton(var1, var2, 3) && (this.scrollState == 0 || this.scrollState == 2)) {
                this.scrollDown();
            }

        }
    }

    protected final void keyRepeated(int var1) {
        super.keyRepeated(var1);
        this.keyPressed(var1);
    }

    private void scrollUp() {
        --this.scrollPosition;
        if (this.scrollPosition <= 0) {
            this.scrollPosition = 0;
        }

        this.repaint();
    }

    private void scrollDown() {
        if (this.maxScrollPosition > 0) {
            ++this.scrollPosition;
        }

        this.repaint();
    }

    private void handleLeftButton() {
        if ("Gửi tin".equals(this.leftButtonText) && this.messageConfig.getPaidCondition() > 0) {
            this.leftButtonText = "";
            this.rightButtonText = "";
            (new Thread(this)).start();
        } else {
            if ("Xác nhận".equals(this.leftButtonText)) {
                this.leftButtonText = "Gửi tin";
                this.rightButtonText = "Quay lại";
                this.initializePaymentFlow();
            }

        }
    }

    private void handleRightButton() {
        System.out.println("payed=" + this.messageConfig.getCounter1());
        System.out.println("count=" + this.requiredPayments);
        if ("Quay lại".equals(this.rightButtonText)) {
            if (this.messageConfig.getCounter1() < this.requiredPayments) {
                this.leftButtonText = "Xác nhận";
                this.rightButtonText = "Thoát";
                this.showMessage("Bạn chưa trả tiền xong, đề nghị tiếp tục trả tiền.");
            } else {
                this.notifyPaymentComplete();
                Display.getDisplay(this.midlet).setCurrent(this.previousScreen);
            }
        } else {
            if ("Thoát".equals(this.rightButtonText)) {
                this.notifyPaymentComplete();
                Display.getDisplay(this.midlet).setCurrent(this.previousScreen);
            }

        }
    }

    public final void paint(Graphics var1) {
        Font.getDefaultFont();
        Font var2 = Font.getFont(0, 1, 0);
        var1.setColor(4423868);
        var1.fillRect(0, 0, screenWidth, screenHeight);
        var1.setColor(255, 102, 0);
        var1.fillRect(0, 0, screenWidth, 30);
        var1.setColor(16777215);
        int var3 = (30 - var2.getHeight()) / 2;
        var1.drawString(this.headerText, 2, var3, 0);
        var1.setFont(var2);
        int var4 = var2.getHeight() + 8;
        int[] var6 = C42.a(var1, this.displayText, var2, screenWidth - 10, screenHeight - var4 - 10, this.scrollPosition, 5, 35, 5);
        this.maxScrollPosition = var6[1];
        var1.setColor(255, 102, 0);
        var3 = screenHeight - var4;
        var1.fillRect(0, var3, screenWidth, var4);
        var1.setColor(16777215);
        var3 = screenHeight - (var4 + var2.getHeight()) / 2;
        var1.drawString(this.leftButtonText, 2, var3, 0);
        int var5 = screenWidth - C42.a(this.rightButtonText, var2) - 2 - 2;
        var1.drawString(this.rightButtonText, var5, var3, 0);
        var5 = screenHeight - var4 / 2;
        this.scrollState = -1;
        if (this.scrollPosition > 0 && this.maxScrollPosition > 0) {
            this.scrollState = 0;
            var3 = var5 + 5;
            var1.fillTriangle(screenWidth / 2 - 10 - 3, var3, screenWidth / 2 - 10 + 3, var3, screenWidth / 2 - 10, var3 - 11);
            var5 -= 5;
            var1.fillTriangle(screenWidth / 2 + 10 - 3, var5, screenWidth / 2 + 10 + 3, var5, screenWidth / 2 + 10, var5 + 11);
        } else if (this.scrollPosition > 0) {
            this.scrollState = 1;
            var5 += 5;
            var1.fillTriangle(screenWidth / 2 - 10 - 3, var5, screenWidth / 2 - 10 + 3, var5, screenWidth / 2 - 10, var5 - 11);
        } else {
            if (this.maxScrollPosition > 0) {
                this.scrollState = 2;
                var5 -= 5;
                var1.fillTriangle(screenWidth / 2 + 10 - 3, var5, screenWidth / 2 + 10 + 3, var5, screenWidth / 2 + 10, var5 + 11);
            }

        }
    }

    public final void setPaymentCallback(PaymentCallback var1) {
        this.paymentCallback = var1;
    }

    public final int handleScriptCall(int var1, ScriptContext context, int var3) throws Exception {
        switch (var1) {
            case 0:
                return 0;
            case 1:
                if (var3 == 0) {
                    context.pushValue(this.messageConfig.getCounter1());
                    return 1;
                }

                this.messageConfig.setCounter1((Integer) context.getStackValue(0));
                return 0;
            case 2:
                context.pushValue(this.requiredPayments);
                return 1;
            case 3:
                context.pushValue(this.messageConfig.getPaidPrice());
                return 1;
            case 4:
                if (var3 == 0) {
                    context.pushValue(this.messageConfig.getCounter2());
                    return 1;
                }

                this.messageConfig.setCounter2((Integer) context.getStackValue(0));
                return 0;
            case 5:
                C42.a(this.messageConfig, this.itemName, this.itemDescription, this.itemCode, this.paymentCode, this.requiredPayments);
                return 0;
            case 6:
                this.leftButtonText = (String) context.getStackValue(0);
                return 0;
            case 7:
                this.rightButtonText = (String) context.getStackValue(0);
                return 0;
            case 8:
                this.showMessage((String) context.getStackValue(0));
                return 0;
            case 9:
                context.pushValue(SmsConfigLoader.currentSmsNumber);
                return 1;
            case 10:
                context.pushValue(SmsConfigLoader.currentSmsContent);
                return 1;
            case 11:
                context.pushValue(this.additionalData1);
                return 1;
            case 12:
                context.getStackValue(0);
                this.updatePaymentStatus();
                return 0;
            default:
                return 0;
        }
    }
}
