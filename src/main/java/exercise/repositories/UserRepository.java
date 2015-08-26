package exercise.repositories;

import exercise.values.User;

@FunctionalInterface
public interface UserRepository {
    User findOrCreate(String userName);
}