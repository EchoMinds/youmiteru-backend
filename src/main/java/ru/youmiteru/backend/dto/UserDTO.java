package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.youmiteru.backend.domain.Season;

import java.util.List;

public record UserDTO(
    Long userId,
    String profileImageUrl,
    String username,
    // season -> seasonDtoBasic(Only image,id,name)
    List<Season> favoriteSeasons,
    // season -> seasonDtoWithRating(basicDto+rating)
    List<Season> seasonHaveUserRating
){}
