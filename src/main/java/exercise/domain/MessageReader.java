package exercise.domain;

import exercise.inputs.InputHandler;
import exercise.values.InputArgs;
import exercise.values.Message;
import exercise.values.User;

import java.util.List;

public class MessageReader implements InputHandler {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessageReader(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> handleUserInput(InputArgs args) {
        final String username = args.getUsername();
        final User user = userRepository.findOrCreate(username);
        final List<Long> postIds = user.getPostIds();
        return messageRepository.getMessages(postIds);
    }
}
