package ru.youmiteru.backend.convertors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.CommentDTO;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.dto.VideoDTO;
import ru.youmiteru.backend.dto.VoiceActorDTO;
import ru.youmiteru.backend.service.CommentService;
import ru.youmiteru.backend.service.RatingService;
import ru.youmiteru.backend.service.VoiceActorService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static ru.youmiteru.backend.domain.AnimeFormat.TV_SHOW;
import static ru.youmiteru.backend.domain.TitleState.FINISHED;

class SeasonConvertorsTest {

    private SeasonConvertors seasonConvertors;

    @Mock
    private CommentService commentService;

    @Mock
    private RatingService ratingService;

    @Mock
    private VoiceActorService voiceActorService;

    @Mock
    private VideoConvertors videoConvertors;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        seasonConvertors = new SeasonConvertors(commentService, ratingService, voiceActorService, videoConvertors);
    }

    @Test
    void testHomePageResponse() {
        Season season = new Season();
        season.setId(1L);
        season.setName("Test Season");
        season.setDescription("Test Description");
        season.setSeasonImageUrl("http://test.com/image.jpg");

        SeasonDTO.Response.HomePage homePageResponse = seasonConvertors.homePageResponse(season);

        assertEquals(1L, homePageResponse.getSeasonId());
        assertEquals("Test Season", homePageResponse.getSeasonName());
        assertEquals("Test Description", homePageResponse.getDescription());
        assertEquals("http://test.com/image.jpg", homePageResponse.getImageUrl());
    }

    @Test
    void testSeasonPageResponse() {
        Season season = new Season();
        season.setId(1L);
        season.setSeasonImageUrl("http://test.com/image.jpg");
        season.setName("Test Season");
        season.setAnimeFormat(TV_SHOW);
        season.setDescription("Test Description");
        season.setReleaseDate(LocalDate.of(2023, 1, 1));
        season.setTitleState(FINISHED);
        season.setAgeRestriction("PG_13");
        season.setYearSeason("2023 Spring");
        season.setReducedDescription("Reduced Description");

        List<CommentDTO.Response.Comments> comments = new ArrayList<>();
        when(commentService.getCommentsList(season)).thenReturn(comments);

        double rating = 4.5;
        when(ratingService.getRating(season.getId())).thenReturn(rating);

        List<VoiceActorDTO.Response.VoiceActorForSeason> voiceActors = new ArrayList<>();
        when(voiceActorService.getVoiceActorList(season)).thenReturn(voiceActors);

        Video testVideo = new Video(1, "TestPlayer", "https://example.com/player");
        VideoDTO.Response.VideoDtoForSeason testVideoDtoForSeason = new VideoDTO.Response.VideoDtoForSeason();
        testVideoDtoForSeason.setEpisode(1);
        testVideoDtoForSeason.setPlayer("TestPlayer");
        testVideoDtoForSeason.setLink("https://example.com/player");

        List<Video> videos = new ArrayList<>();
        videos.add(testVideo);
        season.setVideoList(videos);

        List<VideoDTO.Response.VideoDtoForSeason> videoDtos = new ArrayList<>();
        videoDtos.add(testVideoDtoForSeason);
        when(videoConvertors.convertToVideoDtoForSeason(testVideo)).thenReturn(testVideoDtoForSeason);

        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("Action"));
        genres.add(new Genre("Adventure"));
        Title title = new Title();
        title.setGenres(genres);
        season.setTitle(title);

        List<SeasonDTO.Response.RelatedSeason> relatedSeasons = new ArrayList<>();
        relatedSeasons.add(new SeasonDTO.Response.RelatedSeason(2L, "http://test.com/related.jpg"));

        SeasonDTO.Response.SeasonPage seasonPageResponse = seasonConvertors.seasonPageResponse(season, relatedSeasons);

        assertEquals(1L, seasonPageResponse.getSeasonId());
        assertEquals("http://test.com/image.jpg", seasonPageResponse.getImageUrl());
        assertEquals("Test Season", seasonPageResponse.getSeasonName());
        assertEquals("TV_SHOW", seasonPageResponse.getAnimeFormat());
        assertEquals("Test Description", seasonPageResponse.getDescription());
        assertEquals(LocalDate.of(2023, 1, 1), seasonPageResponse.getReleaseDate());
        assertEquals(FINISHED, seasonPageResponse.getTitleState());
        assertEquals("PG_13", seasonPageResponse.getAgeRestriction());
        assertEquals("2023 Spring", seasonPageResponse.getYearSeason());
        assertEquals("Reduced Description", seasonPageResponse.getReducedDescription());
        assertEquals(comments, seasonPageResponse.getCommentsList());
        assertEquals(rating, seasonPageResponse.getRating());
        assertEquals(voiceActors, seasonPageResponse.getVoiceActors());
        assertEquals(videoDtos, seasonPageResponse.getVideoDtoList());
        assertEquals(List.of("Action", "Adventure"), seasonPageResponse.getGenres());
        assertEquals(relatedSeasons, seasonPageResponse.getRelatedSeasons());
    }

    @Test
    void testConvertToRelatedSeason() {
        Season season = new Season();
        season.setId(1L);
        season.setSeasonImageUrl("http://test.com/image.jpg");

        SeasonDTO.Response.RelatedSeason relatedSeason = seasonConvertors.convertToRelatedSeason(season);

        assertEquals(1L, relatedSeason.getSeasonId());
        assertEquals("http://test.com/image.jpg", relatedSeason.getImageUrl());
    }
}
