package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rating", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "value")
    private Long value;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userIdRating;

    @ManyToOne
    @JoinColumn(name = "season_id", referencedColumnName = "id")
    private Season seasonIdRating;
}
