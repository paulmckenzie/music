package crowdmix.main;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static crowdmix.main.PostMessageCommand.NullMessage;
import static crowdmix.main.Users.BOB;
import static org.junit.Assert.assertEquals;

public class PostMessageCommandTest {
    private static final String message = "Good Game Though!";
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void cannotCreateCommandWithNullTarget() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NullMessage);
        new PostMessageCommand(null, message);
    }

    @Test
    public void cannotCreateCommandWithNullMessage() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NullMessage);
        new PostMessageCommand(BOB, null);
    }

    @Test
    public void canReadTargetAfterCreation() throws Throwable {
        assertEquals(BOB, new PostMessageCommand(BOB, message).getTarget());
    }

    @Test
    public void canReadMessageAfterCreation() throws Throwable {
        assertEquals(message, new PostMessageCommand(BOB, message).getMessage());
    }
}