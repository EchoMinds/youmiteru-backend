package ru.youmiteru.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EnumType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.youmiteru.backend.controller.admin_panel.SeasonCrudController;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.TitleState;
import ru.youmiteru.backend.dto.SeasonDto.ListHomePage;
import ru.youmiteru.backend.dto.SeasonDto.SeasonPage;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.service.SeasonService;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SeasonCrudController.class)
public class SeasonCrudControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SeasonService seasonService;

    @MockBean
    private SeasonRepository seasonRepository;

    private Season testSeason;
    private ListHomePage listHomePage;
    private SeasonPage seasonPage;

    @BeforeEach
    void setUp() {
        testSeason = new Season();
        testSeason.setId(1L);
        testSeason.setName("Test Season");
        testSeason.setReleaseDate(LocalDate.of(2023, 4, 1));

        listHomePage = new ListHomePage(Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
        seasonPage = new SeasonPage(1L, "imageUrl", "Test Season", "TV", "Description", LocalDate.of(2023, 4, 1), TitleState.FINISHED, "18+", "Spring", "Reduced Description", 4.5, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
    }

    @Test
    void testGetAllSeasons() throws Exception {
        when(seasonService.getAllSeasonForHomePage()).thenReturn(listHomePage);

        mockMvc.perform(get("/api/seasons/"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetSeasonPage() throws Exception {
        when(seasonService.getSeasonPage(eq(1L))).thenReturn(seasonPage);

        mockMvc.perform(get("/api/seasons/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCreateSeason() throws Exception {
        when(seasonRepository.save(any(Season.class))).thenReturn(testSeason);

        mockMvc.perform(post("/api/seasons/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testSeason)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Test Season"))
            .andExpect(jsonPath("$.releaseDate").value("2023-04-01"));
    }

    @Test
    void testUpdateSeason() throws Exception {
        Season updatedSeason = new Season();
        updatedSeason.setName("Updated Season");

        when(seasonService.updateSeasonService(eq(1L), any(Season.class))).thenReturn(null);

        mockMvc.perform(put("/api/seasons/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedSeason)))
            .andExpect(status().isOk());
    }

    @Test
    void testDeleteSeason() throws Exception {
        mockMvc.perform(delete("/api/seasons/1"))
            .andExpect(status().isNoContent());

        verify(seasonRepository).deleteById(1L);
    }
}
