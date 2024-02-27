package ru.youmiteru.backend.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.VoiceActor;
import ru.youmiteru.backend.dto.VoiceActorDTO;

@Component
@RequiredArgsConstructor
public class VoiceActorConvertors {
    //convert voice actors
    public VoiceActorDTO.Response.VoiceActorForSeason convertToVoiceActorForSeason(VoiceActor voiceActor) {
        VoiceActorDTO.Response.VoiceActorForSeason voiceActorForSeason = new VoiceActorDTO.Response.VoiceActorForSeason();

        voiceActorForSeason.setVoiceActorId(voiceActor.getId());
        voiceActorForSeason.setUserId(voiceActor.getUser().getId());
        voiceActorForSeason.setProfileImageUrl(voiceActor.getUser().getProfileImageUrl());

        return voiceActorForSeason;
    }
}
