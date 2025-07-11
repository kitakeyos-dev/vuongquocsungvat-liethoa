package me.kitakeyos;

import game.GameMIDLet;
import java.io.DataInputStream;
import java.nio.charset.StandardCharsets;
import javax.microedition.midlet.MIDlet;

/**
 * SMS Configuration Loader - loads and decrypts SMS payment configuration
 * Handles encrypted data from file or app properties
 */
public final class SmsConfigLoader {
   // Current SMS data for sending
   public static String currentSmsNumber;
   public static String currentSmsContent;

   // Configuration arrays
   private static String[] smsNumbers;
   private static String[] smsContentTemplates;
   public static String[] smsDescriptions;
   private static int[] smsCosts;

   // Initialization flag
   private static boolean configLoaded = false;

   /**
    * Prepare SMS data for specific payment type
    * @param paymentIndex Index of payment type (0-4)
    */
   public static void prepareSmsData(int paymentIndex) {
      if (smsNumbers != null && smsContentTemplates != null) {
         if (paymentIndex < smsContentTemplates.length) {
            // Get UID from app properties
            String userId = GameMIDLet.C15_f217.getAppProperty("uid");
            if (userId == null) {
               userId = "0";
            }

            // Replace %1 placeholder with user ID
            String messageContent = replaceString(smsContentTemplates[paymentIndex], "%1", userId);

            // Get Terminal info
            String terminalInfo = GameMIDLet.C15_f217.getAppProperty("Term");
            if (terminalInfo == null) {
               terminalInfo = "";
            }

            // Replace %2 placeholder with terminal info
            messageContent = replaceString(messageContent, "%2", terminalInfo);

            // Get Reference Code
            String refCode = GameMIDLet.C15_f217.getAppProperty("RefCode");
            if (refCode == null) {
               refCode = "";
            }

            // Replace %cp placeholder with reference code and clean up
            messageContent = replaceString(messageContent, "%cp", refCode).trim();
            messageContent = replaceString(messageContent, "  ", " "); // Remove double spaces

            // Set current SMS data
            currentSmsNumber = smsNumbers[paymentIndex];
            currentSmsContent = messageContent;
         }
      }
   }

   /**
    * Convert hex character to byte value
    * @param hexChar Hex character (0-9, a-f)
    * @return Byte value
    */
   private static byte hexCharToByte(byte hexChar) {
      return (byte)(hexChar <= 57 ? hexChar - 48 : hexChar + 10 - 97);
   }

   /**
    * Convert hex string bytes to binary data
    * @param hexBytes Hex string as byte array
    * @return Binary data
    */
   private static byte[] hexStringToBytes(byte[] hexBytes) {
      int hexLength = hexBytes.length;
      byte[] binaryData = new byte[hexLength / 2];
      int hexIndex = 0;

      for(int binaryIndex = 0; hexIndex < hexLength; binaryIndex++) {
         binaryData[binaryIndex] |= hexCharToByte(hexBytes[hexIndex]);
         binaryData[binaryIndex] = (byte)(binaryData[binaryIndex] | hexCharToByte(hexBytes[hexIndex + 1]) << 4);
         hexIndex += 2;
      }

      return binaryData;
   }

   /**
    * Replace all occurrences of substring in string
    * @param sourceString Source string
    * @param searchString String to find
    * @param replaceString Replacement string
    * @return Modified string
    */
   private static String replaceString(String sourceString, String searchString, String replaceString) {
      StringBuffer result = new StringBuffer();
      int foundIndex = sourceString.indexOf(searchString);
      int lastIndex = 0;
      int searchLength = searchString.length();

      while (foundIndex != -1) {
         result.append(sourceString.substring(lastIndex, foundIndex)).append(replaceString);
         lastIndex = foundIndex + searchLength;
         foundIndex = sourceString.indexOf(searchString, lastIndex);
      }

      result.append(sourceString.substring(lastIndex, sourceString.length()));
      return result.toString();
   }

   /**
    * Decrypt data using XOR cipher with key
    * @param encryptedData Data to decrypt
    * @param decryptionKey XOR key
    */
   private static void xorDecrypt(byte[] encryptedData, byte[] decryptionKey) {
      int dataLength = encryptedData.length;
      int keyLength = decryptionKey.length;

      for(int i = 0; i < dataLength; i++) {
         int keyOffset = keyLength - i % 3;

         for(int j = 0; j < keyOffset; j++) {
            encryptedData[i] ^= decryptionKey[j];
         }
      }
   }

   /**
    * Convert byte array to UTF-8 string with fallback
    * @param bytes Byte array
    * @return UTF-8 string
    */
   private static String bytesToString(byte[] bytes) {
      String result;
      try {
         result = new String(bytes, StandardCharsets.UTF_8);
      } catch (Exception e1) {
         try {
            result = new String(bytes, StandardCharsets.UTF_8);
         } catch (Exception e2) {
            result = new String(bytes);
         }
      }

      return result;
   }

   /**
    * Split string by delimiter
    * @param sourceString String to split
    * @param delimiter Delimiter string
    * @return Array of split strings
    */
   private static String[] splitString(String sourceString, String delimiter) {
      if (sourceString == null) {
         System.out.println("split. sourceString == null");
         return null;
      }

      if (sourceString.isEmpty() || delimiter == null) {
         return new String[]{sourceString};
      }

      if (delimiter.length() <= 0) {
         return new String[]{sourceString};
      }

      String[] result = null;
      int[] startIndices = new int[sourceString.length()];
      int[] endIndices = new int[sourceString.length()];
      int segmentCount = 0;
      boolean inSegment = false;
      int currentPos = 0;

      // Find all segments separated by delimiter
      do {
         boolean delimiterFound = false;
         int delimiterEnd = currentPos + delimiter.length();

         if (delimiterEnd <= sourceString.length() &&
                 sourceString.substring(currentPos, delimiterEnd).equals(delimiter)) {
            delimiterFound = true;
         }

         if (delimiterFound) {
            if (inSegment) {
               inSegment = false;
               endIndices[segmentCount++] = currentPos;
            }
            currentPos = delimiterEnd;
         } else {
            if (!inSegment) {
               inSegment = true;
               startIndices[segmentCount] = currentPos;
            }
            currentPos++;
         }
      } while(currentPos < sourceString.length());

      if (inSegment) {
         endIndices[segmentCount++] = sourceString.length();
      }

      // Extract segments
      if (segmentCount > 0) {
         result = new String[segmentCount];
         for(int i = 0; i < segmentCount; i++) {
            result[i] = sourceString.substring(startIndices[i], endIndices[i]);
         }
      }

      return result;
   }

   /**
    * Load SMS configuration from MIDlet properties or encrypted file
    * @param midlet MIDlet instance for accessing app properties
    */
   public static void loadSmsConfiguration(MIDlet midlet) {
      if (!configLoaded) {
         configLoaded = true;

         // Try to get configuration from app properties first
         String configData = midlet.getAppProperty("sr");
         boolean isFromProperties = true;
         String[] configLines = null;
         byte[] rawData;

         if (configData == null) {
            // Load from encrypted file
            try {
               DataInputStream dataStream = new DataInputStream(ManagedInputStream.openStream("/l2.bin"));
               rawData = new byte[dataStream.available()];
               dataStream.read(rawData);
               dataStream.close();

               configLines = splitString(bytesToString(rawData), String.valueOf('\n'));
               rawData = configLines[0].trim().getBytes();
            } catch (Exception e) {
               e.printStackTrace();
               return;
            }
            isFromProperties = false;
         } else {
            rawData = configData.getBytes();
         }

         // Decrypt the configuration data
         byte[] decryptionKey = new byte[6];
         int dataLength = rawData.length;

         // Extract key from first 3 and last 3 bytes
         System.arraycopy(rawData, 0, decryptionKey, 0, 3);
         System.arraycopy(rawData, dataLength - 3, decryptionKey, 3, 3);

         // Extract encrypted payload (middle part)
         byte[] encryptedPayload = new byte[dataLength - 6];
         System.arraycopy(rawData, 3, encryptedPayload, 0, dataLength - 6);

         // Convert from hex and decrypt
         encryptedPayload = hexStringToBytes(encryptedPayload);
         xorDecrypt(encryptedPayload, decryptionKey);

         // Verify checksum
         byte[] checksumKey = new byte[]{1, 2, 5, 7, 4};
         int payloadLength = encryptedPayload.length - 5;

         // Calculate checksum
         for(int i = 0; i < payloadLength; i++) {
            checksumKey[i % 5] ^= encryptedPayload[i];
         }

         // Verify checksum
         for(int i = 0; i < 5; i++) {
            if (checksumKey[i] != encryptedPayload[payloadLength + i]) {
               return; // Checksum failed
            }
         }

         // Extract valid payload
         byte[] validPayload = new byte[payloadLength];
         System.arraycopy(encryptedPayload, 0, validPayload, 0, payloadLength);

         // Parse configuration data
         String[] configParts = splitString(bytesToString(validPayload), "|");
         int paymentTypeCount = Integer.parseInt(configParts[0]);

         // Initialize arrays
         smsNumbers = new String[paymentTypeCount];
         smsContentTemplates = new String[paymentTypeCount];
         smsCosts = new int[paymentTypeCount];

         // Parse SMS numbers, costs, and templates
         for(int i = 0; i < paymentTypeCount; i++) {
            smsNumbers[i] = configParts[i + 1];
            smsCosts[i] = Integer.parseInt(configParts[i + paymentTypeCount + 1]);
            smsContentTemplates[i] = configParts[i + paymentTypeCount + paymentTypeCount + 1];
         }

         // Load descriptions
         smsDescriptions = new String[paymentTypeCount];
         if (isFromProperties) {
            // Load descriptions from app properties
            for(int i = 0; i < paymentTypeCount; i++) {
               smsDescriptions[i] = midlet.getAppProperty("sr" + (i + 1));
            }
         } else {
            // Load descriptions from file
            for(int i = 0; i < paymentTypeCount; i++) {
               smsDescriptions[i] = configLines[i + 1].trim();
            }
         }
      }
   }

   /**
    * Get SMS number for payment type
    * @param paymentIndex Payment type index
    * @return SMS number or null if invalid index
    */
   public static String getSmsNumber(int paymentIndex) {
      if (smsNumbers != null && paymentIndex >= 0 && paymentIndex < smsNumbers.length) {
         return smsNumbers[paymentIndex];
      }
      return null;
   }

   /**
    * Get SMS cost for payment type
    * @param paymentIndex Payment type index
    * @return SMS cost or 0 if invalid index
    */
   public static int getSmsCost(int paymentIndex) {
      if (smsCosts != null && paymentIndex >= 0 && paymentIndex < smsCosts.length) {
         return smsCosts[paymentIndex];
      }
      return 0;
   }

   /**
    * Get number of available payment types
    * @return Number of payment types
    */
   public static int getPaymentTypeCount() {
      return smsNumbers != null ? smsNumbers.length : 0;
   }
}