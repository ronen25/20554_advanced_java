import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Graph extends JPanel {
    // Properties
    GraphAxis hAxis, vAxis;
    GraphView graphView;

    /** Constructor */
    public Graph() {
        // Initialize UI
        initUI();
    }

    /** Initializes component UI */
    private void initUI() {
        // Initialize layout
        this.setLayout(new BorderLayout());

        // Initialize graph TODO

        // Initialize axis
        hAxis = new GraphAxis(0, 100, 10, true);
        vAxis = new GraphAxis(0, 100, 10, false);

        // Add all components
        this.add(hAxis, BorderLayout.SOUTH);
        this.add(vAxis, BorderLayout.WEST);
    }
}
