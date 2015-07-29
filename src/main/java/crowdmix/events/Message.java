package crowdmix.events;

public class Message {
    private final Long id;
    private final String text;

    public Message(final Long id, final String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
