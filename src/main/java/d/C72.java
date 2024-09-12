package d;

import java.util.Vector;

public final class C72 {
   public C36 C72_f964;
   public C72 C72_f965;
   public String C72_f966 = "";
   public Vector C72_f967;
   public Object[] C72_f968;
   public int C72_f969;
   private C34[] C72_f970;
   private int C72_f971;
   public C63 C72_f972;

   public C72() {
   }

   public C72(C63 var1, C36 var2) {
      this.C72_f972 = var1;
      this.C72_f964 = var2;
      this.C72_f968 = new Object[10];
      this.C72_f970 = new C34[10];
      this.C72_f967 = new Vector();
   }

   public final C34 a(C71 var1, int var2, int var3, int var4, boolean var5, boolean var6) throws Exception {
      this.d(this.C72_f971 + 1);
      C34 var7;
      (var7 = this.b()).C34_f551 = var2;
      var7.C34_f552 = var3;
      var7.C34_f553 = var4;
      var7.C34_f554 = var5;
      var7.C34_f555 = var6;
      var7.C34_f549 = var1;
      return var7;
   }

   public final void a() throws Exception {
      if (this.c()) {
         throw new Exception("Stack underflow");
      } else {
         this.d(this.C72_f971 - 1);
      }
   }

   private void d(int var1) throws Exception {
      if (var1 > this.C72_f971) {
         if (var1 > 100) {
            throw new Exception("Stack overflow");
         }

         int var3;
         int var4;
         for(var3 = var4 = this.C72_f970.length; var3 <= var1; var3 <<= 1) {
         }

         if (var3 > var4) {
            C34[] var5 = new C34[var3];
            System.arraycopy(this.C72_f970, 0, var5, 0, var4);
            this.C72_f970 = var5;
         }
      } else {
         this.a(var1, this.C72_f971 - 1);
      }

      this.C72_f971 = var1;
   }

   private void a(int var1, int var2) {
      for(; var1 <= var2; ++var1) {
         if (this.C72_f970[var1] != null) {
            this.C72_f970[var1].C34_f549 = null;
         }
      }

   }

   public final void a(int var1) throws Exception {
      if (this.C72_f969 < var1) {
         if (var1 > 1000) {
            throw new Exception("Stack overflow");
         }

         int var3;
         int var4;
         for(var3 = var4 = this.C72_f968.length; var3 <= var1; var3 <<= 1) {
         }

         if (var3 > var4) {
            Object[] var5 = new Object[var3];
            System.arraycopy(this.C72_f968, 0, var5, 0, var4);
            this.C72_f968 = var5;
         }
      } else {
         this.b(var1, this.C72_f969 - 1);
      }

      this.C72_f969 = var1;
   }

   public final void a(int var1, int var2, int var3) {
      if (var3 > 0 && var1 != var2) {
         System.arraycopy(this.C72_f968, var1, this.C72_f968, var2, var3);
      }

   }

   private void b(int var1, int var2) {
      while(var1 <= var2) {
         this.C72_f968[var1] = null;
         ++var1;
      }

   }

   public final void b(int var1) {
      int var2 = this.C72_f967.size();

      while(true) {
         --var2;
         if (var2 < 0) {
            return;
         }

         C22 var3;
         if ((var3 = (C22)this.C72_f967.elementAt(var2)).C22_f272 < var1) {
            return;
         }

         var3.C22_f273 = this.C72_f968[var3.C22_f272];
         var3.C22_f271 = null;
         this.C72_f967.removeElementAt(var2);
      }
   }

   public final C34 b() {
      if (this.c()) {
         return null;
      } else {
         C34 var1;
         if ((var1 = this.C72_f970[this.C72_f971 - 1]) == null) {
            var1 = new C34(this);
            this.C72_f970[this.C72_f971 - 1] = var1;
         }

         return var1;
      }
   }

   public final C34 c(int var1) throws Exception {
      C47.a(var1 >= 0, "Level must be non-negative");
      C47.a((var1 = this.C72_f971 - var1 - 1) >= 0, "Level too high");
      return this.C72_f970[var1];
   }

   public final boolean c() {
      return this.C72_f971 == 0;
   }
}
