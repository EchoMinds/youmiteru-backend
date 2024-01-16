package ru.youmiteru.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class SeasonDTO {

    private Long id;

    private String name;

    private String description;

    private String seasonImageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeasonImageUrl() {
        return seasonImageUrl;
    }

    public void setSeasonImageUrl(String seasonImageUrl) {
        this.seasonImageUrl = seasonImageUrl;
    }
}
