package exercise.domain;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SimpleIdProviderTest {

    @Test
    public void testNextIdIncreasing() throws Exception {
        final IdProvider provider = new SimpleIdProvider();
        final Long firstId = provider.nextId();
        final Long secondId = provider.nextId();
        assertTrue(firstId < secondId);
    }
}