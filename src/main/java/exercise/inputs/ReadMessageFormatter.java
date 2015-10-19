package exercise.inputs;

import exercise.domain.TimeProvider;
import exercise.values.Message;

import java.time.Duration;

import static exercise.inputs.ElapsedTimeFormatter.formatDuration;

public class ReadMessageFormatter implements MessageFormatter {
    static final String FORMAT = "%s (%s ago)";
    private final TimeProvider timeProvider;

    public ReadMessageFormatter(TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    @Override
    public String format(Message message) {
        final Duration elapsedTime = Duration.between(message.getTimestamp(), timeProvider.now());
        return String.format(FORMAT, message.getText(), formatDuration(elapsedTime.getSeconds()));
    }
}
