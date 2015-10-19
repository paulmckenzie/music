package exercise.inputs;

public class ElapsedTimeFormatter implements DurationFormatter {

    @Override
    public String formatDuration(long seconds) {
        return seconds < 60L ? formatSeconds(seconds) : formatMinutes(seconds);
    }

    private static String formatMinutes(long seconds) {
        final long minutes = seconds / 60;
        return minutes + " minute" + pluralS(minutes);
    }

    private static String formatSeconds(long seconds) {
        return seconds + " second" + pluralS(seconds);
    }

    private static String pluralS(long unit) {
        return unit == 1 ? "" : "s";
    }
}
