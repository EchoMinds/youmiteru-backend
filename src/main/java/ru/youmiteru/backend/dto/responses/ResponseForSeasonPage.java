package ru.youmiteru.backend.dto.responses;

import ru.youmiteru.backend.dto.SeasonDto.SeasonPage;

public record ResponseForSeasonPage(
    SeasonPage seasonPage,
    Long userId

) {
}
