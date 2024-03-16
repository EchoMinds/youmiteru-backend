package ru.youmiteru.backend.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.CommentDTO;
import ru.youmiteru.backend.dto.SeasonDto.HomePage;
import ru.youmiteru.backend.dto.SeasonDto.ListHomePage;
import ru.youmiteru.backend.dto.SeasonDto.RelatedSeason;
import ru.youmiteru.backend.dto.SeasonDto.SeasonPage;
import ru.youmiteru.backend.dto.VideoDTO;
import ru.youmiteru.backend.dto.VoiceActorDTO;
import ru.youmiteru.backend.repositories.SeasonRepository;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SeasonServiceTest {

    @Mock
    private SeasonRepository seasonRepository;

    @Mock
    private SeasonConvertors seasonConvertors;

    @InjectMocks
    private SeasonService seasonService;

    private Season season;
    private HomePage homePage;
    private RelatedSeason relatedSeason;

    @BeforeEach
    void setUp() {
        Title title = new Title("https://example.com/title-image.jpg", "Test Title", "Test Description");
        title.setId(1L);
        title.setGenres(Collections.emptyList());
        title.setSeasonList(Collections.emptyList());
        season = new Season(1L, title, "Test Season", "Test Description", true, false, false, false);
        homePage = new HomePage(1L, "Test HomePage", "Test URL", "https://example.com/title-image.jpg");
        relatedSeason = new RelatedSeason(1L, "Test Related Season");
    }

    @Test
    void getAllSeasonForHomePage() {
        when(seasonRepository.findAnnouncement()).thenReturn(Collections.singletonList(season));
        when(seasonRepository.findRecent()).thenReturn(Collections.singletonList(season));
        when(seasonRepository.findBanner()).thenReturn(Collections.singletonList(season));
        when(seasonRepository.findPopular()).thenReturn(Collections.singletonList(season));
        when(seasonConvertors.homePageResponse(season)).thenReturn(homePage);

        ListHomePage result = seasonService.getAllSeasonForHomePage();

        assertEquals(1, result.banners().size());
        assertEquals(1, result.announcedSeasons().size());
        assertEquals(1, result.popularSeasons().size());
        assertEquals(1, result.recentReleasedSeasons().size());
    }


    @Test
    void getSeasonPage() {
        Season seasonEntity = new Season(1L, new Title("https://example.com/image.jpg", "Test Title", "Test Description"), "Test Season", "Test Description", true, false, false, false);
        RelatedSeason relatedSeason = new RelatedSeason(2L, "Related Season");
        List<RelatedSeason> relatedSeasons = Collections.singletonList(relatedSeason);
        List<String> genres = Arrays.asList("Action", "Adventure");
        List<CommentDTO> comments = Collections.emptyList();
        List<VoiceActorDTO> voiceActors = Collections.emptyList();
        List<VideoDTO> videos = Collections.emptyList();

        when(seasonRepository.findById(1L)).thenReturn(Optional.of(seasonEntity));
        when(seasonConvertors.convertToRelatedSeason(seasonEntity.getTitle().getSeasonList().get(0))).thenReturn(relatedSeason);
        when(seasonConvertors.seasonPageResponse(seasonEntity, relatedSeasons)).thenReturn(new SeasonPage(
            seasonEntity.getId(),
            seasonEntity.getTitle().getTitleImageUrl(),
            seasonEntity.getName(),
            "TV",
            seasonEntity.getReducedDescription(),
            LocalDate.now(),
            TitleState.ANNOUNCEMENT,
            "R+",
            "Spring 2023",
            "Reduced Description",
            8.5,
            relatedSeasons,
            genres,
            comments,
            voiceActors,
            videos
        ));

        SeasonPage result = seasonService.getSeasonPage(1L);

        assertEquals(seasonEntity.getId(), result.seasonId());
        assertEquals(seasonEntity.getTitle().getTitleImageUrl(), result.imageUrl());
        assertEquals(seasonEntity.getName(), result.seasonName());
        assertEquals(1, result.relatedSeasons().size());
        assertEquals(2, result.genres().size());
        assertEquals(0, result.commentsList().size());
        assertEquals(0, result.voiceActorDTO().size());
        assertEquals(0, result.videoDtoList().size());
    }

}
