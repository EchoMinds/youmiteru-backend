package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Genre;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.TitleDTO;
import ru.youmiteru.backend.repositories.GenreRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.TitleRepository;
import ru.youmiteru.backend.util.CatalogFilter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;
    private final GenreRepository genreRepository;
    private final SeasonRepository seasonRepository;

    //Возвращает каталог с сортировкой жанров
    public List<TitleDTO.Response.Catalog> getCatalog (List<String> filter, List<Long> dates, List<String> format,
                                                       List<String> state, List<String> ageRestriction, List<String> yearSeason) {

        List<TitleDTO.Response.Catalog> titleDto = filterConvertorCatalog(filter, dates, format, state, ageRestriction, yearSeason)
            .stream().map(this::convertToCatalog).collect(Collectors.toList());

        return titleDto;

    }

    //конвертирует в дто для каталога
    public TitleDTO.Response.Catalog convertToCatalog(Title title){
        TitleDTO.Response.Catalog dto = new TitleDTO.Response.Catalog();

        dto.setTitleName(title.getName());
        dto.setTitleImageUrl(title.getTitleImageUrl());

        return dto;
    }

    public List<Title> filterConvertorCatalog(List<String> genre, List<Long> dates,
                                              List<String> format, List<String> state,
                                              List<String> ageRestriction, List<String> yearSeason){

        List<Title> necessaryTitle = new ArrayList<>();
        CatalogFilter catalogFilter = new CatalogFilter(titleRepository, genreRepository, seasonRepository);

        if(genre == null &&dates == null &&format == null &&state == null &&ageRestriction == null&&yearSeason == null){
            return titleRepository.findAllForFilter();
        } else {
            //жанры
            if(genre != null){
                necessaryTitle = catalogFilter.filterTitleGenre(genre);
            }

            //даты года
            if(dates != null){
                if(!necessaryTitle.isEmpty()){
                    necessaryTitle = catalogFilter.filterTitleDate(dates, necessaryTitle);
                } else {
                    necessaryTitle = catalogFilter.filterTitleDate(dates, null);
                }
            } else {

            }

            //формат аниме(фильм, сериал, OVA)
            if(format != null){
                if(!necessaryTitle.isEmpty()){
                    necessaryTitle = catalogFilter.filterTitleFormat(format, necessaryTitle);
                } else {
                    necessaryTitle = catalogFilter.filterTitleFormat(format, null);
                }
            }

            //статус аниме (вышел, выходит, заброшен)
            if(state != null){
                if(!necessaryTitle.isEmpty()){
                    necessaryTitle = catalogFilter.filterTitleState(state, necessaryTitle);
                } else {
                    necessaryTitle = catalogFilter.filterTitleState(state, null);
                }
            }

            //возраст (18+, 12+, 6+)
            if(ageRestriction != null){
                if(!necessaryTitle.isEmpty()){
                    necessaryTitle = catalogFilter.filterTitleAgeRestriction(ageRestriction, necessaryTitle);
                } else {
                    necessaryTitle = catalogFilter.filterTitleAgeRestriction(ageRestriction, null);
                }
            }

            //сезонов месяца(зима, осень, весна, лето)
            if(yearSeason != null) {
                if (!necessaryTitle.isEmpty()) {
                    necessaryTitle = catalogFilter.filterTitleYearSeason(yearSeason, necessaryTitle);
                } else {
                    necessaryTitle = catalogFilter.filterTitleYearSeason(yearSeason, null);
                }
            }
            return necessaryTitle;
        }
    }
}
