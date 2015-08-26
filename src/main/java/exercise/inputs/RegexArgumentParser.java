package exercise.inputs;

import exercise.values.InputArgs;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexArgumentParser implements InputArgumentParser {
    private static final Pattern EventParserRegex = Pattern.compile("(\\w+)\\s*(wall|follows|->)?\\s?(.+)?");

    @Override
    public InputArgs parse(String userInput) {
        final Matcher matcher = EventParserRegex.matcher(userInput);
        final boolean matches = matcher.matches();
        assert matches;
        final String username = matcher.group(1);
        final String type = matcher.group(2) == null ? "read" : matcher.group(2);
        final String args = matcher.group(3);
        return new InputArgs(InputType.fromToken(type), username, Optional.ofNullable(args));
    }
}
