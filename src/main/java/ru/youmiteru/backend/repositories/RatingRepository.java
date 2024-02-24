package ru.youmiteru.backend.repositories;

import org.hibernate.mapping.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.Rating;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query(value = "select ROUND(AVG(value),1) from youmiteru_backend.rating where season_id = :seasonId " ,nativeQuery = true)
    Double getRatingBySeasonId(@Param("seasonId") Long seasonId);
}
