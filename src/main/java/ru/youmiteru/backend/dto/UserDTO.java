package ru.youmiteru.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {
    protected interface profileImageUrl {
        @JsonProperty(value = "profile_picture_url")
        String getProfileImageUrl();
    }
    protected interface userId {
        @JsonProperty(value = "user_id")
        Long getUserId();
    }

}
