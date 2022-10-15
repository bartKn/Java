package pl.bartkn.recipes.db;

import org.springframework.data.repository.CrudRepository;
import pl.bartkn.recipes.user.User;

public interface UserRepository extends CrudRepository<User, String> {
    User findUserByEmail(String email);
}

