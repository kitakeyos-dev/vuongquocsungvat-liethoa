package layout;

import a.GameUtils;
import javax.microedition.lcdui.Graphics;

/**
 * Dialog Component - UI component for rendering dialogs with flexible positioning
 * Supports 9 different anchor positions relative to other components
 */
public final class DialogComponent implements IComponent {

   // Component positioning and selection
   private int selectedComponentId = -1;
   private int offsetX = 0;
   private int offsetY = 0;
   private int width = 0;
   private int height = 0;
   private int zIndex = 1;

   // Component data and configuration
   private ComponentData componentData = new ComponentData();
   private int activeComponentId = -1;
   private byte anchorPosition = 9; // 9 = no anchoring
   private ScrollableListComponent childScrollableListComponent = null;
   private IComponent[] childComponents = null;

   // Rendering states
   public byte focusedState = -1;
   public byte enabledState = -1;
   TextRenderer textRenderer;
   private boolean isVisible = true;

   // Anchor position constants
   public static final byte ANCHOR_TOP_LEFT = 0;
   public static final byte ANCHOR_TOP_CENTER = 1;
   public static final byte ANCHOR_TOP_RIGHT = 2;
   public static final byte ANCHOR_MIDDLE_LEFT = 3;
   public static final byte ANCHOR_MIDDLE_CENTER = 4;
   public static final byte ANCHOR_MIDDLE_RIGHT = 5;
   public static final byte ANCHOR_BOTTOM_LEFT = 6;
   public static final byte ANCHOR_BOTTOM_CENTER = 7;
   public static final byte ANCHOR_BOTTOM_RIGHT = 8;
   public static final byte ANCHOR_NONE = 9;

   /**
    * Constructor - creates dialog component with default settings
    */
   public DialogComponent() {
      this.isVisible = true;
   }

   /**
    * Initialize dialog data with current bounds
    */
   public final void initializeDialog() {
      Rectangle bounds = new Rectangle(this.offsetX, this.offsetY, this.width, this.height);
      this.componentData.setBounds(bounds);
      this.componentData.resetScrollPosition();
   }

   /**
    * Render the dialog component
    */
   @Override
   public final void render(Graphics graphics, boolean isFocused, boolean isEnabled, IComponent parentComponent, int[] renderParams) {
      if (this.isVisible) {
         if (this.componentData != null) {
            this.componentData.render(graphics, this.offsetX, this.offsetY, this.width, this.height,
                    isFocused, this.focusedState, this.enabledState, this.textRenderer);
         }
      }
   }

   /**
    * Update dialog component state
    */
   @Override
   public final void update(boolean isFocused, boolean isEnabled, IComponent parentComponent, int[] updateParams) {
      if (this.componentData != null) {
         ComponentData data = this.componentData;
         if (isFocused) {
            if (data.focusedRenderer != null) {
               data.focusedRenderer.update();
            }
         } else if (data.renderer != null) {
            data.renderer.update();
         }
      }
   }

   // === VISIBILITY ===

   @Override
   public final void setVisible(boolean visible) {
      this.isVisible = visible;
   }

   // === SELECTION MANAGEMENT ===

   @Override
   public final int getSelectedComponentId() {
      return this.selectedComponentId;
   }

   public final void setSelectedComponentId(int componentId) {
      this.selectedComponentId = componentId;
   }

   // === POSITION MANAGEMENT ===

   @Override
   public final int getOffsetX() {
      return this.offsetX;
   }

   @Override
   public final void setOffsetX(int xOffset, IComponent parentComponent) {
      this.offsetX = xOffset;
      this.updateAnchoredPosition(parentComponent);
   }

   @Override
   public final int getOffsetY() {
      return this.offsetY;
   }

   @Override
   public final void setOffsetY(int yOffset, IComponent parentComponent) {
      this.offsetY = yOffset;
      this.updateAnchoredPosition(parentComponent);
   }

   // === SIZE MANAGEMENT ===

   @Override
   public final int getWidth() {
      return this.width;
   }

   @Override
   public final void setWidth(int newWidth, IComponent parentComponent) {
      this.width = newWidth;
      this.updateAnchoredPosition(parentComponent);
   }

   @Override
   public final int getHeight() {
      return this.height;
   }

   @Override
   public final void setHeight(int newHeight, IComponent parentComponent) {
      this.height = newHeight;
      this.updateAnchoredPosition(parentComponent);
   }

   // === CHILD COMPONENT MANAGEMENT ===

   @Override
   public final ScrollableListComponent getChildComponent() {
      return this.childScrollableListComponent;
   }

   public final void setChildComponent(ScrollableListComponent child) {
      this.childScrollableListComponent = child;
   }

   @Override
   public final IComponent[] getComponents() {
      return null; // Dialog components typically don't have multiple children
   }

   // === DATA MANAGEMENT ===

   @Override
   public final ComponentData getComponentData() {
      return this.componentData;
   }

   @Override
   public final void setComponentData(ComponentData data) {
      this.componentData = data;
   }

   // === Z-INDEX MANAGEMENT ===

   @Override
   public final int getZIndex() {
      return this.zIndex;
   }

   public final void setZIndex(int index) {
      this.zIndex = index;
   }

   // === ACTIVE COMPONENT MANAGEMENT ===

   @Override
   public final int getActiveComponentId() {
      return this.activeComponentId;
   }

   public final void setActiveComponentId(int componentId) {
      this.activeComponentId = componentId;
   }

   /**
    * Set anchor position for dialog positioning
    * @param position Anchor position (0-8 for specific positions, 9 for no anchoring)
    */
   public final void setAnchorPosition(byte position) {
      this.anchorPosition = position;
   }

   /**
    * Get current anchor position
    * @return Anchor position value
    */
   public final byte getAnchorPosition() {
      return this.anchorPosition;
   }

   /**
    * Update position based on anchor settings
    */
   private final void updateAnchoredPosition(IComponent anchorComponent) {
      this.setActiveComponent(anchorComponent);
   }

   /**
    * Set active component and update position based on anchor
    */
   @Override
   public final void setActiveComponent(IComponent targetComponent) {
      if (targetComponent != null && this.activeComponentId > 0 && this.anchorPosition != ANCHOR_NONE) {
         IComponent anchorTarget = GameUtils.findChildById(targetComponent, this.activeComponentId);

         if (anchorTarget != null) {
            switch (this.anchorPosition) {
               case ANCHOR_TOP_LEFT:
                  // Position at top-left corner of target
                  this.offsetX = anchorTarget.getOffsetX();
                  this.offsetY = anchorTarget.getOffsetY();
                  break;

               case ANCHOR_TOP_CENTER:
                  // Position at top-center of target
                  this.offsetX = anchorTarget.getOffsetX() + (anchorTarget.getWidth() - this.width) / 2;
                  this.offsetY = anchorTarget.getOffsetY();
                  this.width = anchorTarget.getWidth();
                  break;

               case ANCHOR_TOP_RIGHT:
                  // Position at top-right corner of target
                  this.offsetX = anchorTarget.getOffsetX() + (anchorTarget.getWidth() - this.width);
                  this.offsetY = anchorTarget.getOffsetY();
                  break;

               case ANCHOR_MIDDLE_LEFT:
                  // Position at middle-left of target
                  this.offsetX = anchorTarget.getOffsetX();
                  this.offsetY = anchorTarget.getOffsetY() + (anchorTarget.getHeight() - this.height) / 2;
                  this.height = anchorTarget.getHeight();
                  break;

               case ANCHOR_MIDDLE_CENTER:
                  // Position at center of target (fill entire area)
                  this.offsetX = anchorTarget.getOffsetX();
                  this.offsetY = anchorTarget.getOffsetY();
                  this.width = anchorTarget.getWidth();
                  this.height = anchorTarget.getHeight();
                  break;

               case ANCHOR_MIDDLE_RIGHT:
                  // Position at middle-right of target
                  this.offsetX = anchorTarget.getOffsetX() + (anchorTarget.getWidth() - this.width);
                  this.offsetY = anchorTarget.getOffsetY() + (anchorTarget.getHeight() - this.height) / 2;
                  this.height = anchorTarget.getHeight();
                  break;

               case ANCHOR_BOTTOM_LEFT:
                  // Position at bottom-left corner of target
                  this.offsetX = anchorTarget.getOffsetX();
                  this.offsetY = anchorTarget.getOffsetY() + (anchorTarget.getHeight() - this.height);
                  break;

               case ANCHOR_BOTTOM_CENTER:
                  // Position at bottom-center of target
                  this.offsetX = anchorTarget.getOffsetX() + (anchorTarget.getWidth() - this.width) / 2;
                  this.offsetY = anchorTarget.getOffsetY() + (anchorTarget.getHeight() - this.height);
                  this.width = anchorTarget.getWidth();
                  break;

               case ANCHOR_BOTTOM_RIGHT:
                  // Position at bottom-right corner of target
                  this.offsetX = anchorTarget.getOffsetX() + (anchorTarget.getWidth() - this.width);
                  this.offsetY = anchorTarget.getOffsetY() + (anchorTarget.getHeight() - this.height);
                  break;

               case ANCHOR_NONE:
               default:
                  // No anchoring - keep current position
                  break;
            }
         }
      }
   }

   /**
    * Set dialog configuration
    * @param config Dialog configuration object
    */
   public final void setDialogConfig(TextRenderer config) {
      this.textRenderer = config;
   }

   /**
    * Get dialog configuration
    * @return Dialog configuration object
    */
   public final TextRenderer getDialogConfig() {
      return this.textRenderer;
   }

   /**
    * Set focused state for rendering
    * @param state Focused state value
    */
   public final void setFocusedState(byte state) {
      this.focusedState = state;
   }

   /**
    * Set enabled state for rendering
    * @param state Enabled state value
    */
   public final void setEnabledState(byte state) {
      this.enabledState = state;
   }

   /**
    * Check if dialog is visible
    * @return true if visible
    */
   public final boolean isVisible() {
      return this.isVisible;
   }

   // === CLEANUP ===

   @Override
   public final void cleanUp() {
      if (this.childScrollableListComponent != null) {
         this.childScrollableListComponent = null;
      }

      if (this.componentData != null) {
         this.componentData.cleanup();
         this.componentData = null;
      }

      if (this.textRenderer != null) {
         this.textRenderer = null;
      }
   }

   /**
    * Get string representation for debugging
    * @return String description of dialog state
    */
   public String toString() {
      return "DialogComponent{" +
              "position=(" + offsetX + "," + offsetY + "), " +
              "size=(" + width + "x" + height + "), " +
              "anchor=" + anchorPosition + ", " +
              "visible=" + isVisible + ", " +
              "zIndex=" + zIndex + "}";
   }
}