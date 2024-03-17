package ru.youmiteru.backend.dto.Title;

import ru.youmiteru.backend.dto.SeasonDto.HomePage;

import java.util.List;

public record TitlePageDTO (
    Long titleId,
    String titleName,
    String titleImage,
    String titleDescription,
    List<String> genreName,
    List<HomePage> seasonList
)
{ }
