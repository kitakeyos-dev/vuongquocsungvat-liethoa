package a.a;

import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

final class C10 implements PlayerListener {
   private final C11 C10_f165;

   C10(C11 var1) {
      this.C10_f165 = var1;
   }

   public void playerUpdate(Player var1, String var2, Object var3) {
      if (C11.a(this.C10_f165)[0][1] != 0) {
         if (var2.equals("deviceUnavailable")) {
            try {
               var1.stop();
               return;
            } catch (Exception var4) {
               return;
            }
         }

         if (var2.equals("deviceAvailable") || var2.equals("volumeChanged")) {
            try {
               var1.start();
               return;
            } catch (Exception var5) {
            }
         }
      }

   }
}
