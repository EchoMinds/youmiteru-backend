package ru.youmiteru.backend.user.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.youmiteru.backend.user.service.SeasonPanelService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class SeasonPanelControllerTest {
    @InjectMocks
    private SeasonPanelController seasonPanelController;

    @Mock
    private SeasonPanelService seasonPanelService;

    private Long user_id = 1L;
    private Long season_id = 1L;

    @Test
    void testAddFavoriteSeasonListOK(){
        when(seasonPanelService.addFavorite(user_id, season_id)).thenReturn(HttpStatus.OK);

        HttpStatus httpStatus = seasonPanelController.addFavoriteSeasonList(user_id, season_id);
        assertEquals(httpStatus, HttpStatus.OK);
    }
    @Test
    void testAddFavoriteSeasonListBadRequest(){
        HttpStatus httpStatus = seasonPanelController.addFavoriteSeasonList(user_id, season_id);
        assertEquals(httpStatus, HttpStatus.BAD_REQUEST);
    }

    @Test
    void testDeleteFavoriteSeasonListOk(){
        when(seasonPanelService.deleteFavorite(user_id, season_id)).thenReturn(HttpStatus.OK);
        HttpStatus httpStatus = seasonPanelController.deleteFavoriteSeasonList(user_id, season_id);
        assertEquals(httpStatus, HttpStatus.OK);
    }

    @Test
    void testDeleteFavoriteSeasonListBadRequest(){
        HttpStatus httpStatus = seasonPanelController.deleteFavoriteSeasonList(user_id, season_id);
        assertEquals(httpStatus, HttpStatus.BAD_REQUEST);
    }
}
