package exercise.inputs;

import org.junit.Test;

import static exercise.inputs.InputType.*;
import static org.junit.Assert.assertEquals;

public class InputTypeTest {
    @Test
    public void fromToken() {
        assertEquals(READ, InputType.fromToken("read"));
        assertEquals(POST, InputType.fromToken("->"));
        assertEquals(WALL, InputType.fromToken("wall"));
        assertEquals(FOLLOW, InputType.fromToken("follows"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void fromTokenWithBogusInput(){
        InputType.fromToken("bogus");
    }
}