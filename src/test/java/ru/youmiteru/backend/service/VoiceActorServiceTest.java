package ru.youmiteru.backend.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VoiceActorServiceTest {

    @Mock
    private VoiceActorConvertors voiceActorConvertors;

    @Mock
    private VoiceActorRepository voiceActorRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @InjectMocks
    private VoiceActorService voiceActorService;

    private VoiceActor testVoiceActor;
    private User testUser;
    private Season testSeason;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setId(1L);
        testUser.setProfileImageUrl("user.jpg");

        testVoiceActor = new VoiceActor();
        testVoiceActor.setId(1L);
        testVoiceActor.setName("Voice Actor");
        testVoiceActor.setUser(testUser);
        testVoiceActor.setSeasons(new ArrayList<>());

        testSeason = new Season();
        testSeason.setId(1L);
        testSeason.setVoiceActors(new ArrayList<>());
    }

    @Test
    void testGetAllVoiceActors() {
        List<VoiceActor> voiceActors = List.of(testVoiceActor);
        when(voiceActorRepository.findAll()).thenReturn(voiceActors);
        when(voiceActorConvertors.convertToVoiceActorForSeason(testVoiceActor)).thenReturn(new VoiceActorDTO(1L, 1L, "user.jpg"));

        List<VoiceActorDtoCrud> result = voiceActorService.getAllVoiceActors();

        assertEquals(1, result.size());
        VoiceActorDtoCrud voiceActorDto = result.get(0);
        assertEquals(1L, voiceActorDto.id());
        assertEquals("Voice Actor", voiceActorDto.name());
        assertEquals(1L, voiceActorDto.userId());
    }

    @Test
    void testGetVoiceActorById() {
        when(voiceActorRepository.findById(1L)).thenReturn(Optional.of(testVoiceActor));

        VoiceActorDtoCrud result = voiceActorService.getVoiceActorById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Voice Actor", result.name());
        assertEquals(1L, result.userId());
    }

    @Test
    void testGetVoiceActorById_NotFound() {
        when(voiceActorRepository.findById(1L)).thenReturn(Optional.empty());

        VoiceActorDtoCrud result = voiceActorService.getVoiceActorById(1L);

        assertNull(result);
    }

    @Test
    void testUpdateVoiceActor() {
        VoiceActorDtoCrud updateDto = new VoiceActorDtoCrud(1L, "Updated Name", 1L);
        when(voiceActorRepository.findById(1L)).thenReturn(Optional.of(testVoiceActor));
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(voiceActorRepository.save(any(VoiceActor.class))).thenReturn(testVoiceActor);

        VoiceActorDtoCrud result = voiceActorService.updateVoiceActor(1L, updateDto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Updated Name", result.name());
        assertEquals(1L, result.userId());
    }

    @Test
    void testUpdateVoiceActor_NotFound() {
        VoiceActorDtoCrud updateDto = new VoiceActorDtoCrud(1L, "Updated Name", 1L);
        when(voiceActorRepository.findById(1L)).thenReturn(Optional.empty());

        VoiceActorDtoCrud result = voiceActorService.updateVoiceActor(1L, updateDto);

        assertNull(result);
    }

    @Test
    void testCreateVoiceActor() {
        CreateVoiceActorDto createDto = new CreateVoiceActorDto("New Voice Actor", 1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
        when(voiceActorRepository.save(any(VoiceActor.class))).thenReturn(testVoiceActor);

        VoiceActorDtoCrud result = voiceActorService.createVoiceActor(createDto);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Voice Actor", result.name());
        assertEquals(1L, result.userId());
    }

    @Test
    void testAddSeasonToVoiceActor() {
        when(voiceActorRepository.findById(1L)).thenReturn(Optional.of(testVoiceActor));
        when(seasonRepository.findById(1L)).thenReturn(Optional.of(testSeason));
        when(voiceActorRepository.save(any(VoiceActor.class))).thenReturn(testVoiceActor);

        VoiceActorDtoCrud result = voiceActorService.addSeasonToVoiceActor(1L, 1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Voice Actor", result.name());
        assertEquals(1L, result.userId());
        assertEquals(1, testVoiceActor.getSeasons().size());
        assertEquals(1L, testVoiceActor.getSeasons().get(0).getId());
        assertEquals(1, testSeason.getVoiceActors().size());
        assertEquals(1L, testSeason.getVoiceActors().get(0).getId());
    }

    @Test
    void testDeleteVoiceActor() {
        voiceActorService.deleteVoiceActor(1L);

        verify(voiceActorRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetVoiceActorList() {
        testSeason.getVoiceActors().add(testVoiceActor);
        VoiceActorDTO voiceActorDTO = new VoiceActorDTO(1L, 1L, "user.jpg");
        when(voiceActorConvertors.convertToVoiceActorForSeason(testVoiceActor)).thenReturn(voiceActorDTO);

        List<VoiceActorDTO> result = voiceActorService.getVoiceActorList(testSeason);

        assertEquals(1, result.size());
        VoiceActorDTO dto = result.get(0);
        assertEquals(1L, dto.voiceActorId());
        assertEquals(1L, dto.userId());
        assertEquals("user.jpg", dto.profileImageUrl());
    }
}
