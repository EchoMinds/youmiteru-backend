package ru.youmiteru.backend.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.Genre;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.Title.TitleCatalogDTO;
import ru.youmiteru.backend.dto.Title.TitlePageDTO;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TitleConvertors {
    private final SeasonConvertors seasonConvertors;
    public TitleCatalogDTO convertToCatalogDTO(Title title){
        return new TitleCatalogDTO(
            title.getId(),
            title.getName(),
            title.getTitleImageUrl()
        );
    }

    public TitlePageDTO convertToPageDTO(Title title){
        return new TitlePageDTO(
            title.getId(),
            title.getName(),
            title.getTitleImageUrl(),
            title.getDescription(),
            title.getGenres().stream().map(Genre::getName).collect(Collectors.toList()),
            title.getSeasonList().stream().map(seasonConvertors::homePageResponse).collect(Collectors.toList())
        );
    }

}
