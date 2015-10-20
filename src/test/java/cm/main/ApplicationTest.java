package cm.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {
    @Mock private Consumer<List<String>> sink;
    @Mock private MessageHandler messageHandler;

    @Test
    public void eventSourceEmitsSomeStringsAndTheyProcessedAndOutput() throws Throwable {
        when(messageHandler.handleUserInput("hey")).thenReturn(asList("one", "two"));
        when(messageHandler.handleUserInput("now")).thenReturn(asList("three", "four", "five"));

        final Application application = new Application(Stream.of("hey", "now"), messageHandler, sink);
        application.run();

        verify(messageHandler).handleUserInput("hey");
        verify(sink).accept(asList("one", "two"));
        verify(messageHandler).handleUserInput("now");
        verify(sink).accept(asList("three", "four", "five"));
        verifyNoMoreInteractions(messageHandler, sink);
    }
}