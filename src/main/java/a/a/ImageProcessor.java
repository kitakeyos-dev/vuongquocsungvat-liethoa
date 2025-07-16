package a.a;

import a.GameUtils;
import a.b.AnimatedSprite;
import javax.microedition.lcdui.Image;

/**
 * Image Processor - utility class for image manipulation and effects
 * Provides various image processing operations like scaling, color effects, and blending
 */
public final class ImageProcessor {
   // Blend mode constants
   private static byte BLEND_MODE_OR = 1;              // C69_f956 - Bitwise OR blending
   private static byte BLEND_MODE_REPLACE = 2;         // C69_f957 - Replace blending

   /**
    * Convert Image to ImageData with color correction
    * Converts white and black pixels to white for transparency
    * @param sourceImage Source image to convert
    * @param targetImageData Target ImageData object to store result
    * @return Processed ImageData object
    */
   public static ImageData convertImageWithColorCorrection(Image sourceImage, ImageData targetImageData) {
      int width = sourceImage.getWidth();
      int height = sourceImage.getHeight();
      int[] pixelData = new int[width * height];
      sourceImage.getRGB(pixelData, 0, width, 0, 0, width, height);

      // Convert white and black pixels to white (for transparency)
      for (int i = 0; i < pixelData.length; i++) {
         if (pixelData[i] == -1 || pixelData[i] == -16777216) {  // White or black
            pixelData[i] = 16777215;  // Set to white
         }
      }

      targetImageData.setPixelData(pixelData, width, height);
      return targetImageData;
   }

   /**
    * Render animated sprite to ImageData
    * Creates an image from sprite composite and converts to ImageData
    * @param sprite Animated sprite to render
    * @param compositeId Sprite composite ID to render
    * @param spriteBounds Sprite bounds [x, y, width, height]
    * @param flipMode Flip mode for sprite rendering
    * @param targetImageData Target ImageData to store result
    * @return ImageData with rendered sprite
    */
   public static ImageData renderSpriteToImageData(AnimatedSprite sprite, int compositeId, int[] spriteBounds,
                                                   byte flipMode, ImageData targetImageData) {
      // Create temporary image
      Image tempImage = Image.createImage(spriteBounds[2], spriteBounds[3]);
      tempImage.getGraphics().setColor(0);
      tempImage.getGraphics().fillRect(0, 0, spriteBounds[2], spriteBounds[3]);

      // Render sprite to image
      sprite.renderSpriteComposite(tempImage.getGraphics(), compositeId,
              -spriteBounds[0], -spriteBounds[1], flipMode, 20);

      // Store offset information
      targetImageData.offsetX = spriteBounds[0];
      targetImageData.offsetY = spriteBounds[1];

      return convertImageWithColorCorrection(tempImage, targetImageData);
   }

   /**
    * Scale ImageData to new dimensions
    * Uses nearest neighbor scaling algorithm
    * @param imageData ImageData to scale
    * @param newWidth Target width
    * @param newHeight Target height
    * @return Scaled ImageData
    */
   public static ImageData scaleImageData(ImageData imageData, int newWidth, int newHeight) {
      int[] scaledPixels = new int[newWidth * newHeight];

      for (int y = 0; y < newHeight; y++) {
         for (int x = 0; x < newWidth; x++) {
            // Calculate source pixel coordinates using nearest neighbor
            int sourceX = x * imageData.width / newWidth;
            int sourceY = y * imageData.height / newHeight;
            scaledPixels[x + y * newWidth] = imageData.pixels[sourceX + sourceY * imageData.width];
         }
      }

      imageData.setPixelData(scaledPixels, newWidth, newHeight);

      // Scale offset coordinates proportionally
      imageData.offsetX = imageData.offsetX * newWidth / imageData.width / 10;
      imageData.offsetY = imageData.offsetY * newWidth / imageData.width / 10;

      return imageData;
   }

   /**
    * Scale ImageData by percentage
    * @param imageData ImageData to scale
    * @param scalePercent Scale percentage (10 = 100%, 5 = 50%, 20 = 200%)
    * @return Scaled ImageData
    */
   public static ImageData scaleImageByPercent(ImageData imageData, int scalePercent) {
      int newWidth = imageData.width * scalePercent / 10;
      int newHeight = imageData.height * scalePercent / 10;
      int[] scaledPixels = new int[newWidth * newHeight];

      for (int y = 0; y < newHeight; y++) {
         for (int x = 0; x < newWidth; x++) {
            // Calculate source coordinates
            int sourceX = x * imageData.width / newWidth;
            int sourceY = y * imageData.height / newHeight;
            scaledPixels[x + y * newWidth] = imageData.pixels[sourceX + sourceY * imageData.width];
         }
      }

      imageData.setPixelData(scaledPixels, newWidth, newHeight);

      // Scale offsets proportionally
      imageData.offsetX = imageData.offsetX * scalePercent / 10;
      imageData.offsetY = imageData.offsetY * scalePercent / 10;

      return imageData;
   }

   /**
    * Apply brightness and contrast adjustment to ImageData
    * @param imageData ImageData to process
    * @param brightnessMultiplier Brightness multiplier (1.0 = normal)
    * @param brightnessOffset Brightness offset (-255 to 255)
    * @return Processed ImageData
    */
   public static ImageData adjustBrightnessContrast(ImageData imageData, int brightnessMultiplier, int brightnessOffset) {
      for (int y = 0; y < imageData.height; y++) {
         for (int x = 0; x < imageData.width; x++) {
            int pixelIndex = y * imageData.width + x;
            int pixel = imageData.pixels[pixelIndex];

            // Extract color components
            int alpha = pixel >> 24;
            int red = (pixel >> 16) & 255;
            int green = (pixel >> 8) & 255;
            int blue = pixel & 255;

            // Apply brightness/contrast adjustment
            red = red * brightnessMultiplier + brightnessOffset;
            green = green * brightnessMultiplier + brightnessOffset;
            blue = blue * brightnessMultiplier + brightnessOffset;

            // Clamp values to valid range
            red = clampColorValue(red);
            green = clampColorValue(green);
            blue = clampColorValue(blue);

            // Recombine color components
            imageData.pixels[pixelIndex] = (alpha << 24) | (red << 16) | (green << 8) | blue;
         }
      }

      return imageData;
   }

   /**
    * Apply alpha (transparency) to ImageData
    * @param imageData ImageData to process
    * @param alphaValue Alpha value (0-255, 0 = transparent, 255 = opaque)
    * @return ImageData with applied alpha
    */
   public static ImageData applyAlpha(ImageData imageData, int alphaValue) {
      if (alphaValue < 0 || alphaValue > 255) {
         return imageData;  // Invalid alpha value
      }

      for (int i = 0; i < imageData.totalPixels; i++) {
         int pixel = imageData.pixels[i];

         // Skip white and transparent pixels
         if (pixel != 16777215 && pixel != 0) {  // Not white and not transparent
            if (pixel == -16777216) {  // Black pixel
               imageData.pixels[i] = 0;  // Make transparent
            } else {
               // Apply alpha while preserving RGB
               imageData.pixels[i] = (alphaValue << 24) | (pixel & 16777215);
            }
         }
      }

      return imageData;
   }

   /**
    * Create brightened version of Image
    * @param sourceImage Source image to brighten
    * @param brightnessLevel Brightness level (unused in current implementation)
    * @return Brightened image
    */
   public static Image createBrightenedImage(Image sourceImage, int brightnessLevel) {
      ImageData imageData = new ImageData();
      int width = sourceImage.getWidth();
      int height = sourceImage.getHeight();

      // Create temporary image
      Image tempImage = Image.createImage(width, height);
      tempImage.getGraphics().setColor(0);
      tempImage.getGraphics().fillRect(0, 0, width, height);
      tempImage.getGraphics().drawImage(sourceImage, 0, 0, 20);

      // Convert to ImageData and apply brightness
      imageData.setPixelData(GameUtils.extractImageRGB(tempImage), width, height);
      applyAlpha(imageData, brightnessLevel);

      // Convert back to Image
      Image result = GameUtils.createRGBImage(imageData.pixels, imageData.width, imageData.height, true);
      imageData.pixels = null;  // Free memory
      return result;
   }

   /**
    * Apply solid color fill to ImageData
    * @param imageData ImageData to process
    * @param alpha Alpha value (-1 to use RGB only)
    * @param red Red component (0-255)
    * @param green Green component (0-255)
    * @param blue Blue component (0-255)
    * @return ImageData with applied color
    */
   public static ImageData applySolidColor(ImageData imageData, int alpha, int red, int green, int blue) {
      for (int i = 0; i < imageData.pixels.length; i++) {
         if (imageData.pixels[i] != 16777215) {  // Skip white pixels
            if (alpha >= 0 && alpha <= 255) {
               // Include alpha channel
               imageData.pixels[i] = (alpha << 24) | (red << 16) | (green << 8) | blue;
            } else {
               // RGB only
               imageData.pixels[i] = (red << 16) | (green << 8) | blue;
            }
         }
      }

      return imageData;
   }

   /**
    * Blend two ImageData objects using specified blend mode
    * @param baseImage Base ImageData
    * @param overlayImage Overlay ImageData
    * @param blendMode Blend mode (0=AND, 1=OR, 2=REPLACE)
    * @return Blended ImageData
    */
   public static ImageData blendImages(ImageData baseImage, ImageData overlayImage, byte blendMode) {
      int overlayX = 0;
      int overlayY = 0;

      // Apply initial processing for AND blend mode
      if (blendMode == 0) {
         adjustBrightnessContrast(baseImage, 5, 5);
      }

      // Blend pixel by pixel
      for (int y = 0; y < baseImage.height; y++) {
         for (int x = 0; x < baseImage.width; x++) {
            int basePixel = baseImage.pixels[y * baseImage.width + x];
            int overlayPixel = overlayImage.pixels[overlayY * overlayImage.width + overlayX];

            // Only blend if base pixel has alpha
            if ((basePixel >> 24) != 0) {
               switch (blendMode) {
                  case 0: // AND blending
                     baseImage.pixels[y * baseImage.width + x] = basePixel & overlayPixel;
                     break;
                  case 1: // OR blending (BLEND_MODE_OR)
                     baseImage.pixels[y * baseImage.width + x] = basePixel | overlayPixel;
                     break;
                  case 2: // Replace blending (BLEND_MODE_REPLACE)
                     baseImage.pixels[y * baseImage.width + x] = overlayPixel;
                     break;
               }
            }

            // Advance overlay X coordinate with wrapping
            if (overlayX < overlayImage.width - 1) {
               overlayX++;
            } else {
               overlayX = 0;
            }
         }

         // Advance overlay Y coordinate with wrapping
         if (overlayY < overlayImage.height - 1) {
            overlayY++;
         } else {
            overlayY = 0;
         }

         overlayX = 0;  // Reset X at end of each row
      }

      return baseImage;
   }

   /**
    * Convert Image to grayscale
    * @param sourceImage Source image to convert
    * @return Grayscale version of the image
    */
   public static Image convertToGrayscale(Image sourceImage) {
      ImageData imageData = new ImageData();
      imageData.setPixelData(GameUtils.extractImageRGB(sourceImage),
              sourceImage.getWidth(), sourceImage.getHeight());

      for (int y = 0; y < imageData.height; y++) {
         for (int x = 0; x < imageData.width; x++) {
            int pixelIndex = y * imageData.width + x;
            int pixel = imageData.pixels[pixelIndex];

            // Extract color components
            int alpha = pixel >> 24;
            int red = (pixel >> 16) & 255;
            int green = (pixel >> 8) & 255;
            int blue = pixel & 255;

            // Calculate grayscale value using luminance formula
            int grayscale = (int)(0.299 * red + 0.587 * green + 0.114 * blue);

            // Apply grayscale conversion
            if (imageData.pixels[pixelIndex] != -1 && imageData.pixels[pixelIndex] != -16777216) {
               // Normal pixels - convert to grayscale
               imageData.pixels[pixelIndex] = (alpha << 24) | (grayscale << 16) | (grayscale << 8) | grayscale;
            } else {
               // White or black pixels - preserve alpha only
               imageData.pixels[pixelIndex] &= 16777215;  // Remove alpha, keep RGB
            }
         }
      }

      // Convert back to Image
      Image result = GameUtils.createRGBImage(imageData.pixels, imageData.width, imageData.height, true);
      imageData.pixels = null;  // Free memory
      return result;
   }

   /**
    * Clamp color value to valid range (0-255)
    * @param value Color value to clamp
    * @return Clamped value
    */
   private static int clampColorValue(int value) {
      if (value > 255) {
         return 255;
      } else if (value < 0) {
         return 0;
      }
      return value;
   }

   /**
    * Create ImageData from Image
    * @param sourceImage Source image
    * @return ImageData representation
    */
   public static ImageData createImageDataFromImage(Image sourceImage) {
      ImageData imageData = new ImageData();
      imageData.setPixelData(GameUtils.extractImageRGB(sourceImage),
              sourceImage.getWidth(), sourceImage.getHeight());
      return imageData;
   }

   /**
    * Create Image from ImageData
    * @param imageData Source ImageData
    * @return Image representation
    */
   public static Image createImageFromImageData(ImageData imageData) {
      return GameUtils.createRGBImage(imageData.pixels, imageData.width, imageData.height, true);
   }

   /**
    * Apply sepia tone effect to ImageData
    * @param imageData ImageData to process
    * @return ImageData with sepia effect
    */
   public static ImageData applySepiaEffect(ImageData imageData) {
      for (int i = 0; i < imageData.totalPixels; i++) {
         int pixel = imageData.pixels[i];

         if (pixel != 16777215 && pixel != 0) {  // Skip white and transparent
            int alpha = pixel >> 24;
            int red = (pixel >> 16) & 255;
            int green = (pixel >> 8) & 255;
            int blue = pixel & 255;

            // Apply sepia transformation
            int sepiaRed = (int)(red * 0.393 + green * 0.769 + blue * 0.189);
            int sepiaGreen = (int)(red * 0.349 + green * 0.686 + blue * 0.168);
            int sepiaBlue = (int)(red * 0.272 + green * 0.534 + blue * 0.131);

            // Clamp values
            sepiaRed = clampColorValue(sepiaRed);
            sepiaGreen = clampColorValue(sepiaGreen);
            sepiaBlue = clampColorValue(sepiaBlue);

            imageData.pixels[i] = (alpha << 24) | (sepiaRed << 16) | (sepiaGreen << 8) | sepiaBlue;
         }
      }

      return imageData;
   }

   /**
    * Apply blur effect to ImageData (simple box blur)
    * @param imageData ImageData to blur
    * @param radius Blur radius
    * @return Blurred ImageData
    */
   public static ImageData applyBlurEffect(ImageData imageData, int radius) {
      if (radius <= 0) return imageData;

      int[] blurredPixels = new int[imageData.totalPixels];

      for (int y = 0; y < imageData.height; y++) {
         for (int x = 0; x < imageData.width; x++) {
            int redSum = 0, greenSum = 0, blueSum = 0, alphaSum = 0;
            int pixelCount = 0;

            // Sample pixels in blur radius
            for (int dy = -radius; dy <= radius; dy++) {
               for (int dx = -radius; dx <= radius; dx++) {
                  int sampleX = x + dx;
                  int sampleY = y + dy;

                  // Check bounds
                  if (sampleX >= 0 && sampleX < imageData.width &&
                          sampleY >= 0 && sampleY < imageData.height) {

                     int samplePixel = imageData.pixels[sampleY * imageData.width + sampleX];
                     alphaSum += (samplePixel >> 24) & 255;
                     redSum += (samplePixel >> 16) & 255;
                     greenSum += (samplePixel >> 8) & 255;
                     blueSum += samplePixel & 255;
                     pixelCount++;
                  }
               }
            }

            // Average the color components
            if (pixelCount > 0) {
               int avgAlpha = alphaSum / pixelCount;
               int avgRed = redSum / pixelCount;
               int avgGreen = greenSum / pixelCount;
               int avgBlue = blueSum / pixelCount;

               blurredPixels[y * imageData.width + x] =
                       (avgAlpha << 24) | (avgRed << 16) | (avgGreen << 8) | avgBlue;
            } else {
               blurredPixels[y * imageData.width + x] = imageData.pixels[y * imageData.width + x];
            }
         }
      }

      imageData.pixels = blurredPixels;
      return imageData;
   }

   /**
    * Get blend mode constants for external use
    * @return Array of blend mode values [AND, OR, REPLACE]
    */
   public static byte[] getBlendModes() {
      return new byte[]{0, BLEND_MODE_OR, BLEND_MODE_REPLACE};
   }
}