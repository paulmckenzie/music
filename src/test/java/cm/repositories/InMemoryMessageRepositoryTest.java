package cm.repositories;

import cm.values.Message;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class InMemoryMessageRepositoryTest {
    private InMemoryMessageRepository repository;
    private final String userName = "Charlie";

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryMessageRepository();
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotReuseId() {
        repository.saveMessage(new Message(33L, userName, "Nice wallpaper", now()));
        repository.saveMessage(new Message(33L, userName, "Wow, and again", now()));
    }

    @Test
    public void willReturnEmptyListForEmptyListOfIds() {
        assertEquals(emptyList(), repository.getMessages(emptyList()));
    }

    @Test
    public void willReturnNonEmptyListForListOfIds() {
        final Message message33 = new Message(33L, userName, "Nice wallpaper", now());
        final Message message34 = new Message(34L, userName, "Wow, and again", now());
        final Message message35 = new Message(35L, userName, "Don't see me", now());
        repository.saveMessage(message33);
        repository.saveMessage(message34);
        repository.saveMessage(message35);
        final List<Message> expected = asList(message33, message34);

        assertEquals(expected, repository.getMessages(asList(33L, 34L)));
    }
}