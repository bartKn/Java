package pl.bartkn.recipes.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bartkn.recipes.user.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User saveUser(User userToSave) {
        return userRepository.save(userToSave);
    }

    public boolean isEmailTaken(String email) {
        return userRepository.existsById(email);
    }

}
