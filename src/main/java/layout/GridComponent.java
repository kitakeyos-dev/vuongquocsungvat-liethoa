package layout;

import javax.microedition.lcdui.Graphics;

/**
 * Grid Component - UI component hiển thị dữ liệu dạng lưới với navigation
 * Hỗ trợ scrolling, selection, và custom rendering cho từng cell
 */
public final class GridComponent implements IComponent {

    // Position and basic dimensions
    private int offsetX;
    private int offsetY;
    private int cellWidth;
    private int cellHeight;
    private int cellSpacingX;
    private int cellSpacingY;

    // Grid dimensions
    private int totalColumns;
    private int totalRows;

    // Selection and cursor
    private int cursorOffsetX;
    private int cursorOffsetY;

    // Visible area dimensions
    private int visibleColumns;
    private int visibleRows;

    // Scroll position
    private int scrollOffsetX;
    private int scrollOffsetY;

    // Navigation settings
    private int horizontalWrapMode; // 0 = no wrap, 1 = wrap around
    private int verticalWrapMode;   // 0 = no wrap, 1 = wrap around
    private int horizontalScrollMode; // 0 = cell by cell, 1 = page by page
    private int verticalScrollMode;   // 0 = cell by cell, 1 = page by page

    // Current selection
    private int selectedCellIndex;

    // Background color
    public int backgroundColor;

    // Rendering resources
    public SpriteRenderer backgroundRenderer;
    public SpriteRenderer selectionRenderer;
    public boolean showSelection;

    // Component state
    private boolean isVisible = true;
    private int selectedComponentId;
    private int zIndex;
    private int activeComponentId;

    // Grid cell data
    GridCell[] gridCells;

    /**
     * Constructor - khởi tạo grid component
     */

    public GridComponent() {
        this.offsetX = this.offsetY = 0;
        this.cellWidth = this.cellHeight = this.cellSpacingX = this.cellSpacingY = this.totalColumns = this.totalRows = 0;
        this.cursorOffsetX = this.cursorOffsetY = 0;
        this.visibleColumns = this.visibleRows = 0;
        this.scrollOffsetX = this.scrollOffsetY = 0;
        this.zIndex = 2;
        this.selectedComponentId = this.activeComponentId = -1;
        this.selectedCellIndex = 0;
        this.gridCells = new GridCell[0];
        this.selectionRenderer = null;
        this.showSelection = false;
        this.isVisible = true;
    }

    public final void setCellWidth(int width) {
        this.cellWidth = width;
    }

    public final void setCellHeight(int height) {
        this.cellHeight = height;
    }

    public final void setCellSpacingX(int spacing) {
        this.cellSpacingX = spacing;
    }

    public final void setCellSpacingY(int spacing) {
        this.cellSpacingY = spacing;
    }

    public final int getTotalColumns() {
        return this.totalColumns;
    }

    public final void setTotalColumns(int columns) {
        this.totalColumns = columns;
    }

    public final int getTotalRows() {
        return this.totalRows;
    }

    public final void setVisibleColumns(int columns) {
        if (columns > 0 && columns <= this.totalColumns) {
            this.visibleColumns = columns;
        } else {
            this.visibleColumns = this.totalColumns;
        }
    }

    public final void setVisibleRows(int rows) {
        if (rows > 0 && rows <= this.totalRows) {
            this.visibleRows = rows;
        } else {
            this.visibleRows = this.totalRows;
        }
    }

    public final void setTotalRows(int rows) {
        this.totalRows = rows;
    }

    // === SCROLL POSITION SETTERS ===

    public final void setScrollOffsetX(int offset) {
        this.scrollOffsetX = offset;
    }

    public final void setScrollOffsetY(int offset) {
        this.scrollOffsetY = offset;
    }

    private int getMaxVisibleColumn() {
        return this.scrollOffsetY + this.scrollOffsetX - 1;
    }

    private int getMaxVisibleRow() {
        return this.visibleRows + this.visibleColumns - 1;
    }

    public final void setCursorOffsetX(int offset) {
        this.cursorOffsetX = offset;
    }

    public final void setCursorOffsetY(int offset) {
        this.cursorOffsetY = offset;
    }

    // === NAVIGATION MODES ===

    public final void setHorizontalWrapMode(int mode) {
        this.horizontalWrapMode = mode;
    }

    public final void setVerticalWrapMode(int mode) {
        this.verticalWrapMode = mode;
    }

    public final void setHorizontalScrollMode(int mode) {
        this.horizontalScrollMode = mode;
    }

    public final void setVerticalScrollMode(int mode) {
        this.verticalScrollMode = mode;
    }

    /**
     * Create navigation links between grid cells
     *
     * @return Array of grid cells with navigation data
     */
    public final GridCell[] createNavigationGrid() {
        int totalCells = this.totalColumns * this.totalRows;
        GridCell[] cells = new GridCell[totalCells];

        // Initialize each cell with basic navigation indices
        for (int i = 0; i < totalCells; i++) {
            cells[i] = new GridCell(i - this.totalColumns, i + this.totalColumns, i - 1, i + 1);
        }

        // Set up horizontal navigation (row-wise)
        setupHorizontalNavigation(cells);

        // Set up vertical navigation (column-wise)
        setupVerticalNavigation(cells);

        return cells;
    }

    /**
     * Setup horizontal navigation links for each row
     */
    private void setupHorizontalNavigation(GridCell[] cells) {
        for (int row = 0; row < this.totalRows; row++) {
            GridCell firstCellInRow = null;
            GridCell lastCellInRow = null;
            int firstIndex = -1;
            int lastIndex = -1;

            // Find first and last active cells in this row
            for (int col = 0; col < this.totalColumns; col++) {
                int cellIndex = row * this.totalColumns + col;
                if (firstCellInRow == null && cells[cellIndex].isActive()) {
                    firstCellInRow = cells[cellIndex];
                    firstIndex = cellIndex;
                }
                if (lastCellInRow == null && cells[(row + 1) * this.totalColumns - col - 1].isActive()) {
                    lastCellInRow = cells[(row + 1) * this.totalColumns - col - 1];
                    lastIndex = (row + 1) * this.totalColumns - col - 1;
                }
            }

            // Setup navigation based on wrap mode
            if (firstCellInRow != null && firstCellInRow.isActive()) {
                if (this.horizontalWrapMode == 0) {
                    // No wrapping - connect to edge cells
                    firstCellInRow.setLeftNavigation(firstIndex);
                    lastCellInRow.setRightNavigation(lastIndex);
                } else {
                    // Wrapping - connect first to last and vice versa
                    setupWrappingNavigation(cells, firstIndex, lastIndex, true);
                }
            }
        }
    }

    /**
     * Setup vertical navigation links for each column
     */
    private void setupVerticalNavigation(GridCell[] cells) {
        for (int col = 0; col < this.totalColumns; col++) {
            GridCell firstCellInColumn = null;
            GridCell lastCellInColumn = null;
            int firstIndex = -1;
            int lastIndex = -1;

            // Find first and last active cells in this column
            for (int row = 0; row < this.totalRows; row++) {
                int cellIndex = col + this.totalColumns * row;
                if (firstCellInColumn == null && cells[cellIndex].isActive()) {
                    firstCellInColumn = cells[cellIndex];
                    firstIndex = cellIndex;
                }
                if (lastCellInColumn == null && cells[col + this.totalColumns * (this.totalRows - row - 1)].isActive()) {
                    lastCellInColumn = cells[col + this.totalColumns * (this.totalRows - row - 1)];
                    lastIndex = col + this.totalColumns * (this.totalRows - row - 1);
                }
            }

            // Setup navigation based on wrap mode
            if (firstCellInColumn != null && firstCellInColumn.isActive()) {
                if (this.verticalWrapMode == 0) {
                    // No wrapping
                    firstCellInColumn.setUpNavigation(firstIndex);
                    lastCellInColumn.setDownNavigation(lastIndex);
                } else {
                    // Wrapping - connect first to last and vice versa
                    setupWrappingNavigation(cells, firstIndex, lastIndex, false);
                }
            }
        }
    }

    /**
     * Setup wrapping navigation between cells
     */
    private void setupWrappingNavigation(GridCell[] cells, int firstIndex, int lastIndex, boolean isHorizontal) {
        if (isHorizontal) {
            // Find previous active cell (wrapping)
            int prevIndex = -1;
            for (int i = 0; i < cells.length; i++) {
                int checkIndex = firstIndex - i - 1;
                if (checkIndex < 0) {
                    checkIndex += this.totalColumns * this.totalRows;
                }
                if (prevIndex == -1 && cells[checkIndex].isActive()) {
                    prevIndex = checkIndex;
                    break;
                }
            }
            cells[firstIndex].setLeftNavigation(prevIndex);

            // Find next active cell (wrapping)
            int nextIndex = -1;
            for (int i = 0; i < cells.length; i++) {
                int checkIndex = lastIndex + i + 1;
                if (checkIndex >= this.totalColumns * this.totalRows) {
                    checkIndex -= this.totalColumns * this.totalRows;
                }
                if (nextIndex == -1 && cells[checkIndex].isActive()) {
                    nextIndex = checkIndex;
                    break;
                }
            }
            cells[lastIndex].setRightNavigation(nextIndex);
        } else {
            // Vertical wrapping logic similar to horizontal
            setupVerticalWrapping(cells, firstIndex, lastIndex);
        }
    }

    /**
     * Setup vertical wrapping navigation
     */
    private void setupVerticalWrapping(GridCell[] cells, int firstIndex, int lastIndex) {
        int columnIndex = firstIndex % this.totalColumns;

        // Find previous active cell in column (wrapping up)
        int prevIndex = -1;
        for (int row = 0; row < this.totalRows; row++) {
            int checkRow = firstIndex / this.totalColumns - 1 - row;
            if (checkRow < 0) {
                checkRow += this.totalRows;
            }
            int checkIndex = checkRow * this.totalColumns + columnIndex;
            if (prevIndex == -1 && cells[checkIndex].isActive()) {
                prevIndex = checkIndex;
                break;
            }
        }
        cells[firstIndex].setUpNavigation(prevIndex);

        // Find next active cell in column (wrapping down)
        int nextIndex = -1;
        for (int row = 0; row < this.totalRows; row++) {
            int checkRow = lastIndex / this.totalColumns + 1 + row;
            if (checkRow >= this.totalRows) {
                checkRow -= this.totalRows;
            }
            int checkIndex = checkRow * this.totalColumns + columnIndex;
            if (nextIndex == -1 && cells[checkIndex].isActive()) {
                nextIndex = checkIndex;
                break;
            }
        }
        cells[lastIndex].setDownNavigation(nextIndex);
    }


    /**
     * Create empty grid cell array
     */
    public static GridCell[] createEmptyGrid(int columns, int rows) {
        GridCell[] cells = new GridCell[columns * rows];
        for (int i = 0; i < columns * rows; i++) {
            cells[i] = new GridCell();
        }
        return cells;
    }

    /**
     * Navigate in specified direction
     *
     * @param direction 0=up, 1=down, 2=left, 3=right
     * @return true if navigation occurred
     */
    public final boolean navigate(byte direction) {
        boolean moved = false;
        int newIndex = this.selectedCellIndex;

        if (direction == 0) { // Up
            if (this.gridCells[this.selectedCellIndex].isActive() &&
                    newIndex != this.gridCells[this.selectedCellIndex].getUpNavigation()) {
                newIndex = this.gridCells[this.selectedCellIndex].getUpNavigation();
                moved = true;
            }
        } else if (direction == 1) { // Down
            if (this.gridCells[this.selectedCellIndex].isActive() &&
                    newIndex != this.gridCells[this.selectedCellIndex].getDownNavigation()) {
                newIndex = this.gridCells[this.selectedCellIndex].getDownNavigation();
                moved = true;
            }
        } else if (direction == 2) { // Left
            if (this.gridCells[this.selectedCellIndex].isActive() &&
                    newIndex != this.gridCells[this.selectedCellIndex].getLeftNavigation()) {
                newIndex = this.gridCells[this.selectedCellIndex].getLeftNavigation();
                moved = true;
            }
        } else if (direction == 3) { // Right
            if (this.gridCells[this.selectedCellIndex].isActive() &&
                    newIndex != this.gridCells[this.selectedCellIndex].getRightNavigation()) {
                newIndex = this.gridCells[this.selectedCellIndex].getRightNavigation();
                moved = true;
            }
        }

        this.setSelectedCellIndex(newIndex);
        return moved;
    }


    public final void render(Graphics var1, boolean var2, boolean var3, IComponent var4, int[] var5) {
        if (this.isVisible) {
            if (var1 != null) {
                var1.setColor(this.backgroundColor);
                var1.fillRect(this.offsetX, this.offsetY, this.getWidth(), this.getHeight());
                Rectangle var8 = new Rectangle(this.offsetX, this.offsetY, this.getWidth(), this.getHeight());
                if (this.backgroundRenderer != null) {
                    this.backgroundRenderer.render(var1, var8, 0);
                }

                int var9 = this.getMaxVisibleColumn();
                int var10 = this.getMaxVisibleRow();

                for (int var11 = this.scrollOffsetY; var11 <= var9; ++var11) {
                    for (int var6 = this.visibleRows; var6 <= var10; ++var6) {
                        var8 = new Rectangle(this.offsetX + this.cellSpacingX + (var6 - this.visibleRows) * (this.cellSpacingX + this.cellWidth), this.offsetY + this.cellSpacingY + (var11 - this.scrollOffsetY) * (this.cellSpacingY + this.cellHeight), this.cellWidth, this.cellHeight);
                        SpriteRenderer var7;
                        if ((var7 = this.gridCells[var11 * this.totalColumns + var6].cellRenderer) != null) {
                            var7.render(var1, var8, 0);
                        }
                    }
                }

                var8 = new Rectangle(this.offsetX + this.cellSpacingX + (this.selectedCellIndex % this.totalColumns - this.visibleRows) * (this.cellSpacingX + this.cellWidth) + this.cursorOffsetX, this.offsetY + this.cellSpacingY + (this.selectedCellIndex / this.totalColumns - this.scrollOffsetY) * (this.cellSpacingY + this.cellHeight) + this.cursorOffsetY, this.getWidth(), this.getHeight());
                if (this.showSelection && this.selectionRenderer != null && var1 != null) {
                    this.selectionRenderer.render(var1, var8, 0);
                }
            }

        }
    }

    public final void update(boolean var1, boolean var2, IComponent var3, int[] var4) {
        if (this.backgroundRenderer != null) {
            this.backgroundRenderer.update();
        }

        for (int var5 = 0; var5 < this.gridCells.length; ++var5) {
            if (this.gridCells[var5].cellRenderer != null) {
                this.gridCells[var5].cellRenderer.update();
            }
        }

        if (this.showSelection && this.selectionRenderer != null) {
            this.selectionRenderer.update();
        }

    }

    public final int getSelectedComponentId() {
        return this.selectedComponentId;
    }

    public final void setSelectedComponentId(int var1) {
        this.selectedComponentId = var1;
    }

    public final int getOffsetX() {
        return this.offsetX;
    }

    public final void setOffsetX(int var1, IComponent var2) {
        this.offsetX = var1;
    }

    public final int getOffsetY() {
        return this.offsetY;
    }

    public final void setOffsetY(int var1, IComponent var2) {
        this.offsetY = var1;
    }

    public final int getWidth() {
        return this.visibleColumns > 0 && this.visibleColumns <= this.totalColumns ? (this.cellWidth + this.cellSpacingX) * this.visibleColumns + this.cellSpacingX : (this.cellWidth + this.cellSpacingX) * this.totalColumns + this.cellSpacingX;
    }

    public final void setWidth(int var1, IComponent var2) {
    }

    public final int getHeight() {
        return this.scrollOffsetX > 0 && this.scrollOffsetX <= this.totalRows ? (this.cellHeight + this.cellSpacingY) * this.scrollOffsetX + this.cellSpacingY : (this.cellHeight + this.cellSpacingY) * this.totalRows + this.cellSpacingY;
    }

    public final void setHeight(int var1, IComponent var2) {
    }

    public final void setSelectedCellIndex(int var1) {
        if (this.gridCells != null && var1 > -2 && var1 < this.gridCells.length) {
            this.selectedCellIndex = var1;
            int var3 = this.selectedCellIndex / this.totalColumns;
            if (this.verticalScrollMode == 1) {
                this.scrollOffsetY = var3 / this.scrollOffsetX * this.scrollOffsetX;
            } else if (var3 < this.scrollOffsetY) {
                this.scrollOffsetY = var3;
            } else if (var3 > this.getMaxVisibleColumn()) {
                this.scrollOffsetY = var3 - this.scrollOffsetX + 1;
            }

            var3 = this.selectedCellIndex % this.totalColumns;
            if (this.verticalWrapMode == 1) {
                this.visibleRows = var3 / this.visibleColumns * this.visibleColumns;
                return;
            }

            if (var3 < this.visibleRows) {
                this.visibleRows = var3;
                return;
            }

            if (var3 > this.getMaxVisibleRow()) {
                this.visibleRows = var3 - this.visibleColumns + 1;
            }
        }

    }

    public final Component getChildComponent() {
        return null;
    }

    public final IComponent[] getComponents() {
        return null;
    }

    public final DialogData getComponentData() {
        return null;
    }

    public final void setComponentData(DialogData var1) {
    }

    public final int getZIndex() {
        return this.zIndex;
    }

    public final int getActiveComponentId() {
        return this.activeComponentId;
    }

    public final void setActiveComponentId(int var1) {
        this.activeComponentId = var1;
    }

    public final void setActiveComponent(IComponent var1) {
    }

    public final void cleanUp() {
        if (this.selectionRenderer != null) {
            this.selectionRenderer.cleanup();
            this.selectionRenderer = null;
        }

        if (this.backgroundRenderer != null) {
            this.backgroundRenderer.cleanup();
            this.backgroundRenderer = null;
        }

        if (this.gridCells != null) {
            for (int var1 = 0; var1 < this.gridCells.length; ++var1) {
                if (this.gridCells[var1] != null) {
                    this.gridCells[var1].cellRenderer.cleanup();
                    this.gridCells[var1] = null;
                }
            }

            this.gridCells = null;
        }

    }

    public final void setVisible(boolean var1) {
        this.isVisible = var1;
    }
}
