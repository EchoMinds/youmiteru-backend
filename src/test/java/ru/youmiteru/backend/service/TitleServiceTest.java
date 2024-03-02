package ru.youmiteru.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.TitleRepository;
import ru.youmiteru.backend.util.CatalogFilter;
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

import java.util.List;

@DisplayName("TitleServiceTest")
public class TitleServiceTest {

    @Nested
    @DisplayName("testCatalog")
    @ExtendWith(MockitoExtension.class)
    class testCatalog {

        @Mock
        private TitleRepository titleRepositoryMock;

        @InjectMocks
        private static TitleService titleServiceMock;

        @Mock
        private static CatalogFilter catalogFilter;

        private Season fakeSeason;
        private Title fakeTitle;
        private Genre fakeGenre;

        private List<String> genre;
        private List<Long> date;
        private List<String> format;
        private List<String> state;
        private List<String> ageRestriction;
        private List<String> yearSeason;
        @BeforeEach
        void init(){
            fakeTitle = new Title();
            fakeTitle.setId(1L);
            fakeTitle.setTitleImageUrl("url");
            fakeTitle.setName("Boku no Kokoro no Yabai Yatsu Season 2");
            fakeTitle.setDescription("Повседневная жизнь маленького");

            fakeSeason = new Season();
            fakeSeason.setId(1L);
            fakeSeason.setSeasonImageUrl("https://example.com/season_image.jpg");
            fakeSeason.setName("Fake Season");
            fakeSeason.setDescription("This is a fake season for testing purposes.");
            fakeSeason.setReleaseDate(LocalDate.of(2024, 1, 1));
            fakeSeason.setAgeRestriction("18+");
            fakeSeason.setYearSeason("WINTER");
            fakeSeason.setAnimeBannerUrl("https://example.com/banner.jpg");
            fakeSeason.setTitle(fakeTitle);
            fakeSeason.setTitleState(TitleState.ANNOUNCEMENT);
            fakeSeason.setAnimeFormat(AnimeFormat.TV_SHOW);

            fakeGenre = new Genre();
            fakeGenre.setId(1L);
            fakeGenre.setName("Shoujo");
            fakeGenre.setTitles(List.of(fakeTitle));

            fakeTitle.setSeasonList(List.of(fakeSeason));
            fakeTitle.setGenres(List.of(fakeGenre));

            //спецификации для фильтра
            genre = List.of("Shoujo");
            date = List.of(2024L);
            format = List.of("TV_SHOW");
            state = List.of("ANNOUNCEMENT");
            ageRestriction = List.of("18+");
            yearSeason = List.of("WINTER");
        }
        @Test
        @DisplayName("filterConvertorCatalog")
        void filterConvertorCatalog(){
            assertEquals(fakeTitle.getGenres().get(0).getName(), genre.get(0));
            assertEquals(fakeTitle.getSeasonList().get(0).getReleaseDate().getYear(), date.get(0));
            assertEquals(fakeTitle.getSeasonList().get(0).getAnimeFormat().name(), format.get(0));
            assertEquals(fakeTitle.getSeasonList().get(0).getTitleState().name(), state.get(0));
            assertEquals(fakeTitle.getSeasonList().get(0).getAgeRestriction(), ageRestriction.get(0));
            assertEquals(fakeTitle.getSeasonList().get(0).getYearSeason(), yearSeason.get(0));
        }
    }
}
