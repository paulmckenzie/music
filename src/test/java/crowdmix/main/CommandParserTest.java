package crowdmix.main;

import org.junit.Test;

import static crowdmix.main.CommandParser.parse;
import static crowdmix.main.Users.ALICE;
import static crowdmix.main.Users.BOB;
import static org.junit.Assert.assertEquals;

public class CommandParserTest {

    @Test
    public void canParseReadTimelineCommand() throws Throwable {
        final ReadTimelineCommand command = (ReadTimelineCommand) parse(ALICE);
        assertEquals(ALICE, command.getTarget());
    }

    @Test
    public void canParseFollowCommand() throws Throwable {
        final FollowCommand followCommand = (FollowCommand) parse(String.format("%s follows %s", BOB, ALICE));
        assertEquals(BOB, followCommand.getTarget());
        assertEquals(ALICE, followCommand.getFollows());
    }

    @Test
    public void canParseWallCommand() throws Throwable {
        final WallCommand command = (WallCommand) parse(String.format("%s wall", BOB));
        assertEquals(BOB, command.getTarget());
    }

    @Test
    public void canParsePostMessageCommand() throws Throwable {
        final String message = "Good game though!";
        final PostMessageCommand command = (PostMessageCommand) parse(String.format("%s -> %s", BOB, message));
        assertEquals(BOB, command.getTarget());
        assertEquals(message, command.getMessage());
    }
}
