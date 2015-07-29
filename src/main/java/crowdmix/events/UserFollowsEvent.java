package crowdmix.events;

public class UserFollowsEvent implements Event {
    private final String userName;
    private final String followedUser;

    public UserFollowsEvent(final String userName, final String followedUser) {
        this.userName = userName;
        this.followedUser = followedUser;
    }

    public String getUserName() {
        return userName;
    }

    public String getFollowedUser() {
        return followedUser;
    }
}