package ru.youmiteru.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "season", schema = "youmiteru_backend")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    public void setTitle(Title title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "season_image_url")
    private String seasonImageUrl;

    @Column(name = "reduced_description")
    private String reducedDescription;
    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "id")
    private Title title;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "title_state")
    private TitleState titleState;


    @Column(name = "age_restriction")
    private String ageRestriction;

    @Column(name = "year_season")
    private String yearSeason;

    @Column(name = "anime_format")
    @Enumerated(value = EnumType.STRING)
    private AnimeFormat animeFormat;

    @ManyToMany
    @JoinTable(
        name = "seasons_voice_actors",
        schema = "youmiteru_backend",
        joinColumns = @JoinColumn(name = "season_id"),
        inverseJoinColumns = @JoinColumn(name = "voice_actor_id")
    )
    private List<VoiceActor> voiceActors;

    @ManyToMany(mappedBy = "favoriteSeasonList")
    private List<User> thisUserLikeThisAnime;

    @OneToMany(mappedBy = "season")
    private List<Video> videoList;

    @Column(name = "anime_banner_url")
    private String animeBannerUrl;

    @OneToMany(mappedBy = "season")
    private List<Comment> seasonCommentList;

    @OneToMany(mappedBy = "season")
    private List<Rating> seasonRatingList;

    public Season(long id, Title title, String testSeason, String testDescription, boolean date, boolean titleState,
                  boolean animeFormat, boolean videoList) {
    }

}
