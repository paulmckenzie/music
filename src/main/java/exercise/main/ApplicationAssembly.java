package exercise.main;

import exercise.domain.*;
import exercise.inputs.InputHandler;
import exercise.inputs.InputType;
import exercise.inputs.RegexArgumentParser;
import exercise.repositories.InMemoryMessageRepository;
import exercise.repositories.InMemoryUserRepository;
import exercise.repositories.MessageRepository;
import exercise.repositories.UserRepository;

import java.util.EnumMap;
import java.util.HashMap;
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
        inputHandlers.put(InputType.POST, new MessagePostingService(messageFactory, userRepository, messageRepository));
        inputHandlers.put(InputType.READ, new MessageReader(userRepository, messageRepository));
        inputHandlers.put(InputType.FOLLOW, new UserFollowingService(userRepository));
        inputHandlers.put(InputType.WALL, new WallService(userRepository, messageRepository));

        return new MainMessageHandler(new RegexArgumentParser(), inputHandlers);
    }
}
