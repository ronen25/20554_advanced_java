import javax.swing.*;
import java.awt.*;
import java.net.SocketException;

/** Server UI */
public class ServerWindow extends JFrame {
    // Constants
    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 150;
    private final String WINDOW_TITLE = "Pub/Sub Server";

    // Properties
    private MessageServer server;
    private MessageInput messageInput;
    private JButton jbtnExit;

    // Cnstr.
    public ServerWindow() {
        initUI();
        initServer();
    }

    private void initUI() {
        // Initialize frame
        setTitle(WINDOW_TITLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize layout and the container panel
        this.setLayout(new BorderLayout());

        // Initialize message input control
        messageInput = new MessageInput();
        this.add(messageInput, BorderLayout.CENTER);

        // Initialize the exit button
        jbtnExit = new JButton("Exit");
        this.add(jbtnExit, BorderLayout.SOUTH);
    }

    private void initServer() {
        server = new MessageServer();
        try {
            server.startServer();
        }
        catch (SocketException sockEx) {
            JOptionPane.showMessageDialog(this, sockEx.getMessage(), "Socket Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
