package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.*;
import ru.youmiteru.backend.dto.SeasonDto.*;
import ru.youmiteru.backend.exceptions.SeasonNotFoundException;
import ru.youmiteru.backend.exceptions.UserNotFoundException;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.convertors.SeasonConvertors;
import ru.youmiteru.backend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeasonService {
    private final SeasonRepository seasonRepository;
    private final SeasonConvertors seasonConvertors;
    private final UserRepository userRepository;

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

    public List<FavoriteSeason> getUserFavoriteSeasonList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return new ArrayList<>();
    }

    public List<RatedSeason> getUserRatedSeasonList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getRatingList().stream().map(m -> seasonConvertors.convertSeasonToRatedSeason(m.getSeason(),
            m.getValue())).toList();
    }
}
