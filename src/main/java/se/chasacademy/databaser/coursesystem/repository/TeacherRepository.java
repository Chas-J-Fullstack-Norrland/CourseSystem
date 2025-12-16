package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.chasacademy.databaser.coursesystem.models.Teacher;

@Repository
public interface TeacherRepository implements JpaRepository<Teacher, Long> {
    Teacher findByEmail(String email);
}
