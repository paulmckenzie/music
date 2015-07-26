package crowdmix.main;

public interface Command {
    static final String NullMessage = "Cannot create a command with a null argument";

    String getTarget();
}
