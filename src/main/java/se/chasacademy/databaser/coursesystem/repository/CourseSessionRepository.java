package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.chasacademy.databaser.coursesystem.models.CourseSession;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseSessionRepository extends JpaRepository<CourseSession,Long> {
    List<CourseSession> findByDateAfter(LocalDate date);

    List<CourseSession> findByRoom_Name(String roomName);
}
