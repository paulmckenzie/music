package cm.main;

import cm.domain.*;
import cm.inputs.*;
import cm.repositories.InMemoryMessageRepository;
import cm.repositories.InMemoryUserRepository;
import cm.repositories.MessageRepository;
import cm.repositories.UserRepository;

import java.util.EnumMap;
import java.util.Map;

public class ApplicationAssembly {
    private ApplicationAssembly() {
    }

    public static MessageHandler makeMessageHandler() {
        return makeMessageHandler(new SystemTimeProvider());
    }

    public static MessageHandler makeMessageHandler(final TimeProvider timeProvider) {
        final IdProvider idProvider = new SimpleIdProvider();
        final DurationFormatter durationFormatter = new ElapsedTimeFormatter();
        final MessageFactory messageFactory = new SimpleMessageFactory(idProvider, timeProvider);
        final UserRepository userRepository = new InMemoryUserRepository();
        final MessageRepository messageRepository = new InMemoryMessageRepository();
        final Map<InputType, InputHandler> inputHandlers = new EnumMap<>(InputType.class);
        inputHandlers.put(InputType.POST, new MessagePostCommandHandler(messageFactory, userRepository, messageRepository));
        inputHandlers.put(InputType.READ, new ReadTimelineQueryHandler(userRepository, messageRepository, new ReadMessageFormatter(timeProvider, durationFormatter)::format));
        inputHandlers.put(InputType.FOLLOW, new FollowCommandHandler(userRepository));
        inputHandlers.put(InputType.WALL, new WallQueryHandler(userRepository, messageRepository, new WallMessageFormatter(timeProvider, durationFormatter)::format));

        return new MainMessageHandler(new RegexArgumentParser(), inputHandlers);
    }
}
