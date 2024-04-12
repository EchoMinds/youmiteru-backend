package ru.youmiteru.backend.controller.user_panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.service.RatingService;

@RestController
@RequestMapping("/api/season")
public class RatingPanelController {
    private final RatingService ratingPanelService;

    @Autowired
    public RatingPanelController(RatingService ratingPanelService) {
        this.ratingPanelService = ratingPanelService;
    }

    //Добавление оценки рейтинга к сезону
    @PostMapping("/{seasonId}/addRatingValue/{userId}")
    public HttpStatus addRatingValue(@PathVariable Long seasonId,
                                                     @PathVariable Long userId,
                                                     @RequestBody int ratingValue){
        return ratingPanelService.addRatingValueSeason(seasonId, userId, ratingValue);
    }

    //обновление оценки рейтинга сезона
    @PutMapping("/{seasonId}/addRatingValue/{userId}/{ratingId}")
    public HttpStatus updateRatingValue(@PathVariable Long seasonId,
                                                        @PathVariable Long userId,
                                                        @PathVariable Long ratingId,
                                                        @RequestBody int ratingValue){
        return ratingPanelService.updateRatingValueSeason(seasonId, userId, ratingId, ratingValue);
    }

    //удаление оценки сезона
    @DeleteMapping("/{seasonId}/addRatingValue/{userId}/{ratingId}")
    public HttpStatus deleteRatingValue(@PathVariable Long seasonId,
                                                        @PathVariable Long userId,
                                                        @PathVariable Long ratingId){

        return ratingPanelService.deleteRatingValueSeason(seasonId, userId, ratingId);
    }

}
