package ru.youmiteru.backend.service;

import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Video;
import ru.youmiteru.backend.dto.VideoDTO.CreateVideoDto;
import ru.youmiteru.backend.dto.VideoDTO.VideoDetailDto;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.VideoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final SeasonRepository seasonRepository;

    public VideoService(VideoRepository videoRepository, SeasonRepository seasonRepository) {
        this.videoRepository = videoRepository;
        this.seasonRepository = seasonRepository;
    }

    public List<VideoDetailDto> getAllVideos() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public VideoDetailDto getVideoById(Long id) {
        Optional<Video> video = videoRepository.findById(id);
        return video.map(this::convertToDto).orElse(null);
    }

    public VideoDetailDto createVideo(CreateVideoDto videoDto) {
        Video video = convertToEntity(videoDto);
        Video savedVideo = videoRepository.save(video);
        return convertToDto(savedVideo);
    }

    public VideoDetailDto updateVideo(Long id, VideoDetailDto videoDto) {
        Optional<Video> existingVideo = videoRepository.findById(id);
        if (existingVideo.isPresent()) {
            Video updatedVideo = existingVideo.get();
            updatedVideo.setEpisode(videoDto.episode());
            updatedVideo.setPlayer(videoDto.player());
            updatedVideo.setPlayerUrl(videoDto.playerUrl());
            Optional<Season> season = seasonRepository.findById(videoDto.seasonId());
            season.ifPresent(updatedVideo::setSeason);
            Video savedVideo = videoRepository.save(updatedVideo);
            return convertToDto(savedVideo);
        }
        return null;
    }

    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }

    private VideoDetailDto convertToDto(Video video) {
        return new VideoDetailDto(
            video.getId(),
            video.getEpisode(),
            video.getPlayer(),
            video.getPlayerUrl(),
            video.getSeason().getId()
        );
    }

    private Video convertToEntity(CreateVideoDto createVideoDto) {
        Video video = new Video();
        video.setEpisode(createVideoDto.episode());
        video.setPlayer(createVideoDto.player());
        video.setPlayerUrl(createVideoDto.playerUrl());
        Optional<Season> season = seasonRepository.findById(createVideoDto.seasonId());
        season.ifPresent(video::setSeason);
        return video;
    }

}
