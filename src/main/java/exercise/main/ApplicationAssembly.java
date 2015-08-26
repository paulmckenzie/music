package exercise.main;

import exercise.domain.*;
import exercise.inputs.InputHandler;
import exercise.inputs.InputType;
import exercise.inputs.RegexArgumentParser;

import java.util.HashMap;
import java.util.Map;

public class ApplicationAssembly {

    public static MessageHandler makeMessageHandler() {
        final Map<InputType, InputHandler> inputHandlers = new HashMap<>();
        final IdProvider idProvider = new SimpleIdProvider();
        final TimeProvider timeProvider = new SystemTimeProvider();
        final MessageFactory messageFactory = new SimpleMessageFactory(idProvider, timeProvider);
        final UserRepository userRepository = new InMemoryUserRepository();
        final MessageRepository messageRepository = new InMemoryMessageRepository();
        final InputHandler postingService = new MessagePostingService(messageFactory, userRepository, messageRepository);
        final InputHandler messageReader = new MessageReader(userRepository, messageRepository);

        inputHandlers.put(InputType.POST, postingService);
        inputHandlers.put(InputType.READ, messageReader);

        return new MainMessageHandler(new RegexArgumentParser(), inputHandlers);
    }
}
