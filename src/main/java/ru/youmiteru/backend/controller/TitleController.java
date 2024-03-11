package ru.youmiteru.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.dto.TitleDTO.Response.*;
import ru.youmiteru.backend.service.TitleService;

import java.util.List;

@RestController
@RequestMapping("/api/title")
@RequiredArgsConstructor
public class TitleController {
    private final TitleService titleService;

    /*@GetMapping
    public ResponseEntity<List<TitleCatalogDTO>> getCatalog(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                           @RequestParam(value = "genres", required = false) List<String> genres,
                                                           @RequestParam(value = "dates", required = false) List<Long> date,
                                                           @RequestParam(value = "format", required = false) List<String> format,
                                                           @RequestParam(value = "state", required = false) List<String> state,
                                                           @RequestParam(value = "ageRestriction", required = false) List<String> ageRestriction,
                                                            @RequestParam(value = "yearSeason", required = false) List<String> yearSeason){

        List<TitleCatalogDTO> catalog = titleService.getCatalog(offset ,genres, date, format, state, ageRestriction, yearSeason);
        if(!catalog.isEmpty()){
            return new ResponseEntity<>(catalog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }*/

    @GetMapping
    public ResponseEntity<TitlePageCountDTO> getCatalog(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
                                                            @RequestParam(value = "genres", required = false) List<String> genres,
                                                            @RequestParam(value = "dates", required = false) List<Long> date,
                                                            @RequestParam(value = "format", required = false) List<String> format,
                                                            @RequestParam(value = "state", required = false) List<String> state,
                                                            @RequestParam(value = "ageRestriction", required = false) List<String> ageRestriction,
                                                            @RequestParam(value = "yearSeason", required = false) List<String> yearSeason){

        TitlePageCountDTO catalog = titleService.getCatalog(offset ,genres, date, format, state, ageRestriction, yearSeason);
        if(catalog != null){
            return new ResponseEntity<>(catalog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
