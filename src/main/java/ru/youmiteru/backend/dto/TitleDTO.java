package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class TitleDTO {
    private interface titleId {
        @JsonProperty(value = "title_id")
        Long getTitleId();
    }

    private interface titleImageUrl {
        @JsonProperty(value = "title_image_url")
        Long getTitleImageUrl();
    }

    private interface titleName {
        @JsonProperty(value = "title_name")
        Long getTitleName();
    }

    private interface titleDescription {
        @JsonProperty(value = "title_description")
        Long getTitleDescription();
    }

    public enum Response {
        ;

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class TitleInformationForSeasonPage
            implements titleId, titleName, titleImageUrl {
            Long titleId;
            String titleName;
            String titleImageUrl;
        }
    }
}
