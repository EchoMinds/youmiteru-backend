package ru.youmiteru.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.youmiteru.backend.domain.Genre;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.GenreDTO.GenreDto;
import ru.youmiteru.backend.repositories.GenreRepository;
import ru.youmiteru.backend.repositories.TitleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

class GenreServiceTest {

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private TitleRepository titleRepository;

    @InjectMocks
    private GenreService genreService;

    private Genre testGenre;
    private Title testTitle;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testGenre = new Genre();
        testGenre.setId(1L);
        testGenre.setName("Action");
        testGenre.setTitles(new ArrayList<>());

        testTitle = new Title();
        testTitle.setId(1L);
        testTitle.setName("Test Title");
        testTitle.setGenres(new ArrayList<>());
    }

    @Test
    void testAssociateGenreWithTitle() {
        when(genreRepository.findById(1L)).thenReturn(Optional.of(testGenre));
        when(titleRepository.findById(1L)).thenReturn(Optional.of(testTitle));
        when(genreRepository.save(testGenre)).thenReturn(testGenre);

        GenreDto result = genreService.associateGenreWithTitle(1L, 1L);

        assertEquals(1L, result.id());
        assertEquals("Action", result.name());
        assertEquals(1, result.titleIds().size());
        assertEquals(1L, result.titleIds().get(0));
        verify(genreRepository, times(1)).save(testGenre);
    }

    @Test
    void testAssociateGenreWithTitle_GenreNotFound() {
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        GenreDto result = genreService.associateGenreWithTitle(1L, 1L);

        assertNull(result);
        verify(genreRepository, never()).save(any(Genre.class));
    }

    @Test
    void testDissociateGenreFromTitle() {
        testGenre.getTitles().add(testTitle);
        testTitle.getGenres().add(testGenre);

        when(genreRepository.findById(1L)).thenReturn(Optional.of(testGenre));
        when(titleRepository.findById(1L)).thenReturn(Optional.of(testTitle));
        when(genreRepository.save(testGenre)).thenReturn(testGenre);

        GenreDto result = genreService.dissociateGenreFromTitle(1L, 1L);

        assertEquals(1L, result.id());
        assertEquals("Action", result.name());
        assertEquals(0, result.titleIds().size());
        verify(genreRepository, times(1)).save(testGenre);
    }

    @Test
    void testDissociateGenreFromTitle_GenreNotFound() {
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        GenreDto result = genreService.dissociateGenreFromTitle(1L, 1L);

        assertNull(result);
        verify(genreRepository, never()).save(any(Genre.class));
    }
}
