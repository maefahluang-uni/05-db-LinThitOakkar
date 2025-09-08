package th.mfu.boot;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
     @Override
    List<User> findAll();

    // Custom query method to find a user by username
    User findByUsername(String username);

}
