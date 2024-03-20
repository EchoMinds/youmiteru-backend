package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.convertors.UsersConvertors;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.dto.SeasonDto.FavoriteSeason;
import ru.youmiteru.backend.dto.SeasonDto.RatedSeason;
import ru.youmiteru.backend.dto.UserDTO;
import ru.youmiteru.backend.exceptions.UserNotFoundException;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UsersConvertors usersConvertors;
    private final SeasonConvertors seasonConvertors;

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return usersConvertors.convertUserToUserDto(
            user, getUserFavoriteSeasonList(id), getUserRatedSeasonList(id));
    }

    public List<FavoriteSeason> getUserFavoriteSeasonList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getFavoriteSeasonList().stream().map(seasonConvertors::convertSeasonToFavoriteSeasonDTO).toList();
    }

    public List<RatedSeason> getUserRatedSeasonList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getRatingList().stream().map(m -> seasonConvertors.convertSeasonToRatedSeason(m.getSeason(),
            m.getValue())).toList();
    }
}
