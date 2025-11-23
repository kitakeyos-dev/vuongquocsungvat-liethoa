package engine;

/**
 * Interface for handling user input events including key presses, pointer events, and state changes
 */
public interface InputHandler {

   /**
    * Handle key press event
    * @param keyCode The key code that was pressed
    */
   void onKeyPressed(int keyCode);

   /**
    * Handle key release event
    * @param keyCode The key code that was released
    */
   void onKeyReleased(int keyCode);

   /**
    * Set the active state of this input handler
    * @param active Whether this handler should be active and receive input
    */
   void setActive(boolean active);

   /**
    * Show soft keys (virtual keyboard) if available
    */
   void showSoftKeys();

   /**
    * Handle pointer move event (mouse/touch move)
    * @param x X coordinate of the pointer
    * @param y Y coordinate of the pointer
    */
   void onPointerPressed(int x, int y);

   /**
    * Handle pointer press event (mouse/touch down)
    * @param x X coordinate where pointer was pressed
    * @param y Y coordinate where pointer was pressed
    */
   void onPointerReleased(int x, int y);
}