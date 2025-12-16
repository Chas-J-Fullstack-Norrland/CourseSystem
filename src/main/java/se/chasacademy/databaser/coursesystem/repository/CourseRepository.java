package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.chasacademy.databaser.coursesystem.model.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
