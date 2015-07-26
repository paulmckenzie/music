package crowdmix.main;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static crowdmix.main.PostMessageCommand.NullMessage;

public class PostMessageCommandTest {
    private static final String target = "Bob";
    private static final String message = "Good Game Though!";
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void canCreateCommand() throws Exception {
        new PostMessageCommand(target, message);
    }

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
        new PostMessageCommand(target, null);
    }

    @Test
    public void canReadTargetAfterCreation() throws Throwable {
        Assert.assertEquals(target, new PostMessageCommand(target, message).getTarget());
    }

    @Test
    public void canReadMessageAfterCreation() throws Throwable {
        Assert.assertEquals(message, new PostMessageCommand(target, message).getMessage());
    }
}