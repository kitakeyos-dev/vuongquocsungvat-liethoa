package layout;

import a.GameUtils;
import javax.microedition.lcdui.Graphics;

/**
 * A container component that can hold and manage multiple child UI components
 * with positioning, alignment, and layout capabilities
 */
public final class UIContainerComponent implements IComponent {
   // Core component properties
   private int selectedComponentId = -1;
   private int offsetX = 0;
   private int offsetY = 0;
   private int width = 0;
   private int height = 0;
   private int zIndex = 0;

   // Layout and behavior configuration
   private byte[][] actionMappings = null;              // Renamed from dialogData
   private ComponentData componentData = new ComponentData();
   private int parentComponentId = -1;                  // Renamed from activeComponentId
   private byte alignmentMode = 9;                      // Alignment mode for positioning

   // Child components management
   private ScrollableListComponent attachedListComponent = null;  // Renamed from childScrollableListComponent
   private IComponent[] childComponents = new IComponent[60];     // Renamed from components

   // List components for navigation and interaction
   public ScrollableListComponent primaryListComponent = null;    // Renamed from otherChildScrollableListComponent
   public ScrollableListComponent secondaryListComponent = null;  // Renamed from additionalChildScrollableListComponent

   // Visibility state
   private boolean isVisible = true;

   /**
    * Create a new UI container component
    */
   public UIContainerComponent() {
      this.isVisible = true;
   }

   /**
    * Set the visibility of this container
    * @param visible Whether the container should be visible
    */
   public final void setVisible(boolean visible) {
      this.isVisible = visible;
   }

   /**
    * Set action mappings for input handling
    * @param mappings 2D array defining action mappings
    */
   public final void setActionMappings(byte[][] mappings) {
      this.actionMappings = mappings;
   }

   /**
    * Get the action mappings for this container
    * @return Action mappings array
    */
   public final byte[][] getDialogData() {
      return this.actionMappings;
   }

   /**
    * Render this container and all its child components
    * @param graphics Graphics context for rendering
    * @param isHighlighted Whether this container is highlighted
    * @param isEnabled Whether this container is enabled
    * @param rootComponent Root component for coordinate calculations
    * @param activeIndices Array of currently active component indices
    */
   public final void render(Graphics graphics, boolean isHighlighted, boolean isEnabled,
                            IComponent rootComponent, int[] activeIndices) {
      if (!this.isVisible) {
         return;
      }

      for (int i = 0; i < this.childComponents.length && this.childComponents[i] != null; i++) {
         if (this.childComponents[i].getChildComponent() != null) {
            // Determine if this child should be highlighted
            boolean shouldHighlight = false;
            int activeSlot = GameUtils.findFirstAvailableSlot(activeIndices);
            if (activeSlot > 0 && activeIndices[activeSlot - 1] == this.selectedComponentId) {
               shouldHighlight = true;
            }

            // Render child with list component
            this.childComponents[i].getChildComponent().renderList(graphics,
                    this.childComponents[i].getSelectedComponentId(), shouldHighlight,
                    activeIndices, isEnabled, rootComponent);
         } else {
            // Render regular child component
            this.childComponents[i].render(graphics, isHighlighted, isEnabled, rootComponent, activeIndices);
         }
      }
   }

   /**
    * Update this container and all child components
    * @param isHighlighted Whether this container is highlighted
    * @param isEnabled Whether this container is enabled
    * @param rootComponent Root component for coordinate calculations
    * @param activeIndices Array of currently active component indices
    */
   public final void update(boolean isHighlighted, boolean isEnabled,
                            IComponent rootComponent, int[] activeIndices) {
      for (int i = 0; i < this.childComponents.length && this.childComponents[i] != null; i++) {
         if (this.childComponents[i].getChildComponent() != null) {
            // Update child with list component
            if (GameUtils.findFirstAvailableSlot(activeIndices) > 0) {
               // Additional logic could be added here for active state handling
            }

            this.childComponents[i].getChildComponent().updateComponent(
                    this.childComponents[i].getSelectedComponentId(), activeIndices,
                    isEnabled, rootComponent);
         } else {
            // Update regular child component
            this.childComponents[i].update(isHighlighted, isEnabled, rootComponent, activeIndices);
         }
      }
   }

   /**
    * Get the ID of the currently selected component
    * @return Selected component ID
    */
   public final int getSelectedComponentId() {
      return this.selectedComponentId;
   }

   /**
    * Set the currently selected component ID
    * @param componentId ID of component to select
    */
   public final void setSelectedComponentId(int componentId) {
      this.selectedComponentId = componentId;
   }

   /**
    * Get the X offset of this container
    * @return X offset in pixels
    */
   public final int getOffsetX() {
      return this.offsetX;
   }

   /**
    * Set the X offset of this container
    * @param x New X offset
    * @param rootComponent Root component for alignment calculations
    */
   public final void setOffsetX(int x, IComponent rootComponent) {
      this.offsetX = x;
      this.updateAlignment(rootComponent);
   }

   /**
    * Get the Y offset of this container
    * @return Y offset in pixels
    */
   public final int getOffsetY() {
      return this.offsetY;
   }

   /**
    * Set the Y offset of this container
    * @param y New Y offset
    * @param rootComponent Root component for alignment calculations
    */
   public final void setOffsetY(int y, IComponent rootComponent) {
      this.offsetY = y;
      this.updateAlignment(rootComponent);
   }

   /**
    * Get the width of this container
    * @return Width in pixels
    */
   public final int getWidth() {
      return this.width;
   }

   /**
    * Set the width of this container
    * @param width New width
    * @param rootComponent Root component for alignment calculations
    */
   public final void setWidth(int width, IComponent rootComponent) {
      this.width = width;
      this.updateAlignment(rootComponent);
   }

   /**
    * Get the height of this container
    * @return Height in pixels
    */
   public final int getHeight() {
      return this.height;
   }

   /**
    * Set the height of this container
    * @param height New height
    * @param rootComponent Root component for alignment calculations
    */
   public final void setHeight(int height, IComponent rootComponent) {
      this.height = height;
      this.updateAlignment(rootComponent);
   }

   /**
    * Get the attached scrollable list component
    * @return Attached list component or null
    */
   public final ScrollableListComponent getChildComponent() {
      return this.attachedListComponent;
   }

   /**
    * Set the attached scrollable list component
    * @param listComponent List component to attach
    */
   public final void setChildComponent(ScrollableListComponent listComponent) {
      this.attachedListComponent = listComponent;
   }

   /**
    * Get the array of child components
    * @return Array of child components
    */
   public final IComponent[] getComponents() {
      return this.childComponents;
   }

   /**
    * Get the component data for this container
    * @return Component data object
    */
   public final ComponentData getComponentData() {
      return this.componentData;
   }

   /**
    * Set the component data for this container
    * @param data New component data
    */
   public final void setComponentData(ComponentData data) {
      this.componentData = data;
   }

   /**
    * Get the Z-index (layer) of this component
    * @return Z-index value
    */
   public final int getZIndex() {
      return this.zIndex;
   }

   /**
    * Set the Z-index (layer) of this component
    * @param zIndex New Z-index value
    */
   public final void setZIndex(int zIndex) {
      this.zIndex = zIndex;
   }

   /**
    * Get the ID of the parent component
    * @return Parent component ID
    */
   public final int getActiveComponentId() {
      return this.parentComponentId;
   }

   /**
    * Set the ID of the parent component
    * @param parentId Parent component ID
    */
   public final void setActiveComponentId(int parentId) {
      this.parentComponentId = parentId;
   }

   /**
    * Update alignment and positioning relative to a parent component
    * @param rootComponent Root component for calculations
    */
   public final void setActiveComponent(IComponent rootComponent) {
      if (rootComponent == null) {
         return;
      }

      // Apply alignment if we have a parent component and alignment is not default
      if (this.parentComponentId > 0 && this.alignmentMode != 9) {
         IComponent parentComponent = GameUtils.findChildById(rootComponent, this.parentComponentId);
         this.applyAlignment(parentComponent);
      }

      // Recursively update all child components
      if (this.childComponents != null) {
         for (int i = 0; i < this.childComponents.length && this.childComponents[i] != null; i++) {
            this.childComponents[i].setActiveComponent(rootComponent);
         }
      }
   }

   /**
    * Apply alignment positioning relative to a parent component
    * @param parentComponent Component to align relative to
    */
   private void applyAlignment(IComponent parentComponent) {
      if (parentComponent == null) {
         return;
      }

      switch (this.alignmentMode) {
         case 0: // Top-left
            this.offsetX = parentComponent.getOffsetX();
            this.offsetY = parentComponent.getOffsetY();
            break;
         case 1: // Top-center
            this.offsetX = parentComponent.getOffsetX() + (parentComponent.getWidth() - this.width) / 2;
            this.offsetY = parentComponent.getOffsetY();
            this.width = parentComponent.getWidth();
            break;
         case 2: // Top-right
            this.offsetX = parentComponent.getOffsetX() + (parentComponent.getWidth() - this.width);
            this.offsetY = parentComponent.getOffsetY();
            break;
         case 3: // Middle-left
            this.offsetX = parentComponent.getOffsetX();
            this.offsetY = parentComponent.getOffsetY() + (parentComponent.getHeight() - this.height) / 2;
            this.height = parentComponent.getHeight();
            break;
         case 4: // Center (fill parent)
            this.offsetX = parentComponent.getOffsetX();
            this.offsetY = parentComponent.getOffsetY();
            this.width = parentComponent.getWidth();
            this.height = parentComponent.getHeight();
            break;
         case 5: // Middle-right
            this.offsetX = parentComponent.getOffsetX() + (parentComponent.getWidth() - this.width);
            this.offsetY = parentComponent.getOffsetY() + (parentComponent.getHeight() - this.height) / 2;
            this.height = parentComponent.getHeight();
            break;
         case 6: // Bottom-left
            this.offsetX = parentComponent.getOffsetX();
            this.offsetY = parentComponent.getOffsetY() + (parentComponent.getHeight() - this.height);
            break;
         case 7: // Bottom-center
            this.offsetX = parentComponent.getOffsetX() + (parentComponent.getWidth() - this.width) / 2;
            this.offsetY = parentComponent.getOffsetY() + (parentComponent.getHeight() - this.height);
            this.width = parentComponent.getWidth();
            break;
         case 8: // Bottom-right
            this.offsetX = parentComponent.getOffsetX() + (parentComponent.getWidth() - this.width);
            this.offsetY = parentComponent.getOffsetY() + (parentComponent.getHeight() - this.height);
            break;
         default:
            // No alignment (alignmentMode == 9 or other)
            break;
      }
   }

   /**
    * Update alignment after position/size changes
    * @param rootComponent Root component for calculations
    */
   private void updateAlignment(IComponent rootComponent) {
      this.setActiveComponent(rootComponent);
   }

   /**
    * Set the alignment mode for this container
    * @param alignment Alignment mode (0-8, 9 for no alignment)
    */
   public final void setAlignmentMode(byte alignment) {
      this.alignmentMode = alignment;
   }

   /**
    * Get the current alignment mode
    * @return Alignment mode
    */
   public final byte getAlignmentMode() {
      return this.alignmentMode;
   }

   /**
    * Add a child component to this container
    * @param child Component to add
    * @return true if successfully added
    */
   public final boolean addChildComponent(IComponent child) {
      for (int i = 0; i < this.childComponents.length; i++) {
         if (this.childComponents[i] == null) {
            this.childComponents[i] = child;
            return true;
         }
      }
      return false; // No space available
   }

   /**
    * Remove a child component from this container
    * @param child Component to remove
    * @return true if successfully removed
    */
   public final boolean removeChildComponent(IComponent child) {
      for (int i = 0; i < this.childComponents.length; i++) {
         if (this.childComponents[i] == child) {
            this.childComponents[i] = null;
            // Shift remaining components to fill gap
            for (int j = i; j < this.childComponents.length - 1; j++) {
               this.childComponents[j] = this.childComponents[j + 1];
            }
            this.childComponents[this.childComponents.length - 1] = null;
            return true;
         }
      }
      return false;
   }

   /**
    * Get the number of child components
    * @return Count of non-null child components
    */
   public final int getChildCount() {
      int count = 0;
      for (int i = 0; i < this.childComponents.length && this.childComponents[i] != null; i++) {
         count++;
      }
      return count;
   }

   /**
    * Clean up all resources used by this container
    */
   public final void cleanUp() {
      // Clean up list components
      if (this.secondaryListComponent != null) {
         this.secondaryListComponent.cleanup();
         this.secondaryListComponent = null;
      }

      if (this.primaryListComponent != null) {
         this.primaryListComponent.cleanup();
         this.primaryListComponent = null;
      }

      // Clean up child components
      if (this.childComponents != null) {
         for (int i = 0; i < this.childComponents.length; i++) {
            if (this.childComponents[i] != null) {
               this.childComponents[i].cleanUp();
               this.childComponents[i] = null;
            }
         }
         this.childComponents = null;
      }

      // Clean up attached list component
      if (this.attachedListComponent != null) {
         this.attachedListComponent = null;
      }

      // Clean up action mappings
      if (this.actionMappings != null) {
         this.actionMappings = null;
      }

      // Clean up component data
      if (this.componentData != null) {
         this.componentData.cleanup();
         this.componentData = null;
      }
   }
}