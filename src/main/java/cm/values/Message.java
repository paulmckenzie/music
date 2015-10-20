package cm.values;

import java.time.LocalDateTime;

public class Message {
    private final Long id;
    private final String userName;
    private final String text;
    private final LocalDateTime timestamp;

    public Message(Long id, String userName, String text, LocalDateTime timestamp) {
        this.id = id;
        this.userName = userName;
        this.text = text;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
