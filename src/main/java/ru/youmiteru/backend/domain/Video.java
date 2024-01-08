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

    @Column(name = "episode")
    private int episode;

    @Column(name = "player")
    private String player;

    @Column(name = "link")
    private String playerUrl;

    @ManyToOne
    @JoinColumn(
        name = "season_id",
        referencedColumnName = "id"
    )
    private Season season;

    public Video(int episode, String player, String playerUrl) {
        this.episode = episode;
        this.player = player;
        this.playerUrl = playerUrl;
    }
}
