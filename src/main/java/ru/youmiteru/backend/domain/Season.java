package ru.youmiteru.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.youmiteru.backend.util.JsonObj;
import ru.youmiteru.backend.util.enums.TitleState;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "season", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
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
    private Title titleId;

//    @Column(name = "title_state")
//    private TitleState title_state;

    // for test
    @Column(name = "title_state")
    private String title_state;

    @Column(name = "age_restriction")
    private String ageRestriction;

    @Column(name = "year_season")
    private String yearSeason;

    @ManyToMany
    @JoinTable(
        name = "seasons_voice_actors",
        joinColumns = @JoinColumn(name = "season_id"),
        inverseJoinColumns = @JoinColumn(name = "voice_actor_id")
    )
    private List<VoiceActor> voiceActor;


    @OneToMany(mappedBy = "seasonId")
    private List<Video> videoList;

    @Column(name = "anime_pictures", columnDefinition = "jsonb")
    private JsonObj animePictures;  // Изменено на JsonNode

    @OneToMany(mappedBy = "seasonId")
    private List<Comment> seasonCommentList;

    @OneToMany(mappedBy = "seasonIdRating")
    private List<Rating> seasonRatingList;

//    public Season(String seasonImageUrl, String name, String description, LocalDate releaseDate,
//                  Title titleId, TitleState title_state, String ageRestriction, String yearSeason,
//                  List<VoiceActor> voiceActor, List<Video> videoList) {
//        this.seasonImageUrl = seasonImageUrl;
//        this.name = name;
//        this.description = description;
//        this.releaseDate = releaseDate;
//        this.titleId = titleId;
//        this.title_state = title_state;
//        this.ageRestriction = ageRestriction;
//        this.yearSeason = yearSeason;
//        this.voiceActor = voiceActor;
//        this.videoList = videoList;
//    }
}
