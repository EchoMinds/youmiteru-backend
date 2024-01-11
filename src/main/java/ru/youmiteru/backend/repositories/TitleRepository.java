package ru.youmiteru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.Title;

@Repository
public interface TitleRepository extends JpaRepository<Title,Long> {
}
