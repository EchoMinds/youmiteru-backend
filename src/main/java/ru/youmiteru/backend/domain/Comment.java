package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comment", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "message")
    private String message;

    @Column(name = "rating_value")
    private int ratingValue;

    /////////
    @ManyToOne
    @JoinColumn(name = "reply_to", referencedColumnName = "id")
    private Comment replyTo;

    @OneToMany(mappedBy = "replyTo")
    private List<Comment> treeComment;
    ///////////

    @ManyToOne
    @JoinColumn(name = "writer_id", referencedColumnName = "id")
    private User writerId;

    @ManyToOne
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season seasonId;

    // при отсутствии replyTo ставится null
    public Comment(LocalDateTime creationDate, String message,
                   int ratingValue, User writerId, Season seasonId) {
        this.creationDate = creationDate;
        this.message = message;
        this.ratingValue = ratingValue;
        this.writerId = writerId;
        this.seasonId = seasonId;
        this.replyTo = null;
    }

    public Comment(LocalDateTime creationDate, String message, int ratingValue,
                   Comment replyTo, User writerId, Season seasonId) {
        this.creationDate = creationDate;
        this.message = message;
        this.ratingValue = ratingValue;
        this.replyTo = replyTo;
        this.writerId = writerId;
        this.seasonId = seasonId;
    }
}
