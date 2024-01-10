package ru.youmiteru.backend.repositories;

import jakarta.persistence.Convert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.util.JpaConverterJson;

@Repository
@Convert(converter = JpaConverterJson.class)
public interface SeasonRepository extends JpaRepository<Season, Long> {

}
