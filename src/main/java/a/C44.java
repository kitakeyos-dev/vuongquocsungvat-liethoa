package a;

import a.b.C67;
import c.DialogManager;
import e.a.a.a.b.C14;
import e.a.a.a.b.C3;
import e.b.a.a.C2;
import game.C1;
import game.C25;
import game.C53;
import game.C7;
import game.C9;
import game.GameMIDLet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.midlet.MIDlet;
import me.kitakeyos.C50;

public abstract class C44 extends C8 implements C14, C2 {
   private static short C44_f692;
   private static short C44_f693;
   private static int C44_f694;
   private static Font C44_f695;
   private static Font C44_f696;
   private static int C44_f697;
   public byte C44_f698;
   public byte C44_f699;
   public DialogManager C44_f700;
   public C9 C44_f701;
   private static boolean C44_f702 = false;
   public static boolean C44_f703 = false;
   public static byte C44_f704 = -1;
   public static byte C44_f705 = 0;
   private static byte[][] C44_f706 = new byte[7][3];
   private static Timer C44_f707;
   private static TimerTask C44_f708;
   private static boolean C44_f709 = false;
   public static boolean C44_f710;
   public static boolean C44_f711 = false;
   private byte C44_f712;
   private static byte[] C44_f713 = new byte[]{0, 0, 0, 0, 0};
   private byte C44_f715;
   private byte C44_f716;
   private byte C44_f717;
   private String[] C44_f718 = new String[]{"01", "02", "03", "04", "05"};
   private byte[][] C44_f719 = new byte[][]{{4, 1, 0}, {2, 1, 1}, {2, 1, 2}, {2, 1, 3}, {2, 1, 4}};
   private String[][] C44_f720 = new String[][]{{"Kích hoạt trò chơi", "Bạn muốn khám phá bí mật của vương quốc sủng vật, dẫn dắt thú yêu chiến đấu, tiến hóa, ấp trứng? Chỉ cần 1 tin nhắn 0đ để kích hoạt trò chơi, chỉ nhắn tin 1 lần cho tất cả các lượt chơi. Bạn có muốn nhắn tin không?"}, {"Tất trúng cầu", "Chỉ cần nhắn 1 tin nhắn 0đ, bạn sẽ sở hữu 1 tất trúng cầu, tỷ lệ 100% bắt được sủng vật? Bạn có muốn nhắn tin không?"}, {"Mua kim tiền", "Kiếm tiền vất vả, vật phẩm đắt đỏ? Chỉ cần nhắn 1 tin nhắn 0đ bạn sẽ đạt được 10000 kim tiền. Bạn có muốn nhắn tin không?"}, {"Mua đẳng cấp", "Thăng cấp chậm chạp, kẻ địch lại quá mạnh? Chỉ cần 1 tin nhắn 0đ, tất cả sủng vật trong ba lô của bạn đều được thăng lên 5 cấp. Bạn có muốn nhắn tin không?"}, {"Mua huy hiệu", "Kiếm huy hiệu khó khăn? Chỉ cần 1 tin nhắn 0đ, bạn sẽ đạt được 10 huy hiệu. Bạn có muốn nhắn tin không?"}};
   private C3 C44_f721;

   public abstract void a();

   public abstract void a(Graphics var1);

   public abstract boolean b();

   public abstract void c();

   public abstract void a(byte var1);

   public final void d() {
      if (!C44_f702) {
         if (C44_f707 == null || C44_f708 == null) {
            C44_f707 = new Timer();
            C44_f708 = new C45();
         }

         C44_f707.schedule(C44_f708, 10L, 200L);
         C44_f702 = true;
      }
   }

   protected static void e() {
      if (C44_f708 != null) {
         C44_f708.cancel();
         C44_f708 = null;
      }

      if (C44_f707 != null) {
         C44_f707.cancel();
         C44_f707 = null;
         System.gc();
      }

      C44_f702 = false;
      C44_f709 = true;
   }

   public static void a(boolean var0) {
      C44_f709 = false;
   }

   public static boolean f() {
      return C44_f709;
   }

   public static void a(short var0, short var1) {
      C44_f692 = var0;
      C44_f693 = var1;
   }

   public static short g() {
      return C44_f692;
   }

   public static short h() {
      return C44_f693;
   }

   public static short i() {
      return (short)(C44_f692 / 2);
   }

   public static short j() {
      return (short)(C44_f693 / 2);
   }

   public static void a(int var0) {
      C44_f694 = 66;
   }

   public static int k() {
      return C44_f694;
   }

   public static void b(int var0) {
      C44_f697 = var0;
   }

   public static int l() {
      return C44_f697;
   }

   public static Font m() {
      if (C44_f695 == null) {
         C44_f695 = Font.getFont(0, 0, 8);
      }

      return C44_f695;
   }

   public static Font n() {
      if (C44_f696 == null) {
         C44_f696 = Font.getFont(0, 0, 16);
      }

      return C44_f696;
   }

   public static int o() {
      return C44_f695.getHeight();
   }

   public static String c(int var0) {
      return var0 == 0 ? "" : C67.C67_f924[var0];
   }

   public static String a(int var0, int[] var1) {
      if (var0 == 0) {
         return "";
      } else {
         int var2 = 0;
         String var3 = "";
         int var4;
         if ((var4 = c(var0).indexOf("%s", 0)) == -1) {
            return c(var0);
         } else {
            int var5;
            for(var5 = 0; var4 != -1; var4 = c(var0).indexOf("%s", var5)) {
               var3 = var3 + c(var0).substring(var5, var4) + var1[var2];
               ++var2;
               var5 = var4 + 2;
            }

            return var3 + c(var0).substring(var5);
         }
      }
   }

   public static String a(String var0, int[] var1) {
      if (var0.equals("")) {
         return "";
      } else {
         int var2 = 0;
         String var3 = "";
         int var4;
         if ((var4 = var0.indexOf("%s", 0)) == -1) {
            return var0;
         } else {
            int var5;
            for(var5 = 0; var4 != -1; var4 = var0.indexOf("%s", var5)) {
               var3 = var3 + var0.substring(var5, var4) + var1[var2];
               ++var2;
               var5 = var4 + 2;
            }

            return var3 + var0.substring(var5);
         }
      }
   }

   public static String a(int var0, String[] var1) {
      if (var0 == 0) {
         return "";
      } else {
         int var2 = 0;
         String var3 = "";
         int var4;
         if ((var4 = c(var0).indexOf("%s", 0)) == -1) {
            return c(var0);
         } else {
            int var5;
            for(var5 = 0; var4 != -1; var4 = c(var0).indexOf("%s", var5)) {
               var3 = var3 + c(var0).substring(var5, var4) + var1[var2];
               ++var2;
               var5 = var4 + 2;
            }

            return var3 + c(var0).substring(var5);
         }
      }
   }

   public static void a(Graphics var0, Image var1, String var2, int var3, int var4, int var5, int var6, int var7) {
      for(var7 = 0; var7 < var2.length(); ++var7) {
         char var8;
         if (Character.isDigit(var8 = var2.charAt(var7))) {
            var8 = (char)(var8 - 48);
         } else {
            switch(var8) {
            case '+':
               var8 = '\n';
               break;
            case '-':
               var8 = '\n';
            }
         }

         var0.drawRegion(var1, var8 * var5, 0, var5, var6, 0, var3 - ((var2.length() - 1 - (var7 << 1)) * var5 >> 1), var4, 20);
      }

   }

   public static boolean p() {
      return C44_f704 != -1;
   }

   public void q() {
   }

   public void r() {
   }

   public static boolean s() {
      if (C44_f704 == -1) {
         return true;
      } else {
         return C44_f706[C44_f704][0] == 1;
      }
   }

   public static boolean t() {
      if (C44_f704 == -1) {
         return true;
      } else {
         return C44_f706[C44_f704][0] == 2;
      }
   }

   public static boolean a(int var0, int var1) {
      if (C44_f704 == -1) {
         return false;
      } else if (var1 != C44_f706[C44_f704][2]) {
         return true;
      } else if (C44_f706[C44_f704][1] == -1) {
         return true;
      } else {
         return C44_f706[C44_f704][1] == var0;
      }
   }

   public static void b(int var0, int var1) {
      if (C44_f704 != -1) {
         if (var1 == -1) {
            C44_f706[C44_f704][2] = 0;
         }

         C44_f706[C44_f704][var0] = (byte)var1;
      }
   }

   public static byte d(int var0) {
      return C44_f704 == -1 ? -1 : C44_f706[C44_f704][1];
   }

   public static void u() {
      b(1, -1);
      b(0, 0);
      C44_f704 = -1;
      C44_f705 = 0;
   }

   public final void b(boolean var1) {
      if (this.C44_f712 == 4) {
         if (var1) {
            ++this.C44_f716;
            ++C44_f713[this.C44_f715];
            System.out.println(" curNum = " + this.C44_f716 + " tolNum = " + this.C44_f717);
            if (this.C44_f716 >= this.C44_f717) {
               switch(this.C44_f715) {
               case 0:
                  C44_f711 = true;
                  C53.p().s(2000);
                  C53.p().c(1, 5, (byte)0);
                  C53.p().c(4, 5, (byte)0);
                  C53.p().c(11, 2, (byte)0);
                  C53.p().v(5);
                  C7.B().C7_f60[C25.e(9, 0)][5] = 3;
                  C7.B().C7_f56[5].a((byte)3);
                  break;
               case 1:
                  C53.p().c(0, 1, (byte)0);
                  break;
               case 2:
                  C53.p().s(10000);
                  break;
               case 3:
                  C25.C25_f335 = 0;
                  if (C25.C25_f334 == null) {
                     C25.C25_f334 = new Vector();
                  }

                  if (C25.C25_f333 == null) {
                     C25.C25_f333 = new Vector();
                  }

                  C25.C25_f334.removeAllElements();
                  C25.C25_f333.removeAllElements();

                  for(int var2 = 0; var2 < C53.p().C53_f778; ++var2) {
                     if (C53.p().C53_f777[var2].t() == 50) {
                        C53.p().C53_f777[var2].K();
                     } else {
                        C53.p().C53_f777[var2].y();
                        if (C53.p().C53_f777[var2].t() + 5 >= 50) {
                           C53.p().C53_f777[var2].h(50 - C53.p().C53_f777[var2].t());
                        } else {
                           C53.p().C53_f777[var2].h((int)5);
                        }

                        C53.p().C53_f777[var2].J();
                        if (C53.p().C53_f777[var2].F() < 5 && C53.p().C53_f777[var2].F() < C53.p().C53_f777[var2].t() / 10 + 1) {
                           C25.C25_f333.addElement(C53.p().C53_f777[var2]);
                           C25.C25_f334.addElement("" + var2);
                        }
                     }
                  }

                  if (C25.C25_f333.size() <= 0) {
                     C25.C25_f335 = 2;
                  } else {
                     C25.C25_f335 = 1;
                  }
                  break;
               case 4:
                  C53.p().v(10);
               }
            }

            this.c((byte)2);
            return;
         }

         this.c((byte)3);
      }

   }

   private boolean B() {
      switch(this.C44_f715) {
      case 0:
         this.l(this.C44_f715);
         break;
      case 1:
         this.l(this.C44_f715);
         break;
      case 2:
         this.l(this.C44_f715);
         break;
      case 3:
         this.l(this.C44_f715);
         break;
      case 4:
         this.l(this.C44_f715);
      }

      return true;
   }

   public final boolean b(byte var1) {
      this.C44_f715 = var1;
      switch(var1) {
      case 0:
         this.C44_f717 = 1;
         break;
      case 1:
         this.C44_f717 = 1;
         break;
      case 2:
         this.C44_f717 = 1;
         break;
      case 3:
         this.C44_f717 = 1;
         break;
      case 4:
         this.C44_f717 = 1;
      }

      this.C44_f716 = 0;
      return true;
   }

   public final void c(byte var1) {
      while(true) {
         byte var10000 = this.C44_f712;
         if (var1 != 5 && var1 != 0) {
            this.C44_f701.aM();
         }

         switch(var1) {
         case 1:
            System.out.println(" " + a(513, (int[])(new int[]{this.C44_f717, this.C44_f716})));
            this.C44_f701.d(a(513, (int[])(new int[]{this.C44_f717, this.C44_f716})));
            break;
         case 2:
            if (this.w()) {
               if (this.C44_f715 == 0) {
                  this.C44_f701.d(c((int)515) + c((int)633));
               } else {
                  this.C44_f701.d(c((int)515));
               }
            } else {
               this.C44_f701.d(c((int)516));
               System.out.println(" " + c((int)516));
            }
            break;
         case 3:
            System.out.println(" " + c((int)516));
            this.C44_f701.d(c((int)516));
            break;
         case 4:
            System.out.println(" " + c((int)514));
            this.C44_f701.d(c((int)514));
            break;
         case 5:
            C44_f703 = false;
            this.C44_f701.aN();
         }

         this.C44_f712 = var1;
         if (var1 != 5) {
            return;
         }

         var1 = 0;
      }
   }

   public final int v() {
      return this.C44_f715;
   }

   public final boolean w() {
      return this.C44_f716 >= this.C44_f717;
   }

   public final byte x() {
      return this.C44_f712;
   }

   public final byte y() {
      return this.C44_f717;
   }

   public final void e(int var1) {
      C44_f703 = true;
      if (var1 == 1) {
         this.c((byte)4);
         if (!this.B()) {
            this.c((byte)3);
            return;
         }
      } else if (var1 == 2) {
         this.c((byte)5);
      }

   }

   public final void f(int var1) {
      switch(this.C44_f712) {
      case 1:
         this.e(var1);
         return;
      case 3:
         if (var1 == 1 || var1 == 2) {
            this.c((byte)5);
         }
      case 2:
      case 4:
      default:
      }
   }

   private void l(int var1) {
      C50.a((MIDlet)GameMIDLet.C15_f217);
      C50.a(var1);
      this.C44_f719[var1][0] = 1;
      this.C44_f721 = new C3(GameMIDLet.C15_f217, C1.C1_f5, "", "", this.C44_f718[var1], (String)null, this.C44_f719[var1][0], this.C44_f720[var1][0], C50.C50_f758[var1], "");
      Display.getDisplay(GameMIDLet.C15_f217).setCurrent(this.C44_f721);
      this.C44_f721.a((C14)this);
   }

   public final void c(boolean var1) {
      this.b(var1);
   }
}
