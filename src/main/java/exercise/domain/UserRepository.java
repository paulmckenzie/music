package exercise.domain;

import exercise.values.User;

@FunctionalInterface
public interface UserRepository {
    User findOrCreate(String userName);
}