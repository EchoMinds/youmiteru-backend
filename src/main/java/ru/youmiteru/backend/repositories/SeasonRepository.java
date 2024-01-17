package ru.youmiteru.backend.repositories;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.AnimeFormat;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.Title;
import ru.youmiteru.backend.domain.TitleState;

import java.util.Arrays;
import java.util.List;

@Repository
public interface SeasonRepository extends JpaRepository<Season, Long> {
    List<Season> findByTitleState(TitleState titleState, PageRequest pageRequest);
}
