package cm.domain;

import java.time.LocalDateTime;

@FunctionalInterface
public interface TimeProvider {
    LocalDateTime now();
}
