package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.youmiteru.backend.domain.TitleState;

import java.time.LocalDate;
import java.util.List;

public class SeasonDTO {
    protected interface seasonId {
        @JsonProperty(value = "seasons_id")
        Long getSeasonId();
    }

    protected interface imageUrl {
        @JsonProperty(value = "season_image_url")
        String getImageUrl();
    }

    protected interface seasonName {
        @JsonProperty(value = "seasons_name")
        String getSeasonName();
    }

    protected interface animeFormat {
        @JsonProperty(value = "anime_format")
        String getAnimeFormat();
    }

    protected interface description {
        @JsonProperty(value = "seasons_description")
        String getDescription();
    }

    protected interface releaseDate {
        @JsonProperty(value = "release_date")
        LocalDate getReleaseDate();
    }

    protected interface titleState {
        @JsonProperty(value = "title_state")
        TitleState getTitleState();
    }

    protected interface ageRestriction {
        @JsonProperty(value = "age_restriction")
        String getAgeRestriction();
    }

    protected interface yearSeason {
        @JsonProperty(value = "year_season")
        String getYearSeason();
    }

    protected interface animeBannerUrl {
        @JsonProperty(value = "anime_banner_url")
        String getAnimeBannerUrl();
    }

    protected interface rating {
        @JsonProperty(value = "rating")
        Double getRating();
    }


    public enum Response {
        ;

        //home page
        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class HomePage implements seasonId, seasonName, description, imageUrl {
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

        //season page
        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class SeasonPage
            implements seasonId, imageUrl, seasonName, animeFormat, description, releaseDate,
            titleState, ageRestriction, yearSeason, animeBannerUrl, rating{
            private Long seasonId;
            private String imageUrl;
            private String seasonName;
            private String animeFormat;
            private String description;
            private LocalDate releaseDate;
            private TitleDTO.Response.TitleInformationForSeasonPage titleInformation;
            private TitleState titleState;
            private String ageRestriction;
            private String yearSeason;
            private String animeBannerUrl;
            private Double rating;
            List<CommentDTO.Response.Comments> commentsList;
            List<VoiceActorDTO.Response.VoiceActorForSeason> voiceActors;
            List<VideoDTO.Response.VideoDtoForSeason> videoDtoList;
        }
    }
}
