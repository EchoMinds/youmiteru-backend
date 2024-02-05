package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    public interface profileImageUrl {
        @JsonProperty(value = "profile_picture_url")
        String getProfileImageUrl();
    }
}
