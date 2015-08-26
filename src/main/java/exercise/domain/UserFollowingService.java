package exercise.domain;

import exercise.inputs.InputHandler;
import exercise.repositories.UserRepository;
import exercise.values.InputArgs;
import exercise.values.Message;
import exercise.values.User;

import java.util.Collections;
import java.util.List;

public class UserFollowingService implements InputHandler {
    private final UserRepository userRepository;

    public UserFollowingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Message> handleUserInput(final InputArgs args) {
        final User user = userRepository.findOrCreate(args.getUsername());
        final User followedUser = userRepository.findOrCreate(args.getArgument().get());
        user.addFollowedUser(followedUser);
        return Collections.emptyList();
    }
}
