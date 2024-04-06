package ru.youmiteru.backend.user.service;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.repositories.RatingRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class RatingPanelService {
    private SeasonRepository seasonRepository;
    private UserRepository userRepository;
    private RatingRepository ratingRepository;

    public Rating addRatingValueSeason(Long season_id, Long user_id, int value){
        Optional<Season> optionalSeason = seasonRepository.findById(season_id);
        Optional<User> optionalUser = userRepository.findById(user_id);

        if(optionalSeason.isPresent() && optionalUser.isPresent()){
            Season season = optionalSeason.get();
            User user = optionalUser.get();
            Rating rating = new Rating(value, season, user);

            season.setSeasonRatingList(List.of(rating));
            user.setRatingList(List.of(rating));

            ratingRepository.save(rating);
            seasonRepository.save(season);
            userRepository.save(user);

            return rating;
        }

        return null;
    }

    public Rating updateRatingValueSeason(Long season_id, Long user_id, int value){
        Optional<Season> optionalSeason = seasonRepository.findById(season_id);
        Optional<User> optionalUser = userRepository.findById(user_id);

        if (optionalSeason.isPresent() && optionalUser.isPresent()){
            Season season = optionalSeason.get();
            User user = optionalUser.get();
            Rating rating = ratingRepository.findByUserAndSeason(user, season);

            rating.setValue(value);
            season.setSeasonRatingList(List.of(rating));
            user.setRatingList(List.of(rating));

            ratingRepository.save(rating);
            return rating;
        }

        return null;
    }

    public Rating deleteRatingValueSeason(Long season_id, Long user_id, int value){
        Optional<Season> optionalSeason = seasonRepository.findById(season_id);
        Optional<User> optionalUser = userRepository.findById(user_id);

        if (optionalSeason.isPresent() && optionalUser.isPresent()) {
            Season season = optionalSeason.get();
            User user = optionalUser.get();
            Rating rating = ratingRepository.findByUserAndSeason(user, season);

            season.getSeasonRatingList().remove(rating);
            user.getRatingList().remove(rating);
            ratingRepository.delete(rating);
            seasonRepository.save(season);
            userRepository.save(user);

            return rating;
        }

        return null;
    }
}
