package ru.youmiteru.backend.controller.admin_panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.dto.VideoDTO.CreateVideoDto;
import ru.youmiteru.backend.dto.VideoDTO.VideoDetailDto;
import ru.youmiteru.backend.service.VideoService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/video")
public class VideoAdminController {
    private final VideoService videoService;

    @Autowired
    public VideoAdminController(VideoService videoService) {
        this.videoService = videoService;
    }

    // Переделать по getAllVideosBySeasonId
    // не валидно, смысла нет
    @GetMapping
    public List<VideoDetailDto> getAllVideos() {
        return videoService.getAllVideos();
    }

    //Смысла нет
    @GetMapping("/{id}")
    public VideoDetailDto getVideoById(@PathVariable Long id) {
        return videoService.getVideoById(id);
    }

    @PostMapping
    public VideoDetailDto createVideo(@RequestBody CreateVideoDto videoDto) {
        return videoService.createVideo(videoDto);
    }

    @PutMapping("/{id}")
    public VideoDetailDto updateVideo(@PathVariable Long id, @RequestBody VideoDetailDto videoDto) {
        return videoService.updateVideo(id, videoDto);
    }

    @DeleteMapping("/{id}")
    public void deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);
    }
}
