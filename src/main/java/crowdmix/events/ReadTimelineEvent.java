package crowdmix.events;

public class ReadTimelineEvent implements Event {
    private final String userName;

    public ReadTimelineEvent(final String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
