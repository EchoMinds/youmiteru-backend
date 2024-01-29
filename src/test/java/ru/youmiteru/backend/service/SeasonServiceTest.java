package ru.youmiteru.backend.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.AnimeFormat;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.domain.TitleState;
import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.repositories.SeasonRepository;
import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class SeasonServiceTest {

    @Mock
    private SeasonRepository seasonRepository;

    @Mock
    private SeasonDTO.Response.HomePage homePage;

    @InjectMocks
    private SeasonService seasonService;

    @Test
    public void shouldReturnSeasons(){
        List<Season> seasons = getSeason();

        Mockito.when(seasonRepository.findAnnouncement()).thenReturn(seasons);


        SeasonDTO.Response.ListHomePage result = seasonService.getAllSeasonForHomePage();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(seasons.size(), 2);
        Assertions.assertEquals(result.getAnnounced_seasons().size(), 2);
        Assertions.assertEquals(result.getRecent_released_seasons().size(), 0);
        Assertions.assertEquals(result.getAnnounced_seasons().get(0).getSeasonName(), "Boku no Kokoro no Yabai Yatsu Season 2");
        Assertions.assertEquals(result.getAnnounced_seasons().get(1).getSeasonName(), "Karakai Jouzu no Takagi-san 2");
    }

    private List<Season> getSeason(){
        Season season1 = new Season();
        Season season2 = new Season();

        season1.setId(1L);
        season1.setSeasonImageUrl("https://desu.shikimori.one/uploads/poster/animes/55690/49d51b1c556632d0214a49831575b5e0.jpeg");
        season1.setName("Boku no Kokoro no Yabai Yatsu Season 2");
        season1.setDescription("Повседневная жизнь маленького социопата");
        season1.setReleaseDate(LocalDate.of(2024, 1, 14));
        season1.setTitle(new Title("https://desu.shikimori.one/uploads/poster/animes/55690/49d51b1c556632d0214a49831575b5e0.jpeg",
            "Boku no Kokoro no Yabai Yatsu Season 2", "Повседневная жизнь маленького социопата"));
        season1.setTitleState(TitleState.ANNOUNCEMENT);
        season1.setAgeRestriction("18");
        season1.setYearSeason("2024");
        season1.setAnimeFormat(AnimeFormat.TV_SHOW);


        season2.setId(1L);
        season2.setSeasonImageUrl("https://desu.shikimori.one/uploads/poster/animes/38993/534699774d8b121395967a91c573a66e.jpeg");
        season2.setName("Karakai Jouzu no Takagi-san 2");
        season2.setDescription("В классе Нисикаты учится девочка по имени Такаги.");
        season2.setReleaseDate(LocalDate.of(2019, 1, 7));
        season2.setTitle(new Title("https://desu.shikimori.one/uploads/poster/animes/38993/534699774d8b121395967a91c573a66e.jpeg",
            "Karakai Jouzu no Takagi-san", "В классе Нисикаты учится девочка по имени Такаги."));
        season2.setTitleState(TitleState.ANNOUNCEMENT);
        season2.setAgeRestriction("16");
        season2.setYearSeason("2019");
        season2.setAnimeFormat(AnimeFormat.TV_SHOW);

        return List.of(season1, season2);
    }

}
