package ru.youmiteru.backend.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class SeasonTest {

    Season getSeason(){
        Season season1 = new Season();

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

        return season1;
    }

    @Test
    void domainSeasonTest(){
        Season result = getSeason();

        Assertions.assertEquals(result.getName(), "Boku no Kokoro no Yabai Yatsu Season 2");
        Assertions.assertEquals(result.getAnimeFormat(), AnimeFormat.TV_SHOW);
        Assertions.assertEquals(result.getAgeRestriction(), "18");
    }

}
