package game;

import a.GameUtils;
import a.C44;
import a.a.C21;
import a.a.C30;
import a.a.C69;
import a.b.C67;
import c.DialogData;
import c.C17;
import c.RootComponent;
import c.DialogManager;
import c.Dialog;
import c.DialogHandler;
import java.util.Vector;

public final class C9 implements DialogHandler {
   private static C9 C9_f120;
   private C44 C9_f121;
   private DialogManager C9_f122 = DialogManager.getInstance();
   private C53 C9_f123;
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
   private static String[] C9_f142 = new String[]{"Thủy Kimura", "Bích Thủy thành", "Nguyên Mộc Thành", "Niêm Thổ Thành", "Hắc Thạch thành", "Thiên không", "Xa cổ"};
   private static short[] C9_f143 = new short[]{1, 0, 196, 208, 0, 2, 1, 196, 208, 0, 3, 3, 196, 208, 0, 4, 5, 320, 352, 0, 5, 3, 320, 196, 0, 7, 2, 288, 112, 0, 8, 0, 160, 144, 0};
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
   private String[] C9_f157 = new String[]{"/data/ui/option.ui", "/data/ui/answer.ui", "/data/ui/wharf1.ui"};
   public short[] C9_f158 = new short[]{9, 0, 120, 448, 9, 1, 136, 272, 9, 2, 208, 256, 9, 3, 80, 264, 9, 4, 112, 288, 9, 5, 40, 280, 9, 6, 136, 328, 9, 7, 104, 328};
   private String[] C9_f159 = new String[]{"Đạt được 2000 kim tiền", "Đạt được 5 Phong ấn cầu", "Đạt được 5 Bánh Sandwich", "Đạt được 2 Sinh mệnh thạch", "Đạt được 2 huy hiệu"};
   private short[][] C9_f160 = new short[][]{{621, 622}, {623, 624}, {625, 626}, {627, 628}, {629, 630, 631, 632}};
   private short[][] C9_f161 = new short[][]{{5, 2, 112, 224, 2, 2, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10}, {4, 0, 48, 176, 2, 2, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10}, {3, 6, 288, 224, 3, 0, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10}, {1, 5, 272, 128, 3, 0, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10}, {1, 5, 272, 128, 3, 2, 0, 0, 0, 3, 6, 288, 224, 3, 0, 0, 0, 0, 4, 0, 48, 176, 2, 0, 0, 0, 0, 5, 2, 112, 224, 2, 2, 0, 0, 0}};
   private byte C9_f162;
   private byte C9_f163;
   private String[] C9_f164 = new String[]{"Dẫn thưởng", "Tiến hóa", "Dị hóa", "Tài liệu", "Cách mở"};

   public static C9 a() {
      if (C9_f120 == null) {
         C9_f120 = new C9();
      }

      return C9_f120;
   }

   public C9() {
      if (this.C9_f123 == null) {
         this.C9_f123 = C53.p();
      }

   }

   public final void b() {
      C9_f120 = null;
      this.C9_f123 = null;
   }

   public final void a(C44 var1) {
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
         for(int var1 = 1; var1 <= 7; ++var1) {
            this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
         }
      }

   }

   public void e() {
      if (this.C9_f133 < 2 && !C7.C7_f67 && C25.C25_f338 && this.C9_f122.containsDialog("/data/ui/world.ui")) {
         Dialog var2 = this.C9_f122.getDialog("/data/ui/world.ui");
          if (var2.getChildById(1).getComponentData().C12_f195.a().b(4)) {
            this.C9_f122.getDialog("/data/ui/world.ui").getChildById(6).getComponentData().C12_f179 = ((C25)this.C9_f121).C25_f296;
            this.C9_f133 = 1;
         } else if (this.C9_f133 == 1) {
             var2 = this.C9_f122.getDialog("/data/ui/world.ui");
             if (var2.getChildById(1).getComponentData().C12_f195.a().h() >= 5) {
               this.C9_f122.getDialog("/data/ui/world.ui").getChildById(6).getComponentData().C12_f179 = "";
               this.C9_f133 = 2;
               C25.C25_f338 = false;
            }
         }
      }

      this.f();
   }

   public boolean f() {
      if (this.C9_f134 < 2 && this.C9_f122.isTopDialog("/data/ui/openbox.ui")) {
         if (this.C9_f134 == 1) {
            if (this.C9_f121.g(196640)) {
               this.C9_f122.currentDialog.getChildById(2).getComponentData().C12_f179 = "";
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
               if (this.C9_f121.g(196640)) {
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
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = C9_f142.length;
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
      this.aV();
   }

   private void aV() {
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;

      for(int var1 = 0; var1 < 5; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1 + 5).getComponentData().C12_f179 = C9_f142[var1 + this.C9_f135];
      }

      this.C9_f122.currentDialog.getChildById(13).setOffsetY(109 + this.C9_f136 * 88 / C9_f142.length, this.C9_f122.currentDialog.getRootComponent());
   }

   public final void i() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.aV();
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.aV();
      } else if (this.C9_f121.g(196640)) {
         C25.B().C25_f290 = C9_f143[this.C9_f136 * 5];
         C25.B().C25_f291 = C9_f143[this.C9_f136 * 5 + 1];
         C25.B().C25_f293 = C9_f143[this.C9_f136 * 5 + 2];
         C25.B().C25_f294 = C9_f143[this.C9_f136 * 5 + 3];
         C25.C25_f320 = (byte)C9_f143[this.C9_f136 * 5 + 4];
         C25.B().C25_f295 = -1;
         C55.B().a((byte)9);
      } else {
         if (this.C9_f121.g(262144)) {
            this.C9_f121.a((byte)8);
            this.C9_f122.removeDialog("/data/ui/transmit.ui");
         }

      }
   }

   public final boolean j() {
      return this.C9_f122.isTopDialog("/data/ui/openbox.ui") || this.C9_f122.isTopDialog("/data/ui/taskTip.ui");
   }

   public final void k() {
      String[] var1 = new String[]{"Tùy thân cửa hàng", "Sủng vật", "Lưng bao", "Đồ giám", "Nhiệm vụ", "Lưu dữ liệu"};
      this.aU();
      this.C9_f122.showDialog("/data/ui/gamemenu.ui", 257, this);
      int var2;
      if (C44.C44_f711) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = 6;
         this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = C44.c(605 + this.C9_f125);
         this.C9_f122.currentDialog.getChildById(15).getComponentData().C12_f179 = var1[0];

         for(var2 = 0; var2 < 5; ++var2) {
            this.C9_f122.currentDialog.getChildById(var2 + 5).getComponentData().C12_f179 = var1[var2 + 1];
         }
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = 5;
         this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = C44.c(606 + this.C9_f125);
         this.C9_f122.currentDialog.getChildById(15).getComponentData().C12_f179 = var1[1];

         for(var2 = 0; var2 < 4; ++var2) {
            this.C9_f122.currentDialog.getChildById(var2 + 5).getComponentData().C12_f179 = var1[var2 + 2];
         }

         this.C9_f122.currentDialog.getChildById(9).setVisible(false);
      }

      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f125;
      this.C9_f122.currentDialog.getChildById(18).getComponentData().C12_f179 = "" + this.C9_f123.G();
      this.C9_f122.currentDialog.getChildById(19).getComponentData().C12_f179 = "" + this.C9_f123.F();
      this.C9_f131 = 0;
   }

   public final void l() {
      this.C9_f121.q();
      if (!C44.a((int)this.C9_f125, (int)0) && !this.j() && this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (!C44.a((int)this.C9_f125, (int)0) && !this.j() && this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (!this.j() && C44.s() && this.C9_f121.g(196640)) {
         if (C44.p() && !C44.a((int)this.C9_f125, (int)0)) {
            return;
         }

         if (C44.C44_f711) {
            switch(this.C9_f125) {
            case 0:
               this.C9_f121.a((byte)14);
               this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
               break;
            case 1:
               this.C9_f126 = 0;
               this.C9_f121.r();
               this.C9_f121.a((byte)7);
               this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
               break;
            case 2:
               this.C9_f121.r();
               this.C9_f121.a((byte)8);
               this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
               break;
            case 3:
               this.C9_f126 = 0;
               this.C9_f121.a((byte)9);
               this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
               break;
            case 4:
               this.C9_f125 = 0;
               this.C9_f121.a((byte)10);
               this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
               break;
            case 5:
               this.C9_f122.currentDialog.getChildById(11).setVisible(false);
               this.C9_f122.currentDialog.getChildById(12).setVisible(false);
               this.C9_f121.a((byte)22);
            }
         } else {
            switch(this.C9_f125) {
            case 0:
               this.C9_f126 = 0;
               this.C9_f121.r();
               this.C9_f121.a((byte)7);
               this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
               break;
            case 1:
               this.C9_f121.r();
               this.C9_f121.a((byte)8);
               this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
               break;
            case 2:
               this.C9_f126 = 0;
               this.C9_f121.a((byte)9);
               this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
               break;
            case 3:
               this.C9_f125 = 0;
               this.C9_f121.a((byte)10);
               this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
               break;
            case 4:
               this.C9_f122.currentDialog.getChildById(11).setVisible(false);
               this.C9_f122.currentDialog.getChildById(12).setVisible(false);
               this.C9_f121.a((byte)22);
            }
         }
      } else if (C44.t() && this.C9_f121.g(262144)) {
         this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
         this.C9_f121.a((byte)0);
      }

      this.g();
   }

   public final void m() {
      this.aU();
      this.C9_f122.showDialog("/data/ui/gamesystem.ui", 257, this);
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f125;
      this.C9_f131 = 0;
   }

   public final void n() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.g(196640)) {
         switch(this.C9_f125) {
         case 0:
            this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
            this.C9_f121.a((byte)0);
            return;
         case 1:
            this.C9_f121.a((byte)20);
            this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
            return;
         case 2:
            this.C9_f121.a((byte)21);
            this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
            return;
         case 3:
            if (this.C9_f131 == 0) {
               this.C9_f122.showDialog("/data/ui/option.ui", 257, this);
               this.C9_f126 = 1;
               ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f126;
               this.C9_f122.currentDialog.getChildById(12).getComponentData().C12_f179 = "";
               this.C9_f122.currentDialog.getChildById(13).getComponentData().C12_f179 = "Không";
               this.C9_f131 = 1;
               return;
            } else {
               switch(this.C9_f126) {
               case 0:
                  C55.B().C55_f824 = 0L;
                  C55.B().C55_f823 = 0L;
                  C53.p().C53_f776 = false;
                  C55.B().a((byte)7);
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
         if (this.C9_f121.g(262144)) {
            if (this.C9_f131 == 0) {
               this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
               this.C9_f121.a((byte)0);
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
         this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Trợ giúp";
         this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "Nhấn nút 2, 4, 6, 8 để di chuyển#nNút 5: công kích, đối thoại, xác nhận#nNút 1: Xem nhiệm vụ#nNút 9: lựa chọn sủng vật cưỡi#nNút 0: Xem bản đồ#nNút mềm trái: menu hệ thống#nNút mềm phải: menu trò chơi";

         for(var2 = 0; var2 < 28; ++var2) {
            this.C9_f122.currentDialog.getChildById(var2 + 9).setVisible(false);
         }
      } else if (var1 > 0) {
         this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "";

         for(var2 = 0; var2 < 14; ++var2) {
            this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).setVisible(true);
            this.C9_f122.currentDialog.getChildById(9 + (var2 << 1) + 1).setVisible(true);
            if ((var1 - 1) * 14 + var2 < 26) {
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().C12_f195.a(325, false, (byte)-2);
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().C12_f195.a((var1 - 1) * 14 + var2 + 1);
               if ((var1 - 1) * 14 + var2 <= 10) {
                  this.C9_f122.currentDialog.getChildById(9 + (var2 << 1) + 1).getComponentData().C12_f179 = C44.c(var2 + 311);
               } else {
                  this.C9_f122.currentDialog.getChildById(9 + (var2 << 1) + 1).getComponentData().C12_f179 = C44.c(333 + ((var1 - 1) * 14 + var2 - 11));
               }
            } else {
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1)).setVisible(false);
               this.C9_f122.currentDialog.getChildById(9 + (var2 << 1) + 1).setVisible(false);
            }
         }
      }

      this.C9_f122.currentDialog.getChildById(39).getComponentData().C12_f179 = var1 + 1 + "/3";
   }

   public final void p() {
      if (this.C9_f121.g(16400)) {
         --this.C9_f125;
         if (this.C9_f125 <= 0) {
            this.C9_f125 = 0;
         }

         this.e(this.C9_f125);
      } else if (this.C9_f121.g(32832)) {
         ++this.C9_f125;
         if (this.C9_f125 >= 2) {
            this.C9_f125 = 2;
         }

         this.e(this.C9_f125);
      } else {
         if (this.C9_f121.g(262144)) {
            this.C9_f121.a((byte)0);
            this.C9_f122.removeDialog("/data/ui/help1.ui");
         }

      }
   }

   public final void q() {
      this.C9_f122.showDialog("/data/ui/help.ui", 257, this);
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Quan tại";
      this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "Tên trò chơi: Sủng vật Vương quốc - Liệt hỏa#nViệt hóa: BIGAME#nWapsite: 3g.mwap.biz";
      this.C9_f122.currentDialog.getChildById(6).setVisible(true);
      this.C9_f122.currentDialog.getChildById(7).setVisible(false);

      for(int var1 = 9; var1 < 13; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
      }

   }

   public final void r() {
      if (this.C9_f121.g(262144)) {
         this.C9_f121.a((byte)0);
         this.C9_f122.removeDialog("/data/ui/help.ui");
      }

   }

   public final void s() {
      this.C9_f122.showDialog("/data/ui/help.ui", 257, this);
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Tùy chọn";
      this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "";
      this.C9_f122.currentDialog.getChildById(6).setVisible(false);
      this.C9_f122.currentDialog.getChildById(7).setVisible(true);

      for(int var1 = 9; var1 < 13; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1).setVisible(true);
      }

      this.aW();
   }

   private void aW() {
      for(int var1 = 1; var1 < 4; ++var1) {
         if (var1 <= C55.B().C55_f831) {
            this.C9_f122.currentDialog.getChildById(var1 + 9).getComponentData().C12_f192 = -2148;
         } else {
            this.C9_f122.currentDialog.getChildById(var1 + 9).getComponentData().C12_f192 = -8540732;
         }
      }

   }

   public final void t() {
      if (this.C9_f121.g(16400)) {
         C55.B().G();
         this.aW();
      } else if (this.C9_f121.g(32832)) {
         C55.B().F();
         this.aW();
      } else {
         if (this.C9_f121.g(131072)) {
            C13.B().C13_f205 = C55.B().C55_f831;
            this.C9_f121.a((byte)0);
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
      switch(var1) {
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
         for(var1 = 0; var1 < 2; ++var1) {
            if (this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195.a(336, false, (byte)0);
               if (var1 == 0) {
                  this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195.a((int)8);
               } else {
                  this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195.a((int)10);
               }
            }
         }

         return;
      case 5:
         this.C9_f122.currentDialog.getChildById(16).setVisible(false);
         this.C9_f122.currentDialog.getChildById(17).setVisible(false);

         for(var1 = 0; var1 < 2; ++var1) {
            this.C9_f122.currentDialog.getChildById(var1 + 16).cleanUp();
            if (this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().C12_f195.a(336, false, (byte)0);
               if (var1 == 0) {
                  this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().C12_f195.a((int)8);
               } else {
                  this.C9_f122.currentDialog.getChildById(var1 + 18).getComponentData().C12_f195.a((int)11);
               }
            }
         }

         return;
      case 6:
         this.C9_f122.currentDialog.getChildById(19).setVisible(false);
         this.C9_f122.currentDialog.getChildById(19).cleanUp();
         if (this.C9_f122.currentDialog.getChildById(20).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(20).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(20).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(20).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(20).getComponentData().C12_f195.a(336, false, (byte)0);
            this.C9_f122.currentDialog.getChildById(20).getComponentData().C12_f195.a((int)12);
            return;
         }
         break;
      case 7:
         this.C9_f122.currentDialog.getChildById(20).setVisible(false);
         this.C9_f122.currentDialog.getChildById(20).cleanUp();
         return;
      case 8:
         for(var1 = 0; var1 < 2; ++var1) {
            if (this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().C12_f195.a(336, false, (byte)0);
               if (var1 == 0) {
                  this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().C12_f195.a((int)7);
               } else {
                  this.C9_f122.currentDialog.getChildById(var1 + 21).getComponentData().C12_f195.a((int)13);
               }
            }
         }

         return;
      case 9:
         this.C9_f122.currentDialog.getChildById(21).setVisible(false);
         this.C9_f122.currentDialog.getChildById(22).setVisible(false);

         for(var1 = 0; var1 < 2; ++var1) {
            this.C9_f122.currentDialog.getChildById(var1 + 21).cleanUp();
            if (this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().C12_f195.a(336, false, (byte)0);
               if (var1 == 0) {
                  this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().C12_f195.a((int)7);
               } else {
                  this.C9_f122.currentDialog.getChildById(var1 + 23).getComponentData().C12_f195.a((int)14);
               }
            }
         }

         return;
      case 10:
         this.C9_f122.currentDialog.getChildById(24).setVisible(false);
         this.C9_f122.currentDialog.getChildById(24).cleanUp();
         if (this.C9_f122.currentDialog.getChildById(25).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(25).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(25).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(25).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(25).getComponentData().C12_f195.a(336, false, (byte)0);
            this.C9_f122.currentDialog.getChildById(25).getComponentData().C12_f195.a((int)15);
            return;
         }
         break;
      case 11:
         this.C9_f122.currentDialog.getChildById(25).setVisible(false);
         this.C9_f122.currentDialog.getChildById(25).cleanUp();
         return;
      case 12:
         if (this.C9_f122.currentDialog.getChildById(26).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(26).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(26).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(26).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(26).getComponentData().C12_f195.a(336, false, (byte)0);
            this.C9_f122.currentDialog.getChildById(26).getComponentData().C12_f195.a((int)5);
            return;
         }
         break;
      case 13:
         this.C9_f122.currentDialog.getChildById(26).setVisible(false);
         this.C9_f122.currentDialog.getChildById(26).cleanUp();
         if (this.C9_f122.currentDialog.getChildById(27).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(27).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(27).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(27).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(27).getComponentData().C12_f195.a(336, false, (byte)0);
            this.C9_f122.currentDialog.getChildById(27).getComponentData().C12_f195.a((int)5);
            return;
         }
         break;
      case 14:
         return;
      case 15:
         if (this.C9_f122.currentDialog.getChildById(28).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(28).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(28).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(28).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(28).getComponentData().C12_f195.a(336, false, (byte)0);
            this.C9_f122.currentDialog.getChildById(28).getComponentData().C12_f195.a((int)6);
            return;
         }
         break;
      case 16:
         this.C9_f122.currentDialog.getChildById(28).setVisible(false);
         this.C9_f122.currentDialog.getChildById(28).cleanUp();
         if (this.C9_f122.currentDialog.getChildById(29).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(29).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(29).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(29).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(29).getComponentData().C12_f195.a(336, false, (byte)0);
            this.C9_f122.currentDialog.getChildById(29).getComponentData().C12_f195.a((int)6);
            return;
         }
         break;
      case 17:
         this.C9_f122.currentDialog.getChildById(29).setVisible(false);
         this.C9_f122.currentDialog.getChildById(29).cleanUp();
         if (this.C9_f122.currentDialog.getChildById(30).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(30).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(30).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(30).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(30).getComponentData().C12_f195.a(336, false, (byte)0);
            this.C9_f122.currentDialog.getChildById(30).getComponentData().C12_f195.a((int)6);
            return;
         }
         break;
      case 18:
      case 19:
         this.C9_f122.currentDialog.getChildById(30).setVisible(false);
         this.C9_f122.currentDialog.getChildById(30).cleanUp();
         if (this.C9_f122.currentDialog.getChildById(31).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(31).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(31).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(31).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(31).getComponentData().C12_f195.a(336, false, (byte)0);
            this.C9_f122.currentDialog.getChildById(31).getComponentData().C12_f195.a((int)6);
            return;
         }
         break;
      case 20:
         C30.a().c(16777215, 2);
         C30.a().C30_f482 = 85;
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
         C30.a().c(16777215, 1);
         C30.a().C30_f482 = 255;
         if (this.C9_f122.currentDialog.getChildById(32).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(32).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(32).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(32).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(32).getComponentData().C12_f195.a(336, false, (byte)0);
            return;
         }
         break;
      case 25:
         this.C9_f122.currentDialog.getChildById(32).setVisible(false);
         this.C9_f122.currentDialog.getChildById(32).cleanUp();

         for(var1 = 0; var1 < 5; ++var1) {
            if (this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().C12_f195.a(336, false, (byte)0);
               if (var1 == 0) {
                  this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().C12_f195.a((int)0);
               } else if (var1 == 1) {
                  this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().C12_f195.a((int)8);
               } else if (var1 == 2) {
                  this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().C12_f195.a((int)5);
               } else if (var1 == 3) {
                  this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().C12_f195.a((int)7);
               } else if (var1 == 4) {
                  this.C9_f122.currentDialog.getChildById(var1 + 33).getComponentData().C12_f195.a((int)6);
               }
            }
         }

         return;
      case 26:
         C55.B().C55_f829 = C69.b(C55.B().C55_f829, var2);
         return;
      case 27:
         if (var2 > 38) {
            this.C9_f122.currentDialog.getChildById(var2 - 1).setVisible(false);
            this.C9_f122.currentDialog.getChildById(var2 - 1).cleanUp();
         }

         if (this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195.a((int)4);
            this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195.a(336, false, (byte)0);
            return;
         }
         break;
      case 28:
         if (var2 > 43) {
            this.C9_f122.currentDialog.getChildById(var2 - 1).setVisible(false);
            this.C9_f122.currentDialog.getChildById(var2 - 1).cleanUp();
         }

         if (this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195.a((int)1);
            this.C9_f122.currentDialog.getChildById(var2).getComponentData().C12_f195.a(336, false, (byte)0);
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
      if (this.C9_f121.g(16400)) {
         --this.C9_f127;
         if (this.C9_f127 <= 0) {
            this.C9_f127 = 0;
         }

         this.e(this.C9_f127);
      } else if (this.C9_f121.g(32832)) {
         ++this.C9_f127;
         if (this.C9_f127 >= 2) {
            this.C9_f127 = 2;
         }

         this.e(this.C9_f127);
      } else {
         if (this.C9_f121.g(262144)) {
            this.C9_f121.a((byte)13);
            this.C9_f122.removeDialog("/data/ui/help1.ui");
         }

      }
   }

   public final void z() {
      this.s();
      this.C9_f122.removeDialog("/data/ui/gamesystem.ui");
   }

   public final void A() {
      if (this.C9_f121.g(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         C55.B().G();
         if (C25.B().C25_f342 != null) {
            C25.B().C25_f342.b(C55.B().C55_f831);
         }

         this.aW();
      } else if (this.C9_f121.g(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         C55.B().F();
         if (C25.B().C25_f342 != null) {
            C25.B().C25_f342.b(C55.B().C55_f831);
         }

         this.aW();
      } else {
         if (this.C9_f121.g(131072)) {
            this.C9_f121.a((byte)13);
            this.C9_f122.removeDialog("/data/ui/help.ui");
         }

      }
   }

   public final void B() {
      this.C9_f122.showDialog("/data/ui/petstate.ui", 257, this);
      this.C9_f131 = 0;
      if (this.C9_f123.C53_f792.size() > 6) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(-1);
      }

      this.C9_f122.currentDialog.getChildById(2).getComponentData().C12_f179 = "Ngân hàng Sủng vật";
      this.C9_f122.currentDialog.getChildById(75).setVisible(false);
      this.C9_f122.currentDialog.getChildById(76).setVisible(false);
      this.aX();
   }

   private void aX() {
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = this.C9_f123.C53_f792.size();
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;
      if (this.C9_f123.C53_f792.size() >= 6) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f577 = 6;
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f577 = this.C9_f123.C53_f792.size();
      }

      if (this.C9_f136 >= this.C9_f123.C53_f792.size()) {
         this.C9_f136 = this.C9_f123.C53_f792.size() - 1;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f136;
      }

      if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 5) {
         --this.C9_f135;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579 = this.C9_f135;
      }

      int var1;
      for(var1 = 0; var1 < 6; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.C53_f792.size()) {
            int[] var2 = (int[])this.C9_f123.C53_f792.elementAt(this.C9_f135 + var1);
            if (var1 == 0) {
               this.C9_f122.currentDialog.getChildById(14 + var1 * 6).getComponentData().C12_f179 = "" + (this.C9_f135 + var1 + 1);
            } else {
               this.C9_f122.currentDialog.getChildById(15 + var1 * 6).getComponentData().C12_f179 = "" + (this.C9_f135 + var1 + 1);
            }

            this.C9_f122.currentDialog.getChildById(16 + var1 * 6).getComponentData().C12_f179 = "#P" + var2[6] * 100 / C41.a(var2[0], var2[1], var2[4], 1);
            this.C9_f122.currentDialog.getChildById(17 + var1 * 6).getComponentData().C12_f179 = "#P" + C41.a((short)var2[7], (short)var2[1]);
         } else {
            this.C9_f122.currentDialog.getChildById(16 + var1 * 6).getComponentData().C12_f179 = "#P0";
            this.C9_f122.currentDialog.getChildById(17 + var1 * 6).getComponentData().C12_f179 = "#P0";
         }
      }

      int[] var4 = null;
      if (this.C9_f123.C53_f792.size() > 0) {
         this.C9_f122.currentDialog.getChildById(64).setVisible(true);
         var4 = (int[])this.C9_f123.C53_f792.elementAt(this.C9_f136);
      } else {
         this.C9_f122.currentDialog.getChildById(64).setVisible(false);
      }

      if (var4 != null) {
         if (this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195 != null) {
            this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195.c();
         } else {
            this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195.C17_f222 = 3;
         }

         this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195.a(C67.a((byte)0, (short)var4[0], (byte)17), false, (byte)-1);
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f179 = C44.c((int)C67.a((byte)0, (short)var4[0], (byte)0));
         this.C9_f122.currentDialog.getChildById(52).getComponentData().C12_f179 = C44.c(365 + C67.a((byte)0, (short)var4[0], (byte)1));
         if (C67.a((byte)0, (short)var4[0], (byte)19) == -1) {
            this.C9_f122.currentDialog.getChildById(62).getComponentData().C12_f179 = "";
         } else if (C67.C67_f923[0][C67.a((byte)0, (short)var4[0], (byte)19)][2] != 1 && C67.C67_f923[0][C67.a((byte)0, (short)var4[0], (byte)19)][2] != 2) {
            if (C67.C67_f923[0][C67.a((byte)0, (short)var4[0], (byte)19)][2] == 3) {
               this.C9_f122.currentDialog.getChildById(62).getComponentData().C12_f179 = "Có thể dị hoá";
            }
         } else {
            this.C9_f122.currentDialog.getChildById(62).getComponentData().C12_f179 = "Có thể tiến hóa";
         }

         this.C9_f122.currentDialog.getChildById(61).getComponentData().C12_f179 = C41.y(var4[0]);
         if (this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.a(258, false, (byte)-1);
         }

         if (var4[2] != -1) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.a((int)C67.C67_f923[3][var4[2]][1]);
            this.C9_f122.currentDialog.getChildById(60).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[3][var4[2]][0]);
         } else {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(60).getComponentData().C12_f179 = "";
         }

         this.C9_f122.currentDialog.getChildById(65).getComponentData().C12_f179 = "" + var4[1];
         this.C9_f122.currentDialog.getChildById(66).getComponentData().C12_f179 = "" + C41.a(var4[0], var4[1], var4[4], 2);
         this.C9_f122.currentDialog.getChildById(67).getComponentData().C12_f179 = "" + C41.a(var4[0], var4[1], var4[4], 3);
         this.C9_f122.currentDialog.getChildById(68).getComponentData().C12_f179 = "" + C41.a(var4[0], var4[1], var4[4], 4);
         int var5 = var4[4];
         var1 = C67.a((byte)0, (short)var4[0], (byte)4) - 1;

         for(int var3 = 0; var3 < 5; ++var3) {
            this.C9_f122.currentDialog.getChildById(74 - var3).setVisible(true);
            this.C9_f122.currentDialog.getChildById(74 - var3).getComponentData().C12_f195.C17_f222 = 3;
            if (var3 > var1) {
               this.C9_f122.currentDialog.getChildById(74 - var3).setVisible(false);
            } else if (var5 > 0) {
               this.C9_f122.currentDialog.getChildById(74 - var3).getComponentData().C12_f195.a((byte)14, (byte)-1);
               --var5;
            } else {
               this.C9_f122.currentDialog.getChildById(74 - var3).getComponentData().C12_f195.a((byte)16, (byte)-1);
            }
         }

         if (this.C9_f125 == 1) {
            this.C9_f122.currentDialog.getChildById(64).getComponentData().C12_f179 = "Lấy ra";
            return;
         }

         if (this.C9_f125 == 2) {
            this.C9_f122.currentDialog.getChildById(64).getComponentData().C12_f179 = "Phóng sinh";
         }
      }

   }

   public final void C() {
      if (this.C9_f131 == 0 && this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.aX();
      } else if (this.C9_f131 == 0 && this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.aX();
      }

      int[] var1;
      if (this.C9_f131 == 0) {
         if (this.C9_f121.g(196640)) {
            if (this.C9_f125 == 1) {
               if (this.C9_f123.C53_f778 >= 6) {
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Ba lô Sủng vật đã đủ", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 1;
               } else {
                  if (this.C9_f123.C53_f792.size() <= 0) {
                     return;
                  }

                  this.C9_f123.r(this.C9_f136);
                  if (this.C9_f123.C53_f792.size() <= 0) {
                     this.C9_f121.a((byte)16);
                     this.C9_f122.removeDialog("/data/ui/petstate.ui");
                  } else {
                     this.aX();
                  }
               }
            } else if (this.C9_f125 == 2) {
               if (this.C9_f123.C53_f792.size() <= 0) {
                  return;
               }

               var1 = (int[])this.C9_f123.C53_f792.elementAt(this.C9_f136);
               if (C67.a((byte)0, (short)var1[0], (byte)22) == 2) {
                  this.C9_f131 = 2;
                  this.H();
                  this.a("Thần thú không thể phóng sinh", "Nhấn nút 5 tiếp tục");
               } else {
                  this.C9_f131 = 1;
                  this.C9_f122.showDialog("/data/ui/msgconfirm.ui", 257, this);
                  this.b("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
               }
            }
         } else if (this.C9_f121.g(786432)) {
            this.C9_f121.a((byte)16);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }
      } else if (this.C9_f131 > 0) {
         if (this.C9_f121.g(196640) && this.C9_f125 == 1 || this.C9_f121.g(131072) && this.C9_f125 == 2 && this.C9_f131 == 1 || this.C9_f121.g(196640) && this.C9_f125 == 2 && this.C9_f131 == 2) {
            if (this.C9_f125 == 1) {
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.C9_f131 = 0;
            } else if (this.C9_f125 == 2) {
               if (this.C9_f131 == 1) {
                  this.C9_f122.removeDialog("/data/ui/msgconfirm.ui");
                  var1 = (int[])this.C9_f123.C53_f792.elementAt(this.C9_f136);
                  this.C9_f123.m(var1[2]);
                  this.C9_f123.q(this.C9_f136);
                  this.aX();
               } else if (this.C9_f131 == 2) {
                  this.I();
               }

               this.C9_f131 = 0;
            }
         } else if (this.C9_f121.g(786432)) {
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
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Ngân hàng Sủng vật";
      this.C9_f122.currentDialog.getChildById(6).getComponentData().C12_f179 = "Gởi lại";
      this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Lấy ra";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "Phóng sinh";
   }

   public final void E() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.g(196640)) {
         switch(this.C9_f125) {
         case 0:
            this.C9_f126 = 0;
            this.C9_f121.a((byte)7);
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            return;
         case 1:
            this.C9_f121.a((byte)15);
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            return;
         case 2:
            this.C9_f121.a((byte)15);
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            return;
         case 3:
            C7.C7_f66 = true;
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            this.C9_f121.a((byte)0);
         default:
         }
      } else {
         if (this.C9_f121.g(262144)) {
            C7.C7_f66 = true;
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            this.C9_f121.a((byte)0);
         }

      }
   }

   public final void F() {
      this.aU();
      this.C9_f122.showDialog("/data/ui/shop.ui", 257, this);
      this.C9_f125 = 0;
   }

   public final void G() {
      this.C9_f121.q();
      if (!C44.a((int)this.C9_f125, (int)0) && !this.j() && this.C9_f131 == 0 && this.C9_f121.g(4100) && this.aY()) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (!C44.a((int)this.C9_f125, (int)0) && !this.j() && this.C9_f131 == 0 && this.C9_f121.g(8448) && this.aY()) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.aY() && !this.j() && C44.s() && this.C9_f121.g(196640)) {
         if (C44.p() && !C44.a((int)this.C9_f125, (int)0)) {
            return;
         }

         switch(this.C9_f125) {
         case 0:
            this.C9_f121.r();
            this.C9_f121.a((byte)2);
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            break;
         case 1:
            this.C9_f121.a((byte)3);
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            break;
         case 2:
            int var1;
            if (this.C9_f131 == 0) {
               if (C53.p().B() == -1) {
                  this.C9_f131 = 6;
                  this.H();
                  this.a("Toàn bộ trạng thái đã đầy, không cần khôi phục", "Nhấn nút 5 tiếp tục");
               } else if (!C44.C44_f711) {
                  this.C9_f131 = 3;

                  for(var1 = 0; var1 < this.C9_f123.C53_f778; ++var1) {
                     this.C9_f123.C53_f777[var1].J();
                  }

                  this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
               } else {
                  this.C9_f122.showDialog("/data/ui/msgRecover.ui", 257, this);
                  this.C9_f122.currentDialog.getChildById(4).getComponentData().C12_f179 = "Có khôi phục trạng thái ba lô sủng vật không?";
                  this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Cần tiền tài: ";
                  this.C9_f122.currentDialog.getChildById(6).getComponentData().C12_f179 = "" + C53.p().B();
                  this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "" + C53.p().F();
                  this.C9_f122.removeDialog("/data/ui/shop.ui");
                  this.C9_f131 = 1;
               }
            } else if (this.C9_f131 == 1) {
               var1 = C53.p().B();
               if (!C53.p().u(var1)) {
                  this.C9_f131 = 2;
                  this.H();
                  this.a("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
               } else {
                  this.C9_f131 = 3;
                  C53.p().s(-var1);

                  for(var1 = 0; var1 < this.C9_f123.C53_f778; ++var1) {
                     this.C9_f123.C53_f777[var1].J();
                  }

                  this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
               }

               this.C9_f122.removeDialog("/data/ui/msgRecover.ui");
            } else {
               if (this.C9_f131 == 2 && C44.C44_f711) {
                  this.C9_f121.a((byte)102);
               }

               this.C9_f131 = 0;
               this.I();
            }
            break;
         case 3:
            C7.C7_f66 = true;
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            this.C9_f121.a((byte)0);
         }
      } else if (!this.j() && this.C9_f121.g(262144) && C44.t() && this.aY()) {
         if (this.C9_f131 == 1) {
            this.C9_f122.showDialog("/data/ui/shop.ui", 257, this);
            this.C9_f122.removeDialog("/data/ui/msgRecover.ui");
            this.C9_f125 = 0;
            this.C9_f131 = 0;
         } else if (this.C9_f131 == 0) {
            C7.C7_f66 = true;
            this.C9_f122.removeDialog("/data/ui/shop.ui");
            this.C9_f121.a((byte)0);
         }
      }

      if (this.C9_f131 == 3 && this.aA()) {
         this.C9_f131 = 4;
         this.C9_f122.showDialog("/data/ui/shop.ui", 257, this);
         this.K();
         this.a("Đang lưu...");
         this.M();
      } else if (this.C9_f131 == 4 && ((C25)this.C9_f121).I()) {
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
      short[][] var10001 = C67.C67_f923[var1];
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = var10001.length;
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Mua";
      if (this.C9_f121 instanceof C25) {
         this.C9_f122.currentDialog.getChildById(57).setVisible(true);
         this.C9_f122.currentDialog.getChildById(58).setVisible(true);
         this.C9_f122.currentDialog.getChildById(57).getComponentData().C12_f179 = "Mua sắm";
         this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "Quay lại";
         this.C9_f122.currentDialog.getChildById(39).setVisible(false);
         this.C9_f122.currentDialog.getChildById(40).setVisible(false);
      } else if (this.C9_f121 instanceof C29) {
         this.C9_f122.currentDialog.getChildById(57).setVisible(false);
         this.C9_f122.currentDialog.getChildById(58).setVisible(false);
         this.C9_f122.currentDialog.getChildById(39).setVisible(true);
         this.C9_f122.currentDialog.getChildById(40).setVisible(true);
         this.C9_f122.currentDialog.getChildById(39).getComponentData().C12_f179 = "Mua sắm";
         this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = "Quay lại";
      }

      this.b(var1, var2);
   }

   private void b(int var1, byte var2) {
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;

      for(int var3 = 0; var3 < 5; ++var3) {
         if (this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().C12_f195.a(258, false, (byte)-1);
         }

         this.C9_f122.currentDialog.getChildById(var3 + 51).getComponentData().C12_f195.a((int)C67.C67_f923[var1][this.C9_f135 + var3][1]);
         this.C9_f122.currentDialog.getChildById(14 + var3 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[var1][this.C9_f135 + var3][0]);
         if (this.C9_f121 instanceof C25) {
            if (this.C9_f141 != 1 && this.C9_f141 != 3) {
               if (this.C9_f141 == 2) {
                  if (C67.C67_f923[var1][this.C9_f135 + var3][4] == 0) {
                     this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().C12_f179 = "" + C67.C67_f923[var1][this.C9_f135 + var3][3] * 3 / 2;
                  } else {
                     this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().C12_f179 = "" + C67.C67_f923[var1][this.C9_f135 + var3][3];
                  }
               }
            } else {
               this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().C12_f179 = "" + C67.C67_f923[var1][this.C9_f135 + var3][3];
            }
         } else if (var2 == 0 && var1 == 4 && this.C9_f135 + var3 == 0) {
            this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().C12_f179 = "" + C67.C67_f923[var1][this.C9_f135 + var3][3];
         } else {
            this.C9_f122.currentDialog.getChildById(15 + var3 * 5).getComponentData().C12_f179 = "" + (C67.C67_f923[var1][this.C9_f135 + var3][3] << 1);
         }

         if (C67.C67_f923[var1][this.C9_f135 + var3][4] == 0) {
            this.C9_f122.currentDialog.getChildById(var3 + 45).getComponentData().C12_f195.a((int)84);
         } else if (C67.C67_f923[var1][this.C9_f135 + var3][4] == 1) {
            this.C9_f122.currentDialog.getChildById(var3 + 45).getComponentData().C12_f195.a((int)83);
         } else if (C67.C67_f923[var1][this.C9_f135 + var3][4] == 2) {
            this.C9_f122.currentDialog.getChildById(var3 + 45).getComponentData().C12_f195.a((int)74);
         }
      }

      this.C9_f122.currentDialog.getChildById(56).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[var1][this.C9_f136][2]);
      this.C9_f122.currentDialog.getChildById(43).getComponentData().C12_f179 = "" + this.C9_f123.G();
      this.C9_f122.currentDialog.getChildById(44).getComponentData().C12_f179 = "" + this.C9_f123.F();
      this.C9_f122.currentDialog.getChildById(38).setOffsetY(102 + this.C9_f136 * 84 / C67.C67_f923[var1].length, this.C9_f122.currentDialog.getRootComponent());
   }

   public final void a(byte var1, byte var2) {
      this.C9_f121.q();
      if (!C44.a((int)this.C9_f125, (int)0) && this.C9_f131 <= 1 && this.C9_f121.g(4100) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(0);
         if (this.C9_f131 == 0) {
            this.b((int)var1, (byte)var2);
         }
      } else if (!C44.a((int)this.C9_f125, (int)0) && this.C9_f131 <= 1 && this.C9_f121.g(8448) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(1);
         if (this.C9_f131 == 0) {
            this.b((int)var1, (byte)var2);
         }
      } else if (this.C9_f131 == 1 && this.C9_f121.g(16400) && this.C9_f126 > 0 && !this.j()) {
         --this.C9_f126;
         if (this.C9_f126 <= 0) {
            this.C9_f126 = 99 - this.C9_f123.a(this.C9_f136, var2);
         }

         this.a(this.C9_f126, this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3], C67.C67_f923[var1][this.C9_f136][4], var1);
      } else if (this.C9_f131 == 1 && this.C9_f121.g(32832) && !this.j()) {
         ++this.C9_f126;
         if (this.C9_f126 > 99 - this.C9_f123.a(this.C9_f136, var2)) {
            this.C9_f126 = 1;
         }

         this.a(this.C9_f126, this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3], C67.C67_f923[var1][this.C9_f136][4], var1);
      } else if (C44.s() && this.C9_f121.g(196640) && !this.j()) {
         if (C44.p() && !C44.a((int)this.C9_f125, (int)0)) {
            return;
         }

         if (C67.C67_f923[var1][this.C9_f136][4] == 2) {
            if (this.C9_f131 == 0) {
               if (C44.C44_f711) {
                  if (!this.C9_f123.a((int)this.C9_f136, 1, (byte)0)) {
                     this.C9_f131 = 3;
                     this.H();
                     this.a("Đạo cụ đã đủ", "Nhấn nút 5 tiếp tục");
                  } else {
                     this.C9_f121.a((byte)101);
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
            if (this.C9_f123.a(this.C9_f136, this.C9_f126, var2)) {
               if (this.C9_f131 == 0) {
                  this.C9_f127 = 0;
                  this.b(var1, var2);
               } else if (this.C9_f131 > 0) {
                  if (C44.C44_f711) {
                     if (this.C9_f131 == 4) {
                        this.C9_f121.a((byte)104);
                     } else if (this.C9_f131 == 3) {
                        this.C9_f121.a((byte)102);
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
               this.C9_f121.r();
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.b((int)var1, (byte)var2);
            }
         } else if (this.C9_f123.a(this.C9_f136, this.C9_f126, var2)) {
            if (this.C9_f131 == 0) {
               this.C9_f131 = 1;
               this.C9_f122.showDialog("/data/ui/msgyn.ui", 257, this);
               this.C9_f126 = 1;
               this.C9_f127 = 0;
               this.a(this.C9_f126, this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3], C67.C67_f923[var1][this.C9_f136][4], var1);
            } else if (this.C9_f131 == 1) {
               this.b(var1, var2);
            } else if (this.C9_f131 == 2) {
               C25.B().C25_f348.G();
               this.C9_f131 = 0;
               this.C9_f126 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.b((int)var1, (byte)var2);
            } else {
               if (C44.C44_f711) {
                  if (this.C9_f131 == 4) {
                     this.C9_f122.removeDialog("/data/ui/msgyn.ui");
                     this.C9_f121.a((byte)104);
                  } else if (this.C9_f131 == 3) {
                     this.C9_f122.removeDialog("/data/ui/msgyn.ui");
                     this.C9_f121.a((byte)102);
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
      } else if (this.C9_f121.g(262144) && !this.j() && C44.t()) {
         if (this.C9_f131 == 0) {
            if (this.C9_f121 instanceof C25) {
               if (this.C9_f141 == 1) {
                  this.C9_f121.a((byte)1);
               } else if (this.C9_f141 == 2) {
                  this.C9_f121.a((byte)14);
               } else if (this.C9_f141 == 3) {
                  this.C9_f121.a((byte)27);
               }

               this.C9_f122.removeDialog("/data/ui/shopbuy.ui");
            } else {
               this.C9_f122.removeDialog("/data/ui/shopbuy.ui");
               this.C9_f121.a((byte)20);
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
      if ((!(this.C9_f121 instanceof C25) || this.C9_f141 != 1 && this.C9_f141 != 3 || !this.C9_f123.b((int)this.C9_f136, (int)(this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3]), (int)var1)) && (this.C9_f141 != 2 || (C67.C67_f923[var1][this.C9_f136][4] != 0 || !this.C9_f123.b((int)this.C9_f136, (int)(this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3] * 3 / 2), (int)var1)) && (C67.C67_f923[var1][this.C9_f136][4] == 0 || !this.C9_f123.b((int)this.C9_f136, (int)(this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3]), (int)var1)))) {
         if (this.C9_f121 instanceof C29 && this.C9_f123.b((int)this.C9_f136, (int)(this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3] << 1), (int)var1)) {
            if (this.C9_f127 == 0) {
               this.C9_f123.c(this.C9_f136, this.C9_f126, var2);
               if (C67.C67_f923[var1][this.C9_f136][4] == 0) {
                  this.C9_f123.s(-this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3] << 1);
               } else {
                  this.C9_f123.v(-this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3] << 1);
               }

               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               if (var1 == 3 && this.C9_f136 == 17) {
                  this.a("Đã thành công mua sắm #2" + C44.c((int)C67.C67_f923[var1][this.C9_f136][0]) + " * " + 5 * this.C9_f126, "Nhấn nút 5 tiếp tục");
               } else {
                  this.a("Đã thành công mua sắm #2" + C44.c((int)C67.C67_f923[var1][this.C9_f136][0]) + " * " + this.C9_f126, "Nhấn nút 5 tiếp tục");
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
               if (C67.C67_f923[var1][this.C9_f136][4] == 0) {
                  this.C9_f131 = 3;
                  this.a("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
               } else {
                  this.C9_f131 = 4;
                  this.a("Số lượng Huy hiệu chưa đủ", "Nhấn nút 5 tiếp tục");
               }

               int var3;
               if (this.C9_f121 instanceof C25) {
                  label133: {
                     if (this.C9_f141 != 1 && this.C9_f141 != 3) {
                        if (this.C9_f141 != 2) {
                           break label133;
                        }

                        if (C67.C67_f923[var1][this.C9_f136][4] == 0) {
                           var3 = this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3] * 3 / 2;
                        } else {
                           var3 = this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3];
                        }
                     } else {
                        var3 = this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3];
                     }

                     this.a(new int[]{var1, var2, this.C9_f136, C67.C67_f923[var1][this.C9_f136][4], var3, this.C9_f126});
                  }
               } else if (this.C9_f121 instanceof C29) {
                  var3 = this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3] << 1;
                  this.a(new int[]{var1, var2, this.C9_f136, C67.C67_f923[var1][this.C9_f136][4], var3, this.C9_f126});
               }
            } else {
               this.C9_f131 = 0;
            }

            this.C9_f122.removeDialog("/data/ui/msgyn.ui");
         }
      } else {
         if (this.C9_f127 == 0) {
            this.C9_f123.c(this.C9_f136, this.C9_f126, var2);
            if (C67.C67_f923[var1][this.C9_f136][4] == 0) {
               if (this.C9_f141 != 1 && this.C9_f141 != 3) {
                  if (this.C9_f141 == 2) {
                     this.C9_f123.s(-this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3] * 3 / 2);
                  }
               } else {
                  this.C9_f123.s(-this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3]);
               }
            } else if (this.C9_f141 != 1 && this.C9_f141 != 3) {
               if (this.C9_f141 == 2) {
                  this.C9_f123.v(-this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3]);
               }
            } else {
               this.C9_f123.v(-this.C9_f126 * C67.C67_f923[var1][this.C9_f136][3]);
            }

            this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
            if (var1 == 3 && this.C9_f136 == 17) {
               this.a("Đã thành công mua sắm #2" + C44.c((int)C67.C67_f923[var1][this.C9_f136][0]) + " * " + 5 * this.C9_f126, "Nhấn nút 5 tiếp tục");
            } else {
               this.a("Đã thành công mua sắm #2" + C44.c((int)C67.C67_f923[var1][this.C9_f136][0]) + " * " + this.C9_f126, "Nhấn nút 5 tiếp tục");
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
         this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "" + var1 * 5;
      } else {
         this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "" + var1;
      }

      if (this.C9_f121 instanceof C25) {
         if (this.C9_f141 != 1 && this.C9_f141 != 3) {
            if (this.C9_f141 == 2) {
               if (var3 == 0) {
                  this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "" + var2 * 3 / 2;
               } else {
                  this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "" + var2;
               }
            }
         } else {
            this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "" + var2;
         }
      } else {
         this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "" + (var2 << 1);
      }

      if (var3 == 0) {
         this.C9_f122.currentDialog.getChildById(12).getComponentData().C12_f195.a((int)84);
      } else {
         if (var3 == 1) {
            this.C9_f122.currentDialog.getChildById(12).getComponentData().C12_f195.a((int)83);
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
      this.C9_f122.currentDialog.getChildById(6).getComponentData().C12_f179 = var2;
      this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = var1;
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
      this.C9_f122.currentDialog.getChildById(2).getComponentData().C12_f179 = var1;
   }

   public final void M() {
      this.C9_f122.currentDialog.getChildById(3).setVisible(false);
      this.C9_f122.currentDialog.getChildById(4).setVisible(false);
   }

   public final void N() {
      if (this.C9_f131 == 0) {
         if (this.C9_f121.g(196640)) {
            this.C9_f131 = 1;
            this.a("Đang lưu...");
            this.M();
            return;
         }

         if (this.C9_f121.g(262144)) {
            if (C44.C44_f711) {
               this.C9_f125 = 5;
            } else {
               this.C9_f125 = 4;
            }

            this.C9_f121.a((byte)6);
            this.C9_f122.removeDialog("/data/ui/msgtip.ui");
            this.C9_f131 = 0;
            return;
         }
      } else if (this.C9_f131 == 1) {
         if (((C25)this.C9_f121).I()) {
            this.a("Lưu thành công");
            this.C9_f131 = 2;
            return;
         }
      } else if (this.C9_f131 == 2) {
         this.C9_f122.removeDialog("/data/ui/msgtip.ui");
         this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
         this.C9_f121.a((byte)0);
         this.C9_f131 = 0;
      }

   }

   private void b(String var1, String var2) {
      this.C9_f122.currentDialog.getChildById(2).getComponentData().C12_f179 = var2;
      this.C9_f122.currentDialog.getChildById(4).getComponentData().C12_f179 = var1;
   }

   public final void O() {
      this.C9_f122.showDialog("/data/ui/shopbuy.ui", 257, this);
      this.C9_f125 = 0;
      this.C9_f131 = 0;
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Bán ra";
      this.C9_f122.currentDialog.getChildById(39).getComponentData().C12_f179 = "";
      this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = "";
      this.C9_f122.currentDialog.getChildById(57).getComponentData().C12_f179 = "Bán đi";
      this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "Quay lại";
      this.C9_f123.y();
      this.aZ();
   }

   private void aZ() {
      if (this.C9_f123.C53_f796.size() > 5) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(0);
      }

      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = this.C9_f123.C53_f796.size();
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;
      if (this.C9_f136 >= this.C9_f123.C53_f796.size()) {
         this.C9_f136 = this.C9_f123.C53_f796.size() - 1;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f136;
      }

      if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
         --this.C9_f135;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579 = this.C9_f135;
      }

      for(int var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.C53_f796.size()) {
            int var2 = ((int[])this.C9_f123.C53_f796.elementAt(this.C9_f135 + var1))[0];
            if (this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().C12_f195.a(258, false, (byte)-1);
            }

            this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().C12_f195.a((int)C67.C67_f923[4][var2][1]);
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[4][var2][0]);
            this.C9_f122.currentDialog.getChildById(15 + var1 * 5).getComponentData().C12_f179 = "" + C67.C67_f923[4][var2][3] / 2;
            if (C67.C67_f923[4][var2][4] == 0) {
               this.C9_f122.currentDialog.getChildById(var1 + 45).getComponentData().C12_f195.a((int)84);
            } else if (C67.C67_f923[4][var2][4] == 1) {
               this.C9_f122.currentDialog.getChildById(var1 + 45).getComponentData().C12_f195.a((int)83);
            } else if (C67.C67_f923[4][var2][4] == 2) {
               this.C9_f122.currentDialog.getChildById(var1 + 45).getComponentData().C12_f195.a((int)74);
            }
         } else {
            if (this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().C12_f195 != null) {
               this.C9_f122.currentDialog.getChildById(var1 + 51).getComponentData().C12_f195.c();
            }

            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = "";
            this.C9_f122.currentDialog.getChildById(15 + var1 * 5).getComponentData().C12_f179 = "";
            this.C9_f122.currentDialog.getChildById(var1 + 45).getComponentData().C12_f195.a((int)86);
         }
      }

      if (this.C9_f123.C53_f796.size() > 0) {
         this.C9_f122.currentDialog.getChildById(56).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[4][((int[])this.C9_f123.C53_f796.elementAt(this.C9_f136))[0]][2]);
      } else {
         this.C9_f122.currentDialog.getChildById(56).getComponentData().C12_f179 = "";
      }

      if (this.C9_f123.C53_f796.size() > 0) {
         this.C9_f122.currentDialog.getChildById(43).getComponentData().C12_f179 = "" + this.C9_f123.G();
         this.C9_f122.currentDialog.getChildById(44).getComponentData().C12_f179 = "" + this.C9_f123.F();
         this.C9_f122.currentDialog.getChildById(38).setOffsetY(102 + this.C9_f136 * 84 / this.C9_f123.C53_f796.size(), this.C9_f122.currentDialog.getRootComponent());
      }
   }

   public final void P() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else {
         int[] var1;
         if (this.C9_f131 == 1 && this.C9_f121.g(16400) && this.C9_f126 > 0) {
            var1 = (int[])this.C9_f123.C53_f796.elementAt(this.C9_f136);
            --this.C9_f126;
            if (this.C9_f126 <= 0) {
               this.C9_f126 = this.C9_f123.a((int)var1[0], (byte)0);
            }

            this.a(this.C9_f126, this.C9_f126 * C67.C67_f923[4][var1[0]][3] / 2, C67.C67_f923[4][var1[0]][4], 4);
         } else if (this.C9_f131 == 1 && this.C9_f121.g(32832)) {
            var1 = (int[])this.C9_f123.C53_f796.elementAt(this.C9_f136);
            ++this.C9_f126;
            if (this.C9_f126 > this.C9_f123.a((int)var1[0], (byte)0)) {
               this.C9_f126 = 1;
            }

            this.a(this.C9_f126, this.C9_f126 * C67.C67_f923[4][var1[0]][3] / 2, C67.C67_f923[4][var1[0]][4], 4);
         } else {
            if (this.C9_f121.g(196640) && this.C9_f123.C53_f796.size() > 0) {
               var1 = (int[])this.C9_f123.C53_f796.elementAt(this.C9_f136);
               if (this.C9_f131 == 0) {
                  this.C9_f131 = 1;
                  this.C9_f122.showDialog("/data/ui/msgyn.ui", 257, this);
                  this.C9_f126 = 1;
                  this.C9_f127 = 0;
                  this.a(this.C9_f126, this.C9_f126 * C67.C67_f923[4][var1[0]][3] / 2, C67.C67_f923[4][var1[0]][4], 4);
                  return;
               }

               if (this.C9_f127 != 0) {
                  this.C9_f122.removeDialog("/data/ui/msgyn.ui");
                  this.C9_f131 = 0;
                  return;
               }

               this.C9_f123.d(var1[0], this.C9_f126, (byte)0);
               this.C9_f123.s(this.C9_f126 * C67.C67_f923[4][var1[0]][3] / 2);
            } else {
               if (!this.C9_f121.g(262144)) {
                  return;
               }

               if (this.C9_f131 == 0) {
                  this.C9_f121.a((byte)1);
                  this.C9_f122.removeDialog("/data/ui/shopbuy.ui");
                  this.C9_f125 = 1;
                  ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f125;
                  return;
               }
            }

            this.C9_f131 = 0;
            this.C9_f122.removeDialog("/data/ui/msgyn.ui");
            this.C9_f123.y();
            this.aZ();
         }
      }
   }

   public final void Q() {
      this.C9_f122.showDialog("/data/ui/record.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
      this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = "" + (this.C9_f123.C53_f778 + this.C9_f123.C53_f792.size());
      this.C9_f122.currentDialog.getChildById(17).getComponentData().C12_f179 = "" + this.C9_f123.C53_f783;
      this.C9_f122.currentDialog.getChildById(20).getComponentData().C12_f179 = "" + this.C9_f123.C53_f785;
      this.C9_f122.currentDialog.getChildById(26).getComponentData().C12_f179 = "" + this.C9_f123.C53_f784;
      int var1 = 0;

      for(byte var2 = 0; var2 < this.C9_f123.C53_f779.length; ++var2) {
         if (this.C9_f123.b(var2, (byte)0) == 2) {
            ++var1;
         }
      }

      this.C9_f122.currentDialog.getChildById(29).getComponentData().C12_f179 = "" + var1;
      long var4 = C55.B().C55_f826 + C55.B().C55_f827 - C55.B().C55_f828;
      DialogData var10000 = this.C9_f122.currentDialog.getChildById(31).getComponentData();
      C25.B();
      var10000.C12_f179 = C25.a(var4)[1];
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).additionalChildComponent.C38_f580 = this.C9_f126;
      this.C9_f125 = 0;
      this.C9_f131 = 0;
      this.C9_f132 = true;
   }

   public final void R() {
      if (this.C9_f121.g(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.C9_f132 = true;
      } else if (this.C9_f121.g(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.C9_f132 = true;
      } else if (this.C9_f121.g(196640)) {
         if (this.C9_f131 == 0) {
            switch(this.C9_f126) {
            case 0:
               if (C53.p().l(5)) {
                  this.C9_f121.a((byte)11);
               } else {
                  this.H();
                  this.a("Không đạt được sủng vật sách tranh đạo cụ", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 1;
               }
               break;
            case 1:
               this.C9_f121.a((byte)12);
            }
         } else {
            this.C9_f131 = 0;
            this.I();
         }
      } else if (this.C9_f121.g(262144) && this.C9_f131 == 0) {
         if (C44.C44_f711) {
            this.C9_f125 = 3;
         } else {
            this.C9_f125 = 2;
         }

         this.C9_f121.a((byte)6);
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
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
      this.bb();
      this.C9_f132 = true;
   }

   private void ba() {
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579 = 0;
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = 0;
   }

   private void bb() {
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = this.C9_f123.C53_f803[this.C9_f125];
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;
      short var1 = C67.C67_f923[0][this.C9_f123.C53_f802[this.C9_f125] + this.C9_f136][17];
      if (this.C9_f123.a((byte)this.C9_f125, this.C9_f136 + this.C9_f123.C53_f802[this.C9_f125]) > 0) {
         this.C9_f122.currentDialog.getChildById(21).setVisible(true);
         if (this.C9_f122.currentDialog.getChildById(21).getComponentData().C12_f195 != null) {
            this.C9_f122.currentDialog.getChildById(21).getComponentData().C12_f195.c();
         } else {
            this.C9_f122.currentDialog.getChildById(21).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(21).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(21).getComponentData().C12_f195.C17_f222 = 3;
         }

         this.C9_f122.currentDialog.getChildById(21).getComponentData().C12_f195.a(var1, false, (byte)-1);
         this.C9_f122.currentDialog.getChildById(21).getComponentData().C12_f195.a((byte)1);
      } else {
         this.C9_f122.currentDialog.getChildById(21).setVisible(false);
      }

      int var3 = 0;

      int var2;
      for(var2 = 0; var2 < this.C9_f123.C53_f803[this.C9_f125]; ++var2) {
         if (this.C9_f123.a((byte)this.C9_f125, this.C9_f123.C53_f802[this.C9_f125] + var2) == 2) {
            ++var3;
         }
      }

      for(var2 = 0; var2 < 5; ++var2) {
         if (this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().C12_f195.a((int)102);
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().C12_f195.a(257, false, (byte)-1);
         }

         if (this.C9_f123.a((byte)this.C9_f125, var2 + this.C9_f135 + this.C9_f123.C53_f802[this.C9_f125]) == 2) {
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().C12_f195.a((int)101);
         } else {
            this.C9_f122.currentDialog.getChildById(var2 + 44).getComponentData().C12_f195.a((int)102);
         }

         this.C9_f122.currentDialog.getChildById(24 + (var2 << 2) + 3).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[0][this.C9_f123.C53_f802[this.C9_f125] + var2 + this.C9_f135][0]);
      }

      this.C9_f122.currentDialog.getChildById(20).getComponentData().C12_f179 = C44.c(365 + this.C9_f125) + var3 + "/" + this.C9_f123.C53_f803[this.C9_f125];
      this.C9_f122.currentDialog.getChildById(23).setOffsetY(99 + (this.C9_f136 << 6) / this.C9_f123.C53_f803[this.C9_f125], this.C9_f122.currentDialog.getRootComponent());
   }

   public final void T() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bb();
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bb();
      } else if (this.C9_f121.g(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.ba();
         this.bb();
      } else if (this.C9_f121.g(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.ba();
         this.bb();
      } else if (!this.C9_f121.g(196640) && this.C9_f121.g(786432)) {
         if (this.C9_f121.C44_f699 == 8) {
            this.C9_f121.a((byte)8);
         } else {
            this.C9_f126 = 0;
            this.C9_f121.a((byte)9);
         }

         this.C9_f122.removeDialog("/data/ui/petmap.ui");
      }

      this.C9_f132 = true;
   }

   public final void U() {
      this.C9_f122.showDialog("/data/ui/task.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/gamemenu.ui");
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).additionalChildComponent.C38_f580 = this.C9_f125;
      this.C9_f126 = 0;
      this.C9_f127 = 0;
      this.bc();
      this.bd();
   }

   private void bc() {
      int var1;
      int var2;
      switch(this.C9_f125) {
      case 0:
         if (C7.C7_f106 >= C7.C7_f103.length / 2 - 1) {
            int var10001 = C7.C7_f103.length;
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = var10001 / 2;
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = C7.C7_f103.length / 2 - 1;
         } else {
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = C7.C7_f106 + 1;
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = C7.C7_f106;
         }

         this.C9_f122.currentDialog.getChildById(36).getComponentData().C12_f179 = "";
         this.C9_f136 = C7.C7_f106;
         this.C9_f135 = C7.C7_f106 - 4;
         if (this.C9_f136 <= 0) {
            this.C9_f136 = 0;
         }

         if (this.C9_f135 <= 0) {
            this.C9_f135 = 0;
         }

         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579 = this.C9_f135;
         this.C9_f122.currentDialog.getChildById(37).getComponentData().C12_f179 = "Đầu mối chính hoàn thành độ: ";
         if (C7.C7_f106 >= C7.C7_f103.length / 2) {
            this.C9_f122.currentDialog.getChildById(38).getComponentData().C12_f179 = C7.C7_f103[C7.C7_f103.length - 1];
         } else {
            this.C9_f122.currentDialog.getChildById(38).getComponentData().C12_f179 = C7.C7_f103[C7.C7_f103.length / 2 + C7.C7_f106];
         }

         var1 = C7.C7_f106 * 1000 / (C7.C7_f103.length / 2);
         if (!C44.C44_f711) {
            if ((var2 = var1 % 10) == 0) {
               var2 = 1;
            }

            this.C9_f122.currentDialog.getChildById(38).getComponentData().C12_f179 = var1 / 50 + "." + var2 + "%";
         } else {
            this.C9_f122.currentDialog.getChildById(38).getComponentData().C12_f179 = var1 / 10 + "." + var1 % 10 + "%";
         }

         if (C7.C7_f106 > 4) {
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
         } else {
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(0);
         }

         this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f187 = 11290624;
         break;
      case 1:
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = C7.C7_f107;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = 0;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579 = 0;
         this.C9_f122.currentDialog.getChildById(36).getComponentData().C12_f179 = "";
         this.C9_f122.currentDialog.getChildById(37).getComponentData().C12_f179 = "Chi nhánh hoàn thành độ: ";
         var1 = 0;

         for(var2 = 0; var2 < C7.C7_f105.length; ++var2) {
            if (C7.C7_f105[var2][1] == 3) {
               ++var1;
            }
         }

         var2 = var1 * 1000 / (C7.C7_f102.length / 2);
         this.C9_f122.currentDialog.getChildById(38).getComponentData().C12_f179 = var2 / 10 + "." + var2 % 10 + "%";
         if (C7.C7_f107 > 5) {
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
         } else {
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(0);
         }

         this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f187 = 11290624;
      }

      this.bd();
   }

   private void bd() {
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;

      for(int var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f125 == 0) {
            if (C7.C7_f106 > 0) {
               if (this.C9_f135 + var1 < C7.C7_f106) {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().C12_f179 = "" + (var1 + this.C9_f135 + 1);
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3).getComponentData().C12_f179 = C7.C7_f103[this.C9_f135 + var1];
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().C12_f179 = "Hoàn thành";
               } else if (this.C9_f135 + var1 == C7.C7_f106 && this.C9_f135 + var1 <= C7.C7_f103.length / 2 - 1) {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().C12_f179 = "" + (var1 + this.C9_f135 + 1);
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3).getComponentData().C12_f179 = C7.C7_f103[this.C9_f135 + var1];
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().C12_f179 = "";
               } else {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().C12_f179 = "";
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3).getComponentData().C12_f179 = "";
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().C12_f179 = "";
                  this.C9_f122.currentDialog.getChildById(36).getComponentData().C12_f179 = "";
               }
            } else {
               this.C9_f122.currentDialog.getChildById(12).getComponentData().C12_f179 = "1";
               this.C9_f122.currentDialog.getChildById(13).getComponentData().C12_f179 = C7.C7_f103[0];
               this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = "";
            }
         } else if (this.C9_f125 == 1) {
            if (this.C9_f135 + var1 < C7.C7_f107) {
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().C12_f179 = "" + (var1 + this.C9_f135 + 1);
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3).getComponentData().C12_f179 = C7.C7_f102[C7.C7_f105[this.C9_f135 + var1][0]];
               if (C7.C7_f105[this.C9_f135 + var1][1] == 3) {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().C12_f179 = "Hoàn thành";
               } else {
                  this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().C12_f179 = "";
               }
            } else {
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().C12_f179 = "";
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 3).getComponentData().C12_f179 = "";
               this.C9_f122.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().C12_f179 = "";
            }
         }
      }

      switch(this.C9_f125) {
      case 0:
         this.C9_f122.currentDialog.getChildById(36).getComponentData().C12_f179 = C7.C7_f103[C7.C7_f103.length / 2 + this.C9_f136];
         break;
      case 1:
         if (C7.C7_f107 > 0) {
            this.C9_f122.currentDialog.getChildById(36).getComponentData().C12_f179 = C7.C7_f102[C7.C7_f102.length / 2 + C7.C7_f105[this.C9_f136][0]];
         }
      }

      if (((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 > 0) {
         this.C9_f122.currentDialog.getChildById(40).setOffsetY(104 + (this.C9_f136 << 6) / ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574, this.C9_f122.currentDialog.getRootComponent());
      }

   }

   public final void V() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bd();
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bd();
      } else if (this.C9_f121.g(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.bc();
      } else if (this.C9_f121.g(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.bc();
      } else if (this.C9_f121.g(983072)) {
         if (C44.C44_f711) {
            this.C9_f125 = 4;
         } else {
            this.C9_f125 = 3;
         }

         this.C9_f122.removeDialog("/data/ui/task.ui");
         if (this.C9_f121.C44_f699 == 0) {
            this.C9_f125 = 0;
            this.C9_f121.a((byte)0);
         } else if (this.C9_f121.C44_f699 == 33) {
            this.C9_f121.a((byte)33);
         } else {
            this.C9_f121.a((byte)6);
         }
      } else {
         if (this.C9_f121.g(10)) {
            this.C9_f122.removeDialog("/data/ui/task.ui");
            this.C9_f121.a((byte)0);
         }

      }
   }

   public final void W() {
      this.C9_f122.showDialog("/data/ui/badge.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/record.ui");
      this.C9_f125 = 0;
      this.C9_f131 = 0;

      for(int var1 = 0; var1 < 8; ++var1) {
         if (this.C9_f123.C53_f779[var1][0] != 0) {
            this.C9_f122.currentDialog.getChildById(var1 + 25).getComponentData().C12_f195.a(var1 + 46);
         }
      }

      this.be();
   }

   private void be() {
      this.C9_f122.currentDialog.getChildById(13).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[2][this.C9_f125][0]);
      this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[2][this.C9_f125][2 + this.C9_f123.b((byte)this.C9_f125, (byte)1)]);
      if (this.C9_f123.b((byte)this.C9_f125, (byte)0) == 0) {
         this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f179 = "Chưa đạt";
      } else {
         this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f179 = "Đã đạt được";
         this.C9_f123.b((byte)this.C9_f125, (byte)1);
         this.C9_f122.currentDialog.getChildById(33).getComponentData().C12_f179 = "";
      }
   }

   public final void X() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.be();
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.be();
      } else if (this.C9_f121.g(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.be();
      } else if (this.C9_f121.g(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.be();
      } else {
         if (this.C9_f121.g(786432)) {
            if (this.C9_f121.C44_f699 == 8) {
               this.C9_f121.a((byte)8);
            } else {
               this.C9_f126 = 1;
               this.C9_f121.a((byte)9);
            }

            this.C9_f122.removeDialog("/data/ui/badge.ui");
         }

      }
   }

   public final void a(int var1) {
      this.C9_f122.showDialog("/data/ui/smsTip.ui", 257, this);
      if (this.C9_f122.currentDialog.getChildById(6).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(6).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(6).getComponentData().C12_f195.C17_f222 = 2;
         this.C9_f122.currentDialog.getChildById(6).getComponentData().C12_f195.a((int)-1);
         this.C9_f122.currentDialog.getChildById(6).getComponentData().C12_f195.a(257, false, (byte)-1);
         this.C9_f122.currentDialog.getChildById(6).getComponentData().C12_f195.a(var1 + 46);
      }

      this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = C44.c(var1 + 187) + ":" + C44.c(var1 + 195);
      this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = C44.c((int)377);
   }

   public final void Y() {
      this.C9_f122.removeDialog("/data/ui/smsTip.ui");
   }

   public final void Z() {
      this.C9_f125 = 0;
      this.f(this.C9_f126);
   }

   private void f(int var1) {
      C41[] var2 = this.C9_f123.C53_f777;
      C9 var5 = this;
      this.C9_f122.showDialog("/data/ui/petstate.ui", 257, this);
      this.g(var1);
      this.C9_f131 = 0;
      int var4;
      if (this.C9_f121 instanceof C25) {
         for(var4 = 0; var4 < 6; ++var4) {
            if (var2[var4] != null) {
               var5.C9_f122.currentDialog.getChildById(16 + var4 * 6).getComponentData().C12_f179 = "#P" + var2[var4].M();
               var5.C9_f122.currentDialog.getChildById(17 + var4 * 6).getComponentData().C12_f179 = "#P" + var2[var4].P();
            } else {
               var5.C9_f122.currentDialog.getChildById(16 + var4 * 6).getComponentData().C12_f179 = "#P0";
               var5.C9_f122.currentDialog.getChildById(17 + var4 * 6).getComponentData().C12_f179 = "#P0";
            }
         }

         if (var5.C9_f121.C44_f699 == 16) {
            var5.C9_f122.currentDialog.getChildById(64).getComponentData().C12_f179 = "Gởi lại";
         }

         var5.C9_f122.currentDialog.getChildById(75).setVisible(false);
         var5.C9_f122.currentDialog.getChildById(76).setVisible(false);
      } else if (this.C9_f121 instanceof C29) {
         for(var4 = 0; var4 < 6; ++var4) {
            if (var4 < ((C29)var5.C9_f121).C29_f405.length && var2[((C29)var5.C9_f121).C29_f405[var4]] != null) {
               var5.C9_f122.currentDialog.getChildById(16 + var4 * 6).getComponentData().C12_f179 = "#P" + var2[((C29)var5.C9_f121).C29_f405[var4]].M();
               var5.C9_f122.currentDialog.getChildById(17 + var4 * 6).getComponentData().C12_f179 = "#P" + var2[((C29)var5.C9_f121).C29_f405[var4]].P();
            } else {
               var5.C9_f122.currentDialog.getChildById(16 + var4 * 6).getComponentData().C12_f179 = "#P0";
               var5.C9_f122.currentDialog.getChildById(17 + var4 * 6).getComponentData().C12_f179 = "#P0";
            }
         }

         var5.C9_f122.currentDialog.getChildById(63).setVisible(false);
         var5.C9_f122.currentDialog.getChildById(64).setVisible(false);
         if (var5.C9_f121.C44_f699 == 4) {
            var5.C9_f122.currentDialog.getChildById(75).getComponentData().C12_f179 = "Sử dụng";
         } else if (var5.C9_f121.C44_f698 == 5) {
            var5.C9_f122.currentDialog.getChildById(75).getComponentData().C12_f179 = "Xuất chiến";
         }
      }

      ((RootComponent)var5.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = var5.C9_f123.C53_f778;
      ((RootComponent)var5.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f577 = var5.C9_f123.C53_f778;
      ((RootComponent)var5.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = var1;
      var5.C9_f132 = true;
   }

   private void a(C41[] var1, int var2) {
      if (var1[var2] != null) {
         if (this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195 != null) {
            this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195.c();
         } else {
            this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195.C17_f222 = 3;
         }

         short var10001 = var1[var2].C41_f655;
         this.C9_f122.currentDialog.getChildById(48).getComponentData().C12_f195.a(var10001, false, (byte)-1);
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f179 = C44.c(var1[var2].j((byte)0));
         this.C9_f122.currentDialog.getChildById(52).getComponentData().C12_f179 = C44.c(365 + var1[var2].j((byte)1));
         if (var1[var2].j((byte)19) == -1) {
            this.C9_f122.currentDialog.getChildById(62).getComponentData().C12_f179 = "";
         } else if (C67.C67_f923[0][var1[var2].j((byte)19)][2] != 1 && C67.C67_f923[0][var1[var2].j((byte)19)][2] != 2) {
            if (C67.C67_f923[0][var1[var2].j((byte)19)][2] == 3) {
               this.C9_f122.currentDialog.getChildById(62).getComponentData().C12_f179 = "Có thể dị hoá";
            }
         } else {
            this.C9_f122.currentDialog.getChildById(62).getComponentData().C12_f179 = "Có thể tiến hóa";
         }

         this.C9_f122.currentDialog.getChildById(61).getComponentData().C12_f179 = var1[var2].U();
         if (this.C9_f121 instanceof C29) {
            this.C9_f122.currentDialog.getChildById(64).getComponentData().C12_f179 = "Xuất chiến";
         } else if (this.C9_f121 instanceof C25) {
            this.C9_f122.currentDialog.getChildById(64).getComponentData().C12_f179 = "Xác nhận";
         }

         if (this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.a(258, false, (byte)-1);
         }

         C41 var10000 = var1[var2];
         byte var4 = 5;
         if (var10000.C60_f855[var4] != -1) {
            short[][] var7 = C67.C67_f923[3];
            C41 var10002 = var1[var2];
            var4 = 5;
            short[] var8 = var7[var10002.C60_f855[var4]];
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.a((int)var8[1]);
            DialogData var6 = this.C9_f122.currentDialog.getChildById(60).getComponentData();
            var7 = C67.C67_f923[3];
            var10002 = var1[var2];
            var4 = 5;
            var6.C12_f179 = C44.c((int)var7[var10002.C60_f855[var4]][0]);
         } else {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(60).getComponentData().C12_f179 = "";
         }

         this.C9_f122.currentDialog.getChildById(65).getComponentData().C12_f179 = "" + var1[var2].t();
         this.C9_f122.currentDialog.getChildById(66).getComponentData().C12_f179 = "" + var1[var2].a((byte)2);
         this.C9_f122.currentDialog.getChildById(67).getComponentData().C12_f179 = "" + var1[var2].a((byte)3);
         this.C9_f122.currentDialog.getChildById(68).getComponentData().C12_f179 = "" + var1[var2].a((byte)4);
         var10000 = var1[var2];
         var4 = 0;
         int var3 = var10000.C60_f856[var4];
         int var5 = C67.a((byte)0, (short)var1[var2].r(), (byte)4) - 1;

         for(var2 = 0; var2 < 5; ++var2) {
            this.C9_f122.currentDialog.getChildById(74 - var2).setVisible(true);
            this.C9_f122.currentDialog.getChildById(74 - var2).getComponentData().C12_f195.a(257, false, (byte)-1);
            this.C9_f122.currentDialog.getChildById(74 - var2).getComponentData().C12_f195.C17_f222 = 3;
            if (var2 > var5) {
               this.C9_f122.currentDialog.getChildById(74 - var2).setVisible(false);
            } else if (var3 > 0) {
               this.C9_f122.currentDialog.getChildById(74 - var2).getComponentData().C12_f195.a((byte)14, (byte)-1);
               --var3;
            } else {
               this.C9_f122.currentDialog.getChildById(74 - var2).getComponentData().C12_f195.a((byte)16, (byte)-1);
            }
         }
      }

   }

   private void g(int var1) {
      if (this.C9_f121 instanceof C25) {
         this.a(this.C9_f123.C53_f777, var1);
      } else {
         if (this.C9_f121 instanceof C29) {
            this.a((C41[])this.C9_f123.C53_f777, ((C29)this.C9_f121).C29_f405[var1]);
         }

      }
   }

   public final void aa() {
      if (this.C9_f131 == 0) {
         if (!C44.a((int)this.C9_f125, (int)0) && !this.j() && this.C9_f121.g(4100)) {
            this.C9_f122.currentDialog.handleAction(0);
         } else if (!C44.a((int)this.C9_f125, (int)0) && !this.j() && this.C9_f121.g(8448)) {
            this.C9_f122.currentDialog.handleAction(1);
         } else if (C44.s() && !this.j() && this.C9_f121.g(196640)) {
            if (C44.p() && !C44.a((int)this.C9_f125, (int)0)) {
               return;
            }

            if (this.C9_f121 instanceof C29) {
               int var1;
               if ((var1 = ((C29)this.C9_f121).l(this.C9_f125)) == 0) {
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
                  ((C29)this.C9_f121).e(((C29)this.C9_f121).C29_f407, 0);
                  this.C9_f124 = 0;
                  this.C9_f121.a((byte)15);
                  this.C9_f122.removeDialog("/data/ui/petsetting.ui");
                  this.C9_f122.removeDialog("/data/ui/petstate.ui");
               }
            } else if (this.C9_f121 instanceof C25) {
               if (this.C9_f121.C44_f699 == 16) {
                  if (this.C9_f123.A()) {
                     if (this.C9_f123.i(this.C9_f125)) {
                        this.C9_f123.m(this.C9_f123.C53_f777[this.C9_f125].b((byte)5));
                        this.C9_f123.C53_f777[this.C9_f125].a((byte)5, (short)-1);
                        this.C9_f123.b(this.C9_f123.C53_f777[this.C9_f125].Q());
                        this.C9_f123.n(this.C9_f125);
                        if (this.C9_f125 >= this.C9_f123.C53_f778) {
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
               } else if (this.C9_f121.C44_f699 != 6 && this.C9_f121.C44_f699 != 0) {
                  if (this.C9_f121.C44_f699 == 27) {
                     if (this.C9_f128 == 1 && this.C9_f123.C53_f777[this.C9_f125].S() == 1 || this.C9_f128 == 2 && this.C9_f123.C53_f777[this.C9_f125].S() == 2) {
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
                  this.C9_f121.r();
                  this.C9_f131 = 1;
                  this.C9_f122.showDialog("/data/ui/petsetting.ui", 257, this);
                  ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f126;
                  if (this.C9_f123.C53_f777[this.C9_f125].S() == 2) {
                     this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "Dị hoá";
                     ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = 6;
                     ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f577 = 6;
                  } else if (this.C9_f123.C53_f777[this.C9_f125].S() == 1) {
                     this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "Tiến hóa";
                     ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = 6;
                     ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f577 = 6;
                  } else {
                     this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "";
                     ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = 5;
                     ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f577 = 5;
                  }
               }
            }
         } else if (C7.t() && !this.j() && this.C9_f121.g(262144)) {
            if (this.C9_f121 instanceof C25) {
               if (this.C9_f121.C44_f699 == 16) {
                  this.C9_f121.a((byte)16);
               } else if (this.C9_f121.C44_f699 == 6) {
                  if (C44.C44_f711) {
                     this.C9_f125 = 1;
                  } else {
                     this.C9_f125 = 0;
                  }

                  this.C9_f121.a((byte)6);
               } else if (this.C9_f121.C44_f699 == 27) {
                  this.C9_f121.a((byte)27);
               } else if (this.C9_f121.C44_f699 == 0) {
                  this.C9_f121.a((byte)23);
               }

               this.C9_f122.removeDialog("/data/ui/petstate.ui");
            } else if (this.C9_f121 instanceof C29) {
               if (((C29)this.C9_f121).C44_f699 == 7 || ((C29)this.C9_f121).C44_f699 == 13) {
                  return;
               }

               this.C9_f122.removeDialog("/data/ui/petstate.ui");
               C29.B().C29_f415 = false;
               this.C9_f124 = 0;
               this.C9_f121.a((byte)20);
            }
         }
      } else if (this.C9_f131 == 1) {
         if (!C44.a((int)this.C9_f126, (int)0) && !this.j() && this.C9_f121.g(4100)) {
            this.C9_f122.currentDialog.handleAction(0);
         } else if (!C44.a((int)this.C9_f126, (int)0) && !this.j() && this.C9_f121.g(8448)) {
            this.C9_f122.currentDialog.handleAction(1);
         } else if (C44.s() && !this.j() && this.C9_f121.g(196640)) {
            if (C44.p() && !C44.a((int)this.C9_f126, (int)0)) {
               return;
            }

            if (this.C9_f121.C44_f699 == 16) {
               this.C9_f121.a((byte)16);
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.C9_f122.removeDialog("/data/ui/petstate.ui");
               this.C9_f131 = 0;
            } else if (this.C9_f121.C44_f699 == 6 || this.C9_f121.C44_f699 == 0) {
               switch(this.C9_f126) {
               case 0:
                  this.bh();
                  break;
               case 1:
                  if (!this.C9_f123.C53_f777[this.C9_f125].T()) {
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
                     this.C9_f123.p(this.C9_f125);
                     this.C9_f131 = 0;
                     this.C9_f125 = 0;
                     this.f(this.C9_f125);
                     this.C9_f122.removeDialog("/data/ui/petsetting.ui");
                     ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = 0;
                     ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579 = 0;
                  }
                  break;
               case 2:
                  this.C9_f121.r();
                  this.bf();
                  break;
               case 3:
                  if (C67.a((byte)0, (short)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)22) == 2) {
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
                  this.C9_f121.r();
                  this.bl();
               }
            }
         } else if (C7.t() && !this.j() && this.C9_f121.g(262144)) {
            if (this.C9_f121.C44_f699 == 16) {
               return;
            }

            this.C9_f131 = 0;
            this.C9_f122.removeDialog("/data/ui/petsetting.ui");
         }
      } else if (this.C9_f131 >= 2) {
         if (this.C9_f121 instanceof C29) {
            if (this.C9_f121.g(196640)) {
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
            }
         } else if (this.C9_f121.C44_f699 != 6 && this.C9_f121.C44_f699 != 0) {
            if (this.C9_f131 <= 3) {
               this.bp();
            } else if (this.C9_f131 == 4 && this.C9_f121.g(196640)) {
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
            }
         } else {
            switch(this.C9_f126) {
            case 0:
               this.bn();
               break;
            case 1:
               if (this.C9_f121.g(196640)) {
                  this.C9_f131 = 0;
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               }
               break;
            case 2:
               this.bm();
               break;
            case 3:
               if (this.C9_f121.g(131072) && this.C9_f131 == 2 || this.C9_f121.g(196640) && this.C9_f131 == 3) {
                  if (this.C9_f131 == 2) {
                     if (this.C9_f123.i(this.C9_f125)) {
                        this.C9_f123.m(this.C9_f123.C53_f777[this.C9_f125].b((byte)5));
                        this.C9_f123.C53_f777[this.C9_f125].a((byte)5, (short)-1);
                        this.C9_f123.n(this.C9_f125);
                        if (this.C9_f125 >= this.C9_f123.C53_f778) {
                           --this.C9_f125;
                        }

                        this.f(this.C9_f125);
                        ((C25)this.C9_f121).C25_f348.G();
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
               } else if (this.C9_f121.g(786432) && this.C9_f131 <= 2) {
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
      this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "Vật phẩm trang sức";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "Trạng thái";
      if (this.C9_f121 instanceof C25) {
         this.C9_f122.currentDialog.getChildById(5).setVisible(false);
         this.C9_f122.currentDialog.getChildById(6).setVisible(false);
         this.C9_f122.currentDialog.getChildById(59).setVisible(true);
         this.C9_f122.currentDialog.getChildById(60).setVisible(true);
         this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "Mang theo";
      } else {
         this.C9_f122.currentDialog.getChildById(5).setVisible(true);
         this.C9_f122.currentDialog.getChildById(6).setVisible(true);
         this.C9_f122.currentDialog.getChildById(59).setVisible(false);
         this.C9_f122.currentDialog.getChildById(60).setVisible(false);
         this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Mang theo";
      }

      this.bg();
      this.C9_f132 = true;
   }

   private void bg() {
      if (this.C9_f123.C53_f789.size() > 5) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(0);
      }

      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = this.C9_f123.C53_f789.size();
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;
      if (this.C9_f136 >= this.C9_f123.C53_f789.size()) {
         this.C9_f136 = this.C9_f123.C53_f789.size() - 1;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f136;
      }

      if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
         --this.C9_f135;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579 = this.C9_f135;
      }

      if (this.C9_f123.C53_f789.size() > 0) {
         C41 var10000 = this.C9_f123.C53_f777[this.C9_f125];
         byte var4 = 5;
         if (var10000.C60_f855[var4] == ((int[])this.C9_f123.C53_f789.elementAt(this.C9_f136))[0]) {
            if (this.C9_f121 instanceof C25) {
               this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "Dỡ xuống";
            } else {
               this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Dỡ xuống";
            }
         } else if (this.C9_f121 instanceof C25) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "Mang theo";
         } else {
            this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Mang theo";
         }

         for(int var1 = 0; var1 < 5; ++var1) {
            if (this.C9_f135 + var1 < this.C9_f123.C53_f789.size()) {
               int[] var2 = (int[])this.C9_f123.C53_f789.elementAt(this.C9_f135 + var1);
               if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195 == null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195 = new C17();
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.a((int)0);
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.C17_f222 = 2;
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.a(258, false, (byte)-1);
               }

               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.a((int)C67.C67_f923[3][var2[0]][1]);
               this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[3][var2[0]][0]);
               var10000 = C53.p().C53_f777[this.C9_f125];
               var4 = 5;
               if (var10000.C60_f855[var4] == var2[0]) {
                  this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = "Đã mang theo";
               } else if (var2[1] == 1) {
                  this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = "Bị mang theo";
               } else {
                  this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = "";
               }
            } else {
               if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195 != null) {
                  this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.c();
               }

               this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().C12_f179 = "";
               this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = "";
            }
         }

         if (this.C9_f123.C53_f789.size() > 0) {
            this.C9_f122.currentDialog.getChildById(53).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[3][((int[])this.C9_f123.C53_f789.elementAt(this.C9_f136))[0]][2]);
         } else {
            this.C9_f122.currentDialog.getChildById(53).getComponentData().C12_f179 = "";
         }

         if (this.C9_f123.C53_f789.size() > 0) {
            this.C9_f122.currentDialog.getChildById(51).setOffsetY(98 + this.C9_f136 * 62 / this.C9_f123.C53_f789.size(), this.C9_f122.currentDialog.getRootComponent());
         } else {
            this.C9_f122.currentDialog.getChildById(51).setOffsetY(98, this.C9_f122.currentDialog.getRootComponent());
         }
      }
   }

   private void bh() {
      this.C9_f131 = 2;
      this.C9_f127 = 0;
      this.C9_f122.showDialog("/data/ui/choice.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/petsetting.ui");
      this.C9_f122.removeDialog("/data/ui/petstate.ui");
      this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "Đạo cụ";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "Số lượng";
      if (this.C9_f121 instanceof C25) {
         this.C9_f122.currentDialog.getChildById(5).setVisible(false);
         this.C9_f122.currentDialog.getChildById(6).setVisible(false);
         this.C9_f122.currentDialog.getChildById(59).setVisible(true);
         this.C9_f122.currentDialog.getChildById(60).setVisible(true);
         this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "Sử dụng";
      } else {
         this.C9_f122.currentDialog.getChildById(5).setVisible(true);
         this.C9_f122.currentDialog.getChildById(6).setVisible(true);
         this.C9_f122.currentDialog.getChildById(59).setVisible(false);
         this.C9_f122.currentDialog.getChildById(60).setVisible(false);
         this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Sử dụng";
      }

      this.bi();
      this.C9_f132 = true;
   }

   private void bi() {
      if (this.C9_f123.C53_f787.size() > 5) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(0);
      }

      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = this.C9_f123.C53_f787.size();
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;
      if (this.C9_f136 >= this.C9_f123.C53_f787.size()) {
         this.C9_f136 = this.C9_f123.C53_f787.size() - 1;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f136;
      }

      if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
         --this.C9_f135;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579 = this.C9_f135;
      }

      for(int var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.C53_f787.size()) {
            int[] var2 = (int[])this.C9_f123.C53_f787.elementAt(this.C9_f135 + var1);
            if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.a(258, false, (byte)-1);
            }

            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.a((int)C67.C67_f923[4][var2[0]][1]);
            this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[4][var2[0]][0]);
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = "" + var2[1];
         } else {
            if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195 != null) {
               this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.c();
            }

            this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().C12_f179 = "";
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = "";
         }
      }

      if (this.C9_f123.C53_f787.size() > 0) {
         this.C9_f122.currentDialog.getChildById(53).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[4][((int[])this.C9_f123.C53_f787.elementAt(this.C9_f136))[0]][2]);
      } else {
         this.C9_f122.currentDialog.getChildById(53).getComponentData().C12_f179 = "";
      }

      if (this.C9_f123.C53_f787.size() > 0) {
         this.C9_f122.currentDialog.getChildById(51).setOffsetY(98 + this.C9_f136 * 80 / this.C9_f123.C53_f787.size(), this.C9_f122.currentDialog.getRootComponent());
      } else {
         this.C9_f122.currentDialog.getChildById(51).setOffsetY(98, this.C9_f122.currentDialog.getRootComponent());
      }
   }

   private void bj() {
      this.C9_f131 = 2;
      this.C9_f127 = 0;
      this.C9_f122.showDialog("/data/ui/skill.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/petsetting.ui");
      this.C9_f122.removeDialog("/data/ui/petstate.ui");
      this.C9_f122.currentDialog.getChildById(12).getComponentData().C12_f179 = C44.c(this.C9_f123.C53_f777[this.C9_f125].j((byte)0));
      this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = "" + this.C9_f123.C53_f777[this.C9_f125].t();
      if (this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f195 != null) {
         this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f195.c();
      } else {
         this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f195.a((int)0);
         this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f195.C17_f222 = 3;
      }

      short var10001 = this.C9_f123.C53_f777[this.C9_f125].C41_f655;
      this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f195.a(var10001, false, (byte)-1);
      int var1 = this.C9_f123.C53_f777[this.C9_f125].F();

      for(int var2 = 0; var2 < var1; ++var2) {
         this.C9_f122.currentDialog.getChildById(var2 + 18).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[1][this.C9_f123.C53_f777[this.C9_f125].t(var2)][1]);
      }

      this.bk();
      this.C9_f132 = true;
   }

   private void bk() {
      if (this.C9_f123.C53_f777[this.C9_f125].t(this.C9_f127) != -1) {
         String[] var1 = new String[]{"Nhất định", "Nhất định"};
         this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = C44.a(C67.C67_f923[1][this.C9_f123.C53_f777[this.C9_f125].t(this.C9_f127)][2], (String[])var1);
      } else {
         this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "";
      }
   }

   private void bl() {
      this.C9_f131 = 2;
      this.C9_f127 = 0;
      this.C9_f122.showDialog("/data/ui/evolve.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/petsetting.ui");
      this.C9_f122.removeDialog("/data/ui/petstate.ui");
      if (this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.a((int)0);
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.C17_f222 = 3;
      }

      short var10001 = this.C9_f123.C53_f777[this.C9_f125].C41_f655;
      this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.a(var10001, false, (byte)-1);
      short var1 = (short)(C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)20) + 12);
      short var2 = C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)21);
      this.C9_f122.currentDialog.getChildById(38).getComponentData().C12_f179 = C44.c((int)C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)0));
      this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = "" + this.C9_f123.C53_f777[this.C9_f125].t();
      this.C9_f122.currentDialog.getChildById(45).getComponentData().C12_f179 = C44.c((int)C67.a((byte)3, var1, (byte)0));
      this.C9_f122.currentDialog.getChildById(46).getComponentData().C12_f179 = this.C9_f123.a((int)var1, (byte)2) + "/" + var2;
      var1 = C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)19);
      C41 var6;
      (var6 = new C41()).a(var1, (byte)this.C9_f123.C53_f777[this.C9_f125].t(), (short)-1, (byte)-1, (short)-1, (byte)-1);

      for(int var5 = 0; var5 < 4; ++var5) {
         DialogData var10000 = this.C9_f122.currentDialog.getChildById(var5 + 19).getComponentData();
         StringBuffer var7 = new StringBuffer();
         C41 var10002 = this.C9_f123.C53_f777[this.C9_f125];
         byte var4 = (byte)(var5 + 1);
         var10000.C12_f179 = var7.append(var10002.C60_f855[var4]).toString();
         var10000 = this.C9_f122.currentDialog.getChildById(var5 + 31).getComponentData();
         var7 = new StringBuffer();
         var4 = (byte)(var5 + 1);
         var10000.C12_f179 = var7.append(var6.C60_f855[var4]).toString();
      }

      this.C9_f132 = true;
   }

   private void bm() {
      if (!C44.a((int)this.C9_f125, (int)0) && !this.j() && this.C9_f131 == 2 && this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bg();
      } else if (!C44.a((int)this.C9_f125, (int)0) && !this.j() && this.C9_f131 == 2 && this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bg();
      } else if (C44.s() && !this.j() && this.C9_f121.g(196640) && this.C9_f123.C53_f789.size() > 0) {
         if (!C44.p() || C44.a((int)this.C9_f125, (int)0)) {
            if (this.C9_f131 == 2) {
               int[] var1 = (int[])this.C9_f123.C53_f789.elementAt(this.C9_f136);
               C41 var10000 = this.C9_f123.C53_f777[this.C9_f125];
               byte var3 = 5;
               if (var10000.C60_f855[var3] == var1[0]) {
                  C41 var10001 = this.C9_f123.C53_f777[this.C9_f125];
                  var3 = 5;
                  this.C9_f123.m(var10001.C60_f855[var3]);
                  var10000 = this.C9_f123.C53_f777[this.C9_f125];
                  byte var4 = -1;
                  var3 = 5;
                  var10000.C60_f855[var3] = var4;
                  this.bg();
                  this.H();
                  this.a("Thành công dỡ xuống", "Nhấn nút 5 tiếp tục");
               } else {
                  this.C9_f123.f(var1[0], this.C9_f125);
                  this.bg();
                  this.H();
                  this.a("Thành công mang theo", "Nhấn nút 5 tiếp tục");
               }

               this.C9_f131 = 3;
            } else {
               this.C9_f131 = 2;
               this.C9_f121.r();
               this.f(this.C9_f125);
               this.I();
               this.C9_f122.removeDialog("/data/ui/choice.ui");
            }
         }
      } else {
         if (C7.t() && !this.j() && this.C9_f131 == 2 && this.C9_f121.g(262144)) {
            this.f(this.C9_f125);
            this.C9_f122.removeDialog("/data/ui/choice.ui");
         }

      }
   }

   private void bn() {
      if (this.C9_f131 == 2 && this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f131 == 2 && this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else {
         if (this.C9_f121.g(196640)) {
            if (this.C9_f123.C53_f787.size() <= 0) {
               return;
            }

            if (this.C9_f131 == 2) {
               this.C9_f131 = 3;
               int[] var1;
               switch((var1 = (int[])this.C9_f123.C53_f787.elementAt(this.C9_f127))[0]) {
               case 13:
               case 14:
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Đạo cụ này không thể sử dụng", "Nhấn nút 5 tiếp tục");
                  return;
               default:
                  switch(this.C9_f123.C53_f777[this.C9_f125].x(var1[0])) {
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
                     this.C9_f123.C53_f777[this.C9_f125].w(var1[0]);
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
         } else if (this.C9_f131 == 2 && this.C9_f121.g(262144)) {
            this.f(this.C9_f125);
            this.C9_f122.removeDialog("/data/ui/choice.ui");
         }

      }
   }

   private void bo() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bk();
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bk();
      } else if (this.C9_f121.g(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
         this.bk();
      } else if (this.C9_f121.g(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
         this.bk();
      } else {
         if (this.C9_f121.g(262144)) {
            this.f(this.C9_f125);
            this.C9_f122.removeDialog("/data/ui/skill.ui");
         }

      }
   }

   private void bp() {
      short var1;
      short var3;
      short var4;
      if (C25.C25_f300 != null) {
         if (!C25.C25_f300.j()) {
            var1 = C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)19);
            String var9 = C44.c((int)C67.a((byte)0, var1, (byte)0));
            var3 = C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)19);
            var4 = C67.a((byte)0, var3, (byte)17);
            C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)21);
            this.C9_f122.currentDialog.getChildById(10).setVisible(true);
            this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.a(var4, false, (byte)-1);
            this.C9_f122.currentDialog.getChildById(38).getComponentData().C12_f179 = C44.c((int)C67.a((byte)0, var3, (byte)0));
            C41 var11 = new C41();
            short var5 = C67.a((byte)0, var3, (byte)3);
            byte var6 = -1;
            C41 var10000 = this.C9_f123.C53_f777[this.C9_f125];
            byte var8 = 0;
            if (var10000.C60_f855[var8] >= var5) {
               var10000 = this.C9_f123.C53_f777[this.C9_f125];
               var8 = 0;
               var6 = (byte)var10000.C60_f855[var8];
            }

            int var10002 = this.C9_f123.C53_f777[this.C9_f125].t();
            C41 var10003 = this.C9_f123.C53_f777[this.C9_f125];
            var8 = 5;
            short var13 = var10003.C60_f855[var8];
            C41 var10004 = this.C9_f123.C53_f777[this.C9_f125];
            var8 = 6;
            var11.a(var3, var10002, var13, (byte)var10004.C60_f856[var8], (short)var6, (byte)-1);
            var8 = 1;
            var11.a((short)var11.C60_f855[var8], this.C9_f123.C53_f777[this.C9_f125].A(), this.C9_f123.C53_f777[this.C9_f125].C41_f662);
            var11.b(this.C9_f123.C53_f777[this.C9_f125].R());
            this.C9_f123.a((byte)((byte)this.C9_f123.C53_f777[this.C9_f125].j((byte)1)), var3, (byte)2);
            this.C9_f123.C53_f777[this.C9_f125].a(var11.Q());
            var5 = (short)(C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)20) + 12);
            var4 = C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)21);
            var3 = C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)19);
            int var12 = this.C9_f123.a((int)var5, (byte)2);
            if (var3 == -1) {
               this.C9_f122.currentDialog.getChildById(42).getComponentData().C12_f179 = "";
               this.C9_f122.currentDialog.getChildById(45).getComponentData().C12_f179 = "";
               this.C9_f122.currentDialog.getChildById(46).getComponentData().C12_f179 = "";
            } else {
               this.C9_f122.currentDialog.getChildById(45).getComponentData().C12_f179 = C44.c((int)C67.a((byte)3, var5, (byte)0));
               this.C9_f122.currentDialog.getChildById(46).getComponentData().C12_f179 = var12 + "/" + var4;
            }

            if (this.C9_f123.C53_f777[this.C9_f125].S() == 2) {
               this.C9_f131 = 3;
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Dị hoá thành #2" + var9, "Nhấn nút 5 tiếp tục");
            } else {
               this.C9_f131 = 3;
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Tiến hóa thành #2" + var9, "Nhấn nút 5 tiếp tục");
            }

            C25.C25_f300 = null;
         }

      } else {
         if (C44.s() && !this.j() && this.C9_f121.g(196640)) {
            if (this.C9_f131 == 2) {
               var1 = (short)(C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)20) + 12);
               short var2 = C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)21);
               if ((var3 = C67.a((byte)0, (byte)this.C9_f123.C53_f777[this.C9_f125].r(), (byte)19)) == -1) {
                  this.C9_f131 = 3;
                  this.H();
                  this.a("Không thể lại tiến hóa hoặc dị hoá", "Nhấn nút 5 tiếp tục");
                  return;
               }

               var4 = C67.a((byte)0, var3, (byte)17);
               if (this.C9_f123.C53_f777[this.C9_f125].t() >= C41.C41_f639[C67.a((byte)0, var3, (byte)2) - 1]) {
                  if (this.C9_f123.a((int)var1, (byte)2) >= var2) {
                     this.C9_f122.currentDialog.getChildById(10).setVisible(false);
                     C25.C25_f300 = new C21();
                     short[] var10 = new short[]{0, 0, 10, 116, 164, this.C9_f123.C53_f777[this.C9_f125].C41_f655, 0, 0, var4, 0, 0};
                     C25.C25_f300.a(var10);
                     C25.C25_f300.d(true);
                     C25.C25_f300.a();
                     this.C9_f123.d(var1, var2, (byte)2);
                     return;
                  }

                  this.C9_f131 = 3;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  if (this.C9_f123.C53_f777[this.C9_f125].S() == 2) {
                     this.a("Tài liệu chưa đủ, không thể dị hoá", "Nhấn nút 5 tiếp tục");
                     return;
                  }

                  this.a("Tài liệu chưa đủ, không thể tiến hóa", "Nhấn nút 5 tiếp tục");
                  return;
               }

               this.C9_f131 = 3;
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Còn chưa tới" + C41.C41_f639[C67.a((byte)0, var3, (byte)2) - 1] + " cấp, không thể vào hóa", "Nhấn nút 5 tiếp tục");
               return;
            }

            if (this.C9_f131 == 3) {
               if (this.C9_f121.C44_f699 == 6 || this.C9_f121.C44_f699 == 0) {
                  this.C9_f131 = 2;
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  this.C9_f121.r();
                  return;
               }

               if (this.C9_f121.C44_f699 == 27) {
                  this.f(this.C9_f125);
                  this.C9_f131 = 0;
                  this.C9_f126 = 0;
                  this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
                  this.C9_f122.removeDialog("/data/ui/evolve.ui");
                  return;
               }
            }
         } else if (this.C9_f131 < 3 && this.C9_f121.g(262144) && !this.j() && C44.t()) {
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
      this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = "Vật phẩm";
      this.C9_f125 = 0;
   }

   private void bq() {
      ((RootComponent)this.C9_f122.currentDialog.getChildById(8 + this.C9_f125 * 39)).otherChildComponent.C38_f579 = 0;
      ((RootComponent)this.C9_f122.currentDialog.getChildById(8 + this.C9_f125 * 39)).otherChildComponent.C38_f580 = 0;
      this.br();
   }

   private void br() {
      switch(this.C9_f125) {
      case 0:
         this.bs();
         break;
      case 1:
         C9 var4 = this;
         if (this.C9_f123.C53_f789.size() > 5) {
            ((RootComponent)this.C9_f122.currentDialog.getChildById(47)).otherChildComponent.a(1);
         } else {
            ((RootComponent)this.C9_f122.currentDialog.getChildById(47)).otherChildComponent.a(0);
         }

         ((RootComponent)this.C9_f122.currentDialog.getChildById(47)).otherChildComponent.C38_f574 = this.C9_f123.C53_f789.size();
         this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(47)).otherChildComponent.C38_f579;
         this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(47)).otherChildComponent.C38_f580;
         this.C9_f122.currentDialog.getChildById(7).setVisible(false);

         for(int var2 = 0; var2 < 5; ++var2) {
            if (var4.C9_f135 + var2 < var4.C9_f123.C53_f789.size()) {
               int[] var3 = (int[])var4.C9_f123.C53_f789.elementAt(var4.C9_f135 + var2);
               if (var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f195 == null) {
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f195 = new C17();
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f195.a((int)0);
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f195.C17_f222 = 2;
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f195.a(258, false, (byte)-1);
               }

               if (var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f191 == null) {
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f191 = new C17();
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f191.a((int)0);
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f191.C17_f222 = 2;
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f191.a(258, false, (byte)-1);
               }

               var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f195.a((int)C67.C67_f923[3][var3[0]][1]);
               var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f191.a((int)C67.C67_f923[3][var3[0]][1]);
               var4.C9_f122.currentDialog.getChildById(60 + var2 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[3][var3[0]][0]);
               if (var3[1] == 1) {
                  var4.C9_f122.currentDialog.getChildById(61 + var2 * 5).getComponentData().C12_f179 = "Đã mang theo";
               } else {
                  var4.C9_f122.currentDialog.getChildById(61 + var2 * 5).getComponentData().C12_f179 = "";
               }
            } else {
               if (var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f195 != null) {
                  var4.C9_f122.currentDialog.getChildById(59 + var2 * 5).getComponentData().C12_f195.c();
               }

               var4.C9_f122.currentDialog.getChildById(60 + var2 * 5).getComponentData().C12_f179 = "";
               var4.C9_f122.currentDialog.getChildById(61 + var2 * 5).getComponentData().C12_f179 = "";
            }
         }

         if (var4.C9_f123.C53_f789.size() > 0) {
            var4.C9_f122.currentDialog.getChildById(85).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[3][((int[])var4.C9_f123.C53_f789.elementAt(var4.C9_f136))[0]][2]);
         } else {
            var4.C9_f122.currentDialog.getChildById(85).getComponentData().C12_f179 = "";
         }

         if (var4.C9_f123.C53_f789.size() > 0) {
            var4.C9_f122.currentDialog.getChildById(84).setOffsetY(127 + var4.C9_f136 * 72 / var4.C9_f123.C53_f789.size(), var4.C9_f122.currentDialog.getRootComponent());
         } else {
            var4.C9_f122.currentDialog.getChildById(84).setOffsetY(127, var4.C9_f122.currentDialog.getRootComponent());
         }
         break;
      case 2:
         this.bt();
         break;
      case 3:
         this.bu();
         if (this.C9_f136 < 0 || this.C9_f123.C53_f791.size() <= 0) {
            return;
         }

         int[] var1 = (int[])this.C9_f123.C53_f791.elementAt(this.C9_f136);
         this.C9_f122.currentDialog.getChildById(164).setVisible(false);
         this.C9_f122.currentDialog.getChildById(165).setVisible(false);
         switch(var1[0]) {
         case 0:
            if (this.C9_f123.l(var1[0])) {
               this.C9_f122.currentDialog.getChildById(7).setVisible(true);
               this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Ấp trứng";
               this.C9_f122.currentDialog.getChildById(164).setVisible(true);
               this.C9_f122.currentDialog.getChildById(165).setVisible(true);
               if (this.C9_f123.C53_f786 == 0) {
                  this.C9_f122.currentDialog.getChildById(164).getComponentData().C12_f179 = "#P" + C25.C25_f314 * 100 / 10;
                  this.C9_f122.currentDialog.getChildById(165).getComponentData().C12_f179 = C25.C25_f314 + "/10";
               } else {
                  this.C9_f122.currentDialog.getChildById(164).getComponentData().C12_f179 = "#P" + C25.C25_f314 * 100 / 30;
                  this.C9_f122.currentDialog.getChildById(165).getComponentData().C12_f179 = C25.C25_f314 + "/30";
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
            this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Mở ra";
            break;
         case 7:
         case 8:
         case 9:
            this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Sử dụng";
         }
      }

      this.C9_f132 = true;
   }

   private void bs() {
      int var1;
      if ((var1 = this.C9_f123.C53_f788.size() + this.C9_f123.C53_f787.size()) > 5) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(8)).otherChildComponent.a(1);
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(8)).otherChildComponent.a(0);
      }

      ((RootComponent)this.C9_f122.currentDialog.getChildById(8)).otherChildComponent.C38_f574 = var1;
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(8)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(8)).otherChildComponent.C38_f580;
      this.C9_f122.currentDialog.getChildById(7).setVisible(true);
      this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Sử dụng";

      for(int var2 = 0; var2 < 5; ++var2) {
         if (this.C9_f135 + var2 < var1) {
            int[] var3;
            if (this.C9_f135 + var2 < this.C9_f123.C53_f788.size()) {
               var3 = (int[])this.C9_f123.C53_f788.elementAt(this.C9_f135 + var2);
            } else {
               var3 = (int[])this.C9_f123.C53_f787.elementAt(this.C9_f135 + var2 - this.C9_f123.C53_f788.size());
            }

            if (this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f195.a(258, false, (byte)-1);
            }

            if (this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f191 == null) {
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f191 = new C17();
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f191.a((int)0);
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f191.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f191.a(258, false, (byte)-1);
            }

            this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f195.a((int)C67.C67_f923[4][var3[0]][1]);
            this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f191.a((int)C67.C67_f923[4][var3[0]][1]);
            this.C9_f122.currentDialog.getChildById(19 + var2 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[4][var3[0]][0]);
            this.C9_f122.currentDialog.getChildById(20 + var2 * 5).getComponentData().C12_f179 = "" + var3[1];
         } else {
            if (this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f195 != null) {
               this.C9_f122.currentDialog.getChildById(18 + var2 * 5).getComponentData().C12_f195.c();
            }

            this.C9_f122.currentDialog.getChildById(19 + var2 * 5).getComponentData().C12_f179 = "";
            this.C9_f122.currentDialog.getChildById(20 + var2 * 5).getComponentData().C12_f179 = "";
         }
      }

      if (var1 > 0) {
         if (this.C9_f136 < this.C9_f123.C53_f788.size()) {
            this.C9_f122.currentDialog.getChildById(46).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[4][((int[])this.C9_f123.C53_f788.elementAt(this.C9_f136))[0]][2]);
         } else {
            this.C9_f122.currentDialog.getChildById(46).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[4][((int[])this.C9_f123.C53_f787.elementAt(this.C9_f136 - this.C9_f123.C53_f788.size()))[0]][2]);
         }
      } else {
         this.C9_f122.currentDialog.getChildById(46).getComponentData().C12_f179 = "";
      }

      if (var1 > 0) {
         this.C9_f122.currentDialog.getChildById(43).setOffsetY(127 + this.C9_f136 * 72 / var1, this.C9_f122.currentDialog.getRootComponent());
      } else {
         this.C9_f122.currentDialog.getChildById(43).setOffsetY(127, this.C9_f122.currentDialog.getRootComponent());
      }
   }

   private void bt() {
      if (this.C9_f123.C53_f790.size() > 5) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(86)).otherChildComponent.a(1);
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(86)).otherChildComponent.a(0);
      }

      ((RootComponent)this.C9_f122.currentDialog.getChildById(86)).otherChildComponent.C38_f574 = this.C9_f123.C53_f790.size();
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(86)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(86)).otherChildComponent.C38_f580;
      this.C9_f122.currentDialog.getChildById(7).setVisible(false);

      for(int var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.C53_f790.size()) {
            int[] var2 = (int[])this.C9_f123.C53_f790.elementAt(this.C9_f135 + var1);
            if (this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f195.a(258, false, (byte)-1);
            }

            if (this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f191 == null) {
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f191 = new C17();
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f191.a((int)0);
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f191.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f191.a(258, false, (byte)-1);
            }

            this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f195.a((int)C67.C67_f923[3][var2[0]][1]);
            this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f191.a((int)C67.C67_f923[3][var2[0]][1]);
            if (var2[0] == 17) {
               this.C9_f122.currentDialog.getChildById(99 + var1 * 5).getComponentData().C12_f179 = "Chìa khóa vàng";
            } else {
               this.C9_f122.currentDialog.getChildById(99 + var1 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[3][var2[0]][0]);
            }

            this.C9_f122.currentDialog.getChildById(100 + var1 * 5).getComponentData().C12_f179 = "" + var2[1];
         } else {
            if (this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f195 != null) {
               this.C9_f122.currentDialog.getChildById(98 + var1 * 5).getComponentData().C12_f195.c();
            }

            this.C9_f122.currentDialog.getChildById(99 + var1 * 5).getComponentData().C12_f179 = "";
            this.C9_f122.currentDialog.getChildById(100 + var1 * 5).getComponentData().C12_f179 = "";
         }
      }

      if (this.C9_f123.C53_f790.size() > 0) {
         this.C9_f122.currentDialog.getChildById(124).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[3][((int[])this.C9_f123.C53_f790.elementAt(this.C9_f136))[0]][2]);
      } else {
         this.C9_f122.currentDialog.getChildById(124).getComponentData().C12_f179 = "";
      }

      if (this.C9_f123.C53_f790.size() > 0) {
         this.C9_f122.currentDialog.getChildById(123).setOffsetY(127 + this.C9_f136 * 72 / this.C9_f123.C53_f790.size(), this.C9_f122.currentDialog.getRootComponent());
      } else {
         this.C9_f122.currentDialog.getChildById(123).setOffsetY(127, this.C9_f122.currentDialog.getRootComponent());
      }
   }

   private void bu() {
      if (this.C9_f123.C53_f791.size() > 5) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(125)).otherChildComponent.a(1);
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(125)).otherChildComponent.a(0);
      }

      ((RootComponent)this.C9_f122.currentDialog.getChildById(125)).otherChildComponent.C38_f574 = this.C9_f123.C53_f791.size();
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(125)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(125)).otherChildComponent.C38_f580;

      int var1;
      for(var1 = 0; var1 < 5; ++var1) {
         if (this.C9_f135 + var1 < this.C9_f123.C53_f791.size()) {
            int[] var2 = (int[])this.C9_f123.C53_f791.elementAt(this.C9_f135 + var1);
            if (this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f195.a(258, false, (byte)-1);
            }

            if (this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f191 == null) {
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f191 = new C17();
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f191.a((int)0);
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f191.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f191.a(258, false, (byte)-1);
            }

            this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f195.a((int)C67.C67_f923[5][var2[0]][1]);
            this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f191.a((int)C67.C67_f923[5][var2[0]][1]);
            this.C9_f122.currentDialog.getChildById(138 + var1 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[5][var2[0]][0]);
            switch(var2[0]) {
            case 0:
               if (this.C9_f123.l(var2[0])) {
                  this.C9_f122.currentDialog.getChildById(163).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[5][var2[0]][2]);
                  if (C25.B().O()) {
                     this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().C12_f179 = "Hoàn thành";
                  } else {
                     this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().C12_f179 = "1 cái";
                  }
               } else {
                  this.C9_f122.currentDialog.getChildById(163).getComponentData().C12_f179 = C44.c((int)634);
                  this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().C12_f179 = "0 cái";
               }
               break;
            default:
               this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().C12_f179 = "";
            }
         } else {
            if (this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f195 != null) {
               this.C9_f122.currentDialog.getChildById(137 + var1 * 5).getComponentData().C12_f195.c();
            }

            this.C9_f122.currentDialog.getChildById(138 + var1 * 5).getComponentData().C12_f179 = "";
            this.C9_f122.currentDialog.getChildById(139 + var1 * 5).getComponentData().C12_f179 = "";
         }
      }

      if (this.C9_f123.C53_f791.size() > 0) {
         if ((var1 = ((int[])this.C9_f123.C53_f791.elementAt(this.C9_f136))[0]) != 0) {
            this.C9_f122.currentDialog.getChildById(163).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[5][var1][2]);
            this.C9_f122.currentDialog.getChildById(7).setVisible(true);
         }

         if (var1 == 0) {
            if (((int[])this.C9_f123.C53_f791.elementAt(this.C9_f136))[1] == 1) {
               this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Đóng cửa";
            } else {
               this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Mở ra";
            }
         } else if (var1 <= 0 && var1 > 4) {
            if (var1 == 10) {
               this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Gia tốc";
            } else {
               this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Sử dụng";
            }
         } else if (this.C9_f123.C53_f765 == var1 - 1) {
            this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Triệu hồi";
         } else {
            this.C9_f122.currentDialog.getChildById(7).getComponentData().C12_f179 = "Triệu hoán";
         }
      } else {
         this.C9_f122.currentDialog.getChildById(163).getComponentData().C12_f179 = "";
         this.C9_f122.currentDialog.getChildById(7).setVisible(false);
      }

      if (this.C9_f123.C53_f791.size() > 0) {
         this.C9_f122.currentDialog.getChildById(162).setOffsetY(127 + this.C9_f136 * 72 / this.C9_f123.C53_f791.size(), this.C9_f122.currentDialog.getRootComponent());
      } else {
         this.C9_f122.currentDialog.getChildById(162).setOffsetY(127, this.C9_f122.currentDialog.getRootComponent());
      }
   }

   public final void ac() {
      if (this.C9_f131 == 0 && this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.g(this.C9_f126);
      } else if (this.C9_f131 == 0 && this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.g(this.C9_f126);
      } else if (this.C9_f121.g(196640)) {
         this.bx();
      } else {
         if (this.C9_f131 == 0 && this.C9_f121.g(262144)) {
            this.C9_f121.a((byte)8);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }

      }
   }

   public final void ad() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.g(this.C9_f125);
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.g(this.C9_f125);
      } else if (this.C9_f121.g(196640)) {
         this.C9_f123.f(this.C9_f130, this.C9_f125);
         this.C9_f121.a((byte)8);
      } else {
         if (this.C9_f121.g(262144)) {
            this.C9_f121.a((byte)8);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }

      }
   }

   public final void ae() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.g(this.C9_f125);
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.g(this.C9_f125);
      } else {
         if (this.C9_f121.g(196640)) {
            if (this.C9_f131 == 0) {
               if (this.C9_f123.C53_f777[this.C9_f125].t() < 50) {
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Chỉ có thể cho 50 cấp sủng vật sử dụng", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 2;
                  return;
               }

               if (this.C9_f123.e(this.C9_f130, this.C9_f125)) {
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Sử dụng thành công", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 1;
                  return;
               }
            } else {
               if (this.C9_f131 == 1) {
                  this.C9_f131 = 0;
                  this.C9_f121.a((byte)8);
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
         } else if (this.C9_f121.g(262144) && this.C9_f131 == 0) {
            this.C9_f121.a((byte)8);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }

      }
   }

   public final void af() {
      this.C9_f121.q();
      if (this.C9_f131 == 0 && this.C9_f121.g(16400) && !this.j() && !C44.a((int)this.C9_f125, (int)1)) {
         this.C9_f122.currentDialog.handleAction(7);
         this.C9_f122.currentDialog.handleAction(2);
         this.C9_f122.currentDialog.handleAction(5);
         this.bq();
         this.C9_f121.r();
      } else if (this.C9_f131 == 0 && this.C9_f121.g(32832) && !this.j() && !C44.a((int)this.C9_f125, (int)1)) {
         this.C9_f122.currentDialog.handleAction(7);
         this.C9_f122.currentDialog.handleAction(3);
         this.C9_f122.currentDialog.handleAction(5);
         this.bq();
         this.C9_f121.r();
      } else if (this.C9_f131 == 0 && this.C9_f121.g(4100) && !this.j() && !C44.a((int)this.C9_f136, (int)0)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f131 == 0 && this.C9_f121.g(8448) && !this.j() && !C44.a((int)this.C9_f136, (int)0)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.g(196640) && !this.j() && C44.s()) {
         int var5;
         if (this.C9_f131 == 0) {
            if (C44.p() && !C44.a((int)this.C9_f136, (int)0)) {
               return;
            }

            int[] var1;
            label206:
            switch(this.C9_f125) {
            case 0:
               if (this.C9_f136 >= this.C9_f123.C53_f788.size()) {
                  if (this.C9_f123.C53_f787.size() <= 0) {
                     return;
                  }

                  var1 = (int[])this.C9_f123.C53_f787.elementAt(this.C9_f136 - this.C9_f123.C53_f788.size());
               } else {
                  var1 = (int[])this.C9_f123.C53_f788.elementAt(this.C9_f136);
               }

               switch(var1[0]) {
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
                  this.C9_f121.a((byte)17);
                  this.C9_f122.removeDialog("/data/ui/bag.ui");
                  break label206;
               case 13:
                  if (this.C9_f131 == 0) {
                     if (this.C9_f123.C53_f775 <= 0) {
                        if (C25.B().C25_f290 == 3 && C25.B().C25_f291 == 7) {
                           this.H();
                           this.a("Nơi này không cách nào sử dụng tránh quái hoàn", "Nhấn nút 5 tiếp tục");
                           this.C9_f131 = 1;
                        } else if (this.C9_f123.b((int)var1[0], (int)1, (byte)0)) {
                           this.C9_f123.d(var1[0], 1, (byte)0);
                           this.C9_f123.C53_f775 = C67.C67_f923[4][var1[0]][6];
                           this.C9_f123.C53_f774 = 0;
                           var5 = this.C9_f123.C53_f788.size() + this.C9_f123.C53_f787.size();
                           if (this.C9_f136 >= var5) {
                              this.C9_f136 = var5 - 1;
                              ((RootComponent)this.C9_f122.currentDialog.getChildById(8)).otherChildComponent.C38_f580 = this.C9_f136;
                           }

                           if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
                              --this.C9_f135;
                              ((RootComponent)this.C9_f122.currentDialog.getChildById(8)).otherChildComponent.C38_f579 = this.C9_f135;
                           }

                           this.bs();
                           this.H();
                           this.C9_f123.b(1);
                           this.a("Thành công sử dụng đạo cụ, cũng có thời gian ngắn tránh quái hiệu quả", "Nhấn nút 5 tiếp tục");
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
                     if (!this.C9_f123.l(0) || (this.C9_f123.C53_f786 != 0 || C25.C25_f314 >= 10) && (this.C9_f123.C53_f786 <= 0 || C25.C25_f314 >= 30)) {
                        this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.a("Không có trứng có thể ấp trứng", "Nhấn nút 5 tiếp tục");
                        this.C9_f131 = 1;
                     } else if (this.C9_f123.b((int)var1[0], (int)1, (byte)0)) {
                        if (this.C9_f123.C53_f786 == 0) {
                           C25.C25_f314 = 10;
                        } else {
                           C25.C25_f314 = 30;
                        }

                        this.C9_f123.d(var1[0], 1, (byte)0);
                        var5 = this.C9_f123.C53_f788.size() + this.C9_f123.C53_f787.size();
                        if (this.C9_f136 >= var5) {
                           this.C9_f136 = var5 - 1;
                           ((RootComponent)this.C9_f122.currentDialog.getChildById(8)).otherChildComponent.C38_f580 = this.C9_f136;
                        }

                        if (this.C9_f135 > 0 && this.C9_f136 - this.C9_f135 < 4) {
                           --this.C9_f135;
                           ((RootComponent)this.C9_f122.currentDialog.getChildById(8)).otherChildComponent.C38_f579 = this.C9_f135;
                        }

                        this.bs();
                        this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.a("Thành công sử dụng, tranh thủ thời gian đi ấp trứng trứng sủng vật a!", "Nhấn nút 5 tiếp tục");
                        this.C9_f131 = 1;
                     }
                  }
                  break label206;
               }
            case 3:
               switch((var1 = (int[])this.C9_f123.C53_f791.elementAt(this.C9_f136))[0]) {
               case 0:
                  if (this.C9_f123.l(var1[0])) {
                     if (C25.B().O()) {
                        if (this.C9_f123.z() == 2) {
                           this.H();
                           this.a("Không gian không đủ, hãy thanh lý lại không gian ấp trứng", "Nhấn nút 5 tiếp tục");
                           this.C9_f131 = 1;
                        } else {
                           C25.C25_f314 = 0;
                           if (C25.B().C25_f348.C7_f60[C25.e(4, 5)] != null) {
                              C25.B().C25_f348.C7_f60[C25.e(4, 5)][15] = 4;
                              if (C25.B().C25_f290 == 4 && C25.B().C25_f291 == 5) {
                                 C25.B().C25_f348.C7_f56[15].a((byte)4);
                              }
                           }

                           this.C9_f123.k(var1[0]);
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
                  this.C9_f121.a((byte)11);
                  this.C9_f122.removeDialog("/data/ui/bag.ui");
                  break;
               case 6:
                  this.C9_f121.a((byte)12);
                  this.C9_f122.removeDialog("/data/ui/bag.ui");
                  break;
               case 7:
               case 8:
               case 9:
                  this.C9_f130 = var1[0];
                  this.C9_f121.a((byte)19);
                  this.C9_f122.removeDialog("/data/ui/bag.ui");
                  break;
               case 10:
                  this.C9_f121.a((byte)24);
                  this.C9_f122.removeDialog("/data/ui/bag.ui");
               }
            }
         } else if (this.C9_f131 == 1 || this.C9_f131 == 2) {
            if (this.C9_f131 != 2) {
               this.C9_f121.r();
               this.C9_f131 = 0;
            } else {
               if (this.C9_f123.C53_f786 == 0) {
                  byte var6 = this.h(58);
                  this.C9_f123.a((short)58);
                  if (var6 == 0) {
                     this.c("Ấp trứng tìm được #2" + C44.c((int)C67.C67_f923[0][58][0]) + "#0 để vào ba lô");
                  } else if (var6 == 1) {
                     this.c("Ấp trứng tìm được #2" + C44.c((int)C67.C67_f923[0][58][0]) + "#0 để vào ngân hàng");
                  } else {
                     this.c("Không có không gian, đã phóng sinh");
                  }
               } else {
                  var5 = GameUtils.findFirstGreaterOrEqual(new int[]{76, 52, 28, 4, 0}, GameUtils.getRandomInt(100));
                  short[] var2 = new short[]{0, 56, 58, 95, 72};
                  byte var3 = this.h(var2[var5]);

                  int var4;
                  for(var4 = 0; var4 < this.C9_f123.C53_f786 && this.C9_f123.C53_f795[var4] != var2[var5]; ++var4) {
                  }

                  if (var4 >= this.C9_f123.C53_f786) {
                     this.C9_f123.a(var2[var5]);
                  }

                  if (var3 == 0) {
                     this.c("Ấp trứng tìm được #2" + C44.c((int)C67.C67_f923[0][var2[var5]][0]) + "#0 để vào ba lô");
                  } else if (var3 == 1) {
                     this.c("Ấp trứng tìm được #2" + C44.c((int)C67.C67_f923[0][var2[var5]][0]) + "#0 để vào ngân hàng");
                  } else {
                     this.c("Không có không gian, đã phóng sinh");
                  }
               }

               this.C9_f131 = 3;
            }

            this.I();
         }
      } else if (this.C9_f131 == 0 && this.C9_f121.g(262144) && !this.j() && C44.t()) {
         if (C44.C44_f711) {
            this.C9_f125 = 2;
         } else {
            this.C9_f125 = 1;
         }

         this.C9_f121.a((byte)6);
         this.C9_f122.removeDialog("/data/ui/bag.ui");
      }

      if (this.C9_f131 == 3 && !this.j()) {
         this.C9_f121.r();
         this.br();
         this.C9_f131 = 0;
      }

      this.f();
      this.C9_f132 = true;
   }

   private byte h(int var1) {
      int[][] var2 = new int[][]{{60, 20, 0}, {75, 50, 20, 0}};
      byte var3 = -1;
      byte var4 = 0;
      if (C67.C67_f923[0][var1][4] == 5) {
         if (C67.C67_f923[0][var1][3] == 2) {
            var3 = 1;
            var4 = 2;
         } else if (C67.C67_f923[0][var1][3] == 3) {
            var3 = 0;
            var4 = 3;
         }
      }

      int var5 = C67.C67_f923[0][var1][1] * 10;
      short var6 = C67.C67_f923[1][var5][5];
      byte var7 = this.C9_f123.z();
      if (var3 == -1) {
         if (var7 == 0) {
            this.C9_f123.a(var1, 5, (short)-1, (byte)2, (short)-1, (byte)-1, new int[]{1, var5, var6});
         } else if (var7 == 1) {
            int var8 = GameUtils.getRandomInRange(C67.C67_f923[0][var1][3], C67.C67_f923[0][var1][3]);
            this.C9_f123.a(var1, 5, (short)-1, (byte)2, (byte)var8, (byte)-1, C41.b(var1, 5, var8), 0, -1, new int[]{1, var5, var6});
         }
      } else {
         byte var9 = (byte)(var4 + (byte) GameUtils.findFirstGreaterOrEqual(var2[var3], GameUtils.getRandomInt(100)));
         if (var7 == 0) {
            this.C9_f123.a(var1, 5, (short)-1, (byte)2, (short)var9, (byte)-1, new int[]{1, var5, var6});
         } else if (var7 == 1) {
            this.C9_f123.a(var1, 5, (short)-1, (byte)2, (short)var9, (byte)-1, C41.b(var1, 5, var9), 0, -1, new int[]{1, var5, var6});
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
      for(int var1 = 0; var1 < 4; ++var1) {
         if (this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().C12_f195.C17_f222 = 3;
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().C12_f195.a(260, false, (byte)-1);
         }

         if (this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195.a((int)131);
            this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var1 + 16).getComponentData().C12_f195.a(257, false, (byte)0);
         }

         if (this.C9_f123.f(var1)) {
            if (this.C9_f125 == var1) {
               this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().C12_f195.a((byte)var1, (byte)-1);
               if (this.C9_f125 == 0) {
                  this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().C12_f179 = "Lục đi điểu";
               } else if (this.C9_f125 == 1) {
                  this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().C12_f179 = "Hư không hành giả";
               } else if (this.C9_f125 == 2) {
                  this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().C12_f179 = "Hải âu";
               } else if (this.C9_f125 == 3) {
                  this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().C12_f179 = "Nham sơn long";
               }
            } else {
               this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().C12_f195.a((byte)(var1 + 8), (byte)-1);
               this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().C12_f179 = "";
            }

            if (!this.C9_f123.g(var1)) {
               this.C9_f122.currentDialog.getChildById(var1 + 16).setVisible(true);
            } else {
               this.C9_f122.currentDialog.getChildById(var1 + 16).setVisible(false);
            }
         } else {
            this.C9_f122.currentDialog.getChildById(var1 + 16).setVisible(false);
            this.C9_f122.currentDialog.getChildById(var1 + 4).getComponentData().C12_f195.a((byte)(var1 + 4), (byte)-1);
            this.C9_f122.currentDialog.getChildById(var1 + 8).getComponentData().C12_f179 = "";
         }
      }

   }

   public final void ah() {
      if (!this.j() && this.C9_f121.g(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
      } else if (!this.j() && this.C9_f121.g(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
      } else if (!this.j() && this.C9_f121.g(512)) {
         this.C9_f122.removeDialog("/data/ui/ride.ui");
         this.C9_f121.a((byte)0);
      } else if (!this.j() && this.C9_f121.g(196640)) {
         if (this.C9_f123.f(this.C9_f125)) {
            if (this.C9_f123.g(this.C9_f125)) {
               this.C9_f123.h(this.C9_f125);
               this.C9_f122.removeDialog("/data/ui/ride.ui");
               this.C9_f121.a((byte)0);
            } else {
               this.b("Nơi này không thể sử dụng sủng vật cưỡi");
            }
         } else {
            this.b("Chưa có sủng vật cưỡi này");
         }
      } else if (!this.j() && this.C9_f121.g(262144)) {
         this.C9_f122.removeDialog("/data/ui/ride.ui");
         this.C9_f121.a((byte)0);
      }

      this.f();
      this.C9_f132 = true;
   }

   public final void a(C41 var1, C41 var2) {
      this.C9_f122.showDialog("/data/ui/battle.ui", 257, this);
      this.C9_f124 = 0;
      this.C9_f129 = 0;
      this.a(var1, false);
      this.b(var2, false);
      this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "100%";
      this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "100%";
      ((C29)this.C9_f121).D();
      this.C9_f122.removeDialog("/data/ui/world.ui");
   }

   public final void b(C41 var1, C41 var2) {
      if (var1.a(var2) == 0) {
         if (var1.s() == 0) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "300%";
            this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "60%";
         } else {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "60%";
            this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "300%";
         }
      } else if (var1.a(var2) == 1) {
         if (var1.s() == 0) {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "60%";
            this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "300%";
         } else {
            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "300%";
            this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "60%";
         }
      } else {
         this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "100%";
         this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "100%";
      }
   }

   public final void a(C41 var1, C41 var2, C41 var3, int var4, int var5) {
      if (var1.a(var2) == 0) {
         if (var3.s() == 0) {
            if ((var4 *= 200 / var5) == var5 && var4 != 200) {
               var4 = 200;
            }

            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = var4 + 100 + "%";
            return;
         }

         if (var3.s() == 1) {
            if ((var4 *= 40 / var5) == var5 && var4 != 40) {
               var4 = 40;
            }

            this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = 100 - var4 + "%";
            return;
         }
      } else if (var1.a(var2) == 1) {
         if (var3.s() == 0) {
            if ((var4 *= 40 / var5) == var5 && var4 != 40) {
               var4 = 40;
            }

            this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = 100 - var4 + "%";
            return;
         }

         if (var3.s() == 1) {
            if ((var4 *= 200 / var5) == var5 && var4 != 200) {
               var4 = 200;
            }

            this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = var4 + 100 + "%";
            return;
         }
      } else {
         this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "100%";
         this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "100%";
      }

   }

   public final void a(C41 var1, C41 var2, int var3, int var4) {
      this.C9_f145 = this.C9_f146 = 0;
      if (var1.a(var2) == 0) {
         this.C9_f145 += var3 * (200 / var4);
         if (this.C9_f145 == var4 && this.C9_f145 != 200) {
            this.C9_f145 = 200;
         }

         this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = 100 + this.C9_f145 + "%";
         this.C9_f146 += var3 * (40 / var4);
         if (this.C9_f146 == var4 && this.C9_f146 != 40) {
            this.C9_f146 = 40;
         }

         this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = 100 - this.C9_f146 + "%";
      } else if (var1.a(var2) == 1) {
         this.C9_f145 += var3 * (40 / var4);
         if (this.C9_f145 == var4 && this.C9_f145 != 40) {
            this.C9_f145 = 40;
         }

         this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = 100 - this.C9_f145 + "%";
         this.C9_f146 += var3 * (200 / var4);
         if (this.C9_f146 == var4 && this.C9_f146 != 200) {
            this.C9_f146 = 200;
         }

         this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = 100 + this.C9_f146 + "%";
      } else {
         this.C9_f122.currentDialog.getChildById(59).getComponentData().C12_f179 = "100%";
         this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "100%";
      }
   }

   public final boolean a(C41 var1, boolean var2) {
      int var3 = 0;
      byte var7;
      if (this.C9_f147 == 0) {
         int var10000 = var1.O();
         var7 = 1;
         if ((var3 = Math.abs(var10000 - var1.C60_f856[var7]) / 11) <= 1) {
            var3 = 1;
         }
      }

      int var4 = var1.O();
      var7 = 1;
      short var5 = var1.C60_f856[var7];
      if (var4 != var5) {
         ++this.C9_f148;
         if (this.C9_f148 < 4) {
            if (var2) {
               this.C9_f122.currentDialog.getChildById(55).getComponentData().C12_f179 = "#P" + var1.M();
               this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "#P" + var1.N();
            } else {
               this.C9_f122.currentDialog.getChildById(55).getComponentData().C12_f179 = "#P" + var1.N();
               this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "#P" + var1.M();
            }

            return false;
         }
      }

      this.C9_f147 += var3;
      if (var2) {
         if ((var4 += this.C9_f147) >= var5) {
            var4 = var5;
         }

         var1.u(var4);
         this.C9_f122.currentDialog.getChildById(41).getComponentData().C12_f179 = "#P" + var1.M();
         this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "#P" + var1.N();
         this.C9_f122.currentDialog.getChildById(55).getComponentData().C12_f179 = "#P" + var1.N();
      } else {
         if ((var4 -= this.C9_f147) <= var5) {
            var4 = var5;
         }

         var1.u(var4);
         this.C9_f122.currentDialog.getChildById(41).getComponentData().C12_f179 = "#P" + var1.N();
         this.C9_f122.currentDialog.getChildById(55).getComponentData().C12_f179 = "#P" + var1.M();
         this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "#P" + var1.M();
      }

      DialogData var8 = this.C9_f122.currentDialog.getChildById(38).getComponentData();
      StringBuffer var10001 = (new StringBuffer()).append(var1.O()).append("/");
      var7 = 1;
      var8.C12_f179 = var10001.append(var1.C60_f855[var7]).toString();
      this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "#P" + var1.P();
      this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = var1.A() + "/" + var1.v();
      this.C9_f122.currentDialog.getChildById(12).getComponentData().C12_f179 = C44.c(var1.j((byte)0));
      this.C9_f122.currentDialog.getChildById(13).getComponentData().C12_f179 = "lv" + var1.t();
      this.C9_f122.currentDialog.getChildById(17).getComponentData().C12_f195.a(94 + var1.j((byte)1));
      if (var4 == var5) {
         this.C9_f147 = 0;
         this.C9_f148 = 0;
         this.C9_f149 = 0;
         return true;
      } else {
         return false;
      }
   }

   public final void a(C41 var1) {
      int var2;
      for(var2 = 0; var2 < 6; ++var2) {
         if (this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().C12_f195.a(325, false, (byte)0);
         }

         if (this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().C12_f195.a((int)145);
            this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().C12_f195.a(257, false, (byte)0);
         }

         this.C9_f122.currentDialog.getChildById(var2 + 43).getComponentData().C12_f195.a((int)145);
         this.C9_f122.currentDialog.getChildById(var2 + 26).getComponentData().C12_f195.a((int)0);
      }

      for(var2 = 0; var2 < 3; ++var2) {
         short[] var10002;
         if (var1.C41_f643[0][var2] != -1 && var1.C41_f641[var1.C41_f643[0][var2]][0] > 0) {
            var10002 = var1.C41_f641[var1.C41_f643[0][var2]];
            this.C9_f122.currentDialog.getChildById(43 + this.C9_f149).getComponentData().C12_f195.a(134 + var10002[0]);
            this.C9_f122.currentDialog.getChildById(26 + this.C9_f149).getComponentData().C12_f195.a(var1.C41_f643[0][var2] + 12);
            ++this.C9_f149;
         }

         if (var1.C41_f643[1][var2] != -1 && var1.C41_f642[var1.C41_f643[1][var2]][0] > 0) {
            var10002 = var1.C41_f642[var1.C41_f643[1][var2]];
            this.C9_f122.currentDialog.getChildById(43 + this.C9_f149).getComponentData().C12_f195.a(134 + var10002[0]);
            this.C9_f122.currentDialog.getChildById(26 + this.C9_f149).getComponentData().C12_f195.a(var1.C41_f643[1][var2] + 1);
            ++this.C9_f149;
         }
      }

   }

   private void g(C41 var1) {
      this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "#P" + var1.M();
      DialogData var10000 = this.C9_f122.currentDialog.getChildById(38).getComponentData();
      StringBuffer var10001 = (new StringBuffer()).append(var1.O()).append("/");
      byte var3 = 1;
      var10000.C12_f179 = var10001.append(var1.C60_f855[var3]).toString();
      this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f179 = "lv" + var1.t();
   }

   public final boolean b(C41 var1, boolean var2) {
      int var3 = 0;
      byte var7;
      if (this.C9_f147 == 0) {
         int var10000 = var1.O();
         var7 = 1;
         if ((var3 = Math.abs(var10000 - var1.C60_f856[var7]) / 11) <= 1) {
            var3 = 1;
         }
      }

      int var4 = var1.O();
      var7 = 1;
      short var5 = var1.C60_f856[var7];
      if (var4 != var5) {
         ++this.C9_f148;
         if (this.C9_f148 < 4) {
            if (var2) {
               this.C9_f122.currentDialog.getChildById(56).getComponentData().C12_f179 = "#P" + var1.M();
               this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = "#P" + var1.N();
            } else {
               this.C9_f122.currentDialog.getChildById(56).getComponentData().C12_f179 = "#P" + var1.N();
               this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = "#P" + var1.M();
            }

            return false;
         }
      }

      this.C9_f147 += var3;
      if (var2) {
         if ((var4 += this.C9_f147) >= var5) {
            var4 = var5;
         }

         var1.u(var4);
         this.C9_f122.currentDialog.getChildById(42).getComponentData().C12_f179 = "#P" + var1.M();
         this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = "#P" + var1.N();
         this.C9_f122.currentDialog.getChildById(56).getComponentData().C12_f179 = "#P" + var1.N();
      } else {
         if ((var4 -= this.C9_f147) <= var5) {
            var4 = var5;
         }

         var1.u(var4);
         this.C9_f122.currentDialog.getChildById(42).getComponentData().C12_f179 = "#P" + var1.N();
         this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = "#P" + var1.M();
         this.C9_f122.currentDialog.getChildById(56).getComponentData().C12_f179 = "#P" + var1.M();
      }

      DialogData var8 = this.C9_f122.currentDialog.getChildById(39).getComponentData();
      StringBuffer var10001 = (new StringBuffer()).append(var1.O()).append("/");
      var7 = 1;
      var8.C12_f179 = var10001.append(var1.C60_f855[var7]).toString();
      if (this.C9_f123.a((byte)var1.j((byte)1), var1.r()) == 2) {
         this.C9_f122.currentDialog.getChildById(19).getComponentData().C12_f195.a((int)101);
      } else {
         this.C9_f122.currentDialog.getChildById(19).getComponentData().C12_f195.a((int)102);
      }

      this.C9_f122.currentDialog.getChildById(15).getComponentData().C12_f179 = C44.c(var1.j((byte)0));
      this.C9_f122.currentDialog.getChildById(16).getComponentData().C12_f179 = "lv" + var1.t();
      this.C9_f122.currentDialog.getChildById(18).getComponentData().C12_f195.a(94 + var1.j((byte)1));
      if (var4 == var5) {
         this.C9_f147 = 0;
         this.C9_f148 = 0;
         this.C9_f149 = 0;
         return true;
      } else {
         return false;
      }
   }

   public final void b(C41 var1) {
      int var2;
      for(var2 = 0; var2 < 6; ++var2) {
         if (this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().C12_f195.a(325, false, (byte)0);
         }

         if (this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().C12_f195.a((int)145);
            this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().C12_f195.a(257, false, (byte)0);
         }

         this.C9_f122.currentDialog.getChildById(var2 + 49).getComponentData().C12_f195.a((int)145);
         this.C9_f122.currentDialog.getChildById(var2 + 32).getComponentData().C12_f195.a((int)0);
      }

      for(var2 = 0; var2 < 3; ++var2) {
         short[] var10002;
         if (var1.C41_f643[0][var2] != -1 && var1.C41_f641[var1.C41_f643[0][var2]][0] > 0) {
            var10002 = var1.C41_f641[var1.C41_f643[0][var2]];
            this.C9_f122.currentDialog.getChildById(49 + this.C9_f149).getComponentData().C12_f195.a(134 + var10002[0]);
            this.C9_f122.currentDialog.getChildById(32 + this.C9_f149).getComponentData().C12_f195.a(var1.C41_f643[0][var2] + 12);
            ++this.C9_f149;
         }

         if (var1.C41_f643[1][var2] != -1 && var1.C41_f642[var1.C41_f643[1][var2]][0] > 0) {
            var10002 = var1.C41_f642[var1.C41_f643[1][var2]];
            this.C9_f122.currentDialog.getChildById(49 + this.C9_f149).getComponentData().C12_f195.a(134 + var10002[0]);
            this.C9_f122.currentDialog.getChildById(32 + this.C9_f149).getComponentData().C12_f195.a(var1.C41_f643[1][var2] + 1);
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
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).additionalChildComponent.C38_f580 = this.C9_f124;
      this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(true);
   }

   public final void c(C41 var1) {
      this.C9_f131 = 0;
      this.a(var1, false);
      this.aj();
   }

   public final void d(C41 var1) {
      ((C29)this.C9_f121).q();
      if (!C44.a((int)this.C9_f124, (int)1) && this.C9_f131 == 0 && !this.j() && this.C9_f121.g(16400)) {
         this.C9_f122.currentDialog.handleAction(2);
      } else if (!C44.a((int)this.C9_f124, (int)1) && this.C9_f131 == 0 && !this.j() && this.C9_f121.g(32832)) {
         this.C9_f122.currentDialog.handleAction(3);
      } else if (!this.j() && this.C9_f121.g(196640)) {
         switch(this.C9_f124) {
         case 0:
            this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
            this.C9_f121.a((byte)3);
            break;
         case 1:
            if (((C29)this.C9_f121).C29_f398 == 2) {
               this.b("Trận chiến này không cho bắt sủng vật");
            } else if (this.C9_f123.z() == 2) {
               this.b("Không gian không đủ, không cách nào bắt được");
            } else {
               this.C9_f125 = 0;
               this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
               ((C29)this.C9_f121).r();
               this.C9_f121.a((byte)21);
            }
            break;
         case 2:
            if (this.C9_f131 == 0) {
               if (var1.p(2)) {
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Trạng thái bị quấn, không thể sử dụng đạo cụ", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 1;
               } else {
                  this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
                  this.C9_f121.a((byte)4);
               }
            } else {
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.C9_f131 = 0;
            }
            break;
         case 3:
            if (this.C9_f131 == 0) {
               if (var1.p(2)) {
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Trạng thái bị quấn, không thể đổi sủng vật", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 1;
               } else {
                  this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
                  ((C29)this.C9_f121).C29_f407 = ((C29)this.C9_f121).C29_f404[((C29)this.C9_f121).C29_f410];
                  C29.B().C29_f415 = true;
                  this.C9_f121.a((byte)5);
               }
            } else {
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.C9_f131 = 0;
            }
            break;
         case 4:
            this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
            this.C9_f121.a((byte)11);
            break;
         case 5:
            if (this.C9_f131 == 0) {
               if (var1.p(2)) {
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Trạng thái bị quấn, không thể chạy trốn", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 1;
               } else if (((C29)this.C9_f121).C29_f398 <= 0 && C7.C7_f71) {
                  boolean var3 = false;
                  if (((C29)this.C9_f121).C29_f408.t() > ((C29)this.C9_f121).C29_f402[0].t()) {
                     var3 = true;
                  } else if (((C29)this.C9_f121).C29_f408.t() == ((C29)this.C9_f121).C29_f402[0].t()) {
                     if (GameUtils.getRandomInt(100) <= 95) {
                        var3 = true;
                     }
                  } else {
                     int var2 = ((C29)this.C9_f121).C29_f402[0].t() - ((C29)this.C9_f121).C29_f408.t();
                     if ((var2 = 95 - var2 * 10) <= 15) {
                        var2 = 15;
                     }

                     if (GameUtils.getRandomInt(100) < var2) {
                        var3 = true;
                     }
                  }

                  if (var3) {
                     this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(false);
                     C55.B().a((byte)10);
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
            ((C29)this.C9_f121).C29_f408.C41_f667 = true;
            ++((C29)this.C9_f121).C29_f410;
            this.C9_f121.a((byte)1);
         } else {
            this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(true);
         }

         this.C9_f131 = 0;
      }

   }

   public final void e(C41 var1) {
      this.C9_f122.showDialog("/data/ui/choiceskill.ui", 257, this);
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = var1.F();
      if (this.C9_f129 >= var1.F()) {
         this.C9_f129 = var1.F() - 1;
      }

      if (var1.F() > 5) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(-1);
      }

      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Sử dụng";
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f129;
      this.h(var1);
      this.C9_f131 = 0;
   }

   private void h(C41 var1) {
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;
      int var2 = var1.F();

      for(int var3 = 0; var3 < 5; ++var3) {
         if (var3 >= var2) {
            this.C9_f122.currentDialog.getChildById(13 + var3 * 5).getComponentData().C12_f179 = "";
            this.C9_f122.currentDialog.getChildById(14 + var3 * 5).getComponentData().C12_f179 = "";
         } else {
            this.C9_f122.currentDialog.getChildById(13 + var3 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[1][var1.t(this.C9_f135 + var3)][1]);
            this.C9_f122.currentDialog.getChildById(14 + var3 * 5).getComponentData().C12_f179 = var1.C41_f645[this.C9_f135 + var3] + "/" + C67.C67_f923[1][var1.t(this.C9_f135 + var3)][5];
         }
      }

      this.i(var1.C41_f646[this.C9_f129]);
      this.C9_f122.currentDialog.getChildById(51).setOffsetY(98 + this.C9_f136 * 80 / var2, this.C9_f122.currentDialog.getRootComponent());
   }

   private void i(int var1) {
      this.C9_f122.currentDialog.getChildById(53).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[1][var1][2]);
   }

   public final void f(C41 var1) {
      if (this.aB()) {
         if (this.C9_f131 == 0 && this.C9_f121.g(4100)) {
            this.C9_f122.currentDialog.handleAction(0);
            this.h(var1);
         } else if (this.C9_f131 == 0 && this.C9_f121.g(8448)) {
            this.C9_f122.currentDialog.handleAction(1);
            this.h(var1);
         } else if (this.C9_f121.g(196640)) {
            if (this.C9_f131 == 0) {
               if (var1.s(this.C9_f129)) {
                  this.C9_f122.removeDialog("/data/ui/choiceskill.ui");
                  ((C29)this.C9_f121).d(var1.C41_f646[this.C9_f129]);
                  int var10000 = ((C29)this.C9_f121).C29_f397;
                  ((C29)this.C9_f121).getClass();
                  if (var10000 == 0) {
                     ((C29)this.C9_f121).G();
                  } else {
                     this.C9_f121.a((byte)6);
                  }
               } else {
                  this.C9_f131 = 1;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Kỹ năng giá trị chưa đủ", "Nhấn nút 5 tiếp tục");
               }
            } else {
               this.C9_f131 = 0;
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               if (var1.p(2) && var1.s() == 0) {
                  boolean var2 = false;

                  for(int var3 = 0; var3 < var1.C41_f645.length; ++var3) {
                     if (var1.C41_f645[var3] != 0) {
                        var2 = true;
                     }
                  }

                  if (!var2) {
                     this.C9_f122.removeDialog("/data/ui/choiceskill.ui");
                     this.c("Không có kỹ năng giá trị, không cách nào chiến đấu");
                     ((C29)this.C9_f121).F();
                  }
               }
            }
         } else if (this.C9_f121.g(262144) && this.C9_f131 == 0) {
            this.C9_f122.removeDialog("/data/ui/choiceskill.ui");
            this.C9_f121.a((byte)20);
         }
      }

      this.g();
   }

   public final void ak() {
      this.C9_f131 = 0;
      this.C9_f122.showDialog("/data/ui/choice.ui", 257, this);
      this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "Pokemon ball";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "Tỉ lệ bắt";
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Sử dụng";
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f125;
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(0);
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = this.C9_f123.C53_f788.size();

      for(int var1 = 0; var1 < this.C9_f123.C53_f788.size(); ++var1) {
         int[] var2 = (int[])this.C9_f123.C53_f788.elementAt(var1);
         if (this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.C17_f222 = 2;
            this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.a(258, false, (byte)-1);
         }

         this.C9_f122.currentDialog.getChildById(var1 + 54).getComponentData().C12_f195.a((int)C67.C67_f923[4][var2[0]][1]);
         this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[4][var2[0]][0]);
         this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = ((C29)this.C9_f121).m(var2[0]) + "%";
      }

      this.C9_f122.currentDialog.getChildById(59).setVisible(false);
      this.C9_f122.currentDialog.getChildById(60).setVisible(false);
      this.bw();
   }

   private void bw() {
      int[] var1 = (int[])this.C9_f123.C53_f788.elementAt(this.C9_f125);
      this.C9_f122.currentDialog.getChildById(53).getComponentData().C12_f179 = "Số lượng: " + var1[1] + " cái ";
   }

   public final void al() {
      this.C9_f121.q();
      if (!C44.a((int)this.C9_f125, (int)0) && this.C9_f131 == 0 && this.C9_f121.g(4100) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(0);
         this.bw();
      } else if (!C44.a((int)this.C9_f125, (int)0) && this.C9_f131 == 0 && this.C9_f121.g(8448) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(1);
         this.bw();
      } else if (this.C9_f121.g(196640) && !this.j() && C44.s()) {
         if (C44.p() && !C44.a((int)this.C9_f125, (int)0)) {
            return;
         }

         C7.C7_f72 = true;
         if (this.C9_f131 == 0) {
            int[] var1 = (int[])this.C9_f123.C53_f788.elementAt(this.C9_f125);
            if (!this.C9_f123.b((int)var1[0], (int)1, (byte)0)) {
               this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
               this.a("Số lượng Pokemon ball không đủ", "Nhấn nút 5 tiếp tục");
               this.C9_f131 = 1;
            } else {
               this.C9_f131 = 0;
               C29.C29_f444 = (byte)var1[0];
               this.C9_f121.r();
               this.C9_f123.d(var1[0], 1, (byte)0);
               this.C9_f121.a((byte)17);
               this.C9_f122.removeDialog("/data/ui/choice.ui");
            }
         } else if (this.C9_f131 == 1) {
            if (C44.C44_f711 && ((int[])this.C9_f123.C53_f788.elementAt(this.C9_f125))[0] == 0) {
               this.C9_f122.removeDialog("/data/ui/choice.ui");
               this.C9_f121.a((byte)101);
            }

            this.C9_f131 = 0;
            this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
         }
      } else if (C7.t() && this.C9_f131 == 0 && this.C9_f121.g(262144) && !this.j()) {
         this.C9_f122.removeDialog("/data/ui/choice.ui");
         this.C9_f121.a((byte)20);
      }

      this.g();
   }

   public final void am() {
      this.C9_f130 = 0;
      this.C9_f131 = 0;
      this.C9_f125 = 0;
      this.C9_f122.showDialog("/data/ui/choice.ui", 257, this);
      this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "Đạo cụ";
      this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "Số lượng";
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Sử dụng";
      this.C9_f122.currentDialog.getChildById(59).setVisible(false);
      this.C9_f122.currentDialog.getChildById(60).setVisible(false);
      this.bi();
   }

   public final void an() {
      if (this.C9_f131 == 0 && this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f131 == 0 && this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else {
         if (this.C9_f121.g(196640)) {
            if (this.C9_f123.C53_f787.size() <= 0) {
               return;
            }

            this.C9_f130 = ((int[])this.C9_f123.C53_f787.elementAt(this.C9_f136))[0];
            if (this.C9_f131 == 0) {
               switch(C67.C67_f923[4][this.C9_f130][5]) {
               case 7:
               case 8:
               case 9:
               case 10:
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Trong chiến đấu không thể sử dụng", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 1;
                  return;
               default:
                  this.C9_f121.a((byte)16);
                  this.C9_f122.removeDialog("/data/ui/choice.ui");
                  return;
               }
            }

            if (this.C9_f131 == 1) {
               this.C9_f122.removeDialog("/data/ui/msgwarm.ui");
               this.C9_f131 = 0;
               return;
            }
         } else if (this.C9_f131 == 0 && this.C9_f121.g(262144)) {
            this.C9_f122.removeDialog("/data/ui/choice.ui");
            this.C9_f121.a((byte)20);
         }

      }
   }

   private void bx() {
      if (this.C9_f131 == 0) {
         this.C9_f131 = 1;
         int var1;
         if (this.C9_f121 instanceof C25) {
            var1 = this.C9_f123.C53_f777[this.C9_f126].x(this.C9_f130);
         } else {
            var1 = this.C9_f123.C53_f777[((C29)this.C9_f121).C29_f405[this.C9_f126]].x(this.C9_f130);
         }

         switch(var1) {
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
            if (this.C9_f123.b((int)this.C9_f130, (int)1, (byte)0)) {
               if (this.C9_f121 instanceof C25) {
                  this.C9_f123.C53_f777[this.C9_f126].w(this.C9_f130);
               } else {
                  ((C29)this.C9_f121).C29_f408.C41_f667 = true;
                  this.C9_f123.C53_f777[((C29)this.C9_f121).C29_f405[this.C9_f126]].w(this.C9_f130);
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
            if (this.C9_f121 instanceof C25) {
               this.C9_f121.a((byte)8);
               return;
            }

            if (C29.B().C29_f408.equals(((C29)this.C9_f121).n(this.C9_f126))) {
               this.g(((C29)this.C9_f121).o(this.C9_f126));
            }

            if (((C29)this.C9_f121).C29_f408.C41_f667) {
               ++((C29)this.C9_f121).C29_f410;
               ((C29)this.C9_f121).a((byte)1);
               return;
            }

            ((C29)this.C9_f121).a((byte)4);
         }

      }
   }

   public final void ao() {
      if (this.C9_f131 == 0 && this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f131 == 0 && this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.g(196640)) {
         this.bx();
      } else {
         if (this.C9_f131 == 0 && this.C9_f121.g(262144)) {
            if (this.C9_f150) {
               if (C29.B().C29_f408.equals(((C29)this.C9_f121).n(this.C9_f126))) {
                  this.g(((C29)this.C9_f121).o(this.C9_f126));
               }

               if (((C29)this.C9_f121).C29_f408.C41_f667) {
                  ++((C29)this.C9_f121).C29_f410;
                  ((C29)this.C9_f121).a((byte)1);
               } else {
                  ((C29)this.C9_f121).a((byte)4);
               }

               this.C9_f122.removeDialog("/data/ui/petstate.ui");
               return;
            }

            ((C29)this.C9_f121).a((byte)4);
            this.C9_f122.removeDialog("/data/ui/petstate.ui");
         }

      }
   }

   public final void b(int var1, int var2) {
      if (this.C9_f138 >= C29.C29_f413.size()) {
         this.C9_f138 = 0;
         C55.B().a((byte)10);
      } else {
         label21:
         while(true) {
            C41 var3 = (C41)C29.C29_f413.elementAt(this.C9_f138);

            while(this.C9_f138 < C29.C29_f413.size() && var3.u()) {
               ++this.C9_f138;
               if (this.C9_f138 < C29.C29_f413.size()) {
                  continue label21;
               }
            }

            this.C9_f151 = var1;
            this.C9_f152 = var2;
            var3.c();
            var3.b(var1, var2);
            this.C9_f137 = 0;
            return;
         }
      }
   }

   public final void ap() {
      if (this.C9_f138 >= C29.C29_f413.size()) {
         this.C9_f138 = 0;
         C55.B().a((byte)10);
      } else {
         if (this.C9_f137 <= 0) {
            this.C9_f153 += 8;
         }

         C41 var1;
         int var2 = (var1 = (C41)C29.C29_f413.elementAt(this.C9_f138)).B() + this.C9_f153;
         int var3 = var1.v();
         int var4 = var1.A();
         if (var2 >= var3) {
            var2 = var3;
         } else if (var2 >= var4) {
            var2 = var4;
         }

         if (this.C9_f121.g(196640)) {
            if (var4 >= var3) {
               this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = var3 + "/" + var3;
               this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "#P" + var1.v(var3);
               var1.j((int)0);
               this.C9_f137 = 0;
               ((C29)this.C9_f121).a((byte)22);
            } else if (var2 < var4) {
               this.C9_f153 = 0;
               var1.j(var4);
               this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = var4 + "/" + var1.v();
               this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "#P" + var1.v(var4);
            } else {
               this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = var4 + "/" + var1.v();
               this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "#P" + var1.v(var4);
               var1.j(var2);
               ++this.C9_f138;

               while(this.C9_f138 < C29.C29_f413.size() && ((C41)C29.C29_f413.elementAt(this.C9_f138)).u()) {
                  ++this.C9_f138;
               }

               if (this.C9_f138 >= C29.C29_f413.size()) {
                  this.C9_f138 = 0;
                  C55.B().a((byte)10);
               } else {
                  ((C41)C29.C29_f413.elementAt(this.C9_f138)).b(this.C9_f151, this.C9_f152);
               }

               this.C9_f137 = 0;
               this.C9_f153 = 0;
            }
         } else {
            this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = var2 + "/" + var1.v();
            this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "#P" + var1.v(var2);
            this.C9_f122.currentDialog.getChildById(12).getComponentData().C12_f179 = C44.c(var1.j((byte)0));
            this.C9_f122.currentDialog.getChildById(13).getComponentData().C12_f179 = "lv" + var1.t();
            this.C9_f122.currentDialog.getChildById(17).getComponentData().C12_f195.a(94 + var1.j((byte)1));
            if (var2 >= var3) {
               var1.j((int)0);
               ((C29)this.C9_f121).a((byte)22);
            } else {
               if (var2 < var4) {
                  return;
               }

               ++this.C9_f137;
               var1.j(var2);
               if (this.C9_f137 >= 10) {
                  ++this.C9_f138;

                  while(this.C9_f138 < C29.C29_f413.size() && ((C41)C29.C29_f413.elementAt(this.C9_f138)).u()) {
                     ++this.C9_f138;
                  }

                  if (this.C9_f138 >= C29.C29_f413.size()) {
                     this.C9_f138 = 0;
                     C55.B().a((byte)10);
                  } else {
                     ((C41)C29.C29_f413.elementAt(this.C9_f138)).b(this.C9_f151, this.C9_f152);
                  }

                  this.C9_f137 = 0;
               }
            }

            this.C9_f153 = 0;
         }
      }
   }

   public final void aq() {
      C41 var1 = (C41)C29.C29_f413.elementAt(this.C9_f138);
      String[] var2 = new String[4];

      int var3;
      byte var5;
      for(var3 = 0; var3 < 4; ++var3) {
         StringBuffer var10002 = new StringBuffer();
         var5 = (byte)(var3 + 1);
         var2[var3] = var10002.append(var1.C60_f855[var5]).toString();
      }

      var1.w();
      this.g(var1);
      this.C9_f122.showDialog("/data/ui/levelUp.ui", 257, this);

      for(var3 = 0; var3 < 4; ++var3) {
         this.C9_f122.currentDialog.getChildById(var3 + 19).getComponentData().C12_f179 = var2[var3];
      }

      if (var1.F() < 5 && var1.F() < var1.t() / 10 + 1) {
         this.C9_f139 = var1.G();
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f179 = "Có thể học tập kỹ năng mới";
      } else {
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f179 = "";
      }

      this.C9_f122.currentDialog.getChildById(38).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[0][var1.r()][0]);
      this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = "" + var1.t();
      if (this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.C17_f222 = 3;
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.a((int)0);
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.a(var1.C41_f655, false, (byte)-1);
      }

      for(var3 = 0; var3 < 4; ++var3) {
         DialogData var10000 = this.C9_f122.currentDialog.getChildById(var3 + 31).getComponentData();
         StringBuffer var10001 = new StringBuffer();
         var5 = (byte)(var3 + 1);
         var10000.C12_f179 = var10001.append(var1.C60_f855[var5]).toString();
      }

   }

   public final void ar() {
      ++this.C9_f154;
      if (this.C9_f154 > 40) {
         this.C9_f154 = 0;
         if (this.C9_f139 != null) {
            ((C29)this.C9_f121).a((byte)23);
         } else if (this.C9_f138 + 1 >= C29.C29_f413.size()) {
            if (((C41)C29.C29_f413.elementAt(this.C9_f138)).A() > 0) {
               this.C9_f121.a((byte)8);
            } else {
               this.C9_f138 = 0;
               C55.B().a((byte)10);
            }

            this.C9_f122.removeDialog("/data/ui/levelUp.ui");
         } else {
            this.C9_f121.a((byte)8);
            this.C9_f122.removeDialog("/data/ui/levelUp.ui");
         }
      }

      if (this.C9_f121.g(196640)) {
         this.C9_f154 = 0;
         if (this.C9_f139 != null) {
            this.C9_f121.a((byte)23);
            return;
         }

         if (this.C9_f138 + 1 >= C29.C29_f413.size()) {
            if (((C41)C29.C29_f413.elementAt(this.C9_f138)).A() > 0) {
               this.C9_f121.a((byte)8);
            } else {
               this.C9_f138 = 0;
               C55.B().a((byte)10);
            }

            this.C9_f122.removeDialog("/data/ui/levelUp.ui");
            return;
         }

         this.C9_f121.a((byte)8);
         this.C9_f122.removeDialog("/data/ui/levelUp.ui");
      }

   }

   public final void as() {
      this.C9_f122.showDialog("/data/ui/choiceskill.ui", 257, this);
      this.C9_f122.removeDialog("/data/ui/levelUp.ui");
      this.C9_f125 = 0;
      this.C9_f131 = 0;
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = this.C9_f139.length;
      if (this.C9_f139.length > 5) {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
      } else {
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(-1);
      }

      if (this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195.C17_f222 = 3;
         this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195.a((int)0);
         this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195.a(257, false, (byte)-1);
      }

      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195.a((byte)11, (byte)-1);
      this.C9_f122.currentDialog.getChildById(6).setVisible(false);
      this.by();
      if (!C25.C25_f332) {
         this.b("Có thể nhấn #1nút mềm trái#0 để học tập kỹ năng");
         C25.C25_f332 = true;
      }

   }

   private void by() {
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;

      for(int var1 = 0; var1 < 5; ++var1) {
         if (var1 >= this.C9_f139.length) {
            this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().C12_f179 = "";
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = "";
         } else {
            this.C9_f122.currentDialog.getChildById(13 + var1 * 5).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[1][this.C9_f139[this.C9_f135 + var1]][1]);
            this.C9_f122.currentDialog.getChildById(14 + var1 * 5).getComponentData().C12_f179 = "" + C67.C67_f923[1][this.C9_f139[this.C9_f135 + var1]][5];
         }
      }

      this.i(this.C9_f139[this.C9_f136]);
      this.C9_f122.currentDialog.getChildById(51).setOffsetY(98 + this.C9_f136 * 62 / this.C9_f139.length, this.C9_f122.currentDialog.getRootComponent());
   }

   public final void at() {
      if (!this.j() && this.C9_f121.g(4100) && this.C9_f131 == 0) {
         this.C9_f122.currentDialog.handleAction(0);
         this.by();
      } else if (!this.j() && this.C9_f121.g(8448) && this.C9_f131 == 0) {
         this.C9_f122.currentDialog.handleAction(1);
         this.by();
      } else if (!this.j() && this.C9_f131 == 0 && (this.C9_f121.g(131072) || this.C9_f121.a(40, 228, 45, 20)) || this.C9_f131 == 1 && this.C9_f121.g(196640)) {
         if (this.C9_f131 == 0) {
            this.C9_f131 = 1;
            this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
            this.a("Học tập" + C44.c((int)C67.C67_f923[1][this.C9_f139[this.C9_f125]][1]), "Nhấn nút 5 tiếp tục");
         } else if (this.C9_f131 == 1) {
            C41 var1;
            (var1 = (C41)C29.C29_f413.elementAt(this.C9_f138)).g((byte)this.C9_f139[this.C9_f136]);
            this.C9_f139 = null;
            if (this.C9_f138 + 1 >= C29.C29_f413.size() && var1.A() <= 0) {
               this.C9_f138 = 0;
               C55.B().a((byte)10);
            } else {
               this.C9_f121.a((byte)8);
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
            if (C25.C25_f333.size() <= 0) {
               this.C9_f121.a((byte)14);
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
               C25.C25_f335 = 2;
               C44 var10000 = this.C9_f121;
               C25.F();
               if (((C25)this.C9_f121).H()) {
                  this.a("Lưu thành công");
                  this.C9_f131 = 7;
               }
            } else if (this.C9_f131 == 7) {
               this.C9_f122.removeDialog("/data/ui/msgtip.ui");
               this.C9_f131 = 0;
               if (this.C9_f121.C44_f699 == 14) {
                  this.C9_f121.a((byte)14);
               } else {
                  this.C9_f121.a((byte)0);
               }
               break label93;
            }

            if (!this.j() && this.C9_f121.g(4100) && this.C9_f131 == 3) {
               this.C9_f122.currentDialog.handleAction(0);
               this.by();
            } else if (!this.j() && this.C9_f121.g(8448) && this.C9_f131 == 3) {
               this.C9_f122.currentDialog.handleAction(1);
               this.by();
            } else if (!this.j() && this.C9_f131 == 3 && (this.C9_f121.g(131072) || this.C9_f121.a(40, 228, 45, 20)) || this.C9_f131 == 4 && this.C9_f121.g(196640)) {
               if (this.C9_f131 == 3) {
                  this.C9_f131 = 4;
                  this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                  this.a("Học tập" + C44.c((int)C67.C67_f923[1][this.C9_f139[this.C9_f136]][1]), "Nhấn nút 5 tiếp tục");
               } else if (this.C9_f131 == 4) {
                  ((C41)C25.C25_f333.elementAt(this.C9_f138)).g((byte)this.C9_f139[this.C9_f136]);
                  this.C9_f139 = null;
                  ++this.C9_f138;
                  if (this.C9_f138 >= C25.C25_f333.size()) {
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
      } else if (this.C9_f121.g(196640)) {
         this.C9_f122.showDialog("/data/ui/choiceskill.ui", 257, this);
         this.C9_f122.removeDialog("/data/ui/levelUp.ui");
         this.C9_f125 = 0;
         this.C9_f131 = 3;
         ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = this.C9_f139.length;
         if (this.C9_f139.length > 5) {
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(1);
         } else {
            ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(-1);
         }

         if (this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195.C17_f222 = 3;
            this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195.a((int)0);
            this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195.a(257, false, (byte)-1);
         }

         this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f195.a((byte)11, (byte)-1);
         this.C9_f122.currentDialog.getChildById(6).setVisible(false);
         this.by();
         this.C9_f132 = true;
      }

      this.f();
   }

   private void bz() {
      this.C9_f131 = 2;
      C41 var1 = (C41)C25.C25_f333.elementAt(this.C9_f138);
      this.C9_f122.showDialog("/data/ui/levelUp.ui", 257, this);

      int var2;
      for(var2 = 0; var2 < 4; ++var2) {
         this.C9_f122.currentDialog.getChildById(var2 + 19).getComponentData().C12_f179 = "" + var1.i((int)((byte)(var2 + 1 - 1)));
      }

      if (var1.F() < 5 && var1.F() < var1.t() / 10 + 1) {
         this.C9_f139 = var1.G();
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f179 = "Nhấn nút 5 học tập kỹ năng mới";
      } else {
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f179 = "";
      }

      this.C9_f122.currentDialog.getChildById(38).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[0][var1.r()][0]);
      this.C9_f122.currentDialog.getChildById(40).getComponentData().C12_f179 = "" + var1.t();
      if (this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.C17_f222 = 3;
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.a((int)0);
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f195.a(var1.C41_f655, false, (byte)-1);
      }

      for(var2 = 0; var2 < 4; ++var2) {
         DialogData var10000 = this.C9_f122.currentDialog.getChildById(var2 + 31).getComponentData();
         StringBuffer var10001 = new StringBuffer();
         byte var4 = (byte)(var2 + 1);
         var10000.C12_f179 = var10001.append(var1.C60_f855[var4]).toString();
      }

   }

   public final void aw() {
      this.C9_f122.showDialog("/data/ui/npcEnemy.ui", 296, this);
      if (this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.C17_f222 = 2;
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a(296, false, (byte)0);
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a((int)0);
      }

      this.C9_f122.currentDialog.getChildById(36).setVisible(false);
   }

   private void a(int var1, int var2, int var3) {
      if (var3 != -1 && this.C9_f122.currentDialog.getChildById(var3).getComponentData().C12_f195 != null) {
         this.C9_f122.currentDialog.getChildById(var3).setVisible(false);
      }

      if (this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.C17_f222 = 2;
         this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.a(296, false, (byte)0);
         this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.a((int)0);
      }

      this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.a(var2);
   }

   public final void c(int var1, int var2) {
      switch(var1) {
      case 0:
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a(var2);
         return;
      case 1:
         for(var1 = 2; var1 < 4; ++var1) {
            if (this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.a((int)0);
            }

            if (var1 % 2 == 1) {
               this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.a(0, false, (byte)-1);
            } else if (C25.C25_f318 == -1) {
               if (C25.C25_f319 == -1) {
                  this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.a(C25.B().C25_f287[8].C20_f261.C62_f882, false, (byte)-1);
               } else {
                  this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.a(C25.B().C25_f287[C25.C25_f319].C20_f261.C62_f882, false, (byte)-1);
               }
            } else {
               this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.a(C25.B().C25_f287[C25.C25_f318].C20_f261.C62_f882, false, (byte)-1);
            }

            this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195.a((int)1);
         }

         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a(var2);
         return;
      case 2:
         for(var1 = 2; var1 < 4; ++var1) {
            if (this.C9_f122.currentDialog.getChildById(var1).getComponentData().C12_f195 != null) {
               this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
            }

            if (this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195.a((int)0);
            }

            if (var1 % 2 == 1) {
               this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195.a(0, false, (byte)-1);
            } else if (C25.C25_f318 == -1) {
               if (C25.C25_f319 == -1) {
                  this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195.a(C25.B().C25_f287[8].C20_f261.C62_f882, false, (byte)-1);
               } else {
                  this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195.a(C25.B().C25_f287[C25.C25_f319].C20_f261.C62_f882, false, (byte)-1);
               }
            } else {
               this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195.a(C25.B().C25_f287[C25.C25_f318].C20_f261.C62_f882, false, (byte)-1);
            }

            this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195.a((int)1);
         }

         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a(var2);
         return;
      case 3:
         for(var1 = 2; var1 < 4; ++var1) {
            if (this.C9_f122.currentDialog.getChildById(var1 + 32).getComponentData().C12_f195 != null) {
               this.C9_f122.currentDialog.getChildById(var1 + 32).setVisible(false);
            }

            if (this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().C12_f195.C17_f222 = 2;
               this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().C12_f195.a((int)0);
            }

            if (var1 % 2 == 1) {
               this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().C12_f195.a(0, false, (byte)-1);
            } else if (C25.C25_f318 == -1) {
               if (C25.C25_f319 == -1) {
                  this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().C12_f195.a(C25.B().C25_f287[8].C20_f261.C62_f882, false, (byte)-1);
               } else {
                  this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().C12_f195.a(C25.B().C25_f287[C25.C25_f319].C20_f261.C62_f882, false, (byte)-1);
               }
            } else {
               this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().C12_f195.a(C25.B().C25_f287[C25.C25_f318].C20_f261.C62_f882, false, (byte)-1);
            }

            this.C9_f122.currentDialog.getChildById(var1 + 2).getComponentData().C12_f195.a((int)1);
         }

         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a(var2);
         this.C9_f155 = C29.B().H();
         this.C9_f156 = this.C9_f123.C53_f778;
         if (var2 - 3 < this.C9_f155) {
            this.a((int)6, 6, -1);
         }

         if (var2 - 3 < this.C9_f156) {
            this.a((int)18, 6, -1);
            return;
         }
         break;
      case 4:
         if (var2 - 3 < this.C9_f155) {
            this.a((int)(6 + (var2 - 3 << 1)), 6, 6 + (var2 - 4 << 1));
         } else {
            this.a((int)(6 + (var2 - 3 << 1)), 5, 6 + (var2 - 4 << 1));
         }

         if (var2 - 4 < this.C9_f155) {
            this.a((int)(7 + (var2 - 4 << 1)), 6, 6 + (var2 - 4 << 1));
         } else {
            this.a((int)(7 + (var2 - 4 << 1)), 5, 6 + (var2 - 4 << 1));
         }

         if (var2 - 4 < this.C9_f156) {
            this.a((int)(19 + (var2 - 4 << 1)), 6, 18 + (var2 - 4 << 1));
         } else {
            this.a((int)(19 + (var2 - 4 << 1)), 5, 18 + (var2 - 4 << 1));
         }

         if (var2 - 3 < this.C9_f156) {
            this.a((int)(18 + (var2 - 3 << 1)), 6, 18 + (var2 - 4 << 1));
            return;
         }

         this.a((int)(18 + (var2 - 3 << 1)), 5, 18 + (var2 - 4 << 1));
         return;
      case 5:
         if (var2 - 4 < this.C9_f155) {
            this.a((int)(7 + (var2 - 4 << 1)), 6, 6 + (var2 - 4 << 1));
         } else {
            this.a((int)(7 + (var2 - 4 << 1)), 5, 6 + (var2 - 4 << 1));
         }

         if (var2 - 4 < this.C9_f156) {
            this.a((int)(19 + (var2 - 4 << 1)), 6, 18 + (var2 - 4 << 1));
            return;
         }

         this.a((int)(19 + (var2 - 4 << 1)), 5, 18 + (var2 - 4 << 1));
         return;
      case 6:
         this.a((int)30, 8, -1);
         this.a((int)31, 7, -1);
         return;
      case 7:
         this.a((int)32, 8, 30);
         this.a((int)33, 7, 31);
         return;
      case 8:
         this.C9_f122.currentDialog.getChildById(36).setVisible(true);
         return;
      case 9:
         this.C9_f122.currentDialog.getChildById(36).setVisible(false);
         return;
      case 10:
         this.a((int)1, 4, 32);
         this.a((int)1, 4, 33);

         for(var1 = 4; var1 < 6; ++var1) {
            this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
         }

         for(var1 = 7; var1 < 19; var1 += 2) {
            this.C9_f122.currentDialog.getChildById(var1).setOffsetX(172 + 17 * (var1 - 7) / 2, this.C9_f122.currentDialog.getRootComponent());
            this.C9_f122.currentDialog.getChildById(var1 + 12).setOffsetX(-30 + 17 * (var1 - 7) / 2, this.C9_f122.currentDialog.getRootComponent());
         }

         return;
      case 11:
         for(var1 = 4; var1 < 6; ++var1) {
            this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
         }

         for(var1 = 7; var1 < 19; var1 += 2) {
            this.C9_f122.currentDialog.getChildById(var1).setVisible(false);
            this.C9_f122.currentDialog.getChildById(var1 + 12).setVisible(false);
         }

         this.a((int)1, 0, -1);
      }

   }

   private void e(String var1) {
      if (this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a((int)0);
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.C17_f222 = 3;
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a(257, false, (byte)-2);
      }

      this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a((byte)9, (byte)-2);
      this.C9_f122.currentDialog.getChildById(2).getComponentData().C12_f179 = var1;
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
      if (this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a((int)0);
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.C17_f222 = 3;
         this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a(257, false, (byte)-2);
      }

      this.C9_f122.currentDialog.getChildById(1).getComponentData().C12_f195.a((byte)10, (byte)-2);
      this.C9_f122.currentDialog.getChildById(2).getComponentData().C12_f179 = var1;
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
      switch(this.C9_f126) {
      case 0:
         var1 = "Tùy thời mua sắm các loại đạo cụ, già trẻ không gạt.";
         break;
      case 1:
         var2 = new int[]{2, 1, 2};
         var1 = C44.c((int)602) + C44.a(604, (int[])var2);
         break;
      case 2:
         var2 = new int[]{2, 1, 2};
         var1 = C44.c((int)603) + C44.a(604, (int[])var2);
         break;
      case 3:
         var2 = new int[]{2, 1, 2};
         var1 = C44.c((int)601) + C44.a(604, (int[])var2);
      }

      this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = var1;
      if (this.C9_f126 > 0) {
         this.C9_f121.c((byte)0);
         this.bB();
      }

   }

   private void bB() {
      switch(this.C9_f126) {
      case 1:
         this.C9_f121.b((byte)3);
         return;
      case 2:
         this.C9_f121.b((byte)4);
         return;
      case 3:
         this.C9_f121.b((byte)2);
      default:
      }
   }

   public final void aD() {
      switch(this.C9_f126) {
      case 0:
         if (this.C9_f121.g(4100) && this.C9_f131 == 0) {
            this.C9_f122.currentDialog.handleAction(0);
            this.bA();
            return;
         }

         if (this.C9_f121.g(8448) && this.C9_f131 == 0) {
            this.C9_f122.currentDialog.handleAction(1);
            this.bA();
            return;
         }

         if (this.C9_f121.g(196640)) {
            this.C9_f121.a((byte)26);
            this.C9_f122.removeDialog("/data/ui/bodyShop.ui");
            return;
         }

         if (this.C9_f121.g(786432)) {
            this.C9_f125 = 0;
            this.C9_f121.a((byte)6);
            this.C9_f122.removeDialog("/data/ui/bodyShop.ui");
            return;
         }
         break;
      default:
         switch(this.C9_f121.x()) {
         case 0:
            if (this.C9_f121.g(4100) && this.C9_f131 == 0) {
               this.C9_f122.currentDialog.handleAction(0);
               this.bA();
               return;
            }

            if (this.C9_f121.g(8448) && this.C9_f131 == 0) {
               this.C9_f122.currentDialog.handleAction(1);
               this.bA();
               return;
            }

            if ((this.C9_f131 != 0 || !this.C9_f121.g(131072)) && (this.C9_f131 != 1 || !this.C9_f121.g(65568))) {
               if (this.C9_f121.g(786432) && this.C9_f131 == 0) {
                  this.C9_f125 = 0;
                  this.C9_f121.a((byte)6);
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
               if (this.C9_f121.v() == 3) {
                  if (C25.C25_f336 != null) {
                     C25.C25_f336.removeAllElements();
                  }

                  int var1;
                  for(var1 = 0; var1 < C53.p().C53_f778 && C53.p().C53_f777[var1].t() >= 50; ++var1) {
                  }

                  if (var1 >= C53.p().C53_f778) {
                     this.C9_f131 = 1;
                     this.C9_f122.showDialog("/data/ui/msgwarm.ui", 257, this);
                     this.a("Trong ba lô sủng vật đều đã max level", "Nhấn nút 5 tiếp tục");
                     return;
                  }
               }

               if (this.C9_f121.y() > 1) {
                  this.C9_f121.c((byte)1);
                  return;
               }

               if (this.C9_f122.currentDialog.getChildById(11).getComponentData().b()) {
                  this.C9_f121.e(1);
                  return;
               }
            }
            break;
         case 1:
            if (this.C9_f121.g(131072)) {
               this.C9_f121.f(1);
               return;
            }

            if (this.C9_f121.g(262144)) {
               this.C9_f121.f(2);
               return;
            }
            break;
         case 2:
            if (this.bC() && this.C9_f121.g(917504)) {
               if (this.C9_f121.w()) {
                  if (this.C9_f121.v() == 3) {
                     this.C9_f121.a((byte)25);
                  }

                  this.C9_f121.c((byte)5);
               } else {
                  this.C9_f121.c((byte)1);
               }

               this.C9_f131 = 0;
               return;
            }
            break;
         case 3:
            if (this.C9_f121.g(393216)) {
               this.C9_f121.f(1);
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
         if (this.C9_f121.v() == 3) {
            if (C25.B().I()) {
               this.a("Lưu thành công");
               this.C9_f131 = 2;
            }
         } else if (C25.B().J()) {
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
      GameUtils.a(var2, C44.o(), this.C9_f122.currentDialog.getChildById(14).getWidth(), C44.m(), true, (byte)-1, this.C9_f121.C44_f700.dialogConfig);
      GameUtils.d(this.C9_f122.currentDialog.getChildById(14).getHeight());
      this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = GameUtils.e(1);
      C25.C25_f317 = (byte)var3;
      C25.C25_f316 = (byte)var4;
      this.C9_f122.currentDialog.getChildById(8).setVisible(false);
      this.C9_f122.currentDialog.getChildById(11).setVisible(false);
      this.C9_f122.currentDialog.getChildById(12).setVisible(true);
      this.C9_f122.currentDialog.getChildById(13).setVisible(true);
      if (var3 == -1) {
         this.C9_f122.currentDialog.getChildById(12).setVisible(false);
         this.C9_f122.currentDialog.getChildById(13).setVisible(false);
      }

      switch(var3) {
      case 0:
         if (var4 != -1) {
            if (this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f195.C17_f222 = 3;
               this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f195.a(323, false, (byte)-2);
            }

            this.C9_f122.currentDialog.getChildById(11).setVisible(true);
            this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f195.a((byte)(var3 + (var4 << 1)), (byte)-2);
         }

         this.C9_f122.currentDialog.getChildById(13).setVisible(false);
         this.C9_f122.currentDialog.getChildById(12).getComponentData().C12_f179 = var1;
         return;
      case 1:
         if (var4 != -1) {
            if (this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f195 == null) {
               this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f195 = new C17();
               this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f195.C17_f222 = 3;
               this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f195.a(323, false, (byte)-2);
            }

            this.C9_f122.currentDialog.getChildById(8).setVisible(true);
            this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f195.a((byte)(var3 + (var4 << 1)), (byte)-2);
         }

         this.C9_f122.currentDialog.getChildById(12).setVisible(false);
         this.C9_f122.currentDialog.getChildById(13).getComponentData().C12_f179 = var1;
      default:
      }
   }

   public final void b(int var1) {
      this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = GameUtils.e(var1);
   }

   public final void aF() {
      this.C9_f122.removeDialog("/data/ui/dialog.ui");
   }

   public final boolean d(int var1, int var2) {
      if (var2 == -1) {
         return true;
      } else {
         switch(var1) {
         case 0:
            if (this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f195.a().f()) {
               return true;
            }
            break;
         case 1:
            if (this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f195.a().f()) {
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
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = var2;
      switch(var1) {
      case 0:
         for(var1 = 0; var1 < var3.length; ++var1) {
            this.C9_f122.currentDialog.getChildById(var1 + 12).getComponentData().C12_f179 = var3[var1];
         }

         return;
      case 1:
         this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = var4;

         for(var1 = 0; var1 < var3.length; ++var1) {
            this.C9_f122.currentDialog.getChildById(9 + (var1 << 2)).getComponentData().C12_f179 = var3[var1];
         }

         return;
      case 2:
         this.C9_f122.currentDialog.getChildById(10).setVisible(false);
         this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = "Trò chơi";
         this.C9_f122.currentDialog.getChildById(9).getComponentData().C12_f179 = "Xác nhận";

         for(var1 = 0; var1 < var3.length; ++var1) {
            this.C9_f122.currentDialog.getChildById(var1 + 5).getComponentData().C12_f179 = var3[var1];
         }
      default:
      }
   }

   public final int c(int var1) {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.C9_f125 = this.C9_f140[0];
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.C9_f125 = this.C9_f140[0];
      } else if (this.C9_f121.g(196640)) {
         this.C9_f122.removeDialog(this.C9_f157[var1]);
         return this.C9_f125;
      }

      return -1;
   }

   public final void a(int[] var1, int[] var2, String[] var3, String[] var4) {
      this.C9_f125 = 0;
      this.C9_f122.showDialog("/data/ui/taskOption.ui", 257, this);

      int var5;
      for(var5 = 0; var5 < var4.length; ++var5) {
         this.C9_f122.currentDialog.getChildById(var5 + 17).getComponentData().C12_f179 = var4[var5];
      }

      for(var5 = 0; var5 < var1.length; ++var5) {
         if (this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195 == null) {
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195 = new C17();
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.C17_f222 = 2;
            if (var1[var5] >= 3 && var1[var5] < 5) {
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.a((int)-1);
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.a(257, false, (byte)0);
            } else {
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.a((int)0);
               this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.a(258, false, (byte)0);
            }
         }

         switch(var1[var5]) {
         case 0:
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.a((int)C67.C67_f923[4][var2[var5]][1]);
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().C12_f179 = var3[var5];
            break;
         case 1:
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.a((int)C67.C67_f923[3][var2[var5]][1]);
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().C12_f179 = var3[var5];
            break;
         case 2:
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.a((int)C67.C67_f923[5][var2[var5]][1]);
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().C12_f179 = var3[var5];
            break;
         case 3:
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.a((int)84);
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().C12_f179 = var3[var5];
            break;
         case 4:
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 13).getComponentData().C12_f195.a((int)83);
            this.C9_f122.currentDialog.getChildById((var5 << 1) + 14).getComponentData().C12_f179 = var3[var5];
         case 5:
         default:
            break;
         case 6:
            this.C9_f122.currentDialog.getChildById(21).getComponentData().C12_f179 = "#2" + C44.c((int)C67.a((byte)0, (short)var2[var5], (byte)0)) + " #0" + var3[var5];
         }
      }

   }

   public final int aG() {
      if (this.C9_f121.g(4100)) {
         this.C9_f122.currentDialog.handleAction(0);
         this.C9_f125 = this.C9_f140[0];
      } else if (this.C9_f121.g(8448)) {
         this.C9_f122.currentDialog.handleAction(1);
         this.C9_f125 = this.C9_f140[0];
      } else {
         if (this.C9_f121.g(196640)) {
            this.C9_f122.removeDialog("/data/ui/taskOption.ui");
            return this.C9_f125;
         }

         if (this.C9_f121.g(262144)) {
            this.C9_f122.removeDialog("/data/ui/taskOption.ui");
            return 1;
         }
      }

      return -1;
   }

   public final void aH() {
      this.bE();
      this.c("Có dùng 10000 kim tiền để khôi phục trạng thái của tất cả sủng vật trong ba lô không?", "Tại chỗ sống lại");
   }

   private void bD() {
      byte var1 = -1;
      if (C25.B().C25_f290 == 9) {
         var1 = (byte)C25.B().C25_f291;
      }

      if (var1 == -1) {
         C25.B();
         if (C25.G()) {
            C25.B().c();
            this.C9_f123.C53_f776 = false;
            C55.B().a((byte)9);
         } else {
            C55.B().a((byte)7);
         }
      } else {
         for(int var2 = 0; var2 < this.C9_f123.C53_f778; ++var2) {
            this.C9_f123.C53_f777[var2].l(1);
            this.C9_f123.C53_f777[var2].u(1);
            this.C9_f123.C53_f777[var2].c();
         }

         C25.C25_f318 = -1;
         if (C25.B().C25_f291 == 0) {
            short[] var5 = new short[]{15, 194, 433, 16, 142, 357, 17, 97, 268, 18, 183, 224};

            for(int var3 = 0; var3 < C25.B().C25_f287.length; ++var3) {
               for(int var4 = 0; var4 < var5.length / 3; ++var4) {
                  if (C25.B().C25_f287[var3].C18_f248 == var5[var4 * 3]) {
                     C25.B().C25_f287[var3].b(var5[var4 * 3 + 1], var5[var4 * 3 + 2]);
                  }
               }
            }
         }

         C25.B().C25_f290 = this.C9_f158[var1 << 2];
         C25.B().C25_f291 = this.C9_f158[(var1 << 2) + 1];
         C53.p().b(this.C9_f158[(var1 << 2) + 2], this.C9_f158[(var1 << 2) + 3]);
         short var10001 = this.C9_f158[(var1 << 2) + 2];
         short var10002 = this.C9_f158[(var1 << 2) + 3];
         C53.p().C20_f262.b(var10001, var10002);
         C53 var10000 = C53.p();
         byte var6 = 2;
         var10000.C60_f866 = var6;
         C55.B().a((byte)10);
      }
   }

   public final void aI() {
      if (!this.C9_f121.g(196640)) {
         if (this.C9_f131 == 0 && this.C9_f121.g(786432)) {
            this.bD();
            this.bF();
         }

      } else {
         int var1;
         if (this.C9_f131 == 0) {
            if (!this.C9_f123.u(10000)) {
               this.H();
               this.a("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
               this.C9_f131 = 1;
            } else {
               this.C9_f123.s(-10000);

               for(var1 = 0; var1 < this.C9_f123.C53_f778; ++var1) {
                  this.C9_f123.C53_f777[var1].J();
                  C41 var10000 = this.C9_f123.C53_f777[var1];
                  C41 var10001 = this.C9_f123.C53_f777[var1];
                  byte var3 = 1;
                  var10000.u(var10001.C60_f856[var3]);
               }

               C29.B().C();
               this.C9_f121.a((byte)0);
               this.bF();
            }
         } else {
            for(var1 = 0; var1 < this.C9_f123.C53_f778; ++var1) {
               this.C9_f123.C53_f777[var1].l(1);
               this.C9_f123.C53_f777[var1].u(1);
               this.C9_f123.C53_f777[var1].c();
            }

            if (C44.C44_f711) {
               this.C9_f121.a((byte)102);
            } else {
               this.bD();
            }

            this.I();
         }
      }
   }

   public final void d(int var1) {
      this.C9_f121.b((byte)0);
      this.C9_f121.c((byte)0);
      this.bE();
      int[] var2 = new int[]{4, 1, 4};
      String var3 = C44.c((int)599) + C44.a(604, (int[])var2);
      this.c(var3, "Kích hoạt trò chơi");
   }

   public final void aJ() {
      this.C9_f121.b((byte)1);
      this.C9_f121.c((byte)0);
      this.bE();
      int[] var1 = new int[]{2, 1, 2};
      String var2 = C44.c((int)600) + C44.a(604, (int[])var1);
      this.c(var2, "Mua sắm tất trúng cầu");
   }

   public final void aK() {
      this.C9_f131 = 0;
      this.C9_f121.b((byte)4);
      this.C9_f121.c((byte)0);
      this.bE();
      int[] var1 = new int[]{2, 1, 2};
      String var2 = C44.c((int)603) + C44.a(604, (int[])var1);
      this.c(var2, "Mua huy hiệu");
   }

   public final void aL() {
      this.C9_f131 = 0;
      this.C9_f121.b((byte)2);
      this.C9_f121.c((byte)0);
      this.bE();
      int[] var1 = new int[]{2, 1, 2};
      String var2 = C44.c((int)601) + C44.a(604, (int[])var1);
      this.c(var2, "Mua kim tiền");
   }

   private void bE() {
      this.C9_f122.showDialog("/data/ui/smsInfo.ui", 257, this);
      if (this.C9_f121 instanceof C25) {
         this.C9_f122.currentDialog.getChildById(6).setVisible(true);
         this.C9_f122.currentDialog.getChildById(7).setVisible(true);
         this.C9_f122.currentDialog.getChildById(10).setVisible(false);
         this.C9_f122.currentDialog.getChildById(11).setVisible(false);
      } else {
         this.C9_f122.currentDialog.getChildById(6).setVisible(false);
         this.C9_f122.currentDialog.getChildById(7).setVisible(false);
         this.C9_f122.currentDialog.getChildById(10).setVisible(true);
         this.C9_f122.currentDialog.getChildById(11).setVisible(true);
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f179 = "Xác nhận";
         this.C9_f122.currentDialog.getChildById(11).getComponentData().C12_f179 = "Quay lại";
      }
   }

   private void c(String var1, String var2) {
      this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = var1;
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = var2;
   }

   private void bF() {
      this.C9_f122.removeDialog("/data/ui/smsInfo.ui");
   }

   public final void aM() {
      if (!this.C9_f122.containsDialog("/data/ui/smsTip.ui")) {
         this.C9_f122.showDialog("/data/ui/smsTip.ui", 257, this);
      }

      for(int var1 = 0; var1 < 3; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1 + 6).setVisible(false);
      }

      this.C9_f132 = true;
   }

   public final void d(String var1) {
      this.C9_f132 = true;
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = var1;
   }

   public final void aN() {
      this.C9_f122.removeDialog("/data/ui/smsTip.ui");
   }

   public final void aO() {
      switch(this.C9_f121.x()) {
      case 0:
         if (!this.C9_f121.g(16400) && !this.C9_f121.g(32832)) {
            if (this.C9_f121.g(131072)) {
               if (this.C9_f121.y() > 1) {
                  this.C9_f121.c((byte)1);
                  return;
               }

               this.C9_f121.e(1);
               return;
            }

            if (this.C9_f121.g(786432)) {
               this.bF();
               this.C9_f121.c((byte)5);
               this.C9_f121.a(this.C9_f121.C44_f699);
               return;
            }
         }
         break;
      case 1:
         if (this.C9_f121.g(131072)) {
            this.C9_f121.f(1);
            return;
         }

         if (this.C9_f121.g(262144)) {
            this.C9_f121.f(2);
            return;
         }
         break;
      case 2:
         boolean var1 = false;
         if (this.C9_f121.C44_f698 != 100) {
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

         if (var1 && this.bC() && this.C9_f121.g(917504)) {
            this.C9_f137 = 0;
            if (this.C9_f121.w()) {
               this.bF();
               this.aN();
               this.C9_f121.a(this.C9_f121.C44_f699);
            } else {
               this.C9_f121.c((byte)5);
            }

            this.C9_f131 = 0;
            return;
         }
         break;
      case 3:
         if (this.C9_f121.g(393216)) {
            this.C9_f121.f(1);
         }
      }

   }

   public final void a(byte var1, int var2, int var3) {
      this.C9_f126 = 0;
      this.C9_f162 = var1;
      this.C9_f163 = (byte)var2;
      label23:
      switch(var2) {
      case 0:
         this.C9_f122.showDialog("/data/ui/wharf1.ui", 257, this);
         this.C9_f122.currentDialog.getChildById(8).getComponentData().C12_f179 = C44.c(var3);
         var2 = 0;

         while(true) {
            if (var2 >= this.C9_f160[var1].length) {
               break label23;
            }

            this.C9_f122.currentDialog.getChildById(var2 + 5).getComponentData().C12_f179 = C44.c((int)this.C9_f160[var1][var2]);
            ++var2;
         }
      case 1:
         this.C9_f122.showDialog("/data/ui/wharf2.ui", 257, this);
         this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f179 = C44.c(var3);

         for(var2 = 0; var2 < this.C9_f160[var1].length; ++var2) {
            this.C9_f122.currentDialog.getChildById(var2 + 5).getComponentData().C12_f179 = C44.c((int)this.C9_f160[var1][var2]);
         }
      }

      this.C9_f122.currentDialog.getChildById(5 + this.C9_f160[var1].length).getComponentData().C12_f179 = "Không ra hàng";
   }

   public final void aP() {
      if (this.C9_f121.g(4100) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.g(8448) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.g(196640) && !this.j()) {
         if (this.C9_f126 == this.C9_f161[this.C9_f162].length / 9) {
            switch(this.C9_f163) {
            case 0:
               this.C9_f122.removeDialog("/data/ui/wharf1.ui");
               break;
            case 1:
               this.C9_f122.removeDialog("/data/ui/wharf2.ui");
            }

            if (C25.C25_f318 != -1 && C25.B().C25_f287[C25.C25_f318].v() == 0) {
               C25.B().a((byte)13, C25.B().C25_f287[C25.C25_f318].C60_f861, C25.B().C25_f287[C25.C25_f318].C60_f862 - 40, C25.B().C25_f287[C25.C25_f318]);
            }

            this.C9_f121.a((byte)0);
         } else {
            label59: {
               short var10001 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 6];
               short[] var10002 = this.C9_f161[this.C9_f162];
               if (C25.B().C25_f348.C7_f60[C25.e(var10001, var10002[this.C9_f126 * 9 + 7])] != null) {
                  var10001 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 6];
                  var10002 = this.C9_f161[this.C9_f162];
                  if (C25.B().C25_f348.C7_f60[C25.e(var10001, var10002[this.C9_f126 * 9 + 7])][this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 8]] == 3) {
                     C25.B().C25_f290 = this.C9_f161[this.C9_f162][this.C9_f126 * 9];
                     C25.B().C25_f291 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 1];
                     C25.B().C25_f293 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 2];
                     C25.B().C25_f294 = this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 3];
                     C25.C25_f320 = (byte)this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 4];
                     C25.B().C25_f295 = -1;
                     C33.B().d((byte)this.C9_f161[this.C9_f162][this.C9_f126 * 9 + 5]);
                     this.C9_f121.a((byte)29);
                     switch(this.C9_f163) {
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
      } else if (this.C9_f121.g(262144) && !this.j()) {
         if (C25.C25_f318 != -1 && C25.B().C25_f287[C25.C25_f318].v() == 0) {
            C25.B().a((byte)13, C25.B().C25_f287[C25.C25_f318].C60_f861, C25.B().C25_f287[C25.C25_f318].C60_f862 - 40, C25.B().C25_f287[C25.C25_f318]);
         }

         switch(this.C9_f163) {
         case 0:
            this.C9_f122.removeDialog("/data/ui/wharf1.ui");
            break;
         case 1:
            this.C9_f122.removeDialog("/data/ui/wharf2.ui");
         }

         this.C9_f121.a((byte)0);
      }

      this.f();
   }

   public final void aQ() {
      this.C9_f125 = 0;
      this.C9_f122.showDialog("/data/ui/shopbuy.ui", 257, this);
      this.C9_f125 = 0;
      this.C9_f131 = 0;
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f574 = 1;
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.a(0);
      this.C9_f122.currentDialog.getChildById(41).setVisible(false);
      this.C9_f122.currentDialog.getChildById(43).setVisible(false);
      this.C9_f122.currentDialog.getChildById(5).getComponentData().C12_f179 = "Mua";
      this.C9_f122.currentDialog.getChildById(57).setVisible(true);
      this.C9_f122.currentDialog.getChildById(58).setVisible(true);
      this.C9_f122.currentDialog.getChildById(57).getComponentData().C12_f179 = "Mua sắm";
      this.C9_f122.currentDialog.getChildById(58).getComponentData().C12_f179 = "Quay lại";
      this.C9_f122.currentDialog.getChildById(39).setVisible(false);
      this.C9_f122.currentDialog.getChildById(40).setVisible(false);
      this.C9_f135 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f579;
      this.C9_f136 = ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580;
      if (this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f195 == null) {
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f195 = new C17();
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f195.a((int)0);
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f195.C17_f222 = 2;
         this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f195.a(258, false, (byte)-1);
      }

      this.C9_f122.currentDialog.getChildById(51).getComponentData().C12_f195.a((int)C67.C67_f923[5][0][1]);
      this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = C44.c((int)C67.C67_f923[5][0][0]);
      this.C9_f122.currentDialog.getChildById(15).getComponentData().C12_f179 = "5000";
      this.C9_f122.currentDialog.getChildById(45).getComponentData().C12_f195.a((int)84);
      this.C9_f122.currentDialog.getChildById(56).getComponentData().C12_f179 = "Ấp trứng ra sủng vật";
      this.C9_f122.currentDialog.getChildById(44).getComponentData().C12_f179 = "" + this.C9_f123.F();
      this.C9_f122.currentDialog.getChildById(38).setOffsetY(102 + this.C9_f136 * 84 / C67.C67_f923[5].length, this.C9_f122.currentDialog.getRootComponent());
   }

   private void bG() {
      this.C9_f122.removeDialog("/data/ui/shopbuy.ui");
   }

   public final int aR() {
      if (this.C9_f121.g(196640)) {
         if (this.C9_f131 == 0) {
            if (this.C9_f123.u(5000)) {
               if (this.C9_f123.l(0)) {
                  this.H();
                  this.a("Đã có trứng sủng vật, không cần mua sắm", "Nhấn nút 5 tiếp tục");
                  this.C9_f131 = 2;
               } else {
                  this.C9_f123.e(0, -1);
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
               this.C9_f121.a((byte)102);
            } else if (this.C9_f131 == 2) {
               C7.C7_f75 = 0;
               this.bG();
               this.C9_f121.a((byte)0);
            }
         }
      } else if (this.C9_f121.g(262144) && this.C9_f131 == 0) {
         C7.C7_f75 = 1;
         this.bG();
         this.C9_f121.a((byte)0);
      }

      return -1;
   }

   public final void aS() {
      this.C9_f122.showDialog("/data/ui/wharf2.ui", 257, this);
      ((RootComponent)this.C9_f122.currentDialog.getChildById(0)).otherChildComponent.C38_f580 = this.C9_f128;
      this.C9_f131 = 0;
      this.C9_f122.currentDialog.getChildById(10).getComponentData().C12_f179 = "Tiện lợi điếm";
      this.C9_f122.currentDialog.getChildById(12).getComponentData().C12_f179 = "Tiến vào";

      for(int var1 = 0; var1 < this.C9_f164.length; ++var1) {
         this.C9_f122.currentDialog.getChildById(var1 + 5).getComponentData().C12_f179 = this.C9_f164[var1];
      }

   }

   public final void aT() {
      if (this.C9_f121.g(4100) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(0);
      } else if (this.C9_f121.g(8448) && !this.j()) {
         this.C9_f122.currentDialog.handleAction(1);
      } else if (this.C9_f121.g(196640) && !this.j()) {
         if (this.C9_f131 == 0) {
            switch(this.C9_f128) {
            case 0:
               this.C9_f122.removeDialog("/data/ui/wharf2.ui");
               this.C9_f121.a((byte)31);
               return;
            case 1:
            case 2:
               C25.B();
               if (C25.C25_f339) {
                  this.C9_f122.removeDialog("/data/ui/wharf2.ui");
                  this.C9_f126 = 0;
                  this.C9_f121.a((byte)7);
                  return;
               }

               this.H();
               this.C9_f131 = 1;
               this.a("Công năng theo đạo học sau mở ra", "Nhấn nút 5 tiếp tục");
               return;
            case 3:
               this.C9_f122.removeDialog("/data/ui/wharf2.ui");
               this.C9_f121.a((byte)32);
               return;
            case 4:
               this.C9_f122.removeDialog("/data/ui/wharf2.ui");
               this.C9_f121.a((byte)0);
            default:
            }
         } else {
            this.C9_f131 = 0;
            this.I();
            this.C9_f132 = true;
         }
      } else {
         if (this.C9_f121.g(262144) && this.C9_f131 == 0 && !this.j()) {
            this.C9_f122.removeDialog("/data/ui/wharf2.ui");
            this.C9_f121.a((byte)0);
         }

      }
   }

   public final void a(int[] var1, int[] var2) {
      this.C9_f140 = var1;
      if (this.C9_f121 instanceof C25) {
         switch(((C25)this.C9_f121).C44_f698) {
         case 0:
            return;
         case 1:
            this.C9_f125 = var1[0];
            return;
         case 2:
         case 26:
         case 32:
            this.c(var1);
            return;
         case 3:
            if (this.C9_f131 != 0) {
               this.C9_f127 = var1[0];
               return;
            }

            this.C9_f125 = var1[0];
            this.aZ();
            break;
         case 4:
            return;
         case 5:
            this.C9_f125 = var1[1];
            this.bv();
            return;
         case 6:
            this.C9_f125 = var1[0];
            if (!C44.C44_f711) {
               this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = C44.c(606 + this.C9_f125);
               return;
            }

            this.C9_f122.currentDialog.getChildById(14).getComponentData().C12_f179 = C44.c(605 + this.C9_f125);
            break;
         case 7:
            this.b(var1);
            return;
         case 8:
            if (var1[0] >= 0) {
               this.C9_f126 = var1[0];
            }

            if (var1[1] >= 0) {
               this.C9_f125 = var1[1];
            }

            this.br();
            return;
         case 9:
            this.C9_f126 = var1[1];
            return;
         case 10:
            this.C9_f125 = var1[1];
            switch(this.C9_f125) {
            case 0:
               this.C9_f126 = var1[0];
               return;
            case 1:
               this.C9_f127 = var1[0];
            default:
               return;
            }
         case 11:
            this.C9_f126 = var1[0];
            this.C9_f125 = var1[1];
            return;
         case 12:
            this.C9_f125 = var1[1];
            return;
         case 13:
            if (this.C9_f131 == 0) {
               this.C9_f125 = var1[0];
               return;
            }

            this.C9_f126 = var1[0];
            return;
         case 14:
            this.C9_f126 = var1[0];
            return;
         case 15:
            this.C9_f126 = var1[0];
            return;
         case 16:
            this.C9_f125 = var1[0];
            return;
         case 17:
         case 18:
         case 19:
            this.C9_f126 = var1[0];
            return;
         case 20:
            this.C9_f126 = var1[1];
            return;
         case 24:
            this.C9_f126 = var1[0];
            return;
         case 27:
            this.C9_f128 = var1[0];
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
            this.C9_f126 = var1[0];
            return;
         }
      } else if (this.C9_f121 instanceof C29) {
         switch(((C29)this.C9_f121).C44_f698) {
         case 2:
            return;
         case 3:
            this.C9_f129 = var1[0];
            return;
         case 4:
            this.C9_f125 = var1[0];
            this.bi();
            return;
         case 5:
            this.b(var1);
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
            this.c(var1);
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
            this.C9_f126 = var1[0];
            this.g(this.C9_f126);
            return;
         case 17:
            return;
         case 18:
            return;
         case 19:
            return;
         case 20:
            this.C9_f124 = var1[1];
            this.C9_f122.currentDialog.getChildById(20 + this.C9_f124).setVisible(true);
            return;
         case 21:
            this.C9_f125 = var1[0];
         case 23:
            this.C9_f125 = var1[0];
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
            switch(this.C9_f126) {
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
