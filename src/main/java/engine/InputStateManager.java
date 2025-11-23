package engine;

/**
 * Abstract base class for managing input state and key mappings
 * Provides functionality for tracking key states, pointer position, and delegating input to child handlers
 */
public abstract class InputStateManager implements InputHandler {
    // State management
    protected boolean isActive;                          // C8_f110 - Whether this manager is active
    private InputHandler childHandler;                   // C8_f111 - Child input handler to delegate to

    // Key state tracking (bit masks)
    private int currentKeyState = 0;                     // C8_f112 - Currently held keys
    private int pressedKeysThisFrame = 0;                // C8_f113 - Keys pressed this frame
    private int releasedKeysThisFrame = 0;               // C8_f114 - Keys released this frame
    private int previousKeyState = 0;                    // C8_f115 - Previous frame key state
    private int currentPressedKeys = 0;                  // C8_f116 - Current pressed key mask
    private int currentReleasedKeys = 0;                 // C8_f117 - Current released key mask

    // Pointer tracking
    private int lastPointerX = -1;                       // C8_f118 - Last pointer X position
    private int lastPointerY = -1;                       // C8_f119 - Last pointer Y position

    /**
     * Convert a key code to its corresponding bit mask for state tracking
     *
     * @param keyCode The key code to convert
     * @return Bit mask representing this key
     */
    private static int keyCodeToBitMask(int keyCode) {
        switch (keyCode) {
            // Navigation keys
            case -22:
            case -7:
                return 262144;  // Up arrow
            case -21:
            case -6:
                return 131072;  // Down arrow
            case -5:
                return 65536;   // Left arrow
            case -4:
                return 32768;   // Right arrow
            case -3:
                return 16384;   // Select/Enter
            case -2:
                return 8192;    // Back/Cancel
            case -1:
                return 4096;    // Menu

            // Numeric keypad
            case 48:
            case 109:
                return 1;       // 0
            case 49:
            case 114:
                return 2;       // 1
            case 50:
            case 116:
                return 4;       // 2
            case 51:
            case 121:
                return 8;       // 3
            case 52:
            case 102:
                return 16;      // 4
            case 53:
            case 103:
                return 32;      // 5
            case 54:
            case 104:
                return 64;      // 6
            case 55:
            case 118:
                return 128;     // 7
            case 56:
            case 98:
                return 256;     // 8
            case 57:
            case 110:
                return 512;     // 9

            // Special keys
            case 35:
            case 117:
                return 2048;    // # key
            case 42:
            case 106:
                return 1024;    // * key

            default:
                return 0; // Unknown key
        }
    }

    /**
     * Check if a specific key is currently pressed
     *
     * @param keyMask Bit mask of the key to check
     * @return true if the key is currently pressed
     */
    public final boolean isKeyPressed(int keyMask) {
        return (this.currentPressedKeys & keyMask) != 0;
    }

    /**
     * Check if pointer click occurred within specified bounds
     *
     * @param x      Left boundary
     * @param y      Top boundary
     * @param width  Width of the area
     * @param height Height of the area
     * @return true if click was within bounds
     */
    public final boolean isPointerClickInBounds(int x, int y, int width, int height) {
        if (this.lastPointerX >= x && this.lastPointerX <= x + width &&
                this.lastPointerY >= y && this.lastPointerY <= y + height) {
            // Reset pointer position after successful hit test
            this.lastPointerX = -1;
            this.lastPointerY = -1;
            return true;
        }
        return false;
    }

    /**
     * Check if a key was released (with some bit manipulation)
     *
     * @param keyCode Key code to check
     * @return true if key was released
     */
    public final boolean isKeyReleased(int keyCode) {
        return (this.currentReleasedKeys & keyCode) != 0;
    }

    /**
     * Check if a key is currently held down
     *
     * @param keyMask Bit mask of the key to check
     * @return true if key is held down
     */
    public final boolean isKeyHeld(int keyMask) {
        return (this.previousKeyState & keyMask) != 0;
    }

    /**
     * Reset all input states and show soft keys
     */
    public final void showSoftKeys() {
        this.currentKeyState = 0;
        this.pressedKeysThisFrame = 0;
        this.releasedKeysThisFrame = 0;
        this.previousKeyState = 0;
        this.currentPressedKeys = 0;
        this.currentReleasedKeys = 0;

        if (this.childHandler != null) {
            this.childHandler.showSoftKeys();
        }
    }

    /**
     * Update input state for next frame
     * Call this at the end of each frame to prepare for next frame's input
     */
    protected final void updateInputState() {
        this.previousKeyState = this.currentKeyState;
        this.currentPressedKeys = this.pressedKeysThisFrame;
        this.currentReleasedKeys = this.releasedKeysThisFrame;
        this.pressedKeysThisFrame = 0;
        this.releasedKeysThisFrame = 0;
    }

    /**
     * Handle key press event
     *
     * @param keyCode The key code that was pressed
     */
    public final void onKeyPressed(int keyCode) {
        int keyMask = keyCodeToBitMask(keyCode);
        this.pressedKeysThisFrame |= keyMask;
        this.currentKeyState |= keyMask;

        if (this.childHandler != null) {
            this.childHandler.onKeyPressed(keyCode);
        }
    }

    /**
     * Handle key release event
     *
     * @param keyCode The key code that was released
     */
    public final void onKeyReleased(int keyCode) {
        int keyMask = keyCodeToBitMask(keyCode);
        this.releasedKeysThisFrame |= keyMask;
        this.currentKeyState &= ~keyMask;

        if (this.childHandler != null) {
            this.childHandler.onKeyReleased(keyCode);
        }
    }

    /**
     * Handle pointer press event
     *
     * @param x X coordinate of the pointer
     * @param y Y coordinate of the pointer
     */
    public final void onPointerPressed(int x, int y) {
        if (this.childHandler != null) {
            this.childHandler.onPointerPressed(x, y);
        }
    }

    /**
     * Handle pointer release event
     *
     * @param x X coordinate where pointer was pressed
     * @param y Y coordinate where pointer was pressed
     */
    public final void onPointerReleased(int x, int y) {
        this.lastPointerX = x;
        this.lastPointerY = y;

        if (this.childHandler != null) {
            this.childHandler.onPointerReleased(x, y);
        }
    }

    /**
     * Set the active state of this input manager
     *
     * @param active Whether this manager should be active
     */
    public void setActive(boolean active) {
        this.showSoftKeys();
        this.isActive = active;
    }

    /**
     * Set a child input handler to delegate input events to
     *
     * @param handler The child handler to set, or null to remove
     */
    protected final void setChildHandler(InputHandler handler) {
        if (this.childHandler != null) {
            this.childHandler.setActive(false);
            this.childHandler = null;
        }

        if (handler != null) {
            handler.setActive(true);
            this.childHandler = handler;
        }
    }

    /**
     * Check if this input manager is currently active
     *
     * @return true if active
     */
    public final boolean isActive() {
        return this.isActive;
    }
}