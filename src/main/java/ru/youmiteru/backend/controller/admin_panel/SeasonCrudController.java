package ru.youmiteru.backend.controller.admin_panel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.SeasonDto.ListHomePage;
import ru.youmiteru.backend.dto.SeasonDto.SeasonPage;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.service.SeasonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seasons")
public class SeasonCrudController {
    private final SeasonService seasonService;

    public SeasonCrudController(SeasonService seasonService, SeasonRepository seasonRepository) {
        this.seasonService = seasonService;
        this.seasonRepository = seasonRepository;
    }

    private final SeasonRepository seasonRepository;


    @GetMapping("/")
    public ListHomePage getAllSeasons() {
        return seasonService.getAllSeasonForHomePage();
    }

    @GetMapping("/{id}")
    private SeasonPage getSeasonPage(@PathVariable Long id) {
        return seasonService.getSeasonPage(id);
    }


    @PostMapping("/")
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
