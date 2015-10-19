package exercise.inputs;

@FunctionalInterface
public interface DurationFormatter {
    String formatDuration(long seconds);
}
