package ru.youmiteru.backend.repositories;

import org.hibernate.mapping.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.Rating;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

}
