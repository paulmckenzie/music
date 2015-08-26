package exercise.domain;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleIdProvider implements IdProvider {
    private AtomicLong counter = new AtomicLong();

    @Override
    public Long nextId() {
        return counter.incrementAndGet();
    }
}
