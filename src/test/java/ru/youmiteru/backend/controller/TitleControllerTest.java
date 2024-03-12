package ru.youmiteru.backend.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.youmiteru.backend.dto.TitleDTO.Response.TitlePageCountDTO;
import ru.youmiteru.backend.service.TitleService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Arrays;

@DisplayName("TitleControllerTest")
@ExtendWith(MockitoExtension.class)
public class TitleControllerTest {
    @InjectMocks
    private TitleController catalogController;
    @Mock
    private TitleService titleService;

    @BeforeEach
    void setUp() {
        titleService = mock(TitleService.class);
        catalogController = new TitleController(titleService);
    }

    @Test
    void testGetCatalog() {
        List<String> genres = Arrays.asList("Action", "Adventure");
        List<Long> dates = Arrays.asList(2022L, 2023L);
        List<String> format = Arrays.asList("DVD", "Blu-ray");
        List<String> state = Arrays.asList("Released", "In Production");
        List<String> ageRestriction = Arrays.asList("PG", "R");
        List<String> yearSeason = Arrays.asList("2022", "2023");
        TitlePageCountDTO catalogDTO = new TitlePageCountDTO();
        when(titleService.getCatalog(anyInt(), eq(genres), eq(dates), eq(format), eq(state), eq(ageRestriction), eq(yearSeason)))
            .thenReturn(catalogDTO);

        ResponseEntity<TitlePageCountDTO> responseEntity = catalogController.getCatalog(0, genres, dates, format, state, ageRestriction, yearSeason);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(catalogDTO, responseEntity.getBody());
    }

    @Test
    void testGetCatalog_NotFound() {
        when(titleService.getCatalog(anyInt(), any(), any(), any(), any(), any(), any())).thenReturn(null);

        ResponseEntity<TitlePageCountDTO> responseEntity = catalogController.getCatalog(0, null, null, null, null, null, null);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
