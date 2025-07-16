package a.a;

import a.b.GameEntity;
import a.b.AnimatedSprite;
import a.b.ResourceManager;
import javax.microedition.lcdui.Graphics;

public final class C21 extends GameEntity {
   private ImageData[] C21_f264;
   private short[] C21_f265;
   public byte C21_f266;
   private int C21_f267 = 0;
   private int C21_f268 = 0;
   private int[] C21_f269 = new int[]{262, 263, 264, 265, 266, 267, 268, 299, 300, 301, 304, 306, 307, 308, 309};
   public AnimatedSprite C21_f270 = new AnimatedSprite();

   public final void a(short[] var1) {
      this.C21_f266 = (byte)var1[0];
      AnimatedSprite var2;
      int[] var3;
      int var4;
      switch(this.C21_f266) {
      case 0:
         this.C21_f265 = new short[3];
         System.arraycopy(var1, 0, this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[3], var1[4]);
         this.C21_f264 = new ImageData[3];
         var2 = new AnimatedSprite();

         for(int var6 = 0; var6 < 2; ++var6) {
            var2.loadSpriteSet(var1[5 + var6 * 3], false);
            int[] var7 = var2.getSpritePartBounds(var1[6 + var6 * 3], (byte)var1[7 + var6 * 3]);
            this.C21_f264[var6] = new ImageData();
            this.C21_f264[var6] = ImageProcessor.renderSpriteToImageData(var2, var1[6 + var6 * 3], var7, (byte)var1[7 + var6 * 3], this.C21_f264[var6]);
            var2.releaseResources();
         }

         this.C21_f264[2] = this.C21_f264[0].createCopy();
         return;
      case 1:
         this.C21_f265 = new short[var1.length - 6];
         this.currentDirection = (byte)var1[5];
         System.arraycopy(var1, 6, this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[3];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);
         var2.releaseResources();
         this.C21_f264[1] = new ImageData();
         this.C21_f264[1].setPixelData(ResourceManager.textureData[this.C21_f265[2]], 16, 16);
         this.C21_f264[2] = this.C21_f264[0].createCopy();
         return;
      case 2:
         return;
      case 3:
         return;
      case 4:
         return;
      case 5:
         return;
      case 6:
         return;
      case 7:
         this.C21_f265 = new short[var1.length - 6];
         this.currentDirection = (byte)var1[5];
         System.arraycopy(var1, 6, this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[2];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);
         this.C21_f264[1] = this.C21_f264[0].createCopy();
         var4 = var3[2] * var1[9] / var1[10];
         int var5 = var3[3] * var1[11] / var1[12];
         this.C21_f267 = (var3[2] - var4) / 2;
         this.C21_f268 = var3[3] - var5;
         this.C21_f264[1] = ImageProcessor.scaleImageData(this.C21_f264[1], var4, var5);
         var2.releaseResources();
         return;
      case 8:
         this.C21_f265 = new short[var1.length - 6];
         this.currentDirection = (byte)var1[5];
         System.arraycopy(var1, 6, this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[2];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);
         var2.releaseResources();
         this.C21_f264[1] = this.C21_f264[0].createCopy();
         if (this.C21_f265[4] == 1) {
            this.C21_f264[1] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData)this.C21_f264[1], this.C21_f265[2]), 1, 50);
            ImageData var10000 = this.C21_f264[1];
            var10000.offsetX += this.C21_f265[3];
            var10000 = this.C21_f264[1];
            var10000.offsetY += this.C21_f265[4];
         }

         return;
      case 9:
         this.C21_f265 = new short[var1.length - 10];
         this.currentDirection = (byte)var1[5];
         System.arraycopy(var1, 10, this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[2];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);
         this.C21_f264[1] = this.C21_f264[0].createCopy();
         this.C21_f264[1] = ImageProcessor.applySolidColor(this.C21_f264[1], var1[6], var1[7], var1[8], var1[9]);
         this.C21_f264[1] = ImageProcessor.adjustBrightnessContrast(this.C21_f264[1], 1, 50);
         var2.releaseResources();
         return;
      case 10:
         this.C21_f265 = new short[var1.length - 7];
         this.currentDirection = (byte)var1[5];
         System.arraycopy(var1, 7, this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[2];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);
         this.C21_f264[1] = this.C21_f264[0].createCopy();
         this.C21_f264[1] = ImageProcessor.applyAlpha(this.C21_f264[1], var1[6]);
         var2.releaseResources();
         return;
      case 11:
      case 14:
         this.C21_f265 = new short[var1.length - 7 - (var1[6] - 1 << 2)];
         this.currentDirection = (byte)var1[5];
         System.arraycopy(var1, 7 + (var1[6] - 1 << 2), this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[var1[6]];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);
         if (var1[0] == 11) {
            for(var4 = 1; var4 < this.C21_f264.length; ++var4) {
               this.C21_f264[var4] = this.C21_f264[0].createCopy();
               this.C21_f264[var4] = ImageProcessor.applySolidColor(this.C21_f264[var4], var1[7 + (var4 - 1 << 2)], var1[8 + (var4 - 1 << 2)], var1[9 + (var4 - 1 << 2)], var1[10 + (var4 - 1 << 2)]);
            }
         } else {
            for(var4 = 1; var4 < this.C21_f264.length; ++var4) {
               this.C21_f264[var4] = this.C21_f264[0].createCopy();
               this.C21_f264[var4] = ImageProcessor.adjustBrightnessContrast(this.C21_f264[var4], var1[7 + (var4 - 1 << 2)], var1[8 + (var4 - 1 << 2)]);
            }
         }

         var2.releaseResources();
         return;
      case 12:
         this.currentDirection = (byte)var1[5];
         this.C21_f265 = new short[var1.length - 9];
         System.arraycopy(var1, 9, this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[var1[6]];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);

         for(var4 = 1; var4 < this.C21_f264.length; ++var4) {
            this.C21_f264[var4] = this.C21_f264[0].createCopy();
         }

         for(var4 = 0; var4 < this.C21_f264.length; ++var4) {
            this.C21_f264[var4] = ImageProcessor.applyAlpha(this.C21_f264[var4], var1[var4 + 7]);
         }

         var2.releaseResources();
         return;
      case 13:
         this.currentDirection = (byte)var1[5];
         this.C21_f265 = new short[var1.length - 7 - var1[6]];
         System.arraycopy(var1, 7 + var1[6], this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[var1[6]];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);

         for(var4 = 1; var4 < this.C21_f264.length; ++var4) {
            this.C21_f264[var4] = this.C21_f264[0].createCopy();
         }

         for(var4 = 0; var4 < this.C21_f264.length; ++var4) {
            this.C21_f264[var4] = ImageProcessor.applyAlpha(this.C21_f264[var4], var1[var4 + 7]);
         }

         return;
      case 15:
         this.currentDirection = (byte)var1[5];
         this.C21_f265 = new short[var1.length - 7 - (var1[6] - 1 << 2)];
         System.arraycopy(var1, 7 + (var1[6] - 1 << 2), this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[var1[6]];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);

         for(var4 = 1; var4 < this.C21_f264.length; ++var4) {
            this.C21_f264[var4] = this.C21_f264[0].createCopy();
            this.C21_f264[var4] = ImageProcessor.applySolidColor(this.C21_f264[var4], var1[7 + (var4 - 1 << 2)], var1[8 + (var4 - 1 << 2)], var1[9 + (var4 - 1 << 2)], var1[10 + (var4 - 1 << 2)]);
         }

         var2.releaseResources();
         return;
      case 16:
         this.C21_f265 = new short[var1.length - 6];
         this.currentDirection = (byte)var1[5];
         System.arraycopy(var1, 6, this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[1];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);
         this.C21_f265[1] = (short)(this.C21_f264[0].height / this.C21_f265[2]);
         var2.releaseResources();
         return;
      case 17:
         this.C21_f265 = new short[var1.length - 11];
         this.currentDirection = (byte)var1[5];
         System.arraycopy(var1, 11, this.C21_f265, 0, this.C21_f265.length);
         this.setWorldPosition(var1[1], var1[2]);
         this.C21_f264 = new ImageData[2];
         (var2 = new AnimatedSprite()).loadSpriteSet(var1[3], false);
         var3 = var2.getSpritePartBounds(var1[4], (byte)var1[5]);
         this.C21_f264[0] = new ImageData();
         this.C21_f264[0] = ImageProcessor.renderSpriteToImageData(var2, var1[4], var3, (byte)var1[5], this.C21_f264[0]);
         this.C21_f264[0] = ImageProcessor.scaleImageByPercent((ImageData)this.C21_f264[0], var1[10]);
         this.C21_f264[1] = this.C21_f264[0].createCopy();
         this.C21_f264[1] = ImageProcessor.applySolidColor(this.C21_f264[1], var1[6], var1[7], var1[8], var1[9]);
         var2.releaseResources();
         return;
      default:
         this.currentDirection = (byte)var1[2];
         this.C21_f270.loadSpriteSet(this.C21_f269[this.C21_f266 - 20], false);
         this.C21_f270.setAnimation((byte)((byte)var1[1]), (byte)0, true);
      }
   }

   private void e() {
      if (this.C21_f264 != null) {
         for(int var1 = 0; var1 < this.C21_f264.length; ++var1) {
            this.C21_f264[var1].pixels = null;
            this.C21_f264[var1] = null;
         }

         this.C21_f264 = null;
      }

      if (this.C21_f265 != null) {
         this.C21_f265 = null;
      }

   }

   public final void a() {
      this.setActive(true);
      this.setVisible(true);
   }

   public final void b() {
      this.setActive(false);
      this.setVisible(false);
   }

   public final boolean a(byte var1) {
      return this.C21_f266 == 8 && this.isActive;
   }

   public final boolean c() {
      return this.C21_f270.isAtLastFrame();
   }

   public final boolean a(int var1) {
      return this.C21_f270.isAtFrame(var1);
   }

   public final boolean d() {
      if (!this.isActive) {
         return false;
      } else {
         switch(this.C21_f266) {
         case 0:
            if (this.C21_f265[1] < this.C21_f265[2] / 5) {
               this.C21_f264[2] = this.C21_f264[0].createCopy();
               if (this.C21_f265[1] % 2 == 1) {
                  this.C21_f264[2] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData)this.C21_f264[2], 6), 5, 1);
               } else {
                  this.C21_f264[2] = ImageProcessor.adjustBrightnessContrast(this.C21_f264[2], 2, 1);
               }
            } else if (this.C21_f265[1] >= (this.C21_f265[2] << 2) / 5) {
               this.C21_f264[2] = this.C21_f264[1].createCopy();
               if (this.C21_f265[1] % 2 == 1) {
                  this.C21_f264[2] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData)this.C21_f264[2], 6), 5, 1);
               } else {
                  this.C21_f264[2] = ImageProcessor.adjustBrightnessContrast(this.C21_f264[2], 2, 1);
               }
            } else {
               if (this.C21_f265[1] % 4 != 1 && this.C21_f265[1] % 4 != 2) {
                  this.C21_f264[2] = this.C21_f264[1].createCopy();
               } else {
                  this.C21_f264[2] = this.C21_f264[0].createCopy();
               }

               if (this.C21_f265[1] % 2 == 1) {
                  this.C21_f264[2] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData)this.C21_f264[2], 8), 8, 1);
               } else {
                  this.C21_f264[2] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData)this.C21_f264[2], 4), 4, 1);
               }
            }

            if (this.C21_f265[1] >= this.C21_f265[2]) {
               this.b();
               this.e();
               return false;
            }

            ++this.C21_f265[1];
            break;
         case 1:
            int[] var1;
            int var2;
            int var3;
            label200:
            switch(this.C21_f265[4]) {
            case 0:
               var1 = new int[4];

               for(var2 = 0; var2 < this.C21_f264[1].width; ++var2) {
                  for(var3 = 0; var3 < 4; ++var3) {
                     var1[var3] = this.C21_f264[1].pixels[var2 + var3 * this.C21_f264[1].width];
                  }

                  for(var3 = 0; var3 < this.C21_f264[1].height - 4; ++var3) {
                     this.C21_f264[1].pixels[var2 + var3 * this.C21_f264[1].width] = this.C21_f264[1].pixels[var2 + (var3 + 4) * this.C21_f264[1].width];
                  }

                  for(var3 = 0; var3 < 4; ++var3) {
                     this.C21_f264[1].pixels[var2 + (var3 + this.C21_f264[1].height - 4) * this.C21_f264[1].width] = var1[var3];
                  }
               }
               break;
            case 1:
               var1 = new int[4];
               var2 = 0;

               while(true) {
                  if (var2 >= this.C21_f264[1].width) {
                     break label200;
                  }

                  for(var3 = 0; var3 < 4; ++var3) {
                     var1[var3] = this.C21_f264[1].pixels[var2 + (this.C21_f264[1].height - 4 + var3) * this.C21_f264[1].width];
                  }

                  for(var3 = this.C21_f264[1].height - 1; var3 > 3; --var3) {
                     this.C21_f264[1].pixels[var2 + var3 * this.C21_f264[1].width] = this.C21_f264[1].pixels[var2 + (var3 - 4) * this.C21_f264[1].width];
                  }

                  for(var3 = 0; var3 < 4; ++var3) {
                     this.C21_f264[1].pixels[var2 + var3 * this.C21_f264[1].width] = var1[var3];
                  }

                  ++var2;
               }
            case 2:
               var1 = new int[4];
               var2 = 0;

               while(true) {
                  if (var2 >= this.C21_f264[1].height) {
                     break label200;
                  }

                  for(var3 = 0; var3 < 4; ++var3) {
                     var1[var3] = this.C21_f264[1].pixels[var2 * this.C21_f264[1].height + var3];
                  }

                  for(var3 = 0; var3 < this.C21_f264[1].width - 4; ++var3) {
                     this.C21_f264[1].pixels[var2 * this.C21_f264[1].height + var3] = this.C21_f264[1].pixels[var2 * this.C21_f264[1].height + var3 + 4];
                  }

                  for(var3 = 0; var3 < 4; ++var3) {
                     this.C21_f264[1].pixels[var2 * this.C21_f264[1].height + var3 + this.C21_f264[1].width - 4] = var1[var3];
                  }

                  ++var2;
               }
            case 3:
               var1 = new int[4];

               for(var2 = 0; var2 < this.C21_f264[1].height; ++var2) {
                  for(var3 = 0; var3 < 4; ++var3) {
                     var1[var3] = this.C21_f264[1].pixels[var2 * this.C21_f264[1].height + this.C21_f264[1].width - 4 + var3];
                  }

                  for(var3 = this.C21_f264[1].width - 1; var3 > 3; --var3) {
                     this.C21_f264[1].pixels[var2 * this.C21_f264[1].height + var3] = this.C21_f264[1].pixels[var2 * this.C21_f264[1].height + var3 - 4];
                  }

                  for(var3 = 0; var3 < 4; ++var3) {
                     this.C21_f264[1].pixels[var2 * this.C21_f264[1].height + var3] = var1[var3];
                  }
               }
            }

            this.C21_f264[2] = this.C21_f264[0].createCopy();
            this.C21_f264[2] = ImageProcessor.blendImages(this.C21_f264[2], this.C21_f264[1], (byte)this.C21_f265[3]);
            if (this.C21_f265[0] >= this.C21_f265[1]) {
               this.b();
               this.e();
               return false;
            }

            ++this.C21_f265[0];
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
            break;
         case 7:
         case 9:
         case 10:
         case 16:
         case 17:
            if (this.C21_f265[0] >= this.C21_f265[1]) {
               this.b();
               this.e();
               return false;
            }

            ++this.C21_f265[0];
            break;
         case 8:
            if (this.C21_f265[0] < this.C21_f265[1] / this.C21_f265[3] * this.C21_f265[2]) {
               if (this.C21_f265[4] == 1) {
                  this.C21_f264[1] = this.C21_f264[0].createCopy();
               }

               this.C21_f264[1] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData)this.C21_f264[1], this.C21_f265[5 + (this.C21_f265[2] - 1) * 3]), 1, 50);
               ImageData var4 = this.C21_f264[1];
               var4.offsetX += this.C21_f265[6 + (this.C21_f265[2] - 1) * 3];
               var4 = this.C21_f264[1];
               var4.offsetY += this.C21_f265[7 + (this.C21_f265[2] - 1) * 3];
            } else {
               ++this.C21_f265[2];
            }

            if (this.C21_f265[0] >= this.C21_f265[1]) {
               this.b();
               this.e();
               return false;
            }

            ++this.C21_f265[0];
            break;
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
            if (this.C21_f265[2] < this.C21_f265[3]) {
               ++this.C21_f265[2];
            } else {
               this.C21_f265[2] = 0;
               ++this.C21_f265[0];
               if (this.C21_f265[0] >= this.C21_f265[1]) {
                  this.C21_f265[0] = 0;
                  this.b();
                  this.e();
                  return false;
               }
            }
            break;
         default:
            this.C21_f270.updateAnimation();
         }

         return true;
      }
   }

   public final void a(Graphics var1, int var2, int var3) {
      if (this.isVisible && this.isInteractable) {
         switch(this.C21_f266) {
         case 0:
            var1.drawRGB(this.C21_f264[2].pixels, 0, this.C21_f264[2].width, this.worldX + this.C21_f264[2].offsetX, this.worldY + this.C21_f264[2].offsetY, this.C21_f264[2].width, this.C21_f264[2].height, true);
            return;
         case 1:
            var1.drawRGB(this.C21_f264[2].pixels, 0, this.C21_f264[2].width, this.worldX + this.C21_f264[2].offsetX, this.worldY + this.C21_f264[2].offsetY, this.C21_f264[2].width, this.C21_f264[2].height, true);
            return;
         case 2:
            return;
         case 3:
            return;
         case 4:
            return;
         case 5:
            return;
         case 6:
            return;
         case 7:
            if (this.C21_f265[0] / this.C21_f265[2] % 2 == 0) {
               var1.drawRGB(this.C21_f264[1].pixels, 0, this.C21_f264[1].width, this.worldX + this.C21_f264[0].offsetX + this.C21_f267, this.worldY + this.C21_f264[0].offsetY + this.C21_f268, this.C21_f264[1].width, this.C21_f264[1].height, true);
               return;
            }

            var1.drawRGB(this.C21_f264[0].pixels, 0, this.C21_f264[0].width, this.worldX + this.C21_f264[0].offsetX, this.worldY + this.C21_f264[0].offsetY, this.C21_f264[0].width, this.C21_f264[0].height, true);
            return;
         case 8:
            var1.drawRGB(this.C21_f264[1].pixels, 0, this.C21_f264[1].width, this.worldX + this.C21_f264[1].offsetX, this.worldY + this.C21_f264[1].offsetY, this.C21_f264[1].width, this.C21_f264[1].height, true);
            return;
         case 9:
         case 10:
            var1.drawRGB(this.C21_f264[0].pixels, 0, this.C21_f264[0].width, this.worldX + this.C21_f264[0].offsetX, this.worldY + this.C21_f264[0].offsetY, this.C21_f264[0].width, this.C21_f264[0].height, true);
            if (this.C21_f265[0] / this.C21_f265[2] % 2 == 0) {
               var1.drawRGB(this.C21_f264[1].pixels, 0, this.C21_f264[1].width, this.worldX + this.C21_f264[1].offsetX, this.worldY + this.C21_f264[1].offsetY, this.C21_f264[1].width, this.C21_f264[1].height, true);
               return;
            }
            break;
         case 11:
         case 14:
            var1.setColor(16711935);

            for(var2 = 1; var2 < this.C21_f264.length; ++var2) {
               if (this.currentDirection == 1) {
                  var1.drawRGB(this.C21_f264[var2].pixels, 0, this.C21_f264[var2].width, this.worldX + this.C21_f264[var2].offsetX - this.C21_f265[4 + (this.C21_f265[0] * (this.C21_f264.length - 1) << 1) + (var2 - 1 << 1)], this.worldY + this.C21_f264[var2].offsetY + this.C21_f265[4 + (this.C21_f265[0] * (this.C21_f264.length - 1) << 1) + (var2 - 1 << 1) + 1], this.C21_f264[var2].width, this.C21_f264[var2].height, true);
               } else {
                  var1.drawRGB(this.C21_f264[var2].pixels, 0, this.C21_f264[var2].width, this.worldX + this.C21_f264[var2].offsetX + this.C21_f265[4 + (this.C21_f265[0] * (this.C21_f264.length - 1) << 1) + (var2 - 1 << 1)], this.worldY + this.C21_f264[var2].offsetY + this.C21_f265[4 + (this.C21_f265[0] * (this.C21_f264.length - 1) << 1) + (var2 - 1 << 1) + 1], this.C21_f264[var2].width, this.C21_f264[var2].height, true);
               }
            }

            return;
         case 12:
            if (this.currentDirection == 1) {
               var1.drawRGB(this.C21_f264[1].pixels, 0, this.C21_f264[1].width, this.worldX + this.C21_f264[1].offsetX - (this.C21_f265[4 + (this.C21_f265[1] << 1) + (this.C21_f265[0] << 1)] + this.C21_f265[4 + (this.C21_f265[0] << 1)]), this.worldY + this.C21_f264[1].offsetY - this.C21_f265[4 + (this.C21_f265[1] << 1) + (this.C21_f265[0] << 1) + 1] + this.C21_f265[4 + (this.C21_f265[0] << 1) + 1], this.C21_f264[1].width, this.C21_f264[1].height, true);
               var1.drawRGB(this.C21_f264[0].pixels, 0, this.C21_f264[0].width, this.worldX + this.C21_f264[0].offsetX - this.C21_f265[4 + (this.C21_f265[0] << 1)], this.worldY + this.C21_f264[0].offsetY + this.C21_f265[4 + (this.C21_f265[0] << 1) + 1], this.C21_f264[0].width, this.C21_f264[0].height, true);
               return;
            }

            var1.drawRGB(this.C21_f264[1].pixels, 0, this.C21_f264[1].width, this.worldX + this.C21_f264[1].offsetX + this.C21_f265[4 + (this.C21_f265[1] << 1) + (this.C21_f265[0] << 1)] + this.C21_f265[4 + (this.C21_f265[0] << 1)], this.worldY + this.C21_f264[1].offsetY - this.C21_f265[4 + (this.C21_f265[1] << 1) + (this.C21_f265[0] << 1) + 1] + this.C21_f265[4 + (this.C21_f265[0] << 1) + 1], this.C21_f264[1].width, this.C21_f264[1].height, true);
            var1.drawRGB(this.C21_f264[0].pixels, 0, this.C21_f264[0].width, this.worldX + this.C21_f264[0].offsetX + this.C21_f265[4 + (this.C21_f265[0] << 1)], this.worldY + this.C21_f264[0].offsetY + this.C21_f265[4 + (this.C21_f265[0] << 1) + 1], this.C21_f264[0].width, this.C21_f264[0].height, true);
            return;
         case 13:
            for(var2 = 0; var2 < this.C21_f264.length; ++var2) {
               if (this.currentDirection == 1) {
                  var1.drawRGB(this.C21_f264[var2].pixels, 0, this.C21_f264[var2].width, this.worldX + this.C21_f264[var2].offsetX - this.C21_f265[4 + (this.C21_f265[0] * this.C21_f264.length << 1) + (var2 << 1)], this.worldY + this.C21_f264[var2].offsetY + this.C21_f265[4 + (this.C21_f265[0] * this.C21_f264.length << 1) + (var2 << 1) + 1], this.C21_f264[var2].width, this.C21_f264[var2].height, true);
               } else {
                  var1.drawRGB(this.C21_f264[var2].pixels, 0, this.C21_f264[var2].width, this.worldX + this.C21_f264[var2].offsetX + this.C21_f265[4 + (this.C21_f265[0] * this.C21_f264.length << 1) + (var2 << 1)], this.worldY + this.C21_f264[var2].offsetY + this.C21_f265[4 + (this.C21_f265[0] * this.C21_f264.length << 1) + (var2 << 1) + 1], this.C21_f264[var2].width, this.C21_f264[var2].height, true);
               }
            }

            return;
         case 15:
            var2 = 4 + this.C21_f265[0] * 3;
            if (this.currentDirection == 1) {
               var1.drawRGB(this.C21_f264[this.C21_f265[var2]].pixels, 0, this.C21_f264[this.C21_f265[var2]].width, this.worldX + this.C21_f264[this.C21_f265[var2]].offsetX - this.C21_f265[var2 + 1], this.worldY + this.C21_f264[this.C21_f265[var2]].offsetY + this.C21_f265[var2 + 2], this.C21_f264[this.C21_f265[var2]].width, this.C21_f264[this.C21_f265[var2]].height, true);
               return;
            }

            var1.drawRGB(this.C21_f264[this.C21_f265[var2]].pixels, 0, this.C21_f264[this.C21_f265[var2]].width, this.worldX + this.C21_f264[this.C21_f265[var2]].offsetX + this.C21_f265[var2 + 1], this.worldY + this.C21_f264[this.C21_f265[var2]].offsetY + this.C21_f265[var2 + 2], this.C21_f264[this.C21_f265[var2]].width, this.C21_f264[this.C21_f265[var2]].height, true);
            return;
         case 16:
            for(var2 = 0; var2 < this.C21_f265[2]; ++var2) {
               for(var3 = 0; var3 < this.C21_f264[0].width * this.C21_f265[0]; ++var3) {
                  if (this.C21_f264[0].pixels[var2 * this.C21_f265[1] * this.C21_f264[0].width + var3] != 16777215 && this.C21_f264[0].pixels[var2 * this.C21_f265[1] * this.C21_f264[0].width + var3] != 0) {
                     this.C21_f264[0].pixels[var2 * this.C21_f265[1] * this.C21_f264[0].width + var3] &= 16777215;
                  }
               }
            }

            var1.drawRGB(this.C21_f264[0].pixels, 0, this.C21_f264[0].width, this.worldX + this.C21_f264[0].offsetX, this.worldY + this.C21_f264[0].offsetY, this.C21_f264[0].width, this.C21_f264[0].height, true);
            return;
         case 17:
            var1.drawRGB(this.C21_f264[0].pixels, 0, this.C21_f264[0].width, this.worldX + this.C21_f264[0].offsetX, this.worldY + this.C21_f264[0].offsetY + this.C21_f265[3], this.C21_f264[0].width, this.C21_f264[0].height, true);
            if (this.C21_f265[0] / this.C21_f265[2] % 2 == 0) {
               var1.drawRGB(this.C21_f264[1].pixels, 0, this.C21_f264[1].width, this.worldX + this.C21_f264[1].offsetX, this.worldY + this.C21_f264[1].offsetY + this.C21_f265[3], this.C21_f264[1].width, this.C21_f264[1].height, true);
               return;
            }
            break;
         default:
            this.C21_f270.renderCurrentFrame(var1, this.worldX, this.worldY, this.currentDirection);
         }

      }
   }
}
