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

    //Return data for HomePage
    public Map<String, Object> getAllSeasonForHomePage(){
        //find popular seasons with rating
        List<Season> seasonsRating  = new ArrayList<>();
        List<Rating> ratings = ratingRepository.findRating();
        for(Rating rating : ratings){
            seasonsRating.add(rating.getSeason());
        }

        List<SeasonDTO.Request.HomePage> anons = covertListSeasonDTO(seasonRepository.findAnnouncement());
        List<SeasonDTO.Request.HomePage> release = covertListSeasonDTO(seasonRepository.findRelease());
        List<SeasonDTO.Request.HomePage> banner = covertListSeasonDTO(seasonRepository.findBanner());
        List<SeasonDTO.Request.HomePage> popular = covertListSeasonDTO(seasonsRating);
        Map<String, Object> relust = SeasonDTO.generateResponseBanners(banner, anons, popular, release);
        return relust;
    }


    //Convert List Seasons to DTO
    private List<SeasonDTO.Request.HomePage> covertListSeasonDTO (List<Season> seasons){
        return seasons.stream().map(this::convertToSeasonDTO)
            .collect(Collectors.toList());
    }

    //Convert Seasons to DTO
    private SeasonDTO.Request.HomePage convertToSeasonDTO(Season season) {
        SeasonDTO.Request.HomePage seasonDTO = new SeasonDTO.Request.HomePage();

        seasonDTO.setSeasonId(season.getId());
        seasonDTO.setSeasonName(season.getName());
        seasonDTO.setDescription(season.getDescription());
        seasonDTO.setImageUrl(season.getSeasonImageUrl());

        return seasonDTO;
    }
}
