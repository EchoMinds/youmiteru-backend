package ru.youmiteru.backend.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "comment", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "message")
    private String message;

    @Column(name = "rating_value", nullable = false)
    private int ratingValue = 0;

    @ManyToOne
    @JoinColumn(name = "reply_to", referencedColumnName = "id")
    private Comment replyTo;


    @OneToMany(mappedBy = "replyTo", fetch = FetchType.LAZY)
    private List<Comment> answerForThisCommList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id", referencedColumnName = "id")
    private User writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season season;

}
