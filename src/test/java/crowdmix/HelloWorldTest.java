package crowdmix;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class HelloWorldTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testSomeJava8Stuff() {
        List<Integer> input = asList(1, 3, 2, 4);
        Collections.sort(input, Integer::compareTo);
        assertEquals(input, asList(1, 2, 3, 4));
    }
}