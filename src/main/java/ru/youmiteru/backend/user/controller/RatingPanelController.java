package ru.youmiteru.backend.user.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.user.service.RatingPanelService;

@RestController
@RequestMapping("/api/season")
@Data
public class RatingPanelController {
    private RatingPanelService ratingPanelService;

    @PostMapping("/{seasonId}/addRatingValue/{userId}")
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

    @PutMapping("/{seasonId}/addRatingValue/{userId}")
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

    @DeleteMapping("/{seasonId}/addRatingValue/{userId}")
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
