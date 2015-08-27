package exercise.domain;

import exercise.repositories.MessageRepository;
import exercise.repositories.UserRepository;
import exercise.values.InputArgs;
import exercise.inputs.InputHandler;
import exercise.values.Message;
import exercise.values.User;

import java.util.Collections;
import java.util.List;

public class MessagePostCommandHandler implements InputHandler {
    private final MessageFactory messageFactory;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessagePostCommandHandler(final MessageFactory messageFactory, final UserRepository userRepository, final MessageRepository messageRepository) {
        this.messageFactory = messageFactory;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<String> handleUserInput(final InputArgs args) {
        final Message message = messageFactory.fromInput(args);
        final User user = userRepository.findOrCreate(args.getUsername());
        user.addPost(message.getId());
        messageRepository.saveMessage(message);
        return Collections.emptyList();
    }
}
