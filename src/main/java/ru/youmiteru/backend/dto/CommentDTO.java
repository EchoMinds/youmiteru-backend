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

//public class CommentDTO {
//    protected interface commentId {
//        @JsonProperty(value = "comment_id")
//        Long getCommentId();
//    }
//
//    protected interface creationDate {
//        @JsonProperty(value = "creation_date")
//        LocalDateTime getCreationDate();
//    }
//
//    protected interface message {
//        @JsonProperty(value = "message")
//        String getMessage();
//    }
//
//    protected interface replyToId {
//        @JsonProperty(value = "reply_to_id")
//        Long getReplyToId();
//    }
//
//    protected interface rating {
//        @JsonProperty(value = "rating")
//        int getRating();
//    }
//
//    protected interface writerId {
//        @JsonProperty(value = "writer_id")
//        Long getWriterId();
//    }
//
//    public enum Response {
//        ;
//
//        @NoArgsConstructor
//        @AllArgsConstructor
//        @Data
//        public static class Comments
//            implements CommentDTO.commentId, CommentDTO.creationDate, CommentDTO.message, UserDTO.profileImageUrl,
//            CommentDTO.writerId, CommentDTO.rating {
//            Long commentId;
//            LocalDateTime creationDate;
//            String message;
//            String profileImageUrl;
//            Long writerId;
//            int rating;
//            List<Comments> subcommentsList;
//        }
//    }
//}
