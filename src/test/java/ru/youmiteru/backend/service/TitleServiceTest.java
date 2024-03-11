package ru.youmiteru.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.convertors.TitleConvertors;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.TitleDTO;
import ru.youmiteru.backend.dto.TitleDTO.Response.TitleCatalogDTO;
import ru.youmiteru.backend.repositories.GenreRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.TitleRepository;
import ru.youmiteru.backend.domain.*;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.List;

@DisplayName("TitleServiceTest")
@ExtendWith(MockitoExtension.class)
public class TitleServiceTest {

    @Mock
    private TitleRepository titleRepositoryMock;
    @Mock
    private GenreRepository genreRepositoryMock;
    @Mock
    private SeasonRepository seasonRepositoryMock;
    @Mock
    private TitleCatalogDTO titleDTO;
    @Mock
    private TitleConvertors titleConvertorsMock;
    @InjectMocks
    private static TitleService titleServiceMock;
    @Mock
    private static CatalogFilterService catalogFilter;

    private Season fakeSeason1, fakeSeason2;
    private Title fakeTitle1, fakeTitle2;
    private Genre fakeGenre;
    private TitleCatalogDTO testDto1, testDto2;

    private Integer offset;
    private List<String> genre;
    private List<Long> date;
    private List<String> format;
    private List<String> state;
    private List<String> ageRestriction;
    private List<String> yearSeason;
    @BeforeEach
    void init(){
        fakeTitle1 = new Title();
        fakeTitle1.setId(1L);
        fakeTitle1.setTitleImageUrl("url");
        fakeTitle1.setName("Boku no Kokoro no Yabai Yatsu Season 2");
        fakeTitle1.setDescription("Повседневная жизнь маленького");

        fakeTitle2 = new Title();
        fakeTitle2.setId(2L);
        fakeTitle2.setName("Boku no Kokoro");
        fakeTitle2.setTitleImageUrl("url2");
        fakeTitle2.setDescription("Повседневная жизнь маленького");

        fakeSeason1 = new Season();
        fakeSeason1.setId(1L);
        fakeSeason1.setSeasonImageUrl("https://example.com/season_image.jpg");
        fakeSeason1.setName("Fake Season");
        fakeSeason1.setDescription("This is a fake season for testing purposes.");
        fakeSeason1.setReleaseDate(LocalDate.of(2023, 1, 1));
        fakeSeason1.setAgeRestriction("2023");
        fakeSeason1.setYearSeason("2023");
        fakeSeason1.setAnimeBannerUrl("https://example.com/banner.jpg");
        fakeSeason1.setTitle(fakeTitle1);
        fakeSeason1.setTitleState(TitleState.ANNOUNCEMENT);
        fakeSeason1.setAnimeFormat(AnimeFormat.TV_SHOW);

        fakeSeason2 = new Season();
        fakeSeason2.setId(2L);
        fakeSeason2.setSeasonImageUrl("urlSeason1");
        fakeSeason2.setName("Fake Season2");
        fakeSeason2.setReleaseDate(LocalDate.of(2023, 1, 1));
        fakeSeason2.setDescription("This is a fake season");
        fakeSeason2.setAgeRestriction("2023");
        fakeSeason2.setYearSeason("2023");
        fakeSeason2.setAnimeBannerUrl("urlBanner2");
        fakeSeason2.setTitle(fakeTitle2);
        fakeSeason2.setTitleState(TitleState.ANNOUNCEMENT);
        fakeSeason2.setAnimeFormat(AnimeFormat.TV_SHOW);

        fakeGenre = new Genre();
        fakeGenre.setId(1L);
        fakeGenre.setName("Shoujo");
        fakeGenre.setTitles(List.of(fakeTitle1, fakeTitle2));

        fakeTitle1.setSeasonList(List.of(fakeSeason1));
        fakeTitle1.setGenres(List.of(fakeGenre));
        fakeTitle2.setSeasonList(List.of(fakeSeason2));
        fakeTitle2.setGenres(List.of(fakeGenre));

        //спецификации для фильтра
        offset = 0;
        genre = List.of("Shoujo");
        date = List.of(2023L);
        format = List.of("TV_SHOW");
        state = List.of("ANNOUNCEMENT");
        ageRestriction = List.of("18");
        yearSeason = List.of("WINTER");

        testDto1 = new TitleCatalogDTO();
        testDto1.setTitleName(fakeTitle1.getName());
        testDto1.setTitleImageUrl(fakeTitle1.getTitleImageUrl());

        testDto2 = new TitleCatalogDTO();
        testDto2.setTitleName(fakeTitle2.getName());
        testDto2.setTitleImageUrl(fakeTitle2.getTitleImageUrl());
    }
    @Test
    @DisplayName("filterForCatalog")
    void getCatalog(){
        when(titleServiceMock.filterForCatalog(genre, date, format, state, ageRestriction, yearSeason)).thenReturn(List.of(fakeTitle1,fakeTitle2));

        TitleDTO.Response.TitlePageCountDTO testDtoTitle = titleServiceMock.getCatalog(offset ,genre, date, format, state, ageRestriction, yearSeason);

        assertNotNull(testDtoTitle);
        for (TitleCatalogDTO test : testDtoTitle.getTitlesForCatalog()){
            assertEquals(testDto1, test);
            assertEquals(testDto1.getTitleImageUrl(), test.getTitleImageUrl());
            assertEquals(testDto1.getTitleName(), test.getTitleName());
        }

        List<Title> filteredTitleTest = titleServiceMock.filterForCatalog(genre, date, format, state, ageRestriction, yearSeason);
        assertNotNull(filteredTitleTest);
        for (Title test : filteredTitleTest){
            assertEquals(test.getName(), "Boku no Kokoro no Yabai Yatsu Season 2");
            assertEquals(test.getGenres().get(0).getName(), genre.get(0));
            assertEquals(test.getSeasonList().get(0).getAnimeFormat().name(), format.get(0));
            assertEquals(test.getSeasonList().get(0).getTitleState().name(), state.get(0));
            assertEquals(test.getSeasonList().get(0).getAgeRestriction(), ageRestriction.get(0));
            assertEquals(test.getSeasonList().get(0).getYearSeason(), yearSeason.get(0));
        }
    }

    @Test
    @DisplayName("testPageCount")
    void testCount(){
        when(titleServiceMock.filterForCatalog(genre, date, format, state, ageRestriction, yearSeason)).thenReturn(List.of(fakeTitle1,fakeTitle2));
        TitleDTO.Response.TitlePageCountDTO testDtoTitle = titleServiceMock.getCatalog(offset ,genre, date, format, state, ageRestriction, yearSeason);

        assertNotNull(testDtoTitle);
        assertEquals(testDtoTitle.getTotalPage(), (int) Math.ceil((double) testDtoTitle.getTitlesForCatalog().size()/20));
        assertEquals(testDtoTitle.getCurrentPage(), offset);
    }

}
