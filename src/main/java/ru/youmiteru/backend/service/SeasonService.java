package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.exceptions.SeasonNotFoundException;
import ru.youmiteru.backend.repositories.SeasonRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;

    //Return data for HomePage
    public SeasonDTO.Response.ListHomePage getAllSeasonForHomePage() {


        SeasonDTO.Response.ListHomePage listHomePage = new SeasonDTO.Response.ListHomePage();

        List<SeasonDTO.Response.HomePage> anons = seasonRepository.findAnnouncement()
            .stream().map(this::convertToSeasonDtoForHomePage).collect(Collectors.toList());
        List<SeasonDTO.Response.HomePage> release = seasonRepository.findRecent()
            .stream().map(this::convertToSeasonDtoForHomePage).collect(Collectors.toList());
        List<SeasonDTO.Response.HomePage> banner = seasonRepository.findBanner()
            .stream().map(this::convertToSeasonDtoForHomePage).collect(Collectors.toList());
        List<SeasonDTO.Response.HomePage> popular = seasonRepository.findPopular()
            .stream().map(this::convertToSeasonDtoForHomePage).collect(Collectors.toList());

        listHomePage.setBanners(banner);
        listHomePage.setAnnounced_seasons(anons);
        listHomePage.setPopular_seasons(popular);
        listHomePage.setRecent_released_seasons(release);

        return listHomePage;
    }

    //Convert Seasons to DTO FOR HOME PAGE!!!!
    private SeasonDTO.Response.HomePage convertToSeasonDtoForHomePage(Season season) {
        SeasonDTO.Response.HomePage seasonDTO = new SeasonDTO.Response.HomePage();

        seasonDTO.setSeasonId(season.getId());
        seasonDTO.setSeasonName(season.getName());
        seasonDTO.setDescription(season.getDescription());
        seasonDTO.setImageUrl(season.getSeasonImageUrl());

        return seasonDTO;
    }

    //return Data for Season page
    public SeasonDTO.Response.SeasonPage getSeasonPage(Long id) {
        Season seasonPage = seasonRepository.findById(id).orElseThrow(SeasonNotFoundException::new);
        return convertToDtoForSeasonPage(seasonPage);
    }

    public static SeasonDTO.Response.SeasonPage convertToDtoForSeasonPage(Season seasonPage) {
        SeasonDTO.Response.SeasonPage dto = new SeasonDTO.Response.SeasonPage();
        dto.setSeasonId(seasonPage.getId());
        dto.setImageUrl(seasonPage.getSeasonImageUrl());
        dto.setSeasonName(seasonPage.getName());
        dto.setAnimeFormat(String.valueOf(seasonPage.getAnimeFormat()));
        dto.setDescription(seasonPage.getDescription());
        dto.setReleaseDate(seasonPage.getReleaseDate());
        dto.setTitleId(seasonPage.getTitle().getId());
        dto.setTitleState(seasonPage.getTitleState());
        dto.setAgeRestriction(seasonPage.getAgeRestriction());
        dto.setYearSeason(seasonPage.getYearSeason());
        return dto;
    }
}
