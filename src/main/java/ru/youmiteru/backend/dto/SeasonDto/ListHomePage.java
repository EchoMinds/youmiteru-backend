package ru.youmiteru.backend.dto.SeasonDto;

import java.util.List;

public record ListHomePage(
    List<HomePage> banners,
    List<HomePage> announcedSeasons,
    List<HomePage> popularSeasons,
    List<HomePage> recentReleasedSeasons
) {}
