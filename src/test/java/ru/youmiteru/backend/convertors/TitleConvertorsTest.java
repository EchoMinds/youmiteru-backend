package ru.youmiteru.backend.convertors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.TitleDTO;
import ru.youmiteru.backend.fakeDomain.FakeTitleForTestCatalog;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("TitleConvertorsTest")
@ExtendWith(MockitoExtension.class)
public class TitleConvertorsTest {
    @InjectMocks
    private TitleConvertors titleConvertorsMock;

    private Title fakeTitle;
    private Season fakeSeason;
    private Genre fakeGenre;

    private TitleDTO.Response.TitleCatalogDTO testDto1;

    @BeforeEach
    void init(){
        fakeTitle = FakeTitleForTestCatalog.createTitle();
        fakeSeason = FakeTitleForTestCatalog.creareSeason();
        fakeGenre = FakeTitleForTestCatalog.createGenre();

        testDto1 = new TitleDTO.Response.TitleCatalogDTO();
        testDto1.setTitleId(fakeTitle.getId());
        testDto1.setTitleName(fakeTitle.getName());
        testDto1.setTitleImageUrl(fakeTitle.getTitleImageUrl());
    }

    @DisplayName("testConvertToCatalogDTO")
    @Test
    void testConvertToCatalogDTO(){
        TitleDTO.Response.TitleCatalogDTO testDto = titleConvertorsMock.convertToCatalogDTO(fakeTitle);
        assertNotNull(testDto);
        assertEquals(testDto.getTitleId(), fakeTitle.getId());
        assertEquals(testDto.getTitleName(), fakeTitle.getName());
        assertEquals(testDto.getTitleImageUrl(), fakeTitle.getTitleImageUrl());
    }
}
