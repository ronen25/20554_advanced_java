import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonPanel extends JPanel {
	// Constants
	private static final String SET_BUTTON_TEXT = "set";
	private static final String CLEAR_BUTTON_TEXT = "clear";
	
	// Properties
	private JButton btnSet, btnClear;
	private FlowLayout layout;
	
	// Constructor
	public ButtonPanel() {
		initUI();
	}

	/** Returns (a reference to) the Set button. */
	public JButton getBtnSet() {
		return btnSet;
	}

	/** Returns (a reference to) the Clear button. */
	public JButton getBtnClear() {
		return btnClear;
	}
	
	private void initUI() {
		// Initialize and set the UI
		layout = new FlowLayout();
		this.setLayout(layout);
		
		// Initialize the buttons themselves.
		btnSet = new JButton(SET_BUTTON_TEXT);
		btnClear = new JButton(CLEAR_BUTTON_TEXT);
		
		// Add buttons to the layout
		this.add(btnSet);
		this.add(btnClear);
	}
}
