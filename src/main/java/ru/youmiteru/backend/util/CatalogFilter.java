package ru.youmiteru.backend.util;

import lombok.RequiredArgsConstructor;
import org.flywaydb.core.extensibility.Tier;
import ru.youmiteru.backend.domain.Genre;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.repositories.GenreRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.TitleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CatalogFilter {
    private final TitleRepository titleRepository;
    private final GenreRepository genreRepository;
    private final SeasonRepository seasonRepository;


    //----------Фильтрация для жанра
    public List<Title> filterTitleGenre(List<String> filter){
        List<Long> genreIds = new ArrayList<>();
        List<Long> titleIds = new ArrayList<>();

        for (String name : filter){
            for (Genre searchGenre : genreRepository.findByName(name)){
                genreIds.add(searchGenre.getId());
            }
        }

        List<Long> checkTitleIds = titleRepository.findTitleIdsByGenreIdsList(genreIds);

        for (Long id : checkTitleIds){
            int i = 0;
            for (Long ids : titleRepository.findGenreIdsByTitleIds(id)){
                for (Long oo : genreIds){
                    if (oo.equals(ids)){
                        i++;
                    }
                }
            }
            if (i == genreIds.size()){
                titleIds.add(id);
            }
        }
        return titleRepository.findAllById(titleIds).stream().distinct().collect(Collectors.toList());
    }


    //----------Фильтрация для даты года
    public List<Title> filterTitleDate(List<Long> dates, List<Title> checkTitle){
        List<Title> necessaryTitle = new ArrayList<>();

        if (checkTitle!=null){
            for (Title title : checkTitle) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(Long date : dates ){
                        if ((seasons.getReleaseDate().getYear()) == date){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        } else {
            for (Title title : titleRepository.findAllForFilter()) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(Long date : dates ){
                        if ((seasons.getReleaseDate().getYear()) == date){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        }

        return necessaryTitle.stream().distinct().collect(Collectors.toList());
    }


    //----------Фильтрация формата аниме(фильм, сериал, OVA)
    public List<Title> filterTitleFormat(List<String> formats, List<Title> checkTitle){
        List<Title> necessaryTitle = new ArrayList<>();

        if(checkTitle!=null){
            for (Title title : checkTitle) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(String format : formats){
                        if ((seasons.getAnimeFormat().toString()).equalsIgnoreCase(format)){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        } else {
            for (Title title : titleRepository.findAllForFilter()) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(String format : formats){
                        if ((seasons.getAnimeFormat().toString()).equals(format)){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        }

        return necessaryTitle.stream().distinct().collect(Collectors.toList());
    }

    //----------Фильтрация статуса аниме (вышел, выходит, заброшен)
    public List<Title> filterTitleState(List<String> states, List<Title> checkTitle){
        List<Title> necessaryTitle = new ArrayList<>();

        if(checkTitle!=null){
            for (Title title : checkTitle) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(String state : states){
                        if ((seasons.getTitleState().toString()).equals(state)){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        } else {
            for (Title title : titleRepository.findAllForFilter()) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(String state : states){
                        if ((seasons.getTitleState().toString()).equals(state)){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        }

        return necessaryTitle.stream().distinct().collect(Collectors.toList());
    }

    //----------Фильтрация возраста (18+, 12+, 6+)
    public List<Title> filterTitleAgeRestriction(List<String> ageRestrictions,List<Title> checkTitle){
        List<Title> necessaryTitle = new ArrayList<>();

        if (checkTitle!=null){
            for (Title title : checkTitle) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(String age : ageRestrictions){
                        if ((seasons.getAgeRestriction()).equals(age)){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        } else {
            for (Title title : titleRepository.findAllForFilter()) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(String age : ageRestrictions){
                        if ((seasons.getAgeRestriction()).equals(age)){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        }

        return necessaryTitle.stream().distinct().collect(Collectors.toList());
    }

    //----------Фильтрация сезонов месяца(зима, осень, весна, лето)
    public List<Title> filterTitleYearSeason(List<String> yearSeasons,List<Title> checkTitle){
        List<Title> necessaryTitle = new ArrayList<>();

        if (checkTitle!=null){
            for (Title title : checkTitle) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(String year : yearSeasons){
                        if ((seasons.getYearSeason()).equals(year)){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        } else {
            for (Title title : titleRepository.findAllForFilter()) {
                for (Season seasons : seasonRepository.findByTitle(title)){
                    for(String year : yearSeasons){
                        if ((seasons.getYearSeason()).equals(year)){
                            necessaryTitle.add(title);
                        }
                    }
                }
            }
        }

        return necessaryTitle.stream().distinct().collect(Collectors.toList());
    }

}
