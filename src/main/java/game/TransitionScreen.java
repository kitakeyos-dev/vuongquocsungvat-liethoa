package game;

import engine.GameEngineBase;
import engine.entity.GameObject;
import engine.graphics.ScreenTransitionManager;
import engine.graphics.WorldRenderer;
import engine.entity.CameraController;
import engine.entity.TileMapRenderer;
import javax.microedition.lcdui.Graphics;
/**
 * Handles transition screens between different game states.
 * This screen manages animated transitions with moving objects.
 */
public final class TransitionScreen extends GameEngineBase {
   private GameObject transitionObject;
   private WorldRenderer worldRenderer = null;
   private static TransitionScreen instance = null;
   private TileMapRenderer mapRenderer = null;
   private CameraController cameraController = null;
   private boolean animationEnabled = false;
   private byte transitionDirection = 0;
   public boolean transitionComplete = false;

   // Direction constants
   public static final byte DIRECTION_DOWN = 0;
   public static final byte DIRECTION_UP = 2;

   // Sprite and map constants
   private static final int TRANSITION_SPRITE_ID = 343;
   private static final int TRANSITION_MAP_ID = 101;
   private static final short MOVEMENT_SPEED = 5;

   /**
    * Gets the singleton instance of TransitionScreen
    * @return the singleton instance
    */
   public static TransitionScreen getInstance() {
      if (instance == null) {
         instance = new TransitionScreen();
      }
      return instance;
   }

   /**
    * Private constructor for singleton pattern
    */
   public TransitionScreen() {
      this.cameraController = new CameraController();
      this.mapRenderer = new TileMapRenderer();
      this.worldRenderer = new WorldRenderer();
      this.transitionObject = new GameObject();
   }

   /**
    * Stops the transition animation
    */
   public final void stopAnimation() {
      this.animationEnabled = false;
   }

   @Override
   public final void update() {
      if (this.animationEnabled) {
         byte speedIndex = 0;

         switch(this.transitionDirection) {
            case DIRECTION_DOWN:
               this.transitionObject.moveInDirection(this.transitionObject.primaryStates[speedIndex]);
               if (this.transitionObject.worldY - this.mapRenderer.cameraY > getScreenHeight()) {
                  this.transitionComplete = true;
               }
               break;

            case DIRECTION_UP:
               this.transitionObject.moveInDirection(this.transitionObject.primaryStates[speedIndex]);
               if (this.transitionObject.worldY - this.mapRenderer.cameraY < 0) {
                  this.transitionComplete = true;
               }
               break;
         }

         this.worldRenderer.updateWorld();
      }
   }

   @Override
   public final void renderPauseScreen(Graphics graphics) {
      this.worldRenderer.renderWorld(graphics);
   }

   @Override
   public final boolean initializeGame() {
      // Initialize movement speed
      this.transitionObject.primaryStates = new short[3];
      byte speedIndex = 0;
      this.transitionObject.primaryStates[speedIndex] = MOVEMENT_SPEED;

      // Load transition object sprite
      this.transitionObject.loadSpriteSet(TRANSITION_SPRITE_ID, false);

      // Set initial direction and position
      this.setTransitionDirection(this.transitionDirection);

      // Activate the transition object
      this.transitionObject.activate();

      // Set up world rendering
      this.worldRenderer.addEntity(this.transitionObject);
      this.mapRenderer.loadMap(TRANSITION_MAP_ID);
      this.worldRenderer.setTileMapRenderer(this.mapRenderer);

      // Set up camera to follow the transition object
      this.cameraController.setFollowEntity(this.transitionObject, true);
      this.worldRenderer.setCameraController(this.cameraController);

      // Update world once to initialize
      this.worldRenderer.updateWorld();
      return true;
   }

   @Override
   public final void cleanupCurrentScreen() {
      this.transitionObject = null;
      this.worldRenderer = null;
      instance = null;
      this.mapRenderer = null;
      this.cameraController = null;
   }

   @Override
   public final void changeState(byte newState) {
      this.C44_f699 = this.C44_f698;
      switch(newState) {
         case 1:
            ScreenTransitionManager.a().c(0, 13);
            ScreenTransitionManager.a().a(5, 1, getScreenWidth(), 30, 30);
            break;
         case 2:
            this.transitionComplete = false;
            this.animationEnabled = true;
            break;
         case 3:
            this.transitionComplete = false;
            ScreenTransitionManager.a().c(0, 12);
            ScreenTransitionManager.a().a(5, 1, getScreenWidth(), 30, 30);
      }
   }

   /**
    * Sets the direction and initial position for the transition animation
    * @param direction the direction of transition (0=down, 2=up)
    */
   public final void setTransitionDirection(byte direction) {
      this.transitionDirection = direction;
      this.transitionObject.currentDirection = direction;

      short centerX = (short)(getScreenWidth() >> 1);
      short positionY = 0;

      switch(direction) {
         case DIRECTION_DOWN:
            centerX = (short)(getScreenWidth() >> 1);
            positionY = 10; // Start near top of screen
            break;

         case DIRECTION_UP:
            centerX = (short)(getScreenWidth() >> 1);
            positionY = (short)(getScreenHeight() - 10); // Start near bottom of screen
            break;
      }

      this.transitionObject.setWorldPosition(centerX, positionY);
      this.transitionObject.setAnimation(direction, (byte)-1, false);
   }

   /**
    * Checks if the transition animation is currently running
    * @return true if animation is enabled
    */
   public boolean isAnimationEnabled() {
      return this.animationEnabled;
   }

   /**
    * Checks if the transition has completed
    * @return true if transition is complete
    */
   public boolean isTransitionComplete() {
      return this.transitionComplete;
   }

   /**
    * Gets the current transition direction
    * @return current direction (0=down, 2=up)
    */
   public byte getTransitionDirection() {
      return this.transitionDirection;
   }

   /**
    * Gets the transition object for direct manipulation if needed
    * @return the GameObject used for transition animation
    */
   public GameObject getTransitionObject() {
      return this.transitionObject;
   }
}
