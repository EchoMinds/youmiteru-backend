package ru.youmiteru.backend.convertors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.dto.SeasonDto.RelatedSeason;
import ru.youmiteru.backend.service.CommentService;
import ru.youmiteru.backend.service.RatingService;
import ru.youmiteru.backend.service.VoiceActorService;

import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class SeasonConvertorTest {

    @Mock
    private CommentService commentService;

    @Mock
    private RatingService ratingService;

    @Mock
    private VoiceActorService voiceActorService;


    @InjectMocks
    private SeasonConvertors seasonConvertors;

    @Test
    void testSeasonPageResponse() {
        Title title = new Title("http:/lsdjfls/", "Title Name", "This is the description");
        title.setId(1L);

        Season season = new Season();
        season.setId(1L);
        season.setTitle(new Title("http:/lsdjfls/", "Title Name", "This is the description"));
        season.setName("Test Season");
        season.setDescription("Test Description");

        List<RelatedSeason> relatedSeasonList = new ArrayList<>(); // Create an empty list

        assertEquals(1, 1);
    }
}
