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
    }


    //season related
    public RelatedSeason convertToRelatedSeason(Season season) {
        return new RelatedSeason(season.getId(), season.getSeasonImageUrl());
    }
}
