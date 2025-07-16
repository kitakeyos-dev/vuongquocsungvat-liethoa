package game;

import me.kitakeyos.ManagedInputStream;
import javax.microedition.lcdui.Graphics;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Hashtable;

/**
 * Bitmap Font Renderer - custom font system for pixel-perfect text rendering
 * Loads font data from binary file and renders text using bitmap characters
 */
public final class BitmapFontRenderer {
    // Font properties
    public static byte fontHeight;                          // C4_f33 - Height of font in pixels
    private static byte[][] fontBitmapData;                 // C4_f34 - 2D array containing bitmap data for each character
    private static int[] characterWidths;                   // C4_f35 - Width of each character
    private static int[] characterOffsets;                  // C4_f36 - Offset positions in bitmap data
    private static Hashtable characterLookup;               // C4_f37 - Map from character to index
    public static int specialCharacterWidth;                // C4_f38 - Width of special character (cached)

    // Caching for performance
    private static String cachedString;                     // C4_f39 - Last calculated string (for caching)
    private static int cachedStringWidth;                   // C4_f40 - Cached width of last string
    private static char cachedCharacter;                    // C4_f41 - Last calculated character (for caching)
    private static int cachedCharacterWidth;                // C4_f42 - Cached width of last character

    // Text alignment constants
    public static final int ALIGN_CENTER_HORIZONTAL = 1;
    public static final int ALIGN_CENTER_VERTICAL = 2;
    public static final int ALIGN_RIGHT = 8;
    public static final int ALIGN_BOTTOM = 32;

    /**
     * Static initializer - loads font data from binary file
     */
    static {
        characterLookup = new Hashtable();
        loadFontData();
        specialCharacterWidth = calculateStringWidth("nhung1");
    }

    /**
     * Load font data from binary font file
     */
    private static void loadFontData() {
        try {
            InputStream fontStream = ManagedInputStream.openStream("/font.bin");
            DataInputStream dataInput = new DataInputStream(fontStream);

            // Read font metadata
            String characterSet = dataInput.readUTF();
            fontHeight = dataInput.readByte();

            int characterCount = characterSet.length();
            characterWidths = new int[characterCount];
            characterOffsets = new int[characterCount];

            // Read character widths and calculate offsets
            int totalWidth = 0;
            for (int i = 0; i < characterCount; i++) {
                characterWidths[i] = dataInput.readByte();
                characterOffsets[i] = totalWidth;
                totalWidth += characterWidths[i];

                // Build character lookup table
                characterLookup.put(new Integer(characterSet.charAt(i)), new Integer(i));
            }

            // Read bitmap data
            fontBitmapData = new byte[fontHeight][totalWidth];
            int bitPosition = 7;
            byte currentByte = 0;

            for (int row = 0; row < fontHeight; row++) {
                for (int col = 0; col < totalWidth; col++) {
                    bitPosition++;
                    if (bitPosition >= 8) {
                        bitPosition = 0;
                        currentByte = dataInput.readByte();
                    }

                    // Extract bit and store as pixel
                    if ((currentByte & 1) != 0) {
                        fontBitmapData[row][col] = 1;
                    }

                    currentByte = (byte) (currentByte >> 1);
                }
            }

            dataInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculate width of a string using cached results for performance
     * @param text String to measure
     * @return Width in pixels
     */
    public static int calculateStringWidth(String text) {
        if (text == cachedString) {
            return cachedStringWidth;
        } else {
            cachedString = text;
            return cachedStringWidth = calculateStringWidth(text, 0, text.length() - 1);
        }
    }

    /**
     * Calculate width of a substring
     * @param text String to measure
     * @param startIndex Start index (inclusive)
     * @param endIndex End index (inclusive)
     * @return Width in pixels
     */
    public static int calculateStringWidth(String text, int startIndex, int endIndex) {
        int totalWidth = 0;

        for (int i = startIndex; i < endIndex; i++) {
            Integer characterKey = new Integer(text.charAt(i));

            try {
                Integer characterIndex = (Integer) characterLookup.get(characterKey);
                totalWidth += characterWidths[characterIndex.intValue()];
            } catch (Exception e) {
                // Character not found in font - skip it
            }
        }

        return totalWidth;
    }

    /**
     * Get width of a single character using cached results
     * @param character Character to measure
     * @return Width in pixels
     */
    public static int getCharacterWidth(char character) {
        if (character == cachedCharacter) {
            return cachedCharacterWidth;
        } else {
            try {
                cachedCharacter = character;
                Integer characterIndex = (Integer) characterLookup.get(new Integer(character));
                return cachedCharacterWidth = characterWidths[characterIndex.intValue()];
            } catch (Exception e) {
                cachedCharacterWidth = 0;
                return 0;
            }
        }
    }

    /**
     * Draw text with white outline (for better visibility)
     * @param graphics Graphics context
     * @param text Text to draw
     * @param x X position
     * @param y Y position
     * @param originalColor Original color to restore after drawing
     */
    public static final void drawTextWithOutline(Graphics graphics, String text, int x, int y, int originalColor) {
        int savedColor = graphics.getColor();
        graphics.setColor(0xFFFFFF); // White outline
        drawText(graphics, text, x, y);
        graphics.setColor(savedColor);
    }

    /**
     * Draw text at specified position
     * @param graphics Graphics context
     * @param text Text to draw
     * @param x X position
     * @param y Y position
     */
    public static final void drawText(Graphics graphics, String text, int x, int y) {
        int currentX = x;
        int textLength = text.length();

        for (int i = 0; i < textLength; i++) {
            currentX += drawCharacter(graphics, text.charAt(i), currentX, y);
        }
    }

    /**
     * Draw text with alignment options
     * @param graphics Graphics context
     * @param text Text to draw
     * @param x X position
     * @param y Y position
     * @param alignment Alignment flags (combination of ALIGN_* constants)
     */
    public static final void drawTextWithAlignment(Graphics graphics, String text, int x, int y, int alignment) {
        int drawX = x;
        int drawY = y;

        // Calculate horizontal alignment
        if ((alignment & ALIGN_CENTER_HORIZONTAL) != 0) {
            drawX = x - calculateStringWidth(text) / 2;
        } else if ((alignment & ALIGN_RIGHT) != 0) {
            drawX = x - calculateStringWidth(text);
        }

        // Calculate vertical alignment
        if ((alignment & ALIGN_CENTER_VERTICAL) != 0) {
            drawY = y - fontHeight / 2;
        } else if ((alignment & ALIGN_BOTTOM) != 0) {
            drawY = y - fontHeight;
        }

        // Draw text
        int currentX = drawX;
        int textLength = text.length();
        for (int i = 0; i < textLength; i++) {
            currentX += drawCharacter(graphics, text.charAt(i), currentX, drawY);
        }
    }

    /**
     * Draw a single character
     * @param graphics Graphics context
     * @param character Character to draw
     * @param x X position
     * @param y Y position
     * @return Width of the drawn character
     */
    public static final int drawCharacter(Graphics graphics, char character, int x, int y) {
        Integer characterKey = new Integer(character);

        try {
            Integer characterIndex = (Integer) characterLookup.get(characterKey);
            int charIndexValue = characterIndex.intValue();
            int characterWidth = characterWidths[charIndexValue];
            int bitmapOffset = characterOffsets[charIndexValue];

            // Draw character bitmap
            for (int row = 0; row < fontHeight; row++) {
                byte[] bitmapRow = fontBitmapData[row];

                for (int col = 0; col < characterWidth; col++) {
                    if (bitmapRow[col + bitmapOffset] != 0) {
                        // Draw pixel
                        graphics.drawLine(x + col, y + row, x + col, y + row);
                    }
                }
            }

            return characterWidth;
        } catch (Exception e) {
            return 0; // Character not found
        }
    }

    /**
     * Word wrap text to fit within specified width
     * @param text Text to wrap
     * @param maxWidth Maximum width in pixels
     * @return Array of wrapped lines
     */
    public static String[] wrapText(String text, int maxWidth) {
        String[] tempLines = new String[50]; // Temporary array
        int textLength = text.length();
        int wordWrapThreshold = maxWidth - specialCharacterWidth;
        int currentWidth = 0;
        int lineCount = 0;
        StringBuffer currentLine = new StringBuffer();

        for (int i = 0; i < textLength; i++) {
            char currentChar = text.charAt(i);
            int charWidth = getCharacterWidth(currentChar);

            // Check if adding this character would exceed the width
            if ((currentWidth + charWidth) <= maxWidth &&
                    (currentChar != ' ' || (currentWidth + charWidth) <= wordWrapThreshold)) {
                currentLine.append(currentChar);
                currentWidth += charWidth;
            } else {
                // Line is full, start new line
                tempLines[lineCount++] = currentLine.toString();
                currentLine = new StringBuffer();

                if (currentChar != ' ') {
                    // Add character to new line
                    currentWidth = charWidth;
                    currentLine.append(currentChar);
                } else {
                    // Skip space at beginning of new line
                    currentWidth = 0;
                }
            }
        }

        // Add final line if it has content
        if (currentLine.length() > 0) {
            tempLines[lineCount++] = currentLine.toString();
        }

        // Create final array with exact size
        String[] finalLines = new String[lineCount];
        System.arraycopy(tempLines, 0, finalLines, 0, lineCount);
        return finalLines;
    }

    /**
     * Get font height
     * @return Font height in pixels
     */
    public static int getFontHeight() {
        return fontHeight;
    }

    /**
     * Check if character is supported by this font
     * @param character Character to check
     * @return true if character is available
     */
    public static boolean isCharacterSupported(char character) {
        return characterLookup.containsKey(new Integer(character));
    }

    /**
     * Get number of supported characters
     * @return Number of characters in font
     */
    public static int getSupportedCharacterCount() {
        return characterWidths != null ? characterWidths.length : 0;
    }

    /**
     * Draw text centered in a rectangle
     * @param graphics Graphics context
     * @param text Text to draw
     * @param x Rectangle X
     * @param y Rectangle Y
     * @param width Rectangle width
     * @param height Rectangle height
     */
    public static void drawCenteredText(Graphics graphics, String text, int x, int y, int width, int height) {
        int textWidth = calculateStringWidth(text);
        int centerX = x + (width - textWidth) / 2;
        int centerY = y + (height - fontHeight) / 2;
        drawText(graphics, text, centerX, centerY);
    }

    /**
     * Draw multiline text with line spacing
     * @param graphics Graphics context
     * @param lines Array of text lines
     * @param x X position
     * @param y Y position
     * @param lineSpacing Additional spacing between lines
     */
    public static void drawMultilineText(Graphics graphics, String[] lines, int x, int y, int lineSpacing) {
        int currentY = y;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i] != null) {
                drawText(graphics, lines[i], x, currentY);
                currentY += fontHeight + lineSpacing;
            }
        }
    }

    /**
     * Calculate height of multiline text
     * @param lines Array of text lines
     * @param lineSpacing Additional spacing between lines
     * @return Total height in pixels
     */
    public static int getMultilineTextHeight(String[] lines, int lineSpacing) {
        int validLines = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i] != null) {
                validLines++;
            }
        }
        return validLines * fontHeight + (validLines - 1) * lineSpacing;
    }

    /**
     * Get maximum width from array of text lines
     * @param lines Array of text lines
     * @return Maximum width in pixels
     */
    public static int getMaxLineWidth(String[] lines) {
        int maxWidth = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i] != null) {
                int lineWidth = calculateStringWidth(lines[i]);
                if (lineWidth > maxWidth) {
                    maxWidth = lineWidth;
                }
            }
        }
        return maxWidth;
    }

    /**
     * Clear cached values (call when font data changes)
     */
    public static void clearCache() {
        cachedString = null;
        cachedStringWidth = 0;
        cachedCharacter = 0;
        cachedCharacterWidth = 0;
    }

    /**
     * Get memory usage estimate
     * @return Estimated memory usage in bytes
     */
    public static int getEstimatedMemoryUsage() {
        int bitmapSize = 0;
        if (fontBitmapData != null) {
            for (int i = 0; i < fontBitmapData.length; i++) {
                if (fontBitmapData[i] != null) {
                    bitmapSize += fontBitmapData[i].length;
                }
            }
        }

        int lookupSize = characterLookup.size() * 8; // Rough estimate
        int arraySize = (characterWidths != null ? characterWidths.length * 4 : 0) +
                (characterOffsets != null ? characterOffsets.length * 4 : 0);

        return bitmapSize + lookupSize + arraySize;
    }

    /**
     * Get font information string
     * @return String describing font properties
     */
    public static String getFontInfo() {
        return "BitmapFont{" +
                "height=" + fontHeight +
                ", characters=" + getSupportedCharacterCount() +
                ", memoryUsage=" + getEstimatedMemoryUsage() + "B" +
                "}";
    }
}