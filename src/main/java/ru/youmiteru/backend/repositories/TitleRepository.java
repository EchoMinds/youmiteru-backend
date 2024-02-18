package ru.youmiteru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.Title;

import java.util.List;
import java.util.Optional;

@Repository
public interface TitleRepository extends JpaRepository<Title,Long>, JpaSpecificationExecutor<Title> {


    @Query(value = "select (genre_id) from youmiteru_backend.anime_genres where title_id = :#{#id}", nativeQuery = true)
    List<Long> findGenreIdsByTitleIds(@Param("id") Long id);

    @Query(value = "select (title_id) from youmiteru_backend.anime_genres where genre_id in :id", nativeQuery = true)
    List<Long> findTitleIdsByGenreIdsList(@Param("id") List<Long> id);

}
