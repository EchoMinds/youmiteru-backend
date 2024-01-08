package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "user", schema = "youmiteru_backend")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private Role role;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    public User(String profileImageUrl, String name, String email, Role role) {
        this.profileImageUrl = profileImageUrl;
        this.name = name;
        this.email = email;
        this.role = role;
        this.creationTime = LocalDateTime.now();
    }
}
