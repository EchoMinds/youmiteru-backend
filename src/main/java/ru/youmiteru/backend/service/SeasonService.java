package ru.youmiteru.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.repositories.SeasonRepository;

import java.util.List;
import java.util.Objects;

@Service
public class SeasonService {
    private final SeasonRepository seasonRepository;

    @Autowired
    public SeasonService(SeasonRepository seasonRepository) {
        this.seasonRepository = seasonRepository;
    }

    public List<Season> getAllSeason (){
        return seasonRepository.findAll();
    }
}
