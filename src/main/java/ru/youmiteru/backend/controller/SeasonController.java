package ru.youmiteru.backend.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.service.SeasonService;

@RestController
@RequestMapping("/youmiteru")
@AllArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;


    @GetMapping("/all")
    public SeasonDTO.Response.ListHomePage getAllSeasons(){
        return seasonService.getAllSeasonForHomePage();
    }

}
