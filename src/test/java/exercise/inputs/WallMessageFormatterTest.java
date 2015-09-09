package exercise.inputs;

import exercise.domain.TimeProvider;
import exercise.values.Message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallMessageFormatterTest {
    private final LocalDateTime now = LocalDateTime.now();
    private final String userName = "Alice";
    private final String text = "Hey, nice wallpaper";
    @Mock private TimeProvider timeProvider;
    private WallMessageFormatter wallMessageFormatter;

    @Before
    public void setUp() throws Exception {
        wallMessageFormatter = new WallMessageFormatter(timeProvider);
    }

    @Test
    public void canFormatSecondsAgo() {
        when(timeProvider.now()).thenReturn(now);
        final Message message = aMessage(now.minusSeconds(20));
        final String formattedOutput = wallMessageFormatter.format(message);
        assertEquals(format(WallMessageFormatter.FORMAT, userName, text, "20 seconds"), formattedOutput);
    }

    @Test
    public void canFormatJustNow() {
        when(timeProvider.now()).thenReturn(now);
        final Message message = aMessage(now);
        final String formattedOutput = wallMessageFormatter.format(message);
        assertEquals(format(WallMessageFormatter.FORMAT, userName, text, "0 seconds"), formattedOutput);
    }

    @Test
    public void canFormatOneSecondAgo() {
        when(timeProvider.now()).thenReturn(now);
        final Message message = aMessage(now.minusSeconds(1));
        final String formattedOutput = wallMessageFormatter.format(message);
        assertEquals(format(WallMessageFormatter.FORMAT, userName, text, "1 second"), formattedOutput);
    }

    @Test
    public void canFormatTwoMinutesAgo() {
        when(timeProvider.now()).thenReturn(now);
        final Message message = aMessage(now.minusSeconds(125));
        final String formattedOutput = wallMessageFormatter.format(message);
        assertEquals(format(WallMessageFormatter.FORMAT, userName, text, "2 minutes"), formattedOutput);
    }

    @Test
    public void canFormatOneMinuteAgo() {
        when(timeProvider.now()).thenReturn(now);
        final Message message = aMessage(now.minusSeconds(60));
        final String formattedOutput = wallMessageFormatter.format(message);
        assertEquals(format(WallMessageFormatter.FORMAT, userName, text, "1 minute"), formattedOutput);
    }

    private Message aMessage(LocalDateTime timestamp) {
        return new Message(22L, userName, text, timestamp);
    }
}