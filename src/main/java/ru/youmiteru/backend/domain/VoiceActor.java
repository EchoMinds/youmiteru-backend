package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@Table(name = "voice_actor", schema = "youmiteru_backend")
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VoiceActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToMany(mappedBy = "voiceActors")
    private List<Season> seasons;

    public VoiceActor(String name) {
        this.name = name;
        user = null;
    }

    public VoiceActor(String name, User userId) {
        this.name = name;
        this.user = userId;
    }
}
