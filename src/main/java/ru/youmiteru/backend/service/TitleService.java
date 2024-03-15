package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.convertors.TitleConvertors;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.Title.TitleCatalogDTO;
import ru.youmiteru.backend.dto.Title.TitlePageCountDto;
import ru.youmiteru.backend.repositories.GenreRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.TitleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;
    private final GenreRepository genreRepository;
    private final TitleConvertors titleConvertors;
    private final SeasonRepository seasonRepository;
    private static final Logger logger = LogManager.getLogger();

    //Возвращает каталог с сортировкой жанров
    public TitlePageCountDto getCatalog (Integer offset , List<String> filter, List<Long> dates, List<String> format,
                                                           List<String> state, List<String> ageRestriction, List<String> yearSeason) {

        List<TitleCatalogDTO> titleDto = filterForCatalog(filter, dates, format, state, ageRestriction, yearSeason)
            .stream()
            .map(titleConvertors::convertToCatalogDTO)
            .collect(Collectors.toList());

        Pageable pageable = PageRequest.of(offset, 20);
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), titleDto.size());

        List<TitleCatalogDTO> titlesForCatalog = new PageImpl<>(titleDto.subList(start, end), pageable, titleDto.size()).getContent();
        int totalPage = (int) Math.ceil((double) titleDto.size() / 20);

        return new TitlePageCountDto(offset, totalPage, titlesForCatalog);
    }

    public List<Title> filterForCatalog(List<String> genre, List<Long> dates,
                                        List<String> format, List<String> state,
                                        List<String> ageRestriction, List<String> yearSeason){

        List<Title> necessaryTitle = new ArrayList<>();
        CatalogFilterService catalogFilter = new CatalogFilterService(titleRepository, genreRepository, seasonRepository);
        int error_count = 0;

        if(genre == null && dates == null && format == null && state == null
            && ageRestriction == null && yearSeason == null){
            logger.info("метод filterForCatalog возвращает все данные из бд, так как требования были пустыми");
            return titleRepository.findAllForFilter();
        } else {
            try {
                //жанры
                if(genre != null){
                    logger.info("метод filterForCatalog просит у TitleConvertors Genre");
                    if(!catalogFilter.filterTitleGenre(genre).isEmpty()){
                        necessaryTitle = catalogFilter.filterTitleGenre(genre);
                    } else throw new Exception("TitleConvertors вернул genre пустым");
                }
                //даты года
                if(dates != null){
                    logger.info("метод filterForCatalog просит у TitleConvertors Dates");
                    if(!necessaryTitle.isEmpty()){
                        if(!catalogFilter.filterTitleDate(dates, necessaryTitle).isEmpty()){
                            necessaryTitle = catalogFilter.filterTitleDate(dates, necessaryTitle);
                        } else throw new Exception("TitleConvertors вернул dates пустым");
                    } else {
                        necessaryTitle = catalogFilter.filterTitleDate(dates, null);
                    }
                }

                //формат аниме(фильм, сериал, OVA)
                if(format != null){
                    logger.info("метод filterForCatalog просит у TitleConvertors AnimeFormat");
                    if(!necessaryTitle.isEmpty()){
                        if (!catalogFilter.filterTitleFormat(format, necessaryTitle).isEmpty()){
                            necessaryTitle = catalogFilter.filterTitleFormat(format, necessaryTitle);
                        } else throw new Exception("TitleConvertors вернул format пустым");
                    } else {
                        necessaryTitle = catalogFilter.filterTitleFormat(format, null);
                    }
                }

                //статус аниме (вышел, выходит, заброшен)
                if(state != null){
                    logger.info("метод filterForCatalog просит у TitleConvertors TitleState");
                    if(!necessaryTitle.isEmpty()){
                        if (!catalogFilter.filterTitleState(state, necessaryTitle).isEmpty()) {
                            necessaryTitle = catalogFilter.filterTitleState(state, necessaryTitle);
                        } else throw new Exception("TitleConvertors вернул state пустым");
                    } else {
                        necessaryTitle = catalogFilter.filterTitleState(state, null);
                    }
                }

                //возраст (18+, 12+, 6+)
                if(ageRestriction != null){
                    logger.info("метод filterForCatalog просит у TitleConvertors AgeRestriction");
                    if(!necessaryTitle.isEmpty()){
                        if(!catalogFilter.filterTitleAgeRestriction(ageRestriction, necessaryTitle).isEmpty()){
                            necessaryTitle = catalogFilter.filterTitleAgeRestriction(ageRestriction, necessaryTitle);
                        } else throw new Exception("TitleConvertors вернул ageRestriction пустым");
                    } else {
                        necessaryTitle = catalogFilter.filterTitleAgeRestriction(ageRestriction, null);
                    }
                }

                //сезонов месяца(зима, осень, весна, лето)
                if(yearSeason != null) {
                    logger.info("метод filterForCatalog просит у TitleConvertors YearSeason");
                    if (!necessaryTitle.isEmpty()) {
                        if(!catalogFilter.filterTitleYearSeason(yearSeason, necessaryTitle).isEmpty()){
                            necessaryTitle = catalogFilter.filterTitleYearSeason(yearSeason, necessaryTitle);
                        } else throw new Exception("TitleConvertors вернул yearSeason пустым");
                    } else {
                        necessaryTitle = catalogFilter.filterTitleYearSeason(yearSeason, null);
                    }
                }
                 return necessaryTitle;
            } catch (Exception e){
                logger.info("ошибка, вернулась пустая страница " + e.getMessage());
                return new ArrayList<>();
            }
        }
    }
}
