package layout;

/**
 * Interface for handling dialog events and user interactions
 */
public interface DialogHandler {
   /**
    * Handle dialog action with navigation and selection data
    * @param navigationData Array containing navigation information [componentType, componentIndex, actionType]
    * @param selectionData Array containing selection state information
    */
   void action(int[] navigationData, int[] selectionData);
}
