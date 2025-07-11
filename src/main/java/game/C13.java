package game;

import a.GameUtils;
import a.GameEngineBase;
import c.DialogManager;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class C13 extends GameEngineBase {
   private static C13 C13_f199;
   private static int[] C13_f200 = null;
   private static int C13_f201;
   private byte C13_f202 = 0;
   private byte C13_f203 = 0;
   private Image C13_f204;
   int C13_f205 = 0;
   private byte C13_f206 = 10;
   private int[][] C13_f207;
   private int[] C13_f208;
   private byte C13_f209;
   private byte C13_f210;
   private boolean C13_f211;
   private int C13_f212;
   private int[] C13_f213;
   private int[] C13_f214;

   public C13() {
      this.C13_f207 = new int[this.C13_f206][5];
      this.C13_f208 = new int[]{28, 3, 21, 22, 50, 5, 17, 17};
      this.C13_f209 = 30;
      this.C13_f210 = 30;
      this.C13_f211 = false;
      this.C13_f212 = 0;
      this.C13_f213 = new int[this.C13_f206];
      this.C13_f214 = new int[]{3958719, 3958719, 3958719, 7248110, 7248110, 9943031};
   }

   public static C13 B() {
      if (C13_f199 == null) {
         C13_f199 = new C13();
      }

      return C13_f199;
   }

   public final boolean initializeGame() {
      this.gameController = C9.a();
      this.dialogManager = DialogManager.getInstance();
      this.gameController.a((GameEngineBase)this);
      C13_f201 = 0;
      if (inputEnabled) {
         C13_f200 = new int[]{504, 503, 505, 506, 507, 508};
      } else {
         C13_f200 = new int[]{503, 505, 506, 507, 508};
      }

      if (this.C13_f204 == null) {
         this.C13_f204 = GameUtils.loadImage("/data/img/", "img_833");
      }

      this.C();
      this.changeState((byte)0);
      this.C13_f205 = 0;
      if (C25.B().C25_f342 != null) {
         this.C13_f205 = GameScreenManager.getInstance().difficultyLevel;
         GameScreenManager.getInstance().setDifficultyLevel(0);
         C25.B().C25_f342.a((byte)1);
         C25.B().C25_f342.a();
         C25.B().C25_f342 = null;
      }

      return true;
   }

   private void C() {
      for(int var1 = 0; var1 < this.C13_f206; ++var1) {
         this.C13_f207[var1][0] = -GameUtils.getRandomInt(this.C13_f209);
         this.C13_f207[var1][1] = getScreenHeight() + GameUtils.getRandomInt(this.C13_f210);
         this.C13_f207[var1][2] = GameUtils.getRandomInt(2);
         this.C13_f207[var1][3] = GameUtils.getRandomInRange(1, 5);
         this.C13_f207[var1][4] = GameUtils.getRandomInRange(3, 5);
      }

   }

   private void D() {
      C25.C25_f321 = false;
      C25.C25_f332 = false;
      C25.C25_f335 = 0;
      C7.C7_f67 = true;
      C25.B().C25_f290 = 0;
      C25.B().C25_f291 = 0;
      if (C25.B().C25_f348 != null) {
         C25.B().C25_f348.D();
      }

      if (C25.B().C25_f286 != null) {
         C25.B().C25_f286.q();
      }

      if (this.gameController != null) {
         this.gameController.b();
      }

      this.C13_f202 = 0;
      C53.p().C53_f776 = false;
      inputEnabled = false;
      GameScreenManager.getInstance().changeState((byte)9);
      if (this.C13_f205 > 0 && GameScreenManager.getInstance().difficultyLevel <= 0) {
         GameScreenManager.getInstance().setDifficultyLevel(this.C13_f205);
      }

   }

   public final void update() {
      if (this.C8_f110) {
         this.A();
         switch(this.C44_f698) {
         case 0:
            if (this.C13_f202 == 0 && this.g(16400)) {
               if (--C13_f201 < 0) {
                  C13_f201 = C13_f200.length - 1;
               }
            } else if (this.C13_f202 == 0 && this.g(32832)) {
               if (++C13_f201 > C13_f200.length - 1) {
                  C13_f201 = 0;
               }
            } else if (this.g(196640)) {
               if (inputEnabled) {
                  switch(C13_f201) {
                  case 0:
                     if (this.C13_f202 == 0) {
                        GameUtils.a();
                        this.E();
                        if (C25.B().C25_f348 != null) {
                           C25.B().C25_f348.D();
                        }

                        if (C25.B().C25_f286 != null) {
                           C25.B().C25_f286.q();
                        }

                        if (this.gameController != null) {
                           this.gameController.b();
                        }

                        this.C13_f202 = 0;
                        C53.p().C53_f776 = false;
                        GameScreenManager.getInstance().changeState((byte)9);
                        GameScreenManager.getInstance().changeState((byte)9);
                        if (this.C13_f205 > 0 && GameScreenManager.getInstance().difficultyLevel <= 0) {
                           GameScreenManager.getInstance().setDifficultyLevel(this.C13_f205);
                        }
                     } else if (this.C13_f202 == 1) {
                        this.C13_f202 = 0;
                        this.gameController.az();
                     }
                     break;
                  case 1:
                     if (this.C13_f202 == 0) {
                        GameUtils.a();
                        this.changeState((byte)5);
                     } else if (this.C13_f202 == 1) {
                        this.C13_f202 = 0;
                        this.gameController.az();
                     }
                     break;
                  case 2:
                     this.changeState((byte)1);
                     break;
                  case 3:
                     this.changeState((byte)2);
                     break;
                  case 4:
                     this.changeState((byte)3);
                     break;
                  case 5:
                     this.changeState((byte)4);
                  }
               } else {
                  switch(C13_f201) {
                  case 0:
                     if (this.C13_f202 == 0) {
                        GameUtils.a();
                        this.E();
                        this.D();
                        GameScreenManager.getInstance().changeState((byte)9);
                     } else if (this.C13_f202 == 1) {
                        this.C13_f202 = 0;
                        this.gameController.az();
                     }
                     break;
                  case 1:
                     this.changeState((byte)1);
                     break;
                  case 2:
                     this.changeState((byte)2);
                     break;
                  case 3:
                     this.changeState((byte)3);
                     break;
                  case 4:
                     this.changeState((byte)4);
                  }
               }
            }

            if (this.gameController.f()) {
               this.C13_f202 = 0;
            }

            C13 var1 = this;
            if (this.C13_f211) {
               ++this.C13_f212;
               if (this.C13_f212 >= 100) {
                  for(int var2 = 0; var2 < var1.C13_f213.length; ++var2) {
                     var1.C13_f213[var2] = 0;
                  }

                  var1.C13_f212 = 0;
                  var1.C();
                  var1.C13_f211 = false;
               }
            }
            break;
         case 1:
            this.gameController.t();
            break;
         case 2:
            this.gameController.p();
            break;
         case 3:
            this.gameController.r();
            break;
         case 4:
            if (this.g(131072)) {
               GameScreenManager.getInstance().changeState((byte)1);
            } else if (this.g(262144)) {
               this.changeState((byte)0);
            }
            break;
         case 5:
            if (this.g(131104)) {
               C25.B();
               C25.K();
               this.D();
            } else if (this.g(262144)) {
               this.changeState((byte)0);
               this.dialogManager.removeDialog("/data/ui/msgtip.ui");
            }
         }

         this.dialogManager.closeCurrentDialog();
      }
   }

   public final void renderPauseScreen(Graphics var1) {
      int var4;
      switch(this.C44_f698) {
      case 1:
      case 2:
      case 3:
         Graphics var2 = var1;

         for(var4 = 0; var4 < getScreenHeight() / 20; ++var4) {
            if (var4 % 2 == 0) {
               var2.setColor(10440998);
            } else {
               var2.setColor(12082732);
            }

            var2.fillRect(0, var4 * 20, getScreenWidth(), 20);
         }
      default:
         this.dialogManager.render(var1);
         if (this.C44_f698 == 0) {
            String var10002 = getLocalizedText(C13_f200[C13_f201]);
            int var10003 = (getScreenWidth() - getDefaultFont().stringWidth(getLocalizedText(C13_f200[C13_f201]))) / 2 + 4;
            int var10004 = getScreenHeight() - 20;
            boolean var7 = true;
            int var6 = var10004;
            int var5 = var10003;
            String var9 = var10002;
            var1.setColor(this.C13_f214[this.C13_f203]);
            var1.drawString(var9, var5, var6 - 1, 36);
            var1.drawString(var9, var5, var6 + 1, 36);
            var1.drawString(var9, var5 - 1, var6, 36);
            var1.drawString(var9, var5 + 1, var6, 36);
            var1.setColor(16777215);
            var1.drawString(var9, var5, var6, 36);
            ++this.C13_f203;
            if (this.C13_f203 >= 6) {
               this.C13_f203 = 0;
            }

            Graphics var3 = var1;
            C13 var8 = this;
            if (!this.C13_f211) {
               for(var4 = 0; var4 < var8.C13_f206; ++var4) {
                  var3.drawRegion(var8.C13_f204, var8.C13_f208[var8.C13_f207[var4][2] << 2], var8.C13_f208[(var8.C13_f207[var4][2] << 2) + 1], var8.C13_f208[(var8.C13_f207[var4][2] << 2) + 2], var8.C13_f208[(var8.C13_f207[var4][2] << 2) + 3], 0, var8.C13_f207[var4][0], var8.C13_f207[var4][1], 20);
                  int[] var10000 = var8.C13_f207[var4];
                  var10000[0] += var8.C13_f207[var4][3];
                  var10000 = var8.C13_f207[var4];
                  var10000[1] -= var8.C13_f207[var4][4];
                  if (var8.C13_f207[var4][0] > getScreenWidth() || var8.C13_f207[var4][1] < 0) {
                     int var10 = var8.C13_f213[var4]++;
                  }
               }

               for(var4 = 0; var4 < var8.C13_f213.length && var8.C13_f213[var4] > 0; ++var4) {
               }

               if (var4 >= var8.C13_f213.length) {
                  var8.C13_f211 = true;
               }
            }

         } else {
            if (this.C44_f698 == 4) {
               var1.setColor(0);
               var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
               var1.setColor(16777215);
               var1.drawString("Bạn có muốn thoát không?", getScreenCenterX(), getScreenCenterY() - 10, 17);
               var1.drawString("", 2, getScreenHeight(), 36);
               var1.drawString("Không", getScreenWidth() - 2, getScreenHeight(), 40);
            }

         }
      }
   }

   public final void cleanupCurrentScreen() {
      this.C13_f204 = null;
      this.dialogManager.clearDialogs();
   }

   private void E() {
      this.dialogManager.removeDialog("/data/ui/menu.ui");
   }

   public final void changeState(byte var1) {
      this.C44_f698 = var1;
      switch(var1) {
      case 0:
         this.gameController.u();
         this.gameController.w();
         return;
      case 1:
         if (this.C13_f205 > 0) {
            GameScreenManager.getInstance().difficultyLevel = (byte)this.C13_f205;
         }

         this.gameController.s();
         this.E();
         return;
      case 2:
         this.gameController.o();
         this.E();
         return;
      case 3:
         this.gameController.q();
         this.E();
         return;
      case 5:
         this.gameController.K();
         this.gameController.a("Có chắc chắn xóa dữ liệu cũ để chơi mới không?");
      case 4:
      default:
      }
   }
}
