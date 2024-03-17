package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public record CommentDTO(
    Long commentId,
    LocalDateTime creationDate,
    String message,
    String profileImageUrl,
    Long writerId,
    int rating,
    List<CommentDTO> subcommentsList
){}
