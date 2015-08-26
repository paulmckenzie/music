package exercise.domain;

import exercise.values.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> repo = new HashMap<>();

    @Override
    public User findOrCreate(String userName) {
        if (!repo.containsKey(userName)) {
            repo.put(userName, new User(userName));
        }
        return repo.get(userName);
    }
}

