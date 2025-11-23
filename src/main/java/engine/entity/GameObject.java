package engine.entity;

import engine.GameEngineBase;
import engine.GameUtils;
import game.GameWorldManager;

import javax.microedition.lcdui.Graphics;

public class GameObject extends GameEntity {
    public Sprite sprite = new Sprite();
    public GameObject attachedObject = null;
    protected boolean useAlternativeRender;

    /**
     * Loads a sprite set for this game object
     *
     * @param spriteSetId    ID of the sprite set to load
     * @param cachingEnabled whether to enable caching for the sprite set
     * @return true if successfully loaded
     */
    public boolean loadSpriteSet(int spriteSetId, boolean cachingEnabled) {
        return this.sprite.loadSpriteSet(spriteSetId, cachingEnabled);
    }

    /**
     * Replaces an image in the current sprite set
     *
     * @param imageIndex index of the image to replace
     * @param newImageId ID of the new image
     */
    public final void replaceImage(int imageIndex, int newImageId, boolean forceRelease) {
        this.sprite.replaceImage(imageIndex, newImageId, forceRelease);
    }

    /**
     * Sets the animation for this object
     *
     * @param animationId ID of the animation
     * @param startFrame  starting frame of the animation
     * @param loop        whether the animation should loop
     * @return true if animation was set successfully
     */
    public final boolean setAnimation(byte animationId, byte startFrame, boolean loop) {
        return this.sprite.setAnimation(animationId, startFrame, loop);
    }

    /**
     * Sets animation properties and direction
     *
     * @param animationId ID of the animation
     * @param frameDelay  delay between frames
     * @param direction   direction the object is facing
     */
    public final void setAnimationAndDirection(byte animationId, byte frameDelay, byte direction) {
        this.sprite.setAnimationProperties(animationId, frameDelay);
        super.currentDirection = direction;
    }

    /**
     * Updates the animation frame
     *
     * @return true if animation was updated, false if object is not visible
     */
    public final boolean updateAnimation() {
        return !this.isVisible ? false : this.sprite.updateAnimation();
    }

    /**
     * Checks if the animation has reached its last frame
     *
     * @return true if at last frame
     */
    public final boolean isAnimationComplete() {
        return this.sprite.isAtLastFrame();
    }

    /**
     * Renders the object with normal appearance
     *
     * @param graphics Graphics context
     * @param cameraX  camera X offset
     * @param cameraY  camera Y offset
     */
    public final void render(Graphics graphics, int cameraX, int cameraY) {
        if (this.isVisible) {
            if (this.currentDirection == 3) { // Facing left
                this.sprite.renderCurrentFrame(graphics, this.worldX - cameraX, this.worldY - cameraY, (byte) 1);
            } else { // Facing right or other directions
                this.sprite.renderCurrentFrame(graphics, this.worldX - cameraX, this.worldY - cameraY, (byte) 0);
            }
        }
    }

    /**
     * Renders the object with alternative appearance (e.g., highlighted, selected)
     *
     * @param graphics Graphics context
     * @param cameraX  camera X offset
     * @param cameraY  camera Y offset
     */
    public final void renderAlternative(Graphics graphics, int cameraX, int cameraY) {
        if (this.useAlternativeRender) {
            if (this.currentDirection == 3) { // Facing left
                this.sprite.renderCurrentFrame(graphics, this.worldX - cameraX, this.worldY - cameraY, (byte) 3);
            } else { // Facing right or other directions
                this.sprite.renderCurrentFrame(graphics, this.worldX - cameraX, this.worldY - cameraY, (byte) 4);
            }
        }
    }

    /**
     * Activates this object (makes it active, visible, and interactable)
     */
    public void activate() {
        this.setActive(true);
        this.setVisible(true);
        this.setInteractable(true);
    }

    /**
     * Deactivates this object (makes it inactive, invisible, and non-interactable)
     */
    public void deactivate() {
        this.setActive(false);
        this.setVisible(false);
        this.setInteractable(false);
    }

    /**
     * Deactivates any attached object
     */
    public final void deactivateAttachedObject() {
        if (this.attachedObject != null) {
            this.attachedObject.deactivate();
        }
    }

    /**
     * Sets whether to use alternative rendering mode
     *
     * @param useAlternative true to use alternative rendering
     */
    public final void setAlternativeRender(boolean useAlternative) {
        this.useAlternativeRender = useAlternative;
    }

    /**
     * Moves the object in its current direction
     *
     * @param distance distance to move
     */
    public void moveInDirection(int distance) {
        switch (this.currentDirection) {
            case 0: // Down
                this.moveY(distance);
                break;
            case 1: // Right
                this.moveX(distance);
                break;
            case 2: // Up
                this.moveY(-distance);
                break;
            case 3: // Left
                this.moveX(-distance);
        }

        // Update attached object position if it exists
        if (this.attachedObject != null) {
            this.attachedObject.setWorldPosition(this.worldX, this.worldY);
        }
    }

    public final void moveRelative(int direction, int distance) {
        switch(direction) {
            case 0:
                this.moveY(distance);
                break;
            case 1:
                this.moveX(distance);
                break;
            case 2:
                this.moveY(-distance);
                break;
            case 3:
                this.moveX(-distance);
        }

        // Update attached object position if it exists
        if (this.attachedObject != null) {
            this.attachedObject.setWorldPosition(this.worldX, this.worldY);
        }

    }

    /**
     * Updates the interactable state based on visibility in camera view
     */
    public final void updateInteractableState() {
        if (this.sprite.isInvalid()) {
            this.setInteractable(true);
        } else if (GameUtils.checkCollisionWithShortArray(
                GameWorldManager.B().C25_f283.cameraX,
                GameWorldManager.B().C25_f283.cameraY,
                GameEngineBase.getScreenWidth(),
                GameEngineBase.getScreenHeight(),
                this.worldX,
                this.worldY,
                this.sprite.getCurrentFrameTriggers())) {
            this.setInteractable(true);
        } else {
            this.setInteractable(false);
        }
    }

    /**
     * Applies color effects to the sprite
     */
    public final void applyColorEffects() {
        this.sprite.applyColorEffects();
    }

    /**
     * Applies brightness effect to the sprite
     *
     * @param brightnessLevel brightness level to apply
     */
    public final void applyBrightnessEffect(int brightnessLevel) {
        this.sprite.applyBrightnessEffect(brightnessLevel);
    }
}
