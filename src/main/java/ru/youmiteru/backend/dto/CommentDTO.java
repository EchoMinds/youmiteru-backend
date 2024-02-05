package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class CommentDTO {
    private interface commentId {
        @JsonProperty(value = "comment_id")
        Long getCommentId();
    }

    private interface creationDate {
        @JsonProperty(value = "creation_date")
        LocalDateTime getCreationDate();
    }

    private interface message {
        @JsonProperty(value = "message")
        String getMessage();
    }

    public enum Response {
        ;

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class Comments
            implements CommentDTO.commentId, CommentDTO.creationDate, CommentDTO.message, UserDTO.profileImageUrl {
            Long commentId;
            LocalDateTime creationDate;
            String message;
            String profileImageUrl;
        }
    }
}
