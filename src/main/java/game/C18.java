package game;

import a.GameUtils;
import a.a.C20;
import a.b.C68;
import javax.microedition.lcdui.Graphics;

public final class C18 extends C20 {
   public byte C18_f223;
   public boolean C18_f224;
   public byte C18_f225;
   public byte C18_f226;
   public short C18_f227;
   public short C18_f228;
   private int C18_f229;
   private int C18_f230;
   private int C18_f231;
   private int C18_f232;
   public byte C18_f233;
   public int C18_f234;
   public int C18_f235;
   public byte C18_f236;
   private short C18_f237;
   private short C18_f238;
   private short C18_f239;
   public short C18_f240;
   public short C18_f241;
   public short C18_f242;
   private boolean C18_f243;
   private byte[] C18_f244 = new byte[]{2, 3, 0, 1};
   private byte C18_f245 = 0;
   public C20 C18_f246;
   public C20 C18_f247;
   public short C18_f248 = -1;
   private short[] C18_f249 = new short[]{8, 9, 2, 96, 320, 0};

   public final void a(short[] var1, int var2) {
      this.C18_f248 = (short)var2;
      this.C18_f223 = (byte)var1[0];
      this.C20_f261.a(var1[1], false);
      this.C20_f261.c();
      this.C18_f225 = (byte)var1[6];
      if (this.C18_f223 == 0 && (this.C18_f225 == 1 || this.C18_f225 == 18)) {
         byte var4 = (byte)(var1[2] % 3);
         super.C60_f866 = var4;
      }

      this.a((byte)var1[2]);
      this.C60_f861 = var1[3];
      this.C60_f862 = var1[4];
      if (var1[5] == 1) {
         this.c(true);
      } else {
         this.c(false);
      }

      switch(this.C18_f223) {
      case 0:
         this.C60_f871 = (byte)var1[7];
         this.C18_f226 = (byte)var1[8];
         if (this.C18_f226 != 0 && this.C18_f246 == null && this.k()) {
            this.C18_f246 = new C20();
            this.C18_f246.a(259, false);
            this.C18_f246.C20_f261.c();
            this.C18_f246.a(this.C18_f226, (byte)-1, true);
            this.C18_f246.b(this.C60_f861, this.C60_f862 - 40);
            this.C18_f246.C60_f868 = this;
         }

         this.B();
         this.w();
         if (var1[9] == 0) {
            this.C18_f224 = false;
         } else {
            this.C18_f224 = true;
         }

         this.C18_f227 = var1[11];
         this.C18_f228 = var1[12];
         this.C18_f229 = 0;
         this.C18_f231 = GameUtils.getRandomInRange(20, 40);
         this.C18_f233 = 0;
         if (this.C18_f225 == 12) {
            this.C60_f866 = 0;
         } else if (this.C18_f225 == 13) {
            this.C60_f866 = 1;
         }

         if (this.C18_f225 == 3) {
            if (this.C60_f860 == 4) {
               this.C60_f866 = 1;
            }
         } else if (this.C18_f225 == 2) {
            if (this.C60_f860 == 5) {
               this.C60_f866 = 2;
            } else if (this.C60_f860 == 3) {
               this.C60_f866 = 0;
            }
         }

         if (this.C18_f225 == 1 && this.C20_f261.C62_f882 != 226 || this.C18_f225 == 2 || this.C18_f225 == 3 || this.C18_f225 == 17) {
            if (this.C20_f262 == null) {
               this.C20_f262 = new C20();
               this.C20_f262.a(337, false);
            }

            this.C20_f262.b(this.C60_f861, this.C60_f862);
            if (this.C20_f261.C62_f882 == 4) {
               this.C20_f262.a((byte)0, (byte)0, this.C18_f243);
            } else {
               this.C20_f262.a((byte)1, (byte)0, this.C18_f243);
            }

            this.C20_f262.c();
         }
         break;
      case 1:
         if (var1[1] == 320) {
            this.C60_f871 = 2;
         } else {
            this.C60_f871 = 1;
         }

         if (this.k() && var1[0] > 0 && var1[0] <= 3) {
            C25.B().C25_f288.addElement(this);
         }

         if (this.C18_f225 == 3) {
            this.C18_f224 = true;
         }

         this.C18_f236 = (byte)var1[7];
         this.C18_f237 = var1[8];
         this.C18_f238 = var1[9];
         this.C18_f239 = var1[10];
         break;
      case 2:
         if (var1[7] == 0) {
            this.C18_f243 = false;
         } else {
            this.C18_f243 = true;
         }
         break;
      case 3:
         this.C60_f871 = 1;
         this.C18_f240 = var1[7];
         this.C18_f241 = var1[8];
         switch(this.C18_f241) {
         case 9:
            this.C18_f241 = 1;
            break;
         case 10:
            this.C18_f241 = 0;
            break;
         case 11:
            this.C18_f241 = 2;
            break;
         case 12:
            this.C18_f241 = 3;
         }

         this.C18_f242 = var1[9];
         this.C18_f224 = true;
      }

      this.C60_f855 = new short[3];
      this.C60_f856 = new short[3];
   }

   public final void p() {
      if (this.C20_f261 != null) {
         this.C20_f261.d();
      }

   }

   public final void a(byte var1) {
      switch(this.C18_f223) {
      case 0:
         if (this.C18_f225 == 8) {
            this.a((byte)0, (byte)-1, false);
            this.C18_f229 = 0;
            this.C60_f860 = var1;
            return;
         }

         if (this.C18_f225 != 1 && this.C18_f225 != 18) {
            this.a(var1, (byte)-1, false);
            this.C60_f860 = var1;
            C25.B().a(this.C18_f248, 0, this.C60_f860, true);
            return;
         }

         this.C60_f860 = (byte)(var1 / 3);
         if (this.C60_f860 == 0) {
            if (this.C60_f866 == 3) {
               this.a((byte)1, (byte)-1, false);
               return;
            }

            this.a(this.C60_f866, (byte)-1, false);
            return;
         }

         if (this.C60_f860 == 1) {
            if (this.C60_f866 == 3) {
               this.a((byte)(this.C60_f860 * 3 + 1), (byte)-1, false);
               return;
            }

            this.a((byte)(this.C60_f860 * 3 + this.C60_f866), (byte)-1, false);
            return;
         }
         break;
      case 1:
         if (this.C18_f225 == 0) {
            switch(var1) {
            case 0:
               this.a(var1, (byte)-1, false);
               break;
            case 1:
               this.a(var1, (byte)-2, false);
               break;
            case 2:
               this.a(var1, (byte)-1, false);
               break;
            case 3:
               this.a(var1, (byte)-2, false);
            }
         } else {
            this.a(var1, (byte)-1, false);
            if (this.C18_f225 == 3) {
               C25.B().a(this.C18_f248, 0, var1, true);
            }
         }

         this.C60_f860 = var1;
         return;
      case 2:
         this.C60_f860 = var1;
         return;
      case 3:
         this.a(var1, (byte)-2, false);
         this.C60_f860 = var1;
         C25.B().a(this.C18_f248, 0, this.C60_f860, true);
      }

   }

   public final void q() {
      switch(this.C18_f223) {
      case 0:
         this.z();
         break;
      case 1:
         if (this.C18_f225 == 0) {
            if (this.C60_f860 == 0 && GameUtils.checkCollisionBetweenShortArrays(C53.p().C60_f861, C53.p().C60_f862, this.C60_f861, this.C60_f862, C53.p().C20_f261.k(), this.C20_f261.k())) {
               this.a((byte)1);
            } else if (this.C60_f860 == 2 && !GameUtils.checkCollisionBetweenShortArrays(C53.p().C60_f861, C53.p().C60_f862, this.C60_f861, this.C60_f862, C53.p().C20_f261.k(), this.C20_f261.k())) {
               this.a((byte)3);
            } else if (this.C60_f860 == 1 && this.C20_f261.f()) {
               this.a((byte)2);
            } else if (this.C60_f860 == 3 && this.C20_f261.f()) {
               this.a((byte)0);
            }
         }

         byte var10001;
         if (this.C18_f225 == 0 && this.C20_f261.f() || this.C18_f225 == 1 || this.C18_f225 == 3 && this.i() == 2) {
            if (this.C20_f261.C62_f882 == 320 && !this.k()) {
               return;
            }

            var10001 = this.C18_f244[this.C18_f236];
            if ((C53.p().C60_f867 == var10001 && this.C20_f261.C62_f882 != 320 && this.C20_f261.C62_f882 != 310 || this.C20_f261.C62_f882 == 320 || this.C20_f261.C62_f882 == 310) && GameUtils.checkCollisionBetweenShortArrays(C53.p().C60_f861, C53.p().C60_f862, this.C60_f861, this.C60_f862, C53.p().C20_f261.k(), this.C20_f261.j())) {
               C25.B().C25_f290 = this.C18_f237;
               C25.B().C25_f291 = this.C18_f238;
               C25.B().C25_f295 = this.C18_f239;
               GameScreenManager.getInstance().changeState((byte)9);
            }
         } else if (this.C18_f225 == 2) {
            var10001 = this.C18_f244[this.C18_f236];
            if ((C53.p().C60_f867 == var10001 && this.C20_f261.C62_f882 != 320 || this.C20_f261.C62_f882 == 320) && GameUtils.checkCollisionBetweenShortArrays(C53.p().C60_f861, C53.p().C60_f862, this.C60_f861, this.C60_f862, C53.p().C20_f261.k(), this.C20_f261.j())) {
               for(int var1 = 0; var1 < this.C18_f249.length / 6; ++var1) {
                  if (this.C18_f249[var1 * 6] == this.C18_f248 && this.C18_f249[var1 * 6 + 1] == C25.B().C25_f290 && this.C18_f249[var1 * 6 + 2] == C25.B().C25_f291) {
                     C25.B().C25_f293 = this.C18_f249[var1 * 6 + 3];
                     C25.B().C25_f294 = this.C18_f249[var1 * 6 + 4];
                     C25.C25_f320 = (byte)this.C18_f249[var1 * 6 + 5];
                     break;
                  }
               }

               C25.B().C25_f290 = this.C18_f237;
               C25.B().C25_f291 = this.C18_f238;
               C25.B().C25_f295 = -1;
               GameScreenManager.getInstance().changeState((byte)9);
            }
         } else if (this.C18_f225 == 4 && C53.p().i() != 9 && C53.p().i() != 10 && GameUtils.checkCollisionBetweenShortArrays(C53.p().C60_f861, C53.p().C60_f862, this.C60_f861, this.C60_f862, C53.p().C20_f261.k(), this.C20_f261.j())) {
            C53.p().b(this.C60_f861, this.C60_f862);
            C53.p().C20_f262.b(this.C60_f861, this.C60_f862);
            C53.p().a((byte)9, (byte)this.C60_f866);
            C25.B().C25_f295 = this.C18_f239;
         }
      case 2:
      case 3:
      }

      this.f();
   }

   private void z() {
      int var1;
      byte var2;
      byte[] var4;
      switch(this.C18_f225) {
      case 1:
         if (this.C60_f860 == 1) {
            var2 = 0;
            this.a((int)super.C60_f856[var2]);
            if (C25.B().C25_f313 != null && C25.B().C25_f313.C60_f868.equals(this) && !C53.p().a(this, C53.p().C20_f261.k(), this.C20_f261.k())) {
               C25.B().D();
            }
         }

         if (C25.B().C25_f313 != null && C25.B().C25_f313.C60_f868.equals(this) && (!this.k() || !C53.p().a(this, C53.p().C20_f261.k(), this.C20_f261.k()))) {
            C25.C25_f318 = -1;
            C25.B().D();
         }

         if (this.C18_f246 != null && this.k() && this.C18_f246.C60_f868.equals(this) && !C53.p().a(this, C53.p().C20_f261.k(), this.C20_f261.k())) {
            this.B();
         }

         if (C7.C7_f64 != null && C7.C7_f64.size() > 0 && this.C18_f245 == 1 && !C53.p().a(this, C53.p().C20_f261.k(), this.C20_f261.k())) {
            for(var1 = 0; var1 < C7.C7_f64.size(); ++var1) {
               if (((C20)C7.C7_f64.elementAt(var1)).C60_f868.equals(this)) {
                  ((C20)C7.C7_f64.elementAt(var1)).c();
                  return;
               }
            }
         }
         break;
      case 2:
         if (this.A()) {
            var4 = new byte[]{0, 1, 2, 3, 5};
            this.a(var4[GameUtils.getRandomInt(5)]);
            if (this.C60_f860 != 3 && this.C60_f860 != 0) {
               if (this.C60_f860 != 5 && this.C60_f860 != 2) {
                  if (GameUtils.getRandomInt(2) == 0) {
                     var2 = 3;
                     super.C60_f866 = var2;
                  } else {
                     var2 = 1;
                     super.C60_f866 = var2;
                  }
               } else {
                  var2 = 2;
                  super.C60_f866 = var2;
               }
            } else {
               var2 = 0;
               super.C60_f866 = var2;
            }
         }

         if (this.C60_f860 == 3) {
            if (this.C18_f229 >= 64) {
               this.a((byte)0);
               return;
            }

            this.a((int)4);
            this.C18_f229 += 4;
            return;
         }

         if (this.C60_f860 == 5) {
            if (this.C18_f229 <= 0) {
               this.a((byte)2);
               return;
            }

            this.a((int)4);
            this.C18_f229 -= 4;
            return;
         }
         break;
      case 3:
         if (this.A()) {
            var4 = new byte[]{0, 1, 2, 4};
            this.a(var4[GameUtils.getRandomInt(4)]);
            if (this.C60_f860 == 0) {
               var2 = 0;
               super.C60_f866 = var2;
            } else if (this.C60_f860 == 2) {
               var2 = 2;
               super.C60_f866 = var2;
            } else if (GameUtils.getRandomInt(2) == 0) {
               var2 = 3;
               super.C60_f866 = var2;
            } else {
               var2 = 1;
               super.C60_f866 = var2;
            }
         }

         if (this.C60_f860 == 4) {
            if (this.C60_f866 == 1) {
               if (this.C18_f229 >= 64) {
                  this.a((byte)0);
                  return;
               }

               this.a((int)4);
               this.C18_f229 += 4;
               return;
            }

            if (this.C60_f866 == 3) {
               if (this.C18_f229 <= 0) {
                  this.a((byte)2);
                  return;
               }

               this.a((int)4);
               this.C18_f229 -= 4;
               return;
            }
         }
         break;
      case 4:
      case 5:
      case 6:
      case 7:
      case 15:
         if (this.C18_f247 != null && this.k() && this.C18_f247.C60_f868.equals(this) && (this.C60_f860 != 0 || !C53.p().a(this, C53.p().C20_f261.k(), this.C20_f261.k()))) {
            this.y();
         }

         if (this.C60_f860 == 1 && this.C20_f261.f()) {
            this.a((byte)2);
            if (this.C18_f225 == 6 && this.C18_f225 == 7) {
               C25.B().a(this.C18_f248, 0, this.C60_f860, false);
            } else {
               C25.B().a(this.C18_f248, 0, this.C60_f860, true);
            }

            if ((this.C18_f225 == 7 || this.C18_f225 == 6) && (var1 = GameUtils.getRandomInt(2)) > 0) {
               C53.p().s(var1);
               int[] var3 = new int[]{var1, super.C60_f861, super.C60_f862 - 20, 0};
               C53.p().C53_f799.addElement(var3);
            }

            C7.C7_f69 = false;
            return;
         }
         break;
      case 8:
         if (this.C60_f860 == 1) {
            if (this.C18_f229 < 2 && this.a(this.C60_f866, 8, (byte)0)) {
               ++this.C18_f229;
               this.a((int)8);
               this.t();
               return;
            }

            this.a((byte)0);
            return;
         }
         break;
      case 9:
      case 10:
         if (this.C18_f233 == 1) {
            if ((this.C20_f261.C62_f882 == 302 || this.C20_f261.C62_f882 == 298) && this.a(this.C60_f866, 4, (byte)1)) {
               this.a((int)4);
               ((C20)this.C60_f868).a(4);
               return;
            }

            if (this.a(this.C60_f866, 4, (byte)2)) {
               this.a((int)4);
               ((C20)this.C60_f868).a(4);
               return;
            }

            this.t();
            this.C18_f233 = 2;
            this.a((byte)0);
            return;
         }
         break;
      case 11:
         return;
      case 12:
         if (this.k()) {
            if (!GameUtils.isPointInShortArrayRectangle(C53.p().C60_f861, C53.p().C60_f862, this.C60_f861, this.C60_f862, this.C20_f261.k())) {
               if (this.a(this.C60_f866, 4, (byte)0)) {
                  this.a((int)4);
                  return;
               }

               if (this.C60_f866 == 2) {
                  this.C60_f866 = 0;
                  return;
               }

               this.C60_f866 = 2;
               return;
            }

            if (C53.p().i() != 8) {
               C53.p().a((byte)8, (byte)this.C60_f866);
               return;
            }
         }
         break;
      case 13:
         if (this.k()) {
            if (!GameUtils.isPointInShortArrayRectangle(C53.p().C60_f861, C53.p().C60_f862, this.C60_f861, this.C60_f862, this.C20_f261.k())) {
               if (this.a(this.C60_f866, 4, (byte)0)) {
                  this.a((int)4);
                  return;
               }

               if (this.C60_f866 == 3) {
                  this.C60_f866 = 1;
                  return;
               }

               this.C60_f866 = 3;
               return;
            }

            if (C53.p().i() != 8) {
               C53.p().a((byte)8, (byte)this.C60_f866);
               return;
            }
         }
         break;
      case 14:
         this.s();
         if (this.C18_f232 < 4) {
            ++this.C18_f232;
            return;
         }

         this.C18_f232 = 0;
         return;
      case 16:
         if (GameUtils.isPointInShortArrayRectangle(C53.p().C60_f861, C53.p().C60_f862, this.C60_f861, this.C60_f862, this.C20_f261.k()) && C53.p().i() != 5) {
            C53.p().a((byte)5, (byte)C53.p().C60_f866);
            return;
         }
      }

   }

   public final void r() {
      byte var2;
      if (this.C18_f225 == 9) {
         this.a((byte)1);
         if ((this.C20_f261.C62_f882 == 302 || this.C20_f261.C62_f882 == 298) && this.a((byte)1, 4, (byte)1)) {
            var2 = 1;
            super.C60_f866 = var2;
         } else if (this.a((byte)1, 4, (byte)2)) {
            var2 = 1;
            super.C60_f866 = var2;
         } else {
            var2 = 3;
            super.C60_f866 = var2;
         }
      } else if ((this.C20_f261.C62_f882 == 302 || this.C20_f261.C62_f882 == 298) && this.a((byte)2, 4, (byte)1)) {
         this.a((byte)2);
         var2 = 2;
         super.C60_f866 = var2;
      } else if (this.a((byte)2, 4, (byte)2)) {
         this.a((byte)2);
         var2 = 2;
         super.C60_f866 = var2;
      } else {
         this.a((byte)1);
         var2 = 0;
         super.C60_f866 = var2;
      }

      this.C18_f233 = 1;
   }

   public final void s() {
      this.C18_f234 = 0;

      while(true) {
         boolean var10000;
         label86: {
            byte var4;
            byte var10001 = super.C20_f261.g();
            int var10002 = 16 * (this.C18_f234 + 1);
            boolean var1 = false;
            int var3 = var10002;
            byte var2 = var10001;
            C18 var5 = this;
            var4 = 0;
            int var6;
            label83:
            switch(var2) {
            case 0:
               var4 = C68.a().a(0, this.C60_f861, this.C60_f862 + var3);
               var6 = 0;

               while(true) {
                  if (var6 >= C25.B().C25_f287.length) {
                     break label83;
                  }

                  if (C25.B().C25_f287[var6].C18_f225 != var5.C18_f225 && C25.B().C25_f287[var6].C20_f261.k() != null && GameUtils.isPointInShortArrayRectangle(var5.C60_f861, var5.C60_f862 + var3, C25.B().C25_f287[var6].C60_f861, C25.B().C25_f287[var6].C60_f862, C25.B().C25_f287[var6].C20_f261.k())) {
                     C25.B().C25_f287[var6].C60_f868 = var5;
                     var10000 = false;
                     break label86;
                  }

                  ++var6;
               }
            case 1:
               var4 = C68.a().a(0, this.C60_f861 + var3, this.C60_f862);
               var6 = 0;

               while(true) {
                  if (var6 >= C25.B().C25_f287.length) {
                     break label83;
                  }

                  if (C25.B().C25_f287[var6].C18_f225 != var5.C18_f225 && C25.B().C25_f287[var6].C20_f261.k() != null && GameUtils.isPointInShortArrayRectangle(var5.C60_f861 + var3, var5.C60_f862, C25.B().C25_f287[var6].C60_f861, C25.B().C25_f287[var6].C60_f862, C25.B().C25_f287[var6].C20_f261.k())) {
                     C25.B().C25_f287[var6].C60_f868 = var5;
                     var10000 = false;
                     break label86;
                  }

                  ++var6;
               }
            case 2:
               var4 = C68.a().a(0, this.C60_f861, this.C60_f862 - var3);
               var6 = 0;

               while(true) {
                  if (var6 >= C25.B().C25_f287.length) {
                     break label83;
                  }

                  if (C25.B().C25_f287[var6].C18_f225 != var5.C18_f225 && C25.B().C25_f287[var6].C20_f261.k() != null && GameUtils.isPointInShortArrayRectangle(var5.C60_f861, var5.C60_f862 - var3, C25.B().C25_f287[var6].C60_f861, C25.B().C25_f287[var6].C60_f862, C25.B().C25_f287[var6].C20_f261.k())) {
                     C25.B().C25_f287[var6].C60_f868 = var5;
                     var10000 = false;
                     break label86;
                  }

                  ++var6;
               }
            case 3:
               var4 = C68.a().a(0, this.C60_f861 - var3, this.C60_f862);

               for(var6 = 0; var6 < C25.B().C25_f287.length; ++var6) {
                  if (C25.B().C25_f287[var6].C18_f225 != var5.C18_f225 && C25.B().C25_f287[var6].C20_f261.k() != null && GameUtils.isPointInShortArrayRectangle(var5.C60_f861 - var3, var5.C60_f862, C25.B().C25_f287[var6].C60_f861, C25.B().C25_f287[var6].C60_f862, C25.B().C25_f287[var6].C20_f261.k())) {
                     C25.B().C25_f287[var6].C60_f868 = var5;
                     var10000 = false;
                     break label86;
                  }
               }
            }

            var10000 = var4 == 0;
         }

         if (!var10000) {
            return;
         }

         ++this.C18_f234;
      }
   }

   private boolean A() {
      ++this.C18_f230;
      if (this.C18_f230 >= this.C18_f231) {
         this.C18_f230 = 0;
         this.C18_f231 = GameUtils.getRandomInRange(20, 40);
         return true;
      } else {
         return false;
      }
   }

   private boolean a(byte var1, int var2, byte var3) {
      byte var4 = 0;
      switch(var1) {
      case 0:
         var4 = C68.a().a(0, this.C60_f861, this.C60_f862 + var2);
         break;
      case 1:
         var4 = C68.a().a(0, this.C60_f861 + var2 + this.C20_f261.k()[2] / 2, this.C60_f862);
         break;
      case 2:
         var4 = C68.a().a(0, this.C60_f861, this.C60_f862 - this.C20_f261.k()[3] - var2);
         break;
      case 3:
         var4 = C68.a().a(0, this.C60_f861 - var2 - this.C20_f261.k()[2] / 2, this.C60_f862);
      }

      return var4 == var3;
   }

   public final void c(Graphics var1, int var2, int var3) {
      switch(super.C20_f261.g()) {
      case 0:
         var1.setColor(65280);
         var1.fillRect(this.C60_f861 - var2 - (this.C18_f232 + 5) / 2, this.C60_f862 - this.C20_f261.j()[3] - var3 + 20, this.C18_f232 + 5, this.C18_f234 + 1 << 4);
         var1.setColor(16777215);
         var1.fillRect(this.C60_f861 - var2 - (this.C18_f232 + 3) / 2, this.C60_f862 - this.C20_f261.j()[3] - var3 + 20, this.C18_f232 + 3, this.C18_f234 + 1 << 4);
         return;
      case 1:
         var1.setColor(65280);
         var1.fillRect(this.C60_f861 - var2 + 7, this.C60_f862 - this.C20_f261.j()[3] - var3 - (this.C18_f232 + 5) / 2 + 13, this.C18_f234 << 4, this.C18_f232 + 5);
         var1.setColor(16777215);
         var1.fillRect(this.C60_f861 - var2 + 7, this.C60_f862 - this.C20_f261.j()[3] - var3 - (this.C18_f232 + 3) / 2 + 13, this.C18_f234 << 4, this.C18_f232 + 3);
      default:
         return;
      case 2:
         var1.setColor(65280);
         var1.fillRect(this.C60_f861 - var2 - (this.C18_f232 + 5) / 2, this.C60_f862 - this.C20_f261.j()[3] - var3 - (this.C18_f234 << 4) + 8, this.C18_f232 + 5, this.C18_f234 << 4);
         var1.setColor(16777215);
         var1.fillRect(this.C60_f861 - var2 - (this.C18_f232 + 3) / 2, this.C60_f862 - this.C20_f261.j()[3] - var3 - (this.C18_f234 << 4) + 8, this.C18_f232 + 3, this.C18_f234 << 4);
         return;
      case 3:
         var1.setColor(65280);
         var1.fillRect(this.C60_f861 - var2 - 8 - (this.C18_f234 << 4), this.C60_f862 - this.C20_f261.j()[3] - var3 - (this.C18_f232 + 5) / 2 + 13, this.C18_f234 << 4, this.C18_f232 + 5);
         var1.setColor(16777215);
         var1.fillRect(this.C60_f861 - var2 - 8 - (this.C18_f234 << 4), this.C60_f862 - this.C20_f261.j()[3] - var3 - (this.C18_f232 + 3) / 2 + 13, this.C18_f234 << 4, this.C18_f232 + 3);
      }
   }

   public final void t() {
      C25.B().a(this.C18_f248, 0, this.C60_f861);
      C25.B().a(this.C18_f248, 1, this.C60_f862);
   }

   public final void u() {
      byte var1 = 0;
      if (this.k()) {
         var1 = 1;
      }

      C25.B().a(this.C18_f248, 1, var1, true);
      C25.B().a(this.C18_f248, 0, this.C60_f860, true);
      C25.B().a(this.C18_f248, 2, this.C60_f866, true);
   }

   public final void a(int var1) {
      int var10000 = this.C60_f861;
      var10000 = this.C60_f862;
      super.a(var1);
   }

   public final void f(byte var1) {
      this.C18_f245 = 1;
   }

   public final byte v() {
      return this.C18_f245;
   }

   public final void f(int var1) {
      if (this.C18_f247 == null && this.k()) {
         this.C18_f247 = new C20();
         this.C18_f247.a(259, false);
         this.C18_f247.C20_f261.c();
         this.C18_f247.a((byte)7, (byte)-1, true);
         this.C18_f247.b(this.C60_f861, this.C60_f862 - var1);
         this.C18_f247.C60_f868 = this;
      }

      if (this.C18_f247 != null) {
         this.C18_f247.c();
      }

   }

   public final void w() {
      if (C25.B().n(this.C18_f248)) {
         this.x();
      }

   }

   private void B() {
      if (this.C18_f246 != null) {
         this.C18_f246.c();
      }

   }

   public final void d() {
      super.d();
      this.x();
      this.e();
      this.y();
   }

   public final void x() {
      if (this.C18_f246 != null) {
         this.C18_f246.d();
      }

   }

   public final void y() {
      if (this.C18_f247 != null) {
         this.C18_f247.d();
      }

   }

   public final void b(int var1, int var2) {
      super.b(var1, var2);
      if (this.C20_f262 != null && this.C20_f262.k()) {
         this.C20_f262.b(var1, var2);
      }

   }
}
