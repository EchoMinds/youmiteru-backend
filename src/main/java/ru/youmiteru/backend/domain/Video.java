package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "video", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "player")
    private String player;

    @Column(name = "link")
    private String playerUrl;
}
