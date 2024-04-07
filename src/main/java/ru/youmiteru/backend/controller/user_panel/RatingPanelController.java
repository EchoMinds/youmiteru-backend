package ru.youmiteru.backend.controller.user_panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.service.RatingService;

@RestController
@RequestMapping("/api/season/{seasonId}/addRatingValue/{userId}")
public class RatingPanelController {
    private final RatingService ratingPanelService;

    @Autowired
    public RatingPanelController(RatingService ratingPanelService) {
        this.ratingPanelService = ratingPanelService;
    }

    //Добавление оценки рейтинга к сезону
    @PostMapping
    public HttpStatus addRatingValue(@PathVariable Long seasonId,
                                                     @PathVariable Long userId,
                                                     @RequestBody int ratingValue){
        Rating rating = ratingPanelService.addRatingValueSeason(seasonId, userId, ratingValue);

        if (rating != null){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    //обновление оценки рейтинга сезона
    @PutMapping
    public HttpStatus updateRatingValue(@PathVariable Long seasonId,
                                                        @PathVariable Long userId,
                                                        @RequestBody int ratingValue){
        Rating rating = ratingPanelService.updateRatingValueSeason(seasonId, userId, ratingValue);
        if (rating != null){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    //удаление оценки сезона
    @DeleteMapping
    public HttpStatus deleteRatingValue(@PathVariable Long seasonId,
                                                        @PathVariable Long userId,
                                                        @RequestBody int ratingValue){

        Rating rating = ratingPanelService.deleteRatingValueSeason(seasonId, userId, ratingValue);
        if (rating != null){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

}
