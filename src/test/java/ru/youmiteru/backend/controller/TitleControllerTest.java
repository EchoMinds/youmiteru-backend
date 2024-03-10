package ru.youmiteru.backend.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.youmiteru.backend.dto.TitleDTO.Response.TitleCatalogDTO;
import ru.youmiteru.backend.service.TitleService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@DisplayName("TitleControllerTest")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(TitleController.class)
public class TitleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    TitleService titleService;

    private Integer offset = 0;
    private List<String> genre = List.of("Shoujo");
    private List<Long> date = List.of(2023L);
    private List<String> format = List.of("TV_SHOW");
    private List<String> state = List.of("ANNOUNCEMENT");
    private List<String> ageRestriction = List.of("18");
    private List<String> yearSeason = List.of("WINTER");

    @Test
    void getCatalog() throws Exception {
        Mockito.when(this.titleService.getCatalog(offset, genre, date, format, state, ageRestriction, yearSeason))
            .thenReturn(getResultForTest());

        mockMvc.perform(get("/api/title"))
            .andExpect(status().isNotFound());
    }

    private List<TitleCatalogDTO> getResultForTest(){
        TitleCatalogDTO catalogDTO1 = new TitleCatalogDTO();
        TitleCatalogDTO catalogDTO2 = new TitleCatalogDTO();

        catalogDTO1.setTitleName("Boku no Kokoro no Yabai Yatsu Season 2");
        catalogDTO1.setTitleImageUrl("https://example.com/season_image.jpg");

        catalogDTO2.setTitleName("Boku no Kokoro");
        catalogDTO2.setTitleImageUrl("https://example.com/banner.jpg");

        return List.of(catalogDTO1, catalogDTO2);
    }

}
