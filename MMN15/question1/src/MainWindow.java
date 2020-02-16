import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Main application window */
public class MainWindow extends JFrame {
    // Constants
    private final int WINDOW_WIDTH = 700;
    private final int WINDOW_HEIGHT = 700;

    // Properties
    private int numThreads, numPasses;
    private ImageDisplay display;
    private ButtonPanel btnPanel;
    private ImageCrunchWorker crunchWorker;

    // Cnstr.
    public MainWindow(int dimension, int threads, int passes) {
        numThreads = threads;
        numPasses = passes;

        initFrame();
        initUI(dimension);

        initActions();
    }

    /** Initialize the frame itself. */
    private void initFrame() {
        setTitle("Shrink");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /** Initialize UI */
    private void initUI(int dimension) {
        // Initialize layout
        this.setLayout(new BorderLayout());

        // Initialize display
        display = new ImageDisplay(dimension);

        // Initialize button panel
        btnPanel = new ButtonPanel();

        // Add everything to the window
        this.add(display, BorderLayout.CENTER);
        this.add(btnPanel, BorderLayout.SOUTH);
    }

    /** Initialize button actions. */
    private void initActions() {
        // "Clear" button event
        btnPanel.getClearButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                clearButtonClicked();
            }
        });

        // "Go" button event
        btnPanel.getGoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goButtonClicked();
            }
        });
    }

    /** Event callback for the "clear" button. */
    private void clearButtonClicked() {
        display.clearBoard();
    }

    /** Event callback for the go/stop button. */
    private void goButtonClicked() {
        crunchWorker = new ImageCrunchWorker(display, btnPanel.getGoButton(), numPasses, numThreads);
        crunchWorker.execute();
    }
}
