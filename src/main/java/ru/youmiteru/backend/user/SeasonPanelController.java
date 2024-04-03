package ru.youmiteru.backend.user;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Data
@RequestMapping("/api/season")
public class SeasonPanelController {
    private SeasonPanelService seasonPanelService;

    @PostMapping("/{seasonId}/addFavorite/{userId}")
    public ResponseEntity<HttpStatus> addFavoriteSeasonList(@PathVariable Long userId,
                                                            @PathVariable Long seasonId){
        return seasonPanelService.addFavorite(userId, seasonId);
    }

    @DeleteMapping("/{seasonId}/addFavorite/{userId}")
    public ResponseEntity<HttpStatus> deleteFavoriteSeasonList(@PathVariable Long userId,
                                                               @PathVariable Long seasonId){
        return seasonPanelService.deleteFavorite(userId, seasonId);
    }
}
