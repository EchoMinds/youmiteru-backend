package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;
    private final SeasonRepository seasonRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LogManager.getLogger();
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


            Rating rating = new Rating(value, user, season);

            List<Rating> listUser = new ArrayList<>(user.getRatingList());
            List<Rating> listSeason = new ArrayList<>(season.getSeasonRatingList());
            listSeason.add(rating);
            listUser.add(rating);
            user.setRatingList(listUser);
            season.setSeasonRatingList(listSeason);

            ratingRepository.save(rating);
            seasonRepository.save(season);
            userRepository.save(user);

            logger.info("addRatingValueSeason нашел нужные данные");
            return HttpStatus.OK;
        } else {
            logger.info("addRatingValueSeason не смогу найти User или Season");
            throw new RatingNotFoundException();
        }
    }

    public HttpStatus updateRatingValueSeason(Long season_id, Long user_id, Long ratingId , int newValue){

        Optional<Rating> optionalRating = ratingRepository.findById(ratingId);

        if (optionalRating.isPresent()) {
            Rating rating = optionalRating.get();
            if (rating.getSeason().getId().equals(season_id) && rating.getUser().getId().equals(user_id)){

                rating.setValue(newValue);
                ratingRepository.save(rating);
                return HttpStatus.OK;
            }
            return HttpStatus.BAD_REQUEST;
        } else {
            throw new RatingNotFoundException();
        }
    }

    public HttpStatus deleteRatingValueSeason(Long season_id, Long user_id, Long rating_id){
        Optional<Season> optionalSeason = seasonRepository.findById(season_id);
        Optional<User> optionalUser = userRepository.findById(user_id);
        Optional<Rating> optionalRating = ratingRepository.findById(rating_id);

        if (optionalSeason.isPresent() && optionalUser.isPresent()&&optionalRating.isPresent()) {
            Season season = optionalSeason.get();
            User user = optionalUser.get();
            Rating rating = optionalRating.get();
            if (rating.getSeason().getId().equals(season_id) && rating.getUser().getId().equals(user_id)){

                season.getSeasonRatingList().remove(rating);
                user.getRatingList().remove(rating);

                ratingRepository.delete(rating);
                seasonRepository.save(season);
                userRepository.save(user);
                return HttpStatus.OK;
            }

            return HttpStatus.BAD_REQUEST;
        } else {
            throw new RatingNotFoundException();
        }
    }
}
