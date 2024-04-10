package ru.youmiteru.backend.user.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.exceptions.RatingNotFoundException;
import ru.youmiteru.backend.fakeDomain.FakeDomainCreator;
import ru.youmiteru.backend.repositories.RatingRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.UserRepository;
import ru.youmiteru.backend.service.RatingService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RatingPanelServiceTest {
    @InjectMocks
    private RatingService ratingPanelService;
    @Mock
    private SeasonRepository seasonRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private RatingRepository ratingRepository;

    private Long season_id;
    private Long user_id;
    private int ratingValue;

    private Season fakeSeason;
    private User fakeUser;
    private Rating fakeRating;

    @BeforeEach
    void init(){
        season_id = 1L;
        user_id = 1L;
        ratingValue = 0;
        fakeSeason = FakeDomainCreator.createFakeSeason();
        fakeUser = FakeDomainCreator.createFakeUser();
        fakeRating =FakeDomainCreator.createFakeRating();
        fakeSeason.setSeasonRatingList(List.of(fakeRating));
    }

    @Test
    void testAddRatingValueSeason(){
        when(seasonRepository.findById(season_id)).thenReturn(Optional.of(fakeSeason));
        when(userRepository.findById(user_id)).thenReturn(Optional.of(fakeUser));

        HttpStatus result = ratingPanelService.addRatingValueSeason(season_id, user_id, ratingValue);

        assertEquals(HttpStatus.OK, result);

        verify(seasonRepository, times(1)).findById(season_id);
        verify(userRepository, times(1)).findById(user_id);
        verify(ratingRepository, times(1)).save(any(Rating.class));
        verify(seasonRepository, times(1)).save(fakeSeason);
        verify(userRepository, times(1)).save(fakeUser);
    }

    @Test
    public void testUpdateRatingValueSeason_Success() {
        Long seasonId = 1L;
        Long userId = 1L;
        Long ratingId = 1L;
        int newValue = 5;

        Rating rating = new Rating();
        rating.setId(ratingId);
        rating.setValue(3); // original value
        Season season = new Season();
        season.setId(seasonId);
        User user = new User();
        user.setId(userId);
        rating.setSeason(season);
        rating.setUser(user);

        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));

        HttpStatus result = ratingPanelService.updateRatingValueSeason(seasonId, userId, ratingId, newValue);

        assertEquals(HttpStatus.OK, result);
        assertEquals(newValue, rating.getValue());

        verify(ratingRepository, times(1)).findById(ratingId);
        verify(ratingRepository, times(1)).save(rating);
    }

    @Test
    public void testUpdateRatingValueSeason_RatingNotFound() {
        Long seasonId = 1L;
        Long userId = 1L;
        Long ratingId = 1L;
        int newValue = 5;

        when(ratingRepository.findById(ratingId)).thenReturn(Optional.empty());

        assertThrows(RatingNotFoundException.class, () -> {
            ratingPanelService.updateRatingValueSeason(seasonId, userId, ratingId, newValue);
        });

        verify(ratingRepository, times(1)).findById(ratingId);
        verify(ratingRepository, never()).save(any());
    }

    @Test
    public void testUpdateRatingValueSeason_InvalidSeasonOrUser() {
        Long seasonId = 1L;
        Long userId = 1L;
        Long ratingId = 1L;
        int newValue = 5;

        Rating rating = new Rating();
        rating.setId(ratingId);
        Season season = new Season();
        season.setId(2L); // Different season ID
        User user = new User();
        user.setId(2L); // Different user ID
        rating.setSeason(season);
        rating.setUser(user);

        when(ratingRepository.findById(ratingId)).thenReturn(Optional.of(rating));

        HttpStatus result = ratingPanelService.updateRatingValueSeason(seasonId, userId, ratingId, newValue);

        assertEquals(HttpStatus.BAD_REQUEST, result);

        verify(ratingRepository, times(1)).findById(ratingId);
        verify(ratingRepository, never()).save(any());
    }
}
