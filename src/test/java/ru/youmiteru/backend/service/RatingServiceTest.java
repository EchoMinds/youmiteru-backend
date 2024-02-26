package ru.youmiteru.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.repositories.RatingRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("RatingServiceTest")
@ExtendWith(MockitoExtension.class)
class RatingServiceTest {
    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    @Test
    @DisplayName("getRating")
    void getRating() {
        when(ratingRepository.getRatingBySeasonId(1L)).thenReturn(10.0);

        assertEquals(10.0, ratingService.getRating(1L));


    }
}
