package exercise.values;

import java.time.LocalDateTime;

public class Message {
    private final Long id;
    private final String text;
    private final LocalDateTime timeStamp;

    public Message(Long id, String text, LocalDateTime timeStamp) {
        this.id = id;
        this.text = text;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getTimestamp() {
        return timeStamp;
    }
}
