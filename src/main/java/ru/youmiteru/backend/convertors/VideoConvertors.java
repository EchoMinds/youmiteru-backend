package ru.youmiteru.backend.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.Video;
import ru.youmiteru.backend.dto.VideoDTO.VideoDTO;

@Component
@RequiredArgsConstructor
public class VideoConvertors {
    //convert video to videoDto
    public VideoDTO convertToVideoDtoForSeason(Video video) {

        return new VideoDTO(
            video.getEpisode(),
            video.getPlayer(),
            video.getPlayerUrl()
        );
    }
}
