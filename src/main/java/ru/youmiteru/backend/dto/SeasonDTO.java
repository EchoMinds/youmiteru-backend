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


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeasonDTO {

    private Long id;
    private String name;
    private String description;
    private String seasonImageUrl;

    public static Map<String, Object> generateResponseBanners(Object banner, Object anons, Object popular, Object released){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("announced_seasons", anons);
        map.put("banners", banner);
        map.put("popular_seasons", popular);
        map.put("recent-released_seasons", released);

        return map;
    }
}
