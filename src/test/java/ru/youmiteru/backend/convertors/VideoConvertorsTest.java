package ru.youmiteru.backend.convertors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.Video;
import ru.youmiteru.backend.dto.VideoDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class VideoConvertorsTest {

    @InjectMocks
    private VideoConvertors videoConvertors;

    @Test
    void testConvertToVideoDtoForSeason() {
        // Arrange
        int episode = 1;
        String player = "Player 1";
        String playerUrl = "https://example.com/player.html";
        Video video = new Video(episode, player, playerUrl);

        // Act
        VideoDTO videoDTO = videoConvertors.convertToVideoDtoForSeason(video);

        // Assert
        assertEquals(episode, videoDTO.episode());
        assertEquals(player, videoDTO.player());
        assertEquals(playerUrl, videoDTO.link());
    }
}
