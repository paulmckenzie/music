package crowdmix.main;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static crowdmix.main.FollowCommand.NullMessage;
import static crowdmix.main.Users.ALICE;
import static crowdmix.main.Users.CHARLIE;
import static org.junit.Assert.assertEquals;

public class FollowCommandTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void cannotCreateCommandWithNullTarget() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NullMessage);
        new FollowCommand(null, ALICE);
    }

    @Test
    public void cannotCreateCommandWithNullFollows() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NullMessage);
        new FollowCommand(CHARLIE, null);
    }

    @Test
    public void testTargetAccessor() throws Exception {
        assertEquals(CHARLIE, new FollowCommand(CHARLIE, ALICE).getTarget());
    }

    @Test
    public void testFollowsAccessor() throws Exception {
        assertEquals(ALICE, new FollowCommand(CHARLIE, ALICE).getFollows());
    }
}