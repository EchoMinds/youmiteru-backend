package ru.youmiteru.backend.convertors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.domain.VoiceActor;
import ru.youmiteru.backend.dto.VoiceActorDTO.VoiceActorDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoiceActorConvertorsTest {

    @InjectMocks
    private VoiceActorConvertors voiceActorConvertors;

    @Mock
    private VoiceActor voiceActor;

    @Mock
    private User user;

    @Test
    void testConvertToVoiceActorForSeason() {
        // Arrange
        Long voiceActorId = 1L;
        Long userId = 2L;
        String profileImageUrl = "https://example.com/profile.jpg";

        when(voiceActor.getId()).thenReturn(voiceActorId);
        when(voiceActor.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(userId);
        when(user.getProfileImageUrl()).thenReturn(profileImageUrl);

        // Act
        VoiceActorDTO voiceActorDTO = voiceActorConvertors.convertToVoiceActorForSeason(voiceActor);

        // Assert
        assertEquals(voiceActorId, voiceActorDTO.voiceActorId());
        assertEquals(userId, voiceActorDTO.userId());
        assertEquals(profileImageUrl, voiceActorDTO.profileImageUrl());
    }
}
