package cm.inputs;

import cm.main.MainMessageHandler;
import cm.values.InputArgs;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainMessageHandlerTest {
    @Mock InputHandler postHandler;
    @Mock InputHandler readTimelineHandler;
    @Mock InputArgumentParser argumentParser;
    @Mock InputArgs inputArgs;
    private MainMessageHandler handler;

    @Before
    public void setUp() throws Exception {
        final Map<InputType, InputHandler> handlers = new HashMap<>();
        handlers.put(InputType.POST, postHandler);
        handlers.put(InputType.READ, readTimelineHandler);
        handler = new MainMessageHandler(argumentParser, handlers);
    }

    @Test
    public void aMessagePostInputIsDelegatedToAnInputHandler() {
        final String input = "Arbitrary text";
        when(argumentParser.parse(input)).thenReturn(inputArgs);
        when(inputArgs.getInputType()).thenReturn(InputType.POST);
        handler.handleUserInput(input);
        verify(postHandler).handleUserInput(inputArgs);
    }
}