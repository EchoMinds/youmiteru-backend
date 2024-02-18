package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.repositories.RatingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;


    //get rating
    public Double getRating(Long id) {

        //get class rating for season
        List<Rating> valueListWithRaitingClass = ratingRepository.findAllBySeason_Id(id);
        //get only value
        List<Integer> ratings = valueListWithRaitingClass.stream().map(Rating::getValue).toList();

        double rating = ratings.stream().mapToDouble(d -> d).average().orElse(0.0);
        double scaleRounding = 100;
        //rounding
        double result = Math.ceil(rating * scaleRounding) / scaleRounding;

        return result;
    }
}
