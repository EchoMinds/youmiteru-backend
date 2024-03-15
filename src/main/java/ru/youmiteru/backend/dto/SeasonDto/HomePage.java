package ru.youmiteru.backend.dto.SeasonDto;

public record HomePage(
    Long seasonId,
    String seasonName,
    String description,
    String imageUrl
) {}
