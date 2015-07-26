package crowdmix.main;

import org.junit.Test;

import static crowdmix.main.CommandType.*;
import static org.junit.Assert.*;

public class CommandTypeTest {
    @Test
    public void fromToken() throws Throwable {
        assertEquals(READ, CommandType.fromToken(null));
        assertEquals(POST, CommandType.fromToken("->"));
        assertEquals(WALL, CommandType.fromToken("wall"));
        assertEquals(FOLLOW, CommandType.fromToken("follows"));
    }

}