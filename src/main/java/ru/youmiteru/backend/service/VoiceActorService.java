package ru.youmiteru.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.VoiceActor;
import ru.youmiteru.backend.dto.VoiceActorDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoiceActorService {
    // get voice actor list
    public List<VoiceActorDTO.Response.VoiceActorForSeason> getVoiceActorList(Season season) {
        List<VoiceActorDTO.Response.VoiceActorForSeason> voiceActorForSeasonList = season.getVoiceActors()
            .stream().map(this::convertToVoiceActorForSeason).toList();

        return voiceActorForSeasonList;
    }

    //convert voice actors
    public VoiceActorDTO.Response.VoiceActorForSeason convertToVoiceActorForSeason(VoiceActor voiceActor) {
        VoiceActorDTO.Response.VoiceActorForSeason voiceActorForSeason = new VoiceActorDTO.Response.VoiceActorForSeason();

        voiceActorForSeason.setVoiceActorId(voiceActor.getId());
        voiceActorForSeason.setUserId(voiceActor.getUser().getId());
        voiceActorForSeason.setProfileImageUrl(voiceActor.getUser().getProfileImageUrl());

        return voiceActorForSeason;
    }
}
