package event;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * Event Script Manager - quản lý các event/script với multiple commands
 * Chứa danh sách commands và điều khiển execution flow
 */
public final class EventScript {

    /** Array of script commands */
    private ScriptCommand[] commands;

    /** Event ID/index */
    private byte eventId;

    /** Vector containing active commands */
    private Vector<ScriptCommand> activeCommands;

    /** Current command index being executed */
    private byte currentCommandIndex;

    /** Event execution state */
    private byte executionState;

    /** Event parameters (packed as int) */
    private int eventParameters;

    /**
     * Load event script from data stream
     * @param dataStream Input stream containing event data
     * @param eventIndex Index of this event
     * @param parameters Event parameters (packed int)
     * @param stringTable String lookup table for text references
     */
    public final void loadFromStream(DataInputStream dataStream, byte eventIndex, int parameters, String[] stringTable) {
        this.eventId = eventIndex;
        this.eventParameters = parameters;

        try {
            // Read number of commands
            short commandCount = dataStream.readShort();
            this.commands = new ScriptCommand[commandCount];
            this.activeCommands = new Vector<>();

            // Load each command
            for (int i = 0; i < commandCount; i++) {
                this.commands[i] = new ScriptCommand();
                this.commands[i].loadFromStream(dataStream, stringTable);
                this.activeCommands.addElement(this.commands[i]);
            }

            // Initialize execution state
            this.executionState = 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get current execution state
     * @return Current state (0 = ready, 1 = running, 2 = completed, etc.)
     */
    public final byte getExecutionState() {
        return this.executionState;
    }

    /**
     * Set execution state
     * @param state New execution state
     */
    public final void setExecutionState(byte state) {
        this.executionState = state;
    }

    /**
     * Get event ID
     * @return Event identifier
     */
    public final byte getEventId() {
        return this.eventId;
    }

    /**
     * Set current command index
     * @param commandIndex Index of command to execute next
     */
    public final void setCurrentCommandIndex(byte commandIndex) {
        this.currentCommandIndex = commandIndex;
    }

    /**
     * Get current active command
     * @return Current command or null if index out of bounds
     */
    public final ScriptCommand getCurrentCommand() {
        if (this.currentCommandIndex >= this.activeCommands.size()) {
            return null;
        }
        return (ScriptCommand) this.activeCommands.elementAt(this.currentCommandIndex);
    }

    /**
     * Get first command in the script
     * @return First command or null if no commands
     */
    public final ScriptCommand getFirstCommand() {
        if (this.activeCommands.isEmpty()) {
            return null;
        }
        return this.activeCommands.firstElement();
    }

    /**
     * Execute next command and advance to the next one
     * Handles command execution flow and looping
     */
    public final void executeNextCommand() {
        // Get current command (for any side effects)
        this.getCurrentCommand();

        // Advance to next command
        this.currentCommandIndex++;

        // Check if we've reached the end, loop back to start
        if (this.currentCommandIndex >= this.activeCommands.size()) {
            this.currentCommandIndex = 0;
        }
    }

    /**
     * Get event parameters as coordinate pair
     * @return int array [x, y] extracted from packed parameters, or null if no parameters
     */
    public final int[] getEventCoordinates() {
        if (this.eventParameters == -1) {
            return null;
        }

        // Extract coordinates from packed int
        // High byte = X coordinate, Low byte = Y coordinate
        int[] coordinates = new int[2];
        coordinates[0] = (this.eventParameters >> 8) & 255;  // X coordinate
        coordinates[1] = this.eventParameters & 255;         // Y coordinate

        return coordinates;
    }

    /**
     * Get total number of commands in this script
     * @return Number of commands
     */
    public final int getCommandCount() {
        return this.activeCommands != null ? this.activeCommands.size() : 0;
    }

    /**
     * Get command at specific index
     * @param index Command index
     * @return Command at index or null if out of bounds
     */
    public final ScriptCommand getCommandAt(int index) {
        if (this.activeCommands == null || index < 0 || index >= this.activeCommands.size()) {
            return null;
        }
        return this.activeCommands.elementAt(index);
    }

    /**
     * Check if script has completed execution
     * @return true if all commands have been executed
     */
    public final boolean isCompleted() {
        return this.executionState == 2; // Assuming 2 = completed
    }

    /**
     * Reset script to beginning
     */
    public final void reset() {
        this.currentCommandIndex = 0;
        this.executionState = 0;
    }

    /**
     * Get event parameters (raw packed int)
     * @return Event parameters
     */
    public final int getEventParameters() {
        return this.eventParameters;
    }

    /**
     * Set event parameters
     * @param parameters New event parameters
     */
    public final void setEventParameters(int parameters) {
        this.eventParameters = parameters;
    }
}