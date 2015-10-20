package cm.inputs;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElapsedTimeFormatterTest {
    private DurationFormatter durationFormatter = new ElapsedTimeFormatter();
    @Test
    public void canFormatOneSecondAgo() {
        assertEquals("1 second", durationFormatter.formatDuration(1));
    }

    @Test
    public void canFormatTwoSecondsAgo() {
        assertEquals("2 seconds", durationFormatter.formatDuration(2));
    }

    @Test
    public void canFormat59SecondsAgo() {
        assertEquals("59 seconds", durationFormatter.formatDuration(59));
    }

    @Test
    public void canFormat60SecondsAgo() {
        assertEquals("1 minute", durationFormatter.formatDuration(60));
    }

    @Test
    public void canFormat61SecondsAgo() {
        assertEquals("1 minute", durationFormatter.formatDuration(61));
    }

    @Test
    public void canFormatTwoMinutesAgo() {
        assertEquals("2 minutes", durationFormatter.formatDuration(120));
    }
}