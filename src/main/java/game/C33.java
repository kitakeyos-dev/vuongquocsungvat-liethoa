package game;

import a.GameEngineBase;
import a.a.C20;
import a.a.C30;
import a.a.WorldRenderer;
import a.b.CameraController;
import a.b.GameEntity;
import a.b.TileMapRenderer;
import javax.microedition.lcdui.Graphics;

public final class C33 extends GameEngineBase {
   private C20 C33_f540;
   private WorldRenderer C33_f541 = null;
   private static C33 C33_f542 = null;
   private TileMapRenderer C33_f543 = null;
   private CameraController C33_f544 = null;
   private boolean C33_f545 = false;
   private byte C33_f546 = 0;
   public boolean C33_f547 = false;

   public static C33 B() {
      if (C33_f542 == null) {
         C33_f542 = new C33();
      }

      return C33_f542;
   }

   public C33() {
      this.C33_f544 = new CameraController();
      this.C33_f543 = new TileMapRenderer();
      this.C33_f541 = new WorldRenderer();
      this.C33_f540 = new C20();
   }

   public final void C() {
      this.C33_f545 = false;
   }

   public final void update() {
      if (this.C33_f545) {
         byte var2;
         switch(this.C33_f546) {
         case 0:
            var2 = 0;
            this.C33_f540.a(this.C33_f540.primaryStates[var2]);
            if (this.C33_f540.worldY - this.C33_f543.cameraY > getScreenHeight()) {
               this.C33_f547 = true;
            }
            break;
         case 2:
            var2 = 0;
            this.C33_f540.a(this.C33_f540.primaryStates[var2]);
            if (this.C33_f540.worldY - this.C33_f543.cameraY < 0) {
               this.C33_f547 = true;
            }
         }

         this.C33_f541.updateWorld();
      }
   }

   public final void renderPauseScreen(Graphics var1) {
      this.C33_f541.renderWorld(var1);
   }

   public final boolean initializeGame() {
      this.C33_f540.primaryStates = new short[3];
      byte var4 = 5;
      byte var3 = 0;
      this.C33_f540.primaryStates[var3] = var4;
      this.C33_f540.a(343, false);
      this.d(this.C33_f546);
      this.C33_f540.c();
      this.C33_f541.addEntity((GameEntity)this.C33_f540);
      this.C33_f543.loadMap(101);
      this.C33_f541.setTileMapRenderer(this.C33_f543);
      this.C33_f544.setFollowEntity(this.C33_f540, true);
      this.C33_f541.setCameraController(this.C33_f544);
      this.C33_f541.updateWorld();
      return true;
   }

   public final void cleanupCurrentScreen() {
      this.C33_f540 = null;
      this.C33_f541 = null;
      C33_f542 = null;
      this.C33_f543 = null;
      this.C33_f544 = null;
   }

   public final void changeState(byte var1) {
      this.C44_f699 = this.C44_f698;
      switch(var1) {
      case 1:
         C30.a().c(0, 13);
         C30.a().a(5, 1, getScreenWidth(), 30, 30);
         break;
      case 2:
         this.C33_f547 = false;
         this.C33_f545 = true;
         break;
      case 3:
         this.C33_f547 = false;
         C30.a().c(0, 12);
         C30.a().a(5, 1, getScreenWidth(), 30, 30);
      }

      this.C44_f698 = var1;
   }

   public final void d(byte var1) {
      this.C33_f546 = var1;
      this.C33_f540.currentDirection = var1;
      short var2 = (short)(getScreenWidth() >> 1);
      short var3 = 0;
      switch(var1) {
      case 0:
         var2 = (short)(getScreenWidth() >> 1);
         var3 = 10;
         break;
      case 2:
         var2 = (short)(getScreenWidth() >> 1);
         var3 = (short)(getScreenHeight() - 10);
      }

      this.C33_f540.setWorldPosition(var2, var3);
      this.C33_f540.a(var1, (byte)-1, false);
   }
}
