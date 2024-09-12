package c;

import a.C26;

import javax.microedition.lcdui.Graphics;

public final class C58 {
    private C23[] C58_f842 = new C23[200];
    private int C58_f843 = 0;
    private int C58_f844 = -1;
    private C43 C58_f845;
    private int[] C58_f846 = new int[]{0, 1, 2, 3, 5, 6, 7, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private int[] C58_f847 = null;
    private int[] C58_f848 = null;
    private C31 C58_f849;
    private C61 C58_f850;

    public C58(C61 var1) {
        this.C58_f850 = var1;
        this.d();
    }

    private void d() {
        this.C58_f844 = -1;
        this.C58_f845 = new C43();
        C43 var10000 = this.C58_f845;
        this.C58_f845.a(0);
        this.C58_f845.c(-1);
        int[] var10001 = this.C58_f847;
        this.C58_f847 = C26.c(100);
        this.f();
    }

    public final C43 a() {
        return this.C58_f845;
    }

    public final void a(C31 var1) {
        this.C58_f849 = var1;
    }

    public final void a(String var1, int var2, boolean var3) {
        this.d();
        byte[] var6;
        C26.b(var6 = new byte[20000], var1, 0);
        int[] var5 = new int[]{0};
        this.C58_f844 = C26.d(var6, var5);
        this.C58_f844 = 0;
        C26.d(var6, var5);
        byte var4 = C26.c(var6, var5);
        this.C58_f845.b(var4);
        short var7 = C26.d(var6, var5);
        this.C58_f845.a(var7);
        var7 = C26.d(var6, var5);
        this.C58_f845.a(var7, this.C58_f845);
        var7 = C26.d(var6, var5);
        this.C58_f845.b(var7, this.C58_f845);
        var7 = C26.d(var6, var5);
        this.C58_f845.c(var7, this.C58_f845);
        var7 = C26.d(var6, var5);
        this.C58_f845.d(var7, this.C58_f845);
        this.C58_f842[this.C58_f843] = this.C58_f845;
        this.C58_f843 = 1;
        this.a(var6, var5, this.C58_f845, var2, false);
        this.C58_f847 = C26.c(50);
        this.a(this.C58_f845, -1);
    }

    private void a(byte[] var1, int[] var2, C43 var3, int var4, boolean var5) {
        byte var6 = C26.c(var1, var2);
        int var8;
        if (var6 > 0) {
            byte[][] var7 = new byte[var6][4];

            for (var8 = 0; var8 < var6; ++var8) {
                var7[var8][0] = C26.c(var1, var2);
                var7[var8][1] = C26.c(var1, var2);
                var7[var8][2] = C26.c(var1, var2);
                var7[var8][3] = C26.c(var1, var2);
            }

            var3.a(var7);
        }

        byte var19 = C26.c(var1, var2);

        short var11;
        short var12;
        int var14;
        int var15;
        int var26;
        short var32;
        for (var8 = 0; var8 < var19; ++var8) {
            var6 = C26.c(var1, var2);
            C38 var9;
            (var9 = new C38(var6)).C38_f581 = C26.c(var1, var2) != 0;
            var9.C38_f577 = C26.d(var1, var2);
            var9.C38_f574 = C26.d(var1, var2);
            var9.C38_f582 = C26.c(var1, var2);
            var9.C38_f583 = C26.c(var1, var2);

            int var10;
            for (var10 = 0; var10 < var9.C38_f574; ++var10) {
                var11 = C26.d(var1, var2);
                var9.C38_f575[var10] = var11;
                byte[] var13 = new byte[var12 = C26.d(var1, var2)];

                for (var14 = 0; var14 < var12; ++var14) {
                    var13[var14] = C26.c(var1, var2);
                }

                var9.C38_f592.addElement(C26.a(var13));
            }

            var9.C38_f576 = new int[var9.C38_f577 + this.C58_f846.length][][];

            for (var10 = 0; var10 < var9.C38_f576.length; ++var10) {
                var9.C38_f576[var10] = new int[0][];
            }

            short var25 = C26.d(var1, var2);

            for (var26 = 0; var26 < var25; ++var26) {
                var12 = C26.d(var1, var2);
                int[][] var36 = new int[var32 = C26.d(var1, var2)][];

                for (var15 = 0; var15 < var32; ++var15) {
                    var36[var15] = new int[5];
                    var36[var15][0] = C26.d(var1, var2);
                    var36[var15][1] = C26.d(var1, var2);
                    var36[var15][2] = C26.d(var1, var2);
                    var36[var15][3] = C26.d(var1, var2);
                    var36[var15][4] = C26.d(var1, var2);
                }

                var9.C38_f576[var12] = var36;
            }

            if (var6 == 0) {
                var3.C43_f689 = var9;
            } else {
                var3.C43_f690 = var9;
            }
        }

        short var21 = C26.d(var1, var2);

        for (int var18 = 0; var18 < var21; ++var18) {
            byte var22;
            if ((var22 = C26.c(var1, var2)) == 0) {
                C43 var29;
                (var29 = new C43()).b(var22);
                var29.a(C26.d(var1, var2));
                var29.a(C26.d(var1, var2), this.a());
                var29.b(C26.d(var1, var2), this.a());
                var29.c(C26.d(var1, var2), this.a());
                var29.d(C26.d(var1, var2), this.a());
                var29.c(var3.b());
                if (var3.C43_f689 != null) {
                    for (var26 = 0; var26 < var3.C43_f689.C38_f575.length; ++var26) {
                        if (var3.C43_f689.C38_f575[var26] == var29.b()) {
                            var29.a(var3.C43_f689);
                            break;
                        }
                    }
                }

                if (var3.C43_f690 != null) {
                    for (var26 = 0; var26 < var3.C43_f690.C38_f575.length; ++var26) {
                        if (var3.C43_f690.C38_f575[var26] == var29.b()) {
                            var29.a(var3.C43_f690);
                            break;
                        }
                    }
                }

                this.C58_f842[this.C58_f843] = var29;
                ++this.C58_f843;
                var3.h()[var18] = var29;
                this.a(var1, var2, (C43) var3.h()[var18], var4, var5);
            } else if (var22 == 1) {
                C40 var28;
                (var28 = new C40()).b(var22);
                var28.a(C26.d(var1, var2));
                var28.a(C26.d(var1, var2), this.a());
                var28.b(C26.d(var1, var2), this.a());
                var28.c(C26.d(var1, var2), this.a());
                var28.d(C26.d(var1, var2), this.a());
                var28.a();
                var28.C40_f636 = this.C58_f849;
                byte[] var33 = new byte[var11 = C26.d(var1, var2)];

                for (int var35 = 0; var35 < var11; ++var35) {
                    var33[var35] = C26.c(var1, var2);
                }

                var28.i().C12_f179 = C26.a(var33);
                var28.i().C12_f182 = C26.c(var1, var2);
                var28.i().C12_f183 = C26.c(var1, var2);
                var28.i().C12_f184 = C26.c(var1, var2) != 0;
                var28.i().C12_f185 = C26.e(var1, var2);
                var28.i().C12_f186 = C26.e(var1, var2);
                var28.i().C12_f187 = C26.e(var1, var2);
                var32 = C26.d(var1, var2);
                byte var39 = C26.c(var1, var2);
                if (var32 < 0) {
                    var28.i().C12_f191 = null;
                } else {
                    var28.i().C12_f191 = new C17();
                    var28.i().C12_f191.C17_f222 = var39;
                    var28.i().C12_f191.a((int) var32);
                }

                var28.i().C12_f192 = C26.e(var1, var2);
                var28.i().C12_f193 = C26.e(var1, var2);
                var28.i().C12_f194 = C26.e(var1, var2);
                short var38 = C26.d(var1, var2);
                var19 = C26.c(var1, var2);
                if (var38 < 0) {
                    var28.i().C12_f195 = null;
                } else {
                    var28.i().C12_f195 = new C17();
                    var28.i().C12_f195.a((int) var38);
                    var28.i().C12_f195.C17_f222 = var19;
                }

                var28.i().C12_f190 = C26.c(var1, var2);
                if (var28.i().C12_f191 != null) {
                    var28.i().C12_f191.a(var4, var5, var28.i().C12_f190);
                }

                if (var28.i().C12_f195 != null) {
                    var28.i().C12_f195.a(var4, var5, var28.i().C12_f190);
                }

                var28.c(var3.b());
                int var24;
                if (var3.C43_f689 != null) {
                    for (var24 = 0; var24 < var3.C43_f689.C38_f575.length; ++var24) {
                        if (var3.C43_f689.C38_f575[var24] == var28.b()) {
                            var28.a(var3.C43_f689);
                            break;
                        }
                    }
                }

                if (var3.C43_f690 != null) {
                    for (var24 = 0; var24 < var3.C43_f690.C38_f575.length; ++var24) {
                        if (var3.C43_f690.C38_f575[var24] == var28.b()) {
                            var28.a(var3.C43_f690);
                            break;
                        }
                    }
                }

                this.C58_f842[this.C58_f843] = var28;
                ++this.C58_f843;
                var3.h()[var18] = var28;
                var28.C40_f634 = C26.c(var1, var2);
                var28.C40_f635 = C26.c(var1, var2);
            } else if (var22 == 2) {
                C66 var27;
                (var27 = new C66()).q(C26.d(var1, var2));
                var27.a(C26.d(var1, var2), this.a());
                var27.b(C26.d(var1, var2), this.a());
                var27.a((int) C26.c(var1, var2));
                var27.b(C26.c(var1, var2));
                var27.c(C26.c(var1, var2));
                var27.d(C26.c(var1, var2));
                var27.e(C26.c(var1, var2));
                var27.f(C26.c(var1, var2));
                var27.g(C26.c(var1, var2));
                var27.h(C26.c(var1, var2));
                var27.k(C26.c(var1, var2));
                var27.l(C26.c(var1, var2));
                var27.m(C26.c(var1, var2));
                var27.n(C26.c(var1, var2));
                var27.o(C26.c(var1, var2));
                var27.p(C26.c(var1, var2));
                var27.i(C26.c(var1, var2));
                var27.j(C26.c(var1, var2));
                var27.C66_f912 = C26.e(var1, var2);
                var11 = C26.d(var1, var2);
                byte var30 = C26.c(var1, var2);
                if (var11 < 0) {
                    var27.C66_f913 = null;
                } else {
                    var27.C66_f913 = new C17();
                    var27.C66_f913.a((int) var11);
                    var27.C66_f913.C17_f222 = var30;
                    var27.C66_f913.a(var4, var5, var30);
                }

                var11 = C26.d(var1, var2);
                var30 = C26.c(var1, var2);
                if (var11 < 0) {
                    var27.C66_f914 = null;
                } else {
                    var27.C66_f914 = new C17();
                    var27.C66_f914.a((int) var11);
                    var27.C66_f914.C17_f222 = var30;
                    var27.C66_f914.a(var4, var5, var30);
                }

                var27.r(C26.d(var1, var2));
                byte var34;
                if ((var34 = C26.c(var1, var2)) == 0) {
                    var27.C66_f920 = var27.n();
                } else if (var34 == 1) {
                    var27.C66_f920 = C66.a(var27.a(), var27.m());
                    short var37 = C26.d(var1, var2);

                    for (var15 = 0; var15 < var37; ++var15) {
                        short var20 = C26.d(var1, var2);
                        short var23 = C26.d(var1, var2);
                        byte var31 = C26.c(var1, var2);
                        var12 = C26.d(var1, var2);
                        var32 = C26.d(var1, var2);
                        short var16 = C26.d(var1, var2);
                        short var17 = C26.d(var1, var2);
                        var27.C66_f920[var20] = new C49(var23, var31, var12, var32, var16, var17);
                    }
                }

                var27.s(var3.b());
                C38 var10000;
                if (var3.C43_f689 != null) {
                    for (var14 = 0; var14 < var3.C43_f689.C38_f575.length; ++var14) {
                        if (var3.C43_f689.C38_f575[var14] == var27.b()) {
                            var10000 = var3.C43_f689;
                            break;
                        }
                    }
                }

                if (var3.C43_f690 != null) {
                    for (var14 = 0; var14 < var3.C43_f690.C38_f575.length; ++var14) {
                        if (var3.C43_f690.C38_f575[var14] == var27.b()) {
                            var10000 = var3.C43_f690;
                            break;
                        }
                    }
                }

                this.C58_f842[this.C58_f843] = var27;
                ++this.C58_f843;
                var3.h()[var18] = var27;
            }
        }

    }

    private C35 a(C23 var1) {
        if (var1.j() != 0) {
            return new C35(var1.c(), var1.d(), var1.e(), var1.f());
        } else {
            C43 var2;
            if ((var2 = (C43) var1).h() != null && var2.h()[0] != null) {
                int var8 = this.a(var2.h()[0]).C35_f557;
                int var3 = this.a(var2.h()[0]).C35_f557 + this.a(var2.h()[0]).C35_f559;
                int var4 = this.a(var2.h()[0]).C35_f558;
                int var5 = this.a(var2.h()[0]).C35_f558 + this.a(var2.h()[0]).C35_f560;

                for (int var6 = 0; var6 < var2.h().length && var2.h()[var6] != null; ++var6) {
                    C35 var7 = this.a(var2.h()[var6]);
                    if (var8 > var7.C35_f557) {
                        var8 = var7.C35_f557;
                    }

                    if (var3 < var7.C35_f557 + var7.C35_f559) {
                        var3 = var7.C35_f557 + var7.C35_f559;
                    }

                    if (var4 > var7.C35_f558) {
                        var4 = var7.C35_f558;
                    }

                    if (var5 < var7.C35_f558 + var7.C35_f560) {
                        var5 = var7.C35_f558 + var7.C35_f560;
                    }
                }

                return new C35(var8, var4, var3 - var8, var5 - var4);
            } else {
                return new C35(var1.c(), var1.d(), var1.e(), var1.f());
            }
        }
    }

    public final void a(Graphics var1) {
        Graphics var2;
        C43 var3;
        C58 var15;
        C38 var10000;
        label96:
        {
            var3 = this.C58_f845;
            var2 = var1;
            var15 = this;
            if (var3.C43_f689 != null || var3.C43_f690 != null) {
                if (var3.C43_f689 == null && var3.C43_f690 != null) {
                    var10000 = var3.C43_f690;
                    break label96;
                }

                if (var3.C43_f689 != null && var3.C43_f690 == null) {
                    var10000 = var3.C43_f689;
                    break label96;
                }
            }

            var10000 = null;
        }

        C38 var6 = var10000;
        if (var10000 != null && var6.C38_f577 < var6.C38_f574) {
            int var20 = var6.C38_f587;
            C58 var8;
            C58 var21 = var8 = this;
            C38 var4 = var6;
            C58 var7 = var21;
            int var9 = 0;
            int var10 = 0;
            int var11 = 0;
            int var12 = 0;
            if (var6.C38_f575[0] != -1) {
                C35 var13;
                var9 = (var13 = var7.a(var8.a(var6.C38_f575[0]))).C35_f557;
                var10 = var13.C35_f557 + var13.C35_f559;
                var11 = var13.C35_f558;
                var12 = var13.C35_f558 + var13.C35_f560;

                for (int var14 = 1; var14 != var4.C38_f577; ++var14) {
                    var13 = var7.a(var8.a(var4.C38_f575[var14]));
                    if (var9 > var13.C35_f557) {
                        var9 = var13.C35_f557;
                    }

                    if (var10 < var13.C35_f557 + var13.C35_f559) {
                        var10 = var13.C35_f557 + var13.C35_f559;
                    }

                    if (var11 > var13.C35_f558) {
                        var11 = var13.C35_f558;
                    }

                    if (var12 < var13.C35_f558 + var13.C35_f560) {
                        var12 = var13.C35_f558 + var13.C35_f560;
                    }
                }
            }

            new C35(var9, var11, var10 - var9, var12 - var11);
            C35 var16 = new C35();
            C35 var19 = new C35();
            if (var6.C38_f584 != 0) {
                var20 = var6.C38_f584;
            }

            var1.setColor(255, 255, 255);
            var1.fillRect(var16.C35_f557, var16.C35_f558, var16.C35_f559, var16.C35_f560);
            var1.setColor(245, 222, 179);
            var1.drawRect(var16.C35_f557, var16.C35_f558, var16.C35_f559, var16.C35_f560);
            var1.setColor(95, 158, 160);
            var1.fillRect(var19.C35_f557, var19.C35_f558, var19.C35_f559, var19.C35_f560);
        }

        for (int var17 = 0; var17 < var3.h().length && var3.h()[var17] != null; ++var17) {
            if (var3.h()[var17].g() != null) {
                boolean var5 = false;
                int var18;
                if ((var18 = C26.a(var15.C58_f848)) > 0 && var15.C58_f848[var18 - 1] == var3.b()) {
                    var5 = true;
                }

                var3.h()[var17].g().a(var2, var3.h()[var17].b(), var5, var15.C58_f848, true, var15.C58_f845);
            } else {
                var3.h()[var17].a(var2, false, true, var15.C58_f845, var15.C58_f848);
            }
        }

    }

    public final void b() {
        int var10003 = this.C58_f844;
        boolean var1 = true;
        C43 var2 = this.C58_f845;
        C58 var4 = this;

        for (int var3 = 0; var3 < var2.h().length && var2.h()[var3] != null; ++var3) {
            if (var2.h()[var3].g() != null) {
                if (C26.a(var4.C58_f848) > 0) {
                    int[] var10000 = var4.C58_f848;
                    var2.b();
                }

                var2.h()[var3].g().a(var2.h()[var3].b(), var4.C58_f848, true, var4.C58_f845);
            } else {
                var2.h()[var3].a(false, true, var4.C58_f845, var4.C58_f848);
            }
        }

    }

    public final C23 a(int var1) {
        return C26.a((C23) this.C58_f845, (int) var1);
    }

    public final boolean b(int var1) {
        C23 var2 = this.a(this.C58_f845, this.C58_f847, 0);
        boolean var4;
        boolean var9;
        if (var2.j() == 2) {
            C66 var3 = (C66) var2;
            var4 = false;
            switch (var1) {
                case 0:
                    var4 = var3.a((byte) 0);
                    this.C58_f850.a(new int[]{-1, -1, 0}, new int[]{-1, -1, -1, -1});
                    break;
                case 1:
                    var4 = var3.a((byte) 1);
                    this.C58_f850.a(new int[]{-1, -1, 1}, new int[]{-1, -1, -1, -1});
                    break;
                case 2:
                    var4 = var3.a((byte) 2);
                    this.C58_f850.a(new int[]{-1, -1, 2}, new int[]{-1, -1, -1, -1});
                    break;
                case 3:
                    var4 = var3.a((byte) 3);
                    this.C58_f850.a(new int[]{-1, -1, 3}, new int[]{-1, -1, -1, -1});
                case 4:
                case 6:
                default:
                    break;
                case 5:
                    this.C58_f850.a(new int[]{-1, -1, 4}, new int[]{-1, -1, -1, -1});
                    var4 = true;
                    break;
                case 7:
                    if (var4 = this.e()) {
                        var3.C66_f915 = false;
                        this.C58_f850.a(new int[]{-1, -1, 7}, new int[]{-1, -1, -1, -1});
                    } else {
                        this.C58_f850.a(new int[]{-1, -1, 5}, new int[]{-1, -1, -1, -1});
                    }
            }

            var9 = var4;
        } else {
            C43 var12 = (C43) var2;
            int var11 = var1;
            C58 var10 = this;
            var4 = false;
            boolean var5 = false;
            byte[][] var6;
            if (var12.a() != null) {
                var6 = var12.a();
            } else {
                var6 = new byte[][]{{0, 0, 1, -1}, {1, 1, 1, -1}, {2, 2, 1, -1}, {3, 3, 1, -1}, {5, 4, -1, -1}, {7, 5, -1, -1}};
            }

            int[] var7 = new int[3];
            if (var12.C43_f690 != null) {
                var7[1] = var12.C43_f690.C38_f580;
            } else {
                var7[1] = -1;
            }

            if (var12.C43_f689 != null) {
                var7[0] = var12.C43_f689.C38_f580;
            } else {
                var7[0] = -1;
            }

            for (int var8 = 0; var8 < var6.length; ++var8) {
                if (var6[var8][0] == var11) {
                    var5 = true;
                    switch (var6[var8][1]) {
                        case 0:
                            var7[2] = 0;
                            if (var12.C43_f689 != null) {
                                if (var6[var8][3] != -1 && var12.C43_f689.C38_f580 % (var6[var8][3] + 1) == 0) {
                                    var12.C43_f689.a(var6[var8][3], var10.C58_f845);
                                } else {
                                    var12.C43_f689.b(var6[var8][2], var10.C58_f845);
                                }

                                var4 = true;
                                var7[0] = var12.C43_f689.C38_f580;
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 1:
                            var7[2] = 1;
                            if (var12.C43_f689 != null) {
                                if (var6[var8][3] != -1 && (var12.C43_f689.C38_f580 + 1) % (var6[var8][3] + 1) == 0) {
                                    var12.C43_f689.b(var6[var8][3], var10.C58_f845);
                                } else {
                                    var12.C43_f689.a(var6[var8][2], var10.C58_f845);
                                }

                                var4 = true;
                                var7[0] = var12.C43_f689.C38_f580;
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 2:
                            var7[2] = 2;
                            if (var12.C43_f690 != null) {
                                if (var6[var8][3] != -1 && var12.C43_f690.C38_f580 % (var6[var8][3] + 1) == 0) {
                                    var12.C43_f690.a(var6[var8][3], var10.C58_f845);
                                } else {
                                    var12.C43_f690.b(var6[var8][2], var10.C58_f845);
                                }

                                var4 = true;
                                var7[1] = var12.C43_f690.C38_f580;
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 3:
                            var7[2] = 3;
                            if (var12.C43_f690 != null) {
                                if (var6[var8][3] != -1 && (var12.C43_f690.C38_f580 + 1) % (var6[var8][3] + 1) == 0) {
                                    var12.C43_f690.b(var6[var8][3], var10.C58_f845);
                                } else {
                                    var12.C43_f690.a(var6[var8][2], var10.C58_f845);
                                }

                                var4 = true;
                                var7[1] = var12.C43_f690.C38_f580;
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 4:
                            if (var12.C43_f690 != null) {
                                if (var12.C43_f690.C38_f580 >= C26.a(var12.C43_f690.C38_f575)) {
                                    var4 = var10.a(var12, var12.C43_f690.C38_f575[var12.C43_f690.C38_f580 - var12.C43_f690.C38_f579]);
                                } else {
                                    var4 = var10.a(var12, var12.C43_f690.C38_f575[var12.C43_f690.C38_f580]);
                                }
                            }

                            if (!var4 && var12.C43_f689 != null) {
                                if (var12.C43_f689.C38_f580 >= C26.a(var12.C43_f689.C38_f575)) {
                                    var4 = var10.a(var12, var12.C43_f689.C38_f575[var12.C43_f689.C38_f580 - var12.C43_f689.C38_f579]);
                                } else {
                                    var4 = var10.a(var12, var12.C43_f689.C38_f575[var12.C43_f689.C38_f580]);
                                }
                            }

                            if (var4) {
                                var7[2] = 6;
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var7[2] = 4;
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 5:
                            if (var4 = var10.e()) {
                                var7[2] = 7;
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var7[2] = 5;
                                var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                            }
                    }

                    if (var4) {
                        break;
                    }
                }
            }

            if (!var5) {
                switch (var11) {
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        var7[2] = var11 - 6;
                        var10.C58_f850.a(var7, new int[]{-1, -1, -1, -1});
                }
            }

            var9 = var4;
        }

        return var9;
    }

    private boolean e() {
        boolean var1 = false;
        int var2 = -1;

        for (int var3 = C26.a(this.C58_f848) - 2; var3 >= 0; --var3) {
            C23 var4;
            if (((C43) (var4 = C26.a((C23) this.C58_f845, (int) this.C58_f848[var3]))).C43_f689 != null || ((C43) var4).C43_f690 != null) {
                var1 = true;
                var2 = var4.b();
                break;
            }
        }

        if (var1) {
            this.C58_f847 = this.c(var2);
            if (this.C58_f847 == null) {
                int[] var10001 = this.C58_f847;
                this.C58_f847 = C26.c(50);
            }

            this.f();
        }

        return var1;
    }

    private boolean a(C23 var1, int var2) {
        if (var1.j() == 1) {
            return false;
        } else {
            int var3;
            if ((var3 = this.a(var1, var2, true)) == -1) {
                return false;
            } else {
                this.C58_f847 = this.c(var3);
                if (this.C58_f847 == null) {
                    int[] var10001 = this.C58_f847;
                    this.C58_f847 = C26.c(50);
                }

                this.f();
                return true;
            }
        }
    }

    private void f() {
        int[] var10001 = this.C58_f848;
        this.C58_f848 = C26.c(50);
        Object var1 = this.C58_f845;
        byte var2 = 0;
        int var4 = var2 + 1;
        this.C58_f848[0] = ((C23) var1).b();

        for (int var3 = 0; var3 < this.C58_f847.length && this.C58_f847[var3] != -1; ++var3) {
            var1 = ((C23) var1).h()[this.C58_f847[var3]];
            this.C58_f848[var4++] = ((C23) var1).b();
        }

    }

    private int a(C23 var1, int var2, boolean var3) {
        if (var1.j() == 2 && var2 == -1) {
            if (var3) {
                ((C66) var1).C66_f915 = true;
            }

            return var1.b();
        } else if ((((C43) var1).C43_f689 != null || ((C43) var1).C43_f690 != null) && var2 == -1) {
            return var1.b();
        } else {
            for (int var4 = 0; var4 < var1.h().length && var1.h()[var4] != null; ++var4) {
                int var5;
                if (var1.h()[var4].j() != 1 && (var2 == -1 || var1.h()[var4].b() == var2) && (var5 = this.a((C23) var1.h()[var4], -1, var3)) != -1) {
                    return var5;
                }
            }

            return -1;
        }
    }

    private int[] c(int var1) {
        int[] var2 = C26.c(50);

        for (C23 var5 = C26.a((C23) this.C58_f845, (int) var1); var5.k() != -1; var5 = C26.a((C23) this.C58_f845, (int) var5.k())) {
            C23 var3 = C26.a((C23) this.C58_f845, (int) var5.k());

            for (int var4 = 0; var4 < var3.h().length && var3.h()[var4] != null; ++var4) {
                if (var3.h()[var4].b() == var5.b()) {
                    C26.a((int[]) var2, 0, var4);
                    break;
                }
            }
        }

        return var2;
    }

    private C23 a(C23 var1, int[] var2, int var3) {
        int var4;
        while ((var4 = C26.a(var2)) != 0) {
            if (var3 == var4 - 1) {
                return var1.h()[var2[var3]];
            }

            C23 var10000 = var1.h()[var2[var3]];
            ++var3;
            var2 = var2;
            var1 = var10000;
        }

        return var1;
    }

    public final void c() {
        this.C58_f845.l();
        this.C58_f846 = null;
        this.C58_f847 = null;
        this.C58_f848 = null;
        this.C58_f850 = null;
        this.C58_f842 = null;
    }
}
