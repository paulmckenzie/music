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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallQueryHandlerTest {
    private final Optional<String> wallCommandArgument = empty();

    @Mock
    private UserRepository userRepository;
    @Mock
    private MessageRepository messageRepository;
    @Mock
    private User bob;
    @Mock
    private User alice;
    @Mock
    private User charlie;

    private WallQueryHandler wallService;

    @Before
    public void setUp() throws Exception {
        wallService = new WallQueryHandler(userRepository, messageRepository, m -> m.getId().toString());
    }

    @Test
    public void testHandleUsedddrInput() throws Exception {

        final LocalDateTime now = LocalDateTime.now();
        final Message message38 = new Message(38L, "Bob", "A", now.minus(10, SECONDS));
        final Message message39 = new Message(39L, "Charlie", "A", now.minus(9, SECONDS));
        final Message message40 = new Message(40L, "Charlie", "A", now.minus(8, SECONDS));
        final Message message41 = new Message(41L, "Alice", "A", now.minus(7, SECONDS));
        when(userRepository.findOrCreate("Bob")).thenReturn(bob);
        when(bob.getFollowedUsers()).thenReturn(asList(bob, alice, charlie));
        when(bob.getPostIds()).thenReturn(singletonList(38L));
        when(alice.getPostIds()).thenReturn(singletonList(41L));
        when(charlie.getPostIds()).thenReturn(asList(39L, 40L));
        when(messageRepository.getMessages(asList(38L, 39L, 40L, 41L))).thenReturn(asList(message38, message39, message40, message41));

        final List<String> outputs = wallService.handleUserInput(new InputArgs(InputType.WALL, "Bob", wallCommandArgument));
        assertEquals(asList("41", "40", "39", "38"), outputs);
    }
}