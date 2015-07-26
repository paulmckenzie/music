package crowdmix.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static crowdmix.main.CommandType.fromToken;

public class CommandParser {
    static final Pattern CommandParserRegex = Pattern.compile("(\\w+)\\s*(wall|follows|->)?\\s?(.+)?");

    public static Command parse(final String commandString) {
        final Matcher matcher = CommandParserRegex.matcher(commandString);
        final boolean matches = matcher.matches();
        assert matches;
        return makeCommand(matcher.group(1), fromToken(matcher.group(2)), matcher.group(3));
    }

    private static Command makeCommand(String target, CommandType commandType, String argument) {
        Command command;
        switch (commandType) {
            case POST:
                command = new PostMessageCommand(target, argument);
                break;
            case WALL:
                command = new WallCommand(target);
                break;
            case FOLLOW:
                command = new FollowCommand(target, argument);
                break;
            case READ:
                command = new ReadTimelineCommand(target);
                break;
            default:
                throw new IllegalArgumentException("This can not happen");
        }
        return command;
    }
}
