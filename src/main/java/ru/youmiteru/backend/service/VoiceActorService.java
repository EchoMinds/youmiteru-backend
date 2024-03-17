package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.convertors.VoiceActorConvertors;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.dto.VoiceActorDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoiceActorService {
    private final VoiceActorConvertors voiceActorConvertors;

    public List<VoiceActorDTO> getVoiceActorList(Season season) {
        return season.getVoiceActors()
            .stream().map(voiceActorConvertors::convertToVoiceActorForSeason).toList();
    }
}
