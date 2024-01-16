package ru.youmiteru.backend.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDTO;

import java.util.HashMap;
import java.util.Map;

//Response Json проще говоря это название листа json
public class ResponseHandler {
    public static ResponseEntity<Object> generateResponseBanners(Object response){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("banners", response);

        return new ResponseEntity<Object>( map, HttpStatus.OK);
    }
}
