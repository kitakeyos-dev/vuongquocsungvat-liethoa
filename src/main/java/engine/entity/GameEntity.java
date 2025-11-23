package engine.entity;

import engine.GameUtils;

/**
 * Game Entity - base class for objects in the game world
 * Handles position, movement, state management, and sprite animation
 */
public class GameEntity {
   // Sprite animation states
   public short[] primaryStates;                       // C60_f855 - Primary animation states
   public short[] secondaryStates;                     // C60_f856 - Secondary animation states

   // Entity state flags
   protected boolean isActive;                         // C60_f857 - Whether entity is active
   protected boolean isVisible;                        // C60_f858 - Whether entity is visible
   protected boolean isInteractable;                   // C60_f859 - Whether entity can be interacted with
   protected byte facingDirection;                     // C60_f860 - Direction entity is facing (0=up, 1=left, 2=down, 3=right)

   // Position and movement
   public int worldX;                                  // C60_f861 - X position in world coordinates
   public int worldY;                                  // C60_f862 - Y position in world coordinates
   public int targetX;                                 // C60_f863 - Target X position for movement
   public int targetY;                                 // C60_f864 - Target Y position for movement

   // Visual state
   public byte currentAnimationId;                     // C60_f865 - Current animation being played
   public byte currentDirection;                       // C60_f866 - Current facing direction
   public byte visualState;                            // C60_f867 - Current visual state

   // Entity relationships
   public GameEntity followTarget;                     // C60_f868 - Entity to follow
   public int boundingBoxX;                            // C60_f869 - Bounding box X offset
   public int boundingBoxY;                            // C60_f870 - Bounding box Y offset
   public int layer;                         // C60_f871 - Bounding box size

   // Trail/follow system
   private short trailLength = 10;                     // C60_f872 - Length of movement trail
   private int[][] movementTrail;                      // C60_f873 - Trail of previous positions and states
   private boolean trailEnabled = false;               // C60_f874 - Whether trail system is active

   /**
    * Set primary animation state
    * @param stateIndex Index of the state to set
    * @param value New state value
    */
   public final void setPrimaryState(byte stateIndex, short value) {
      this.primaryStates[stateIndex] = value;
   }

   /**
    * Set secondary animation state
    * @param stateIndex Index of the state to set
    * @param value New state value
    */
   public final void setSecondaryState(byte stateIndex, short value) {
      this.secondaryStates[stateIndex] = value;
   }

   /**
    * Get primary animation state
    * @param stateIndex Index of the state to get
    * @return State value
    */
   public final short getPrimaryState(byte stateIndex) {
      return this.primaryStates[stateIndex];
   }

   /**
    * Get secondary animation state
    * @param stateIndex Index of the state to get
    * @return State value
    */
   public final short getSecondaryState(byte stateIndex) {
      return this.secondaryStates[stateIndex];
   }

   /**
    * Copy primary states to secondary states (save current state)
    */
   public void saveCurrentStates() {
      for (byte i = 0; i < this.primaryStates.length; i++) {
         short currentState = this.primaryStates[i];
         this.secondaryStates[i] = currentState;
      }
   }

   /**
    * Get current facing direction
    * @return Direction (0=up, 1=left, 2=down, 3=right)
    */
   public final byte getFacingDirection() {
      return this.facingDirection;
   }

   /**
    * Set entity active state
    * @param active Whether entity is active
    */
   public final void setActive(boolean active) {
      this.isActive = active;
   }

   /**
    * Check if entity is active
    * @return true if entity is active
    */
   public final boolean isActive() {
      return this.isActive;
   }

   /**
    * Set entity visibility
    * @param visible Whether entity is visible
    */
   public final void setVisible(boolean visible) {
      this.isVisible = visible;
   }

   /**
    * Check if entity is visible
    * @return true if entity is visible
    */
   public final boolean isVisible() {
      return this.isVisible;
   }

   /**
    * Set entity interactable state
    * @param interactable Whether entity can be interacted with
    */
   public final void setInteractable(boolean interactable) {
      this.isInteractable = interactable;
   }

   /**
    * Check if entity is interactable
    * @return true if entity can be interacted with
    */
   public final boolean isInteractable() {
      return this.isInteractable;
   }

   /**
    * Set entity world X position
    * @param x New X position
    */
   public final void setWorldX(int x) {
      this.worldX = x;
   }

   /**
    * Set entity world position
    * @param x New X position
    * @param y New Y position
    */
   public void setWorldPosition(int x, int y) {
      this.worldX = x;
      this.worldY = y;
   }

   /**
    * Set current facing direction
    * @param direction New direction (0=up, 1=left, 2=down, 3=right)
    */
   public final void setCurrentDirection(byte direction) {
      this.currentDirection = direction;
   }

   /**
    * Get world X position
    * @return Current X position
    */
   public final int getWorldX() {
      return this.worldX;
   }

   /**
    * Get world Y position
    * @return Current Y position
    */
   public final int getWorldY() {
      return this.worldY;
   }

   /**
    * Move entity by relative amount
    * @param deltaX X movement amount
    */
   public final void moveX(int deltaX) {
      this.worldX += deltaX;
   }

   /**
    * Move entity by relative amount
    * @param deltaY Y movement amount
    */
   public final void moveY(int deltaY) {
      this.worldY += deltaY;
   }

   /**
    * Move entity by relative amount (different implementation)
    * @param deltaX X movement amount
    * @param deltaY Y movement amount
    */
   public void moveRelative(int deltaX, int deltaY) {
      this.worldX += deltaX;
      this.worldY += deltaY;
   }

   /**
    * Move towards target position with specified speed
    * @param speed Movement speed per frame
    * @param targetX Target X coordinate
    * @param targetY Target Y coordinate
    * @return true if target position reached
    */
   public final boolean moveTowards(int speed, int targetX, int targetY) {
      if (this.worldX == targetX && this.worldY == targetY) {
         return true;
      }

      int distance = GameUtils.calculateEuclideanDistance(this.worldX, this.worldY, targetX, targetY);
      if (distance < speed) {
         // Close enough, snap to target
         this.worldX = targetX;
         this.worldY = targetY;
      } else {
         // Move towards target
         this.moveX((targetX - this.worldX) * speed / distance);
         this.moveY((targetY - this.worldY) * speed / distance);
      }

      return false;
   }

   /**
    * Update movement trail system for follower entities
    * @param leaderSprite Leader entity's sprite for animation sync
    * @param followerSprite Follower entity's sprite to update
    */
   public final void updateMovementTrail(Sprite leaderSprite, Sprite followerSprite) {
      if (this.trailEnabled) {
         if (this.followTarget.facingDirection != 0) {
            // Record leader's current state
            this.movementTrail[0][0] = this.followTarget.worldX;
            this.movementTrail[0][1] = this.followTarget.worldY;
            this.movementTrail[0][2] = leaderSprite.nextAnimationId;
            this.movementTrail[0][3] = this.followTarget.currentDirection;

            // Shift trail data backwards
            for (int i = this.trailLength; i > 0; i--) {
               this.movementTrail[i][0] = this.movementTrail[i - 1][0];
               this.movementTrail[i][1] = this.movementTrail[i - 1][1];
               this.movementTrail[i][2] = this.movementTrail[i - 1][2];
               this.movementTrail[i][3] = this.movementTrail[i - 1][3];

               if (i % this.trailLength == 0) {
                  // Update follower position and animation from trail
                  this.setWorldPosition(this.movementTrail[i][0], this.movementTrail[i][1]);

                  if (this.movementTrail[i][3] == 3) {
                     followerSprite.setAnimation((byte) this.movementTrail[i][2], (byte) 1, false);
                  } else {
                     followerSprite.setAnimation((byte) this.movementTrail[i][2], (byte) this.movementTrail[i][3], false);
                  }

                  byte newDirection = (byte) this.movementTrail[i][3];
                  this.currentDirection = newDirection;
               }
            }
         }
      }
   }

   /**
    * Initialize movement trail system
    * @param animationId Initial animation ID to fill trail with
    */
   public final void initializeMovementTrail(byte animationId) {
      this.trailEnabled = true;
      this.movementTrail = new int[this.trailLength + 1][4];

      // Initialize trail with current leader state
      for (int i = 0; i < this.trailLength + 1; i++) {
         this.movementTrail[i][0] = this.followTarget.worldX;
         this.movementTrail[i][1] = this.followTarget.worldY;
         this.movementTrail[i][3] = this.followTarget.currentDirection;
      }

      // Set animation ID if provided
      if (animationId >= 0) {
         for (int i = 0; i < this.trailLength + 1; i++) {
            this.movementTrail[i][2] = animationId;
         }
      }

      // Adjust trail position based on leader's direction
      int[] trailPosition = this.movementTrail[10]; // Use middle of trail
      switch (this.followTarget.currentDirection) {
         case 0: // Up
            trailPosition[1] -= this.trailLength;
            break;
         case 1: // Left
            trailPosition[0] -= this.trailLength;
            break;
         case 2: // Down
            trailPosition[1] += this.trailLength;
            break;
         case 3: // Right
            trailPosition[0] += this.trailLength;
            break;
      }

      this.setWorldPosition(trailPosition[0], trailPosition[1]);
   }

   /**
    * Check if movement trail is enabled
    * @return true if trail system is active
    */
   public final boolean isTrailEnabled() {
      return this.trailEnabled;
   }

   /**
    * Set follow target entity
    * @param target Entity to follow
    */
   public final void setFollowTarget(GameEntity target) {
      this.followTarget = target;
   }

   /**
    * Get follow target entity
    * @return Entity being followed, or null
    */
   public final GameEntity getFollowTarget() {
      return this.followTarget;
   }

   /**
    * Set trail length for movement trail system
    * @param length Number of trail positions to maintain
    */
   public final void setTrailLength(short length) {
      this.trailLength = length;
   }

   /**
    * Get trail length
    * @return Current trail length
    */
   public final short getTrailLength() {
      return this.trailLength;
   }

   /**
    * Disable movement trail system
    */
   public final void disableTrail() {
      this.trailEnabled = false;
      this.movementTrail = null;
   }

   /**
    * Set bounding box for collision detection
    * @param x X offset from entity position
    * @param y Y offset from entity position
    * @param size Size of bounding box
    */
   public final void setBoundingBox(int x, int y, int size) {
      this.boundingBoxX = x;
      this.boundingBoxY = y;
      this.layer = size;
   }

   /**
    * Get bounding box bounds
    * @return Array containing [x, y, width, height]
    */
   public final int[] getBoundingBox() {
      return new int[]{
              this.worldX + this.boundingBoxX,
              this.worldY + this.boundingBoxY,
              this.layer,
              this.layer
      };
   }

   /**
    * Check collision with another entity
    * @param other Other entity to check collision with
    * @return true if entities are colliding
    */
   public final boolean isCollidingWith(GameEntity other) {
      int[] thisBounds = this.getBoundingBox();
      int[] otherBounds = other.getBoundingBox();

      return GameUtils.checkCollision(
              thisBounds[0], thisBounds[1], thisBounds[2], thisBounds[3],
              otherBounds[0], otherBounds[1], otherBounds[2], otherBounds[3],
              0 // No rotation
      );
   }

   /**
    * Check if entity is at specific world position
    * @param x X coordinate to check
    * @param y Y coordinate to check
    * @return true if entity is at that position
    */
   public final boolean isAtPosition(int x, int y) {
      return this.worldX == x && this.worldY == y;
   }

   /**
    * Get distance to another entity
    * @param other Other entity
    * @return Distance in world units
    */
   public final int getDistanceTo(GameEntity other) {
      return GameUtils.calculateEuclideanDistance(this.worldX, this.worldY, other.worldX, other.worldY);
   }

   /**
    * Get distance to a point
    * @param x X coordinate
    * @param y Y coordinate
    * @return Distance in world units
    */
   public final int getDistanceTo(int x, int y) {
      return GameUtils.calculateEuclideanDistance(this.worldX, this.worldY, x, y);
   }

   /**
    * Set target position for movement
    * @param x Target X coordinate
    * @param y Target Y coordinate
    */
   public final void setTargetPosition(int x, int y) {
      this.targetX = x;
      this.targetY = y;
   }

   /**
    * Get target position
    * @return Array containing [targetX, targetY]
    */
   public final int[] getTargetPosition() {
      return new int[]{this.targetX, this.targetY};
   }

   /**
    * Initialize entity state arrays
    * @param stateCount Number of animation states to support
    */
   protected void initializeStates(int stateCount) {
      this.primaryStates = new short[stateCount];
      this.secondaryStates = new short[stateCount];
   }

   /**
    * Reset entity to default state
    */
   public void reset() {
      this.isActive = true;
      this.isVisible = true;
      this.isInteractable = false;
      this.facingDirection = 0;
      this.currentDirection = 0;
      this.worldX = 0;
      this.worldY = 0;
      this.targetX = 0;
      this.targetY = 0;
      this.followTarget = null;
      this.disableTrail();
   }

   /**
    * Update entity logic (to be overridden by subclasses)
    */
   public void update() {
      // Base implementation does nothing
      // Subclasses should override this for specific behavior
   }

   /**
    * Get string representation for debugging
    * @return String description of entity state
    */
   @Override
   public String toString() {
      return "GameEntity{" +
              "worldPos=(" + worldX + "," + worldY + ")" +
              ", direction=" + currentDirection +
              ", active=" + isActive +
              ", visible=" + isVisible +
              ", trailEnabled=" + trailEnabled +
              "}";
   }
}