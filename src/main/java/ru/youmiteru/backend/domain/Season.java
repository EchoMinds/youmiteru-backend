package ru.youmiteru.backend.domain;

import com.fasterxml.jackson.databind.util.JSONPObject;
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

    @Column(name = "title_id")
    private Long titleId;

    @Column(name = "title_state")
    private TitleState title_state;

    @Column(name = "age_restriction")
    private String ageRestriction;

    @Column(name = "year_season")
    private String yearSeason;

    @Column(name = "voice_actor")
    @ManyToMany
    @JoinTable(
        name = "seasons_voice_actors",
        joinColumns = @JoinColumn(name = "season_id"),
        inverseJoinColumns = @JoinColumn(name = "voice_actor_id")
    )
    private List<VoiceActor> voiceActor;


    @OneToMany(mappedBy = "season")
    private List<Video> videoList;

//    private хуйзнаеткакхранить json animePictures;


    public Season(String seasonImageUrl, String name, String description, LocalDate releaseDate,
                  Long titleId, TitleState title_state, String ageRestriction, String yearSeason,
                  List<VoiceActor> voiceActor, List<Video> videoList) {
        this.seasonImageUrl = seasonImageUrl;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.titleId = titleId;
        this.title_state = title_state;
        this.ageRestriction = ageRestriction;
        this.yearSeason = yearSeason;
        this.voiceActor = voiceActor;
        this.videoList = videoList;
    }
}
