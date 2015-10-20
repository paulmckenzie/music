package cm.repositories;

import cm.values.User;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class InMemoryUserRepositoryTest {
    private final String userName = "Alice";
    private InMemoryUserRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryUserRepository();
    }

    @Test
    public void repoWillCreateNewUserIfNotPresent() {
        final User alice = repository.findOrCreate(userName);
        assertEquals(userName, alice.getUserName());
        assertEquals(Collections.emptyList(), alice.getPostIds());
    }

    @Test
    public void repoWillFindUserIfPresent() {
        assertEquals(repository.findOrCreate(userName), repository.findOrCreate(userName));
    }
}