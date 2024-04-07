package ru.youmiteru.backend.controller.user_panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.service.UserService;

@RestController
@RequestMapping("/api/season/{seasonId}/favorite/{userId}")
public class SeasonPanelController {
    private final UserService seasonPanelService;

    @Autowired
    public SeasonPanelController(UserService seasonPanelService) {
        this.seasonPanelService = seasonPanelService;
    }

    //Добавление в избранное юзера
    @PostMapping
    public HttpStatus addFavoriteSeasonList(@PathVariable Long userId,
                                                            @PathVariable Long seasonId){

        if (HttpStatus.OK == seasonPanelService.addFavorite(userId, seasonId)){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    //Удаление в избранное юзера
    @DeleteMapping
    public HttpStatus deleteFavoriteSeasonList(@PathVariable Long userId,
                                                               @PathVariable Long seasonId){
        if (HttpStatus.OK == seasonPanelService.deleteFavorite(userId, seasonId)){
            return HttpStatus.OK;
        } else {
            return HttpStatus.BAD_REQUEST;
        }
    }
}
