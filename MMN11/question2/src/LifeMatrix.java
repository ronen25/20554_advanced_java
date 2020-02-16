/*
 * Advanced Java - MMN11 - Question 2
 * Ronen Lapushner, Nov. 2019
 */

import java.util.Random;

/** A class that represents the game of life state */
public class LifeMatrix {
    // Properties
    private int width;
    private int height;
    private boolean[][] cells;
    private Random randomGen;

    // Constants
    private final int CELL_NEIGHBORS_BIRTH = 3;
    private final int CELL_NEIGHBORS_DEATH = 1;
    private final int CELL_NEIGHBORS_EXISTENCE = 4;
    
    /** Constructs a new LifeMatrix class */
    public LifeMatrix(int width, int height) {
        // Store properties
        this.width = width;
        this.height = height;

        // Initialize array of cells
        this.cells  = new boolean[width][height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++)
                this.cells[i][j] = false;
        }

        // Initialize random number generator
        randomGen = new Random();
    }

    /** Randomly populates the cell matrix with a random amount of cells. */
    public void populate() {
        // We've randomly chosen width cells to populate throughout the matrix.
        final int CELLS_TO_POPULATE = this.width * 2;

        // Populate the cells
        for (int i = 0; i < CELLS_TO_POPULATE; i++) {
            // Random coordinates for the cell
            int x = randomGen.nextInt(this.width - 1);
            int y = randomGen.nextInt(this.height - 1);

            // Toggle the cell
            this.cells[x][y] = true;
        }
    }

    /** Advances the life matrix to the next generation. */
    public void nextGeneration() {
        boolean[][] newGen = new boolean[this.width][this.height];
        
        // For every cell, check neighbors, and perform birth, death of existance
        // based on Game of Life rules.
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                // Get cell's living neighbors
                int livingNeighbors = this.getCellLivingNeighborsCount(x, y);

                // Simulate the game
                if (this.cells[x][y]) {
                    // Death: If cell is living, and has 0, 1 or 4 and more living neighbors,
                    // will die (either from loneliness or overcrowding, respectfully).
                    // Here we also implicitly Existence: cell remains alive if it has 2 or 3
                    // living neighbors.
                    if (livingNeighbors <= CELL_NEIGHBORS_DEATH || livingNeighbors >= CELL_NEIGHBORS_EXISTENCE)
                        newGen[x][y] = false;
                    else
                        newGen[x][y] = true;
                }
                else {
                    // Birth: If cell is dead, and has exactly 3 living neighbors, it becomes alive.
                    if (livingNeighbors == CELL_NEIGHBORS_BIRTH)
                        newGen[x][y] = true;
                }
            }
        }
        
        // Replace the current (old) generation with the new generation
        this.cells = newGen;
    }

    /** Gets the width of this matrix. */
    public int getWidth() {
        return this.width;
    }

    /** Gets the height of this matrix. */
    public int getHeight() {
        return this.height;
    }

    /** Gets the width of this matrix. */
    public boolean getCellValue(int x, int y) {
        // If out of bounds, return false, since exceptions
        // are not allowed.
        if (x < 0 || x >= this.height || y < 0 || y >= this.width)
            return false;
        
        return cells[x][y];
    }

    /** Gets the amount of the living neighbors of the requested cell. */
    public int getCellLivingNeighborsCount(int x, int y) {
        int count = 0;

        // Upper left neighbor alive?
        if ((x - 1) >= 0 && (y - 1) >= 0 && cells[x - 1][y - 1])
            count++;

        // Upper neighbor alive?
        if ((y - 1) >= 0 && cells[x][y - 1])
            count++;

        // Upper right neighbor alive?
        if ((x + 1) < cells[0].length && (y - 1) >= 0 && cells[x + 1][y - 1])
            count++;

        // Right neighbor alive?
        if ((x + 1) < cells[0].length && cells[x + 1][y])
            count++;

        // Lower right neighbor alive?
        if ((x + 1) < cells[0].length && (y + 1) < cells.length && cells[x + 1][y + 1])
            count++;

        // Lower neighbor alive?
        if ((y + 1) < cells.length && cells[x][y + 1])
            count++;

        // Lower left neighbor alive?
        if ((x - 1) >= 0 && (y + 1) < cells.length && cells[x - 1][y + 1])
            count++;

        // Left neighbor alive?
        if ((x - 1) >= 0 && cells[x - 1][y])
            count++;

        return count;
    }
}
