package exercise.main;

import exercise.domain.*;
import exercise.inputs.*;
import exercise.repositories.InMemoryMessageRepository;
import exercise.repositories.InMemoryUserRepository;
import exercise.repositories.MessageRepository;
import exercise.repositories.UserRepository;

import java.util.EnumMap;
import java.util.Map;

public class ApplicationAssembly {
    private ApplicationAssembly() {
    }

    public static MessageHandler makeMessageHandler() {
        final IdProvider idProvider = new SimpleIdProvider();
        final TimeProvider timeProvider = new SystemTimeProvider();
        final MessageFactory messageFactory = new SimpleMessageFactory(idProvider, timeProvider);
        final UserRepository userRepository = new InMemoryUserRepository();
        final MessageRepository messageRepository = new InMemoryMessageRepository();
        final Map<InputType, InputHandler> inputHandlers = new EnumMap<>(InputType.class);
        inputHandlers.put(InputType.POST, new MessagePostCommandHandler(messageFactory, userRepository, messageRepository));
        inputHandlers.put(InputType.READ, new ReadTimelineQueryHandler(userRepository, messageRepository, new ReadMessageFormatter(timeProvider)::format));
        inputHandlers.put(InputType.FOLLOW, new FollowCommandHandler(userRepository));
        inputHandlers.put(InputType.WALL, new WallQueryHandler(userRepository, messageRepository, new WallMessageFormatter(timeProvider)::format));

        return new MainMessageHandler(new RegexArgumentParser(), inputHandlers);
    }
}
