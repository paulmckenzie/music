package cm.repositories;

import cm.values.User;

@FunctionalInterface
public interface UserRepository {
    User findOrCreate(String userName);
}