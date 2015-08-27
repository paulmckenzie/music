package exercise.domain;

import exercise.inputs.InputHandler;
import exercise.inputs.MessageFormatter;
import exercise.repositories.MessageRepository;
import exercise.repositories.UserRepository;
import exercise.values.InputArgs;
import exercise.values.Message;
import exercise.values.User;

import java.util.List;
import java.util.stream.Collectors;

public class ReadTimelineQueryHandler implements InputHandler {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final MessageFormatter formatter;

    public ReadTimelineQueryHandler(UserRepository userRepository, MessageRepository messageRepository, MessageFormatter formatter) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.formatter = formatter;
    }

    @Override
    public List<String> handleUserInput(InputArgs args) {
        final String username = args.getUsername();
        final User user = userRepository.findOrCreate(username);
        final List<Long> postIds = user.getPostIds();
        return messageRepository
                .getMessages(postIds)
                .stream()
                .map(formatter::format)
                .collect(Collectors.toList());
    }
}
