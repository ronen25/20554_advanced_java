import java.util.*;

/**
 * A thread-safe cell matrix that is used as a model for the ImageDisplay component.
 */
public class CellMatrix {
    // Properties
    private int width, height;
    private boolean[][] data;

    // Cnstr.
    public CellMatrix(int width, int height) {
        this.width = width;
        this.height = height;

        // Initialize array
        this.data = new boolean[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.data[x][y] = false;
            }
        }
    }

    // "Internal" constructor, usually used for quick subsection construction
    private CellMatrix(boolean[][] rawData) {
        // Set properties based on the data we've got
        this.width = rawData[0].length;
        this.height = rawData.length;
        data = rawData;
    }

    // "Copy" constructor
    public CellMatrix(CellMatrix other) {
        this(other.data);
    }

    /**
     * Gets a cell's value based on a row and a column.
     * NOTE: This method is synchronized.
     */
    public boolean getCell(int row, int col) throws IndexOutOfBoundsException {
        // Check bounds
        if (row < 0 || row > this.height || col < 0 || col > this.width)
            throw new IndexOutOfBoundsException("Cell (" + row + ", " + col + ") is out of bounds!");

        // Return value
        synchronized (this.data[row]) {
            return this.data[row][col];
        }
    }

    /**
     * Sets a cell's value based on a row and a column.
     * NOTE: This method is synchronized.
     */
    public void setCell(int row, int col, boolean value) throws IndexOutOfBoundsException {
        // Check bounds
        if (row < 0 || row > this.height || col < 0 || col > this.width)
            throw new IndexOutOfBoundsException("Cell (" + row + ", " + col + ") is out of bounds!");

        // Set value
        synchronized (this.data[row]) {
            this.data[row][col] = value;
        }
    }

    /**
     * Returns the raw data matrix from this class.
     */
    public synchronized boolean[][] getRawData() {
        return data;
    }

    /**
     * Clears the board.
     */
    public synchronized void clear() {
        // Clear the board
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.data[x][y] = false;
            }
        }
    }

    /**
     * A utility method that checks how many cells of value Value does a cell have as it's neighbors.
     */
    public int cellNeighbors(int line, int col, boolean value) throws IndexOutOfBoundsException {
        int count = 0;

        // Bound checks
        // Special case: the cell itself is white.
        synchronized (this.data) {
            if (!(this.data[line][col]))
                return count;
            else {
                // Right neighbor white?
                if ((col + 1) < this.data[0].length && this.data[line][col + 1] == value)
                    count++;

                // Left neighbor white?
                if ((col - 1) >= 0 && this.data[line][col - 1] == value)
                    count++;

                // Upper neighbor white?
                if ((line - 1) >= 0 && this.data[line - 1][col] == value)
                    count++;

                // Lower neighbor white?
                if ((line + 1) < this.data.length && this.data[line + 1][col] == value)
                    count++;

                return count;
            }
        }
    }

    /**
     * Gets the width of the matrix.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the matrix.
     */
    public int getHeight() {
        return height;
    }
}
