package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.repositories.SeasonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private static final Logger logger = LogManager.getLogger();

    //Return data for HomePage
    public SeasonDTO.Response.ListHomePage getAllSeasonForHomePage(){
        logger.info("Запуск метода Сервиса getAllSeasonForHomePage");
        SeasonDTO.Response.ListHomePage listHomePage = new SeasonDTO.Response.ListHomePage();

        logger.info("Берёт из БД сезонов анонсы");
        List<SeasonDTO.Response.HomePage> anons = seasonRepository.findAnnouncement()
            .stream().map(this::convertToSeasonDTO).collect(Collectors.toList());
        logger.info("Берёт из БД сезонов релизы");
        List<SeasonDTO.Response.HomePage> release = seasonRepository.findRecent()
            .stream().map(this::convertToSeasonDTO).collect(Collectors.toList());
        logger.info("Берёт из БД сезонов баннер");
        List<SeasonDTO.Response.HomePage> banner = seasonRepository.findBanner()
            .stream().map(this::convertToSeasonDTO).collect(Collectors.toList());
        logger.info("Берёт из БД сезонов популярные");
        List<SeasonDTO.Response.HomePage> popular = seasonRepository.findPopular()
            .stream().map(this::convertToSeasonDTO).collect(Collectors.toList());


        logger.info("Конвертация анонсов в лист ДТО");
        listHomePage.setBanners(banner);
        logger.info("Конвертация релизов в лист ДТО");
        listHomePage.setAnnounced_seasons(anons);
        logger.info("Конвертация баннеров в лист ДТО");
        listHomePage.setPopular_seasons(popular);
        logger.info("Конвертация популярных в лист ДТО");
        listHomePage.setRecent_released_seasons(release);

        logger.info("возвращает listHomePage");
        return listHomePage;
    }

    //Convert Seasons to DTO
    private SeasonDTO.Response.HomePage convertToSeasonDTO(Season season) {
        SeasonDTO.Response.HomePage seasonDTO = new SeasonDTO.Response.HomePage();

        seasonDTO.setSeasonId(season.getId());
        seasonDTO.setSeasonName(season.getName());
        seasonDTO.setDescription(season.getDescription());
        seasonDTO.setImageUrl(season.getSeasonImageUrl());

        return seasonDTO;
    }
}
