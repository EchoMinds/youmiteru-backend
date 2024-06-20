package ru.youmiteru.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.youmiteru.backend.dto.SeasonDto.ListHomePage;
import ru.youmiteru.backend.service.SeasonService;

@RestController
@RequestMapping("/api/season")
@RequiredArgsConstructor
public class SeasonController {
    private final SeasonService seasonService;

    @GetMapping("/homepage")
    public ListHomePage getAllSeasons() {
        return seasonService.getAllSeasonForHomePage();
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> getSeasonPage(@PathVariable Long id) {
        return new ResponseEntity<>(seasonService.getSeasonPage(id), HttpStatus.OK);
    }
}
