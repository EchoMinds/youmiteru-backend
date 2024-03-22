package ru.youmiteru.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.convertors.UsersConvertors;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.dto.SeasonDto.FavoriteSeason;
import ru.youmiteru.backend.dto.SeasonDto.RatedSeason;
import ru.youmiteru.backend.dto.UserDTO;
import ru.youmiteru.backend.exceptions.UserNotFoundException;
import ru.youmiteru.backend.fakeDomain.FakeDomainCreator;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UsersConvertors usersConvertors;
    @Mock
    private SeasonConvertors seasonConvertors;

    private User fakeUser;

    @BeforeEach
    void init() {
        fakeUser = FakeDomainCreator.createFakeUser();
    }

    @Test
    @DisplayName("getUserById should Return User")
    void test_getUserById_shouldReturnUser() {
        //Create Mock
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(fakeUser));
        when(usersConvertors.convertUserToUserDto(any(User.class), anyList(), anyList())).thenReturn(FakeDomainCreator.createFakeUserDTO());

        assertNotNull(userService.getUserById(1L));
    }

    @Test
    @DisplayName("getUserByIdshould Return Error")
    void test_getUserById_shouldReturnError() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserById(2L));
    }

    @Test
    @DisplayName("getUserFavoriteSeasonList should Return Favorite Season")
    void test_getUserFavoriteSeasonList_shouldReturnFavoriteSeason() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(fakeUser));
        when(seasonConvertors.convertSeasonToFavoriteSeasonDTO(any(Season.class))).thenReturn(new FavoriteSeason(1L,
            "Fake Season", "https://example.com/season_image.jpg"));

        assertNotNull(userService.getUserFavoriteSeasonList(1L));
    }

    @Test
    @DisplayName("getUserFavoriteSeasonList should Return Error")
    void test_getUserFavoriteSeasonList_shouldReturnError() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserFavoriteSeasonList(2L));
    }

    @Test
    @DisplayName("getUserRatedSeasonList should Return Rated Season")
    void test_getUserRatedSeasonList_shouldReturnFavoriteSeason() {
        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(fakeUser));
        when(seasonConvertors.convertSeasonToRatedSeason(any(Season.class), eq(5))).thenReturn(
            new RatedSeason(1L, "fake season", "realUrl", 5)
        );

        assertNotNull(userService.getUserRatedSeasonList(1L));
    }

    @Test
    @DisplayName("getUserRatedSeasonList should Return Error")
    void test_getUserRatedSeasonList_shouldReturnError() {
        assertThrows(UserNotFoundException.class, () -> userService.getUserRatedSeasonList(2L));
    }


}
