package layout;

import a.b.SpriteManager;

import javax.microedition.lcdui.Graphics;

/**
 * Sprite Renderer - component for rendering sprites and images with alignment
 * Supports different sprite types and 9 alignment positions within target rectangles
 */
public final class SpriteRenderer {

    // Sprite data and resource manager
    private SpriteManager spriteManager;
    private byte spriteState = 0;
    private short spriteIndex = -1;
    public byte spriteType = 4;

    // Sprite type constants
    public static final byte TYPE_UNKNOWN_1 = 2;
    public static final byte TYPE_ANIMATED_SPRITE = 3;
    public static final byte TYPE_STATIC_IMAGE = 4;

    // Alignment constants (matching previous anchor system)
    public static final int ALIGN_TOP_LEFT = 0;
    public static final int ALIGN_TOP_CENTER = 1;
    public static final int ALIGN_TOP_RIGHT = 2;
    public static final int ALIGN_MIDDLE_LEFT = 3;
    public static final int ALIGN_MIDDLE_CENTER = 4;
    public static final int ALIGN_MIDDLE_RIGHT = 5;
    public static final int ALIGN_BOTTOM_LEFT = 6;
    public static final int ALIGN_BOTTOM_CENTER = 7;
    public static final int ALIGN_BOTTOM_RIGHT = 8;

    /**
     * Default constructor
     */
    public SpriteRenderer() {
        this.spriteManager = null;
        this.spriteIndex = -1;
        this.spriteType = TYPE_STATIC_IMAGE;
    }

    /**
     * Initialize sprite with resource ID
     *
     * @param resourceId     Sprite resource identifier
     * @param enableCaching  Enable sprite caching for performance
     * @param animationState Animation state for animated sprites
     */
    public final void initializeSprite(int resourceId, boolean enableCaching, byte animationState) {
        if (resourceId != -1) {
            this.spriteManager = new SpriteManager();
            this.spriteManager.loadSpriteSet(resourceId, enableCaching);

            // Setup animated sprite if needed
            if (this.spriteType == TYPE_ANIMATED_SPRITE) {
                this.spriteManager.setAnimation((byte) this.spriteIndex, animationState, true);
            }
        }
    }

    /**
     * Set sprite animation frame
     *
     * @param frameIndex     Frame index to display
     * @param animationState Animation state
     */
    public final void setAnimationFrame(byte frameIndex, byte animationState) {
        this.spriteIndex = frameIndex;
        if (this.spriteManager != null) {
            this.spriteManager.setAnimation(frameIndex, animationState, true);
        }
    }

    /**
     * Get sprite manager reference
     *
     * @return Sprite manager instance
     */
    public final SpriteManager getSpriteManager() {
        return this.spriteManager;
    }

    /**
     * Set sprite index
     *
     * @param index Sprite index in sprite sheet
     */
    public final void setSpriteIndex(int index) {
        this.spriteIndex = (short) index;
    }

    /**
     * Get current sprite index
     *
     * @return Current sprite index
     */
    public final short getSpriteIndex() {
        return this.spriteIndex;
    }

    /**
     * Set sprite state
     *
     * @param state Sprite state (1 = active, 0 = inactive)
     */
    public final void setSpriteState(byte state) {
        this.spriteState = state;
    }

    /**
     * Get current sprite state
     *
     * @return Sprite state
     */
    public final byte getSpriteState() {
        return this.spriteState;
    }

    /**
     * Set sprite type
     *
     * @param type Sprite type (TYPE_ANIMATED_SPRITE, TYPE_STATIC_IMAGE, etc.)
     */
    public final void setSpriteType(byte type) {
        this.spriteType = type;
    }

    /**
     * Get sprite type
     *
     * @return Current sprite type
     */
    public final byte getSpriteType() {
        return this.spriteType;
    }

    /**
     * Render sprite within target rectangle with specified alignment
     *
     * @param graphics   Graphics context for drawing
     * @param targetArea Target rectangle to render within
     * @param alignment  Alignment mode (0-8 for different positions)
     */
    public final void render(Graphics graphics, Rectangle targetArea, int alignment) {
        if (this.spriteManager == null) {
            return;
        }

        // Get sprite bounds
        Rectangle spriteBounds = getSpriteBounds();
        if (spriteBounds == null) {
            return;
        }

        // Calculate render position based on alignment
        int renderX = calculateAlignedX(targetArea, spriteBounds, alignment);
        int renderY = calculateAlignedY(targetArea, spriteBounds, alignment);

        // Render based on sprite type
        renderSprite(graphics, renderX, renderY);
    }

    /**
     * Get bounds of current sprite
     *
     * @return Rectangle containing sprite bounds, or null if no sprite
     */
    private Rectangle getSpriteBounds() {
        if (this.spriteIndex == -1) {
            return null;
        }

        int[] bounds = new int[4];

        switch (this.spriteType) {
            case TYPE_UNKNOWN_1:
                bounds = this.spriteManager.getSpritePartBounds(this.spriteIndex, (byte) 0);
                break;
            case TYPE_ANIMATED_SPRITE:
                bounds = this.spriteManager.getAnimationBounds((int) this.spriteIndex, (byte) 0);
                break;
            default:
                return null;
        }

        return new Rectangle(bounds[0], bounds[1], bounds[2], bounds[3]);
    }

    /**
     * Calculate X position based on horizontal alignment
     *
     * @param targetArea   Target rectangle
     * @param spriteBounds Sprite bounds
     * @param alignment    Alignment mode
     * @return Calculated X position
     */
    private int calculateAlignedX(Rectangle targetArea, Rectangle spriteBounds, int alignment) {
        switch (alignment) {
            case ALIGN_TOP_LEFT:
            case ALIGN_MIDDLE_LEFT:
            case ALIGN_BOTTOM_LEFT:
                // Left-aligned
                return targetArea.x - spriteBounds.x;

            case ALIGN_TOP_CENTER:
            case ALIGN_MIDDLE_CENTER:
            case ALIGN_BOTTOM_CENTER:
                // Center-aligned
                return targetArea.x + (targetArea.width - spriteBounds.width) / 2 - spriteBounds.x;

            case ALIGN_TOP_RIGHT:
            case ALIGN_MIDDLE_RIGHT:
            case ALIGN_BOTTOM_RIGHT:
                // Right-aligned
                return targetArea.x + (targetArea.width - spriteBounds.width) - spriteBounds.x;

            default:
                return targetArea.x - spriteBounds.x;
        }
    }

    /**
     * Calculate Y position based on vertical alignment
     *
     * @param targetArea   Target rectangle
     * @param spriteBounds Sprite bounds
     * @param alignment    Alignment mode
     * @return Calculated Y position
     */
    private int calculateAlignedY(Rectangle targetArea, Rectangle spriteBounds, int alignment) {
        switch (alignment) {
            case ALIGN_TOP_LEFT:
            case ALIGN_TOP_CENTER:
            case ALIGN_TOP_RIGHT:
                // Top-aligned
                return targetArea.y - spriteBounds.y;

            case ALIGN_MIDDLE_LEFT:
            case ALIGN_MIDDLE_CENTER:
            case ALIGN_MIDDLE_RIGHT:
                // Middle-aligned
                return targetArea.y + (targetArea.height - spriteBounds.height) / 2 - spriteBounds.y;

            case ALIGN_BOTTOM_LEFT:
            case ALIGN_BOTTOM_CENTER:
            case ALIGN_BOTTOM_RIGHT:
                // Bottom-aligned
                return targetArea.y + (targetArea.height - spriteBounds.height) - spriteBounds.y;

            default:
                return targetArea.y - spriteBounds.y;
        }
    }

    /**
     * Render sprite at specified position
     *
     * @param graphics Graphics context
     * @param x        X position
     * @param y        Y position
     */
    private void renderSprite(Graphics graphics, int x, int y) {
        if (this.spriteType == TYPE_ANIMATED_SPRITE) {
            // Render animated sprite
            this.spriteManager.renderCurrentFrame(graphics, x, y, this.spriteState);
        } else if (this.spriteType == TYPE_UNKNOWN_1) {
            // Render static sprite/image
            this.spriteManager.renderSpriteComposite(graphics, this.spriteIndex, x, y, (byte) 0, 20);
        }
    }

    /**
     * Update sprite animation
     * Called each frame to update animated sprites
     */
    public final void update() {
        if (this.spriteType == TYPE_ANIMATED_SPRITE && this.spriteManager != null) {
            this.spriteManager.updateAnimation();
        }
    }

    /**
     * Clean up sprite resources
     */
    public final void cleanup() {
        if (this.spriteManager != null) {
            this.spriteManager.forceCleanup();
            this.spriteManager = null;
        }
    }

    /**
     * Check if sprite is initialized
     *
     * @return true if sprite manager is loaded
     */
    public final boolean isInitialized() {
        return this.spriteManager != null;
    }

    /**
     * Check if sprite has valid index
     *
     * @return true if sprite index is valid
     */
    public final boolean hasValidSprite() {
        return this.spriteIndex != -1;
    }

    /**
     * Get sprite dimensions
     *
     * @return Rectangle with sprite width and height, or null if no sprite
     */
    public final Rectangle getSpriteDimensions() {
        Rectangle bounds = getSpriteBounds();
        if (bounds != null) {
            return new Rectangle(0, 0, bounds.width, bounds.height);
        }
        return null;
    }

    /**
     * Set sprite properties in one call
     *
     * @param resourceId    Resource ID
     * @param spriteIndex   Sprite index
     * @param spriteType    Sprite type
     * @param enableCaching Enable caching
     */
    public final void setupSprite(int resourceId, int spriteIndex, byte spriteType, boolean enableCaching) {
        this.spriteType = spriteType;
        this.setSpriteIndex(spriteIndex);
        this.initializeSprite(resourceId, enableCaching, (byte) 0);
    }

    /**
     * Render sprite with default center alignment
     *
     * @param graphics   Graphics context
     * @param targetArea Target rectangle
     */
    public final void renderCentered(Graphics graphics, Rectangle targetArea) {
        this.render(graphics, targetArea, ALIGN_MIDDLE_CENTER);
    }

    /**
     * Get string representation for debugging
     *
     * @return String description of renderer state
     */
    public String toString() {
        return "SpriteRenderer{" +
                "spriteIndex=" + spriteIndex +
                ", spriteType=" + spriteType +
                ", spriteState=" + spriteState +
                ", initialized=" + isInitialized() + "}";
    }
}