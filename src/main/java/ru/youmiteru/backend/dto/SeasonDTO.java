package ru.youmiteru.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class SeasonDTO {

    private interface seasonId {
        Long getSeasonId();
    }

    private interface seasonName {
        String getSeasonName();
    }

    private interface description {
        String getDescription();
    }

    private interface imageUrl {
        String getImageUrl();
    }

    public enum Request {;
        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class HomePage implements seasonId, seasonName, description, imageUrl{
            Long seasonId;
            String seasonName;
            String description;
            String imageUrl;
        }
    }

    public static Map<String, Object> generateResponseBanners(Object banner, Object anons, Object popular, Object released){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("announced_seasons", anons);
        map.put("banners", banner);
        map.put("popular_seasons", popular);
        map.put("recent-released_seasons", released);

        return map;
    }
}
