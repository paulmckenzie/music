package exercise.inputs;

import exercise.domain.TimeProvider;
import exercise.values.Message;

import java.time.Duration;

public class ReadMessageFormatter implements MessageFormatter {
    static final String FORMAT = "%s (%s ago)";
    private final TimeProvider timeProvider;
    private final DurationFormatter durationFormatter;

    public ReadMessageFormatter(TimeProvider timeProvider, DurationFormatter durationFormatter) {
        this.timeProvider = timeProvider;
        this.durationFormatter = durationFormatter;
    }

    @Override
    public String format(Message message) {
        final Duration elapsedTime = Duration.between(message.getTimestamp(), timeProvider.now());
        return String.format(FORMAT, message.getText(), durationFormatter.formatDuration(elapsedTime.getSeconds()));
    }
}
