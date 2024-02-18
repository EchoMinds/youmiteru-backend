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

    //get comments
    public List<CommentDTO.Response.Comments> getCommentsList(Season seasonPage) {
        return seasonPage.getSeasonCommentList().stream()
            .filter(comment -> comment.getReplyTo() == null)
            .map(this::convertToCommentDto).toList();
    }

    //return commentDTO for season Page
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

    //get SubComments
    public List<CommentDTO.Response.Comments> getSubCommentsList(Comment comment) {
        return commentRepository.findByReplyTo(comment).stream()
            .map(this::convertToCommentDto).toList();
    }
    // safe moment if we need another method to get comments in season page!
//    private CommentDTO.Response.SubComments convertToSubCommentDto(Comment comment) {
//        CommentDTO.Response.SubComments subComment = new CommentDTO.Response.SubComments(
//            comment.getId(), comment.getCreationDate(), comment.getMessage(), comment.getWriter().getProfileImageUrl(),
//            comment.getReplyTo().getId(), comment.getWriter().getId(), comment.getRatingValue()
//        );
//
//        return subComment;
//    }
}
