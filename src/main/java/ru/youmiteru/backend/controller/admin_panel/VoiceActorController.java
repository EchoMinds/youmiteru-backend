package ru.youmiteru.backend.controller.admin_panel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.youmiteru.backend.dto.VoiceActorDTO.CreateVoiceActorDto;
import ru.youmiteru.backend.dto.VoiceActorDTO.VoiceActorDtoCrud;
import ru.youmiteru.backend.dto.VoiceActorDTO.VoiceActorSeasonDto;
import ru.youmiteru.backend.service.VoiceActorService;

import java.util.List;

@RestController
@RequestMapping("/voice-actors")
public class VoiceActorController {
    private final VoiceActorService voiceActorService;

    public VoiceActorController(VoiceActorService voiceActorService) {
        this.voiceActorService = voiceActorService;
    }

    @GetMapping
    public List<VoiceActorDtoCrud> getAllVoiceActors() {
        return voiceActorService.getAllVoiceActors();
    }

    @GetMapping("/{id}")
    public VoiceActorDtoCrud getVoiceActorById(@PathVariable Long id) {
        return voiceActorService.getVoiceActorById(id);
    }

    @PostMapping
    public VoiceActorDtoCrud createVoiceActor(@RequestBody CreateVoiceActorDto voiceActorDto) {
        return voiceActorService.createVoiceActor(voiceActorDto);
    }

    @PutMapping("/{id}")
    public VoiceActorDtoCrud updateVoiceActor(@PathVariable Long id, @RequestBody VoiceActorDtoCrud voiceActorDto) {
        return voiceActorService.updateVoiceActor(id, voiceActorDto);
    }

    @DeleteMapping("/{id}")
    public void deleteVoiceActor(@PathVariable Long id) {
        voiceActorService.deleteVoiceActor(id);
    }

    @PutMapping("/{voiceActorId}/seasons/{seasonId}")
    public ResponseEntity<VoiceActorDtoCrud> addSeasonToVoiceActor(
        @PathVariable Long voiceActorId,
        @PathVariable Long seasonId,
        @RequestBody VoiceActorSeasonDto voiceActorSeasonDto
    ) {
        if (voiceActorId.equals(voiceActorSeasonDto.voiceActorId()) && seasonId.equals(voiceActorSeasonDto.seasonId())) {
            VoiceActorDtoCrud updatedVoiceActor = voiceActorService.addSeasonToVoiceActor(voiceActorId, seasonId);
            if (updatedVoiceActor != null) {
                return ResponseEntity.ok(updatedVoiceActor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

}
