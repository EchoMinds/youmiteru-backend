package ru.youmiteru.backend.user;

import lombok.Data;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.Optional;

@Service
@Data
public class UserPanelService {
    private UserRepository userRepository;

    public User updateUserName(Long id, String name){
        Optional<User> user = userRepository.findById(id);
        User newUser = user.get();
        newUser.setName(name);
        return userRepository.save(newUser);
    }
}
