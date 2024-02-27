package ru.youmiteru.backend.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.dto.SeasonDTO.Response.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("SeasonServiceTest")
@ExtendWith(MockitoExtension.class)
class SeasonServiceTest {
    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private static SeasonService seasonService;

    @Mock
    private static SeasonConvertors seasonConvertors;

    @Nested
    @DisplayName("testHomePage")
    @ExtendWith(MockitoExtension.class)
    class testHomePage {

        @Mock
        private SeasonRepository seasonRepositoryMock;

        @InjectMocks
        private static SeasonService seasonServiceMock;

        @Mock
        private static SeasonConvertors seasonConvertors;

        private static List<Season> getFakeAnons;
        private static List<Season> getFakeRelease;
        private static List<Season> getFakePopularSeasons;
        private static List<Season> getFakeBanners;

        @BeforeAll
        static void init() {
            getFakeAnons = List.of(
                new Season(),
                new Season(),
                new Season()
            );
            getFakeRelease = List.of(
                new Season(),
                new Season(),
                new Season()
            );
            getFakePopularSeasons = List.of(
                new Season(),
                new Season(),
                new Season()
            );
            getFakeBanners = List.of(
                new Season(),
                new Season(),
                new Season()
            );

        }

        @Test
        @DisplayName("getAllSeasonForHomePage")
        void getAllSeasonForHomePage() {
            when(seasonRepositoryMock.findBanner()).thenReturn(getFakeBanners);
            when(seasonRepositoryMock.findAnnouncement()).thenReturn(getFakeAnons);
            when(seasonRepositoryMock.findRecent()).thenReturn(getFakeRelease);
            when(seasonRepositoryMock.findPopular()).thenReturn(getFakePopularSeasons);

            SeasonDTO.Response.ListHomePage response = seasonServiceMock.getAllSeasonForHomePage();


            assertEquals(getFakeBanners.size(), response.getBanners().size());
            assertEquals(getFakeAnons.size(), response.getAnnounced_seasons().size());
            assertEquals(getFakeRelease.size(), response.getRecent_released_seasons().size());
            assertEquals(getFakePopularSeasons.size(), response.getPopular_seasons().size());

        }

    }

    @Nested
    @DisplayName("testSeasonPage")
    @ExtendWith(MockitoExtension.class)
    class testSeasonPage {
        private Season fakeSeason;
        private Title fakeTitle;
        private TitleState fakeTitleState;
        private AnimeFormat fakeAnimeFormat;
        private List<VoiceActor> fakeVoiceActors;
        private List<Video> fakeVideoList;
        private List<Comment> fakeCommentList;
        private List<Rating> fakeRatingList;
        private SeasonPage fakeDtoSeason;

        @BeforeEach
        void initClasses() {
            fakeTitle = new Title();
            fakeTitle.setId(1L);
            fakeTitle.setTitleImageUrl("test");
            fakeTitle.setSeasonList(new ArrayList<>());

            fakeTitleState = TitleState.ANNOUNCEMENT;
            fakeAnimeFormat = AnimeFormat.OVA;
            fakeVoiceActors = List.of(new VoiceActor(), new VoiceActor());
            fakeVideoList = List.of(new Video(), new Video());
            fakeCommentList = List.of(new Comment(), new Comment());
            Rating fakeRating = new Rating();
            fakeRating.setValue(10);
            fakeRatingList = List.of(fakeRating, fakeRating);

            //create fakeSeason
            fakeSeason = new Season();
            fakeSeason.setId(1L);
            fakeSeason.setSeasonImageUrl("https://example.com/season_image.jpg");
            fakeSeason.setName("Fake Season");
            fakeSeason.setDescription("This is a fake season for testing purposes.");
            fakeSeason.setReleaseDate(LocalDate.now());
            fakeSeason.setAgeRestriction("PG-13");
            fakeSeason.setYearSeason("2024");
            fakeSeason.setAnimeBannerUrl("https://example.com/banner.jpg");

            fakeSeason.setTitle(fakeTitle);
            fakeSeason.setTitleState(fakeTitleState);
            fakeSeason.setAnimeFormat(fakeAnimeFormat);
            fakeSeason.setVoiceActors(fakeVoiceActors);
            fakeSeason.setVideoList(fakeVideoList);
            fakeSeason.setSeasonCommentList(fakeCommentList);
            fakeSeason.setSeasonRatingList(fakeRatingList);
            //
            fakeDtoSeason = new SeasonPage();

            fakeDtoSeason.setSeasonId(fakeSeason.getId());
            fakeDtoSeason.setImageUrl(fakeSeason.getSeasonImageUrl());
            fakeDtoSeason.setSeasonName(fakeSeason.getName());
            fakeDtoSeason.setAnimeFormat(String.valueOf(fakeSeason.getAnimeFormat()));
            fakeDtoSeason.setDescription(fakeSeason.getDescription());
            fakeDtoSeason.setReleaseDate(fakeSeason.getReleaseDate());
            fakeDtoSeason.setTitleState(fakeSeason.getTitleState());
            fakeDtoSeason.setAgeRestriction(fakeSeason.getAgeRestriction());
            fakeDtoSeason.setYearSeason(fakeSeason.getYearSeason());
            fakeDtoSeason.setCommentsList(new ArrayList<>());
            fakeDtoSeason.setRating(10.0);
            fakeDtoSeason.setVoiceActors(new ArrayList<>());
            fakeDtoSeason.setVideoDtoList(new ArrayList<>());
            fakeDtoSeason.setGenres(new ArrayList<>());
            fakeDtoSeason.setRelatedSeasons(new ArrayList<>());
        }

        @Test
        @DisplayName("getSeasonPage_shouldGetSeasonPage")
        void getSeasonPage_shouldGetSeasonPage() {

            when(seasonRepository.findById(1L)).thenReturn(Optional.of(fakeSeason));
            when(seasonConvertors.seasonPageResponse(fakeSeason, new ArrayList<>())).thenReturn(fakeDtoSeason);
            SeasonPage seasonPage = seasonService.getSeasonPage(1L);

            assertEquals(fakeDtoSeason, seasonPage);
        }
    }

    @Test
    @DisplayName("getRelatedSeasons")
    void getRelatedSeasons() {
        Title fakeTitle = new Title();
        Season fakeSeason = new Season();

        fakeTitle.setSeasonList(List.of(fakeSeason, fakeSeason));

        when(seasonConvertors.convertToRelatedSeason(fakeSeason)).thenReturn(new RelatedSeason());

        List<RelatedSeason> relatedSeasonsList = seasonService.getRelatedSeasons(fakeTitle);

        assertEquals(List.of(new RelatedSeason(), new RelatedSeason()), relatedSeasonsList);

    }


}
