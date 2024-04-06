package ru.youmiteru.backend.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserPanelService {
    private UserRepository userRepository;

    @Autowired
    public UserPanelService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUserName(Long id, String name){
        Optional<User> user = userRepository.findById(id);
        User newUser = user.get();
        newUser.setName(name);
        return userRepository.save(newUser);
    }
}
