package layout;

import a.GameEngineBase;
import a.GameUtils;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 * Data class for text-based UI components with rendering capabilities
 */
public final class ComponentData {
    // Text content and formatting
    public String text = "";                          // C12_f179 - The actual text content
    private int[] scrollPosition = new int[2];        // C12_f180 - Text scroll position [x, y]
    private int scrollSpeed;                          // C12_f181 - Speed of text scrolling
    public int paddingHorizontal;                     // C12_f182 - Horizontal padding
    public int paddingVertical;                       // C12_f183 - Vertical padding
    public boolean isScrollingEnabled;                // C12_f184 - Whether text can scroll

    // Color scheme for focused/selected state
    public int focusedBackgroundColor;                // C12_f185 - Background color when focused
    public int focusedBorderColor;                    // C12_f186 - Border color when focused
    public int focusedTextColor;                      // C12_f187 - Text color when focused

    // Animation and state tracking
    private boolean isAnimating = false;              // C12_f188 - Whether currently animating
    private int animationFrame = 0;                   // C12_f189 - Current animation frame
    public byte spriteBlendMode = -1;                 // C12_f190 - Blend mode for sprites

    // Sprite renderers for visual elements
    public AnimatedRenderer focusedRenderer;              // C12_f191 - shown when focused

    // Color scheme for normal/unfocused state
    public int backgroundColor;                 // C12_f192 - Background color when normal
    public int borderColor;                     // C12_f193 - Border color when normal
    public int textColor;                       // C12_f194 - Text color when normal
    public AnimatedRenderer renderer;               // C12_f195 - Sprite shown when normal

    // Layout and rendering state
    private Rectangle bounds = null;                  // C12_f196 - Component bounds
    private String lastRenderedText = "";             // C12_f197 - Cache of last rendered text
    private boolean[] scrollState = new boolean[]{false, false}; // C12_f198 - Scroll state flags

    /**
     * Initialize text component data with default values
     */
    public ComponentData() {
        this.scrollPosition[1] = this.scrollPosition[0] = 0;
        this.scrollSpeed = 2;
        this.paddingHorizontal = 4;
        this.paddingVertical = 4;
        this.isScrollingEnabled = false;

        // Default colors (white)
        this.focusedBackgroundColor = 16777215;
        this.focusedBorderColor = 16777215;
        this.focusedTextColor = 16777215;
        this.backgroundColor = 16777215;
        this.borderColor = 16777215;
        this.textColor = 16777215;

        this.focusedRenderer = null;
        this.renderer = null;
        this.isAnimating = false;
        this.spriteBlendMode = -1;
        this.animationFrame = 0;
    }

    /**
     * Fill a rectangle with the specified color
     *
     * @param graphics Graphics context
     * @param rect     Rectangle to fill
     * @param color    Fill color (with alpha channel)
     */
    private static void fillRect(Graphics graphics, Rectangle rect, int color) {
        if (graphics != null && (color >> 24) != 0) {
            graphics.setColor(color);
            graphics.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    /**
     * Draw a rectangle outline with the specified color
     *
     * @param graphics Graphics context
     * @param rect     Rectangle to draw
     * @param color    Border color (with alpha channel)
     */
    private static void drawRect(Graphics graphics, Rectangle rect, int color) {
        if (graphics != null && (color >> 24) != 0) {
            graphics.setColor(color);
            graphics.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    /**
     * Set the bounds for this component
     *
     * @param bounds New bounds rectangle
     */
    public final void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    /**
     * Reset scroll position to start of text
     */
    public final void resetScrollPosition() {
        this.scrollPosition[1] = -this.bounds.height;
        this.scrollPosition[0] = -this.bounds.width / 2;
        this.scrollState[0] = this.scrollState[1] = false;
    }

    /**
     * Check if text scrolling is complete
     *
     * @return true if scrolling has finished
     */
    public final boolean isScrollingComplete() {
        return this.scrollState[1];
    }

    /**
     * Render text with proper handling of progress bars and scrolling
     *
     * @param graphics         Graphics context
     * @param bounds           Text bounds
     * @param text             Text to render
     * @param textColor        Text color
     * @param padding          Text padding
     * @param scrollingEnabled Whether scrolling is enabled
     * @param blendMode        Sprite blend mode
     * @param textRenderer     Text rendering configuration
     * @param componentState   Component state identifier
     */
    private void renderText(final Graphics graphics, final Rectangle bounds, final String text,
                            final int textColor, final int padding, final boolean scrollingEnabled,
                            final byte blendMode, final TextRenderer textRenderer, final byte componentState) {
        if (graphics != null && (textColor >> 24) != 0) {
            // Handle progress bar rendering
            if (text.startsWith("#P") && text.length() > 2) {
                boolean parseError = false;
                int percentage = 0;

                try {
                    percentage = Integer.parseInt(text.substring(2).trim());
                } catch (Exception e) {
                    parseError = true;
                }

                if (!parseError && percentage >= 0 && percentage <= 100) {
                    int progressWidth = percentage * bounds.width / 100;
                    graphics.setColor(textColor);
                    graphics.fillRect(bounds.x + 1, bounds.y + 1, progressWidth - 1, bounds.height - 1);
                    return;
                }
            }

            // Standard text rendering
            Font font = Font.getFont(0, 0, 8);
            if (!this.lastRenderedText.equals(text)) {
                this.scrollPosition[1] = -bounds.height;
                this.scrollPosition[0] = -bounds.width / 2;
            }

            this.lastRenderedText = text;
            GameUtils.a(graphics, text, textColor, bounds.x, bounds.y, font.getHeight(),
                    bounds.width, bounds.height, font, scrollingEnabled, padding,
                    this.scrollPosition, this.scrollSpeed, blendMode, textRenderer,
                    componentState, this.scrollState);
        }
    }

    /**
     * Main rendering method for the text component
     *
     * @param graphics       Graphics context for rendering
     * @param x              X coordinate
     * @param y              Y coordinate
     * @param width          Component width
     * @param height         Component height
     * @param isFocused      Whether component has focus
     * @param blendMode      Sprite blend mode
     * @param componentState Component state
     * @param textRenderer   Text rendering configuration
     */
    public final void render(Graphics graphics, int x, int y, int width, int height,
                             boolean isFocused, byte blendMode, byte componentState,
                             TextRenderer textRenderer) {
        if (this.isAnimating != isFocused || this.animationFrame == 0) {
            this.animationFrame = -1;
            this.isAnimating = isFocused;
        }

        this.bounds = new Rectangle(x, y, width, height);
        graphics.setClip(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());

        if (isFocused) {
            // Render focused state
            fillRect(graphics, this.bounds, this.focusedBackgroundColor);
            drawRect(graphics, this.bounds, this.focusedBorderColor);

            if (this.focusedRenderer != null) {
                this.focusedRenderer.render(graphics, this.bounds, this.paddingVertical);
            }

            this.renderText(graphics, this.bounds, this.text, this.focusedTextColor,
                    this.paddingHorizontal, this.isScrollingEnabled, blendMode,
                    textRenderer, componentState);
        } else {
            // Render normal state
            fillRect(graphics, this.bounds, this.backgroundColor);
            drawRect(graphics, this.bounds, this.borderColor);

            if (this.renderer != null) {
                this.renderer.render(graphics, this.bounds, this.paddingVertical);
            }

            this.renderText(graphics, this.bounds, this.text, this.textColor,
                    this.paddingHorizontal, this.isScrollingEnabled, blendMode,
                    textRenderer, componentState);
        }
    }

    /**
     * Clean up sprite resources
     */
    public final void cleanup() {
        if (this.focusedRenderer != null) {
            this.focusedRenderer.cleanup();
            this.focusedRenderer = null;
        }

        if (this.renderer != null) {
            this.renderer.cleanup();
            this.renderer = null;
        }
    }
}