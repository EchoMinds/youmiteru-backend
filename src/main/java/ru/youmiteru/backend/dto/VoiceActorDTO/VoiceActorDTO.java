package ru.youmiteru.backend.dto.VoiceActorDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record VoiceActorDTO(
    Long voiceActorId,
    Long userId,
    String profileImageUrl
){}
