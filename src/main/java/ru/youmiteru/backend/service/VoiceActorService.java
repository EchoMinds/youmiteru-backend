package ru.youmiteru.backend.service;

import org.springframework.stereotype.Service;
import ru.youmiteru.backend.convertors.VoiceActorConvertors;
import ru.youmiteru.backend.domain.Season;
import ru.youmiteru.backend.domain.User;
import ru.youmiteru.backend.domain.VoiceActor;
import ru.youmiteru.backend.dto.VoiceActorDTO.CreateVoiceActorDto;
import ru.youmiteru.backend.dto.VoiceActorDTO.VoiceActorDTO;
import ru.youmiteru.backend.dto.VoiceActorDTO.VoiceActorDtoCrud;
import ru.youmiteru.backend.repositories.SeasonRepository;
import ru.youmiteru.backend.repositories.UserRepository;
import ru.youmiteru.backend.repositories.VoiceActorRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VoiceActorService {
    private final VoiceActorConvertors voiceActorConvertors;
    private final VoiceActorRepository voiceActorRepository;
    private final UserRepository userRepository;
    private final SeasonRepository seasonRepository;

    public VoiceActorService(VoiceActorConvertors voiceActorConvertors, VoiceActorRepository voiceActorRepository, UserRepository userRepository, SeasonRepository seasonRepository) {
        this.voiceActorConvertors = voiceActorConvertors;
        this.voiceActorRepository = voiceActorRepository;
        this.userRepository = userRepository;
        this.seasonRepository = seasonRepository;
    }

    public List<VoiceActorDtoCrud> getAllVoiceActors() {
        List<VoiceActor> voiceActors = voiceActorRepository.findAll();
        return voiceActors.stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    public VoiceActorDtoCrud getVoiceActorById(Long id) {
        Optional<VoiceActor> voiceActor = voiceActorRepository.findById(id);
        return voiceActor.map(this::convertToDto).orElse(null);
    }

    public VoiceActorDtoCrud updateVoiceActor(Long id, VoiceActorDtoCrud voiceActorDto) {
        Optional<VoiceActor> existingVoiceActor = voiceActorRepository.findById(id);
        if (existingVoiceActor.isPresent()) {
            VoiceActor updatedVoiceActor = existingVoiceActor.get();
            updatedVoiceActor.setName(voiceActorDto.name());
            Optional<User> user = userRepository.findById(voiceActorDto.userId());
            user.ifPresent(updatedVoiceActor::setUser);
            VoiceActor savedVoiceActor = voiceActorRepository.save(updatedVoiceActor);
            return convertToDto(savedVoiceActor);
        }
        return null;
    }

    public VoiceActorDtoCrud createVoiceActor(CreateVoiceActorDto voiceActorDto) {
        VoiceActor voiceActor = convertToEntity(voiceActorDto);
        VoiceActor savedVoiceActor = voiceActorRepository.save(voiceActor);
        return convertToDto(savedVoiceActor);
    }
    public VoiceActorDtoCrud addSeasonToVoiceActor(Long voiceActorId, Long seasonId) {
        Optional<VoiceActor> optionalVoiceActor = voiceActorRepository.findById(voiceActorId);
        Optional<Season> optionalSeason = seasonRepository.findById(seasonId);

        VoiceActor voiceActor = optionalVoiceActor.get();
        Season season = optionalSeason.get();

        voiceActor.getSeasons().add(season);
        season.getVoiceActors().add(voiceActor);

        VoiceActor updatedVoiceActor = voiceActorRepository.save(voiceActor);
        return convertToDto(updatedVoiceActor);

    }

    public void deleteVoiceActor(Long id) {
        voiceActorRepository.deleteById(id);
    }

    private VoiceActorDtoCrud convertToDto(VoiceActor voiceActor) {
        return new VoiceActorDtoCrud(
            voiceActor.getId(),
            voiceActor.getName(),
            voiceActor.getUser().getId()
        );
    }

    private VoiceActor convertToEntity(CreateVoiceActorDto voiceActorDto) {
        VoiceActor voiceActor = new VoiceActor();
        voiceActor.setName(voiceActorDto.name());
        Optional<User> user = userRepository.findById(voiceActorDto.userId());
        user.ifPresent(voiceActor::setUser);
        return voiceActor;
    }

    public List<VoiceActorDTO> getVoiceActorList(Season season) {
        return season.getVoiceActors()
            .stream().map(voiceActorConvertors::convertToVoiceActorForSeason).toList();
    }
}
