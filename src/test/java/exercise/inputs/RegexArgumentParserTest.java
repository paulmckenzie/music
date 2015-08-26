package exercise.inputs;

import exercise.values.InputArgs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RegexArgumentParserTest {

    private RegexArgumentParser regexArgumentParser;

    @Before
    public void setUp() throws Exception {
        regexArgumentParser = new RegexArgumentParser();
    }

    @Test
    public void testParsePost() throws Exception {
        final String userName1 = "Alice";
        final String messageText = "some text here";
        assertEquals(new InputArgs(InputType.POST, userName1, of(messageText)), regexArgumentParser.parse(userName1 + " -> " + messageText));
    }

    @Test
    public void testParseRead() throws Exception {
        final String userName1 = "Alice";
        assertEquals(new InputArgs(InputType.READ, userName1, empty()), regexArgumentParser.parse(userName1));
    }

    @Test
    public void testParseWall() throws Exception {
        final String userName1 = "Alice";
        assertEquals(new InputArgs(InputType.WALL, userName1, empty()), regexArgumentParser.parse(userName1 + " wall"));
    }

    @Test
    public void testParseFollows() throws Exception {
        final String userName1 = "Alice";
        final String userName2 = "Bob";
        assertEquals(new InputArgs(InputType.FOLLOW, userName1, of(userName2)), regexArgumentParser.parse(userName1 + " follows " + userName2));
    }
}