package ru.youmiteru.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.convertors.VideoConvertors;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Video;
import ru.youmiteru.backend.dto.VideoDTO;
import ru.youmiteru.backend.dto.VideoDTO.Response.*;
import static org.mockito.Mockito.verify;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DisplayName("VideoServiceTest")
@ExtendWith(MockitoExtension.class)
class VideoServiceTest {

    @InjectMocks
    private VideoService videoService;

    @Mock
    private VideoConvertors videoConvertors;


    private Season season;
    private Video video;

    @BeforeEach
    void init() {
        video = new Video();
        video.setEpisode(1);
        video.setPlayerUrl("test");
        video.setPlayer("vk");

        season = new Season();
        season.setId(1L);
        season.setVideoList(List.of(
            video,
            video,
            video));
    }

    @Test
    @DisplayName("getVideoListForSeasonPage")
    void getVideoListForSeasonPage() {
        VideoDtoForSeason videoDtoForSeason = new VideoDtoForSeason(1, "vk", "test");

        when(videoConvertors.convertToVideoDtoForSeason(video)).thenReturn(videoDtoForSeason);

        List<VideoDTO.Response.VideoDtoForSeason> videoDtoForSeasons =
            videoService.getVideoListForSeasonPage(season);

        assertEquals(List.of(videoDtoForSeason, videoDtoForSeason, videoDtoForSeason), videoDtoForSeasons);
    }
}
