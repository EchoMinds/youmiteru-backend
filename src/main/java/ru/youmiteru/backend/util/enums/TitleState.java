package ru.youmiteru.backend.util.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum TitleState {
    //выпускается
    @Enumerated(EnumType.STRING)
    RELEASED,
    // анонс
    @Enumerated(EnumType.STRING)
    ANNOUNCEMENT,
    //незакончено(брошено)
    @Enumerated(EnumType.STRING)
    INCOMPLETE,
    //закончено
    @Enumerated(EnumType.STRING)
    FINISHED
}
