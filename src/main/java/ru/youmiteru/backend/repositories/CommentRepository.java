package ru.youmiteru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
