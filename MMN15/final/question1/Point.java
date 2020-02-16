/** Utility class used by ImageCruncher. Provided in an extra file for clarity. */
public class Point {
    // Properties
    private int x, y;

    // Cnstr.
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** Get the X coordinate of the point. */
    public int getX() {
        return x;
    }

    /** Set the X coordinate of the point. */
    public void setX(int x) {
        this.x = x;
    }

    /** Get the Y coordinate of the point. */
    public int getY() {
        return y;
    }

    /** Set the X coordinate of the point. */
    public void setY(int y) {
        this.y = y;
    }
}
