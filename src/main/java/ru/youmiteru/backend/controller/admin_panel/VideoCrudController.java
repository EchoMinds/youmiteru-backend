package ru.youmiteru.backend.controller.admin_panel;

import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.dto.VideoDTO.CreateVideoDto;
import ru.youmiteru.backend.dto.VideoDTO.VideoDetailDto;
import ru.youmiteru.backend.service.VideoService;

import java.util.List;

@RestController
@RequestMapping("/titles")
public class VideoCrudController {
    private final VideoService videoService;

    public VideoCrudController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public List<VideoDetailDto> getAllVideos() {
        return videoService.getAllVideos();
    }

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
