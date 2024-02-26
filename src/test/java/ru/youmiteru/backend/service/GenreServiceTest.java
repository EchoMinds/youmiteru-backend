package ru.youmiteru.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.Genre;
import ru.youmiteru.backend.domain.Title;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("GenreServiceTest")
@ExtendWith(MockitoExtension.class)
class GenreServiceTest {
    @InjectMocks
    private  GenreService genreService;

    @Mock
    private  Title testedTitle1;
    @Mock
    private  Genre fakeGenre1;

    @Test
    @DisplayName("shouldGetGenre")
    void shouldGetGenre() {
        when(testedTitle1.getGenres()).thenReturn(List.of(fakeGenre1, fakeGenre1));
        when(fakeGenre1.getName()).thenReturn("TestedName1");

        assertEquals(
            List.of("TestedName1", "TestedName1"),
            genreService.getGenre(testedTitle1)
        );
    }
}
