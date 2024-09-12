package d;

import java.lang.ref.WeakReference;

public class C36 {
   private Object[] C36_f561;
   private Object[] C36_f562;
   private int[] C36_f563;
   private int C36_f564;
   private boolean C36_f565;
   private boolean C36_f566;

   public void a(Object var1, Object var2) throws Exception {
      g(var1);
      int var3 = this.c(var1);
      int var4;
      if ((var4 = this.a(var1, var3)) < 0) {
         var4 = this.b(var1, var3);
      }

      Object var5 = var2;
      if (this.C36_f566) {
         var5 = e(var2);
      }

      this.C36_f562[var4] = var5;
   }

   public Object a(Object var1) throws Exception {
      g(var1);
      int var2 = this.c(var1);
      int var3;
      return (var3 = this.a(var1, var2)) >= 0 ? this.d(var3) : null;
   }

   public Object a(int var1) throws Exception {
      return this.a(C63.a(var1));
   }

   public Object b(Object var1) throws Exception {
      int var2 = 0;
      if (var1 != null) {
         int var3 = this.c(var1);
         C47.a((var2 = 1 + this.a(var1, var3)) > 0, "invalid key to 'next'");
      }

      while(var2 != this.C36_f561.length) {
         Object var4;
         if ((var4 = this.c(var2)) != null && this.d(var2) != null) {
            return var4;
         }

         ++var2;
      }

      return null;
   }

   public int a() throws Exception {
      int var1 = this.C36_f561.length;
      int var2 = 0;

      while(var2 < var1) {
         int var3;
         Integer var4 = C63.a(var3 = var1 + var2 + 1 >> 1);
         if (this.a(var4) == null) {
            var1 = var3 - 1;
         } else {
            var2 = var3;
         }
      }

      return var2;
   }

   private static int b(int var0) {
      if (var0 < 4) {
         return 4;
      } else {
         --var0;
         int var10000 = var0 | var0 >> 1 | (var0 |= var0 >> 1) >> 2;
         return (var10000 | var10000 >> 4 | (var10000 | var10000 >> 4) >> 8 | (var10000 | var10000 >> 4 | (var10000 | var10000 >> 4) >> 8) >> 16) + 1;
      }
   }

   private C36() {
      int var2 = b(4);
      this.C36_f561 = new Object[var2];
      this.C36_f562 = new Object[var2];
      this.C36_f563 = new int[var2];
      this.C36_f564 = var2;
   }

   public C36(byte var1) {
      this();
   }

   private int c(Object var1) {
      int var2 = this.C36_f561.length;
      Integer var3;
      return (var1 instanceof Integer ? ((Integer)(var3 = (Integer)var1) == 0 ? 0 : var3.hashCode()) : (var1 instanceof String ? var1.hashCode() : System.identityHashCode(var1))) & var2 - 1;
   }

   private static Object d(Object var0) {
      return !f(var0) ? var0 : ((WeakReference)var0).get();
   }

   private static Object e(Object var0) {
      return !f(var0) ? var0 : new WeakReference(var0);
   }

   private static boolean f(Object var0) {
      return var0 != null && !(var0 instanceof String) && !(var0 instanceof Integer) && !(var0 instanceof Boolean) && !(var0 instanceof C32);
   }

   private Object c(int var1) {
      Object var2 = this.C36_f561[var1];
      return this.C36_f565 ? d(var2) : var2;
   }

   private void a(int var1, Object var2) {
      if (this.C36_f565) {
         var2 = e(var2);
      }

      this.C36_f561[var1] = var2;
   }

   private Object d(int var1) {
      Object var2 = this.C36_f562[var1];
      return this.C36_f566 ? d(var2) : var2;
   }

   private int a(Object var1, int var2) {
      Object var3;
      if ((var3 = this.c(var2)) == null) {
         return -1;
      } else if (var1 instanceof Integer) {
         int var4 = (Integer)var1;

         while(true) {
            if (var3 instanceof Integer) {
               int var5 = (Integer)var3;
               if (var4 == var5) {
                  return var2;
               }
            }

            if ((var2 = this.C36_f563[var2]) == -1) {
               return -1;
            }

            var3 = this.c(var2);
         }
      } else if (var1 instanceof String) {
         while(!var1.equals(var3)) {
            if ((var2 = this.C36_f563[var2]) == -1) {
               return -1;
            }

            var3 = this.c(var2);
         }

         return var2;
      } else {
         while(var1 != var3) {
            if ((var2 = this.C36_f563[var2]) == -1) {
               return -1;
            }

            var3 = this.c(var2);
         }

         return var2;
      }
   }

   private int b(Object var1, int var2) throws Exception {
      Object var3;
      while((var3 = this.c(var2)) != null) {
         try {
            while(this.c(--this.C36_f564) != null) {
            }
         } catch (ArrayIndexOutOfBoundsException var10) {
            C36 var12 = this;
            boolean var13 = this.C36_f565;
            boolean var4 = this.C36_f566;
            this.a(false, false);
            Object[] var5 = this.C36_f561;
            Object[] var6 = this.C36_f562;
            int var7 = var5.length;
            int var8 = 0;
            int var9 = var7;

            while(var9-- > 0) {
               if (var12.C36_f561[var9] != null && var12.C36_f562[var9] != null) {
                  ++var8;
               }
            }

            var8 = 2 * b(var8);
            var12.C36_f561 = new Object[var8];
            var12.C36_f562 = new Object[var8];
            var12.C36_f563 = new int[var8];
            var12.C36_f564 = var8;
            var9 = var7;

            while(var9-- > 0) {
               Object var15;
               Object var16;
               if ((var15 = var5[var9]) != null && (var16 = var6[var9]) != null) {
                  var12.a(var15, var16);
               }
            }

            var12.a(var13, var4);
            var2 = this.c(var1);
            continue;
         }

         int var14;
         if ((var14 = this.c(var3)) == var2) {
            this.a(this.C36_f564, var1);
            this.C36_f563[this.C36_f564] = this.C36_f563[var2];
            return this.C36_f563[var2] = this.C36_f564;
         }

         this.C36_f561[this.C36_f564] = this.C36_f561[var2];
         this.C36_f562[this.C36_f564] = this.C36_f562[var2];
         this.C36_f563[this.C36_f564] = this.C36_f563[var2];
         this.a(var2, var1);
         this.C36_f563[var2] = -1;

         int var11;
         for(var11 = var14; (var14 = this.C36_f563[var11]) != var2; var11 = var14) {
         }

         this.C36_f563[var11] = this.C36_f564;
         return var2;
      }

      this.a(var2, var1);
      this.C36_f563[var2] = -1;
      return var2;
   }

   private static void g(Object var0) throws Exception {
      C47.a(var0 != null, "table index is nil");
   }

   public void a(boolean var1, boolean var2) {
      if (var1 != this.C36_f565) {
         this.a(this.C36_f561, var1);
         this.C36_f565 = var1;
      }

      if (var2 != this.C36_f566) {
         this.a(this.C36_f562, var2);
         this.C36_f566 = var2;
      }

   }

   private void a(Object[] var1, boolean var2) {
      for(int var3 = var1.length - 1; var3 >= 0; --var3) {
         Object var4 = var1[var3];
         var1[var3] = var2 ? e(var4) : d(var4);
      }

   }
}
