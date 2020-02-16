import java.io.Serializable;
import java.util.Date;

public abstract class Message implements Serializable {
    // Properties
    protected Date date;
    protected ConnectionDetails origin;

    // Cnstr.
    public Message(ConnectionDetails origin, Date date) {
        this.origin = origin;
        this.date = date;
    }

    /** Gets the message's publication date. */
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ConnectionDetails getOrigin() {
        return origin;
    }

    public void setOrigin(ConnectionDetails origin) {
        this.origin = origin;
    }

    public String toString() {
        return this.date.toString() + "\t" + this.origin.toString() + " - ";
    }
}
