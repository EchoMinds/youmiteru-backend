package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record VoiceActorDTO(
    Long voiceActorId,
    Long userId,
    String profileImageUrl
){}
//public class VoiceActorDTO {
//    private interface voiceActorId {
//        @JsonProperty(value = "voice_actor_id")
//        Long getVoiceActorId();
//    }
//
//    public enum Response {
//        ;
//
//        @NoArgsConstructor
//        @AllArgsConstructor
//        @Data
//        public static class VoiceActorForSeason implements
//            VoiceActorDTO.voiceActorId, UserDTO.userId, UserDTO.profileImageUrl {
//            Long voiceActorId;
//            Long userId;
//            String profileImageUrl;
//        }
//    }
//}
