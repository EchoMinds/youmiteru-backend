package ru.youmiteru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.Genre;
@Repository
public interface GenreRepository extends JpaRepository<Genre,Long>{

}
