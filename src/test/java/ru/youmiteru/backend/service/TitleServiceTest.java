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
import ru.youmiteru.backend.dto.Title.TitleCatalogDTO;
import ru.youmiteru.backend.dto.Title.TitlePageCountDto;
import ru.youmiteru.backend.fakeDomain.FakeDomainCreator;
import ru.youmiteru.backend.repositories.GenreRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.TitleRepository;
import ru.youmiteru.backend.domain.*;

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

    private Season fakeSeason1;
    private Title fakeTitle1;
    private Genre fakeGenre;
    private TitleCatalogDTO testDto1;

    private Integer offset;
    private List<String> genre;
    private List<Long> date;
    private List<String> format;
    private List<String> state;
    private List<String> ageRestriction;
    private List<String> yearSeason;

    @BeforeEach
    void init() {
        fakeTitle1 = FakeDomainCreator.createFakeTitle();
        fakeSeason1 = FakeDomainCreator.createFakeSeason();
        fakeGenre = FakeDomainCreator.createFakeGenre();

        //спецификации для фильтра
        offset = 0;
        genre = List.of("Shoujo");
        date = List.of(2023L);
        format = List.of("TV_SHOW");
        state = List.of("ANNOUNCEMENT");
        ageRestriction = List.of("18");
        yearSeason = List.of("WINTER");

        testDto1 = new TitleCatalogDTO(1L, fakeTitle1.getName(), fakeTitle1.getTitleImageUrl());
    }

    @Test
    @DisplayName("filterForCatalog")
    void getCatalog() {
        when(titleServiceMock.filterForCatalog(genre, date, format, state, ageRestriction, yearSeason)).thenReturn(List.of(fakeTitle1));

        TitlePageCountDto testDtoTitle = titleServiceMock.getCatalog(offset, genre, date, format, state, ageRestriction, yearSeason);

        assertNotNull(testDtoTitle);
        for (TitleCatalogDTO test : testDtoTitle.titlesForCatalog()) {
            assertEquals(testDto1, test);
            assertEquals(testDto1.titleImageUrl(), test.titleImageUrl());
            assertEquals(testDto1.titleName(), test.titleName());
        }

        List<Title> filteredTitleTest = titleServiceMock.filterForCatalog(genre, date, format, state, ageRestriction, yearSeason);
        assertNotNull(filteredTitleTest);
        for (Title test : filteredTitleTest) {
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
    void testCount() {
        when(titleServiceMock.filterForCatalog(genre, date, format, state, ageRestriction, yearSeason)).thenReturn(List.of(fakeTitle1));
        TitlePageCountDto testDtoTitle = titleServiceMock.getCatalog(offset, genre, date, format, state, ageRestriction, yearSeason);

        assertNotNull(testDtoTitle);
        assertEquals(testDtoTitle.totalPage(), (int) Math.ceil((double) testDtoTitle.titlesForCatalog().size() / 20));
        assertEquals(testDtoTitle.currentPage(), offset);
    }

}
