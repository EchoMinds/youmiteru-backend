package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.*;
import ru.youmiteru.backend.dto.SeasonDto.HomePage;
import ru.youmiteru.backend.dto.SeasonDto.ListHomePage;
import ru.youmiteru.backend.dto.SeasonDto.RelatedSeason;
import ru.youmiteru.backend.dto.SeasonDto.SeasonPage;
import ru.youmiteru.backend.exceptions.SeasonNotFoundException;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private final SeasonConvertors seasonConvertors;
    private static final Logger logger = LogManager.getLogger();

    public ListHomePage getAllSeasonForHomePage() {
        logger.info("Запуск метода Сервиса getAllSeasonForHomePage");

        logger.info("Берёт из БД сезонов анонсы");
        List<HomePage> anons = seasonRepository.findAnnouncement()
            .stream()
            .map(seasonConvertors::homePageResponse)
            .collect(Collectors.toList());

        logger.info("Берёт из БД сезонов релизы");
        List<HomePage> release = seasonRepository.findRecent()
            .stream()
            .map(seasonConvertors::homePageResponse)
            .collect(Collectors.toList());

        logger.info("Берёт из БД сезонов баннер");
        List<HomePage> banner = seasonRepository.findBanner()
            .stream()
            .map(seasonConvertors::homePageResponse)
            .collect(Collectors.toList());

        logger.info("Берёт из БД сезонов популярные");
        List<HomePage> popular = seasonRepository.findPopular()
            .stream()
            .map(seasonConvertors::homePageResponse)
            .collect(Collectors.toList());

        logger.info("возвращает listHomePage");
        return new ListHomePage(banner, anons, popular, release);
    }

    public SeasonPage getSeasonPage(Long id) {
        Season seasonPages = seasonRepository.findById(id).orElseThrow(SeasonNotFoundException::new);
        List<RelatedSeason>  relatedSeasons =seasonPages.getTitle().getSeasonList().stream().map(seasonConvertors::convertToRelatedSeason).toList();
        return seasonConvertors.seasonPageResponse(seasonPages,
            relatedSeasons);
    }
}
