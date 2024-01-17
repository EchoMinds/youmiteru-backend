package ru.youmiteru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.response.ResponseHandler;
import ru.youmiteru.backend.service.SeasonService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/youmitery")
public class SeasonController {
    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    @GetMapping("/home-page")
    public ResponseEntity<Object> getSeasonsForHome(){

        //Возращает сезоны в баннер
        List<SeasonDTO> banner = seasonService.getAllSeasonForBanners()
            .stream().map(this::convertToSeasonDTO)
            .collect(Collectors.toList());

        //Возвращает анонсированные сезоны
        List<SeasonDTO> anons = seasonService.getAllSeasonForAnnounced()
            .stream().map(this::convertToSeasonDTO)
            .collect(Collectors.toList());

        //Возвращает популярные сезоны
        List<SeasonDTO> popular = seasonService.getAllPopularSeason()
            .stream().map(this::convertToSeasonDTO)
            .collect(Collectors.toList());

        //Возвращает выпущенные сезоны
        List<SeasonDTO> released = seasonService.getAllReleasedSeasons()
            .stream().map(this::convertToSeasonDTO)
            .collect(Collectors.toList());

        return ResponseHandler.generateResponseBanners(banner, anons, popular, released);
    }


    //Конвертация Сезона в СезонДТО
    private SeasonDTO convertToSeasonDTO(Season season) {
        SeasonDTO seasonDTO = new SeasonDTO();

        seasonDTO.setId(season.getId());
        seasonDTO.setName(season.getName());
        seasonDTO.setDescription(season.getDescription());
        seasonDTO.setSeasonImageUrl(season.getSeasonImageUrl());

        return seasonDTO;
    }
}
