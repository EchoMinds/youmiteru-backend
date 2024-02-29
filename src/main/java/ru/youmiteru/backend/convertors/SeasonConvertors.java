package ru.youmiteru.backend.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.Genre;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.dto.VideoDTO;
import ru.youmiteru.backend.service.*;

import java.util.List;
import java.util.stream.Collectors;

/*
 *
 * Convertors for Season
 *
 */
@Component
@RequiredArgsConstructor
public class SeasonConvertors {
    private final CommentService commentService;
    private final RatingService ratingService;
    private final VoiceActorService voiceActorService;
    private final VideoConvertors videoConvertors;

    //home page
    public SeasonDTO.Response.HomePage homePageResponse(Season season) {
        SeasonDTO.Response.HomePage seasonDTO = new SeasonDTO.Response.HomePage();

        seasonDTO.setSeasonId(season.getId());
        seasonDTO.setSeasonName(season.getName());
        seasonDTO.setDescription(season.getDescription());
        seasonDTO.setImageUrl(season.getSeasonImageUrl());

        return seasonDTO;
    }

    //season page
    public SeasonDTO.Response.SeasonPage seasonPageResponse
    (Season seasonPage, List<SeasonDTO.Response.RelatedSeason> relatedSeasonList) {
        SeasonDTO.Response.SeasonPage dto = new SeasonDTO.Response.SeasonPage();

        dto.setSeasonId(seasonPage.getId());
        dto.setImageUrl(seasonPage.getSeasonImageUrl());
        dto.setSeasonName(seasonPage.getName());
        dto.setAnimeFormat(String.valueOf(seasonPage.getAnimeFormat()));
        dto.setDescription(seasonPage.getDescription());
        dto.setReleaseDate(seasonPage.getReleaseDate());
        dto.setTitleState(seasonPage.getTitleState());
        dto.setAgeRestriction(seasonPage.getAgeRestriction());
        dto.setYearSeason(seasonPage.getYearSeason());
        dto.setCommentsList(commentService.getCommentsList(seasonPage));
        dto.setRating(ratingService.getRating(seasonPage.getId()));
        dto.setVoiceActors(voiceActorService.getVoiceActorList(seasonPage));
        List<VideoDTO.Response.VideoDtoForSeason> videoDtoList = seasonPage.getVideoList()
            .stream().map(videoConvertors::convertToVideoDtoForSeason).toList();
        dto.setVideoDtoList(videoDtoList);
        List<String> genres = seasonPage.getTitle().getGenres().stream().map(Genre::getName)
            .toList();
        dto.setGenres(genres);
        dto.setRelatedSeasons(relatedSeasonList);

        return dto;
    }


    //season related
    public SeasonDTO.Response.RelatedSeason convertToRelatedSeason(Season season) {
        return new SeasonDTO.Response.RelatedSeason(season.getId(), season.getSeasonImageUrl());
    }
}
