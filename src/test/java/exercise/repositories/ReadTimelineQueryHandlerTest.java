package exercise.repositories;

import exercise.domain.ReadTimelineQueryHandler;
import exercise.inputs.InputType;
import exercise.values.InputArgs;
import exercise.values.Message;
import exercise.values.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.SECONDS;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ReadTimelineQueryHandlerTest {

    private final LocalDateTime now = LocalDateTime.now();
    private final String userName = "Alice";
    private final Message m22 = new Message(22L, userName, "Message 1", now.minus(10, SECONDS));
    private final Message m23 = new Message(23L, userName, "Message 2", now);
    private final InputArgs inputArgs = new InputArgs(InputType.READ, userName, Optional.empty());
    @Mock private UserRepository userRepository;
    @Mock private MessageRepository messageRepository;
    @Mock private User user;
    private ReadTimelineQueryHandler reader;

    @Before
    public void setUp() {
        reader = new ReadTimelineQueryHandler(userRepository, messageRepository, Message::getText);
    }

    @Test
    public void testHandleUserInput() throws Exception {
        Mockito.when(user.getUserName()).thenReturn(userName);
        Mockito.when(user.getPostIds()).thenReturn(asList(22L, 23L));
        Mockito.when(userRepository.findOrCreate(user.getUserName())).thenReturn(user);
        Mockito.when(messageRepository.getMessages(asList(22L, 23L))).thenReturn(asList(m22, m23));

        final List<String> messages = reader.handleUserInput(inputArgs);
        assertEquals(asList("Message 2", "Message 1"), messages);
    }
}