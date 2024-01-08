package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genre", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;
}
