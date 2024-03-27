package ru.youmiteru.backend.convertors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.dto.SeasonDto.FavoriteSeason;
import ru.youmiteru.backend.dto.SeasonDto.RatedSeason;
import ru.youmiteru.backend.fakeDomain.FakeDomainCreator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UsersConvertorsTest {
    @InjectMocks
    private UsersConvertors usersConvertors;

    @Test
    void convertUserToUserDto() {
        User user = FakeDomainCreator.createFakeUser();
        assertEquals(
            FakeDomainCreator.createFakeUserDTO(),
            usersConvertors.convertUserToUserDto(user,
                List.of(new FavoriteSeason(
                    1L, "Fake Season", "https://example.com/season_image.jpg")),
                List.of(new RatedSeason(
                    1L, "Fake Season", "https://example.com/season_image.jpg", 5))
            )
        );
    }
}
