package ru.youmiteru.backend.dto.Title;

import java.time.LocalDate;

public record SeasonList(
    Long id,
    String name,
    String seasonImageUrl,
    String descriptrion,
    LocalDate releaseDate,
    String titleState,
    String ageRestriction,
    String yearSeason,
    String animeFormat

) {
}
