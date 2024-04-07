package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.exceptions.RatingNotFoundException;
import ru.youmiteru.backend.repositories.RatingRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final SeasonRepository seasonRepository;
    private final UserRepository userRepository;
    @Autowired
    public RatingService(SeasonRepository seasonRepository, UserRepository userRepository, RatingRepository ratingRepository) {
        this.seasonRepository = seasonRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    public Double getRating(Long id) {
        return ratingRepository.getRatingBySeasonId(id);
    }

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

        throw new RatingNotFoundException();
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

        throw new RatingNotFoundException();
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

        throw new RatingNotFoundException();
    }
}
