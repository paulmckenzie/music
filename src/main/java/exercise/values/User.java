package exercise.values;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class User {
    private final String userName;
    private final List<Long> postIds = new ArrayList<>();
    private final List<User> followedUsers = new ArrayList<>();

    public User(final String userName) {
        this.userName = userName;
        followedUsers.add(this);
    }

    public String getUserName() {
        return userName;
    }

    public List<Long> getPostIds() {
        return unmodifiableList(postIds);
    }

    public void addPost(final Long id) {
        postIds.add(id);
    }

    public List<User> getFollowedUsers() {
        return unmodifiableList(followedUsers);
    }

    public void addFollowedUser(User followedUser) {
        followedUsers.add(followedUser);
    }


}
