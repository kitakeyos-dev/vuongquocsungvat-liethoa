package game;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class GameMIDLet extends MIDlet {
   private Display C15_f215;
   private C1 C15_f216;
   public static GameMIDLet C15_f217;

   public GameMIDLet() {
      C15_f217 = this;
      this.C15_f215 = Display.getDisplay(this);
      this.C15_f216 = C1.a(this);
      this.C15_f215.setCurrent(this.C15_f216);
   }

   public void startApp() {
   }

   public void pauseApp() {
   }

   public void destroyApp(boolean var1) {
      this.C15_f216 = null;
      System.gc();
      if (var1) {
         this.notifyDestroyed();
      }

   }
}
