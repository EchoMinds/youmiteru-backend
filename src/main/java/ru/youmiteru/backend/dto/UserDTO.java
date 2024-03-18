package ru.youmiteru.backend.dto;



import ru.youmiteru.backend.dto.SeasonDto.FavoriteSeason;
import ru.youmiteru.backend.dto.SeasonDto.RatedSeason;

import java.util.List;

public record UserDTO(
    Long userId,
    String profileImageUrl,
    String username,
    // season -> seasonDtoBasic(Only image,id,name)
    List<FavoriteSeason> favoriteSeasons,
    // season -> seasonDtoWithRating(basicDto+rating)
    List<RatedSeason> ratedSeasons
){}
