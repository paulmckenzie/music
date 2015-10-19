package exercise.inputs;

import exercise.domain.TimeProvider;
import exercise.values.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReadMessageFormatterTest {
    private static final String TEXT = "Lorem ipsum dolor sit amet.";
    private static final String USER_NAME = "Alice";
    private final LocalDateTime now = LocalDateTime.now();

    @Mock
    private TimeProvider timeProvider;

    @Test
    public void canFormat() throws Throwable {
        when(timeProvider.now()).thenReturn(now);
        final ReadMessageFormatter formatter = new ReadMessageFormatter(timeProvider);
        final String formattedOutput = formatter.format(aMessage(now.minusSeconds(20)));
        assertEquals(format(ReadMessageFormatter.FORMAT, TEXT, "20 seconds"), formattedOutput);
    }

    private Message aMessage(LocalDateTime timestamp) {
        return new Message(22L, USER_NAME, TEXT, timestamp);
    }
}