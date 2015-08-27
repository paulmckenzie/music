package exercise.inputs;

import exercise.values.Message;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SimpleMessageFormatterTest {

    @Test
    public void formatterReturnsMessageText() {
        final Message message = mock(Message.class);
        when(message.getText()).thenReturn("Foo");
        final String formattedMessage = new SimpleMessageFormatter().format(message);
        assertEquals("Foo", formattedMessage);
    }
}