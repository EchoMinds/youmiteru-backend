package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.SeasonDto.*;
import ru.youmiteru.backend.dto.responses.ResponseForSeasonPage;
import ru.youmiteru.backend.exceptions.SeasonNotFoundException;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private final SeasonConvertors seasonConvertors;
    private final UserRepository userRepository;
    private final UserService userService;

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

    public ResponseForSeasonPage getSeasonPage(Long idSeason) {
        Season seasonPages = seasonRepository.findById(idSeason).orElseThrow(SeasonNotFoundException::new);
        Long idUser = null;

        List<RelatedSeason> relatedSeasons = seasonPages.getTitle().getSeasonList().stream()
            .map(seasonConvertors::convertToRelatedSeason).toList();

        Authentication authentication = SecurityContextHolder
            .getContext().getAuthentication();
        System.out.println(authentication.getName());
        if (!authentication.getName().equals("anonymousUser")) {
            idUser = userRepository.findByEmail(authentication.getName()).getId();
        }
        return new ResponseForSeasonPage(seasonConvertors.seasonPageResponse(seasonPages,
            relatedSeasons), idUser);
    }

    public ResponseEntity<?> updateSeasonService(Long id, Season season) {
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
