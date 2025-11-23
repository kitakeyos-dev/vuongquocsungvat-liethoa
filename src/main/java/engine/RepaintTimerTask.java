package engine;

import game.GameCanvas;
import java.util.TimerTask;

public final class RepaintTimerTask extends TimerTask {
   public final void run() {
      GameCanvas.getInstance().repaint();
   }
}
