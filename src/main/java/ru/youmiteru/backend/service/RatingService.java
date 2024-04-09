package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public HttpStatus addRatingValueSeason(Long season_id, Long user_id, int value){
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

            return HttpStatus.OK;
        } else {
            throw new RatingNotFoundException();
        }
    }

    public HttpStatus updateRatingValueSeason(Long season_id, Long user_id, int value){


            Optional<Season> optionalSeason = seasonRepository.findById(season_id);
            Optional<User> optionalUser = userRepository.findById(user_id);

            if (optionalSeason.isPresent() && optionalUser.isPresent()) {
                Season season = optionalSeason.get();
                User user = optionalUser.get();
                Rating rating = ratingRepository.findByUserAndSeason(user, season);

                if (rating == null){
                    throw new RatingNotFoundException();
                }

                rating.setValue(value);
                season.setSeasonRatingList(List.of(rating));
                user.setRatingList(List.of(rating));

                ratingRepository.save(rating);
                return HttpStatus.OK;
            } else {
                throw new RatingNotFoundException();
            }
    }

    public HttpStatus deleteRatingValueSeason(Long season_id, Long user_id, int value){
        Optional<Season> optionalSeason = seasonRepository.findById(season_id);
        Optional<User> optionalUser = userRepository.findById(user_id);

        if (optionalSeason.isPresent() && optionalUser.isPresent()) {
            Season season = optionalSeason.get();
            User user = optionalUser.get();
            Rating rating = ratingRepository.findByUserAndSeason(user, season);

            if (rating == null){
                throw new RatingNotFoundException();
            }
            season.getSeasonRatingList().remove(rating);
            user.getRatingList().remove(rating);
            ratingRepository.delete(rating);
            seasonRepository.save(season);
            userRepository.save(user);

            return HttpStatus.OK;
        } else {
            throw new RatingNotFoundException();
        }
    }
}
