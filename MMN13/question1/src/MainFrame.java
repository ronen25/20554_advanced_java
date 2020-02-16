import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    // Constants
    private static final String TITLE = "Graph Window";
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    // Properties
    private ButtonPanel pnlButtons;
    private Graph graph;

    /** Constructor */
    public MainFrame() {
        // Set frame properties
        setTitle(TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        // Initialize the UI components
        initUI();
    }

    /** Initializes the frame UI */
    private void initUI() {
        // Initialize and set frame layout
        this.setLayout(new BorderLayout());

        // Initialize graph
        graph = new Graph();

        // Initialize button panel
        pnlButtons = new ButtonPanel();

        // Add both
        this.add(graph, BorderLayout.CENTER);
        this.add(pnlButtons, BorderLayout.SOUTH);
    }
}
