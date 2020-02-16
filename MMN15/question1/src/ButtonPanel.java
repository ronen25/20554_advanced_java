import javax.swing.*;
import java.awt.*;

/** Button panel component for the main window. */
public class ButtonPanel extends JPanel {
    // Properties
    private JButton btnClear, btnGo;

    // ButtonPanel
    public ButtonPanel() {
        initButtons();
    }

    /** Initializes the buttons. */
    private void initButtons() {
        setLayout(new FlowLayout());

        // Go button
        btnGo = new JButton("Go");

        // Clear button
        btnClear = new JButton("Clear");

        // Add
        this.add(btnGo);
        this.add(btnClear);
    }

    /** Returns the Clear button. */
    public JButton getClearButton() {
        return btnClear;
    }

    /** Returns the Go button. */
    public JButton getGoButton() {
        return btnGo;
    }
}
