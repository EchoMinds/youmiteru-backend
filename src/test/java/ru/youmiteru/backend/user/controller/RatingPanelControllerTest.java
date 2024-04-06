package ru.youmiteru.backend.user.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.user.service.RatingPanelService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatingPanelControllerTest {
    @InjectMocks
    private RatingPanelController ratingPanelController;
    @Mock
    private RatingPanelService ratingPanelService;

    private int value = 1;
    private Long season_id = 1L;
    private Long user_id = 1L;

    //POST
    @Test
    void testAddRatingValueOK(){
        when(ratingPanelService.addRatingValueSeason(season_id, user_id, value)).thenReturn(new Rating());
        HttpStatus http = ratingPanelController.addRatingValue(season_id, user_id, value);
        assertEquals(http, HttpStatus.OK);
    }
    @Test
    void testAddRatingValueBadRequest(){
        HttpStatus http = ratingPanelController.addRatingValue(season_id, user_id, value);
        assertEquals(http, HttpStatus.BAD_REQUEST);
    }

    //PUT
    @Test
    void testUpdateRatingValueOK(){
        when(ratingPanelService.updateRatingValueSeason(season_id, user_id, value)).thenReturn(new Rating());
        HttpStatus http = ratingPanelController.updateRatingValue(season_id, user_id, value);
        assertEquals(http, HttpStatus.OK);
    }
    @Test
    void testUpdateRatingValueBadRequest(){
        HttpStatus http = ratingPanelController.updateRatingValue(season_id, user_id, value);
        assertEquals(http, HttpStatus.BAD_REQUEST);
    }

    //DELETE
    @Test
    void testDeleteRatingValueBadRequest(){
        HttpStatus http = ratingPanelController.updateRatingValue(season_id, user_id, value);
        assertEquals(http, HttpStatus.BAD_REQUEST);
    }


}
