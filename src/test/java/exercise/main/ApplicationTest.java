package exercise.main;

import exercise.values.Message;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {
    @Mock private Source source;
    @Mock private Sink sink;
    @Mock private MessageHandler messageHandler;
    private Application application;

    @Before
    public void setUp() throws Exception {
        application = new Application(source, messageHandler, sink);
    }

    @Test
    public void eventSourceEmitsQuitAndNothingHappens() throws Throwable {
        Mockito.when(source.nextInput()).thenReturn(Application.QUIT);
        application.run();
        Mockito.verify(source).nextInput();
        Mockito.verifyNoMoreInteractions(messageHandler, sink);
    }

    @Test
    public void eventSourceEmitsSomeStringsAndTheyProcessedAndOutput() throws Throwable {
        final List<String> messages = Arrays.asList("hey", "now");
        Mockito.when(source.nextInput()).thenReturn("foo").thenReturn(Application.QUIT);
        Mockito.when(messageHandler.handleUserInput("foo")).thenReturn(messages);

        application.run();

        Mockito.verify(source, Mockito.times(2)).nextInput();
        Mockito.verify(messageHandler).handleUserInput("foo");
        messages.forEach(message -> Mockito.verify(sink).printMessage(message));
        Mockito.verifyNoMoreInteractions(source, messageHandler, sink);
    }
}