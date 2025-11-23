package layout;

import engine.GameUtils;
import javax.microedition.lcdui.Graphics;

/**
 * Main dialog system that manages UI components and handles user input
 */
public final class DialogSystem {
    // Component management
    private IComponent[] childComponents = new IComponent[200];  // All child components
    private int childCount = 0;                                 // Number of active children
    private int currentDialogIndex = -1;                        // Current dialog index
    private UIContainerComponent uiContainerComponent;                        // Root UI component

    // Input handling
    private int[] allowedActions = new int[]{0, 1, 2, 3, 5, 6, 7, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private int[] navigationIndices = null;                     // Navigation path indices
    private int[] activeComponentIndices = null;                // Currently active component indices

    // Configuration and event handling
    private TextRenderer textRenderer;                          // Text rendering configuration
    private DialogHandler eventHandler;                         // Event handler for dialog actions

    /**
     * Create a new dialog system with the specified event handler
     * @param eventHandler Handler for dialog events
     */
    public DialogSystem(DialogHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.resetDialog();
    }

    /**
     * Reset dialog to initial state
     */
    private void resetDialog() {
        this.currentDialogIndex = -1;
        this.uiContainerComponent = new UIContainerComponent();
        this.uiContainerComponent.setSelectedComponentId(0);
        this.uiContainerComponent.setActiveComponentId(-1);
        this.navigationIndices = GameUtils.initializeArrayWithMinusOne(100);
        this.initializeActiveIndices();
    }

    /**
     * Get the root container of this dialog
     * @return Root container
     */
    public final UIContainerComponent getUIContainerComponent() {
        return this.uiContainerComponent;
    }

    /**
     * Set the text renderer configuration
     * @param textRenderer Text rendering configuration
     */
    public final void setTextRenderer(TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
    }

    /**
     * Load dialog from data file
     * @param dialogId Dialog resource identifier
     * @param resourceType Resource type parameter
     * @param enableAnimations Whether to enable animations
     */
    public final void loadDialog(String dialogId, int resourceType, boolean enableAnimations) {
        this.resetDialog();
        byte[] dialogData = new byte[20000];
        GameUtils.readBytesFromStream(dialogData, dialogId, 0);
        int[] readPosition = new int[]{0};

        // Read dialog header
        this.currentDialogIndex = GameUtils.readShortFromBytes(dialogData, readPosition);
        this.currentDialogIndex = 0;
        GameUtils.readShortFromBytes(dialogData, readPosition);

        // Configure root component
        byte zIndex = GameUtils.readByte(dialogData, readPosition);
        this.uiContainerComponent.setZIndex(zIndex);

        short selectedId = GameUtils.readShortFromBytes(dialogData, readPosition);
        this.uiContainerComponent.setSelectedComponentId(selectedId);

        short offsetX = GameUtils.readShortFromBytes(dialogData, readPosition);
        this.uiContainerComponent.setOffsetX(offsetX, this.uiContainerComponent);

        short offsetY = GameUtils.readShortFromBytes(dialogData, readPosition);
        this.uiContainerComponent.setOffsetY(offsetY, this.uiContainerComponent);

        short width = GameUtils.readShortFromBytes(dialogData, readPosition);
        this.uiContainerComponent.setWidth(width, this.uiContainerComponent);

        short height = GameUtils.readShortFromBytes(dialogData, readPosition);
        this.uiContainerComponent.setHeight(height, this.uiContainerComponent);

        this.childComponents[this.childCount] = this.uiContainerComponent;
        this.childCount = 1;

        // Parse dialog component data
        this.parseDialogData(dialogData, readPosition, this.uiContainerComponent, resourceType, enableAnimations);
        this.navigationIndices = GameUtils.initializeArrayWithMinusOne(50);
        this.activateComponent(this.uiContainerComponent, -1);
    }

    /**
     * Parse dialog data and create components
     * @param data Raw dialog data
     * @param readPosition Current read position in data
     * @param parentComponent Parent component to attach children to
     * @param resourceType Resource type for sprite loading
     * @param enableAnimations Whether animations are enabled
     */
    private void parseDialogData(byte[] data, int[] readPosition, UIContainerComponent parentComponent,
                                 int resourceType, boolean enableAnimations) {
        // Parse action mappings
        byte actionCount = GameUtils.readByte(data, readPosition);
        if (actionCount > 0) {
            byte[][] actionMappings = new byte[actionCount][4];
            for (int i = 0; i < actionCount; i++) {
                actionMappings[i][0] = GameUtils.readByte(data, readPosition);
                actionMappings[i][1] = GameUtils.readByte(data, readPosition);
                actionMappings[i][2] = GameUtils.readByte(data, readPosition);
                actionMappings[i][3] = GameUtils.readByte(data, readPosition);
            }
            parentComponent.setActionMappings(actionMappings);
        }

        // Parse scrollable list components
        byte componentCount = GameUtils.readByte(data, readPosition);
        for (int i = 0; i < componentCount; i++) {
            byte componentType = GameUtils.readByte(data, readPosition);
            ScrollableListComponent listComponent = new ScrollableListComponent(componentType);

            // Read component properties
            listComponent.isWrapEnabled = GameUtils.readByte(data, readPosition) != 0;
            listComponent.visibleItemCount = GameUtils.readShortFromBytes(data, readPosition);
            listComponent.totalItemCount = GameUtils.readShortFromBytes(data, readPosition);
            listComponent.scrollMode = GameUtils.readByte(data, readPosition);
            listComponent.centerOffset = GameUtils.readByte(data, readPosition);

            // Read item IDs and text content
            for (int j = 0; j < listComponent.totalItemCount; j++) {
                short itemId = GameUtils.readShortFromBytes(data, readPosition);
                listComponent.itemIds[j] = itemId;

                short textLength = GameUtils.readShortFromBytes(data, readPosition);
                byte[] textData = new byte[textLength];
                for (int k = 0; k < textLength; k++) {
                    textData[k] = GameUtils.readByte(data, readPosition);
                }
                listComponent.itemTexts.addElement(GameUtils.a(textData));
            }

            // Read action mappings for this component
            listComponent.actionMappings = new int[listComponent.visibleItemCount + this.allowedActions.length][][];
            for (int j = 0; j < listComponent.actionMappings.length; j++) {
                listComponent.actionMappings[j] = new int[0][];
            }

            short mappingCount = GameUtils.readShortFromBytes(data, readPosition);
            for (int j = 0; j < mappingCount; j++) {
                short actionIndex = GameUtils.readShortFromBytes(data, readPosition);
                short stateCount = GameUtils.readShortFromBytes(data, readPosition);
                int[][] stateData = new int[stateCount][];

                for (int k = 0; k < stateCount; k++) {
                    stateData[k] = new int[5];
                    stateData[k][0] = GameUtils.readShortFromBytes(data, readPosition);
                    stateData[k][1] = GameUtils.readShortFromBytes(data, readPosition);
                    stateData[k][2] = GameUtils.readShortFromBytes(data, readPosition);
                    stateData[k][3] = GameUtils.readShortFromBytes(data, readPosition);
                    stateData[k][4] = GameUtils.readShortFromBytes(data, readPosition);
                }
                listComponent.actionMappings[actionIndex] = stateData;
            }

            // Assign component to parent
            if (componentType == 0) {
                parentComponent.primaryListComponent = listComponent;
            } else {
                parentComponent.secondaryListComponent = listComponent;
            }
        }

        // Parse child components recursively
        short childCount = GameUtils.readShortFromBytes(data, readPosition);
        for (int i = 0; i < childCount; i++) {
            byte childType = GameUtils.readByte(data, readPosition);

            if (childType == 0) {
                // Create nested container component
                UIContainerComponent childContainer = new UIContainerComponent();
                childContainer.setZIndex(childType);
                childContainer.setSelectedComponentId(GameUtils.readShortFromBytes(data, readPosition));
                childContainer.setOffsetX(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                childContainer.setOffsetY(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                childContainer.setWidth(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                childContainer.setHeight(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                childContainer.setActiveComponentId(parentComponent.getSelectedComponentId());

                // Link with list components if applicable
                if (parentComponent.primaryListComponent != null) {
                    for (int j = 0; j < parentComponent.primaryListComponent.itemIds.length; j++) {
                        if (parentComponent.primaryListComponent.itemIds[j] == childContainer.getSelectedComponentId()) {
                            childContainer.setChildComponent(parentComponent.primaryListComponent);
                            break;
                        }
                    }
                }

                if (parentComponent.secondaryListComponent != null) {
                    for (int j = 0; j < parentComponent.secondaryListComponent.itemIds.length; j++) {
                        if (parentComponent.secondaryListComponent.itemIds[j] == childContainer.getSelectedComponentId()) {
                            childContainer.setChildComponent(parentComponent.secondaryListComponent);
                            break;
                        }
                    }
                }

                this.childComponents[this.childCount] = childContainer;
                this.childCount++;
                parentComponent.getComponents()[i] = childContainer;
                this.parseDialogData(data, readPosition, (UIContainerComponent) parentComponent.getComponents()[i], resourceType, enableAnimations);

            } else if (childType == 1) {
                // Create text-based dialog component
                DialogComponent textComponent = new DialogComponent();
                textComponent.setZIndex(childType);
                textComponent.setSelectedComponentId(GameUtils.readShortFromBytes(data, readPosition));
                textComponent.setOffsetX(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                textComponent.setOffsetY(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                textComponent.setWidth(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                textComponent.setHeight(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                textComponent.initializeDialog();
                textComponent.textRenderer = this.textRenderer;

                // Read text content
                short textLength = GameUtils.readShortFromBytes(data, readPosition);
                byte[] textData = new byte[textLength];
                for (int j = 0; j < textLength; j++) {
                    textData[j] = GameUtils.readByte(data, readPosition);
                }
                textComponent.getComponentData().text = GameUtils.a(textData);

                // Read text formatting properties
                textComponent.getComponentData().paddingHorizontal = GameUtils.readByte(data, readPosition);
                textComponent.getComponentData().paddingVertical = GameUtils.readByte(data, readPosition);
                textComponent.getComponentData().isScrollingEnabled = GameUtils.readByte(data, readPosition) != 0;
                textComponent.getComponentData().focusedBackgroundColor = GameUtils.e(data, readPosition);
                textComponent.getComponentData().focusedBorderColor = GameUtils.e(data, readPosition);
                textComponent.getComponentData().focusedTextColor = GameUtils.e(data, readPosition);

                // Read focused sprite
                short focusedSpriteIndex = GameUtils.readShortFromBytes(data, readPosition);
                byte focusedSpriteType = GameUtils.readByte(data, readPosition);
                if (focusedSpriteIndex < 0) {
                    textComponent.getComponentData().focusedRenderer = null;
                } else {
                    textComponent.getComponentData().focusedRenderer = new SpriteRenderer();
                    textComponent.getComponentData().focusedRenderer.spriteType = focusedSpriteType;
                    textComponent.getComponentData().focusedRenderer.setSpriteIndex((int) focusedSpriteIndex);
                }

                // Read normal state colors
                textComponent.getComponentData().backgroundColor = GameUtils.e(data, readPosition);
                textComponent.getComponentData().borderColor = GameUtils.e(data, readPosition);
                textComponent.getComponentData().textColor = GameUtils.e(data, readPosition);

                // Read normal sprite
                short normalSpriteIndex = GameUtils.readShortFromBytes(data, readPosition);
                byte normalSpriteType = GameUtils.readByte(data, readPosition);
                if (normalSpriteIndex < 0) {
                    textComponent.getComponentData().renderer = null;
                } else {
                    textComponent.getComponentData().renderer = new SpriteRenderer();
                    textComponent.getComponentData().renderer.setSpriteIndex((int) normalSpriteIndex);
                    textComponent.getComponentData().renderer.spriteType = normalSpriteType;
                }

                textComponent.getComponentData().spriteBlendMode = GameUtils.readByte(data, readPosition);

                // Initialize sprites
                if (textComponent.getComponentData().focusedRenderer != null) {
                    textComponent.getComponentData().focusedRenderer.initializeSprite(resourceType, enableAnimations, textComponent.getComponentData().spriteBlendMode);
                }
                if (textComponent.getComponentData().renderer != null) {
                    textComponent.getComponentData().renderer.initializeSprite(resourceType, enableAnimations, textComponent.getComponentData().spriteBlendMode);
                }

                textComponent.setActiveComponentId(parentComponent.getSelectedComponentId());

                // Link with list components if applicable
                if (parentComponent.primaryListComponent != null) {
                    for (int j = 0; j < parentComponent.primaryListComponent.itemIds.length; j++) {
                        if (parentComponent.primaryListComponent.itemIds[j] == textComponent.getSelectedComponentId()) {
                            textComponent.setChildComponent(parentComponent.primaryListComponent);
                            break;
                        }
                    }
                }

                if (parentComponent.secondaryListComponent != null) {
                    for (int j = 0; j < parentComponent.secondaryListComponent.itemIds.length; j++) {
                        if (parentComponent.secondaryListComponent.itemIds[j] == textComponent.getSelectedComponentId()) {
                            textComponent.setChildComponent(parentComponent.secondaryListComponent);
                            break;
                        }
                    }
                }

                this.childComponents[this.childCount] = textComponent;
                this.childCount++;
                parentComponent.getComponents()[i] = textComponent;
                textComponent.focusedState = GameUtils.readByte(data, readPosition);
                textComponent.enabledState = GameUtils.readByte(data, readPosition);

            } else if (childType == 2) {
                // Create grid component
                GridComponent gridComponent = new GridComponent();
                gridComponent.setSelectedComponentId(GameUtils.readShortFromBytes(data, readPosition));
                gridComponent.setOffsetX(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                gridComponent.setOffsetY(GameUtils.readShortFromBytes(data, readPosition), this.getUIContainerComponent());
                gridComponent.setCellWidth((int) GameUtils.readByte(data, readPosition));
                gridComponent.setCellHeight(GameUtils.readByte(data, readPosition));
                gridComponent.setCellSpacingX(GameUtils.readByte(data, readPosition));
                gridComponent.setCellSpacingY(GameUtils.readByte(data, readPosition));
                gridComponent.setTotalColumns(GameUtils.readByte(data, readPosition));
                gridComponent.setTotalRows(GameUtils.readByte(data, readPosition));
                gridComponent.setVisibleColumns(GameUtils.readByte(data, readPosition));
                gridComponent.setVisibleRows(GameUtils.readByte(data, readPosition));
                gridComponent.setCursorOffsetX(GameUtils.readByte(data, readPosition));
                gridComponent.setCursorOffsetY(GameUtils.readByte(data, readPosition));
                gridComponent.setHorizontalWrapMode(GameUtils.readByte(data, readPosition));
                gridComponent.setHorizontalScrollMode(GameUtils.readByte(data, readPosition));
                gridComponent.setVerticalWrapMode(GameUtils.readByte(data, readPosition));
                gridComponent.setVerticalScrollMode(GameUtils.readByte(data, readPosition));
                gridComponent.setScrollOffsetX(GameUtils.readByte(data, readPosition));
                gridComponent.setScrollOffsetY(GameUtils.readByte(data, readPosition));
                gridComponent.backgroundColor = GameUtils.e(data, readPosition);

                // Read background sprite
                short backgroundSpriteIndex = GameUtils.readShortFromBytes(data, readPosition);
                byte backgroundSpriteType = GameUtils.readByte(data, readPosition);
                if (backgroundSpriteIndex < 0) {
                    gridComponent.backgroundRenderer = null;
                } else {
                    gridComponent.backgroundRenderer = new SpriteRenderer();
                    gridComponent.backgroundRenderer.setSpriteIndex((int) backgroundSpriteIndex);
                    gridComponent.backgroundRenderer.spriteType = backgroundSpriteType;
                    gridComponent.backgroundRenderer.initializeSprite(resourceType, enableAnimations, backgroundSpriteType);
                }

                // Read selection sprite
                short selectionSpriteIndex = GameUtils.readShortFromBytes(data, readPosition);
                byte selectionSpriteType = GameUtils.readByte(data, readPosition);
                if (selectionSpriteIndex < 0) {
                    gridComponent.selectionRenderer = null;
                } else {
                    gridComponent.selectionRenderer = new SpriteRenderer();
                    gridComponent.selectionRenderer.setSpriteIndex((int) selectionSpriteIndex);
                    gridComponent.selectionRenderer.spriteType = selectionSpriteType;
                    gridComponent.selectionRenderer.initializeSprite(resourceType, enableAnimations, selectionSpriteType);
                }

                gridComponent.setSelectedCellIndex(GameUtils.readShortFromBytes(data, readPosition));

                // Read grid data
                byte gridDataType = GameUtils.readByte(data, readPosition);
                if (gridDataType == 0) {
                    gridComponent.gridCells = gridComponent.createNavigationGrid();
                } else if (gridDataType == 1) {
                    gridComponent.gridCells = GridComponent.createEmptyGrid(gridComponent.getTotalColumns(), gridComponent.getTotalRows());
                    short cellCount = GameUtils.readShortFromBytes(data, readPosition);

                    for (int j = 0; j < cellCount; j++) {
                        short cellIndex = GameUtils.readShortFromBytes(data, readPosition);
                        short cellId = GameUtils.readShortFromBytes(data, readPosition);
                        byte cellType = GameUtils.readByte(data, readPosition);
                        short param1 = GameUtils.readShortFromBytes(data, readPosition);
                        short param2 = GameUtils.readShortFromBytes(data, readPosition);
                        short param3 = GameUtils.readShortFromBytes(data, readPosition);
                        short param4 = GameUtils.readShortFromBytes(data, readPosition);
                        gridComponent.gridCells[cellIndex] = new GridCell(cellId, cellType, param1, param2, param3, param4);
                    }
                }

                gridComponent.setActiveComponentId(parentComponent.getSelectedComponentId());
                this.childComponents[this.childCount] = gridComponent;
                this.childCount++;
                parentComponent.getComponents()[i] = gridComponent;
            }
        }
    }

    /**
     * Calculate the bounding rectangle for a component
     * @param component Component to calculate bounds for
     * @return Bounding rectangle
     */
    private Rectangle calculateBounds(IComponent component) {
        if (component.getZIndex() != 0) {
            return new Rectangle(component.getOffsetX(), component.getOffsetY(), component.getWidth(), component.getHeight());
        } else {
            UIContainerComponent rootComp = (UIContainerComponent) component;
            if (rootComp.getComponents() != null && rootComp.getComponents()[0] != null) {
                int minX = this.calculateBounds(rootComp.getComponents()[0]).x;
                int maxX = this.calculateBounds(rootComp.getComponents()[0]).x + this.calculateBounds(rootComp.getComponents()[0]).width;
                int minY = this.calculateBounds(rootComp.getComponents()[0]).y;
                int maxY = this.calculateBounds(rootComp.getComponents()[0]).y + this.calculateBounds(rootComp.getComponents()[0]).height;

                for (int i = 0; i < rootComp.getComponents().length && rootComp.getComponents()[i] != null; i++) {
                    Rectangle bounds = this.calculateBounds(rootComp.getComponents()[i]);
                    if (minX > bounds.x) {
                        minX = bounds.x;
                    }
                    if (maxX < bounds.x + bounds.width) {
                        maxX = bounds.x + bounds.width;
                    }
                    if (minY > bounds.y) {
                        minY = bounds.y;
                    }
                    if (maxY < bounds.y + bounds.height) {
                        maxY = bounds.y + bounds.height;
                    }
                }

                return new Rectangle(minX, minY, maxX - minX, maxY - minY);
            } else {
                return new Rectangle(component.getOffsetX(), component.getOffsetY(), component.getWidth(), component.getHeight());
            }
        }
    }

    /**
     * Render the dialog system
     * @param graphics Graphics context for rendering
     */
    public final void render(Graphics graphics) {
        UIContainerComponent root = this.uiContainerComponent;
        ScrollableListComponent activeList = null;

        // Determine which list component is active
        if (root.primaryListComponent != null || root.secondaryListComponent != null) {
            if (root.primaryListComponent == null && root.secondaryListComponent != null) {
                activeList = root.secondaryListComponent;
            } else if (root.primaryListComponent != null && root.secondaryListComponent == null) {
                activeList = root.primaryListComponent;
            }
        }

        // Render list background if needed
        if (activeList != null && activeList.visibleItemCount < activeList.totalItemCount) {
            int bgColor = activeList.backgroundColor;

            // Calculate list bounds
            int minX = 0, maxX = 0, minY = 0, maxY = 0;
            if (activeList.itemIds[0] != -1) {
                Rectangle firstBounds = this.calculateBounds(this.getChildById(activeList.itemIds[0]));
                minX = firstBounds.x;
                maxX = firstBounds.x + firstBounds.width;
                minY = firstBounds.y;
                maxY = firstBounds.y + firstBounds.height;

                for (int i = 1; i != activeList.visibleItemCount; i++) {
                    Rectangle bounds = this.calculateBounds(this.getChildById(activeList.itemIds[i]));
                    if (minX > bounds.x) {
                        minX = bounds.x;
                    }
                    if (maxX < bounds.x + bounds.width) {
                        maxX = bounds.x + bounds.width;
                    }
                    if (minY > bounds.y) {
                        minY = bounds.y;
                    }
                    if (maxY < bounds.y + bounds.height) {
                        maxY = bounds.y + bounds.height;
                    }
                }
            }

            Rectangle listBounds = new Rectangle(minX, minY, maxX - minX, maxY - minY);
            Rectangle scrollBar = new Rectangle();
            Rectangle scrollThumb = new Rectangle();

            if (activeList.layoutMode != 0) {
                bgColor = activeList.layoutMode;
            }

            // Draw list background
            graphics.setColor(255, 255, 255);
            graphics.fillRect(listBounds.x, listBounds.y, listBounds.width, listBounds.height);
            graphics.setColor(245, 222, 179);
            graphics.drawRect(listBounds.x, listBounds.y, listBounds.width, listBounds.height);
            graphics.setColor(95, 158, 160);
            graphics.fillRect(scrollThumb.x, scrollThumb.y, scrollThumb.width, scrollThumb.height);
        }

        // Render all child components
        for (int i = 0; i < root.getComponents().length && root.getComponents()[i] != null; i++) {
            if (root.getComponents()[i].getChildComponent() != null) {
                boolean isActive = false;
                int activeSlot = GameUtils.findFirstAvailableSlot(this.activeComponentIndices);
                if (activeSlot > 0 && this.activeComponentIndices[activeSlot - 1] == root.getSelectedComponentId()) {
                    isActive = true;
                }

                root.getComponents()[i].getChildComponent().renderList(graphics, root.getComponents()[i].getSelectedComponentId(),
                        isActive, this.activeComponentIndices, true, this.uiContainerComponent);
            } else {
                root.getComponents()[i].render(graphics, false, true, this.uiContainerComponent, this.activeComponentIndices);
            }
        }
    }

    /**
     * Close the dialog and perform cleanup
     */
    public final void closeDialog() {
        UIContainerComponent root = this.uiContainerComponent;

        for (int i = 0; i < root.getComponents().length && root.getComponents()[i] != null; i++) {
            if (root.getComponents()[i].getChildComponent() != null) {
                if (GameUtils.findFirstAvailableSlot(this.activeComponentIndices) > 0) {
                    root.getSelectedComponentId();
                }
                root.getComponents()[i].getChildComponent().updateComponent(root.getComponents()[i].getSelectedComponentId(),
                        this.activeComponentIndices, true, this.uiContainerComponent);
            } else {
                root.getComponents()[i].update(false, true, this.uiContainerComponent, this.activeComponentIndices);
            }
        }
    }

    /**
     * Find child component by ID
     * @param componentId ID to search for
     * @return Component with matching ID, or null if not found
     */
    public final IComponent getChildById(int componentId) {
        return GameUtils.findChildById(this.uiContainerComponent, componentId);
    }

    /**
     * Handle user input action
     * @param actionId Action identifier
     * @return true if action was handled successfully
     */
    public final boolean handleAction(int actionId) {
        IComponent activeComponent = this.findActiveComponent(this.uiContainerComponent, this.navigationIndices, 0);
        boolean actionHandled = false;

        if (activeComponent.getZIndex() == 2) {
            // Handle grid component input
            GridComponent grid = (GridComponent) activeComponent;
            switch (actionId) {
                case 0: // Up
                    actionHandled = grid.navigate((byte) 0);
                    this.eventHandler.action(new int[]{-1, -1, 0}, new int[]{-1, -1, -1, -1});
                    break;
                case 1: // Down
                    actionHandled = grid.navigate((byte) 1);
                    this.eventHandler.action(new int[]{-1, -1, 1}, new int[]{-1, -1, -1, -1});
                    break;
                case 2: // Left
                    actionHandled = grid.navigate((byte) 2);
                    this.eventHandler.action(new int[]{-1, -1, 2}, new int[]{-1, -1, -1, -1});
                    break;
                case 3: // Right
                    actionHandled = grid.navigate((byte) 3);
                    this.eventHandler.action(new int[]{-1, -1, 3}, new int[]{-1, -1, -1, -1});
                    break;
                case 5: // Select
                    this.eventHandler.action(new int[]{-1, -1, 4}, new int[]{-1, -1, -1, -1});
                    actionHandled = true;
                    break;
                case 7: // Back/Cancel
                    if (actionHandled = this.navigateBack()) {
                        grid.showSelection = false;
                        this.eventHandler.action(new int[]{-1, -1, 7}, new int[]{-1, -1, -1, -1});
                    } else {
                        this.eventHandler.action(new int[]{-1, -1, 5}, new int[]{-1, -1, -1, -1});
                    }
                    break;
            }
        } else {
            // Handle container component input
            UIContainerComponent container = (UIContainerComponent) activeComponent;
            actionHandled = this.handleContainerAction(container, actionId);
        }

        return actionHandled;
    }

    /**
     * Handle actions for container components
     * @param container Container component
     * @param actionId Action identifier
     * @return true if action was handled
     */
    private boolean handleContainerAction(UIContainerComponent container, int actionId) {
        boolean actionHandled = false;
        boolean actionFound = false;

        // Get action mappings
        byte[][] actionMappings;
        if (container.getDialogData() != null) {
            actionMappings = container.getDialogData();
        } else {
            actionMappings = new byte[][]{{0, 0, 1, -1}, {1, 1, 1, -1}, {2, 2, 1, -1}, {3, 3, 1, -1}, {5, 4, -1, -1}, {7, 5, -1, -1}};
        }

        int[] actionData = new int[3];
        if (container.secondaryListComponent != null) {
            actionData[1] = container.secondaryListComponent.selectedIndex;
        } else {
            actionData[1] = -1;
        }

        if (container.primaryListComponent != null) {
            actionData[0] = container.primaryListComponent.selectedIndex;
        } else {
            actionData[0] = -1;
        }

        // Process action mappings
        for (int i = 0; i < actionMappings.length; i++) {
            if (actionMappings[i][0] == actionId) {
                actionFound = true;
                switch (actionMappings[i][1]) {
                    case 0: // Navigate primary list up
                        actionData[2] = 0;
                        if (container.primaryListComponent != null) {
                            if (actionMappings[i][3] != -1 && container.primaryListComponent.selectedIndex % (actionMappings[i][3] + 1) == 0) {
                                container.primaryListComponent.moveSelectionForward(actionMappings[i][3], this.uiContainerComponent);
                            } else {
                                container.primaryListComponent.moveSelectionBackward(actionMappings[i][2], this.uiContainerComponent);
                            }
                            actionHandled = true;
                            actionData[0] = container.primaryListComponent.selectedIndex;
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        } else {
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        }
                        break;
                    case 1: // Navigate primary list down
                        actionData[2] = 1;
                        if (container.primaryListComponent != null) {
                            if (actionMappings[i][3] != -1 && (container.primaryListComponent.selectedIndex + 1) % (actionMappings[i][3] + 1) == 0) {
                                container.primaryListComponent.moveSelectionBackward(actionMappings[i][3], this.uiContainerComponent);
                            } else {
                                container.primaryListComponent.moveSelectionForward(actionMappings[i][2], this.uiContainerComponent);
                            }
                            actionHandled = true;
                            actionData[0] = container.primaryListComponent.selectedIndex;
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        } else {
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        }
                        break;
                    case 2: // Navigate secondary list up
                        actionData[2] = 2;
                        if (container.secondaryListComponent != null) {
                            if (actionMappings[i][3] != -1 && container.secondaryListComponent.selectedIndex % (actionMappings[i][3] + 1) == 0) {
                                container.secondaryListComponent.moveSelectionForward(actionMappings[i][3], this.uiContainerComponent);
                            } else {
                                container.secondaryListComponent.moveSelectionBackward(actionMappings[i][2], this.uiContainerComponent);
                            }
                            actionHandled = true;
                            actionData[1] = container.secondaryListComponent.selectedIndex;
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        } else {
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        }
                        break;
                    case 3: // Navigate secondary list down
                        actionData[2] = 3;
                        if (container.secondaryListComponent != null) {
                            if (actionMappings[i][3] != -1 && (container.secondaryListComponent.selectedIndex + 1) % (actionMappings[i][3] + 1) == 0) {
                                container.secondaryListComponent.moveSelectionBackward(actionMappings[i][3], this.uiContainerComponent);
                            } else {
                                container.secondaryListComponent.moveSelectionForward(actionMappings[i][2], this.uiContainerComponent);
                            }
                            actionHandled = true;
                            actionData[1] = container.secondaryListComponent.selectedIndex;
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        } else {
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        }
                        break;
                    case 4: // Select/Enter
                        if (container.secondaryListComponent != null) {
                            if (container.secondaryListComponent.selectedIndex >= GameUtils.findFirstAvailableSlot(container.secondaryListComponent.itemIds)) {
                                actionHandled = this.activateComponent(container, container.secondaryListComponent.itemIds[container.secondaryListComponent.selectedIndex - container.secondaryListComponent.scrollOffset]);
                            } else {
                                actionHandled = this.activateComponent(container, container.secondaryListComponent.itemIds[container.secondaryListComponent.selectedIndex]);
                            }
                        }

                        if (!actionHandled && container.primaryListComponent != null) {
                            if (container.primaryListComponent.selectedIndex >= GameUtils.findFirstAvailableSlot(container.primaryListComponent.itemIds)) {
                                actionHandled = this.activateComponent(container, container.primaryListComponent.itemIds[container.primaryListComponent.selectedIndex - container.primaryListComponent.scrollOffset]);
                            } else {
                                actionHandled = this.activateComponent(container, container.primaryListComponent.itemIds[container.primaryListComponent.selectedIndex]);
                            }
                        }

                        if (actionHandled) {
                            actionData[2] = 6;
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        } else {
                            actionData[2] = 4;
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        }
                        break;
                    case 5: // Back/Cancel
                        if (actionHandled = this.navigateBack()) {
                            actionData[2] = 7;
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        } else {
                            actionData[2] = 5;
                            this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                        }
                        break;
                }

                if (actionHandled) {
                    break;
                }
            }
        }

        // Handle unmapped actions (function keys)
        if (!actionFound) {
            switch (actionId) {
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                    actionData[2] = actionId - 6;
                    this.eventHandler.action(actionData, new int[]{-1, -1, -1, -1});
                    break;
            }
        }

        return actionHandled;
    }

    /**
     * Navigate back in the component hierarchy
     * @return true if navigation was successful
     */
    private boolean navigateBack() {
        boolean canNavigateBack = false;
        int targetComponentId = -1;

        for (int i = GameUtils.findFirstAvailableSlot(this.activeComponentIndices) - 2; i >= 0; i--) {
            IComponent component = GameUtils.findChildById(this.uiContainerComponent, this.activeComponentIndices[i]);
            if (((UIContainerComponent) component).primaryListComponent != null || ((UIContainerComponent) component).secondaryListComponent != null) {
                canNavigateBack = true;
                targetComponentId = component.getSelectedComponentId();
                break;
            }
        }

        if (canNavigateBack) {
            this.navigationIndices = this.buildNavigationPath(targetComponentId);
            if (this.navigationIndices == null) {
                this.navigationIndices = GameUtils.initializeArrayWithMinusOne(50);
            }
            this.initializeActiveIndices();
        }

        return canNavigateBack;
    }

    /**
     * Activate a component and update navigation state
     * @param parentComponent Parent component
     * @param targetComponentId ID of component to activate
     * @return true if activation was successful
     */
    private boolean activateComponent(IComponent parentComponent, int targetComponentId) {
        if (parentComponent.getZIndex() == 1) {
            return false;
        }

        int componentIndex = this.findComponentIndex(parentComponent, targetComponentId, true);
        if (componentIndex == -1) {
            return false;
        }

        this.navigationIndices = this.buildNavigationPath(componentIndex);
        if (this.navigationIndices == null) {
            this.navigationIndices = GameUtils.initializeArrayWithMinusOne(50);
        }

        this.initializeActiveIndices();
        return true;
    }

    /**
     * Initialize the active component indices array
     */
    private void initializeActiveIndices() {
        this.activeComponentIndices = GameUtils.initializeArrayWithMinusOne(50);
        IComponent currentComponent = this.uiContainerComponent;
        int indexCounter = 0;
        this.activeComponentIndices[0] = currentComponent.getSelectedComponentId();
        indexCounter++;

        for (int i = 0; i < this.navigationIndices.length && this.navigationIndices[i] != -1; i++) {
            currentComponent = currentComponent.getComponents()[this.navigationIndices[i]];
            this.activeComponentIndices[indexCounter++] = currentComponent.getSelectedComponentId();
        }
    }

    /**
     * Find the index of a component for activation
     * @param parentComponent Parent component to search in
     * @param targetComponentId ID of target component
     * @param showSelection Whether to show selection highlight
     * @return Component index or -1 if not found
     */
    private int findComponentIndex(IComponent parentComponent, int targetComponentId, boolean showSelection) {
        if (parentComponent.getZIndex() == 2 && targetComponentId == -1) {
            if (showSelection) {
                ((GridComponent) parentComponent).showSelection = true;
            }
            return parentComponent.getSelectedComponentId();
        } else if ((((UIContainerComponent) parentComponent).primaryListComponent != null ||
                ((UIContainerComponent) parentComponent).secondaryListComponent != null) && targetComponentId == -1) {
            return parentComponent.getSelectedComponentId();
        } else {
            for (int i = 0; i < parentComponent.getComponents().length && parentComponent.getComponents()[i] != null; i++) {
                if (parentComponent.getComponents()[i].getZIndex() != 1 &&
                        (targetComponentId == -1 || parentComponent.getComponents()[i].getSelectedComponentId() == targetComponentId)) {
                    int result = this.findComponentIndex(parentComponent.getComponents()[i], -1, showSelection);
                    if (result != -1) {
                        return result;
                    }
                }
            }
            return -1;
        }
    }

    /**
     * Build navigation path to a specific component
     * @param targetComponentId ID of target component
     * @return Array representing the navigation path
     */
    private int[] buildNavigationPath(int targetComponentId) {
        int[] path = GameUtils.initializeArrayWithMinusOne(50);

        for (IComponent current = GameUtils.findChildById(this.uiContainerComponent, targetComponentId);
             current.getActiveComponentId() != -1;
             current = GameUtils.findChildById(this.uiContainerComponent, current.getActiveComponentId())) {

            IComponent parent = GameUtils.findChildById(this.uiContainerComponent, current.getActiveComponentId());

            for (int i = 0; i < parent.getComponents().length && parent.getComponents()[i] != null; i++) {
                if (parent.getComponents()[i].getSelectedComponentId() == current.getSelectedComponentId()) {
                    GameUtils.insertAtStart(path, 0, i);
                    break;
                }
            }
        }

        return path;
    }

    /**
     * Find the currently active component based on navigation path
     * @param startComponent Component to start search from
     * @param navigationPath Navigation path array
     * @param currentDepth Current depth in the path
     * @return The active component
     */
    private IComponent findActiveComponent(IComponent startComponent, int[] navigationPath, int currentDepth) {
        int pathLength = GameUtils.findFirstAvailableSlot(navigationPath);
        while (pathLength != 0) {
            if (currentDepth == pathLength - 1) {
                return startComponent.getComponents()[navigationPath[currentDepth]];
            }
            startComponent = startComponent.getComponents()[navigationPath[currentDepth]];
            currentDepth++;
        }
        return startComponent;
    }

    /**
     * Clean up dialog resources
     */
    public final void cleanup() {
        this.uiContainerComponent.cleanUp();
        this.allowedActions = null;
        this.navigationIndices = null;
        this.activeComponentIndices = null;
        this.eventHandler = null;
        this.childComponents = null;
    }
}