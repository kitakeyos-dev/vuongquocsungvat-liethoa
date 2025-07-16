package a.b;

import a.GameUtils;

/**
 * Static cache manager for loading and caching sprite data with reference counting
 * Handles sprite resource management and memory optimization
 */
public final class SpriteDataCache {
   // Cache array for sprite data objects
   private static SpriteAnimationData[] spriteCache;                    // C64_f892 - Cache of loaded sprite objects

   /**
    * Initialize the sprite manager with specified capacity
    * @param capacity Maximum number of sprites that can be cached
    */
   public static void initialize(int capacity) {
      spriteCache = new SpriteAnimationData[1000]; // Fixed size as in original
   }

   /**
    * Load and get sprite data with reference counting
    * @param spriteId ID of the sprite to load
    * @return Sprite data object with incremented reference count
    */
   public static SpriteAnimationData getSpriteData(int spriteId) {
      // Load sprite if not already cached
      if (spriteCache[spriteId] == null) {
         spriteCache[spriteId] = new SpriteAnimationData();
         loadSpriteFromFile(spriteId);
      }

      // Increment reference count
      spriteCache[spriteId].referenceCount++;
      return spriteCache[spriteId];
   }

   /**
    * Load sprite data from file
    * @param spriteId ID of the sprite to load
    */
   private static void loadSpriteFromFile(int spriteId) {
      byte[] fileData = new byte[20000];
      int[] readPosition = new int[]{0};

      // Load sprite file data
      GameUtils.loadStreamToByteArray(fileData, "/data/spr/spr_" + spriteId + "_all(r)", 0);

      SpriteAnimationData spriteData = spriteCache[spriteId];

      // Read sprite frame indices
      spriteData.spriteDefinitions = GameUtils.readShortArray(fileData, readPosition);

      // Read sprite frame definitions (coordinates, dimensions)
      spriteData.spriteCompositions = GameUtils.readShortMatrix(fileData, readPosition);

      // Read sprite collision data
      spriteData.animationSequences = GameUtils.readShortMatrix(fileData, readPosition);

      // Process animation sequences
      short[] animationRawData = GameUtils.readShortArray(fileData, readPosition);
      spriteData.frameEvents = parseAnimationSequences(animationRawData, spriteData.spriteCompositions.length);

      // Process additional animation data (possibly events or triggers)
      short[] eventsRawData = GameUtils.readShortArray(fileData, readPosition);
      spriteData.frameTriggers = parseAnimationSequences(eventsRawData, spriteData.spriteCompositions.length);
   }

   /**
    * Parse raw animation sequence data into structured format
    * @param rawData Raw animation data from file
    * @param frameCount Number of available frames
    * @return Parsed animation sequences organized by animation ID
    */
   private static short[][] parseAnimationSequences(short[] rawData, int frameCount) {
      if (rawData == null) {
         return null;
      }

      // Create array to hold animation sequences for each animation ID
      short[][] animationSequences = new short[frameCount][];

      // Parse animation data in groups of 5 values per entry
      // Format: [animationId, param1, param2, param3, param4]
      for (int entryIndex = 0; entryIndex < rawData.length / 5; entryIndex++) {
         int baseIndex = entryIndex * 5;
         short animationId = rawData[baseIndex];

         // Extract the 4 parameters for this animation entry
         short[] animationParams = new short[]{
                 rawData[baseIndex + 1],  // Parameter 1 (frame index?)
                 rawData[baseIndex + 2],  // Parameter 2 (timing?)
                 rawData[baseIndex + 3],  // Parameter 3 (x offset?)
                 rawData[baseIndex + 4]   // Parameter 4 (y offset?)
         };

         // Append these parameters to the animation sequence for this ID
         animationSequences[animationId] = GameUtils.concatenateShortArrays(
                 animationSequences[animationId],
                 animationParams
         );
      }

      return animationSequences;
   }

   /**
    * Release a reference to sprite data
    * @param spriteId ID of the sprite to release
    */
   public static void releaseSpriteReference(int spriteId) {
      if (spriteCache[spriteId] != null) {
         spriteCache[spriteId].referenceCount--;

         // Ensure reference count doesn't go below zero
         if (spriteCache[spriteId].referenceCount <= 0) {
            spriteCache[spriteId].referenceCount = 0;
         }
      }
   }

   /**
    * Attempt to unload sprite data if no longer referenced
    * @param spriteId ID of the sprite to potentially unload
    * @return true if sprite was successfully unloaded, false if still referenced
    */
   public static boolean tryUnloadSprite(int spriteId) {
      if (spriteCache[spriteId] != null && spriteCache[spriteId].referenceCount <= 0) {
         spriteCache[spriteId].referenceCount = 0;
         spriteCache[spriteId] = null;
         return true;
      }
      return false;
   }
}