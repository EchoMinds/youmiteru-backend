package ru.youmiteru.backend.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "titleId")
    private List<Season> seasonList;

    public Title(String titleImageUrl, String name, String description) {
        this.titleImageUrl = titleImageUrl;
        this.name = name;
        this.description = description;
    }
}
