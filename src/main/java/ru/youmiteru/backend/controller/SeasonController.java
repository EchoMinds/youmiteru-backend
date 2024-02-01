package ru.youmiteru.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.service.SeasonService;

@RestController
@RequestMapping("/youmiteru")
@RequiredArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;

    @GetMapping("/all")
    public SeasonDTO.Response.ListHomePage getAllSeasons() {
        return seasonService.getAllSeasonForHomePage();
    }

    @GetMapping("/season/{id}")
    private SeasonDTO.Response.SeasonPage getSeasonPage(@PathVariable Long id) {
        return seasonService.getSeasonPage(id);
    }
}
