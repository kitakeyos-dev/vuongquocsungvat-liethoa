package d;

import java.util.Random;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public final class C47 implements C32 {
    private static final Object C47_f727;
    private int C47_f728;
    private static C47[] C47_f729;
    private static long C47_f730;
    private static Class C47_f731;

    static {
        Runtime.getRuntime();
        C47_f727 = new Integer(1);
    }

    private C47(int var1) {
        this.C47_f728 = var1;
    }

    public static void a(C63 var0) throws Exception {
        String[] var1;
        (var1 = new String[23])[0] = "pcall";
        var1[1] = "print";
        var1[2] = "select";
        var1[3] = "type";
        var1[4] = "tostring";
        var1[5] = "tonumber";
        var1[6] = "error";
        var1[7] = "unpack";
        var1[8] = "next";
        var1[9] = "setfenv";
        var1[10] = "getfenv";
        var1[11] = "rawequal";
        var1[12] = "_s";
        var1[13] = "_ss";
        var1[14] = "_t";
        var1[15] = "_a";
        var1[16] = "rp";
        var1[17] = "_cn";
        var1[18] = "_r";
        var1[19] = "_o";
        var1[20] = "_c";
        var1[21] = "_sp";
        var1[22] = "nc";
        int var2;
        if (C47_f729 == null) {
            C47_f729 = new C47[23];

            for (var2 = 0; var2 < 23; ++var2) {
                C47_f729[var2] = new C47(var2);
            }
        }

        for (var2 = 0; var2 < 23; ++var2) {
            var0.C63_f890.C72_f964.a(var1[var2], C47_f729[var2]);
        }

        C36 var10000 = var0.C63_f890.C72_f964;
        Class var10002 = C47_f731;
        if (var10002 == null) {
            try {
                var10002 = Class.forName("javax.microedition.io.Connector");
            } catch (ClassNotFoundException var3) {
                throw new NoClassDefFoundError(var3.getMessage());
            }

            C47_f731 = var10002;
        }

        var10000.a("_co", var10002);
    }

    public final int a(C34 var1, int var2) throws Exception {
        String var4;
        Object var5;
        Integer var6;
        int var7;
        Object var17;
        Object var21;
        Object var23;
        C36 var26;
        switch (this.C47_f728) {
            case 0:
                return var1.C34_f548.C72_f972.b(var1.C34_f548, var2 - 1);
            case 1:
                var4 = null;
                C63 var18;
                var5 = C63.b((var18 = (var1 = var1).C34_f548.C72_f972).C63_f890.C72_f964, "tostring");
                StringBuffer var25 = new StringBuffer();

                for (var7 = 0; var7 < var2; ++var7) {
                    Object var9 = var1.a(var7);
                    int var10 = var18.C63_f890.C72_f969;
                    var18.C63_f890.a(var10 + 1 + 3);
                    Object[] var11;
                    (var11 = var18.C63_f890.C72_f968)[var10] = var5;
                    var11[var10 + 1] = var9;
                    var11[var10 + 2] = null;
                    var11[var10 + 3] = null;
                    int var28 = var18.a(var18.C63_f890, 3);
                    var9 = null;
                    if (var28 > 0) {
                        var9 = var18.C63_f890.C72_f968[var10];
                    }

                    var18.C63_f890.a(var10);
                    var25.append(var9);
                    if (var7 < var2) {
                        var25.append("\t");
                    }
                }

                var18.C63_f891.println(var25);
                return 0;
            case 2:
                return 0;
            case 3:
                a(var2 > 0, "Not enough arguments");
                var17 = var1.a(0);
                var1.a(var17 == null ? "nil" : (var17 instanceof String ? "string" : (var17 instanceof Integer ? "number" : (var17 instanceof Boolean ? "boolean" : (!(var17 instanceof C32) && !(var17 instanceof C71) ? (var17 instanceof C36 ? "table" : (var17 instanceof C72 ? "thread" : "userdata")) : "function")))));
                return 1;
            case 4:
                a(var2 > 0, "Not enough arguments");
                Object var30 = var1.a(0);
                C63 var10001 = var1.C34_f548.C72_f972;
                var5 = var30;
                var4 = var30 == null ? "nil" : (var5 instanceof String ? (String) var5 : (var5 instanceof Boolean ? (var5 == Boolean.TRUE ? "true" : "false") : (!(var5 instanceof C32) && !(var5 instanceof C71) ? (var5 instanceof C36 ? "table 0x" + System.identityHashCode(var5) : var5.toString()) : "function 0x" + System.identityHashCode(var5))));
                var1.a(var4);
                return 1;
            case 5:
                a(var2 > 0, "Not enough arguments");
                var17 = var1.a(0);
                if (var2 == 1) {
                    var1.a(b(var17));
                    return 1;
                }

                var4 = (String) var17;
                a((var6 = b(var1.a(1))) != null, "Argument 2 must be a number");
                var7 = (Integer) var6;
                Integer var29 = a(var4, var7);
                var1.a(var29);
                return 1;
            case 6:
                return 0;
            case 7:
                return b(var1, var2);
            case 8:
                a(var2 > 0, "Not enough arguments");
                var26 = (C36) var1.a(0);
                var5 = null;
                if (var2 >= 2) {
                    var5 = var1.a(1);
                }

                if ((var23 = var26.b(var5)) == null) {
                    var1.c(1);
                    var1.a(0, (Object) null);
                    return 1;
                }

                Object var27 = var26.a(var23);
                var1.c(2);
                var1.a(0, var23);
                var1.a(1, var27);
                return 2;
            case 9:
                a(var2 >= 2, "Not enough arguments");
                a((var26 = (C36) var1.a(1)) != null, "expected a table");
                C71 var22;
                if ((var23 = var1.a(0)) instanceof C71) {
                    var22 = (C71) var23;
                } else {
                    a((var6 = b(var23)) != null, "expected a function or a number");
                    if ((var7 = (Integer) var6) == 0) {
                        var1.C34_f548.C72_f964 = var26;
                        return 0;
                    }

                    C34 var8;
                    if (!(var8 = var1.C34_f548.c(var7)).e()) {
                        a("No closure found at this level: " + var7);
                    }

                    var22 = var8.C34_f549;
                }

                var22.C71_f962 = var26;
                var1.c(1);
                return 1;
            case 10:
                var21 = C47_f727;
                if (var2 > 0) {
                    var21 = var1.a(0);
                }

                C36 var10000;
                if (var21 != null && !(var21 instanceof C32)) {
                    if (var21 instanceof C71) {
                        var10000 = ((C71) var21).C71_f962;
                    } else {
                        a((var6 = b(var21)) != null, "Expected number");
                        a((var7 = var6) >= 0, "level must be non-negative");
                        C34 var24;
                        var10000 = (var24 = var1.C34_f548.c(var7)).e() ? var24.C34_f549.C71_f962 : var24.C34_f548.C72_f964;
                    }
                } else {
                    var10000 = var1.C34_f548.C72_f964;
                }

                C36 var20 = var10000;
                var1.a(var20);
                return 1;
            case 11:
                a(var2 >= 2, "Not enough arguments");
                var21 = var1.a(0);
                var5 = var1.a(1);
                var1.a(C63.c(var21, var5) ? Boolean.TRUE : Boolean.FALSE);
                return 1;
            case 14:
                var1.a(new Integer((int) (System.currentTimeMillis() % 2147483647L)));
                return 1;
            case 15:
                if (var2 == 1 && (var21 = var1.a(0)) instanceof TextMessage) {
                    var1.a(((TextMessage) var21).getAddress());
                    return 1;
                }

                return 0;
            case 16:
                var1.a(a((String) var1.a(0), (String) var1.a(1), (String) var1.a(2)));
                return 1;
            case 17:
                var1.a(var1.a(0).getClass());
                return 1;
            case 18:
                var2 = (Integer) var1.a(0);
                int var15 = (Integer) var1.a(1);
                var1.a(new Integer((new Random()).nextInt() % (var15 - var2 + 1) + var2));
                return 1;
            case 19:
                var1.a(new Integer(((String) var1.a(0)).charAt(0)));
                return 1;
            case 20:
                StringBuffer var3 = new StringBuffer();

                for (int var19 = 0; var19 < var2; ++var19) {
                    var3.append((char) (var1.a(var19)));
                }

                var1.a(var3.toString());
                return 1;
            case 21:
                if ((var4 = System.getProperty((String) var1.a(0))) != null) {
                    var4 = var4.toLowerCase();
                }

                var1.a(var4);
                return 1;
            case 22:
                var1.a(new Integer((int) (C47_f730 + 10L)));
                return 1;
            default:
                return 0;
        }
    }

    private static int b(C34 var0, int var1) throws Exception {
        a(var1 > 0, "Not enough arguments");
        C36 var2 = (C36) var0.a(0);
        Object var3 = null;
        Object var4 = null;
        if (var1 >= 2) {
            var3 = var0.a(1);
        }

        if (var1 >= 3) {
            var4 = var0.a(2);
        }

        if (var3 != null) {
            var1 = (Integer) var3;
        } else {
            var1 = 1;
        }

        int var5;
        if (var4 != null) {
            var5 = (Integer) var4;
        } else {
            var5 = var2.a();
        }

        if ((var5 = var5 + 1 - var1) <= 0) {
            var0.c(0);
            return 0;
        } else {
            var0.c(var5);

            for (int var6 = 0; var6 < var5; ++var6) {
                var0.a(var6, var2.a(C63.a(var1 + var6)));
            }

            return var5;
        }
    }

    public static void a(boolean var0, String var1) throws Exception {
        if (!var0) {
            a(var1);
        }

    }

    public static void a(String var0) throws Exception {
        throw new Exception(var0);
    }

    private static Integer a(String var0, int var1) throws Exception {
        if (var1 >= 2 && var1 <= 36) {
            try {
                return var1 == 10 ? Integer.valueOf(var0) : C63.a(Integer.parseInt(var0, var1));
            } catch (NumberFormatException var2) {
                return null;
            }
        } else {
            throw new Exception("base out of range");
        }
    }

    public static String a(Object var0) {
        if (var0 instanceof String) {
            return (String) var0;
        } else {
            return var0 instanceof Integer ? ((Integer) var0).toString() : var0.toString();
        }
    }

    public static Integer b(Object var0) throws Exception {
        if (var0 instanceof Integer) {
            return (Integer) var0;
        } else {
            return var0 instanceof String ? a((String) var0, 10) : null;
        }
    }

    private static String a(String var0, String var1, String var2) {
        StringBuilder var3 = new StringBuilder();
        int var4 = var0.indexOf(var1);
        int var5 = 0;

        for (int var6 = var1.length(); var4 != -1; var4 = var0.indexOf(var1, var5)) {
            var3.append(var0, var5, var4).append(var2);
            var5 = var4 + var6;
        }

        var3.append(var0.substring(var5));
        return var3.toString();
    }
}
