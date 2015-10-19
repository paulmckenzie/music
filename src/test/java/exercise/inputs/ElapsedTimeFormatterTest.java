package exercise.inputs;

import org.junit.Assert;
import org.junit.Test;

import static exercise.inputs.ElapsedTimeFormatter.formatDuration;
import static org.junit.Assert.assertEquals;

public class ElapsedTimeFormatterTest {
    @Test
    public void canFormatOneSecondAgo() {
        assertEquals("1 second", formatDuration(1));
    }

    @Test
    public void canFormatTwoSecondsAgo() {
        assertEquals("2 seconds", formatDuration(2));
    }

    @Test
    public void canFormat59SecondsAgo() {
        assertEquals("59 seconds", formatDuration(59));
    }

    @Test
    public void canFormat60SecondsAgo() {
        assertEquals("1 minute", formatDuration(60));

    }

    @Test
    public void canFormat61SecondsAgo() {
        assertEquals("1 minute", formatDuration(61));
    }

    @Test
    public void canFormatTwoMinutesAgo() {
        assertEquals("2 minutes", formatDuration(120));
    }

    @Test
    public void canFormatOneMinuteAgo() {
    }

}