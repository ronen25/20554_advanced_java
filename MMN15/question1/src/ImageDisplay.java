import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** Class for displaying the cell matrix. */
public class ImageDisplay extends JPanel {
    // Constants
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color FILL_COLOR = Color.BLACK;

    // Properties
    private int dimension;
    private int cellWidth, cellHeight;
    private CellMatrix model;

    // Cnstr.
    public ImageDisplay(int n) {
        this.dimension = n;

        // Init model
        model = new CellMatrix(n, n);

        // Init mouse events
        initMouseEvents();
    }

    private void initMouseEvents() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Get the actual coordinates of the clicked cell
                Point location = screenToMatrix(e.getX(), e.getY());
                int row = (int)location.getX();
                int col = (int)location.getY();

                // Toggle the cell
                toggleCell(row, col);

                // Refresh the display
                repaint();
            }
        });
    }

    /** Draws the UI for the ImageDisplay (by basically drawing the grid). */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // First we need to calculate the size of each cell, based on width and height.
        this.cellWidth = this.getWidth() / this.dimension;
        this.cellHeight = this.getHeight() / this.dimension;

        // Fill background
        g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Draw the cell matrix.
        g.setColor(FILL_COLOR);
        for (int row = 0; row < this.dimension; row++) {
            for (int col = 0; col < this.dimension; col++) {
                // Calculate X, Y coordinates. Leave 1 pixel for the borders.
                int xCoord = (col * cellWidth) + 1;
                int yCoord = (row * cellHeight) + 1;

                // Draw and fill if needed.
                g.drawRect(xCoord, yCoord, cellWidth, cellHeight);
                if (model.getCell(row, col))
                    g.fillRect(xCoord, yCoord, cellWidth, cellHeight);
            }
        }
    }

    /** Clears the board. */
    public void clearBoard() {
        // Clear the model and refresh
        model.clear();
        this.repaint();
    }

    /** Sets the model and displays it. */
    public void setModel(CellMatrix newModel) {
        this.model = newModel;
        this.repaint();
    }

    public CellMatrix getModel() {
        return this.model;
    }

    public int getMatrixWidth() {
        return this.model.getWidth();
    }

    public int getMatrixHeight() {
        return this.model.getHeight();
    }

    /**
     * A utility method to convert screen points (i.e. mouse location) to a matrix location (i.e. cell index).
     * The method returns the point (-1, -1) if the coordinates are out of bounds.
     */
    private Point screenToMatrix(int xCoord, int yCoord)  {
        // Check bounds
        if (xCoord < 0 || xCoord > this.getWidth() || yCoord < 0 || yCoord > this.getHeight())
            return new Point(-1, -1);

        // Convert the point
        int row = yCoord / cellHeight;
        int col = xCoord / cellWidth;

        return new Point(row, col);
    }

    /** Toggles a particular cell. */
    private void toggleCell(int row, int col) {
        // Get the value of the cell (from the model), and negate it
        boolean newCellValue = !(model.getCell(row, col));

        // Set the cell
        model.setCell(row, col, newCellValue);
    }
}
