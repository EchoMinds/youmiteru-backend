package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "voice_actor", schema = "youmiteru_backend")
@NoArgsConstructor
public class VoiceActor{
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public VoiceActor(String name) {
        this.name = name;
    }
}
