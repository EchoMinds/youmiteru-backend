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

    public List<CommentDTO.Response.Comments> getCommentsList(Season seasonPage) {
        return seasonPage.getSeasonCommentList().stream()
            .filter(comment -> comment.getReplyTo() == null)    // return only head comment
            .map(this::convertToCommentDto).toList();
    }

    public List<CommentDTO.Response.Comments> getSubCommentsList(Comment comment) {
        return commentRepository.findByReplyTo(comment).stream()
            .map(this::convertToCommentDto).toList();
    }


    public CommentDTO.Response.Comments convertToCommentDto(Comment comment) {
        CommentDTO.Response.Comments commentDTO = new CommentDTO.Response.Comments();

        commentDTO.setCommentId(comment.getId());
        commentDTO.setCreationDate(comment.getCreationDate());
        commentDTO.setRating(comment.getRatingValue());
        commentDTO.setMessage(comment.getMessage());
        commentDTO.setWriterId(comment.getWriter().getId());
        commentDTO.setProfileImageUrl(comment.getWriter().getProfileImageUrl());
        commentDTO.setSubcommentsList(getSubCommentsList(comment));

        return commentDTO;
    }
}
