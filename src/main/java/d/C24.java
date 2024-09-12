package d;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class C24 {
    public int[] C24_f274;
    public Object[] C24_f275;
    public C24[] C24_f276;
    public int C24_f277;
    public boolean C24_f278;
    private String C24_f279;
    public int C24_f280;
    public int C24_f281;


    private C24(DataInputStream var1, String var2) {
        this.C24_f279 = a(var1);
        try {
            var1.readInt();
            var1.readInt();
            this.C24_f280 = var1.read();
            this.C24_f277 = var1.read();
            int var6 = var1.read();
            this.C24_f278 = (var6 & 2) != 0;
            this.C24_f281 = var1.read();
            var6 = var1.readInt();
            this.C24_f274 = new int[var6];

            int var3;
            for (var3 = 0; var3 < var6; ++var3) {
                this.C24_f274[var3] = var1.readInt();
            }

            var3 = var1.readInt();
            this.C24_f275 = new Object[var3];

            for (var6 = 0; var6 < var3; ++var6) {
                Object var4 = null;
                int var5;
                switch (var5 = var1.read()) {
                    case 0:
                        break;
                    case 1:
                        var4 = var1.read() == 0 ? Boolean.FALSE : Boolean.TRUE;
                        break;
                    case 2:
                    default:
                        throw new IOException("unknown constant type: " + var5);
                    case 3:
                        var4 = C63.a(var1.readInt());
                        break;
                    case 4:
                        var4 = a(var1);
                }

                this.C24_f275[var6] = var4;
            }

            var6 = var1.readInt();
            this.C24_f276 = new C24[var6];

            for (int var7 = 0; var7 < var6; ++var7) {
                this.C24_f276[var7] = new C24(var1, this.C24_f279);
            }

            var1.readInt();
            var1.readInt();
            var1.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final String toString() {
        return this.C24_f279;
    }

    private static String a(DataInputStream var0) {
        try {
            short var1;
            if ((var1 = var0.readShort()) == 0) {
                return "";
            } else {
                a(var1 < 65536, "Too long str: " + var1);
                byte[] var2 = new byte[var1];
                int var3 = 0;
                int var4 = var1;

                for (int var5 = 0; var5 < 100 && var4 > 0; ++var5) {
                    int var6 = var0.read(var2, var3, var4);
                    var3 += var6;
                    var4 -= var6;
                }

                a(var4 == 0, "strload");
                return new String(var2, "utf-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void a(boolean var0, String var1) throws IOException {
        if (!var0) {
            throw new IOException("Couldn't load bytecode:" + var1);
        }
    }

    public static C71 a(InputStream var0, C36 var1) {
        if (!(var0 instanceof DataInputStream)) {
            var0 = new DataInputStream(var0);
        }

        DataInputStream var2 = null;
        try {
            a((var2 = (DataInputStream) var0).read() == 27, "Signature 1");
            var2.read();
            var2.read();
            var2.read();
            var2.read();
            var2.read();
            var2.read();
            var2.read();
            var2.read();
            var2.read();
            var2.read();
            var2.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        C24 var3 = new C24(var2, null);
        return new C71(var3, var1);
    }
}
