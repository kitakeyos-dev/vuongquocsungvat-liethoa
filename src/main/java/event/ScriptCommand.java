package event;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Script Command - đại diện cho một lệnh trong event script
 * Chứa command ID, numeric parameters, và string parameters
 */
public final class ScriptCommand {

    /** Command type/ID - determines what action this command performs */
    private short commandId;

    /** Numeric parameters for the command */
    private short[] numericParameters;

    /** String parameters for the command (text, dialog, etc.) */
    private String[] stringParameters;

    /**
     * Load command data from input stream
     * @param dataStream Input stream containing command data
     * @param stringTable Lookup table for string references
     */
    public final void loadFromStream(DataInputStream dataStream, String[] stringTable) {
        try {
            // Read command ID
            this.commandId = dataStream.readShort();

            // Read parameter counts
            byte totalParameterCount = dataStream.readByte();
            byte numericParameterCount = dataStream.readByte();

            // Load numeric parameters
            this.numericParameters = new short[numericParameterCount];
            for (int i = 0; i < numericParameterCount; i++) {
                this.numericParameters[i] = dataStream.readShort();
            }

            // Load string parameters (if string table is available)
            if (stringTable != null) {
                int stringParameterCount = totalParameterCount - numericParameterCount;
                this.stringParameters = new String[stringParameterCount];

                for (int i = 0; i < stringParameterCount; i++) {
                    short stringIndex = dataStream.readShort();
                    this.stringParameters[i] = stringTable[stringIndex];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get command ID/type
     * @return Command identifier that determines the action to perform
     */
    public final short getCommandId() {
        return this.commandId;
    }

    /**
     * Get numeric parameters
     * @return Array of numeric parameters for this command
     */
    public final short[] getNumericParameters() {
        return this.numericParameters;
    }

    /**
     * Get string parameters
     * @return Array of string parameters for this command
     */
    public final String[] getStringParameters() {
        return this.stringParameters;
    }

    /**
     * Get specific numeric parameter by index
     * @param index Parameter index
     * @return Parameter value or 0 if index out of bounds
     */
    public final short getNumericParameter(int index) {
        if (this.numericParameters == null || index < 0 || index >= this.numericParameters.length) {
            return 0;
        }
        return this.numericParameters[index];
    }

    /**
     * Get specific string parameter by index
     * @param index Parameter index
     * @return Parameter string or null if index out of bounds
     */
    public final String getStringParameter(int index) {
        if (this.stringParameters == null || index < 0 || index >= this.stringParameters.length) {
            return null;
        }
        return this.stringParameters[index];
    }

    /**
     * Get number of numeric parameters
     * @return Count of numeric parameters
     */
    public final int getNumericParameterCount() {
        return this.numericParameters != null ? this.numericParameters.length : 0;
    }

    /**
     * Get number of string parameters
     * @return Count of string parameters
     */
    public final int getStringParameterCount() {
        return this.stringParameters != null ? this.stringParameters.length : 0;
    }

    /**
     * Check if command has any parameters
     * @return true if command has numeric or string parameters
     */
    public final boolean hasParameters() {
        return getNumericParameterCount() > 0 || getStringParameterCount() > 0;
    }
}