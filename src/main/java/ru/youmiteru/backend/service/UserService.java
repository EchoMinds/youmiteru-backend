package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.convertors.UsersConvertors;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.dto.UserDTO;
import ru.youmiteru.backend.exceptions.UserNotFoundException;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UsersConvertors usersConvertors;
    private final SeasonService seasonService;

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return usersConvertors.convertUserToUserDto(
            user, seasonService.getFavoriteSeasonList(id), new ArrayList<>());
    }
}
