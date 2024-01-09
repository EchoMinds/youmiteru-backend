package ru.youmiteru.backend.util.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum Role {

    @Enumerated(EnumType.STRING)
    USER,
    @Enumerated(EnumType.STRING)
    ACTOR,
    @Enumerated(EnumType.STRING)
    ADMIN

}
