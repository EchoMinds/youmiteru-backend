package ru.youmiteru.backend.controller.user_panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.youmiteru.backend.domain.Role;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.repositories.UserRepository;
import ru.youmiteru.backend.service.UserService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<String> getUserInfo(@AuthenticationPrincipal OAuth2User principal) {
        String userId = principal.getAttribute("id");
        String userLogin = principal.getAttribute("login");
        String userEmail = principal.getAttribute("default_email");
        String userProfilePicture = principal.getAttribute("default_avatar_id");

        // Временная диагностика
        System.out.println("User ID: " + userId);
        System.out.println("User Login: " + userLogin);
        System.out.println("User Email: " + userEmail);
        System.out.println("User Profile Picture: " + userProfilePicture);

        // Проверяем, существует ли пользователь в базе данных
        if (!userRepository.existsByEmail(userEmail)) {
            // Создаем и сохраняем нового пользователя
            User newUser = new User();

            if (userId != null) {
                newUser.setId(Long.valueOf(userId));
            }
            newUser.setName(userLogin);
            newUser.setEmail(userEmail);
            newUser.setProfileImageUrl(userProfilePicture);
            newUser.setRole(Role.USER);
            newUser.setCreationTime(LocalDateTime.now());
            userRepository.save(newUser);
        }

        // Возвращаем ответ
        return ResponseEntity.ok(String.format("User ID: %s, Login: %s, Email: %s, Profile Picture: %s",
            userId, userLogin, userEmail, userProfilePicture));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserPageById(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }
}
