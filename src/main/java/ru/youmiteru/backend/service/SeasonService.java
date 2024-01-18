package ru.youmiteru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Rating;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.repositories.RatingRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository, RatingRepository ratingRepository) {
        this.seasonRepository = seasonRepository;
        this.ratingRepository = ratingRepository;
    }

    //Возвращает данные для Home-page
    public Map<String, Object> getAllSeasonForHomePage(){
        List<Season> seasonsRating  = new ArrayList<>();
        List<Rating> ratings = ratingRepository.findRating();

        for(Rating rating : ratings){
            seasonsRating.add(rating.getSeason());
        }

        List<SeasonDTO> anons = covertListSeasonDTO(seasonRepository.findAnnouncement());
        List<SeasonDTO> release = covertListSeasonDTO(seasonRepository.findRelease());
        List<SeasonDTO> banner = covertListSeasonDTO(seasonRepository.findBanner());
        List<SeasonDTO> popular = covertListSeasonDTO(seasonsRating);
        Map<String, Object> relust = SeasonDTO.generateResponseBanners(banner, anons, popular, release);
        return relust;
    }


    //Конвертирует лист Сезонов в ДТО
    private List<SeasonDTO> covertListSeasonDTO (List<Season> seasons){
        return seasons.stream().map(this::convertToSeasonDTO)
            .collect(Collectors.toList());
    }

    //Конвертирует Сезон в ДТО
    private SeasonDTO convertToSeasonDTO(Season season) {
        SeasonDTO seasonDTO = new SeasonDTO();

        seasonDTO.setId(season.getId());
        seasonDTO.setName(season.getName());
        seasonDTO.setDescription(season.getDescription());
        seasonDTO.setSeasonImageUrl(season.getSeasonImageUrl());

        return seasonDTO;
    }
}
