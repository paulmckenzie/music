package exercise.domain;

import exercise.inputs.InputType;
import exercise.repositories.UserRepository;
import exercise.values.InputArgs;
import exercise.values.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserFollowingServiceTest {
    @Mock private UserRepository userRepository;
    @Mock private User user1 ;
    @Mock private User user2;
    private FollowCommandHandler followCommandHandler;

    @Before
    public void setUp() throws Exception {
        followCommandHandler = new FollowCommandHandler(userRepository);
    }

    @Test
    public void testHandleUserInput() throws Exception {
        final String username1 = "a user name";
        when(userRepository.findOrCreate(username1)).thenReturn(user1);

        final String username2 = "another user name";
        when(userRepository.findOrCreate(username2)).thenReturn(user2);

        followCommandHandler.handleUserInput(new InputArgs(InputType.FOLLOW, username1, Optional.of(username2)));

        verify(user1).addFollowedUser(user2);
    }
}