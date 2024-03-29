package ru.youmiteru.backend.dto.GenreDTO;

import java.util.List;

public record GenreDto(
    Long id,
    String name,
    List<Long> titleIds
) {}
