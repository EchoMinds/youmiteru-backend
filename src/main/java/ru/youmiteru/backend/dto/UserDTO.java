package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(
    String profileImageUrl,
    Long userId
){}
