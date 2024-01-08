package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "message")
    private String message;

    @Column(name = "rating_value")
    private Double ratingValue;

    @Column(name = "reply_to")
    private Long replyTo;
}
