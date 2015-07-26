package crowdmix.main;

public class PostMessageCommand implements Command {
    private final String target;
    private final String message;

    public PostMessageCommand(String target, String message) {
        validate(target, message);
        this.target = target;
        this.message = message;
    }

    @Override
    public String getTarget() {
        return target;
    }

    public String getMessage() {
        return message;
    }

    private void validate(String target, String message) {
        if (target == null || message == null) throw new IllegalArgumentException(NullMessage);
    }
}
