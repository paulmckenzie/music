package crowdmix.main;

public interface UserRepository {
    User findOrCreate(String userName);
}
