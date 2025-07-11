package game;

import a.GameUtils;
import a.GameEngineBase;
import a.a.C20;
import a.a.C30;
import a.b.C6;
import a.b.C60;
import a.b.ResourceManager;
import a.b.C68;
import java.util.Vector;

public final class C53 extends C20 {
   private static C53 C53_f764;
   public int C53_f765;
   private int C53_f766;
   private int C53_f767;
   public byte C53_f768;
   private int C53_f769;
   private boolean C53_f770;
   public int C53_f771;
   private int C53_f772;
   private int C53_f773;
   public int C53_f774;
   public int C53_f775;
   public boolean C53_f776;
   public C41[] C53_f777;
   public int C53_f778;
   public byte[][] C53_f779;
   public byte[][] C53_f780;
   public byte[][] C53_f781;
   public byte[] C53_f782;
   public byte C53_f783;
   public byte C53_f784;
   public byte C53_f785;
   public byte C53_f786;
   public Vector C53_f787;
   public Vector C53_f788;
   public Vector C53_f789;
   public Vector C53_f790;
   public Vector C53_f791;
   public Vector C53_f792;
   public byte[] C53_f793;
   public byte[] C53_f794;
   public short[] C53_f795;
   public Vector C53_f796;
   public boolean[] C53_f797;
   public static boolean C53_f798;
   public Vector C53_f799;
   private int C53_f800;
   private int C53_f801;
   public int[] C53_f802 = new int[]{0, 16, 32, 48, 64, 76, 88};
   public int[] C53_f803 = new int[]{16, 16, 16, 16, 12, 12, 12};
   private static byte[][] C53_f804 = new byte[][]{{9, 2, 9, 3, 0}};
   private static short[][] C53_f805 = new short[][]{{0, 3, 112, 256}};
   private Vector C53_f806 = null;

   public static C53 p() {
      if (C53_f764 == null) {
         C53_f764 = new C53();
      }

      return C53_f764;
   }

   public final void q() {
      this.C53_f778 = 0;
      C53_f764 = null;
   }

   public C53() {
      this.C60_f855 = new short[3];
      this.C60_f856 = new short[3];
      this.C53_f777 = new C41[6];
      this.C53_f779 = new byte[8][2];
      this.C53_f797 = new boolean[21];
      this.C53_f787 = new Vector();
      this.C53_f788 = new Vector();
      int[] var1 = new int[]{0, 0, 1};
      this.C53_f788.addElement(var1);
      this.C53_f789 = new Vector();
      this.C53_f790 = new Vector();
      this.C53_f791 = new Vector();
      this.C53_f792 = new Vector();
      this.C53_f799 = new Vector();
      this.C53_f780 = new byte[7][];
      this.C53_f782 = new byte[7];
      this.C53_f781 = new byte[7][];
      this.C53_f795 = new short[]{-1, -1, -1, -1, -1};

      int var3;
      for(var3 = 0; var3 < this.C53_f781.length; ++var3) {
         this.C53_f781[var3] = new byte[this.C53_f803[var3]];

         for(int var2 = 0; var2 < this.C53_f781[var3].length; ++var2) {
            this.C53_f781[var3][var2] = -1;
         }
      }

      this.C53_f780[0] = new byte[16];
      this.C53_f780[1] = new byte[16];
      this.C53_f780[2] = new byte[16];
      this.C53_f780[3] = new byte[16];
      this.C53_f780[4] = new byte[12];
      this.C53_f780[5] = new byte[12];
      this.C53_f780[6] = new byte[12];
      this.C53_f793 = new byte[4];
      this.C53_f794 = new byte[4];
      this.C53_f768 = 0;

      for(var3 = 0; var3 < 8; ++var3) {
         this.C53_f779[var3][0] = 0;
         this.C53_f779[var3][1] = 0;
      }

      for(var3 = 0; var3 < 4; ++var3) {
         this.C53_f793[var3] = 0;
      }

      this.C53_f800 = 1000;
      this.C53_f801 = 0;
      this.C53_f765 = -1;
      this.C53_f776 = false;
   }

   public final void a(short[] var1) {
      if (this.C53_f765 == -1) {
         this.a(0, false);
      }

      this.C60_f861 = var1[0];
      this.C60_f862 = var1[1];
      this.h(this.C53_f765);
      super.C20_f261.applyColorEffects();
      this.a((byte)0, (byte)((byte)var1[2]));
      short var4 = var1[3];
      byte var3 = 0;
      super.C60_f855[var3] = var4;
      var4 = var1[4];
      var3 = 1;
      super.C60_f855[var3] = var4;
      var4 = var1[5];
      var3 = 2;
      super.C60_f855[var3] = var4;
      if (this.C53_f765 == -1) {
         this.h();
      }

      this.C60_f871 = 1;
      this.C53_f772 = var1[6];
      this.C53_f773 = var1[7];
      this.C53_f771 = this.D();
      if (this.C53_f768 == 1) {
         this.a(0, 107, true);
      }

      if (this.C20_f262 == null) {
         this.C20_f262 = new C20();
         this.C20_f262.a(337, false);
      }

      this.C20_f262.b(this.C60_f861, this.C60_f862);
      if (this.C20_f261.spriteSetId == 4) {
         this.C20_f262.a((byte)0, (byte)0, false);
      } else {
         this.C20_f262.a((byte)1, (byte)0, false);
      }

      this.C20_f262.c();
      Object var5 = null;
      super.C60_f868 = (C60)var5;
      this.C53_f776 = true;
   }

   public final void r() {
      if (this.o() && this.C60_f868.i() != 0) {
         this.a(((C20)this.C60_f868).C20_f261, this.C20_f261);
      } else {
         int var1;
         int var2;
         switch(this.C60_f860) {
         case 0:
            if (this.C53_f793[2] != 2 && this.N()) {
               this.a((byte)3, (byte)this.C60_f866);
               return;
            }
            break;
         case 1:
            if (this.C53_f793[2] != 2) {
               C25.C25_f318 = this.J();
               if (this.K() && this.L()) {
                  this.a((int)this.C53_f766);
                  if (this.C53_f767 < 8) {
                     this.C53_f767 += this.C53_f766;
                  } else {
                     this.C53_f767 = 4;
                  }

                  this.O();
                  return;
               }

               this.C53_f767 = 0;
               return;
            }

            int var3 = this.C60_f862;
            var2 = this.C60_f861;
            boolean var10000;
            switch(this.C60_f866) {
            case 0:
               var10000 = C68.a().b(var2, var3 - 25 + this.C53_f766);
               break;
            case 1:
               var10000 = C68.a().b(var2 + this.C53_f766, var3 - 25);
               break;
            case 2:
               var10000 = C68.a().b(var2, var3 - 25 - this.C53_f766);
               break;
            case 3:
               var10000 = C68.a().b(var2 - this.C53_f766, var3 - 25);
               break;
            default:
               var10000 = false;
            }

            if (!var10000) {
               C25.C25_f318 = this.J();
               Object var5 = null;
               super.C60_f868 = (C60)var5;

               for(var1 = 0; var1 < C25.B().C25_f287.length; ++var1) {
                  this.y(var1);
               }

               byte var6 = 0;
               this.a((int)super.C60_f856[var6]);
               if (this.C53_f767 < 8) {
                  var6 = 0;
                  this.C53_f767 += super.C60_f856[var6];
               } else {
                  this.C53_f767 = 4;
               }

               this.O();
               return;
            }
            break;
         case 2:
            if (this.K() && this.L()) {
               this.a((int)this.C53_f766);
               if (this.C53_f767 < 8) {
                  this.C53_f767 += this.C53_f766;
               } else {
                  this.C53_f767 = 4;
               }

               this.O();
               return;
            }

            this.C53_f767 = 0;
            return;
         case 3:
         default:
            break;
         case 4:
            return;
         case 5:
            if (this.C53_f769 < 16) {
               switch(this.C60_f865) {
               case 0:
               case 2:
                  if (this.C60_f865 == 2) {
                     if (this.C60_f862 > ((C18)this.C60_f868).n() - 16) {
                        this.a((int)this.C60_f865, (int)4);
                     }
                  } else if (this.C60_f862 < ((C18)this.C60_f868).n() - 16) {
                     this.a((int)this.C60_f865, (int)4);
                  }

                  if (this.C60_f863 > ((C18)this.C60_f868).m()) {
                     if (this.C60_f861 <= ((C18)this.C60_f868).m()) {
                        this.c(((C18)this.C60_f868).m());
                     } else {
                        this.a((int)3, (int)4);
                     }
                  } else if (this.C60_f863 < ((C18)this.C60_f868).m()) {
                     if (this.C60_f861 >= ((C18)this.C60_f868).m()) {
                        this.c(((C18)this.C60_f868).m());
                     } else {
                        this.a((int)1, (int)4);
                     }
                  }
                  break;
               case 1:
               case 3:
                  if (this.C60_f865 == 3) {
                     if (this.C60_f861 > ((C18)this.C60_f868).C60_f861) {
                        this.a((int)this.C60_f865, (int)4);
                     }
                  } else if (this.C60_f861 < ((C18)this.C60_f868).C60_f861) {
                     this.a((int)this.C60_f865, (int)4);
                  }

                  if (this.C60_f864 > ((C18)this.C60_f868).C60_f862 - 16) {
                     if (this.C60_f862 <= ((C18)this.C60_f868).C60_f862 - 16) {
                        var2 = ((C18)this.C60_f868).C60_f862 - 16;
                        super.C60_f862 = var2;
                     } else {
                        this.a((int)2, (int)4);
                     }
                  } else if (this.C60_f864 < ((C18)this.C60_f868).C60_f862 - 16) {
                     if (this.C60_f862 >= ((C18)this.C60_f868).C60_f862 - 16) {
                        var2 = ((C18)this.C60_f868).C60_f862 - 16;
                        super.C60_f862 = var2;
                     } else {
                        this.a((int)0, (int)4);
                     }
                  }
               }

               if (this.C53_f769 % 4 == 3) {
                  this.a((byte)1, (byte)-1, false);
               } else {
                  this.a((byte)(this.C53_f769 % 4), (byte)-1, false);
               }

               this.d((byte)(this.C53_f769 % 4));
               ++this.C53_f769;
               return;
            }

            byte var4 = 0;

            for(var2 = 0; var2 < C53_f804.length; ++var2) {
               byte[] var10001 = C53_f804[var2];
               if (C25.B().C25_f290 == var10001[0]) {
                  var10001 = C53_f804[var2];
                  if (C25.B().C25_f291 == var10001[1]) {
                     C25.B().C25_f290 = C53_f804[var2][2];
                     C25.B().C25_f291 = C53_f804[var2][3];
                     var4 = C53_f804[var2][4];
                     break;
                  }
               }
            }

            for(var2 = 0; var2 < C53_f805[var4].length / 4; ++var2) {
               if (((C18)this.C60_f868).C18_f248 >= C53_f805[var4][var2 << 2] && ((C18)this.C60_f868).C18_f248 <= C53_f805[var4][(var2 << 2) + 1]) {
                  C25.B().C25_f293 = C53_f805[var4][(var2 << 2) + 2];
                  C25.B().C25_f294 = C53_f805[var4][(var2 << 2) + 3];
                  break;
               }
            }

            C53_f798 = true;
            C25.B().C25_f295 = -1;
            GameScreenManager.getInstance().changeState((byte)9);
            return;
         case 6:
            if (this.M()) {
               this.a((int)this.b((byte)2));
               return;
            }

            this.a((int)this.c((byte)1));
            this.a((byte)0, (byte)this.C60_f866);
            return;
         case 7:
            if (((C18)this.C60_f868).C18_f233 == 0) {
               if (this.C53_f769 < 7) {
                  this.a((int)4);
                  ++this.C53_f769;
                  return;
               }

               if (this.C53_f769 == 7) {
                  if (this.C60_f866 == 3) {
                     this.a((byte)1, (byte)-1, false);
                  } else {
                     this.a(this.C60_f866, (byte)-1, false);
                  }

                  this.d(this.C60_f866);
                  ((C18)this.C60_f868).r();
                  ++this.C53_f769;
                  return;
               }
            } else if (((C18)this.C60_f868).C18_f233 == 2) {
               if (this.C53_f769 < 8 && this.C53_f769 > 0) {
                  this.a((int)4);
                  --this.C53_f769;
                  return;
               }

               if (this.C53_f769 == 8) {
                  this.a((byte)7, (byte)this.C60_f866);
                  --this.C53_f769;
                  return;
               }

               ((C18)this.C60_f868).C18_f233 = 0;
               ((C18)this.C60_f868).a((C60)null);
               this.a((C60)null);
               this.a((byte)0, (byte)this.C60_f866);
               return;
            }
            break;
         case 8:
            if (this.C53_f769 < 16) {
               if (this.C53_f769 % 4 == 3) {
                  this.a((byte)1, (byte)-1, false);
               } else {
                  this.a((byte)(this.C53_f769 % 4), (byte)-1, false);
               }

               this.d((byte)(this.C53_f769 % 4));
               ++this.C53_f769;
               return;
            }

            var1 = C25.B().C25_f287[C25.B().C25_f295].C60_f861 - C25.B().C25_f287[C25.B().C25_f295].C60_f861 % this.b((byte)2);
            var2 = C25.B().C25_f287[C25.B().C25_f295].C60_f862 - C25.B().C25_f287[C25.B().C25_f295].C60_f862 % this.b((byte)2);
            this.b(var1, var2);
            this.C20_f262.b(var1, var2);
            this.a((byte)0, (byte)C25.B().C25_f287[C25.B().C25_f295].C18_f236);
            this.a((int)32);
            C6.a().a(8);
            C6.a().a(false);
            return;
         case 9:
            if (this.C53_f769 < 16) {
               if (this.C53_f769 % 4 == 3) {
                  this.a((byte)1, (byte)-1, false);
               } else {
                  this.a((byte)(this.C53_f769 % 4), (byte)-1, false);
               }

               this.d((byte)(this.C53_f769 % 4));
               ++this.C53_f769;
               return;
            }

            var1 = C25.B().C25_f287[C25.B().C25_f295].C60_f861 - C25.B().C25_f287[C25.B().C25_f295].C60_f861 % this.b((byte)2);
            var2 = C25.B().C25_f287[C25.B().C25_f295].C60_f862 - C25.B().C25_f287[C25.B().C25_f295].C60_f862 % this.b((byte)2);
            this.b(var1, var2);
            this.C20_f262.b(var1, var2);
            this.a((byte)10, (byte)this.C60_f866);
            C6.a().a(8);
            C6.a().a(false);
            return;
         case 10:
            if (this.C53_f769 > 0) {
               if (this.C53_f769 % 4 == 3) {
                  this.a((byte)1, (byte)-1, false);
               } else {
                  this.a((byte)(this.C53_f769 % 4), (byte)-1, false);
               }

               this.d((byte)(this.C53_f769 % 4));
               --this.C53_f769;
               return;
            }

            this.a((byte)0, (byte)C25.B().C25_f287[C25.B().C25_f295].C18_f236);
            this.a((int)32);
         }

      }
   }

   public final void a(byte var1, byte var2) {
      while(true) {
         switch(var1) {
         case 0:
            if (this.C53_f793[2] != 2 && this.N()) {
               var1 = 3;
               continue;
            }

            if (var2 == 3) {
               this.a((byte)1, (byte)-1, false);
            } else {
               this.a(var2, (byte)-1, false);
            }

            super.C60_f866 = var2;
            break;
         case 1:
            if (this.C53_f793[2] != 2 && this.N()) {
               var1 = 2;
               continue;
            }

            if (super.C20_f261.getCurrentAnimationId() < 6) {
               if (var2 == 3) {
                  this.a((byte)4, (byte)-1, var2);
               } else {
                  this.a((byte)((byte)(var2 + 3)), (byte)-1, var2);
               }
            } else {
               if (var2 == 3) {
                  this.a((byte)(var1 * 3 + 1), (byte)-1, false);
               } else {
                  this.a((byte)(var1 * 3 + var2), (byte)-1, false);
               }

               super.C60_f866 = var2;
            }
            break;
         case 2:
            if (this.N()) {
               if (super.C20_f261.getCurrentAnimationId() < 9) {
                  if (var2 == 3) {
                     this.a((byte)7, (byte)-1, var2);
                  } else {
                     this.a((byte)((byte)(var2 + 6)), (byte)-1, var2);
                  }
               } else if (var2 == 3) {
                  this.a((byte)(var1 * 3 + 1), (byte)-1, false);
               } else {
                  this.a((byte)(var1 * 3 + var2), (byte)-1, false);
               }
            } else if (var2 == 3) {
               this.a((byte)(this.C60_f860 * 3 + 1), (byte)-1, false);
            } else {
               this.a((byte)(this.C60_f860 * 3 + var2), (byte)-1, false);
            }

            super.C60_f866 = var2;
            break;
         case 3:
            if (var2 == 3) {
               this.a((byte)(var1 * 3 + 1), (byte)-1, false);
            } else {
               this.a((byte)(var1 * 3 + var2), (byte)-1, false);
            }

            super.C60_f866 = var2;
            break;
         case 4:
            this.a((byte)(var1 * 3), (byte)-2, false);
            super.C60_f866 = var2;
            this.C53_f767 = 0;
            break;
         case 5:
            this.C53_f769 = 0;
            super.C60_f865 = var2;
            int var5 = this.C60_f862;
            int var4 = this.C60_f861;
            super.C60_f863 = var4;
            super.C60_f864 = var5;
            if (var2 == 3) {
               this.a((byte)1, (byte)-1, false);
            } else {
               this.a(var2, (byte)-1, false);
            }

            super.C60_f866 = var2;
            break;
         case 6:
            if (var2 == 3) {
               this.a((byte)1, (byte)-1, false);
            } else {
               this.a(var2, (byte)-1, false);
            }

            super.C60_f866 = var2;
            break;
         case 7:
            if (var2 == 3) {
               this.a((byte)4, (byte)-1, false);
            } else {
               this.a((byte)(var2 + 3), (byte)-1, false);
            }

            super.C60_f866 = var2;
            break;
         case 8:
         case 9:
            this.C53_f769 = 0;
            super.C60_f866 = var2;
         }

         this.C60_f860 = var1;
         if (this.C60_f860 != 0 && this.C60_f860 != 1) {
            if (C25.B().C25_f311 != null) {
               C25.B().C25_f311.b(false);
            }
         } else if (C25.B().C25_f311 != null) {
            C25.B().C25_f311.b(true);
            return;
         }

         return;
      }
   }

   public final boolean f(int var1) {
      return this.C53_f793[var1] != 0;
   }

   public final boolean g(int var1) {
      return this.C53_f794[var1] != 1;
   }

   public final boolean s() {
      return this.C53_f765 != 2 || C68.a().a(0, this.C60_f861 + 7, this.C60_f862 + 7) == 0 && C68.a().a(0, this.C60_f861 - 8, this.C60_f862 - 8) == 0;
   }

   public final void h(int var1) {
      if (var1 != -1) {
         this.C53_f793[var1] = 2;
         this.C20_f261.forceCleanup();
         this.a(var1 + 1, false);
         super.C20_f261.applyColorEffects();
         this.a((byte)0, (byte)this.C60_f866);
         if (this.C53_f768 == 1) {
            this.a(1, 107, true);
         }

         byte var3;
         byte var4;
         if ((this.C53_f793[var1] != 2 || var1 != 0) && (this.C53_f793[var1] != 2 || var1 != 1)) {
            var4 = 4;
            var3 = 0;
            super.C60_f856[var3] = var4;
         } else {
            var4 = 8;
            var3 = 0;
            super.C60_f856[var3] = var4;
         }

         if (this.C53_f793[2] == 2 && C25.B().C25_f311 != null) {
            C25.B().C25_f311.d();
         }

         this.C53_f765 = var1;
         var3 = 0;
         this.C53_f766 = super.C60_f856[var3];
      }
   }

   public final void t() {
      this.C20_f261.forceCleanup();
      this.a(0, false);
      super.C20_f261.applyColorEffects();

      for(int var1 = 0; var1 < 4; ++var1) {
         if (this.C53_f793[var1] == 2) {
            this.C53_f793[var1] = 1;
         }
      }

      if (this.C53_f768 == 1) {
         this.a(0, 107, true);
      }

      if (C25.B().C25_f311 != null) {
         C25.B().C25_f311.c();
      }

      byte var2 = 0;
      short var3 = super.C60_f855[var2];
      var2 = 0;
      super.C60_f856[var2] = var3;
      this.C53_f765 = -1;
   }

   public final void u() {
      this.C53_f768 = 1;
      boolean var1 = false;

      for(int var2 = 0; var2 < 4; ++var2) {
         if (this.C53_f793[var2] == 2) {
            var1 = true;
            break;
         }
      }

      if (var1) {
         this.a(1, 107, true);
      } else {
         this.a(0, 107, true);
      }
   }

   public final void v() {
      this.C53_f768 = 0;
      boolean var1 = false;

      for(int var2 = 0; var2 < 4; ++var2) {
         if (this.C53_f793[var2] == 2) {
            var1 = true;
            break;
         }
      }

      if (var1) {
         this.a(1, 100, true);
      } else {
         this.a(0, 100, true);
      }
   }

   private short J() {
      for(short var1 = 0; var1 < C25.B().C25_f287.length; ++var1) {
         if (C25.B().C25_f287[var1].k()) {
            if ((C25.B().C25_f287[var1].C20_f261.spriteSetId <= 85 || C25.B().C25_f287[var1].C20_f261.spriteSetId == 226 || C25.B().C25_f287[var1].C20_f261.spriteSetId == 92 || C25.B().C25_f287[var1].C20_f261.spriteSetId == 102 || C25.B().C25_f287[var1].C20_f261.spriteSetId == 137) && C25.B().C25_f287[var1].C18_f223 == 0 && (C25.B().C25_f287[var1].C18_f225 == 1 || C25.B().C25_f287[var1].C18_f225 == 18) && this.a(C25.B().C25_f287[var1], this.C20_f261.getCurrentFrameEvents(), C25.B().C25_f287[var1].C20_f261.getCurrentFrameEvents())) {
               if (C25.B().C25_f287[var1].v() == 0) {
                  C25.B().a((byte)13, C25.B().C25_f287[var1].C60_f861, C25.B().C25_f287[var1].C60_f862 - 40, C25.B().C25_f287[var1]);
                  if (C25.B().C25_f287[var1].C18_f246 != null) {
                     C25.B().C25_f287[var1].C18_f246.d();
                  }
               } else if (C25.B().C25_f287[var1].v() == 1) {
                  C25.B().a((byte)13, C25.B().C25_f287[var1].C60_f861, C25.B().C25_f287[var1].C60_f862 - 40, C25.B().C25_f287[var1]);
                  if (C7.C7_f64 != null && C7.C7_f64.size() > 0) {
                     for(int var2 = 0; var2 < C7.C7_f64.size(); ++var2) {
                        if (((C20)C7.C7_f64.elementAt(var2)).C60_f868.equals(C25.B().C25_f287[var1])) {
                           ((C20)C7.C7_f64.elementAt(var2)).d();
                           break;
                        }
                     }
                  }
               } else {
                  C25.B().a((byte)13, C25.B().C25_f287[var1].C60_f861, C25.B().C25_f287[var1].C60_f862 - 40, C25.B().C25_f287[var1]);
                  if (C25.B().C25_f287[var1].C18_f226 != 0) {
                     C25.B().C25_f287[var1].x();
                  }
               }

               return var1;
            }

            if (C25.B().C25_f287[var1].C18_f223 == 2 && this.a(C25.B().C25_f287[var1], this.C20_f261.getCurrentFrameEvents(), C25.B().C25_f287[var1].C20_f261.getCurrentFrameEvents())) {
               C25.B().m(var1);
            }
         }
      }

      C25.B().D();
      C7.C7_f68 = false;
      return -1;
   }

   private boolean y(int var1) {
      switch(C25.B().C25_f287[var1].C18_f223) {
      case 3:
         short[] var2;
         short var3 = (var2 = C25.B().C25_f287[var1].C20_f261.getCurrentFrameEvents())[0];
         short var4 = var2[1];
         short var5 = (short)(var2[2] + 16);
         short var6 = (short)(var2[3] + 16);
         if (C25.B().C25_f287[var1].C18_f224 && this.a(C25.B().C25_f287[var1], this.C20_f261.getCurrentFrameEvents(), new short[]{var3, var4, var5, var6})) {
            C18 var7 = C25.B().C25_f287[var1];
            super.C60_f868 = var7;
         }
      default:
         return true;
      }
   }

   private boolean K() {
      C18 var4 = null;
      super.C60_f868 = var4;
      boolean var1 = true;
      if (this.C53_f806 != null) {
         this.C53_f806.removeAllElements();
      }

      for(int var2 = 0; var2 < C25.B().C25_f287.length; ++var2) {
         this.y(var2);
         if (C25.B().C25_f287[var2].C18_f224 && this.a(C25.B().C25_f287[var2], this.C20_f261.getCurrentFrameEvents(), C25.B().C25_f287[var2].C20_f261.getCurrentFrameEvents())) {
            switch(C25.B().C25_f287[var2].C18_f223) {
            case 0:
               switch(C25.B().C25_f287[var2].C18_f225) {
               case 0:
                  return false;
               case 1:
               case 2:
               case 3:
               case 12:
               case 13:
               default:
                  continue;
               case 4:
               case 11:
                  if (C25.B().C25_f287[var2].i() != 2 && C25.B().C25_f287[var2].k()) {
                     var4 = C25.B().C25_f287[var2];
                     super.C60_f868 = var4;
                     return false;
                  }
                  continue;
               case 5:
                  if (C25.B().C25_f287[var2].i() != 2) {
                     if (this.C53_f779[5][0] == 2) {
                        var4 = C25.B().C25_f287[var2];
                        super.C60_f868 = var4;
                     }

                     return false;
                  }
               case 6:
                  if (C25.B().C25_f287[var2].i() != 2) {
                     if (this.C53_f793[3] != 2) {
                        if (this.C53_f779[2][0] == 2) {
                           var4 = C25.B().C25_f287[var2];
                           super.C60_f868 = var4;
                           if (this.C53_f806 == null) {
                              this.C53_f806 = new Vector();
                           }

                           this.C53_f806.addElement(C25.B().C25_f287[var2]);
                           C25.B().C25_f287[var2].f((int)20);
                        }

                        var1 = false;
                     } else {
                        C25.B().C25_f287[var2].a((byte)1);
                        C25.B().C25_f285.a(C25.B().C25_f287[var2], 2);
                     }
                  }
                  continue;
               case 7:
                  if (C25.B().C25_f287[var2].i() != 2) {
                     if (this.C53_f793[3] != 2) {
                        if (this.C53_f779[1][0] == 2) {
                           var4 = C25.B().C25_f287[var2];
                           super.C60_f868 = var4;
                           if (this.C53_f806 == null) {
                              this.C53_f806 = new Vector();
                           }

                           this.C53_f806.addElement(C25.B().C25_f287[var2]);
                           C25.B().C25_f287[var2].f((int)30);
                        }

                        var1 = false;
                     } else {
                        C25.B().C25_f287[var2].a((byte)1);
                        C25.B().C25_f285.a(C25.B().C25_f287[var2], 2);
                     }
                  }
                  continue;
               case 8:
                  if (C25.B().C25_f287[var2].k()) {
                     if ((C18)C25.B().C25_f287[var2].C60_f868 != null && ((C18)C25.B().C25_f287[var2].C60_f868).C18_f235 > ((C18)C25.B().C25_f287[var2].C60_f868).C18_f234) {
                        return false;
                     }

                     for(int var5 = 0; var5 < C25.B().C25_f287.length; ++var5) {
                        if (C25.B().C25_f287[var5].C18_f224 && !C25.B().C25_f287[var5].equals(C25.B().C25_f287[var2]) && C25.B().C25_f287[var5].C18_f223 == 0 && (C25.B().C25_f287[var5].C18_f225 == 8 || C25.B().C25_f287[var5].C18_f225 == 11)) {
                           switch(this.C60_f866) {
                           case 0:
                              if (GameUtils.checkCollisionBetweenShortArrays(C25.B().C25_f287[var2].C60_f861, C25.B().C25_f287[var2].C60_f862 + 8, C25.B().C25_f287[var5].C60_f861, C25.B().C25_f287[var5].C60_f862, C25.B().C25_f287[var2].C20_f261.getCurrentFrameEvents(), C25.B().C25_f287[var5].C20_f261.getCurrentFrameEvents())) {
                                 return false;
                              }
                              break;
                           case 1:
                              if (GameUtils.checkCollisionBetweenShortArrays(C25.B().C25_f287[var2].C60_f861 + 8, C25.B().C25_f287[var2].C60_f862, C25.B().C25_f287[var5].C60_f861, C25.B().C25_f287[var5].C60_f862, C25.B().C25_f287[var2].C20_f261.getCurrentFrameEvents(), C25.B().C25_f287[var5].C20_f261.getCurrentFrameEvents())) {
                                 return false;
                              }
                              break;
                           case 2:
                              if (GameUtils.checkCollisionBetweenShortArrays(C25.B().C25_f287[var2].C60_f861, C25.B().C25_f287[var2].C60_f862 - 8, C25.B().C25_f287[var5].C60_f861, C25.B().C25_f287[var5].C60_f862, C25.B().C25_f287[var2].C20_f261.getCurrentFrameEvents(), C25.B().C25_f287[var5].C20_f261.getCurrentFrameEvents())) {
                                 return false;
                              }
                              break;
                           case 3:
                              if (GameUtils.checkCollisionBetweenShortArrays(C25.B().C25_f287[var2].C60_f861 - 8, C25.B().C25_f287[var2].C60_f862, C25.B().C25_f287[var5].C60_f861, C25.B().C25_f287[var5].C60_f862, C25.B().C25_f287[var2].C20_f261.getCurrentFrameEvents(), C25.B().C25_f287[var5].C20_f261.getCurrentFrameEvents())) {
                                 return false;
                              }
                           }
                        }
                     }

                     C25.B().C25_f287[var2].a((byte)1);
                     C18 var10000 = C25.B().C25_f287[var2];
                     byte var6 = this.C60_f866;
                     var10000.C60_f866 = var6;
                     return false;
                  }
                  continue;
               case 9:
                  if (C68.a().a(0, this.C60_f861, this.C60_f862) != 2 && C68.a().a(0, this.C60_f861, this.C60_f862) != 1) {
                     if (this.C60_f866 == 3 || this.C60_f866 == 1) {
                        this.C53_f769 = 0;
                        this.a((byte)7, (byte)this.C60_f866);
                        C25.B().C25_f287[var2].C60_f868 = this;
                        var4 = C25.B().C25_f287[var2];
                        super.C60_f868 = var4;
                        return false;
                     }
                     continue;
                  }

                  return false;
               case 10:
                  if (C68.a().a(0, this.C60_f861, this.C60_f862) == 2 || C68.a().a(0, this.C60_f861, this.C60_f862) == 1) {
                     return false;
                  }

                  if (this.C60_f866 == 0 || this.C60_f866 == 2) {
                     this.C53_f769 = 0;
                     this.a((byte)7, (byte)this.C60_f866);
                     C25.B().C25_f287[var2].C60_f868 = this;
                     var4 = C25.B().C25_f287[var2];
                     super.C60_f868 = var4;
                     return false;
                  }
                  continue;
               case 14:
                  return false;
               case 15:
                  if (C25.B().C25_f287[var2].i() != 2) {
                     if (this.C53_f797[6]) {
                        var4 = C25.B().C25_f287[var2];
                        super.C60_f868 = var4;
                        return false;
                     }

                     C25.B().C25_f287[var2].a((byte)1);
                     C25.B().C25_f285.a(C25.B().C25_f287[var2], 2);
                  }
                  continue;
               case 16:
                  var4 = C25.B().C25_f287[var2];
                  super.C60_f868 = var4;
                  continue;
               }
            case 1:
               if (C25.B().C25_f287[var2].C18_f225 == 3) {
                  return false;
               }
            case 2:
            default:
               break;
            case 3:
               return false;
            }
         }
      }

      return var1;
   }

   private boolean L() {
      byte var7 = 0;
      this.C53_f766 = super.C60_f856[var7];
      int var1 = this.C60_f861 - 8;
      int var2 = this.C60_f862 - 8;
      int var3 = this.C60_f861 + 7;
      int var4 = this.C60_f862 + 7;
      byte[] var5 = new byte[]{-1, -1, -1, -1, -1};
      switch(this.C60_f866) {
      case 2:
         if (C68.a().b(this.C60_f861, var2 - this.C53_f766)) {
            return false;
         } else {
            var5[0] = C68.a().a(0, var1, var2 - this.C53_f766);
            var5[1] = C68.a().a(0, var3, var2 - this.C53_f766);
            var5[2] = C68.a().a(0, this.C60_f861, var2 - this.C53_f766);
            if (this.a(var5[0]) || this.a(var5[1])) {
               if (!this.a(var5[0])) {
                  var7 = 1;
                  this.C53_f766 = super.C60_f856[var7];
                  return this.c((byte)var5[1], (byte)1);
               } else {
                  if (!this.a(var5[1])) {
                     var7 = 1;
                     this.C53_f766 = super.C60_f856[var7];
                     return this.c((byte)var5[0], (byte)3);
                  }

                  return this.c((byte)var5[2], (byte)2);
               }
            } else if (C68.a().a(0, this.C60_f861, this.C60_f862) == 3) {
               return false;
            } else {
               var5[0] = C68.a().a(0, this.C60_f861 - 16, var2 - this.C53_f766);
               var5[1] = C68.a().a(0, this.C60_f861 + 16, var2 - this.C53_f766);
               var5[3] = C68.a().a(0, this.C60_f861 - 16, this.C60_f862);
               var5[4] = C68.a().a(0, this.C60_f861 + 16, this.C60_f862);
               var7 = 1;
               this.C53_f766 = super.C60_f856[var7];
               if (!this.a(var5[0])) {
                  return this.c((byte)var5[1], (byte)1);
               } else if (!this.a(var5[1])) {
                  return this.c((byte)var5[0], (byte)3);
               } else if (this.a(var5[4])) {
                  return this.c((byte)var5[1], (byte)1);
               } else if (this.a(var5[3])) {
                  return this.c((byte)var5[0], (byte)3);
               }
            }
         }
      case 0:
         if (C68.a().b(this.C60_f861, var4 + this.C53_f766)) {
            return false;
         } else {
            var5[0] = C68.a().a(0, var1, var4 + this.C53_f766);
            var5[1] = C68.a().a(0, var3, var4 + this.C53_f766);
            var5[2] = C68.a().a(0, this.C60_f861, var4 + this.C53_f766);
            if (this.a(var5[0]) || this.a(var5[1])) {
               if (!this.a(var5[0])) {
                  var7 = 1;
                  this.C53_f766 = super.C60_f856[var7];
                  return this.c((byte)var5[1], (byte)1);
               } else if (!this.a(var5[1])) {
                  var7 = 1;
                  this.C53_f766 = super.C60_f856[var7];
                  return this.c((byte)var5[0], (byte)3);
               } else {
                  return this.c((byte)var5[2], (byte)0);
               }
            } else if (C68.a().a(0, this.C60_f861, this.C60_f862) == 3) {
               return false;
            } else {
               var5[0] = C68.a().a(0, var1 - 16, var4 + this.C53_f766);
               var5[1] = C68.a().a(0, var3 + 16, var4 + this.C53_f766);
               var5[3] = C68.a().a(0, this.C60_f861 - 16, this.C60_f862);
               var5[4] = C68.a().a(0, this.C60_f861 + 16, this.C60_f862);
               var7 = 1;
               this.C53_f766 = super.C60_f856[var7];
               if (!this.a(var5[0])) {
                  return this.c((byte)var5[1], (byte)1);
               } else if (!this.a(var5[1])) {
                  return this.c((byte)var5[0], (byte)3);
               } else if (this.a(var5[4])) {
                  return this.c((byte)var5[1], (byte)1);
               } else if (this.a(var5[3])) {
                  return this.c((byte)var5[0], (byte)3);
               }
            }
         }
      case 3:
         if (C68.a().b(var1 - this.C53_f766, this.C60_f862)) {
            return false;
         } else {
            var5[0] = C68.a().a(0, var1 - this.C53_f766, var2);
            var5[1] = C68.a().a(0, var1 - this.C53_f766, var4);
            var5[2] = C68.a().a(0, var1 - this.C53_f766, this.C60_f862);
            if (this.a(var5[0]) || this.a(var5[1])) {
               if (!this.a(var5[0])) {
                  var7 = 1;
                  this.C53_f766 = super.C60_f856[var7];
                  return this.c((byte)var5[1], (byte)0);
               } else {
                  if (!this.a(var5[1])) {
                     var7 = 1;
                     this.C53_f766 = super.C60_f856[var7];
                     return this.c((byte)var5[0], (byte)2);
                  }

                  return this.c((byte)var5[2], (byte)3);
               }
            } else if (C68.a().a(0, this.C60_f861, this.C60_f862) == 3) {
               return false;
            } else {
               var5[0] = C68.a().a(0, var1 - this.C53_f766, var2 - 16);
               var5[1] = C68.a().a(0, var1 - this.C53_f766, var4 + 16);
               var5[3] = C68.a().a(0, this.C60_f861, this.C60_f862 - 16);
               var5[4] = C68.a().a(0, this.C60_f861, this.C60_f862 + 16);
               var7 = 1;
               this.C53_f766 = super.C60_f856[var7];
               if (!this.a(var5[0])) {
                  return this.c((byte)var5[1], (byte)0);
               } else if (!this.a(var5[1])) {
                  return this.c((byte)var5[0], (byte)2);
               } else if (this.a(var5[4])) {
                  return this.c((byte)var5[1], (byte)0);
               } else if (this.a(var5[3])) {
                  return this.c((byte)var5[0], (byte)2);
               }
            }
         }
      case 1:
         if (C68.a().b(var3 + this.C53_f766, this.C60_f862)) {
            return false;
         } else {
            var5[0] = C68.a().a(0, var3 + this.C53_f766, var2);
            var5[1] = C68.a().a(0, var3 + this.C53_f766, var4);
            var5[2] = C68.a().a(0, var3 + this.C53_f766, this.C60_f862);
            if (this.a(var5[0]) || this.a(var5[1])) {
               if (!this.a(var5[0])) {
                  var7 = 1;
                  this.C53_f766 = super.C60_f856[var7];
                  return this.c((byte)var5[1], (byte)0);
               } else {
                  if (!this.a(var5[1])) {
                     var7 = 1;
                     this.C53_f766 = super.C60_f856[var7];
                     return this.c((byte)var5[0], (byte)2);
                  }

                  return this.c((byte)var5[2], (byte)1);
               }
            } else if (C68.a().a(0, this.C60_f861, this.C60_f862) == 3) {
               return false;
            } else {
               var5[0] = C68.a().a(0, var3 + this.C53_f766, var2 - 16);
               var5[1] = C68.a().a(0, var3 + this.C53_f766, var4 + 16);
               var5[3] = C68.a().a(0, this.C60_f861, this.C60_f862 - 16);
               var5[4] = C68.a().a(0, this.C60_f861, this.C60_f862 + 16);
               var7 = 1;
               this.C53_f766 = super.C60_f856[var7];
               if (!this.a(var5[0])) {
                  return this.c((byte)var5[1], (byte)0);
               } else if (!this.a(var5[1])) {
                  return this.c((byte)var5[0], (byte)2);
               } else if (this.a(var5[4])) {
                  return this.c((byte)var5[1], (byte)0);
               } else if (this.a(var5[3])) {
                  return this.c((byte)var5[0], (byte)2);
               }
            }
         }
      default:
         return true;
      }
   }

   private boolean a(byte var1) {
      switch(var1) {
      case 1:
         return false;
      case 2:
         if (this.C53_f779[3][0] != 2) {
            return false;
         }

         return true;
      default:
         return true;
      }
   }

   public final boolean a(C18 var1, short[] var2, short[] var3) {
      if (var3 == null) {
         return false;
      } else {
         byte var5;
         switch(this.C60_f866) {
         case 0:
            if (var1.C18_f225 == 14) {
               var5 = 0;
               return a(var1, var3, var2, this.C60_f861, this.C60_f862 + super.C60_f856[var5]);
            }

            var5 = 0;
            if (GameUtils.checkCollisionBetweenShortArrays(this.C60_f861, this.C60_f862 + super.C60_f856[var5], var1.C60_f861, var1.C60_f862, var2, var3)) {
               return true;
            }
            break;
         case 1:
            if (var1.C18_f225 == 14) {
               var5 = 0;
               return a(var1, var3, var2, this.C60_f861 + super.C60_f856[var5], this.C60_f862);
            }

            var5 = 0;
            if (GameUtils.checkCollisionBetweenShortArrays(this.C60_f861 + super.C60_f856[var5], this.C60_f862, var1.C60_f861, var1.C60_f862, var2, var3)) {
               return true;
            }
            break;
         case 2:
            if (var1.C18_f225 == 14) {
               var5 = 0;
               return a(var1, var3, var2, this.C60_f861, this.C60_f862 - super.C60_f856[var5]);
            }

            var5 = 0;
            if (GameUtils.checkCollisionBetweenShortArrays(this.C60_f861, this.C60_f862 - super.C60_f856[var5], var1.C60_f861, var1.C60_f862, var2, var3)) {
               return true;
            }
            break;
         case 3:
            if (var1.C18_f225 == 14) {
               var5 = 0;
               return a(var1, var3, var2, this.C60_f861 - super.C60_f856[var5], this.C60_f862);
            }

            var5 = 0;
            if (GameUtils.checkCollisionBetweenShortArrays(this.C60_f861 - super.C60_f856[var5], this.C60_f862, var1.C60_f861, var1.C60_f862, var2, var3)) {
               return true;
            }
         }

         return false;
      }
   }

   private static boolean a(C18 var0, short[] var1, short[] var2, int var3, int var4) {
      switch(var0.C20_f261.getCurrentAnimationId()) {
      case 0:
         if (GameUtils.checkCollisionWithShortArray(var0.C60_f861 + var1[0], var0.C60_f862 + var1[1], var1[2], var1[3] + (var0.C18_f234 << 4), var3, var4, var2)) {
            return true;
         }
         break;
      case 1:
         if (GameUtils.checkCollisionWithShortArray(var0.C60_f861 + var1[0], var0.C60_f862 + var1[1], var1[2] + (var0.C18_f234 << 4), var1[3], var3, var4, var2)) {
            return true;
         }
         break;
      case 2:
         if (GameUtils.checkCollisionWithShortArray(var0.C60_f861 + var1[0], var0.C60_f862 + var1[1] - (var0.C18_f234 << 4), var1[2], var1[3] + (var0.C18_f234 << 4), var3, var4, var2)) {
            return true;
         }
         break;
      case 3:
         if (GameUtils.checkCollisionWithShortArray(var0.C60_f861 + var1[0] - (var0.C18_f234 << 4), var0.C60_f862 + var1[1], var1[2] + (var0.C18_f234 << 4), var1[3], var3, var4, var2)) {
            return true;
         }
      }

      return false;
   }

   private boolean M() {
      boolean var1 = true;
      switch(this.C60_f866) {
      case 0:
         var1 = this.a(C68.a().a(0, this.C60_f861, this.C60_f862 + 16));
         break;
      case 1:
         var1 = this.a(C68.a().a(0, this.C60_f861 + 16, this.C60_f862));
         break;
      case 2:
         var1 = this.a(C68.a().a(0, this.C60_f861, this.C60_f862 - 16));
         break;
      case 3:
         var1 = this.a(C68.a().a(0, this.C60_f861 - 16, this.C60_f862));
      }

      return var1 && C68.a().a(0, this.C60_f861, this.C60_f862) != 3 ? false : var1;
   }

   private boolean N() {
      boolean var1 = true;
      C68 var10001;
      byte var2;
      switch(this.C60_f866) {
      case 0:
         var10001 = C68.a();
         var2 = 0;
         var1 = this.a(var10001.a(0, this.C60_f861, this.C60_f862 + super.C60_f856[var2]));
         break;
      case 1:
         var10001 = C68.a();
         var2 = 0;
         var1 = this.a(var10001.a(0, this.C60_f861 + super.C60_f856[var2], this.C60_f862));
         break;
      case 2:
         var10001 = C68.a();
         var2 = 0;
         var1 = this.a(var10001.a(0, this.C60_f861, this.C60_f862 - super.C60_f856[var2]));
         break;
      case 3:
         var10001 = C68.a();
         var2 = 0;
         var1 = this.a(var10001.a(0, this.C60_f861 - super.C60_f856[var2], this.C60_f862));
      }

      return var1 && C68.a().a(0, this.C60_f861, this.C60_f862) != 2 ? false : var1;
   }

   private boolean c(byte var1, byte var2) {
      switch(var1) {
      case -1:
      case 0:
         this.a((byte)1, (byte)var2);
         break;
      case 1:
         if (C68.a().a(0, this.C60_f861, this.C60_f862) == 2) {
            this.a((byte)2, (byte)var2);
         }

         return false;
      case 2:
         if (!this.a((byte)2)) {
            return false;
         }

         this.a((byte)2, (byte)var2);
         break;
      case 3:
         this.a((byte)6, (byte)var2);
      case 4:
      }

      return true;
   }

   public final void w() {
      if (((C18)this.C60_f868).i() == 1) {
         C25.B().gameController.az();
         C25.B().gameController.b("Bảo rương này đã trống");
      } else {
         String var1;
         if (((C18)this.C60_f868).C18_f225 == 0) {
            ((C18)this.C60_f868).a((byte)1);
            if (this.a((int)((C18)this.C60_f868).C18_f242, ((C18)this.C60_f868).C18_f240, (byte)((C18)this.C60_f868).C18_f241)) {
               this.c(((C18)this.C60_f868).C18_f242, ((C18)this.C60_f868).C18_f240, (byte)((C18)this.C60_f868).C18_f241);
               var1 = null;
               if (((C18)this.C60_f868).C18_f241 == 0) {
                  var1 = GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[4][((C18)this.C60_f868).C18_f242][0]);
               } else if (((C18)this.C60_f868).C18_f241 == 2) {
                  var1 = GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[3][((C18)this.C60_f868).C18_f242][0]);
               }

               C25.B().gameController.a((String)("Đạt được: " + var1), ((C18)this.C60_f868).C18_f240);
            } else {
               C25.B().gameController.ay();
            }

            this.a((byte)0, (byte)this.C60_f866);
         } else {
            if (((C18)this.C60_f868).C18_f225 == 1) {
               if (this.b((int)17, (int)1, (byte)2)) {
                  ((C18)this.C60_f868).a((byte)1);
                  this.d(17, 1, (byte)2);
                  if (this.a((int)((C18)this.C60_f868).C18_f242, ((C18)this.C60_f868).C18_f240, (byte)((C18)this.C60_f868).C18_f241)) {
                     this.c(((C18)this.C60_f868).C18_f242, ((C18)this.C60_f868).C18_f240, (byte)((C18)this.C60_f868).C18_f241);
                     var1 = null;
                     if (((C18)this.C60_f868).C18_f241 == 0) {
                        var1 = GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[4][((C18)this.C60_f868).C18_f242][0]);
                     } else if (((C18)this.C60_f868).C18_f241 == 2) {
                        var1 = GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[3][((C18)this.C60_f868).C18_f242][0]);
                     }

                     C25.B().gameController.a((String)("Đạt được: " + var1), ((C18)this.C60_f868).C18_f240);
                  } else {
                     C25.B().gameController.ay();
                  }
               } else {
                  C25.B().gameController.ax();
               }

               this.a((byte)0, (byte)this.C60_f866);
            }

         }
      }
   }

   public final boolean x() {
      if (this.C60_f868 == null) {
         return false;
      } else if (((C18)this.C60_f868).i() != 0) {
         return false;
      } else {
         C7.C7_f69 = true;
         if (((C18)this.C60_f868).C18_f225 != 7 && ((C18)this.C60_f868).C18_f225 != 6) {
            if (((C18)this.C60_f868).C18_f225 != 16) {
               ((C18)this.C60_f868).a((byte)1);
            }
         } else {
            for(int var1 = 0; var1 < this.C53_f806.size(); ++var1) {
               C18 var2;
               (var2 = (C18)this.C53_f806.elementAt(var1)).a((byte)1);
               var2.y();
            }

            this.C53_f806.removeAllElements();
         }

         return true;
      }
   }

   private static boolean a(int var0, int var1, Vector var2) {
      for(int var4 = 0; var4 < var2.size(); ++var4) {
         int[] var3;
         if ((var3 = (int[])var2.elementAt(var4))[0] == var0) {
            if (var3[1] < 99) {
               return true;
            }

            return false;
         }
      }

      if (var1 > 99) {
         return false;
      } else {
         return true;
      }
   }

   private static boolean b(int var0, int var1, Vector var2) {
      for(int var4 = 0; var4 < var2.size(); ++var4) {
         int[] var3;
         if ((var3 = (int[])var2.elementAt(var4))[0] == var0) {
            if (var3[1] - var1 >= 0) {
               return true;
            }

            return false;
         }
      }

      return false;
   }

   private static boolean c(int var0, int var1, Vector var2) {
      int[] var3;
      for(int var4 = 0; var4 < var2.size(); ++var4) {
         if ((var3 = (int[])var2.elementAt(var4))[0] == var0) {
            var3[1] += var1;
            if (var3[1] >= 99) {
               var3[1] = 99;
            }

            return true;
         }
      }

      var3 = new int[]{var0, var1, 0};
      var2.addElement(var3);
      return false;
   }

   private static boolean d(int var0, int var1, Vector var2) {
      for(int var4 = 0; var4 < var2.size(); ++var4) {
         int[] var3;
         if ((var3 = (int[])var2.elementAt(var4))[0] == var0) {
            var3[1] -= var1;
            if (var3[1] <= 0 && var3[2] == 0) {
               var2.removeElementAt(var4);
            }

            return true;
         }
      }

      return false;
   }

   public final boolean i(int var1) {
      int var2 = 0;

      for(int var3 = 0; var3 < this.C53_f778; ++var3) {
         if (var3 != var1 && this.C53_f777[var3].T()) {
            ++var2;
         }
      }

      if (var2 > 0) {
         return true;
      } else {
         return false;
      }
   }

   public final boolean a(int var1, int var2, byte var3) {
      switch(var3) {
      case 0:
         if (ResourceManager.gameDatabase[4][var1][5] == 0) {
            return a(var1, var2, this.C53_f788);
         }

         return a(var1, var2, this.C53_f787);
      case 2:
         if (var1 >= 12) {
            return a(var1, var2, this.C53_f790);
         } else {
            var2 = var1;
            C53 var4 = this;

            for(int var5 = 0; var5 < var4.C53_f789.size(); ++var5) {
               if (((int[])var4.C53_f789.elementAt(var5))[0] == var2) {
                  return false;
               }
            }

            return true;
         }
      default:
         return false;
      }
   }

   public final boolean b(int var1, int var2, byte var3) {
      switch(var3) {
      case 0:
         if (ResourceManager.gameDatabase[4][var1][5] == 0) {
            return b(var1, var2, this.C53_f788);
         }

         return b(var1, var2, this.C53_f787);
      case 2:
         return b(var1, var2, this.C53_f790);
      default:
         return false;
      }
   }

   public final boolean c(int var1, int var2, byte var3) {
      switch(var3) {
      case 0:
         if (ResourceManager.gameDatabase[4][var1][5] == 0) {
            return c(var1, var2, this.C53_f788);
         }

         return c(var1, var2, this.C53_f787);
      case 2:
         if (var1 >= 12) {
            if (var1 == 17) {
               return c(var1, var2 * 5, this.C53_f790);
            }

            return c(var1, var2, this.C53_f790);
         }

         int[] var4 = new int[]{var1, 0, 0};
         this.C53_f789.addElement(var4);
         return true;
      default:
         return false;
      }
   }

   public final boolean d(int var1, int var2, byte var3) {
      switch(var3) {
      case 0:
         if (ResourceManager.gameDatabase[4][var1][5] == 0) {
            return d(var1, var2, this.C53_f788);
         }

         return d(var1, var2, this.C53_f787);
      case 2:
         return d(var1, var2, this.C53_f790);
      default:
         return false;
      }
   }

   public final int a(int var1, byte var2) {
      int var3;
      int[] var4;
      switch(var2) {
      case 0:
         if (ResourceManager.gameDatabase[4][var1][5] == 0) {
            for(var3 = 0; var3 < this.C53_f788.size(); ++var3) {
               if ((var4 = (int[])this.C53_f788.elementAt(var3))[0] == var1) {
                  return var4[1];
               }
            }

            return 0;
         } else {
            for(var3 = 0; var3 < this.C53_f787.size(); ++var3) {
               if ((var4 = (int[])this.C53_f787.elementAt(var3))[0] == var1) {
                  return var4[1];
               }
            }

            return 0;
         }
      case 2:
         for(var3 = 0; var3 < this.C53_f790.size(); ++var3) {
            if ((var4 = (int[])this.C53_f790.elementAt(var3))[0] == var1) {
               return var4[1];
            }
         }
      }

      return 0;
   }

   public final void y() {
      if (this.C53_f796 == null) {
         this.C53_f796 = new Vector();
      } else {
         this.C53_f796.removeAllElements();
      }

      int var1;
      int[] var2;
      for(var1 = 0; var1 < this.C53_f788.size(); ++var1) {
         var2 = (int[])this.C53_f788.elementAt(var1);
         if (ResourceManager.gameDatabase[4][var2[0]][4] == 0) {
            this.C53_f796.addElement(var2);
         }
      }

      for(var1 = 0; var1 < this.C53_f787.size(); ++var1) {
         var2 = (int[])this.C53_f787.elementAt(var1);
         if (ResourceManager.gameDatabase[4][var2[0]][4] == 0) {
            this.C53_f796.addElement(var2);
         }
      }

   }

   public final boolean j(int var1) {
      int[] var2;
      if (var1 == 0) {
         var2 = new int[]{var1, 0, 0};
      } else {
         var2 = new int[]{var1, 1, 0};
         if (var1 == 1 || var1 == 2 || var1 == 3 || var1 == 4) {
            this.C53_f793[var1 - 1] = 1;
         }
      }

      this.C53_f791.addElement(var2);
      return true;
   }

   public final void c(int var1, int var2) {
      int[] var3;
      for(int var4 = 0; var4 < this.C53_f791.size(); ++var4) {
         if ((var3 = (int[])this.C53_f791.elementAt(var4))[0] == var1) {
            var3[2] += var2;
            if (var3[2] >= 99) {
               var3[2] = 99;
            }

            return;
         }
      }

      var3 = new int[]{var1, 0, var2};
      this.C53_f791.addElement(var3);
   }

   public final int d(int var1, int var2) {
      if (0 >= this.C53_f791.size()) {
         return var2 > 99 ? 1 : -1;
      } else {
         int[] var3;
         if ((var3 = (int[])this.C53_f791.elementAt(0))[0] != var1 || var1 != 7 && var1 != 9 && var1 != 8) {
            return -1;
         } else {
            return var3[1] + var2 <= 99 ? 0 : 1;
         }
      }
   }

   public final boolean e(int var1, int var2) {
      for(int var4 = 0; var4 < this.C53_f791.size(); ++var4) {
         int[] var3;
         if ((var3 = (int[])this.C53_f791.elementAt(var4))[0] == var1 && (var1 == 7 || var1 == 9 || var1 == 8)) {
            this.C53_f777[var2].i((byte)var1);
            if (var3[2] > 0) {
               int var10002 = var3[2]--;
               this.C53_f791.setElementAt(new int[]{var3[0], 0, var3[2]}, var4);
            } else if (var3[2] <= 0) {
               this.C53_f791.removeElementAt(var4);
            }

            return true;
         }

         if (var3[0] == var1 && var3[1] == 0) {
            this.C53_f791.setElementAt(new int[]{var3[0], 1, var3[2]}, var4);
            break;
         }
      }

      return false;
   }

   public final void k(int var1) {
      for(int var3 = 0; var3 < this.C53_f791.size(); ++var3) {
         int[] var2;
         if ((var2 = (int[])this.C53_f791.elementAt(var3))[0] == var1 && var2[1] == 1) {
            this.C53_f791.setElementAt(new int[]{var2[0], 0, var2[2]}, var3);
            return;
         }
      }

   }

   public final boolean l(int var1) {
      for(int var2 = 0; var2 < this.C53_f791.size(); ++var2) {
         int[] var3;
         if ((var3 = (int[])this.C53_f791.elementAt(var2))[0] == var1 && var3[1] == 1) {
            return true;
         }
      }

      return false;
   }

   public final boolean m(int var1) {
      if (var1 == -1) {
         return false;
      } else {
         for(int var2 = 0; var2 < this.C53_f789.size(); ++var2) {
            int[] var3;
            if ((var3 = (int[])this.C53_f789.elementAt(var2))[0] == var1) {
               var3[1] = 0;
               return true;
            }
         }

         return false;
      }
   }

   public final void f(int var1, int var2) {
      C41 var10000 = this.C53_f777[var2];
      byte var6 = 5;
      byte var3;
      if (var10000.C60_f855[var6] >= 0) {
         C41 var10001 = this.C53_f777[var2];
         var6 = 5;
         this.m(var10001.C60_f855[var6]);
         var10000 = this.C53_f777[var2];
         var3 = -1;
         var6 = 5;
         var10000.C60_f855[var6] = var3;
      }

      int var4 = var1;
      C53 var7 = this;
      int var5 = 0;

      int[] var11;
      boolean var12;
      while(true) {
         if (var5 >= var7.C53_f789.size()) {
            var12 = false;
            break;
         }

         if ((var11 = (int[])var7.C53_f789.elementAt(var5))[0] == var4 && var11[1] == 1) {
            var12 = true;
            break;
         }

         ++var5;
      }

      if (var12) {
         this.m(var1);
         boolean var8 = false;

         for(var4 = 0; var4 < this.C53_f778; ++var4) {
            var10000 = this.C53_f777[var4];
            var6 = 5;
            if (var10000.C60_f855[var6] == var1) {
               var10000 = this.C53_f777[var4];
               var3 = -1;
               var6 = 5;
               var10000.C60_f855[var6] = var3;
               var8 = true;
               break;
            }
         }

         if (!var8) {
            for(var4 = 0; var4 < this.C53_f792.size(); ++var4) {
               int[] var9;
               if ((var9 = (int[])this.C53_f792.elementAt(var2))[2] == var1) {
                  var9[2] = -1;
                  break;
               }
            }
         }
      }

      var4 = var1;
      var7 = this;
      var5 = 0;

      while(true) {
         if (var5 >= var7.C53_f789.size()) {
            var12 = false;
            break;
         }

         if ((var11 = (int[])var7.C53_f789.elementAt(var5))[0] == var4) {
            var11[1] = 1;
            var12 = true;
            break;
         }

         ++var5;
      }

      var10000 = this.C53_f777[var2];
      short var10 = (short)var1;
      var6 = 5;
      var10000.C60_f855[var6] = var10;
   }

   public final byte z() {
      if (this.C53_f778 < 6) {
         return 0;
      } else {
         return (byte)(this.C53_f792.size() < 100 ? 1 : 2);
      }
   }

   public final boolean A() {
      return this.C53_f792.size() < 100;
   }

   public final void a(int var1, int var2, short var3, byte var4, short var5, byte var6, int[] var7) {
      this.C53_f777[this.C53_f778] = new C41();
      this.C53_f777[this.C53_f778].a(var1, var2, (short)-1, var4, var5, (byte)-1);
      this.C53_f777[this.C53_f778].b(var7);
      this.a((byte)((byte)this.C53_f777[this.C53_f778].j((byte)1)), var1, (byte)2);
      ++this.C53_f778;
   }

   public final void a(int var1, int var2, int var3, short var4, byte var5, short var6, byte var7, int[] var8) {
      this.C53_f777[this.C53_f778] = new C41();
      System.arraycopy(this.C53_f777, var1, this.C53_f777, var1 + 1, this.C53_f778 - var1);
      this.C53_f777[var1] = null;
      this.C53_f777[var1] = new C41();
      this.C53_f777[var1].a(var2, var3, (short)-1, var5, var6, (byte)-1);
      this.C53_f777[var1].b(var8);
      ++this.C53_f778;
   }

   public final void a(int[] var1) {
      this.C53_f777[this.C53_f778] = new C41();
      this.C53_f777[this.C53_f778].a(var1[0], var1[1], (short)var1[2], (byte)var1[3], (short)var1[4], (byte)var1[5]);
      this.C53_f777[this.C53_f778].a((short)var1[6], var1[7], var1[8]);
      int[] var2 = new int[var1.length - 9];

      for(int var3 = 0; var3 < var2.length; ++var3) {
         var2[var3] = var1[var3 + 9];
      }

      this.C53_f777[this.C53_f778].b(var2);
      this.a((byte)((byte)this.C53_f777[this.C53_f778].j((byte)1)), var1[0], (byte)2);
      ++this.C53_f778;
   }

   public final void n(int var1) {
      for(this.C53_f777[var1] = null; var1 < this.C53_f778 - 1; ++var1) {
         this.C53_f777[var1] = this.C53_f777[var1 + 1];
         this.C53_f777[var1 + 1] = null;
      }

      --this.C53_f778;
   }

   public final void o(int var1) {
      for(int var2 = 0; var2 < this.C53_f778; ++var2) {
         if (this.C53_f777[var2].r() == var1) {
            this.n(var2);
            return;
         }
      }

   }

   public final int B() {
      int[] var1 = new int[this.C53_f778];

      int var2;
      for(var2 = 0; var2 < this.C53_f778; ++var2) {
         C41 var10002 = this.C53_f777[var2];
         byte var4 = 1;
         short var5 = var10002.C60_f855[var4];
         C41 var10003 = this.C53_f777[var2];
         var4 = 1;
         var1[var2] = var5 - var10003.C60_f856[var4];
      }

      var2 = var1[0];

      for(int var3 = 1; var3 < var1.length; ++var3) {
         if (var2 < var1[var3]) {
            var2 = var1[var3];
         }
      }

      if (var2 == 0) {
         return -1;
      } else {
         return var2;
      }
   }

   public final void p(int var1) {
      C41 var2;
      for(var2 = this.C53_f777[var1]; var1 > 0; --var1) {
         this.C53_f777[var1] = this.C53_f777[var1 - 1];
      }

      this.C53_f777[0] = var2;
   }

   public final void a(int var1, int var2, short var3, byte var4, short var5, byte var6, int var7, int var8, int var9, int[] var10) {
      int[] var11;
      (var11 = new int[9 + var10.length])[0] = var1;
      var11[1] = var2;
      var11[2] = -1;
      var11[3] = var4;
      var11[4] = var5;
      var11[5] = -1;
      var11[6] = var7;
      var11[7] = 0;
      var11[8] = var9;
      System.arraycopy(var10, 0, var11, 9, var10.length);
      this.C53_f792.addElement(var11);
      this.a((byte)((byte) ResourceManager.gameDatabase[0][var1][1]), var1, (byte)2);
   }

   public final void b(int[] var1) {
      this.C53_f792.addElement(var1);
      this.a((byte)((byte) ResourceManager.gameDatabase[0][var1[0]][1]), var1[0], (byte)2);
   }

   public final void q(int var1) {
      this.C53_f792.removeElementAt(var1);
   }

   public final void r(int var1) {
      C41[] var10000 = this.C53_f777;
      int var10001 = this.C53_f778;
      C41 var4 = new C41();
      int[] var3 = (int[])this.C53_f792.elementAt(var1);
      var4.a(var3[0], var3[1], (short)var3[2], (byte)var3[3], (short)var3[4], (byte)var3[5]);
      var4.a((short)var3[6], var3[7], var3[8]);
      int[] var2 = new int[var3.length - 9];

      for(int var5 = 0; var5 < var2.length; ++var5) {
         var2[var5] = var3[var5 + 9];
      }

      var4.b(var2);
      var10000[var10001] = var4;
      ++this.C53_f778;
      this.q(var1);
   }

   public final void a(short var1) {
      this.C53_f795[this.C53_f786] = var1;
      ++this.C53_f786;
   }

   public final void b(byte var1, byte var2, byte var3) {
      this.C53_f779[var1][var2] = var3;
      if (this.C53_f779[0][0] == 2) {
         C30.a().a(ResourceManager.getDatabaseValue((byte)2, (short)0, (byte)5) / 2, ResourceManager.getDatabaseValue((byte)2, (short)0, (byte)5) / 2);
      }

   }

   public final byte b(byte var1, byte var2) {
      return this.C53_f779[var1][var2];
   }

   public final void a(byte var1, int var2, byte var3) {
      int var6 = var2;
      byte var5 = var1;
      C53 var4 = this;
      int var7 = 0;

      boolean var10000;
      while(true) {
         if (var7 >= var4.C53_f782[var5]) {
            var10000 = true;
            break;
         }

         if (var4.C53_f781[var5][var7] == var6) {
            var10000 = false;
            break;
         }

         ++var7;
      }

      if (var10000) {
         this.C53_f781[var1][this.C53_f782[var1]] = (byte)var2;
         ++this.C53_f782[var1];
         if (var3 == 2) {
            ++this.C53_f783;
            if (ResourceManager.gameDatabase[0][var2][22] == 2) {
               ++this.C53_f784;
            } else if (ResourceManager.gameDatabase[0][var2][22] == 1) {
               ++this.C53_f785;
            }
         }

         this.C53_f780[var1][var2 - this.C53_f802[var1]] = var3;
      } else {
         if (this.a(var1, var2) <= 1) {
            if (var3 == 2) {
               ++this.C53_f783;
               if (ResourceManager.gameDatabase[0][var2][22] == 2) {
                  ++this.C53_f784;
               } else if (ResourceManager.gameDatabase[0][var2][22] == 1) {
                  ++this.C53_f785;
               }
            }

            this.C53_f780[var1][var2 - this.C53_f802[var1]] = var3;
         }

      }
   }

   public final byte a(byte var1, int var2) {
      return this.C53_f780[var1][var2 - this.C53_f802[var1]];
   }

   public final void C() {
      this.C53_f770 = true;
   }

   public final int D() {
      return C25.B().C25_f290 == 4 && C25.B().C25_f291 == 1 ? GameUtils.getRandomInRange(4, 8) : GameUtils.getRandomInRange(this.C53_f772, this.C53_f773);
   }

   private void O() {
      if (!C25.B().C25_f348.F() && C7.C7_f106 != 0) {
         --this.C53_f774;
         if (this.C53_f774 <= 0) {
            C25.B().e(false);
            this.C53_f774 = 0;
         }

         --this.C53_f775;
         if (this.C53_f775 == 0) {
            this.C20_f261.applyBrightnessEffect(0);
            this.C53_f775 = -1;
         }

         if (C25.B().C() && this.C53_f775 <= 0) {
            if (this.C53_f770 && this.C53_f771 > 0 && this.C53_f793[1] != 2 && this.C53_f793[3] != 2) {
               --this.C53_f771;
            }

         }
      }
   }

   public final boolean E() {
      return this.C53_f771 <= 0;
   }

   public final int F() {
      return this.C53_f800;
   }

   public final void s(int var1) {
      this.C53_f800 += var1;
   }

   public final void t(int var1) {
      this.C53_f800 = 0;
   }

   public final boolean u(int var1) {
      return this.C53_f800 >= var1;
   }

   public final int G() {
      return this.C53_f801;
   }

   public final void v(int var1) {
      this.C53_f801 += var1;
   }

   public final void w(int var1) {
      this.C53_f801 = 0;
   }

   public final boolean x(int var1) {
      return this.C53_f801 >= var1;
   }

   public final boolean b(int var1, int var2, int var3) {
      return ResourceManager.gameDatabase[var3][var1][4] == 0 ? this.u(var2) : this.x(var2);
   }

   public final void H() {
      this.a(68, 7, (short)-1, (byte)2, (short)2, (byte)-1, new int[]{1, 40, 45});
      this.j(0);
   }

   public final boolean a(int var1, boolean var2) {
      super.a(var1, var2);
      if (this.C53_f775 > 0) {
         this.C20_f261.applyBrightnessEffect(1);
      }

      return true;
   }

   public final void I() {
      this.a((byte)0, (byte)this.C60_f866);
   }
}
