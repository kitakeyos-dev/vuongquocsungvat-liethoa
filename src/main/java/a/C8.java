package a;

public abstract class C8 implements C65 {
   protected boolean C8_f110;
   private C65 C8_f111;
   private int C8_f112;
   private int C8_f113;
   private int C8_f114;
   private int C8_f115;
   private int C8_f116;
   private int C8_f117;
   private int C8_f118 = -1;
   private int C8_f119 = -1;

   private static int a(int var0) {
      switch(var0) {
      case -22:
      case -7:
         return 262144;
      case -21:
      case -6:
         return 131072;
      case -20:
      case -19:
      case -18:
      case -17:
      case -16:
      case -15:
      case -14:
      case -13:
      case -12:
      case -11:
      case -10:
      case -9:
      case -8:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 19:
      case 20:
      case 21:
      case 22:
      case 23:
      case 24:
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
      case 36:
      case 37:
      case 38:
      case 39:
      case 40:
      case 41:
      case 43:
      case 44:
      case 45:
      case 46:
      case 47:
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
      case 99:
      case 100:
      case 101:
      case 105:
      case 107:
      case 108:
      case 111:
      case 112:
      case 113:
      case 115:
      case 119:
      case 120:
      default:
         return 0;
      case -5:
         return 65536;
      case -4:
         return 32768;
      case -3:
         return 16384;
      case -2:
         return 8192;
      case -1:
         return 4096;
      case 35:
      case 117:
         return 2048;
      case 42:
      case 106:
         return 1024;
      case 48:
      case 109:
         return 1;
      case 49:
      case 114:
         return 2;
      case 50:
      case 116:
         return 4;
      case 51:
      case 121:
         return 8;
      case 52:
      case 102:
         return 16;
      case 53:
      case 103:
         return 32;
      case 54:
      case 104:
         return 64;
      case 55:
      case 118:
         return 128;
      case 56:
      case 98:
         return 256;
      case 57:
      case 110:
         return 512;
      }
   }

   public final boolean g(int var1) {
      return (this.C8_f116 & var1) != 0;
   }

   public final boolean a(int var1, int var2, int var3, int var4) {
      if (this.C8_f118 >= var1 && this.C8_f118 <= var1 + var3 && this.C8_f119 >= var2 && this.C8_f119 <= var2 + var4) {
         int var10000 = this.C8_f119;
         var10000 = this.C8_f118;
         this.C8_f118 = -1;
         this.C8_f119 = -1;
         return true;
      } else {
         return false;
      }
   }

   public final boolean h(int var1) {
      return (this.C8_f117 & '\uf154') != 0;
   }

   public final boolean i(int var1) {
      return (this.C8_f115 & var1) != 0;
   }

   public final void showSoftKeys() {
      this.C8_f112 = 0;
      this.C8_f113 = 0;
      this.C8_f114 = 0;
      this.C8_f115 = 0;
      this.C8_f116 = 0;
      this.C8_f117 = 0;
      if (this.C8_f111 != null) {
         this.C8_f111.showSoftKeys();
      }

   }

   protected final void A() {
      this.C8_f115 = this.C8_f112;
      this.C8_f116 = this.C8_f113;
      this.C8_f117 = this.C8_f114;
      this.C8_f113 = 0;
      this.C8_f114 = 0;
   }

   public final void j(int var1) {
      int var2 = a(var1);
      this.C8_f113 |= var2;
      this.C8_f112 |= var2;
      if (this.C8_f111 != null) {
         this.C8_f111.j(var1);
      }

   }

   public final void k(int var1) {
      int var2 = a(var1);
      this.C8_f114 |= var2;
      this.C8_f112 &= ~var2;
      if (this.C8_f111 != null) {
         this.C8_f111.k(var1);
      }

   }

   public final void c(int var1, int var2) {
      if (this.C8_f111 != null) {
         this.C8_f111.c(var1, var2);
      }

   }

   public final void d(int var1, int var2) {
      this.C8_f118 = var1;
      this.C8_f119 = var2;
      if (this.C8_f111 != null) {
         this.C8_f111.d(var1, var2);
      }

   }

   public void setActive(boolean var1) {
      this.showSoftKeys();
      this.C8_f110 = var1;
   }

   protected final void a(C65 var1) {
      if (this.C8_f111 != null) {
         this.C8_f111.setActive(false);
         this.C8_f111 = null;
      }

      if (var1 != null) {
         var1.setActive(true);
         this.C8_f111 = var1;
      }

   }
}
