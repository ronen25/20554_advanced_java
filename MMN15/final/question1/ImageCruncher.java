import java.util.ArrayList;

/**
 * A worker class that performs a SINGLE PASS of image "crunching".
 * Note: The worker modifies the model in-place.
 */
public class ImageCruncher implements Runnable {
    // Properties
    private int minRow, maxRow;
    private final CellMatrix modelRef;

    // Cnstr.
    public ImageCruncher(int minRow, int maxRow, CellMatrix model) {
        this.minRow = minRow;
        this.maxRow = maxRow;
        this.modelRef = model;
    }

    /**
     * Performs the crunch as follows: pixels that have one or more filled-in
     * neighbors are turned into a white pixel.
     *
     * We perform this operation on a copy of the array (since we don't want
     * to update stuff twice, and because CellMatrix is synchronized, for performance
     * reasons).
     *
     * After we're done, we're updating the original model.
     */
    @Override
    public void run() {
        // Special case: No work for this worker.
        if (minRow > maxRow)
            return;

        // Create a list of point coordinates that will be updated to the model
        // at the end of the crunching run.
        ArrayList<Point> points = new ArrayList<>();

        // Go through all the lines we need, and crunch every pixel as follows:
        for (int line = minRow; line <= maxRow; line++) {
            for (int col = 0; col < modelRef.getWidth(); col++) {
                // We only need special handling for filled cells.
                if (modelRef.getCell(line, col)) {
                    // If the filled cell has any white neighbors, it must become
                    // unfilled.
                    if (modelRef.cellNeighbors(line, col, false) > 0)
                        points.add(new Point(line, col));
                }
            }
        }

        // Now go through all points gathered and nullify them in the model.
        for (Point p: points)
            modelRef.setCell(p.getX(), p.getY(), false);
    }
}
