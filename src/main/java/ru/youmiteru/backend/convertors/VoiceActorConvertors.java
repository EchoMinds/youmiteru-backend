package ru.youmiteru.backend.convertors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.youmiteru.backend.domain.VoiceActor;
import ru.youmiteru.backend.dto.VoiceActorDTO.VoiceActorDTO;

@Component
@RequiredArgsConstructor
public class VoiceActorConvertors {
    //convert voice actors
    public VoiceActorDTO convertToVoiceActorForSeason(VoiceActor voiceActor) {

        return new VoiceActorDTO(
            voiceActor.getId(),
            voiceActor.getUser().getId(),
            voiceActor.getUser().getProfileImageUrl()
        );

    }
}
