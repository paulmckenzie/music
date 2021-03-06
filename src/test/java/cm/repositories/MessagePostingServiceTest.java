package cm.repositories;

import cm.domain.MessageFactory;
import cm.domain.MessagePostCommandHandler;
import cm.inputs.InputType;
import cm.values.InputArgs;
import cm.values.Message;
import cm.values.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MessagePostingServiceTest {
    @Mock private MessageFactory messageFactory;
    @Mock private UserRepository userRepository;
    @Mock private MessageRepository messageRepository;
    @Mock private Message message;
    @Mock private User user;
    private final InputArgs inputArgs = new InputArgs(InputType.POST, "Alice", Optional.of("Nice weather"));

    private MessagePostCommandHandler service;

    @Before
    public void setUp() throws Exception {
        service = new MessagePostCommandHandler(messageFactory, userRepository, messageRepository);
    }

    @Test
    public void testHandleUserInput() throws Exception {
        when(messageFactory.fromInput(inputArgs)).thenReturn(message);
        when(userRepository.findOrCreate(inputArgs.getUsername())).thenReturn(user);

        final List<String> messages = service.handleUserInput(inputArgs);

        assertTrue(messages.isEmpty());
        verify(messageFactory).fromInput(inputArgs);
        verify(userRepository).findOrCreate(inputArgs.getUsername());
        verify(user).addPost(message.getId());
        verify(messageRepository).saveMessage(message);
    }
}