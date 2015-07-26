package crowdmix.main;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static crowdmix.main.FollowCommand.NullMessage;
import static org.junit.Assert.assertEquals;

public class FollowCommandTest {
    static final String target = "Charlie";
    static final String follows = "Alice";
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testCanCreate() throws Exception {
        new FollowCommand(target, follows);
    }

    @Test
    public void cannotCreateCommandWithNullTarget() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NullMessage);
        new FollowCommand(null, follows);
    }

    @Test
    public void cannotCreateCommandWithNullFollows() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NullMessage);
        new FollowCommand(target, null);
    }

    @Test
    public void testTargetAccessor() throws Exception {
        assertEquals(target, new FollowCommand(target, follows).getTarget());
    }

    @Test
    public void testFollowsAccessor() throws Exception {
        assertEquals(follows, new FollowCommand(target, follows).getFollows());
    }
}