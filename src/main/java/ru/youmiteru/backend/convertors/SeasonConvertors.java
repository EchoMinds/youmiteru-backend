package ru.youmiteru.backend.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.Genre;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDto.HomePage;
import ru.youmiteru.backend.dto.SeasonDto.RelatedSeason;
import ru.youmiteru.backend.dto.SeasonDto.SeasonPage;
import ru.youmiteru.backend.dto.VideoDTO;
import ru.youmiteru.backend.service.*;

import java.util.List;

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
    public HomePage homePageResponse(Season season) {
        return new HomePage(season.getId(), season.getName(), season.getDescription(), season.getSeasonImageUrl());
    }

    //season page
    public SeasonPage seasonPageResponse (Season seasonPage, List<RelatedSeason> relatedSeasonList) {
        List<String> genres = seasonPage.getTitle().getGenres().stream().map(Genre::getName)
            .toList();

        List<VideoDTO> videoDtoList = seasonPage.getVideoList()
            .stream().map(videoConvertors::convertToVideoDtoForSeason).toList();

        return new SeasonPage(
            seasonPage.getId(),
            seasonPage.getSeasonImageUrl(),
            seasonPage.getName(),
            String.valueOf(seasonPage.getAnimeFormat()),
            seasonPage.getDescription(),
            seasonPage.getReleaseDate(),
            seasonPage.getTitleState(),
            seasonPage.getAgeRestriction(),
            seasonPage.getYearSeason(),
            seasonPage.getReducedDescription(),
            ratingService.getRating(seasonPage.getId()),
            relatedSeasonList,
            genres,
            commentService.getCommentsList(seasonPage),
            voiceActorService.getVoiceActorList(seasonPage),
            videoDtoList
        );

        /*SeasonDTO.Response.SeasonPage dto = new SeasonDTO.Response.SeasonPage();

        dto.setSeasonId(seasonPage.getId());
        dto.setImageUrl(seasonPage.getSeasonImageUrl());
        dto.setSeasonName(seasonPage.getName());
        dto.setAnimeFormat(String.valueOf(seasonPage.getAnimeFormat()));
        dto.setDescription(seasonPage.getDescription());
        dto.setReleaseDate(seasonPage.getReleaseDate());
        dto.setTitleState(seasonPage.getTitleState());
        dto.setAgeRestriction(seasonPage.getAgeRestriction());
        dto.setYearSeason(seasonPage.getYearSeason());
        dto.setReducedDescription(seasonPage.getReducedDescription());
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
        */
    }


    //season related
    public RelatedSeason convertToRelatedSeason(Season season) {
        return new RelatedSeason(season.getId(), season.getSeasonImageUrl());
    }
}
