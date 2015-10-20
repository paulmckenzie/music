package cm.domain;

import cm.inputs.InputHandler;
import cm.repositories.UserRepository;
import cm.values.InputArgs;
import cm.values.User;

import java.util.Collections;
import java.util.List;

public class FollowCommandHandler implements InputHandler {
    private final UserRepository userRepository;

    public FollowCommandHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<String> handleUserInput(final InputArgs args) {
        final User user = userRepository.findOrCreate(args.getUsername());
        final User followedUser = userRepository.findOrCreate(args.getArgument().get());
        user.addFollowedUser(followedUser);
        return Collections.emptyList();
    }
}
