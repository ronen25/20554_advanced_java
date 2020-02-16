import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class DisplayBlock extends JPanel {
	// Constants
	private final int TABLE_DIMENSION = 3;
	private final int CELL_NO_VALUE = 0;
	private final float FONT_MULTIPLIER = 2.0f;
		
	// Properties
	private int row, column;
	private GridLayout layout;
	private JTextField[][] textboxes;
	private int[][] values;

	// Constructor
	public DisplayBlock(int row, int column) {
		this.row = row;
		this.column = column;
		
		// Initialize layout and UI
		initLayout();
		initData();
		initUI();
	}
	
	/** Initialize all data structures */
	private void initData() {
		// Initialize the text fields.
		textboxes = new JTextField[TABLE_DIMENSION][TABLE_DIMENSION];
		
		// Initialize the data array
		values = new int[TABLE_DIMENSION][TABLE_DIMENSION];
		for (int row = 0; row < TABLE_DIMENSION; row++) {
			for (int col = 0; col < TABLE_DIMENSION; col++) {
				values[row][col] = CELL_NO_VALUE;
			}
		}
	}
	
	/** Initialize block layout */
	private void initLayout() {
		layout = new GridLayout(TABLE_DIMENSION, TABLE_DIMENSION);
		this.setLayout(layout);
	}
	
	/** Initialize block UI */
	private void initUI() {
		// Set a nicer, bigger font.
		// The font is based on the current font, but is bold and double in size.
		Font currentFont = this.getFont();
		Font textboxFont = currentFont
					.deriveFont(currentFont.getSize() * FONT_MULTIPLIER)
					.deriveFont(Font.BOLD);
		
		// Initialize and put the cells in place
		for (int row = 0; row < TABLE_DIMENSION; row++) {
			for (int col = 0; col < TABLE_DIMENSION; col++) {
				// Initialize the text box, with the nicer font.
				// Also don't forget to center the text for extra niceness :-)
				textboxes[row][col] = new JTextField();
				textboxes[row][col].setFont(textboxFont);
				textboxes[row][col].setHorizontalAlignment(JTextField.CENTER);

				// Add to layout
				this.add(textboxes[row][col]);
			}
		}
		
		// For looks, we might want to set the background of the block
		// to be gray, to make it look like the booklet.
		// For that - calculate the number of the block (i.e. 0, 3, etc.)
		int blockIndex = this.row * TABLE_DIMENSION + this.column;
		if (blockIndex % 2 == 0) {
			// This block is in an even position, so color all cells GRAY.
			for (int row = 0; row < TABLE_DIMENSION; row++) {
				for (int col = 0; col < TABLE_DIMENSION; col++) {
					textboxes[row][col].setBackground(Color.GRAY);
				}
			}
		}
	}
	
	/** Adds a keyboard handler to every cell in the block. */
	public void addCellEventHandler(ActionListener handler) {
		// Set the event for every cell
		for (int row = 0; row < TABLE_DIMENSION; row++) {
			for (int col = 0; col < TABLE_DIMENSION; col++) {
				textboxes[row][col].addActionListener(handler);
			}
		}
	}
	
	/** Retrieves the values in the particular column of the block. */
	public ArrayList<Integer> getColumnValues(int colIndex) 
		throws IndexOutOfBoundsException {
		// Check column index
		if (colIndex < 0 || colIndex > TABLE_DIMENSION)
			throw new IndexOutOfBoundsException("Column index must be in range!");
		
		// Initialize the column values array, and retrieve all values from the data array.
		ArrayList<Integer> colValues = new ArrayList<Integer>();
		for (int row = 0; row < TABLE_DIMENSION; row++) {
			int value = values[row][colIndex];
			colValues.add(value);
		}
		
		return colValues;
	}
	
	/** Retrieves the values in the particular row of the block. */
	public ArrayList<Integer> getRowValues(int rowIndex) 
		throws IndexOutOfBoundsException {
		// Check column index
		if (rowIndex < 0 || rowIndex > TABLE_DIMENSION)
			throw new IndexOutOfBoundsException("Row index must be in range!");
		
		// Initialize the column values array, and retrieve all values from the data array.
		ArrayList<Integer> colValues = new ArrayList<Integer>();
		for (int col = 0; col < TABLE_DIMENSION; col++) {
			int value = values[rowIndex][col];
			colValues.add(value);
		}
		
		return colValues;
	}
	
	/** Gets the cell value from a row and a column. */
	public int getCellValue(int row, int col)
		throws IndexOutOfBoundsException {
		// Check if index is in range
		if (row < 0 || row > TABLE_DIMENSION || col < 0 || col > TABLE_DIMENSION)
			throw new IndexOutOfBoundsException("Row/Column indexes must be in range!");
		
		// Return the value directly from the array
		return values[row][col];
	}
	
	/** 
	 * Sets the numbers in the block.
	 * Every cell that has a value in it must become uneditable.
	 * We also copy the preset numbers to the values array.
	 */
	public void setNumbers() {
		for (int row = 0; row < TABLE_DIMENSION; row++) {
			for (int col = 0; col < TABLE_DIMENSION; col++) {
				// Get cell text
				String text = textboxes[row][col].getText();
				
				// If cell has value, make it uneditable
				if (!text.isEmpty()) {
					// Try to parse the value as an int.
					// If the value is incorrect, notify the user by painting the text red.
					try {
						values[row][col] = Integer.parseInt(text);
						textboxes[row][col].setEditable(false);
						textboxes[row][col].setForeground(Color.BLUE);
					}
					catch (NumberFormatException ex) {
						textboxes[row][col].setForeground(Color.RED);
					}
				}
			}
		}
	}
	
	/** Clears the block from any values. It also resets the values data structure. */
	public void clearNumbers() {
		// Clear all cell values.
		for (int row = 0; row < TABLE_DIMENSION; row++) {
			for (int col = 0; col < TABLE_DIMENSION; col++) {
				// Clear cell value in UI and in data.
				textboxes[row][col].setText("");
				textboxes[row][col].setForeground(Color.BLACK);
				values[row][col] = CELL_NO_VALUE;
				
				// Just in case, set it editable too (in case the user has set the numbers before).
				textboxes[row][col].setEditable(true);
			}
		}
	}

	/** Returns the row of the block in the general grid. It is set when constructing the block. */
	public int getRow() {
		return row;
	}

	/** Returns the column of the block in the general grid. It is set when constructing the block. */
	public int getColumn() {
		return column;
	}
}
