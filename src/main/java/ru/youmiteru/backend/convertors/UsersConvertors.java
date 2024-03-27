package ru.youmiteru.backend.convertors;

import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.dto.SeasonDto.FavoriteSeason;
import ru.youmiteru.backend.dto.SeasonDto.RatedSeason;
import ru.youmiteru.backend.dto.UserDTO;

import java.util.List;

@Component
public class UsersConvertors {

    public UserDTO convertUserToUserDto(User user,
                                        List<FavoriteSeason> favoriteSeasons,
                                        List<RatedSeason> seasonHaveUserRating
    ) {
        return new UserDTO(user.getId(), user.getProfileImageUrl(),
            user.getName(), favoriteSeasons, seasonHaveUserRating);
    }
}

