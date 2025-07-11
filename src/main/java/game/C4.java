package game;

import me.kitakeyos.ManagedInputStream;

import javax.microedition.lcdui.Graphics;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Hashtable;

public final class C4 {
    public static byte C4_f33;
    private static byte[][] C4_f34;
    private static int[] C4_f35;
    private static int[] C4_f36;
    private static Hashtable C4_f37;
    public static int C4_f38;
    private static String C4_f39;
    private static int C4_f40;
    private static char C4_f41;
    private static int C4_f42;

    static {
        C4_f37 = new Hashtable();
        InputStream inputStream = ManagedInputStream.openStream("/font.bin");
        DataInputStream var0 = new DataInputStream(inputStream);

        try {
            String var1 = var0.readUTF();
            byte var2 = C4_f33 = var0.readByte();
            int var3;
            C4_f35 = new int[var3 = var1.length()];
            C4_f36 = new int[var3];
            int var4 = 0;

            int var5;
            for (var5 = 0; var5 < var3; ++var5) {
                C4_f35[var5] = var0.readByte();
                C4_f36[var5] = var4;
                var4 += C4_f35[var5];
                C4_f37.put(new Integer(var1.charAt(var5)), new Integer(var5));
            }

            C4_f34 = new byte[var2][var4];
            var5 = 7;
            byte var8 = 0;

            for (var3 = 0; var3 < var2; ++var3) {
                for (int var6 = 0; var6 < var4; ++var6) {
                    ++var5;
                    if (var5 >= 8) {
                        var5 = 0;
                        var8 = var0.readByte();
                    }

                    if ((var8 & 1) != 0) {
                        C4_f34[var3][var6] = 1;
                    }

                    var8 = (byte) (var8 >> 1);
                }
            }

            var0.close();
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        C4_f38 = a("nhung1");
    }

    public static int a(String var0) {
        if (var0 == C4_f39) {
            return C4_f40;
        } else {
            C4_f39 = var0;
            return C4_f40 = a(var0, 0, var0.length() - 1);
        }
    }

    public static int a(String var0, int var1, int var2) {
        int var3 = 0;

        for (var1 = var1; var1 < var2; ++var1) {
            Integer var4 = new Integer(var0.charAt(var1));

            try {
                var3 += C4_f35[(Integer) C4_f37.get(var4)];
            } catch (Exception var5) {
            }
        }

        return var3;
    }

    public static int a(char var0) {
        if (var0 == C4_f41) {
            return C4_f42;
        } else {
            try {
                C4_f41 = var0;
                return C4_f42 = C4_f35[(Integer) C4_f37.get(new Integer(var0))];
            } catch (Exception var1) {
                C4_f42 = 0;
                return 0;
            }
        }
    }

    public static final void a(Graphics var0, String var1, int var2, int var3, int var4) {
        var4 = var0.getColor();
        var0.setColor(16777215);
        a(var0, var1, var2, var3);
        var0.setColor(var4);
    }

    public static final void a(Graphics var0, String var1, int var2, int var3) {
        int var4 = var1.length();

        for (int var5 = 0; var5 < var4; ++var5) {
            var2 += a(var0, var1.charAt(var5), var2, var3);
        }

    }

    public static final void b(Graphics var0, String var1, int var2, int var3, int var4) {
        int var5 = var2;
        int var6 = var3;
        if ((var4 & 1) != 0) {
            var5 = var2 - a(var1) / 2;
        } else if ((var4 & 8) != 0) {
            var5 = var2 - a(var1);
        }

        if ((var4 & 2) != 0) {
            var6 = var3 - C4_f33 / 2;
        } else if ((var4 & 32) != 0) {
            var6 = var3 - C4_f33;
        }

        var2 = var1.length();

        for (var3 = 0; var3 < var2; ++var3) {
            var5 += a(var0, var1.charAt(var3), var5, var6);
        }

    }

    public static final int a(Graphics var0, char var1, int var2, int var3) {
        Integer var10 = new Integer(var1);

        try {
            int var11 = (Integer) C4_f37.get(var10);
            int var4 = C4_f35[var11];
            var11 = C4_f36[var11];
            byte var5 = C4_f33;

            for (int var6 = 0; var6 < var5; ++var6) {
                byte[] var7 = C4_f34[var6];

                for (int var8 = 0; var8 < var4; ++var8) {
                    if (var7[var8 + var11] != 0) {
                        var0.drawLine(var2 + var8, var3 + var6, var2 + var8, var3 + var6);
                    }
                }
            }

            return var4;
        } catch (Exception var9) {
            return 0;
        }
    }

    public static String[] a(String var0, int var1) {
        String[] var2 = new String[50];
        int var3 = var0.length();
        int var4 = var1 - C4_f38;
        int var5 = 0;
        int var6 = 0;
        StringBuffer var7 = new StringBuffer();

        for (int var8 = 0; var8 < var3; ++var8) {
            char var9;
            int var10 = a(var9 = var0.charAt(var8));
            if ((var5 += var10) <= var1 && (var9 != ' ' || var5 <= var4)) {
                var7.append(var9);
            } else {
                var2[var6++] = var7.toString();
                var7 = new StringBuffer();
                if (var9 != ' ') {
                    var5 = var10;
                    var7.append(var9);
                } else {
                    var5 = 0;
                }
            }

            var5 = var5;
        }

        if (var7.length() > 0) {
            var2[var6++] = var7.toString();
        }

        String[] var11 = new String[var6];
        System.arraycopy(var2, 0, var11, 0, var6);
        return var11;
    }
}
