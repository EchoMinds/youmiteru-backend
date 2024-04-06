package ru.youmiteru.backend.user.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.user.service.RatingPanelService;

@RestController
@RequestMapping("/api/season")
@Data
public class RatingPanelController {
    private RatingPanelService ratingPanelService;

    @PostMapping("/{seasonId}/addRatingValue/{userId}")
    public ResponseEntity<HttpStatus> addRatingValue(@PathVariable Long seasonId,
                                                     @PathVariable Long userId,
                                                     @RequestBody int ratingValue){
        return ratingPanelService.addRatingValueSeason(seasonId, userId, ratingValue);
    }

    @PutMapping("/{seasonId}/addRatingValue/{userId}")
    public ResponseEntity<HttpStatus> updateRatingValue(@PathVariable Long seasonId,
                                                        @PathVariable Long userId,
                                                        @RequestBody int ratingValue){
        return ratingPanelService.updateRatingValueSeason(seasonId, userId, ratingValue);
    }

    @DeleteMapping("/{seasonId}/addRatingValue/{userId}")
    public ResponseEntity<HttpStatus> deleteRatingValue(@PathVariable Long seasonId,
                                                        @PathVariable Long userId,
                                                        @RequestBody int ratingValue){

        return ratingPanelService.deleteRatingValueSeason(seasonId, userId, ratingValue);
    }

}
