package exercise.domain;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

public class SystemTimeProviderTest {

    @Test
    public void testNow() throws InterruptedException {
        final TimeProvider timeProvider = new SystemTimeProvider();
        final LocalDateTime timestamp1 = timeProvider.now();
        Thread.sleep(500L);
        final LocalDateTime timestamp2 = timeProvider.now();
        assertTrue(timestamp2.isAfter(timestamp1));
    }
}