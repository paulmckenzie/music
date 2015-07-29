package crowdmix.events;

public class MessagePostedEvent implements Event {
    private final String userName;
    private final Long timestamp;
    private final String message;

    public MessagePostedEvent(final String userName, final Long timestamp, final String message) {
        this.userName = userName;
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}