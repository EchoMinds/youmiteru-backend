package ru.youmiteru.backend.dto.VideoDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



public record VideoDTO(
    int episode,
    String player,
    String link
) {
    @Override
    public int episode() {
        return episode;
    }

    @Override
    public String player() {
        return player;
    }

    @Override
    public String link() {
        return link;
    }
}


