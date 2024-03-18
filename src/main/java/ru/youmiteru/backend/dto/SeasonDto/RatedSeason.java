package ru.youmiteru.backend.dto.SeasonDto;

public record RatedSeason(
    Long seasonId,
    String seasonName,
    String imageUrl,
    Double seasonRating
) {
}
