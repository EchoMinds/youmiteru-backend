package ru.youmiteru.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.LinkedHashMap;
@Entity
@Table(name = "season", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "season_image_url")
    private String seasonImageUrl;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name = "title_id", referencedColumnName = "id")
    @JsonIgnore
    private Title titleId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "title_state")
    private TitleState title_state;


    @Column(name = "age_restriction")
    private String ageRestriction;

    @Column(name = "year_season")
    private String yearSeason;

    @Column(name = "anime_format")
    @Enumerated(value = EnumType.STRING)
    private AnimeFormat animeFormat;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
        name = "seasons_voice_actors",
        joinColumns = @JoinColumn(name = "season_id"),
        inverseJoinColumns = @JoinColumn(name = "voice_actor_id")
    )
    private List<VoiceActor> voiceActor;


    @OneToMany(mappedBy = "seasonId")
    @JsonIgnore
    private List<Video> videoList;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "anime_pictures", columnDefinition = "jsonb")
    private LinkedHashMap<String,String> animePictures;

    @OneToMany(mappedBy = "seasonId")
    @JsonIgnore
    private List<Comment> seasonCommentList;

    @OneToMany(mappedBy = "seasonIdRating")
    @JsonIgnore
    private List<Rating> seasonRatingList;

}
