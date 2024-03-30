package ru.youmiteru.backend.controller.admin_panel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDto.ListHomePage;
import ru.youmiteru.backend.dto.SeasonDto.SeasonPage;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.service.SeasonService;

@RestController
@RequestMapping("/api/admin/season")
public class SeasonAdminController {
    private final SeasonService seasonService;
    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonAdminController(SeasonService seasonService, SeasonRepository seasonRepository) {
        this.seasonService = seasonService;
        this.seasonRepository = seasonRepository;
    }

    @PostMapping
    public Season createSeason(@RequestBody Season season) {
        return seasonRepository.save(season);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSeason(@PathVariable Long id, @RequestBody Season season) {
        return seasonService.updateSeasonService(id, season);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeasonById(@PathVariable Long id) {
        seasonRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
