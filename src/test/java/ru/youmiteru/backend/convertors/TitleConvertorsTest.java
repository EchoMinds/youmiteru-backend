package ru.youmiteru.backend.convertors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.Title.TitleCatalogDTO;
import ru.youmiteru.backend.dto.Title.TitlePageDTO;
import ru.youmiteru.backend.fakeDomain.FakeDomainCreator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TitleConvertorsTest")
@ExtendWith(MockitoExtension.class)
public class TitleConvertorsTest {

    @InjectMocks
    private TitleConvertors titleConvertorsMock;
    @Mock
    private SeasonConvertors seasonConvertors;

    private Title fakeTitle;
    private Season fakeSeason;
    private Genre fakeGenre;
    private TitleCatalogDTO testDto1;

    @BeforeEach
    void init() {
        fakeTitle = FakeDomainCreator.createFakeTitle();
        fakeSeason = FakeDomainCreator.createFakeSeason();
        fakeGenre = FakeDomainCreator.createFakeGenre();
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

    @DisplayName("testConvertToPageDTO")
    @Test
    void testConvertToPageDTO() {
        TitlePageDTO testDto = titleConvertorsMock.convertToPageDTO(fakeTitle);
        assertNotNull(testDto);
        assertEquals(testDto.titleId(), fakeTitle.getId());
        assertEquals(testDto.titleName(), fakeTitle.getName());
        assertEquals(testDto.titleImage(), fakeTitle.getTitleImageUrl());
        assertEquals(testDto.genreName(), fakeTitle.getGenres().stream().map(f -> f.getName()).collect(Collectors.toList()));
    }
}
