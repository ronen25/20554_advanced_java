import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    // Properties
    JButton btnClear, btnSetXAxis, btnSetYAxis;

    // Cnstr.
    public ButtonPanel() {
        // Initialize UI
        initUI();
    }

    /** Initializes the UI for this panel. */
    private void initUI() {
        // Set layout
        this.setLayout(new FlowLayout());

        // Clear button
        btnClear = new JButton("clear");
        this.add(btnClear);

        // Set X Axis button
        btnSetXAxis = new JButton("setXAxis");
        this.add(btnSetXAxis);

        // Set Y Axis button
        btnSetYAxis = new JButton("setYAxis");
        this.add(btnSetYAxis);
    }
}
