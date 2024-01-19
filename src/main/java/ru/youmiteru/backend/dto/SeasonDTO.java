package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class SeasonDTO {


    private interface seasonId {
        @JsonProperty(value = "seasons_id")
        Long getSeasonId();
    }

    private interface seasonName {
        @JsonProperty(value = "seasons_name")
        String getSeasonName();
    }

    private interface description {
        @JsonProperty(value = "seasons_description")
        String getDescription();
    }

    private interface imageUrl {
        @JsonProperty(value = "season_image_url")
        String getImageUrl();
    }

    public enum Response {;
        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class HomePage implements seasonId, seasonName, description, imageUrl{
            Long seasonId;
            String seasonName;
            String description;
            String imageUrl;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class ListHomePage {
            List<Response.HomePage> banners;
            List<Response.HomePage> announced_seasons;
            List<Response.HomePage> popular_seasons;
            List<Response.HomePage> recent_released_seasons;
        }
    }
}
