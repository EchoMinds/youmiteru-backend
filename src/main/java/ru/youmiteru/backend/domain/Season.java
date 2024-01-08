package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "season", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "season_image_url")
    private String seasonImageUrl;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "voice_actor")
    @ManyToMany
    private List<VoiceActor> voiceActor;

    @Column(name = "title_state")
    private TitleState title_state;
}
