package ru.youmiteru.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.TitleDTO;
import ru.youmiteru.backend.fakeDomain.FakeTitleForTestCatalog;
import ru.youmiteru.backend.repositories.GenreRepository;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.TitleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("CatalogFilterServiceTest")
@ExtendWith(MockitoExtension.class)
public class CatalogFilterServiceTest {

    @InjectMocks
    private CatalogFilterService catalogFilterServiceMock;

    @Mock
    private TitleRepository titleRepositoryMock;
    @Mock
    private GenreRepository genreRepositoryMock;
    @Mock
    private SeasonRepository seasonRepositoryMock;

    private Season fakeSeason1;
    private Title fakeTitle1;
    private List<String> nameGenres = new ArrayList<>();
    private List<Long> dates = new ArrayList<>();
    private List<String> formats = new ArrayList<>();
    private List<String> states = new ArrayList<>();
    private List<String> ageRestrictions = new ArrayList<>();
    private List<String> yearSeasons = new ArrayList<>();
    @BeforeEach
    void init(){
        nameGenres.add("Shoujo");
        dates.add(2023L);
        formats.add("TV_SHOW");
        states.add("ANNOUNCEMENT");
        ageRestrictions.add("18");
        yearSeasons.add("WINTER");

        fakeTitle1 = FakeTitleForTestCatalog.createTitle();
        fakeSeason1 = FakeTitleForTestCatalog.creareSeason();
    }

    @Test
    @DisplayName("getGenresTest")
    void getGenres (){
        when(catalogFilterServiceMock.filterTitleGenre(nameGenres)).thenReturn(List.of(fakeTitle1));
        List<Title> titlesResultGenres = catalogFilterServiceMock.filterTitleGenre(nameGenres);

        System.out.println(titlesResultGenres);
        assertNotNull(titlesResultGenres);
        assertEquals(titlesResultGenres.size(), 1);
        assertEquals(titlesResultGenres.get(0).getName(), fakeTitle1.getName());
        assertEquals(titlesResultGenres.get(0).getTitleImageUrl(), fakeTitle1.getTitleImageUrl());
        assertEquals(titlesResultGenres.get(0).getId(), fakeTitle1.getId());
    }

    @Test
    @DisplayName("getDatesTest")
    void getDates(){
        when(catalogFilterServiceMock.filterTitleDate(dates, null)).thenReturn(List.of(fakeTitle1));
        when(titleRepositoryMock.findAllForFilter()).thenReturn(List.of(fakeTitle1));
        when(seasonRepositoryMock.findByTitle(any())).thenReturn(List.of(fakeSeason1));

        List<Title> titlesResultDates = catalogFilterServiceMock.filterTitleDate(dates, null);


        assertNotNull(titlesResultDates);
        assertEquals(titlesResultDates.get(0).getName(), fakeTitle1.getName());
        assertEquals(titlesResultDates.get(0).getTitleImageUrl(), fakeTitle1.getTitleImageUrl());
        assertEquals(titlesResultDates.get(0).getId(), fakeTitle1.getId());
    }

    @Test
    @DisplayName("getTitleFormatTest")
    void getTitleFormat(){
        when(catalogFilterServiceMock.filterTitleFormat(formats, null)).thenReturn(List.of(fakeTitle1));
        when(titleRepositoryMock.findAllForFilter()).thenReturn(List.of(fakeTitle1));
        when(seasonRepositoryMock.findByTitle(any())).thenReturn(List.of(fakeSeason1));

        List<Title> titlesResultFormat = catalogFilterServiceMock.filterTitleFormat(formats, null);


        assertNotNull(titlesResultFormat);
        assertEquals(titlesResultFormat.get(0).getName(), fakeTitle1.getName());
        assertEquals(titlesResultFormat.get(0).getTitleImageUrl(), fakeTitle1.getTitleImageUrl());
        assertEquals(titlesResultFormat.get(0).getId(), fakeTitle1.getId());
    }

    @Test
    @DisplayName("getTitleStateTest")
    void getTitleState(){
        when(catalogFilterServiceMock.filterTitleState(states, null)).thenReturn(List.of(fakeTitle1));
        when(titleRepositoryMock.findAllForFilter()).thenReturn(List.of(fakeTitle1));
        when(seasonRepositoryMock.findByTitle(any())).thenReturn(List.of(fakeSeason1));

        List<Title> titlesResultState = catalogFilterServiceMock.filterTitleState(states, null);


        assertNotNull(titlesResultState);
        assertEquals(titlesResultState.get(0).getName(), fakeTitle1.getName());
        assertEquals(titlesResultState.get(0).getTitleImageUrl(), fakeTitle1.getTitleImageUrl());
        assertEquals(titlesResultState.get(0).getId(), fakeTitle1.getId());
    }

    @Test
    @DisplayName("getTitleAgeRestrictionTest")
    void getTitleAgeRestriction(){
        when(catalogFilterServiceMock.filterTitleAgeRestriction(ageRestrictions, null)).thenReturn(List.of(fakeTitle1));
        when(titleRepositoryMock.findAllForFilter()).thenReturn(List.of(fakeTitle1));
        when(seasonRepositoryMock.findByTitle(any())).thenReturn(List.of(fakeSeason1));

        List<Title> titlesResultAgeRestriction = catalogFilterServiceMock.filterTitleAgeRestriction(ageRestrictions, null);


        assertNotNull(titlesResultAgeRestriction);
        assertEquals(titlesResultAgeRestriction.size(), 0);
    }

    @Test
    @DisplayName("getTitleStateTest")
    void getTitleYearSeason(){
        when(catalogFilterServiceMock.filterTitleYearSeason(yearSeasons, null)).thenReturn(List.of(fakeTitle1));
        when(titleRepositoryMock.findAllForFilter()).thenReturn(List.of(fakeTitle1));
        when(seasonRepositoryMock.findByTitle(any())).thenReturn(List.of(fakeSeason1));

        List<Title> titlesResultYearSeason = catalogFilterServiceMock.filterTitleYearSeason(yearSeasons, null);


        assertNotNull(titlesResultYearSeason);
        assertEquals(titlesResultYearSeason.size(), 0);
    }
}
