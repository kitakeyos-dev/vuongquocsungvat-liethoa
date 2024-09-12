package b;

import java.io.DataInputStream;
import java.io.IOException;

public final class C70 {
    private short C70_f958;
    private short[] C70_f959;
    private String[] C70_f960;

    public final void a(DataInputStream var1, String[] var2) {
        try {
            this.C70_f958 = var1.readShort();
            byte var3 = var1.readByte();
            byte var4 = var1.readByte();
            this.C70_f959 = new short[var4];

            int var5;
            for (var5 = 0; var5 < var4; ++var5) {
                this.C70_f959[var5] = var1.readShort();
            }

            if (var2 != null) {
                this.C70_f960 = new String[var3 - var4];

                for (var5 = 0; var5 < var3 - var4; ++var5) {
                    short var6 = var1.readShort();
                    this.C70_f960[var5] = var2[var6];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final short a() {
        return this.C70_f958;
    }

    public final short[] b() {
        return this.C70_f959;
    }

    public final String[] c() {
        return this.C70_f960;
    }
}
