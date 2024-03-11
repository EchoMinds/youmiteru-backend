package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class TitleDTO {
    protected interface titleId {
        @JsonProperty(value = "title_id")
        Long getTitleId();
    }

    protected interface titleImageUrl {
        @JsonProperty(value = "title_image_url")
        String getTitleImageUrl();
    }

    protected interface titleName {
        @JsonProperty(value = "title_name")
        String getTitleName();
    }

    protected interface titleDescription {
        @JsonProperty(value = "title_description")
        String getTitleDescription();
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

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class TitleCatalogDTO implements titleName, titleImageUrl {
            String titleName;
            String titleImageUrl;
        }

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class TitlePageCountDTO {
            Integer currentPage;
            Integer totalPage;
            List<Response.TitleCatalogDTO> titlesForCatalog;
        }
    }
}
