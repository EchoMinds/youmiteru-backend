package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Video;
import ru.youmiteru.backend.dto.VideoDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    //get video list
    public List<VideoDTO.Response.VideoDtoForSeason> getVideoListForSeasonPage(Season season) {
        return season.getVideoList()
            .stream().map(this::convertToVideoDtoForSeason).toList();
    }

    //convert video to videoDto
    public VideoDTO.Response.VideoDtoForSeason convertToVideoDtoForSeason(Video video) {
        VideoDTO.Response.VideoDtoForSeason videoDtoForSeason = new VideoDTO.Response.VideoDtoForSeason();

        videoDtoForSeason.setEpisode(video.getEpisode());
        videoDtoForSeason.setLink(video.getPlayerUrl());
        videoDtoForSeason.setPlayer(video.getPlayer());

        return videoDtoForSeason;
    }


}
