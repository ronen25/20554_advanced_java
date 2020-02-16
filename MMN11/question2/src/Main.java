/*
 * Advanced Java - MMN11 - Question 2
 * Ronen Lapushner, Nov. 2019
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Main {
    static final int WINDOW_WIDTH = 800;
    static final int WINDOW_HEIGHT = 600;
    static final int MATRIX_DIMENSION = 10;

    public static void main(String[] args) {
        // Initialize the game of life matrix
        LifeMatrix matrix = new LifeMatrix(MATRIX_DIMENSION, MATRIX_DIMENSION);
        matrix.populate();

        // Initialize the game panel
        GamePanel gamePanel = new GamePanel(matrix);

        // Initialize the game window
        JFrame frmMain = new JFrame("Game of Life");
        frmMain.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmMain.add(gamePanel);
        frmMain.setVisible(true);

        while (true) {
            // Ask the user if he wants to advance to the next generation
            int option = JOptionPane.showConfirmDialog(null,
                    "Would you like to advance to the next generation?",
                    "Generation Advance",
                    JOptionPane.YES_NO_OPTION);

            // Check what the user answered.
            if (option == JOptionPane.YES_OPTION) {
                matrix.nextGeneration();
                gamePanel.repaint();
            }
            else {
                // Exit the loop if the user answered negatively.
                break;
            }
        }
    }
}
