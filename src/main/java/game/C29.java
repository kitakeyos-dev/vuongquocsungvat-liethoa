package game;

import a.GameUtils;
import a.GameEngineBase;
import a.a.GameObject;
import a.a.EffectEntity;
import a.b.GameEntity;
import a.b.ResourceManager;
import layout.DialogManager;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class C29 extends GameEngineBase {
   private final byte[] C29_f393 = new byte[]{2, 4};
   private static C29 C29_f394;
   private static C53 C29_f395;
   private byte C29_f396 = 0;
   public int C29_f397;
   public byte C29_f398;
   private byte C29_f399;
   public Image C29_f400;
   private int[][] C29_f401;
   public C41[] C29_f402;
   private byte[] C29_f403;
   public byte[] C29_f404;
   public byte[] C29_f405;
   private byte[] C29_f406;
   public byte C29_f407 = 0;
   public C41 C29_f408;
   private Vector C29_f409;
   public byte C29_f410;
   private boolean C29_f411;
   private static Vector C29_f412;
   public static Vector C29_f413;
   private boolean C29_f414;
   public boolean C29_f415;
   private boolean C29_f416;
   private boolean C29_f417;
   private boolean C29_f418;
   private byte C29_f419;
   private byte[] C29_f420;
   private byte[] C29_f421;
   private byte C29_f422;
   private int C29_f423;
   private EffectEntity C29_f424;
   private byte C29_f425 = 0;
   private byte C29_f426 = 0;
   private byte C29_f427 = 0;
   private byte C29_f428 = 0;
   private boolean C29_f429;
   private boolean C29_f430;
   private byte[] C29_f431;
   private int[] C29_f432;
   private boolean C29_f433;
   private byte C29_f434;
   private byte C29_f435;
   private byte C29_f436;
   private byte[] C29_f437;
   private byte[][] C29_f438;
   private byte[] C29_f439;
   private byte[] C29_f440;
   private byte[][] C29_f441 = new byte[][]{{3, 5, 13}, {0, 1, 2, 3, 8, 9, 10}};
   private GameObject C29_f442;
   private boolean C29_f443;
   public static byte C29_f444 = 0;
   private GameObject[] C29_f445;
   private static short[][] C29_f446;
   private static short[][][] C29_f447;
   public static byte[][][] C29_f448;
   private static byte[][] C29_f449;
   public static short[][] C29_f450;
   private static byte[][] C29_f451;
   private static byte[][] C29_f452;
   private static byte[][] C29_f453;
   private int C29_f454;
   private int C29_f455;
   private int C29_f456;
   private int C29_f457;
   private int C29_f458;
   private int C29_f459;
   private String C29_f460 = null;
   private static Image[] C29_f461;
   private static short[][] C29_f462;
   private Vector C29_f463 = new Vector();
   private Vector C29_f464 = new Vector();
   private int C29_f465;
   private boolean C29_f466;
   private int C29_f467;
   private byte[] C29_f468;
   private byte[] C29_f469;
   private byte[] C29_f470;

   public static C29 B() {
      if (C29_f394 == null) {
         C29_f394 = new C29();
      }

      return C29_f394;
   }

   public C29() {
      new Vector();
      new Vector();
      this.C29_f465 = 0;
      this.C29_f466 = false;
      this.C29_f467 = 0;
      this.C29_f468 = new byte[]{10, 11, 12, 13, 15};
      this.C29_f469 = new byte[]{10, 12, 13, 14, 15, 16};
      this.C29_f470 = new byte[]{105, 100, 80, 60, 40, 20, 5};
      if (this.C29_f409 == null) {
         this.C29_f409 = new Vector();
      }

      if (C29_f412 == null) {
         C29_f412 = new Vector();
      }

      if (C29_f413 == null) {
         C29_f413 = new Vector();
      }

   }

   public final void cleanupCurrentScreen() {
      this.C29_f409.removeAllElements();

      int var1;
      for(var1 = 0; var1 < this.C29_f405.length; ++var1) {
         this.n(var1).D();
         this.n(var1).E();
         this.n(var1).C41_f663 = 0;
         this.n(var1).e(false);
         this.n(var1).C41_f664.removeAllElements();
         this.n(var1).C41_f665.removeAllElements();
         C41 var10000 = this.n(var1);
         C41 var10001 = this.n(var1);
         byte var3 = 1;
         var10000.u(var10001.secondaryStates[var3]);
      }

      C29_f412.removeAllElements();
      C29_f413.removeAllElements();

      for(var1 = 0; var1 < this.C29_f402.length; ++var1) {
         if (this.C29_f402[var1] != null) {
            this.C29_f402[var1].deactivate();
            this.C29_f402[var1] = null;
         }
      }

      for(var1 = 0; var1 < C29_f461.length; ++var1) {
         if (C29_f461[var1] != null) {
            C29_f461[var1] = null;
         }
      }

      C29_f461 = null;
      if (this.C29_f442 != null) {
         this.C29_f442.sprite.forceCleanup();
         this.C29_f442 = null;
      }

      if (this.C29_f445 != null) {
         for(var1 = 0; var1 < this.C29_f445.length; ++var1) {
            this.C29_f445[var1].sprite.forceCleanup();
            this.C29_f445[var1] = null;
         }

         this.C29_f445 = null;
      }

      this.C29_f406 = null;
      this.C29_f410 = 0;
      this.C29_f423 = 0;
      this.C29_f408 = null;
      this.C29_f411 = false;
      this.C29_f414 = false;
      this.C29_f424 = null;
      this.C29_f402 = null;
      this.C29_f400 = null;
      this.C29_f403 = null;
      this.C29_f404 = null;
      this.C29_f420 = null;
      this.C29_f421 = null;
      this.C29_f437 = null;
      this.C29_f431 = null;
      C29_f451 = null;
      C29_f452 = null;
      C25.C25_f315 = 0;
      C29_f446 = null;
      C29_f447 = null;
      C29_f448 = null;
      C29_f449 = null;
      C29_f450 = null;
      C29_f462 = null;
      C29_f451 = null;
      C29_f452 = null;
      C29_f453 = null;
      this.gameController.ai();
   }

   public final boolean initializeGame() {
      this.startGameTimer();
      this.C29_f406 = new byte[2];
      C29_f395 = C53.p();
      this.C29_f405 = new byte[C29_f395.C53_f778];
      int var1 = 0;

      int var2;
      for(var2 = 0; var2 < C29_f395.C53_f778; ++var2) {
         this.C29_f405[var2] = (byte)var2;
         if (this.n(var2) != null && this.n(var2).T()) {
            ++var1;
         }

         this.n(var2).j(this.n(var2).A());
      }

      if (var1 == 1 && this.C29_f397 == 1) {
         this.C29_f402 = new C41[3];
      } else {
         this.C29_f402 = new C41[this.C29_f393[this.C29_f397]];
      }

      C29_f446 = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/pos.mid"));
      InputStream var4 = GameUtils.openInputStream("/data/script/cpos.mid");
      C29_f447 = new short[3][][];

      for(var2 = 0; var2 < 3; ++var2) {
         C29_f447[var2] = GameUtils.readShortMatrix(var4);
      }

      var4 = GameUtils.openInputStream("/data/script/layer.mid");
      C29_f448 = new byte[15][][];

      for(var2 = 0; var2 < C29_f448.length; ++var2) {
         C29_f448[var2] = GameUtils.readByteMatrix(var4);
      }

      C29_f449 = GameUtils.readByteMatrix(GameUtils.openInputStream("/data/script/effect.mid"));
      C29_f450 = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/speffect.mid"));
      C29_f462 = GameUtils.readShortMatrix(GameUtils.openInputStream("/data/script/blood.mid"));
      C29_f451 = GameUtils.readByteMatrix(var4 = GameUtils.openInputStream("/data/script/bufDebuf.mid"));
      C29_f452 = GameUtils.readByteMatrix(var4);
      C29_f453 = GameUtils.readByteMatrix(var4);
      if (this.C29_f397 == 0) {
         if (this.C29_f398 == 1) {
            this.C29_f399 = 2;
         } else {
            this.C29_f399 = 0;
         }
      } else {
         this.C29_f399 = 1;
      }

      this.C29_f445 = new GameObject[this.C29_f402.length + 2];

      for(var2 = 0; var2 < this.C29_f445.length; ++var2) {
         this.C29_f445[var2] = new GameObject();
         this.C29_f445[var2].loadSpriteSet(294, false);
         if (var2 == this.C29_f402.length + 1) {
            this.C29_f445[var2].setAnimation((byte)2, (byte)-1, false);
            if (this.C29_f397 == 0) {
               this.C29_f445[var2].activate();
            }
         } else if (var2 == this.C29_f402.length) {
            this.C29_f445[var2].setAnimation((byte)1, (byte)-1, false);
            this.C29_f445[var2].activate();
            this.C29_f445[var2].setVisible(false);
         } else {
            this.C29_f445[var2].setAnimation((byte)0, (byte)-1, false);
            this.C29_f445[var2].setWorldPosition(C29_f447[this.C29_f399][var2][2], C29_f447[this.C29_f399][var2][3]);
            this.C29_f445[var2].activate();
         }
      }

      this.C29_f420 = new byte[this.C29_f402.length];
      this.C29_f421 = new byte[this.C29_f402.length];
      this.r(this.C29_f402.length);
      var2 = 0;

      for(var1 = 0; var1 < this.C29_f402.length; ++var1) {
         if (this.C29_f397 == 0) {
            if (var1 <= 0) {
               switch(this.C29_f398) {
               case 0:
               case 1:
                  this.q(var1);
                  break;
               case 2:
                  this.q(var1);
               }
            } else {
               while(!this.n(this.C29_f405[var2]).T()) {
                  ++var2;
               }

               this.e(var1, var2);
               C29_f412.addElement(this.n(this.C29_f405[var2]));
               this.f(0, var2);
            }
         } else if (var1 <= 1) {
            switch(this.C29_f398) {
            case 0:
            case 1:
               this.q(var1);
               break;
            case 2:
               this.q(var1);
            }
         } else {
            while(!this.n(this.C29_f405[var2]).T()) {
               ++var2;
            }

            this.e(var1, var2);
            C29_f412.addElement(this.n(this.C29_f405[var2]));
            this.f(var1 - 2, var2);
            ++var2;
         }
      }

      this.Q();
      C29_f461 = new Image[3];

      for(var1 = 0; var1 < C29_f461.length; ++var1) {
         C29_f461[var1] = GameUtils.loadImage("/data/tex/", "blood_" + var1);
      }

      for(var1 = 0; var1 < C29_f395.C53_f778; ++var1) {
         C41 var10000 = C29_f395.C53_f777[var1];
         C41 var10001 = C29_f395.C53_f777[var1];
         byte var3 = 1;
         var10000.C41_f649 = var10001.secondaryStates[var3];
      }

      this.changeState((byte)0);
      stopGameTimer();
      return true;
   }

   public final void C() {
      int var1 = 0;

      for(int var2 = 0; var2 < C29_f395.C53_f778; ++var2) {
         if (this.n(var2) != null && this.n(var2).T()) {
            ++var1;
         }

         this.n(var2).j(this.n(var2).A());
      }

      C41[] var4;
      if (this.C29_f397 == 0) {
         var4 = new C41[1];
      } else {
         var4 = new C41[2];
      }

      for(byte var3 = 0; var3 < this.C29_f402.length; ++var3) {
         if (this.C29_f402[var3].s() == 1) {
            var4[var3] = this.C29_f402[var3];
         }
      }

      if (var1 == 1 && this.C29_f397 == 1) {
         this.C29_f402 = new C41[3];
      } else {
         this.C29_f402 = new C41[this.C29_f393[this.C29_f397]];
      }

      this.C29_f445 = new GameObject[this.C29_f402.length + 2];

      int var5;
      for(var5 = 0; var5 < this.C29_f445.length; ++var5) {
         this.C29_f445[var5] = new GameObject();
         this.C29_f445[var5].loadSpriteSet(294, false);
         if (var5 == this.C29_f402.length + 1) {
            this.C29_f445[var5].setAnimation((byte)2, (byte)-1, false);
            if (this.C29_f397 == 0) {
               this.C29_f445[var5].activate();
            }
         } else if (var5 == this.C29_f402.length) {
            this.C29_f445[var5].setAnimation((byte)1, (byte)-1, false);
            this.C29_f445[var5].activate();
            this.C29_f445[var5].setVisible(false);
         } else {
            this.C29_f445[var5].setAnimation((byte)0, (byte)-1, false);
            this.C29_f445[var5].setWorldPosition(C29_f447[this.C29_f399][var5][2], C29_f447[this.C29_f399][var5][3]);
            this.C29_f445[var5].activate();
         }
      }

      this.C29_f420 = new byte[this.C29_f402.length];
      this.C29_f421 = new byte[this.C29_f402.length];
      this.C29_f423 = 0;
      this.r(this.C29_f402.length);
      var5 = 0;

      for(var1 = 0; var1 < this.C29_f402.length; ++var1) {
         if (this.C29_f397 == 0) {
            if (var1 <= 0) {
               this.C29_f402[var1] = var4[var1];
            } else {
               while(!this.n(this.C29_f405[var5]).T()) {
                  ++var5;
               }

               this.e(var1, var5);
               C29_f412.addElement(this.n(this.C29_f405[var5]));
               this.f(0, var5);
            }
         } else if (var1 <= 1) {
            this.C29_f402[var1] = var4[var1];
         } else {
            while(!this.n(this.C29_f405[var5]).T()) {
               ++var5;
            }

            this.e(var1, var5);
            C29_f412.addElement(this.n(this.C29_f405[var5]));
            this.f(var1 - 2, var5);
            ++var5;
         }
      }

      this.Q();
   }

   public final void D() {
      if (this.C29_f397 == 1) {
         int var1;
         for(var1 = 0; ((C41)this.C29_f409.elementAt(var1)).s() != 0 || ((C41)this.C29_f409.elementAt(var1)).s() == 0 && !((C41)this.C29_f409.elementAt(var1)).T(); ++var1) {
         }

         if (this.C29_f402[0].T()) {
            this.gameController.b(this.C29_f402[this.C29_f404[var1]], this.C29_f402[0]);
            return;
         }

         this.gameController.b(this.C29_f402[this.C29_f404[var1]], this.C29_f402[1]);
      }

   }

   public final void E() {
      this.gameController = C9.a();
      this.gameController.a((GameEngineBase)this);
      this.dialogManager = DialogManager.getInstance();
      if (this.C29_f397 == 0) {
         this.gameController.a(this.C29_f402[1], this.C29_f402[0]);
      } else {
         this.gameController.a(this.C29_f402[2], this.C29_f402[0]);
      }
   }

   public final void e(int var1, int var2) {
      this.C29_f402[var1] = this.n(this.C29_f405[var2]);
      this.C29_f402[var1].e(true);
      this.C29_f402[var1].f((int)0);
      C41 var10000 = this.C29_f402[var1];
      byte var3 = 0;
      var10000.currentDirection = var3;
      this.C29_f402[var1].setWorldPosition(C29_f447[this.C29_f399][var1][0], C29_f447[this.C29_f399][var1][1]);
      this.C29_f402[var1].activate();
   }

   private void q(int var1) {
      this.C29_f402[var1] = new C41();
      this.C29_f402[var1].a(this.C29_f401[this.C29_f406[0]][0], this.C29_f401[this.C29_f406[0]][1], (short)-1, (byte)2, (short)this.C29_f401[this.C29_f406[0]][2], (byte)-1);
      this.C29_f402[var1].f((int)1);
      C41 var10000 = this.C29_f402[var1];
      byte var3 = 1;
      var10000.currentDirection = var3;
      this.C29_f402[var1].setWorldPosition(C29_f447[this.C29_f399][var1][0], C29_f447[this.C29_f399][var1][1]);
      short var2 = ResourceManager.gameDatabase[0][this.C29_f401[this.C29_f406[0]][0]][1];
      this.C29_f402[var1].g((byte)(var2 * 10));
      this.C29_f402[var1].H();
      this.C29_f402[var1].activate();
      C29_f395.a((byte)((byte)this.C29_f402[var1].j((byte)1)), this.C29_f402[var1].r(), (byte)1);
      ++this.C29_f406[0];
   }

   private void I() {
      this.C29_f426 = this.C29_f425;
      this.C29_f431 = C29_f449[this.C29_f408.C41_f659];
      if (this.C29_f431[this.C29_f426 * 7 + 1] == 1) {
         this.C29_f424 = new EffectEntity();
         short var1;
         short var2;
         short var3;
         short var4;
         short var5;
         if (this.C29_f431[this.C29_f426 * 7] == 0) {
            var1 = (short)((C41)this.C29_f408.followTarget).worldX;
            var2 = (short)((C41)this.C29_f408.followTarget).worldY;
            var3 = (short)((C41)this.C29_f408.followTarget).r();
            var3 = ResourceManager.gameDatabase[0][var3][17];
            var4 = (short)((C41)this.C29_f408.followTarget).q();
            var5 = (short)((C41)this.C29_f408.followTarget).currentDirection;
         } else {
            var1 = (short)this.C29_f408.worldX;
            var2 = (short)this.C29_f408.worldY;
            var3 = (short)this.C29_f408.r();
            var3 = ResourceManager.gameDatabase[0][var3][17];
            var4 = (short)this.C29_f408.q();
            var5 = (short)this.C29_f408.currentDirection;
         }

         short[] var6;
         short[] var7 = new short[(var6 = C29_f450[this.C29_f431[this.C29_f426 * 7 + 2]]).length + 5];
         System.arraycopy(var6, 1, var7, 6, var6.length - 1);
         short[] var8;
         System.arraycopy(var8 = new short[]{var6[0], var1, var2, var3, var4, var5}, 0, var7, 0, var8.length);
         this.C29_f424.initializeEffect(var7);
         this.C29_f424.setInteractable(true);
      } else if (this.C29_f431[this.C29_f426 * 7] == 0) {
         ((C41)this.C29_f408.followTarget).a((short)this.C29_f431[this.C29_f426 * 7 + 2], this.C29_f431[this.C29_f426 * 7 + 3]);
      } else {
         this.C29_f408.a((short)this.C29_f431[this.C29_f426 * 7 + 2], this.C29_f431[this.C29_f426 * 7 + 3]);
      }

      ++this.C29_f425;
   }

   private boolean a(C41 var1) {
      while(!this.b(var1)) {
         for(int var2 = 0; var2 < this.C29_f441[this.C29_f439[this.C29_f436 << 1]].length; ++var2) {
            if (this.C29_f441[this.C29_f439[this.C29_f436 << 1]][var2] == this.C29_f439[(this.C29_f436 << 1) + 1]) {
               return false;
            }
         }

         if (this.C29_f439[this.C29_f436 << 1] == 0) {
            var1.o(this.C29_f439[(this.C29_f436 << 1) + 1]);
            var1.d(this.C29_f439[(this.C29_f436 << 1) + 1], this.C29_f440[this.C29_f436]);
         } else if (this.C29_f439[this.C29_f436 << 1] == 1) {
            var1.q(this.C29_f439[(this.C29_f436 << 1) + 1]);
            var1.c(this.C29_f439[(this.C29_f436 << 1) + 1], this.C29_f440[this.C29_f436]);
         }

         if (var1.s() == 0) {
            this.gameController.a(var1, false);
            this.gameController.a(var1);
         } else {
            this.gameController.b(var1, this.C29_f466);
            this.gameController.b(var1);
         }

         ++this.C29_f436;
      }

      return true;
   }

   private void J() {
      C41 var1 = (C41)this.C29_f409.elementAt(this.C29_f410);
      this.C29_f435 = this.C29_f434;
      this.C29_f437 = this.C29_f438[this.C29_f436];
      if (this.C29_f437[this.C29_f435 << 2] == 1) {
         this.C29_f424 = new EffectEntity();
         short var2 = (short)var1.worldX;
         short var3 = (short)var1.worldY;
         short var4 = (short)var1.r();
         var4 = ResourceManager.gameDatabase[0][var4][17];
         short var5 = (short)var1.q();
         short var8 = (short)var1.currentDirection;
         short[] var6;
         short[] var7 = new short[(var6 = C29_f450[this.C29_f437[(this.C29_f435 << 2) + 1]]).length + 5];
         System.arraycopy(var6, 1, var7, 6, var6.length - 1);
         short[] var9;
         System.arraycopy(var9 = new short[]{var6[0], var2, var3, var4, var5, var8}, 0, var7, 0, var9.length);
         this.C29_f424.initializeEffect(var7);
         this.C29_f424.setInteractable(true);
      } else {
         var1.a((short)this.C29_f437[(this.C29_f435 << 2) + 1], this.C29_f437[(this.C29_f435 << 2) + 2]);
      }

      ++this.C29_f434;
   }

   private void a(C41 var1, boolean var2) {
      if (!var1.T()) {
         var1.D();
         var1.E();
         this.gameController.b(var1);
         this.h(var1);
         ++this.C29_f406[1];
      }

      if (this.C29_f406[1] >= this.C29_f401.length) {
         W();
         this.changeState((byte)8);
      } else if (!var1.T()) {
         for(int var3 = 0; var3 < this.C29_f402.length; ++var3) {
            if (this.C29_f402[var3].m(11) && this.C29_f402[this.C29_f402[var3].C41_f641[11][1]].equals(var1)) {
               this.C29_f402[var3].n(11);
            }
         }

         if (this.C29_f406[0] < this.C29_f401.length) {
            this.q(this.C29_f404[this.C29_f410]);
            this.C29_f407 = this.C29_f404[this.C29_f410];
            this.changeState((byte)15);
         } else {
            ++this.C29_f410;
            this.L();
         }
      } else {
         if (var2) {
            this.changeState((byte)2);
         }

      }
   }

   private void b(C41 var1, boolean var2) {
      int var3;
      for(var3 = 0; var3 < this.C29_f405.length && !this.n(this.C29_f405[var3]).T(); ++var3) {
      }

      if (var3 >= this.C29_f405.length) {
         this.changeState((byte)9);
      } else if (!var1.T()) {
         for(int var4 = 0; var4 < this.C29_f402.length; ++var4) {
            if (this.C29_f402[var4].m(11) && this.C29_f402[this.C29_f402[var4].C41_f641[11][1]].equals(var1)) {
               this.C29_f402[var4].n(11);
            }
         }

         var1.D();
         var1.E();
         this.gameController.a(var1);
         C29_f412.removeElement(var1);
         C29_f413.removeElement(var1);
         var1.C41_f653 = 0;
         var1.e(false);
         var1.C41_f663 = 0;
         if (this.P()) {
            this.C29_f407 = this.C29_f404[this.C29_f410];
            this.changeState((byte)5);
         } else {
            ++this.C29_f410;
            this.L();
         }
      } else {
         if (var2) {
            if (var1.p(9)) {
               this.changeState((byte)2);
               return;
            }

            this.changeState((byte)20);
         }

      }
   }

   private boolean b(C41 var1) {
      if (this.C29_f436 >= this.C29_f438.length) {
         this.C29_f436 = 0;
         this.C29_f435 = this.C29_f434 = 0;
         this.C29_f438 = null;
         this.C29_f439 = null;
         this.C29_f440 = null;
         this.C29_f424 = null;
         if (this.C44_f698 == 12) {
            this.a(var1, true);
         } else if (this.C44_f698 == 13) {
            this.b(var1, true);
         }

         return true;
      } else {
         return false;
      }
   }

   private void c(C41 var1) {
      ++this.C29_f436;
      if (!this.b(var1)) {
         if (this.a(var1)) {
            return;
         }

         this.C29_f435 = this.C29_f434 = 0;
         this.J();
         if (this.C44_f698 == 12) {
            if (this.g(var1) == 2) {
               this.C29_f436 = 0;
               this.C29_f438 = null;
               this.C29_f439 = null;
               this.C29_f440 = null;
            }

            this.a(var1, false);
            return;
         }

         if (this.C44_f698 == 13) {
            if (this.g(var1) == 1) {
               this.C29_f436 = 0;
               this.C29_f438 = null;
               this.C29_f439 = null;
               this.C29_f440 = null;
            }

            this.b(var1, false);
         }
      }

   }

   private void K() {
      C41 var1;
      if ((var1 = (C41)this.C29_f409.elementAt(this.C29_f410)).C41_f640 != null) {
         label101: {
            if (var1.C41_f640.isActive()) {
               if (!var1.C41_f640.sprite.isAtLastFrame()) {
                  if (this.C29_f437[(this.C29_f435 << 2) + 3] != -1 && var1.C41_f640.isAtFrame((int)this.C29_f437[(this.C29_f435 << 2) + 3]) && this.C29_f434 < this.C29_f437.length / 4) {
                     this.J();
                  }
                  break label101;
               }

               var1.C41_f640.deactivateEffect();
               var1.C41_f640 = null;
               if (this.C29_f434 > this.C29_f437.length / 4 - 1) {
                  if (this.C29_f424 == null) {
                     this.C29_f417 = true;
                  }
                  break label101;
               }

               this.J();
               if (var1.C41_f640 == null) {
                  break label101;
               }
            }

            var1.C41_f640.activateEffect();
         }
      }

      if (this.C29_f424 != null && !this.C29_f424.isActive()) {
         this.C29_f424.activateEffect();
         var1.setVisible(false);
      }

      if (this.C29_f424 != null && this.C29_f424.isActive() && !this.C29_f424.updateEffect()) {
         this.C29_f424 = null;
         var1.setVisible(true);
         if (this.C29_f434 > this.C29_f437.length / 4 - 1) {
            this.C29_f417 = true;
         } else {
            this.J();
         }
      }

      if (this.C29_f417) {
         boolean var3 = false;
         int var4 = var1.O();
         int var5 = 0;
         byte var7;
         if (!this.C29_f466) {
            if (this.C29_f439[this.C29_f436 << 1] == 0) {
               var5 = var1.o(this.C29_f439[(this.C29_f436 << 1) + 1]);
               var1.d(this.C29_f439[(this.C29_f436 << 1) + 1], this.C29_f440[this.C29_f436]);
            } else if (this.C29_f439[this.C29_f436 << 1] == 1) {
               var1.q(this.C29_f439[(this.C29_f436 << 1) + 1]);
               var1.c(this.C29_f439[(this.C29_f436 << 1) + 1], this.C29_f440[this.C29_f436]);
            }

            var7 = 1;
            if (var1.secondaryStates[var7] < var4) {
               StringBuffer var10001 = new StringBuffer();
               var7 = 1;
               this.a(var10001.append(var1.secondaryStates[var7] - var4).toString(), (byte)0, 0, var1.s(), var1.worldX, var1.worldY, 9, 12);
            }

            if (var5 > 0) {
               this.a("+" + var5, (byte)0, 2, var1.s(), var1.worldX, var1.worldY, 9, 12);
            }

            this.C29_f466 = true;
            this.gameController.C9_f149 = 0;
            if (var1.s() == 0) {
               this.gameController.a(var1);
            } else {
               this.gameController.b(var1);
            }
         }

         boolean var8 = this.S();
         if (var1.s() == 0) {
            var7 = 1;
            if (var4 < var1.secondaryStates[var7]) {
               if (this.gameController.a(var1, true) && var8) {
                  this.C29_f466 = false;
                  var3 = true;
                  this.c(var1);
               }
            } else if (this.gameController.a(var1, false) && var8) {
               this.C29_f466 = false;
               var3 = true;
               this.c(var1);
            }
         } else {
            var7 = 1;
            if (var4 < var1.secondaryStates[var7]) {
               if (this.gameController.b(var1, true) && var8) {
                  this.C29_f466 = false;
                  var3 = true;
                  this.c(var1);
               }
            } else if (this.gameController.b(var1, false) && var8) {
               this.C29_f466 = false;
               var3 = true;
               this.c(var1);
            }
         }

         if (var3) {
            this.C29_f417 = false;
         }
      }

   }

   private void r(int var1) {
      this.C29_f403 = new byte[var1];
      this.C29_f404 = new byte[var1];

      for(var1 = 0; var1 < this.C29_f403.length; ++var1) {
         this.C29_f403[var1] = (byte)var1;
      }

   }

   private void e(byte var1) {
      short var2;
      short[] var4;
      switch(var1) {
      case 0:
         this.C29_f442.setAnimation((byte)var1, (byte)0, true);
         break;
      case 1:
         this.C29_f402[0].setVisible(false);
         var2 = ResourceManager.gameDatabase[0][((C41)this.C29_f408.followTarget).r()][17];
         var4 = new short[]{8, (short)((C41)this.C29_f408.followTarget).worldX, (short)((C41)this.C29_f408.followTarget).worldY, var2, 0, (short)((C41)this.C29_f408.followTarget).currentDirection, 0, 9, 1, 3, 0, 10, 0, 0, 7, 0, -10, 4, 0, -20};
         this.C29_f424 = new EffectEntity();
         this.C29_f424.initializeEffect(var4);
         this.C29_f424.setInteractable(true);
         this.C29_f424.activateEffect();
         this.C29_f442.setAnimation(var1, (byte)-2, true);
         break;
      case 2:
         this.C29_f442.setAnimation((byte)var1, (byte)0, true);
         break;
      case 3:
         this.C29_f442.setAnimation(var1, (byte)-2, true);
         break;
      case 4:
         var2 = ResourceManager.gameDatabase[0][((C41)this.C29_f408.followTarget).r()][17];
         var4 = new short[]{8, (short)((C41)this.C29_f408.followTarget).worldX, (short)((C41)this.C29_f408.followTarget).worldY, var2, 0, (short)((C41)this.C29_f408.followTarget).currentDirection, 0, 8, 1, 4, 1, 4, 0, -20, 6, 0, -12, 8, 0, -4, 10, 0, 0};
         this.C29_f424 = new EffectEntity();
         this.C29_f424.initializeEffect(var4);
         this.C29_f424.setInteractable(true);
         this.C29_f424.activateEffect();
         this.C29_f442.setAnimation((byte)1, (byte)-2, true);
      }

      this.C29_f396 = var1;
   }

   private void a(int var1, boolean var2) {
      this.C29_f445[this.C29_f402.length + 1].setVisible(var2);
      this.C29_f445[this.C29_f402.length + 1].setWorldPosition(C29_f446[this.C29_f397][(var1 << 2) + 2], C29_f446[this.C29_f397][(var1 << 2) + 3]);
   }

   private void b(int var1, boolean var2) {
      this.C29_f445[this.C29_f402.length].setVisible(var2);
      this.C29_f445[this.C29_f402.length].setWorldPosition(C29_f446[this.C29_f397][(var1 << 2) + 2], C29_f446[this.C29_f397][(var1 << 2) + 3]);
   }

   public final void changeState(byte var1) {
      this.C44_f699 = this.C44_f698;
      this.C44_f698 = var1;
      int var2;
      int var3;
      int var4;
      switch(var1) {
      case 0:
         for(this.C29_f410 = 0; ((C41)this.C29_f409.elementAt(this.C29_f410)).s() != 0; ++this.C29_f410) {
         }

         return;
      case 1:
         if (this.C29_f410 >= this.C29_f409.size()) {
            this.C29_f410 = 0;
         }

         for(this.C29_f408 = (C41)this.C29_f409.elementAt(this.C29_f410); this.C29_f408.C41_f667 || !this.C29_f408.T(); this.C29_f408 = (C41)this.C29_f409.elementAt(this.C29_f410)) {
            ++this.C29_f410;
            if (this.C29_f408.C41_f667) {
               this.C29_f408.C41_f667 = false;
            }

            if (this.C29_f410 >= this.C29_f409.size()) {
               this.C29_f411 = true;
               this.C29_f410 = 0;
               break;
            }
         }

         if (this.C29_f408.p(2) && this.C29_f408.s() == 0) {
            boolean var6 = false;

            for(var2 = 0; var2 < this.C29_f408.C41_f645.length; ++var2) {
               if (this.C29_f408.C41_f645[var2] != 0) {
                  var6 = true;
               }
            }

            if (!var6) {
               this.gameController.c("Không còn tinh lực, không cách nào chiến đấu");
               ++this.C29_f410;
               if (this.C29_f410 >= this.C29_f409.size()) {
                  this.C29_f411 = true;
                  this.C29_f410 = 0;
                  return;
               }
            }

            return;
         }
         break;
      case 2:
         return;
      case 3:
         this.gameController.e((C41)this.C29_f409.elementAt(this.C29_f410));
         return;
      case 4:
         this.gameController.am();
         return;
      case 5:
         this.gameController.C9_f126 = 0;
         this.gameController.Z();
         return;
      case 6:
         this.C29_f419 = 0;
         this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419), false);
         this.gameController.b(this.C29_f408, (C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
         this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
         this.a(Integer.parseInt((String)this.C29_f408.C41_f665.elementAt(this.C29_f419)), true);
         return;
      case 7:
         if (this.C29_f408.s() == 0) {
            this.gameController.a(this.C29_f408, false);
            this.gameController.a(this.C29_f408);
         } else {
            this.gameController.b(this.C29_f408, false);
            this.gameController.b(this.C29_f408);
         }

         if (((C41)this.C29_f408.followTarget).s() == 1) {
            this.gameController.b((C41)this.C29_f408.followTarget, false);
            this.gameController.b((C41)this.C29_f408.followTarget);
         } else {
            this.gameController.a((C41)this.C29_f408.followTarget, false);
            this.gameController.a((C41)this.C29_f408.followTarget);
         }

         this.C29_f416 = false;
         this.C29_f417 = false;
         this.I();
         if (this.O()) {
            this.C29_f432 = this.C29_f408.b((C41)this.C29_f408.followTarget);
         }

         switch(this.C29_f408.C41_f659) {
         case 52:
         case 58:
            if (GameUtils.getRandomInt(100) > 30) {
               this.C29_f433 = false;
               break;
            }
         default:
            this.C29_f433 = true;
         }

         if (this.C29_f431[this.C29_f426 * 7] == 0) {
            this.C29_f408.a((byte)1, true);
            return;
         }

         this.C29_f408.a((byte)0, true);
         return;
      case 8:
         C25.B().C25_f348.C7_f74 = 0;

         for(var2 = 0; var2 < C29_f412.size(); ++var2) {
            var4 = ((C41)C29_f412.elementAt(var2)).C41_f649 - ((C41)C29_f412.elementAt(var2)).getSecondaryState((byte)1);
            if (paymentActive) {
               var4 = var4 % 20 / 100;
            } else {
               var4 = var4 % 50 / 100;
            }

            if (var4 > 0) {
               ((C41)C29_f412.elementAt(var2)).l(var4);
               ((C41)C29_f412.elementAt(var2)).u(((C41)C29_f412.elementAt(var2)).getSecondaryState((byte)1));
            }
         }

         this.C29_f445[0].setWorldPosition(C29_f446[0][6], C29_f446[0][7]);
         this.gameController.b(C29_f446[0][4], C29_f446[0][5]);
         return;
      case 9:
         this.N();
         return;
      case 10:
         return;
      case 11:
         this.gameController.a((int)4, (byte)0);
         return;
      case 12:
      case 13:
         if (this.C29_f408.s() == 0) {
            this.gameController.a(this.C29_f408, false);
            this.gameController.a(this.C29_f408);
         } else {
            this.gameController.b(this.C29_f408, false);
            this.gameController.b(this.C29_f408);
         }

         C41 var5;
         if ((var5 = (C41)this.C29_f409.elementAt(this.C29_f410)).m(13) || var5.m(14)) {
            var5.D();
         }

         this.C29_f438 = new byte[var5.r(0) + var5.r(1)][];
         this.C29_f439 = new byte[this.C29_f438.length << 1];
         this.C29_f440 = new byte[this.C29_f438.length];
         var2 = 0;

         for(var3 = 0; var3 < 3; ++var3) {
            if (var5.C41_f643[0][var3] != -1) {
               this.C29_f440[var2] = (byte)var3;
               this.C29_f438[var2] = C29_f451[C29_f453[0][var5.C41_f643[0][var3]]];
               this.C29_f439[var2 << 1] = 0;
               this.C29_f439[(var2 << 1) + 1] = var5.C41_f643[0][var3];
               ++var2;
            }
         }

         for(var3 = 0; var3 < 3; ++var3) {
            if (var5.C41_f643[1][var3] != -1) {
               this.C29_f440[var2] = (byte)var3;
               this.C29_f438[var2] = C29_f452[C29_f453[1][var5.C41_f643[1][var3]]];
               this.C29_f439[var2 << 1] = 1;
               this.C29_f439[(var2 << 1) + 1] = var5.C41_f643[1][var3];
               ++var2;
            }
         }

         this.C29_f435 = this.C29_f434 = 0;
         if (!this.a(var5)) {
            this.J();
            return;
         }
         break;
      case 14:
         return;
      case 15:
         this.gameController.C9_f124 = 0;
         this.C29_f414 = true;
         this.C29_f423 = this.C29_f407;
         this.C29_f421[this.C29_f423] = 0;
         this.C29_f409.setElementAt(this.C29_f402[this.C29_f407], this.C29_f403[this.C29_f407]);
         this.C29_f402[this.C29_f407].C41_f667 = true;
         this.b(this.C29_f407, false);
         ++this.C29_f410;
         return;
      case 16:
         this.gameController.C9_f126 = 0;
         this.gameController.C9_f150 = false;
         this.gameController.Z();
         return;
      case 17:
         this.C29_f408.setFollowTarget((GameEntity)this.C29_f402[0]);
         this.C29_f442 = new GameObject();
         this.C29_f442.loadSpriteSet(269, false);
         this.C29_f442.setWorldPosition(this.C29_f408.getWorldX(), this.C29_f408.getWorldY());
         this.C29_f442.activate();
         this.e((byte)0);
         this.C29_f443 = false;
         var3 = this.m(C29_f444);
         if (GameUtils.getRandomInt(100) < var3) {
            this.C29_f443 = true;
         } else {
            this.C29_f443 = false;
         }

         if (actionType == 0 && currentAction == 5) {
            this.C29_f443 = false;
         }

         this.gameController.C9_f131 = 0;
         return;
      case 18:
         return;
      case 19:
      case 25:
      case 26:
      case 27:
      case 28:
      case 29:
      case 30:
      case 31:
      case 32:
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
      case 100:
      case 103:
      default:
         break;
      case 20:
         this.C29_f408 = (C41)this.C29_f409.elementAt(this.C29_f410);

         for(var4 = 0; var4 < this.C29_f402.length; ++var4) {
            if (this.C29_f402[var4].s() == 1 && this.C29_f402[var4].T()) {
               this.gameController.b(this.C29_f402[var4], false);
               this.gameController.b(this.C29_f402[var4]);
            }
         }

         this.b(this.C29_f404[this.C29_f410], true);
         this.gameController.c(this.C29_f408);
         if (this.C29_f402[0].T()) {
            this.gameController.b(this.C29_f408, this.C29_f402[0]);
            return;
         }

         this.gameController.b(this.C29_f408, this.C29_f402[1]);
         return;
      case 21:
         this.C29_f408.setFollowTarget((GameEntity)this.C29_f402[0]);
         this.gameController.ak();
         return;
      case 22:
         this.gameController.aq();
         return;
      case 23:
         this.gameController.as();
         return;
      case 24:
         this.gameController.aH();
         return;
      case 101:
         this.gameController.aJ();
         break;
      case 102:
         this.gameController.aL();
         return;
      case 104:
         this.gameController.aK();
         return;
      }

   }

   public final void update() {
      if (this.isActive) {
         this.updateInputState();
         byte var1;
         boolean var2;
         boolean var4;
         int var5;
         int var6;
         switch(this.C44_f698) {
         case 0:
            ++this.C29_f422;
            this.C29_f420[this.C29_f423] = this.C29_f421[this.C29_f423];
            this.C29_f402[this.C29_f423].setWorldPosition(C29_f447[this.C29_f399][this.C29_f423][this.C29_f421[this.C29_f423] << 2], C29_f447[this.C29_f399][this.C29_f423][(this.C29_f421[this.C29_f423] << 2) + 1]);
            this.C29_f445[this.C29_f423].setWorldPosition(C29_f447[this.C29_f399][this.C29_f423][(this.C29_f421[this.C29_f423] << 2) + 2], C29_f447[this.C29_f399][this.C29_f423][(this.C29_f421[this.C29_f423] << 2) + 3]);
            if (this.C29_f397 == 1 && this.C29_f421[this.C29_f423] > C29_f447[this.C29_f399][this.C29_f423].length / 4 - 3 && this.C29_f423 % 2 == 0 && this.C29_f421.length > this.C29_f423 + 1) {
               ++this.C29_f421[this.C29_f423 + 1];
               this.C29_f420[this.C29_f423 + 1] = this.C29_f421[this.C29_f423 + 1];
               this.C29_f402[this.C29_f423 + 1].setWorldPosition(C29_f447[this.C29_f399][this.C29_f423 + 1][this.C29_f421[this.C29_f423 + 1] << 2], C29_f447[this.C29_f399][this.C29_f423 + 1][(this.C29_f421[this.C29_f423 + 1] << 2) + 1]);
               this.C29_f445[this.C29_f423 + 1].setWorldPosition(C29_f447[this.C29_f399][this.C29_f423 + 1][(this.C29_f421[this.C29_f423 + 1] << 2) + 2], C29_f447[this.C29_f399][this.C29_f423 + 1][(this.C29_f421[this.C29_f423 + 1] << 2) + 3]);
            }

            if (this.C29_f397 == 0) {
               this.gameController.a(this.C29_f402[1], this.C29_f402[0], this.C29_f402[this.C29_f423], this.C29_f421[this.C29_f423] + 1, C29_f447[this.C29_f399][this.C29_f423].length / 4);
            }

            if (this.C29_f422 > 1) {
               ++this.C29_f421[this.C29_f423];
               this.C29_f422 = 0;
            }

            if (this.C29_f421[this.C29_f423] > C29_f447[this.C29_f399][this.C29_f423].length / 4 - 1) {
               this.C29_f421[this.C29_f423] = (byte)(C29_f447[this.C29_f399][this.C29_f423].length / 4 - 1);
               this.C29_f420[this.C29_f423] = this.C29_f421[this.C29_f423];
               ++this.C29_f423;
               if (this.C29_f423 > this.C29_f402.length - 1) {
                  this.C29_f423 = this.C29_f402.length - 1;
                  this.changeState((byte)20);
               }
            }
            break;
         case 1:
            if (this.gameController.aB()) {
               if (this.C29_f408.s() == 1 && this.C29_f398 == 0 && (this.C29_f408.r() == 33 || this.C29_f408.r() == 59) && this.C29_f408.getSecondaryState((byte)1) < this.C29_f408.getPrimaryState((byte)1)) {
                  this.changeState((byte)10);
                  return;
               }

               if (!this.C29_f411) {
                  if (this.C29_f408.s() == 1) {
                     if (d(this.C29_f408)) {
                        this.changeState((byte)12);
                     } else {
                        this.changeState((byte)2);
                     }
                  } else {
                     this.changeState((byte)2);
                  }
               } else {
                  if (this.C29_f414) {
                     this.Q();
                     this.C29_f410 = 0;
                     this.C29_f414 = false;
                  }

                  for(this.C29_f410 = 0; ((C41)this.C29_f409.elementAt(this.C29_f410)).s() != 0 || ((C41)this.C29_f409.elementAt(this.C29_f410)).s() == 0 && !((C41)this.C29_f409.elementAt(this.C29_f410)).T(); ++this.C29_f410) {
                  }

                  if (d((C41)this.C29_f409.elementAt(this.C29_f410))) {
                     this.changeState((byte)13);
                  } else {
                     this.changeState((byte)20);
                  }

                  this.C29_f411 = false;
               }
            }

            this.gameController.g();
            this.C29_f445[this.C29_f402.length].updateAnimation();
            break;
         case 2:
            C41 var12;
            if (this.C29_f408.s() == 1) {
               var1 = (byte)e(this.C29_f408);
               if (this.C29_f408.p(9)) {
                  this.f(this.C29_f408);
               } else {
                  var2 = true;
                  var12 = this.C29_f408;
                  if (C41.a((byte)var1, (byte)9) == 0 && this.C29_f408.p(8) && GameUtils.getRandomInt(100) > ResourceManager.getDatabaseValue((byte)1, (short)var1, (byte)8)) {
                     this.f(this.C29_f408);
                     var2 = false;
                  }

                  if (var2) {
                     this.d(var1);
                  }
               }

               var5 = GameUtils.getRandomInt(this.C29_f408.C41_f664.size());
               C41 var7 = (C41)this.C29_f408.C41_f664.elementAt(var5);
               this.C29_f408.C41_f666 = Byte.parseByte((String)this.C29_f408.C41_f665.elementAt(var5));
               this.C29_f408.a(var1, var7);
               this.gameController.b(this.C29_f408, var7);
               this.changeState((byte)7);
            } else if (this.C29_f408.p(9)) {
               this.f(this.C29_f408);
               var6 = GameUtils.getRandomInt(this.C29_f408.C41_f664.size());
               C41 var9 = (C41)this.C29_f408.C41_f664.elementAt(var6);
               this.C29_f408.C41_f666 = Byte.parseByte((String)this.C29_f408.C41_f665.elementAt(var6));
               byte var8 = (byte)e(this.C29_f408);
               this.C29_f408.a(var8, var9);
               this.gameController.b(this.C29_f408, var9);
               this.changeState((byte)7);
            } else {
               var4 = true;
               var12 = this.C29_f408;
               if (C41.a((byte)this.C29_f408.I(), (byte)9) == 0 && this.C29_f408.p(8) && GameUtils.getRandomInt(100) > ResourceManager.getDatabaseValue((byte)1, (short)this.C29_f408.I(), (byte)8)) {
                  this.f(this.C29_f408);
                  var4 = false;
               }

               if (var4) {
                  if ((C41)this.C29_f408.followTarget != null && !((C41)this.C29_f408.followTarget).T()) {
                     for(var5 = 0; var5 < this.C29_f408.C41_f664.size(); ++var5) {
                        if (((C41)this.C29_f408.C41_f664.elementAt(var5)).T()) {
                           this.C29_f408.C41_f666 = Byte.parseByte((String)this.C29_f408.C41_f665.elementAt(var5));
                           this.C29_f408.a(this.C29_f408.I(), (C41)this.C29_f408.C41_f664.elementAt(var5));
                        }
                     }
                  } else {
                     this.C29_f408.a(this.C29_f408.I(), (C41)this.C29_f408.followTarget);
                  }
               }

               this.gameController.b(this.C29_f408, (C41)this.C29_f408.followTarget);
               this.changeState((byte)7);
            }
            break;
         case 3:
            this.gameController.f((C41)this.C29_f409.elementAt(this.C29_f410));
            break;
         case 4:
            this.gameController.an();
            break;
         case 5:
            this.gameController.aa();
            break;
         case 6:
            if (this.isKeyPressed(4100)) {
               if (this.C29_f397 == 1) {
                  --this.C29_f419;
                  if (this.C29_f419 <= 0) {
                     this.C29_f419 = 0;
                  }

                  this.a(Integer.parseInt((String)this.C29_f408.C41_f665.elementAt(this.C29_f419)), true);
                  this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419), false);
                  this.gameController.b(this.C29_f408, (C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
                  this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
               }
            } else if (this.isKeyPressed(8448)) {
               if (this.C29_f397 == 1) {
                  ++this.C29_f419;
                  if (this.C29_f419 >= this.C29_f408.C41_f664.size() - 1) {
                     this.C29_f419 = (byte)(this.C29_f408.C41_f664.size() - 1);
                  }

                  this.a(Integer.parseInt((String)this.C29_f408.C41_f665.elementAt(this.C29_f419)), true);
                  this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419), false);
                  this.gameController.b(this.C29_f408, (C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
                  this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
               }
            } else if (this.isKeyPressed(16400)) {
               if (this.C29_f397 == 1) {
                  --this.C29_f419;
                  if (this.C29_f419 <= 0) {
                     this.C29_f419 = 0;
                  }

                  this.a(Integer.parseInt((String)this.C29_f408.C41_f665.elementAt(this.C29_f419)), true);
                  this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419), false);
                  this.gameController.b(this.C29_f408, (C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
                  this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
               }
            } else if (this.isKeyPressed(32832)) {
               if (this.C29_f397 == 1) {
                  ++this.C29_f419;
                  if (this.C29_f419 >= this.C29_f408.C41_f664.size() - 1) {
                     this.C29_f419 = (byte)(this.C29_f408.C41_f664.size() - 1);
                  }

                  this.a(Integer.parseInt((String)this.C29_f408.C41_f665.elementAt(this.C29_f419)), true);
                  this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419), false);
                  this.gameController.b(this.C29_f408, (C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
                  this.gameController.b((C41)this.C29_f408.C41_f664.elementAt(this.C29_f419));
               }
            } else if (this.isKeyPressed(196640)) {
               this.G();
            } else if (this.isKeyPressed(786432)) {
               this.a(Integer.parseInt((String)this.C29_f408.C41_f665.elementAt(this.C29_f419)), false);
               this.changeState((byte)3);
            }

            this.C29_f445[this.C29_f402.length].updateAnimation();
            this.C29_f445[this.C29_f402.length + 1].updateAnimation();
            break;
         case 7:
            if (this.C29_f408.C41_f640 != null) {
               if (this.C29_f408.q() == 0) {
                  if (this.C29_f408.C41_f640.isActive()) {
                     if (this.C29_f408.C41_f640.isAnimationComplete()) {
                        this.C29_f408.C41_f640.deactivateEffect();
                        this.C29_f408.C41_f640 = null;
                        if (this.C29_f425 <= this.C29_f431.length / 7 - 1 && !this.T()) {
                           this.I();
                           if (this.C29_f408.C41_f640 != null) {
                              this.C29_f408.C41_f640.activateEffect();
                           }

                           if (this.C29_f424 != null) {
                              this.C29_f429 = true;
                           }
                        } else {
                           this.C29_f417 = true;
                           this.C29_f418 = true;
                        }
                     } else if (this.C29_f431[this.C29_f426 * 7 + 4] != -1 && this.C29_f408.C41_f640.isAtFrame((int)this.C29_f431[this.C29_f426 * 7 + 4])) {
                        this.C29_f408.C41_f640.deactivateEffect();
                        if (this.C29_f425 < this.C29_f431.length / 7 - 1 || this.T()) {
                           this.I();
                           if (this.C29_f424 != null) {
                              this.C29_f429 = true;
                           }
                        }
                     } else if (this.C29_f431[this.C29_f426 * 7 + 5] != -1 && this.C29_f408.C41_f640.isAtFrame((int)this.C29_f431[this.C29_f426 * 7 + 5])) {
                        this.C29_f408.a(this.C29_f431[this.C29_f426 * 7 + 6], true);
                     }
                  } else {
                     this.C29_f430 = false;
                     this.C29_f408.C41_f640.activateEffect();
                  }
               } else if (this.C29_f408.q() == 1 && this.C29_f408.isAnimationComplete()) {
                  this.C29_f408.a((byte)0, true);
               }
            } else if (((C41)this.C29_f408.followTarget).C41_f640 != null) {
               if (((C41)this.C29_f408.followTarget).C41_f640.isActive()) {
                  if (((C41)this.C29_f408.followTarget).C41_f640.isAnimationComplete()) {
                     ((C41)this.C29_f408.followTarget).C41_f640.deactivateEffect();
                     if (this.C29_f425 <= this.C29_f431.length / 7 - 1 && !this.T()) {
                        if (this.C29_f431[this.C29_f425 * 7] == 1) {
                           ((C41)this.C29_f408.followTarget).a((byte)2, true);
                        } else {
                           this.C29_f427 = 0;
                           ((C41)this.C29_f408.followTarget).C41_f640 = null;
                           this.I();
                           if (((C41)this.C29_f408.followTarget).C41_f640 != null) {
                              ((C41)this.C29_f408.followTarget).C41_f640.activateEffect();
                           }

                           if (this.C29_f424 != null) {
                              this.C29_f429 = true;
                           }
                        }
                     } else {
                        ((C41)this.C29_f408.followTarget).a((byte)2, true);
                     }
                  } else {
                     if (this.C29_f431[this.C29_f426 * 7 + 5] != -1) {
                        this.C29_f427 = this.C29_f426;
                     }

                     if (this.C29_f431[this.C29_f427 * 7 + 5] != -1 && ((C41)this.C29_f408.followTarget).C41_f640.isAtFrame((int)this.C29_f431[this.C29_f427 * 7 + 5])) {
                        ((C41)this.C29_f408.followTarget).a(this.C29_f431[this.C29_f427 * 7 + 6], true);
                        this.C29_f427 = 0;
                     }

                     if (this.C29_f431[this.C29_f426 * 7 + 4] != -1 && ((C41)this.C29_f408.followTarget).C41_f640.isAtFrame((int)this.C29_f431[this.C29_f426 * 7 + 4])) {
                        this.I();
                        if (this.C29_f424 != null) {
                           this.C29_f429 = true;
                        }
                     }
                  }
               } else if ((this.C29_f408.q() != 1 || !this.C29_f408.isAnimationComplete()) && !this.C29_f430) {
                  if (((C41)this.C29_f408.followTarget).q() == 2 && ((C41)this.C29_f408.followTarget).isAnimationComplete()) {
                     this.C29_f416 = true;
                     ((C41)this.C29_f408.followTarget).C41_f640 = null;
                     if (this.C29_f425 <= this.C29_f431.length / 7 - 1 && !this.T()) {
                        this.I();
                        if (this.C29_f424 != null) {
                           this.C29_f429 = true;
                        }
                     } else {
                        this.C29_f417 = true;
                     }
                  }
               } else {
                  this.C29_f408.a((byte)0, true);
                  ((C41)this.C29_f408.followTarget).C41_f640.activateEffect();
                  this.C29_f430 = false;
               }
            }

            if (this.C29_f424 != null && !this.C29_f424.isActive() && (this.C29_f408.q() == 1 && this.C29_f408.isAnimationComplete() || this.C29_f429 || this.C29_f408.q() == 0)) {
               if (this.C29_f426 == 0) {
                  this.C29_f430 = true;
               }

               this.C29_f408.a((byte)0, true);
               this.C29_f424.activateEffect();
               this.C29_f428 = this.C29_f426;
               if (this.C29_f431[this.C29_f426 * 7] == 0) {
                  ((C41)this.C29_f408.followTarget).setVisible(false);
               } else {
                  this.C29_f408.setVisible(false);
               }
            }

            if (this.C29_f424 != null && this.C29_f424.isActive() && !this.C29_f424.updateEffect()) {
               this.C29_f424 = null;
               this.C29_f429 = false;
               if (this.C29_f431[this.C29_f428 * 7] == 0) {
                  ((C41)this.C29_f408.followTarget).setVisible(true);
               } else {
                  this.C29_f408.setVisible(true);
               }

               if (((C41)this.C29_f408.followTarget).C41_f640 == null && this.C29_f408.C41_f640 == null) {
                  if (this.C29_f425 <= this.C29_f431.length / 7 - 1 && !this.T()) {
                     if (this.C29_f431[this.C29_f425 * 7] == 1) {
                        this.C29_f416 = true;
                     }

                     this.I();
                     if (this.C29_f424 != null) {
                        this.C29_f429 = true;
                     }
                  } else {
                     if (this.C29_f431[this.C29_f426 * 7] == 0) {
                        this.C29_f416 = true;
                     }

                     this.C29_f418 = true;
                     this.C29_f417 = true;
                  }
               }

               this.C29_f428 = 0;
            }

            if (this.C29_f416) {
               this.C29_f418 = false;
               if (this.U() && (((C41)this.C29_f408.followTarget).T() || this.c((C41)this.C29_f408.followTarget, true))) {
                  this.C29_f418 = true;
                  this.C29_f416 = false;
               }
            }

            if (this.C29_f418 && this.C29_f417 && this.M()) {
               this.C29_f418 = false;
               this.C29_f417 = false;
            }
            break;
         case 8:
            this.gameController.ap();
         case 9:
         case 14:
         case 18:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
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
         case 100:
         case 103:
         default:
            break;
         case 10:
            if (!this.gameController.j() && this.c(this.C29_f408, false)) {
               this.gameController.c(getLocalizedText(ResourceManager.gameDatabase[0][this.C29_f408.r()][0]) + "Chạy trốn");
            }

            if (this.gameController.g()) {
               GameScreenManager.getInstance().changeState((byte)10);
            }
            break;
         case 11:
            this.gameController.a((byte)4, (byte)0);
            break;
         case 12:
         case 13:
            this.K();
            break;
         case 15:
            if (this.C29_f397 == 0) {
               C41 var10001 = this.C29_f402[1];
               C41 var10002 = this.C29_f402[0];
               C41[] var10003 = this.C29_f402;
               int var13 = this.C29_f423;
               this.gameController.a(var10001, var10002, this.C29_f421[this.C29_f423] + 1, C29_f447[this.C29_f399][this.C29_f423].length / 4);
            }

            if (this.C29_f422 > 0) {
               ++this.C29_f421[this.C29_f423];
               this.C29_f422 = 0;
            } else {
               ++this.C29_f422;
               if (this.C29_f421[this.C29_f423] > C29_f447[this.C29_f399][this.C29_f423].length / 4 - 3) {
                  this.C29_f420[this.C29_f423] = this.C29_f421[this.C29_f423];
               }

               this.C29_f402[this.C29_f423].setWorldPosition(C29_f447[this.C29_f399][this.C29_f423][this.C29_f421[this.C29_f423] << 2], C29_f447[this.C29_f399][this.C29_f423][(this.C29_f421[this.C29_f423] << 2) + 1]);
            }

            if (this.C29_f421[this.C29_f423] > C29_f447[this.C29_f399][this.C29_f423].length / 4 - 1) {
               this.C29_f420[this.C29_f423] = this.C29_f421[this.C29_f423] = (byte)(C29_f447[this.C29_f399][this.C29_f423].length / 4 - 1);
               var4 = true;
               if (this.C29_f415) {
                  while(this.C29_f410 < this.C29_f409.size() && (!((C41)this.C29_f409.elementAt(this.C29_f410)).T() || ((C41)this.C29_f409.elementAt(this.C29_f410)).s() != 0)) {
                     ++this.C29_f410;
                  }

                  if (this.C29_f410 >= this.C29_f409.size()) {
                     this.C29_f410 = 0;
                     this.changeState((byte)1);
                  } else if (d((C41)this.C29_f409.elementAt(this.C29_f410))) {
                     this.changeState((byte)13);
                  } else {
                     var4 = false;
                     this.changeState((byte)20);
                  }

                  this.C29_f415 = false;
               } else {
                  if (this.C29_f397 == 0) {
                     this.C29_f410 = (byte)this.C29_f409.size();
                  }

                  int var3;
                  if (this.C29_f410 >= this.C29_f409.size()) {
                     for(var5 = 0; var5 < this.C29_f402.length; ++var5) {
                        this.C29_f402[var5].C41_f667 = false;
                     }

                     if (this.C29_f414) {
                        this.Q();
                        this.C29_f414 = false;
                     }

                     if (this.C44_f699 != 12 && this.C44_f699 != 13) {
                        var2 = false;
                        if (this.C29_f408.m(12) && this.C29_f408.C41_f668[12] == 2) {
                           --this.C29_f408.C41_f668[12];
                           if (!((C41)this.C29_f408.followTarget).T()) {
                              var2 = true;
                              --this.C29_f408.C41_f668[12];
                           } else {
                              --this.C29_f410;
                              this.changeState((byte)2);
                           }
                        } else {
                           var3 = GameUtils.getRandomInt(100);
                           if ((this.C29_f408.C41_f659 == 63 || this.C29_f408.C41_f659 == 69) && var3 <= ResourceManager.gameDatabase[1][this.C29_f408.C41_f659][8] && ((C41)this.C29_f408.followTarget).T()) {
                              --this.C29_f410;
                              this.changeState((byte)2);
                           } else {
                              var2 = true;
                           }
                        }

                        if (var2) {
                           for(this.C29_f410 = 0; ((C41)this.C29_f409.elementAt(this.C29_f410)).s() != 0 || ((C41)this.C29_f409.elementAt(this.C29_f410)).s() == 0 && !((C41)this.C29_f409.elementAt(this.C29_f410)).T(); ++this.C29_f410) {
                           }

                           if (d((C41)this.C29_f409.elementAt(this.C29_f410))) {
                              this.changeState((byte)13);
                           } else {
                              var4 = false;
                              this.changeState((byte)20);
                           }
                        }
                     } else {
                        for(this.C29_f410 = 0; ((C41)this.C29_f409.elementAt(this.C29_f410)).s() != 0 || ((C41)this.C29_f409.elementAt(this.C29_f410)).s() == 0 && !((C41)this.C29_f409.elementAt(this.C29_f410)).T(); ++this.C29_f410) {
                        }

                        if (d((C41)this.C29_f409.elementAt(this.C29_f410))) {
                           this.changeState((byte)13);
                        } else {
                           var4 = false;
                           this.changeState((byte)20);
                        }
                     }
                  } else if (this.C44_f699 != 13 && this.C44_f699 != 12) {
                     label739: {
                        var2 = false;
                        if (this.C29_f408.m(12) && this.C29_f408.C41_f668[12] == 2) {
                           --this.C29_f408.C41_f668[12];
                           if (((C41)this.C29_f408.followTarget).T()) {
                              --this.C29_f410;
                              this.changeState((byte)2);
                              break label739;
                           }

                           --this.C29_f408.C41_f668[12];
                        } else {
                           var3 = GameUtils.getRandomInt(100);
                           if ((this.C29_f408.C41_f659 == 63 || this.C29_f408.C41_f659 == 69) && var3 <= ResourceManager.gameDatabase[1][this.C29_f408.C41_f659][8] && ((C41)this.C29_f408.followTarget).T()) {
                              --this.C29_f410;
                              this.changeState((byte)2);
                              break label739;
                           }
                        }

                        var2 = true;
                     }

                     if (var2) {
                        this.changeState((byte)1);
                     }
                  } else {
                     this.changeState((byte)1);
                  }
               }

               if (var4) {
                  this.D();
                  if (this.C29_f402[this.C29_f407].s() == 0) {
                     this.gameController.a(this.C29_f402[this.C29_f407], false);
                     this.gameController.a(this.C29_f402[this.C29_f407]);
                  } else {
                     this.gameController.b(this.C29_f402[this.C29_f407], false);
                     this.gameController.b(this.C29_f402[this.C29_f407]);
                  }
               }
            }
            break;
         case 16:
            this.gameController.ao();
            break;
         case 17:
            if (this.gameController.C9_f131 == 0) {
               if (this.C29_f396 == 0 && this.C29_f442.isAnimationComplete()) {
                  this.e((byte)1);
               } else if (this.C29_f396 == 1 && this.C29_f442.isAnimationComplete()) {
                  if (!this.C29_f424.updateEffect()) {
                     this.e((byte)2);
                  }
               } else if (this.C29_f396 == 2 && this.C29_f442.isAnimationComplete()) {
                  if (this.C29_f443) {
                     this.e((byte)3);
                  } else {
                     this.e((byte)4);
                  }
               } else if (this.C29_f396 == 3 && this.C29_f442.isAnimationComplete()) {
                  if ((var1 = C29_f395.z()) == 0) {
                     this.gameController.C9_f131 = 1;
                     this.gameController.b("Bắt thành công #2" + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][((C41)this.C29_f408.followTarget).r()][0]));
                     C29_f395.a(((C41)this.C29_f408.followTarget).Q());
                  } else if (var1 == 1) {
                     this.gameController.C9_f131 = 2;
                     this.gameController.b("Bắt thành công #2" + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][((C41)this.C29_f408.followTarget).r()][0]));
                     C29_f395.b(((C41)this.C29_f408.followTarget).Q());
                  } else {
                     this.gameController.C9_f131 = 1;
                     this.gameController.b("Không còn không gian, sủng vật này đã phóng sinh");
                  }
               } else if (this.C29_f396 == 4 && this.C29_f442.isAnimationComplete() && !this.C29_f424.updateEffect()) {
                  this.C29_f424 = null;
                  this.C29_f402[0].setVisible(true);
                  this.C29_f442.deactivate();
                  this.C29_f408.C41_f667 = true;
                  if (this.C29_f443) {
                     this.gameController.b("Ngân hàng và Ba lô đều đã đầy");
                     this.gameController.C9_f131 = 3;
                  } else {
                     ++this.C29_f410;
                     this.changeState((byte)1);
                  }
               }

               this.C29_f442.updateAnimation();
            } else {
               if (this.gameController.aA()) {
                  if (this.gameController.C9_f131 == 3) {
                     this.gameController.C9_f131 = 0;
                     this.C29_f408.C41_f667 = true;
                     ++this.C29_f410;
                     this.changeState((byte)1);
                  } else if (this.gameController.C9_f131 == 2) {
                     this.gameController.b("Sủng vật ba lô đã đủ, đã để vào ngân hàng");
                     this.gameController.C9_f131 = 4;
                  } else if (this.gameController.C9_f131 == 4 || this.gameController.C9_f131 == 1) {
                     this.gameController.C9_f131 = 0;
                     C25.B().C25_f348.C7_f74 = -1;
                     this.q();
                     GameScreenManager.getInstance().changeState((byte)10);
                  }
               }

               this.gameController.f();
            }
            break;
         case 19:
            if (this.isKeyPressed(786432)) {
               this.changeState((byte)18);
            }
            break;
         case 20:
            this.C29_f445[this.C29_f402.length].updateAnimation();
            this.gameController.d(this.C29_f408);
            break;
         case 21:
            this.gameController.al();
            break;
         case 22:
            this.gameController.ar();
            break;
         case 23:
            this.gameController.at();
            break;
         case 24:
            this.gameController.aI();
            break;
         case 101:
         case 102:
         case 104:
            this.gameController.aO();
         }

         for(var6 = 0; var6 < this.C29_f402.length; ++var6) {
            this.C29_f402[var6].p();
         }

         this.dialogManager.closeCurrentDialog();
      }
   }

   private void b(Graphics var1) {
      var1.setColor(16777215);

      for(int var2 = 0; var2 < this.C29_f402.length; ++var2) {
         this.C29_f402[var2].a(var1);
      }

   }

   private void a(Graphics var1, boolean var2, int var3, int var4) {
      for(var3 = 0; var3 < this.C29_f402.length; ++var3) {
         this.C29_f445[var3].render(var1, 0, 0);
      }

      if (var2) {
         this.C29_f445[this.C29_f402.length].render(var1, 0, 0);
         if (this.C29_f397 == 1) {
            this.C29_f445[this.C29_f402.length + 1].render(var1, 0, 0);
         }
      }

   }

   private void a(String var1, byte var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.C29_f460 = var1;
      this.C29_f454 = var5;
      this.C29_f455 = var6;
      this.C29_f458 = var3;
      this.C29_f459 = var4;
      this.C29_f456 = var7;
      this.C29_f457 = var8;
      this.C29_f464.addElement(this.C29_f460);
      this.C29_f463.addElement(new int[]{var2, this.C29_f458, this.C29_f459, -1, this.C29_f456, this.C29_f457});
   }

   private void c(Graphics var1) {
      for(int var4 = 0; var4 < this.C29_f463.size(); ++var4) {
         int[] var2 = (int[])this.C29_f463.elementAt(var4);
         String var3 = (String)this.C29_f464.elementAt(var4);
         switch(var2[0]) {
         case 0:
            if (var2[2] == 0) {
               a(var1, C29_f461[var2[1]], var3, this.C29_f454 + C29_f462[var2[0]][var2[3] << 1] + 30, this.C29_f455 + C29_f462[var2[0]][(var2[3] << 1) + 1] - 30, var2[4], var2[5], 1);
            } else {
               a(var1, C29_f461[var2[1]], var3, this.C29_f454 - C29_f462[var2[0]][var2[3] << 1] - 30, this.C29_f455 + C29_f462[var2[0]][(var2[3] << 1) + 1] - 30, var2[4], var2[5], 1);
            }
            break;
         case 1:
            if (var2[2] == 0) {
               GameUtils.a(var1, var3, 16704699, this.C29_f454 - 10, this.C29_f455 + C29_f462[var2[0]][(var2[3] << 1) + 1] - 30, 17, 17, this.dialogManager.textRenderer, 2);
            } else {
               GameUtils.a(var1, var3, 16704699, this.C29_f454 + 10, this.C29_f455 + C29_f462[var2[0]][(var2[3] << 1) + 1] - 30, 17, 17, this.dialogManager.textRenderer, 2);
            }
         }
      }

   }

   public final void renderPauseScreen(Graphics var1) {
      if (this.isActive) {
         if (this.C29_f400 != null) {
            var1.drawImage(this.C29_f400, 0, 0, 20);
         } else {
            var1.setColor(0);
            var1.fillRect(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
         }

         switch(this.C44_f698) {
         case 0:
            this.a(var1, false, 0, 0);
            this.b(var1);
            break;
         case 1:
         case 10:
            this.a(var1, false, 0, 0);
            this.b(var1);
            break;
         case 2:
            this.a(var1, false, 0, 0);
            this.b(var1);
            break;
         case 3:
            var1.setColor(16777215);
            break;
         case 4:
            var1.setColor(16777215);
            break;
         case 5:
            var1.setColor(16777215);
            var1.drawString(getLocalizedText(ResourceManager.gameDatabase[0][this.n(this.C29_f405[0]).r()][0]), getScreenWidth() >> 1, 200, 17);
            break;
         case 6:
            if (this.C29_f397 == 1) {
               this.a(var1, true, 0, 0);
            } else {
               this.a(var1, false, 0, 0);
            }

            this.b(var1);
            break;
         case 7:
            this.a(var1, false, 0, 0);
            if (this.C29_f424 != null) {
               this.C29_f424.render(var1);
            }

            this.b(var1);
            this.c(var1);
            break;
         case 8:
            this.C29_f445[0].render(var1, 0, 0);
            if (this.gameController.C9_f138 < C29_f413.size()) {
               ((C41)C29_f413.elementAt(this.gameController.C9_f138)).a(var1);
            }
         case 9:
         case 11:
         case 14:
         case 16:
         case 18:
         case 19:
         case 21:
         default:
            break;
         case 12:
         case 13:
            this.a(var1, false, 0, 0);
            if (this.C29_f424 != null) {
               this.C29_f424.render(var1);
            }

            this.b(var1);
            this.c(var1);
            break;
         case 15:
            this.a(var1, false, 0, 0);
            this.b(var1);
            break;
         case 17:
            this.a(var1, false, 0, 0);
            this.b(var1);
            if (this.C29_f424 != null && this.C29_f424.isShakeEffect((byte)8)) {
               this.C29_f424.render(var1);
               this.C29_f442.render(var1, 0, 0);
            } else {
               this.C29_f442.render(var1, 0, 0);
            }
            break;
         case 20:
            this.a(var1, true, 0, 0);
            this.b(var1);
         }

         this.dialogManager.render(var1);
      }
   }

   public final void F() {
      ++this.C29_f410;
      if (this.C29_f410 < this.C29_f409.size()) {
         while(((C41)this.C29_f409.elementAt(this.C29_f410)).s() != 0 || ((C41)this.C29_f409.elementAt(this.C29_f410)).s() == 0 && !((C41)this.C29_f409.elementAt(this.C29_f410)).T()) {
            ++this.C29_f410;
            if (this.C29_f410 >= this.C29_f409.size()) {
               break;
            }
         }
      }

      if (this.C29_f410 >= this.C29_f409.size()) {
         this.changeState((byte)1);
      } else if (d((C41)this.C29_f409.elementAt(this.C29_f410))) {
         this.changeState((byte)13);
      } else {
         this.changeState((byte)20);
      }
   }

   private void L() {
      if (this.C29_f410 < this.C29_f409.size()) {
         this.changeState((byte)1);
      } else {
         if (this.C29_f414) {
            this.Q();
            this.C29_f414 = false;
         }

         for(int var1 = 0; var1 < this.C29_f409.size(); ++var1) {
            ((C41)this.C29_f409.elementAt(var1)).C41_f667 = false;
         }

         for(this.C29_f410 = 0; ((C41)this.C29_f409.elementAt(this.C29_f410)).s() != 0 || ((C41)this.C29_f409.elementAt(this.C29_f410)).s() == 0 && !((C41)this.C29_f409.elementAt(this.C29_f410)).T(); ++this.C29_f410) {
         }

         if (d((C41)this.C29_f409.elementAt(this.C29_f410))) {
            this.changeState((byte)13);
         } else {
            this.changeState((byte)20);
         }
      }
   }

   private boolean M() {
      if (!this.V()) {
         return false;
      } else {
         this.C29_f425 = 0;
         this.C29_f426 = 0;

         int var2;
         for(var2 = 0; var2 < this.C29_f405.length && !this.n(this.C29_f405[var2]).T(); ++var2) {
         }

         if ((((C41)this.C29_f408.followTarget).s() != 1 || ((C41)this.C29_f408.followTarget).T()) && (this.C29_f408.s() != 1 || this.C29_f408.T())) {
            if (((C41)this.C29_f408.followTarget).s() == 0 && !((C41)this.C29_f408.followTarget).T() || this.C29_f408.s() == 0 && !this.C29_f408.T()) {
               ((C41)this.C29_f408.followTarget).D();
               ((C41)this.C29_f408.followTarget).E();
               this.gameController.a((C41)this.C29_f408.followTarget);
               C29_f412.removeElement((C41)this.C29_f408.followTarget);
               C29_f413.removeElement((C41)this.C29_f408.followTarget);
               ((C41)this.C29_f408.followTarget).C41_f653 = 0;
               ((C41)this.C29_f408.followTarget).e(false);
               ((C41)this.C29_f408.followTarget).C41_f663 = 0;
            }
         } else {
            ((C41)this.C29_f408.followTarget).D();
            ((C41)this.C29_f408.followTarget).E();
            this.gameController.b((C41)this.C29_f408.followTarget);
            this.h((C41)this.C29_f408.followTarget);
            ++this.C29_f406[1];
         }

         byte var1;
         if (var2 >= this.C29_f405.length) {
            var1 = 2;
         } else if (this.C29_f406[1] >= this.C29_f401.length) {
            var1 = 1;
         } else {
            var1 = 0;
         }

         switch(var1) {
         case 0:
            boolean var4 = false;
            int var3;
            if (((C41)this.C29_f408.followTarget).T() && this.C29_f408.T()) {
               var4 = true;
            } else {
               for(var3 = 0; var3 < this.C29_f402.length; ++var3) {
                  if (this.C29_f402[var3].m(11) && this.C29_f402[this.C29_f402[var3].C41_f641[11][1]].equals((C41)this.C29_f408.followTarget)) {
                     this.C29_f402[var3].n(11);
                  }
               }

               if ((((C41)this.C29_f408.followTarget).s() != 1 || ((C41)this.C29_f408.followTarget).T()) && (this.C29_f408.s() != 1 || this.C29_f408.T())) {
                  if (!this.P()) {
                     var4 = true;
                  } else {
                     if (this.C29_f408.s() == 0 && !this.C29_f408.T()) {
                        this.C29_f407 = this.C29_f404[this.C29_f410];
                     } else {
                        this.C29_f407 = this.C29_f408.C41_f666;
                     }

                     this.changeState((byte)5);
                  }
               } else if (this.C29_f406[0] >= this.C29_f401.length) {
                  var4 = true;
               } else {
                  if (this.C29_f408.s() == 1 && !this.C29_f408.T()) {
                     this.C29_f407 = this.C29_f404[this.C29_f410];
                  } else {
                     this.C29_f407 = this.C29_f408.C41_f666;
                  }

                  this.q(this.C29_f407);
                  this.changeState((byte)15);
               }
            }

            if (var4) {
               if (this.C29_f408.m(12) && this.C29_f408.C41_f668[12] == 2) {
                  --this.C29_f408.C41_f668[12];
                  if (!((C41)this.C29_f408.followTarget).T()) {
                     --this.C29_f408.C41_f668[12];
                     ++this.C29_f410;
                     this.L();
                  } else {
                     this.changeState((byte)2);
                  }
               } else {
                  var3 = GameUtils.getRandomInt(100);
                  if ((this.C29_f408.C41_f659 == 63 || this.C29_f408.C41_f659 == 69) && var3 <= ResourceManager.gameDatabase[1][this.C29_f408.C41_f659][8]) {
                     if (!((C41)this.C29_f408.followTarget).T()) {
                        --this.C29_f408.C41_f668[12];
                        ++this.C29_f410;
                        this.L();
                     } else {
                        this.changeState((byte)2);
                     }
                  } else {
                     ++this.C29_f410;
                     this.L();
                  }
               }
            }
            break;
         case 1:
            W();
            this.changeState((byte)8);
            break;
         case 2:
            this.changeState((byte)9);
         }

         if (C29_f395.b((byte)5, (byte)0) == 2 && C29_f395.b((byte)5, (byte)1) == 1) {
            for(int var5 = 0; var5 < this.C29_f402.length; ++var5) {
               if (this.C29_f402[var2].s() == 0 && this.C29_f402[var2].T()) {
                  this.C29_f402[var2].z();
               }
            }
         }

         return true;
      }
   }

   private void N() {
      if (C25.B().C25_f348.C7_f70) {
         this.changeState((byte)24);
      } else {
         for(int var1 = 0; var1 < C29_f395.C53_f778; ++var1) {
            C29_f395.C53_f777[var1].l(1);
            C29_f395.C53_f777[var1].u(1);
            C29_f395.C53_f777[var1].activate();
         }

         GameScreenManager.getInstance().changeState((byte)10);
      }

      C25.B().C25_f348.C7_f74 = 1;
      C25.B().C25_f348.C7_f70 = true;
   }

   private static boolean d(C41 var0) {
      return var0.r(0) > 0 || var0.r(1) > 0;
   }

   private boolean O() {
      return this.C29_f408.s() != ((C41)this.C29_f408.followTarget).s() || this.C29_f408.p(8);
   }

   private static int e(C41 var0) {
      byte var1 = var0.C41_f646[0];
      int[] var2 = new int[]{50, 20, 15, 10, 5, 5, 5, 5, 5, 5};
      int var3 = GameUtils.getRandomInt(100);

      for(int var4 = 0; var4 < var0.C41_f646.length; ++var4) {
         if (var0.C41_f646[var4] != -1 && var0.C41_f645[var4] > 0 && var3 < var2[var4]) {
            var1 = var0.C41_f646[var4];
         }
      }

      return var1;
   }

   public final void d(byte var1) {
      this.C29_f408.C41_f664.removeAllElements();
      this.C29_f408.C41_f665.removeAllElements();
      C41 var10000 = this.C29_f408;
      int var2;
      switch(C41.a((byte)var1, (byte)9)) {
      case 0:
         for(var2 = 0; var2 < this.C29_f402.length; ++var2) {
            if (this.C29_f402[var2].s() != this.C29_f408.s() && this.C29_f402[var2].T()) {
               this.C29_f408.C41_f664.addElement(this.C29_f402[var2]);
               this.C29_f408.C41_f665.addElement("" + var2);
            }
         }
      default:
         return;
      case 1:
         for(var2 = 0; var2 < this.C29_f402.length; ++var2) {
            if (this.C29_f402[var2].s() == this.C29_f408.s() && this.C29_f402[var2].T()) {
               this.C29_f408.C41_f664.addElement(this.C29_f402[var2]);
               this.C29_f408.C41_f665.addElement("" + var2);
            }
         }

      }
   }

   private void f(C41 var1) {
      var1.C41_f664.removeAllElements();
      var1.C41_f665.removeAllElements();

      for(int var2 = 0; var2 < this.C29_f402.length; ++var2) {
         if (this.C29_f402[var2].T() && !this.C29_f402[var2].equals(var1)) {
            var1.C41_f664.addElement(this.C29_f402[var2]);
            var1.C41_f665.addElement("" + var2);
         }
      }

   }

   public final void G() {
      C41 var10000 = this.C29_f408;
      C41 var2 = (C41)this.C29_f408.C41_f664.elementAt(this.C29_f419);
      var10000.followTarget = var2;
      this.C29_f408.C41_f666 = Byte.parseByte((String)this.C29_f408.C41_f665.elementAt(this.C29_f419));
      this.C29_f408.h(((C41)this.C29_f409.elementAt(this.C29_f410)).C41_f646[this.gameController.C9_f129]);
      this.a(Integer.parseInt((String)this.C29_f408.C41_f665.elementAt(this.C29_f419)), false);
      this.F();
   }

   private int g(C41 var1) {
      if (!var1.T()) {
         if (var1.s() == 0) {
            if (this.P()) {
               return 1;
            }
         } else if (this.C29_f406[0] < this.C29_f401.length) {
            return 2;
         }
      }

      return 0;
   }

   private boolean P() {
      int var1 = 0;

      for(int var2 = 0; var2 < C29_f395.C53_f778; ++var2) {
         if (this.n(var2).T() && !this.n(var2).L()) {
            ++var1;
         }
      }

      if (var1 > 0) {
         return true;
      } else {
         return false;
      }
   }

   private void f(int var1, int var2) {
      byte var3 = this.C29_f405[var2];
      this.C29_f405[var2] = this.C29_f405[var1];
      this.C29_f405[var1] = var3;
   }

   public final int l(int var1) {
      if (!this.n(this.C29_f405[var1]).T()) {
         return 0;
      } else if (this.n(this.C29_f405[var1]).L()) {
         return 1;
      } else {
         byte var2 = this.C29_f405[var1];
         --var1;

         while(var1 >= 0) {
            this.C29_f405[var1 + 1] = this.C29_f405[var1];
            --var1;
         }

         this.C29_f405[0] = var2;

         for(var1 = 0; var1 < C29_f412.size() && !C29_f412.elementAt(var1).equals(this.n(this.C29_f405[0])); ++var1) {
         }

         if (var1 >= C29_f412.size()) {
            C29_f412.addElement(this.n(this.C29_f405[0]));
         }

         this.n(this.C29_f405[0]).C41_f667 = true;
         this.n(this.C29_f405[0]).e(true);
         this.C29_f408.e(false);
         this.C29_f408.C41_f663 = 0;

         for(var1 = 0; var1 < this.C29_f402.length; ++var1) {
            if (this.C29_f402[var1].m(11) && this.C29_f402[this.C29_f402[var1].C41_f641[11][1]].equals(this.C29_f408)) {
               this.C29_f402[var1].n(11);
            }
         }

         return -1;
      }
   }

   private void Q() {
      this.C29_f409.removeAllElements();
      int var2 = -1;

      C41 var10000;
      C41 var10001;
      byte var1;
      int var3;
      byte var6;
      short var8;
      for(var3 = 0; var3 < this.C29_f403.length - 1; ++var3) {
         for(int var4 = var3 + 1; var4 < this.C29_f403.length; ++var4) {
            var10000 = this.C29_f402[var3];
            var6 = 4;
            var8 = var10000.primaryStates[var6];
            var10001 = this.C29_f402[var4];
            var6 = 4;
            if (var8 < var10001.primaryStates[var6]) {
               var1 = this.C29_f403[var3];
               this.C29_f403[var3] = this.C29_f403[var4];
               this.C29_f403[var4] = var1;
            }
         }
      }

      for(var3 = 0; var3 < this.C29_f403.length; ++var3) {
         if (this.C29_f402[var3].f((byte)7)) {
            var2 = var3;
            this.C29_f403[var3] = 0;
            break;
         }
      }

      if (var2 != -1) {
         var3 = 1;
         int[] var7 = new int[this.C29_f403.length - 1];

         int var5;
         for(var5 = 0; var5 < this.C29_f403.length; ++var5) {
            if (var5 != var2) {
               var7[var3 - 1] = var5;
               this.C29_f403[var5] = (byte)var3;
               ++var3;
            }
         }

         for(var5 = 0; var5 < var7.length - 1; ++var5) {
            for(var2 = var5 + 1; var2 < var7.length; ++var2) {
               var10000 = this.C29_f402[var7[var5]];
               var6 = 4;
               var8 = var10000.primaryStates[var6];
               var10001 = this.C29_f402[var7[var2]];
               var6 = 4;
               if (var8 < var10001.primaryStates[var6]) {
                  var1 = this.C29_f403[var7[var5]];
                  this.C29_f403[var7[var5]] = this.C29_f403[var7[var2]];
                  this.C29_f403[var7[var2]] = var1;
               }
            }
         }
      }

      for(var3 = 0; var3 < this.C29_f403.length; ++var3) {
         this.C29_f404[this.C29_f403[var3]] = (byte)var3;
      }

      for(var3 = 0; var3 < this.C29_f404.length; ++var3) {
         this.C29_f409.addElement(this.C29_f402[this.C29_f404[var3]]);
      }

   }

   private void R() {
      C41 var10000;
      byte var2;
      if (this.C29_f408.f((byte)10)) {
         var10000 = (C41)this.C29_f408.followTarget;
         var2 = 1;
         if (var10000.secondaryStates[var2] <= ResourceManager.gameDatabase[3][10][5]) {
            var10000 = (C41)this.C29_f408.followTarget;
            short var3 = ResourceManager.gameDatabase[3][10][5];
            var2 = 1;
            var10000.secondaryStates[var2] = var3;
         }
      }

      var10000 = (C41)this.C29_f408.followTarget;
      var2 = 1;
      if (var10000.secondaryStates[var2] <= 0) {
         ((C41)this.C29_f408.followTarget).a((byte)3, true);
      } else {
         ((C41)this.C29_f408.followTarget).a((byte)0, true);
      }
   }

   private boolean c(C41 var1, boolean var2) {
      if (this.C29_f398 == 0 && var2) {
         if (var1.C41_f669 != null && !var1.C41_f669.isActive()) {
            return true;
         }
      } else {
         int var3 = var1.worldX;
         if (var1.s() == 0) {
            this.C29_f465 -= 10;
         } else {
            this.C29_f465 += 10;
         }

         var3 += this.C29_f465;
         if (Math.abs(this.C29_f465) >= 100) {
            var1.deactivate();
            this.C29_f465 = 0;
            return true;
         }

         var1.setWorldPosition(var3, var1.worldY);
      }

      return false;
   }

   private boolean S() {
      int var1;
      for(var1 = 0; var1 < this.C29_f463.size(); ++var1) {
         int[] var2;
         int var10002 = (var2 = (int[])this.C29_f463.elementAt(var1))[3]++;
         if (var2[3] >= C29_f462[var2[0]].length / 2) {
            this.C29_f463.removeElementAt(var1);
            this.C29_f464.removeElementAt(var1);
            --var1;
         }
      }

      if (var1 <= 0) {
         return true;
      } else {
         return false;
      }
   }

   private boolean T() {
      if (this.C29_f432 != null) {
         if (this.C29_f432[2] != -1) {
            return false;
         } else {
            return this.C29_f431[this.C29_f431.length - 1] == this.C29_f425;
         }
      } else {
         return !this.C29_f433;
      }
   }

   private boolean U() {
      if (ResourceManager.gameDatabase[1][this.C29_f408.C41_f659][3] == 0) {
         this.gameController.C9_f149 = 0;
         if (((C41)this.C29_f408.followTarget).s() == 0) {
            this.gameController.a((C41)this.C29_f408.followTarget);
         } else {
            this.gameController.b((C41)this.C29_f408.followTarget);
         }

         ((C41)this.C29_f408.followTarget).a((byte)0, true);
         return true;
      } else if (((C41)this.C29_f408.followTarget).q() == 3) {
         return true;
      } else {
         if (!this.C29_f466) {
            int var1 = this.C29_f408.t();
            if (this.C29_f408.s() == 0 && C29_f395.b((byte)4, (byte)0) == 2 && C29_f395.b((byte)4, (byte)1) == 1) {
               var1 += ResourceManager.gameDatabase[2][4][6];
            }

            if (this.C29_f408.m(4)) {
               var1 = ((C41)this.C29_f408.followTarget).t() - (var1 - this.C29_f408.C41_f642[4][1]) << 1;
            } else {
               var1 = ((C41)this.C29_f408.followTarget).t() - var1 << 1;
            }

            if (this.C29_f408.f((byte)9)) {
               var1 = 0;
            }

            if (var1 <= 0) {
               var1 = 0;
            } else if (var1 >= 20) {
               var1 = 20;
            }

            if (GameUtils.getRandomInt(100) >= var1) {
               ((C41)this.C29_f408.followTarget).k(this.C29_f432[0]);
               if (this.C29_f408.f((byte)10)) {
                  C41 var10000 = (C41)this.C29_f408.followTarget;
                  byte var2 = 1;
                  if (var10000.secondaryStates[var2] <= ResourceManager.gameDatabase[3][10][5]) {
                     var10000 = (C41)this.C29_f408.followTarget;
                     short var3 = ResourceManager.gameDatabase[3][10][5];
                     var2 = 1;
                     var10000.secondaryStates[var2] = var3;
                  }
               }

               if (this.C29_f432[1] == 1) {
                  this.a("-" + this.C29_f432[0], (byte)0, 1, ((C41)this.C29_f408.followTarget).s(), ((C41)this.C29_f408.followTarget).worldX, ((C41)this.C29_f408.followTarget).worldY, 15, 19);
               } else {
                  this.a("-" + this.C29_f432[0], (byte)0, 0, ((C41)this.C29_f408.followTarget).s(), ((C41)this.C29_f408.followTarget).worldX, ((C41)this.C29_f408.followTarget).worldY, 9, 12);
               }

               if (this.C29_f432[2] != -1) {
                  this.a(GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[7][this.C29_f432[2]][0]), (byte)1, 0, ((C41)this.C29_f408.followTarget).s(), ((C41)this.C29_f408.followTarget).worldX, ((C41)this.C29_f408.followTarget).worldY, 9, 12);
               }
            } else {
               this.a("Né tránh", (byte)1, 0, ((C41)this.C29_f408.followTarget).s(), ((C41)this.C29_f408.followTarget).worldX, ((C41)this.C29_f408.followTarget).worldY, 9, 12);
            }

            this.C29_f466 = true;
            this.gameController.C9_f149 = 0;
            if (((C41)this.C29_f408.followTarget).s() == 0) {
               this.gameController.a((C41)this.C29_f408.followTarget);
            } else {
               this.gameController.b((C41)this.C29_f408.followTarget);
            }
         }

         boolean var4 = this.S();
         if (((C41)this.C29_f408.followTarget).s() == 0) {
            if (this.gameController.a((C41)this.C29_f408.followTarget, false) && var4) {
               this.R();
               this.C29_f466 = false;
               return true;
            }
         } else if (this.gameController.b((C41)this.C29_f408.followTarget, false) && var4) {
            this.R();
            this.C29_f466 = false;
            return true;
         }

         return false;
      }
   }

   private boolean a(C41 var1, boolean var2, int var3) {
      byte var4;
      if (var1.s() == 0) {
         var4 = 1;
         if (var3 < var1.secondaryStates[var4]) {
            if (this.gameController.a(var1, true) && var2) {
               this.C29_f466 = false;
               return true;
            }
         } else if (this.gameController.a(var1, false) && var2) {
            this.C29_f466 = false;
            return true;
         }
      } else {
         var4 = 1;
         if (var3 < var1.secondaryStates[var4]) {
            if (this.gameController.b(var1, true) && var2) {
               this.C29_f466 = false;
               return true;
            }
         } else if (this.gameController.b(var1, false) && var2) {
            this.C29_f466 = false;
            return true;
         }
      }

      return false;
   }

   private boolean V() {
      if (!this.C29_f466) {
         int var1 = 0;
         if (ResourceManager.gameDatabase[1][this.C29_f408.C41_f659][9] == 0) {
            this.C29_f467 = this.C29_f408.O();
         } else {
            this.C29_f467 = ((C41)this.C29_f408.followTarget).O();
         }

         byte var2;
         byte var5;
         switch(var2 = this.C29_f408.C41_f659) {
         case 11:
         case 17:
            var1 = (short)(this.C29_f408.C() * ResourceManager.gameDatabase[1][var2][8] / 100);
            var5 = 1;
            this.C29_f408.u(this.C29_f408.secondaryStates[var5]);
            if (var1 <= 0) {
               var1 = 1;
            }

            this.C29_f408.l(var1);
            break;
         case 21:
         case 27:
         case 42:
         case 48:
         case 62:
         case 68:
            this.C29_f408.a((byte)((byte) ResourceManager.gameDatabase[1][var2][7]), -1, var2);
            break;
         case 52:
         case 58:
            if (this.C29_f433) {
               var1 = this.C29_f432[0] * ResourceManager.gameDatabase[1][var2][8] / 100;
               var5 = 1;
               this.C29_f408.u(this.C29_f408.secondaryStates[var5]);
               this.C29_f408.l(this.C29_f432[0] * ResourceManager.gameDatabase[1][var2][8] / 100);
            }
         case 64:
            this.C29_f408.setAnimationAndDirection((byte) ResourceManager.gameDatabase[1][var2][7], this.C29_f408.C41_f666, var2);
            break;
         default:
            if (ResourceManager.gameDatabase[1][var2][6] == 1) {
               var1 = ((C41)this.C29_f408.followTarget).a((byte)((byte) ResourceManager.gameDatabase[1][var2][7]), -1, var2);
            }
         }

         byte var3 = (byte) ResourceManager.gameDatabase[1][var2][6];
         short var7;
         if (ResourceManager.gameDatabase[1][this.C29_f408.C41_f659][9] == 0) {
            if (this.C29_f408.f((byte)8) && GameUtils.getRandomInt(100) <= ResourceManager.gameDatabase[3][8][5]) {
               var5 = 1;
               this.C29_f408.u(this.C29_f408.secondaryStates[var5]);
               this.C29_f408.l((short)(this.C29_f432[0] * ResourceManager.gameDatabase[3][8][6] / 100));
            }

            if (((C41)this.C29_f408.followTarget).m(2)) {
               int var4 = this.C29_f432[0] * ((C41)this.C29_f408.followTarget).C41_f641[2][2] / 100;
               this.C29_f408.k(var4);
            }

            if (((C41)this.C29_f408.followTarget).m(5) && (var7 = this.C29_f408.C41_f668[5]) > 0) {
               this.C29_f408.k(var7);
               this.C29_f408.C41_f668[5] = 0;
            }

            var5 = 1;
            if (this.C29_f408.secondaryStates[var5] < this.C29_f467) {
               StringBuffer var10001 = new StringBuffer();
               var5 = 1;
               this.a(var10001.append(this.C29_f408.secondaryStates[var5] - this.C29_f467).toString(), (byte)0, 0, this.C29_f408.s(), this.C29_f408.worldX, this.C29_f408.worldY, 9, 12);
            } else if (var1 > 0) {
               this.a("+" + var1, (byte)0, 2, this.C29_f408.s(), this.C29_f408.worldX, this.C29_f408.worldY, 9, 12);
            }
         } else if (var1 > 0) {
            this.a("+" + var1, (byte)0, 2, ((C41)this.C29_f408.followTarget).s(), ((C41)this.C29_f408.followTarget).worldX, ((C41)this.C29_f408.followTarget).worldY, 9, 12);
         }

         if (var3 == 1) {
            var7 = ResourceManager.gameDatabase[1][var2][7];
            switch(var2) {
            case 21:
            case 27:
            case 42:
            case 48:
            case 62:
            case 64:
            case 68:
               this.a(GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[6][var7][0]), (byte)1, 2, this.C29_f408.s(), this.C29_f408.worldX, this.C29_f408.worldY, 9, 12);
               break;
            default:
               this.a(GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[6][var7][0]), (byte)1, 2, ((C41)this.C29_f408.followTarget).s(), ((C41)this.C29_f408.followTarget).worldX, ((C41)this.C29_f408.followTarget).worldY, 9, 12);
            }
         }

         if (ResourceManager.gameDatabase[1][this.C29_f408.C41_f659][9] == 0) {
            this.gameController.C9_f149 = 0;
            if (this.C29_f408.s() == 0) {
               this.gameController.a(this.C29_f408);
            } else {
               this.gameController.b(this.C29_f408);
            }
         } else {
            this.gameController.C9_f149 = 0;
            if (((C41)this.C29_f408.followTarget).s() == 0) {
               this.gameController.a((C41)this.C29_f408.followTarget);
            } else {
               this.gameController.b((C41)this.C29_f408.followTarget);
            }
         }

         this.C29_f466 = true;
      }

      boolean var6 = this.S();
      return ((C41)this.C29_f408.followTarget).s() == this.C29_f408.s() && !this.C29_f408.p(9) ? this.a((C41)this.C29_f408.followTarget, var6, this.C29_f467) : this.a(this.C29_f408, var6, this.C29_f467);
   }

   private static void W() {
      int var0;
      for(var0 = 0; var0 < C29_f413.size(); ++var0) {
         if (((C41)C29_f413.elementAt(var0)).T()) {
            ((C41)C29_f413.elementAt(var0)).g(((C41)C29_f413.elementAt(var0)).C41_f653);
            ((C41)C29_f413.elementAt(var0)).C41_f653 = 0;
            ((C41)C29_f413.elementAt(var0)).e(false);
         } else {
            C29_f413.removeElementAt(var0);
            --var0;
         }
      }

      if (C29_f395.b((byte)0, (byte)0) == 2 && C29_f395.b((byte)0, (byte)1) == 1) {
         for(var0 = 0; var0 < C29_f395.C53_f778; ++var0) {
            if (C29_f395.C53_f777[var0].T()) {
               C41 var10000 = C29_f395.C53_f777[var0];
               C41 var10001 = C29_f395.C53_f777[var0];
               byte var2 = 1;
               var10000.u(var10001.secondaryStates[var2] + ResourceManager.gameDatabase[0][C29_f395.C53_f777[var0].r()][5] * ResourceManager.gameDatabase[2][0][6] / 100);
               C29_f395.C53_f777[var0].l(ResourceManager.gameDatabase[0][C29_f395.C53_f777[var0].r()][5] * ResourceManager.gameDatabase[2][0][6] / 100);
            }
         }
      }

   }

   private void h(C41 var1) {
      int var2;
      int var10000 = ((var2 = var1.t()) << 1) * var2 + 50;
      byte var3 = 0;
      int var9 = var10000 * this.C29_f468[var1.primaryStates[var3] - 1] / 10 + 400;
      int var10;
      int[] var4 = new int[var10 = C29_f412.size()];
      C41 var5 = null;
      byte var6 = 0;

      int var7;
      int var8;
      for(var8 = 0; var8 < var10; ++var8) {
         if ((var5 = (C41)C29_f412.elementAt(var8)).t() - var2 >= 6) {
            var6 = this.C29_f470[6];
         } else if (var5.t() - var2 > 0) {
            var6 = this.C29_f470[var5.t() - var2];
         } else if (var5.t() == var2) {
            var6 = this.C29_f470[1];
         } else if (var5.t() < var2) {
            var6 = this.C29_f470[0];
         }

         var7 = var9 / var10 * this.C29_f469[var10 - 1] * var6 / 1000;
         if (var5.f((byte)5)) {
            var7 = var7 * (ResourceManager.gameDatabase[3][5][5] + 100) / 100;
         }

         var5.C41_f653 += var7;
         var4[var8] = var7;
         if (!C29_f413.contains(var5)) {
            C29_f413.addElement(var5);
         }
      }

      for(var8 = 0; var8 < C29_f395.C53_f778; ++var8) {
         if (this.n(var8).T() && !C29_f412.contains(this.n(var8))) {
            C41 var12;
            if (C29_f395.b((byte)7, (byte)0) == 2) {
               if (var5.t() - var2 >= 6) {
                  var6 = this.C29_f470[6];
               } else if (var5.t() - var2 > 0) {
                  var6 = this.C29_f470[var5.t() - var2];
               } else if (var5.t() == var2) {
                  var6 = this.C29_f470[1];
               } else if (var5.t() < var2) {
                  var6 = this.C29_f470[0];
               }

               var7 = var9 / var10 * this.C29_f469[var10 - 1] * var6 / 3000;
               var12 = this.n(var8);
               var12.C41_f653 += var7;
               this.n(var8).activate();
               if (!C29_f413.contains(this.n(var8))) {
                  C29_f413.addElement(this.n(var8));
               }
            } else if (this.n(var8).f((byte)6)) {
               if (var5.t() - var2 >= 6) {
                  var6 = this.C29_f470[6];
               } else if (var5.t() - var2 > 0) {
                  var6 = this.C29_f470[var5.t() - var2];
               } else if (var5.t() == var2) {
                  var6 = this.C29_f470[1];
               } else if (var5.t() < var2) {
                  var6 = this.C29_f470[0];
               }

               var7 = var9 / var10 * this.C29_f469[var10 - 1] * var6 / 1000;
               var12 = this.n(var8);
               var12.C41_f653 += var7;
               this.n(var8).activate();
               if (!C29_f413.contains(this.n(var8))) {
                  C29_f413.addElement(this.n(var8));
               }
            }
         }
      }

      for(var9 = 0; var9 < C29_f412.size(); ++var9) {
         C41 var11;
         if (!(var11 = (C41)C29_f412.elementAt(var9)).L()) {
            C29_f412.removeElement(var11);
         }
      }

      if (C29_f395.l(0)) {
         if (C29_f395.C53_f786 == 0) {
            if (var5.t() >= 30 && ++C25.C25_f314 >= 10) {
               C25.C25_f314 = 10;
               return;
            }
         } else if (var5.t() >= 40 && ++C25.C25_f314 >= 30) {
            C25.C25_f314 = 30;
         }
      }

   }

   public final int m(int var1) {
      if (var1 == 0) {
         return 100;
      } else {
         byte var2 = 0;
         if (((C41)this.C29_f408.followTarget).m(1)) {
            var2 = 1;
         }

         if (((C41)this.C29_f408.followTarget).m(2)) {
            var2 = 2;
         }

         if (((C41)this.C29_f408.followTarget).m(10)) {
            var2 = 3;
         }

         if (this.C29_f408.f((byte)11)) {
            var2 = 4;
         }

         byte var3 = 1;
         C41 var10000 = (C41)this.C29_f408.followTarget;
         byte var6 = 1;
         short var4 = var10000.secondaryStates[var6];
         var10000 = (C41)this.C29_f408.followTarget;
         var6 = 1;
         short var5 = var10000.primaryStates[var6];
         if (var4 <= var5 * 15 / 100) {
            var3 = 85;
         } else if (var4 <= var5 * 50 / 100) {
            var3 = 45;
         } else if (var4 <= var5) {
            var3 = 20;
         }

         int var10 = var3 * ResourceManager.gameDatabase[4][var1][6] / 100;
         int[] var8 = new int[]{110, 100, 95, 80, 70};
         C41 var10002 = (C41)this.C29_f408.followTarget;
         var6 = 0;
         var10 = var10 * var8[var10002.primaryStates[var6] - 1] / 100;
         var8 = new int[]{10, 11, 12, 12, 12};
         var10 = var10 * var8[var2] / 10;
         if (this.C29_f408.f((byte)11)) {
            var10 = var10 * (100 + ResourceManager.gameDatabase[3][11][5]) / 100;
         }

         int[] var7 = new int[]{1000, 500, 1, 1000};
         var10 = var10 * var7[ResourceManager.gameDatabase[0][((C41)this.C29_f408.followTarget).r()][22]] / 1000;
         if (((C41)this.C29_f408.followTarget).t() >= 20) {
            byte[] var9 = new byte[]{0, 15, 35, 65};
            if (var10 >= var9[var1]) {
               var10 = var9[var1];
            }
         }

         if (var10 >= 100) {
            var10 = 100;
         } else if (var10 <= 0) {
            var10 = 1;
         }

         return var10;
      }
   }

   public final C41 n(int var1) {
      return var1 > this.C29_f405.length - 1 ? null : C29_f395.C53_f777[var1];
   }

   public final C41 o(int var1) {
      return var1 > this.C29_f405.length - 1 ? null : C29_f395.C53_f777[this.C29_f405[var1]];
   }

   public final void a(int[][] var1) {
      this.C29_f401 = var1;
   }

   public final int H() {
      return this.C29_f401.length;
   }

   public final int p(int var1) {
      return this.C29_f401[0][0];
   }

   public final void q() {
      C41 var10000;
      int var1;
      byte var3;
      switch(actionType) {
      case 0:
         if (currentAction == 0) {
            if (this.C29_f408 != null) {
               var10000 = this.C29_f402[0];
               var3 = 1;
               var1 = var10000.primaryStates[var3] * 50 / 100;
               var10000 = this.C29_f402[0];
               var3 = 1;
               if (var10000.secondaryStates[var3] <= var1) {
                  b(0, 1);
                  ++currentAction;
                  this.gameController.c("Di Lặc thỏ thỏ đã bị thương, nhanh sử dụng #2 phong ấn cầu #1 tiến hành bắt được a");
               }

               return;
            }
         } else if (currentAction == 1) {
            if (this.gameController.aB()) {
               ++currentAction;
               this.gameController.C9_f124 = 1;
               b(2, 1);
               b(1, 1);
               this.gameController.aj();
               this.gameController.c("Hãy nhấn #2nút 5");
               return;
            }
         } else {
            if (currentAction == 3) {
               ++currentAction;
               b(2, 0);
               b(1, 1);
               this.gameController.c("Hãy lựa chọn phong ấn cầu");
               return;
            }

            if (currentAction == 5) {
               ++currentAction;
               this.gameController.c("Đáng tiếc đã bắt trượt, thử dùng loại xịn #2Tất trúng cầu#1 xem sao!");
               return;
            }

            if (currentAction == 6) {
               if (this.gameController.aB()) {
                  b(1, 0);
                  this.gameController.C9_f124 = 1;
                  this.gameController.aj();
                  this.gameController.C9_f125 = 0;
                  ++currentAction;
                  this.changeState((byte)21);
                  return;
               }
            } else if (currentAction == 8) {
               b(1, -1);
               b(0, 0);
               actionType = -1;
               currentAction = 0;
               return;
            }
         }
         break;
      case 2:
         if (currentAction == 0) {
            b(0, 0);
            ++currentAction;
            this.gameController.c("Tranh thủ thời gian lựa chọn #2Tất trúng cầu#1 để bắt sủng vật");
            return;
         }

         if (currentAction == 2) {
            if (this.C29_f408 != null) {
               var10000 = this.C29_f402[0];
               var3 = 1;
               var1 = var10000.primaryStates[var3] * 50 / 100 + 2;
               var10000 = this.C29_f402[0];
               var3 = 1;
               if (var10000.secondaryStates[var3] <= var1) {
                  --currentAction;
                  this.gameController.c("Lựa chọn #2Tất trúng cầu#1 để bắt sủng vật");
               }
            }

            if (C29_f395.a((byte)1, (int)29) == 2) {
               actionType = -1;
               currentAction = 0;
               return;
            }
         }
         break;
      case 5:
         if (currentAction == 0) {
            this.gameController.C9_f124 = 1;
            ++currentAction;
            this.gameController.aj();
            this.gameController.c("Tranh thủ thời gian lựa chọn #2Tất trúng cầu#1 để bắt sủng vật");
         }
      }

   }

   public final void r() {
      switch(actionType) {
      case 0:
         if (currentAction == 2 || currentAction == 4 || currentAction == 7) {
            ++currentAction;
            return;
         }
         break;
      case 5:
         if (currentAction == 1) {
            ++currentAction;
            b(1, 0);
            b(0, 1);
            return;
         }

         if (currentAction == 2) {
            b(0, 0);
            actionType = -1;
            currentAction = 0;
         }
      }

   }
}
