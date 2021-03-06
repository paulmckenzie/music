package cm.domain;

import cm.inputs.InputType;
import cm.values.InputArgs;
import cm.values.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleMessageFactoryTest {

    @Mock private IdProvider idProvider;
    @Mock private TimeProvider timeProvider;
    private SimpleMessageFactory messageFactory;
    private final LocalDateTime now = LocalDateTime.now();

    @Before
    public void setUp() throws Exception {
        messageFactory = new SimpleMessageFactory(idProvider, timeProvider);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ifThereIsNoMessageTextThereInNoMessageToCreate() {
        messageFactory.fromInput(new InputArgs(InputType.POST, "Alice", Optional.empty()));
    }

    @Test
    public void createAMessageWithATimestampAndAnId() {
        when(idProvider.nextId()).thenReturn(29401L);
        when(timeProvider.now()).thenReturn(now);

        final Message message = messageFactory.fromInput(new InputArgs(InputType.POST, "Alice", Optional.of("Nice weather")));

        assertEquals(29401L, message.getId().longValue());
        assertEquals("Nice weather", message.getText());
        assertEquals(now, message.getTimestamp());
    }
}