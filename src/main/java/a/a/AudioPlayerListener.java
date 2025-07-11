package a.a;

import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

final class AudioPlayerListener implements PlayerListener {
   private final AudioManager audioManager;

   AudioPlayerListener(AudioManager var1) {
      this.audioManager = var1;
   }

   public void playerUpdate(Player var1, String var2, Object var3) {
      if (AudioManager.getAudioSettings(this.audioManager)[0][1] != 0) {
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
            } catch (Exception var5) {
            }
         }
      }

   }
}
