package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Comment;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.CommentDTO;
import ru.youmiteru.backend.repositories.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<CommentDTO> getCommentsList(Season seasonPage) {
        return seasonPage.getSeasonCommentList().stream()
            .filter(comment -> comment.getReplyTo() == null)    // return only head comment
            .map(this::convertToCommentDto).toList();
    }

    public List<CommentDTO> getSubCommentsList(Comment comment) {
        return commentRepository.findByReplyTo(comment).stream()
            .map(this::convertToCommentDto).toList();
    }


    public CommentDTO convertToCommentDto(Comment comment) {
        return new CommentDTO(
            comment.getId(),
            comment.getCreationDate(),
            comment.getMessage(),
            comment.getWriter().getProfileImageUrl(),
            comment.getWriter().getId(),
            comment.getRatingValue(),
            getSubCommentsList(comment)
        );
    }
}
