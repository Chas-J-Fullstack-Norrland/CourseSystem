package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.chasacademy.databaser.coursesystem.model.CourseSession;

@Repository
public interface CourseSessionRepository extends JpaRepository<CourseSession,Long> {
}
