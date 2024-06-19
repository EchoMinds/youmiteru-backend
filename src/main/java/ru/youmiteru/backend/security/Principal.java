package ru.youmiteru.backend.security;

public record Principal(
        String email,
        String id
) {
}
