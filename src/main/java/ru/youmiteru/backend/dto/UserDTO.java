package ru.youmiteru.backend.dto;



import ru.youmiteru.backend.dto.SeasonDto.FavoriteSeason;
import ru.youmiteru.backend.dto.SeasonDto.RatedSeason;

import java.util.List;

public record UserDTO(
    Long userId,
    String profileImageUrl,
    String username,
    List<FavoriteSeason> favoriteSeasons,
    List<RatedSeason> ratedSeasons
){}
