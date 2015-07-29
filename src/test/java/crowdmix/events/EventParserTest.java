package crowdmix.events;

import crowdmix.main.TimeProvider;
import crowdmix.main.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EventParserTest {
    private static final User ALICE = new User("Alice");
    @Mock
    private TimeProvider timeProvider;
    private EventParser parser;

    @Before
    public void setUp() {
        parser = new EventParser(timeProvider);
    }

    @Test
    public void canParseReadTimelineEvent() {
        final ReadTimelineEvent event = (ReadTimelineEvent) parser.parseEvent(ALICE.getUserName());
        assertEquals(ALICE.getUserName(), event.getUserName());
    }

    @Test
    public void canParseUserFollowsEvent() {
        final UserFollowsEvent followCommand = (UserFollowsEvent) parser.parseEvent("BOB follows ALICE");
        assertEquals("BOB", followCommand.getUserName());
        assertEquals("ALICE", followCommand.getFollowedUser());
    }

    @Test
    public void canParseWallEvent() {
        final WallEvent event = (WallEvent) parser.parseEvent("BOB wall");
        assertEquals("BOB", event.getUserName());
    }

    @Test
    public void canParseMessagePostedEvent() {
        final Long aTimeStamp = 29401L;
        Mockito.when(timeProvider.now()).thenReturn(aTimeStamp);
        final String message = "Good game though!";
        final MessagePostedEvent event = (MessagePostedEvent) parser.parseEvent(String.format("BOB -> %s", message));
        assertEquals("BOB", event.getUserName());
        assertEquals(aTimeStamp, event.getTimestamp());
        assertEquals(message, event.getMessage());
    }
}
