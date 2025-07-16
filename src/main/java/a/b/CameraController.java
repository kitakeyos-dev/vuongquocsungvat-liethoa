package a.b;

/**
 * Camera Controller - singleton class for managing camera movement and behavior
 * Extends GameEntity to support position-based camera operations
 */
public final class CameraController extends GameEntity {
    // Singleton instance
    private static CameraController instance;                   // C6_f45

    // Camera behavior settings
    public byte cameraMode;                                     // C6_f46 - Camera movement mode
    private int movementSpeed;                                  // C6_f47 - Camera movement speed
    private int smoothingFactor;                               // C6_f48 - Smoothing for camera movement
    private int cameraLag;                                     // C6_f49 - Lag/delay for camera movement
    private boolean isLocked;                                  // C6_f50 - Whether camera is locked to target

    // Camera mode constants
    public static final byte MODE_FIXED_POSITION = 0;          // Camera stays at fixed position
    public static final byte MODE_FOLLOW_ENTITY = 1;           // Camera follows an entity
    public static final byte MODE_SMOOTH_MOVE = 2;             // Camera moves smoothly to target

    /**
     * Private constructor for singleton pattern
     */
    public CameraController() {
        this.isLocked = true;
        this.cameraMode = 0;
        super.initializeStates(4); // Initialize with 4 state slots
    }

    /**
     * Get singleton instance of camera controller
     * @return CameraController instance
     */
    public static CameraController getInstance() {
        if (instance == null) {
            instance = new CameraController();
        }
        return instance;
    }

    /**
     * Set camera lag/delay for smoother movement
     * @param lag Lag amount in frames
     */
    public void setCameraLag(final int lag) {
        this.cameraLag = lag;
    }

    /**
     * Lock camera to current position or target
     * @param locked Whether camera should be locked
     */
    public void setLocked(final boolean locked) {
        this.isLocked = locked;
    }

    /**
     * Set camera to fixed position mode
     * @param x Camera X position
     * @param y Camera Y position  
     * @param locked Whether camera is immediately locked to position
     */
    public void setFixedPosition(final int x, final int y, final boolean locked) {
        this.cameraMode = MODE_FIXED_POSITION;
        this.isLocked = locked;

        if (this.isLocked) {
            // Immediately set camera position
            this.worldX = x;
            this.worldY = y;
            return;
        }

        // Set up smooth movement to target
        super.boundingBoxX = x;       // Store target position
        super.boundingBoxY = y;
    }

    /**
     * Set camera to follow entity mode
     * @param target Entity to follow
     * @param locked Whether camera immediately snaps to target
     */
    public void setFollowEntity(final GameEntity target, final boolean locked) {
        this.cameraMode = MODE_FOLLOW_ENTITY;
        super.followTarget = target;
        this.isLocked = locked;

        if (this.isLocked) {
            // Immediately position camera at target
            this.worldX = this.followTarget.worldX;
            this.worldY = this.followTarget.worldY;
        }
    }

    /**
     * Check if camera is locked to its target
     * @return true if camera is locked
     */
    public boolean isLocked() {
        return this.isLocked;
    }

    /**
     * Update camera position and behavior
     * Called each frame to update camera state
     */
    public void updateCamera() {
        switch (this.cameraMode) {
            case MODE_FIXED_POSITION:
                if (!this.isLocked && this.moveTowards(this.cameraLag, this.boundingBoxX, this.boundingBoxY)) {
                    this.isLocked = true;
                }
                break;

            case MODE_FOLLOW_ENTITY:
                if (this.isLocked) {
                    // Immediately follow target
                    this.worldX = this.followTarget.worldX;
                    this.worldY = this.followTarget.worldY;
                } else {
                    // Smoothly move towards target
                    if (this.moveTowards(this.cameraLag, this.followTarget.worldX, this.followTarget.worldY)) {
                        this.isLocked = true;
                    }
                }
                break;

            case MODE_SMOOTH_MOVE:
                if (this.isLocked) {
                    return; // Already at target
                }

                if (this.smoothingFactor > 0) {
                    this.smoothingFactor--;
                    return;
                }

                // Check if reached origin (0,0)
                final int currentX = this.worldX;
                if (currentX == 0) {
                    final int currentY = this.worldY;
                    if (currentY == 0) {
                        if (this.movementSpeed < 0) {
                            this.movementSpeed++;
                            this.smoothingFactor = 0;
                            return;
                        }
                        this.isLocked = true;
                        return;
                    }
                }

                // Move towards origin
                this.moveTowards(0, 0, 0);
                break;
        }
    }

    /**
     * Set camera movement speed
     * @param speed Movement speed in pixels per frame
     */
    public void setMovementSpeed(int speed) {
        this.movementSpeed = speed;
    }

    /**
     * Get camera movement speed
     * @return Current movement speed
     */
    public int getMovementSpeed() {
        return this.movementSpeed;
    }

    /**
     * Set smoothing factor for camera movement
     * @param smoothing Smoothing amount (higher = more smooth)
     */
    public void setSmoothingFactor(int smoothing) {
        this.smoothingFactor = smoothing;
    }

    /**
     * Get current smoothing factor
     * @return Smoothing factor value
     */
    public int getSmoothingFactor() {
        return this.smoothingFactor;
    }

    /**
     * Set camera mode
     * @param mode Camera behavior mode
     */
    public void setCameraMode(byte mode) {
        this.cameraMode = mode;
    }

    /**
     * Get current camera mode
     * @return Camera mode
     */
    public byte getCameraMode() {
        return this.cameraMode;
    }

    /**
     * Immediately snap camera to position
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void snapToPosition(int x, int y) {
        this.worldX = x;
        this.worldY = y;
        this.isLocked = true;
    }

    /**
     * Immediately snap camera to follow target
     */
    public void snapToTarget() {
        if (this.followTarget != null) {
            this.worldX = this.followTarget.worldX;
            this.worldY = this.followTarget.worldY;
            this.isLocked = true;
        }
    }

    /**
     * Start smooth movement to position
     * @param targetX Target X coordinate
     * @param targetY Target Y coordinate
     * @param speed Movement speed
     */
    public void smoothMoveTo(int targetX, int targetY, int speed) {
        this.cameraMode = MODE_SMOOTH_MOVE;
        this.boundingBoxX = targetX;
        this.boundingBoxY = targetY;
        this.cameraLag = speed;
        this.isLocked = false;
    }

    /**
     * Check if camera is currently moving
     * @return true if camera is in motion
     */
    public boolean isMoving() {
        return !this.isLocked;
    }

    /**
     * Stop camera movement and lock at current position
     */
    public void stopMovement() {
        this.isLocked = true;
    }

    /**
     * Reset camera to default state
     */
    @Override
    public void reset() {
        super.reset();
        this.cameraMode = MODE_FIXED_POSITION;
        this.isLocked = true;
        this.movementSpeed = 0;
        this.smoothingFactor = 0;
        this.cameraLag = 0;
    }

    /**
     * Update camera (overrides base update method)
     */
    @Override
    public void update() {
        this.updateCamera();
    }

    /**
     * Get camera viewport bounds
     * @param screenWidth Screen width
     * @param screenHeight Screen height
     * @return Array containing [leftX, topY, rightX, bottomY]
     */
    public int[] getViewportBounds(int screenWidth, int screenHeight) {
        int halfWidth = screenWidth / 2;
        int halfHeight = screenHeight / 2;

        return new int[]{
                this.worldX - halfWidth,    // Left
                this.worldY - halfHeight,   // Top
                this.worldX + halfWidth,    // Right
                this.worldY + halfHeight    // Bottom
        };
    }

    /**
     * Check if a point is visible in camera viewport
     * @param x World X coordinate
     * @param y World Y coordinate
     * @param screenWidth Screen width
     * @param screenHeight Screen height
     * @return true if point is visible
     */
    public boolean isPointVisible(int x, int y, int screenWidth, int screenHeight) {
        int[] bounds = getViewportBounds(screenWidth, screenHeight);
        return x >= bounds[0] && x <= bounds[2] && y >= bounds[1] && y <= bounds[3];
    }

    /**
     * Convert world coordinates to screen coordinates
     * @param worldX World X coordinate
     * @param worldY World Y coordinate
     * @param screenWidth Screen width
     * @param screenHeight Screen height
     * @return Array containing [screenX, screenY]
     */
    public int[] worldToScreen(int worldX, int worldY, int screenWidth, int screenHeight) {
        int screenX = worldX - this.worldX + screenWidth / 2;
        int screenY = worldY - this.worldY + screenHeight / 2;
        return new int[]{screenX, screenY};
    }

    /**
     * Convert screen coordinates to world coordinates
     * @param screenX Screen X coordinate
     * @param screenY Screen Y coordinate
     * @param screenWidth Screen width
     * @param screenHeight Screen height
     * @return Array containing [worldX, worldY]
     */
    public int[] screenToWorld(int screenX, int screenY, int screenWidth, int screenHeight) {
        int worldX = screenX - screenWidth / 2 + this.worldX;
        int worldY = screenY - screenHeight / 2 + this.worldY;
        return new int[]{worldX, worldY};
    }

    /**
     * Get string representation for debugging
     * @return String description of camera state
     */
    @Override
    public String toString() {
        return "CameraController{" +
                "mode=" + cameraMode +
                ", position=(" + worldX + "," + worldY + ")" +
                ", locked=" + isLocked +
                ", following=" + (followTarget != null ? "entity" : "none") +
                ", speed=" + movementSpeed +
                "}";
    }
}