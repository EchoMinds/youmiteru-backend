package ru.youmiteru.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.youmiteru.backend.controller.admin_panel.GenreController;
import ru.youmiteru.backend.dto.GenreDTO.GenreDto;
import ru.youmiteru.backend.dto.GenreDTO.GenreTitleDto;
import ru.youmiteru.backend.service.GenreService;
import ru.youmiteru.backend.service.TitleService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GenreController.class)
public class GenreControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GenreService genreService;

    @MockBean
    private TitleService titleService;

    private GenreDto genreDto;
    private GenreTitleDto genreTitleDto;

    @BeforeEach
    void setUp() {
        List<Long> titleIds = List.of(1L, 2L);
        genreDto = new GenreDto(1L, "Action", titleIds);
        genreTitleDto = new GenreTitleDto(1L, 1L);

    }

    @Test
    void testAssociateGenreWithTitle_NotFound() throws Exception {
        when(genreService.associateGenreWithTitle(eq(1L), eq(1L))).thenReturn(null);

        mockMvc.perform(post("/genres/1/titles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genreTitleDto)))
            .andExpect(status().isNotFound());
    }

    @Test
    void testAssociateGenreWithTitle_BadRequest() throws Exception {
        GenreTitleDto invalidDto = new GenreTitleDto(2L, 1L);

        mockMvc.perform(post("/genres/1/titles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
            .andExpect(status().isBadRequest());
    }


    @Test
    void testDissociateGenreFromTitle_NotFound() throws Exception {
        when(genreService.dissociateGenreFromTitle(eq(1L), eq(1L))).thenReturn(null);

        mockMvc.perform(delete("/genres/1/titles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(genreTitleDto)))
            .andExpect(status().isNotFound());
    }

    @Test
    void testDissociateGenreFromTitle_BadRequest() throws Exception {
        GenreTitleDto invalidDto = new GenreTitleDto(2L, 1L);

        mockMvc.perform(delete("/genres/1/titles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDto)))
            .andExpect(status().isBadRequest());
    }
}
