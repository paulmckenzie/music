package cm.domain;

import cm.inputs.InputHandler;
import cm.inputs.MessageFormatter;
import cm.repositories.MessageRepository;
import cm.repositories.UserRepository;
import cm.values.InputArgs;
import cm.values.User;

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
                .sorted((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp()))
                .map(formatter::format)
                .collect(Collectors.toList());
    }
}
