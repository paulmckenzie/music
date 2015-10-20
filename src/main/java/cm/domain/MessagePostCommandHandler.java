package cm.domain;

import cm.inputs.InputHandler;
import cm.repositories.MessageRepository;
import cm.repositories.UserRepository;
import cm.values.InputArgs;
import cm.values.Message;
import cm.values.User;

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
