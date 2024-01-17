package ru.youmiteru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.AnimeFormat;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.TitleState;
import ru.youmiteru.backend.repositories.SeasonRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SeasonService {
    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    //Возвращает лист сезонов с лимитом 7, для баннера
    public List<Season> getAllSeasonForBanners (){
        List<Season> seasons = seasonRepository.findAll(PageRequest.of(0, 10))
            .stream().collect(Collectors.toList());

        return seasons;
    }

    //Возвращает лист анонсированных сезонов с лимитом 10
    public List<Season> getAllSeasonForAnnounced (){
        List<Season> seasons = (seasonRepository.findByTitleState( TitleState.ANNOUNCEMENT, PageRequest.of(0, 10)))
            .stream()
            .collect(Collectors.toList());

        return seasons;
    }

    //Возвращает лист популярных сезонов
    public List<Season> getAllPopularSeason(){
        List<Season> seasons = (seasonRepository.findAll(PageRequest.of(0, 10)))
            .stream()
            .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
            .collect(Collectors.toList());

        return seasons;
    }

    //Возвращает лист выпушенных сезонов
    public List<Season> getAllReleasedSeasons(){
        List<Season> seasons = (seasonRepository.findByTitleState( TitleState.RELEASED, PageRequest.of(0, 10)))
            .stream()
            .collect(Collectors.toList());

        return seasons;
    }
}
