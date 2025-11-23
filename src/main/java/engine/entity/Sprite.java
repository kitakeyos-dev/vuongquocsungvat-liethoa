package engine.entity;

import engine.graphics.ImageProcessor;
import game.QuestManager;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Animated Sprite - comprehensive sprite and animation management system
 * Handles sprite loading, animation playback, transformation, and rendering with multiple flip modes
 */
public final class Sprite {

    // Transformation lookup tables for different flip modes
    private static int[] FLIP_NONE = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
    private static int[] FLIP_HORIZONTAL = new int[]{2, 4, 1, 7, 0, 5, 3, 6};
    private static int[] FLIP_VERTICAL = new int[]{3, 6, 0, 5, 1, 7, 2, 4};
    private static int[] FLIP_BOTH = new int[]{1, 7, 2, 4, 3, 6, 0, 5};

    // Sprite resources
    private Image[] spriteImages;
    private SpriteAnimationData animationData;
    private int[] imageResourceIds;

    // Sprite identification
    public int spriteSetId;

    // Animation state
    private int frameTimer;
    private int currentFrameIndex;
    private byte currentAnimationId;
    private int totalFramesInAnimation;
    private int frameDelay;
    protected byte nextAnimationId;
    private boolean animationEnabled = true;

    // Flip mode constants
    public static final byte FLIP_MODE_NONE = 0;
    public static final byte FLIP_MODE_HORIZONTAL = 1;
    public static final byte FLIP_MODE_VERTICAL = 2;
    public static final byte FLIP_MODE_BOTH = 3;
    public static final byte FLIP_MODE_ROTATE_180 = 4;

    // Animation behavior constants
    public static final byte ANIM_LOOP = -1;
    public static final byte ANIM_STOP = -2;

    /**
     * Load sprite set from resource data
     * @param spriteSetResourceId Resource ID for sprite set
     * @param enableCaching Enable image caching for performance
     * @return true if loading successful
     */
    public final boolean loadSpriteSet(int spriteSetResourceId, boolean enableCaching) {
        // Get sprite animation data
        short[] spriteSetData = ResourceManager.spriteAnimationData[spriteSetResourceId];

        // Initialize arrays for images and resource IDs
        this.imageResourceIds = new int[spriteSetData.length - 1];
        this.spriteImages = new Image[spriteSetData.length - 1];

        // Load each sprite image
        for (int i = 0; i < this.spriteImages.length; i++) {
            this.imageResourceIds[i] = spriteSetData[i + 1];
            this.spriteImages[i] = ResourceManager.getCachedImage(this.imageResourceIds[i]);
        }

        // Store sprite set ID and initialize animation data
        this.spriteSetId = spriteSetResourceId;
        this.animationData = SpriteDataCache.getSpriteData(spriteSetData[0]);
        this.animationData.useExtendedFormat = enableCaching;

        // Initialize animation to first frame
        this.initializeFrame(0);
        return true;
    }

    /**
     * Release sprite resources (soft cleanup)
     */
    public final void releaseResources() {
        for (int i = 0; i < this.imageResourceIds.length; i++) {
            this.spriteImages[i] = null;
            ResourceManager.releaseImageReference(this.imageResourceIds[i]);
        }

        this.imageResourceIds = null;
        this.animationData = null;
        SpriteDataCache.releaseSpriteReference(this.spriteSetId);
    }

    /**
     * Force cleanup all sprite resources
     */
    public final void forceCleanup() {
        if (this.imageResourceIds != null) {
            for (int i = 0; i < this.imageResourceIds.length; i++) {
                this.spriteImages[i] = null;
                ResourceManager.forceReleaseImage(this.imageResourceIds[i]);
            }

            this.imageResourceIds = null;
            this.animationData = null;
            SpriteDataCache.tryUnloadSprite(this.spriteSetId);
        }
    }

    /**
     * Replace sprite image at specific index
     * @param imageIndex Index of image to replace
     * @param newResourceId New resource ID
     * @param forceRelease Force release old image
     */
    public final void replaceImage(int imageIndex, int newResourceId, boolean forceRelease) {
        this.spriteImages[imageIndex] = null;

        if (forceRelease) {
            ResourceManager.forceReleaseImage(this.imageResourceIds[imageIndex]);
        } else {
            ResourceManager.releaseImageReference(this.imageResourceIds[imageIndex]);
        }

        this.imageResourceIds[imageIndex] = newResourceId;
        this.spriteImages[imageIndex] = ResourceManager.getCachedImage(this.imageResourceIds[imageIndex]);
    }

    /**
     * Apply color effects to sprites
     */
    public final void applyColorEffects() {
        if (QuestManager.questState == 1) {
            for (int i = 0; i < this.spriteImages.length; i++) {
                this.spriteImages[i] = ImageProcessor.convertToGrayscale(ResourceManager.getCachedImage(this.imageResourceIds[i]));
            }
        }
    }

    /**
     * Reset sprites with color effects
     */
    public final void resetWithColorEffects() {
        for (int i = 0; i < this.spriteImages.length; i++) {
            if (QuestManager.questState == 1) {
                this.spriteImages[i] = ImageProcessor.convertToGrayscale(ResourceManager.getCachedImage(this.imageResourceIds[i]));
            } else {
                this.forceCleanup();
            }
        }

        if (QuestManager.questState == 0) {
            this.loadSpriteSet(this.spriteSetId, false);
        }
    }

    /**
     * Apply brightness effects to sprites
     * @param brightnessMode Brightness mode (1 = brighten, other = normal)
     */
    public final void applyBrightnessEffect(int brightnessMode) {
        for (int i = 0; i < this.spriteImages.length; i++) {
            if (brightnessMode == 1) {
                this.spriteImages[i] = ImageProcessor.createBrightenedImage(ResourceManager.getCachedImage(this.imageResourceIds[i]), 100);
            } else {
                this.forceCleanup();
            }
        }

        if (brightnessMode == 0) {
            this.loadSpriteSet(this.spriteSetId, false);
        }
    }

    /**
     * Set animation with behavior control
     * @param animationId Animation ID to play
     * @param nextAnimation Next animation to play (-1 = loop, -2 = stop)
     * @param restartIfSame Restart animation if it's already playing
     * @return true if animation was set
     */
    public final boolean setAnimation(byte animationId, byte nextAnimation, boolean restartIfSame) {
        if (this.currentAnimationId == animationId && !restartIfSame) {
            this.currentAnimationId = animationId;
        } else {
            this.currentAnimationId = animationId;
            this.initializeFrame(0);
        }

        this.nextAnimationId = nextAnimation;
        return true;
    }

    /**
     * Set animation properties directly
     * @param animationId Animation ID
     * @param nextAnimation Next animation ID
     */
    public final void setAnimationProperties(byte animationId, byte nextAnimation) {
        this.currentAnimationId = animationId;
        this.nextAnimationId = nextAnimation;
    }

    /**
     * Initialize animation frame
     * @param frameIndex Frame index to initialize
     */
    private void initializeFrame(int frameIndex) {
        this.currentFrameIndex = frameIndex;

        if (this.animationData.animationSequences != null) {
            if (this.animationData.useExtendedFormat) {
                // Extended animation data (4 values per frame)
                this.frameTimer = this.animationData.animationSequences[this.currentAnimationId][this.currentFrameIndex << 2];
                this.totalFramesInAnimation = this.animationData.animationSequences[this.currentAnimationId].length / 4;
            } else {
                // Simple animation data (2 values per frame)
                this.frameTimer = this.animationData.animationSequences[this.currentAnimationId][this.currentFrameIndex << 1];
                this.totalFramesInAnimation = this.animationData.animationSequences[this.currentAnimationId].length / 2;
            }

            this.frameDelay = 0;

            // Adjust timer
            if (this.frameTimer > 0) {
                this.frameTimer--;
            } else if (this.frameDelay > 0) {
                this.frameDelay--;
            }
        }
    }

    /**
     * Update animation (call each frame)
     * @return true if animation completed/looped
     */
    public final boolean updateAnimation() {
        if (!this.animationEnabled) {
            return false;
        }

        if (this.frameTimer > 0) {
            this.frameTimer--;
        } else if (this.frameDelay > 0) {
            this.frameDelay--;
        } else {
            // Advance to next frame
            this.currentFrameIndex++;

            if (this.currentFrameIndex >= this.totalFramesInAnimation) {
                // Animation completed
                if (this.nextAnimationId >= 0) {
                    // Switch to next animation
                    this.setAnimation(this.nextAnimationId, (byte)-1, true);
                } else if (this.nextAnimationId == ANIM_STOP) {
                    // Stop at last frame
                    this.currentFrameIndex--;
                    this.initializeFrame(this.currentFrameIndex);
                } else if (this.nextAnimationId == ANIM_LOOP) {
                    // Loop animation
                    this.initializeFrame(0);
                }
                return true;
            }

            this.initializeFrame(this.currentFrameIndex);
        }

        return false;
    }

    /**
     * Check if animation is at last frame
     * @return true if at last frame
     */
    public final boolean isAtLastFrame() {
        return this.currentFrameIndex >= this.totalFramesInAnimation - 1;
    }

    /**
     * Check if at specific frame
     * @param frameIndex Frame index to check
     * @return true if current frame matches
     */
    public final boolean isAtFrame(int frameIndex) {
        return this.currentFrameIndex == frameIndex;
    }

    /**
     * Get current animation ID
     * @return Current animation ID
     */
    public final byte getCurrentAnimationId() {
        return this.currentAnimationId;
    }

    /**
     * Get current frame index
     * @return Current frame index
     */
    public final int getCurrentFrameIndex() {
        return this.currentFrameIndex;
    }

    /**
     * Enable/disable animation updates
     * @param enabled Animation enabled state
     */
    public final void setAnimationEnabled(boolean enabled) {
        this.animationEnabled = enabled;
    }

    /**
     * Get bounding box for animation frame
     * @param animationId Animation ID
     * @param flipMode Flip mode for calculations
     * @return int array [x, y, width, height] or null if invalid
     */
    public final int[] getAnimationBounds(int animationId, byte flipMode) {
        if (animationId < 0 || animationId >= this.animationData.animationSequences.length) {
            return null;
        }

        // Get first sprite bounds
        int[] firstSpriteBounds = getSpritePartBounds(this.animationData.animationSequences[animationId][1], flipMode);
        int minX = firstSpriteBounds[0];
        int maxX = firstSpriteBounds[0] + firstSpriteBounds[2];
        int minY = firstSpriteBounds[1];
        int maxY = firstSpriteBounds[1] + firstSpriteBounds[3];

        // Find bounding box that encompasses all sprite parts
        int frameCount = this.animationData.useExtendedFormat ?
                this.animationData.animationSequences[animationId].length / 4 :
                this.animationData.animationSequences[animationId].length / 2;

        for (int frame = 1; frame < frameCount; frame++) {
            int spriteIndex = this.animationData.useExtendedFormat ?
                    this.animationData.animationSequences[animationId][(frame << 2) + 1] :
                    this.animationData.animationSequences[animationId][(frame << 1) + 1];

            int[] spriteBounds = getSpritePartBounds(spriteIndex, flipMode);
            if (spriteBounds != null) {
                minX = Math.min(minX, spriteBounds[0]);
                maxX = Math.max(maxX, spriteBounds[0] + spriteBounds[2]);
                minY = Math.min(minY, spriteBounds[1]);
                maxY = Math.max(maxY, spriteBounds[1] + spriteBounds[3]);
            }
        }

        return new int[]{minX, minY, maxX - minX, maxY - minY};
    }

    /**
     * Get bounds for individual sprite part
     * @param spritePartId Sprite part ID
     * @param flipMode Flip mode for calculations
     * @return int array [x, y, width, height] or null if invalid
     */
    public final int[] getSpritePartBounds(int spritePartId, byte flipMode) {
        if (this.animationData.spriteCompositions[spritePartId].length <= 0) {
            return null;
        }

        // Calculate bounds based on sprite parts and flip mode
        short spriteId = this.animationData.spriteCompositions[spritePartId][0];
        int minX = this.animationData.spriteCompositions[spritePartId][1];
        int minY = this.animationData.spriteCompositions[spritePartId][2];

        // Find minimum bounds from all sprite parts
        for (int i = 0; i < this.animationData.spriteCompositions[spritePartId].length; i += 4) {
            spriteId = this.animationData.spriteCompositions[spritePartId][i];
            minX = Math.min(minX, this.animationData.spriteCompositions[spritePartId][i + 1]);
            minY = Math.min(minY, this.animationData.spriteCompositions[spritePartId][i + 2]);
        }

        // Calculate dimensions
        int[] dimensions = new int[2];
        int[] maxDimensions = calculateSpriteDimensions(0, spriteId, spritePartId, minX, minY, dimensions);
        int maxWidth = maxDimensions[0];
        int maxHeight = maxDimensions[1];

        // Find maximum bounds
        for (int i = 0; i < this.animationData.spriteCompositions[spritePartId].length; i += 4) {
            spriteId = this.animationData.spriteCompositions[spritePartId][i];
            int[] partDimensions = calculateSpriteDimensions(i, spriteId, spritePartId, minX, minY, dimensions);
            maxWidth = Math.max(maxWidth, partDimensions[0]);
            maxHeight = Math.max(maxHeight, partDimensions[1]);
        }

        // Apply flip mode transformations
        return applyFlipModeTransformation(spritePartId, flipMode, minX, minY, maxWidth, maxHeight);
    }

    /**
     * Apply flip mode transformation to sprite bounds
     */
    private int[] applyFlipModeTransformation(int spritePartId, byte flipMode, int minX, int minY, int maxWidth, int maxHeight) {
        short spriteId = this.animationData.spriteCompositions[spritePartId][0];

        switch (flipMode) {
            case FLIP_MODE_HORIZONTAL:
                if (this.animationData.spriteCompositions[spritePartId][3] % 2 == 1) {
                    minX = -this.animationData.spriteCompositions[spritePartId][1] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
                } else {
                    minX = -this.animationData.spriteCompositions[spritePartId][1] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
                }

                // Process all sprite parts for horizontal flip
                for (int i = 0; i < this.animationData.spriteCompositions[spritePartId].length; i += 4) {
                    spriteId = this.animationData.spriteCompositions[spritePartId][i];
                    int flippedX = calculateHorizontalFlipX(spritePartId, i, spriteId);
                    minX = Math.min(minX, flippedX);
                }
                break;

            case FLIP_MODE_BOTH:
                // Apply both horizontal and vertical flip
                return calculateBothFlipBounds(spritePartId, maxWidth, maxHeight);

            case FLIP_MODE_ROTATE_180:
                // Apply 180-degree rotation
                return calculateRotatedBounds(spritePartId, maxWidth, maxHeight);
        }

        return new int[]{minX, minY, maxWidth, maxHeight};
    }

    /**
     * Calculate horizontal flip X coordinate
     */
    private int calculateHorizontalFlipX(int spritePartId, int partIndex, short spriteId) {
        if (this.animationData.spriteCompositions[spritePartId][partIndex + 3] % 2 == 1) {
            return -this.animationData.spriteCompositions[spritePartId][partIndex + 1] -
                    this.animationData.spriteDefinitions[spriteId * 5 + 4];
        } else {
            return -this.animationData.spriteCompositions[spritePartId][partIndex + 1] -
                    this.animationData.spriteDefinitions[spriteId * 5 + 3];
        }
    }

    /**
     * Calculate bounds for both horizontal and vertical flip
     */
    private int[] calculateBothFlipBounds(int spritePartId, int maxWidth, int maxHeight) {
        short spriteId = this.animationData.spriteCompositions[spritePartId][0];
        int minX, minY;

        if (this.animationData.spriteCompositions[spritePartId][3] % 2 == 1) {
            minX = -this.animationData.spriteCompositions[spritePartId][1] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
            minY = -this.animationData.spriteCompositions[spritePartId][2] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
        } else {
            minX = -this.animationData.spriteCompositions[spritePartId][1] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
            minY = -this.animationData.spriteCompositions[spritePartId][2] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
        }

        // Process all parts for both flip
        for (int i = 0; i < this.animationData.spriteCompositions[spritePartId].length; i += 4) {
            spriteId = this.animationData.spriteCompositions[spritePartId][i];

            if (this.animationData.spriteCompositions[spritePartId][i + 3] % 2 == 1) {
                int flippedX = -this.animationData.spriteCompositions[spritePartId][i + 1] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
                int flippedY = -this.animationData.spriteCompositions[spritePartId][i + 2] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
                minX = Math.min(minX, flippedX);
                minY = Math.min(minY, flippedY);
            } else {
                int flippedX = -this.animationData.spriteCompositions[spritePartId][i + 1] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
                int flippedY = -this.animationData.spriteCompositions[spritePartId][i + 2] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
                minX = Math.min(minX, flippedX);
                minY = Math.min(minY, flippedY);
            }
        }

        return new int[]{minX, minY, maxWidth, maxHeight};
    }

    /**
     * Calculate bounds for 180-degree rotation
     */
    private int[] calculateRotatedBounds(int spritePartId, int maxWidth, int maxHeight) {
        short spriteId = this.animationData.spriteCompositions[spritePartId][0];
        int minY;

        if (this.animationData.spriteCompositions[spritePartId][3] % 2 == 1) {
            minY = -this.animationData.spriteCompositions[spritePartId][2] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
        } else {
            minY = -this.animationData.spriteCompositions[spritePartId][2] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
        }

        for (int i = 0; i < this.animationData.spriteCompositions[spritePartId].length; i += 4) {
            spriteId = this.animationData.spriteCompositions[spritePartId][i];

            if (this.animationData.spriteCompositions[spritePartId][i + 3] % 2 == 1) {
                int rotatedY = -this.animationData.spriteCompositions[spritePartId][i + 2] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
                minY = Math.min(minY, rotatedY);
            } else {
                int rotatedY = -this.animationData.spriteCompositions[spritePartId][i + 2] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
                minY = Math.min(minY, rotatedY);
            }
        }

        return new int[]{this.animationData.spriteCompositions[spritePartId][1], minY, maxWidth, maxHeight};
    }

    /**
     * Calculate sprite dimensions
     */
    private int[] calculateSpriteDimensions(int partIndex, int spriteId, int spritePartId, int minX, int minY, int[] result) {
        if (this.animationData.spriteCompositions[spritePartId][partIndex + 3] % 2 == 1) {
            result[1] = this.animationData.spriteCompositions[spritePartId][partIndex + 2] - minY + this.animationData.spriteDefinitions[spriteId * 5 + 3];
            result[0] = this.animationData.spriteCompositions[spritePartId][partIndex + 1] - minX + this.animationData.spriteDefinitions[spriteId * 5 + 4];
        } else {
            result[0] = this.animationData.spriteCompositions[spritePartId][partIndex + 1] - minX + this.animationData.spriteDefinitions[spriteId * 5 + 3];
            result[1] = this.animationData.spriteCompositions[spritePartId][partIndex + 2] - minY + this.animationData.spriteDefinitions[spriteId * 5 + 4];
        }
        return result;
    }

    /**
     * Render current animation frame
     * @param graphics Graphics context
     * @param x X position
     * @param y Y position
     * @param flipMode Flip mode
     */
    public final void renderCurrentFrame(Graphics graphics, int x, int y, byte flipMode) {
        if (this.animationData.useExtendedFormat) {
            this.renderSpriteComposite(graphics, this.animationData.animationSequences[this.currentAnimationId][(this.currentFrameIndex << 2) + 1], x, y, flipMode, 20);
        } else {
            this.renderSpriteComposite(graphics, this.animationData.animationSequences[this.currentAnimationId][(this.currentFrameIndex << 1) + 1], x, y, flipMode, 20);
        }
    }

    /**
     * Render sprite composite with multiple parts
     * @param graphics Graphics context
     * @param spriteCompositeId Composite sprite ID
     * @param x X position
     * @param y Y position
     * @param flipMode Flip mode
     * @param anchor Anchor point
     */
    public final void renderSpriteComposite(Graphics graphics, int spriteCompositeId, int x, int y, byte flipMode, int anchor) {
        if (this.animationData.spriteCompositions[spriteCompositeId].length > 0) {
            switch (flipMode) {
                case FLIP_MODE_NONE:
                    renderNormalSprite(graphics, spriteCompositeId, x, y);
                    break;
                case FLIP_MODE_HORIZONTAL:
                    renderHorizontalFlippedSprite(graphics, spriteCompositeId, x, y);
                    break;
                case FLIP_MODE_BOTH:
                    renderBothFlippedSprite(graphics, spriteCompositeId, x, y);
                    break;
                case FLIP_MODE_ROTATE_180:
                    renderRotatedSprite(graphics, spriteCompositeId, x, y);
                    break;
            }
        }
    }

    /**
     * Render sprite without transformation
     */
    private void renderNormalSprite(Graphics graphics, int spriteCompositeId, int x, int y) {
        for (int i = 0; i < this.animationData.spriteCompositions[spriteCompositeId].length; i += 4) {
            int spriteId = this.animationData.spriteCompositions[spriteCompositeId][i];
            int spriteX = x + this.animationData.spriteCompositions[spriteCompositeId][i + 1];
            int spriteY = y + this.animationData.spriteCompositions[spriteCompositeId][i + 2];
            int transform = FLIP_NONE[this.animationData.spriteCompositions[spriteCompositeId][i + 3]];

            this.renderIndividualSprite(graphics, spriteId, spriteX, spriteY, transform, 20);
        }
    }

    /**
     * Render horizontally flipped sprite
     */
    private void renderHorizontalFlippedSprite(Graphics graphics, int spriteCompositeId, int x, int y) {
        for (int i = 0; i < this.animationData.spriteCompositions[spriteCompositeId].length; i += 4) {
            int spriteId = this.animationData.spriteCompositions[spriteCompositeId][i];
            int spriteX, spriteY;

            if (this.animationData.spriteCompositions[spriteCompositeId][i + 3] % 2 == 1) {
                spriteX = x - this.animationData.spriteCompositions[spriteCompositeId][i + 1] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
            } else {
                spriteX = x - this.animationData.spriteCompositions[spriteCompositeId][i + 1] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
            }

            spriteY = y + this.animationData.spriteCompositions[spriteCompositeId][i + 2];
            int transform = FLIP_HORIZONTAL[this.animationData.spriteCompositions[spriteCompositeId][i + 3]];

            this.renderIndividualSprite(graphics, spriteId, spriteX, spriteY, transform, 20);
        }
    }

    /**
     * Render sprite with both horizontal and vertical flip
     */
    private void renderBothFlippedSprite(Graphics graphics, int spriteCompositeId, int x, int y) {
        for (int i = 0; i < this.animationData.spriteCompositions[spriteCompositeId].length; i += 4) {
            int spriteId = this.animationData.spriteCompositions[spriteCompositeId][i];
            int spriteX, spriteY;

            if (this.animationData.spriteCompositions[spriteCompositeId][i + 3] % 2 == 1) {
                spriteX = x - this.animationData.spriteCompositions[spriteCompositeId][i + 1] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
                spriteY = y - this.animationData.spriteCompositions[spriteCompositeId][i + 2] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
            } else {
                spriteX = x - this.animationData.spriteCompositions[spriteCompositeId][i + 1] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
                spriteY = y - this.animationData.spriteCompositions[spriteCompositeId][i + 2] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
            }

            int transform = FLIP_VERTICAL[this.animationData.spriteCompositions[spriteCompositeId][i + 3]];
            this.renderIndividualSprite(graphics, spriteId, spriteX, spriteY, transform, 20);
        }
    }

    /**
     * Render sprite with 180-degree rotation
     */
    private void renderRotatedSprite(Graphics graphics, int spriteCompositeId, int x, int y) {
        for (int i = 0; i < this.animationData.spriteCompositions[spriteCompositeId].length; i += 4) {
            int spriteId = this.animationData.spriteCompositions[spriteCompositeId][i];
            int spriteX = x + this.animationData.spriteCompositions[spriteCompositeId][i + 1];
            int spriteY;

            if (this.animationData.spriteCompositions[spriteCompositeId][i + 3] % 2 == 1) {
                spriteY = y - this.animationData.spriteCompositions[spriteCompositeId][i + 2] - this.animationData.spriteDefinitions[spriteId * 5 + 3];
            } else {
                spriteY = y - this.animationData.spriteCompositions[spriteCompositeId][i + 2] - this.animationData.spriteDefinitions[spriteId * 5 + 4];
            }

            int transform = FLIP_BOTH[this.animationData.spriteCompositions[spriteCompositeId][i + 3]];
            this.renderIndividualSprite(graphics, spriteId, spriteX, spriteY, transform, 20);
        }
    }

    /**
     * Render individual sprite part
     * @param graphics Graphics context
     * @param spriteId Sprite ID in sprite sheet
     * @param x X position
     * @param y Y position
     * @param transform Transformation flags
     * @param anchor Anchor point
     */
    private void renderIndividualSprite(Graphics graphics, int spriteId, int x, int y, int transform, int anchor) {
        // Get sprite data: [imageIndex, srcX, srcY, width, height]
        int imageIndex = this.animationData.spriteDefinitions[spriteId * 5];
        int srcX = this.animationData.spriteDefinitions[spriteId * 5 + 1];
        int srcY = this.animationData.spriteDefinitions[spriteId * 5 + 2];
        int width = this.animationData.spriteDefinitions[spriteId * 5 + 3];
        int height = this.animationData.spriteDefinitions[spriteId * 5 + 4];

        graphics.drawRegion(this.spriteImages[imageIndex], srcX, srcY, width, height, transform, x, y, anchor);
    }

    /**
     * Check if sprite data is invalid
     * @return true if sprite data is null or invalid
     */
    public final boolean isInvalid() {
        return this.animationData == null || this.animationData.frameTriggers == null;
    }

    /**
     * Get frame trigger data for current frame
     * @return Frame trigger data array or null
     */
    public final short[] getCurrentFrameTriggers() {
        if (this.animationData.frameTriggers == null) {
            return null;
        }

        int frameDataIndex = this.animationData.useExtendedFormat ?
                this.animationData.animationSequences[this.currentAnimationId][(this.currentFrameIndex << 2) + 1] :
                this.animationData.animationSequences[this.currentAnimationId][(this.currentFrameIndex << 1) + 1];

        return this.animationData.frameTriggers[frameDataIndex];
    }

    /**
     * Get frame event data for current frame
     * @return Frame event data array or null
     */
    public final short[] getCurrentFrameEvents() {
        if (this.animationData.frameEvents == null) {
            return null;
        }

        int frameDataIndex = this.animationData.useExtendedFormat ?
                this.animationData.animationSequences[this.currentAnimationId][(this.currentFrameIndex << 2) + 1] :
                this.animationData.animationSequences[this.currentAnimationId][(this.currentFrameIndex << 1) + 1];

        return this.animationData.frameEvents[frameDataIndex];
    }

    /**
     * Get total number of animations
     * @return Number of animations in this sprite set
     */
    public final int getAnimationCount() {
        return this.animationData != null && this.animationData.animationSequences != null ?
                this.animationData.animationSequences.length : 0;
    }

    /**
     * Get total frames in current animation
     * @return Number of frames in current animation
     */
    public final int getCurrentAnimationFrameCount() {
        return this.totalFramesInAnimation;
    }

    /**
     * Get sprite set ID
     * @return Sprite set resource ID
     */
    public final int getSpriteSetId() {
        return this.spriteSetId;
    }

    /**
     * Set animation to specific frame
     * @param animationId Animation ID
     * @param frameIndex Frame index
     */
    public final void setAnimationFrame(byte animationId, int frameIndex) {
        this.currentAnimationId = animationId;
        this.initializeFrame(frameIndex);
    }

    /**
     * Get animation progress as percentage
     * @return Animation progress (0.0 to 1.0)
     */
    public final float getAnimationProgress() {
        if (this.totalFramesInAnimation <= 0) return 0.0f;
        return (float)this.currentFrameIndex / (float)this.totalFramesInAnimation;
    }

    /**
     * Check if animation system is enabled
     * @return true if animation updates are enabled
     */
    public final boolean isAnimationEnabled() {
        return this.animationEnabled;
    }

    /**
     * Get next animation ID
     * @return Next animation ID to play
     */
    public final byte getNextAnimationId() {
        return this.nextAnimationId;
    }

    /**
     * Get remaining time for current frame
     * @return Frame timer value
     */
    public final int getFrameTimer() {
        return this.frameTimer;
    }

    /**
     * Reset animation to beginning
     */
    public final void resetAnimation() {
        this.initializeFrame(0);
    }

    /**
     * Pause animation updates
     */
    public final void pauseAnimation() {
        this.animationEnabled = false;
    }

    /**
     * Resume animation updates
     */
    public final void resumeAnimation() {
        this.animationEnabled = true;
    }

    /**
     * Get string representation for debugging
     * @return String description of sprite manager state
     */
    public String toString() {
        return "SpriteManager{" +
                "spriteSetId=" + spriteSetId +
                ", currentAnimation=" + currentAnimationId +
                ", currentFrame=" + currentFrameIndex +
                ", totalFrames=" + totalFramesInAnimation +
                ", animationEnabled=" + animationEnabled +
                ", frameTimer=" + frameTimer + "}";
    }
}