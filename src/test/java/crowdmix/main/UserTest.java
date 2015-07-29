package crowdmix.main;

import org.junit.Test;

import java.util.Random;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class UserTest {

    private final String userName = "Charlie";

    @Test
    public void userNameGetter() {
        assertEquals(userName, new User(userName).getUserName());
    }

    @Test
    public void isCreatedWithNoPosts() {
        assertEquals(0, new User(userName).getPostIds().size());
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
}