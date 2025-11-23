package layout;

import engine.GameUtils;

import javax.microedition.lcdui.Graphics;
import java.util.Vector;

/**
 * A scrollable list component that can display and manage a collection of child components
 */
public final class ScrollableListComponent {
    // Core list properties
    public int totalItemCount;                    // Total number of items in the list
    public int[] itemIds;                        // Array of item IDs
    public int[][][] actionMappings;             // Action mappings for different states
    public int visibleItemCount;                 // Number of visible items at once

    // Layout and positioning
    private Rectangle[] itemBounds;              // Bounds for each visible item
    public int scrollOffset;                     // Current scroll offset
    public int selectedIndex;                    // Currently selected item index
    public boolean isWrapEnabled;                // Whether selection wraps around

    // Scrolling behavior
    public int scrollMode;                       // Scrolling mode (0=normal, 1=circular)
    public int centerOffset;                     // Offset for centering items
    public int layoutMode;                       // Layout arrangement mode
    public int spacing;                          // Spacing between items
    public int itemHeight;                       // Height of each item
    public int backgroundColor;                  // Background color

    // Internal state
    private int displayMode;                     // How items are displayed (-1=scrolling, 1=static)
    public int[][] layoutGrid;                   // Grid layout information
    private int animationState;                  // Current animation state
    private Vector cachedItems;                  // Cached item data
    public Vector itemTexts;                     // Text content for items

    /**
     * Creates a new scrollable list component
     *
     * @param layoutMode The layout arrangement mode for this component
     */
    public ScrollableListComponent(final int layoutMode) {
        // Initialize scrolling properties
        this.scrollMode = 0;
        this.centerOffset = 0;
        this.spacing = 0;
        this.itemHeight = 10;
        this.backgroundColor = 2;
        this.displayMode = -1;
        this.layoutGrid = null;
        this.animationState = -1;
        this.cachedItems = new Vector();
        this.itemTexts = new Vector();

        // Initialize core list properties
        this.totalItemCount = 0;
        this.itemIds = GameUtils.initializeArrayWithMinusOne(50);
        this.visibleItemCount = 0;
        this.itemBounds = new Rectangle[20];
        this.scrollOffset = 0;
        this.selectedIndex = 0;
        this.isWrapEnabled = true;
        this.layoutMode = layoutMode;
        this.displayMode = -1;
    }

    /**
     * Move selection forward by specified number of steps
     *
     * @param steps           Number of steps to move forward
     * @param parentComponent Parent component for layout updates
     */
    public final void moveSelectionForward(final int steps, final IComponent parentComponent) {
        if (this.totalItemCount <= 1) {
            this.selectedIndex = 0;
            this.scrollOffset = 0;
            return;
        }

        if (this.isWrapEnabled) {
            if (this.scrollMode == 0) {
                this.selectedIndex += steps;
                if (this.selectedIndex >= this.totalItemCount) {
                    this.selectedIndex %= this.totalItemCount;
                    if (this.selectedIndex >= this.scrollOffset + this.visibleItemCount || this.selectedIndex < this.scrollOffset) {
                        this.scrollOffset = this.selectedIndex;
                    }
                    this.updateLayout(parentComponent);
                    return;
                }
                if (this.selectedIndex >= this.scrollOffset + this.visibleItemCount) {
                    this.scrollOffset += steps;
                    if (this.scrollOffset + this.visibleItemCount >= this.totalItemCount) {
                        this.scrollOffset = this.totalItemCount - this.visibleItemCount;
                    }
                    this.updateLayout(parentComponent);
                }
            } else if (this.scrollMode == 1) {
                this.selectedIndex += steps;
                if (this.selectedIndex >= this.totalItemCount) {
                    this.selectedIndex %= this.totalItemCount;
                }
                this.scrollOffset = ((this.selectedIndex - this.centerOffset < 0) ?
                        (this.totalItemCount + (this.selectedIndex - this.centerOffset)) :
                        (this.selectedIndex - this.centerOffset));
                this.updateLayout(parentComponent);
            }
        } else {
            this.selectedIndex += steps;
            if (this.selectedIndex >= this.totalItemCount) {
                this.selectedIndex = this.totalItemCount - 1;
                if (this.totalItemCount >= this.visibleItemCount) {
                    this.scrollOffset = this.totalItemCount - this.visibleItemCount;
                }
                this.updateLayout(parentComponent);
                return;
            }
            if (this.selectedIndex >= this.scrollOffset + this.visibleItemCount) {
                this.scrollOffset += steps;
                this.updateLayout(parentComponent);
            }
        }
    }

    /**
     * Move selection backward by specified number of steps
     *
     * @param steps           Number of steps to move backward
     * @param parentComponent Parent component for layout updates
     */
    public final void moveSelectionBackward(final int steps, final IComponent parentComponent) {
        if (this.totalItemCount <= 1) {
            this.selectedIndex = 0;
            this.scrollOffset = 0;
            return;
        }

        if (this.isWrapEnabled) {
            if (this.scrollMode == 0) {
                this.selectedIndex -= steps;
                if (this.selectedIndex < 0) {
                    this.selectedIndex = this.totalItemCount + this.selectedIndex % this.totalItemCount;
                    if (this.selectedIndex >= this.scrollOffset + this.visibleItemCount || this.selectedIndex < this.scrollOffset) {
                        this.scrollOffset = this.totalItemCount - this.visibleItemCount - (this.totalItemCount - this.selectedIndex - 1);
                    }
                    this.updateLayout(parentComponent);
                    return;
                }
                if (this.selectedIndex < this.scrollOffset) {
                    this.scrollOffset = this.selectedIndex;
                    this.updateLayout(parentComponent);
                }
            } else if (this.scrollMode == 1) {
                this.selectedIndex -= steps;
                if (this.selectedIndex < 0) {
                    this.selectedIndex = this.totalItemCount + this.selectedIndex % this.totalItemCount;
                }
                this.scrollOffset = ((this.selectedIndex - this.centerOffset < 0) ?
                        (this.totalItemCount + (this.selectedIndex - this.centerOffset)) :
                        (this.selectedIndex - this.centerOffset));
                this.updateLayout(parentComponent);
            }
        } else {
            this.selectedIndex -= steps;
            if (this.selectedIndex < 0) {
                this.selectedIndex = 0;
                this.scrollOffset = 0;
                this.updateLayout(parentComponent);
                return;
            }
            if (this.selectedIndex < this.scrollOffset) {
                this.scrollOffset -= steps;
                this.updateLayout(parentComponent);
            }
        }
    }

    /**
     * Update the layout and refresh cached item data
     *
     * @param parentComponent Parent component for context
     */
    private void updateLayout(final IComponent parentComponent) {
        if (this.displayMode == 1) {
            if (this.animationState == 1) {
                for (int i = 0; i < this.visibleItemCount; ++i) {
                    this.cachedItems.elementAt((this.scrollOffset + i) % this.totalItemCount);
                }
                return;
            }
            if (this.animationState == 2) {
                for (int k = 0; k < this.visibleItemCount; ++k) {
                    this.cachedItems.elementAt((this.scrollOffset + k) % this.totalItemCount);
                }
            }
        }
    }

    /**
     * Render the scrollable list component
     *
     * @param graphics          Graphics context for rendering
     * @param activeComponentId ID of currently active component
     * @param isHighlighted     Whether this component is highlighted
     * @param activeIndices     Array of active component indices
     * @param isEnabled         Whether the component is enabled
     * @param rootComponent     Root component for coordinate calculations
     */
    public final void renderList(final Graphics graphics, final int activeComponentId, final boolean isHighlighted,
                                 final int[] activeIndices, final boolean isEnabled, final IComponent rootComponent) {
        if (this.itemBounds != null) {
            this.itemBounds = new Rectangle[20];
            for (int i = 0; i < this.visibleItemCount; ++i) {
                final IComponent childComponent = GameUtils.findChildById(rootComponent, this.itemIds[i]);
                this.itemBounds[i] = new Rectangle(childComponent.getOffsetX(), childComponent.getOffsetY(),
                        childComponent.getWidth(), childComponent.getHeight());
            }
        }

        int activeItemIndex = -1;
        for (int j = 0; j < this.itemIds.length && this.itemIds[j] != -1; ++j) {
            if (this.itemIds[j] == activeComponentId) {
                activeItemIndex = j;
                break;
            }
        }

        final int[] visibleIndices = GameUtils.initializeArrayWithMinusOne(50);
        if (this.displayMode == 1) {
            for (int k = 0; k < this.visibleItemCount; ++k) {
                visibleIndices[k] = k;
            }
        } else if (this.displayMode == -1) {
            for (int l = 0; l < this.visibleItemCount; ++l) {
                visibleIndices[l] = (this.scrollOffset + l) % this.itemIds.length;
            }
        }

        for (int m = 0; m < visibleIndices.length && visibleIndices[m] != -1; ++m) {
            if (activeItemIndex == visibleIndices[m]) {
                final IComponent targetComponent = GameUtils.findChildById(rootComponent, activeComponentId);
                final int deltaX = targetComponent.getOffsetX() - this.itemBounds[m].x;
                final int deltaY = targetComponent.getOffsetY() - this.itemBounds[m].y;
                final int originalWidth = targetComponent.getWidth();
                final int originalHeight = targetComponent.getHeight();

                // Temporarily adjust component bounds for rendering
                GameUtils.a(targetComponent, -deltaX, -deltaY, rootComponent);
                targetComponent.setWidth(this.itemBounds[m].width, rootComponent);
                targetComponent.setHeight(this.itemBounds[m].height, rootComponent);

                // Render with appropriate highlight state
                if (isHighlighted && this.totalItemCount > 0) {
                    if (this.displayMode == 1) {
                        if (this.selectedIndex == (this.scrollOffset + visibleIndices[m]) % this.totalItemCount) {
                            targetComponent.render(graphics, true, isEnabled, rootComponent, activeIndices);
                        } else {
                            targetComponent.render(graphics, false, isEnabled, rootComponent, activeIndices);
                        }
                    } else if (this.selectedIndex == visibleIndices[m]) {
                        targetComponent.render(graphics, true, isEnabled, rootComponent, activeIndices);
                    } else {
                        targetComponent.render(graphics, false, isEnabled, rootComponent, activeIndices);
                    }
                } else {
                    targetComponent.render(graphics, false, isEnabled, rootComponent, activeIndices);
                }

                // Restore original component bounds
                GameUtils.a(targetComponent, deltaX, deltaY, rootComponent);
                targetComponent.setWidth(originalWidth, rootComponent);
                targetComponent.setHeight(originalHeight, rootComponent);
            }
        }
    }

    /**
     * Update component state and child components
     *
     * @param activeComponentId ID of currently active component
     * @param activeIndices     Array of active component indices
     * @param isEnabled         Whether the component is enabled
     * @param rootComponent     Root component for coordinate calculations
     */
    public final void updateComponent(final int activeComponentId, final int[] activeIndices,
                                      final boolean isEnabled, final IComponent rootComponent) {
        if (this.itemBounds != null) {
            this.itemBounds = new Rectangle[20];
            for (int i = 0; i < this.visibleItemCount; ++i) {
                final IComponent childComponent = GameUtils.findChildById(rootComponent, this.itemIds[i]);
                this.itemBounds[i] = new Rectangle(childComponent.getOffsetX(), childComponent.getOffsetY(),
                        childComponent.getWidth(), childComponent.getHeight());
            }
        }

        int activeItemIndex = -1;
        for (int j = 0; j < this.itemIds.length && this.itemIds[j] != -1; ++j) {
            if (this.itemIds[j] == activeComponentId) {
                activeItemIndex = j;
                break;
            }
        }

        final int[] visibleIndices = GameUtils.initializeArrayWithMinusOne(50);
        if (this.displayMode == 1) {
            for (int k = 0; k < this.visibleItemCount; ++k) {
                visibleIndices[k] = k;
            }
        } else if (this.displayMode == -1) {
            for (int l = 0; l < this.visibleItemCount; ++l) {
                visibleIndices[l] = (this.scrollOffset + l) % this.itemIds.length;
            }
        }

        for (int m = 0; m < visibleIndices.length && visibleIndices[m] != -1; ++m) {
            if (activeItemIndex == visibleIndices[m]) {
                final IComponent targetComponent = GameUtils.findChildById(rootComponent, activeComponentId);
                final int deltaX = targetComponent.getOffsetX() - this.itemBounds[m].x;
                final int deltaY = targetComponent.getOffsetY() - this.itemBounds[m].y;
                final int originalWidth = targetComponent.getWidth();
                final int originalHeight = targetComponent.getHeight();

                // Temporarily adjust component bounds for update
                GameUtils.a(targetComponent, -deltaX, -deltaY, rootComponent);
                targetComponent.setWidth(this.itemBounds[m].width, rootComponent);
                targetComponent.setHeight(this.itemBounds[m].height, rootComponent);

                // Update the component
                targetComponent.update(isEnabled, isEnabled, rootComponent, activeIndices);

                // Restore original component bounds
                GameUtils.a(targetComponent, deltaX, deltaY, rootComponent);
                targetComponent.setWidth(originalWidth, rootComponent);
                targetComponent.setHeight(originalHeight, rootComponent);
            }
        }
    }

    /**
     * Clean up resources used by this component
     */
    public final void cleanup() {
        if (this.itemTexts != null) {
            this.itemTexts = null;
        }
        if (this.itemIds != null) {
            this.itemIds = null;
        }
        if (this.actionMappings != null) {
            this.actionMappings = null;
        }
        if (this.cachedItems != null) {
            this.cachedItems = null;
        }
        if (this.itemBounds != null) {
            this.itemBounds = null;
        }
    }

    /**
     * Set the display mode for this component
     *
     * @param displayMode Display mode (-1 for scrolling, 1 for static, other values default to scrolling)
     */
    public final void setDisplayMode(final int displayMode) {
        if (displayMode != 1 && displayMode != -1) {
            this.displayMode = -1;
            return;
        }
        this.displayMode = displayMode;
    }
}