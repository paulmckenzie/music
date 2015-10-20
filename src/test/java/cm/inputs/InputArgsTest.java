package cm.inputs;

import cm.values.InputArgs;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class InputArgsTest {
    private final InputType inputType = InputType.POST;
    private final String username = "Alice";
    private final Optional<String> argument = Optional.of("Nice Castle!");
    private final InputArgs inputArgs = new InputArgs(inputType, username, argument);

    @Test
    public void testGetInputType() throws Exception {
        assertEquals(inputType, inputArgs.getInputType());
    }

    @Test
    public void testGetUsername() throws Exception {
        assertEquals(username, inputArgs.getUsername());
    }

    @Test
    public void testGetArgument() throws Exception {
        assertEquals(argument, inputArgs.getArgument());
    }
}