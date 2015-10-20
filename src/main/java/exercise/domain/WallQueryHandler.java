package exercise.domain;

import exercise.inputs.InputHandler;
import exercise.inputs.MessageFormatter;
import exercise.repositories.MessageRepository;
import exercise.repositories.UserRepository;
import exercise.values.InputArgs;
import exercise.values.User;

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
