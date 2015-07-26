package crowdmix.main;

public class WallCommand implements Command {
    private final String target;

    public WallCommand(String target) {
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
