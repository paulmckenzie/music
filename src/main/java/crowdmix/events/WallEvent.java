package crowdmix.events;

class WallEvent implements Event {
    private final String userName;

    WallEvent(final String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
