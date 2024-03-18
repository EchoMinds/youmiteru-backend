package ru.youmiteru.backend.dto.VideoDTO;

public record VideoDetailDto (
        Long id,
        int episode,
        String player,
        String playerUrl,
        Long seasonId
){}
