package game;

import engine.GameUtils;
import engine.GameEngineBase;
import engine.graphics.AudioManager;
import engine.graphics.DebugLogger;
import engine.graphics.GameObject;
import engine.graphics.EffectEntity;
import engine.graphics.ScreenTransitionManager;
import engine.graphics.WorldRenderer;
import engine.entity.RecordStoreManager;
import engine.entity.CameraController;
import engine.entity.GameEntity;
import engine.entity.ResourceManager;
import engine.entity.TileMapRenderer;
import layout.DialogManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import me.kitakeyos.ManagedInputStream;

public final class GameWorldManager extends GameEngineBase {
   private static GameWorldManager C25_f282;
   public TileMapRenderer C25_f283;
   private CameraController C25_f284;
   public WorldRenderer C25_f285;
   public PlayerCharacter C25_f286;
   public NPCEntity[] C25_f287;
   public Vector C25_f288;
   private int C25_f289;
   public int C25_f290;
   public int C25_f291;
   private int C25_f292;
   public short C25_f293;
   public short C25_f294;
   public int C25_f295;
   public String C25_f296;
   public static int[] C25_f297 = new int[]{0, 2, 9, 17, 25, 38, 45, 47, 60, 67, 75, 90};
   public static Image C25_f298 = null;
   private static Image C25_f299 = null;
   public static EffectEntity C25_f300 = null;
   private static RecordStoreManager[] C25_f301 = new RecordStoreManager[10];
   private static byte[][][] C25_f302 = null;
   private static short[][][] C25_f303 = null;
   private static boolean[][] C25_f304 = null;
   private static String[] C25_f305;
   private static short[][] C25_f306;
   private static Vector C25_f307 = new Vector();
   private static Vector C25_f308 = new Vector();
   private static Vector C25_f309 = new Vector();
   private static Vector C25_f310 = new Vector();
   public GameObject C25_f311;
   private GameObject C25_f312;
   public GameObject C25_f313;
   public static int C25_f314 = 0;
   public static byte C25_f315 = 0;
   public static byte C25_f316;
   public static byte C25_f317;
   public static short C25_f318 = -1;
   public static short C25_f319 = -1;
   public static byte C25_f320 = 0;
   public static boolean C25_f321 = false;
   public static int C25_f322 = -1;
   public static int C25_f323 = 0;
   public static int C25_f324 = 0;
   public static int C25_f325 = getScreenWidth();
   public static int C25_f326 = getScreenHeight();
   private static byte[] C25_f327 = new byte[]{9, 10, 11};
   private int[] C25_f328;
   private int[] C25_f329;
   private short[] C25_f330;
   private static String[] C25_f331 = new String[]{"PK6_RMS_ACTOR", "PK6_RMS_WORLD", "PK6_RMS_EVENT", "PK6_RMS_RMS", "PK6_RMS_SMS", "PK6_RMS_CNTSMS", "PK6_RMS_GOLD", "PK6_RMS_POKPET", "PK6_RMS_CONITEM", "PK6_RMS_PETBALL"};
   public static boolean C25_f332 = false;
   public static Vector C25_f333 = null;
   public static Vector C25_f334;
   public static byte C25_f335;
   public static Vector C25_f336 = null;
   public static byte C25_f337 = 0;
   public static boolean C25_f338 = false;
   protected static boolean C25_f339 = false;
   public static byte[] C25_f340 = new byte[2];
   private Image[] C25_f341;
   public AudioManager C25_f342;
   private static byte[][] C25_f343 = null;
   public static byte C25_f344 = -1;
   public static boolean C25_f345;
   private static byte[] C25_f346 = null;
   private static byte C25_f347 = -1;
   public QuestManager C25_f348;
   public static String[] C25_f349;
   private int C25_f350;
   private String C25_f351;
   private byte C25_f352;
   private byte C25_f353;
   private byte C25_f354;
   private byte C25_f355;
   private int C25_f356;
   private int C25_f357;
   private int C25_f358;
   private int C25_f359;
   private boolean C25_f360;
   private GameObject C25_f361;
   private GameObject[] C25_f362;
   private int C25_f363;
   private int[] C25_f364;
   private int[] C25_f365;
   private int[] C25_f366;
   private int[][] C25_f367;
   private int[] C25_f368;
   public static byte C25_f369 = -1;
   private boolean C25_f370;
   private boolean[] C25_f371;
   private byte[] C25_f372;
   private byte C25_f373;
   private byte C25_f374;
   private boolean C25_f375;

   public static GameWorldManager B() {
      if (C25_f282 == null) {
         C25_f282 = new GameWorldManager();
      }

      return C25_f282;
   }

   public GameWorldManager() {
      TextRenderingEngine.a();
      this.C25_f288 = new Vector();
      this.C25_f290 = 0;
      this.C25_f291 = 0;
      this.C25_f292 = 0;
      this.C25_f293 = 224;
      this.C25_f294 = 496;
      this.C25_f295 = -1;
      this.C25_f296 = "Gỗ thô";
      this.C25_f311 = null;
      this.C25_f312 = null;
      this.C25_f313 = null;
      this.C25_f328 = new int[]{21, 35, 50, 0, 45};
      this.C25_f329 = new int[]{9, 0, 20, 3, 9, 1, 17, 1, 9, 2, 9, 4, 9, 6, 86, 5, 9, 6, 58, 6, 9, 5, 21, 2, 9, 4, 3, 0};
      this.C25_f330 = new short[]{1, 5, 0, 616, 3, 6, 0, 617, 4, 0, 0, 618, 5, 2, 0, 619, 6, 0, 1, 620};
      this.C25_f342 = null;
      this.C25_f350 = 0;
      this.C25_f351 = "Ngoại trừ tiến hóa, sủng vật còn có thể dị hoá, dị hoá sau sủng vật đem càng cụ tính công kích. Mặt khác từng chủ thành liên minh huấn luyện sư cũng sẽ cung cấp tiến hóa cùng dị hoá phục vụ, ngươi có thể thường đi xem.";
      this.C25_f360 = false;
      this.C25_f361 = null;
      this.C25_f362 = null;
      this.C25_f363 = 8;
      this.C25_f364 = new int[]{2, 1, 73, 158, 3, 3, 216, 165, 4, 5, 161, 338, 5, 3, 111, 385, 5, 5, 112, 124, 6, 1, 140, 100, 7, 2, 48, 58};
      this.C25_f365 = new int[]{1, 5, 265, 113, 3, 6, 281, 192, 4, 0, 24, 144, 5, 2, 88, 175, 6, 0, 55, 190};
      this.C25_f366 = new int[]{16735795, 5708544, 5693667, 28273, 7796622, 1924393, 16774529, 7760896, 3291479, 10268671, 2038828, 13341951, 4443391, 16777215, 1862959, 13886935};
      this.C25_f367 = new int[][]{{0, 0, 1, 0, 386, 5, 5, 5, 0, 1, 1, 387, 5, 5, 0, 5, 1, 2, 388, 5, 5, 0, 10, 1, 3, 389, 5, 5, 5, 10, 1, 4, 390, 5, 5, 10, 10, 1, 5, 391, 5, 5, 10, 15, 1, 6, 392, 5, 5, 10, 20, -1, -1, 518, 5, 5}, {0, 0, -1, -1, 517, 5, 5, 0, 5, 2, 0, 393, 5, 5, 0, 10, 2, 1, 394, 5, 5, 0, 15, 2, 2, 395, 5, 5, 5, 10, 2, 3, 396, 5, 5, 5, 5, 2, 4, 397, 5, 5, 5, 15, 2, 5, 398, 5, 5, 5, 20, 2, 6, 399, 5, 5, 5, 25, 2, 7, 400, 5, 5}, {15, 0, -1, -1, 518, 5, 5, 15, 5, 3, 0, 401, 5, 5, 15, 10, 3, 1, 402, 5, 5, 10, 10, 3, 2, 403, 5, 5, 10, 15, 3, 3, 404, 5, 5, 5, 15, 3, 4, 405, 5, 5, 0, 15, 3, 5, 406, 5, 5, 15, 15, 3, 6, 407, 5, 5, 15, 20, 3, 7, 408, 5, 5}, {0, 15, 4, 0, 409, 5, 5, 5, 15, 4, 1, 410, 5, 5, 10, 15, 4, 5, 414, 5, 5, 10, 20, 4, 6, 415, 5, 5, 15, 20, 4, 7, 416, 5, 5, 20, 20, 4, 8, 417, 5, 5, 15, 15, 4, 9, 418, 5, 5, 20, 15, 4, 10, 419, 5, 5, 15, 10, 4, 11, 420, 5, 5, 15, 5, 4, 12, 421, 5, 5, 0, 10, 4, 2, 411, 5, 5, 5, 10, 4, 3, 412, 5, 5, 10, 10, 4, 4, 413, 5, 5, 15, 0, -1, -1, 524, 5, 5}, {10, 5, 5, 0, 422, 5, 5, 5, 5, 5, 1, 423, 5, 5, 0, 5, 5, 2, 424, 5, 5, 5, 0, 5, 3, 425, 5, 5, 15, 5, 5, 4, 426, 5, 5, 20, 5, 5, 5, 427, 5, 5, 18, 0, 5, 6, 428, 5, 5, 10, 10, -1, -1, 522, 5, 5}, {0, 5, 6, 0, 429, 5, 5, 0, 0, 6, 1, 430, 5, 5}, {5, 15, 7, 0, 431, 5, 5, 5, 10, 7, 1, 432, 5, 5, 5, 5, 7, 2, 433, 5, 5, 0, 5, 7, 3, 434, 5, 5, 0, 0, 7, 4, 435, 5, 5, 0, 10, 7, 5, 436, 5, 5, 0, 15, 7, 6, 437, 5, 5, 10, 5, 7, 7, 438, 5, 5, 10, 0, 7, 8, 439, 5, 5, 15, 0, 7, 9, 440, 5, 5, 15, 5, 7, 10, 441, 5, 5, 10, 10, 7, 11, 442, 5, 5, 10, 15, 7, 12, 443, 5, 5}, {5, 10, 8, 0, 444, 5, 5, 5, 15, 8, 1, 445, 5, 5, 0, 15, 8, 2, 446, 5, 5, 0, 10, 8, 3, 447, 5, 5, 0, 5, 8, 4, 448, 5, 5, 5, 5, 8, 5, 449, 5, 5, 5, 0, 8, 6, 450, 5, 5}};
      this.C25_f368 = new int[]{3, 5, 2, 6, 4, 5, 5, 5, 5, 3, 1, 2, 4, 4, 2, 4};
      this.C25_f370 = false;
      this.C25_f371 = new boolean[]{false, false, false, false, false, false, false};
      this.C25_f372 = new byte[]{10, 15, 20, 30, 40, 50, 100};
      this.C25_f375 = false;
      this.C25_f283 = TileMapRenderer.getInstance();
      this.C25_f284 = CameraController.getInstance();
      this.C25_f285 = new WorldRenderer();
   }

   public final boolean C() {
      return C25_f306[C25_f297[this.C25_f290] + this.C25_f291][2] != -1;
   }

   public static byte a(byte var0, byte var1) {
      if (C25_f347 != -1) {
         for(int var2 = 0; var2 < C25_f347; ++var2) {
            if (C25_f346[var2 * 3] == var0) {
               switch(var1) {
               case 0:
                  return C25_f346[var2 * 3 + 1];
               case 1:
                  return C25_f346[var2 * 3 + 2];
               }
            }
         }
      }

      return -1;
   }

   public final boolean initializeGame() {
      try {
         this.startGameTimer();
         if (C25_f301 == null) {
            C25_f301 = new RecordStoreManager[10];
         }

         for(int var2 = 0; var2 < C25_f301.length; ++var2) {
            if (C25_f301[var2] == null) {
               C25_f301[var2] = new RecordStoreManager(C25_f331[var2], 1);
            }
         }

         this.C25_f348 = QuestManager.getInstance();
         this.C25_f348.setGameEngine((GameEngineBase)this);
         this.C25_f286 = PlayerCharacter.p();
         C25_f340[0] = C25_f340[1] = -1;
         if (QuestManager.questState != 0) {
            ResourceManager.clearImageCache();
         }

         if (C25_f302 == null) {
            C25_f302 = new byte[127][][];
            C25_f303 = new short[127][][];
            C25_f304 = new boolean[127][2];
         }

         if (!this.C25_f286.C53_f776) {
            if (inputEnabled) {
               this.R();
               this.T();
            }

            V();
         }

         this.a(this.C25_f290, this.C25_f291, "/data/event/");
         ScreenTransitionManager.b();
         GameWorldManager var1 = this;
         C25_f306 = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/petArea.mid"));
         int var3;
         if (this.C()) {
            int[] var7 = new int[C25_f306[C25_f297[this.C25_f290] + this.C25_f291].length - 5];

            for(var3 = 0; var3 < var7.length; ++var3) {
               var7[var3] = C25_f306[C25_f297[var1.C25_f290] + var1.C25_f291][var3 + 5];
            }

            for(var3 = 0; var3 < var7.length / 4; ++var3) {
               int[] var6 = new int[4];
               switch(var7[(var3 << 2) + 1]) {
               case 0:
                  System.arraycopy(var7, var3 << 2, var6, 0, var6.length);
                  C25_f307.addElement(var6);
                  break;
               case 1:
                  System.arraycopy(var7, var3 << 2, var6, 0, var6.length);
                  C25_f308.addElement(var6);
                  break;
               case 2:
                  System.arraycopy(var7, var3 << 2, var6, 0, var6.length);
                  C25_f309.addElement(var6);
               case 3:
               default:
                  break;
               case 4:
                  System.arraycopy(var7, var3 << 2, var6, 0, var6.length);
                  C25_f310.addElement(var6);
               }
            }
         }

         var1 = this;
         byte[][] var10000 = C25_f343 = GameUtils.readByteMatrix(GameUtils.openInputStream("/data/script/media.mid"));
         int var4 = this.C25_f291;
         var3 = this.C25_f290;
         byte var9;
         if ((var9 = var10000[C25_f297[var3] + var4][0]) == -1) {
            C25_f345 = false;
         } else {
            var4 = this.C25_f291;
            var3 = this.C25_f290;
            if (this.C25_f348.questStates[C25_f297[var3] + var4][var9] == 3) {
               C25_f345 = false;
            } else {
               C25_f345 = true;
            }
         }

         var4 = this.C25_f291;
         var3 = this.C25_f290;
         C25_f344 = C25_f343[C25_f297[var3] + var4][1];
         var4 = this.C25_f291;
         var3 = this.C25_f290;
         int var14 = C25_f297[var3] + var4;
         byte[] var11;
         if ((C25_f347 = C25_f343[e(this.C25_f290, this.C25_f291)][3]) == -1) {
            if (!this.C()) {
               (var11 = new byte[1])[0] = C25_f344;
            } else {
               (var11 = new byte[2])[0] = C25_f344;
               var11[1] = C25_f344;
            }
         } else {
            if (!this.C()) {
               var11 = new byte[(C25_f347 << 1) + 1];

               for(var3 = 0; var3 < C25_f347; ++var3) {
                  var11[var3 << 1] = C25_f343[e(var1.C25_f290, var1.C25_f291)][4 + var3 * 3 + 1];
                  var11[(var3 << 1) + 1] = C25_f343[e(var1.C25_f290, var1.C25_f291)][4 + var3 * 3 + 2];
               }

               var11[C25_f347 << 1] = C25_f344;
            } else {
               var11 = new byte[(C25_f347 << 1) + 2];

               for(var3 = 0; var3 < C25_f347; ++var3) {
                  var11[var3 << 1] = C25_f343[e(var1.C25_f290, var1.C25_f291)][4 + var3 * 3 + 1];
                  var11[(var3 << 1) + 1] = C25_f343[e(var1.C25_f290, var1.C25_f291)][4 + var3 * 3 + 2];
               }

               var11[C25_f347 << 1] = C25_f344;
               var11[(C25_f347 << 1) + 1] = 4;
            }

            C25_f346 = new byte[C25_f347 * 3];
            System.arraycopy(C25_f343[e(var1.C25_f290, var1.C25_f291)], 4, C25_f346, 0, C25_f347 * 3);
         }

         if (var1.C25_f342 == null) {
            var1.C25_f342 = new AudioManager(7, -1, (byte)0, "/data/sound/");
         }

         var1.C25_f342.preloadMusicTracks(var11);
         var1.C25_f342.setMasterVolume(GameScreenManager.getInstance().difficultyLevel);
         var1 = this;
         InputStream var12 = GameUtils.openInputStream("/data/script/petRide.mid");
         this.C25_f286.C53_f794 = GameUtils.readByteMatrix(var12)[C25_f297[this.C25_f290] + this.C25_f291];
         if (this.C25_f286.C53_f765 >= 0 && !this.C25_f286.g(this.C25_f286.C53_f765)) {
            this.C25_f286.t();
         }

         this.C25_f352 = this.C25_f286.C53_f794[4];
         this.C25_f353 = -1;

         for(var9 = 0; var9 < var1.C25_f364.length / 4; ++var9) {
            if (var1.C25_f290 == var1.C25_f364[var9 << 2] && var1.C25_f291 == var1.C25_f364[(var9 << 2) + 1]) {
               var1.C25_f353 = var9;
               break;
            }
         }

         var1.C25_f354 = -1;

         for(var9 = 0; var9 < var1.C25_f365.length / 4; ++var9) {
            if (var1.C25_f290 == var1.C25_f365[var9 << 2] && var1.C25_f291 == var1.C25_f365[(var9 << 2) + 1]) {
               var1.C25_f354 = var9;
               break;
            }
         }

         var1 = this;
         C25_f298 = null;
         short[][] var13 = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/backPic.mid"));

         for(var3 = 0; var3 < var13.length; ++var3) {
            if (var13[var3][0] == var1.C25_f290 && var13[var3][1] == var1.C25_f291) {
               if (var13[var3][2] == 0) {
                  C25_f298 = GameUtils.loadImage("/data/img/", "img_" + var13[var3][3]);
               } else if (var13[var3][2] == 1) {
                  GameEngineBase.setBackgroundColor(var13[var3][3] << 16 | var13[var3][4] << 8 | var13[var3][5]);
               }
               break;
            }

            GameEngineBase.setBackgroundColor(2996676);
         }

         C25_f299 = GameUtils.loadImage("/data/tex/", "gold");
         GameUtils.loadImage("/data/img/", "img_10023");
         if (this.C25_f286.C53_f774 > 0) {
            this.e(true);
         }

         this.C25_f296 = getLocalizedText(384 + C25_f297[this.C25_f290] + this.C25_f291);
         this.C25_f283.loadMap(this.C25_f289);
         this.C25_f283.setCameraPosition(0, 0);
         this.C25_f285.setTileMapRenderer(this.C25_f283);
         this.ae();
         if (C25_f321) {
            QuestManager.isQuestActive = false;
            this.ad();
            this.C25_f284.setFollowEntity(this.C25_f286, true);
            this.C25_f285.setCameraController(this.C25_f284);
            this.C25_f285.updateWorld();
         } else {
            if (!this.C25_f286.C53_f776) {
               short[] var8 = new short[]{this.C25_f293, this.C25_f294, (short)C25_f320, 4, 4, 8, 40, 100, 0};
               this.C25_f286.a(var8);
               this.C25_f286.H();
            }

            if (C25_f322 == -1) {
               this.C25_f284.setFixedPosition(C25_f323, C25_f324, true);
               this.C25_f285.setCameraController(this.C25_f284);
               this.C25_f285.updateWorld();
            } else {
               this.C25_f284.setFollowEntity(this.C25_f287[C25_f322], true);
               this.C25_f285.setCameraController(this.C25_f284);
               this.C25_f285.updateWorld();
            }

            this.C25_f286.setAlternativeRender(false);
            C25_f321 = true;
         }

         this.P();
         if (this.C25_f290 == 3 && this.C25_f291 == 7) {
            if (this.C25_f286.C53_f775 > 0) {
               this.C25_f286.C53_f775 = 0;
               this.C25_f286.applyBrightnessEffect(0);
            }

            this.C25_f341 = new Image[4];

            for(int var10 = 0; var10 < this.C25_f341.length; ++var10) {
               this.C25_f341[var10] = GameUtils.loadImage("/data/tex/", "down" + var10);
            }

            this.C25_f286.t();
            this.C25_f286.h(0);
         }

         if (this.C25_f290 == 5 && this.C25_f291 == 6 || this.C25_f290 == 4 && (this.C25_f291 == 3 || this.C25_f291 == 4)) {
            if (this.C25_f286.C53_f779[0][0] == 2) {
               ScreenTransitionManager.a().a((byte)0, this.C25_f284.getWorldX(), this.C25_f284.getWorldY() - this.C25_f328[this.C25_f286.C53_f765 + 1], getScreenWidth(), getScreenHeight(), 110, 110);
            } else {
               ScreenTransitionManager.a().a((byte)0, this.C25_f284.getWorldX(), this.C25_f284.getWorldY() - this.C25_f328[this.C25_f286.C53_f765 + 1], getScreenWidth(), getScreenHeight(), 50, 50);
            }
         } else {
            ScreenTransitionManager.a().a((byte)-1);
         }

         this.gameController = DialogUIManager.a();
         this.gameController.a((GameEngineBase)this);
         this.dialogManager = DialogManager.getInstance();
         this.C25_f348.updateQuestEffects();
         this.C25_f348.update();
         C25_f338 = true;
         this.changeState((byte)0);
         if (QuestManager.questState == 2) {
            QuestManager.questState = 0;
         }

         if (!C25_f345) {
            this.C25_f342.playBackgroundMusic(C25_f344, 1);
         }

         stopGameTimer();
      } catch (Exception var5) {
         DebugLogger.logError(var5, "init");
      }

      return true;
   }

   private void P() {
      for(int var1 = 0; var1 < this.C25_f287.length; ++var1) {
         this.C25_f287[var1].updateInteractableState();
         this.C25_f285.addEntity((GameEntity)this.C25_f287[var1]);
      }

   }

   public final void a(byte var1, int var2, int var3, GameObject var4) {
      if (this.C25_f313 == null) {
         this.C25_f313 = new GameObject();
         this.C25_f313.loadSpriteSet(259, false);
         this.C25_f313.sprite.setAnimationProperties((byte)13, (byte)-1);
         this.C25_f285.addEntity((GameEntity)this.C25_f313);
         this.C25_f313.sprite.applyColorEffects();
         this.C25_f313.layer = 0;
         this.C25_f313.activate();
      }

      this.C25_f313.setWorldPosition(var2, var3);
      this.C25_f313.followTarget = var4;
   }

   public final void D() {
      if (this.C25_f313 != null) {
         this.C25_f285.removeEntity((GameEntity)this.C25_f313);
         this.C25_f313 = null;
      }

   }

   public final void l(int var1) {
      if (this.C25_f311 == null) {
         this.C25_f311 = new GameObject();
         this.C25_f311.loadSpriteSet(var1, false);
         this.C25_f311.sprite.applyColorEffects();
         this.C25_f311.layer = 1;
      }

   }

   public final void a(GameObject var1) {
      if (this.C25_f311 != null) {
         this.C25_f312 = var1;
         this.C25_f311.followTarget = var1;
         this.C25_f311.initializeMovementTrail(var1.sprite.getCurrentAnimationId());
         this.C25_f311.activate();
         this.C25_f285.addEntity((GameEntity)this.C25_f311);
         if (C25_f298 != null) {
            this.C25_f311.setAlternativeRender(true);
         } else {
            this.C25_f311.setAlternativeRender(false);
         }
      }
   }

   public final void E() {
      if (this.C25_f311 != null) {
         this.C25_f285.removeEntity((GameEntity)this.C25_f311);
         this.C25_f311 = null;
      }

   }

   private boolean a(PlayerCharacter var1) {
      try {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         DataOutputStream var3 = new DataOutputStream(var2);
         if (this.C25_f290 == 9) {
            var3.writeShort(super.gameController.C9_f158[(this.C25_f291 << 2) + 2]);
            var3.writeShort(super.gameController.C9_f158[(this.C25_f291 << 2) + 3]);
            var3.writeByte(2);
         } else if (this.C25_f290 == 3 && this.C25_f291 == 7) {
            var3.writeShort(240);
            var3.writeShort(40);
            var3.writeByte(0);
         } else {
            var3.writeShort(var1.worldX);
            var3.writeShort(var1.worldY);
            var3.writeByte(var1.currentDirection);
         }

         int var4;
         int var5;
         for(var4 = 0; var4 < var1.C53_f779.length; ++var4) {
            for(var5 = 0; var5 < var1.C53_f779[var4].length; ++var5) {
               var3.writeByte(var1.C53_f779[var4][var5]);
            }
         }

         for(var4 = 0; var4 < var1.C53_f793.length; ++var4) {
            var3.writeByte(var1.C53_f793[var4]);
         }

         for(var4 = 0; var4 < var1.C53_f780.length; ++var4) {
            for(var5 = 0; var5 < var1.C53_f780[var4].length; ++var5) {
               var3.writeByte(var1.C53_f780[var4][var5]);
            }
         }

         for(var4 = 0; var4 < var1.C53_f782.length; ++var4) {
            var3.writeByte(var1.C53_f782[var4]);
         }

         for(var4 = 0; var4 < var1.C53_f781.length; ++var4) {
            for(var5 = 0; var5 < var1.C53_f781[var4].length; ++var5) {
               var3.writeByte(this.C25_f286.C53_f781[var4][var5]);
            }
         }

         var3.writeByte(this.C25_f286.C53_f785);
         var3.writeByte(this.C25_f286.C53_f784);
         var3.writeByte(this.C25_f286.C53_f783);
         var3.writeByte(this.C25_f286.C53_f786);

         for(var4 = 0; var4 < this.C25_f286.C53_f795.length; ++var4) {
            var3.writeByte(this.C25_f286.C53_f795[var4]);
         }

         if (!this.H()) {
            return false;
         } else if (!this.aa()) {
            return false;
         } else if (!this.Y()) {
            return false;
         } else {
            var3.writeInt(var1.C53_f789.size());

            int var6;
            int[] var12;
            for(var4 = 0; var4 < var1.C53_f789.size(); ++var4) {
               var12 = (int[])var1.C53_f789.elementAt(var4);
               var3.writeInt(var12.length);

               for(var6 = 0; var6 < var12.length; ++var6) {
                  var3.writeInt(var12[var6]);
               }
            }

            var3.writeInt(var1.C53_f790.size());

            for(var4 = 0; var4 < var1.C53_f790.size(); ++var4) {
               var12 = (int[])var1.C53_f790.elementAt(var4);
               var3.writeInt(var12.length);

               for(var6 = 0; var6 < var12.length; ++var6) {
                  var3.writeInt(var12[var6]);
               }
            }

            var3.writeInt(var1.C53_f791.size());

            for(var4 = 0; var4 < var1.C53_f791.size(); ++var4) {
               var12 = (int[])var1.C53_f791.elementAt(var4);
               var3.writeInt(var12.length);

               for(var6 = 0; var6 < var12.length; ++var6) {
                  var3.writeInt(var12[var6]);
               }
            }

            for(var4 = 0; var4 < var1.C53_f797.length; ++var4) {
               var3.writeBoolean(var1.C53_f797[var4]);
            }

            if (!this.W()) {
               return false;
            } else {
               if (C25_f334 == null) {
                  C25_f334 = new Vector();
               }

               var3.writeByte(C25_f334.size());

               for(var4 = 0; var4 < C25_f334.size(); ++var4) {
                  String var13 = (String)C25_f334.elementAt(var4);
                  var3.writeByte(GameUtils.parseByte(var13));
               }

               for(var4 = 0; var4 < this.C25_f371.length; ++var4) {
                  var3.writeBoolean(this.C25_f371[var4]);
               }

               if (this.C25_f311 == null) {
                  var3.writeByte(-1);
               } else {
                  var3.writeByte(this.C25_f311.sprite.spriteSetId);
               }

               var3.write(this.C25_f286.C53_f768);
               var3.writeInt(C25_f314);
               var3.writeBoolean(C25_f339);
               long var9 = GameScreenManager.getInstance().storyStartTime + GameScreenManager.getInstance().worldMapTime - GameScreenManager.getInstance().currentTime;
               var3.writeLong(var9);
               var3.writeByte(this.C25_f286.C53_f765);
               var3.writeInt(var1.C53_f792.size());

               for(var4 = 0; var4 < var1.C53_f792.size(); ++var4) {
                  int[] var14 = (int[])var1.C53_f792.elementAt(var4);
                  var3.writeInt(var14.length);

                  for(var5 = 0; var5 < var14.length; ++var5) {
                     var3.writeInt(var14[var5]);
                  }
               }

               C25_f301[0].writeData(var2);
               var2.close();
               var3.close();
               return true;
            }
         }
      } catch (Exception var11) {
         System.out.println(" savePlayer ex = " + var11);
         return false;
      }
   }

   private boolean b(PlayerCharacter var1) {
      try {
         ByteArrayInputStream var2 = new ByteArrayInputStream(C25_f301[0].readData());
         DataInputStream var3 = new DataInputStream(var2);
         this.C25_f293 = var3.readShort();
         this.C25_f294 = var3.readShort();
         short var4 = (short)var3.readByte();

         int var5;
         int var6;
         for(var5 = 0; var5 < var1.C53_f779.length; ++var5) {
            for(var6 = 0; var6 < var1.C53_f779[var5].length; ++var6) {
               var1.C53_f779[var5][var6] = var3.readByte();
            }
         }

         for(var5 = 0; var5 < var1.C53_f793.length; ++var5) {
            var1.C53_f793[var5] = var3.readByte();
         }

         for(var5 = 0; var5 < var1.C53_f780.length; ++var5) {
            for(var6 = 0; var6 < var1.C53_f780[var5].length; ++var6) {
               var1.C53_f780[var5][var6] = var3.readByte();
            }
         }

         for(var5 = 0; var5 < var1.C53_f782.length; ++var5) {
            var1.C53_f782[var5] = var3.readByte();
         }

         for(var5 = 0; var5 < var1.C53_f781.length; ++var5) {
            for(var6 = 0; var6 < var1.C53_f781[var5].length; ++var6) {
               this.C25_f286.C53_f781[var5][var6] = var3.readByte();
            }
         }

         this.C25_f286.C53_f785 = var3.readByte();
         this.C25_f286.C53_f784 = var3.readByte();
         this.C25_f286.C53_f783 = var3.readByte();
         this.C25_f286.C53_f786 = var3.readByte();

         for(var5 = 0; var5 < this.C25_f286.C53_f795.length; ++var5) {
            this.C25_f286.C53_f795[var5] = (short)var3.readByte();
         }

         this.ac();
         this.ab();
         this.Z();
         var5 = var3.readInt();
         var1.C53_f789.removeAllElements();

         int[] var7;
         int var8;
         for(var6 = 0; var6 < var5; ++var6) {
            var7 = new int[var3.readInt()];

            for(var8 = 0; var8 < var7.length; ++var8) {
               var7[var8] = var3.readInt();
            }

            var1.C53_f789.addElement(var7);
         }

         var5 = var3.readInt();
         var1.C53_f790.removeAllElements();

         for(var6 = 0; var6 < var5; ++var6) {
            var7 = new int[var3.readInt()];

            for(var8 = 0; var8 < var7.length; ++var8) {
               var7[var8] = var3.readInt();
            }

            var1.C53_f790.addElement(var7);
         }

         var5 = var3.readInt();
         var1.C53_f791.removeAllElements();

         for(var6 = 0; var6 < var5; ++var6) {
            var7 = new int[var3.readInt()];

            for(var8 = 0; var8 < var7.length; ++var8) {
               var7[var8] = var3.readInt();
            }

            var1.C53_f791.addElement(var7);
         }

         for(var6 = 0; var6 < var1.C53_f797.length; ++var6) {
            var1.C53_f797[var6] = var3.readBoolean();
         }

         this.X();
         if (C25_f333 == null) {
            C25_f333 = new Vector();
         }

         C25_f333.removeAllElements();
         byte var11;
         var7 = new int[var11 = var3.readByte()];

         for(var6 = 0; var6 < var11; ++var6) {
            var7[var6] = var3.readByte();
            if (this.C25_f286.C53_f777[var7[var6]] != null) {
               this.C25_f286.C53_f777[var7[var6]].x();
               C25_f333.addElement(this.C25_f286.C53_f777[var7[var6]]);
            }
         }

         for(var6 = 0; var6 < this.C25_f371.length; ++var6) {
            this.C25_f371[var6] = var3.readBoolean();
         }

         byte var12;
         if ((var12 = var3.readByte()) != -1) {
            this.l(var12);
         }

         var1.C53_f768 = var3.readByte();
         C25_f314 = var3.readInt();
         C25_f339 = var3.readBoolean();
         GameScreenManager var10000 = GameScreenManager.getInstance();
         var10000.storyStartTime += var3.readLong();
         var1.C53_f765 = var3.readByte();
         var1.a(new short[]{this.C25_f293, this.C25_f294, (short)var4, 4, 4, 8, 40, 100, 0});
         if ((var6 = var3.readInt()) > 0) {
            var1.C53_f792.removeAllElements();

            for(int var10 = 0; var10 < var6; ++var10) {
               var7 = new int[var3.readInt()];

               for(var5 = 0; var5 < var7.length; ++var5) {
                  var7[var5] = var3.readInt();
               }

               var1.C53_f792.addElement(var7);
            }
         }

         var2.close();
         var3.close();
         return true;
      } catch (Exception var9) {
         System.out.println(" loadPlayer ex = " + var9);
         return false;
      }
   }

   private boolean Q() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.C25_f290);
         var2.writeInt(this.C25_f291);

         int var3;
         int var4;
         int var5;
         for(var3 = 0; var3 < C25_f302.length; ++var3) {
            if (C25_f302[var3] == null) {
               var2.writeShort(-1);
            } else {
               var2.writeShort(C25_f302[var3].length);

               for(var4 = 0; var4 < C25_f302[var3].length; ++var4) {
                  if (C25_f302[var3][var4] == null) {
                     var2.writeByte(-1);
                  } else {
                     var2.writeByte(C25_f302[var3][var4].length);

                     for(var5 = 0; var5 < C25_f302[var3][var4].length; ++var5) {
                        var2.writeByte(C25_f302[var3][var4][var5]);
                     }
                  }
               }
            }
         }

         for(var3 = 0; var3 < C25_f303.length; ++var3) {
            if (C25_f303[var3] == null) {
               var2.writeShort(-1);
            } else {
               var2.writeShort(C25_f303[var3].length);

               for(var4 = 0; var4 < C25_f303[var3].length; ++var4) {
                  if (C25_f303[var3][var4] == null) {
                     var2.writeByte(-1);
                  } else {
                     var2.writeByte(C25_f303[var3][var4].length);

                     for(var5 = 0; var5 < C25_f303[var3][var4].length; ++var5) {
                        var2.writeShort(C25_f303[var3][var4][var5]);
                     }
                  }
               }
            }
         }

         C25_f301[1].writeData(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         return false;
      }
   }

   private boolean R() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(C25_f301[1].readData());
         DataInputStream var2 = new DataInputStream(var1);
         this.C25_f290 = var2.readInt();
         this.C25_f291 = var2.readInt();

         short var3;
         int var4;
         int var5;
         byte var7;
         int var8;
         for(var4 = 0; var4 < C25_f302.length; ++var4) {
            if ((var3 = var2.readShort()) == -1) {
               C25_f302[var4] = null;
            } else {
               C25_f302[var4] = new byte[var3][];

               for(var5 = 0; var5 < C25_f302[var4].length; ++var5) {
                  if ((var7 = var2.readByte()) == -1) {
                     C25_f302[var4][var5] = null;
                  } else {
                     C25_f302[var4][var5] = new byte[var7];

                     for(var8 = 0; var8 < C25_f302[var4][var5].length; ++var8) {
                        C25_f302[var4][var5][var8] = var2.readByte();
                     }
                  }
               }
            }
         }

         for(var4 = 0; var4 < C25_f303.length; ++var4) {
            if ((var3 = var2.readShort()) == -1) {
               C25_f303[var4] = null;
            } else {
               C25_f303[var4] = new short[var3][];

               for(var5 = 0; var5 < C25_f303[var4].length; ++var5) {
                  if ((var7 = var2.readByte()) == -1) {
                     C25_f303[var4][var5] = null;
                  } else {
                     C25_f303[var4][var5] = new short[var7];

                     for(var8 = 0; var8 < C25_f303[var4][var5].length; ++var8) {
                        C25_f303[var4][var5][var8] = var2.readShort();
                     }
                  }
               }
            }
         }

         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         System.out.println(" sceneId ex = " + var6);
         return false;
      }
   }

   private boolean S() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2 = new DataOutputStream(var1);

         int var3;
         int var4;
         for(var3 = 0; var3 < this.C25_f348.questStates.length; ++var3) {
            if (this.C25_f348.questStates[var3] == null) {
               var2.writeByte(-1);
            } else {
               var2.writeByte(this.C25_f348.questStates[var3].length);

               for(var4 = 0; var4 < this.C25_f348.questStates[var3].length; ++var4) {
                  var2.writeByte(this.C25_f348.questStates[var3][var4]);
               }
            }
         }

         var2.writeByte(QuestManager.questDialogState);
         var2.writeByte(QuestManager.questFlagCount);

         for(var3 = 0; var3 < QuestManager.questFlagCount; ++var3) {
            var2.writeShort(QuestManager.questFlags[var3][0]);
            var2.writeShort(QuestManager.questFlags[var3][1]);
         }

         int[] var6;
         if ((var6 = this.C25_f348.getSavedTimeArray()) == null) {
            var2.writeByte(-1);
         } else {
            var2.writeByte(var6.length);

            for(var4 = 0; var4 < var6.length; ++var4) {
               var2.writeInt(var6[var4]);
            }

            var2.writeByte(this.C25_f348.questProgress);
         }

         C25_f301[2].writeData(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var5) {
         return false;
      }
   }

   private boolean T() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(C25_f301[2].readData());
         DataInputStream var2 = new DataInputStream(var1);

         int var4;
         for(var4 = 0; var4 < this.C25_f348.questStates.length; ++var4) {
            byte var3;
            if ((var3 = var2.readByte()) == -1) {
               this.C25_f348.questStates[var4] = null;
            } else {
               this.C25_f348.questStates[var4] = new byte[var3];

               for(int var8 = 0; var8 < this.C25_f348.questStates[var4].length; ++var8) {
                  this.C25_f348.questStates[var4][var8] = var2.readByte();
               }
            }
         }

         QuestManager.questDialogState = var2.readByte();
         QuestManager.questFlagCount = var2.readByte();

         for(var4 = 0; var4 < QuestManager.questFlagCount; ++var4) {
            QuestManager.questFlags[var4][0] = var2.readShort();
            QuestManager.questFlags[var4][1] = var2.readShort();
         }

         byte var10;
         if ((var10 = var2.readByte()) != -1) {
            int[] var9 = new int[var10];
            int[] var5 = this.C25_f348.getCurrentTimeArray();

            for(int var6 = 0; var6 < var10; ++var6) {
               var9[var6] = var2.readInt();
            }

            this.C25_f348.setSavedTimeArray(var9);
            boolean var11 = false;
            if (var5[0] > var9[0] || var5[1] > var9[1] || var5[2] > var9[2] || var5[3] - var9[3] >= 20) {
               var11 = true;
            }

            this.C25_f348.questProgress = var2.readByte();
            if (var11) {
               this.C25_f348.questProgress = 0;
            }
         }

         C25_f321 = true;
         var1.close();
         var2.close();
         return true;
      } catch (IOException var7) {
         return false;
      }
   }

   public static boolean F() {
      try {
         ByteArrayOutputStream var0 = new ByteArrayOutputStream();
         DataOutputStream var1 = new DataOutputStream(var0);
         inputEnabled = true;
         var1.writeBoolean(inputEnabled);
         var1.writeBoolean(C25_f321);
         var1.writeBoolean(C25_f332);
         var1.writeByte(C25_f335);
         C25_f301[3].writeData(var0);
         var0.close();
         var1.close();
         return true;
      } catch (IOException var2) {
         return false;
      }
   }

   public static boolean G() {
      if (C25_f301[3] == null) {
         C25_f301[3] = new RecordStoreManager(C25_f331[3], 1);
      }

      try {
         ByteArrayInputStream var0 = new ByteArrayInputStream(C25_f301[3].readData());
         DataInputStream var1;
         inputEnabled = (var1 = new DataInputStream(var0)).readBoolean();
         C25_f321 = var1.readBoolean();
         C25_f332 = var1.readBoolean();
         C25_f335 = var1.readByte();
         var0.close();
         var1.close();
         return true;
      } catch (IOException var2) {
         inputEnabled = false;
         System.out.println(" isHaveSms = " + inputEnabled);
         return false;
      }
   }

   private static boolean U() {
      try {
         ByteArrayOutputStream var0 = new ByteArrayOutputStream();
         DataOutputStream var1;
         (var1 = new DataOutputStream(var0)).writeBoolean(paymentActive);
         C25_f301[4].writeData(var0);
         var0.close();
         var1.close();
         return true;
      } catch (IOException var2) {
         return false;
      }
   }

   private static boolean V() {
      try {
         ByteArrayInputStream var0 = new ByteArrayInputStream(C25_f301[4].readData());
         DataInputStream var1;
         paymentActive = (var1 = new DataInputStream(var0)).readBoolean();
         var0.close();
         var1.close();
         return true;
      } catch (IOException var2) {
         return false;
      }
   }

   private boolean W() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.C25_f286.F());
         var2.writeInt(this.C25_f286.G());
         C25_f301[6].writeData(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var3) {
         return false;
      }
   }

   private boolean X() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(C25_f301[6].readData());
         DataInputStream var2 = new DataInputStream(var1);
         this.C25_f286.t(0);
         this.C25_f286.w(0);
         this.C25_f286.s(var2.readInt());
         this.C25_f286.v(var2.readInt());
         var1.close();
         var2.close();
         return true;
      } catch (IOException var3) {
         return false;
      }
   }

   private boolean Y() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.C25_f286.C53_f788.size());

         for(int var3 = 0; var3 < this.C25_f286.C53_f788.size(); ++var3) {
            int[] var4 = (int[])this.C25_f286.C53_f788.elementAt(var3);
            var2.writeInt(var4.length);

            for(int var5 = 0; var5 < var4.length; ++var5) {
               var2.writeInt(var4[var5]);
            }
         }

         C25_f301[9].writeData(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         return false;
      }
   }

   private boolean Z() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(C25_f301[9].readData());
         DataInputStream var2;
         int var3 = (var2 = new DataInputStream(var1)).readInt();
         this.C25_f286.C53_f788.removeAllElements();

         for(int var4 = 0; var4 < var3; ++var4) {
            int[] var5 = new int[var2.readInt()];

            for(int var6 = 0; var6 < var5.length; ++var6) {
               var5[var6] = var2.readInt();
            }

            this.C25_f286.C53_f788.addElement(var5);
         }

         var1.close();
         var2.close();
         return true;
      } catch (IOException var7) {
         return false;
      }
   }

   private boolean aa() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.C25_f286.C53_f787.size());

         for(int var3 = 0; var3 < this.C25_f286.C53_f787.size(); ++var3) {
            int[] var4 = (int[])this.C25_f286.C53_f787.elementAt(var3);
            var2.writeInt(var4.length);

            for(int var5 = 0; var5 < var4.length; ++var5) {
               var2.writeInt(var4[var5]);
            }
         }

         C25_f301[8].writeData(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         return false;
      }
   }

   private boolean ab() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(C25_f301[8].readData());
         DataInputStream var2;
         int var3 = (var2 = new DataInputStream(var1)).readInt();
         this.C25_f286.C53_f787.removeAllElements();

         for(int var4 = 0; var4 < var3; ++var4) {
            int[] var5 = new int[var2.readInt()];

            for(int var6 = 0; var6 < var5.length; ++var6) {
               var5[var6] = var2.readInt();
            }

            this.C25_f286.C53_f787.addElement(var5);
         }

         var1.close();
         var2.close();
         return true;
      } catch (IOException var7) {
         return false;
      }
   }

   public final boolean H() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeByte(this.C25_f286.C53_f778);

         for(int var3 = 0; var3 < this.C25_f286.C53_f778; ++var3) {
            int[] var4 = this.C25_f286.C53_f777[var3].Q();
            var2.writeInt(var4.length);

            for(int var5 = 0; var5 < var4.length; ++var5) {
               var2.writeInt(var4[var5]);
            }
         }

         C25_f301[7].writeData(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         return false;
      }
   }

   private boolean ac() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(C25_f301[7].readData());
         DataInputStream var2;
         byte var3 = (var2 = new DataInputStream(var1)).readByte();

         int var4;
         for(var4 = 0; var4 < this.C25_f286.C53_f778; ++var4) {
            this.C25_f286.C53_f777[var4] = null;
         }

         this.C25_f286.C53_f778 = 0;

         for(var4 = 0; var4 < var3; ++var4) {
            int[] var5 = new int[var2.readInt()];

            for(int var6 = 0; var6 < var5.length; ++var6) {
               var5[var6] = var2.readInt();
            }

            this.C25_f286.a(var5);
         }

         var1.close();
         var2.close();
         return true;
      } catch (IOException var7) {
         return false;
      }
   }

   public final boolean I() {
      if (!this.a(this.C25_f286)) {
         return false;
      } else if (!this.Q()) {
         return false;
      } else if (!this.S()) {
         return false;
      } else if (!F()) {
         return false;
      } else {
         return U();
      }
   }

   public final boolean J() {
      if (!U()) {
         return false;
      } else if (!this.W()) {
         return false;
      } else {
         return this.Y();
      }
   }

   public static void K() {
      C25_f302 = null;
      C25_f303 = null;

      for(int var0 = 0; var0 < 10; ++var0) {
         if (var0 != 4 && C25_f301[var0] != null) {
            C25_f301[var0].deleteStore();
            C25_f301[var0] = null;
         }
      }

      C25_f301 = null;
   }

   public final boolean L() {
      this.C25_f348.setGameEngine((GameEngineBase)this);
      this.gameController.a((GameEngineBase)this);
      QuestManager.isQuestActive = false;
      C25_f338 = true;
      this.changeState((byte)0);
      if (C25_f345) {
         this.C25_f342.playBackgroundMusic(a(this.C25_f348.lastEventIndex, (byte)0), 1);
      } else {
         this.C25_f342.playBackgroundMusic(C25_f344, 1);
      }

      this.dialogManager.removeDialog("/data/ui/battle.ui");
      return true;
   }

   private void ad() {
      short[] var1;
      if (!this.C25_f286.C53_f776) {
         if (inputEnabled) {
            this.b(this.C25_f286);
         } else {
            var1 = new short[]{this.C25_f293, this.C25_f294, (short)C25_f320, 4, 4, 8, 40, 100, 0};
            this.C25_f286.a(var1);
         }
      } else if (this.C25_f295 >= 0) {
         byte var3 = 2;
         int var4 = this.C25_f287[this.C25_f295].worldX - this.C25_f287[this.C25_f295].worldX % this.C25_f286.primaryStates[var3];
         var3 = 2;
         int var2 = this.C25_f287[this.C25_f295].worldY - this.C25_f287[this.C25_f295].worldY % this.C25_f286.primaryStates[var3];
         this.C25_f286.setWorldPosition(var4, var2);
         this.C25_f286.attachedObject.setWorldPosition(var4, var2);
         this.C25_f286.a((byte)0, (byte)this.C25_f287[this.C25_f295].C18_f236);
         if (this.C25_f287[this.C25_f295].sprite.spriteSetId == 222) {
            this.C25_f286.moveInDirection((int)24);
         } else {
            this.C25_f286.moveInDirection((int)32);
         }
      } else {
         var1 = new short[]{this.C25_f293, this.C25_f294, (short)C25_f320, 4, 4, 8, 40, 100, 0};
         this.C25_f286.a(var1);
      }

      this.C25_f285.addEntity((GameEntity)this.C25_f286);
      this.C25_f286.C();
      this.C25_f286.activate();
      this.a((GameObject)this.C25_f286);
      if (C25_f298 != null) {
         this.C25_f286.setAlternativeRender(false);
      } else {
         this.C25_f286.setAlternativeRender(true);
      }
   }

   private void ae() {
      for(int var1 = 0; var1 < this.C25_f287.length; ++var1) {
         if (this.C25_f287[var1].C18_f223 == 0 && this.C25_f287[var1].C18_f225 == 14) {
            NPCEntity var2;
            (var2 = this.C25_f287[var1]).C18_f234 = 0;

            while(true) {
               byte var10001 = var2.sprite.getCurrentAnimationId();
               int var10002 = 16 * (var2.C18_f234 + 1);
               boolean var3 = false;
               int var5 = var10002;
               byte var4 = var10001;
               byte var6 = 0;
               switch(var4) {
               case 0:
                  var6 = TileMapRenderer.getInstance().getTileAt(0, var2.worldX, var2.worldY + var5);
                  break;
               case 1:
                  var6 = TileMapRenderer.getInstance().getTileAt(0, var2.worldX + var5, var2.worldY);
                  break;
               case 2:
                  var6 = TileMapRenderer.getInstance().getTileAt(0, var2.worldX, var2.worldY - var5);
                  break;
               case 3:
                  var6 = TileMapRenderer.getInstance().getTileAt(0, var2.worldX - var5, var2.worldY);
               }

               if (var6 != 0) {
                  var2.C18_f235 = var2.C18_f234;
                  var2.C18_f234 = 0;
                  break;
               }

               ++var2.C18_f234;
            }
         }
      }

   }

   private void a(int var1, int var2, String var3) {
      var3 = var3 + "scene_" + var1 + ".mid";

      try {
         InputStream var16 = ManagedInputStream.openStream(var3);
         DataInputStream var4;
         short var5;
         short[] var6 = new short[var5 = (var4 = new DataInputStream(var16)).readShort()];

         int var7;
         for(var7 = 0; var7 < var5; ++var7) {
            var6[var7] = var4.readShort();
         }

         var7 = 0;

         int var17;
         for(var17 = 0; var17 < var2; ++var17) {
            var7 += var6[var17];
         }

         var4.skipBytes(var7);
         var5 = var4.readShort();
         String[] var18 = null;
         if (var5 > 0) {
            var18 = new String[var5];

            for(var7 = 0; var7 < var5; ++var7) {
               short var8 = var4.readShort();
               StringBuffer var9 = new StringBuffer();

               for(int var10 = 0; var10 < var8; ++var10) {
                  var9.append((char)(var4.read() << 8 | var4.read() & 255));
               }

               var18[var7] = var9.toString();
            }
         }

         byte var22 = var4.readByte();
         StringBuffer var20 = new StringBuffer();

         for(int var24 = 0; var24 < var22; ++var24) {
            var20.append((char)(var4.read() << 8 | var4.read() & 255));
         }

         this.C25_f289 = var4.readShort();
         C25_f305 = null;
         var4.readShort();
         short var26 = var4.readShort();
         boolean[] var19 = new boolean[2];
         if (C25_f302[C25_f297[var1] + var2] == null) {
            C25_f302[C25_f297[var1] + var2] = new byte[var26][3];
            var19[0] = true;
         }

         if (this.C25_f290 == 9) {
            C25_f303[C25_f297[var1] + var2] = null;
         }

         if (C25_f303[C25_f297[var1] + var2] == null) {
            C25_f303[C25_f297[var1] + var2] = new short[var26][2];
            var19[1] = true;
         }

         if (var26 > 0) {
            this.C25_f287 = new NPCEntity[var26];

            for(var7 = 0; var7 < var26; ++var7) {
               try {
                  this.C25_f287[var7] = new NPCEntity();
                  short[] var21;
                  (var21 = new short[var4.readShort()])[0] = (short)var4.readByte();
                  var21[1] = var4.readShort();
                  var21[2] = var4.readShort();
                  var21[3] = var4.readShort();
                  var21[4] = var4.readShort();
                  var21[5] = (short)var4.readByte();
                  var21[6] = (short)var4.readByte();
                  switch(var21[0]) {
                  case 0:
                     var21[7] = (short)var4.readByte();
                     var21[8] = (short)var4.readByte();
                     var21[9] = (short)var4.readByte();
                     var21[10] = (short)var4.readByte();
                     var21[11] = var4.readShort();
                     var21[12] = var4.readShort();
                     if (var19[0]) {
                        C25_f302[C25_f297[var1] + var2][var7][0] = (byte)var21[2];
                        C25_f302[C25_f297[var1] + var2][var7][1] = (byte)var21[5];
                     } else {
                        if (var21[6] != 7 && var21[6] != 6) {
                           var21[2] = (short)C25_f302[C25_f297[var1] + var2][var7][0];
                        }

                        var21[5] = (short)C25_f302[C25_f297[var1] + var2][var7][1];
                     }

                     if (var19[1]) {
                        C25_f303[C25_f297[var1] + var2][var7][0] = var21[3];
                        C25_f303[C25_f297[var1] + var2][var7][1] = var21[4];
                     } else {
                        var21[3] = C25_f303[C25_f297[var1] + var2][var7][0];
                        var21[4] = C25_f303[C25_f297[var1] + var2][var7][1];
                     }

                     this.C25_f287[var7].a(var21, var7);
                     if (!var19[0]) {
                        if (var21[6] == 1) {
                           if (var21[6] != 7 && var21[6] != 6) {
                              var21[2] = (short)C25_f302[C25_f297[var1] + var2][var7][0];
                           }

                           NPCEntity var10000 = this.C25_f287[var7];
                           byte var11 = C25_f302[C25_f297[var1] + var2][var7][2];
                           var10000.currentDirection = var11;
                           this.C25_f287[var7].a((byte)var21[2]);
                        }
                     } else {
                        C25_f302[C25_f297[var1] + var2][var7][2] = this.C25_f287[var7].currentDirection;
                     }
                     break;
                  case 1:
                     var21[7] = (short)var4.readByte();
                     var21[8] = var4.readShort();
                     var21[9] = var4.readShort();
                     var21[10] = var4.readShort();
                     if (var19[0] && var21[6] == 3) {
                        C25_f302[C25_f297[var1] + var2][var7][0] = (byte)var21[2];
                        C25_f302[C25_f297[var1] + var2][var7][1] = (byte)var21[5];
                     }

                     if (var21[6] == 3) {
                        var21[2] = (short)C25_f302[C25_f297[var1] + var2][var7][0];
                        var21[5] = (short)C25_f302[C25_f297[var1] + var2][var7][1];
                     }

                     this.C25_f287[var7].a(var21, var7);
                     break;
                  case 2:
                     var21[7] = var4.readShort();
                     if (var21[7] == 1) {
                        var21[8] = (short)var4.readByte();
                        var21[9] = (short)var4.readByte();
                        var21[10] = (short)var4.readByte();
                        var21[11] = (short)var4.readByte();
                        var21[12] = (short)var4.readByte();
                     }

                     this.C25_f287[var7].a(var21, var7);
                     break;
                  case 3:
                     var21[7] = (short)var4.readByte();
                     var21[8] = (short)var4.readByte();
                     var21[9] = (short)var4.readByte();
                     var21[10] = var4.readShort();
                     var21[11] = var4.readShort();
                     if (var19[0]) {
                        C25_f302[C25_f297[var1] + var2][var7][0] = (byte)var21[2];
                     } else {
                        var21[2] = (short)C25_f302[C25_f297[var1] + var2][var7][0];
                     }

                     this.C25_f287[var7].a(var21, var7);
                  }
               } catch (Exception var12) {
                  System.out.println(" k = " + var7 + " e = " + var12);
               }
            }

            short var25;
            C25_f305 = new String[var25 = var4.readShort()];

            for(int var23 = 0; var23 < var25; ++var23) {
               StringBuffer var14 = new StringBuffer();
               byte var15 = var4.readByte();

               for(var17 = 0; var17 < var15; ++var17) {
                  var14.append((char)(var4.readByte() << 8 | var4.readByte() & 255));
               }

               C25_f305[var23] = var14.toString();
            }
         }

         this.C25_f350 = 1;
         var5 = var4.readShort();
         this.C25_f350 = 2;
         if (var5 > 0) {
            this.C25_f348.loadQuests(var4, this.C25_f290, this.C25_f291, var5, var18);
         }

         var4.close();
         var16.close();
      } catch (Exception var13) {
         System.out.println(" initRoom = " + var13 + " bug = " + this.C25_f350);
      }
   }

   public final void cleanupCurrentScreen() {
      this.C25_f285.clearWorld();
      this.C25_f283.clearMapData();
      if (this.C25_f287 != null) {
         for(int var1 = 0; var1 < this.C25_f287.length; ++var1) {
            NPCEntity var2;
            (var2 = this.C25_f287[var1]).deactivateAttachedObject();
            if (var2.attachedObject != null) {
               var2.attachedObject.sprite.forceCleanup();
               var2.attachedObject = null;
            }

            var2.sprite.forceCleanup();
            var2.sprite = null;
            if (var2.C18_f246 != null) {
               var2.C18_f246.sprite.forceCleanup();
               var2.C18_f246 = null;
            }

            if (var2.C18_f247 != null) {
               var2.C18_f247.sprite.forceCleanup();
               var2.C18_f247 = null;
            }

            var2.C18_f248 = -1;
            this.C25_f287[var1] = null;
         }

         this.C25_f287 = null;
      }

      C25_f298 = null;
      C25_f299 = null;
      C25_f306 = null;
      C25_f343 = null;
      this.C25_f288.removeAllElements();
      C25_f307.removeAllElements();
      C25_f309.removeAllElements();
      C25_f310.removeAllElements();
      C25_f308.removeAllElements();
      if (C25_f336 != null) {
         C25_f336.removeAllElements();
         C25_f336 = null;
      }

      this.dialogManager.clearAllDialogs();
      this.C25_f348.cleanupCurrentScreen();
      C25_f318 = -1;
      ScreenTransitionManager.c();
   }

   public final void changeState(byte var1) {
      this.C44_f699 = this.C44_f698;
      switch(var1) {
      case 0:
         u();
         if (!QuestManager.isQuestActive) {
            if (C25_f338) {
               this.gameController.c();
            } else {
               this.gameController.d();
            }
         }

         this.C25_f286.a((byte)0, (byte)this.C25_f286.currentDirection);
         break;
      case 1:
         this.gameController.C9_f141 = 1;
         this.gameController.F();
         break;
      case 2:
         if (C25_f318 != -1 && this.C25_f287[C25_f318] != null && this.C25_f287[C25_f318].sprite.spriteSetId == 24) {
            this.gameController.a((int)4, (byte)0);
         } else if (C25_f318 != -1 && this.C25_f287[C25_f318] != null && this.C25_f287[C25_f318].sprite.spriteSetId == 20) {
            this.gameController.a((int)3, (byte)2);
         }
         break;
      case 3:
         this.gameController.O();
      case 4:
      case 33:
      case 34:
      case 35:
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      case 42:
      case 43:
      case 44:
      case 45:
      case 46:
      case 47:
      case 48:
      case 49:
      case 50:
      case 51:
      case 52:
      case 53:
      case 54:
      case 55:
      case 56:
      case 57:
      case 58:
      case 59:
      case 60:
      case 61:
      case 62:
      case 63:
      case 64:
      case 65:
      case 66:
      case 67:
      case 68:
      case 69:
      case 70:
      case 71:
      case 72:
      case 73:
      case 74:
      case 75:
      case 76:
      case 77:
      case 78:
      case 79:
      case 80:
      case 81:
      case 82:
      case 83:
      case 84:
      case 85:
      case 86:
      case 87:
      case 88:
      case 89:
      case 90:
      case 91:
      case 92:
      case 93:
      case 94:
      case 95:
      case 96:
      case 97:
      case 98:
      case 99:
      case 103:
      default:
         break;
      case 5:
         this.gameController.ag();
         break;
      case 6:
         this.gameController.k();
         break;
      case 7:
         this.gameController.C9_f126 = 0;
         this.gameController.Z();
         break;
      case 8:
         this.gameController.ab();
         break;
      case 9:
         this.gameController.Q();
         break;
      case 10:
         this.gameController.U();
         break;
      case 11:
         this.gameController.S();
         break;
      case 12:
         this.gameController.W();
         break;
      case 13:
         this.gameController.m();
         break;
      case 14:
         this.gameController.aC();
         break;
      case 15:
         this.gameController.B();
         break;
      case 16:
         this.gameController.D();
         break;
      case 17:
         this.gameController.C9_f150 = false;
      case 18:
      case 19:
         this.gameController.C9_f126 = 0;
         this.gameController.Z();
         break;
      case 20:
         this.gameController.x();
         break;
      case 21:
         this.gameController.z();
         break;
      case 22:
         this.gameController.K();
         this.gameController.a("Có lưu dữ liệu không?");
         break;
      case 23:
         if (this.C44_f699 == 7) {
            this.gameController.a((String)"", (String)this.C25_f351, -1, -1);
         } else if (this.C25_f287 != null) {
            if (this.C25_f287[C25_f318].sprite.spriteSetId == 68) {
               this.gameController.a((String)C25_f305[this.C25_f287[C25_f318].C18_f228], (String)"Muốn lên thuyền đi đâu?", 1, -1);
            } else if (this.C25_f287[C25_f318].C18_f227 < 0) {
               this.gameController.a((String)C25_f305[this.C25_f287[C25_f318].C18_f228], (String)C25_f349[0], 1, -1);
            } else {
               this.gameController.a((String)C25_f305[this.C25_f287[C25_f318].C18_f228], (String)C25_f349[this.C25_f287[C25_f318].C18_f227], 1, -1);
            }
         }

         this.C25_f286.a((byte)0, (byte)this.C25_f286.currentDirection);
         break;
      case 24:
         this.gameController.h();
         break;
      case 25:
         this.gameController.au();
         break;
      case 26:
         this.gameController.C9_f141 = 2;
         this.gameController.a((int)4, (byte)0);
         break;
      case 27:
         this.gameController.aS();
         break;
      case 28:
         byte var6;
         for(var6 = 0; var6 < this.C25_f330.length / 4 && (this.C25_f330[var6 << 2] != this.C25_f290 || this.C25_f330[(var6 << 2) + 1] != this.C25_f291); ++var6) {
         }

         this.gameController.a((byte)var6, this.C25_f330[(var6 << 2) + 2], this.C25_f330[(var6 << 2) + 3]);
         break;
      case 29:
         ScreenTransitionManager.a().c(0, 2);
         break;
      case 30:
         this.gameController.aQ();
         break;
      case 31:
         this.gameController.C9_f131 = 0;
         boolean var3 = false;
         this.ai();
         if (this.C25_f374 >= this.C25_f373) {
            var3 = true;
         }

         this.C25_f375 = var3;
         if (this.C25_f375) {
            if (this.C25_f373 == this.C25_f372.length - 1) {
               this.gameController.a((String)C25_f305[this.C25_f287[C25_f318].C18_f228], (String) getLocalizedText(613), 1, -1);
            } else if (this.C25_f373 == this.C25_f372.length - 2) {
               this.gameController.a((String)C25_f305[this.C25_f287[C25_f318].C18_f228], (String) getLocalizedText(612), 1, -1);
            } else {
               int[] var2 = new int[]{this.C25_f372[this.C25_f373], this.C25_f372[this.C25_f373 + 1]};
               this.gameController.a((String)C25_f305[this.C25_f287[C25_f318].C18_f228], (String) formatString(611, var2), 1, -1);
            }
         } else if (this.C25_f373 < this.C25_f372.length) {
            String var10001 = C25_f305[this.C25_f287[C25_f318].C18_f228];
            byte var7 = this.C25_f372[this.C25_f373];
            boolean var4 = true;
            int var5;
            this.gameController.a((String)var10001, (String)((var5 = GameEngineBase.getLocalizedText((int)614).indexOf("%s")) == -1 ? GameEngineBase.getLocalizedText((int)614) : GameEngineBase.getLocalizedText((int)614).substring(0, var5) + var7 + GameEngineBase.getLocalizedText((int)614).substring(var5 + 2)), 1, -1);
         } else {
            this.gameController.a((String)C25_f305[this.C25_f287[C25_f318].C18_f228], (String) getLocalizedText(615), 1, -1);
         }
         break;
      case 32:
         this.gameController.C9_f141 = 3;
         this.gameController.a((int)3, (byte)2);
         break;
      case 100:
         this.gameController.d(0);
         break;
      case 101:
         this.gameController.aJ();
         break;
      case 102:
         this.gameController.aL();
         break;
      case 104:
         this.gameController.aK();
      }

      this.gameController.C9_f132 = true;
      this.C44_f698 = var1;
      this.showSoftKeys();
   }

   public final void update() {
      if (this.isActive) {
         this.updateInputState();
         label203:
         switch(this.C44_f698) {
         case 0:
            this.af();
            break;
         case 1:
            this.gameController.G();
            break;
         case 2:
            if ((C25_f318 == -1 || this.C25_f287[C25_f318] == null || this.C25_f287[C25_f318].sprite.spriteSetId != 24) && this.C25_f348.questChangeState != 0) {
               if (C25_f318 != -1 && this.C25_f287[C25_f318] != null && this.C25_f287[C25_f318].sprite.spriteSetId == 20 || this.C25_f348.questChangeState == 1) {
                  this.gameController.a((byte)3, (byte)2);
               }
            } else {
               this.gameController.a((byte)4, (byte)0);
            }
            break;
         case 3:
            this.gameController.P();
            break;
         case 4:
            int var1;
            if (this.C25_f360) {
               if (this.C25_f356 == this.C25_f358 && this.C25_f357 == this.C25_f359) {
                  this.C25_f360 = false;
               }

               if ((var1 = GameUtils.calculateEuclideanDistance(this.C25_f356, this.C25_f357, this.C25_f358, this.C25_f359)) < this.C25_f363) {
                  this.C25_f356 = this.C25_f358;
                  this.C25_f357 = this.C25_f359;
               } else {
                  this.C25_f356 += (this.C25_f358 - this.C25_f356) * this.C25_f363 / var1;
                  this.C25_f357 += (this.C25_f359 - this.C25_f357) * this.C25_f363 / var1;
               }
            }

            if (!this.C25_f360) {
               if (this.isKeyHeld(16400)) {
                  if (this.C25_f356 < 0) {
                     this.C25_f356 += this.C25_f363;
                  }
               } else if (this.isKeyHeld(32832)) {
                  if (this.C25_f356 + (this.C25_f368[this.C25_f352 << 1] << 4) * 5 > getScreenWidth()) {
                     this.C25_f356 -= this.C25_f363;
                  }
               } else if (this.isKeyHeld(4100)) {
                  if (this.C25_f357 < 0) {
                     this.C25_f357 += this.C25_f363;
                  }
               } else if (this.isKeyHeld(8448)) {
                  if (this.C25_f357 + (this.C25_f368[(this.C25_f352 << 1) + 1] << 3) * 5 > getScreenHeight() - 30) {
                     this.C25_f357 -= this.C25_f363;
                  }
               } else if (this.isKeyPressed(262145) || this.isPointerClickInBounds(0, 270, getScreenWidth(), 30)) {
                  BattleSystemManager.B().C29_f400 = null;
                  this.changeState((byte)0);
               }
            }

            this.C25_f361.updateAnimation();
            var1 = 0;

            while(true) {
               if (var1 >= this.C25_f362.length) {
                  break label203;
               }

               this.o(var1);
               this.C25_f362[var1].updateAnimation();
               ++var1;
            }
         case 5:
            this.gameController.ah();
            break;
         case 6:
            this.gameController.l();
            break;
         case 7:
            this.gameController.aa();
            this.q();
            break;
         case 8:
            this.gameController.af();
            break;
         case 9:
            this.gameController.R();
            break;
         case 10:
            this.gameController.V();
            break;
         case 11:
            this.gameController.T();
            break;
         case 12:
            this.gameController.X();
            break;
         case 13:
            this.gameController.n();
            break;
         case 14:
            this.gameController.aD();
            break;
         case 15:
            this.gameController.C();
            break;
         case 16:
            this.gameController.E();
            break;
         case 17:
            this.gameController.ac();
            break;
         case 18:
            this.gameController.ad();
            break;
         case 19:
            this.gameController.ae();
            break;
         case 20:
            this.gameController.y();
            break;
         case 21:
            this.gameController.A();
            break;
         case 22:
            this.gameController.N();
            break;
         case 23:
            if (this.gameController.d(C25_f317, C25_f316) && this.isKeyPressed(196640)) {
               if (GameUtils.pageCount < GameUtils.b()) {
                  GameUtils.c();
                  this.gameController.b(GameUtils.pageCount);
               } else {
                  label227: {
                     this.gameController.aF();
                     if (this.C44_f699 != 7) {
                        if (this.C25_f287[C25_f318].sprite.spriteSetId <= 85) {
                           NPCEntity var10000 = this.C25_f287[C25_f318];
                           byte var3 = this.C25_f287[C25_f318].currentAnimationId;
                           var10000.currentDirection = var3;
                        }

                        this.C25_f287[C25_f318].a((byte)0);
                        this.C25_f286.a((byte)0, (byte)this.C25_f286.currentDirection);
                        if (this.C25_f287[C25_f318].sprite.spriteSetId == 24 || this.C25_f287[C25_f318].sprite.spriteSetId == 20) {
                           this.changeState((byte)1);
                           break label227;
                        }

                        if (this.C25_f287[C25_f318].sprite.spriteSetId == 25) {
                           this.changeState((byte)16);
                           break label227;
                        }

                        if (this.C25_f287[C25_f318].sprite.spriteSetId == 68) {
                           this.changeState((byte)28);
                           break label227;
                        }

                        if (C25_f318 != -1) {
                           B().a((byte)13, B().C25_f287[C25_f318].worldX, B().C25_f287[C25_f318].worldY - 40, B().C25_f287[C25_f318]);
                        }
                     }

                     this.changeState((byte)0);
                  }
               }
            }

            this.C25_f285.updateWorld();
            break;
         case 24:
            this.gameController.i();
            break;
         case 25:
            this.gameController.av();
            break;
         case 26:
            this.gameController.a((byte)4, (byte)0);
            break;
         case 27:
            this.gameController.aT();
            break;
         case 28:
            this.gameController.aP();
            break;
         case 29:
            ScreenTransitionManager.a().d();
            if (ScreenTransitionManager.a().C30_f476) {
               ScreenTransitionManager.a().C30_f472 = -1;
               GameScreenManager.getInstance().changeState((byte)23);
            }
            break;
         case 30:
            this.gameController.aR();
            break;
         case 31:
            this.ag();
            break;
         case 32:
            this.gameController.a((byte)3, (byte)2);
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 48:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 103:
         default:
            break;
         case 100:
         case 101:
         case 102:
         case 104:
            this.gameController.aO();
         }

         if (this.C44_f698 == 0 && !this.C25_f348.hasActiveEvent() && C25_f337 == 0 && C25_f336 != null && C25_f336.size() > 0) {
            if (this.C25_f292 >= C25_f336.size()) {
               C25_f336.removeAllElements();
               this.C25_f292 = 0;
               C25_f337 = 1;
            } else if (this.gameController.aA()) {
               int[] var4 = (int[])C25_f336.elementAt(this.C25_f292);
               String var2 = "Tiến hóa";
               if (ResourceManager.gameDatabase[0][ResourceManager.getDatabaseValue((byte)0, (short)var4[0], (byte)19)][2] == 3) {
                  var2 = "Dị hoá";
               }

               if (!C25_f339 && C25_f340[0] != -1) {
                  if (this.C25_f292 == C25_f336.size() - 1) {
                     this.gameController.H();
                     this.gameController.a("Nhấn #2" + getLocalizedText(var4[1]) + "#0 đạt tới có thể" + var2 + " điều kiện", "Nhấn nút 5 tiếp tục");
                  } else {
                     this.gameController.b("#2" + getLocalizedText(var4[1]) + "#0 có thể" + var2);
                  }
               } else {
                  this.gameController.b("#2" + getLocalizedText(var4[1]) + "#0 có thể" + var2);
               }

               ++this.C25_f292;
            }
         }

         this.dialogManager.closeCurrentDialog();
         if (C25_f300 != null) {
            C25_f300.updateEffect();
         }

      }
   }

   public static void b(Graphics var0) {
      if (C25_f298 == null) {
         var0.setColor(getBackgroundColor());
         var0.fillRect(0, 0, getScreenWidth(), getScreenHeight());
      } else {
         int var1 = C25_f298.getWidth();

         for(int var2 = 0; var2 < GameEngineBase.getScreenWidth() / var1; ++var2) {
            var0.drawImage(C25_f298, var2 * var1, 0, 20);
         }

      }
   }

   private void o(int var1) {
      switch(var1) {
      case 0:
         if (this.C25_f357 >= 0) {
            this.C25_f362[var1].deactivate();
            return;
         }

         this.C25_f362[var1].activate();
         return;
      case 1:
         if (this.C25_f357 + (this.C25_f368[(this.C25_f352 << 1) + 1] << 3) * 5 <= getScreenHeight()) {
            this.C25_f362[var1].deactivate();
            return;
         }

         this.C25_f362[var1].activate();
         return;
      case 2:
         if (this.C25_f356 >= 0) {
            this.C25_f362[var1].deactivate();
            return;
         }

         this.C25_f362[var1].activate();
         return;
      case 3:
         if (this.C25_f356 + (this.C25_f368[this.C25_f352 << 1] << 4) * 5 <= getScreenWidth()) {
            this.C25_f362[var1].deactivate();
            return;
         } else {
            this.C25_f362[var1].activate();
         }
      default:
      }
   }

   public final void renderPauseScreen(Graphics var1) {
      GameWorldManager var2;
      Graphics var3;
      int var4;
      int[] var5;
      if (this.C44_f698 == 4) {
         var3 = var1;
         var2 = this;
         var1.drawImage(BattleSystemManager.B().C29_f400, 0, 0, 20);
         int var11 = (var5 = this.C25_f367[this.C25_f352]).length;
         int var6 = this.C25_f355 * 7;

         int var9;
         for(var4 = 0; var4 < var11; var4 += 7) {
            if (var2.C25_f290 == var5[var4 + 2] && var2.C25_f291 == var5[var4 + 3]) {
               var3.setColor(188, 122, 255);
            } else {
               var3.setColor(var2.C25_f366[var2.C25_f352 << 1]);
            }

            var3.fillRoundRect(var2.C25_f356 + (var5[var4] << 4), var2.C25_f357 + (var5[var4 + 1] << 3), var5[var4 + 5] << 4, var5[var4 + 6] << 3, 12, 12);
            var3.setColor(0);
            var3.drawRoundRect(var2.C25_f356 + (var5[var4] << 4), var2.C25_f357 + (var5[var4 + 1] << 3), var5[var4 + 5] << 4, var5[var4 + 6] << 3, 12, 12);
            if (var4 == var6) {
               var2.C25_f361.setWorldPosition(var2.C25_f356 + (var5[var4] << 4) + 16 * var5[var4 + 5] / 2, var2.C25_f357 + (var5[var4 + 1] << 3) + 8 * var5[var4 + 6] / 2 + 20);
               var2.C25_f361.render(var3, 0, 0);
            }

            String[] var7;
            int var8 = (var7 = BitmapFontRenderer.wrapText(GameEngineBase.getLocalizedText(var5[var4 + 4]), var5[var4 + 5] << 4)).length;

            for(var9 = 0; var9 < var8; ++var9) {
               GameUtils.a(var3, var7[var9], var2.C25_f366[(var2.C25_f352 << 1) + 1], var2.C25_f356 + (var5[var4] << 4) + 16 * var5[var4 + 5] / 2, var2.C25_f357 + (var5[var4 + 1] << 3) + 8 * var5[var4 + 6] / 2 + (var9 - var8 / 2) * (BitmapFontRenderer.fontHeight + 1), 17, 17, var2.dialogManager.textRenderer, -1);
            }
         }

         var3.setColor(65280);
         var11 = var2.C25_f288.size();
         TileMapRenderer var13 = TileMapRenderer.getInstance();

         int var12;
         for(var9 = 0; var9 < var11; ++var9) {
            NPCEntity var10;
            var4 = ((var10 = (NPCEntity)var2.C25_f288.elementAt(var9)).worldX * var5[var6 + 5] << 4) / var13.mapPixelWidth + (var5[var6] << 4) + var2.C25_f356;
            var12 = (var10.worldY * var5[var6 + 6] << 3) / var13.mapPixelHeight + (var5[var6 + 1] << 3) + var2.C25_f357;
            if (var10.getFacingDirection() != 0 && ((NPCEntity)var2.C25_f288.elementAt(var9)).getFacingDirection() != 1) {
               var3.fillRect(var4, var12 - 5, 3, 9);
            } else {
               var3.fillRect(var4, var12 - 2, 9, 3);
            }
         }

         if (var2.C25_f353 != -1) {
            var4 = (var2.C25_f364[(var2.C25_f353 << 2) + 2] * var5[var6 + 5] << 4) / var13.mapPixelWidth + (var5[var6] << 4) + var2.C25_f356;
            var12 = (var2.C25_f364[(var2.C25_f353 << 2) + 3] * var5[var6 + 6] << 3) / var13.mapPixelHeight + (var5[var6 + 1] << 3) + var2.C25_f357;
            var3.setColor(16711680);
            var3.fillRect(var4, var12, 6, 6);
         }

         if (var2.C25_f354 != -1) {
            var4 = (var2.C25_f365[(var2.C25_f354 << 2) + 2] * var5[var6 + 5] << 4) / var13.mapPixelWidth + (var5[var6] << 4) + var2.C25_f356;
            var12 = (var2.C25_f365[(var2.C25_f354 << 2) + 3] * var5[var6 + 6] << 3) / var13.mapPixelHeight + (var5[var6 + 1] << 3) + var2.C25_f357;
            var3.setColor(2758133);
            var3.fillRect(var4, var12, 6, 6);
         }

         for(var9 = 0; var9 < var2.C25_f362.length; ++var9) {
            var2.C25_f362[var9].render(var3, 0, 0);
         }

         var3.setColor(1862801);
         var3.fillRect(0, GameEngineBase.getScreenHeight() - 30, GameEngineBase.getScreenWidth(), 30);
         var3.setColor(65280);
         var3.fillRect(15, GameEngineBase.getScreenHeight() - 22, 16, 16);
         BitmapFontRenderer.drawText(var3, "Cửa ra vào", 35, GameEngineBase.getScreenHeight() - 18);
         var3.setColor(2758133);
         var3.fillRect(90, GameEngineBase.getScreenHeight() - 22, 16, 16);
         BitmapFontRenderer.drawText(var3, "Bến tàu", 110, GameEngineBase.getScreenHeight() - 18);
         var3.setColor(16711680);
         var3.fillRect(150, GameEngineBase.getScreenHeight() - 22, 16, 16);
         BitmapFontRenderer.drawText(var3, "Cửa Đạo quán", 168, GameEngineBase.getScreenHeight() - 18);
      } else {
         if (this.C44_f698 == 0 || this.C44_f698 == 23 || this.gameController.C9_f132) {
            b(var1);
            ScreenTransitionManager.a().b(var1);
            this.C25_f285.renderWorld(var1);
            ScreenTransitionManager.a().c(var1);
            if (this.gameController.C9_f132) {
               this.gameController.C9_f132 = false;
            }
         }

         if (ScreenTransitionManager.a().C30_f523 != -1) {
            if (this.C25_f284.followTarget instanceof PlayerCharacter) {
               ScreenTransitionManager.a().b(this.C25_f284.worldX - TileMapRenderer.getInstance().cameraX, this.C25_f284.worldY - TileMapRenderer.getInstance().cameraY - this.C25_f328[this.C25_f286.C53_f765 + 1]);
            } else {
               ScreenTransitionManager.a().b(this.C25_f284.worldX - TileMapRenderer.getInstance().cameraX, this.C25_f284.worldY - TileMapRenderer.getInstance().cameraY - 20);
            }

            ScreenTransitionManager.a().a(var1, 0, 0);
         }

         QuestManager var10000 = this.C25_f348;
         QuestManager.renderEffects(var1);
         this.dialogManager.render(var1);
         if (C25_f300 != null) {
            C25_f300.render(var1);
         }

         var3 = var1;
         var2 = this;

         for(var4 = 0; var4 < var2.C25_f286.C53_f799.size(); ++var4) {
            var5 = (int[])var2.C25_f286.C53_f799.elementAt(var4);
            GameUtils.a(var3, "+" + var5[0], 16704699, var5[1] + 12 - var2.C25_f283.cameraX, var5[2] - var5[3] - var2.C25_f283.cameraY, 17, 17, var2.dialogManager.textRenderer, 2);
            var3.drawImage(C25_f299, var5[1] - var2.C25_f283.cameraX - 6, var5[2] - var5[3] - var2.C25_f283.cameraY, 20);
         }

         if (!this.gameController.j() && !p()) {
            this.C25_f348.renderPauseScreen(var1);
         }

         if (this.C25_f290 == 3 && this.C25_f291 == 7 && this.C44_f698 == 0) {
            if (this.C25_f348.questEffectCounter > 0) {
               if (this.C25_f341 != null) {
                  var1.drawImage(this.C25_f341[this.C25_f348.questEffectCounter - 1], getScreenWidth() >> 1, getScreenHeight() >> 1, 3);
                  return;
               }
            } else if (GameScreenManager.getInstance().pauseStartTime != 0L) {
               var1.setColor(896);
               var1.setFont(getLargeFont());
               var1.drawString(a(GameScreenManager.getInstance().pauseStartTime - GameScreenManager.getInstance().gameStartTime)[0], 10, 40, 20);
            }
         }

      }
   }

   private void af() {
      GameWorldManager var1;
      if (!this.C25_f348.hasActiveEvent() && this.C25_f286.getFacingDirection() < 5 && !this.gameController.j() && this.gameController.J()) {
         if (this.isKeyHeld(4100)) {
            this.C25_f286.a((byte)1, (byte)2);
         } else if (this.isKeyHeld(8448)) {
            this.C25_f286.a((byte)1, (byte)0);
         } else if (this.isKeyHeld(16400)) {
            this.C25_f286.a((byte)1, (byte)3);
         } else if (this.isKeyHeld(32832)) {
            this.C25_f286.a((byte)1, (byte)1);
         }

         if (this.isKeyPressed(65568)) {
            if (C25_f318 != -1) {
               this.C25_f286.a((byte)0, (byte)this.C25_f286.currentDirection);
               if (QuestManager.isQuestTriggered) {
                  QuestManager.isQuestReady = true;
                  QuestManager.isQuestTriggered = false;
               } else {
                  if (this.C25_f287[C25_f318].sprite.spriteSetId <= 85) {
                     this.C25_f287[C25_f318].currentAnimationId = this.C25_f287[C25_f318].currentDirection;
                     byte var2;
                     NPCEntity var10000;
                     switch(this.C25_f286.currentDirection) {
                     case 0:
                        var10000 = this.C25_f287[C25_f318];
                        var2 = 2;
                        var10000.currentDirection = var2;
                        break;
                     case 1:
                        var10000 = this.C25_f287[C25_f318];
                        var2 = 3;
                        var10000.currentDirection = var2;
                        break;
                     case 2:
                        var10000 = this.C25_f287[C25_f318];
                        var2 = 0;
                        var10000.currentDirection = var2;
                        break;
                     case 3:
                        var10000 = this.C25_f287[C25_f318];
                        var2 = 1;
                        var10000.currentDirection = var2;
                     }

                     this.C25_f287[C25_f318].a((byte)0);
                  }

                  if (this.C25_f287[C25_f318].sprite.spriteSetId == 17) {
                     this.gameController.C9_f128 = 0;
                     this.changeState((byte)27);
                  } else {
                     this.changeState((byte)23);
                  }
               }

               B().D();
            } else if ((NPCEntity)this.C25_f286.followTarget != null && ((NPCEntity)this.C25_f286.followTarget).C18_f223 == 3) {
               this.C25_f286.w();
            } else {
               this.C25_f286.x();
            }
         }

         if (this.isKeyReleased(61780)) {
            this.C25_f286.a((byte)0, (byte)this.C25_f286.currentDirection);
         }

         if (this.isKeyPressed(262144)) {
            this.r();
            this.gameController.C9_f125 = 0;
            this.changeState((byte)6);
         } else if (this.isKeyPressed(131072)) {
            this.gameController.C9_f125 = 0;
            this.changeState((byte)13);
         } else if (this.isKeyPressed(1)) {
            var1 = this;
            boolean var5 = true;

            for(int var3 = 0; var3 < C25_f327.length; ++var3) {
               if (C25_f327[var3] == var1.C25_f290) {
                  var5 = false;
                  break;
               }
            }

            if (var5) {
               byte var6;
               for(var6 = 0; var6 < var1.C25_f367[var1.C25_f352].length / 7; ++var6) {
                  if (var1.C25_f367[var1.C25_f352][var6 * 7 + 2] == var1.C25_f290 && var1.C25_f367[var1.C25_f352][var6 * 7 + 3] == var1.C25_f291) {
                     var1.C25_f355 = var6;
                     break;
                  }
               }

               var1.C25_f358 = (getScreenWidth() >> 1) - (var1.C25_f367[var1.C25_f352][var1.C25_f355 * 7] << 4) - 40;
               var1.C25_f359 = (getScreenHeight() >> 1) - (var1.C25_f367[var1.C25_f352][var1.C25_f355 * 7 + 1] << 3) - 20;
               var1.C25_f360 = true;
               if (var1.C25_f361 == null) {
                  var1.C25_f361 = new GameObject();
                  var1.C25_f361.loadSpriteSet(0, false);
                  var1.C25_f361.setAnimation((byte)3, (byte)-1, false);
                  var1.C25_f361.activate();
               }

               if (var1.C25_f362 == null) {
                  var1.C25_f362 = new GameObject[4];

                  for(var6 = 0; var6 < var1.C25_f362.length; ++var6) {
                     var1.C25_f362[var6] = new GameObject();
                     var1.C25_f362[var6].loadSpriteSet(223, false);
                     if (var6 <= 1) {
                        var1.C25_f362[var6].setWorldPosition(getScreenWidth() >> 1, 20 + var6 * (getScreenHeight() - 20));
                     } else {
                        var1.C25_f362[var6].setWorldPosition(10 + var6 % 2 * (getScreenWidth() - 20), getScreenHeight() >> 1);
                     }

                     var1.C25_f362[var6].setAnimation(var6, (byte)-1, false);
                     var1.o(var6);
                  }
               }

               BattleSystemManager.B().C29_f400 = Image.createImage(GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
               Graphics var8 = BattleSystemManager.B().C29_f400.getGraphics();
               var1.C25_f286.a((byte)0, (byte)var1.C25_f286.currentDirection);
               var1.C25_f285.renderCutsceneMode(var8);
               var1.changeState((byte)4);
            } else {
               var1.gameController.b("Khu này không có bản đồ");
            }
         } else if (this.isKeyPressed(2)) {
            this.gameController.C9_f125 = 0;
            this.changeState((byte)10);
         } else if (this.isKeyPressed(8)) {
            this.gameController.C9_f125 = 1;
            this.changeState((byte)10);
         } else if (this.isKeyPressed(512)) {
            label235: {
               boolean var10;
               if (this.C25_f290 != 3 || this.C25_f291 != 7) {
                  if (this.C25_f286.C53_f765 >= 0 && this.C25_f348.isEventVisible) {
                     if (this.C25_f286.s()) {
                        this.C25_f286.t();
                     }

                     var10 = false;
                     break label235;
                  }

                  if (this.C25_f348.isEventVisible) {
                     this.changeState((byte)5);
                     var10 = true;
                     break label235;
                  }
               }

               var10 = false;
            }
         }
      }

      this.C25_f286.r();

      for(int var4 = 0; var4 < this.C25_f287.length; ++var4) {
         this.C25_f287[var4].q();
      }

      if (this.C25_f311 != null && this.C25_f311.isActive()) {
         this.C25_f311.updateMovementTrail(this.C25_f312.sprite, this.C25_f311.sprite);
      }

      this.C25_f286.visualState = this.C25_f286.currentDirection;
      this.C25_f285.updateWorld();
      this.M();
      if (C25_f335 == 1 && paymentActive) {
         this.changeState((byte)25);
      }

      if (!this.C25_f348.hasActiveEvent() && !this.gameController.J() && !C25_f339 && C25_f340[0] != -1 && this.isKeyPressed(65568)) {
         actionType = 4;
         C25_f339 = true;
         this.gameController.C9_f126 = 0;
         this.changeState((byte)7);
         this.gameController.I();
      }

      if (!this.gameController.j() && C25_f315 == 0 && this.O()) {
         this.gameController.b("Có thể tiến hành sản xuất trứng sủng vật");
         C25_f315 = 1;
      }

      this.C25_f348.updateEffects();
      this.gameController.e();
      var1 = this;

      for(int var7 = 0; var7 < var1.C25_f286.C53_f799.size(); ++var7) {
         int[] var9;
         int[] var11 = var9 = (int[])var1.C25_f286.C53_f799.elementAt(var7);
         var11[3] += 5;
         if (var9[3] > 30) {
            var1.C25_f286.C53_f799.removeElementAt(var7);
            --var7;
         }
      }

      if (!this.gameController.j()) {
         this.C25_f348.update();
         this.q();
      }

   }

   private void ag() {
      if (this.gameController.d(C25_f317, C25_f316) && !this.gameController.j() && this.isKeyPressed(196640)) {
         if (GameUtils.pageCount < GameUtils.b()) {
            GameUtils.c();
            this.gameController.b(GameUtils.pageCount);
         } else {
            this.gameController.aF();
            if (this.C25_f287[C25_f318].sprite.spriteSetId <= 85) {
               NPCEntity var10000 = this.C25_f287[C25_f318];
               byte var2 = this.C25_f287[C25_f318].currentAnimationId;
               var10000.currentDirection = var2;
            }

            this.C25_f287[C25_f318].a((byte)0);
            this.C25_f286.a((byte)0, (byte)this.C25_f286.currentDirection);
            this.gameController.C9_f131 = 1;
            if (this.C25_f375) {
               this.C25_f371[this.C25_f373] = true;
               if (this.C25_f373 < this.C25_f372.length - 1) {
                  this.C25_f286.v(1);
                  this.gameController.b("Đạt được 1 huy hiệu");
               } else if (this.C25_f286.b((byte)7, (byte)0) == 0) {
                  this.gameController.b("Đạt được hoàng kim huy hiệu");
                  this.C25_f286.b((byte)7, (byte)0, (byte)2);
                  QuestManager.questDialogState = (byte)(QuestManager.questDescriptions.length / 2);
               }
            }
         }
      }

      this.gameController.f();
      if (this.gameController.C9_f131 == 1 && this.gameController.aA()) {
         this.gameController.C9_f128 = 0;
         this.changeState((byte)27);
      }

      this.C25_f285.updateWorld();
   }

   private boolean a(int[] var1) {
      int var2 = -1;
      if (var1[2] != -1) {
         var2 = GameUtils.getRandomInRange(var1[2], var1[3]);
      }

      if (!this.C()) {
         return false;
      } else {
         int var3 = GameUtils.getRandomInRange(C25_f306[C25_f297[this.C25_f290] + this.C25_f291][3], C25_f306[C25_f297[this.C25_f290] + this.C25_f291][4]);
         this.C25_f286.a((byte)((byte) ResourceManager.gameDatabase[0][var1[0]][1]), var1[0], (byte)1);
         BattleSystemManager.B().a(new int[][]{{var1[0], var3, var2}});
         return true;
      }
   }

   public final void m(int var1) {
      int var2 = 0;

      while(true) {
         int var4 = this.C25_f291;
         int var3 = this.C25_f290;
         if (var2 >= (C25_f306[C25_f297[var3] + var4].length - 5) / 4) {
            return;
         }

         var4 = this.C25_f291;
         var3 = this.C25_f290;
         if (var1 == C25_f306[C25_f297[var3] + var4][5 + (var2 << 2) + 4]) {
            int[] var5 = new int[4];
            var4 = this.C25_f291;
            var3 = this.C25_f290;
            System.arraycopy(C25_f306[C25_f297[var3] + var4], 5 + (var2 << 2), var5, 0, var5.length);
            this.a(var5);
            this.ah();
            return;
         }

         ++var2;
      }
   }

   public final void e(boolean var1) {
      if (this.C25_f370 != var1) {
         if (this.C()) {
            this.C25_f370 = var1;
            int var3 = 0;

            while(true) {
               int var4 = this.C25_f291;
               int var2 = this.C25_f290;
               if (var3 >= (C25_f306[C25_f297[var2] + var4].length - 5) / 4) {
                  return;
               }

               var4 = this.C25_f291;
               var2 = this.C25_f290;
               short var5 = C25_f306[C25_f297[var2] + var4][5 + (var3 << 2) + 4];
               if (var1) {
                  this.C25_f287[var5].activate();
               } else {
                  this.C25_f287[var5].deactivate();
               }

               ++var3;
            }
         }
      }
   }

   public final void M() {
      if (this.C25_f286.E()) {
         boolean var10000;
         label47: {
            byte var2 = TileMapRenderer.getInstance().getTileAt(0, PlayerCharacter.p().worldX, PlayerCharacter.p().worldY);
            int[] var3 = null;
            C25_f369 = var2;
            switch(var2) {
            case 0:
               if (C25_f307.size() <= 0) {
                  var10000 = false;
                  break label47;
               }

               var3 = (int[])C25_f307.elementAt(GameUtils.getRandomInt(C25_f307.size()));
               break;
            case 1:
               if (C25_f308.size() <= 0) {
                  var10000 = false;
                  break label47;
               }

               var3 = (int[])C25_f308.elementAt(GameUtils.getRandomInt(C25_f308.size()));
               break;
            case 2:
               if (C25_f309.size() <= 0) {
                  var10000 = false;
                  break label47;
               }

               var3 = (int[])C25_f309.elementAt(GameUtils.getRandomInt(C25_f309.size()));
               break;
            case 3:
               var10000 = false;
               break label47;
            case 4:
               if (C25_f310.size() <= 0) {
                  var10000 = false;
                  break label47;
               }

               var3 = (int[])C25_f310.elementAt(GameUtils.getRandomInt(C25_f310.size()));
            }

            var10000 = this.a(var3);
         }

         if (!var10000) {
            this.C25_f286.C53_f771 = this.C25_f286.D();
            return;
         }

         if (this.C25_f290 == 3 && this.C25_f291 == 7) {
            QuestManager.isMapControl = false;
         } else {
            QuestManager.isMapControl = true;
         }

         this.ah();
      }

   }

   public final void N() {
      BattleSystemManager.B().C29_f400 = Image.createImage(GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
      Graphics var1 = BattleSystemManager.B().C29_f400.getGraphics();
      this.C25_f285.renderCutsceneMode(var1);
   }

   private void ah() {
      BattleSystemManager var10000 = BattleSystemManager.B();
      BattleSystemManager.B().getClass();
      var10000.C29_f397 = 0;
      BattleSystemManager.B().C29_f398 = 0;
      this.N();
      this.C25_f286.a((byte)0, (byte)this.C25_f286.currentDirection);
      this.C25_f286.C53_f771 = this.C25_f286.D();
      GameScreenManager.getInstance().changeState((byte)12);
      this.C25_f342.playBackgroundMusic(4, 1);
   }

   public static int e(int var0, int var1) {
      return C25_f297[var0] + var1;
   }

   public final void a(int var1, int var2, byte var3, boolean var4) {
      int var6 = this.C25_f291;
      int var5 = this.C25_f290;
      if (C25_f302[C25_f297[var5] + var6] != null) {
         var6 = this.C25_f291;
         var5 = this.C25_f290;
         if (C25_f302[C25_f297[var5] + var6][var1] != null) {
            var6 = this.C25_f291;
            var5 = this.C25_f290;
            C25_f304[C25_f297[var5] + var6][0] = var4;
            var6 = this.C25_f291;
            var5 = this.C25_f290;
            C25_f302[C25_f297[var5] + var6][var1][var2] = var3;
         }
      }

   }

   public final void a(int var1, int var2, int var3) {
      int var5 = this.C25_f291;
      int var4 = this.C25_f290;
      if (C25_f303[C25_f297[var4] + var5] != null) {
         var5 = this.C25_f291;
         var4 = this.C25_f290;
         if (C25_f303[C25_f297[var4] + var5][var1] != null) {
            var5 = this.C25_f291;
            var4 = this.C25_f290;
            C25_f303[C25_f297[var4] + var5][var1][var2] = (short)var3;
         }
      }

   }

   public final boolean n(int var1) {
      for(int var2 = 0; var2 < this.C25_f329.length / 4; ++var2) {
         if (this.C25_f329[var2 << 2] == this.C25_f290 && this.C25_f329[(var2 << 2) + 1] == this.C25_f291 && var1 == this.C25_f329[(var2 << 2) + 2] && this.C25_f286.C53_f779[this.C25_f329[(var2 << 2) + 3]][0] == 2) {
            return true;
         }
      }

      return false;
   }

   public final boolean O() {
      return this.C25_f286.C53_f786 == 0 && C25_f314 >= 10 || this.C25_f286.C53_f786 > 0 && C25_f314 >= 30;
   }

   private void ai() {
      this.C25_f373 = (byte)this.C25_f371.length;

      for(int var1 = 0; var1 < this.C25_f371.length; ++var1) {
         if (!this.C25_f371[var1]) {
            this.C25_f373 = (byte)var1;
            break;
         }
      }

      boolean var3 = false;

      for(int var2 = this.C25_f372.length - 1; var2 >= 0; --var2) {
         if (this.C25_f286.C53_f783 >= this.C25_f372[var2]) {
            this.C25_f374 = (byte)var2;
            var3 = true;
            break;
         }
      }

      if (!var3) {
         this.C25_f374 = -1;
      }

   }

   public final void q() {
      String var2;
      switch(actionType) {
      case 1:
         if (currentAction == 0) {
            b(0, 1);
            if (paymentActive) {
               b(1, 1);
            } else {
               b(1, 0);
            }

            ++currentAction;
            this.changeState((byte)6);
            return;
         }

         if (currentAction == 1) {
            ++currentAction;
            this.gameController.c("Hãy lựa chọn #2Sủng vật");
            return;
         }

         if (currentAction == 3) {
            b(1, 0);
            var2 = getLocalizedText(ResourceManager.gameDatabase[0][this.C25_f286.C53_f777[GameEngineBase.d(1)].r()][0]);
            ++currentAction;
            this.gameController.c("Hãy lựa chọn #2" + var2);
            return;
         }

         if (currentAction == 4) {
            if (this.gameController.aB() && GameEngineBase.a((int)this.gameController.C9_f125, (int)0)) {
               ++currentAction;
               this.gameController.c("Hãy nhấn #2nút 5");
               return;
            }
         } else {
            if (currentAction == 6) {
               ++currentAction;
               this.gameController.c("Hãy lựa chọn #2Vật phẩm trang sức");
               return;
            }

            if (currentAction == 8) {
               b(1, 0);
               ++currentAction;
               this.gameController.c("Nhấn #2nút 5#1 trang thượng vật phẩm trang sức");
               return;
            }

            if (currentAction == 10) {
               this.gameController.c("Nhấn #2nút mềm phải#0 để quay lại");
               b(1, -1);
               b(0, 2);
               ++currentAction;
               return;
            }
         }
      case 2:
      case 5:
      default:
         break;
      case 3:
         if (currentAction == 0) {
            ++currentAction;
            b(1, 0);
            b(0, 1);
            this.changeState((byte)1);
            return;
         }

         if (currentAction == 1) {
            if (a(this.gameController.C9_f125, 0)) {
               ++currentAction;
               this.gameController.c("Hãy nhấn vào mục #2Mua sắm");
               return;
            }
         } else {
            if (currentAction == 3) {
               ++currentAction;
               this.gameController.c("Trước tiên hãy mua #2Hồng sắc ốc biển#1");
               return;
            }

            if (currentAction == 4) {
               if (this.gameController.aB()) {
                  b(1, 1);
                  ++currentAction;
                  return;
               }
            } else if (currentAction == 5) {
               if (a(this.gameController.C9_f125, 0)) {
                  ++currentAction;
                  this.gameController.c("Nhấn #2nút 5#1 mua sắm");
                  return;
               }
            } else if (currentAction == 7) {
               this.gameController.c("Hãy nhấn #2nút mềm phải#1 để quay lại");
               b(1, -1);
               b(0, 2);
               ++currentAction;
               return;
            }
         }
         break;
      case 4:
         if (currentAction == 0) {
            b(0, 1);

            for(int var1 = 0; var1 < this.C25_f286.C53_f778; ++var1) {
               if (this.C25_f286.C53_f777[var1].t() == C25_f340[0] && this.C25_f286.C53_f777[var1].r() == C25_f340[1]) {
                  b(1, var1);
                  break;
               }
            }

            ++currentAction;
            var2 = getLocalizedText(ResourceManager.getDatabaseValue((byte)0, (short)this.C25_f286.C53_f777[GameEngineBase.d(1)].r(), (byte)0));
            this.gameController.c("Hãy lựa chọn #2" + var2 + "#0 tiến hành tiến hóa");
            return;
         }

         if (currentAction == 1) {
            if (a(this.gameController.C9_f125, 0) && this.gameController.aB()) {
               ++currentAction;
               this.gameController.c("Hãy nhấn #2nút 5#0 để tiếp tục");
               return;
            }
         } else if (currentAction == 3) {
            if (a(this.gameController.C9_f126, 0)) {
               ++currentAction;
               this.gameController.c("Nhấn #2nút 5#0 để vào mục Tiến hóa");
               return;
            }
         } else if (currentAction == 5) {
            ++currentAction;
            this.gameController.c("Nhấn #2nút mềm trái#0 để Tiến hóa");
            return;
         }
         break;
      case 6:
         if (currentAction == 0) {
            ++currentAction;
            b(0, 1);
            if (paymentActive) {
               b(1, 2);
            } else {
               b(1, 1);
            }

            this.changeState((byte)6);
            return;
         }

         if (currentAction == 1) {
            ++currentAction;
            this.gameController.c("Hãy lựa chọn #2Ba lô#0");
            return;
         }

         if (currentAction == 2) {
            if (this.gameController.aB() && a(this.gameController.C9_f125, 0)) {
               this.gameController.c("Nhấn #2nút mềm trái#0 vào Tuyển hạng");
               ++currentAction;
               return;
            }
         } else {
            if (currentAction == 4) {
               ++currentAction;
               this.gameController.c("Hãy sử dụng #2Gia tốc dược#0");
               return;
            }

            if (currentAction == 5) {
               if (this.gameController.aB() && a(this.gameController.C9_f136, 0)) {
                  ++currentAction;
                  this.gameController.c("Nhấn #2nút mềm trái#0 sử dụng");
                  return;
               }
            } else {
               if (currentAction == 7) {
                  ++currentAction;
                  b(0, 3);
                  b(2, 1);
                  b(1, 3);
                  this.gameController.c("Hãy lựa chọn #2Đặc thù đạo cụ#0 ấp trứng trứng sủng vật");
                  return;
               }

               if (currentAction == 9) {
                  if (this.gameController.aB() && a(this.gameController.C9_f136, 0)) {
                     b(0, 1);
                     this.gameController.c("Nhấn #2nút mềm trái#0 để Ấp trứng");
                     ++currentAction;
                     return;
                  }
               } else if (currentAction == 11) {
                  ++currentAction;
                  b(0, 2);
                  b(1, -1);
                  this.gameController.c("Nhấn #2nút mềm phải#0 để quay lại");
               }
            }
         }
      }

   }

   public final void r() {
      int var1;
      switch(actionType) {
      case 1:
         if (currentAction == 2 || currentAction == 7) {
            ++currentAction;
            return;
         }

         if (currentAction == 5) {
            b(1, 2);
            ++currentAction;
            return;
         }

         if (currentAction == 9) {
            ++currentAction;
            int var2 = this.C25_f291;
            var1 = this.C25_f290;
            this.C25_f348.questStates[C25_f297[var1] + var2][this.C25_f348.getCurrentEventIndex()] = 3;
            if (this.C25_f348.eventScripts != null) {
               this.C25_f348.eventScripts[this.C25_f348.getCurrentEventIndex()].setExecutionState((byte)3);
               return;
            }
         }
      case 2:
      case 5:
      default:
         break;
      case 3:
         if (currentAction != 6 && currentAction != 2) {
            break;
         }

         ++currentAction;
         return;
      case 4:
         if (currentAction == 2) {
            b(1, 5);
            ++currentAction;
            return;
         }

         if (currentAction == 4) {
            ++currentAction;
            return;
         }

         if (currentAction == 6) {
            this.gameController.c("Nhấn #2nút mềm phải#0 để quay lại");
            b(1, -1);
            b(0, 2);
            ++currentAction;
            return;
         }
         break;
      case 6:
         if (currentAction == 3) {
            for(var1 = 0; var1 < this.C25_f286.C53_f787.size() + this.C25_f286.C53_f788.size(); ++var1) {
               if (var1 >= this.C25_f286.C53_f788.size()) {
                  if (this.C25_f286.C53_f787.size() <= 0) {
                     break;
                  }

                  if (((int[])this.C25_f286.C53_f787.elementAt(var1 - this.C25_f286.C53_f788.size()))[0] == 14) {
                     b(1, var1);
                     break;
                  }
               }
            }

            ++currentAction;
            return;
         }

         if (currentAction == 6 || currentAction == 10) {
            ++currentAction;
            return;
         }

         if (currentAction == 8 && a(this.gameController.C9_f125, 1)) {
            this.gameController.c("Hãy lựa chọn #2Trứng sủng vật#0 để ấp trứng");
            b(2, 0);
            b(1, 0);
            ++currentAction;
         }
      }

   }

   protected static String[] a(long var0) {
      String[] var2;
      (var2 = new String[2])[1] = var2[0] = "0'00\"000";
      long var3 = var0 % 1000L;
      long var5 = (var0 /= 1000L) % 60L;
      long var7 = (var0 /= 60L) % 60L;
      long var9 = var0 / 60L;
      String var12;
      if (var3 < 10L) {
         var12 = "00" + var3;
      } else if (var3 < 100L) {
         var12 = "0" + var3;
      } else {
         var12 = "" + var3;
      }

      String var4;
      if (var5 < 10L) {
         var4 = "0" + var5 + "\"";
      } else {
         var4 = var5 + "\"";
      }

      String var11 = var0 + "'";
      var2[0] = var11 + var4 + var12;
      String var1 = var9 + "'";
      var11 = var7 + "\"";
      if (var5 < 10L) {
         var4 = "0" + var5;
      } else {
         var4 = "" + var5;
      }

      var2[1] = var1 + var11 + var4;
      return var2;
   }
}
