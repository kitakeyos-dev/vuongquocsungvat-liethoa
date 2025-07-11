package a;

import game.GameCanvas;
import java.util.TimerTask;

public final class C45 extends TimerTask {
   public final void run() {
      GameCanvas.getInstance().repaint();
   }
}
