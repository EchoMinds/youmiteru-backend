package ru.youmiteru.backend.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDTO;

import java.util.HashMap;
import java.util.Map;

//Response Json проще говоря это название листа json
public class ResponseHandler {

    //Response для Баннера
    public static ResponseEntity<Object> generateResponseBanners(Object banner, Object anons, Object popular, Object released){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("announced_seasons", anons);
        map.put("banners", banner);
        map.put("popular_seasons", popular);
        map.put("recent-released_seasons", released);

        return new ResponseEntity<Object>( map, HttpStatus.OK);
    }
}
