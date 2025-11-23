package engine.entity;

import engine.GameUtils;
import event.EventScript;
import game.GameWorldManager;
import me.kitakeyos.ManagedInputStream;

import javax.microedition.lcdui.Image;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResourceManager {
    public static short[][] spriteAnimationData;
    public static short[][] moduleInfoData;
    public static short[][][] gameDatabase;
    public static String[] localizedStrings;
    private static EventScript[] worldEvents;
    public static int[][] textureData;
    public static Image backgroundImage;
    private static Image[] imageCache;
    private static byte[] imageRefCounts;
    private static int maxCachedImages;

    public static void initializeAllResources() {
        spriteAnimationData = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/sprite.mid"));
        String moduleInfoPath = "/data/mod/modInfo.mid";

        try {
            InputStream inputStream = ManagedInputStream.openStream(moduleInfoPath);
            DataInputStream dataStream;
            byte moduleCount;
            moduleInfoData = new short[moduleCount = (dataStream = new DataInputStream(inputStream)).readByte()][];

            for (int i = 0; i < moduleCount; ++i) {
                byte paramCount = dataStream.readByte();
                moduleInfoData[i] = new short[paramCount];

                for (int j = 0; j < paramCount; ++j) {
                    short param = dataStream.readShort();
                    moduleInfoData[i][j] = param;
                }
            }

            dataStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loadLocalizationStrings("/data/script/chs.mid");
        loadNpcDialogs("/data/script/npcDialog.mid");
        loadTextureData();
        loadWorldEvents("/data/event/worldEvt.mid");
        loadGameDatabase("/data/script/db.mid");
        backgroundImage = GameUtils.loadImage("/data/tex/", "bk");
    }

    private static void loadNpcDialogs(String filePath) {
        String[][] dialogMatrix = GameUtils.readStringMatrix(GameUtils.openInputStream(filePath));
        GameWorldManager.C25_f349 = new String[dialogMatrix.length];

        for (int i = 0; i < dialogMatrix.length; ++i) {
            System.arraycopy(dialogMatrix[i], 0, GameWorldManager.C25_f349, i, dialogMatrix[i].length);
        }
    }

    public static short getDatabaseValue(byte category, short index, byte field) {
        return gameDatabase[category][index][field];
    }

    private static void loadGameDatabase(String filePath) {
        InputStream inputStream = GameUtils.openInputStream(filePath);
        gameDatabase = new short[9][][];

        for (int category = 0; category < 9; ++category) {
            gameDatabase[category] = GameUtils.readShortMatrix(inputStream);
        }
    }

    private static void loadLocalizationStrings(String filePath) {
        String[][] stringMatrix = GameUtils.readStringMatrix(GameUtils.openInputStream(filePath));
        localizedStrings = new String[stringMatrix.length];
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < stringMatrix.length; ++i) {
            stringBuilder.delete(0, stringBuilder.length());

            for (int j = 0; j < stringMatrix[i].length; ++j) {
                stringBuilder.append(stringMatrix[i][j]);
            }

            localizedStrings[i] = stringBuilder.toString();
        }
    }

    private static void loadTextureData() {
        textureData = new int[4][];

        for (int textureIndex = 0; textureIndex < 4; ++textureIndex) {
            textureData[textureIndex] = GameUtils.extractImageRGB(GameUtils.loadImage("/data/tex/", "tex_" + textureIndex));
        }
    }

    private static void loadWorldEvents(String filePath) {
        try {
            InputStream inputStream = ManagedInputStream.openStream(filePath);
            DataInputStream dataStream;
            short stringCount = (dataStream = new DataInputStream(inputStream)).readShort();
            String[] stringTable = null;
            if (stringCount > 0) {
                stringTable = new String[stringCount];

                for (int i = 0; i < stringCount; ++i) {
                    byte charCount = dataStream.readByte();
                    StringBuilder stringBuffer = new StringBuilder();

                    for (int j = 0; j < charCount; ++j) {
                        stringBuffer.append((char) (dataStream.read() << 8 | dataStream.read()));
                    }

                    stringTable[i] = stringBuffer.toString();
                }
            }

            if ((stringCount = dataStream.readByte()) > 0) {
                worldEvents = new EventScript[stringCount];

                for (byte eventIndex = 0; eventIndex < stringCount; ++eventIndex) {
                    worldEvents[eventIndex] = new EventScript();
                    worldEvents[eventIndex].loadFromStream(dataStream, eventIndex, -1, stringTable);
                }
            }

            dataStream.close();
            inputStream.close();
        } catch (Exception e) {
        }
    }

    public static void initializeImageCache(int maxImages) {
        maxCachedImages = maxImages;
        imageCache = new Image[maxCachedImages];
        imageRefCounts = new byte[maxCachedImages];
    }

    public static Image getCachedImage(int imageId) {
        if (imageCache[imageId] == null) {
            imageCache[imageId] = GameUtils.loadImage("/data/img/", "img_" + imageId);
        }

        ++imageRefCounts[imageId];
        return imageCache[imageId];
    }

    public static void releaseImageReference(int imageId) {
        --imageRefCounts[imageId];
        if (imageRefCounts[imageId] <= 0) {
            imageRefCounts[imageId] = 0;
        }

    }

    public static boolean forceReleaseImage(int imageId) {
        --imageRefCounts[imageId];
        if (imageRefCounts[imageId] <= 0) {
            imageRefCounts[imageId] = 0;
            imageCache[imageId] = null;
            return true;
        } else {
            return false;
        }
    }

    public static void clearImageCache() {
        for (int i = 0; i < maxCachedImages; ++i) {
            imageRefCounts[i] = 0;
            imageCache[i] = null;
        }

    }
}
