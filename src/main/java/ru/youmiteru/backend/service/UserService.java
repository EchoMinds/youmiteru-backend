package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.convertors.UsersConvertors;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.dto.SeasonDto.FavoriteSeason;
import ru.youmiteru.backend.dto.SeasonDto.RatedSeason;
import ru.youmiteru.backend.dto.UserDTO;
import ru.youmiteru.backend.exceptions.UserNotFoundException;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UsersConvertors usersConvertors;
    private final SeasonConvertors seasonConvertors;
    private final SeasonRepository seasonRepository;

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

    public HttpStatus addFavorite(Long user_id, Long season_id){
        Optional<User> user = userRepository.findById(user_id);
        Optional<Season> season = seasonRepository.findById(season_id);
        System.out.println(user);


        if (user.isPresent() && season.isPresent()){
            Season newSeason = season.get();
            User newUser = user.get();

            List<Season> listSeason = new ArrayList<>(newUser.getFavoriteSeasonList());
            List<User> listUser = new ArrayList<>(newSeason.getThisUserLikeThisAnime());
            listSeason.add(newSeason);
            listUser.add(newUser);
            newUser.setFavoriteSeasonList(listSeason);
            newSeason.setThisUserLikeThisAnime(listUser);

            userRepository.save(newUser);
            seasonRepository.save(newSeason);

            return HttpStatus.OK;
        }

        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus deleteFavorite(Long user_id, Long season_id){
        Optional<User> user = userRepository.findById(user_id);
        Optional<Season> season = seasonRepository.findById(season_id);

        System.out.println(user);
        if (user.isPresent() && season.isPresent()){
            User trueUser = user.get();
            Season trueSeason = season.get();

            trueUser.getFavoriteSeasonList().remove(trueSeason);
            trueSeason.getThisUserLikeThisAnime().remove(trueUser);

            userRepository.save(trueUser);
            seasonRepository.save(trueSeason);

            return HttpStatus.OK;
        }
        return HttpStatus.BAD_REQUEST;
    }
}
