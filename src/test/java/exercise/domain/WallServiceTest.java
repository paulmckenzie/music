package exercise.domain;

import exercise.inputs.InputType;
import exercise.repositories.MessageRepository;
import exercise.repositories.UserRepository;
import exercise.values.InputArgs;
import exercise.values.Message;
import exercise.values.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private MessageRepository messageRepository;
    @Mock private User user;
    @Mock private User followedUser1;
    @Mock private User followedUser2;

    private WallService wallService;

    @Before
    public void setUp() throws Exception {
        wallService = new WallService(userRepository, messageRepository);
    }

    @Test
    public void testHandleUserInput() throws Exception {
        final List<User> followedUsers = asList(followedUser1, followedUser2);
        final List<Message> expectedMessages = asList(mock(Message.class), mock(Message.class), mock(Message.class), mock(Message.class));
        final String username = "Bob";

        when(userRepository.findOrCreate(username)).thenReturn(user);
        when(user.getFollowedUsers()).thenReturn(followedUsers);
        when(followedUser1.getPostIds()).thenReturn(asList(38L, 41L));
        when(followedUser2.getPostIds()).thenReturn(asList(39L, 40L));
        when(messageRepository.getMessages(asList(38L, 39L, 40L, 41L))).thenReturn(expectedMessages);

        final List<Message> messages = wallService.handleUserInput(new InputArgs(InputType.WALL, username, empty()));
        assertEquals(expectedMessages, messages);

    }
}