/*
 * Advanced Java - MMN11 - Question 2
 * Ronen Lapushner, Nov. 2019
 */

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/** A class that represents the Game Of Life display panel */
public class GamePanel extends JPanel {
	// Properties
	private LifeMatrix matrix;
	
	// Constructor
    public GamePanel(LifeMatrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Calculate the width and height of every cell based on the windows'
        // dimensions.
        final int cellWidth = this.getWidth() / matrix.getWidth();
        final int cellHeight = this.getHeight() / matrix.getHeight();

        // Draw every cell as a square.
        // Living cells are colored black, while dead cells are colored white.
        for (int row = 0; row < matrix.getWidth(); row++) {
            for (int col = 0; col < matrix.getHeight(); col++) {
                // Determine cell color
                Color cellColor = matrix.getCellValue(row, col) ? Color.BLACK : Color.WHITE;

                // Draw the cell as a square, with the specified color
                // and the width/height we've calculated earlier.
                g.setColor(Color.BLACK);
                g.drawRect(col * (cellWidth + 1), row * (cellHeight + 1), cellWidth, cellHeight);

                // Fill the cell
                g.setColor(cellColor);
                g.fillRect(col * (cellWidth + 1), row * (cellHeight + 1), cellWidth, cellHeight);
            }
        }
    }
}
