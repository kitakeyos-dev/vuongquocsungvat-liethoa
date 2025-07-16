package layout;

public final class GridCell {
    /**
     * Cell renderer for drawing content
     */
    public AnimatedRenderer cellRenderer;

    /**
     * Navigation indices to adjacent cells
     */
    private int upNavigationIndex;
    private int downNavigationIndex;
    private int leftNavigationIndex;
    private int rightNavigationIndex;

    /**
     * Cell active state (0 = inactive, 1 = active)
     */
    private int activeState;

    /**
     * Default constructor - creates inactive cell
     */
    public GridCell() {
        this.activeState = 0;
        this.cellRenderer = null;
    }

    /**
     * Constructor with navigation indices
     *
     * @param upIndex    Index of cell above this one
     * @param downIndex  Index of cell below this one
     * @param leftIndex  Index of cell to the left
     * @param rightIndex Index of cell to the right
     */
    public GridCell(int upIndex, int downIndex, int leftIndex, int rightIndex) {
        this.cellRenderer = null;
        this.upNavigationIndex = upIndex;
        this.downNavigationIndex = downIndex;
        this.leftNavigationIndex = leftIndex;
        this.rightNavigationIndex = rightIndex;
        this.activeState = 1; // Active by default
    }

    /**
     * Constructor with renderer and navigation
     *
     * @param rendererId    Renderer ID (-1 for no renderer)
     * @param rendererFlags Renderer configuration flags
     * @param upIndex       Navigation index up
     * @param downIndex     Navigation index down
     * @param leftIndex     Navigation index left
     * @param rightIndex    Navigation index right
     */
    public GridCell(int rendererId, byte rendererFlags, int upIndex, int downIndex, int leftIndex, int rightIndex) {
        // Setup renderer if valid ID provided
        if (rendererId != -1) {
            this.cellRenderer = new AnimatedRenderer();
            this.cellRenderer.setSpriteIndex(rendererId);
            this.cellRenderer.spriteType = rendererFlags;
        } else {
            this.cellRenderer = null;
        }

        this.upNavigationIndex = upIndex;
        this.downNavigationIndex = downIndex;
        this.leftNavigationIndex = leftIndex;
        this.rightNavigationIndex = rightIndex;
        this.activeState = 1; // Active by default
    }

    /**
     * Get cell active state
     *
     * @return 1 if active, 0 if inactive
     */
    public final int getActiveState() {
        return this.activeState;
    }

    /**
     * Check if cell is active
     *
     * @return true if cell is active and can be navigated to
     */
    public final boolean isActive() {
        return this.activeState == 1;
    }

    /**
     * Set cell active state
     *
     * @param state 1 for active, 0 for inactive
     */
    public final void setActiveState(int state) {
        this.activeState = state;
    }

    // === NAVIGATION INDEX GETTERS ===

    /**
     * Get index of cell above this one
     *
     * @return Navigation index for up direction
     */
    public final int getUpNavigation() {
        return this.upNavigationIndex;
    }

    /**
     * Get index of cell below this one
     *
     * @return Navigation index for down direction
     */
    public final int getDownNavigation() {
        return this.downNavigationIndex;
    }

    /**
     * Get index of cell to the left
     *
     * @return Navigation index for left direction
     */
    public final int getLeftNavigation() {
        return this.leftNavigationIndex;
    }

    /**
     * Get index of cell to the right
     *
     * @return Navigation index for right direction
     */
    public final int getRightNavigation() {
        return this.rightNavigationIndex;
    }

    // === NAVIGATION INDEX SETTERS ===

    /**
     * Set navigation index for up direction
     *
     * @param index Index of cell above
     */
    public final void setUpNavigation(int index) {
        this.upNavigationIndex = index;
    }

    /**
     * Set navigation index for down direction
     *
     * @param index Index of cell below
     */
    public final void setDownNavigation(int index) {
        this.downNavigationIndex = index;
    }

    /**
     * Set navigation index for left direction
     *
     * @param index Index of cell to the left
     */
    public final void setLeftNavigation(int index) {
        this.leftNavigationIndex = index;
    }

    /**
     * Set navigation index for right direction
     *
     * @param index Index of cell to the right
     */
    public final void setRightNavigation(int index) {
        this.rightNavigationIndex = index;
    }
}
