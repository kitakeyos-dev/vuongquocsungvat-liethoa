package layout;

import game.C4;

import javax.microedition.lcdui.Graphics;

/**
 * Utility class for rendering text with specific styling
 */
public final class TextRenderer {

    /**
     * Render text with specified color and alignment
     *
     * @param text      Text to render
     * @param x         X coordinate
     * @param y         Y coordinate
     * @param alignment Text alignment
     * @param color     Text color
     * @param graphics  Graphics context
     */
    public static void renderText(String text, int x, int y, int alignment, int color, Graphics graphics) {
        graphics.setColor(color);
        C4.b(graphics, text, x, y, alignment);
    }

    /**
     * Calculate text width with specified parameters
     *
     * @param text     Text to measure
     * @param fontType Font type identifier
     * @param maxWidth Maximum width constraint
     * @return Calculated text width
     */
    public static int calculateTextWidth(String text, int fontType, int maxWidth) {
        return C4.a(text, fontType, maxWidth + fontType);
    }
}
