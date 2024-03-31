package ru.youmiteru.backend.dto.SeasonDto;

import ru.youmiteru.backend.domain.TitleState;
import ru.youmiteru.backend.dto.CommentDTO;
import ru.youmiteru.backend.dto.VoiceActorDTO.VoiceActorDTO;

import java.time.LocalDate;
import java.util.List;

public record SeasonPage(
    Long seasonId,
    String imageUrl,
    String seasonName,
    String animeFormat,
    String description,
    LocalDate releaseDate,
    TitleState titleState,
    String ageRestriction,
    String yearSeason,
    String reducedDescription,
    Double rating,
    List<RelatedSeason> relatedSeasons,
    List<String> genres,
    List<CommentDTO> commentsList,
    List<VoiceActorDTO> voiceActorDTO,
    String codeVideoPlayer,
    String animeBannerUrl

) {
}
