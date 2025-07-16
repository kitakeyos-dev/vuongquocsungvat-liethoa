package game;

import a.GameUtils;
import a.GameEngineBase;
import a.InputHandler;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public final class GameCanvas extends Canvas implements Runnable {
   private static GameCanvas instance = null;
   private static GameMIDLet gameMIDlet;
   private static InputHandler currentScreen;
   private GameScreenManager gameScreen;
   public static GameCanvas publicInstance;
   private long frameStartTime = 0L;
   private long frameEndTime = 0L;
   private long frameElapsedTime = 0L;
   private int lastTouchKeyCode = 0;

   public static GameCanvas getInstance(GameMIDLet midLet) {
      if (instance == null) {
         instance = new GameCanvas(midLet);
      }

      return instance;
   }

   public static GameCanvas getInstance() {
      return instance;
   }

   private GameCanvas(GameMIDLet midLet) {
      publicInstance = this;
      this.setFullScreenMode(true);
      gameMIDlet = midLet;
      GameEngineBase.setFrameRate((int)66);
      GameEngineBase.initializeDisplay((short)this.getWidth(), (short)this.getHeight());
      this.gameScreen = GameScreenManager.getInstance();
      this.gameScreen.activate();
      GameScreenManager var2 = this.gameScreen;
      if (currentScreen != null) {
         currentScreen.setActive(false);
         currentScreen = null;
      }

      if (var2 != null) {
         var2.setActive(true);
         currentScreen = var2;
      }

      (new Thread(this)).start();
   }

   protected final void hideNotify() {
      if (!GameEngineBase.gamePaused && this.gameScreen.getCurrentState() > 1) {
         this.gameScreen.pauseGame();
      }

   }

   protected final void paint(Graphics var1) {
      if (this.gameScreen.getCurrentState() > 1) {
         this.gameScreen.renderPauseScreen(var1);
      }

   }

   public final void run() {
      while(this.gameScreen.getCurrentState() > 1) {
         this.frameStartTime = System.currentTimeMillis();
         this.gameScreen.update();
         this.repaint();
         this.serviceRepaints();
         this.frameEndTime = System.currentTimeMillis();
         this.frameElapsedTime = this.frameEndTime - this.frameStartTime;
         if (this.frameElapsedTime > (long) GameEngineBase.getFrameRate()) {
            this.frameElapsedTime = (long) GameEngineBase.getFrameRate();
         }

         try {
            Thread.sleep((long) GameEngineBase.getFrameRate() - this.frameElapsedTime);
         } catch (InterruptedException var1) {
         }
      }

      gameMIDlet.destroyApp(true);
   }

   protected final void keyPressed(int var1) {
      if (this.gameScreen != null) {
         this.gameScreen.onKeyPressed(var1);
      }

   }

   protected final void keyReleased(int var1) {
      if (this.gameScreen != null) {
         this.gameScreen.onKeyReleased(var1);
      }

   }

   protected final void pointerPressed(int var1, int var2) {
      this.lastTouchKeyCode = 0;
      if (this.gameScreen.getCurrentState() == 13) {
         if (GameUtils.isPointInRectangle(var1, var2, 38, 225, 50, 40)) {
            this.lastTouchKeyCode = -6;
         } else if (GameUtils.isPointInRectangle(var1, var2, 150, 225, 50, 40)) {
            this.lastTouchKeyCode = -7;
         }
      } else if (GameUtils.isPointInRectangle(var1, var2, 0, 280, 40, 40)) {
         this.lastTouchKeyCode = -6;
      } else if (GameUtils.isPointInRectangle(var1, var2, 200, 280, 40, 40)) {
         this.lastTouchKeyCode = -7;
      }

      this.keyPressed(this.lastTouchKeyCode);
      if (this.gameScreen != null) {
         this.gameScreen.onPointerPressed(var1, var2);
      }

   }

   protected final void pointerReleased(int var1, int var2) {
      this.keyReleased(this.lastTouchKeyCode);
      if (this.gameScreen != null) {
         this.gameScreen.onPointerReleased(var1, var2);
      }

   }
}
