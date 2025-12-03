package game;

import engine.GameUtils;
import engine.GameEngineBase;
import engine.graphics.EffectEntity;
import engine.graphics.ScreenTransitionManager;
import engine.graphics.ImageProcessor;
import engine.entity.ResourceManager;
import layout.ComponentData;
import layout.SpriteRenderer;
import layout.UIContainerComponent;
import layout.DialogManager;
import layout.DialogSystem;
import layout.DialogHandler;
import java.util.Vector;

public final class DialogUIManager implements DialogHandler {
   private static DialogUIManager C9_f120;
   private GameEngineBase C9_f121;
   private DialogManager C9_f122 = DialogManager.getInstance();
   private PlayerCharacter C9_f123;
   protected int C9_f124;
   protected int C9_f125;
   protected int C9_f126;
   private int C9_f127;
   protected int C9_f128;
   protected int C9_f129;
   private int C9_f130;
   protected int C9_f131;
   protected boolean C9_f132;
   private byte C9_f133;
   private byte C9_f134;
   private int C9_f135;
   protected int C9_f136;
   private int C9_f137;
   protected int C9_f138;
   private int[] C9_f139;
   private int[] C9_f140;
   public byte C9_f141 = -1;
   private static String[] C9_f142 = new String[] { "Thủy Kimura", "Bích Thủy thành", "Nguyên Mộc Thành",
         "Niêm Thổ Thành", "Hắc Thạch thành", "Thiên không", "Xa cổ" };
   private static short[] C9_f143 = new short[] { 1, 0, 196, 208, 0, 2, 1, 196, 208, 0, 3, 3, 196, 208, 0, 4, 5, 320,
         352, 0, 5, 3, 320, 196, 0, 7, 2, 288, 112, 0, 8, 0, 160, 144, 0 };
   private Vector C9_f144 = new Vector();
   private int C9_f145 = 0;
   private int C9_f146 = 0;
   private int C9_f147 = 0;
   private int C9_f148 = 0;
   public int C9_f149 = 0;
   boolean C9_f150 = false;
   private int C9_f151;
   private int C9_f152;
   private int C9_f153 = 0;
   private int C9_f154 = 0;
   private int C9_f155 = 0;
   private int C9_f156 = 0;
   private String[] C9_f157 = new String[] { "/data/ui/option.ui", "/data/ui/answer.ui", "/data/ui/wharf1.ui" };
   public short[] C9_f158 = new short[] { 9, 0, 120, 448, 9, 1, 136, 272, 9, 2, 208, 256, 9, 3, 80, 264, 9, 4, 112, 288,
         9, 5, 40, 280, 9, 6, 136, 328, 9, 7, 104, 328 };
   private String[] C9_f159 = new String[] { "Đạt được 2000 kim tiền", "Đạt được 5 Phong ấn cầu",
         "Đạt được 5 Bánh Sandwich", "Đạt được 2 Sinh mệnh thạch", "Đạt được 2 huy hiệu" };
   private short[][] C9_f160 = new short[][] { { 621, 622 }, { 623, 624 }, { 625, 626 }, { 627, 628 },
         { 629, 630, 631, 632 } };
   private short[][] C9_f161 = new short[][] { { 5, 2, 112, 224, 2, 2, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10 },
         { 4, 0, 48, 176, 2, 2, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10 },
         { 3, 6, 288, 224, 3, 0, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10 },
         { 1, 5, 272, 128, 3, 0, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10 }, { 1, 5, 272, 128, 3, 2, 0, 0, 0, 3, 6, 288,
               224, 3, 0, 0, 0, 0, 4, 0, 48, 176, 2, 0, 0, 0, 0, 5, 2, 112, 224, 2, 2, 0, 0, 0 } };
   private byte C9_f162;
   private byte C9_f163;
   private String[] C9_f164 = new String[] { "Dẫn thưởng", "Tiến hóa", "Dị hóa", "Tài liệu", "Cách mở" };

   public static DialogUIManager a() {
      if (C9_f120 == null) {
         C9_f120 = new DialogUIManager();
      }

      return C9_f120;
   }

   public DialogUIManager() {
      if (this.C9_f123 == null) {
         this.C9_f123 = PlayerCharacter.getInstance();
      }

   }

   public final void b() {
      C9_f120 = null;
      this.C9_f123 = null;
   }

   public final void a(GameEngineBase var1) {
      if (this.C9_f121 != null) {
         this.C9_f121 = null;
      }

      this.C9_f121 = var1;
      this.C9_f132 = true;
   }

   public final void c() {
      this.C9_f122.showDialog("/data/ui/world.ui", 257, this);
      this.C9_f133 = 0;
   }

   public final void d() {
      this.C9_f122.currentDialog.getChildById(5).setVisible(true);
      this.C9_f122.currentDialog.getChildById(7).setVisible(true);
   }

   private void aU() {
      if (this.C9_f122.isTopDialog("/data/ui/world.ui")) {
         for (int var1 = 1; var1 <= 7; ++var1) {
            this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
         }
      }

   }

   public void e() {
      if (this.C9_f133 < 2 && !QuestManager.isQuestActive && GameWorldManager.C25_f338
            && this.C9_f122.containsDialog("/data/ui/world.ui")) {
         DialogSystem var2 = this.C9_f122.getDialog("/data/ui/world.ui");
         if (var2.getChildById(1).getComponentData().renderer.getSpriteManager().isAtFrame(4)) {
            this.C9_f122.getDialog("/data/ui/world.ui").getChildById(6)
                  .getComponentData().text = ((GameWorldManager) this.C9_f121).C25_f296;
            this.C9_f133 = 1;
         } else if (this.C9_f133 == 1) {
            var2 = this.C9_f122.getDialog("/data/ui/world.ui");
            if (var2.getChildById(1).getComponentData().renderer.getSpriteManager().getCurrentFrameIndex() >= 5) {
               this.C9_f122.getDialog("/data/ui/world.ui").getChildById(6).getComponentData().text = "";
               this.C9_f133 = 2;
               GameWorldManager.C25_f338 = false;
            }
         }
      }

      this.f();
   }

   public boolean f() {
      if (this.C9_f134 < 2 && this.C9_f122.isTopDialog("/data/ui/openbox.ui")) {
         if (this.C9_f134 == 1) {
            if (this.C9_f121.isKeyPressed(196640)) {
               this.C9_f122.currentDialog.getChildById(2).getComponentData().text = "";
               this.C9_f134 = 2;
               this.C9_f132 = true;
               this.az();
               return true;
            }
         } else {
            this.C9_f134 = 1;
         }
      }

      return this.g();
   }

   public final boolean g() {
      if (this.C9_f134 < 2) {
         if (this.C9_f122.isTopDialog("/data/ui/taskTip.ui")) {
            if (this.C9_f134 == 1) {
               if (this.C9_f121.isKeyPressed(196640)) {
                  this.C9_f134 = 2;
                  this.C9_f132 = true;
                  if (this.C9_f122.isTopDialog("/data/ui/taskTip.ui")) {
                     this.C9_f122.removeDialog("/data/ui/taskTip.ui");
                  }

                  return true;
               }
            } else {
               this.C9_f134 = 1;
            }
         }

         this.C9_f132 = true;
      }

      return false;
   }

   public final void h() {
      this.C9_f122.showDialog("/data/ui/transmit.ui", 257, this);
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = C9_f142.length;
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
      this.aV();
   }

   private void aV() {
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;

      for (int var1 = 0; var1 < 5; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1 + 5).getComponentData().text = C9_f142[var1 + this.C9_f135];
      }

      this.C9_f122.currentDialog.getChildById(13).setOffsetY(109 + this.C9_f136 * 88 / C9_f142.length,
            this.C9_f122.currentDialog.getUIContainerComponent());
   }

   public final void i() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.aV();
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.aV();
      } else if (this.C9_f121.isKeyPressed(196640)) {
         GameWorldManager.B().C25_f290 = C9_f143[this.C9_f136 * 5];
         GameWorldManager.B().C25_f291 = C9_f143[this.C9_f136 * 5 + 1];
         GameWorldManager.B().C25_f293 = C9_f143[this.C9_f136 * 5 + 2];
         GameWorldManager.B().C25_f294 = C9_f143[this.C9_f136 * 5 + 3];
         GameWorldManager.C25_f320 = (byte) C9_f143[this.C9_f136 * 5 + 4];
         GameWorldManager.B().C25_f295 = -1;
         GameScreenManager.getInstance().changeState((byte) 9);
      } else {
         if (this.C9_f121.isKeyPressed(262144)) {
            this.C9_f121.changeState((byte) 8);
            this.C9_f122.removeDialog("/data/ui/transmit.ui");
         }

      }
   }

   public final boolean j() {
      return this.C9_f122.isTopDialog("/data/ui/openbox.ui") || this.C9_f122.isTopDialog("/data/ui/taskTip.ui");
   }

   public final void k() {
      String[] var1 = new String[] { "Tùy thân cửa hàng", "Sủng vật", "Lưng bao", "Đồ giám", "Nhiệm vụ",
            "Lưu dữ liệu" };
      this.aU();
      this.C9_f122.showDialog("/data/ui/gamemenu.ui", 257, this);
      int var2;
      if (GameEngineBase.paymentActive) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = 6;
         this.C9_f122.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
               .getLocalizedText(605 + this.C9_f125);
         this.C9_f122.currentDialog.getChildById(15).getComponentData().text = var1[0];

         for (var2 = 0; var2 < 5; ++var2) {
            this.C9_f122.currentDialog.getChildById(var2 + 5).getComponentData().text = var1[var2 + 1];
         }
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = 5;
         this.C9_f122.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
               .getLocalizedText(606 + this.C9_f125);
         this.C9_f122.currentDialog.getChildById(15).getComponentData().text = var1[1];

         for (var2 = 0; var2 < 4; ++var2) {
            this.C9_f122.currentDialog.getChildById(var2 + 5).getComponentData().text = var1[var2 + 2];
         }

         this.C9_f122.currentDialog.getChildById(9).setVisible(false);
      }

      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f125;
      this.C9_f122.currentDialog.getChildById(18).getComponentData().text = "" + this.C9_f123.getBadges();
      this.C9_f122.currentDialog.getChildById(19).getComponentData().text = "" + this.C9_f123.getGold();
      this.C9_f131 = 0;
   }

   public final void l() {
      this.C9_f121.updateActionSequence();
      if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && !this.j()
            && this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && !this.j()
            && this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (!this.j() && GameEngineBase.isConfirmAllowed() && this.C9_f121.isKeyPressed(196640)) {
         if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0)) {
            return;
         }

         if (GameEngineBase.paymentActive) {
            switch (this.C9_f125) {
               case 0:
                  this.C9_f121.changeState((byte) 14);
                  this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
                  break;
               case 1:
                  this.C9_f126 = 0;
                  this.C9_f121.handleActionResponse();
                  this.C9_f121.changeState((byte) 7);
                  this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
                  break;
               case 2:
                  this.C9_f121.handleActionResponse();
                  this.C9_f121.changeState((byte) 8);
                  this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
                  break;
               case 3:
                  this.C9_f126 = 0;
                  this.C9_f121.changeState((byte) 9);
                  this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
                  break;
               case 4:
                  this.C9_f125 = 0;
                  this.C9_f121.changeState((byte) 10);
                  this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
                  break;
               case 5:
                  this.C9_f122.currentDialog.getChildById(11).setVisible(false);
                  this.C9_f122.currentDialog.getChildById(12).setVisible(false);
                  this.C9_f121.changeState((byte) 22);
            }
         } else {
            switch (this.C9_f125) {
               case 0:
                  this.C9_f126 = 0;
                  this.C9_f121.handleActionResponse();
                  this.C9_f121.changeState((byte) 7);
                  this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
                  break;
               case 1:
                  this.C9_f121.handleActionResponse();
                  this.C9_f121.changeState((byte) 8);
                  this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
                  break;
               case 2:
                  this.C9_f126 = 0;
                  this.C9_f121.changeState((byte) 9);
                  this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
                  break;
               case 3:
                  this.C9_f125 = 0;
                  this.C9_f121.changeState((byte) 10);
                  this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
                  break;
               case 4:
                  this.C9_f122.currentDialog.getChildById(11).setVisible(false);
                  this.C9_f122.currentDialog.getChildById(12).setVisible(false);
                  this.C9_f121.changeState((byte) 22);
            }
         }
      } else if (GameEngineBase.isCancelAllowed() && this.C9_f121.isKeyPressed(262144)) {
         this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
         this.C9_f121.changeState((byte) 0);
      }

      this.g();
   }

   public final void m() {
      this.aU();
      this.C9_f122.showDialog("/data/ui/gamesystem.ui", 257, this);
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f125;
      this.C9_f131 = 0;
   }

   public final void n() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.isKeyPressed(196640)) {
         switch (this.C9_f125) {
            case 0:
               this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
               this.C9_f121.changeState((byte) 0);
               return;
            case 1:
               this.C9_f121.changeState((byte) 20);
               this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
               return;
            case 2:
               this.C9_f121.changeState((byte) 21);
               this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
               return;
            case 3:
               if (this.C9_f131 == 0) {
                  this.C9_f122.showDialog("/data/ui/option.ui", 257, this);
                  this.C9_f126 = 1;
                  ((UIContainerComponent) this.C9_f122.currentDialog
                        .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f126;
                  this.C9_f122.currentDialog.getChildById(12).getComponentData().text = "";
                  this.C9_f122.currentDialog.getChildById(13).getComponentData().text = "Không";
                  this.C9_f131 = 1;
                  return;
               } else {
                  switch (this.C9_f126) {
                     case 0:
                        GameScreenManager.getInstance().pauseStartTime = 0L;
                        GameScreenManager.getInstance().gameStartTime = 0L;
                        PlayerCharacter.getInstance().isInitialized = false;
                        GameScreenManager.getInstance().changeState((byte) 7);
                        this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
                        break;
                     case 1:
                        this.C9_f122.removeDialog("/data/ui/option.ui");
                        this.C9_f131 = 0;
                        this.C9_f132 = true;
                        return;
                  }
               }
            default:
         }
      } else {
         if (this.C9_f121.isKeyPressed(262144)) {
            if (this.C9_f131 == 0) {
               this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
               this.C9_f121.changeState((byte) 0);
               return;
            }

            if (this.C9_f131 == 1) {
               this.C9_f132 = true;
               this.C9_f122.removeDialog("/data/ui/option.ui");
               this.C9_f131 = 0;
            }
         }

      }
   }

   public final void o() {
      this.C9_f122.showDialog("/data/ui/help1.ui", 257, this);
      this.C9_f125 = 0;
      this.C9_f122.currentDialog.getChildById(6).setVisible(true);
      this.C9_f122.currentDialog.getChildById(7).setVisible(false);
      this.e(this.C9_f125);
   }

   private void e(int var1) {
      int var2;
      if (var1 == 0) {
         this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Trợ giúp";
         this.C9_f122.currentDialog.getChildById(8)
               .getComponentData().text = "Nhấn nút 2, 4, 6, 8 để di chuyển#nNút 5: công kích, đối thoại, xác nhận#nNút 1: Xem nhiệm vụ#nNút 9: lựa chọn sủng vật cưỡi#nNút 0: Xem bản đồ#nNút mềm trái: menu hệ thống#nNút mềm phải: menu trò chơi";

         for (var2 = 0; var2 < 28; ++var2) {
            this.C9_f122.currentDialog.getChildById(var2 + 9).setVisible(false);
         }
      } else if (var1 > 0) {
         this.C9_f122.currentDialog.getChildById(8).getComponentData().text = "";

         for (var2 = 0; var2 < 14; ++var2) {
            this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).setVisible(true);
            this.C9_f122.currentDialog.getChildById(9 + (var2 << 1) + 1).setVisible(true);
            if ((var1 - 1) * 14 + var2 < 26) {
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1))
                     .getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().renderer
                     .setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().renderer
                     .initializeSprite(325, false, (byte) -2);
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().renderer
                     .setSpriteIndex((var1 - 1) * 14 + var2 + 1);
               if ((var1 - 1) * 14 + var2 <= 10) {
                  this.C9_f122.currentDialog.getChildById(9 + (var2 << 1) + 1).getComponentData().text = GameEngineBase
                        .getLocalizedText(var2 + 311);
               } else {
                  this.C9_f122.currentDialog.getChildById(9 + (var2 << 1) + 1).getComponentData().text = GameEngineBase
                        .getLocalizedText(333 + ((var1 - 1) * 14 + var2 - 11));
               }
            } else {
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).setVisible(false);
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1) + 1).setVisible(false);
            }
         }
      }

      this.C9_f122.currentDialog.getChildById(39).getComponentData().text = var1 + 1 + "/3";
   }

   public final void p() {
      if (this.C9_f121.isKeyPressed(16400)) {
         --this.C9_f125;
         if (this.C9_f125 <= 0) {
            this.C9_f125 = 0;
         }

         this.e(this.C9_f125);
      } else if (this.C9_f121.isKeyPressed(32832)) {
         ++this.C9_f125;
         if (this.C9_f125 >= 2) {
            this.C9_f125 = 2;
         }

         this.e(this.C9_f125);
      } else {
         if (this.C9_f121.isKeyPressed(262144)) {
            this.C9_f121.changeState((byte) 0);
            this.C9_f122.removeDialog("/data/ui/help1.ui");
         }

      }
   }

   public final void q() {
      this.C9_f122.showDialog("/data/ui/help.ui", 257, this);
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Quan tại";
      this.C9_f122.currentDialog.getChildById(8)
            .getComponentData().text = "Tên trò chơi: Sủng vật Vương quốc - Liệt hỏa#nViệt hóa: BIGAME#nWapsite: 3g.mwap.biz";
      this.C9_f122.currentDialog.getChildById(6).setVisible(true);
      this.C9_f122.currentDialog.getChildById(7).setVisible(false);

      for (int var1 = 9; var1 < 13; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
      }

   }

   public final void r() {
      if (this.C9_f121.isKeyPressed(262144)) {
         this.C9_f121.changeState((byte) 0);
         this.C9_f122.removeDialog("/data/ui/help.ui");
      }

   }

   public final void s() {
      this.C9_f122.showDialog("/data/ui/help.ui", 257, this);
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Tùy chọn";
      this.C9_f122.currentDialog.getChildById(8).getComponentData().text = "";
      this.C9_f122.currentDialog.getChildById(6).setVisible(false);
      this.C9_f122.currentDialog.getChildById(7).setVisible(true);

      for (int var1 = 9; var1 < 13; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1).setVisible(true);
      }

      this.aW();
   }

   private void aW() {
      for (int var1 = 1; var1 < 4; ++var1) {
         if (var1 <= GameScreenManager.getInstance().difficultyLevel) {
            this.C9_f122.currentDialog.getChildById(var1 + 9).getComponentData().backgroundColor = -2148;
         } else {
            this.C9_f122.currentDialog.getChildById(var1 + 9).getComponentData().backgroundColor = -8540732;
         }
      }

   }

   public final void t() {
      if (this.C9_f121.isKeyPressed(16400)) {
         GameScreenManager.getInstance().decreaseDifficulty();
         this.aW();
      } else if (this.C9_f121.isKeyPressed(32832)) {
         GameScreenManager.getInstance().increaseDifficulty();
         this.aW();
      } else {
         if (this.C9_f121.isKeyPressed(131072)) {
            MainMenuScreen.getInstance().animationCounter = GameScreenManager.getInstance().difficultyLevel;
            this.C9_f121.changeState((byte) 0);
            this.C9_f122.removeDialog("/data/ui/help.ui");
         }

      }
   }

   public final void u() {
      this.C9_f122.showDialog("/data/ui/menu.ui", 336, this);
   }

   public final void v() {
      this.C9_f122.showDialog("/data/ui/menu1.ui", 336, this);
   }

   public final void w() {
      this.C9_f122.removeDialog("/data/ui/menu1.ui");
   }

   public final void a(int var1, int var2) {
      switch (var1) {
         case 0:
            this.C9_f122.currentDialog.getChildById(var2).setVisible(false);
            return;
         case 1:
            this.C9_f122.currentDialog.getChildById(8).setVisible(false);
            this.C9_f122.currentDialog.getChildById(9).setVisible(false);
            return;
         case 2:
            this.C9_f122.currentDialog.getChildById(10).setVisible(false);
            this.C9_f122.currentDialog.getChildById(11).setVisible(false);
            return;
         case 3:
            this.C9_f122.currentDialog.getChildById(12).setVisible(false);
            this.C9_f122.currentDialog.getChildById(13).setVisible(false);
            return;
         case 4:
            for (var1 = 0; var1 < 2; ++var1) {
               if (this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer.spriteType = 2;
                  this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer
                        .setSpriteIndex((int) 0);
                  this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer.initializeSprite(336,
                        false, (byte) 0);
                  if (var1 == 0) {
                     this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer
                           .setSpriteIndex((int) 8);
                  } else {
                     this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer
                           .setSpriteIndex((int) 10);
                  }
               }
            }

            return;
         case 5:
            this.C9_f122.currentDialog.getChildById(16).setVisible(false);
            this.C9_f122.currentDialog.getChildById(17).setVisible(false);

            for (var1 = 0; var1 < 2; ++var1) {
               this.C9_f122.currentDialog.getChildById(var1 + 16).cleanUp();
               if (this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().renderer.spriteType = 2;
                  this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().renderer
                        .setSpriteIndex((int) 0);
                  this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().renderer.initializeSprite(336,
                        false, (byte) 0);
                  if (var1 == 0) {
                     this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().renderer
                           .setSpriteIndex((int) 8);
                  } else {
                     this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().renderer
                           .setSpriteIndex((int) 11);
                  }
               }
            }

            return;
         case 6:
            this.C9_f122.currentDialog.getChildById(19).setVisible(false);
            this.C9_f122.currentDialog.getChildById(19).cleanUp();
            if (this.C9_f122.currentDialog.getChildById(20).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(20).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(20).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(20).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(20).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               this.C9_f122.currentDialog.getChildById(20).getComponentData().renderer.setSpriteIndex((int) 12);
               return;
            }
            break;
         case 7:
            this.C9_f122.currentDialog.getChildById(20).setVisible(false);
            this.C9_f122.currentDialog.getChildById(20).cleanUp();
            return;
         case 8:
            for (var1 = 0; var1 < 2; ++var1) {
               if (this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().renderer.spriteType = 2;
                  this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().renderer
                        .setSpriteIndex((int) 0);
                  this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().renderer.initializeSprite(336,
                        false, (byte) 0);
                  if (var1 == 0) {
                     this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().renderer
                           .setSpriteIndex((int) 7);
                  } else {
                     this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().renderer
                           .setSpriteIndex((int) 13);
                  }
               }
            }

            return;
         case 9:
            this.C9_f122.currentDialog.getChildById(21).setVisible(false);
            this.C9_f122.currentDialog.getChildById(22).setVisible(false);

            for (var1 = 0; var1 < 2; ++var1) {
               this.C9_f122.currentDialog.getChildById(var1 + 21).cleanUp();
               if (this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().renderer.spriteType = 2;
                  this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().renderer
                        .setSpriteIndex((int) 0);
                  this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().renderer.initializeSprite(336,
                        false, (byte) 0);
                  if (var1 == 0) {
                     this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().renderer
                           .setSpriteIndex((int) 7);
                  } else {
                     this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().renderer
                           .setSpriteIndex((int) 14);
                  }
               }
            }

            return;
         case 10:
            this.C9_f122.currentDialog.getChildById(24).setVisible(false);
            this.C9_f122.currentDialog.getChildById(24).cleanUp();
            if (this.C9_f122.currentDialog.getChildById(25).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(25).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(25).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(25).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(25).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               this.C9_f122.currentDialog.getChildById(25).getComponentData().renderer.setSpriteIndex((int) 15);
               return;
            }
            break;
         case 11:
            this.C9_f122.currentDialog.getChildById(25).setVisible(false);
            this.C9_f122.currentDialog.getChildById(25).cleanUp();
            return;
         case 12:
            if (this.C9_f122.currentDialog.getChildById(26).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(26).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(26).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(26).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(26).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               this.C9_f122.currentDialog.getChildById(26).getComponentData().renderer.setSpriteIndex((int) 5);
               return;
            }
            break;
         case 13:
            this.C9_f122.currentDialog.getChildById(26).setVisible(false);
            this.C9_f122.currentDialog.getChildById(26).cleanUp();
            if (this.C9_f122.currentDialog.getChildById(27).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(27).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(27).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(27).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(27).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               this.C9_f122.currentDialog.getChildById(27).getComponentData().renderer.setSpriteIndex((int) 5);
               return;
            }
            break;
         case 14:
            return;
         case 15:
            if (this.C9_f122.currentDialog.getChildById(28).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(28).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(28).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(28).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(28).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               this.C9_f122.currentDialog.getChildById(28).getComponentData().renderer.setSpriteIndex((int) 6);
               return;
            }
            break;
         case 16:
            this.C9_f122.currentDialog.getChildById(28).setVisible(false);
            this.C9_f122.currentDialog.getChildById(28).cleanUp();
            if (this.C9_f122.currentDialog.getChildById(29).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(29).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(29).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(29).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(29).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               this.C9_f122.currentDialog.getChildById(29).getComponentData().renderer.setSpriteIndex((int) 6);
               return;
            }
            break;
         case 17:
            this.C9_f122.currentDialog.getChildById(29).setVisible(false);
            this.C9_f122.currentDialog.getChildById(29).cleanUp();
            if (this.C9_f122.currentDialog.getChildById(30).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(30).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(30).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(30).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(30).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               this.C9_f122.currentDialog.getChildById(30).getComponentData().renderer.setSpriteIndex((int) 6);
               return;
            }
            break;
         case 18:
         case 19:
            this.C9_f122.currentDialog.getChildById(30).setVisible(false);
            this.C9_f122.currentDialog.getChildById(30).cleanUp();
            if (this.C9_f122.currentDialog.getChildById(31).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(31).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(31).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(31).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(31).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               this.C9_f122.currentDialog.getChildById(31).getComponentData().renderer.setSpriteIndex((int) 6);
               return;
            }
            break;
         case 20:
            ScreenTransitionManager.a().c(16777215, 2);
            ScreenTransitionManager.a().C30_f482 = 85;
            return;
         case 21:
         case 22:
         case 23:
            this.C9_f122.currentDialog.getChildById(18).setVisible(false);
            this.C9_f122.currentDialog.getChildById(18).cleanUp();
            this.C9_f122.currentDialog.getChildById(23).setVisible(false);
            this.C9_f122.currentDialog.getChildById(23).cleanUp();
            this.C9_f122.currentDialog.getChildById(27).setVisible(false);
            this.C9_f122.currentDialog.getChildById(27).cleanUp();
            this.C9_f122.currentDialog.getChildById(31).setVisible(false);
            this.C9_f122.currentDialog.getChildById(31).cleanUp();
            this.C9_f122.currentDialog.getChildById(14).setVisible(false);
            this.C9_f122.currentDialog.getChildById(15).setVisible(false);
            return;
         case 24:
            ScreenTransitionManager.a().c(16777215, 1);
            ScreenTransitionManager.a().C30_f482 = 255;
            if (this.C9_f122.currentDialog.getChildById(32).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(32).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(32).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(32).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(32).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               return;
            }
            break;
         case 25:
            this.C9_f122.currentDialog.getChildById(32).setVisible(false);
            this.C9_f122.currentDialog.getChildById(32).cleanUp();

            for (var1 = 0; var1 < 5; ++var1) {
               if (this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().renderer.spriteType = 2;
                  this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().renderer.initializeSprite(336,
                        false, (byte) 0);
                  if (var1 == 0) {
                     this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                           .setSpriteIndex((int) 0);
                  } else if (var1 == 1) {
                     this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                           .setSpriteIndex((int) 8);
                  } else if (var1 == 2) {
                     this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                           .setSpriteIndex((int) 5);
                  } else if (var1 == 3) {
                     this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                           .setSpriteIndex((int) 7);
                  } else if (var1 == 4) {
                     this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                           .setSpriteIndex((int) 6);
                  }
               }
            }

            return;
         case 26:
            GameScreenManager.getInstance().backgroundEffect = ImageProcessor
                  .applyAlpha(GameScreenManager.getInstance().backgroundEffect, var2);
            return;
         case 27:
            if (var2 > 38) {
               this.C9_f122.currentDialog.getChildById(var2 - 1).setVisible(false);
               this.C9_f122.currentDialog.getChildById(var2 - 1).cleanUp();
            }

            if (this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer.setSpriteIndex((int) 4);
               this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
               return;
            }
            break;
         case 28:
            if (var2 > 43) {
               this.C9_f122.currentDialog.getChildById(var2 - 1).setVisible(false);
               this.C9_f122.currentDialog.getChildById(var2 - 1).cleanUp();
            }

            if (this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer.setSpriteIndex((int) 1);
               this.C9_f122.currentDialog.getChildById(var2).getComponentData().renderer.initializeSprite(336, false,
                     (byte) 0);
            }
      }

   }

   public final void x() {
      this.C9_f122.showDialog("/data/ui/help1.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
      this.C9_f127 = 0;
      this.C9_f122.currentDialog.getChildById(6).setVisible(true);
      this.C9_f122.currentDialog.getChildById(7).setVisible(false);
      this.e(this.C9_f127);
   }

   public final void y() {
      if (this.C9_f121.isKeyPressed(16400)) {
         --this.C9_f127;
         if (this.C9_f127 <= 0) {
            this.C9_f127 = 0;
         }

         this.e(this.C9_f127);
      } else if (this.C9_f121.isKeyPressed(32832)) {
         ++this.C9_f127;
         if (this.C9_f127 >= 2) {
            this.C9_f127 = 2;
         }

         this.e(this.C9_f127);
      } else {
         if (this.C9_f121.isKeyPressed(262144)) {
            this.C9_f121.changeState((byte) 13);
            this.C9_f122.removeDialog("/data/ui/help1.ui");
         }

      }
   }

   public final void z() {
      this.s();
      this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
   }

   public final void A() {
      if (this.C9_f121.isKeyPressed(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         GameScreenManager.getInstance().decreaseDifficulty();
         if (GameWorldManager.B().C25_f342 != null) {
            GameWorldManager.B().C25_f342.setMasterVolume(GameScreenManager.getInstance().difficultyLevel);
         }

         this.aW();
      } else if (this.C9_f121.isKeyPressed(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         GameScreenManager.getInstance().increaseDifficulty();
         if (GameWorldManager.B().C25_f342 != null) {
            GameWorldManager.B().C25_f342.setMasterVolume(GameScreenManager.getInstance().difficultyLevel);
         }

         this.aW();
      } else {
         if (this.C9_f121.isKeyPressed(131072)) {
            this.C9_f121.changeState((byte) 13);
            this.C9_f122.removeDialog("/data/ui/help.ui");
         }

      }
   }

   public final void B() {
      this.C9_f122.showDialog("/data/ui/petstate.ui", 257, this);
      this.C9_f131 = 0;
      if (this.C9_f123.pokemonStorage.size() > 6) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(-1);
      }

      this.C9_f122.currentDialog.getChildById(2).getComponentData().text = "Ngân hàng Sủng vật";
      this.C9_f122.currentDialog.getChildById(75).setVisible(false);
      this.C9_f122.currentDialog.getChildById(76).setVisible(false);
      this.aX();
   }

   private void aX() {
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = this.C9_f123.pokemonStorage.size();
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;
      if (this.C9_f123.pokemonStorage.size() >= 6) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.visibleItemCount = 6;
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.visibleItemCount = this.C9_f123.pokemonStorage.size();
      }

      if (this.C9_f136 >= this.C9_f123.pokemonStorage.size()) {
         this.C9_f136 = this.C9_f123.pokemonStorage.size() - 1;
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f136;
      }

      if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 5) {
         --this.C9_f135;
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.scrollOffset = this.C9_f135;
      }

      int var1;
      for (var1 = 0; var1 < 6; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.pokemonStorage.size()) {
            int[] var2 = (int[]) this.C9_f123.pokemonStorage.elementAt(this.C9_f135 + var1);
            if (var1 == 0) {
               this.C9_f122.currentDialog.getChildById(14 + var1 * 6).getComponentData().text = ""
                     + (this.C9_f135 + var1 + 1);
            } else {
               this.C9_f122.currentDialog.getChildById(15 + var1 * 6).getComponentData().text = ""
                     + (this.C9_f135 + var1 + 1);
            }

            this.C9_f122.currentDialog.getChildById(16 + var1 * 6).getComponentData().text = "#P"
                  + var2[6] * 100 / PokemonEntity.calculateStat(var2[0], var2[1], var2[4], 1);
            this.C9_f122.currentDialog.getChildById(17 + var1 * 6).getComponentData().text = "#P"
                  + PokemonEntity.calculateExpPercentStatic((short) var2[7], (short) var2[1]);
         } else {
            this.C9_f122.currentDialog.getChildById(16 + var1 * 6).getComponentData().text = "#P0";
            this.C9_f122.currentDialog.getChildById(17 + var1 * 6).getComponentData().text = "#P0";
         }
      }

      int[] var4 = null;
      if (this.C9_f123.pokemonStorage.size() > 0) {
         this.C9_f122.currentDialog.getChildById(64).setVisible(true);
         var4 = (int[]) this.C9_f123.pokemonStorage.elementAt(this.C9_f136);
      } else {
         this.C9_f122.currentDialog.getChildById(64).setVisible(false);
      }

      if (var4 != null) {
         if (this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer != null) {
            this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer.cleanup();
         } else {
            this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer.spriteType = 3;
         }

         this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer.initializeSprite(
               ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 17), false, (byte) -1);
         this.C9_f122.currentDialog.getChildById(51).getComponentData().text = GameEngineBase
               .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 0));
         this.C9_f122.currentDialog.getChildById(52).getComponentData().text = GameEngineBase
               .getLocalizedText(365 + ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 1));
         if (ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 19) == -1) {
            this.C9_f122.currentDialog.getChildById(62).getComponentData().text = "";
         } else if (ResourceManager.gameDatabase[0][ResourceManager.getDatabaseValue((byte) 0, (short) var4[0],
               (byte) 19)][2] != 1
               && ResourceManager.gameDatabase[0][ResourceManager.getDatabaseValue((byte) 0, (short) var4[0],
                     (byte) 19)][2] != 2) {
            if (ResourceManager.gameDatabase[0][ResourceManager.getDatabaseValue((byte) 0, (short) var4[0],
                  (byte) 19)][2] == 3) {
               this.C9_f122.currentDialog.getChildById(62).getComponentData().text = "Có thể dị hoá";
            }
         } else {
            this.C9_f122.currentDialog.getChildById(62).getComponentData().text = "Có thể tiến hóa";
         }

         this.C9_f122.currentDialog.getChildById(61).getComponentData().text = PokemonEntity.getTypeNameById(var4[0]);
         if (this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer.initializeSprite(258, false,
                  (byte) -1);
         }

         if (var4[2] != -1) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer
                  .setSpriteIndex((int) ResourceManager.gameDatabase[3][var4[2]][1]);
            this.C9_f122.currentDialog.getChildById(60).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.gameDatabase[3][var4[2]][0]);
         } else {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(60).getComponentData().text = "";
         }

         this.C9_f122.currentDialog.getChildById(65).getComponentData().text = "" + var4[1];
         this.C9_f122.currentDialog.getChildById(66).getComponentData().text = ""
               + PokemonEntity.calculateStat(var4[0], var4[1], var4[4], 2);
         this.C9_f122.currentDialog.getChildById(67).getComponentData().text = ""
               + PokemonEntity.calculateStat(var4[0], var4[1], var4[4], 3);
         this.C9_f122.currentDialog.getChildById(68).getComponentData().text = ""
               + PokemonEntity.calculateStat(var4[0], var4[1], var4[4], 4);
         int var5 = var4[4];
         var1 = ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 4) - 1;

         for (int var3 = 0; var3 < 5; ++var3) {
            this.C9_f122.currentDialog.getChildById(74 - var3).setVisible(true);
            this.C9_f122.currentDialog.getChildById(74 - var3).getComponentData().renderer.spriteType = 3;
            if (var3 > var1) {
               this.C9_f122.currentDialog.getChildById(74 - var3).setVisible(false);
            } else if (var5 > 0) {
               this.C9_f122.currentDialog.getChildById(74 - var3).getComponentData().renderer
                     .setAnimationFrame((byte) 14, (byte) -1);
               --var5;
            } else {
               this.C9_f122.currentDialog.getChildById(74 - var3).getComponentData().renderer
                     .setAnimationFrame((byte) 16, (byte) -1);
            }
         }

         if (this.C9_f125 == 1) {
            this.C9_f122.currentDialog.getChildById(64).getComponentData().text = "Lấy ra";
            return;
         }

         if (this.C9_f125 == 2) {
            this.C9_f122.currentDialog.getChildById(64).getComponentData().text = "Phóng sinh";
         }
      }

   }

   public final void C() {
      if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.aX();
      } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.aX();
      }

      int[] var1;
      if (this.C9_f131 == 0) {
         if (this.C9_f121.isKeyPressed(196640)) {
            if (this.C9_f125 == 1) {
               if (this.C9_f123.partySize >= 6) {
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Ba lô Sủng vật đã đủ", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 1;
               } else {
                  if (this.C9_f123.pokemonStorage.size() <= 0) {
                     return;
                  }

                  this.C9_f123.withdrawFromStorage(this.C9_f136);
                  if (this.C9_f123.pokemonStorage.size() <= 0) {
                     this.C9_f121.changeState((byte) 16);
                     this.C9_f122.removeDialog("/data/ui/petstate.ui");
                  } else {
                     this.aX();
                  }
               }
            } else if (this.C9_f125 == 2) {
               if (this.C9_f123.pokemonStorage.size() <= 0) {
                  return;
               }

               var1 = (int[]) this.C9_f123.pokemonStorage.elementAt(this.C9_f136);
               if (ResourceManager.getDatabaseValue((byte) 0, (short) var1[0], (byte) 22) == 2) {
                  this.C9_f131 = 2;
                  this.H();
                  this.a("Thần thú không thể phóng sinh", "Nhấn nút 5 tiếp tục");
               } else {
                  this.C9_f131 = 1;
                  this.C9_f122.showDialog("/data/ui/msgconfirm.ui", 257, this);
                  this.b("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
               }
            }
         } else if (this.C9_f121.isKeyPressed(786432)) {
            this.C9_f121.changeState((byte) 16);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }
      } else if (this.C9_f131 > 0) {
         if (this.C9_f121.isKeyPressed(196640) && this.C9_f125 == 1
               || this.C9_f121.isKeyPressed(131072) && this.C9_f125 == 2 && this.C9_f131 == 1
               || this.C9_f121.isKeyPressed(196640) && this.C9_f125 == 2 && this.C9_f131 == 2) {
            if (this.C9_f125 == 1) {
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.C9_f131 = 0;
            } else if (this.C9_f125 == 2) {
               if (this.C9_f131 == 1) {
                  this.C9_f122.removeDialog("/data/ui/msgconfirm.ui");
                  var1 = (int[]) this.C9_f123.pokemonStorage.elementAt(this.C9_f136);
                  this.C9_f123.deactivateSkill(var1[2]);
                  this.C9_f123.removeFromStorage(this.C9_f136);
                  this.aX();
               } else if (this.C9_f131 == 2) {
                  this.I();
               }

               this.C9_f131 = 0;
            }
         } else if (this.C9_f121.isKeyPressed(786432)) {
            if (this.C9_f125 == 1) {
               return;
            }

            this.C9_f122.removeDialog("/data/ui/msgconfirm.ui");
            this.C9_f131 = 0;
         }
      }

      this.C9_f132 = true;
   }

   public final void D() {
      this.aU();
      this.C9_f122.showDialog("/data/ui/shop.ui", 257, this);
      this.C9_f125 = 0;
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Ngân hàng Sủng vật";
      this.C9_f122.currentDialog.getChildById(6).getComponentData().text = "Gởi lại";
      this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Lấy ra";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "Phóng sinh";
   }

   public final void E() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.isKeyPressed(196640)) {
         switch (this.C9_f125) {
            case 0:
               this.C9_f126 = 0;
               this.C9_f121.changeState((byte) 7);
               this.C9_f122.removeDialog("/data/ui/shop.ui");
               return;
            case 1:
               this.C9_f121.changeState((byte) 15);
               this.C9_f122.removeDialog("/data/ui/shop.ui");
               return;
            case 2:
               this.C9_f121.changeState((byte) 15);
               this.C9_f122.removeDialog("/data/ui/shop.ui");
               return;
            case 3:
               QuestManager.isChangingState = true;
               this.C9_f122.removeDialog("/data/ui/shop.ui");
               this.C9_f121.changeState((byte) 0);
            default:
         }
      } else {
         if (this.C9_f121.isKeyPressed(262144)) {
            QuestManager.isChangingState = true;
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            this.C9_f121.changeState((byte) 0);
         }

      }
   }

   public final void F() {
      this.aU();
      this.C9_f122.showDialog("/data/ui/shop.ui", 257, this);
      this.C9_f125 = 0;
   }

   public final void G() {
      this.C9_f121.updateActionSequence();
      if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && !this.j() && this.C9_f131 == 0
            && this.C9_f121.isKeyPressed(4100) && this.aY()) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && !this.j() && this.C9_f131 == 0
            && this.C9_f121.isKeyPressed(8448) && this.aY()) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.aY() && !this.j() && GameEngineBase.isConfirmAllowed() && this.C9_f121.isKeyPressed(196640)) {
         if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0)) {
            return;
         }

         switch (this.C9_f125) {
            case 0:
               this.C9_f121.handleActionResponse();
               this.C9_f121.changeState((byte) 2);
               this.C9_f122.removeDialog("/data/ui/shop.ui");
               break;
            case 1:
               this.C9_f121.changeState((byte) 3);
               this.C9_f122.removeDialog("/data/ui/shop.ui");
               break;
            case 2:
               int var1;
               if (this.C9_f131 == 0) {
                  if (PlayerCharacter.getInstance().getMaxHealthDifference() == -1) {
                     this.C9_f131 = 6;
                     this.H();
                     this.a("Toàn bộ trạng thái đã đầy, không cần khôi phục", "Nhấn nút 5 tiếp tục");
                  } else if (!GameEngineBase.paymentActive) {
                     this.C9_f131 = 3;

                     for (var1 = 0; var1 < this.C9_f123.partySize; ++var1) {
                        this.C9_f123.partyPokemon[var1].fullRestore();
                     }

                     this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                  } else {
                     this.C9_f122.showDialog("/data/ui/msgRecover.ui", 257, this);
                     this.C9_f122.currentDialog.getChildById(4)
                           .getComponentData().text = "Có khôi phục trạng thái ba lô sủng vật không?";
                     this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Cần tiền tài: ";
                     this.C9_f122.currentDialog.getChildById(6).getComponentData().text = "" + PlayerCharacter.getInstance().getMaxHealthDifference();
                     this.C9_f122.currentDialog.getChildById(8).getComponentData().text = "" + PlayerCharacter.getInstance().getGold();
                     this.C9_f122.removeDialog("/data/ui/shop.ui");
                     this.C9_f131 = 1;
                  }
               } else if (this.C9_f131 == 1) {
                  var1 = PlayerCharacter.getInstance().getMaxHealthDifference();
                  if (!PlayerCharacter.getInstance().hasEnoughGold(var1)) {
                     this.C9_f131 = 2;
                     this.H();
                     this.a("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
                  } else {
                     this.C9_f131 = 3;
                     PlayerCharacter.getInstance().addGold(-var1);

                     for (var1 = 0; var1 < this.C9_f123.partySize; ++var1) {
                        this.C9_f123.partyPokemon[var1].fullRestore();
                     }

                     this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                  }

                  this.C9_f122.removeDialog("/data/ui/msgRecover.ui");
               } else {
                  if (this.C9_f131 == 2 && GameEngineBase.paymentActive) {
                     this.C9_f121.changeState((byte) 102);
                  }

                  this.C9_f131 = 0;
                  this.I();
               }
               break;
            case 3:
               QuestManager.isChangingState = true;
               this.C9_f122.removeDialog("/data/ui/shop.ui");
               this.C9_f121.changeState((byte) 0);
         }
      } else if (!this.j() && this.C9_f121.isKeyPressed(262144) && GameEngineBase.isCancelAllowed() && this.aY()) {
         if (this.C9_f131 == 1) {
            this.C9_f122.showDialog("/data/ui/shop.ui", 257, this);
            this.C9_f122.removeDialog("/data/ui/msgRecover.ui");
            this.C9_f125 = 0;
            this.C9_f131 = 0;
         } else if (this.C9_f131 == 0) {
            QuestManager.isChangingState = true;
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            this.C9_f121.changeState((byte) 0);
         }
      }

      if (this.C9_f131 == 3 && this.aA()) {
         this.C9_f131 = 4;
         this.C9_f122.showDialog("/data/ui/shop.ui", 257, this);
         this.K();
         this.a("Đang lưu...");
         this.M();
      } else if (this.C9_f131 == 4 && ((GameWorldManager) this.C9_f121).I()) {
         this.a("Lưu thành công");
         this.C9_f131 = 5;
      } else if (this.C9_f131 == 5) {
         this.L();
         this.C9_f131 = 0;
         this.C9_f125 = 0;
      }

      this.f();
      this.C9_f132 = true;
   }

   public final void a(int var1, byte var2) {
      this.C9_f122.showDialog("/data/ui/shopbuy.ui", 257, this);
      this.C9_f125 = 0;
      this.C9_f131 = 0;
      short[][] var10001 = ResourceManager.gameDatabase[var1];
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = var10001.length;
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Mua";
      if (this.C9_f121 instanceof GameWorldManager) {
         this.C9_f122.currentDialog.getChildById(57).setVisible(true);
         this.C9_f122.currentDialog.getChildById(58).setVisible(true);
         this.C9_f122.currentDialog.getChildById(57).getComponentData().text = "Mua sắm";
         this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "Quay lại";
         this.C9_f122.currentDialog.getChildById(39).setVisible(false);
         this.C9_f122.currentDialog.getChildById(40).setVisible(false);
      } else if (this.C9_f121 instanceof BattleSystemManager) {
         this.C9_f122.currentDialog.getChildById(57).setVisible(false);
         this.C9_f122.currentDialog.getChildById(58).setVisible(false);
         this.C9_f122.currentDialog.getChildById(39).setVisible(true);
         this.C9_f122.currentDialog.getChildById(40).setVisible(true);
         this.C9_f122.currentDialog.getChildById(39).getComponentData().text = "Mua sắm";
         this.C9_f122.currentDialog.getChildById(40).getComponentData().text = "Quay lại";
      }

      this.b(var1, var2);
   }

   private void b(int var1, byte var2) {
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;

      for (int var3 = 0; var3 < 5; ++var3) {
         if (this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().renderer.initializeSprite(258, false,
                  (byte) -1);
         }

         this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().renderer
               .setSpriteIndex((int) ResourceManager.gameDatabase[var1][this.C9_f135 + var3][1]);
         this.C9_f122.currentDialog.getChildById(14 + var3 * 5).getComponentData().text = GameEngineBase
               .getLocalizedText((int) ResourceManager.gameDatabase[var1][this.C9_f135 + var3][0]);
         if (this.C9_f121 instanceof GameWorldManager) {
            if (this.C9_f141 != 1 && this.C9_f141 != 3) {
               if (this.C9_f141 == 2) {
                  if (ResourceManager.gameDatabase[var1][this.C9_f135 + var3][4] == 0) {
                     this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                           + ResourceManager.gameDatabase[var1][this.C9_f135 + var3][3] * 3 / 2;
                  } else {
                     this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                           + ResourceManager.gameDatabase[var1][this.C9_f135 + var3][3];
                  }
               }
            } else {
               this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                     + ResourceManager.gameDatabase[var1][this.C9_f135 + var3][3];
            }
         } else if (var2 == 0 && var1 == 4 && this.C9_f135 + var3 == 0) {
            this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                  + ResourceManager.gameDatabase[var1][this.C9_f135 + var3][3];
         } else {
            this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                  + (ResourceManager.gameDatabase[var1][this.C9_f135 + var3][3] << 1);
         }

         if (ResourceManager.gameDatabase[var1][this.C9_f135 + var3][4] == 0) {
            this.C9_f122.currentDialog.getChildById(var3 + 45).getComponentData().renderer.setSpriteIndex((int) 84);
         } else if (ResourceManager.gameDatabase[var1][this.C9_f135 + var3][4] == 1) {
            this.C9_f122.currentDialog.getChildById(var3 + 45).getComponentData().renderer.setSpriteIndex((int) 83);
         } else if (ResourceManager.gameDatabase[var1][this.C9_f135 + var3][4] == 2) {
            this.C9_f122.currentDialog.getChildById(var3 + 45).getComponentData().renderer.setSpriteIndex((int) 74);
         }
      }

      this.C9_f122.currentDialog.getChildById(56).getComponentData().text = GameEngineBase
            .getLocalizedText((int) ResourceManager.gameDatabase[var1][this.C9_f136][2]);
      this.C9_f122.currentDialog.getChildById(43).getComponentData().text = "" + this.C9_f123.getBadges();
      this.C9_f122.currentDialog.getChildById(44).getComponentData().text = "" + this.C9_f123.getGold();
      this.C9_f122.currentDialog.getChildById(38).setOffsetY(
            102 + this.C9_f136 * 84 / ResourceManager.gameDatabase[var1].length,
            this.C9_f122.currentDialog.getUIContainerComponent());
   }

   public final void a(byte var1, byte var2) {
      this.C9_f121.updateActionSequence();
      if (!GameEngineBase.isActionBlocked(this.C9_f125, 0) && this.C9_f131 <= 1 && this.C9_f121.isKeyPressed(4100)
            && !this.j()) {
         this.C9_f122.currentDialog.handleAction(0);
         if (this.C9_f131 == 0) {
            this.b((int) var1, var2);
         }
      } else if (!GameEngineBase.isActionBlocked(this.C9_f125, 0) && this.C9_f131 <= 1
            && this.C9_f121.isKeyPressed(8448)
            && !this.j()) {
         this.C9_f122.currentDialog.handleAction(1);
         if (this.C9_f131 == 0) {
            this.b((int) var1, var2);
         }
      } else if (this.C9_f131 == 1 && this.C9_f121.isKeyPressed(16400) && this.C9_f126 > 0 && !this.j()) {
         --this.C9_f126;
         if (this.C9_f126 <= 0) {
            this.C9_f126 = 99 - this.C9_f123.getItemCount(this.C9_f136, var2);
         }

         this.a(this.C9_f126, this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3],
               ResourceManager.gameDatabase[var1][this.C9_f136][4], var1);
      } else if (this.C9_f131 == 1 && this.C9_f121.isKeyPressed(32832) && !this.j()) {
         ++this.C9_f126;
         if (this.C9_f126 > 99 - this.C9_f123.getItemCount(this.C9_f136, var2)) {
            this.C9_f126 = 1;
         }

         this.a(this.C9_f126, this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3],
               ResourceManager.gameDatabase[var1][this.C9_f136][4], var1);
      } else if (GameEngineBase.isConfirmAllowed() && this.C9_f121.isKeyPressed(196640) && !this.j()) {
         if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked(this.C9_f125, 0)) {
            return;
         }

         if (ResourceManager.gameDatabase[var1][this.C9_f136][4] == 2) {
            if (this.C9_f131 == 0) {
               if (GameEngineBase.paymentActive) {
                  if (!this.C9_f123.canAddItem((int) this.C9_f136, 1, (byte) 0)) {
                     this.C9_f131 = 3;
                     this.H();
                     this.a("Đạo cụ đã đủ", "Nhấn nút 5 tiếp tục");
                  } else {
                     this.C9_f121.changeState((byte) 101);
                  }
               } else {
                  this.C9_f131 = 3;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Công năng còn chưa mở", "Nhấn nút 5 tiếp tục");
               }
            } else {
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
            }
         } else if (var2 == 2 && this.C9_f136 < 12) {
            this.C9_f126 = 1;
            if (this.C9_f123.canAddItem(this.C9_f136, this.C9_f126, var2)) {
               if (this.C9_f131 == 0) {
                  this.C9_f127 = 0;
                  this.b(var1, var2);
               } else if (this.C9_f131 > 0) {
                  if (GameEngineBase.paymentActive) {
                     if (this.C9_f131 == 4) {
                        this.C9_f121.changeState((byte) 104);
                     } else if (this.C9_f131 == 3) {
                        this.C9_f121.changeState((byte) 102);
                     }
                  }

                  this.C9_f131 = 0;
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               }
            } else if (this.C9_f131 == 0) {
               this.C9_f131 = 2;
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Đạo cụ này đã đủ", "Nhấn nút 5 tiếp tục");
            } else {
               this.C9_f121.handleActionResponse();
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.b((int) var1, var2);
            }
         } else if (this.C9_f123.canAddItem(this.C9_f136, this.C9_f126, var2)) {
            if (this.C9_f131 == 0) {
               this.C9_f131 = 1;
               this.C9_f122.showDialog("/data/ui/msgyn.ui", 257, this);
               this.C9_f126 = 1;
               this.C9_f127 = 0;
               this.a(this.C9_f126, this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3],
                     ResourceManager.gameDatabase[var1][this.C9_f136][4], var1);
            } else if (this.C9_f131 == 1) {
               this.b(var1, var2);
            } else if (this.C9_f131 == 2) {
               GameWorldManager.B().C25_f348.updateQuestEffects();
               this.C9_f131 = 0;
               this.C9_f126 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.b((int) var1, var2);
            } else {
               if (GameEngineBase.paymentActive) {
                  if (this.C9_f131 == 4) {
                     this.C9_f122.removeDialog("/data/ui/msgyn.ui");
                     this.C9_f121.changeState((byte) 104);
                  } else if (this.C9_f131 == 3) {
                     this.C9_f122.removeDialog("/data/ui/msgyn.ui");
                     this.C9_f121.changeState((byte) 102);
                  }
               }

               this.C9_f131 = 0;
               this.C9_f126 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
            }
         } else if (this.C9_f131 == 0) {
            this.C9_f131 = 2;
            this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
            this.a("Đạo cụ này đã đủ", "Nhấn nút 5 tiếp tục");
         } else {
            this.C9_f131 = 0;
            this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
         }
      } else if (this.C9_f121.isKeyPressed(262144) && !this.j() && GameEngineBase.isCancelAllowed()) {
         if (this.C9_f131 == 0) {
            if (this.C9_f121 instanceof GameWorldManager) {
               if (this.C9_f141 == 1) {
                  this.C9_f121.changeState((byte) 1);
               } else if (this.C9_f141 == 2) {
                  this.C9_f121.changeState((byte) 14);
               } else if (this.C9_f141 == 3) {
                  this.C9_f121.changeState((byte) 27);
               }

               this.C9_f122.removeDialog("/data/ui/shopbuy.ui");
            } else {
               this.C9_f122.removeDialog("/data/ui/shopbuy.ui");
               this.C9_f121.changeState((byte) 20);
            }
         } else if (this.C9_f131 == 1) {
            this.C9_f131 = 0;
            this.C9_f126 = 0;
            this.C9_f122.removeDialog("/data/ui/msgyn.ui");
         }
      }

      this.f();
   }

   private void b(byte var1, byte var2) {
      if ((!(this.C9_f121 instanceof GameWorldManager) || this.C9_f141 != 1 && this.C9_f141 != 3
            || !this.C9_f123.canAfford((int) this.C9_f136,
                  (int) (this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3]), (int) var1))
            && (this.C9_f141 != 2 || (ResourceManager.gameDatabase[var1][this.C9_f136][4] != 0
                  || !this.C9_f123.canAfford((int) this.C9_f136,
                        (int) (this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3] * 3 / 2), (int) var1))
                  && (ResourceManager.gameDatabase[var1][this.C9_f136][4] == 0 || !this.C9_f123.canAfford((int) this.C9_f136,
                        (int) (this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3]), (int) var1)))) {
         if (this.C9_f121 instanceof BattleSystemManager && this.C9_f123.canAfford((int) this.C9_f136,
               (int) (this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3] << 1), (int) var1)) {
            if (this.C9_f127 == 0) {
               this.C9_f123.addItem(this.C9_f136, this.C9_f126, var2);
               if (ResourceManager.gameDatabase[var1][this.C9_f136][4] == 0) {
                  this.C9_f123.addGold(-this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3] << 1);
               } else {
                  this.C9_f123.addBadges(-this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3] << 1);
               }

               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               if (var1 == 3 && this.C9_f136 == 17) {
                  this.a("Đã thành công mua sắm #2"
                        + GameEngineBase.getLocalizedText(ResourceManager.gameDatabase[var1][this.C9_f136][0]) + " * "
                        + 5 * this.C9_f126, "Nhấn nút 5 tiếp tục");
               } else {
                  this.a("Đã thành công mua sắm #2"
                        + GameEngineBase.getLocalizedText(ResourceManager.gameDatabase[var1][this.C9_f136][0]) + " * "
                        + this.C9_f126, "Nhấn nút 5 tiếp tục");
               }

               this.C9_f131 = 2;
               this.C9_f126 = 1;
            } else {
               this.C9_f131 = 0;
            }

            this.C9_f122.removeDialog("/data/ui/msgyn.ui");
         } else {
            if (this.C9_f127 == 0) {
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               if (ResourceManager.gameDatabase[var1][this.C9_f136][4] == 0) {
                  this.C9_f131 = 3;
                  this.a("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
               } else {
                  this.C9_f131 = 4;
                  this.a("Số lượng Huy hiệu chưa đủ", "Nhấn nút 5 tiếp tục");
               }

               int var3;
               if (this.C9_f121 instanceof GameWorldManager) {
                  label133: {
                     if (this.C9_f141 != 1 && this.C9_f141 != 3) {
                        if (this.C9_f141 != 2) {
                           break label133;
                        }

                        if (ResourceManager.gameDatabase[var1][this.C9_f136][4] == 0) {
                           var3 = this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3] * 3 / 2;
                        } else {
                           var3 = this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3];
                        }
                     } else {
                        var3 = this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3];
                     }

                     this.a(new int[] { var1, var2, this.C9_f136, ResourceManager.gameDatabase[var1][this.C9_f136][4],
                           var3, this.C9_f126 });
                  }
               } else if (this.C9_f121 instanceof BattleSystemManager) {
                  var3 = this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3] << 1;
                  this.a(new int[] { var1, var2, this.C9_f136, ResourceManager.gameDatabase[var1][this.C9_f136][4],
                        var3, this.C9_f126 });
               }
            } else {
               this.C9_f131 = 0;
            }

            this.C9_f122.removeDialog("/data/ui/msgyn.ui");
         }
      } else {
         if (this.C9_f127 == 0) {
            this.C9_f123.addItem(this.C9_f136, this.C9_f126, var2);
            if (ResourceManager.gameDatabase[var1][this.C9_f136][4] == 0) {
               if (this.C9_f141 != 1 && this.C9_f141 != 3) {
                  if (this.C9_f141 == 2) {
                     this.C9_f123.addGold(-this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3] * 3 / 2);
                  }
               } else {
                  this.C9_f123.addGold(-this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3]);
               }
            } else if (this.C9_f141 != 1 && this.C9_f141 != 3) {
               if (this.C9_f141 == 2) {
                  this.C9_f123.addBadges(-this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3]);
               }
            } else {
               this.C9_f123.addBadges(-this.C9_f126 * ResourceManager.gameDatabase[var1][this.C9_f136][3]);
            }

            this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
            if (var1 == 3 && this.C9_f136 == 17) {
               this.a("Đã thành công mua sắm #2"
                     + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[var1][this.C9_f136][0])
                     + " * " + 5 * this.C9_f126, "Nhấn nút 5 tiếp tục");
            } else {
               this.a("Đã thành công mua sắm #2"
                     + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[var1][this.C9_f136][0])
                     + " * " + this.C9_f126, "Nhấn nút 5 tiếp tục");
            }

            this.C9_f131 = 2;
            this.C9_f126 = 1;
         } else {
            this.C9_f131 = 0;
         }

         this.C9_f122.removeDialog("/data/ui/msgyn.ui");
      }
   }

   private void a(int[] var1) {
      if (this.C9_f144 == null) {
         this.C9_f144 = new Vector();
      } else {
         this.C9_f144.removeAllElements();
      }

      this.C9_f144.addElement(var1);
   }

   private void a(int var1, int var2, int var3, int var4) {
      if (var4 == 3 && this.C9_f136 == 17) {
         this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "" + var1 * 5;
      } else {
         this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "" + var1;
      }

      if (this.C9_f121 instanceof GameWorldManager) {
         if (this.C9_f141 != 1 && this.C9_f141 != 3) {
            if (this.C9_f141 == 2) {
               if (var3 == 0) {
                  this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "" + var2 * 3 / 2;
               } else {
                  this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "" + var2;
               }
            }
         } else {
            this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "" + var2;
         }
      } else {
         this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "" + (var2 << 1);
      }

      if (var3 == 0) {
         this.C9_f122.currentDialog.getChildById(12).getComponentData().renderer.setSpriteIndex((int) 84);
      } else {
         if (var3 == 1) {
            this.C9_f122.currentDialog.getChildById(12).getComponentData().renderer.setSpriteIndex((int) 83);
         }

      }
   }

   public final void H() {
      this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
   }

   public final void I() {
      this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
   }

   public final boolean J() {
      return !this.C9_f122.isTopDialog("/data/ui/msgwarm.ui");
   }

   public final void a(String var1, String var2) {
      this.C9_f122.currentDialog.getChildById(6).getComponentData().text = var2;
      this.C9_f122.currentDialog.getChildById(7).getComponentData().text = var1;
   }

   public final void K() {
      this.C9_f122.showDialog("/data/ui/msgtip.ui", 257, this);
   }

   public final void L() {
      this.C9_f122.removeDialog("/data/ui/msgtip.ui");
   }

   private boolean aY() {
      return !this.C9_f122.isTopDialog("/data/ui/msgtip.ui");
   }

   public final void a(String var1) {
      this.C9_f122.currentDialog.getChildById(2).getComponentData().text = var1;
   }

   public final void M() {
      this.C9_f122.currentDialog.getChildById(3).setVisible(false);
      this.C9_f122.currentDialog.getChildById(4).setVisible(false);
   }

   public final void N() {
      if (this.C9_f131 == 0) {
         if (this.C9_f121.isKeyPressed(196640)) {
            this.C9_f131 = 1;
            this.a("Đang lưu...");
            this.M();
            return;
         }

         if (this.C9_f121.isKeyPressed(262144)) {
            if (GameEngineBase.paymentActive) {
               this.C9_f125 = 5;
            } else {
               this.C9_f125 = 4;
            }

            this.C9_f121.changeState((byte) 6);
            this.C9_f122.removeDialog("/data/ui/msgtip.ui");
            this.C9_f131 = 0;
            return;
         }
      } else if (this.C9_f131 == 1) {
         if (((GameWorldManager) this.C9_f121).I()) {
            this.a("Lưu thành công");
            this.C9_f131 = 2;
            return;
         }
      } else if (this.C9_f131 == 2) {
         this.C9_f122.removeDialog("/data/ui/msgtip.ui");
         this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
         this.C9_f121.changeState((byte) 0);
         this.C9_f131 = 0;
      }

   }

   private void b(String var1, String var2) {
      this.C9_f122.currentDialog.getChildById(2).getComponentData().text = var2;
      this.C9_f122.currentDialog.getChildById(4).getComponentData().text = var1;
   }

   public final void O() {
      this.C9_f122.showDialog("/data/ui/shopbuy.ui", 257, this);
      this.C9_f125 = 0;
      this.C9_f131 = 0;
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Bán ra";
      this.C9_f122.currentDialog.getChildById(39).getComponentData().text = "";
      this.C9_f122.currentDialog.getChildById(40).getComponentData().text = "";
      this.C9_f122.currentDialog.getChildById(57).getComponentData().text = "Bán đi";
      this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "Quay lại";
      this.C9_f123.refreshUsableItems();
      this.aZ();
   }

   private void aZ() {
      if (this.C9_f123.usableItems.size() > 5) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
      }

      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = this.C9_f123.usableItems.size();
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;
      if (this.C9_f136 >= this.C9_f123.usableItems.size()) {
         this.C9_f136 = this.C9_f123.usableItems.size() - 1;
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f136;
      }

      if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
         --this.C9_f135;
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.scrollOffset = this.C9_f135;
      }

      for (int var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.usableItems.size()) {
            int var2 = ((int[]) this.C9_f123.usableItems.elementAt(this.C9_f135 + var1))[0];
            if (this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().renderer.initializeSprite(258,
                     false, (byte) -1);
            }

            this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().renderer
                  .setSpriteIndex((int) ResourceManager.gameDatabase[4][var2][1]);
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.gameDatabase[4][var2][0]);
            this.C9_f122.currentDialog.getChildById(15 + var1 * 5).getComponentData().text = ""
                  + ResourceManager.gameDatabase[4][var2][3] / 2;
            if (ResourceManager.gameDatabase[4][var2][4] == 0) {
               this.C9_f122.currentDialog.getChildById(var1 + 45).getComponentData().renderer.setSpriteIndex((int) 84);
            } else if (ResourceManager.gameDatabase[4][var2][4] == 1) {
               this.C9_f122.currentDialog.getChildById(var1 + 45).getComponentData().renderer.setSpriteIndex((int) 83);
            } else if (ResourceManager.gameDatabase[4][var2][4] == 2) {
               this.C9_f122.currentDialog.getChildById(var1 + 45).getComponentData().renderer.setSpriteIndex((int) 74);
            }
         } else {
            if (this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().renderer != null) {
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().renderer.cleanup();
            }

            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
            this.C9_f122.currentDialog.getChildById(15 + var1 * 5).getComponentData().text = "";
            this.C9_f122.currentDialog.getChildById(var1 + 45).getComponentData().renderer.setSpriteIndex((int) 86);
         }
      }

      if (!this.C9_f123.usableItems.isEmpty()) {
         this.C9_f122.currentDialog.getChildById(56).getComponentData().text = GameEngineBase.getLocalizedText(
               ResourceManager.gameDatabase[4][((int[]) this.C9_f123.usableItems.elementAt(this.C9_f136))[0]][2]);
      } else {
         this.C9_f122.currentDialog.getChildById(56).getComponentData().text = "";
      }

      if (!this.C9_f123.usableItems.isEmpty()) {
         this.C9_f122.currentDialog.getChildById(43).getComponentData().text = "" + this.C9_f123.getBadges();
         this.C9_f122.currentDialog.getChildById(44).getComponentData().text = "" + this.C9_f123.getGold();
         this.C9_f122.currentDialog.getChildById(38).setOffsetY(102 + this.C9_f136 * 84 / this.C9_f123.usableItems.size(),
               this.C9_f122.currentDialog.getUIContainerComponent());
      }
   }

   public final void P() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else {
         int[] var1;
         if (this.C9_f131 == 1 && this.C9_f121.isKeyPressed(16400) && this.C9_f126 > 0) {
            var1 = (int[]) this.C9_f123.usableItems.elementAt(this.C9_f136);
            --this.C9_f126;
            if (this.C9_f126 <= 0) {
               this.C9_f126 = this.C9_f123.getItemCount((int) var1[0], (byte) 0);
            }

            this.a(this.C9_f126, this.C9_f126 * ResourceManager.gameDatabase[4][var1[0]][3] / 2,
                  ResourceManager.gameDatabase[4][var1[0]][4], 4);
         } else if (this.C9_f131 == 1 && this.C9_f121.isKeyPressed(32832)) {
            var1 = (int[]) this.C9_f123.usableItems.elementAt(this.C9_f136);
            ++this.C9_f126;
            if (this.C9_f126 > this.C9_f123.getItemCount(var1[0], (byte) 0)) {
               this.C9_f126 = 1;
            }

            this.a(this.C9_f126, this.C9_f126 * ResourceManager.gameDatabase[4][var1[0]][3] / 2,
                  ResourceManager.gameDatabase[4][var1[0]][4], 4);
         } else {
            if (this.C9_f121.isKeyPressed(196640) && !this.C9_f123.usableItems.isEmpty()) {
               var1 = (int[]) this.C9_f123.usableItems.elementAt(this.C9_f136);
               if (this.C9_f131 == 0) {
                  this.C9_f131 = 1;
                  this.C9_f122.showDialog("/data/ui/msgyn.ui", 257, this);
                  this.C9_f126 = 1;
                  this.C9_f127 = 0;
                  this.a(this.C9_f126, this.C9_f126 * ResourceManager.gameDatabase[4][var1[0]][3] / 2,
                        ResourceManager.gameDatabase[4][var1[0]][4], 4);
                  return;
               }

               if (this.C9_f127 != 0) {
                  this.C9_f122.removeDialog("/data/ui/msgyn.ui");
                  this.C9_f131 = 0;
                  return;
               }

               this.C9_f123.removeItem(var1[0], this.C9_f126, (byte) 0);
               this.C9_f123.addGold(this.C9_f126 * ResourceManager.gameDatabase[4][var1[0]][3] / 2);
            } else {
               if (!this.C9_f121.isKeyPressed(262144)) {
                  return;
               }

               if (this.C9_f131 == 0) {
                  this.C9_f121.changeState((byte) 1);
                  this.C9_f122.removeDialog("/data/ui/shopbuy.ui");
                  this.C9_f125 = 1;
                  ((UIContainerComponent) this.C9_f122.currentDialog
                        .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f125;
                  return;
               }
            }

            this.C9_f131 = 0;
            this.C9_f122.removeDialog("/data/ui/msgyn.ui");
            this.C9_f123.refreshUsableItems();
            this.aZ();
         }
      }
   }

   public final void Q() {
      this.C9_f122.showDialog("/data/ui/record.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
      this.C9_f122.currentDialog.getChildById(14).getComponentData().text = ""
            + (this.C9_f123.partySize + this.C9_f123.pokemonStorage.size());
      this.C9_f122.currentDialog.getChildById(17).getComponentData().text = "" + this.C9_f123.totalCreaturesSeen;
      this.C9_f122.currentDialog.getChildById(20).getComponentData().text = "" + this.C9_f123.commonCreaturesSeen;
      this.C9_f122.currentDialog.getChildById(26).getComponentData().text = "" + this.C9_f123.rareCreaturesSeen;
      int var1 = 0;

      for (byte var2 = 0; var2 < this.C9_f123.badgeStates.length; ++var2) {
         if (this.C9_f123.getBadgeState(var2, (byte) 0) == 2) {
            ++var1;
         }
      }

      this.C9_f122.currentDialog.getChildById(29).getComponentData().text = "" + var1;
      long var4 = GameScreenManager.getInstance().storyStartTime + GameScreenManager.getInstance().worldMapTime
            - GameScreenManager.getInstance().currentTime;
      ComponentData var10000 = this.C9_f122.currentDialog.getChildById(31).getComponentData();
      GameWorldManager.B();
      var10000.text = GameWorldManager.a(var4)[1];
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).secondaryListComponent.selectedIndex = this.C9_f126;
      this.C9_f125 = 0;
      this.C9_f131 = 0;
      this.C9_f132 = true;
   }

   public final void R() {
      if (this.C9_f121.isKeyPressed(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.C9_f132 = true;
      } else if (this.C9_f121.isKeyPressed(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.C9_f132 = true;
      } else if (this.C9_f121.isKeyPressed(196640)) {
         if (this.C9_f131 == 0) {
            switch (this.C9_f126) {
               case 0:
                  if (PlayerCharacter.getInstance().isSkillActive(5)) {
                     this.C9_f121.changeState((byte) 11);
                  } else {
                     this.H();
                     this.a("Không đạt được sủng vật sách tranh đạo cụ", "Nhấn nút 5 tiếp tục");
                     this.C9_f131 = 1;
                  }
                  break;
               case 1:
                  this.C9_f121.changeState((byte) 12);
            }
         } else {
            this.C9_f131 = 0;
            this.I();
         }
      } else if (this.C9_f121.isKeyPressed(262144) && this.C9_f131 == 0) {
         if (GameEngineBase.paymentActive) {
            this.C9_f125 = 3;
         } else {
            this.C9_f125 = 2;
         }

         this.C9_f121.changeState((byte) 6);
         this.C9_f122.removeDialog("/data/ui/record.ui");
      }

      this.C9_f132 = true;
   }

   public final void S() {
      this.C9_f122.showDialog("/data/ui/petmap.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/record.ui");
      this.C9_f125 = 0;
      this.C9_f126 = 0;
      this.C9_f131 = 0;
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
      this.bb();
      this.C9_f132 = true;
   }

   private void ba() {
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.scrollOffset = 0;
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.selectedIndex = 0;
   }

   private void bb() {
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = this.C9_f123.categorySizes[this.C9_f125];
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;
      short var1 = ResourceManager.gameDatabase[0][this.C9_f123.categoryOffsets[this.C9_f125] + this.C9_f136][17];
      if (this.C9_f123.getCreatureStatus((byte) this.C9_f125, this.C9_f136 + this.C9_f123.categoryOffsets[this.C9_f125]) > 0) {
         this.C9_f122.currentDialog.getChildById(21).setVisible(true);
         if (this.C9_f122.currentDialog.getChildById(21).getComponentData().renderer != null) {
            this.C9_f122.currentDialog.getChildById(21).getComponentData().renderer.cleanup();
         } else {
            this.C9_f122.currentDialog.getChildById(21).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(21).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(21).getComponentData().renderer.spriteType = 3;
         }

         this.C9_f122.currentDialog.getChildById(21).getComponentData().renderer.initializeSprite(var1, false,
               (byte) -1);
         this.C9_f122.currentDialog.getChildById(21).getComponentData().renderer.setSpriteState((byte) 1);
      } else {
         this.C9_f122.currentDialog.getChildById(21).setVisible(false);
      }

      int var3 = 0;

      int var2;
      for (var2 = 0; var2 < this.C9_f123.categorySizes[this.C9_f125]; ++var2) {
         if (this.C9_f123.getCreatureStatus((byte) this.C9_f125, this.C9_f123.categoryOffsets[this.C9_f125] + var2) == 2) {
            ++var3;
         }
      }

      for (var2 = 0; var2 < 5; ++var2) {
         if (this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().renderer.setSpriteIndex((int) 102);
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().renderer.initializeSprite(257, false,
                  (byte) -1);
         }

         if (this.C9_f123.getCreatureStatus((byte) this.C9_f125, var2 + this.C9_f135 + this.C9_f123.categoryOffsets[this.C9_f125]) == 2) {
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().renderer.setSpriteIndex((int) 101);
         } else {
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().renderer.setSpriteIndex((int) 102);
         }

         this.C9_f122.currentDialog.getChildById(24 + (var2 << 2) + 3).getComponentData().text = GameEngineBase
               .getLocalizedText((int) ResourceManager.gameDatabase[0][this.C9_f123.categoryOffsets[this.C9_f125] + var2
                     + this.C9_f135][0]);
      }

      this.C9_f122.currentDialog.getChildById(20).getComponentData().text = GameEngineBase
            .getLocalizedText(365 + this.C9_f125) + var3 + "/" + this.C9_f123.categorySizes[this.C9_f125];
      this.C9_f122.currentDialog.getChildById(23).setOffsetY(
            99 + (this.C9_f136 << 6) / this.C9_f123.categorySizes[this.C9_f125],
            this.C9_f122.currentDialog.getUIContainerComponent());
   }

   public final void T() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bb();
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bb();
      } else if (this.C9_f121.isKeyPressed(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.ba();
         this.bb();
      } else if (this.C9_f121.isKeyPressed(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.ba();
         this.bb();
      } else if (!this.C9_f121.isKeyPressed(196640) && this.C9_f121.isKeyPressed(786432)) {
         if (this.C9_f121.previousState == 8) {
            this.C9_f121.changeState((byte) 8);
         } else {
            this.C9_f126 = 0;
            this.C9_f121.changeState((byte) 9);
         }

         this.C9_f122.removeDialog("/data/ui/petmap.ui");
      }

      this.C9_f132 = true;
   }

   public final void U() {
      this.C9_f122.showDialog("/data/ui/task.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).secondaryListComponent.selectedIndex = this.C9_f125;
      this.C9_f126 = 0;
      this.C9_f127 = 0;
      this.bc();
      this.bd();
   }

   private void bc() {
      int var1;
      int var2;
      switch (this.C9_f125) {
         case 0:
            if (QuestManager.questDialogState >= QuestManager.questDescriptions.length / 2 - 1) {
               int var10001 = QuestManager.questDescriptions.length;
               ((UIContainerComponent) this.C9_f122.currentDialog
                     .getChildById(0)).primaryListComponent.totalItemCount = var10001 / 2;
               ((UIContainerComponent) this.C9_f122.currentDialog
                     .getChildById(0)).primaryListComponent.selectedIndex = QuestManager.questDescriptions.length / 2
                           - 1;
            } else {
               ((UIContainerComponent) this.C9_f122.currentDialog
                     .getChildById(0)).primaryListComponent.totalItemCount = QuestManager.questDialogState + 1;
               ((UIContainerComponent) this.C9_f122.currentDialog
                     .getChildById(0)).primaryListComponent.selectedIndex = QuestManager.questDialogState;
            }

            this.C9_f122.currentDialog.getChildById(36).getComponentData().text = "";
            this.C9_f136 = QuestManager.questDialogState;
            this.C9_f135 = QuestManager.questDialogState - 4;
            if (this.C9_f136 <= 0) {
               this.C9_f136 = 0;
            }

            if (this.C9_f135 <= 0) {
               this.C9_f135 = 0;
            }

            ((UIContainerComponent) this.C9_f122.currentDialog
                  .getChildById(0)).primaryListComponent.scrollOffset = this.C9_f135;
            this.C9_f122.currentDialog.getChildById(37).getComponentData().text = "Đầu mối chính hoàn thành độ: ";
            if (QuestManager.questDialogState >= QuestManager.questDescriptions.length / 2) {
               this.C9_f122.currentDialog.getChildById(38)
                     .getComponentData().text = QuestManager.questDescriptions[QuestManager.questDescriptions.length
                           - 1];
            } else {
               this.C9_f122.currentDialog.getChildById(38)
                     .getComponentData().text = QuestManager.questDescriptions[QuestManager.questDescriptions.length / 2
                           + QuestManager.questDialogState];
            }

            var1 = QuestManager.questDialogState * 1000 / (QuestManager.questDescriptions.length / 2);
            if (!GameEngineBase.paymentActive) {
               if ((var2 = var1 % 10) == 0) {
                  var2 = 1;
               }

               this.C9_f122.currentDialog.getChildById(38).getComponentData().text = var1 / 50 + "." + var2 + "%";
            } else {
               this.C9_f122.currentDialog.getChildById(38).getComponentData().text = var1 / 10 + "." + var1 % 10 + "%";
            }

            if (QuestManager.questDialogState > 4) {
               ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent
                     .setDisplayMode(1);
            } else {
               ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent
                     .setDisplayMode(0);
            }

            this.C9_f122.currentDialog.getChildById(8).getComponentData().focusedTextColor = 11290624;
            break;
         case 1:
            ((UIContainerComponent) this.C9_f122.currentDialog
                  .getChildById(0)).primaryListComponent.totalItemCount = QuestManager.questFlagCount;
            ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.selectedIndex = 0;
            ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.scrollOffset = 0;
            this.C9_f122.currentDialog.getChildById(36).getComponentData().text = "";
            this.C9_f122.currentDialog.getChildById(37).getComponentData().text = "Chi nhánh hoàn thành độ: ";
            var1 = 0;

            for (var2 = 0; var2 < QuestManager.questFlags.length; ++var2) {
               if (QuestManager.questFlags[var2][1] == 3) {
                  ++var1;
               }
            }

            var2 = var1 * 1000 / (QuestManager.questNames.length / 2);
            this.C9_f122.currentDialog.getChildById(38).getComponentData().text = var2 / 10 + "." + var2 % 10 + "%";
            if (QuestManager.questFlagCount > 5) {
               ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent
                     .setDisplayMode(1);
            } else {
               ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent
                     .setDisplayMode(0);
            }

            this.C9_f122.currentDialog.getChildById(9).getComponentData().focusedTextColor = 11290624;
      }

      this.bd();
   }

   private void bd() {
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;

      for (int var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f125 == 0) {
            if (QuestManager.questDialogState > 0) {
               if (this.C9_f135 + var1 < QuestManager.questDialogState) {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = ""
                        + (var1 + this.C9_f135 + 1);
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3)
                        .getComponentData().text = QuestManager.questDescriptions[this.C9_f135 + var1];
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "Hoàn thành";
               } else if (this.C9_f135 + var1 == QuestManager.questDialogState
                     && this.C9_f135 + var1 <= QuestManager.questDescriptions.length / 2 - 1) {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = ""
                        + (var1 + this.C9_f135 + 1);
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3)
                        .getComponentData().text = QuestManager.questDescriptions[this.C9_f135 + var1];
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "";
               } else {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = "";
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3).getComponentData().text = "";
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "";
                  this.C9_f122.currentDialog.getChildById(36).getComponentData().text = "";
               }
            } else {
               this.C9_f122.currentDialog.getChildById(12).getComponentData().text = "1";
               this.C9_f122.currentDialog.getChildById(13).getComponentData().text = QuestManager.questDescriptions[0];
               this.C9_f122.currentDialog.getChildById(14).getComponentData().text = "";
            }
         } else if (this.C9_f125 == 1) {
            if (this.C9_f135 + var1 < QuestManager.questFlagCount) {
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = ""
                     + (var1 + this.C9_f135 + 1);
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3)
                     .getComponentData().text = QuestManager.questNames[QuestManager.questFlags[this.C9_f135
                           + var1][0]];
               if (QuestManager.questFlags[this.C9_f135 + var1][1] == 3) {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "Hoàn thành";
               } else {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "";
               }
            } else {
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = "";
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3).getComponentData().text = "";
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "";
            }
         }
      }

      switch (this.C9_f125) {
         case 0:
            this.C9_f122.currentDialog.getChildById(36)
                  .getComponentData().text = QuestManager.questDescriptions[QuestManager.questDescriptions.length / 2
                        + this.C9_f136];
            break;
         case 1:
            if (QuestManager.questFlagCount > 0) {
               this.C9_f122.currentDialog.getChildById(36)
                     .getComponentData().text = QuestManager.questNames[QuestManager.questNames.length / 2
                           + QuestManager.questFlags[this.C9_f136][0]];
            }
      }

      if (((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.totalItemCount > 0) {
         this.C9_f122.currentDialog.getChildById(40).setOffsetY(
               104 + (this.C9_f136 << 6) / ((UIContainerComponent) this.C9_f122.currentDialog
                     .getChildById(0)).primaryListComponent.totalItemCount,
               this.C9_f122.currentDialog.getUIContainerComponent());
      }

   }

   public final void V() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bd();
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bd();
      } else if (this.C9_f121.isKeyPressed(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.bc();
      } else if (this.C9_f121.isKeyPressed(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.bc();
      } else if (this.C9_f121.isKeyPressed(983072)) {
         if (GameEngineBase.paymentActive) {
            this.C9_f125 = 4;
         } else {
            this.C9_f125 = 3;
         }

         this.C9_f122.removeDialog("/data/ui/task.ui");
         if (this.C9_f121.previousState == 0) {
            this.C9_f125 = 0;
            this.C9_f121.changeState((byte) 0);
         } else if (this.C9_f121.previousState == 33) {
            this.C9_f121.changeState((byte) 33);
         } else {
            this.C9_f121.changeState((byte) 6);
         }
      } else {
         if (this.C9_f121.isKeyPressed(10)) {
            this.C9_f122.removeDialog("/data/ui/task.ui");
            this.C9_f121.changeState((byte) 0);
         }

      }
   }

   public final void W() {
      this.C9_f122.showDialog("/data/ui/badge.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/record.ui");
      this.C9_f125 = 0;
      this.C9_f131 = 0;

      for (int var1 = 0; var1 < 8; ++var1) {
         if (this.C9_f123.badgeStates[var1][0] != 0) {
            this.C9_f122.currentDialog.getChildById(var1 + 25).getComponentData().renderer.setSpriteIndex(var1 + 46);
         }
      }

      this.be();
   }

   private void be() {
      this.C9_f122.currentDialog.getChildById(13).getComponentData().text = GameEngineBase
            .getLocalizedText((int) ResourceManager.gameDatabase[2][this.C9_f125][0]);
      this.C9_f122.currentDialog.getChildById(14).getComponentData().text = GameEngineBase.getLocalizedText(
            (int) ResourceManager.gameDatabase[2][this.C9_f125][2 + this.C9_f123.getBadgeState((byte) this.C9_f125, (byte) 1)]);
      if (this.C9_f123.getBadgeState((byte) this.C9_f125, (byte) 0) == 0) {
         this.C9_f122.currentDialog.getChildById(16).getComponentData().text = "Chưa đạt";
      } else {
         this.C9_f122.currentDialog.getChildById(16).getComponentData().text = "Đã đạt được";
         this.C9_f123.getBadgeState((byte) this.C9_f125, (byte) 1);
         this.C9_f122.currentDialog.getChildById(33).getComponentData().text = "";
      }
   }

   public final void X() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.be();
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.be();
      } else if (this.C9_f121.isKeyPressed(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.be();
      } else if (this.C9_f121.isKeyPressed(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.be();
      } else {
         if (this.C9_f121.isKeyPressed(786432)) {
            if (this.C9_f121.previousState == 8) {
               this.C9_f121.changeState((byte) 8);
            } else {
               this.C9_f126 = 1;
               this.C9_f121.changeState((byte) 9);
            }

            this.C9_f122.removeDialog("/data/ui/badge.ui");
         }

      }
   }

   public final void a(int var1) {
      this.C9_f122.showDialog("/data/ui/smsTip.ui", 257, this);
      if (this.C9_f122.currentDialog.getChildById(6).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(6).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(6).getComponentData().renderer.spriteType = 2;
         this.C9_f122.currentDialog.getChildById(6).getComponentData().renderer.setSpriteIndex((int) -1);
         this.C9_f122.currentDialog.getChildById(6).getComponentData().renderer.initializeSprite(257, false, (byte) -1);
         this.C9_f122.currentDialog.getChildById(6).getComponentData().renderer.setSpriteIndex(var1 + 46);
      }

      this.C9_f122.currentDialog.getChildById(7).getComponentData().text = GameEngineBase.getLocalizedText(var1 + 187)
            + ":" + GameEngineBase.getLocalizedText(var1 + 195);
      this.C9_f122.currentDialog.getChildById(8).getComponentData().text = GameEngineBase.getLocalizedText((int) 377);
   }

   public final void Y() {
      this.C9_f122.removeDialog("/data/ui/smsTip.ui");
   }

   public final void Z() {
      this.C9_f125 = 0;
      this.f(this.C9_f126);
   }

   private void f(int var1) {
      PokemonEntity[] var2 = this.C9_f123.partyPokemon;
      DialogUIManager var5 = this;
      this.C9_f122.showDialog("/data/ui/petstate.ui", 257, this);
      this.g(var1);
      this.C9_f131 = 0;
      int var4;
      if (this.C9_f121 instanceof GameWorldManager) {
         for (var4 = 0; var4 < 6; ++var4) {
            if (var2[var4] != null) {
               var5.C9_f122.currentDialog.getChildById(16 + var4 * 6).getComponentData().text = "#P" + var2[var4].getHpPercent();
               var5.C9_f122.currentDialog.getChildById(17 + var4 * 6).getComponentData().text = "#P" + var2[var4].getExpPercent();
            } else {
               var5.C9_f122.currentDialog.getChildById(16 + var4 * 6).getComponentData().text = "#P0";
               var5.C9_f122.currentDialog.getChildById(17 + var4 * 6).getComponentData().text = "#P0";
            }
         }

         if (var5.C9_f121.previousState == 16) {
            var5.C9_f122.currentDialog.getChildById(64).getComponentData().text = "Gởi lại";
         }

         var5.C9_f122.currentDialog.getChildById(75).setVisible(false);
         var5.C9_f122.currentDialog.getChildById(76).setVisible(false);
      } else if (this.C9_f121 instanceof BattleSystemManager) {
         for (var4 = 0; var4 < 6; ++var4) {
            if (var4 < ((BattleSystemManager) var5.C9_f121).C29_f405.length
                  && var2[((BattleSystemManager) var5.C9_f121).C29_f405[var4]] != null) {
               var5.C9_f122.currentDialog.getChildById(16 + var4 * 6).getComponentData().text = "#P"
                     + var2[((BattleSystemManager) var5.C9_f121).C29_f405[var4]].getHpPercent();
               var5.C9_f122.currentDialog.getChildById(17 + var4 * 6).getComponentData().text = "#P"
                     + var2[((BattleSystemManager) var5.C9_f121).C29_f405[var4]].getExpPercent();
            } else {
               var5.C9_f122.currentDialog.getChildById(16 + var4 * 6).getComponentData().text = "#P0";
               var5.C9_f122.currentDialog.getChildById(17 + var4 * 6).getComponentData().text = "#P0";
            }
         }

         var5.C9_f122.currentDialog.getChildById(63).setVisible(false);
         var5.C9_f122.currentDialog.getChildById(64).setVisible(false);
         if (var5.C9_f121.previousState == 4) {
            var5.C9_f122.currentDialog.getChildById(75).getComponentData().text = "Sử dụng";
         } else if (var5.C9_f121.currentState == 5) {
            var5.C9_f122.currentDialog.getChildById(75).getComponentData().text = "Xuất chiến";
         }
      }

      ((UIContainerComponent) var5.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = var5.C9_f123.partySize;
      ((UIContainerComponent) var5.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.visibleItemCount = var5.C9_f123.partySize;
      ((UIContainerComponent) var5.C9_f122.currentDialog.getChildById(0)).primaryListComponent.selectedIndex = var1;
      var5.C9_f132 = true;
   }

   private void a(PokemonEntity[] var1, int var2) {
      if (var1[var2] != null) {
         if (this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer != null) {
            this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer.cleanup();
         } else {
            this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer.spriteType = 3;
         }

         short var10001 = var1[var2].spriteId;
         this.C9_f122.currentDialog.getChildById(48).getComponentData().renderer.initializeSprite(var10001, false,
               (byte) -1);
         this.C9_f122.currentDialog.getChildById(51).getComponentData().text = GameEngineBase
               .getLocalizedText(var1[var2].getDatabaseValue((byte) 0));
         this.C9_f122.currentDialog.getChildById(52).getComponentData().text = GameEngineBase
               .getLocalizedText(365 + var1[var2].getDatabaseValue((byte) 1));
         if (var1[var2].getDatabaseValue((byte) 19) == -1) {
            this.C9_f122.currentDialog.getChildById(62).getComponentData().text = "";
         } else if (ResourceManager.gameDatabase[0][var1[var2].getDatabaseValue((byte) 19)][2] != 1
               && ResourceManager.gameDatabase[0][var1[var2].getDatabaseValue((byte) 19)][2] != 2) {
            if (ResourceManager.gameDatabase[0][var1[var2].getDatabaseValue((byte) 19)][2] == 3) {
               this.C9_f122.currentDialog.getChildById(62).getComponentData().text = "Có thể dị hoá";
            }
         } else {
            this.C9_f122.currentDialog.getChildById(62).getComponentData().text = "Có thể tiến hóa";
         }

         this.C9_f122.currentDialog.getChildById(61).getComponentData().text = var1[var2].getTypeName();
         if (this.C9_f121 instanceof BattleSystemManager) {
            this.C9_f122.currentDialog.getChildById(64).getComponentData().text = "Xuất chiến";
         } else if (this.C9_f121 instanceof GameWorldManager) {
            this.C9_f122.currentDialog.getChildById(64).getComponentData().text = "Xác nhận";
         }

         if (this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer.initializeSprite(258, false,
                  (byte) -1);
         }

         PokemonEntity var10000 = var1[var2];
         byte var4 = 5;
         if (var10000.primaryStates[var4] != -1) {
            short[][] var7 = ResourceManager.gameDatabase[3];
            PokemonEntity var10002 = var1[var2];
            var4 = 5;
            short[] var8 = var7[var10002.primaryStates[var4]];
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) var8[1]);
            ComponentData var6 = this.C9_f122.currentDialog.getChildById(60).getComponentData();
            var7 = ResourceManager.gameDatabase[3];
            var10002 = var1[var2];
            var4 = 5;
            var6.text = GameEngineBase.getLocalizedText((int) var7[var10002.primaryStates[var4]][0]);
         } else {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(60).getComponentData().text = "";
         }

         this.C9_f122.currentDialog.getChildById(65).getComponentData().text = "" + var1[var2].getLevel();
         this.C9_f122.currentDialog.getChildById(66).getComponentData().text = "" + var1[var2].getStatWithBuffs((byte) 2);
         this.C9_f122.currentDialog.getChildById(67).getComponentData().text = "" + var1[var2].getStatWithBuffs((byte) 3);
         this.C9_f122.currentDialog.getChildById(68).getComponentData().text = "" + var1[var2].getStatWithBuffs((byte) 4);
         var10000 = var1[var2];
         var4 = 0;
         int var3 = var10000.secondaryStates[var4];
         int var5 = ResourceManager.getDatabaseValue((byte) 0, (short) var1[var2].getSpeciesId(), (byte) 4) - 1;

         for (var2 = 0; var2 < 5; ++var2) {
            this.C9_f122.currentDialog.getChildById(74 - var2).setVisible(true);
            this.C9_f122.currentDialog.getChildById(74 - var2).getComponentData().renderer.initializeSprite(257, false,
                  (byte) -1);
            this.C9_f122.currentDialog.getChildById(74 - var2).getComponentData().renderer.spriteType = 3;
            if (var2 > var5) {
               this.C9_f122.currentDialog.getChildById(74 - var2).setVisible(false);
            } else if (var3 > 0) {
               this.C9_f122.currentDialog.getChildById(74 - var2).getComponentData().renderer
                     .setAnimationFrame((byte) 14, (byte) -1);
               --var3;
            } else {
               this.C9_f122.currentDialog.getChildById(74 - var2).getComponentData().renderer
                     .setAnimationFrame((byte) 16, (byte) -1);
            }
         }
      }

   }

   private void g(int var1) {
      if (this.C9_f121 instanceof GameWorldManager) {
         this.a(this.C9_f123.partyPokemon, var1);
      } else {
         if (this.C9_f121 instanceof BattleSystemManager) {
            this.a((PokemonEntity[]) this.C9_f123.partyPokemon, ((BattleSystemManager) this.C9_f121).C29_f405[var1]);
         }

      }
   }

   public final void aa() {
      if (this.C9_f131 == 0) {
         if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && !this.j()
               && this.C9_f121.isKeyPressed(4100)) {
            this.C9_f122.currentDialog.handleAction(0);
         } else if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && !this.j()
               && this.C9_f121.isKeyPressed(8448)) {
            this.C9_f122.currentDialog.handleAction(1);
         } else if (GameEngineBase.isConfirmAllowed() && !this.j() && this.C9_f121.isKeyPressed(196640)) {
            if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0)) {
               return;
            }

            if (this.C9_f121 instanceof BattleSystemManager) {
               int var1;
               if ((var1 = ((BattleSystemManager) this.C9_f121).l(this.C9_f125)) == 0) {
                  this.C9_f131 = 2;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Sủng vật này không thể tham chiến", "Nhấn nút 5 tiếp tục");
                  this.C9_f122.removeDialog("/data/ui/petsetting.ui");
               } else if (var1 == 1) {
                  this.C9_f131 = 2;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Sủng vật này đã đặt ở vị trí chiến đấu", "Nhấn nút 5 tiếp tục");
                  this.C9_f122.removeDialog("/data/ui/petsetting.ui");
               } else if (var1 == -1) {
                  ((BattleSystemManager) this.C9_f121).e(((BattleSystemManager) this.C9_f121).C29_f407, 0);
                  this.C9_f124 = 0;
                  this.C9_f121.changeState((byte) 15);
                  this.C9_f122.removeDialog("/data/ui/petsetting.ui");
                  this.C9_f122.removeDialog("/data/ui/petstate.ui");
               }
            } else if (this.C9_f121 instanceof GameWorldManager) {
               if (this.C9_f121.previousState == 16) {
                  if (this.C9_f123.hasStorageSpace()) {
                     if (this.C9_f123.hasOtherAlivePokemon(this.C9_f125)) {
                        this.C9_f123.deactivateSkill(this.C9_f123.partyPokemon[this.C9_f125].getPrimaryState((byte) 5));
                        this.C9_f123.partyPokemon[this.C9_f125].setPrimaryState((byte) 5, (short) -1);
                        this.C9_f123.addPokemonDataToStorage(this.C9_f123.partyPokemon[this.C9_f125].toSaveData());
                        this.C9_f123.removePokemonAt(this.C9_f125);
                        if (this.C9_f125 >= this.C9_f123.partySize) {
                           --this.C9_f125;
                        }

                        this.f(this.C9_f125);
                     } else {
                        this.C9_f131 = 1;
                        this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.a("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 tiếp tục");
                     }
                  } else {
                     this.C9_f131 = 1;
                     this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                     this.a("Ngân hàng đã đầy, không thể gởi lại", "Nhấn nút 5 tiếp tục");
                  }
               } else if (this.C9_f121.previousState != 6 && this.C9_f121.previousState != 0) {
                  if (this.C9_f121.previousState == 27) {
                     if (this.C9_f128 == 1 && this.C9_f123.partyPokemon[this.C9_f125].getEvolutionTier() == 1
                           || this.C9_f128 == 2 && this.C9_f123.partyPokemon[this.C9_f125].getEvolutionTier() == 2) {
                        this.bl();
                     } else {
                        this.C9_f131 = 4;
                        this.H();
                        if (this.C9_f128 == 1) {
                           this.a("Sủng vật này không thể tiến hóa", "Nhấn nút 5 tiếp tục");
                        } else if (this.C9_f128 == 2) {
                           this.a("Sủng vật này không thể dị hoá", "Nhấn nút 5 tiếp tục");
                        } else {
                           this.a("Không thể vào hóa cùng dị hoá", "Nhấn nút 5 tiếp tục");
                        }
                     }
                  }
               } else {
                  this.C9_f126 = 0;
                  this.C9_f121.handleActionResponse();
                  this.C9_f131 = 1;
                  this.C9_f122.showDialog("/data/ui/petsetting.ui", 257, this);
                  ((UIContainerComponent) this.C9_f122.currentDialog
                        .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f126;
                  if (this.C9_f123.partyPokemon[this.C9_f125].getEvolutionTier() == 2) {
                     this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "Dị hoá";
                     ((UIContainerComponent) this.C9_f122.currentDialog
                           .getChildById(0)).primaryListComponent.totalItemCount = 6;
                     ((UIContainerComponent) this.C9_f122.currentDialog
                           .getChildById(0)).primaryListComponent.visibleItemCount = 6;
                  } else if (this.C9_f123.partyPokemon[this.C9_f125].getEvolutionTier() == 1) {
                     this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "Tiến hóa";
                     ((UIContainerComponent) this.C9_f122.currentDialog
                           .getChildById(0)).primaryListComponent.totalItemCount = 6;
                     ((UIContainerComponent) this.C9_f122.currentDialog
                           .getChildById(0)).primaryListComponent.visibleItemCount = 6;
                  } else {
                     this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "";
                     ((UIContainerComponent) this.C9_f122.currentDialog
                           .getChildById(0)).primaryListComponent.totalItemCount = 5;
                     ((UIContainerComponent) this.C9_f122.currentDialog
                           .getChildById(0)).primaryListComponent.visibleItemCount = 5;
                  }
               }
            }
         } else if (QuestManager.isCancelAllowed() && !this.j() && this.C9_f121.isKeyPressed(262144)) {
            if (this.C9_f121 instanceof GameWorldManager) {
               if (this.C9_f121.previousState == 16) {
                  this.C9_f121.changeState((byte) 16);
               } else if (this.C9_f121.previousState == 6) {
                  if (GameEngineBase.paymentActive) {
                     this.C9_f125 = 1;
                  } else {
                     this.C9_f125 = 0;
                  }

                  this.C9_f121.changeState((byte) 6);
               } else if (this.C9_f121.previousState == 27) {
                  this.C9_f121.changeState((byte) 27);
               } else if (this.C9_f121.previousState == 0) {
                  this.C9_f121.changeState((byte) 23);
               }

               this.C9_f122.removeDialog("/data/ui/petstate.ui");
            } else if (this.C9_f121 instanceof BattleSystemManager) {
               if (((BattleSystemManager) this.C9_f121).previousState == 7
                     || ((BattleSystemManager) this.C9_f121).previousState == 13) {
                  return;
               }

               this.C9_f122.removeDialog("/data/ui/petstate.ui");
               BattleSystemManager.B().C29_f415 = false;
               this.C9_f124 = 0;
               this.C9_f121.changeState((byte) 20);
            }
         }
      } else if (this.C9_f131 == 1) {
         if (!GameEngineBase.isActionBlocked((int) this.C9_f126, (int) 0) && !this.j()
               && this.C9_f121.isKeyPressed(4100)) {
            this.C9_f122.currentDialog.handleAction(0);
         } else if (!GameEngineBase.isActionBlocked((int) this.C9_f126, (int) 0) && !this.j()
               && this.C9_f121.isKeyPressed(8448)) {
            this.C9_f122.currentDialog.handleAction(1);
         } else if (GameEngineBase.isConfirmAllowed() && !this.j() && this.C9_f121.isKeyPressed(196640)) {
            if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.C9_f126, (int) 0)) {
               return;
            }

            if (this.C9_f121.previousState == 16) {
               this.C9_f121.changeState((byte) 16);
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.C9_f122.removeDialog("/data/ui/petstate.ui");
               this.C9_f131 = 0;
            } else if (this.C9_f121.previousState == 6 || this.C9_f121.previousState == 0) {
               switch (this.C9_f126) {
                  case 0:
                     this.bh();
                     break;
                  case 1:
                     if (!this.C9_f123.partyPokemon[this.C9_f125].isAlive()) {
                        this.C9_f131 = 2;
                        this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.a("Sủng vật này không thể tham chiến", "Nhấn nút 5 tiếp tục");
                        this.C9_f122.removeDialog("/data/ui/petsetting.ui");
                        this.C9_f125 = 0;
                     } else if (this.C9_f125 == 0) {
                        this.C9_f131 = 2;
                        this.C9_f125 = 0;
                        this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.a("Sủng vật này đã xuất chiến", "Nhấn nút 5 tiếp tục");
                        this.C9_f122.removeDialog("/data/ui/petsetting.ui");
                     } else {
                        this.C9_f123.movePokemonToFront(this.C9_f125);
                        this.C9_f131 = 0;
                        this.C9_f125 = 0;
                        this.f(this.C9_f125);
                        this.C9_f122.removeDialog("/data/ui/petsetting.ui");
                        ((UIContainerComponent) this.C9_f122.currentDialog
                              .getChildById(0)).primaryListComponent.selectedIndex = 0;
                        ((UIContainerComponent) this.C9_f122.currentDialog
                              .getChildById(0)).primaryListComponent.scrollOffset = 0;
                     }
                     break;
                  case 2:
                     this.C9_f121.handleActionResponse();
                     this.bf();
                     break;
                  case 3:
                     if (ResourceManager.getDatabaseValue((byte) 0, (short) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
                           (byte) 22) == 2) {
                        this.C9_f131 = 3;
                        this.H();
                        this.C9_f122.removeDialog("/data/ui/petsetting.ui");
                        this.a("Thần thú không thể phóng sinh", "Nhấn nút 5 tiếp tục");
                     } else {
                        this.C9_f131 = 2;
                        this.C9_f122.showDialog("/data/ui/msgconfirm.ui", 257, this);
                        this.C9_f122.removeDialog("/data/ui/petsetting.ui");
                        this.b("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
                     }
                     break;
                  case 4:
                     this.bj();
                     break;
                  case 5:
                     this.C9_f121.handleActionResponse();
                     this.bl();
               }
            }
         } else if (QuestManager.isCancelAllowed() && !this.j() && this.C9_f121.isKeyPressed(262144)) {
            if (this.C9_f121.previousState == 16) {
               return;
            }

            this.C9_f131 = 0;
            this.C9_f122.removeDialog("/data/ui/petsetting.ui");
         }
      } else if (this.C9_f131 >= 2) {
         if (this.C9_f121 instanceof BattleSystemManager) {
            if (this.C9_f121.isKeyPressed(196640)) {
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
            }
         } else if (this.C9_f121.previousState != 6 && this.C9_f121.previousState != 0) {
            if (this.C9_f131 <= 3) {
               this.bp();
            } else if (this.C9_f131 == 4 && this.C9_f121.isKeyPressed(196640)) {
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
            }
         } else {
            switch (this.C9_f126) {
               case 0:
                  this.bn();
                  break;
               case 1:
                  if (this.C9_f121.isKeyPressed(196640)) {
                     this.C9_f131 = 0;
                     this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  }
                  break;
               case 2:
                  this.bm();
                  break;
               case 3:
                  if (this.C9_f121.isKeyPressed(131072) && this.C9_f131 == 2
                        || this.C9_f121.isKeyPressed(196640) && this.C9_f131 == 3) {
                     if (this.C9_f131 == 2) {
                        if (this.C9_f123.hasOtherAlivePokemon(this.C9_f125)) {
                           this.C9_f123.deactivateSkill(this.C9_f123.partyPokemon[this.C9_f125].getPrimaryState((byte) 5));
                           this.C9_f123.partyPokemon[this.C9_f125].setPrimaryState((byte) 5, (short) -1);
                           this.C9_f123.removePokemonAt(this.C9_f125);
                           if (this.C9_f125 >= this.C9_f123.partySize) {
                              --this.C9_f125;
                           }

                           this.f(this.C9_f125);
                           ((GameWorldManager) this.C9_f121).C25_f348.updateQuestEffects();
                           this.C9_f122.removeDialog("/data/ui/msgconfirm.ui");
                           this.C9_f131 = 0;
                        } else {
                           this.C9_f131 = 3;
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 tiếp tục");
                           this.C9_f122.removeDialog("/data/ui/msgconfirm.ui");
                        }
                     } else {
                        this.C9_f131 = 0;
                        this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                     }
                  } else if (this.C9_f121.isKeyPressed(786432) && this.C9_f131 <= 2) {
                     this.C9_f131 = 0;
                     this.C9_f122.removeDialog("/data/ui/msgconfirm.ui");
                  }
                  break;
               case 4:
                  this.bo();
                  break;
               case 5:
                  this.bp();
            }
         }
      }

      this.C9_f132 = true;
      this.g();
   }

   private void bf() {
      this.C9_f131 = 2;
      this.C9_f127 = 0;
      this.C9_f122.showDialog("/data/ui/choice.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/petsetting.ui");
      this.C9_f122.removeDialog("/data/ui/petstate.ui");
      this.C9_f122.currentDialog.getChildById(8).getComponentData().text = "Vật phẩm trang sức";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "Trạng thái";
      if (this.C9_f121 instanceof GameWorldManager) {
         this.C9_f122.currentDialog.getChildById(5).setVisible(false);
         this.C9_f122.currentDialog.getChildById(6).setVisible(false);
         this.C9_f122.currentDialog.getChildById(59).setVisible(true);
         this.C9_f122.currentDialog.getChildById(60).setVisible(true);
         this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "Mang theo";
      } else {
         this.C9_f122.currentDialog.getChildById(5).setVisible(true);
         this.C9_f122.currentDialog.getChildById(6).setVisible(true);
         this.C9_f122.currentDialog.getChildById(59).setVisible(false);
         this.C9_f122.currentDialog.getChildById(60).setVisible(false);
         this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Mang theo";
      }

      this.bg();
      this.C9_f132 = true;
   }

   private void bg() {
      if (this.C9_f123.keyItems.size() > 5) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
      }

      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = this.C9_f123.keyItems.size();
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;
      if (this.C9_f136 >= this.C9_f123.keyItems.size()) {
         this.C9_f136 = this.C9_f123.keyItems.size() - 1;
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f136;
      }

      if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
         --this.C9_f135;
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.scrollOffset = this.C9_f135;
      }

      if (this.C9_f123.keyItems.size() > 0) {
         PokemonEntity var10000 = this.C9_f123.partyPokemon[this.C9_f125];
         byte var4 = 5;
         if (var10000.primaryStates[var4] == ((int[]) this.C9_f123.keyItems.elementAt(this.C9_f136))[0]) {
            if (this.C9_f121 instanceof GameWorldManager) {
               this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "Dỡ xuống";
            } else {
               this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Dỡ xuống";
            }
         } else if (this.C9_f121 instanceof GameWorldManager) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "Mang theo";
         } else {
            this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Mang theo";
         }

         for (int var1 = 0; var1 < 5; ++var1) {
            if (this.C9_f135 + var1 < this.C9_f123.keyItems.size()) {
               int[] var2 = (int[]) this.C9_f123.keyItems.elementAt(this.C9_f135 + var1);
               if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer
                        .setSpriteIndex((int) 0);
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.spriteType = 2;
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.initializeSprite(258,
                        false, (byte) -1);
               }

               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer
                     .setSpriteIndex((int) ResourceManager.gameDatabase[3][var2[0]][1]);
               this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = GameEngineBase
                     .getLocalizedText((int) ResourceManager.gameDatabase[3][var2[0]][0]);
               var10000 = PlayerCharacter.getInstance().partyPokemon[this.C9_f125];
               var4 = 5;
               if (var10000.primaryStates[var4] == var2[0]) {
                  this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "Đã mang theo";
               } else if (var2[1] == 1) {
                  this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "Bị mang theo";
               } else {
                  this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
               }
            } else {
               if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer != null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.cleanup();
               }

               this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = "";
               this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
            }
         }

         if (this.C9_f123.keyItems.size() > 0) {
            this.C9_f122.currentDialog.getChildById(53).getComponentData().text = GameEngineBase.getLocalizedText(
                  (int) ResourceManager.gameDatabase[3][((int[]) this.C9_f123.keyItems.elementAt(this.C9_f136))[0]][2]);
         } else {
            this.C9_f122.currentDialog.getChildById(53).getComponentData().text = "";
         }

         if (this.C9_f123.keyItems.size() > 0) {
            this.C9_f122.currentDialog.getChildById(51).setOffsetY(
                  98 + this.C9_f136 * 62 / this.C9_f123.keyItems.size(),
                  this.C9_f122.currentDialog.getUIContainerComponent());
         } else {
            this.C9_f122.currentDialog.getChildById(51).setOffsetY(98,
                  this.C9_f122.currentDialog.getUIContainerComponent());
         }
      }
   }

   private void bh() {
      this.C9_f131 = 2;
      this.C9_f127 = 0;
      this.C9_f122.showDialog("/data/ui/choice.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/petsetting.ui");
      this.C9_f122.removeDialog("/data/ui/petstate.ui");
      this.C9_f122.currentDialog.getChildById(8).getComponentData().text = "Đạo cụ";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "Số lượng";
      if (this.C9_f121 instanceof GameWorldManager) {
         this.C9_f122.currentDialog.getChildById(5).setVisible(false);
         this.C9_f122.currentDialog.getChildById(6).setVisible(false);
         this.C9_f122.currentDialog.getChildById(59).setVisible(true);
         this.C9_f122.currentDialog.getChildById(60).setVisible(true);
         this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "Sử dụng";
      } else {
         this.C9_f122.currentDialog.getChildById(5).setVisible(true);
         this.C9_f122.currentDialog.getChildById(6).setVisible(true);
         this.C9_f122.currentDialog.getChildById(59).setVisible(false);
         this.C9_f122.currentDialog.getChildById(60).setVisible(false);
         this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Sử dụng";
      }

      this.bi();
      this.C9_f132 = true;
   }

   private void bi() {
      if (this.C9_f123.equipmentInventory.size() > 5) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
      }

      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = this.C9_f123.equipmentInventory.size();
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;
      if (this.C9_f136 >= this.C9_f123.equipmentInventory.size()) {
         this.C9_f136 = this.C9_f123.equipmentInventory.size() - 1;
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f136;
      }

      if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
         --this.C9_f135;
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.scrollOffset = this.C9_f135;
      }

      for (int var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.equipmentInventory.size()) {
            int[] var2 = (int[]) this.C9_f123.equipmentInventory.elementAt(this.C9_f135 + var1);
            if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.initializeSprite(258,
                     false, (byte) -1);
            }

            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer
                  .setSpriteIndex((int) ResourceManager.gameDatabase[4][var2[0]][1]);
            this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.gameDatabase[4][var2[0]][0]);
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "" + var2[1];
         } else {
            if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer != null) {
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.cleanup();
            }

            this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = "";
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
         }
      }

      if (this.C9_f123.equipmentInventory.size() > 0) {
         this.C9_f122.currentDialog.getChildById(53).getComponentData().text = GameEngineBase.getLocalizedText(
               (int) ResourceManager.gameDatabase[4][((int[]) this.C9_f123.equipmentInventory.elementAt(this.C9_f136))[0]][2]);
      } else {
         this.C9_f122.currentDialog.getChildById(53).getComponentData().text = "";
      }

      if (this.C9_f123.equipmentInventory.size() > 0) {
         this.C9_f122.currentDialog.getChildById(51).setOffsetY(98 + this.C9_f136 * 80 / this.C9_f123.equipmentInventory.size(),
               this.C9_f122.currentDialog.getUIContainerComponent());
      } else {
         this.C9_f122.currentDialog.getChildById(51).setOffsetY(98,
               this.C9_f122.currentDialog.getUIContainerComponent());
      }
   }

   private void bj() {
      this.C9_f131 = 2;
      this.C9_f127 = 0;
      this.C9_f122.showDialog("/data/ui/skill.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/petsetting.ui");
      this.C9_f122.removeDialog("/data/ui/petstate.ui");
      this.C9_f122.currentDialog.getChildById(12).getComponentData().text = GameEngineBase
            .getLocalizedText(this.C9_f123.partyPokemon[this.C9_f125].getDatabaseValue((byte) 0));
      this.C9_f122.currentDialog.getChildById(14).getComponentData().text = ""
            + this.C9_f123.partyPokemon[this.C9_f125].getLevel();
      if (this.C9_f122.currentDialog.getChildById(16).getComponentData().renderer != null) {
         this.C9_f122.currentDialog.getChildById(16).getComponentData().renderer.cleanup();
      } else {
         this.C9_f122.currentDialog.getChildById(16).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(16).getComponentData().renderer.setSpriteIndex((int) 0);
         this.C9_f122.currentDialog.getChildById(16).getComponentData().renderer.spriteType = 3;
      }

      short var10001 = this.C9_f123.partyPokemon[this.C9_f125].spriteId;
      this.C9_f122.currentDialog.getChildById(16).getComponentData().renderer.initializeSprite(var10001, false,
            (byte) -1);
      int var1 = this.C9_f123.partyPokemon[this.C9_f125].getSkillCount();

      for (int var2 = 0; var2 < var1; ++var2) {
         this.C9_f122.currentDialog.getChildById(var2 + 18).getComponentData().text = GameEngineBase
               .getLocalizedText((int) ResourceManager.gameDatabase[1][this.C9_f123.partyPokemon[this.C9_f125].getSkillAt(var2)][1]);
      }

      this.bk();
      this.C9_f132 = true;
   }

   private void bk() {
      if (this.C9_f123.partyPokemon[this.C9_f125].getSkillAt(this.C9_f127) != -1) {
         String[] var1 = new String[] { "Nhất định", "Nhất định" };
         this.C9_f122.currentDialog.getChildById(9).getComponentData().text = GameEngineBase.formatString(
               ResourceManager.gameDatabase[1][this.C9_f123.partyPokemon[this.C9_f125].getSkillAt(this.C9_f127)][2],
               (String[]) var1);
      } else {
         this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "";
      }
   }

   private void bl() {
      this.C9_f131 = 2;
      this.C9_f127 = 0;
      this.C9_f122.showDialog("/data/ui/evolve.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/petsetting.ui");
      this.C9_f122.removeDialog("/data/ui/petstate.ui");
      if (this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.setSpriteIndex((int) 0);
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.spriteType = 3;
      }

      short var10001 = this.C9_f123.partyPokemon[this.C9_f125].spriteId;
      this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.initializeSprite(var10001, false,
            (byte) -1);
      short var1 = (short) (ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
            (byte) 20) + 12);
      short var2 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
            (byte) 21);
      this.C9_f122.currentDialog.getChildById(38).getComponentData().text = GameEngineBase.getLocalizedText(
            (int) ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(), (byte) 0));
      this.C9_f122.currentDialog.getChildById(40).getComponentData().text = ""
            + this.C9_f123.partyPokemon[this.C9_f125].getLevel();
      this.C9_f122.currentDialog.getChildById(45).getComponentData().text = GameEngineBase
            .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 3, var1, (byte) 0));
      this.C9_f122.currentDialog.getChildById(46).getComponentData().text = this.C9_f123.getItemCount((int) var1, (byte) 2) + "/"
            + var2;
      var1 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(), (byte) 19);
      PokemonEntity var6;
      (var6 = new PokemonEntity()).initialize(var1, (byte) this.C9_f123.partyPokemon[this.C9_f125].getLevel(), (short) -1, (byte) -1,
            (short) -1, (byte) -1);

      for (int var5 = 0; var5 < 4; ++var5) {
         ComponentData var10000 = this.C9_f122.currentDialog.getChildById(var5 + 19).getComponentData();
         StringBuffer var7 = new StringBuffer();
         PokemonEntity var10002 = this.C9_f123.partyPokemon[this.C9_f125];
         byte var4 = (byte) (var5 + 1);
         var10000.text = var7.append(var10002.primaryStates[var4]).toString();
         var10000 = this.C9_f122.currentDialog.getChildById(var5 + 31).getComponentData();
         var7 = new StringBuffer();
         var4 = (byte) (var5 + 1);
         var10000.text = var7.append(var6.primaryStates[var4]).toString();
      }

      this.C9_f132 = true;
   }

   private void bm() {
      if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && !this.j() && this.C9_f131 == 2
            && this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bg();
      } else if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && !this.j() && this.C9_f131 == 2
            && this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bg();
      } else if (GameEngineBase.isConfirmAllowed() && !this.j() && this.C9_f121.isKeyPressed(196640)
            && this.C9_f123.keyItems.size() > 0) {
         if (!GameEngineBase.isActionActive() || GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0)) {
            if (this.C9_f131 == 2) {
               int[] var1 = (int[]) this.C9_f123.keyItems.elementAt(this.C9_f136);
               PokemonEntity var10000 = this.C9_f123.partyPokemon[this.C9_f125];
               byte var3 = 5;
               if (var10000.primaryStates[var3] == var1[0]) {
                  PokemonEntity var10001 = this.C9_f123.partyPokemon[this.C9_f125];
                  var3 = 5;
                  this.C9_f123.deactivateSkill(var10001.primaryStates[var3]);
                  var10000 = this.C9_f123.partyPokemon[this.C9_f125];
                  byte var4 = -1;
                  var3 = 5;
                  var10000.primaryStates[var3] = var4;
                  this.bg();
                  this.H();
                  this.a("Thành công dỡ xuống", "Nhấn nút 5 tiếp tục");
               } else {
                  this.C9_f123.assignSkillToPokemon(var1[0], this.C9_f125);
                  this.bg();
                  this.H();
                  this.a("Thành công mang theo", "Nhấn nút 5 tiếp tục");
               }

               this.C9_f131 = 3;
            } else {
               this.C9_f131 = 2;
               this.C9_f121.handleActionResponse();
               this.f(this.C9_f125);
               this.I();
               this.C9_f122.removeDialog("/data/ui/choice.ui");
            }
         }
      } else {
         if (QuestManager.isCancelAllowed() && !this.j() && this.C9_f131 == 2 && this.C9_f121.isKeyPressed(262144)) {
            this.f(this.C9_f125);
            this.C9_f122.removeDialog("/data/ui/choice.ui");
         }

      }
   }

   private void bn() {
      if (this.C9_f131 == 2 && this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f131 == 2 && this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else {
         if (this.C9_f121.isKeyPressed(196640)) {
            if (this.C9_f123.equipmentInventory.size() <= 0) {
               return;
            }

            if (this.C9_f131 == 2) {
               this.C9_f131 = 3;
               int[] var1;
               switch ((var1 = (int[]) this.C9_f123.equipmentInventory.elementAt(this.C9_f127))[0]) {
                  case 13:
                  case 14:
                     this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                     this.a("Đạo cụ này không thể sử dụng", "Nhấn nút 5 tiếp tục");
                     return;
                  default:
                     switch (this.C9_f123.partyPokemon[this.C9_f125].canUseItem(var1[0])) {
                        case 0:
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Sủng vật này đã tử vong, không thể sử dụng", "Nhấn nút 5 tiếp tục");
                           return;
                        case 1:
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Sủng vật này không có, không thể sử dụng", "Nhấn nút 5 tiếp tục");
                           return;
                        case 2:
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Máu đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
                           return;
                        case 3:
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Kỹ năng giá trị đã đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
                           return;
                        case 4:
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Trên người đều bị lợi hiệu quả", "Nhấn nút 5 tiếp tục");
                           return;
                        case 5:
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Trong hưng phấn, không thể dùng", "Nhấn nút 5 tiếp tục");
                           return;
                        case 6:
                        default:
                           this.C9_f123.partyPokemon[this.C9_f125].useItem(var1[0]);
                           this.f(this.C9_f125);
                           this.C9_f131 = 4;
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Thành công sử dụng đạo cụ", "Nhấn nút 5 tiếp tục");
                           this.C9_f122.removeDialog("/data/ui/choice.ui");
                           return;
                        case 7:
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Máu và tinh khí đều đã đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
                           return;
                        case 8:
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Sủng vật đã chết, không thể sử dụng", "Nhấn nút 5 tiếp tục");
                           return;
                     }
               }
            }

            if (this.C9_f131 == 3) {
               this.C9_f131 = 2;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               return;
            }

            if (this.C9_f131 == 4) {
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               return;
            }
         } else if (this.C9_f131 == 2 && this.C9_f121.isKeyPressed(262144)) {
            this.f(this.C9_f125);
            this.C9_f122.removeDialog("/data/ui/choice.ui");
         }

      }
   }

   private void bo() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bk();
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bk();
      } else if (this.C9_f121.isKeyPressed(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.bk();
      } else if (this.C9_f121.isKeyPressed(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.bk();
      } else {
         if (this.C9_f121.isKeyPressed(262144)) {
            this.f(this.C9_f125);
            this.C9_f122.removeDialog("/data/ui/skill.ui");
         }

      }
   }

   private void bp() {
      short var1;
      short var3;
      short var4;
      if (GameWorldManager.C25_f300 != null) {
         if (!GameWorldManager.C25_f300.isActive()) {
            var1 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
                  (byte) 19);
            String var9 = GameEngineBase
                  .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 0, var1, (byte) 0));
            var3 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
                  (byte) 19);
            var4 = ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 17);
            ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(), (byte) 21);
            this.C9_f122.currentDialog.getChildById(10).setVisible(true);
            this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.initializeSprite(var4, false,
                  (byte) -1);
            this.C9_f122.currentDialog.getChildById(38).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 0));
            PokemonEntity var11 = new PokemonEntity();
            short var5 = ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 3);
            byte var6 = -1;
            PokemonEntity var10000 = this.C9_f123.partyPokemon[this.C9_f125];
            byte var8 = 0;
            if (var10000.primaryStates[var8] >= var5) {
               var10000 = this.C9_f123.partyPokemon[this.C9_f125];
               var8 = 0;
               var6 = (byte) var10000.primaryStates[var8];
            }

            int var10002 = this.C9_f123.partyPokemon[this.C9_f125].getLevel();
            PokemonEntity var10003 = this.C9_f123.partyPokemon[this.C9_f125];
            var8 = 5;
            short var13 = var10003.primaryStates[var8];
            PokemonEntity var10004 = this.C9_f123.partyPokemon[this.C9_f125];
            var8 = 6;
            var11.initialize(var3, var10002, var13, (byte) var10004.secondaryStates[var8], (short) var6, (byte) -1);
            var8 = 1;
            var11.setExpAndFriendship((short) var11.primaryStates[var8], this.C9_f123.partyPokemon[this.C9_f125].getCurrentExp(),
                  this.C9_f123.partyPokemon[this.C9_f125].friendship);
            var11.loadSkillData(this.C9_f123.partyPokemon[this.C9_f125].getSkillSaveData());
            this.C9_f123.registerCreature((byte) ((byte) this.C9_f123.partyPokemon[this.C9_f125].getDatabaseValue((byte) 1)), var3, (byte) 2);
            this.C9_f123.partyPokemon[this.C9_f125].initializeFromData(var11.toSaveData());
            var5 = (short) (ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
                  (byte) 20) + 12);
            var4 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
                  (byte) 21);
            var3 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
                  (byte) 19);
            int var12 = this.C9_f123.getItemCount((int) var5, (byte) 2);
            if (var3 == -1) {
               this.C9_f122.currentDialog.getChildById(42).getComponentData().text = "";
               this.C9_f122.currentDialog.getChildById(45).getComponentData().text = "";
               this.C9_f122.currentDialog.getChildById(46).getComponentData().text = "";
            } else {
               this.C9_f122.currentDialog.getChildById(45).getComponentData().text = GameEngineBase
                     .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 3, var5, (byte) 0));
               this.C9_f122.currentDialog.getChildById(46).getComponentData().text = var12 + "/" + var4;
            }

            if (this.C9_f123.partyPokemon[this.C9_f125].getEvolutionTier() == 2) {
               this.C9_f131 = 3;
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Dị hoá thành #2" + var9, "Nhấn nút 5 tiếp tục");
            } else {
               this.C9_f131 = 3;
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Tiến hóa thành #2" + var9, "Nhấn nút 5 tiếp tục");
            }

            GameWorldManager.C25_f300 = null;
         }

      } else {
         if (GameEngineBase.isConfirmAllowed() && !this.j() && this.C9_f121.isKeyPressed(196640)) {
            if (this.C9_f131 == 2) {
               var1 = (short) (ResourceManager.getDatabaseValue((byte) 0,
                     (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(), (byte) 20) + 12);
               short var2 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
                     (byte) 21);
               if ((var3 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.C9_f123.partyPokemon[this.C9_f125].getSpeciesId(),
                     (byte) 19)) == -1) {
                  this.C9_f131 = 3;
                  this.H();
                  this.a("Không thể lại tiến hóa hoặc dị hoá", "Nhấn nút 5 tiếp tục");
                  return;
               }

               var4 = ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 17);
               if (this.C9_f123.partyPokemon[this.C9_f125]
                     .getLevel() >= PokemonEntity.EVOLUTION_LEVEL_REQUIREMENTS[ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 2) - 1]) {
                  if (this.C9_f123.getItemCount((int) var1, (byte) 2) >= var2) {
                     this.C9_f122.currentDialog.getChildById(10).setVisible(false);
                     GameWorldManager.C25_f300 = new EffectEntity();
                     short[] var10 = new short[] { 0, 0, 10, 116, 164, this.C9_f123.partyPokemon[this.C9_f125].spriteId, 0,
                           0, var4, 0, 0 };
                     GameWorldManager.C25_f300.initializeEffect(var10);
                     GameWorldManager.C25_f300.setInteractable(true);
                     GameWorldManager.C25_f300.activateEffect();
                     this.C9_f123.removeItem(var1, var2, (byte) 2);
                     return;
                  }

                  this.C9_f131 = 3;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  if (this.C9_f123.partyPokemon[this.C9_f125].getEvolutionTier() == 2) {
                     this.a("Tài liệu chưa đủ, không thể dị hoá", "Nhấn nút 5 tiếp tục");
                     return;
                  }

                  this.a("Tài liệu chưa đủ, không thể tiến hóa", "Nhấn nút 5 tiếp tục");
                  return;
               }

               this.C9_f131 = 3;
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Còn chưa tới"
                     + PokemonEntity.EVOLUTION_LEVEL_REQUIREMENTS[ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 2) - 1]
                     + " cấp, không thể vào hóa", "Nhấn nút 5 tiếp tục");
               return;
            }

            if (this.C9_f131 == 3) {
               if (this.C9_f121.previousState == 6 || this.C9_f121.previousState == 0) {
                  this.C9_f131 = 2;
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  this.C9_f121.handleActionResponse();
                  return;
               }

               if (this.C9_f121.previousState == 27) {
                  this.f(this.C9_f125);
                  this.C9_f131 = 0;
                  this.C9_f126 = 0;
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  this.C9_f122.removeDialog("/data/ui/evolve.ui");
                  return;
               }
            }
         } else if (this.C9_f131 < 3 && this.C9_f121.isKeyPressed(262144) && !this.j()
               && GameEngineBase.isCancelAllowed()) {
            this.C9_f131 = 0;
            this.f(this.C9_f125);
            this.C9_f122.removeDialog("/data/ui/evolve.ui");
         }

      }
   }

   public final void ab() {
      this.C9_f122.showDialog("/data/ui/bag.ui", 257, this);
      this.C9_f125 = 0;
      this.bq();
      this.C9_f122.currentDialog.handleAction(5);
      this.C9_f122.currentDialog.getChildById(14).getComponentData().text = "Vật phẩm";
      this.C9_f125 = 0;
   }

   private void bq() {
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(8 + this.C9_f125 * 39)).primaryListComponent.scrollOffset = 0;
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(8 + this.C9_f125 * 39)).primaryListComponent.selectedIndex = 0;
      this.br();
   }

   private void br() {
      switch (this.C9_f125) {
         case 0:
            this.bs();
            break;
         case 1:
            DialogUIManager var4 = this;
            if (this.C9_f123.keyItems.size() > 5) {
               ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(47)).primaryListComponent
                     .setDisplayMode(1);
            } else {
               ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(47)).primaryListComponent
                     .setDisplayMode(0);
            }

            ((UIContainerComponent) this.C9_f122.currentDialog
                  .getChildById(47)).primaryListComponent.totalItemCount = this.C9_f123.keyItems.size();
            this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
                  .getChildById(47)).primaryListComponent.scrollOffset;
            this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
                  .getChildById(47)).primaryListComponent.selectedIndex;
            this.C9_f122.currentDialog.getChildById(7).setVisible(false);

            for (int var2 = 0; var2 < 5; ++var2) {
               if (var4.C9_f135 + var2 < var4.C9_f123.keyItems.size()) {
                  int[] var3 = (int[]) var4.C9_f123.keyItems.elementAt(var4.C9_f135 + var2);
                  if (var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer == null) {
                     var4.C9_f122.currentDialog.getChildById(59 + var2 * 5)
                           .getComponentData().renderer = new SpriteRenderer();
                     var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer
                           .setSpriteIndex((int) 0);
                     var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer.spriteType = 2;
                     var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer
                           .initializeSprite(258, false, (byte) -1);
                  }

                  if (var4.C9_f122.currentDialog.getChildById(59 + var2 * 5)
                        .getComponentData().focusedRenderer == null) {
                     var4.C9_f122.currentDialog.getChildById(59 + var2 * 5)
                           .getComponentData().focusedRenderer = new SpriteRenderer();
                     var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().focusedRenderer
                           .setSpriteIndex((int) 0);
                     var4.C9_f122.currentDialog.getChildById(59 + var2 * 5)
                           .getComponentData().focusedRenderer.spriteType = 2;
                     var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().focusedRenderer
                           .initializeSprite(258, false, (byte) -1);
                  }

                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[3][var3[0]][1]);
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().focusedRenderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[3][var3[0]][1]);
                  var4.C9_f122.currentDialog.getChildById(60 + var2 * 5).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[3][var3[0]][0]);
                  if (var3[1] == 1) {
                     var4.C9_f122.currentDialog.getChildById(61 + var2 * 5).getComponentData().text = "Đã mang theo";
                  } else {
                     var4.C9_f122.currentDialog.getChildById(61 + var2 * 5).getComponentData().text = "";
                  }
               } else {
                  if (var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer != null) {
                     var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer.cleanup();
                  }

                  var4.C9_f122.currentDialog.getChildById(60 + var2 * 5).getComponentData().text = "";
                  var4.C9_f122.currentDialog.getChildById(61 + var2 * 5).getComponentData().text = "";
               }
            }

            if (var4.C9_f123.keyItems.size() > 0) {
               var4.C9_f122.currentDialog.getChildById(85).getComponentData().text = GameEngineBase
                     .getLocalizedText((int) ResourceManager.gameDatabase[3][((int[]) var4.C9_f123.keyItems
                           .elementAt(var4.C9_f136))[0]][2]);
            } else {
               var4.C9_f122.currentDialog.getChildById(85).getComponentData().text = "";
            }

            if (var4.C9_f123.keyItems.size() > 0) {
               var4.C9_f122.currentDialog.getChildById(84).setOffsetY(
                     127 + var4.C9_f136 * 72 / var4.C9_f123.keyItems.size(),
                     var4.C9_f122.currentDialog.getUIContainerComponent());
            } else {
               var4.C9_f122.currentDialog.getChildById(84).setOffsetY(127,
                     var4.C9_f122.currentDialog.getUIContainerComponent());
            }
            break;
         case 2:
            this.bt();
            break;
         case 3:
            this.bu();
            if (this.C9_f136 < 0 || this.C9_f123.skills.size() <= 0) {
               return;
            }

            int[] var1 = (int[]) this.C9_f123.skills.elementAt(this.C9_f136);
            this.C9_f122.currentDialog.getChildById(164).setVisible(false);
            this.C9_f122.currentDialog.getChildById(165).setVisible(false);
            switch (var1[0]) {
               case 0:
                  if (this.C9_f123.isSkillActive(var1[0])) {
                     this.C9_f122.currentDialog.getChildById(7).setVisible(true);
                     this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Ấp trứng";
                     this.C9_f122.currentDialog.getChildById(164).setVisible(true);
                     this.C9_f122.currentDialog.getChildById(165).setVisible(true);
                     if (this.C9_f123.pokedexCount == 0) {
                        this.C9_f122.currentDialog.getChildById(164).getComponentData().text = "#P"
                              + GameWorldManager.C25_f314 * 100 / 10;
                        this.C9_f122.currentDialog.getChildById(165).getComponentData().text = GameWorldManager.C25_f314
                              + "/10";
                     } else {
                        this.C9_f122.currentDialog.getChildById(164).getComponentData().text = "#P"
                              + GameWorldManager.C25_f314 * 100 / 30;
                        this.C9_f122.currentDialog.getChildById(165).getComponentData().text = GameWorldManager.C25_f314
                              + "/30";
                     }
                  } else {
                     this.C9_f122.currentDialog.getChildById(7).setVisible(false);
                  }
                  break;
               case 1:
               case 2:
               case 3:
               case 4:
                  this.C9_f122.currentDialog.getChildById(7).setVisible(false);
                  break;
               case 5:
               case 6:
               case 10:
                  this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Mở ra";
                  break;
               case 7:
               case 8:
               case 9:
                  this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Sử dụng";
            }
      }

      this.C9_f132 = true;
   }

   private void bs() {
      int var1;
      if ((var1 = this.C9_f123.consumableInventory.size() + this.C9_f123.equipmentInventory.size()) > 5) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(8)).primaryListComponent.setDisplayMode(1);
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(8)).primaryListComponent.setDisplayMode(0);
      }

      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(8)).primaryListComponent.totalItemCount = var1;
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(8)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(8)).primaryListComponent.selectedIndex;
      this.C9_f122.currentDialog.getChildById(7).setVisible(true);
      this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Sử dụng";

      for (int var2 = 0; var2 < 5; ++var2) {
         if (this.C9_f135 + var2 < var1) {
            int[] var3;
            if (this.C9_f135 + var2 < this.C9_f123.consumableInventory.size()) {
               var3 = (int[]) this.C9_f123.consumableInventory.elementAt(this.C9_f135 + var2);
            } else {
               var3 = (int[]) this.C9_f123.equipmentInventory.elementAt(this.C9_f135 + var2 - this.C9_f123.consumableInventory.size());
            }

            if (this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5)
                     .getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer
                     .setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer.initializeSprite(258,
                     false, (byte) -1);
            }

            if (this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer == null) {
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5)
                     .getComponentData().focusedRenderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer
                     .setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer
                     .initializeSprite(258, false, (byte) -1);
            }

            this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer
                  .setSpriteIndex((int) ResourceManager.gameDatabase[4][var3[0]][1]);
            this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer
                  .setSpriteIndex((int) ResourceManager.gameDatabase[4][var3[0]][1]);
            this.C9_f122.currentDialog.getChildById(19 + var2 * 5).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.gameDatabase[4][var3[0]][0]);
            this.C9_f122.currentDialog.getChildById(20 + var2 * 5).getComponentData().text = "" + var3[1];
         } else {
            if (this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer != null) {
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer.cleanup();
            }

            this.C9_f122.currentDialog.getChildById(19 + var2 * 5).getComponentData().text = "";
            this.C9_f122.currentDialog.getChildById(20 + var2 * 5).getComponentData().text = "";
         }
      }

      if (var1 > 0) {
         if (this.C9_f136 < this.C9_f123.consumableInventory.size()) {
            this.C9_f122.currentDialog.getChildById(46).getComponentData().text = GameEngineBase.getLocalizedText(
                  (int) ResourceManager.gameDatabase[4][((int[]) this.C9_f123.consumableInventory.elementAt(this.C9_f136))[0]][2]);
         } else {
            this.C9_f122.currentDialog.getChildById(46).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.gameDatabase[4][((int[]) this.C9_f123.equipmentInventory
                        .elementAt(this.C9_f136 - this.C9_f123.consumableInventory.size()))[0]][2]);
         }
      } else {
         this.C9_f122.currentDialog.getChildById(46).getComponentData().text = "";
      }

      if (var1 > 0) {
         this.C9_f122.currentDialog.getChildById(43).setOffsetY(127 + this.C9_f136 * 72 / var1,
               this.C9_f122.currentDialog.getUIContainerComponent());
      } else {
         this.C9_f122.currentDialog.getChildById(43).setOffsetY(127,
               this.C9_f122.currentDialog.getUIContainerComponent());
      }
   }

   private void bt() {
      if (this.C9_f123.specialItems.size() > 5) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(86)).primaryListComponent.setDisplayMode(1);
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(86)).primaryListComponent.setDisplayMode(0);
      }

      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(86)).primaryListComponent.totalItemCount = this.C9_f123.specialItems.size();
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(86)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(86)).primaryListComponent.selectedIndex;
      this.C9_f122.currentDialog.getChildById(7).setVisible(false);

      for (int var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.specialItems.size()) {
            int[] var2 = (int[]) this.C9_f123.specialItems.elementAt(this.C9_f135 + var1);
            if (this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5)
                     .getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer
                     .setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer.initializeSprite(258,
                     false, (byte) -1);
            }

            if (this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer == null) {
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5)
                     .getComponentData().focusedRenderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer
                     .setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer
                     .initializeSprite(258, false, (byte) -1);
            }

            this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer
                  .setSpriteIndex((int) ResourceManager.gameDatabase[3][var2[0]][1]);
            this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer
                  .setSpriteIndex((int) ResourceManager.gameDatabase[3][var2[0]][1]);
            if (var2[0] == 17) {
               this.C9_f122.currentDialog.getChildById(99 + var1 * 5).getComponentData().text = "Chìa khóa vàng";
            } else {
               this.C9_f122.currentDialog.getChildById(99 + var1 * 5).getComponentData().text = GameEngineBase
                     .getLocalizedText((int) ResourceManager.gameDatabase[3][var2[0]][0]);
            }

            this.C9_f122.currentDialog.getChildById(100 + var1 * 5).getComponentData().text = "" + var2[1];
         } else {
            if (this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer != null) {
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer.cleanup();
            }

            this.C9_f122.currentDialog.getChildById(99 + var1 * 5).getComponentData().text = "";
            this.C9_f122.currentDialog.getChildById(100 + var1 * 5).getComponentData().text = "";
         }
      }

      if (this.C9_f123.specialItems.size() > 0) {
         this.C9_f122.currentDialog.getChildById(124).getComponentData().text = GameEngineBase.getLocalizedText(
               (int) ResourceManager.gameDatabase[3][((int[]) this.C9_f123.specialItems.elementAt(this.C9_f136))[0]][2]);
      } else {
         this.C9_f122.currentDialog.getChildById(124).getComponentData().text = "";
      }

      if (this.C9_f123.specialItems.size() > 0) {
         this.C9_f122.currentDialog.getChildById(123).setOffsetY(127 + this.C9_f136 * 72 / this.C9_f123.specialItems.size(),
               this.C9_f122.currentDialog.getUIContainerComponent());
      } else {
         this.C9_f122.currentDialog.getChildById(123).setOffsetY(127,
               this.C9_f122.currentDialog.getUIContainerComponent());
      }
   }

   private void bu() {
      if (this.C9_f123.skills.size() > 5) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(125)).primaryListComponent.setDisplayMode(1);
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(125)).primaryListComponent.setDisplayMode(0);
      }

      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(125)).primaryListComponent.totalItemCount = this.C9_f123.skills.size();
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(125)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(125)).primaryListComponent.selectedIndex;

      int var1;
      for (var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.skills.size()) {
            int[] var2 = (int[]) this.C9_f123.skills.elementAt(this.C9_f135 + var1);
            if (this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer == null) {
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5)
                     .getComponentData().renderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer
                     .setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer.initializeSprite(258,
                     false, (byte) -1);
            }

            if (this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().focusedRenderer == null) {
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5)
                     .getComponentData().focusedRenderer = new SpriteRenderer();
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().focusedRenderer
                     .setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5)
                     .getComponentData().focusedRenderer.spriteType = 2;
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().focusedRenderer
                     .initializeSprite(258, false, (byte) -1);
            }

            this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer
                  .setSpriteIndex((int) ResourceManager.gameDatabase[5][var2[0]][1]);
            this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().focusedRenderer
                  .setSpriteIndex((int) ResourceManager.gameDatabase[5][var2[0]][1]);
            this.C9_f122.currentDialog.getChildById(138 + var1 * 5).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.gameDatabase[5][var2[0]][0]);
            switch (var2[0]) {
               case 0:
                  if (this.C9_f123.isSkillActive(var2[0])) {
                     this.C9_f122.currentDialog.getChildById(163).getComponentData().text = GameEngineBase
                           .getLocalizedText((int) ResourceManager.gameDatabase[5][var2[0]][2]);
                     if (GameWorldManager.B().O()) {
                        this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "Hoàn thành";
                     } else {
                        this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "1 cái";
                     }
                  } else {
                     this.C9_f122.currentDialog.getChildById(163).getComponentData().text = GameEngineBase
                           .getLocalizedText((int) 634);
                     this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "0 cái";
                  }
                  break;
               default:
                  this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "";
            }
         } else {
            if (this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer != null) {
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer.cleanup();
            }

            this.C9_f122.currentDialog.getChildById(138 + var1 * 5).getComponentData().text = "";
            this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "";
         }
      }

      if (this.C9_f123.skills.size() > 0) {
         if ((var1 = ((int[]) this.C9_f123.skills.elementAt(this.C9_f136))[0]) != 0) {
            this.C9_f122.currentDialog.getChildById(163).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.gameDatabase[5][var1][2]);
            this.C9_f122.currentDialog.getChildById(7).setVisible(true);
         }

         if (var1 == 0) {
            if (((int[]) this.C9_f123.skills.elementAt(this.C9_f136))[1] == 1) {
               this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Đóng cửa";
            } else {
               this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Mở ra";
            }
         } else if (var1 <= 0 && var1 > 4) {
            if (var1 == 10) {
               this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Gia tốc";
            } else {
               this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Sử dụng";
            }
         } else if (this.C9_f123.currentVehicleType == var1 - 1) {
            this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Triệu hồi";
         } else {
            this.C9_f122.currentDialog.getChildById(7).getComponentData().text = "Triệu hoán";
         }
      } else {
         this.C9_f122.currentDialog.getChildById(163).getComponentData().text = "";
         this.C9_f122.currentDialog.getChildById(7).setVisible(false);
      }

      if (this.C9_f123.skills.size() > 0) {
         this.C9_f122.currentDialog.getChildById(162).setOffsetY(127 + this.C9_f136 * 72 / this.C9_f123.skills.size(),
               this.C9_f122.currentDialog.getUIContainerComponent());
      } else {
         this.C9_f122.currentDialog.getChildById(162).setOffsetY(127,
               this.C9_f122.currentDialog.getUIContainerComponent());
      }
   }

   public final void ac() {
      if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.g(this.C9_f126);
      } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.g(this.C9_f126);
      } else if (this.C9_f121.isKeyPressed(196640)) {
         this.bx();
      } else {
         if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(262144)) {
            this.C9_f121.changeState((byte) 8);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }

      }
   }

   public final void ad() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.g(this.C9_f125);
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.g(this.C9_f125);
      } else if (this.C9_f121.isKeyPressed(196640)) {
         this.C9_f123.assignSkillToPokemon(this.C9_f130, this.C9_f125);
         this.C9_f121.changeState((byte) 8);
      } else {
         if (this.C9_f121.isKeyPressed(262144)) {
            this.C9_f121.changeState((byte) 8);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }

      }
   }

   public final void ae() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.g(this.C9_f125);
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.g(this.C9_f125);
      } else {
         if (this.C9_f121.isKeyPressed(196640)) {
            if (this.C9_f131 == 0) {
               if (this.C9_f123.partyPokemon[this.C9_f125].getLevel() < 50) {
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Chỉ có thể cho 50 cấp sủng vật sử dụng", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 2;
                  return;
               }

               if (this.C9_f123.useSkillOnPokemon(this.C9_f130, this.C9_f125)) {
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Sử dụng thành công", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 1;
                  return;
               }
            } else {
               if (this.C9_f131 == 1) {
                  this.C9_f131 = 0;
                  this.C9_f121.changeState((byte) 8);
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  this.C9_f122.removeDialog("/data/ui/petstate.ui");
                  return;
               }

               if (this.C9_f131 == 2) {
                  this.C9_f131 = 0;
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  return;
               }
            }
         } else if (this.C9_f121.isKeyPressed(262144) && this.C9_f131 == 0) {
            this.C9_f121.changeState((byte) 8);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }

      }
   }

   public final void af() {
      this.C9_f121.updateActionSequence();
      if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(16400) && !this.j()
            && !GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 1)) {
         this.C9_f122.currentDialog.handleAction(7);
         this.C9_f122.currentDialog.handleAction(2);
         this.C9_f122.currentDialog.handleAction(5);
         this.bq();
         this.C9_f121.handleActionResponse();
      } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(32832) && !this.j()
            && !GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 1)) {
         this.C9_f122.currentDialog.handleAction(7);
         this.C9_f122.currentDialog.handleAction(3);
         this.C9_f122.currentDialog.handleAction(5);
         this.bq();
         this.C9_f121.handleActionResponse();
      } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(4100) && !this.j()
            && !GameEngineBase.isActionBlocked((int) this.C9_f136, (int) 0)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(8448) && !this.j()
            && !GameEngineBase.isActionBlocked((int) this.C9_f136, (int) 0)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.isKeyPressed(196640) && !this.j() && GameEngineBase.isConfirmAllowed()) {
         int var5;
         if (this.C9_f131 == 0) {
            if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.C9_f136, (int) 0)) {
               return;
            }

            int[] var1;
            label206: switch (this.C9_f125) {
               case 0:
                  if (this.C9_f136 >= this.C9_f123.consumableInventory.size()) {
                     if (this.C9_f123.equipmentInventory.size() <= 0) {
                        return;
                     }

                     var1 = (int[]) this.C9_f123.equipmentInventory.elementAt(this.C9_f136 - this.C9_f123.consumableInventory.size());
                  } else {
                     var1 = (int[]) this.C9_f123.consumableInventory.elementAt(this.C9_f136);
                  }

                  switch (var1[0]) {
                     case 0:
                     case 1:
                     case 2:
                     case 3:
                        if (this.C9_f131 == 0) {
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Không thể sử dụng", "Nhấn nút 5 tiếp tục");
                           this.C9_f131 = 1;
                        } else {
                           this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                           this.C9_f131 = 0;
                        }
                        break label206;
                     case 4:
                     case 5:
                     case 6:
                     case 7:
                     case 8:
                     case 9:
                     case 10:
                     case 11:
                     case 12:
                     default:
                        this.C9_f130 = var1[0];
                        this.C9_f121.changeState((byte) 17);
                        this.C9_f122.removeDialog("/data/ui/bag.ui");
                        break label206;
                     case 13:
                        if (this.C9_f131 == 0) {
                           if (this.C9_f123.brightnessEffectDuration <= 0) {
                              if (GameWorldManager.B().C25_f290 == 3 && GameWorldManager.B().C25_f291 == 7) {
                                 this.H();
                                 this.a("Nơi này không cách nào sử dụng tránh quái hoàn", "Nhấn nút 5 tiếp tục");
                                 this.C9_f131 = 1;
                              } else if (this.C9_f123.hasItem((int) var1[0], (int) 1, (byte) 0)) {
                                 this.C9_f123.removeItem(var1[0], 1, (byte) 0);
                                 this.C9_f123.brightnessEffectDuration = ResourceManager.gameDatabase[4][var1[0]][6];
                                 this.C9_f123.effectDuration = 0;
                                 var5 = this.C9_f123.consumableInventory.size() + this.C9_f123.equipmentInventory.size();
                                 if (this.C9_f136 >= var5) {
                                    this.C9_f136 = var5 - 1;
                                    ((UIContainerComponent) this.C9_f122.currentDialog
                                          .getChildById(8)).primaryListComponent.selectedIndex = this.C9_f136;
                                 }

                                 if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
                                    --this.C9_f135;
                                    ((UIContainerComponent) this.C9_f122.currentDialog
                                          .getChildById(8)).primaryListComponent.scrollOffset = this.C9_f135;
                                 }

                                 this.bs();
                                 this.H();
                                 this.C9_f123.applyBrightnessEffect(1);
                                 this.a("Thành công sử dụng đạo cụ, cũng có thời gian ngắn tránh quái hiệu quả",
                                       "Nhấn nút 5 tiếp tục");
                                 this.C9_f131 = 1;
                              }
                           } else {
                              this.H();
                              this.a("Đã có được thời gian ngắn tránh quái hiệu quả", "Nhấn nút 5 tiếp tục");
                              this.C9_f131 = 1;
                           }
                        }
                        break label206;
                     case 14:
                        if (this.C9_f131 == 0) {
                           if (!this.C9_f123.isSkillActive(0) || (this.C9_f123.pokedexCount != 0 || GameWorldManager.C25_f314 >= 10)
                                 && (this.C9_f123.pokedexCount <= 0 || GameWorldManager.C25_f314 >= 30)) {
                              this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                              this.a("Không có trứng có thể ấp trứng", "Nhấn nút 5 tiếp tục");
                              this.C9_f131 = 1;
                           } else if (this.C9_f123.hasItem((int) var1[0], (int) 1, (byte) 0)) {
                              if (this.C9_f123.pokedexCount == 0) {
                                 GameWorldManager.C25_f314 = 10;
                              } else {
                                 GameWorldManager.C25_f314 = 30;
                              }

                              this.C9_f123.removeItem(var1[0], 1, (byte) 0);
                              var5 = this.C9_f123.consumableInventory.size() + this.C9_f123.equipmentInventory.size();
                              if (this.C9_f136 >= var5) {
                                 this.C9_f136 = var5 - 1;
                                 ((UIContainerComponent) this.C9_f122.currentDialog
                                       .getChildById(8)).primaryListComponent.selectedIndex = this.C9_f136;
                              }

                              if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
                                 --this.C9_f135;
                                 ((UIContainerComponent) this.C9_f122.currentDialog
                                       .getChildById(8)).primaryListComponent.scrollOffset = this.C9_f135;
                              }

                              this.bs();
                              this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                              this.a("Thành công sử dụng, tranh thủ thời gian đi ấp trứng trứng sủng vật a!",
                                    "Nhấn nút 5 tiếp tục");
                              this.C9_f131 = 1;
                           }
                        }
                        break label206;
                  }
               case 3:
                  switch ((var1 = (int[]) this.C9_f123.skills.elementAt(this.C9_f136))[0]) {
                     case 0:
                        if (this.C9_f123.isSkillActive(var1[0])) {
                           if (GameWorldManager.B().O()) {
                              if (this.C9_f123.getStorageStatus() == 2) {
                                 this.H();
                                 this.a("Không gian không đủ, hãy thanh lý lại không gian ấp trứng",
                                       "Nhấn nút 5 tiếp tục");
                                 this.C9_f131 = 1;
                              } else {
                                 GameWorldManager.C25_f314 = 0;
                                 if (GameWorldManager.B().C25_f348.questStates[GameWorldManager.e(4, 5)] != null) {
                                    GameWorldManager.B().C25_f348.questStates[GameWorldManager.e(4, 5)][15] = 4;
                                    if (GameWorldManager.B().C25_f290 == 4 && GameWorldManager.B().C25_f291 == 5) {
                                       GameWorldManager.B().C25_f348.eventScripts[15].setExecutionState((byte) 4);
                                    }
                                 }

                                 this.C9_f123.resetSkillUsage(var1[0]);
                                 this.bu();
                                 this.H();
                                 this.a("Ấp trứng thành công", "Nhấn nút 5 tiếp tục");
                                 this.C9_f131 = 2;
                              }
                           } else {
                              this.H();
                              this.a("Vẫn chưa thể ấp trứng", "Nhấn nút 5 tiếp tục");
                              this.C9_f131 = 1;
                           }
                        }
                     case 1:
                     case 2:
                     case 3:
                     case 4:
                     default:
                        break;
                     case 5:
                        this.C9_f121.changeState((byte) 11);
                        this.C9_f122.removeDialog("/data/ui/bag.ui");
                        break;
                     case 6:
                        this.C9_f121.changeState((byte) 12);
                        this.C9_f122.removeDialog("/data/ui/bag.ui");
                        break;
                     case 7:
                     case 8:
                     case 9:
                        this.C9_f130 = var1[0];
                        this.C9_f121.changeState((byte) 19);
                        this.C9_f122.removeDialog("/data/ui/bag.ui");
                        break;
                     case 10:
                        this.C9_f121.changeState((byte) 24);
                        this.C9_f122.removeDialog("/data/ui/bag.ui");
                  }
            }
         } else if (this.C9_f131 == 1 || this.C9_f131 == 2) {
            if (this.C9_f131 != 2) {
               this.C9_f121.handleActionResponse();
               this.C9_f131 = 0;
            } else {
               if (this.C9_f123.pokedexCount == 0) {
                  byte var6 = this.h(58);
                  this.C9_f123.addToPokedex((short) 58);
                  if (var6 == 0) {
                     this.c("Ấp trứng tìm được #2"
                           + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][58][0])
                           + "#0 để vào ba lô");
                  } else if (var6 == 1) {
                     this.c("Ấp trứng tìm được #2"
                           + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][58][0])
                           + "#0 để vào ngân hàng");
                  } else {
                     this.c("Không có không gian, đã phóng sinh");
                  }
               } else {
                  var5 = GameUtils.findFirstGreaterOrEqual(new int[] { 76, 52, 28, 4, 0 }, GameUtils.getRandomInt(100));
                  short[] var2 = new short[] { 0, 56, 58, 95, 72 };
                  byte var3 = this.h(var2[var5]);

                  int var4;
                  for (var4 = 0; var4 < this.C9_f123.pokedexCount && this.C9_f123.pokedexEntries[var4] != var2[var5]; ++var4) {
                  }

                  if (var4 >= this.C9_f123.pokedexCount) {
                     this.C9_f123.addToPokedex(var2[var5]);
                  }

                  if (var3 == 0) {
                     this.c("Ấp trứng tìm được #2"
                           + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][var2[var5]][0])
                           + "#0 để vào ba lô");
                  } else if (var3 == 1) {
                     this.c("Ấp trứng tìm được #2"
                           + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][var2[var5]][0])
                           + "#0 để vào ngân hàng");
                  } else {
                     this.c("Không có không gian, đã phóng sinh");
                  }
               }

               this.C9_f131 = 3;
            }

            this.I();
         }
      } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(262144) && !this.j()
            && GameEngineBase.isCancelAllowed()) {
         if (GameEngineBase.paymentActive) {
            this.C9_f125 = 2;
         } else {
            this.C9_f125 = 1;
         }

         this.C9_f121.changeState((byte) 6);
         this.C9_f122.removeDialog("/data/ui/bag.ui");
      }

      if (this.C9_f131 == 3 && !this.j()) {
         this.C9_f121.handleActionResponse();
         this.br();
         this.C9_f131 = 0;
      }

      this.f();
      this.C9_f132 = true;
   }

   private byte h(int var1) {
      int[][] var2 = new int[][] { { 60, 20, 0 }, { 75, 50, 20, 0 } };
      byte var3 = -1;
      byte var4 = 0;
      if (ResourceManager.gameDatabase[0][var1][4] == 5) {
         if (ResourceManager.gameDatabase[0][var1][3] == 2) {
            var3 = 1;
            var4 = 2;
         } else if (ResourceManager.gameDatabase[0][var1][3] == 3) {
            var3 = 0;
            var4 = 3;
         }
      }

      int var5 = ResourceManager.gameDatabase[0][var1][1] * 10;
      short var6 = ResourceManager.gameDatabase[1][var5][5];
      byte var7 = this.C9_f123.getStorageStatus();
      if (var3 == -1) {
         if (var7 == 0) {
            this.C9_f123.createPokemon(var1, 5, (short) -1, (byte) 2, (short) -1, (byte) -1, new int[] { 1, var5, var6 });
         } else if (var7 == 1) {
            int var8 = GameUtils.getRandomInRange(ResourceManager.gameDatabase[0][var1][3],
                  ResourceManager.gameDatabase[0][var1][3]);
            this.C9_f123.addPokemonToStorage(var1, 5, (short) -1, (byte) 2, (byte) var8, (byte) -1, PokemonEntity.calculateMaxHp(var1, 5, var8), 0, -1,
                  new int[] { 1, var5, var6 });
         }
      } else {
         byte var9 = (byte) (var4 + (byte) GameUtils.findFirstGreaterOrEqual(var2[var3], GameUtils.getRandomInt(100)));
         if (var7 == 0) {
            this.C9_f123.createPokemon(var1, 5, (short) -1, (byte) 2, (short) var9, (byte) -1, new int[] { 1, var5, var6 });
         } else if (var7 == 1) {
            this.C9_f123.addPokemonToStorage(var1, 5, (short) -1, (byte) 2, (short) var9, (byte) -1, PokemonEntity.calculateMaxHp(var1, 5, var9), 0,
                  -1, new int[] { 1, var5, var6 });
         }
      }

      return var7;
   }

   public final void ag() {
      this.aU();
      this.C9_f122.showDialog("/data/ui/ride.ui", 257, this);
      this.C9_f125 = 0;
      this.bv();
   }

   private void bv() {
      for (int var1 = 0; var1 < 4; ++var1) {
         if (this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().renderer.spriteType = 3;
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().renderer.initializeSprite(260, false,
                  (byte) -1);
         }

         if (this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer.setSpriteIndex((int) 131);
            this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().renderer.initializeSprite(257, false,
                  (byte) 0);
         }

         if (this.C9_f123.hasVehicle(var1)) {
            if (this.C9_f125 == var1) {
               this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().renderer
                     .setAnimationFrame((byte) var1, (byte) -1);
               if (this.C9_f125 == 0) {
                  this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().text = "Lục đi điểu";
               } else if (this.C9_f125 == 1) {
                  this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().text = "Hư không hành giả";
               } else if (this.C9_f125 == 2) {
                  this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().text = "Hải âu";
               } else if (this.C9_f125 == 3) {
                  this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().text = "Nham sơn long";
               }
            } else {
               this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().renderer
                     .setAnimationFrame((byte) (var1 + 8), (byte) -1);
               this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().text = "";
            }

            if (!this.C9_f123.isVehicleUsable(var1)) {
               this.C9_f122.currentDialog.getChildById(var1 + 16).setVisible(true);
            } else {
               this.C9_f122.currentDialog.getChildById(var1 + 16).setVisible(false);
            }
         } else {
            this.C9_f122.currentDialog.getChildById(var1 + 16).setVisible(false);
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().renderer
                  .setAnimationFrame((byte) (var1 + 4), (byte) -1);
            this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().text = "";
         }
      }

   }

   public final void ah() {
      if (!this.j() && this.C9_f121.isKeyPressed(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
      } else if (!this.j() && this.C9_f121.isKeyPressed(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
      } else if (!this.j() && this.C9_f121.isKeyPressed(512)) {
         this.C9_f122.removeDialog("/data/ui/ride.ui");
         this.C9_f121.changeState((byte) 0);
      } else if (!this.j() && this.C9_f121.isKeyPressed(196640)) {
         if (this.C9_f123.hasVehicle(this.C9_f125)) {
            if (this.C9_f123.isVehicleUsable(this.C9_f125)) {
               this.C9_f123.activateVehicle(this.C9_f125);
               this.C9_f122.removeDialog("/data/ui/ride.ui");
               this.C9_f121.changeState((byte) 0);
            } else {
               this.b("Nơi này không thể sử dụng sủng vật cưỡi");
            }
         } else {
            this.b("Chưa có sủng vật cưỡi này");
         }
      } else if (!this.j() && this.C9_f121.isKeyPressed(262144)) {
         this.C9_f122.removeDialog("/data/ui/ride.ui");
         this.C9_f121.changeState((byte) 0);
      }

      this.f();
      this.C9_f132 = true;
   }

   public final void a(PokemonEntity var1, PokemonEntity var2) {
      this.C9_f122.showDialog("/data/ui/battle.ui", 257, this);
      this.C9_f124 = 0;
      this.C9_f129 = 0;
      this.a(var1, false);
      this.b(var2, false);
      this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "100%";
      this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "100%";
      ((BattleSystemManager) this.C9_f121).D();
      this.C9_f122.removeDialog("/data/ui/world.ui");
   }

   public final void b(PokemonEntity var1, PokemonEntity var2) {
      if (var1.getTypeAdvantage(var2) == 0) {
         if (var1.getBattlePosition() == 0) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "300%";
            this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "60%";
         } else {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "60%";
            this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "300%";
         }
      } else if (var1.getTypeAdvantage(var2) == 1) {
         if (var1.getBattlePosition() == 0) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "60%";
            this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "300%";
         } else {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "300%";
            this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "60%";
         }
      } else {
         this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "100%";
         this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "100%";
      }
   }

   public final void a(PokemonEntity var1, PokemonEntity var2, PokemonEntity var3, int var4, int var5) {
      if (var1.getTypeAdvantage(var2) == 0) {
         if (var3.getBattlePosition() == 0) {
            if ((var4 *= 200 / var5) == var5 && var4 != 200) {
               var4 = 200;
            }

            this.C9_f122.currentDialog.getChildById(59).getComponentData().text = var4 + 100 + "%";
            return;
         }

         if (var3.getBattlePosition() == 1) {
            if ((var4 *= 40 / var5) == var5 && var4 != 40) {
               var4 = 40;
            }

            this.C9_f122.currentDialog.getChildById(58).getComponentData().text = 100 - var4 + "%";
            return;
         }
      } else if (var1.getTypeAdvantage(var2) == 1) {
         if (var3.getBattlePosition() == 0) {
            if ((var4 *= 40 / var5) == var5 && var4 != 40) {
               var4 = 40;
            }

            this.C9_f122.currentDialog.getChildById(59).getComponentData().text = 100 - var4 + "%";
            return;
         }

         if (var3.getBattlePosition() == 1) {
            if ((var4 *= 200 / var5) == var5 && var4 != 200) {
               var4 = 200;
            }

            this.C9_f122.currentDialog.getChildById(58).getComponentData().text = var4 + 100 + "%";
            return;
         }
      } else {
         this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "100%";
         this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "100%";
      }

   }

   public final void a(PokemonEntity var1, PokemonEntity var2, int var3, int var4) {
      this.C9_f145 = this.C9_f146 = 0;
      if (var1.getTypeAdvantage(var2) == 0) {
         this.C9_f145 += var3 * (200 / var4);
         if (this.C9_f145 == var4 && this.C9_f145 != 200) {
            this.C9_f145 = 200;
         }

         this.C9_f122.currentDialog.getChildById(59).getComponentData().text = 100 + this.C9_f145 + "%";
         this.C9_f146 += var3 * (40 / var4);
         if (this.C9_f146 == var4 && this.C9_f146 != 40) {
            this.C9_f146 = 40;
         }

         this.C9_f122.currentDialog.getChildById(58).getComponentData().text = 100 - this.C9_f146 + "%";
      } else if (var1.getTypeAdvantage(var2) == 1) {
         this.C9_f145 += var3 * (40 / var4);
         if (this.C9_f145 == var4 && this.C9_f145 != 40) {
            this.C9_f145 = 40;
         }

         this.C9_f122.currentDialog.getChildById(59).getComponentData().text = 100 - this.C9_f145 + "%";
         this.C9_f146 += var3 * (200 / var4);
         if (this.C9_f146 == var4 && this.C9_f146 != 200) {
            this.C9_f146 = 200;
         }

         this.C9_f122.currentDialog.getChildById(58).getComponentData().text = 100 + this.C9_f146 + "%";
      } else {
         this.C9_f122.currentDialog.getChildById(59).getComponentData().text = "100%";
         this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "100%";
      }
   }

   public final boolean a(PokemonEntity var1, boolean var2) {
      int var3 = 0;
      byte var7;
      if (this.C9_f147 == 0) {
         int var10000 = var1.getDisplayHp();
         var7 = 1;
         if ((var3 = Math.abs(var10000 - var1.secondaryStates[var7]) / 11) <= 1) {
            var3 = 1;
         }
      }

      int var4 = var1.getDisplayHp();
      var7 = 1;
      short var5 = var1.secondaryStates[var7];
      if (var4 != var5) {
         ++this.C9_f148;
         if (this.C9_f148 < 4) {
            if (var2) {
               this.C9_f122.currentDialog.getChildById(55).getComponentData().text = "#P" + var1.getHpPercent();
               this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getDisplayHpPercent();
            } else {
               this.C9_f122.currentDialog.getChildById(55).getComponentData().text = "#P" + var1.getDisplayHpPercent();
               this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getHpPercent();
            }

            return false;
         }
      }

      this.C9_f147 += var3;
      if (var2) {
         if ((var4 += this.C9_f147) >= var5) {
            var4 = var5;
         }

         var1.setDisplayHp(var4);
         this.C9_f122.currentDialog.getChildById(41).getComponentData().text = "#P" + var1.getHpPercent();
         this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getDisplayHpPercent();
         this.C9_f122.currentDialog.getChildById(55).getComponentData().text = "#P" + var1.getDisplayHpPercent();
      } else {
         if ((var4 -= this.C9_f147) <= var5) {
            var4 = var5;
         }

         var1.setDisplayHp(var4);
         this.C9_f122.currentDialog.getChildById(41).getComponentData().text = "#P" + var1.getDisplayHpPercent();
         this.C9_f122.currentDialog.getChildById(55).getComponentData().text = "#P" + var1.getHpPercent();
         this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getHpPercent();
      }

      ComponentData var8 = this.C9_f122.currentDialog.getChildById(38).getComponentData();
      StringBuffer var10001 = (new StringBuffer()).append(var1.getDisplayHp()).append("/");
      var7 = 1;
      var8.text = var10001.append(var1.primaryStates[var7]).toString();
      this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.getExpPercent();
      this.C9_f122.currentDialog.getChildById(40).getComponentData().text = var1.getCurrentExp() + "/" + var1.getExpToNextLevel();
      this.C9_f122.currentDialog.getChildById(12).getComponentData().text = GameEngineBase
            .getLocalizedText(var1.getDatabaseValue((byte) 0));
      this.C9_f122.currentDialog.getChildById(13).getComponentData().text = "lv" + var1.getLevel();
      this.C9_f122.currentDialog.getChildById(17).getComponentData().renderer.setSpriteIndex(94 + var1.getDatabaseValue((byte) 1));
      if (var4 == var5) {
         this.C9_f147 = 0;
         this.C9_f148 = 0;
         this.C9_f149 = 0;
         return true;
      } else {
         return false;
      }
   }

   public final void a(PokemonEntity var1) {
      int var2;
      for (var2 = 0; var2 < 6; ++var2) {
         if (this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().renderer.initializeSprite(325, false,
                  (byte) 0);
         }

         if (this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().renderer.setSpriteIndex((int) 145);
            this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().renderer.initializeSprite(257, false,
                  (byte) 0);
         }

         this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().renderer.setSpriteIndex((int) 145);
         this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().renderer.setSpriteIndex((int) 0);
      }

      for (var2 = 0; var2 < 3; ++var2) {
         short[] var10002;
         if (var1.activeStatusEffects[0][var2] != -1 && var1.buffs[var1.activeStatusEffects[0][var2]][0] > 0) {
            var10002 = var1.buffs[var1.activeStatusEffects[0][var2]];
            this.C9_f122.currentDialog.getChildById(43 + this.C9_f149).getComponentData().renderer
                  .setSpriteIndex(134 + var10002[0]);
            this.C9_f122.currentDialog.getChildById(26 + this.C9_f149).getComponentData().renderer
                  .setSpriteIndex(var1.activeStatusEffects[0][var2] + 12);
            ++this.C9_f149;
         }

         if (var1.activeStatusEffects[1][var2] != -1 && var1.debuffs[var1.activeStatusEffects[1][var2]][0] > 0) {
            var10002 = var1.debuffs[var1.activeStatusEffects[1][var2]];
            this.C9_f122.currentDialog.getChildById(43 + this.C9_f149).getComponentData().renderer
                  .setSpriteIndex(134 + var10002[0]);
            this.C9_f122.currentDialog.getChildById(26 + this.C9_f149).getComponentData().renderer
                  .setSpriteIndex(var1.activeStatusEffects[1][var2] + 1);
            ++this.C9_f149;
         }
      }

   }

   private void g(PokemonEntity var1) {
      this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getHpPercent();
      ComponentData var10000 = this.C9_f122.currentDialog.getChildById(38).getComponentData();
      StringBuffer var10001 = (new StringBuffer()).append(var1.getDisplayHp()).append("/");
      byte var3 = 1;
      var10000.text = var10001.append(var1.primaryStates[var3]).toString();
      this.C9_f122.currentDialog.getChildById(16).getComponentData().text = "lv" + var1.getLevel();
   }

   public final boolean b(PokemonEntity var1, boolean var2) {
      int var3 = 0;
      byte var7;
      if (this.C9_f147 == 0) {
         int var10000 = var1.getDisplayHp();
         var7 = 1;
         if ((var3 = Math.abs(var10000 - var1.secondaryStates[var7]) / 11) <= 1) {
            var3 = 1;
         }
      }

      int var4 = var1.getDisplayHp();
      var7 = 1;
      short var5 = var1.secondaryStates[var7];
      if (var4 != var5) {
         ++this.C9_f148;
         if (this.C9_f148 < 4) {
            if (var2) {
               this.C9_f122.currentDialog.getChildById(56).getComponentData().text = "#P" + var1.getHpPercent();
               this.C9_f122.currentDialog.getChildById(14).getComponentData().text = "#P" + var1.getDisplayHpPercent();
            } else {
               this.C9_f122.currentDialog.getChildById(56).getComponentData().text = "#P" + var1.getDisplayHpPercent();
               this.C9_f122.currentDialog.getChildById(14).getComponentData().text = "#P" + var1.getHpPercent();
            }

            return false;
         }
      }

      this.C9_f147 += var3;
      if (var2) {
         if ((var4 += this.C9_f147) >= var5) {
            var4 = var5;
         }

         var1.setDisplayHp(var4);
         this.C9_f122.currentDialog.getChildById(42).getComponentData().text = "#P" + var1.getHpPercent();
         this.C9_f122.currentDialog.getChildById(14).getComponentData().text = "#P" + var1.getDisplayHpPercent();
         this.C9_f122.currentDialog.getChildById(56).getComponentData().text = "#P" + var1.getDisplayHpPercent();
      } else {
         if ((var4 -= this.C9_f147) <= var5) {
            var4 = var5;
         }

         var1.setDisplayHp(var4);
         this.C9_f122.currentDialog.getChildById(42).getComponentData().text = "#P" + var1.getDisplayHpPercent();
         this.C9_f122.currentDialog.getChildById(14).getComponentData().text = "#P" + var1.getHpPercent();
         this.C9_f122.currentDialog.getChildById(56).getComponentData().text = "#P" + var1.getHpPercent();
      }

      ComponentData var8 = this.C9_f122.currentDialog.getChildById(39).getComponentData();
      StringBuffer var10001 = (new StringBuffer()).append(var1.getDisplayHp()).append("/");
      var7 = 1;
      var8.text = var10001.append(var1.primaryStates[var7]).toString();
      if (this.C9_f123.getCreatureStatus((byte) var1.getDatabaseValue((byte) 1), var1.getSpeciesId()) == 2) {
         this.C9_f122.currentDialog.getChildById(19).getComponentData().renderer.setSpriteIndex((int) 101);
      } else {
         this.C9_f122.currentDialog.getChildById(19).getComponentData().renderer.setSpriteIndex((int) 102);
      }

      this.C9_f122.currentDialog.getChildById(15).getComponentData().text = GameEngineBase
            .getLocalizedText(var1.getDatabaseValue((byte) 0));
      this.C9_f122.currentDialog.getChildById(16).getComponentData().text = "lv" + var1.getLevel();
      this.C9_f122.currentDialog.getChildById(18).getComponentData().renderer.setSpriteIndex(94 + var1.getDatabaseValue((byte) 1));
      if (var4 == var5) {
         this.C9_f147 = 0;
         this.C9_f148 = 0;
         this.C9_f149 = 0;
         return true;
      } else {
         return false;
      }
   }

   public final void b(PokemonEntity var1) {
      int var2;
      for (var2 = 0; var2 < 6; ++var2) {
         if (this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().renderer.initializeSprite(325, false,
                  (byte) 0);
         }

         if (this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().renderer.setSpriteIndex((int) 145);
            this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().renderer.initializeSprite(257, false,
                  (byte) 0);
         }

         this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().renderer.setSpriteIndex((int) 145);
         this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().renderer.setSpriteIndex((int) 0);
      }

      for (var2 = 0; var2 < 3; ++var2) {
         short[] var10002;
         if (var1.activeStatusEffects[0][var2] != -1 && var1.buffs[var1.activeStatusEffects[0][var2]][0] > 0) {
            var10002 = var1.buffs[var1.activeStatusEffects[0][var2]];
            this.C9_f122.currentDialog.getChildById(49 + this.C9_f149).getComponentData().renderer
                  .setSpriteIndex(134 + var10002[0]);
            this.C9_f122.currentDialog.getChildById(32 + this.C9_f149).getComponentData().renderer
                  .setSpriteIndex(var1.activeStatusEffects[0][var2] + 12);
            ++this.C9_f149;
         }

         if (var1.activeStatusEffects[1][var2] != -1 && var1.debuffs[var1.activeStatusEffects[1][var2]][0] > 0) {
            var10002 = var1.debuffs[var1.activeStatusEffects[1][var2]];
            this.C9_f122.currentDialog.getChildById(49 + this.C9_f149).getComponentData().renderer
                  .setSpriteIndex(134 + var10002[0]);
            this.C9_f122.currentDialog.getChildById(32 + this.C9_f149).getComponentData().renderer
                  .setSpriteIndex(var1.activeStatusEffects[1][var2] + 1);
            ++this.C9_f149;
         }
      }

   }

   public final void ai() {
      this.C9_f124 = 0;
      this.C9_f139 = null;
      this.C9_f122.removeDialog("/data/ui/battle.ui");
   }

   public final void aj() {
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).secondaryListComponent.selectedIndex = this.C9_f124;
      this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(true);
   }

   public final void c(PokemonEntity var1) {
      this.C9_f131 = 0;
      this.a(var1, false);
      this.aj();
   }

   public final void d(PokemonEntity var1) {
      ((BattleSystemManager) this.C9_f121).q();
      if (!GameEngineBase.isActionBlocked((int) this.C9_f124, (int) 1) && this.C9_f131 == 0 && !this.j()
            && this.C9_f121.isKeyPressed(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
      } else if (!GameEngineBase.isActionBlocked((int) this.C9_f124, (int) 1) && this.C9_f131 == 0 && !this.j()
            && this.C9_f121.isKeyPressed(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
      } else if (!this.j() && this.C9_f121.isKeyPressed(196640)) {
         switch (this.C9_f124) {
            case 0:
               this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
               this.C9_f121.changeState((byte) 3);
               break;
            case 1:
               if (((BattleSystemManager) this.C9_f121).C29_f398 == 2) {
                  this.b("Trận chiến này không cho bắt sủng vật");
               } else if (this.C9_f123.getStorageStatus() == 2) {
                  this.b("Không gian không đủ, không cách nào bắt được");
               } else {
                  this.C9_f125 = 0;
                  this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
                  ((BattleSystemManager) this.C9_f121).r();
                  this.C9_f121.changeState((byte) 21);
               }
               break;
            case 2:
               if (this.C9_f131 == 0) {
                  if (var1.hasDebuff(2)) {
                     this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                     this.a("Trạng thái bị quấn, không thể sử dụng đạo cụ", "Nhấn nút 5 tiếp tục");
                     this.C9_f131 = 1;
                  } else {
                     this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
                     this.C9_f121.changeState((byte) 4);
                  }
               } else {
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  this.C9_f131 = 0;
               }
               break;
            case 3:
               if (this.C9_f131 == 0) {
                  if (var1.hasDebuff(2)) {
                     this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                     this.a("Trạng thái bị quấn, không thể đổi sủng vật", "Nhấn nút 5 tiếp tục");
                     this.C9_f131 = 1;
                  } else {
                     this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
                     ((BattleSystemManager) this.C9_f121).C29_f407 = ((BattleSystemManager) this.C9_f121).C29_f404[((BattleSystemManager) this.C9_f121).C29_f410];
                     BattleSystemManager.B().C29_f415 = true;
                     this.C9_f121.changeState((byte) 5);
                  }
               } else {
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  this.C9_f131 = 0;
               }
               break;
            case 4:
               this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
               this.C9_f121.changeState((byte) 11);
               break;
            case 5:
               if (this.C9_f131 == 0) {
                  if (var1.hasDebuff(2)) {
                     this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                     this.a("Trạng thái bị quấn, không thể chạy trốn", "Nhấn nút 5 tiếp tục");
                     this.C9_f131 = 1;
                  } else if (((BattleSystemManager) this.C9_f121).C29_f398 <= 0 && QuestManager.isMapControl) {
                     boolean var3 = false;
                     if (((BattleSystemManager) this.C9_f121).C29_f408
                           .getLevel() > ((BattleSystemManager) this.C9_f121).C29_f402[0].getLevel()) {
                        var3 = true;
                     } else if (((BattleSystemManager) this.C9_f121).C29_f408
                           .getLevel() == ((BattleSystemManager) this.C9_f121).C29_f402[0].getLevel()) {
                        if (GameUtils.getRandomInt(100) <= 95) {
                           var3 = true;
                        }
                     } else {
                        int var2 = ((BattleSystemManager) this.C9_f121).C29_f402[0].getLevel()
                              - ((BattleSystemManager) this.C9_f121).C29_f408.getLevel();
                        if ((var2 = 95 - var2 * 10) <= 15) {
                           var2 = 15;
                        }

                        if (GameUtils.getRandomInt(100) < var2) {
                           var3 = true;
                        }
                     }

                     if (var3) {
                        this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
                        GameScreenManager.getInstance().changeState((byte) 10);
                     } else {
                        this.C9_f131 = 2;
                        this.b("Chạy trốn thất bại");
                     }
                  } else {
                     this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
                     this.C9_f131 = 3;
                     this.b("Trận chiến này không thể trốn chạy");
                  }
               } else {
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  this.C9_f131 = 0;
               }
         }
      }

      this.f();
      if (this.C9_f131 >= 2 && this.aA()) {
         if (this.C9_f131 == 2) {
            ((BattleSystemManager) this.C9_f121).C29_f408.isInBattle = true;
            ++((BattleSystemManager) this.C9_f121).C29_f410;
            this.C9_f121.changeState((byte) 1);
         } else {
            this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(true);
         }

         this.C9_f131 = 0;
      }

   }

   public final void e(PokemonEntity var1) {
      this.C9_f122.showDialog("/data/ui/choiceskill.ui", 257, this);
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = var1
            .getSkillCount();
      if (this.C9_f129 >= var1.getSkillCount()) {
         this.C9_f129 = var1.getSkillCount() - 1;
      }

      if (var1.getSkillCount() > 5) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(-1);
      }

      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Sử dụng";
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f129;
      this.h(var1);
      this.C9_f131 = 0;
   }

   private void h(PokemonEntity var1) {
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;
      int var2 = var1.getSkillCount();

      for (int var3 = 0; var3 < 5; ++var3) {
         if (var3 >= var2) {
            this.C9_f122.currentDialog.getChildById(13 + var3 * 5).getComponentData().text = "";
            this.C9_f122.currentDialog.getChildById(14 + var3 * 5).getComponentData().text = "";
         } else {
            this.C9_f122.currentDialog.getChildById(13 + var3 * 5).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.gameDatabase[1][var1.getSkillAt(this.C9_f135 + var3)][1]);
            this.C9_f122.currentDialog.getChildById(14 + var3 * 5)
                  .getComponentData().text = var1.skillPP[this.C9_f135 + var3] + "/"
                        + ResourceManager.gameDatabase[1][var1.getSkillAt(this.C9_f135 + var3)][5];
         }
      }

      this.i(var1.skills[this.C9_f129]);
      this.C9_f122.currentDialog.getChildById(51).setOffsetY(98 + this.C9_f136 * 80 / var2,
            this.C9_f122.currentDialog.getUIContainerComponent());
   }

   private void i(int var1) {
      this.C9_f122.currentDialog.getChildById(53).getComponentData().text = GameEngineBase
            .getLocalizedText((int) ResourceManager.gameDatabase[1][var1][2]);
   }

   public final void f(PokemonEntity var1) {
      if (this.aB()) {
         if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(4100)) {
            this.C9_f122.currentDialog.handleAction(0);
            this.h(var1);
         } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(8448)) {
            this.C9_f122.currentDialog.handleAction(1);
            this.h(var1);
         } else if (this.C9_f121.isKeyPressed(196640)) {
            if (this.C9_f131 == 0) {
               if (var1.hasSkillPP(this.C9_f129)) {
                  this.C9_f122.removeDialog("/data/ui/choiceskill.ui");
                  ((BattleSystemManager) this.C9_f121).d(var1.skills[this.C9_f129]);
                  int var10000 = ((BattleSystemManager) this.C9_f121).C29_f397;
                  ((BattleSystemManager) this.C9_f121).getClass();
                  if (var10000 == 0) {
                     ((BattleSystemManager) this.C9_f121).G();
                  } else {
                     this.C9_f121.changeState((byte) 6);
                  }
               } else {
                  this.C9_f131 = 1;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Kỹ năng giá trị chưa đủ", "Nhấn nút 5 tiếp tục");
               }
            } else {
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               if (var1.hasDebuff(2) && var1.getBattlePosition() == 0) {
                  boolean var2 = false;

                  for (int var3 = 0; var3 < var1.skillPP.length; ++var3) {
                     if (var1.skillPP[var3] != 0) {
                        var2 = true;
                     }
                  }

                  if (!var2) {
                     this.C9_f122.removeDialog("/data/ui/choiceskill.ui");
                     this.c("Không có kỹ năng giá trị, không cách nào chiến đấu");
                     ((BattleSystemManager) this.C9_f121).F();
                  }
               }
            }
         } else if (this.C9_f121.isKeyPressed(262144) && this.C9_f131 == 0) {
            this.C9_f122.removeDialog("/data/ui/choiceskill.ui");
            this.C9_f121.changeState((byte) 20);
         }
      }

      this.g();
   }

   public final void ak() {
      this.C9_f131 = 0;
      this.C9_f122.showDialog("/data/ui/choice.ui", 257, this);
      this.C9_f122.currentDialog.getChildById(8).getComponentData().text = "Pokemon ball";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "Tỉ lệ bắt";
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Sử dụng";
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f125;
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = this.C9_f123.consumableInventory.size();

      for (int var1 = 0; var1 < this.C9_f123.consumableInventory.size(); ++var1) {
         int[] var2 = (int[]) this.C9_f123.consumableInventory.elementAt(var1);
         if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.spriteType = 2;
            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer.initializeSprite(258, false,
                  (byte) -1);
         }

         this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().renderer
               .setSpriteIndex((int) ResourceManager.gameDatabase[4][var2[0]][1]);
         this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = GameEngineBase
               .getLocalizedText((int) ResourceManager.gameDatabase[4][var2[0]][0]);
         this.C9_f122.currentDialog.getChildById(14 + var1 * 5)
               .getComponentData().text = ((BattleSystemManager) this.C9_f121).m(var2[0]) + "%";
      }

      this.C9_f122.currentDialog.getChildById(59).setVisible(false);
      this.C9_f122.currentDialog.getChildById(60).setVisible(false);
      this.bw();
   }

   private void bw() {
      int[] var1 = (int[]) this.C9_f123.consumableInventory.elementAt(this.C9_f125);
      this.C9_f122.currentDialog.getChildById(53).getComponentData().text = "Số lượng: " + var1[1] + " cái ";
   }

   public final void al() {
      this.C9_f121.updateActionSequence();
      if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && this.C9_f131 == 0
            && this.C9_f121.isKeyPressed(4100)
            && !this.j()) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bw();
      } else if (!GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0) && this.C9_f131 == 0
            && this.C9_f121.isKeyPressed(8448)
            && !this.j()) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bw();
      } else if (this.C9_f121.isKeyPressed(196640) && !this.j() && GameEngineBase.isConfirmAllowed()) {
         if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.C9_f125, (int) 0)) {
            return;
         }

         QuestManager.isSpecialState = true;
         if (this.C9_f131 == 0) {
            int[] var1 = (int[]) this.C9_f123.consumableInventory.elementAt(this.C9_f125);
            if (!this.C9_f123.hasItem((int) var1[0], (int) 1, (byte) 0)) {
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Số lượng Pokemon ball không đủ", "Nhấn nút 5 tiếp tục");
               this.C9_f131 = 1;
            } else {
               this.C9_f131 = 0;
               BattleSystemManager.C29_f444 = (byte) var1[0];
               this.C9_f121.handleActionResponse();
               this.C9_f123.removeItem(var1[0], 1, (byte) 0);
               this.C9_f121.changeState((byte) 17);
               this.C9_f122.removeDialog("/data/ui/choice.ui");
            }
         } else if (this.C9_f131 == 1) {
            if (GameEngineBase.paymentActive && ((int[]) this.C9_f123.consumableInventory.elementAt(this.C9_f125))[0] == 0) {
               this.C9_f122.removeDialog("/data/ui/choice.ui");
               this.C9_f121.changeState((byte) 101);
            }

            this.C9_f131 = 0;
            this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
         }
      } else if (QuestManager.isCancelAllowed() && this.C9_f131 == 0 && this.C9_f121.isKeyPressed(262144)
            && !this.j()) {
         this.C9_f122.removeDialog("/data/ui/choice.ui");
         this.C9_f121.changeState((byte) 20);
      }

      this.g();
   }

   public final void am() {
      this.C9_f130 = 0;
      this.C9_f131 = 0;
      this.C9_f125 = 0;
      this.C9_f122.showDialog("/data/ui/choice.ui", 257, this);
      this.C9_f122.currentDialog.getChildById(8).getComponentData().text = "Đạo cụ";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "Số lượng";
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Sử dụng";
      this.C9_f122.currentDialog.getChildById(59).setVisible(false);
      this.C9_f122.currentDialog.getChildById(60).setVisible(false);
      this.bi();
   }

   public final void an() {
      if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else {
         if (this.C9_f121.isKeyPressed(196640)) {
            if (this.C9_f123.equipmentInventory.size() <= 0) {
               return;
            }

            this.C9_f130 = ((int[]) this.C9_f123.equipmentInventory.elementAt(this.C9_f136))[0];
            if (this.C9_f131 == 0) {
               switch (ResourceManager.gameDatabase[4][this.C9_f130][5]) {
                  case 7:
                  case 8:
                  case 9:
                  case 10:
                     this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                     this.a("Trong chiến đấu không thể sử dụng", "Nhấn nút 5 tiếp tục");
                     this.C9_f131 = 1;
                     return;
                  default:
                     this.C9_f121.changeState((byte) 16);
                     this.C9_f122.removeDialog("/data/ui/choice.ui");
                     return;
               }
            }

            if (this.C9_f131 == 1) {
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.C9_f131 = 0;
               return;
            }
         } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(262144)) {
            this.C9_f122.removeDialog("/data/ui/choice.ui");
            this.C9_f121.changeState((byte) 20);
         }

      }
   }

   private void bx() {
      if (this.C9_f131 == 0) {
         this.C9_f131 = 1;
         int var1;
         if (this.C9_f121 instanceof GameWorldManager) {
            var1 = this.C9_f123.partyPokemon[this.C9_f126].canUseItem(this.C9_f130);
         } else {
            var1 = this.C9_f123.partyPokemon[((BattleSystemManager) this.C9_f121).C29_f405[this.C9_f126]].canUseItem(this.C9_f130);
         }

         switch (var1) {
            case 0:
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Sủng vật này đã tử vong, không thể sử dụng", "Nhấn nút 5 tiếp tục");
               return;
            case 1:
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Sủng vật này không có, không thể sử dụng", "Nhấn nút 5 tiếp tục");
               return;
            case 2:
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Máu đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
               return;
            case 3:
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Kỹ năng giá trị đã đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
               return;
            case 4:
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Trên người đều bị lợi hiệu quả", "Nhấn nút 5 tiếp tục");
               return;
            case 5:
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Trong hưng phấn, không thể dùng", "Nhấn nút 5 tiếp tục");
               return;
            case 6:
            default:
               if (this.C9_f123.hasItem((int) this.C9_f130, (int) 1, (byte) 0)) {
                  if (this.C9_f121 instanceof GameWorldManager) {
                     this.C9_f123.partyPokemon[this.C9_f126].useItem(this.C9_f130);
                  } else {
                     ((BattleSystemManager) this.C9_f121).C29_f408.isInBattle = true;
                     this.C9_f123.partyPokemon[((BattleSystemManager) this.C9_f121).C29_f405[this.C9_f126]].useItem(this.C9_f130);
                  }

                  this.f(this.C9_f126);
                  this.C9_f131 = 1;
                  this.C9_f150 = true;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Thành công sử dụng đạo cụ", "Nhấn nút 5 tiếp tục");
                  return;
               }

               this.C9_f131 = 2;
               this.H();
               this.a("Không có đạo này cụ, hãy mua sắm", "Nhấn nút 5 tiếp tục");
               return;
            case 7:
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Máu và tinh khí đều đã đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
               return;
            case 8:
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Sủng vật đã chết, không thể sử dụng", "Nhấn nút 5 tiếp tục");
         }
      } else if (this.C9_f131 == 1) {
         this.C9_f131 = 0;
         this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
      } else {
         if (this.C9_f131 == 2) {
            this.C9_f131 = 0;
            this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
            if (this.C9_f121 instanceof GameWorldManager) {
               this.C9_f121.changeState((byte) 8);
               return;
            }

            if (BattleSystemManager.B().C29_f408.equals(((BattleSystemManager) this.C9_f121).n(this.C9_f126))) {
               this.g(((BattleSystemManager) this.C9_f121).o(this.C9_f126));
            }

            if (((BattleSystemManager) this.C9_f121).C29_f408.isInBattle) {
               ++((BattleSystemManager) this.C9_f121).C29_f410;
               ((BattleSystemManager) this.C9_f121).changeState((byte) 1);
               return;
            }

            ((BattleSystemManager) this.C9_f121).changeState((byte) 4);
         }

      }
   }

   public final void ao() {
      if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.isKeyPressed(196640)) {
         this.bx();
      } else {
         if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(262144)) {
            if (this.C9_f150) {
               if (BattleSystemManager.B().C29_f408.equals(((BattleSystemManager) this.C9_f121).n(this.C9_f126))) {
                  this.g(((BattleSystemManager) this.C9_f121).o(this.C9_f126));
               }

               if (((BattleSystemManager) this.C9_f121).C29_f408.isInBattle) {
                  ++((BattleSystemManager) this.C9_f121).C29_f410;
                  ((BattleSystemManager) this.C9_f121).changeState((byte) 1);
               } else {
                  ((BattleSystemManager) this.C9_f121).changeState((byte) 4);
               }

               this.C9_f122.removeDialog("/data/ui/petstate.ui");
               return;
            }

            ((BattleSystemManager) this.C9_f121).changeState((byte) 4);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }

      }
   }

   public final void b(int var1, int var2) {
      if (this.C9_f138 >= BattleSystemManager.C29_f413.size()) {
         this.C9_f138 = 0;
         GameScreenManager.getInstance().changeState((byte) 10);
      } else {
         label21: while (true) {
            PokemonEntity var3 = (PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138);

            while (this.C9_f138 < BattleSystemManager.C29_f413.size() && var3.isMaxLevel()) {
               ++this.C9_f138;
               if (this.C9_f138 < BattleSystemManager.C29_f413.size()) {
                  continue label21;
               }
            }

            this.C9_f151 = var1;
            this.C9_f152 = var2;
            var3.activate();
            var3.setWorldPosition(var1, var2);
            this.C9_f137 = 0;
            return;
         }
      }
   }

   public final void ap() {
      if (this.C9_f138 >= BattleSystemManager.C29_f413.size()) {
         this.C9_f138 = 0;
         GameScreenManager.getInstance().changeState((byte) 10);
      } else {
         if (this.C9_f137 <= 0) {
            this.C9_f153 += 8;
         }

         PokemonEntity var1;
         int var2 = (var1 = (PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138)).getDamageReceived() + this.C9_f153;
         int var3 = var1.getExpToNextLevel();
         int var4 = var1.getCurrentExp();
         if (var2 >= var3) {
            var2 = var3;
         } else if (var2 >= var4) {
            var2 = var4;
         }

         if (this.C9_f121.isKeyPressed(196640)) {
            if (var4 >= var3) {
               this.C9_f122.currentDialog.getChildById(40).getComponentData().text = var3 + "/" + var3;
               this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.calculateExpPercent(var3);
               var1.setDamageReceived((int) 0);
               this.C9_f137 = 0;
               ((BattleSystemManager) this.C9_f121).changeState((byte) 22);
            } else if (var2 < var4) {
               this.C9_f153 = 0;
               var1.setDamageReceived(var4);
               this.C9_f122.currentDialog.getChildById(40).getComponentData().text = var4 + "/" + var1.getExpToNextLevel();
               this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.calculateExpPercent(var4);
            } else {
               this.C9_f122.currentDialog.getChildById(40).getComponentData().text = var4 + "/" + var1.getExpToNextLevel();
               this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.calculateExpPercent(var4);
               var1.setDamageReceived(var2);
               ++this.C9_f138;

               while (this.C9_f138 < BattleSystemManager.C29_f413.size()
                     && ((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138)).isMaxLevel()) {
                  ++this.C9_f138;
               }

               if (this.C9_f138 >= BattleSystemManager.C29_f413.size()) {
                  this.C9_f138 = 0;
                  GameScreenManager.getInstance().changeState((byte) 10);
               } else {
                  ((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138)).setWorldPosition(this.C9_f151,
                        this.C9_f152);
               }

               this.C9_f137 = 0;
               this.C9_f153 = 0;
            }
         } else {
            this.C9_f122.currentDialog.getChildById(40).getComponentData().text = var2 + "/" + var1.getExpToNextLevel();
            this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.calculateExpPercent(var2);
            this.C9_f122.currentDialog.getChildById(12).getComponentData().text = GameEngineBase
                  .getLocalizedText(var1.getDatabaseValue((byte) 0));
            this.C9_f122.currentDialog.getChildById(13).getComponentData().text = "lv" + var1.getLevel();
            this.C9_f122.currentDialog.getChildById(17).getComponentData().renderer
                  .setSpriteIndex(94 + var1.getDatabaseValue((byte) 1));
            if (var2 >= var3) {
               var1.setDamageReceived((int) 0);
               ((BattleSystemManager) this.C9_f121).changeState((byte) 22);
            } else {
               if (var2 < var4) {
                  return;
               }

               ++this.C9_f137;
               var1.setDamageReceived(var2);
               if (this.C9_f137 >= 10) {
                  ++this.C9_f138;

                  while (this.C9_f138 < BattleSystemManager.C29_f413.size()
                        && ((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138)).isMaxLevel()) {
                     ++this.C9_f138;
                  }

                  if (this.C9_f138 >= BattleSystemManager.C29_f413.size()) {
                     this.C9_f138 = 0;
                     GameScreenManager.getInstance().changeState((byte) 10);
                  } else {
                     ((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138))
                           .setWorldPosition(this.C9_f151, this.C9_f152);
                  }

                  this.C9_f137 = 0;
               }
            }

            this.C9_f153 = 0;
         }
      }
   }

   public final void aq() {
      PokemonEntity var1 = (PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138);
      String[] var2 = new String[4];

      int var3;
      byte var5;
      for (var3 = 0; var3 < 4; ++var3) {
         StringBuffer var10002 = new StringBuffer();
         var5 = (byte) (var3 + 1);
         var2[var3] = var10002.append(var1.primaryStates[var5]).toString();
      }

      var1.levelUp();
      this.g(var1);
      this.C9_f122.showDialog("/data/ui/levelUp.ui", 257, this);

      for (var3 = 0; var3 < 4; ++var3) {
         this.C9_f122.currentDialog.getChildById(var3 + 19).getComponentData().text = var2[var3];
      }

      if (var1.getSkillCount() < 5 && var1.getSkillCount() < var1.getLevel() / 10 + 1) {
         this.C9_f139 = var1.getLearnableSkills();
         this.C9_f122.currentDialog.getChildById(51).getComponentData().text = "Có thể học tập kỹ năng mới";
      } else {
         this.C9_f122.currentDialog.getChildById(51).getComponentData().text = "";
      }

      this.C9_f122.currentDialog.getChildById(38).getComponentData().text = GameEngineBase
            .getLocalizedText((int) ResourceManager.gameDatabase[0][var1.getSpeciesId()][0]);
      this.C9_f122.currentDialog.getChildById(40).getComponentData().text = "" + var1.getLevel();
      if (this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.spriteType = 3;
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.setSpriteIndex((int) 0);
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.initializeSprite(var1.spriteId, false,
               (byte) -1);
      }

      for (var3 = 0; var3 < 4; ++var3) {
         ComponentData var10000 = this.C9_f122.currentDialog.getChildById(var3 + 31).getComponentData();
         StringBuffer var10001 = new StringBuffer();
         var5 = (byte) (var3 + 1);
         var10000.text = var10001.append(var1.primaryStates[var5]).toString();
      }

   }

   public final void ar() {
      ++this.C9_f154;
      if (this.C9_f154 > 40) {
         this.C9_f154 = 0;
         if (this.C9_f139 != null) {
            ((BattleSystemManager) this.C9_f121).changeState((byte) 23);
         } else if (this.C9_f138 + 1 >= BattleSystemManager.C29_f413.size()) {
            if (((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138)).getCurrentExp() > 0) {
               this.C9_f121.changeState((byte) 8);
            } else {
               this.C9_f138 = 0;
               GameScreenManager.getInstance().changeState((byte) 10);
            }

            this.C9_f122.removeDialog("/data/ui/levelUp.ui");
         } else {
            this.C9_f121.changeState((byte) 8);
            this.C9_f122.removeDialog("/data/ui/levelUp.ui");
         }
      }

      if (this.C9_f121.isKeyPressed(196640)) {
         this.C9_f154 = 0;
         if (this.C9_f139 != null) {
            this.C9_f121.changeState((byte) 23);
            return;
         }

         if (this.C9_f138 + 1 >= BattleSystemManager.C29_f413.size()) {
            if (((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138)).getCurrentExp() > 0) {
               this.C9_f121.changeState((byte) 8);
            } else {
               this.C9_f138 = 0;
               GameScreenManager.getInstance().changeState((byte) 10);
            }

            this.C9_f122.removeDialog("/data/ui/levelUp.ui");
            return;
         }

         this.C9_f121.changeState((byte) 8);
         this.C9_f122.removeDialog("/data/ui/levelUp.ui");
      }

   }

   public final void as() {
      this.C9_f122.showDialog("/data/ui/choiceskill.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/levelUp.ui");
      this.C9_f125 = 0;
      this.C9_f131 = 0;
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.totalItemCount = this.C9_f139.length;
      if (this.C9_f139.length > 5) {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
      } else {
         ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(-1);
      }

      if (this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer.spriteType = 3;
         this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer.setSpriteIndex((int) 0);
         this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer.initializeSprite(257, false, (byte) -1);
      }

      this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer.setAnimationFrame((byte) 11, (byte) -1);
      this.C9_f122.currentDialog.getChildById(6).setVisible(false);
      this.by();
      if (!GameWorldManager.C25_f332) {
         this.b("Có thể nhấn #1nút mềm trái#0 để học tập kỹ năng");
         GameWorldManager.C25_f332 = true;
      }

   }

   private void by() {
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;

      for (int var1 = 0; var1 < 5; ++var1) {
         if (var1 >= this.C9_f139.length) {
            this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = "";
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
         } else {
            this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = GameEngineBase
                  .getLocalizedText((int) ResourceManager.gameDatabase[1][this.C9_f139[this.C9_f135 + var1]][1]);
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = ""
                  + ResourceManager.gameDatabase[1][this.C9_f139[this.C9_f135 + var1]][5];
         }
      }

      this.i(this.C9_f139[this.C9_f136]);
      this.C9_f122.currentDialog.getChildById(51).setOffsetY(98 + this.C9_f136 * 62 / this.C9_f139.length,
            this.C9_f122.currentDialog.getUIContainerComponent());
   }

   public final void at() {
      if (!this.j() && this.C9_f121.isKeyPressed(4100) && this.C9_f131 == 0) {
         this.C9_f122.currentDialog.handleAction(0);
         this.by();
      } else if (!this.j() && this.C9_f121.isKeyPressed(8448) && this.C9_f131 == 0) {
         this.C9_f122.currentDialog.handleAction(1);
         this.by();
      } else if (!this.j() && this.C9_f131 == 0
            && (this.C9_f121.isKeyPressed(131072) || this.C9_f121.isPointerClickInBounds(40, 228, 45, 20))
            || this.C9_f131 == 1 && this.C9_f121.isKeyPressed(196640)) {
         if (this.C9_f131 == 0) {
            this.C9_f131 = 1;
            this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
            this.a(
                  "Học tập" + GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[1][this.C9_f139[this.C9_f125]][1]),
                  "Nhấn nút 5 tiếp tục");
         } else if (this.C9_f131 == 1) {
            PokemonEntity var1;
            (var1 = (PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.C9_f138))
                  .learnSkill((byte) this.C9_f139[this.C9_f136]);
            this.C9_f139 = null;
            if (this.C9_f138 + 1 >= BattleSystemManager.C29_f413.size() && var1.getCurrentExp() <= 0) {
               this.C9_f138 = 0;
               GameScreenManager.getInstance().changeState((byte) 10);
            } else {
               this.C9_f121.changeState((byte) 8);
            }

            this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
            this.C9_f122.removeDialog("/data/ui/choiceskill.ui");
         }
      }

      this.f();
   }

   public final void au() {
      this.C9_f131 = 0;
      this.b("Ba lô sủng vật đều thăng 5 cấp");
   }

   public final void av() {
      if (this.C9_f131 == 0) {
         if (this.aA()) {
            this.C9_f131 = 1;
            if (GameWorldManager.C25_f333.size() <= 0) {
               this.C9_f121.changeState((byte) 14);
            }
         }
      } else if (this.C9_f131 == 1) {
         this.C9_f122.removeDialog("/data/ui/bodyShop.ui");
         this.bz();
         this.C9_f132 = true;
      } else if (this.C9_f131 >= 3) {
         label93: {
            if (this.C9_f131 == 5) {
               this.C9_f131 = 6;
               this.K();
               this.a("Đang lưu...");
               this.M();
            } else if (this.C9_f131 == 6) {
               GameWorldManager.C25_f335 = 2;
               GameEngineBase var10000 = this.C9_f121;
               GameWorldManager.F();
               if (((GameWorldManager) this.C9_f121).H()) {
                  this.a("Lưu thành công");
                  this.C9_f131 = 7;
               }
            } else if (this.C9_f131 == 7) {
               this.C9_f122.removeDialog("/data/ui/msgtip.ui");
               this.C9_f131 = 0;
               if (this.C9_f121.previousState == 14) {
                  this.C9_f121.changeState((byte) 14);
               } else {
                  this.C9_f121.changeState((byte) 0);
               }
               break label93;
            }

            if (!this.j() && this.C9_f121.isKeyPressed(4100) && this.C9_f131 == 3) {
               this.C9_f122.currentDialog.handleAction(0);
               this.by();
            } else if (!this.j() && this.C9_f121.isKeyPressed(8448) && this.C9_f131 == 3) {
               this.C9_f122.currentDialog.handleAction(1);
               this.by();
            } else if (!this.j() && this.C9_f131 == 3
                  && (this.C9_f121.isKeyPressed(131072) || this.C9_f121.isPointerClickInBounds(40, 228, 45, 20))
                  || this.C9_f131 == 4 && this.C9_f121.isKeyPressed(196640)) {
               if (this.C9_f131 == 3) {
                  this.C9_f131 = 4;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a(
                        "Học tập" + GameEngineBase
                              .getLocalizedText((int) ResourceManager.gameDatabase[1][this.C9_f139[this.C9_f136]][1]),
                        "Nhấn nút 5 tiếp tục");
               } else if (this.C9_f131 == 4) {
                  ((PokemonEntity) GameWorldManager.C25_f333.elementAt(this.C9_f138))
                        .learnSkill((byte) this.C9_f139[this.C9_f136]);
                  this.C9_f139 = null;
                  ++this.C9_f138;
                  if (this.C9_f138 >= GameWorldManager.C25_f333.size()) {
                     this.C9_f138 = 0;
                     this.C9_f131 = 5;
                  } else {
                     this.bz();
                  }

                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  this.C9_f122.removeDialog("/data/ui/choiceskill.ui");
               }
            }
         }
      } else if (this.C9_f121.isKeyPressed(196640)) {
         this.C9_f122.showDialog("/data/ui/choiceskill.ui", 257, this);
         this.C9_f122.removeDialog("/data/ui/levelUp.ui");
         this.C9_f125 = 0;
         this.C9_f131 = 3;
         ((UIContainerComponent) this.C9_f122.currentDialog
               .getChildById(0)).primaryListComponent.totalItemCount = this.C9_f139.length;
         if (this.C9_f139.length > 5) {
            ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
         } else {
            ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(-1);
         }

         if (this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer.spriteType = 3;
            this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer.setSpriteIndex((int) 0);
            this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer.initializeSprite(257, false,
                  (byte) -1);
         }

         this.C9_f122.currentDialog.getChildById(5).getComponentData().renderer.setAnimationFrame((byte) 11, (byte) -1);
         this.C9_f122.currentDialog.getChildById(6).setVisible(false);
         this.by();
         this.C9_f132 = true;
      }

      this.f();
   }

   private void bz() {
      this.C9_f131 = 2;
      PokemonEntity var1 = (PokemonEntity) GameWorldManager.C25_f333.elementAt(this.C9_f138);
      this.C9_f122.showDialog("/data/ui/levelUp.ui", 257, this);

      int var2;
      for (var2 = 0; var2 < 4; ++var2) {
         this.C9_f122.currentDialog.getChildById(var2 + 19).getComponentData().text = ""
               + var1.getCachedStat((int) ((byte) (var2 + 1 - 1)));
      }

      if (var1.getSkillCount() < 5 && var1.getSkillCount() < var1.getLevel() / 10 + 1) {
         this.C9_f139 = var1.getLearnableSkills();
         this.C9_f122.currentDialog.getChildById(51).getComponentData().text = "Nhấn nút 5 học tập kỹ năng mới";
      } else {
         this.C9_f122.currentDialog.getChildById(51).getComponentData().text = "";
      }

      this.C9_f122.currentDialog.getChildById(38).getComponentData().text = GameEngineBase
            .getLocalizedText((int) ResourceManager.gameDatabase[0][var1.getSpeciesId()][0]);
      this.C9_f122.currentDialog.getChildById(40).getComponentData().text = "" + var1.getLevel();
      if (this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.spriteType = 3;
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.setSpriteIndex((int) 0);
         this.C9_f122.currentDialog.getChildById(10).getComponentData().renderer.initializeSprite(var1.spriteId, false,
               (byte) -1);
      }

      for (var2 = 0; var2 < 4; ++var2) {
         ComponentData var10000 = this.C9_f122.currentDialog.getChildById(var2 + 31).getComponentData();
         StringBuffer var10001 = new StringBuffer();
         byte var4 = (byte) (var2 + 1);
         var10000.text = var10001.append(var1.primaryStates[var4]).toString();
      }

   }

   public final void aw() {
      this.C9_f122.showDialog("/data/ui/npcEnemy.ui", 296, this);
      if (this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.spriteType = 2;
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.initializeSprite(296, false, (byte) 0);
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex((int) 0);
      }

      this.C9_f122.currentDialog.getChildById(36).setVisible(false);
   }

   private void a(int var1, int var2, int var3) {
      if (var3 != -1 && this.C9_f122.currentDialog.getChildById(var3).getComponentData().renderer != null) {
         this.C9_f122.currentDialog.getChildById(var3).setVisible(false);
      }

      if (this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.spriteType = 2;
         this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.initializeSprite(296, false,
               (byte) 0);
         this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.setSpriteIndex((int) 0);
      }

      this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.setSpriteIndex(var2);
   }

   public final void c(int var1, int var2) {
      switch (var1) {
         case 0:
            this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex(var2);
            return;
         case 1:
            for (var1 = 2; var1 < 4; ++var1) {
               if (this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.spriteType = 2;
                  this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.setSpriteIndex((int) 0);
               }

               if (var1 % 2 == 1) {
                  this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.initializeSprite(0, false,
                        (byte) -1);
               } else if (GameWorldManager.C25_f318 == -1) {
                  if (GameWorldManager.C25_f319 == -1) {
                     this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer
                           .initializeSprite(GameWorldManager.B().C25_f287[8].sprite.spriteSetId, false, (byte) -1);
                  } else {
                     this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.initializeSprite(
                           GameWorldManager.B().C25_f287[GameWorldManager.C25_f319].sprite.spriteSetId, false,
                           (byte) -1);
                  }
               } else {
                  this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.initializeSprite(
                        GameWorldManager.B().C25_f287[GameWorldManager.C25_f318].sprite.spriteSetId, false, (byte) -1);
               }

               this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer.setSpriteIndex((int) 1);
            }

            this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex(var2);
            return;
         case 2:
            for (var1 = 2; var1 < 4; ++var1) {
               if (this.C9_f122.currentDialog.getChildById(var1).getComponentData().renderer != null) {
                  this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
               }

               if (this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer.spriteType = 2;
                  this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer
                        .setSpriteIndex((int) 0);
               }

               if (var1 % 2 == 1) {
                  this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer.initializeSprite(0,
                        false, (byte) -1);
               } else if (GameWorldManager.C25_f318 == -1) {
                  if (GameWorldManager.C25_f319 == -1) {
                     this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer
                           .initializeSprite(GameWorldManager.B().C25_f287[8].sprite.spriteSetId, false, (byte) -1);
                  } else {
                     this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer.initializeSprite(
                           GameWorldManager.B().C25_f287[GameWorldManager.C25_f319].sprite.spriteSetId, false,
                           (byte) -1);
                  }
               } else {
                  this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer.initializeSprite(
                        GameWorldManager.B().C25_f287[GameWorldManager.C25_f318].sprite.spriteSetId, false, (byte) -1);
               }

               this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer.setSpriteIndex((int) 1);
            }

            this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex(var2);
            return;
         case 3:
            for (var1 = 2; var1 < 4; ++var1) {
               if (this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().renderer != null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 32).setVisible(false);
               }

               if (this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().renderer.spriteType = 2;
                  this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().renderer.setSpriteIndex((int) 0);
               }

               if (var1 % 2 == 1) {
                  this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().renderer.initializeSprite(0,
                        false, (byte) -1);
               } else if (GameWorldManager.C25_f318 == -1) {
                  if (GameWorldManager.C25_f319 == -1) {
                     this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().renderer
                           .initializeSprite(GameWorldManager.B().C25_f287[8].sprite.spriteSetId, false, (byte) -1);
                  } else {
                     this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().renderer.initializeSprite(
                           GameWorldManager.B().C25_f287[GameWorldManager.C25_f319].sprite.spriteSetId, false,
                           (byte) -1);
                  }
               } else {
                  this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().renderer.initializeSprite(
                        GameWorldManager.B().C25_f287[GameWorldManager.C25_f318].sprite.spriteSetId, false, (byte) -1);
               }

               this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().renderer.setSpriteIndex((int) 1);
            }

            this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex(var2);
            this.C9_f155 = BattleSystemManager.B().H();
            this.C9_f156 = this.C9_f123.partySize;
            if (var2 - 3 < this.C9_f155) {
               this.a((int) 6, 6, -1);
            }

            if (var2 - 3 < this.C9_f156) {
               this.a((int) 18, 6, -1);
               return;
            }
            break;
         case 4:
            if (var2 - 3 < this.C9_f155) {
               this.a((int) (6 + (var2 - 3 << 1)), 6, 6 + (var2 - 4 << 1));
            } else {
               this.a((int) (6 + (var2 - 3 << 1)), 5, 6 + (var2 - 4 << 1));
            }

            if (var2 - 4 < this.C9_f155) {
               this.a((int) (7 + (var2 - 4 << 1)), 6, 6 + (var2 - 4 << 1));
            } else {
               this.a((int) (7 + (var2 - 4 << 1)), 5, 6 + (var2 - 4 << 1));
            }

            if (var2 - 4 < this.C9_f156) {
               this.a((int) (19 + (var2 - 4 << 1)), 6, 18 + (var2 - 4 << 1));
            } else {
               this.a((int) (19 + (var2 - 4 << 1)), 5, 18 + (var2 - 4 << 1));
            }

            if (var2 - 3 < this.C9_f156) {
               this.a((int) (18 + (var2 - 3 << 1)), 6, 18 + (var2 - 4 << 1));
               return;
            }

            this.a((int) (18 + (var2 - 3 << 1)), 5, 18 + (var2 - 4 << 1));
            return;
         case 5:
            if (var2 - 4 < this.C9_f155) {
               this.a((int) (7 + (var2 - 4 << 1)), 6, 6 + (var2 - 4 << 1));
            } else {
               this.a((int) (7 + (var2 - 4 << 1)), 5, 6 + (var2 - 4 << 1));
            }

            if (var2 - 4 < this.C9_f156) {
               this.a((int) (19 + (var2 - 4 << 1)), 6, 18 + (var2 - 4 << 1));
               return;
            }

            this.a((int) (19 + (var2 - 4 << 1)), 5, 18 + (var2 - 4 << 1));
            return;
         case 6:
            this.a((int) 30, 8, -1);
            this.a((int) 31, 7, -1);
            return;
         case 7:
            this.a((int) 32, 8, 30);
            this.a((int) 33, 7, 31);
            return;
         case 8:
            this.C9_f122.currentDialog.getChildById(36).setVisible(true);
            return;
         case 9:
            this.C9_f122.currentDialog.getChildById(36).setVisible(false);
            return;
         case 10:
            this.a((int) 1, 4, 32);
            this.a((int) 1, 4, 33);

            for (var1 = 4; var1 < 6; ++var1) {
               this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
            }

            for (var1 = 7; var1 < 19; var1 += 2) {
               this.C9_f122.currentDialog.getChildById(var1).setOffsetX(172 + 17 * (var1 - 7) / 2,
                     this.C9_f122.currentDialog.getUIContainerComponent());
               this.C9_f122.currentDialog.getChildById(var1 + 12).setOffsetX(-30 + 17 * (var1 - 7) / 2,
                     this.C9_f122.currentDialog.getUIContainerComponent());
            }

            return;
         case 11:
            for (var1 = 4; var1 < 6; ++var1) {
               this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
            }

            for (var1 = 7; var1 < 19; var1 += 2) {
               this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
               this.C9_f122.currentDialog.getChildById(var1 + 12).setVisible(false);
            }

            this.a((int) 1, 0, -1);
      }

   }

   private void e(String var1) {
      if (this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex((int) 0);
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.spriteType = 3;
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.initializeSprite(257, false, (byte) -2);
      }

      this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.setAnimationFrame((byte) 9, (byte) -2);
      this.C9_f122.currentDialog.getChildById(2).getComponentData().text = var1;
      this.C9_f134 = 0;
   }

   public final void ax() {
      this.C9_f122.showDialog("/data/ui/openbox.ui", 257, this);
      this.e("Không có cái chìa khóa, có thể đến tài liệu cửa hàng mua sắm");
   }

   public final void ay() {
      this.C9_f122.showDialog("/data/ui/openbox.ui", 257, this);
      this.e("Đạo cụ đã đủ");
   }

   public final void a(String var1, int var2) {
      this.C9_f122.showDialog("/data/ui/openbox.ui", 257, this);
      this.e(var1 + " x " + var2);
   }

   public final void b(String var1) {
      this.C9_f122.showDialog("/data/ui/openbox.ui", 257, this);
      this.e(var1);
   }

   public final void az() {
      if (this.C9_f122.isTopDialog("/data/ui/openbox.ui")) {
         this.C9_f122.removeDialog("/data/ui/openbox.ui");
      }

   }

   public final boolean aA() {
      return !this.C9_f122.isTopDialog("/data/ui/openbox.ui");
   }

   public final void c(String var1) {
      this.C9_f122.showDialog("/data/ui/taskTip.ui", 257, this);
      if (this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex((int) 0);
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.spriteType = 3;
         this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.initializeSprite(257, false, (byte) -2);
      }

      this.C9_f122.currentDialog.getChildById(1).getComponentData().renderer.setAnimationFrame((byte) 10, (byte) -2);
      this.C9_f122.currentDialog.getChildById(2).getComponentData().text = var1;
      this.C9_f134 = 0;
   }

   public final boolean aB() {
      return !this.C9_f122.isTopDialog("/data/ui/taskTip.ui");
   }

   public final void aC() {
      this.C9_f126 = 0;
      this.C9_f131 = 0;
      this.C9_f122.showDialog("/data/ui/bodyShop.ui", 257, this);
      this.bA();
   }

   private void bA() {
      String var1 = "";
      int[] var2;
      switch (this.C9_f126) {
         case 0:
            var1 = "Tùy thời mua sắm các loại đạo cụ, già trẻ không gạt.";
            break;
         case 1:
            var2 = new int[] { 2, 1, 2 };
            var1 = GameEngineBase.getLocalizedText((int) 602) + GameEngineBase.formatString(604, (int[]) var2);
            break;
         case 2:
            var2 = new int[] { 2, 1, 2 };
            var1 = GameEngineBase.getLocalizedText((int) 603) + GameEngineBase.formatString(604, (int[]) var2);
            break;
         case 3:
            var2 = new int[] { 2, 1, 2 };
            var1 = GameEngineBase.getLocalizedText((int) 601) + GameEngineBase.formatString(604, (int[]) var2);
      }

      this.C9_f122.currentDialog.getChildById(11).getComponentData().text = var1;
      if (this.C9_f126 > 0) {
         this.C9_f121.setPaymentState((byte) 0);
         this.bB();
      }

   }

   private void bB() {
      switch (this.C9_f126) {
         case 1:
            this.C9_f121.initPayment((byte) 3);
            return;
         case 2:
            this.C9_f121.initPayment((byte) 4);
            return;
         case 3:
            this.C9_f121.initPayment((byte) 2);
         default:
      }
   }

   public final void aD() {
      switch (this.C9_f126) {
         case 0:
            if (this.C9_f121.isKeyPressed(4100) && this.C9_f131 == 0) {
               this.C9_f122.currentDialog.handleAction(0);
               this.bA();
               return;
            }

            if (this.C9_f121.isKeyPressed(8448) && this.C9_f131 == 0) {
               this.C9_f122.currentDialog.handleAction(1);
               this.bA();
               return;
            }

            if (this.C9_f121.isKeyPressed(196640)) {
               this.C9_f121.changeState((byte) 26);
               this.C9_f122.removeDialog("/data/ui/bodyShop.ui");
               return;
            }

            if (this.C9_f121.isKeyPressed(786432)) {
               this.C9_f125 = 0;
               this.C9_f121.changeState((byte) 6);
               this.C9_f122.removeDialog("/data/ui/bodyShop.ui");
               return;
            }
            break;
         default:
            switch (this.C9_f121.getPaymentType()) {
               case 0:
                  if (this.C9_f121.isKeyPressed(4100) && this.C9_f131 == 0) {
                     this.C9_f122.currentDialog.handleAction(0);
                     this.bA();
                     return;
                  }

                  if (this.C9_f121.isKeyPressed(8448) && this.C9_f131 == 0) {
                     this.C9_f122.currentDialog.handleAction(1);
                     this.bA();
                     return;
                  }

                  if ((this.C9_f131 != 0 || !this.C9_f121.isKeyPressed(131072))
                        && (this.C9_f131 != 1 || !this.C9_f121.isKeyPressed(65568))) {
                     if (this.C9_f121.isKeyPressed(786432) && this.C9_f131 == 0) {
                        this.C9_f125 = 0;
                        this.C9_f121.changeState((byte) 6);
                        this.C9_f122.removeDialog("/data/ui/bodyShop.ui");
                        return;
                     }
                  } else {
                     if (this.C9_f131 != 0) {
                        this.C9_f131 = 0;
                        this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                        return;
                     }

                     this.bB();
                     if (this.C9_f121.getCurrentPaymentIndex() == 3) {
                        if (GameWorldManager.C25_f336 != null) {
                           GameWorldManager.C25_f336.removeAllElements();
                        }

                        int var1;
                        for (var1 = 0; var1 < PlayerCharacter.getInstance().partySize
                              && PlayerCharacter.getInstance().partyPokemon[var1].getLevel() >= 50; ++var1) {
                        }

                        if (var1 >= PlayerCharacter.getInstance().partySize) {
                           this.C9_f131 = 1;
                           this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                           this.a("Trong ba lô sủng vật đều đã max level", "Nhấn nút 5 tiếp tục");
                           return;
                        }
                     }

                     if (this.C9_f121.getTotalPaymentCount() > 1) {
                        this.C9_f121.setPaymentState((byte) 1);
                        return;
                     }

                     if (this.C9_f122.currentDialog.getChildById(11).getComponentData().isScrollingComplete()) {
                        this.C9_f121.handlePaymentAction(1);
                        return;
                     }
                  }
                  break;
               case 1:
                  if (this.C9_f121.isKeyPressed(131072)) {
                     this.C9_f121.handlePaymentDialogResponse(1);
                     return;
                  }

                  if (this.C9_f121.isKeyPressed(262144)) {
                     this.C9_f121.handlePaymentDialogResponse(2);
                     return;
                  }
                  break;
               case 2:
                  if (this.bC() && this.C9_f121.isKeyPressed(917504)) {
                     if (this.C9_f121.isPaymentComplete()) {
                        if (this.C9_f121.getCurrentPaymentIndex() == 3) {
                           this.C9_f121.changeState((byte) 25);
                        }

                        this.C9_f121.setPaymentState((byte) 5);
                     } else {
                        this.C9_f121.setPaymentState((byte) 1);
                     }

                     this.C9_f131 = 0;
                     return;
                  }
                  break;
               case 3:
                  if (this.C9_f121.isKeyPressed(393216)) {
                     this.C9_f121.handlePaymentDialogResponse(1);
                  }
            }
      }

   }

   private boolean bC() {
      if (this.C9_f131 == 0) {
         this.C9_f131 = 1;
         this.K();
         this.a("Đang lưu...");
         this.M();
      } else if (this.C9_f131 == 1) {
         if (this.C9_f121.getCurrentPaymentIndex() == 3) {
            if (GameWorldManager.B().I()) {
               this.a("Lưu thành công");
               this.C9_f131 = 2;
            }
         } else if (GameWorldManager.B().J()) {
            this.a("Lưu thành công");
            this.C9_f131 = 2;
         }
      } else if (this.C9_f131 == 2) {
         this.C9_f122.removeDialog("/data/ui/msgtip.ui");
         this.C9_f131 = 3;
      } else if (this.C9_f131 == 3) {
         return true;
      }

      return false;
   }

   public final void aE() {
      this.C9_f122.showDialog("/data/ui/dialog.ui", 257, this);
      this.C9_f122.currentDialog.getChildById(12).setVisible(false);
      this.C9_f122.currentDialog.getChildById(13).setVisible(false);
   }

   public final void a(String var1, String var2, int var3, int var4) {
      this.C9_f122.showDialog("/data/ui/dialog.ui", 257, this);
      GameUtils.a(var2, GameEngineBase.getFontHeight(), this.C9_f122.currentDialog.getChildById(14).getWidth(),
            GameEngineBase.getDefaultFont(), true, (byte) -1, this.C9_f121.dialogManager.textRenderer);
      GameUtils.d(this.C9_f122.currentDialog.getChildById(14).getHeight());
      this.C9_f122.currentDialog.getChildById(14).getComponentData().text = GameUtils.e(1);
      GameWorldManager.C25_f317 = (byte) var3;
      GameWorldManager.C25_f316 = (byte) var4;
      this.C9_f122.currentDialog.getChildById(8).setVisible(false);
      this.C9_f122.currentDialog.getChildById(11).setVisible(false);
      this.C9_f122.currentDialog.getChildById(12).setVisible(true);
      this.C9_f122.currentDialog.getChildById(13).setVisible(true);
      if (var3 == -1) {
         this.C9_f122.currentDialog.getChildById(12).setVisible(false);
         this.C9_f122.currentDialog.getChildById(13).setVisible(false);
      }

      switch (var3) {
         case 0:
            if (var4 != -1) {
               if (this.C9_f122.currentDialog.getChildById(11).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(11).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(11).getComponentData().renderer.spriteType = 3;
                  this.C9_f122.currentDialog.getChildById(11).getComponentData().renderer.setSpriteIndex((int) 0);
                  this.C9_f122.currentDialog.getChildById(11).getComponentData().renderer.initializeSprite(323, false,
                        (byte) -2);
               }

               this.C9_f122.currentDialog.getChildById(11).setVisible(true);
               this.C9_f122.currentDialog.getChildById(11).getComponentData().renderer
                     .setAnimationFrame((byte) (var3 + (var4 << 1)), (byte) -2);
            }

            this.C9_f122.currentDialog.getChildById(13).setVisible(false);
            this.C9_f122.currentDialog.getChildById(12).getComponentData().text = var1;
            return;
         case 1:
            if (var4 != -1) {
               if (this.C9_f122.currentDialog.getChildById(8).getComponentData().renderer == null) {
                  this.C9_f122.currentDialog.getChildById(8).getComponentData().renderer = new SpriteRenderer();
                  this.C9_f122.currentDialog.getChildById(8).getComponentData().renderer.spriteType = 3;
                  this.C9_f122.currentDialog.getChildById(8).getComponentData().renderer.setSpriteIndex((int) 0);
                  this.C9_f122.currentDialog.getChildById(8).getComponentData().renderer.initializeSprite(323, false,
                        (byte) -2);
               }

               this.C9_f122.currentDialog.getChildById(8).setVisible(true);
               this.C9_f122.currentDialog.getChildById(8).getComponentData().renderer
                     .setAnimationFrame((byte) (var3 + (var4 << 1)), (byte) -2);
            }

            this.C9_f122.currentDialog.getChildById(12).setVisible(false);
            this.C9_f122.currentDialog.getChildById(13).getComponentData().text = var1;
         default:
      }
   }

   public final void b(int var1) {
      this.C9_f122.currentDialog.getChildById(14).getComponentData().text = GameUtils.e(var1);
   }

   public final void aF() {
      this.C9_f122.removeDialog("/data/ui/dialog.ui");
   }

   public final boolean d(int var1, int var2) {
      if (var2 == -1) {
         return true;
      } else {
         switch (var1) {
            case 0:
               if (this.C9_f122.currentDialog.getChildById(11).getComponentData().renderer.getSpriteManager()
                     .isAtLastFrame()) {
                  return true;
               }
               break;
            case 1:
               if (this.C9_f122.currentDialog.getChildById(8).getComponentData().renderer.getSpriteManager()
                     .isAtLastFrame()) {
                  return true;
               }
         }

         this.C9_f132 = true;
         return false;
      }
   }

   public final void a(int var1, int var2, String[] var3, String var4) {
      this.C9_f125 = 0;
      this.C9_f122.showDialog(this.C9_f157[var1], 257, this);
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = var2;
      switch (var1) {
         case 0:
            for (var1 = 0; var1 < var3.length; ++var1) {
               this.C9_f122.currentDialog.getChildById(var1 + 12).getComponentData().text = var3[var1];
            }

            return;
         case 1:
            this.C9_f122.currentDialog.getChildById(5).getComponentData().text = var4;

            for (var1 = 0; var1 < var3.length; ++var1) {
               this.C9_f122.currentDialog.getChildById(9 + (var1 << 2)).getComponentData().text = var3[var1];
            }

            return;
         case 2:
            this.C9_f122.currentDialog.getChildById(10).setVisible(false);
            this.C9_f122.currentDialog.getChildById(8).getComponentData().text = "Trò chơi";
            this.C9_f122.currentDialog.getChildById(9).getComponentData().text = "Xác nhận";

            for (var1 = 0; var1 < var3.length; ++var1) {
               this.C9_f122.currentDialog.getChildById(var1 + 5).getComponentData().text = var3[var1];
            }
         default:
      }
   }

   public final int c(int var1) {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.C9_f125 = this.C9_f140[0];
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.C9_f125 = this.C9_f140[0];
      } else if (this.C9_f121.isKeyPressed(196640)) {
         this.C9_f122.removeDialog(this.C9_f157[var1]);
         return this.C9_f125;
      }

      return -1;
   }

   public final void a(int[] var1, int[] var2, String[] var3, String[] var4) {
      this.C9_f125 = 0;
      this.C9_f122.showDialog("/data/ui/taskOption.ui", 257, this);

      int var5;
      for (var5 = 0; var5 < var4.length; ++var5) {
         this.C9_f122.currentDialog.getChildById(var5 + 17).getComponentData().text = var4[var5];
      }

      for (var5 = 0; var5 < var1.length; ++var5) {
         if (this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer == null) {
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 13)
                  .getComponentData().renderer = new SpriteRenderer();
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer.spriteType = 2;
            if (var1[var5] >= 3 && var1[var5] < 5) {
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                     .setSpriteIndex((int) -1);
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                     .initializeSprite(257, false, (byte) 0);
            } else {
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                     .setSpriteIndex((int) 0);
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                     .initializeSprite(258, false, (byte) 0);
            }
         }

         switch (var1[var5]) {
            case 0:
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                     .setSpriteIndex((int) ResourceManager.gameDatabase[4][var2[var5]][1]);
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
               break;
            case 1:
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                     .setSpriteIndex((int) ResourceManager.gameDatabase[3][var2[var5]][1]);
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
               break;
            case 2:
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                     .setSpriteIndex((int) ResourceManager.gameDatabase[5][var2[var5]][1]);
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
               break;
            case 3:
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                     .setSpriteIndex((int) 84);
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
               break;
            case 4:
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                     .setSpriteIndex((int) 83);
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
            case 5:
            default:
               break;
            case 6:
               this.C9_f122.currentDialog.getChildById(21).getComponentData().text = "#2"
                     + GameEngineBase.getLocalizedText(
                           (int) ResourceManager.getDatabaseValue((byte) 0, (short) var2[var5], (byte) 0))
                     + " #0" + var3[var5];
         }
      }

   }

   public final int aG() {
      if (this.C9_f121.isKeyPressed(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.C9_f125 = this.C9_f140[0];
      } else if (this.C9_f121.isKeyPressed(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.C9_f125 = this.C9_f140[0];
      } else {
         if (this.C9_f121.isKeyPressed(196640)) {
            this.C9_f122.removeDialog("/data/ui/taskOption.ui");
            return this.C9_f125;
         }

         if (this.C9_f121.isKeyPressed(262144)) {
            this.C9_f122.removeDialog("/data/ui/taskOption.ui");
            return 1;
         }
      }

      return -1;
   }

   public final void aH() {
      this.bE();
      this.c("Có dùng 10000 kim tiền để khôi phục trạng thái của tất cả sủng vật trong ba lô không?",
            "Tại chỗ sống lại");
   }

   private void bD() {
      byte var1 = -1;
      if (GameWorldManager.B().C25_f290 == 9) {
         var1 = (byte) GameWorldManager.B().C25_f291;
      }

      if (var1 == -1) {
         GameWorldManager.B();
         if (GameWorldManager.G()) {
            GameWorldManager.B().cleanupCurrentScreen();
            this.C9_f123.isInitialized = false;
            GameScreenManager.getInstance().changeState((byte) 9);
         } else {
            GameScreenManager.getInstance().changeState((byte) 7);
         }
      } else {
         for (int var2 = 0; var2 < this.C9_f123.partySize; ++var2) {
            this.C9_f123.partyPokemon[var2].heal(1);
            this.C9_f123.partyPokemon[var2].setDisplayHp(1);
            this.C9_f123.partyPokemon[var2].activate();
         }

         GameWorldManager.C25_f318 = -1;
         if (GameWorldManager.B().C25_f291 == 0) {
            short[] var5 = new short[] { 15, 194, 433, 16, 142, 357, 17, 97, 268, 18, 183, 224 };

            for (int var3 = 0; var3 < GameWorldManager.B().C25_f287.length; ++var3) {
               for (int var4 = 0; var4 < var5.length / 3; ++var4) {
                  if (GameWorldManager.B().C25_f287[var3].C18_f248 == var5[var4 * 3]) {
                     GameWorldManager.B().C25_f287[var3].setWorldPosition(var5[var4 * 3 + 1], var5[var4 * 3 + 2]);
                  }
               }
            }
         }

         GameWorldManager.B().C25_f290 = this.C9_f158[var1 << 2];
         GameWorldManager.B().C25_f291 = this.C9_f158[(var1 << 2) + 1];
         PlayerCharacter.getInstance().setWorldPosition(this.C9_f158[(var1 << 2) + 2], this.C9_f158[(var1 << 2) + 3]);
         short var10001 = this.C9_f158[(var1 << 2) + 2];
         short var10002 = this.C9_f158[(var1 << 2) + 3];
         PlayerCharacter.getInstance().attachedObject.setWorldPosition(var10001, var10002);
         PlayerCharacter var10000 = PlayerCharacter.getInstance();
         byte var6 = 2;
         var10000.currentDirection = var6;
         GameScreenManager.getInstance().changeState((byte) 10);
      }
   }

   public final void aI() {
      if (!this.C9_f121.isKeyPressed(196640)) {
         if (this.C9_f131 == 0 && this.C9_f121.isKeyPressed(786432)) {
            this.bD();
            this.bF();
         }

      } else {
         int var1;
         if (this.C9_f131 == 0) {
            if (!this.C9_f123.hasEnoughGold(10000)) {
               this.H();
               this.a("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
               this.C9_f131 = 1;
            } else {
               this.C9_f123.addGold(-10000);

               for (var1 = 0; var1 < this.C9_f123.partySize; ++var1) {
                  this.C9_f123.partyPokemon[var1].fullRestore();
                  PokemonEntity var10000 = this.C9_f123.partyPokemon[var1];
                  PokemonEntity var10001 = this.C9_f123.partyPokemon[var1];
                  byte var3 = 1;
                  var10000.setDisplayHp(var10001.secondaryStates[var3]);
               }

               BattleSystemManager.B().C();
               this.C9_f121.changeState((byte) 0);
               this.bF();
            }
         } else {
            for (var1 = 0; var1 < this.C9_f123.partySize; ++var1) {
               this.C9_f123.partyPokemon[var1].heal(1);
               this.C9_f123.partyPokemon[var1].setDisplayHp(1);
               this.C9_f123.partyPokemon[var1].activate();
            }

            if (GameEngineBase.paymentActive) {
               this.C9_f121.changeState((byte) 102);
            } else {
               this.bD();
            }

            this.I();
         }
      }
   }

   public final void d(int var1) {
      this.C9_f121.initPayment((byte) 0);
      this.C9_f121.setPaymentState((byte) 0);
      this.bE();
      int[] var2 = new int[] { 4, 1, 4 };
      String var3 = GameEngineBase.getLocalizedText((int) 599) + GameEngineBase.formatString(604, (int[]) var2);
      this.c(var3, "Kích hoạt trò chơi");
   }

   public final void aJ() {
      this.C9_f121.initPayment((byte) 1);
      this.C9_f121.setPaymentState((byte) 0);
      this.bE();
      int[] var1 = new int[] { 2, 1, 2 };
      String var2 = GameEngineBase.getLocalizedText((int) 600) + GameEngineBase.formatString(604, (int[]) var1);
      this.c(var2, "Mua sắm tất trúng cầu");
   }

   public final void aK() {
      this.C9_f131 = 0;
      this.C9_f121.initPayment((byte) 4);
      this.C9_f121.setPaymentState((byte) 0);
      this.bE();
      int[] var1 = new int[] { 2, 1, 2 };
      String var2 = GameEngineBase.getLocalizedText((int) 603) + GameEngineBase.formatString(604, (int[]) var1);
      this.c(var2, "Mua huy hiệu");
   }

   public final void aL() {
      this.C9_f131 = 0;
      this.C9_f121.initPayment((byte) 2);
      this.C9_f121.setPaymentState((byte) 0);
      this.bE();
      int[] var1 = new int[] { 2, 1, 2 };
      String var2 = GameEngineBase.getLocalizedText((int) 601) + GameEngineBase.formatString(604, (int[]) var1);
      this.c(var2, "Mua kim tiền");
   }

   private void bE() {
      this.C9_f122.showDialog("/data/ui/smsInfo.ui", 257, this);
      if (this.C9_f121 instanceof GameWorldManager) {
         this.C9_f122.currentDialog.getChildById(6).setVisible(true);
         this.C9_f122.currentDialog.getChildById(7).setVisible(true);
         this.C9_f122.currentDialog.getChildById(10).setVisible(false);
         this.C9_f122.currentDialog.getChildById(11).setVisible(false);
      } else {
         this.C9_f122.currentDialog.getChildById(6).setVisible(false);
         this.C9_f122.currentDialog.getChildById(7).setVisible(false);
         this.C9_f122.currentDialog.getChildById(10).setVisible(true);
         this.C9_f122.currentDialog.getChildById(11).setVisible(true);
         this.C9_f122.currentDialog.getChildById(10).getComponentData().text = "Xác nhận";
         this.C9_f122.currentDialog.getChildById(11).getComponentData().text = "Quay lại";
      }
   }

   private void c(String var1, String var2) {
      this.C9_f122.currentDialog.getChildById(8).getComponentData().text = var1;
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = var2;
   }

   private void bF() {
      this.C9_f122.removeDialog("/data/ui/smsInfo.ui");
   }

   public final void aM() {
      if (!this.C9_f122.containsDialog("/data/ui/smsTip.ui")) {
         this.C9_f122.showDialog("/data/ui/smsTip.ui", 257, this);
      }

      for (int var1 = 0; var1 < 3; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1 + 6).setVisible(false);
      }

      this.C9_f132 = true;
   }

   public final void d(String var1) {
      this.C9_f132 = true;
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = var1;
   }

   public final void aN() {
      this.C9_f122.removeDialog("/data/ui/smsTip.ui");
   }

   public final void aO() {
      switch (this.C9_f121.getPaymentType()) {
         case 0:
            if (!this.C9_f121.isKeyPressed(16400) && !this.C9_f121.isKeyPressed(32832)) {
               if (this.C9_f121.isKeyPressed(131072)) {
                  if (this.C9_f121.getTotalPaymentCount() > 1) {
                     this.C9_f121.setPaymentState((byte) 1);
                     return;
                  }

                  this.C9_f121.handlePaymentAction(1);
                  return;
               }

               if (this.C9_f121.isKeyPressed(786432)) {
                  this.bF();
                  this.C9_f121.setPaymentState((byte) 5);
                  this.C9_f121.changeState(this.C9_f121.previousState);
                  return;
               }
            }
            break;
         case 1:
            if (this.C9_f121.isKeyPressed(131072)) {
               this.C9_f121.handlePaymentDialogResponse(1);
               return;
            }

            if (this.C9_f121.isKeyPressed(262144)) {
               this.C9_f121.handlePaymentDialogResponse(2);
               return;
            }
            break;
         case 2:
            boolean var1 = false;
            if (this.C9_f121.currentState != 100) {
               var1 = true;
            } else {
               if (this.C9_f137 >= this.C9_f159.length && this.aA()) {
                  var1 = true;
               } else if (this.aA()) {
                  this.b(this.C9_f159[this.C9_f137]);
                  ++this.C9_f137;
               }

               this.f();
            }

            if (var1 && this.bC() && this.C9_f121.isKeyPressed(917504)) {
               this.C9_f137 = 0;
               if (this.C9_f121.isPaymentComplete()) {
                  this.bF();
                  this.aN();
                  this.C9_f121.changeState(this.C9_f121.previousState);
               } else {
                  this.C9_f121.setPaymentState((byte) 5);
               }

               this.C9_f131 = 0;
               return;
            }
            break;
         case 3:
            if (this.C9_f121.isKeyPressed(393216)) {
               this.C9_f121.handlePaymentDialogResponse(1);
            }
      }

   }

   public final void a(byte var1, int var2, int var3) {
      this.C9_f126 = 0;
      this.C9_f162 = var1;
      this.C9_f163 = (byte) var2;
      label23: switch (var2) {
         case 0:
            this.C9_f122.showDialog("/data/ui/wharf1.ui", 257, this);
            this.C9_f122.currentDialog.getChildById(8).getComponentData().text = GameEngineBase.getLocalizedText(var3);
            var2 = 0;

            while (true) {
               if (var2 >= this.C9_f160[var1].length) {
                  break label23;
               }

               this.C9_f122.currentDialog.getChildById(var2 + 5).getComponentData().text = GameEngineBase
                     .getLocalizedText((int) this.C9_f160[var1][var2]);
               ++var2;
            }
         case 1:
            this.C9_f122.showDialog("/data/ui/wharf2.ui", 257, this);
            this.C9_f122.currentDialog.getChildById(10).getComponentData().text = GameEngineBase.getLocalizedText(var3);

            for (var2 = 0; var2 < this.C9_f160[var1].length; ++var2) {
               this.C9_f122.currentDialog.getChildById(var2 + 5).getComponentData().text = GameEngineBase
                     .getLocalizedText((int) this.C9_f160[var1][var2]);
            }
      }

      this.C9_f122.currentDialog.getChildById(5 + this.C9_f160[var1].length).getComponentData().text = "Không ra hàng";
   }

   public final void aP() {
      if (this.C9_f121.isKeyPressed(4100) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.isKeyPressed(8448) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.isKeyPressed(196640) && !this.j()) {
         if (this.C9_f126 == this.C9_f161[this.C9_f162].length / 9) {
            switch (this.C9_f163) {
               case 0:
                  this.C9_f122.removeDialog("/data/ui/wharf1.ui");
                  break;
               case 1:
                  this.C9_f122.removeDialog("/data/ui/wharf2.ui");
            }

            if (GameWorldManager.C25_f318 != -1 && GameWorldManager.B().C25_f287[GameWorldManager.C25_f318].v() == 0) {
               GameWorldManager.B().a((byte) 13, GameWorldManager.B().C25_f287[GameWorldManager.C25_f318].worldX,
                     GameWorldManager.B().C25_f287[GameWorldManager.C25_f318].worldY - 40,
                     GameWorldManager.B().C25_f287[GameWorldManager.C25_f318]);
            }

            this.C9_f121.changeState((byte) 0);
         } else {
            label59: {
               short var10001 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 6];
               short[] var10002 = this.C9_f161[this.C9_f162];
               if (GameWorldManager.B().C25_f348.questStates[GameWorldManager.e(var10001,
                     var10002[this.C9_f126 * 9 + 7])] != null) {
                  var10001 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 6];
                  var10002 = this.C9_f161[this.C9_f162];
                  if (GameWorldManager.B().C25_f348.questStates[GameWorldManager.e(var10001,
                        var10002[this.C9_f126 * 9 + 7])][this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 8]] == 3) {
                     GameWorldManager.B().C25_f290 = this.C9_f161[this.C9_f162][this.C9_f126 * 9];
                     GameWorldManager.B().C25_f291 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 1];
                     GameWorldManager.B().C25_f293 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 2];
                     GameWorldManager.B().C25_f294 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 3];
                     GameWorldManager.C25_f320 = (byte) this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 4];
                     GameWorldManager.B().C25_f295 = -1;
                     TransitionScreen.getInstance()
                           .setTransitionDirection((byte) this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 5]);
                     this.C9_f121.changeState((byte) 29);
                     switch (this.C9_f163) {
                        case 0:
                           this.C9_f122.removeDialog("/data/ui/wharf1.ui");
                           break label59;
                        case 1:
                           this.C9_f122.removeDialog("/data/ui/wharf2.ui");
                        default:
                           break label59;
                     }
                  }
               }

               this.b("Đường thủy chưa mở");
            }
         }
      } else if (this.C9_f121.isKeyPressed(262144) && !this.j()) {
         if (GameWorldManager.C25_f318 != -1 && GameWorldManager.B().C25_f287[GameWorldManager.C25_f318].v() == 0) {
            GameWorldManager.B().a((byte) 13, GameWorldManager.B().C25_f287[GameWorldManager.C25_f318].worldX,
                  GameWorldManager.B().C25_f287[GameWorldManager.C25_f318].worldY - 40,
                  GameWorldManager.B().C25_f287[GameWorldManager.C25_f318]);
         }

         switch (this.C9_f163) {
            case 0:
               this.C9_f122.removeDialog("/data/ui/wharf1.ui");
               break;
            case 1:
               this.C9_f122.removeDialog("/data/ui/wharf2.ui");
         }

         this.C9_f121.changeState((byte) 0);
      }

      this.f();
   }

   public final void aQ() {
      this.C9_f125 = 0;
      this.C9_f122.showDialog("/data/ui/shopbuy.ui", 257, this);
      this.C9_f125 = 0;
      this.C9_f131 = 0;
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = 1;
      ((UIContainerComponent) this.C9_f122.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
      this.C9_f122.currentDialog.getChildById(41).setVisible(false);
      this.C9_f122.currentDialog.getChildById(43).setVisible(false);
      this.C9_f122.currentDialog.getChildById(5).getComponentData().text = "Mua";
      this.C9_f122.currentDialog.getChildById(57).setVisible(true);
      this.C9_f122.currentDialog.getChildById(58).setVisible(true);
      this.C9_f122.currentDialog.getChildById(57).getComponentData().text = "Mua sắm";
      this.C9_f122.currentDialog.getChildById(58).getComponentData().text = "Quay lại";
      this.C9_f122.currentDialog.getChildById(39).setVisible(false);
      this.C9_f122.currentDialog.getChildById(40).setVisible(false);
      this.C9_f135 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.scrollOffset;
      this.C9_f136 = ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex;
      if (this.C9_f122.currentDialog.getChildById(51).getComponentData().renderer == null) {
         this.C9_f122.currentDialog.getChildById(51).getComponentData().renderer = new SpriteRenderer();
         this.C9_f122.currentDialog.getChildById(51).getComponentData().renderer.setSpriteIndex((int) 0);
         this.C9_f122.currentDialog.getChildById(51).getComponentData().renderer.spriteType = 2;
         this.C9_f122.currentDialog.getChildById(51).getComponentData().renderer.initializeSprite(258, false,
               (byte) -1);
      }

      this.C9_f122.currentDialog.getChildById(51).getComponentData().renderer
            .setSpriteIndex((int) ResourceManager.gameDatabase[5][0][1]);
      this.C9_f122.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
            .getLocalizedText((int) ResourceManager.gameDatabase[5][0][0]);
      this.C9_f122.currentDialog.getChildById(15).getComponentData().text = "5000";
      this.C9_f122.currentDialog.getChildById(45).getComponentData().renderer.setSpriteIndex((int) 84);
      this.C9_f122.currentDialog.getChildById(56).getComponentData().text = "Ấp trứng ra sủng vật";
      this.C9_f122.currentDialog.getChildById(44).getComponentData().text = "" + this.C9_f123.getGold();
      this.C9_f122.currentDialog.getChildById(38).setOffsetY(
            102 + this.C9_f136 * 84 / ResourceManager.gameDatabase[5].length,
            this.C9_f122.currentDialog.getUIContainerComponent());
   }

   private void bG() {
      this.C9_f122.removeDialog("/data/ui/shopbuy.ui");
   }

   public final int aR() {
      if (this.C9_f121.isKeyPressed(196640)) {
         if (this.C9_f131 == 0) {
            if (this.C9_f123.hasEnoughGold(5000)) {
               if (this.C9_f123.isSkillActive(0)) {
                  this.H();
                  this.a("Đã có trứng sủng vật, không cần mua sắm", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 2;
               } else {
                  this.C9_f123.useSkillOnPokemon(0, -1);
                  this.H();
                  this.a("Đã thành công mua sắm #2 trứng sủng vật", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 2;
               }
            } else {
               this.H();
               this.a("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
               this.C9_f131 = 1;
            }
         } else if (this.C9_f131 > 0) {
            this.I();
            if (this.C9_f131 == 1) {
               this.C9_f121.changeState((byte) 102);
            } else if (this.C9_f131 == 2) {
               QuestManager.questOptionIndex = 0;
               this.bG();
               this.C9_f121.changeState((byte) 0);
            }
         }
      } else if (this.C9_f121.isKeyPressed(262144) && this.C9_f131 == 0) {
         QuestManager.questOptionIndex = 1;
         this.bG();
         this.C9_f121.changeState((byte) 0);
      }

      return -1;
   }

   public final void aS() {
      this.C9_f122.showDialog("/data/ui/wharf2.ui", 257, this);
      ((UIContainerComponent) this.C9_f122.currentDialog
            .getChildById(0)).primaryListComponent.selectedIndex = this.C9_f128;
      this.C9_f131 = 0;
      this.C9_f122.currentDialog.getChildById(10).getComponentData().text = "Tiện lợi điếm";
      this.C9_f122.currentDialog.getChildById(12).getComponentData().text = "Tiến vào";

      for (int var1 = 0; var1 < this.C9_f164.length; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1 + 5).getComponentData().text = this.C9_f164[var1];
      }

   }

   public final void aT() {
      if (this.C9_f121.isKeyPressed(4100) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.isKeyPressed(8448) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.isKeyPressed(196640) && !this.j()) {
         if (this.C9_f131 == 0) {
            switch (this.C9_f128) {
               case 0:
                  this.C9_f122.removeDialog("/data/ui/wharf2.ui");
                  this.C9_f121.changeState((byte) 31);
                  return;
               case 1:
               case 2:
                  GameWorldManager.B();
                  if (GameWorldManager.C25_f339) {
                     this.C9_f122.removeDialog("/data/ui/wharf2.ui");
                     this.C9_f126 = 0;
                     this.C9_f121.changeState((byte) 7);
                     return;
                  }

                  this.H();
                  this.C9_f131 = 1;
                  this.a("Công năng theo đạo học sau mở ra", "Nhấn nút 5 tiếp tục");
                  return;
               case 3:
                  this.C9_f122.removeDialog("/data/ui/wharf2.ui");
                  this.C9_f121.changeState((byte) 32);
                  return;
               case 4:
                  this.C9_f122.removeDialog("/data/ui/wharf2.ui");
                  this.C9_f121.changeState((byte) 0);
               default:
            }
         } else {
            this.C9_f131 = 0;
            this.I();
            this.C9_f132 = true;
         }
      } else {
         if (this.C9_f121.isKeyPressed(262144) && this.C9_f131 == 0 && !this.j()) {
            this.C9_f122.removeDialog("/data/ui/wharf2.ui");
            this.C9_f121.changeState((byte) 0);
         }

      }
   }

   public final void action(int[] navigationData, int[] selectionData) {
      this.C9_f140 = navigationData;
      if (this.C9_f121 instanceof GameWorldManager) {
         switch (((GameWorldManager) this.C9_f121).currentState) {
            case 0:
               return;
            case 1:
               this.C9_f125 = navigationData[0];
               return;
            case 2:
            case 26:
            case 32:
               this.c(navigationData);
               return;
            case 3:
               if (this.C9_f131 != 0) {
                  this.C9_f127 = navigationData[0];
                  return;
               }

               this.C9_f125 = navigationData[0];
               this.aZ();
               break;
            case 4:
               return;
            case 5:
               this.C9_f125 = navigationData[1];
               this.bv();
               return;
            case 6:
               this.C9_f125 = navigationData[0];
               if (!GameEngineBase.paymentActive) {
                  this.C9_f122.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
                        .getLocalizedText(606 + this.C9_f125);
                  return;
               }

               this.C9_f122.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
                     .getLocalizedText(605 + this.C9_f125);
               break;
            case 7:
               this.b(navigationData);
               return;
            case 8:
               if (navigationData[0] >= 0) {
                  this.C9_f126 = navigationData[0];
               }

               if (navigationData[1] >= 0) {
                  this.C9_f125 = navigationData[1];
               }

               this.br();
               return;
            case 9:
               this.C9_f126 = navigationData[1];
               return;
            case 10:
               this.C9_f125 = navigationData[1];
               switch (this.C9_f125) {
                  case 0:
                     this.C9_f126 = navigationData[0];
                     return;
                  case 1:
                     this.C9_f127 = navigationData[0];
                  default:
                     return;
               }
            case 11:
               this.C9_f126 = navigationData[0];
               this.C9_f125 = navigationData[1];
               return;
            case 12:
               this.C9_f125 = navigationData[1];
               return;
            case 13:
               if (this.C9_f131 == 0) {
                  this.C9_f125 = navigationData[0];
                  return;
               }

               this.C9_f126 = navigationData[0];
               return;
            case 14:
               this.C9_f126 = navigationData[0];
               return;
            case 15:
               this.C9_f126 = navigationData[0];
               return;
            case 16:
               this.C9_f125 = navigationData[0];
               return;
            case 17:
            case 18:
            case 19:
               this.C9_f126 = navigationData[0];
               return;
            case 20:
               this.C9_f126 = navigationData[1];
               return;
            case 24:
               this.C9_f126 = navigationData[0];
               return;
            case 27:
               this.C9_f128 = navigationData[0];
            case 21:
            case 22:
            case 23:
            case 25:
            case 29:
            case 30:
            case 31:
            default:
               return;
            case 28:
               this.C9_f126 = navigationData[0];
               return;
         }
      } else if (this.C9_f121 instanceof BattleSystemManager) {
         switch (((BattleSystemManager) this.C9_f121).currentState) {
            case 2:
               return;
            case 3:
               this.C9_f129 = navigationData[0];
               return;
            case 4:
               this.C9_f125 = navigationData[0];
               this.bi();
               return;
            case 5:
               this.b(navigationData);
               return;
            case 6:
               return;
            case 7:
               return;
            case 8:
               return;
            case 9:
               return;
            case 10:
               return;
            case 11:
               this.c(navigationData);
               return;
            case 12:
               return;
            case 13:
               return;
            case 14:
               return;
            case 15:
               return;
            case 16:
               this.C9_f126 = navigationData[0];
               this.g(this.C9_f126);
               return;
            case 17:
               return;
            case 18:
               return;
            case 19:
               return;
            case 20:
               this.C9_f124 = navigationData[1];
               this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(true);
               return;
            case 21:
               this.C9_f125 = navigationData[0];
            case 23:
               this.C9_f125 = navigationData[0];
            case 22:
         }
      }

   }

   private void b(int[] var1) {
      if (this.C9_f131 == 0) {
         this.C9_f125 = var1[0];
         this.g(this.C9_f125);
      } else if (this.C9_f131 == 1) {
         this.C9_f126 = var1[0];
      } else {
         if (this.C9_f131 == 2) {
            this.C9_f127 = var1[0];
            switch (this.C9_f126) {
               case 0:
                  this.bi();
            }
         }

      }
   }

   private void c(int[] var1) {
      if (this.C9_f131 == 0) {
         this.C9_f125 = var1[0];
      } else {
         this.C9_f127 = var1[0];
      }
   }
}
