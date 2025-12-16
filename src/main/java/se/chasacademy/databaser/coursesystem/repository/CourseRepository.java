package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.chasacademy.databaser.coursesystem.models.Course;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Long> {
    Optional<Course> findByTitle(String title);
    Course course = courseRepository.findByTitle("Java 1").orElseThrow();
    course.getParticipants();
}
