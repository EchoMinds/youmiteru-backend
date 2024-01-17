package ru.youmiteru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.response.ResponseHandler;
import ru.youmiteru.backend.service.SeasonService;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class SeasonController {
    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }

    //Возращает сезоны в баннер
    @GetMapping("/banners")
    public ResponseEntity<Object> getSeasonsForBanners(){
        List<SeasonDTO> result = seasonService.getAllSeasonForBanners()
            .stream().map(this::convertToSeasonDTO)
            .collect(Collectors.toList());

        return ResponseHandler.generateResponseBanners(result);
    }


    //Возвращает анонсированные сезоны
    @GetMapping("/announced_seasons")
    public ResponseEntity<Object> getAnnouncementSeasons(){
        List<SeasonDTO> result = seasonService.getAllSeasonForAnnounced()
            .stream().map(this::convertToSeasonDTO)
            .collect(Collectors.toList());

        return ResponseHandler.generateResponseAnnouncement(result);
    }

    //Возвращает популярные сезоны
    @GetMapping("/popular_seasons")
    public ResponseEntity<Object> getPopularSeasons(){
        List<SeasonDTO> result = seasonService.getAllPopularSeason()
            .stream().map(this::convertToSeasonDTO)
            .collect(Collectors.toList());

        return ResponseHandler.generateResponsePopular(result);
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
