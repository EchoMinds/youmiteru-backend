package ru.youmiteru.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "comment", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date")
    private Timestamp creationDate;

    @Column(name = "message")
    private String message;

    @Column(name = "rating_value", nullable = false)
    private int ratingValue = 0;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "reply_to", referencedColumnName = "id")
    private Comment replyTo;


    @OneToMany(mappedBy = "replyTo", fetch = FetchType.LAZY)
    private List<Comment> answerForThisCommList;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", referencedColumnName = "id")
    private User writerId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season seasonId;

}
