package cm.values;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class UserTest {

    private final String userName = "Charlie";
    private final String otherUserName = "Bob";

    @Test
    public void userNameGetter() {
        assertEquals(userName, new User(userName).getUserName());
    }

    @Test
    public void isCreatedWithNoPosts() {
        assertEquals(0, new User(userName).getPostIds().size());
    }

    @Test
    public void isCreatedWithFollowingHerself() {
        final List<User> followedUsers = new User(userName).getFollowedUsers();
        assertEquals(1, followedUsers.size());
        assertEquals(userName, followedUsers.get(0).getUserName());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void accessorReturnsUnmodifiableList() {
        new User(userName).getPostIds().add(new Random().nextLong());
    }

    @Test
    public void canAddAPost() {
        final User user = new User(userName);
        final Long id = new Random().nextLong();
        user.addPost(id);
        assertEquals(singletonList(id), user.getPostIds());
    }

    @Test
    public void canAddAFollowedUser() {
        final User user = new User(userName);
        final User otherUser = new User(otherUserName);
        user.addFollowedUser(otherUser);
        assertEquals(Arrays.asList(user, otherUser), user.getFollowedUsers());
    }
}