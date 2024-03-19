package ru.youmiteru.backend.convertors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.Title.TitleCatalogDTO;
import ru.youmiteru.backend.fakeDomain.FakeTitleForTestCatalog;
import ru.youmiteru.backend.service.CommentService;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TitleConvertorsTest")
@ExtendWith(MockitoExtension.class)
public class TitleConvertorsTest {

    @InjectMocks
    private TitleConvertors titleConvertorsMock;

    private Title fakeTitle;
    private Season fakeSeason;
    private Genre fakeGenre;
    private TitleCatalogDTO testDto1;

    @BeforeEach
    void init() {
        fakeTitle = FakeTitleForTestCatalog.createTitle();
        fakeSeason = FakeTitleForTestCatalog.creareSeason();
        fakeGenre = FakeTitleForTestCatalog.createGenre();
        testDto1 = new TitleCatalogDTO(fakeTitle.getId(), fakeTitle.getName(), fakeTitle.getTitleImageUrl());
    }

    @DisplayName("testConvertToCatalogDTO")
    @Test
    void testConvertToCatalogDTO() {
        TitleCatalogDTO testDto = titleConvertorsMock.convertToCatalogDTO(fakeTitle);
        assertNotNull(testDto);
        assertEquals(testDto.titleId(), fakeTitle.getId());
        assertEquals(testDto.titleName(), fakeTitle.getName());
        assertEquals(testDto.titleImageUrl(), fakeTitle.getTitleImageUrl());
    }
}
