package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.youmiteru.backend.domain.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    private interface replyToId {
        @JsonProperty(value = "reply_to_id")
        Long getReplyToId();
    }

    private interface rating {
        @JsonProperty(value = "rating")
        int getRating();
    }

    private interface writerId {
        @JsonProperty(value = "writer_id")
        Long getWriterId();
    }

    public enum Response {
        ;

        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class Comments
            implements CommentDTO.commentId, CommentDTO.creationDate, CommentDTO.message, UserDTO.profileImageUrl,
            CommentDTO.writerId, CommentDTO.rating {
            Long commentId;
            LocalDateTime creationDate;
            String message;
            String profileImageUrl;
            Long writerId;
            int rating;
            List<Comments> subcommentsList;
        }
//
//        @NoArgsConstructor
//        @AllArgsConstructor
//        @Data
//        public static class SubComments
//            implements CommentDTO.commentId, CommentDTO.creationDate, CommentDTO.message,
//            UserDTO.profileImageUrl, CommentDTO.replyToId, CommentDTO.writerId, CommentDTO.rating {
//            Long commentId;
//            LocalDateTime creationDate;
//            String message;
//            String profileImageUrl;
//            Long replyToId;
//            Long writerId;
//            int rating;
//        }
    }
}
