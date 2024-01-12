package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "user", schema = "youmiteru_backend")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_picture_url")
    private String profileImageUrl;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @OneToOne(mappedBy = "user")
    private VoiceActor voiceActorAcc;

    @OneToMany(mappedBy = "writer")
    private List<Comment> usersComms;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratingList;


    public User(String profileImageUrl, String name, String email, Role role) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.email = email;
        this.role = role;
        this.creationTime = LocalDateTime.now();
    }
}
