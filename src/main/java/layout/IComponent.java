package layout;

import javax.microedition.lcdui.Graphics;

/**
 * IComponent Interface - base interface for all UI components
 * Defines common methods for rendering, updating, positioning, and hierarchy management
 */
public interface IComponent {

   /**
    * Set component visibility
    * @param visible true to show component, false to hide
    */
   void setVisible(boolean visible);

   /**
    * Render the component
    * @param graphics Graphics context for drawing
    * @param isFocused true if component has focus
    * @param isEnabled true if component is enabled for interaction
    * @param parentComponent Parent component in hierarchy
    * @param renderParams Additional rendering parameters
    */
   void render(Graphics graphics, boolean isFocused, boolean isEnabled, IComponent parentComponent, int[] renderParams);

   /**
    * Update component logic and state
    * @param isFocused true if component has focus
    * @param isEnabled true if component is enabled
    * @param parentComponent Parent component in hierarchy
    * @param updateParams Additional update parameters
    */
   void update(boolean isFocused, boolean isEnabled, IComponent parentComponent, int[] updateParams);

   /**
    * Get ID of currently selected child component
    * @return Selected component ID or -1 if none selected
    */
   int getSelectedComponentId();

   /**
    * Get X position offset of component
    * @return X coordinate in pixels
    */
   int getOffsetX();

   /**
    * Set X position offset of component
    * @param xOffset New X coordinate in pixels
    * @param parentComponent Parent component for relative positioning
    */
   void setOffsetX(int xOffset, IComponent parentComponent);

   /**
    * Get Y position offset of component
    * @return Y coordinate in pixels
    */
   int getOffsetY();

   /**
    * Set Y position offset of component
    * @param yOffset New Y coordinate in pixels
    * @param parentComponent Parent component for relative positioning
    */
   void setOffsetY(int yOffset, IComponent parentComponent);

   /**
    * Get width of component
    * @return Width in pixels
    */
   int getWidth();

   /**
    * Set width of component
    * @param width New width in pixels
    * @param parentComponent Parent component for layout calculations
    */
   void setWidth(int width, IComponent parentComponent);

   /**
    * Get height of component
    * @return Height in pixels
    */
   int getHeight();

   /**
    * Set height of component
    * @param height New height in pixels
    * @param parentComponent Parent component for layout calculations
    */
   void setHeight(int height, IComponent parentComponent);

   /**
    * Get child component (for single-child containers)
    * @return Child component or null if no child
    */
   Component getChildComponent();

   /**
    * Get all child components (for multi-child containers)
    * @return Array of child components or null if no children
    */
   IComponent[] getComponents();

   /**
    * Get component data object
    * @return Component-specific data or null if no data
    */
   DialogData getComponentData();

   /**
    * Set component data object
    * @param componentData New component data
    */
   void setComponentData(DialogData componentData);

   /**
    * Get Z-index for rendering order
    * @return Z-index value (higher values render on top)
    */
   int getZIndex();

   /**
    * Get ID of currently active component
    * @return Active component ID or -1 if none active
    */
   int getActiveComponentId();

   /**
    * Set active component
    * @param activeComponent Component to make active
    */
   void setActiveComponent(IComponent activeComponent);

   /**
    * Clean up component resources and references
    * Called when component is being destroyed
    */
   void cleanUp();
}