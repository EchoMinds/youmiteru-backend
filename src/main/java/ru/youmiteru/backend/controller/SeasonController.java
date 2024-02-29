package ru.youmiteru.backend.controller;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.youmiteru.backend.dto.SeasonDTO;
import ru.youmiteru.backend.service.SeasonService;

@RestController
@RequestMapping("/api/season")
@RequiredArgsConstructor
public class SeasonController {
    private static final Logger logger = LogManager.getLogger();
    private final SeasonService seasonService;

    @GetMapping
    public SeasonDTO.Response.ListHomePage getAllSeasons() {
        return seasonService.getAllSeasonForHomePage();
    }

    @GetMapping("/{id}")
    private SeasonDTO.Response.SeasonPage getSeasonPage(@PathVariable Long id) {
        return seasonService.getSeasonPage(id);
    }
}
