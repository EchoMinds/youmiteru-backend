package ru.youmiteru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre,Long>{
    List<Genre> findByName(String name);
}
