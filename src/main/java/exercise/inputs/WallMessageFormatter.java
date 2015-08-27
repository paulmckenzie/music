package exercise.inputs;

import exercise.domain.TimeProvider;
import exercise.values.Message;

import java.time.Duration;

public class WallMessageFormatter implements MessageFormatter {
    static final String FORMAT = "%s - %s (%s ago)";
    private final TimeProvider timeProvider;

    public WallMessageFormatter(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    @Override
    public String format(Message message) {
        final Duration elapsedTime = getElapsedTime(message);
        return String.format(FORMAT, message.getUserName(), message.getText(), formatDuration(elapsedTime.getSeconds()));
    }

    private Duration getElapsedTime(Message message) {
        return Duration.between(message.getTimestamp(), timeProvider.now());
    }

    private String formatDuration(long seconds) {
        return seconds < 60L ? formatSeconds(seconds) : formatMinutes(seconds);
    }

    private String formatMinutes(long seconds) {
        final long minutes = seconds / 60;
        return minutes + " minute" + pluralS(minutes);
    }

    private String formatSeconds(long seconds) {
        return seconds + " second" + pluralS(seconds);
    }

    private String pluralS(long unit) {
        return unit == 1 ? "" : "s";
    }
}
