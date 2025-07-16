package a;

import a.a.DebugLogger;
import game.BitmapFontRenderer;
import layout.IComponent;
import layout.TextRenderer;
import me.kitakeyos.ManagedInputStream;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class GameUtils {

    private static final char[] PUNCTUATION_MARKS = {'.', ',', '!', '?', ';', ':', '-'};
    private static final int MAX_TEXT_SEGMENTS = 100;           // Maximum number of text segments
    private static final int MARGIN_CONSTANT = 10;

    private static int DEFAULT_VALUE = 5;
    private static int MAX_SIZE = 300;
    private static int[] precomputedValues = null;
    private static Random randomGenerator;
    private static int[][] chunkInfo = new int[20][2];
    private static int[] C26_f381;
    private static int[] colors = new int[]{1862801, 15673612, 12067264, 11689977, 16359727, 16711680, 255};
    private static char[] punctuationMarks = new char[]{'，', '。', '？', '！', '：', '；', '’', '”', '、'};
    private static int currentTextPage = 0;
    public static String[] textChunks;
    private static int displayTextStart = 0;
    public static int pageCount = 1;
    private static int totalTextPages = 0;

    public static short[] concatenateShortArrays(short[] var0, short[] var1) {
        int var2 = 0;
        if (var0 != null) {
            var2 = var0.length;
        }

        short[] var3 = new short[var2 + var1.length];
        if (var0 != null) {
            System.arraycopy(var0, 0, var3, 0, var0.length);
        }

        System.arraycopy(var1, 0, var3, var2, var1.length);
        return var3;
    }

    public static void loadStreamToByteArray(byte[] var0, String var1, int var2) {
        try {
            InputStream var4;
            if (var1.substring(0, 1).endsWith("/")) {
                var4 = ManagedInputStream.openStream(var1);
            } else {
                var4 = ManagedInputStream.openStream("/" + var1);
            }

            DataInputStream var5;
            (var5 = new DataInputStream(var4)).skip(0L);
            var5.read(var0, 0, var0.length);
            var5.close();
        } catch (Exception var3) {
            System.out.println("GameInf err at getStream()  :  " + var3);
        }
    }

    public static short[] readShortArray(byte[] var0, int[] var1) {
        short var2 = readShort(var0, var1);
        short var3 = readShort(var0, var1);
        if (var2 == 0) {
            return null;
        } else {
            short[] var6 = new short[var2 * var3];

            for (int var7 = 0; var7 < var6.length; ++var7) {
                byte var4 = var0[var1[0]];
                var1[0]++;
                byte var5 = var0[var1[0]];
                var1[0]++;
                var6[var7] = (short) (var4 << 8 | var5 & 255);
            }

            return var6;
        }
    }

    public static short[][] readShortMatrix(byte[] var0, int[] var1) {
        short var3 = readShort(var0, var1);
        short var4 = readShort(var0, var1);
        if (var3 == 0) {
            return null;
        } else {
            short[][] var2 = new short[var3][];

            for (int var5 = 0; var5 < var3; ++var5) {
                short var6 = readShort(var0, var1);
                var2[var5] = new short[var6 * var4];

                for (int var9 = 0; var9 < var2[var5].length; ++var9) {
                    byte var7 = var0[var1[0]];
                    var1[0]++;
                    byte var8 = var0[var1[0]];
                    var1[0]++;
                    var2[var5][var9] = (short) (var7 << 8 | var8 & 255);
                }
            }

            return var2;
        }
    }

    private static short readShort(byte[] var0, int[] var1) {
        return readShortFromBytes(var0, var1);
    }

    public static InputStream openInputStream(String var0) {
        try {
            return ManagedInputStream.openStream(var0);
        } catch (Exception var1) {
            return null;
        }
    }

    public static short[][] readShortMatrix(InputStream inputStream) {
        short[][] var1 = null;
        if (inputStream == null) {
            return null;
        } else {
            try {
                DataInputStream var2 = new DataInputStream(inputStream);
                short var5 = (var2).readShort();
                if (var5 >= 0) {
                    var1 = new short[var5][];
                }

                for (int var3 = 0; var3 < var1.length; ++var3) {
                    var5 = var2.readShort();
                    var1[var3] = new short[var5];

                    for (int var6 = 0; var6 < var1[var3].length; ++var6) {
                        var1[var3][var6] = var2.readShort();
                    }
                }
            } catch (Exception var4) {
            }

            return var1;
        }
    }

    public static byte[][] readByteMatrix(InputStream var0) {
        byte[][] var1 = null;
        if (var0 == null) {
            return null;
        } else {
            try {
                DataInputStream var2;
                short var5;
                if ((var5 = (var2 = new DataInputStream(var0)).readShort()) >= 0) {
                    var1 = new byte[var5][];
                }

                for (int var3 = 0; var3 < var1.length; ++var3) {
                    var5 = var2.readShort();
                    var1[var3] = new byte[var5];

                    for (int var6 = 0; var6 < var1[var3].length; ++var6) {
                        var1[var3][var6] = var2.readByte();
                    }
                }
            } catch (Exception var4) {
            }

            return var1;
        }
    }

    public static String[][] readStringMatrix(InputStream var0) {
        String[][] var1 = null;
        StringBuilder var2 = new StringBuilder();

        try {
            short var3;
            DataInputStream var8;
            if ((var3 = (var8 = new DataInputStream(var0)).readShort()) == 0) {
                return null;
            }

            if (var3 > 0) {
                var1 = new String[var3][];
            }

            for (int var4 = 0; var4 < var1.length; ++var4) {
                var3 = var8.readShort();
                var1[var4] = new String[var3];

                for (int var9 = 0; var9 < var1[var4].length; ++var9) {
                    int var5;
                    if ((var5 = var8.readUnsignedByte()) == 255) {
                        var5 = var8.readShort();
                    }

                    for (int var6 = 0; var6 < var5; ++var6) {
                        var2.append((char) var8.readShort());
                    }

                    var1[var4][var9] = var2.toString();
                    var2 = new StringBuilder();
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return var1;
    }

    public static int calculateEuclideanDistance(int var0, int var1, int var2, int var3) {
        var0 = (var0 - var2) * (var0 - var2) + (var1 - var3) * (var1 - var3);
        var1 = 0;

        for (var2 = 1073741824; var2 > 0; var2 >>= 2) {
            if (var0 >= var1 + var2) {
                var0 -= var1 + var2;
                var1 = (var1 >> 1) + var2;
            } else {
                var1 >>= 1;
            }
        }

        return var1;
    }

    public static int fastSqrt(int var0, int var1) {
        int var2;
        int var3;
        if (precomputedValues == null) {
            precomputedValues = new int[MAX_SIZE / DEFAULT_VALUE + 1];
            var2 = DEFAULT_VALUE * DEFAULT_VALUE;

            for (var3 = precomputedValues.length - 1; var3 >= 0; --var3) {
                precomputedValues[var3] = var3 * var3 * var2;
            }
        }

        if (var0 < 0) {
            return -1;
        } else {
            if (var0 <= precomputedValues[30]) {
                if (var0 <= precomputedValues[15]) {
                    if (var0 <= precomputedValues[5]) {
                        for (var2 = 1; var2 <= 5 && var0 > precomputedValues[var2]; ++var2) {
                        }
                    } else if (var0 <= precomputedValues[10]) {
                        for (var2 = 6; var2 <= 10 && var0 > precomputedValues[var2]; ++var2) {
                        }
                    } else {
                        for (var2 = 11; var2 <= 15 && var0 > precomputedValues[var2]; ++var2) {
                        }
                    }
                } else if (var0 <= precomputedValues[20]) {
                    for (var2 = 16; var2 <= 20 && var0 > precomputedValues[var2]; ++var2) {
                    }
                } else if (var0 <= precomputedValues[25]) {
                    for (var2 = 21; var2 <= 25 && var0 > precomputedValues[var2]; ++var2) {
                    }
                } else {
                    for (var2 = 26; var2 <= 30 && var0 > precomputedValues[var2]; ++var2) {
                    }
                }
            } else if (var0 <= precomputedValues[45]) {
                if (var0 <= precomputedValues[35]) {
                    for (var2 = 31; var2 <= 35 && var0 > precomputedValues[var2]; ++var2) {
                    }
                } else if (var0 <= precomputedValues[40]) {
                    for (var2 = 36; var2 <= 40 && var0 > precomputedValues[var2]; ++var2) {
                    }
                } else {
                    for (var2 = 41; var2 <= 45 && var0 > precomputedValues[var2]; ++var2) {
                    }
                }
            } else if (var0 <= precomputedValues[50]) {
                for (var2 = 46; var2 <= 50 && var0 > precomputedValues[var2]; ++var2) {
                }
            } else if (var0 <= precomputedValues[55]) {
                for (var2 = 51; var2 <= 55 && var0 > precomputedValues[var2]; ++var2) {
                }
            } else {
                for (var2 = 56; var2 <= 60 && var0 > precomputedValues[var2]; ++var2) {
                }
            }

            for (var3 = (var2 *= DEFAULT_VALUE) - DEFAULT_VALUE + 1; var3 <= var2; ++var3) {
                if (var3 * var3 >= var0) {
                    if (var1 == 0) {
                        return var3;
                    }

                    if (var1 == 1) {
                        return var3 - 1;
                    }
                }
            }

            return -1;
        }
    }

    public static int getRandomInt(int var0) {
        if (randomGenerator == null) {
            randomGenerator = new Random(System.currentTimeMillis());
        }

        return randomGenerator.nextInt(var0);
    }

    public static int getRandomOffset(int var0) {
        if (randomGenerator == null) {
            randomGenerator = new Random(System.currentTimeMillis());
        }

        return -2 + randomGenerator.nextInt(4);
    }

    public static int getRandomInRange(int var0, int var1) {
        if (randomGenerator == null) {
            randomGenerator = new Random(System.currentTimeMillis());
        }

        return (randomGenerator.nextInt() >>> 1) % (var1 - var0 + 1) + var0;
    }

    public static int findFirstGreaterOrEqual(int[] var0, int var1) {
        int var2;
        for (var2 = 0; var2 < var0.length && var1 < var0[var2]; ++var2) {
        }

        return var2;
    }

    public static boolean checkCollision(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, int var8) {
        if (var8 % 2 != 0) {
            var8 = var2;
            var2 = var3;
            var3 = var8;
        }

        return var0 + var2 >= var4 && var4 + var6 >= var0 && var1 + var3 > var5 && var5 + var7 > var1;
    }

    public static boolean isPointInRectangle(int var0, int var1, int var2, int var3, int var4, int var5) {
        return var0 >= var2 && var0 <= var2 + var4 && var1 >= var3 && var1 <= var3 + 40;
    }

    public static boolean checkCollisionBetweenShortArrays(int var0, int var1, int var2, int var3, short[] var4, short[] var5) {
        return var0 + var4[0] + var4[2] >= var2 + var5[0] && var0 + var4[0] <= var2 + var5[0] + var5[2] && var1 + var4[1] <= var3 + var5[1] + var5[3] && var1 + var4[1] + var4[3] >= var3 + var5[1];
    }

    public static boolean checkCollisionWithShortArray(int var0, int var1, int var2, int var3, int var4, int var5, short[] var6) {
        return var0 + var2 >= var4 + var6[0] && var0 <= var4 + var6[0] + var6[2] && var1 <= var5 + var6[1] + var6[3] && var1 + var3 >= var5 + var6[1];
    }

    public static boolean isPointInShortArrayRectangle(int var0, int var1, int var2, int var3, short[] var4) {
        return var0 >= var2 + var4[0] && var0 <= var2 + var4[0] + var4[2] && var1 <= var3 + var4[1] + var4[3] && var1 >= var3 + var4[1];
    }

    public static Image loadPNG(String var0, String var1) {
        try {
            return Image.createImage(var0 + var1 + ".png");
        } catch (Exception var3) {
            DebugLogger.logError(var3, var0 + var1 + ".png error!!");
            return null;
        }
    }

    public static Image loadImage(String basePath, String fileName) {
        byte[] data = loadStream(basePath + fileName + ".mid");
        return Image.createImage(data, 0, data.length);
    }

    private static byte[] processStream(String var0) {
        byte[] var1 = null;

        try {
            int var2 = 0;
            byte var4 = 0;
            InputStream inputStream = ManagedInputStream.openStream(var0);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(longToBytes(-1991225785L, 4));
            outputStream.write(longToBytes(218765834L, 4));
            outputStream.write(longToBytes(13L, 4));
            outputStream.write(longToBytes(1229472850L, 4));
            outputStream.write(longToBytes(dataInputStream.readInt(), 4));
            outputStream.write(longToBytes(dataInputStream.readInt(), 4));
            outputStream.write(dataInputStream.readByte());
            byte var10 = dataInputStream.readByte();
            chunkInfo = new int[20][2];
            outputStream.write(var10);
            outputStream.write(longToBytes(0L, 3));
            outputStream.write(longToBytes(0L, 4));
            chunkInfo[0][0] = 8;
            chunkInfo[0][1] = 13;
            int var3 = 8 + chunkInfo[0][1] + 12;
            int var13 = var4 + 1;
            int var8;
            byte[] var11;
            if (var10 != 3) {
                if (var10 == 6) {
                    var2 = dataInputStream.readInt();
                }
            } else {
                var2 = dataInputStream.readInt();
                outputStream.write(longToBytes((long) var2, 4));
                outputStream.write(longToBytes(1347179589L, 4));
                var11 = new byte[var2];

                for (var8 = 0; var8 < var11.length; ++var8) {
                    var11[var8] = (byte) dataInputStream.read();
                }

                outputStream.write(var11);
                outputStream.write(longToBytes(0L, 4));
                chunkInfo[1][0] = var3;
                chunkInfo[1][1] = var2;
                var3 += chunkInfo[1][1] + 12;
                ++var13;
                if ((var2 = dataInputStream.readInt()) != 1951551059) {
                    chunkInfo[2][0] = var3;
                    chunkInfo[2][1] = var2;
                    ++var13;
                } else {
                    int var12;
                    if ((var12 = dataInputStream.read()) == 0) {
                        var12 = 256;
                    }

                    outputStream.write(longToBytes((long) var12, 4));
                    outputStream.write(longToBytes(1951551059L, 4));
                    var8 = dataInputStream.read();

                    for (var2 = 0; var2 < var12; ++var2) {
                        if (var2 == var8) {
                            outputStream.write(0);
                        } else {
                            outputStream.write(255);
                        }
                    }

                    outputStream.write(longToBytes(0L, 4));
                    chunkInfo[2][0] = var3;
                    chunkInfo[2][1] = var12;
                    var3 += chunkInfo[2][1] + 12;
                    ++var13;
                    var2 = dataInputStream.readInt();
                }
            }

            outputStream.write(longToBytes(var2, 4));
            outputStream.write(longToBytes(1229209940L, 4));
            var11 = new byte[var2];

            for (var8 = 0; var8 < var11.length; ++var8) {
                var11[var8] = (byte) dataInputStream.read();
            }

            outputStream.write(var11);
            outputStream.write(longToBytes(0L, 4));
            chunkInfo[var13][0] = var3;
            chunkInfo[var13][1] = var2;
            ++var13;
            outputStream.write(longToBytes(0L, 4));
            outputStream.write(longToBytes(1229278788L, 4));
            outputStream.write(longToBytes(-1371381630L, 4));
            var1 = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();

            for (var8 = 0; var8 < var13; ++var8) {
                int var10001 = chunkInfo[var8][0];
                var3 = chunkInfo[var8][1];
                var2 = var10001;
                var10001 = var2 + 4;
                int var15 = var3 + 4;
                int var14 = ~ab(var1, var10001, var15);
                var1[var2 + 8 + var3] = (byte) (var14 >> 24);
                var1[var2 + 8 + var3 + 1] = (byte) (var14 >> 16);
                var1[var2 + 8 + var3 + 2] = (byte) (var14 >> 8);
                var1[var2 + 8 + var3 + 3] = (byte) var14;
            }
        } catch (Exception var9) {
        }

        return var1;
    }

    private static byte[] longToBytes(long value, int byteCount) {
        byte[] bytes = new byte[byteCount];
        int currentByteIndex = byteCount - 1;

        for (int i = 0; currentByteIndex >= 0; i++) {
            bytes[i] = (byte) ((value >> (currentByteIndex * 8)) & 0xFF);
            currentByteIndex--;
        }

        return bytes;
    }

    private static int ab(byte[] var0, int var1, int var2) {
        int var3 = -1;
        int var4;
        if (C26_f381 == null) {
            C26_f381 = new int[256];

            for (int var5 = 0; var5 < 256; ++var5) {
                var4 = var5;

                for (int var6 = 0; var6 < 8; ++var6) {
                    if ((var4 & 1) == 1) {
                        var4 = -306674912 ^ var4 >>> 1;
                    } else {
                        var4 >>>= 1;
                    }
                }

                C26_f381[var5] = var4;
            }
        }

        for (var4 = var1; var4 < var2 + var1; ++var4) {
            var3 = C26_f381[(var3 ^ var0[var4]) & 255] ^ var3 >>> 8;
        }

        return var3;
    }

    public static int[] extractImageRGB(Image var0) {
        int var1 = var0.getWidth();
        int var2 = var0.getHeight();
        int[] var3 = new int[var1 * var2];
        var0.getRGB(var3, 0, var1, 0, 0, var1, var2);
        return var3;
    }

    public static Image createRGBImage(int[] rgb, int width, int height, boolean processAlpha) {
        return Image.createRGBImage(rgb, width, height, processAlpha);
    }

    public static boolean a() {
        return false;
    }

    public static int findFirstAvailableSlot(int[] var0) {
        int var1 = 0;

        for (int var2 = 0; var2 < var0.length; ++var2) {
            if (var0[var2] == -1) {
                var1 = var2;
                break;
            }
        }

        return var1;
    }

    public static IComponent findChildById(IComponent var0, int var1) {
        if (var0.getSelectedComponentId() == var1) {
            return var0;
        } else {
            for (int var2 = 0; var2 < var0.getComponents().length && var0.getComponents()[var2] != null; ++var2) {
                if (var0.getComponents()[var2].getZIndex() == 0) {
                    IComponent var3;
                    if ((var3 = findChildById(var0.getComponents()[var2], var1)) != null) {
                        return var3;
                    }
                } else if (var0.getComponents()[var2].getSelectedComponentId() == var1) {
                    return var0.getComponents()[var2];
                }
            }

            return null;
        }
    }

    public static int[] initializeArrayWithMinusOne(int var0) {
        int[] var1 = new int[var0];

        for (int var2 = 0; var2 < var0; ++var2) {
            var1[var2] = -1;
        }

        return var1;
    }

    public static void insertAtStart(int[] var0, int var1, int var2) {
        if (0 < var0.length) {
            var1 = 0;

            int var3;
            for (var3 = 0; var3 < var0.length; ++var3) {
                if (var0[var3] == -1) {
                    var1 = var3;
                    break;
                }
            }

            for (var3 = var1 + 0 - 1; var3 >= 0; --var3) {
                if (var3 + 0 + 1 < var0.length) {
                    var0[var3 + 1] = var0[var3];
                }
            }

            var0[0] = var2;
        }
    }

    public static void readBytesFromStream(byte[] var0, String var1, int var2) {
        try {
            var2 = 0;

            for (int var3 = 0; var3 < var1.length(); ++var3) {
                if (var1.charAt(var3) != '/') {
                    var2 = var3;
                    break;
                }
            }

            String var7 = "/" + var1.substring(var2);
            (new Object()).getClass();
            InputStream var5 = ManagedInputStream.openStream(var7);
            DataInputStream var6;
            (var6 = new DataInputStream(var5)).skip(0L);
            var6.read(var0, 0, var0.length);
            var6.close();
        } catch (Exception var4) {
            System.out.println("GameInf err at getStream()  :  " + var4);
        }
    }

    public static byte readByte(byte[] var0, int[] var1) {
        int var10004 = var1[0];
        int var10001 = var1[0];
        var1[0] = var10004 + 1;
        return (byte) var0[var10001];
    }

    public static short readShortFromBytes(byte[] var0, int[] var1) {
        int var10004 = var1[0];
        int var10001 = var1[0];
        var1[0] = var10004 + 1;
        int var10000 = (var0[var10001] & 255) << 8;
        int var10005 = var1[0];
        int var10002 = var1[0];
        var1[0] = var10005 + 1;
        return (short) (var10000 | var0[var10002] & 255);
    }

    public static int e(byte[] var0, int[] var1) {
        int var10004 = var1[0];
        int var10001 = var1[0];
        var1[0] = var10004 + 1;
        int var10000 = (var0[var10001] & 255) << 24;
        int var10005 = var1[0];
        int var10002 = var1[0];
        var1[0] = var10005 + 1;
        var10000 |= (var0[var10002] & 255) << 16;
        var10005 = var1[0];
        var10002 = var1[0];
        var1[0] = var10005 + 1;
        var10000 |= (var0[var10002] & 255) << 8;
        var10005 = var1[0];
        var10002 = var1[0];
        var1[0] = var10005 + 1;
        return var10000 | var0[var10002] & 255;
    }

    public static String a(byte[] var0) {
        char[] var1 = new char[var0.length >> 1];

        for (int var2 = 0; var2 < var0.length; var2 += 2) {
            var1[var2 >> 1] = (char) (var0[var2] << 8 | var0[var2 + 1] & 255);
        }

        return new String(var1);
    }

    public static void a(IComponent var0, int var1, int var2, IComponent var3) {
        var0.setOffsetX(var0.getOffsetX() + var1, var3);
        var0.setOffsetY(var0.getOffsetY() + var2, var3);
        if (var0.getZIndex() != 1 && var0.getZIndex() == 0) {
            for (int var4 = 0; var4 < var0.getComponents().length && var0.getComponents()[var4] != null; ++var4) {
                a(var0.getComponents()[var4], var1, var2, var3);
            }
        }

    }

    public static void a(Graphics var0, String var1, int var2, int var3, int var4, int var5, int var6, int var7, Font var8, boolean var9, int var10, int[] var11, int var12, byte var13, TextRenderer var14, byte var15, boolean[] var16) {
        if (var13 != -1 && var14 != null) {
            var5 = BitmapFontRenderer.fontHeight;
        }

        int[] var20;
        (var20 = new int[1 + colors.length])[0] = var2;

        for (var2 = 1; var2 < var20.length; ++var2) {
            var20[var2] = colors[var2 - 1];
        }

        TextLayout var18 = parseAndFormatText(var1, var5, var6, var9, var8, var13, var14);
        int var17 = 0;
        if (var18.segmentCount > 0) {
            if (var9) {
                if (var18.textSegments[var18.segmentCount - 1][3] + var5 > var7) {
                    var16[0] = true;
                    if (var18.textSegments[var18.segmentCount - 1][3] + var5 > var11[1]) {
                        var11[1] += var12;
                    } else {
                        var11[1] = -var7;
                        var16[1] = true;
                    }
                } else {
                    var16[0] = var16[1] = true;
                    var11[1] = 0;
                }
            } else {
                var17 = var8.stringWidth(var1);
                if (var13 != -1 && var14 != null) {
                    var17 = BitmapFontRenderer.calculateStringWidth(var1);
                }

                if (var17 > var6) {
                    var16[1] = true;
                    if (var17 > var11[0]) {
                        var11[0] += var12;
                        if (var11[0] >= var17 - var6) {
                            var16[0] = true;
                        }
                    } else {
                        var11[0] = -var6;
                    }
                } else {
                    var16[1] = var16[0] = true;
                    var11[0] = 0;
                }
            }
        }

        for (int var19 = 0; var19 < var18.segmentCount; ++var19) {
            if (var9) {
                var0.clipRect(var3, var4, var6, var7);
                a(var0, var1.substring(var18.textSegments[var19][0], var18.textSegments[var19][1]), var20[var18.textSegments[var19][4]], var3 + var18.textSegments[var19][2], var4 + var18.textSegments[var19][3] - var11[1], 20, var14);
                var0.clipRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
            } else {
                var12 = 0;
                int var21 = 0;
                switch (var10) {
                    case 0:
                        var12 = var3;
                        var21 = var4;
                        break;
                    case 1:
                        var12 = var3 + (var6 - (var18.totalWidth + var18.currentSegmentWidth)) / 2;
                        var21 = var4;
                        break;
                    case 2:
                        var12 = var3 + (var6 - (var18.totalWidth + var18.currentSegmentWidth));
                        var21 = var4;
                        break;
                    case 3:
                        var12 = var3;
                        var21 = var4 + (var7 - var5) / 2;
                        break;
                    case 4:
                        if (var17 > var6) {
                            var12 = var3;
                        } else {
                            var12 = var3 + (var6 - (var18.totalWidth + var18.currentSegmentWidth)) / 2;
                        }

                        var21 = var4 + (var7 - var5) / 2;
                        break;
                    case 5:
                        var12 = var3 + (var6 - (var18.totalWidth + var18.currentSegmentWidth));
                        var21 = var4 + (var7 - var5) / 2;
                        break;
                    case 6:
                        var12 = var3;
                        var21 = var4 + (var7 - var5);
                        break;
                    case 7:
                        if (var17 > var6) {
                            var12 = var3;
                        } else {
                            var12 = var3 + (var6 - (var18.totalWidth + var18.currentSegmentWidth)) / 2;
                        }

                        var21 = var4 + (var7 - var5);
                        break;
                    case 8:
                        var12 = var3 + (var6 - (var18.totalWidth + var18.currentSegmentWidth));
                        var21 = var4 + (var7 - var5);
                }

                var0.clipRect(var3, var4, var6, var7);
                a(var0, var1.substring(var18.textSegments[var19][0], var18.textSegments[var19][1]), var20[var18.textSegments[var19][4]], var12 + var18.textSegments[var19][2] - var11[0], var21 + var18.textSegments[var19][3], 20, var14);
                var0.clipRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
            }
        }

    }

    /**
     * Parse and format text with line breaks, color codes, and word wrapping
     *
     * @param text           Text to parse and format
     * @param lineHeight     Height of each line in pixels
     * @param maxWidth       Maximum width before wrapping
     * @param enableWordWrap Whether to enable automatic word wrapping
     * @param font           Font to use for width calculations
     * @param renderMode     Text rendering mode (-1 for default)
     * @param textRenderer   Text renderer for advanced width calculations
     * @return TextLayout object containing formatted text segments
     */
    private static TextLayout parseAndFormatText(String text, int lineHeight, int maxWidth,
                                                 boolean enableWordWrap, Font font, byte renderMode,
                                                 TextRenderer textRenderer) {
        TextLayout layout = new TextLayout();

        // Text parsing state
        int segmentStartIndex = 0;          // Start of current text segment
        int currentLineWidth = 0;           // Current line width in pixels
        int currentLineY = 0;              // Current line Y position
        int currentColorCode = 0;           // Current text color code
        int textLength = text.length() - 1; // Last character index
        int wordWrapThreshold = maxWidth - MARGIN_CONSTANT; // Threshold for word wrapping

        // Parse each character in the text
        for (int charIndex = 0; charIndex <= textLength; charIndex++) {
            char currentChar = text.charAt(charIndex);
            int segmentWidth;

            // Handle special formatting codes
            if (currentChar == '#' && charIndex != textLength) {
                char nextChar = text.charAt(charIndex + 1);

                if (nextChar == 'n') {
                    // Manual line break (#n)
                    layout.textSegments[layout.segmentCount++] = new int[]{
                            segmentStartIndex, charIndex, currentLineWidth, currentLineY, currentColorCode
                    };
                    currentLineWidth = 0;
                    currentLineY += lineHeight + 1;
                    segmentStartIndex = charIndex + 2;

                } else if (nextChar >= '0' && nextChar <= '7') {
                    // Color code (#0 to #7)
                    layout.totalWidth += layout.currentSegmentWidth;
                    layout.textSegments[layout.segmentCount++] = new int[]{
                            segmentStartIndex, charIndex, currentLineWidth, currentLineY, currentColorCode
                    };

                    // Calculate width of text segment before color change
                    segmentWidth = calculateTextWidth(text, font, segmentStartIndex, charIndex,
                            renderMode, textRenderer);
                    currentLineWidth += segmentWidth;

                    // Extract color code (0-7)
                    currentColorCode = nextChar - '0';
                    segmentStartIndex = charIndex + 2;
                }

            } else if (charIndex - segmentStartIndex >= 0) {
                // Regular character - check for word wrapping
                segmentWidth = calculateSegmentWidth(text, segmentStartIndex, charIndex + 1,
                        renderMode, textRenderer);
                layout.currentSegmentWidth = segmentWidth;

                // Check if word wrapping is needed
                if (enableWordWrap && shouldWrapLine(currentLineWidth, segmentWidth, maxWidth,
                        currentChar, wordWrapThreshold)) {
                    layout.textSegments[layout.segmentCount++] = new int[]{
                            segmentStartIndex, charIndex, currentLineWidth, currentLineY, currentColorCode
                    };
                    currentLineWidth = 0;
                    currentLineY += lineHeight + 1;
                    segmentStartIndex = charIndex;
                }
            }

            // Handle end of text
            if (charIndex == textLength) {
                if (charIndex == segmentStartIndex) {
                    // Check if last character is punctuation
                    if (handlePunctuationAtEnd(text, charIndex, layout)) {
                        continue;
                    }
                } else if (charIndex + 1 - segmentStartIndex <= 0) {
                    // Empty segment at end
                    continue;
                }

                // Add final text segment
                layout.textSegments[layout.segmentCount++] = new int[]{
                        segmentStartIndex, charIndex + 1, currentLineWidth, currentLineY, currentColorCode
                };
            }
        }

        return layout;
    }

    /**
     * Calculate width of text segment using appropriate method
     *
     * @param text         Full text string
     * @param font         Font for calculations
     * @param startIndex   Start index of segment
     * @param endIndex     End index of segment
     * @param renderMode   Rendering mode
     * @param textRenderer Text renderer instance
     * @return Width in pixels
     */
    private static int calculateTextWidth(String text, Font font, int startIndex, int endIndex,
                                          byte renderMode, TextRenderer textRenderer) {
        String segment = text.substring(startIndex, endIndex);

        if (renderMode != -1 && textRenderer != null) {
            return TextRenderer.calculateTextWidth(segment, 0, endIndex - startIndex);
        } else {
            return font.charsWidth(segment.toCharArray(), 0, endIndex - startIndex);
        }
    }

    /**
     * Calculate width of a text segment
     *
     * @param text         Full text string
     * @param startIndex   Start index
     * @param endIndex     End index
     * @param renderMode   Rendering mode
     * @param textRenderer Text renderer
     * @return Segment width in pixels
     */
    private static int calculateSegmentWidth(String text, int startIndex, int endIndex,
                                             byte renderMode, TextRenderer textRenderer) {
        return TextRenderer.calculateTextWidth(text.substring(startIndex, endIndex),
                0, endIndex - startIndex);
    }

    /**
     * Determine if line should be wrapped
     *
     * @param currentLineWidth  Current width of line
     * @param segmentWidth      Width of current segment
     * @param maxWidth          Maximum allowed width
     * @param currentChar       Current character being processed
     * @param wordWrapThreshold Threshold for word wrapping
     * @return true if line should be wrapped
     */
    private static boolean shouldWrapLine(int currentLineWidth, int segmentWidth, int maxWidth,
                                          char currentChar, int wordWrapThreshold) {
        return (currentLineWidth + segmentWidth >= maxWidth) ||
                (currentChar == ' ' && currentLineWidth + segmentWidth >= wordWrapThreshold);
    }

    /**
     * Handle punctuation at end of text
     *
     * @param text      Full text string
     * @param charIndex Current character index
     * @param layout    Text layout being built
     * @return true if punctuation was handled
     */
    private static boolean handlePunctuationAtEnd(String text, int charIndex, TextLayout layout) {
        char lastChar = text.charAt(charIndex);

        // Check if last character is punctuation
        for (int i = 0; i < PUNCTUATION_MARKS.length; i++) {
            if (lastChar == PUNCTUATION_MARKS[i]) {
                // Extend previous segment to include punctuation
                layout.textSegments[layout.segmentCount - 1][1] = charIndex + 1;
                return true;
            }
        }

        return false;
    }

    private static void a(Graphics var0, String var1, int var2, int var3, int var4, int var5, TextRenderer var6) {
        TextRenderer.renderText(var1, var3, var4, 20, var2, var0);
    }

    public static void a(Graphics var0, String var1, int var2, int var3, int var4, int var5, int var6, TextRenderer var7, int var8) {
        switch (var8) {
            case 0:
                if (var7 != null) {
                    TextRenderer.renderText(var1, var3 - 1, var4, 17, 8607289, var0);
                    TextRenderer.renderText(var1, var3 + 1, var4, 17, 8607289, var0);
                } else {
                    var0.setColor(8607289);
                    var0.drawString(var1, var3 - 1, var4, 17);
                    var0.drawString(var1, var3 + 1, var4, 17);
                }
                break;
            case 1:
                if (var7 != null) {
                    TextRenderer.renderText(var1, var3, var4 - 1, 17, 8607289, var0);
                    TextRenderer.renderText(var1, var3, var4 + 1, 17, 8607289, var0);
                } else {
                    var0.setColor(8607289);
                    var0.drawString(var1, var3, var4 - 1, 17);
                    var0.drawString(var1, var3, var4 + 1, 17);
                }
                break;
            case 2:
                if (var7 != null) {
                    TextRenderer.renderText(var1, var3, var4 - 1, 17, 8607289, var0);
                    TextRenderer.renderText(var1, var3, var4 + 1, 17, 8607289, var0);
                    TextRenderer.renderText(var1, var3 - 1, var4, 17, 8607289, var0);
                    TextRenderer.renderText(var1, var3 + 1, var4, 17, 8607289, var0);
                } else {
                    var0.setColor(8607289);
                    var0.drawString(var1, var3, var4 - 1, 17);
                    var0.drawString(var1, var3, var4 + 1, 17);
                    var0.drawString(var1, var3 - 1, var4, 17);
                    var0.drawString(var1, var3 + 1, var4, 17);
                }
        }

        if (var7 != null) {
            TextRenderer.renderText(var1, var3, var4, 17, var2, var0);
        } else {
            var0.setColor(var2);
            var0.drawString(var1, var3, var4, 17);
        }
    }

    public static void a(String var0, int var1, int var2, Font var3, boolean var4, byte var5, TextRenderer var6) {
        TextLayout var8 = parseAndFormatText(var0, var1, var2, true, var3, (byte) -1, var6);
        var2 = var8.segmentCount;
        int[][] var9 = var8.textSegments;
        var0 = var0;
        pageCount = 1;
        totalTextPages = 0;
        String[] var10 = new String[50];
        int var11 = 0;
        int var12 = var9[0][3];
        int var13 = 0;

        for (int var7 = 0; var7 < var2; ++var7) {
            if (var12 != var9[var7][3]) {
                var12 = var9[var7][3];
                var10[var11++] = var0.substring(var13, var9[var7][0]);
                var13 = var9[var7][0];
            }

            if (var7 == var2 - 1) {
                var10[var11++] = var0.substring(var13, var9[var7][1]);
                break;
            }
        }

        String[] var14 = new String[var11];
        System.arraycopy(var10, 0, var14, 0, var14.length);
        textChunks = var14;
    }

    public static void d(int var0) {
        currentTextPage = var0 / GameEngineBase.getFontHeight();
    }

    public static String e(int var0) {
        StringBuilder var1 = new StringBuilder();

        for (int var2 = currentTextPage * totalTextPages; var2 < (Math.min(var0 * currentTextPage, textChunks.length)); ++var2) {
            var1.append(textChunks[var2]);
        }

        totalTextPages = var0;
        return var1.toString();
    }

    public static int b() {
        return textChunks.length % currentTextPage == 0 ? textChunks.length / currentTextPage : textChunks.length / currentTextPage + 1;
    }

    public static void c() {
        ++pageCount;
    }

    /**
     * Tách chuỗi thành mảng string theo delimiter
     *
     * @param input     Chuỗi cần tách
     * @param delimiter Ký tự phân tách
     * @return Mảng string đã được tách
     */
    public static String[] splitString(String input, char delimiter) {
        if (input == null) {
            return new String[0];
        }

        // Sử dụng ArrayList thay vì Vector (hiện đại hơn và thread-safe không cần thiết)
        List<String> parts = new ArrayList<>();
        String trimmedInput = input.trim();
        int startIndex = 0;
        int delimiterIndex;

        // Tìm và tách các phần
        while ((delimiterIndex = trimmedInput.indexOf(delimiter, startIndex)) != -1) {
            String part = trimmedInput.substring(startIndex, delimiterIndex);
            parts.add(part);
            startIndex = delimiterIndex + 1;
        }

        // Thêm phần cuối cùng
        parts.add(trimmedInput.substring(startIndex));

        // Chuyển đổi sang mảng
        return parts.toArray(new String[0]);
    }

    public static int parseInt(String var0) {
        return Integer.parseInt(var0);
    }

    public static short parseShort(String var0) {
        return Short.parseShort(var0);
    }

    public static byte parseByte(String var0) {
        return Byte.parseByte(var0);
    }

    /**
     * Chuyển đổi chuỗi các số phân tách bằng dấu phẩy thành mảng byte
     *
     * @param input Chuỗi đầu vào (ví dụ: "10,20,30,127")
     * @return Mảng byte chứa các giá trị đã chuyển đổi
     * @throws IllegalArgumentException nếu input null hoặc chứa giá trị không hợp lệ
     * @throws NumberFormatException    nếu không thể parse thành số
     */
    public static byte[] parseStringToBytes(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        String[] stringParts = splitString(input, ',');
        byte[] byteArray = new byte[stringParts.length];

        for (int i = 0; i < byteArray.length; i++) {
            try {
                String trimmed = stringParts[i].trim();
                byteArray[i] = Byte.parseByte(trimmed);
            } catch (NumberFormatException e) {
                throw new NumberFormatException(
                        "Cannot parse '" + stringParts[i] + "' to byte at index " + i
                );
            }
        }

        return byteArray;
    }

    public static long[] convertTime(long milliseconds) {
        long[] result = new long[3];
        result[0] = milliseconds / 3600000L;                    // giờ
        result[1] = (milliseconds % 3600000L) / 60000L;         // phút còn lại
        result[2] = (milliseconds % 60000L) / 1000L;            // giây còn lại
        return result;
    }

    private static byte[] loadStream(String resourcePath) {
        try {
            if (!resourcePath.contains("img_")) {
                return processStream(resourcePath);
            } else {
                byte[] var1 = new byte[1024];
                ByteArrayOutputStream var2 = new ByteArrayOutputStream();
                InputStream var5 = ManagedInputStream.openStream(resourcePath);

                int var3;
                while ((var3 = var5.read(var1)) > 0) {
                    var2.write(var1, 0, var3);
                }

                return var2.toByteArray();
            }
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
