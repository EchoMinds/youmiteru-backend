package ru.youmiteru.backend.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.repositories.SeasonRepository;

import java.util.List;

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

    @Test
    @DisplayName("getSeasonPage")
    void getSeasonPage() {

    }

    @Test
    @DisplayName("getRelatedSeasons")
    void getRelatedSeasons() {
    }


}
