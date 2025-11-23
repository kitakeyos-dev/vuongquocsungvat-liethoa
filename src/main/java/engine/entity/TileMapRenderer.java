package engine.entity;

import engine.GameUtils;
import engine.GameEngineBase;
import engine.graphics.ImageProcessor;
import game.QuestManager;
import java.io.DataInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import me.kitakeyos.ManagedInputStream;

/**
 * Tile-based map renderer for 2D games
 * Handles loading, rendering, and management of tile maps with multiple layers
 */
public final class TileMapRenderer {
    // Singleton instance
    private static TileMapRenderer instance;                        // C68_f931

    // Sprite transformation lookup table
    private static final int[] SPRITE_TRANSFORMS = new int[]{0, 5, 3, 6, 2, 4, 1, 7}; // C68_f932

    // Screen dimensions
    private int screenWidth = -1;                                   // C68_f933
    private int screenHeight = -1;                                  // C68_f934

    // Map dimensions
    private short mapWidthInTiles = -1;                            // C68_f935
    private short mapHeightInTiles = -1;                           // C68_f936
    private short tileWidth = -1;                                  // C68_f937
    private short tileHeight = -1;                                 // C68_f938

    // Map properties
    private int currentMapId = -1;                                 // C68_f939
    private byte currentTilesetId = -1;                            // C68_f940
    private byte previousTilesetId = -1;                           // C68_f941

    // Tileset images
    private Image[] tilesetImages = null;                          // C68_f942

    // Camera/viewport position
    public int cameraX;                                            // C68_f943
    public int cameraY;                                            // C68_f944
    public int mapPixelWidth;                                      // C68_f945
    public int mapPixelHeight;                                     // C68_f946

    // Rendering viewport
    private int viewportStartTileX;                                // C68_f947
    private int viewportStartTileY;                                // C68_f948
    private int viewportTileWidth;                                 // C68_f949
    private int viewportTileHeight;                                // C68_f950

    // Layer data
    private byte layerCount;                                       // C68_f951
    private short[][] tileDefinitions;                             // C68_f952 - Tile sprite definitions [tileId][imageIndex, srcX, srcY, width, height]
    private short[][][] layerData;                                 // C68_f953 - Layer data [layerId][x][y] or [layerId][objectIndex][properties]
    private byte[] layerTypes = null;                              // C68_f954 - Type of each layer (0=grid, 1=grid with transforms, 2-4=object layers)

    /**
     * Get the singleton instance of TileMapRenderer
     * @return TileMapRenderer instance
     */
    public static TileMapRenderer getInstance() {
        if (instance == null) {
            instance = new TileMapRenderer();
        }
        return instance;
    }

    /**
     * Initialize the tile map renderer
     */
    public TileMapRenderer() {
        this.screenHeight = GameEngineBase.getScreenHeight();
        this.screenWidth = GameEngineBase.getScreenWidth();
    }

    /**
     * Clear all map data and free memory
     */
    public final void clearMapData() {
        this.layerData = null;
        this.layerTypes = null;
        this.tileDefinitions = null;
    }

    /**
     * Load a map from file
     * @param mapId ID of the map to load
     */
    public final void loadMap(int mapId) {
        this.currentMapId = mapId;

        try {
            InputStream mapStream = ManagedInputStream.openStream("/data/map/map_" + this.currentMapId + ".mid");
            DataInputStream dataInput = new DataInputStream(mapStream);

            // Read map header
            byte version = dataInput.readByte();
            this.previousTilesetId = this.currentTilesetId;
            this.currentTilesetId = dataInput.readByte();

            // Load tileset if changed
            this.loadTileset();

            // Read map dimensions
            if (version == 1) {
                this.mapWidthInTiles = (short) dataInput.readByte();
            } else {
                this.mapWidthInTiles = dataInput.readShort();
            }

            if (version == 1) {
                this.mapHeightInTiles = (short) dataInput.readByte();
            } else {
                this.mapHeightInTiles = dataInput.readShort();
            }

            // Read tile dimensions
            this.tileWidth = (short) dataInput.readByte();
            this.tileHeight = this.tileWidth;

            // Calculate map pixel dimensions
            this.mapPixelWidth = this.mapWidthInTiles * this.tileWidth;
            this.mapPixelHeight = this.mapHeightInTiles * this.tileHeight;

            // Read layer count and initialize arrays
            this.layerCount = dataInput.readByte();
            this.layerTypes = new byte[this.layerCount];
            this.layerData = new short[this.layerCount][][];

            // Read each layer
            for (int layerIndex = 0; layerIndex < this.layerCount; layerIndex++) {
                byte layerId = dataInput.readByte();
                this.layerTypes[layerId] = dataInput.readByte();
                short dataCount = dataInput.readShort();

                // Initialize layer data based on type
                if (this.layerTypes[layerId] != LayerType.GRID_BASIC && this.layerTypes[layerId] != LayerType.GRID_TRANSFORMED) {
                    // Object layer
                    this.layerData[layerId] = new short[dataCount][4];
                } else {
                    // Grid layer
                    this.layerData[layerId] = new short[this.mapWidthInTiles][this.mapHeightInTiles];

                    // Initialize with empty tiles
                    for (int x = 0; x < this.mapWidthInTiles; x++) {
                        for (int y = 0; y < this.mapHeightInTiles; y++) {
                            this.layerData[layerId][x][y] = -1;
                        }
                    }
                }

                // Read layer data
                for (int dataIndex = 0; dataIndex < dataCount; dataIndex++) {
                    short x, y;

                    // Read coordinates
                    if (version == 1) {
                        x = (short) dataInput.readByte();
                        y = (short) dataInput.readByte();
                    } else {
                        x = dataInput.readShort();
                        y = dataInput.readShort();
                    }

                    short tileData = dataInput.readShort();

                    // Store data based on layer type
                    if (this.layerTypes[layerIndex] == LayerType.GRID_TRANSFORMED) {
                        this.layerData[layerIndex][x][y] = tileData;
                    } else if (this.layerTypes[layerId] == LayerType.GRID_BASIC) {
                        this.layerData[layerIndex][x][y] = (short) (tileData & 0xFFF); // Mask to get tile ID
                    } else {
                        // Object layer
                        this.layerData[layerIndex][dataIndex][1] = x;
                        this.layerData[layerIndex][dataIndex][2] = y;
                        this.layerData[layerIndex][dataIndex][0] = (short) (tileData & 0xFFF); // Tile ID
                        this.layerData[layerIndex][dataIndex][3] = (short) ((tileData & 0x7000) >> 12); // Transform
                    }
                }
            }

            dataInput.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load tileset images and definitions
     */
    private void loadTileset() {
        // Clean up previous tileset
        this.cleanupTileset();

        // Load new tileset images
        this.tilesetImages = new Image[ResourceManager.moduleInfoData[this.currentTilesetId].length];

        for (int i = 0; i < this.tilesetImages.length; i++) {
            if (QuestManager.questState == 1) {
                this.tilesetImages[i] = ImageProcessor.convertToGrayscale(ResourceManager.getCachedImage(ResourceManager.moduleInfoData[this.currentTilesetId][i]));
            } else {
                this.tilesetImages[i] = ResourceManager.getCachedImage(ResourceManager.moduleInfoData[this.currentTilesetId][i]);
            }
        }

        // Load tile definitions
        try {
            InputStream defStream = ManagedInputStream.openStream("/data/mod/mod_" + this.currentTilesetId + ".mid");
            DataInputStream defInput = new DataInputStream(defStream);

            short tileDefCount = defInput.readShort();
            this.tileDefinitions = new short[tileDefCount][5];

            for (int i = 0; i < tileDefCount; i++) {
                this.tileDefinitions[i][0] = (short) defInput.readByte();  // Image index
                this.tileDefinitions[i][1] = defInput.readShort();         // Source X
                this.tileDefinitions[i][2] = defInput.readShort();         // Source Y
                this.tileDefinitions[i][3] = defInput.readShort();         // Width
                this.tileDefinitions[i][4] = defInput.readShort();         // Height
            }

            defInput.close();
            defStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Clean up previous tileset resources
     */
    private void cleanupTileset() {
        if (this.tilesetImages != null) {
            if (QuestManager.questState != 0) {
                // Force cleanup mode
                for (int i = 0; i < this.tilesetImages.length; i++) {
                    if (this.tilesetImages[i] != null) {
                        this.tilesetImages[i] = null;
                    }
                }
                this.tilesetImages = null;
            } else {
                // Smart cleanup - only release images not used in new tileset
                for (int i = 0; i < this.tilesetImages.length; i++) {
                    boolean stillUsed = false;
                    for (int j = 0; j < ResourceManager.moduleInfoData[this.currentTilesetId].length; j++) {
                        if (ResourceManager.moduleInfoData[this.previousTilesetId][i] == ResourceManager.moduleInfoData[this.currentTilesetId][j]) {
                            ResourceManager.releaseImageReference(ResourceManager.moduleInfoData[this.previousTilesetId][i]);
                            this.tilesetImages[i] = null;
                            stillUsed = true;
                            break;
                        }
                    }

                    if (!stillUsed && this.tilesetImages[i] != null) {
                        ResourceManager.forceReleaseImage(ResourceManager.moduleInfoData[this.previousTilesetId][i]);
                        this.tilesetImages[i] = null;
                    }
                }
                this.tilesetImages = null;
            }
        }
    }

    /**
     * Refresh tileset images (reload current tileset)
     */
    public void refreshTileset() {
        // Release current images
        for (int i = 0; i < this.tilesetImages.length; i++) {
            if (QuestManager.questState == 1) {
                this.tilesetImages[i] = ImageProcessor.convertToGrayscale(ResourceManager.getCachedImage(ResourceManager.moduleInfoData[this.currentTilesetId][i]));
            } else {
                ResourceManager.forceReleaseImage(ResourceManager.moduleInfoData[this.currentTilesetId][i]);
            }
        }

        // Reload images
        for (int i = 0; i < this.tilesetImages.length; i++) {
            if (QuestManager.questState == 0) {
                this.tilesetImages[i] = ResourceManager.getCachedImage(ResourceManager.moduleInfoData[this.currentTilesetId][i]);
            }
        }
    }

    /**
     * Update viewport calculations based on camera position
     */
    public final void updateViewport() {
        if (this.tileWidth != 0) {
            this.viewportStartTileY = this.cameraY / this.tileHeight;
            this.viewportStartTileX = this.cameraX / this.tileWidth;
            this.viewportTileHeight = this.screenHeight / this.tileHeight + 1;
            this.viewportTileWidth = this.screenWidth / this.tileWidth + 1;

            // Clamp viewport to map bounds
            if (this.viewportStartTileY + this.viewportTileHeight >= this.mapHeightInTiles) {
                this.viewportTileHeight = this.mapHeightInTiles - 1 - this.viewportStartTileY;
            }

            if (this.viewportTileWidth + this.viewportStartTileX >= this.mapWidthInTiles) {
                this.viewportTileWidth = this.mapWidthInTiles - 1 - this.viewportStartTileX;
            }
        }
    }

    /**
     * Render a specific layer
     * @param graphics Graphics context for rendering
     * @param layerId ID of the layer to render
     */
    public final void renderLayer(Graphics graphics, int layerId) {
        switch (this.layerTypes[layerId]) {
            case LayerType.GRID_BASIC:
                this.renderGridLayer(graphics, layerId, false);
                break;

            case LayerType.GRID_TRANSFORMED:
                this.renderGridLayer(graphics, layerId, true);
                break;

            case LayerType.OBJECT_LAYER_1:
            case LayerType.OBJECT_LAYER_2:
            case LayerType.OBJECT_LAYER_3:
                this.renderObjectLayer(graphics, layerId);
                break;
        }
    }

    /**
     * Render a grid-based layer
     * @param graphics Graphics context
     * @param layerId Layer ID
     * @param useTransforms Whether to apply sprite transformations
     */
    private void renderGridLayer(Graphics graphics, int layerId, boolean useTransforms) {
        for (int x = 0; x <= this.viewportTileWidth; x++) {
            for (int y = 0; y <= this.viewportTileHeight; y++) {
                int mapX = this.viewportStartTileX + x;
                int mapY = this.viewportStartTileY + y;

                if (mapX >= 0 && mapX < this.mapWidthInTiles && mapY >= 0 && mapY < this.mapHeightInTiles) {
                    short tileData = this.layerData[layerId][mapX][mapY];

                    if (tileData != -1) {
                        int screenX = x * this.tileWidth - this.cameraX % this.tileWidth;
                        int screenY = y * this.tileHeight - this.cameraY % this.tileHeight;

                        if (useTransforms) {
                            short tileId = (short) (tileData & 0xFFF);
                            int transform = SPRITE_TRANSFORMS[(tileData & 0x7000) >> 12];

                            graphics.drawRegion(
                                    this.tilesetImages[this.tileDefinitions[tileId][0]],
                                    this.tileDefinitions[tileId][1],
                                    this.tileDefinitions[tileId][2],
                                    this.tileDefinitions[tileId][3],
                                    this.tileDefinitions[tileId][4],
                                    transform,
                                    screenX, screenY,
                                    Graphics.TOP | Graphics.LEFT
                            );
                        } else {
                            graphics.drawRegion(
                                    this.tilesetImages[0],
                                    this.tileDefinitions[tileData][1],
                                    this.tileDefinitions[tileData][2],
                                    this.tileDefinitions[tileData][3],
                                    this.tileDefinitions[tileData][4],
                                    0,
                                    screenX, screenY,
                                    Graphics.TOP | Graphics.LEFT
                            );
                        }
                    }
                }
            }
        }
    }

    /**
     * Render an object-based layer
     * @param graphics Graphics context
     * @param layerId Layer ID
     */
    private void renderObjectLayer(Graphics graphics, int layerId) {
        for (int objIndex = 0; objIndex < this.layerData[layerId].length; objIndex++) {
            if (this.layerData[layerId][objIndex][2] >= 0) {
                int objX = this.layerData[layerId][objIndex][1];
                int objY = this.layerData[layerId][objIndex][2];
                short tileId = this.layerData[layerId][objIndex][0];
                int transform = this.layerData[layerId][objIndex][3];

                // Check if object is visible in viewport
                if (GameUtils.checkCollision(
                        objX, objY,
                        this.tileDefinitions[tileId][3], this.tileDefinitions[tileId][4],
                        this.viewportStartTileX, this.viewportStartTileY,
                        this.viewportTileWidth + 1, this.viewportTileHeight + 1,
                        transform)) {

                    int screenX = (objX - this.viewportStartTileX) * this.tileWidth - this.cameraX % this.tileWidth;
                    int screenY = (objY - this.viewportStartTileY) * this.tileHeight - this.cameraY % this.tileHeight;

                    graphics.drawRegion(
                            this.tilesetImages[this.tileDefinitions[tileId][0]],
                            this.tileDefinitions[tileId][1],
                            this.tileDefinitions[tileId][2],
                            this.tileDefinitions[tileId][3],
                            this.tileDefinitions[tileId][4],
                            SPRITE_TRANSFORMS[transform],
                            screenX, screenY,
                            Graphics.TOP | Graphics.LEFT
                    );
                }
            }
        }
    }

    /**
     * Set camera position
     * @param centerX X coordinate to center camera on
     * @param centerY Y coordinate to center camera on
     */
    public final void setCameraPosition(int centerX, int centerY) {
        this.cameraX = centerX - this.screenWidth / 2;
        this.cameraY = centerY - this.screenHeight / 2;

        // Clamp camera to map bounds
        if (this.cameraX + this.screenWidth >= this.mapWidthInTiles * this.tileWidth) {
            this.cameraX = this.mapWidthInTiles * this.tileWidth - this.screenWidth;
        }

        if (this.cameraX <= 0) {
            this.cameraX = 0;
        }

        if (this.cameraY + this.screenHeight >= this.mapHeightInTiles * this.tileHeight) {
            this.cameraY = this.mapHeightInTiles * this.tileHeight - this.screenHeight;
        }

        if (this.cameraY <= 0) {
            this.cameraY = 0;
        }
    }

    /**
     * Get tile type at world coordinates
     * @param layerId Layer to check (unused in current implementation)
     * @param worldX X coordinate in world space
     * @param worldY Y coordinate in world space
     * @return Tile type value or -1 if invalid
     */
    public final byte getTileAt(int layerId, int worldX, int worldY) {
        if (this.layerData != null && this.layerData[layerId] != null) {
            int tileX = worldX / this.tileWidth;
            int tileY = worldY / this.tileHeight;

            if (this.isOutOfBounds(worldX, worldY)) {
                return 1; // Solid boundary
            }

            // Clamp coordinates
            if (tileY < 0) tileY = 0;
            if (tileX < 0) tileX = 0;
            if (tileX > this.mapWidthInTiles) tileX = this.mapWidthInTiles;
            if (tileY > this.mapHeightInTiles) tileY = this.mapHeightInTiles;

            return (byte) this.layerData[layerId][tileX][tileY];
        }
        return -1;
    }

    /**
     * Check if coordinates are outside map bounds
     * @param worldX X coordinate in world space
     * @param worldY Y coordinate in world space
     * @return true if coordinates are out of bounds
     */
    public final boolean isOutOfBounds(int worldX, int worldY) {
        return worldX <= 0 || worldX >= this.mapPixelWidth || worldY <= 0 || worldY >= this.mapPixelHeight;
    }

    /**
     * Layer type constants
     */
    private static class LayerType {
        public static final int GRID_BASIC = 0;        // Basic grid layer
        public static final int GRID_TRANSFORMED = 1;  // Grid layer with sprite transformations
        public static final int OBJECT_LAYER_1 = 2;    // Object layer type 1
        public static final int OBJECT_LAYER_2 = 3;    // Object layer type 2
        public static final int OBJECT_LAYER_3 = 4;    // Object layer type 3
    }

    /**
     * Get current map dimensions
     * @return Array containing [widthInTiles, heightInTiles, tileWidth, tileHeight]
     */
    public final int[] getMapDimensions() {
        return new int[]{this.mapWidthInTiles, this.mapHeightInTiles, this.tileWidth, this.tileHeight};
    }

    /**
     * Get current camera position
     * @return Array containing [cameraX, cameraY]
     */
    public final int[] getCameraPosition() {
        return new int[]{this.cameraX, this.cameraY};
    }

    /**
     * Check if a map is currently loaded
     * @return true if a map is loaded
     */
    public final boolean isMapLoaded() {
        return this.layerData != null && this.layerData.length > 0;
    }
}