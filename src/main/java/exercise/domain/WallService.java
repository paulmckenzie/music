package exercise.domain;

import exercise.inputs.InputHandler;
import exercise.repositories.MessageRepository;
import exercise.repositories.UserRepository;
import exercise.values.InputArgs;
import exercise.values.Message;
import exercise.values.User;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Stream.concat;

public class WallService implements InputHandler {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public WallService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> handleUserInput(InputArgs args) {
        final User primaryUser = userRepository.findOrCreate(args.getUsername());
        final List<Long> postsOfFollowedUsers = primaryUser
                .getFollowedUsers()
                .stream()
                .flatMap(user -> user.getPostIds().stream())
                .sorted()
                .collect(Collectors.toList());
        return messageRepository.getMessages(postsOfFollowedUsers);
    }
}
