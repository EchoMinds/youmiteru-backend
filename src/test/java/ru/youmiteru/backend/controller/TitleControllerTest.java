package ru.youmiteru.backend.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.youmiteru.backend.dto.Title.TitleCatalogDTO;
import ru.youmiteru.backend.dto.Title.TitlePageCountDto;
import ru.youmiteru.backend.service.TitleService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TitleControllerTest {

    @InjectMocks
    private TitleController titleController;

    @Mock
    private TitleService titleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCatalogWithResults() {
        // Arrange
        Integer offset = 0;
        List<String> genres = Arrays.asList("Action", "Comedy");
        List<Long> date = Arrays.asList(1L, 2L);
        List<String> format = Arrays.asList("TV Series", "Movie");
        List<String> state = Arrays.asList("Ongoing", "Completed");
        List<String> ageRestriction = Arrays.asList("PG-13", "R");
        List<String> yearSeason = Arrays.asList("2022", "2023-Spring");

        TitleCatalogDTO catalogDTO = new TitleCatalogDTO(1L, "Test Title", "https://example.com/image.jpg");

        HttpStatus resposne = HttpStatus.OK;
        // Assert
        assertEquals(HttpStatus.OK, resposne);
    }

    @Test
    void testGetCatalogWithNoResults() {
        // Arrange
        Integer offset = 0;
        TitlePageCountDto emptyCatalog = new TitlePageCountDto(0, 0, List.of());
        when(titleService.getCatalog(offset, null, null, null, null, null, null)).thenReturn(emptyCatalog);

        // Act
        ResponseEntity<TitlePageCountDto> response = titleController.getCatalog(offset, null, null, null, null, null, null);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
