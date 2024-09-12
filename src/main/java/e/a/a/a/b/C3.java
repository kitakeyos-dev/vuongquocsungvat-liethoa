package e.a.a.a.b;

import a.a.C42;
import d.C24;
import d.C34;
import d.C36;
import d.C63;
import d.C71;
import e.a.a.a.a.C19;

import java.io.InputStream;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.MIDlet;

import me.kitakeyos.C50;

public final class C3 extends Canvas implements Runnable {
    private static int C3_f10 = 240;
    private static int C3_f11 = 320;
    private int[][] C3_f12 = new int[4][4];
    private static final String[] C3_f13 = new String[]{"Nokia", "Motorokr", "Motorola", "SonyEricsson", "Samsung", "j2me", "SunMicrosystems_wtk", "MX6", "MotoA668"};
    private String C3_f14 = null;
    private C19 C3_f15;
    private String C3_f16 = null;
    private MIDlet C3_f17 = null;
    private Displayable C3_f18;
    private int C3_f19 = 0;
    private int C3_f20 = 0;
    private String C3_f21 = "Gửi tin";
    private String C3_f22 = "Quay lại";
    private String C3_f23 = null;
    private String C3_f24 = null;
    private String C3_f25 = null;
    private String C3_f26 = null;
    private int C3_f27 = 0;
    private String C3_f28 = null;
    private String C3_f29 = null;
    private String C3_f30 = "Đặt hàng";
    private C14 C3_f31 = null;
    private int C3_f32 = -1;

    public C3(MIDlet var1, Displayable var2, String var3, String var4, String var5, String var6, int var7, String var8, String var9, String var10) {
        this.setFullScreenMode(true);

        try {
            this.C3_f17 = var1;
            this.C3_f18 = var2;
            this.C3_f23 = var3;
            this.C3_f24 = var4;
            if (var5 == null || var5.length() == 0) {
                var5 = "00";
            }

            var6 = "000";
            this.C3_f25 = var5;
            this.C3_f26 = var6;
            this.C3_f27 = var7;
            this.C3_f28 = var8;
            this.C3_f29 = var9;
            this.a(var1);
            C3_f10 = this.getWidth();
            C3_f11 = this.getHeight();
            this.a();
            this.C3_f21 = "Gửi tin";
            this.C3_f22 = "Quay lại";
            C19 var12;
            (var12 = new C19()).c(C50.C50_f754);
            var12.d(C50.C50_f755);
            var12.a(2);
            var12.c(0);
            var12.e("eeee");
            var12.b(1);
            var12.a("");
            var12.b("");
            var12.a(false);
            this.C3_f15 = var12;
            this.b();
        } catch (Exception var11) {
            var11.printStackTrace();
        }
    }

    protected final void sizeChanged(int var1, int var2) {
        super.sizeChanged(var1, var2);
        C3_f10 = this.getWidth();
        C3_f11 = this.getHeight();
        this.a();
    }

    private void a() {
        Font var1;
        int var2 = (var1 = Font.getFont(0, 1, 0)).getHeight() + 4;
        int var3 = C42.a("Gửi tin", var1) + 4;
        this.C3_f12[0][0] = 2;
        this.C3_f12[0][1] = C3_f11 - var2;
        this.C3_f12[0][2] = var3;
        this.C3_f12[0][3] = C3_f11;
        this.C3_f12[1][0] = C3_f10 - var3;
        this.C3_f12[1][1] = C3_f11 - var2;
        this.C3_f12[1][2] = C3_f10;
        this.C3_f12[1][3] = C3_f11;
        var3 <<= 1;
        this.C3_f12[2][0] = C3_f10 / 2 - var3;
        this.C3_f12[2][1] = C3_f11 - var2;
        this.C3_f12[2][2] = C3_f10 / 2;
        this.C3_f12[2][3] = C3_f11;
        this.C3_f12[3][0] = C3_f10 / 2;
        this.C3_f12[3][1] = C3_f11 - var2;
        this.C3_f12[3][2] = C3_f10 / 2 + var3;
        this.C3_f12[3][3] = C3_f11;
    }

    private void a(MIDlet var1) {
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

            for (int var5 = 0; var5 < C3_f13.length; ++var5) {
                var3 = C3_f13[var5].toLowerCase();
                if (var2.length() >= var3.length() && var2.startsWith(var3)) {
                    break;
                }
            }

            this.C3_f14 = var3;
        }

    }

    private void b() {
        if (this.C3_f15.j() == 0) {
            if (this.C3_f15 != null) {
                this.a(this.C3_f29);
            }

        } else {
            this.c();
        }
    }

    private void c() {
        if (this.C3_f15.h()) {
            this.a("Hãy nhấn xác nhận để gửi tin nhắn");
        } else {
            int var1 = this.C3_f15.a();
            if (this.C3_f15.e() != null && this.C3_f15.e().length() != 0) {
                ++var1;
            }

            StringBuffer var2;
            (var2 = new StringBuffer()).append("Bạn đã gửi " + this.C3_f15.j() + " tin nhắn, còn " + (var1 - this.C3_f15.j()) + " tin");
            this.a(var2.toString());
        }
    }

    public final void run() {
        if (this.C3_f15.f() != null && this.C3_f15.e().length() != 0 && this.C3_f15.e() != null && this.C3_f15.e().length() != 0 && (this.C3_f15.k() == 0L || this.C3_f15.h() && this.C3_f15.j() % 2 == 0)) {
            this.C3_f15.a(System.currentTimeMillis());
            this.C3_f15.d(this.C3_f15.j() + 1);
        }

        C63 var1 = new C63();
        C36 var2 = var1.C63_f890.C72_f964;
        try {
            var2.a("ca", new C5(this, 0));
            var2.a("gi", new C5(this, 1));
            var2.a("gs", new C5(this, 2));
            var2.a("gg", new C5(this, 3));
            var2.a("gj", new C5(this, 4));
            var2.a("yc", new C5(this, 5));
            var2.a("m", new C5(this, 6));
            var2.a("n", new C5(this, 7));
            var2.a("as", new C5(this, 8));
            var2.a("_fc", new C5(this, 9));
            var2.a("_fb", new C5(this, 10));
            var2.a("ts", new C5(this, 11));
            var2.a("aa", new C5(this, 12));

        } catch (Exception e) {

        }

        try {
            InputStream var3;
            C71 var5 = C24.a(var3 = this.getClass().getResourceAsStream("/data/event/scene_13.mib"), var1.C63_f890.C72_f964);
            var3.close();
            var1.a(var5, null);
        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    private void a(String var1) {
        this.C3_f19 = 0;
        this.C3_f16 = var1;
        this.repaint();
    }

    private void d() {
        if (this.C3_f31 != null) {
            this.C3_f31.c(this.C3_f15.i() >= this.C3_f27);
        }

    }

    protected final void keyPressed(int var1) {
        int var10000;
        label28:
        {
            if ("Motorola".equals(this.C3_f14)) {
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
                this.h();
                break;
            case -202:
            case -21:
            case -6:
            case 21:
                this.g();
                break;
            case 50:
                this.e();
                break;
            case 56:
                this.f();
                break;
            default:
                System.out.println(var1);
        }

        switch (this.getGameAction(var1)) {
            case 1:
                this.e();
                return;
            case 6:
                this.f();
                return;
            default:
        }
    }

    private boolean a(int var1, int var2, int var3) {
        return var1 > this.C3_f12[var3][0] && var1 < this.C3_f12[var3][2] && var2 > this.C3_f12[var3][1] && var2 < this.C3_f12[var3][3];
    }

    protected final void pointerPressed(int var1, int var2) {
        if (this.a(var1, var2, 0)) {
            this.g();
        } else if (this.a(var1, var2, 1)) {
            this.h();
        } else {
            if (this.a(var1, var2, 2)) {
                if (this.C3_f32 == 0 || this.C3_f32 == 1) {
                    this.e();
                    return;
                }
            } else if (this.a(var1, var2, 3) && (this.C3_f32 == 0 || this.C3_f32 == 2)) {
                this.f();
            }

        }
    }

    protected final void keyRepeated(int var1) {
        super.keyRepeated(var1);
        this.keyPressed(var1);
    }

    private void e() {
        --this.C3_f19;
        if (this.C3_f19 <= 0) {
            this.C3_f19 = 0;
        }

        this.repaint();
    }

    private void f() {
        if (this.C3_f20 > 0) {
            ++this.C3_f19;
        }

        this.repaint();
    }

    private void g() {
        if ("Gửi tin".equals(this.C3_f21) && this.C3_f15.a() > 0) {
            this.C3_f21 = "";
            this.C3_f22 = "";
            (new Thread(this)).start();
        } else {
            if ("Xác nhận".equals(this.C3_f21)) {
                this.C3_f21 = "Gửi tin";
                this.C3_f22 = "Quay lại";
                this.b();
            }

        }
    }

    private void h() {
        System.out.println("payed=" + this.C3_f15.i());
        System.out.println("count=" + this.C3_f27);
        if ("Quay lại".equals(this.C3_f22)) {
            if (this.C3_f15.i() < this.C3_f27) {
                this.C3_f21 = "Xác nhận";
                this.C3_f22 = "Thoát";
                this.a("Bạn chưa trả tiền xong, đề nghị tiếp tục trả tiền.");
            } else {
                this.d();
                Display.getDisplay(this.C3_f17).setCurrent(this.C3_f18);
            }
        } else {
            if ("Thoát".equals(this.C3_f22)) {
                this.d();
                Display.getDisplay(this.C3_f17).setCurrent(this.C3_f18);
            }

        }
    }

    public final void paint(Graphics var1) {
        Font.getDefaultFont();
        Font var2 = Font.getFont(0, 1, 0);
        var1.setColor(4423868);
        var1.fillRect(0, 0, C3_f10, C3_f11);
        var1.setColor(255, 102, 0);
        var1.fillRect(0, 0, C3_f10, 30);
        var1.setColor(16777215);
        int var3 = (30 - var2.getHeight()) / 2;
        var1.drawString(this.C3_f30, 2, var3, 0);
        var1.setFont(var2);
        int var4 = var2.getHeight() + 8;
        int[] var6 = C42.a(var1, this.C3_f16, var2, C3_f10 - 10, C3_f11 - var4 - 10, this.C3_f19, 5, 35, 5);
        this.C3_f20 = var6[1];
        var1.setColor(255, 102, 0);
        var3 = C3_f11 - var4;
        var1.fillRect(0, var3, C3_f10, var4);
        var1.setColor(16777215);
        var3 = C3_f11 - (var4 + var2.getHeight()) / 2;
        var1.drawString(this.C3_f21, 2, var3, 0);
        int var5 = C3_f10 - C42.a(this.C3_f22, var2) - 2 - 2;
        var1.drawString(this.C3_f22, var5, var3, 0);
        var5 = C3_f11 - var4 / 2;
        this.C3_f32 = -1;
        if (this.C3_f19 > 0 && this.C3_f20 > 0) {
            this.C3_f32 = 0;
            var3 = var5 + 5;
            var1.fillTriangle(C3_f10 / 2 - 10 - 3, var3, C3_f10 / 2 - 10 + 3, var3, C3_f10 / 2 - 10, var3 - 11);
            var5 -= 5;
            var1.fillTriangle(C3_f10 / 2 + 10 - 3, var5, C3_f10 / 2 + 10 + 3, var5, C3_f10 / 2 + 10, var5 + 11);
        } else if (this.C3_f19 > 0) {
            this.C3_f32 = 1;
            var5 += 5;
            var1.fillTriangle(C3_f10 / 2 - 10 - 3, var5, C3_f10 / 2 - 10 + 3, var5, C3_f10 / 2 - 10, var5 - 11);
        } else {
            if (this.C3_f20 > 0) {
                this.C3_f32 = 2;
                var5 -= 5;
                var1.fillTriangle(C3_f10 / 2 + 10 - 3, var5, C3_f10 / 2 + 10 + 3, var5, C3_f10 / 2 + 10, var5 + 11);
            }

        }
    }

    public final void a(C14 var1) {
        this.C3_f31 = var1;
    }

    public final int a(int var1, C34 var2, int var3) throws Exception {
        switch (var1) {
            case 0:
                return 0;
            case 1:
                if (var3 == 0) {
                    var2.a(new Integer(this.C3_f15.i()));
                    return 1;
                }

                this.C3_f15.c((Integer) var2.a(0));
                return 0;
            case 2:
                var2.a(new Integer(this.C3_f27));
                return 1;
            case 3:
                var2.a(new Integer(this.C3_f15.d()));
                return 1;
            case 4:
                if (var3 == 0) {
                    var2.a(new Integer(this.C3_f15.j()));
                    return 1;
                }

                this.C3_f15.d((Integer) var2.a(0));
                return 0;
            case 5:
                C42.a(this.C3_f15, this.C3_f23, this.C3_f24, this.C3_f25, this.C3_f26, this.C3_f27);
                return 0;
            case 6:
                this.C3_f21 = (String) var2.a(0);
                return 0;
            case 7:
                this.C3_f22 = (String) var2.a(0);
                return 0;
            case 8:
                this.a((String) var2.a(0));
                return 0;
            case 9:
                var2.a(C50.C50_f754);
                return 1;
            case 10:
                var2.a(C50.C50_f755);
                return 1;
            case 11:
                var2.a(this.C3_f28);
                return 1;
            case 12:
                var2.a(0);
                this.c();
                return 0;
            default:
                return 0;
        }
    }
}
