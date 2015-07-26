package crowdmix.main;

public class ReadTimelineCommand implements Command {
    public static final String NullMessage = "Target can not be null";

    private final String target;

    public ReadTimelineCommand(String target) {
        validate(target);

        this.target = target;
    }

    private void validate(String target) {
        if (target == null)
            throw new IllegalArgumentException(NullMessage);
    }

    @Override
    public String getTarget() {
        return target;
    }
}
