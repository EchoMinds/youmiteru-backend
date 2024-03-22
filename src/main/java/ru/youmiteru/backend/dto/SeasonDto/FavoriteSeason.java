package ru.youmiteru.backend.dto.SeasonDto;

public record FavoriteSeason(
    Long seasonId,
    String seasonName,
    String imageUrl
) {}
