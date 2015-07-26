package crowdmix.main;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static crowdmix.main.ReadTimelineCommand.NullMessage;
import static crowdmix.main.Users.ALICE;
import static org.junit.Assert.assertEquals;


public class ReadTimelineCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test()
    public void cannotCreateCommandWithNullTarget() throws Throwable {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NullMessage);
        new ReadTimelineCommand(null);
    }

    @Test
    public void canReadTarget() throws Throwable {
        assertEquals(ALICE, new ReadTimelineCommand(ALICE).getTarget());
    }
}