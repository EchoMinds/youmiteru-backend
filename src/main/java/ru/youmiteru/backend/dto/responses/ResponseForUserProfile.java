package ru.youmiteru.backend.dto.responses;

import ru.youmiteru.backend.domain.User;

public record ResponseForUserProfile(
    String token,
    User user
) {
}
