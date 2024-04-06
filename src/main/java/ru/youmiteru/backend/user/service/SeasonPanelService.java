package ru.youmiteru.backend.user.service;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class SeasonPanelService {
    private SeasonRepository seasonRepository;
    private UserRepository userRepository;

    public ResponseEntity<HttpStatus> addFavorite(Long user_id, Long season_id){
        Optional<User> user = userRepository.findById(user_id);
        Optional<Season> season = seasonRepository.findById(season_id);

        if (user.isPresent() && season.isPresent()){
            Season newSeason = season.get();
            User newUser = user.get();

            newUser.setFavoriteSeasonList(List.of(newSeason));
            newSeason.setThisUserLikeThisAnime(List.of(newUser));
            userRepository.save(newUser);
            seasonRepository.save(newSeason);

            return (ResponseEntity<HttpStatus>) ResponseEntity.ok();
        }

        return (ResponseEntity<HttpStatus>) ResponseEntity.notFound();
    }

    public ResponseEntity<HttpStatus> deleteFavorite(Long user_id, Long season_id){
        Optional<User> user = userRepository.findById(user_id);
        Optional<Season> season = seasonRepository.findById(season_id);

        if (user.isPresent() && season.isPresent()){
            User trueUser = user.get();
            Season trueSeason = season.get();

            trueUser.getFavoriteSeasonList().remove(trueSeason);
            trueSeason.getThisUserLikeThisAnime().remove(trueUser);

            userRepository.save(trueUser);
            seasonRepository.save(trueSeason);

            return (ResponseEntity<HttpStatus>) ResponseEntity.ok();
        }
        return (ResponseEntity<HttpStatus>) ResponseEntity.badRequest();
    }
}
