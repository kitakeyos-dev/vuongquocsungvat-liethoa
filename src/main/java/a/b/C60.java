package a.b;

import a.C26;

public class C60 {
   public short[] C60_f855;
   public short[] C60_f856;
   protected boolean C60_f857;
   protected boolean C60_f858;
   protected boolean C60_f859;
   protected byte C60_f860;
   public int C60_f861;
   public int C60_f862;
   public int C60_f863;
   public int C60_f864;
   public byte C60_f865;
   public byte C60_f866;
   public byte C60_f867;
   public C60 C60_f868;
   public int C60_f869;
   public int C60_f870;
   public int C60_f871;
   private short C60_f872 = 10;
   private int[][] C60_f873;
   private boolean C60_f874 = false;

   public final void a(byte var1, short var2) {
      this.C60_f855[var1] = var2;
   }

   public final void b(byte var1, short var2) {
      this.C60_f856[var1] = var2;
   }

   public final short b(byte var1) {
      return this.C60_f855[var1];
   }

   public final short c(byte var1) {
      return this.C60_f856[var1];
   }

   public void h() {
      for(byte var1 = 0; var1 < this.C60_f855.length; ++var1) {
         short var4 = this.C60_f855[var1];
         this.C60_f856[var1] = var4;
      }

   }

   public final byte i() {
      return this.C60_f860;
   }

   public final void b(boolean var1) {
      this.C60_f857 = var1;
   }

   public final boolean j() {
      return this.C60_f857;
   }

   public final void c(boolean var1) {
      this.C60_f858 = var1;
   }

   public final boolean k() {
      return this.C60_f858;
   }

   public final void d(boolean var1) {
      this.C60_f859 = var1;
   }

   public final boolean l() {
      return this.C60_f859;
   }

   public final void c(int var1) {
      this.C60_f861 = var1;
   }

   public void b(int var1, int var2) {
      this.C60_f861 = var1;
      this.C60_f862 = var2;
   }

   public final void d(byte var1) {
      this.C60_f866 = var1;
   }

   public final int m() {
      return this.C60_f861;
   }

   public final int n() {
      return this.C60_f862;
   }

   public final void d(int var1) {
      this.C60_f861 += var1;
   }

   public final void e(int var1) {
      this.C60_f862 += var1;
   }

   public void a(int var1, int var2) {
      this.C60_f861 += var1;
      this.C60_f862 += 4;
   }

   public final boolean a(int var1, int var2, int var3) {
      if (this.C60_f861 == var2 && this.C60_f862 == var3) {
         return true;
      } else {
         int var4;
         if ((var4 = C26.a(this.C60_f861, this.C60_f862, var2, var3)) < var1) {
            this.C60_f861 = var2;
            this.C60_f862 = var3;
         } else {
            this.d((var2 - this.C60_f861) * var1 / var4);
            this.e((var3 - this.C60_f862) * var1 / var4);
         }

         return false;
      }
   }

   public final void a(C62 var1, C62 var2) {
      if (this.C60_f874) {
         if (this.C60_f868.C60_f860 != 0) {
            this.C60_f873[0][0] = this.C60_f868.C60_f861;
            this.C60_f873[0][1] = this.C60_f868.C60_f862;
            this.C60_f873[0][2] = var1.C62_f888;
            this.C60_f873[0][3] = this.C60_f868.C60_f866;

            for(int var5 = this.C60_f872; var5 > 0; --var5) {
               this.C60_f873[var5][0] = this.C60_f873[var5 - 1][0];
               this.C60_f873[var5][1] = this.C60_f873[var5 - 1][1];
               this.C60_f873[var5][2] = this.C60_f873[var5 - 1][2];
               this.C60_f873[var5][3] = this.C60_f873[var5 - 1][3];
               if (var5 % this.C60_f872 == 0) {
                  this.b(this.C60_f873[var5][0], this.C60_f873[var5][1]);
                  if (this.C60_f873[var5][3] == 3) {
                     var2.a((byte)this.C60_f873[var5][2], (byte)1, false);
                  } else {
                     var2.a((byte)this.C60_f873[var5][2], (byte)this.C60_f873[var5][3], false);
                  }

                  byte var4 = (byte)this.C60_f873[var5][3];
                  this.C60_f866 = var4;
               }
            }

         }
      }
   }

   public final void e(byte var1) {
      this.C60_f874 = true;
      this.C60_f873 = new int[this.C60_f872 + 1][4];

      int var2;
      for(var2 = 0; var2 < this.C60_f872 + 1; ++var2) {
         this.C60_f873[var2][0] = this.C60_f868.C60_f861;
         this.C60_f873[var2][1] = this.C60_f868.C60_f862;
         this.C60_f873[var2][3] = this.C60_f868.C60_f866;
      }

      if (var1 >= 0) {
         for(var2 = 0; var2 < this.C60_f872 + 1; ++var2) {
            this.C60_f873[var2][2] = var1;
         }
      }

      int[] var10000;
      switch(this.C60_f868.C60_f866) {
      case 0:
         var10000 = this.C60_f873[10];
         var10000[1] -= this.C60_f872;
         break;
      case 1:
         var10000 = this.C60_f873[10];
         var10000[0] -= this.C60_f872;
         break;
      case 2:
         var10000 = this.C60_f873[10];
         var10000[1] += this.C60_f872;
         break;
      case 3:
         var10000 = this.C60_f873[10];
         var10000[0] += this.C60_f872;
      }

      this.b(this.C60_f873[10][0], this.C60_f873[10][1]);
   }

   public final boolean o() {
      return this.C60_f874;
   }

   public final void a(C60 var1) {
      this.C60_f868 = var1;
   }
}
