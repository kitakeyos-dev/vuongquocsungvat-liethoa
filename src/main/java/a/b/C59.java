package a.b;

import java.io.ByteArrayOutputStream;
import javax.microedition.rms.RecordStore;

public final class C59 {
   private String C59_f851;
   private RecordStore C59_f852;
   private int C59_f853;
   private int C59_f854 = 1;

   public C59(String var1, int var2) {
      this.C59_f851 = var1;
      if (this.b() == 0) {
         this.b(1);
      } else {
         if (this.b() != 1) {
            this.a();
            this.b(1);
         }

      }
   }

   private void b(int var1) {
      try {
         byte[] var2 = new byte[]{0};

         for(int var3 = 0; var3 < var1; ++var3) {
            int var6 = var2.length;
            this.a(this.C59_f854, var2, 0, var6, 1);
         }

      } catch (Exception var7) {
      }
   }

   private int b() {
      this.a(this.C59_f854, (byte[])null, 0, 0, 0);
      return this.C59_f853;
   }

   public final byte[] a(int var1) {
      byte[] var3;
      byte[] var2 = new byte[(var3 = this.a(1, (byte[])null, 0, 0, 3)).length - 1];
      System.arraycopy(var3, 1, var2, 0, var2.length);
      this.c();
      return var2;
   }

   public final void a(ByteArrayOutputStream var1) {
      byte[] var4;
      byte[] var2 = new byte[(var4 = var1.toByteArray()).length + 1];
      System.arraycopy(var4, 0, var2, 1, var4.length);
      var2[0] = 1;
      int var3 = var2.length;
      this.a(this.C59_f854, var2, 0, var3, 4);
      this.c();
   }

   private void c() {
      this.a(0, (byte[])null, 0, 0, 6);
   }

   public final void a() {
      this.a(0, (byte[])null, 0, 0, 7);
   }

   private byte[] a(int var1, byte[] var2, int var3, int var4, int var5) {
      try {
         if (var5 == 7 || var5 == 6) {
            if (this.C59_f852 != null) {
               this.C59_f852.closeRecordStore();
               this.C59_f852 = null;
            }

            if (var5 == 7) {
               RecordStore.deleteRecordStore(this.C59_f851);
            }

            return null;
         }

         if (this.C59_f852 == null) {
            this.C59_f852 = RecordStore.openRecordStore(this.C59_f851, true);
         }

         switch(var5) {
         case 0:
            this.C59_f853 = this.C59_f852.getNumRecords();
            break;
         case 1:
            this.C59_f852.addRecord(var2, 0, var4);
            break;
         case 2:
            this.C59_f852.deleteRecord(var1);
            break;
         case 3:
            return this.C59_f852.getRecord(var1);
         case 4:
            this.C59_f852.setRecord(var1, var2, 0, var4);
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      return null;
   }
}
