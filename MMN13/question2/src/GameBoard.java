import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel {
	// Constants
	private final int BLOCK_DIMENSION = 3;
	private final int BLOCK_COUNT = 9;
	
	// Properties
	private GridLayout layout;
	private DisplayBlock[][] blocks;
	
	// Constructor
	public GameBoard() {
		initLayout();
		initBoard();
	}
	
	private void initLayout() {
		layout = new GridLayout(BLOCK_DIMENSION, BLOCK_DIMENSION);
		this.setLayout(layout);
	}
	
	private void initBoard() {
		// Initialize blocks array
		blocks = new DisplayBlock[BLOCK_DIMENSION][BLOCK_DIMENSION];
		
		// Initialize 3x3 (total of 9) blocks, put them into the layout.
		for (int row = 0; row < BLOCK_DIMENSION; row++) {
			for (int col = 0; col < BLOCK_DIMENSION; col++) {
				blocks[row][col] = new DisplayBlock(row, col);
				this.add(blocks[row][col]);
			}
		}
	}
	
	/** Sets the numbers in the board. */
	public void setNumbers() {
		for (int row = 0; row < BLOCK_DIMENSION; row++) {
			for (int col = 0; col < BLOCK_DIMENSION; col++) {
				blocks[row][col].setNumbers();
			}
		}
	}
	
	/** Clears all numbers on board. */
	public void clearNumbers() {
		for (int row = 0; row < BLOCK_DIMENSION; row++) {
			for (int col = 0; col < BLOCK_DIMENSION; col++) {
				blocks[row][col].clearNumbers();
			}
		}
	}
}
