package ru.youmiteru.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.youmiteru.backend.service.SeasonService;

@RestController
@RequestMapping("/youmitery")
public class SeasonController {
    private final SeasonService seasonService;

    @Autowired
    public SeasonController(SeasonService seasonService) {
        this.seasonService = seasonService;
    }


    @GetMapping("/all")
    public ResponseEntity<Object> getAllSeasons(){
        return new ResponseEntity<Object>(seasonService.getAllSeasonForHomePage(), HttpStatus.OK);
    }

}
