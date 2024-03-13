package ru.youmiteru.backend.convertors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.TitleDTO;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("TitleConvertorsTest")
@ExtendWith(MockitoExtension.class)
public class TitleConvertorsTest {
    @InjectMocks
    private TitleConvertors titleConvertorsMock;

    private Title fakeTitle;
    private Season fakeSeason;
    private Genre fakeGenre;

    private TitleDTO.Response.TitleCatalogDTO testDto1;

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
        fakeSeason.setReleaseDate(LocalDate.of(2023, 1, 1));
        fakeSeason.setAgeRestriction("2023");
        fakeSeason.setYearSeason("2023");
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

        testDto1 = new TitleDTO.Response.TitleCatalogDTO();
        testDto1.setTitleId(fakeTitle.getId());
        testDto1.setTitleName(fakeTitle.getName());
        testDto1.setTitleImageUrl(fakeTitle.getTitleImageUrl());
    }

    @DisplayName("testConvertToCatalogDTO")
    @Test
    void testConvertToCatalogDTO(){
        TitleDTO.Response.TitleCatalogDTO testDto = titleConvertorsMock.convertToCatalogDTO(fakeTitle);
        assertNotNull(testDto);
        assertEquals(testDto.getTitleId(), fakeTitle.getId());
        assertEquals(testDto.getTitleName(), fakeTitle.getName());
        assertEquals(testDto.getTitleImageUrl(), fakeTitle.getTitleImageUrl());
    }
}
