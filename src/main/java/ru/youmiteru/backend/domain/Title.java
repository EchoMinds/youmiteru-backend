package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "title", schema = "youmiteru_backend")
@Data
@NoArgsConstructor
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title_image_url")
    private String titleImageUrl;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "title")
    private List<Season> seasonList;

    @ManyToMany
    @JoinTable(
        name = "anime_genres",
        schema = "youmiteru_backend",
        joinColumns = @JoinColumn(name = "title_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    public Title(String titleImageUrl, String name, String description) {
        this.titleImageUrl = titleImageUrl;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Title{" +
            "id=" + id +
            ", titleImageUrl='" + titleImageUrl + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}
