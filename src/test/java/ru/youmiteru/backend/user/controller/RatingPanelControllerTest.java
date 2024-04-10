package ru.youmiteru.backend.user.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.youmiteru.backend.controller.user_panel.RatingPanelController;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.service.RatingService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingPanelControllerTest {
    @InjectMocks
    private RatingPanelController ratingPanelController;
    @Mock
    private RatingService ratingPanelService;

    private int value = 1;
    private Long rating_id = 1L;
    private Long season_id = 1L;
    private Long user_id = 1L;

    //POST
    @Test
    void testAddRatingValueOK(){
        when(ratingPanelService.addRatingValueSeason(season_id, user_id, value)).thenReturn(HttpStatus.OK);
        HttpStatus http = ratingPanelController.addRatingValue(season_id, user_id, value);
        assertEquals(http, HttpStatus.OK);
    }
    @Test
    void testAddRatingValueBadRequest(){
        HttpStatus http = ratingPanelController.addRatingValue(season_id, user_id, value);
        assertEquals(http, null);
    }

    //PUT
    @Test
    void testUpdateRatingValueOK(){
        when(ratingPanelService.updateRatingValueSeason(season_id, user_id, rating_id, value)).thenReturn(HttpStatus.OK);
        HttpStatus http = ratingPanelController.updateRatingValue(season_id, user_id, rating_id, value);
        assertEquals(http, HttpStatus.OK);
    }
    @Test
    void testUpdateRatingValueBadRequest(){
        HttpStatus http = ratingPanelController.updateRatingValue(season_id, user_id, rating_id, value);
        assertEquals(http, null);
    }

    //DELETE
    @Test
    void testDeleteRatingValueBadRequest(){
        HttpStatus http = ratingPanelController.deleteRatingValue(season_id, user_id, rating_id);
        assertEquals(http, null);
    }


}
