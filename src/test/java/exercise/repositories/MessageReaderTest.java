package exercise.repositories;

import exercise.domain.MessageReader;
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
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MessageReaderTest {

    private final String userName = "Alice";
    private final Message m22 = new Message(22L, "Some text", now());
    private final Message m23 = new Message(23L, "Some other text", now());
    private final InputArgs inputArgs = new InputArgs(InputType.READ, userName, Optional.empty());
    @Mock private UserRepository userRepository;
    @Mock private MessageRepository messageRepository;
    @Mock private User user;
    private MessageReader reader;

    @Before
    public void setUp() {
        reader = new MessageReader(userRepository, messageRepository);
    }

    @Test
    public void testHandleUserInput() throws Exception {
        Mockito.when(user.getUserName()).thenReturn(userName);
        Mockito.when(user.getPostIds()).thenReturn(asList(22L, 23L));
        Mockito.when(userRepository.findOrCreate(user.getUserName())).thenReturn(user);
        Mockito.when(messageRepository.getMessages(asList(22L, 23L))).thenReturn(asList(m22, m23));

        final List<Message> messages = reader.handleUserInput(inputArgs);

        assertEquals(asList(m22, m23), messages);
    }
}