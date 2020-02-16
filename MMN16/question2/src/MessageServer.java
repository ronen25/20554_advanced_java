import javax.swing.*;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class MessageServer {
    // Constants
    private final int SERVER_DEFAULT_PORT = 6666;
    private final int BUFFER_SIZE = 512;

    // Properties
    private ConnectionDetails serverDetails;
    private DatagramSocket socket;
    private int port;
    private boolean isListening;
    private ArrayList<ConnectionDetails> activeConnections;
    private Thread connectionThread;

    // Cnstr.
    public MessageServer() {
        activeConnections = new ArrayList<>();
        this.port = SERVER_DEFAULT_PORT;
    }

    // Cnstr. with customizable port
    public MessageServer(int port) {
        this();
        this.port = port;
    }

    /** Starts the server. */
    public synchronized void startServer() throws SocketException, Exception {
        // Initialize the socket
        socket = new DatagramSocket(this.port);

        // Initialize the connection thread, if it's not initialized already
        if (connectionThread != null && connectionThread.isAlive())
            throw new Exception("Cannot start two MessageServers; stop() the other one instead!");

        connectionThread = new Thread() {
            @Override
            public void run() {
                super.run();

                // Flip the flag first.
                setIsListening(true);

                // Set server details
                serverDetails = new ConnectionDetails(socket.getInetAddress(), socket.getPort());

                // While we're meant to listen to incoming connections (which we check in a synchronized manner)...
                while (getIsListening()) {
                    try {
                        byte[] buffer = new byte[BUFFER_SIZE];

                        // Receive a request.
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet);

                        // We've received a request.
                        // The request is (hopefully) a serialized Message object, so we need do deserialize
                        // it back to a Java object.
                        try (ByteArrayInputStream rawByteStream = new ByteArrayInputStream(buffer)) {
                            try (ObjectInputStream inputStream = new ObjectInputStream(rawByteStream)) {
                                Message messageObject = (Message) inputStream.readObject();

                                // Handle the message. The handleMessage() function will dispatch
                                // it to an other handler, depending on the message type.
                                handleMessage(messageObject);
                            }
                        }
                    } catch (IOException ioEx) {
                        JOptionPane.showMessageDialog(null, ioEx.getMessage(),
                                "I/O Error",
                                JOptionPane.ERROR_MESSAGE);
                    } catch (ClassNotFoundException classEx) {
                        JOptionPane.showMessageDialog(null, classEx.getMessage(),
                                "Java Object Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        };

        // Launch the thread
        connectionThread.start();
    }

    /**
     * Stops the server from listening to new connections.
     */
    public synchronized void stopServer() {
        // Set the listening flag. This will flag the connection thread to stop.
        setIsListening(false);

        // Wait for the thread to exit.
        try {
            connectionThread.join();
        } catch (InterruptedException ex) {
        }

        // Close the socket.
        socket.close();
    }

    /**
     * Sets whether the server should be listening to new connections. Used internally.
     */
    private void setIsListening(boolean value) {
        synchronized ((Object) isListening) {
            isListening = value;
        }
    }

    /**
     * Gets whether the server is currently listening to new incoming connections.
     */
    private boolean getIsListening() {
        boolean value;

        synchronized ((Object) isListening) {
            value = isListening;
        }

        return value;
    }

    /**
     * General handler for all types of messages.
     * Effectively, this method checks the message type and dispatches (or errors)
     * when necessary.
     */
    private void handleMessage(Message msg) throws ClassNotFoundException {
        // Check what type of message we have.
        if (msg instanceof ServiceMessage)
            handleServiceMessage((ServiceMessage) msg);
        else if (msg instanceof TextMessage)
            handleTextMessage((TextMessage) msg);
        else
            throw new ClassNotFoundException("Got class type " + msg.getClass().getName() + " instead of '? extends Message'");
    }

    /**
     * Handler for service messages (i.e. subscribes and unsubscribes).
     */
    private void handleServiceMessage(ServiceMessage serviceMessage) {
        // Check message type
        switch (serviceMessage.getServiceType()) {
            case ServiceMessage.SERVICE_SUBSCRIBE:
                // Subscribe the client if it does not already exist.
                // If it does, it's a NOP.
                if (!activeConnections.contains(serviceMessage.getOrigin()))
                    activeConnections.add(serviceMessage.getOrigin());

                break;
            case ServiceMessage.SERVICE_UNSUBSCRIBE:
                // Unsubscribe if the client exists.
                // If it does not, it's a NOP.
                activeConnections.remove(serviceMessage.getOrigin());

                break;
        }
    }

    /**
     * Handler for text messages.
     */
    private void handleTextMessage(TextMessage textMessage) {
        // Store message origin
        ConnectionDetails originator = textMessage.getOrigin();

        // We modify the textMessage object so that the origin is now the server
        textMessage.setOrigin(this.serverDetails);

        try (ByteArrayOutputStream byteOutput = new ByteArrayOutputStream()) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(byteOutput)) {
                // For every client we have, send it the message. We do this by manipulating
                // the textMessage object itself.
                for (ConnectionDetails clientDetails: this.activeConnections) {
                    // Do not send the message to the originating client!
                    if (clientDetails.equals(originator))
                        continue;

                    // Serialize the message
                    outputStream.writeObject((Object)textMessage);

                    // Create a datagram to send.
                    // The send details are the client details.
                    byte[] encodedMessage = byteOutput.toByteArray();
                    DatagramPacket packet = new DatagramPacket(encodedMessage, encodedMessage.length,
                            clientDetails.getAddress(), clientDetails.getPort());
                    this.socket.send(packet);

                    // Reset the streams for the next iteration.
                    outputStream.reset();
                    byteOutput.reset();
                }
            }
        }
        catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
