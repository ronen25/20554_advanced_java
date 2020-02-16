import javax.swing.*;
import java.awt.*;

/** A component through which the admin of the server can write his message to the subscribed clients. */
public class MessageInput extends JPanel {
    // Constants
    private final String PUBLISH_BUTTON_TEXT = "Publish";

    // Properties
    private JTextField textField;
    private JButton btnPublish;

    // Cnstr.
    public MessageInput() {
        initUI();
    }

    /** Initializes the component UI. */
    private void initUI() {
        // Initialize layout
        this.setLayout(new BorderLayout());

        // Initialize and put textbox
        textField = new JTextField();
        this.add(textField, BorderLayout.CENTER);

        // Initialize and put button
        btnPublish = new JButton(PUBLISH_BUTTON_TEXT);
        this.add(btnPublish, BorderLayout.EAST);
    }

    /** Returns the text in the text input box. */
    public String getMessageText() {
        return textField.getText();
    }

    /** Returns the Publish button. Used by this component's users to register a callback for that button. */
    public JButton getPublishButton() {
        return btnPublish;
    }
}
