package layout;

/**
 * Rectangle Bounds - represents a rectangular area with position and dimensions
 * Used for UI component positioning, collision detection, and rendering areas
 */
public final class Rectangle {

    /**
     * X coordinate of the rectangle's top-left corner
     */
    public int x;

    /**
     * Y coordinate of the rectangle's top-left corner
     */
    public int y;

    /**
     * Width of the rectangle
     */
    public int width;

    /**
     * Height of the rectangle
     */
    public int height;

    /**
     * Default constructor - creates rectangle at origin with zero size
     */
    public Rectangle() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    /**
     * Constructor with position and dimensions
     *
     * @param x      X coordinate of top-left corner
     * @param y      Y coordinate of top-left corner
     * @param width  Width of rectangle
     * @param height Height of rectangle
     */
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Copy constructor
     *
     * @param other Rectangle to copy from
     */
    public Rectangle(Rectangle other) {
        this.x = other.x;
        this.y = other.y;
        this.width = other.width;
        this.height = other.height;
    }

    /**
     * Set rectangle position and dimensions
     *
     * @param x      New X coordinate
     * @param y      New Y coordinate
     * @param width  New width
     * @param height New height
     */
    public void setBounds(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Set rectangle position
     *
     * @param x New X coordinate
     * @param y New Y coordinate
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Set rectangle dimensions
     *
     * @param width  New width
     * @param height New height
     */
    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Get right edge X coordinate
     *
     * @return X coordinate of right edge
     */
    public int getRight() {
        return this.x + this.width;
    }

    /**
     * Get bottom edge Y coordinate
     *
     * @return Y coordinate of bottom edge
     */
    public int getBottom() {
        return this.y + this.height;
    }

    /**
     * Get center X coordinate
     *
     * @return X coordinate of center point
     */
    public int getCenterX() {
        return this.x + this.width / 2;
    }

    /**
     * Get center Y coordinate
     *
     * @return Y coordinate of center point
     */
    public int getCenterY() {
        return this.y + this.height / 2;
    }

    /**
     * Check if point is inside rectangle
     *
     * @param pointX X coordinate of point
     * @param pointY Y coordinate of point
     * @return true if point is inside rectangle
     */
    public boolean contains(int pointX, int pointY) {
        return pointX >= this.x && pointX < this.getRight() &&
                pointY >= this.y && pointY < this.getBottom();
    }

    /**
     * Check if another rectangle intersects with this one
     *
     * @param other Rectangle to test intersection with
     * @return true if rectangles intersect
     */
    public boolean intersects(Rectangle other) {
        return this.x < other.getRight() && this.getRight() > other.x &&
                this.y < other.getBottom() && this.getBottom() > other.y;
    }

    /**
     * Check if this rectangle completely contains another rectangle
     *
     * @param other Rectangle to test containment
     * @return true if other rectangle is completely inside this one
     */
    public boolean contains(Rectangle other) {
        return other.x >= this.x && other.y >= this.y &&
                other.getRight() <= this.getRight() && other.getBottom() <= this.getBottom();
    }

    /**
     * Get intersection rectangle with another rectangle
     *
     * @param other Rectangle to intersect with
     * @return New rectangle representing intersection, or null if no intersection
     */
    public Rectangle getIntersection(Rectangle other) {
        if (!this.intersects(other)) {
            return null;
        }

        int intersectX = Math.max(this.x, other.x);
        int intersectY = Math.max(this.y, other.y);
        int intersectRight = Math.min(this.getRight(), other.getRight());
        int intersectBottom = Math.min(this.getBottom(), other.getBottom());

        return new Rectangle(intersectX, intersectY,
                intersectRight - intersectX, intersectBottom - intersectY);
    }

    /**
     * Get union rectangle with another rectangle
     *
     * @param other Rectangle to union with
     * @return New rectangle representing union of both rectangles
     */
    public Rectangle getUnion(Rectangle other) {
        int unionX = Math.min(this.x, other.x);
        int unionY = Math.min(this.y, other.y);
        int unionRight = Math.max(this.getRight(), other.getRight());
        int unionBottom = Math.max(this.getBottom(), other.getBottom());

        return new Rectangle(unionX, unionY,
                unionRight - unionX, unionBottom - unionY);
    }

    /**
     * Expand rectangle by specified amount in all directions
     *
     * @param amount Amount to expand (can be negative to shrink)
     */
    public void expand(int amount) {
        this.x -= amount;
        this.y -= amount;
        this.width += amount * 2;
        this.height += amount * 2;
    }

    /**
     * Translate rectangle by specified offset
     *
     * @param deltaX X offset
     * @param deltaY Y offset
     */
    public void translate(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    /**
     * Check if rectangle is empty (zero or negative area)
     *
     * @return true if rectangle has no area
     */
    public boolean isEmpty() {
        return this.width <= 0 || this.height <= 0;
    }

    /**
     * Get area of rectangle
     *
     * @return Area in square pixels
     */
    public int getArea() {
        return this.width * this.height;
    }

    /**
     * Copy values from another rectangle
     *
     * @param other Rectangle to copy from
     */
    public void copyFrom(Rectangle other) {
        this.x = other.x;
        this.y = other.y;
        this.width = other.width;
        this.height = other.height;
    }

    /**
     * Check if this rectangle equals another rectangle
     *
     * @param other Rectangle to compare with
     * @return true if rectangles have same position and size
     */
    public boolean equals(Rectangle other) {
        if (other == null) return false;
        return this.x == other.x && this.y == other.y &&
                this.width == other.width && this.height == other.height;
    }

    /**
     * Get string representation of rectangle
     *
     * @return String description of rectangle bounds
     */
    public String toString() {
        return "Rectangle{x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "}";
    }
}