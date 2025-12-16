package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.chasacademy.databaser.coursesystem.models.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
}
