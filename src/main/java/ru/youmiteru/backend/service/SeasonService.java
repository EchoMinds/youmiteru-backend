package ru.youmiteru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        List<Season> seasons = seasonRepository.findAll();

        return seasons.stream().limit(7).collect(Collectors.toList());
    }

    //Возвращает лист анонсированных сезонов с лимитом 10
    public List<Season> getAllSeasonForAnnounced (){
        List<Season> seasons = (seasonRepository.findAll()).stream()
            .filter(state -> TitleState.ANNOUNCEMENT.equals(state.getTitle_state()))
            .limit(10)
            .collect(Collectors.toList());

        return seasons;
    }

    //Возвращает лист популярных сезонов
    public List<Season> getAllPopularSeason(){
        List<Season> seasons = (seasonRepository.findAll()).stream()
            .sorted((o1, o2) -> Math.toIntExact(o1.getId() - o2.getId()))
            .limit(10)
            .collect(Collectors.toList());

        return seasons;
    }
}
