package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.convertors.VideoConvertors;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.VideoDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final VideoConvertors videoConvertors;
    public List<VideoDTO.Response.VideoDtoForSeason> getVideoListForSeasonPage(Season season) {
        return season.getVideoList()
            .stream().map(videoConvertors::convertToVideoDtoForSeason).toList();
    }

}
