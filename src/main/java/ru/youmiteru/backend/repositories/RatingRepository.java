package ru.youmiteru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.Rating;
@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
}
