package layout;

import engine.GameEngineBase;

import javax.microedition.lcdui.Graphics;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * Singleton manager for handling multiple dialog instances and their lifecycle
 */
public final class DialogManager {
    private static DialogManager instance;
    private Hashtable dialogCache = new Hashtable();        // Cache of loaded dialogs
    private Vector activeDialogs = new Vector();            // Currently active dialogs
    private Vector dialogStack = new Vector();              // Dialog display order stack
    public DialogSystem currentDialog;                      // Currently focused dialog
    public TextRenderer textRenderer = new TextRenderer(); // Global text renderer

    /**
     * Private constructor for singleton pattern
     */
    private DialogManager() {
        // Initialize text renderer
    }

    /**
     * Get the singleton instance of DialogManager
     *
     * @return DialogManager instance
     */
    public static DialogManager getInstance() {
        if (instance == null) {
            instance = new DialogManager();
        }
        return instance;
    }

    /**
     * Clear all dialogs and free resources
     */
    public final void clearAllDialogs() {
        if (instance != null) {
            DialogManager manager = instance;
            Enumeration dialogs = manager.dialogCache.elements();

            // Clean up all cached dialogs
            while (dialogs.hasMoreElements()) {
                ((DialogSystem) dialogs.nextElement()).cleanup();
            }

            manager.dialogCache.clear();
            manager.activeDialogs.removeAllElements();
        }

        this.currentDialog = null;
        System.gc(); // Suggest garbage collection
    }

    /**
     * Render all active dialogs in order
     *
     * @param graphics Graphics context for rendering
     */
    public final void render(Graphics graphics) {
        if (this.activeDialogs != null) {
            for (int i = 0; i < this.activeDialogs.size(); i++) {
                ((DialogSystem) this.activeDialogs.elementAt(i)).render(graphics);
            }
        }

        // Reset clip region
        graphics.setClip(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
    }

    /**
     * Close the currently focused dialog
     */
    public final void closeCurrentDialog() {
        if (this.currentDialog != null) {
            this.currentDialog.closeDialog();
        }
    }

    /**
     * Show a dialog with the specified parameters
     *
     * @param dialogResourceId Resource identifier for the dialog
     * @param resourceType     Type of resource to load
     * @param eventHandler     Handler for dialog events
     */
    public final void showDialog(String dialogResourceId, int resourceType, DialogHandler eventHandler) {
        DialogSystem dialog = (DialogSystem) this.dialogCache.get(dialogResourceId);

        // Handle existing dialog
        if (dialog != null) {
            // Special handling for persistent dialogs
            if (!dialogResourceId.equals("/data/ui/dialog.ui")) {
                this.dialogCache.remove(dialogResourceId);
            }

            this.activeDialogs.removeElement(dialog);

            // Clean up non-persistent dialogs
            if (!dialogResourceId.equals("/data/ui/dialog.ui")) {
                dialog.cleanup();
                dialog = null;
            }

            // Re-activate persistent dialogs
            if (dialogResourceId.equals("/data/ui/dialog.ui")) {
                this.activeDialogs.addElement(dialog);
                this.currentDialog = dialog;
                this.dialogStack.addElement(dialogResourceId);
            }
        }

        // Create new dialog if needed
        if (dialog == null) {
            dialog = new DialogSystem(eventHandler);
            dialog.setTextRenderer(this.textRenderer);
            dialog.loadDialog(dialogResourceId, resourceType, false);
            this.dialogCache.put(dialogResourceId, dialog);
            this.activeDialogs.addElement(dialog);
            this.currentDialog = dialog;
            this.dialogStack.addElement(dialogResourceId);
        }
    }

    /**
     * Remove a dialog from the manager
     *
     * @param dialogResourceId Resource identifier of dialog to remove
     */
    public final void removeDialog(String dialogResourceId) {
        DialogSystem dialog = (DialogSystem) this.dialogCache.get(dialogResourceId);

        if (dialog != null) {
            // Update current dialog reference
            if (this.currentDialog.equals(dialog)) {
                this.currentDialog = null;
            }

            // Remove from cache and active list
            if (!dialogResourceId.equals("/data/ui/dialog.ui")) {
                this.dialogCache.remove(dialogResourceId);
            }

            this.activeDialogs.removeElement(dialog);
            this.dialogStack.removeElement(dialogResourceId);

            // Clean up non-persistent dialogs
            if (!dialogResourceId.equals("/data/ui/dialog.ui")) {
                dialog.cleanup();
            }
        }

        // Set new current dialog if others exist
        if (!this.dialogCache.isEmpty() && !this.activeDialogs.isEmpty()) {
            this.currentDialog = (DialogSystem) this.activeDialogs.lastElement();
        }
    }

    /**
     * Check if a dialog is currently on top of the stack
     *
     * @param dialogResourceId Resource identifier to check
     * @return true if the dialog is on top
     */
    public boolean isTopDialog(String dialogResourceId) {
        return !this.dialogStack.isEmpty() && this.dialogStack.lastElement().equals(dialogResourceId);
    }

    /**
     * Check if a dialog is currently loaded
     *
     * @param dialogResourceId Resource identifier to check
     * @return true if the dialog is loaded
     */
    public boolean containsDialog(String dialogResourceId) {
        return !this.dialogStack.isEmpty() && this.dialogStack.contains(dialogResourceId);
    }

    /**
     * Get a specific dialog by resource ID
     *
     * @param dialogResourceId Resource identifier
     * @return Dialog instance or null if not found
     */
    public DialogSystem getDialog(String dialogResourceId) {
        return (DialogSystem) this.dialogCache.get(dialogResourceId);
    }

}