import java.util.Date;

public class ServiceMessage extends Message {
    // Constants
    public static final int SERVICE_SUBSCRIBE = 0;
    public static final int SERVICE_UNSUBSCRIBE = 1;

    // Properties
    private int serviceType;

    // Cnstr.
    public ServiceMessage(ConnectionDetails origin, Date date, int type) {
        super(origin, date);

        // Set service type
        setServiceType(type);
    }

    public int getServiceType() {
        return serviceType;
    }

    public void setServiceType(int serviceType) throws IllegalArgumentException {
        // Check bounds
        if (serviceType != SERVICE_SUBSCRIBE && serviceType != SERVICE_UNSUBSCRIBE)
            throw new IllegalArgumentException("Illegal or unknown operation: " + serviceType);

        this.serviceType = serviceType;
    }

    /** Utillity method for converting the service type int to a string. */
    private String serviceTypeToString() {
        switch (this.serviceType) {
            case SERVICE_SUBSCRIBE:
                return "Subscribed";
            case SERVICE_UNSUBSCRIBE:
                return "Unsubscribed";
            default:
                return "(unknown)";
        }
    }

    @Override
    public String toString() {
        return super.toString() + serviceTypeToString();
    }
}
