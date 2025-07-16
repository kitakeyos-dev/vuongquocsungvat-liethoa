package a.a;

import java.util.Vector;

/**
 * Debug Logger - utility class for logging errors and debug messages
 * Maintains a collection of logged messages for debugging purposes
 */
public final class DebugLogger {
   // Storage for logged messages
   private static Vector logEntries = new Vector();        // C16_f218 - Collection of log entries

   /**
    * Log an error or debug message with optional exception
    * @param exception Exception that occurred (can be null)
    * @param message Custom message to log (can be null)
    */
   public static void logError(Throwable exception, String message) {
      String[] logEntry = new String[]{"", ""};

      // Handle message logging
      if (message != null) {
         logEntry[0] = message;
         System.out.println(logEntry[0]);
      } else {
         logEntry[0] = "";
         System.out.println(logEntry[0]);
      }

      // Handle exception logging
      if (exception != null) {
         logEntry[1] = exception.toString();
      } else {
         logEntry[1] = "";
      }

      // Store log entry for later retrieval
      logEntries.addElement(logEntry);
   }

   /**
    * Log a simple message without exception
    * @param message Message to log
    */
   public static void logMessage(String message) {
      logError(null, message);
   }

   /**
    * Log an exception without custom message
    * @param exception Exception to log
    */
   public static void logException(Throwable exception) {
      logError(exception, null);
   }

   /**
    * Log a debug message with timestamp
    * @param message Debug message
    */
   public static void logDebug(String message) {
      String timestampedMessage = "[DEBUG] " + System.currentTimeMillis() + ": " + message;
      logError(null, timestampedMessage);
   }

   /**
    * Log a warning message
    * @param message Warning message
    */
   public static void logWarning(String message) {
      String warningMessage = "[WARNING] " + message;
      logError(null, warningMessage);
   }

   /**
    * Log an info message
    * @param message Info message
    */
   public static void logInfo(String message) {
      String infoMessage = "[INFO] " + message;
      logError(null, infoMessage);
   }

   /**
    * Get all logged entries
    * @return Vector containing all log entries as String arrays [message, exception]
    */
   public static Vector getLogEntries() {
      return logEntries;
   }

   /**
    * Get the number of logged entries
    * @return Number of log entries
    */
   public static int getLogCount() {
      return logEntries.size();
   }

   /**
    * Get a specific log entry
    * @param index Index of the log entry
    * @return String array containing [message, exception] or null if invalid index
    */
   public static String[] getLogEntry(int index) {
      if (index >= 0 && index < logEntries.size()) {
         return (String[]) logEntries.elementAt(index);
      }
      return null;
   }

   /**
    * Get the most recent log entry
    * @return Most recent log entry or null if no entries
    */
   public static String[] getLastLogEntry() {
      if (logEntries.size() > 0) {
         return (String[]) logEntries.lastElement();
      }
      return null;
   }

   /**
    * Clear all log entries
    */
   public static void clearLogs() {
      logEntries.removeAllElements();
   }

   /**
    * Check if there are any logged errors (entries with exceptions)
    * @return true if any log entries contain exceptions
    */
   public static boolean hasErrors() {
      for (int i = 0; i < logEntries.size(); i++) {
         String[] entry = (String[]) logEntries.elementAt(i);
         if (entry[1] != null && !entry[1].isEmpty()) {
            return true;
         }
      }
      return false;
   }

   /**
    * Get all error entries (entries with exceptions)
    * @return Vector containing only error entries
    */
   public static Vector getErrorEntries() {
      Vector errorEntries = new Vector();
      for (int i = 0; i < logEntries.size(); i++) {
         String[] entry = (String[]) logEntries.elementAt(i);
         if (entry[1] != null && !entry[1].isEmpty()) {
            errorEntries.addElement(entry);
         }
      }
      return errorEntries;
   }

   /**
    * Get formatted log output for debugging
    * @return String containing all log entries formatted for display
    */
   public static String getFormattedLogs() {
      if (logEntries.size() == 0) {
         return "No log entries";
      }

      StringBuffer buffer = new StringBuffer();
      buffer.append("=== Debug Log (" + logEntries.size() + " entries) ===\n");

      for (int i = 0; i < logEntries.size(); i++) {
         String[] entry = (String[]) logEntries.elementAt(i);
         buffer.append("[" + i + "] ");

         if (entry[0] != null && !entry[0].isEmpty()) {
            buffer.append("Message: " + entry[0]);
         }

         if (entry[1] != null && !entry[1].isEmpty()) {
            if (entry[0] != null && !entry[0].isEmpty()) {
               buffer.append(" | ");
            }
            buffer.append("Exception: " + entry[1]);
         }

         buffer.append("\n");
      }

      return buffer.toString();
   }

   /**
    * Print all logs to console
    */
   public static void printAllLogs() {
      System.out.println(getFormattedLogs());
   }

   /**
    * Log method entry for debugging
    * @param className Name of the class
    * @param methodName Name of the method
    */
   public static void logMethodEntry(String className, String methodName) {
      logDebug("Entering " + className + "." + methodName + "()");
   }

   /**
    * Log method exit for debugging
    * @param className Name of the class
    * @param methodName Name of the method
    */
   public static void logMethodExit(String className, String methodName) {
      logDebug("Exiting " + className + "." + methodName + "()");
   }

   /**
    * Log variable value for debugging
    * @param variableName Name of the variable
    * @param value Value of the variable
    */
   public static void logVariable(String variableName, Object value) {
      String valueStr = (value != null) ? value.toString() : "null";
      logDebug(variableName + " = " + valueStr);
   }

   /**
    * Log performance timing
    * @param operation Name of the operation
    * @param startTime Start time in milliseconds
    * @param endTime End time in milliseconds
    */
   public static void logPerformance(String operation, long startTime, long endTime) {
      long duration = endTime - startTime;
      logInfo("Performance: " + operation + " took " + duration + "ms");
   }

   /**
    * Get summary of log statistics
    * @return String containing log statistics
    */
   public static String getLogSummary() {
      int totalEntries = logEntries.size();
      int errorCount = getErrorEntries().size();
      int messageCount = totalEntries - errorCount;

      return "Log Summary - Total: " + totalEntries +
              ", Messages: " + messageCount +
              ", Errors: " + errorCount;
   }

   /**
    * Check if logging is available
    * @return true if logging system is functional
    */
   public static boolean isLoggingAvailable() {
      return logEntries != null;
   }

   /**
    * Set maximum number of log entries to keep
    * @param maxEntries Maximum number of entries (0 = unlimited)
    */
   public static void setMaxLogEntries(int maxEntries) {
      if (maxEntries > 0) {
         while (logEntries.size() > maxEntries) {
            logEntries.removeElementAt(0); // Remove oldest entries
         }
      }
   }
}