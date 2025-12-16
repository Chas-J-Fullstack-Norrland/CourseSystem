package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import se.chasacademy.databaser.coursesystem.models.Teacher;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByEmail(String email);

    @Query("SELECT t FROM Teacher t WHERE SIZE(t.courses) > :nrOfCourses")
    List<Teacher> findTeachersWithMoreThanNCourses(@Param("nrOfCourses") int nrOfCourses);
}
