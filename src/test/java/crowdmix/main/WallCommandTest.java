package crowdmix.main;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static crowdmix.main.Users.ALICE;
import static crowdmix.main.WallCommand.NullMessage;
import static org.junit.Assert.assertEquals;

public class WallCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test()
    public void cannotCreateCommandWithNullTarget() throws Throwable {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(NullMessage);
        new WallCommand(null);
    }

    @Test
    public void canReadTarget() throws Throwable {
        assertEquals(ALICE, new WallCommand(ALICE).getTarget());
    }
}