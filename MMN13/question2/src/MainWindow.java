import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
	// Constants
	private static final String MAIN_WINDOW_TITLE = "Sudoku";
	private final int WINDOW_WIDTH = 600;
	private final int WINDOW_HEIGHT = 600;
	
	// Properties
	private BorderLayout layout;
	private GameBoard board;
	private ButtonPanel btnPanel;
	
	// Constructor
	public MainWindow() {
		super(MAIN_WINDOW_TITLE);
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initUI();
		initEvents();
	}
	
	private void initUI() {
		// Initialize and set layout
		layout = new BorderLayout();
		this.setLayout(layout);
		
		// Init game board
		board = new GameBoard();
		this.add(board, BorderLayout.CENTER);
		
		// Init button panel
		btnPanel = new ButtonPanel();
		this.add(btnPanel, BorderLayout.SOUTH);
	}
	
	/** Initialize UI events */
	private void initEvents() {
		// Initialize set button event
		btnPanel.getBtnSet().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("DEBUG: SET BUTTON PRESSED");
				board.setNumbers();
			}
		});
		
		// Initialize clear button event
		btnPanel.getBtnClear().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("DEBUG: CLEAR BUTTON PRESSED");
				board.clearNumbers();
			}
		});
	}
}
