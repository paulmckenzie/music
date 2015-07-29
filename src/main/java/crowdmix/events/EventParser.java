package crowdmix.events;

import crowdmix.main.TimeProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EventParser {
    private final TimeProvider timeProvider;

    private static final Pattern EventParserRegex = Pattern.compile("(\\w+)\\s*(wall|follows|->)?\\s?(.+)?");

    EventParser(final TimeProvider timeProvider) {
        this.timeProvider = timeProvider;
    }

    public Event parseEvent(final String messageText) {
        final Matcher matcher = EventParserRegex.matcher(messageText);
        final boolean matches = matcher.matches();
        assert matches;
        return makeEvent(matcher.group(1), EventType.fromToken(matcher.group(2)), matcher.group(3));
    }

    private Event makeEvent(final String userName, final EventType eventType, final String argument) {
        final Event event;
        switch (eventType) {
            case POST:
                event = new MessagePostedEvent(userName, timeProvider.now(), argument);
                break;
            case WALL:
                event = new WallEvent(userName);
                break;
            case FOLLOW:
                event = new UserFollowsEvent(userName, argument);
                break;
            case READ:
                event = new ReadTimelineEvent(userName);
                break;
            default:
                throw new IllegalArgumentException("This can not happen");
        }
        return event;
    }
}
