package ru.youmiteru.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.youmiteru.backend.domain.VoiceActor;

import java.util.List;

@Repository
public interface VoiceActorRepository extends JpaRepository<VoiceActor,Long> {

}
