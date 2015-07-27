package crowdmix.events;

import crowdmix.events.EventType;
import org.junit.Test;

import static crowdmix.events.EventType.*;
import static org.junit.Assert.assertEquals;

public class EventTypeTest {
    @Test
    public void fromToken() throws Throwable {
        assertEquals(READ, EventType.fromToken(null));
        assertEquals(POST, EventType.fromToken("->"));
        assertEquals(WALL, EventType.fromToken("wall"));
        assertEquals(FOLLOW, EventType.fromToken("follows"));
    }

}