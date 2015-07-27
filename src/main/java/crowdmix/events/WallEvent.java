package crowdmix.events;

public class WallEvent implements Event {
    private final String userName;

    public WallEvent(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
