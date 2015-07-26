package crowdmix.main;

public class FollowCommand implements Command {
    private final String target;
    private final String follows;

    public FollowCommand(String target, String follows) {
        this.target = target;
        this.follows = follows;
        validate(target, follows);
    }

    @Override
    public String getTarget() {
        return target;
    }

    public String getFollows() {
        return follows;
    }

    private void validate(String target, String follows) {
        if (target == null || follows == null) throw new IllegalArgumentException(NullMessage);
    }
}
