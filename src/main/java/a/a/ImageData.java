package a.a;

import e.a.a.a.config.MessageConfig;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 * Image Data - container for pixel data and image manipulation
 * Also contains utility methods for text rendering and data storage
 */
public class ImageData {
   // Pixel data
   public int[] pixels;                                // C42_f671 - Pixel array (ARGB format)
   public int width;                                   // C42_f672 - Image width
   public int height;                                  // C42_f673 - Image height
   public int offsetX;                                 // C42_f674 - X offset for positioning
   public int offsetY;                                 // C42_f675 - Y offset for positioning
   public int totalPixels;                             // C42_f676 - Total number of pixels

   /**
    * Set pixel data for this ImageData
    * @param pixelArray Array of pixels in ARGB format
    * @param imageWidth Width of the image
    * @param imageHeight Height of the image
    */
   public final void setPixelData(int[] pixelArray, int imageWidth, int imageHeight) {
      this.pixels = pixelArray;
      this.width = imageWidth;
      this.height = imageHeight;
      this.totalPixels = pixelArray.length;
   }

   /**
    * Create a copy of this ImageData
    * @return New ImageData instance with copied data
    */
   public final ImageData createCopy() {
      ImageData copy = new ImageData();
      copy.pixels = new int[this.pixels.length];
      System.arraycopy(this.pixels, 0, copy.pixels, 0, copy.pixels.length);
      copy.width = this.width;
      copy.height = this.height;
      copy.totalPixels = this.pixels.length;
      copy.offsetX = this.offsetX;
      copy.offsetY = this.offsetY;
      return copy;
   }

   /**
    * Calculate text width using Font
    * @param text Text to measure
    * @param font Font to use for measurement
    * @return Total width in pixels
    */
   public static int calculateTextWidth(String text, Font font) {
      int totalWidth = 0;

      for (int i = 0; i < text.length(); i++) {
         char character = text.charAt(i);
         totalWidth += font.charWidth(character);
      }

      return totalWidth;
   }

   /**
    * Render text with word wrapping and pagination
    * @param graphics Graphics context for drawing
    * @param text Text to render
    * @param font Font to use
    * @param maxWidth Maximum width before wrapping
    * @param maxHeight Maximum height for text area
    * @param startLine Line to start rendering from
    * @param lineSpacing Spacing between lines
    * @param currentY Current Y position
    * @param returnValue Return value parameter (modified)
    * @return Array containing [final Y position, page number]
    */
   public static int[] renderWrappedText(Graphics graphics, String text, Font font, int maxWidth,
                                         int maxHeight, int startLine, int lineSpacing,
                                         int currentY, int returnValue) {
      int currentLine = 0;
      int pageNumber = 0;

      while (true) {
         int availableWidth = maxWidth;
         Font currentFont = font;
         String remainingText = text;
         int textIndex = 0;
         int lineWidth = 0;

         // Find line break point
         int breakPoint;
         while (true) {
            if (textIndex >= remainingText.length()) {
               breakPoint = 0;
               break;
            }

            char currentChar = remainingText.charAt(textIndex);
            if (currentChar == '\n') {
               breakPoint = textIndex + 1;
               break;
            }

            lineWidth += currentFont.charWidth(currentChar);
            if (lineWidth > availableWidth) {
               breakPoint = textIndex;
               break;
            }

            textIndex++;
         }

         returnValue = breakPoint;

         // If no break point found, render remaining text
         if (breakPoint == 0) {
            graphics.drawString(text, 5, currentY, Graphics.TOP | Graphics.LEFT);
            return new int[]{currentY, pageNumber};
         }

         // Extract line to render
         String lineToRender;
         if (text.charAt(breakPoint - 1) == '\n') {
            lineToRender = text.substring(0, breakPoint - 1);
         } else {
            lineToRender = text.substring(0, breakPoint);
         }

         // Render line if within visible area
         if (currentLine >= startLine && currentY < maxHeight) {
            graphics.drawString(lineToRender, 5, currentY, Graphics.TOP | Graphics.LEFT);
            currentY = currentY + font.getHeight() + 5;
         }

         // Check if exceeded page height
         if (currentY >= maxHeight) {
            pageNumber++;
         }

         currentLine++;

         // Continue with remaining text
         text = text.substring(breakPoint);
      }
   }

   /**
    * Get pixel at specific coordinates
    * @param x X coordinate
    * @param y Y coordinate
    * @return Pixel value or 0 if out of bounds
    */
   public final int getPixel(int x, int y) {
      if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
         return this.pixels[y * this.width + x];
      }
      return 0;
   }

   /**
    * Set pixel at specific coordinates
    * @param x X coordinate
    * @param y Y coordinate
    * @param color Pixel color in ARGB format
    */
   public final void setPixel(int x, int y, int color) {
      if (x >= 0 && x < this.width && y >= 0 && y < this.height) {
         this.pixels[y * this.width + x] = color;
      }
   }

   /**
    * Fill entire image with specified color
    * @param color Fill color in ARGB format
    */
   public final void fillWithColor(int color) {
      for (int i = 0; i < this.totalPixels; i++) {
         this.pixels[i] = color;
      }
   }

   /**
    * Clear image (set all pixels to transparent)
    */
   public final void clear() {
      fillWithColor(0); // Transparent
   }

   /**
    * Check if ImageData is valid
    * @return true if has valid pixel data
    */
   public final boolean isValid() {
      return this.pixels != null && this.width > 0 && this.height > 0;
   }

   /**
    * Get image dimensions
    * @return Array containing [width, height]
    */
   public final int[] getDimensions() {
      return new int[]{this.width, this.height};
   }

   /**
    * Get image offset
    * @return Array containing [offsetX, offsetY]
    */
   public final int[] getOffset() {
      return new int[]{this.offsetX, this.offsetY};
   }

   /**
    * Set image offset
    * @param x X offset
    * @param y Y offset
    */
   public final void setOffset(int x, int y) {
      this.offsetX = x;
      this.offsetY = y;
   }

   /**
    * Get memory usage in bytes
    * @return Estimated memory usage
    */
   public final int getMemoryUsage() {
      return this.pixels != null ? this.pixels.length * 4 : 0; // 4 bytes per pixel (ARGB)
   }

   /**
    * Resize pixel array (does not scale image content)
    * @param newWidth New width
    * @param newHeight New height
    */
   public final void resize(int newWidth, int newHeight) {
      this.pixels = new int[newWidth * newHeight];
      this.width = newWidth;
      this.height = newHeight;
      this.totalPixels = newWidth * newHeight;
   }

   /**
    * Free pixel data memory
    */
   public final void dispose() {
      this.pixels = null;
      this.width = 0;
      this.height = 0;
      this.totalPixels = 0;
      this.offsetX = 0;
      this.offsetY = 0;
   }

   /**
    * Get string representation for debugging
    * @return String description of ImageData
    */
   @Override
   public String toString() {
      return "ImageData{" +
              "width=" + width +
              ", height=" + height +
              ", offset=(" + offsetX + "," + offsetY + ")" +
              ", pixels=" + (pixels != null ? pixels.length : 0) +
              ", memoryUsage=" + getMemoryUsage() + "B" +
              "}";
   }
}