package ru.youmiteru.backend.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.Video;
import ru.youmiteru.backend.dto.VideoDTO;

@Component
@RequiredArgsConstructor
public class VideoConvertors {
    //convert video to videoDto
    public VideoDTO convertToVideoDtoForSeason(Video video) {
//        VideoDTO videoDtoForSeason = new VideoDTO.Response.VideoDtoForSeason();
//
//        videoDtoForSeason.setEpisode(video.getEpisode());
//        videoDtoForSeason.setLink(video.getPlayerUrl());
//        videoDtoForSeason.setPlayer(video.getPlayer());

        return new VideoDTO(
            video.getEpisode(),
            video.getPlayer(),
            video.getPlayerUrl()
        );
    }
}
