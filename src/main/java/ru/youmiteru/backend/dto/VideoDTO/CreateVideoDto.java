package ru.youmiteru.backend.dto.VideoDTO;

public record CreateVideoDto(
    int episode,
    String player,
    String playerUrl,
    Long seasonId
) {}
