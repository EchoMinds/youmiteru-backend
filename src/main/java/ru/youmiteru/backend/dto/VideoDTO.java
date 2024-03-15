package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public record VideoDTO(
    int episode,
    String player,
    String link
) {}


//public class VideoDTO {
//    protected interface episode {
//        @JsonProperty(value = "episode")
//        int getEpisode();
//    }
//
//    protected interface player {
//        @JsonProperty(value = "player")
//        String getPlayer();
//    }
//
//    protected interface link {
//        @JsonProperty(value = "link")
//        String getLink();
//    }
//
//
//    public enum Response {
//        ;
//
//        @NoArgsConstructor
//        @AllArgsConstructor
//        @Data
//        public static class VideoDtoForSeason
//            implements episode, player, link {
//            int episode;
//            String player;
//            String link;
//
//        }
//    }
//}
