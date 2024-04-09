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
import ru.youmiteru.backend.fakeDomain.FakeDomainCreator;
import ru.youmiteru.backend.repositories.RatingRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.UserRepository;
import ru.youmiteru.backend.service.RatingService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    }

    @Test
    void testAddRatingValueSeason(){
        when(seasonRepository.findById(season_id)).thenReturn(Optional.ofNullable(fakeSeason));
        when(userRepository.findById(user_id)).thenReturn(Optional.ofNullable(fakeUser));
        HttpStatus rating = ratingPanelService.addRatingValueSeason(season_id, user_id, ratingValue);

        assertNotNull(rating);
        assertEquals(HttpStatus.OK, rating);
    }

    @Test
    void testUpdateRatingValueSeason(){
        when(seasonRepository.findById(season_id)).thenReturn(Optional.ofNullable(fakeSeason));
        when(userRepository.findById(user_id)).thenReturn(Optional.ofNullable(fakeUser));
        when(ratingRepository.findByUserAndSeason(fakeUser, fakeSeason)).thenReturn(fakeRating);

        HttpStatus rating = ratingPanelService.updateRatingValueSeason(fakeSeason.getId(), fakeUser.getId(), fakeRating.getValue());

        assertNotNull(rating);
        assertEquals(rating, HttpStatus.OK);
    }

}
