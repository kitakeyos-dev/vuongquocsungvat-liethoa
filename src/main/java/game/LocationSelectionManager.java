package game;

import engine.GameEngineBase;
import engine.entity.ResourceManager;
import javax.microedition.lcdui.Graphics;

public final class LocationSelectionManager extends GameEngineBase {
   private static LocationSelectionManager instance = null;
   private StringBuffer C48_f733 = null;
   private WorldGameSession C48_f734 = WorldGameSession.getInstance();
   private BattleSystemManager C48_f735 = BattleSystemManager.B();
   private int[] C48_f736 = new int[] { 2, 7, 8, 8, 13, 7, 2, 13, 7, 8, 15, 29 };
   private int[] C48_f737 = new int[] { 0, 2, 9, 17, 25, 38, 45, 47, 60, 67, 75, 90 };
   private byte C48_f738 = 0;
   private byte C48_f739 = 0;
   private byte C48_f740 = 0;
   private byte C48_f741 = 0;
   private byte C48_f742 = 0;
   private static boolean C48_f743 = true;
   private String[] C48_f744 = new String[] { "1: tràng cảnh khống chế", "2: sủng vật khống chế" };
   private String[] C48_f745 = new String[] { "Lựa chọn tràng cảnh", "Lựa chọn gian phòng", "Gặp địch điều chỉnh thử" };
   private short[] C48_f746 = new short[] { 0, 0, 198, 198, 151, 55, 80, 63, 118, 118, 118, 132, 112, 174, 160, 368,
         136, 136, 230, 245, 167, 135, 168, 198 };
   private String[] C48_f747 = new String[] { "CG tràng cảnh", "Thôn trang", "Bích thủy", "Gỗ thô", "Niêm thổ",
         "Hắc thạch", "Hắc Long thần điện", "Thiên giới", "Viễn cổ", "Đạo quán", "Thần thú mê cung", "Trong phòng" };

   public static LocationSelectionManager B() {
      if (instance == null) {
         instance = new LocationSelectionManager();
      }

      return instance;
   }

   public final void update() {
      if (this.isActive) {
         this.updateInputState();
         switch (this.currentState) {
            case 0:
               if (this.isKeyPressed(196640)) {
                  this.changeState((byte) 1);
                  return;
               }
               break;
            case 1:
               if (this.isKeyPressed(16400)) {
                  this.C48_f741 = 0;
                  --this.C48_f739;
                  if (this.C48_f739 <= 0) {
                     this.C48_f739 = 0;
                     return;
                  }
               } else if (this.isKeyPressed(32832)) {
                  this.C48_f741 = 0;
                  ++this.C48_f739;
                  if (this.C48_f739 > this.C48_f745.length - 1) {
                     this.C48_f739 = (byte) (this.C48_f745.length - 1);
                     return;
                  }
               } else if (this.isKeyPressed(4100)) {
                  if (this.C48_f739 == 0) {
                     --this.C48_f740;
                     if (this.C48_f740 <= 0) {
                        this.C48_f740 = 0;
                        return;
                     }
                  } else if (this.C48_f739 == 1) {
                     --this.C48_f741;
                     if (this.C48_f741 <= 0) {
                        this.C48_f741 = 0;
                        return;
                     }
                  } else if (this.C48_f739 == 2) {
                     C48_f743 = !C48_f743;
                     return;
                  }
               } else if (this.isKeyPressed(8448)) {
                  if (this.C48_f739 == 0) {
                     ++this.C48_f740;
                     if (this.C48_f740 >= this.C48_f736.length - 1) {
                        this.C48_f740 = (byte) (this.C48_f736.length - 1);
                        return;
                     }
                  } else if (this.C48_f739 == 1) {
                     ++this.C48_f741;
                     if (this.C48_f741 >= this.C48_f736[this.C48_f740] - 1) {
                        this.C48_f741 = (byte) (this.C48_f736[this.C48_f740] - 1);
                        return;
                     }
                  } else if (this.C48_f739 == 2) {
                     C48_f743 = !C48_f743;
                     return;
                  }
               } else {
                  if (this.isKeyPressed(196640)) {
                     this.C48_f734.currentRegionId = this.C48_f740;
                     this.C48_f734.currentAreaId = this.C48_f741;
                     WorldGameSession.getInstance().lastInteractedNpcId = -1;
                     WorldGameSession.isGameLoaded = true;
                     inputEnabled = false;
                     this.C48_f734.spawnPositionX = this.C48_f746[this.C48_f740 << 1];
                     this.C48_f734.spawnPositionY = this.C48_f746[(this.C48_f740 << 1) + 1];
                     this.C48_f734.cleanupCurrentScreen();
                     GameScreenManager.getInstance().changeState((byte) 9);
                     return;
                  }

                  if (this.isKeyPressed(1024)) {
                     GameScreenManager.getInstance().changeState((byte) 10);
                     return;
                  }
               }
               break;
            case 2:
               if (this.isKeyPressed(4100)) {
                  --this.C48_f742;
                  if (this.C48_f742 <= 0) {
                     this.C48_f742 = (byte) (ResourceManager.gameDatabase[0].length - 1);
                     return;
                  }
               } else if (this.isKeyPressed(8448)) {
                  ++this.C48_f742;
                  if (this.C48_f742 >= ResourceManager.gameDatabase[0].length - 1) {
                     this.C48_f742 = 0;
                     return;
                  }
               } else if (this.isKeyPressed(196640)) {
                  this.C48_f735.cleanupCurrentScreen();
                  this.C48_f734.checkWildEncounter();
                  GameScreenManager.getInstance().changeState((byte) 12);
               }
         }

      }
   }

   public final void renderPauseScreen(Graphics var1) {
      var1.setColor(16777215);
      var1.fillRect(0, 0, getScreenWidth(), 100);
      int var2;
      switch (this.currentState) {
         case 0:
            var1.setColor(16711680);
            var1.drawString("Đài điều khiển", getScreenWidth() >> 1, 10, 17);
            var1.setColor(0);

            for (var2 = 0; var2 < this.C48_f744.length; ++var2) {
               if (var2 == 0) {
                  var1.setColor(16711680);
               } else {
                  var1.setColor(0);
               }

               var1.drawString(this.C48_f744[var2], 10, 30 + var2 * 20, 20);
            }

            return;
         case 1:
            var1.setColor(16711680);
            var1.drawString(this.C48_f744[0], getScreenWidth() >> 1, 10, 17);

            for (var2 = 0; var2 < this.C48_f745.length; ++var2) {
               if (this.C48_f739 == var2) {
                  var1.setColor(16711680);
               } else {
                  var1.setColor(0);
               }

               var1.drawString(this.C48_f745[var2], 30 + var2 * 80, 30, 20);
            }

            var1.setColor(0);
            var1.drawString("Tràng cảnh: " + this.C48_f740 + "  " + this.C48_f747[this.C48_f740], 10, 50, 20);
            var1.drawString("Gian phòng: " + this.C48_f741 + "  "
                  + getLocalizedText(384 + this.C48_f737[this.C48_f740] + this.C48_f741), 10, 70, 20);
            if (C48_f743) {
               var1.drawString("Có thể gặp được địch", 120, 70, 20);
               return;
            }

            var1.drawString("Không thể gặp địch", 120, 70, 20);
            return;
         case 2:
            var1.setColor(16711680);
            var1.drawString(this.C48_f744[1], getScreenWidth() >> 1, 10, 17);
            var1.drawString(
                  "Sủng vật trước mặt: " + this.C48_f742 + " tên: "
                        + getLocalizedText(ResourceManager.gameDatabase[0][this.C48_f742][0]),
                  getScreenWidth() >> 1, 30, 17);
         default:
      }
   }

   public final boolean initializeGame() {
      if (this.C48_f733 == null) {
         this.C48_f733 = new StringBuffer();
      }

      return true;
   }

   public final void cleanupCurrentScreen() {
   }

   public final void changeState(byte var1) {
      this.previousState = this.currentState;
      switch (var1) {
         case 1:
            this.C48_f740 = (byte) this.C48_f734.currentRegionId;
            this.C48_f741 = (byte) this.C48_f734.currentAreaId;
         case 0:
         default:
            this.currentState = var1;
      }
   }
}
