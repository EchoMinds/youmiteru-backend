package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.SeasonDto.*;
import ru.youmiteru.backend.exceptions.SeasonNotFoundException;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.convertors.SeasonConvertors;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private final SeasonConvertors seasonConvertors;

    public ListHomePage getAllSeasonForHomePage() {
        List<HomePage> anons = seasonRepository.findAnnouncement()
            .stream()
            .map(seasonConvertors::homePageResponse)
            .collect(Collectors.toList());

        List<HomePage> release = seasonRepository.findRecent()
            .stream()
            .map(seasonConvertors::homePageResponse)
            .collect(Collectors.toList());

        List<HomePage> banner = seasonRepository.findBanner()
            .stream()
            .map(seasonConvertors::homePageResponse)
            .collect(Collectors.toList());

        List<HomePage> popular = seasonRepository.findPopular()
            .stream()
            .map(seasonConvertors::homePageResponse)
            .collect(Collectors.toList());

        return new ListHomePage(banner, anons, popular, release);
    }

    public SeasonPage getSeasonPage(Long id) {
        Season seasonPages = seasonRepository.findById(id).orElseThrow(SeasonNotFoundException::new);
        List<RelatedSeason> relatedSeasons = seasonPages.getTitle().getSeasonList().stream()
            .map(seasonConvertors::convertToRelatedSeason).toList();
        return seasonConvertors.seasonPageResponse(seasonPages,
            relatedSeasons);
    }

    public ResponseEntity<?> updateSeasonService(Long id, Season season){
        Optional<Season> seasonOptional = seasonRepository.findById(id);
        if (seasonOptional.isPresent()) {
            Season existingSeason = seasonOptional.get();
            existingSeason.setName(season.getName());
            return ResponseEntity.ok(seasonRepository.save(existingSeason));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
