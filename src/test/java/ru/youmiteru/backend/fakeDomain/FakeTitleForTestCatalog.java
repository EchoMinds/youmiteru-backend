package ru.youmiteru.backend.fakeDomain;

import org.junit.jupiter.api.BeforeEach;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.TitleDTO;

import java.time.LocalDate;
import java.util.List;

public class FakeTitleForTestCatalog {

    public static Title createTitle(){
        Title fakeTitle = new Title();
        fakeTitle.setId(1L);
        fakeTitle.setName("Boku no Kokoro no Yabai Yatsu Season 2");
        fakeTitle.setTitleImageUrl("url");
        fakeTitle.setDescription("Повседневная жизнь маленького");
        fakeTitle.setGenres(List.of(new Genre("Shoujo")));
        fakeTitle.setSeasonList(List.of(creareSeason()));

        return fakeTitle;
    }
    public static Season creareSeason(){
        Title fakeTitle = new Title();
        fakeTitle.setId(1L);
        fakeTitle.setName("Boku no Kokoro no Yabai Yatsu Season 2");
        fakeTitle.setTitleImageUrl("url");
        fakeTitle.setDescription("Повседневная жизнь маленького");
        fakeTitle.setGenres(List.of(new Genre("Shoujo")));

        Season fakeSeason = new Season();
        fakeSeason.setId(1L);
        fakeSeason.setSeasonImageUrl("https://example.com/season_image.jpg");
        fakeSeason.setName("Fake Season");
        fakeSeason.setDescription("This is a fake season for testing purposes.");
        fakeSeason.setReleaseDate(LocalDate.of(2023, 1, 1));
        fakeSeason.setAgeRestriction("2023");
        fakeSeason.setYearSeason("2023");
        fakeSeason.setAnimeBannerUrl("https://example.com/banner.jpg");
        fakeSeason.setTitle(fakeTitle);
        fakeSeason.setTitleState(TitleState.ANNOUNCEMENT);
        fakeSeason.setAnimeFormat(AnimeFormat.TV_SHOW);

        fakeTitle.setSeasonList(List.of(fakeSeason));
        return fakeSeason;
    }
    public static Genre createGenre(){
        Genre fakeGenre = new Genre();

        fakeGenre.setId(1L);
        fakeGenre.setName("Shoujo");
        fakeGenre.setTitles(List.of(new Title("url", "Boku no Kokoro no Yabai Yatsu Season 2", "Повседневная жизнь маленького")));

        return fakeGenre;
    }
}
