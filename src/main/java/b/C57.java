package b;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

public final class C57 {
    private C70[] C57_f836;
    private byte C57_f837;
    private Vector C57_f838;
    private byte C57_f839;
    private byte C57_f840;
    private int C57_f841;

    public final void a(DataInputStream var1, byte var2, int var3, String[] var4) {
        this.C57_f837 = var2;
        this.C57_f841 = var3;
        try {
            short var5 = var1.readShort();
            this.C57_f836 = new C70[var5];
            this.C57_f838 = new Vector();

            for (var3 = 0; var3 < var5; ++var3) {
                this.C57_f836[var3] = new C70();
                this.C57_f836[var3].a(var1, var4);
                this.C57_f838.addElement(this.C57_f836[var3]);
            }

            byte var6 = 0;
            this.C57_f840 = var6;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final byte a() {
        return this.C57_f840;
    }

    public final void a(byte var1) {
        this.C57_f840 = var1;
    }

    public final byte b() {
        return this.C57_f837;
    }

    public final void b(byte var1) {
        this.C57_f839 = var1;
    }

    public final C70 c() {
        return this.C57_f839 >= this.C57_f838.size() ? null : (C70) this.C57_f838.elementAt(this.C57_f839);
    }

    public final C70 d() {
        return (C70) this.C57_f838.firstElement();
    }

    public final void e() {
        this.c();
        ++this.C57_f839;
        if (this.C57_f839 >= this.C57_f838.size()) {
            this.C57_f839 = 0;
        }

    }

    public final int[] f() {
        if (this.C57_f841 == -1) {
            return null;
        } else {
            int[] var1;
            (var1 = new int[2])[0] = this.C57_f841 >> 8 & 255;
            var1[1] = this.C57_f841 & 255;
            return var1;
        }
    }
}
