// Decompiled with: Procyon 0.6.0
// Class Version: 3
package a.b;

public final class C6 extends C60 {
    private static C6 C6_f45;
    public byte C6_f46;
    private int C6_f47;
    private int C6_f48;
    private int C6_f49;
    private boolean C6_f50;

    public C6() {
        this.C6_f50 = true;
        this.C6_f46 = 0;
    }

    public static C6 a() {
        if (C6.C6_f45 == null) {
            C6.C6_f45 = new C6();
        }
        return C6.C6_f45;
    }

    public void a(final int c6_f49) {
        this.C6_f49 = c6_f49;
    }

    public void a(final boolean b) {
        this.C6_f50 = false;
    }

    public void a(final int c60_f861, int n, int c60_f862, final int n2, final boolean c6_f50) {
        this.C6_f46 = 0;
        this.C6_f50 = c6_f50;
        if (this.C6_f50) {
            this.C60_f861 = c60_f861;
            this.C60_f862 = n;
            return;
        }
        c60_f862 = n;
        n = c60_f861;
        super.C60_f869 = n;
        super.C60_f870 = c60_f862;
    }

    public void a(final C60 c60_f868, final int n, final int n2, final boolean c6_f50) {
        this.C6_f46 = 1;
        super.C60_f868 = c60_f868;
        this.C6_f50 = c6_f50;
        if (this.C6_f50) {
            this.C60_f861 = this.C60_f868.C60_f861;
            this.C60_f862 = this.C60_f868.C60_f862;
        }
    }

    public boolean b() {
        return this.C6_f50;
    }

    public void c() {
        switch (this.C6_f46) {
            case 0: {
                if (!this.C6_f50 && this.a(this.C6_f49, this.C60_f869, this.C60_f870)) {
                    this.C6_f50 = true;
                    return;
                }
                break;
            }
            case 1: {
                if (this.C6_f50) {
                    this.C60_f861 = this.C60_f868.C60_f861;
                    this.C60_f862 = this.C60_f868.C60_f862;
                    return;
                }
                if (this.a(this.C6_f49, this.C60_f868.C60_f861, this.C60_f868.C60_f862)) {
                    this.C6_f50 = true;
                    return;
                }
                break;
            }
            case 2: {
                if (this.C6_f50) {
                    return;
                }
                if (this.C6_f47 > 0) {
                    --this.C6_f47;
                    return;
                }
                final int c60_f861 = this.C60_f861;
                if (c60_f861 == 0) {
                    final int c60_f862 = this.C60_f862;
                    if (c60_f862 == 0) {
                        if (this.C6_f48 < 0) {
                            ++this.C6_f48;
                            this.C6_f47 = 0;
                            return;
                        }
                        this.C6_f50 = true;
                        return;
                    }
                }
                this.a(0, 0, 0);
                break;
            }
        }
    }
}
