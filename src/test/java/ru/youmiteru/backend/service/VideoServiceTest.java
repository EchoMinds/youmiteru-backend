package ru.youmiteru.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Video;
import ru.youmiteru.backend.dto.VideoDTO.CreateVideoDto;
import ru.youmiteru.backend.dto.VideoDTO.VideoDetailDto;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.VideoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VideoServiceTest {

    @Mock
    private VideoRepository videoRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private VideoService videoService;

    private Video testVideo;
    private Season testSeason;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testSeason = new Season();
        testSeason.setId(1L);

        testVideo = new Video();
        testVideo.setId(1L);
        testVideo.setEpisode(1);
        testVideo.setPlayer("Player");
        testVideo.setPlayerUrl("https://example.com/video");
        testVideo.setSeason(testSeason);
    }

    @Test
    void testGetAllVideos() {
        List<Video> videos = new ArrayList<>();
        videos.add(testVideo);

        when(videoRepository.findAll()).thenReturn(videos);

        List<VideoDetailDto> result = videoService.getAllVideos();

        assertEquals(1, result.size());
        VideoDetailDto videoDto = result.get(0);
        assertEquals(1L, videoDto.id());
        assertEquals(1, videoDto.episode());
        assertEquals("Player", videoDto.player());
        assertEquals("https://example.com/video", videoDto.playerUrl());
        assertEquals(1L, videoDto.seasonId());
    }

    @Test
    void testGetVideoById() {
        when(videoRepository.findById(1L)).thenReturn(Optional.of(testVideo));

        VideoDetailDto result = videoService.getVideoById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(1, result.episode());
        assertEquals("Player", result.player());
        assertEquals("https://example.com/video", result.playerUrl());
        assertEquals(1L, result.seasonId());
    }

    @Test
    void testGetVideoById_NotFound() {
        when(videoRepository.findById(1L)).thenReturn(Optional.empty());

        VideoDetailDto result = videoService.getVideoById(1L);

        assertNull(result);
    }

    @Test
    void testCreateVideo() {
        CreateVideoDto createVideoDto = new CreateVideoDto(1, "Player", "https://example.com/video", 1L);
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(testSeason));
        when(videoRepository.save(any(Video.class))).thenReturn(testVideo);

        VideoDetailDto result = videoService.createVideo(createVideoDto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(1, result.episode());
        assertEquals("Player", result.player());
        assertEquals("https://example.com/video", result.playerUrl());
        assertEquals(1L, result.seasonId());
    }

    @Test
    void testUpdateVideo() {
        VideoDetailDto updateDto = new VideoDetailDto(1L, 2, "NewPlayer", "https://example.com/new-video", 1L);
        when(videoRepository.findById(1L)).thenReturn(Optional.of(testVideo));
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(testSeason));
        when(videoRepository.save(any(Video.class))).thenReturn(testVideo);

        VideoDetailDto result = videoService.updateVideo(1L, updateDto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals(2, result.episode());
        assertEquals("NewPlayer", result.player());
        assertEquals("https://example.com/new-video", result.playerUrl());
        assertEquals(1L, result.seasonId());
    }

    @Test
    void testUpdateVideo_NotFound() {
        VideoDetailDto updateDto = new VideoDetailDto(1L, 2, "NewPlayer", "https://example.com/new-video", 1L);
        when(videoRepository.findById(1L)).thenReturn(Optional.empty());

        VideoDetailDto result = videoService.updateVideo(1L, updateDto);

        assertNull(result);
    }

    @Test
    void testDeleteVideo() {
        videoService.deleteVideo(1L);

        verify(videoRepository, times(1)).deleteById(1L);
    }
}
