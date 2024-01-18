package ru.youmiteru.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.TitleState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SeasonDTO {

    private Long id;
    private String name;
    private String description;
    private String seasonImageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeasonImageUrl() {
        return seasonImageUrl;
    }

    public void setSeasonImageUrl(String seasonImageUrl) {
        this.seasonImageUrl = seasonImageUrl;
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
