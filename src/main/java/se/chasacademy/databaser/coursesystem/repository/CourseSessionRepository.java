package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.chasacademy.databaser.coursesystem.models.CourseSession;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface CourseSessionRepository extends JpaRepository<CourseSession,Long> {
    List<CourseSession> findByStartTimeAfter(LocalDateTime dateTime);
    CourseSessionRepository.findByStartTimeAfter(LocalDateTime.now());
    List<CourseSession> findByRoom(Room room)//_Name(String name) efter FindByRoom om vi hellre vill det;
}
