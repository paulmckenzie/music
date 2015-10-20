package cm.domain;

import cm.inputs.InputHandler;
import cm.inputs.MessageFormatter;
import cm.repositories.MessageRepository;
import cm.repositories.UserRepository;
import cm.values.InputArgs;
import cm.values.User;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class WallQueryHandler implements InputHandler {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final MessageFormatter formatter;

    public WallQueryHandler(UserRepository userRepository, MessageRepository messageRepository, MessageFormatter formatter) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.formatter = formatter;
    }

    @Override
    public List<String> handleUserInput(InputArgs args) {
        final User primaryUser = userRepository.findOrCreate(args.getUsername());
        final List<Long> postsOfFollowedUsers = primaryUser
                .getFollowedUsers()
                .stream()
                .flatMap(user -> user.getPostIds().stream())
                .sorted()
                .collect(toList());
        return messageRepository
                .getMessages(postsOfFollowedUsers)
                .stream()
                .sorted((m1, m2) -> m2.getTimestamp().compareTo(m1.getTimestamp()))
                .map(formatter::format)
                .collect(toList());
    }
}
