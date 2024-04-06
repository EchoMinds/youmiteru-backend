package ru.youmiteru.backend.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.user.service.SeasonPanelService;

@RestController
@RequestMapping("/api/season")
public class SeasonPanelController {
    private SeasonPanelService seasonPanelService;

    @Autowired
    public SeasonPanelController(SeasonPanelService seasonPanelService) {
        this.seasonPanelService = seasonPanelService;
    }


    @PostMapping("/{seasonId}/addFavorite/{userId}")
    public HttpStatus addFavoriteSeasonList(@PathVariable Long userId,
                                                            @PathVariable Long seasonId){

        if (HttpStatus.OK == seasonPanelService.addFavorite(userId, seasonId)){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @DeleteMapping("/{seasonId}/addFavorite/{userId}")
    public HttpStatus deleteFavoriteSeasonList(@PathVariable Long userId,
                                                               @PathVariable Long seasonId){
        if (HttpStatus.OK == seasonPanelService.deleteFavorite(userId, seasonId)){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
