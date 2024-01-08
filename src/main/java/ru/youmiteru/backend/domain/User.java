package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table(name="user", schema = "youmiteru_backend")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Column(name = "username")
    private String name;

    @Column(name = "email")
    private String email;

    private Role roles;
}
