package crowdmix.events;

import crowdmix.events.*;
import crowdmix.main.TimeProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static crowdmix.Users.ALICE;
import static crowdmix.Users.BOB;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class EventParserTest {
    private final Long aTimeStamp = 29401L;
    @Mock
    private TimeProvider timeProvider;
    private EventParser parser;

    @Before
    public void setUp() {
        parser = new EventParser(timeProvider);
    }

    @Test
    public void canParseReadTimelineEvent() throws Throwable {
        final ReadTimelineEvent event = (ReadTimelineEvent) parser.parseEvent(ALICE);
        assertEquals(ALICE, event.getUserName());
    }

    @Test
    public void canParseUserFollowsEvent() throws Throwable {
        final UserFollowsEvent followCommand = (UserFollowsEvent) parser.parseEvent(String.format("%s follows %s", BOB, ALICE));
        assertEquals(BOB, followCommand.getUserName());
        assertEquals(ALICE, followCommand.getFollowedUser());
    }

    @Test
    public void canParseWallEvent() throws Throwable {
        final WallEvent event = (WallEvent) parser.parseEvent(String.format("%s wall", BOB));
        assertEquals(BOB, event.getUserName());
    }

    @Test
    public void canParseMessagePostedEvent() throws Throwable {
        Mockito.when(timeProvider.now()).thenReturn(aTimeStamp);
        final String message = "Good game though!";
        final MessagePostedEvent event = (MessagePostedEvent) parser.parseEvent(String.format("%s -> %s", BOB, message));
        assertEquals(BOB, event.getUserName());
        assertEquals(aTimeStamp, event.getTimestamp());
        assertEquals(message, event.getMessage());
    }
}
