// Decompiled with: Procyon 0.6.0
// Class Version: 1
package d;

public final class C34
{
   public C72 C34_f548;
   public C71 C34_f549;
   public int C34_f550;
   public int C34_f551;
   int C34_f552;
   public int C34_f553;
   boolean C34_f554;
   public boolean C34_f555;
   boolean C34_f556;

   public C34(final C72 c34_f548) {
      this.C34_f548 = c34_f548;
   }

   public final void a(final int n, final Object o) {
      this.C34_f548.C72_f968[this.C34_f551 + n] = o;
   }

   public final Object a(final int n) {
      return this.C34_f548.C72_f968[this.C34_f551 + n];
   }

   public final int a(final Object o) throws Exception {
      final int a = this.a();
      this.c(a + 1);
      this.a(a, o);
      return 1;
   }

   public final void a(final int n, final int n2, final int n3) {
      this.C34_f548.a(this.C34_f551 + n, this.C34_f551 + n2, n3);
   }

   public final void a(int i, final int n) {
      while (i <= n) {
         this.C34_f548.C72_f968[this.C34_f551 + i] = null;
         ++i;
      }
   }

   public final void b(final int n) throws Exception {
      if (this.a() < n) {
         this.c(n);
      }
      this.a(n, this.a() - 1);
   }

   public final void c(final int n) throws Exception {
      this.C34_f548.a(this.C34_f551 + n);
   }

   public final void d(final int n) {
      this.C34_f548.b(this.C34_f551 + n);
   }

   public final C22 e(final int n) {
      final C72 c34_f548 = this.C34_f548;
      final int c22_f272 = this.C34_f551 + n;
      final C72 c22_f273 = c34_f548;
      int size = c34_f548.C72_f967.size();
      while (--size >= 0) {
         final C22 c22;
         if ((c22 = (C22) c22_f273.C72_f967.elementAt(size)).C22_f272 == c22_f272) {
            return c22;
         }
         if (c22.C22_f272 >= c22_f272) {
            continue;
         }
         break;
      }
      final C22 c23;
      (c23 = new C22()).C22_f271 = c22_f273;
      c23.C22_f272 = c22_f272;
      c22_f273.C72_f967.insertElementAt(c23, size + 1);
      return c23;
   }

   public final int a() {
      return this.C34_f548.C72_f969 - this.C34_f551;
   }

   public final void b() throws Exception {
      if (this.e()) {
         this.C34_f550 = 0;
         if (this.C34_f549.C71_f961.C24_f278) {
            this.C34_f551 += this.C34_f553;
            this.c(this.C34_f549.C71_f961.C24_f281);
            this.a(-this.C34_f553, 0, Math.min(this.C34_f553, this.C34_f549.C71_f961.C24_f277));
            return;
         }
         this.c(this.C34_f549.C71_f961.C24_f281);
      }
   }

   public final void c() throws Exception {
      if (this.e()) {
         this.c(this.C34_f549.C71_f961.C24_f281);
      }
   }

   public final void b(final int n, int n2) throws Exception {
      final int c24_f277 = this.C34_f549.C71_f961.C24_f277;
      int n3;
      if ((n3 = this.C34_f553 - c24_f277) < 0) {
         n3 = 0;
      }
      if (n2 == -1) {
         n2 = n3;
         this.c(n + n2);
      }
      if (n3 > n2) {
         n3 = n2;
      }
      this.a(-this.C34_f553 + c24_f277, n, n3);
      if (n2 - n3 > 0) {
         this.a(n + n3, n + n2 - 1);
      }
   }

   public final boolean d() {
      return !this.e();
   }

   public final boolean e() {
      return this.C34_f549 != null;
   }
}
