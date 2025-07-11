package a.a;

import e.a.a.a.config.MessageConfig;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class C42 {
   public int[] C42_f671;
   public int C42_f672;
   public int C42_f673;
   public int C42_f674;
   public int C42_f675;
   public int C42_f676;

   public final void a(int[] var1, int var2, int var3) {
      this.C42_f671 = var1;
      this.C42_f672 = var2;
      this.C42_f673 = var3;
      this.C42_f676 = var1.length;
   }

   public final C42 a() {
      C42 var1;
      (var1 = new C42()).C42_f671 = new int[this.C42_f671.length];
      System.arraycopy(this.C42_f671, 0, var1.C42_f671, 0, var1.C42_f671.length);
      var1.C42_f672 = this.C42_f672;
      var1.C42_f673 = this.C42_f673;
      var1.C42_f676 = this.C42_f671.length;
      var1.C42_f674 = this.C42_f674;
      var1.C42_f675 = this.C42_f675;
      return var1;
   }

   private static RecordStore a(String var0, String var1, String var2, String var3, int var4) {
      RecordStore var5 = null;

      try {
         var2 = var2;
         if (var2 == null || var2.length() == 0) {
            var2 = "00";
         }

         if (var3 != null) {
            var3.length();
         }

         StringBuffer var7;
         (var7 = new StringBuffer()).append("dcn").append(var0).append(var1).append(var2).append(var4);
         var5 = RecordStore.openRecordStore(var7.toString(), true, 1, true);
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      return var5;
   }

   public static void a(MessageConfig var0, String var1, String var2, String var3, String var4, int var5) {
      RecordStore var19;
      if ((var19 = a(var1, var2, var3, var4, var5)) != null) {
         try {
            ByteArrayOutputStream var20 = new ByteArrayOutputStream();
            DataOutputStream var21;
            (var21 = new DataOutputStream(var20)).writeInt(var0.getCounter1());
            if (var0.getCounter1() < var5) {
               var21.writeInt(var0.getPaidPrice());
               var21.writeUTF(var0.getPaidNumber());
               var21.writeUTF(var0.getPaidContent());
               var21.writeUTF(var0.getPaidReminder());
               var21.writeInt(var0.getPaidCondition());
               var21.writeUTF(var0.getFreeNumber());
               var21.writeUTF(var0.getFreeContent());
               var21.writeBoolean(var0.getSomeFlag());
               var21.writeInt(var0.getCounter2());
               var21.writeLong(var0.getTimestamp());
            }

            byte[] var18 = var20.toByteArray();
            if (var19.getNumRecords() == 0) {
               var19.addRecord(var18, 0, var18.length);
            } else {
               var19.setRecord(1, var18, 0, var18.length);
            }

            return;
         } catch (Exception var16) {
            var16.printStackTrace();
         } finally {
            try {
               var19.closeRecordStore();
            } catch (RecordStoreNotOpenException var14) {
               var14.printStackTrace();
            } catch (RecordStoreException var15) {
               var15.printStackTrace();
            }

         }

      }
   }

   public static int a(String var0, Font var1) {
      int var2 = 0;

      for(int var4 = 0; var4 < var0.length(); ++var4) {
         char var3 = var0.charAt(var4);
         var2 += var1.charWidth(var3);
      }

      return var2;
   }

   public static int[] a(Graphics var0, String var1, Font var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      int var9 = 0;
      int var10 = 0;

      while(true) {
         int var11 = var3;
         Font var16 = var2;
         String var15 = var1;
         int var12 = 0;
         int var14 = 0;

         int var10000;
         while(true) {
            if (var14 >= var15.length()) {
               var10000 = 0;
               break;
            }

            char var13;
            if ((var13 = var15.charAt(var14)) == '\n') {
               var10000 = var14 + 1;
               break;
            }

            if ((var12 += var16.charWidth(var13)) > var11) {
               var10000 = var14;
               break;
            }

            ++var14;
         }

         var8 = var10000;
         if (var10000 == 0) {
            var0.drawString(var1, 5, var7, 0);
            return new int[]{var7, var10};
         }

         if (var1.charAt(var8 - 1) == '\n') {
            var15 = var1.substring(0, var8 - 1);
         } else {
            var15 = var1.substring(0, var8);
         }

         if (var9 >= var5 && var7 < var4) {
            var0.drawString(var15, 5, var7, 0);
            var7 = var7 + var2.getHeight() + 5;
         }

         if (var7 >= var4) {
            ++var10;
         }

         ++var9;
         var1 = var1.substring(var8, var1.length());
      }
   }
}
