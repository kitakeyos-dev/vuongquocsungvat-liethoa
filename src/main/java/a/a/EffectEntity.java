package a.a;

import a.b.Sprite;
import a.b.GameEntity;
import a.b.ResourceManager;

import javax.microedition.lcdui.Graphics;

public final class EffectEntity extends GameEntity {
    private ImageData[] effectImages;
    private short[] effectParameters;
    public byte effectType;
    private int offsetX = 0;
    private int offsetY = 0;
    private int[] spriteSetIds = new int[]{262, 263, 264, 265, 266, 267, 268, 299, 300, 301, 304, 306, 307, 308, 309};
    public Sprite sprite = new Sprite();

    public final void initializeEffect(short[] parameters) {
        this.effectType = (byte) parameters[0];
        Sprite tempSprite;
        int[] spriteBounds;
        int index;

        switch (this.effectType) {
            case 0: // Explosion effect
                this.effectParameters = new short[3];
                System.arraycopy(parameters, 0, this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[3], parameters[4]);
                this.effectImages = new ImageData[3];
                tempSprite = new Sprite();

                for (int i = 0; i < 2; ++i) {
                    tempSprite.loadSpriteSet(parameters[5 + i * 3], false);
                    int[] bounds = tempSprite.getSpritePartBounds(parameters[6 + i * 3], (byte) parameters[7 + i * 3]);
                    this.effectImages[i] = new ImageData();
                    this.effectImages[i] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[6 + i * 3], bounds, (byte) parameters[7 + i * 3], this.effectImages[i]);
                    tempSprite.releaseResources();
                }

                this.effectImages[2] = this.effectImages[0].createCopy();
                return;

            case 1: // Scrolling texture effect
                this.effectParameters = new short[parameters.length - 6];
                this.currentDirection = (byte) parameters[5];
                System.arraycopy(parameters, 6, this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[3];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);
                tempSprite.releaseResources();
                this.effectImages[1] = new ImageData();
                this.effectImages[1].setPixelData(ResourceManager.textureData[this.effectParameters[2]], 16, 16);
                this.effectImages[2] = this.effectImages[0].createCopy();
                return;

            case 2: // Empty effect type
            case 3:
            case 4:
            case 5:
            case 6:
                return;

            case 7: // Scaling effect
                this.effectParameters = new short[parameters.length - 6];
                this.currentDirection = (byte) parameters[5];
                System.arraycopy(parameters, 6, this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[2];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);
                this.effectImages[1] = this.effectImages[0].createCopy();
                int scaledWidth = spriteBounds[2] * parameters[9] / parameters[10];
                int scaledHeight = spriteBounds[3] * parameters[11] / parameters[12];
                this.offsetX = (spriteBounds[2] - scaledWidth) / 2;
                this.offsetY = spriteBounds[3] - scaledHeight;
                this.effectImages[1] = ImageProcessor.scaleImageData(this.effectImages[1], scaledWidth, scaledHeight);
                tempSprite.releaseResources();
                return;

            case 8: // Shake effect
                this.effectParameters = new short[parameters.length - 6];
                this.currentDirection = (byte) parameters[5];
                System.arraycopy(parameters, 6, this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[2];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);
                tempSprite.releaseResources();
                this.effectImages[1] = this.effectImages[0].createCopy();
                if (this.effectParameters[4] == 1) {
                    this.effectImages[1] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData) this.effectImages[1], this.effectParameters[2]), 1, 50);
                    ImageData adjustedImage = this.effectImages[1];
                    adjustedImage.offsetX += this.effectParameters[3];
                    adjustedImage = this.effectImages[1];
                    adjustedImage.offsetY += this.effectParameters[4];
                }
                return;

            case 9: // Color overlay effect
                this.effectParameters = new short[parameters.length - 10];
                this.currentDirection = (byte) parameters[5];
                System.arraycopy(parameters, 10, this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[2];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);
                this.effectImages[1] = this.effectImages[0].createCopy();
                this.effectImages[1] = ImageProcessor.applySolidColor(this.effectImages[1], parameters[6], parameters[7], parameters[8], parameters[9]);
                this.effectImages[1] = ImageProcessor.adjustBrightnessContrast(this.effectImages[1], 1, 50);
                tempSprite.releaseResources();
                return;

            case 10: // Alpha fade effect
                this.effectParameters = new short[parameters.length - 7];
                this.currentDirection = (byte) parameters[5];
                System.arraycopy(parameters, 7, this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[2];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);
                this.effectImages[1] = this.effectImages[0].createCopy();
                this.effectImages[1] = ImageProcessor.applyAlpha(this.effectImages[1], parameters[6]);
                tempSprite.releaseResources();
                return;

            case 11: // Multi-color effect
            case 14: // Brightness variation effect
                this.effectParameters = new short[parameters.length - 7 - (parameters[6] - 1 << 2)];
                this.currentDirection = (byte) parameters[5];
                System.arraycopy(parameters, 7 + (parameters[6] - 1 << 2), this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[parameters[6]];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);

                if (parameters[0] == 11) {
                    for (index = 1; index < this.effectImages.length; ++index) {
                        this.effectImages[index] = this.effectImages[0].createCopy();
                        this.effectImages[index] = ImageProcessor.applySolidColor(this.effectImages[index], parameters[7 + (index - 1 << 2)], parameters[8 + (index - 1 << 2)], parameters[9 + (index - 1 << 2)], parameters[10 + (index - 1 << 2)]);
                    }
                } else {
                    for (index = 1; index < this.effectImages.length; ++index) {
                        this.effectImages[index] = this.effectImages[0].createCopy();
                        this.effectImages[index] = ImageProcessor.adjustBrightnessContrast(this.effectImages[index], parameters[7 + (index - 1 << 2)], parameters[8 + (index - 1 << 2)]);
                    }
                }
                tempSprite.releaseResources();
                return;

            case 12: // Multi-alpha effect
                this.currentDirection = (byte) parameters[5];
                this.effectParameters = new short[parameters.length - 9];
                System.arraycopy(parameters, 9, this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[parameters[6]];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);

                for (index = 1; index < this.effectImages.length; ++index) {
                    this.effectImages[index] = this.effectImages[0].createCopy();
                }

                for (index = 0; index < this.effectImages.length; ++index) {
                    this.effectImages[index] = ImageProcessor.applyAlpha(this.effectImages[index], parameters[index + 7]);
                }
                tempSprite.releaseResources();
                return;

            case 13: // Sequential alpha effect
                this.currentDirection = (byte) parameters[5];
                this.effectParameters = new short[parameters.length - 7 - parameters[6]];
                System.arraycopy(parameters, 7 + parameters[6], this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[parameters[6]];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);

                for (index = 1; index < this.effectImages.length; ++index) {
                    this.effectImages[index] = this.effectImages[0].createCopy();
                }

                for (index = 0; index < this.effectImages.length; ++index) {
                    this.effectImages[index] = ImageProcessor.applyAlpha(this.effectImages[index], parameters[index + 7]);
                }
                return;

            case 15: // Sequence color effect
                this.currentDirection = (byte) parameters[5];
                this.effectParameters = new short[parameters.length - 7 - (parameters[6] - 1 << 2)];
                System.arraycopy(parameters, 7 + (parameters[6] - 1 << 2), this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[parameters[6]];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);

                for (index = 1; index < this.effectImages.length; ++index) {
                    this.effectImages[index] = this.effectImages[0].createCopy();
                    this.effectImages[index] = ImageProcessor.applySolidColor(this.effectImages[index], parameters[7 + (index - 1 << 2)], parameters[8 + (index - 1 << 2)], parameters[9 + (index - 1 << 2)], parameters[10 + (index - 1 << 2)]);
                }
                tempSprite.releaseResources();
                return;

            case 16: // Particle trail effect
                this.effectParameters = new short[parameters.length - 6];
                this.currentDirection = (byte) parameters[5];
                System.arraycopy(parameters, 6, this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[1];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);
                this.effectParameters[1] = (short) (this.effectImages[0].height / this.effectParameters[2]);
                tempSprite.releaseResources();
                return;

            case 17: // Scaled overlay effect
                this.effectParameters = new short[parameters.length - 11];
                this.currentDirection = (byte) parameters[5];
                System.arraycopy(parameters, 11, this.effectParameters, 0, this.effectParameters.length);
                this.setWorldPosition(parameters[1], parameters[2]);
                this.effectImages = new ImageData[2];
                (tempSprite = new Sprite()).loadSpriteSet(parameters[3], false);
                spriteBounds = tempSprite.getSpritePartBounds(parameters[4], (byte) parameters[5]);
                this.effectImages[0] = new ImageData();
                this.effectImages[0] = ImageProcessor.renderSpriteToImageData(tempSprite, parameters[4], spriteBounds, (byte) parameters[5], this.effectImages[0]);
                this.effectImages[0] = ImageProcessor.scaleImageByPercent((ImageData) this.effectImages[0], parameters[10]);
                this.effectImages[1] = this.effectImages[0].createCopy();
                this.effectImages[1] = ImageProcessor.applySolidColor(this.effectImages[1], parameters[6], parameters[7], parameters[8], parameters[9]);
                tempSprite.releaseResources();
                return;

            default: // Animated sprite effect
                this.currentDirection = (byte) parameters[2];
                this.sprite.loadSpriteSet(this.spriteSetIds[this.effectType - 20], false);
                this.sprite.setAnimation((byte) ((byte) parameters[1]), (byte) 0, true);
        }
    }

    private void cleanupResources() {
        if (this.effectImages != null) {
            for (int i = 0; i < this.effectImages.length; ++i) {
                this.effectImages[i].pixels = null;
                this.effectImages[i] = null;
            }
            this.effectImages = null;
        }

        if (this.effectParameters != null) {
            this.effectParameters = null;
        }
    }

    public final void activateEffect() {
        this.setActive(true);
        this.setVisible(true);
    }

    public final void deactivateEffect() {
        this.setActive(false);
        this.setVisible(false);
    }

    public final boolean isShakeEffect(byte type) {
        return this.effectType == type && this.isActive;
    }

    public final boolean isAnimationComplete() {
        return this.sprite.isAtLastFrame();
    }

    public final boolean isAtFrame(int frameIndex) {
        return this.sprite.isAtFrame(frameIndex);
    }

    public final boolean updateEffect() {
        if (!this.isActive) {
            return false;
        } else {
            switch (this.effectType) {
                case 0: // Explosion effect update
                    if (this.effectParameters[1] < this.effectParameters[2] / 5) {
                        this.effectImages[2] = this.effectImages[0].createCopy();
                        if (this.effectParameters[1] % 2 == 1) {
                            this.effectImages[2] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData) this.effectImages[2], 6), 5, 1);
                        } else {
                            this.effectImages[2] = ImageProcessor.adjustBrightnessContrast(this.effectImages[2], 2, 1);
                        }
                    } else if (this.effectParameters[1] >= (this.effectParameters[2] << 2) / 5) {
                        this.effectImages[2] = this.effectImages[1].createCopy();
                        if (this.effectParameters[1] % 2 == 1) {
                            this.effectImages[2] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData) this.effectImages[2], 6), 5, 1);
                        } else {
                            this.effectImages[2] = ImageProcessor.adjustBrightnessContrast(this.effectImages[2], 2, 1);
                        }
                    } else {
                        if (this.effectParameters[1] % 4 != 1 && this.effectParameters[1] % 4 != 2) {
                            this.effectImages[2] = this.effectImages[1].createCopy();
                        } else {
                            this.effectImages[2] = this.effectImages[0].createCopy();
                        }

                        if (this.effectParameters[1] % 2 == 1) {
                            this.effectImages[2] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData) this.effectImages[2], 8), 8, 1);
                        } else {
                            this.effectImages[2] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData) this.effectImages[2], 4), 4, 1);
                        }
                    }

                    if (this.effectParameters[1] >= this.effectParameters[2]) {
                        this.deactivateEffect();
                        this.cleanupResources();
                        return false;
                    }
                    ++this.effectParameters[1];
                    break;

                case 1: // Scrolling texture effect update
                    int[] tempRow;
                    int x;
                    int y;
                    label200:
                    switch (this.effectParameters[4]) {
                        case 0: // Scroll up
                            tempRow = new int[4];
                            for (x = 0; x < this.effectImages[1].width; ++x) {
                                for (y = 0; y < 4; ++y) {
                                    tempRow[y] = this.effectImages[1].pixels[x + y * this.effectImages[1].width];
                                }
                                for (y = 0; y < this.effectImages[1].height - 4; ++y) {
                                    this.effectImages[1].pixels[x + y * this.effectImages[1].width] = this.effectImages[1].pixels[x + (y + 4) * this.effectImages[1].width];
                                }
                                for (y = 0; y < 4; ++y) {
                                    this.effectImages[1].pixels[x + (y + this.effectImages[1].height - 4) * this.effectImages[1].width] = tempRow[y];
                                }
                            }
                            break;
                        case 1: // Scroll down
                            tempRow = new int[4];
                            x = 0;
                            while (true) {
                                if (x >= this.effectImages[1].width) {
                                    break label200;
                                }
                                for (y = 0; y < 4; ++y) {
                                    tempRow[y] = this.effectImages[1].pixels[x + (this.effectImages[1].height - 4 + y) * this.effectImages[1].width];
                                }
                                for (y = this.effectImages[1].height - 1; y > 3; --y) {
                                    this.effectImages[1].pixels[x + y * this.effectImages[1].width] = this.effectImages[1].pixels[x + (y - 4) * this.effectImages[1].width];
                                }
                                for (y = 0; y < 4; ++y) {
                                    this.effectImages[1].pixels[x + y * this.effectImages[1].width] = tempRow[y];
                                }
                                ++x;
                            }
                        case 2: // Scroll left
                            tempRow = new int[4];
                            x = 0;
                            while (true) {
                                if (x >= this.effectImages[1].height) {
                                    break label200;
                                }
                                for (y = 0; y < 4; ++y) {
                                    tempRow[y] = this.effectImages[1].pixels[x * this.effectImages[1].height + y];
                                }
                                for (y = 0; y < this.effectImages[1].width - 4; ++y) {
                                    this.effectImages[1].pixels[x * this.effectImages[1].height + y] = this.effectImages[1].pixels[x * this.effectImages[1].height + y + 4];
                                }
                                for (y = 0; y < 4; ++y) {
                                    this.effectImages[1].pixels[x * this.effectImages[1].height + y + this.effectImages[1].width - 4] = tempRow[y];
                                }
                                ++x;
                            }
                        case 3: // Scroll right
                            tempRow = new int[4];
                            for (x = 0; x < this.effectImages[1].height; ++x) {
                                for (y = 0; y < 4; ++y) {
                                    tempRow[y] = this.effectImages[1].pixels[x * this.effectImages[1].height + this.effectImages[1].width - 4 + y];
                                }
                                for (y = this.effectImages[1].width - 1; y > 3; --y) {
                                    this.effectImages[1].pixels[x * this.effectImages[1].height + y] = this.effectImages[1].pixels[x * this.effectImages[1].height + y - 4];
                                }
                                for (y = 0; y < 4; ++y) {
                                    this.effectImages[1].pixels[x * this.effectImages[1].height + y] = tempRow[y];
                                }
                            }
                    }

                    this.effectImages[2] = this.effectImages[0].createCopy();
                    this.effectImages[2] = ImageProcessor.blendImages(this.effectImages[2], this.effectImages[1], (byte) this.effectParameters[3]);
                    if (this.effectParameters[0] >= this.effectParameters[1]) {
                        this.deactivateEffect();
                        this.cleanupResources();
                        return false;
                    }
                    ++this.effectParameters[0];

                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    break;

                case 7: // Scaling effect
                case 9: // Color overlay effect
                case 10: // Alpha fade effect
                case 16: // Particle trail effect
                case 17: // Scaled overlay effect
                    if (this.effectParameters[0] >= this.effectParameters[1]) {
                        this.deactivateEffect();
                        this.cleanupResources();
                        return false;
                    }
                    ++this.effectParameters[0];
                    break;

                case 8: // Shake effect update
                    if (this.effectParameters[0] < this.effectParameters[1] / this.effectParameters[3] * this.effectParameters[2]) {
                        if (this.effectParameters[4] == 1) {
                            this.effectImages[1] = this.effectImages[0].createCopy();
                        }
                        this.effectImages[1] = ImageProcessor.adjustBrightnessContrast(ImageProcessor.scaleImageByPercent((ImageData) this.effectImages[1], this.effectParameters[5 + (this.effectParameters[2] - 1) * 3]), 1, 50);
                        ImageData shakeImage = this.effectImages[1];
                        shakeImage.offsetX += this.effectParameters[6 + (this.effectParameters[2] - 1) * 3];
                        shakeImage = this.effectImages[1];
                        shakeImage.offsetY += this.effectParameters[7 + (this.effectParameters[2] - 1) * 3];
                    } else {
                        ++this.effectParameters[2];
                    }

                    if (this.effectParameters[0] >= this.effectParameters[1]) {
                        this.deactivateEffect();
                        this.cleanupResources();
                        return false;
                    }
                    ++this.effectParameters[0];
                    break;

                case 11: // Multi-color effect
                case 12: // Multi-alpha effect
                case 13: // Sequential alpha effect
                case 14: // Brightness variation effect
                case 15: // Sequence color effect
                    if (this.effectParameters[2] < this.effectParameters[3]) {
                        ++this.effectParameters[2];
                    } else {
                        this.effectParameters[2] = 0;
                        ++this.effectParameters[0];
                        if (this.effectParameters[0] >= this.effectParameters[1]) {
                            this.effectParameters[0] = 0;
                            this.deactivateEffect();
                            this.cleanupResources();
                            return false;
                        }
                    }
                    break;

                default: // Animated sprite effect
                    this.sprite.updateAnimation();
            }
            return true;
        }
    }

    public final void render(Graphics graphics) {
        if (this.isVisible && this.isInteractable) {
            int cameraX;
            switch (this.effectType) {
                case 0: // Explosion effect render
                    graphics.drawRGB(this.effectImages[2].pixels, 0, this.effectImages[2].width, this.worldX + this.effectImages[2].offsetX, this.worldY + this.effectImages[2].offsetY, this.effectImages[2].width, this.effectImages[2].height, true);
                    return;

                case 1: // Scrolling texture effect render
                    graphics.drawRGB(this.effectImages[2].pixels, 0, this.effectImages[2].width, this.worldX + this.effectImages[2].offsetX, this.worldY + this.effectImages[2].offsetY, this.effectImages[2].width, this.effectImages[2].height, true);
                    return;

                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    return;

                case 7: // Scaling effect render
                    if (this.effectParameters[0] / this.effectParameters[2] % 2 == 0) {
                        graphics.drawRGB(this.effectImages[1].pixels, 0, this.effectImages[1].width, this.worldX + this.effectImages[0].offsetX + this.offsetX, this.worldY + this.effectImages[0].offsetY + this.offsetY, this.effectImages[1].width, this.effectImages[1].height, true);
                        return;
                    }
                    graphics.drawRGB(this.effectImages[0].pixels, 0, this.effectImages[0].width, this.worldX + this.effectImages[0].offsetX, this.worldY + this.effectImages[0].offsetY, this.effectImages[0].width, this.effectImages[0].height, true);
                    return;

                case 8: // Shake effect render
                    graphics.drawRGB(this.effectImages[1].pixels, 0, this.effectImages[1].width, this.worldX + this.effectImages[1].offsetX, this.worldY + this.effectImages[1].offsetY, this.effectImages[1].width, this.effectImages[1].height, true);
                    return;

                case 9: // Color overlay effect render
                case 10: // Alpha fade effect render
                    graphics.drawRGB(this.effectImages[0].pixels, 0, this.effectImages[0].width, this.worldX + this.effectImages[0].offsetX, this.worldY + this.effectImages[0].offsetY, this.effectImages[0].width, this.effectImages[0].height, true);
                    if (this.effectParameters[0] / this.effectParameters[2] % 2 == 0) {
                        graphics.drawRGB(this.effectImages[1].pixels, 0, this.effectImages[1].width, this.worldX + this.effectImages[1].offsetX, this.worldY + this.effectImages[1].offsetY, this.effectImages[1].width, this.effectImages[1].height, true);
                        return;
                    }
                    break;

                case 11: // Multi-color effect render
                case 14: // Brightness variation effect render
                    graphics.setColor(16711935);
                    for (cameraX = 1; cameraX < this.effectImages.length; ++cameraX) {
                        if (this.currentDirection == 1) {
                            graphics.drawRGB(this.effectImages[cameraX].pixels, 0, this.effectImages[cameraX].width, this.worldX + this.effectImages[cameraX].offsetX - this.effectParameters[4 + (this.effectParameters[0] * (this.effectImages.length - 1) << 1) + (cameraX - 1 << 1)], this.worldY + this.effectImages[cameraX].offsetY + this.effectParameters[4 + (this.effectParameters[0] * (this.effectImages.length - 1) << 1) + (cameraX - 1 << 1) + 1], this.effectImages[cameraX].width, this.effectImages[cameraX].height, true);
                        } else {
                            graphics.drawRGB(this.effectImages[cameraX].pixels, 0, this.effectImages[cameraX].width, this.worldX + this.effectImages[cameraX].offsetX + this.effectParameters[4 + (this.effectParameters[0] * (this.effectImages.length - 1) << 1) + (cameraX - 1 << 1)], this.worldY + this.effectImages[cameraX].offsetY + this.effectParameters[4 + (this.effectParameters[0] * (this.effectImages.length - 1) << 1) + (cameraX - 1 << 1) + 1], this.effectImages[cameraX].width, this.effectImages[cameraX].height, true);
                        }
                    }
                    return;

                case 12: // Multi-alpha effect render
                    if (this.currentDirection == 1) {
                        graphics.drawRGB(this.effectImages[1].pixels, 0, this.effectImages[1].width, this.worldX + this.effectImages[1].offsetX - (this.effectParameters[4 + (this.effectParameters[1] << 1) + (this.effectParameters[0] << 1)] + this.effectParameters[4 + (this.effectParameters[0] << 1)]), this.worldY + this.effectImages[1].offsetY - this.effectParameters[4 + (this.effectParameters[1] << 1) + (this.effectParameters[0] << 1) + 1] + this.effectParameters[4 + (this.effectParameters[0] << 1) + 1], this.effectImages[1].width, this.effectImages[1].height, true);
                        graphics.drawRGB(this.effectImages[0].pixels, 0, this.effectImages[0].width, this.worldX + this.effectImages[0].offsetX - this.effectParameters[4 + (this.effectParameters[0] << 1)], this.worldY + this.effectImages[0].offsetY + this.effectParameters[4 + (this.effectParameters[0] << 1) + 1], this.effectImages[0].width, this.effectImages[0].height, true);
                        return;
                    }
                    graphics.drawRGB(this.effectImages[1].pixels, 0, this.effectImages[1].width, this.worldX + this.effectImages[1].offsetX + this.effectParameters[4 + (this.effectParameters[1] << 1) + (this.effectParameters[0] << 1)] + this.effectParameters[4 + (this.effectParameters[0] << 1)], this.worldY + this.effectImages[1].offsetY - this.effectParameters[4 + (this.effectParameters[1] << 1) + (this.effectParameters[0] << 1) + 1] + this.effectParameters[4 + (this.effectParameters[0] << 1) + 1], this.effectImages[1].width, this.effectImages[1].height, true);
                    graphics.drawRGB(this.effectImages[0].pixels, 0, this.effectImages[0].width, this.worldX + this.effectImages[0].offsetX + this.effectParameters[4 + (this.effectParameters[0] << 1)], this.worldY + this.effectImages[0].offsetY + this.effectParameters[4 + (this.effectParameters[0] << 1) + 1], this.effectImages[0].width, this.effectImages[0].height, true);
                    return;

                case 13: // Sequential alpha effect render
                    for (cameraX = 0; cameraX < this.effectImages.length; ++cameraX) {
                        if (this.currentDirection == 1) {
                            graphics.drawRGB(this.effectImages[cameraX].pixels, 0, this.effectImages[cameraX].width, this.worldX + this.effectImages[cameraX].offsetX - this.effectParameters[4 + (this.effectParameters[0] * this.effectImages.length << 1) + (cameraX << 1)], this.worldY + this.effectImages[cameraX].offsetY + this.effectParameters[4 + (this.effectParameters[0] * this.effectImages.length << 1) + (cameraX << 1) + 1], this.effectImages[cameraX].width, this.effectImages[cameraX].height, true);
                        } else {
                            graphics.drawRGB(this.effectImages[cameraX].pixels, 0, this.effectImages[cameraX].width, this.worldX + this.effectImages[cameraX].offsetX + this.effectParameters[4 + (this.effectParameters[0] * this.effectImages.length << 1) + (cameraX << 1)], this.worldY + this.effectImages[cameraX].offsetY + this.effectParameters[4 + (this.effectParameters[0] * this.effectImages.length << 1) + (cameraX << 1) + 1], this.effectImages[cameraX].width, this.effectImages[cameraX].height, true);
                        }
                    }
                    return;

                case 15: // Sequence color effect render
                    cameraX = 4 + this.effectParameters[0] * 3;
                    if (this.currentDirection == 1) {
                        graphics.drawRGB(this.effectImages[this.effectParameters[cameraX]].pixels, 0, this.effectImages[this.effectParameters[cameraX]].width, this.worldX + this.effectImages[this.effectParameters[cameraX]].offsetX - this.effectParameters[cameraX + 1], this.worldY + this.effectImages[this.effectParameters[cameraX]].offsetY + this.effectParameters[cameraX + 2], this.effectImages[this.effectParameters[cameraX]].width, this.effectImages[this.effectParameters[cameraX]].height, true);
                        return;
                    }
                    graphics.drawRGB(this.effectImages[this.effectParameters[cameraX]].pixels, 0, this.effectImages[this.effectParameters[cameraX]].width, this.worldX + this.effectImages[this.effectParameters[cameraX]].offsetX + this.effectParameters[cameraX + 1], this.worldY + this.effectImages[this.effectParameters[cameraX]].offsetY + this.effectParameters[cameraX + 2], this.effectImages[this.effectParameters[cameraX]].width, this.effectImages[this.effectParameters[cameraX]].height, true);
                    return;

                case 16: // Particle trail effect render
                    for (cameraX = 0; cameraX < this.effectParameters[2]; ++cameraX) {
                        int cameraY;
                        for (cameraY = 0; cameraY < this.effectImages[0].width * this.effectParameters[0]; ++cameraY) {
                            if (this.effectImages[0].pixels[cameraX * this.effectParameters[1] * this.effectImages[0].width + cameraY] != 16777215 && this.effectImages[0].pixels[cameraX * this.effectParameters[1] * this.effectImages[0].width + cameraY] != 0) {
                                this.effectImages[0].pixels[cameraX * this.effectParameters[1] * this.effectImages[0].width + cameraY] &= 16777215;
                            }
                        }
                    }
                    graphics.drawRGB(this.effectImages[0].pixels, 0, this.effectImages[0].width, this.worldX + this.effectImages[0].offsetX, this.worldY + this.effectImages[0].offsetY, this.effectImages[0].width, this.effectImages[0].height, true);
                    return;

                case 17: // Scaled overlay effect render
                    graphics.drawRGB(this.effectImages[0].pixels, 0, this.effectImages[0].width, this.worldX + this.effectImages[0].offsetX, this.worldY + this.effectImages[0].offsetY + this.effectParameters[3], this.effectImages[0].width, this.effectImages[0].height, true);
                    if (this.effectParameters[0] / this.effectParameters[2] % 2 == 0) {
                        graphics.drawRGB(this.effectImages[1].pixels, 0, this.effectImages[1].width, this.worldX + this.effectImages[1].offsetX, this.worldY + this.effectImages[1].offsetY + this.effectParameters[3], this.effectImages[1].width, this.effectImages[1].height, true);
                        return;
                    }
                    break;

                default: // Animated sprite effect render
                    this.sprite.renderCurrentFrame(graphics, this.worldX, this.worldY, this.currentDirection);
            }
        }
    }
}
