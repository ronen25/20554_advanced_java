import java.net.InetAddress;

public class ConnectionDetails {
    // Properties
    private InetAddress address;
    private int port;

    // Cnstr.
    public ConnectionDetails(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return address.toString() + ":" + this.port;
    }

    @Override
    public boolean equals(Object obj) {
        // Check if it's a ConnectionDetails object.
        if (!(obj instanceof ConnectionDetails))
            return false;

        // Cast
        ConnectionDetails other = (ConnectionDetails)obj;

        // Compare items
        return this.address.equals((Object)other.address) && (this.port == other.port);
    }
}
