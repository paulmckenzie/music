package exercise.domain;

import exercise.inputs.InputType;
import exercise.repositories.MessageRepository;
import exercise.repositories.UserRepository;
import exercise.values.InputArgs;
import exercise.values.Message;
import exercise.values.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallQueryHandlerTest {

    @Mock private UserRepository userRepository;
    @Mock private MessageRepository messageRepository;
    @Mock private User user;
    @Mock private User followedUser1;
    @Mock private User followedUser2;

    private WallQueryHandler wallService;

    @Before
    public void setUp() throws Exception {
        wallService = new WallQueryHandler(userRepository, messageRepository, Message::getText);
    }

    @Test
    public void testHandleUserInput() throws Exception {
        final List<User> followedUsers = asList(user, followedUser1, followedUser2);
        final List<Message> messages = asList(mock(Message.class), mock(Message.class), mock(Message.class), mock(Message.class));
        messages.forEach(message -> when(message.getText()).thenReturn("A"));
        final String username = "Bob";

        when(userRepository.findOrCreate(username)).thenReturn(user);
        when(user.getFollowedUsers()).thenReturn(followedUsers);
        when(user.getPostIds()).thenReturn(singletonList(38L));
        when(followedUser1.getPostIds()).thenReturn(singletonList(41L));
        when(followedUser2.getPostIds()).thenReturn(asList(39L, 40L));
        when(messageRepository.getMessages(asList(38L, 39L, 40L, 41L))).thenReturn(messages);

        final List<String> outputs = wallService.handleUserInput(new InputArgs(InputType.WALL, username, empty()));
        assertEquals(asList("A", "A", "A", "A"), outputs);
    }
}