package game;

import a.GameEngineBase;
import a.b.C67;
import javax.microedition.lcdui.Graphics;

public final class C48 extends GameEngineBase {
   private static C48 C48_f732 = null;
   private StringBuffer C48_f733 = null;
   private C25 C48_f734 = C25.B();
   private C29 C48_f735 = C29.B();
   private int[] C48_f736 = new int[]{2, 7, 8, 8, 13, 7, 2, 13, 7, 8, 15, 29};
   private int[] C48_f737 = new int[]{0, 2, 9, 17, 25, 38, 45, 47, 60, 67, 75, 90};
   private byte C48_f738 = 0;
   private byte C48_f739 = 0;
   private byte C48_f740 = 0;
   private byte C48_f741 = 0;
   private byte C48_f742 = 0;
   private static boolean C48_f743 = true;
   private String[] C48_f744 = new String[]{"1: tràng cảnh khống chế", "2: sủng vật khống chế"};
   private String[] C48_f745 = new String[]{"Lựa chọn tràng cảnh", "Lựa chọn gian phòng", "Gặp địch điều chỉnh thử"};
   private short[] C48_f746 = new short[]{0, 0, 198, 198, 151, 55, 80, 63, 118, 118, 118, 132, 112, 174, 160, 368, 136, 136, 230, 245, 167, 135, 168, 198};
   private String[] C48_f747 = new String[]{"CG tràng cảnh", "Thôn trang", "Bích thủy", "Gỗ thô", "Niêm thổ", "Hắc thạch", "Hắc Long thần điện", "Thiên giới", "Viễn cổ", "Đạo quán", "Thần thú mê cung", "Trong phòng"};

   public static C48 B() {
      if (C48_f732 == null) {
         C48_f732 = new C48();
      }

      return C48_f732;
   }

   public final void update() {
      if (this.C8_f110) {
         this.A();
         switch(this.C44_f698) {
         case 0:
            if (this.g(196640)) {
               this.changeState((byte)1);
               return;
            }
            break;
         case 1:
            if (this.g(16400)) {
               this.C48_f741 = 0;
               --this.C48_f739;
               if (this.C48_f739 <= 0) {
                  this.C48_f739 = 0;
                  return;
               }
            } else if (this.g(32832)) {
               this.C48_f741 = 0;
               ++this.C48_f739;
               if (this.C48_f739 > this.C48_f745.length - 1) {
                  this.C48_f739 = (byte)(this.C48_f745.length - 1);
                  return;
               }
            } else if (this.g(4100)) {
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
            } else if (this.g(8448)) {
               if (this.C48_f739 == 0) {
                  ++this.C48_f740;
                  if (this.C48_f740 >= this.C48_f736.length - 1) {
                     this.C48_f740 = (byte)(this.C48_f736.length - 1);
                     return;
                  }
               } else if (this.C48_f739 == 1) {
                  ++this.C48_f741;
                  if (this.C48_f741 >= this.C48_f736[this.C48_f740] - 1) {
                     this.C48_f741 = (byte)(this.C48_f736[this.C48_f740] - 1);
                     return;
                  }
               } else if (this.C48_f739 == 2) {
                  C48_f743 = !C48_f743;
                  return;
               }
            } else {
               if (this.g(196640)) {
                  this.C48_f734.C25_f290 = this.C48_f740;
                  this.C48_f734.C25_f291 = this.C48_f741;
                  C25.B().C25_f295 = -1;
                  C25.C25_f321 = true;
                  inputEnabled = false;
                  this.C48_f734.C25_f293 = this.C48_f746[this.C48_f740 << 1];
                  this.C48_f734.C25_f294 = this.C48_f746[(this.C48_f740 << 1) + 1];
                  this.C48_f734.cleanupCurrentScreen();
                  GameScreenManager.getInstance().changeState((byte)9);
                  return;
               }

               if (this.g(1024)) {
                  GameScreenManager.getInstance().changeState((byte)10);
                  return;
               }
            }
            break;
         case 2:
            if (this.g(4100)) {
               --this.C48_f742;
               if (this.C48_f742 <= 0) {
                  this.C48_f742 = (byte)(C67.C67_f923[0].length - 1);
                  return;
               }
            } else if (this.g(8448)) {
               ++this.C48_f742;
               if (this.C48_f742 >= C67.C67_f923[0].length - 1) {
                  this.C48_f742 = 0;
                  return;
               }
            } else if (this.g(196640)) {
               this.C48_f735.cleanupCurrentScreen();
               this.C48_f734.M();
               GameScreenManager.getInstance().changeState((byte)12);
            }
         }

      }
   }

   public final void renderPauseScreen(Graphics var1) {
      var1.setColor(16777215);
      var1.fillRect(0, 0, getScreenWidth(), 100);
      int var2;
      switch(this.C44_f698) {
      case 0:
         var1.setColor(16711680);
         var1.drawString("Đài điều khiển", getScreenWidth() >> 1, 10, 17);
         var1.setColor(0);

         for(var2 = 0; var2 < this.C48_f744.length; ++var2) {
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

         for(var2 = 0; var2 < this.C48_f745.length; ++var2) {
            if (this.C48_f739 == var2) {
               var1.setColor(16711680);
            } else {
               var1.setColor(0);
            }

            var1.drawString(this.C48_f745[var2], 30 + var2 * 80, 30, 20);
         }

         var1.setColor(0);
         var1.drawString("Tràng cảnh: " + this.C48_f740 + "  " + this.C48_f747[this.C48_f740], 10, 50, 20);
         var1.drawString("Gian phòng: " + this.C48_f741 + "  " + getLocalizedText(384 + this.C48_f737[this.C48_f740] + this.C48_f741), 10, 70, 20);
         if (C48_f743) {
            var1.drawString("Có thể gặp được địch", 120, 70, 20);
            return;
         }

         var1.drawString("Không thể gặp địch", 120, 70, 20);
         return;
      case 2:
         var1.setColor(16711680);
         var1.drawString(this.C48_f744[1], getScreenWidth() >> 1, 10, 17);
         var1.drawString("Sủng vật trước mặt: " + this.C48_f742 + " tên: " + getLocalizedText(C67.C67_f923[0][this.C48_f742][0]), getScreenWidth() >> 1, 30, 17);
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
      this.C44_f699 = this.C44_f698;
      switch(var1) {
      case 1:
         this.C48_f740 = (byte)this.C48_f734.C25_f290;
         this.C48_f741 = (byte)this.C48_f734.C25_f291;
      case 0:
      default:
         this.C44_f698 = var1;
      }
   }
}
