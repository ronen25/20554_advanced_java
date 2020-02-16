import java.util.Date;

public class TextMessage extends Message {
    // Properties
    private String message;

    // Cnstr.
    public TextMessage(ConnectionDetails origin, Date date, String message) {
        super(origin, date);

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return super.toString() + message;
    }
}
