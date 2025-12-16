package se.chasacademy.databaser.coursesystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.chasacademy.databaser.coursesystem.models.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface TeacherRepository implements JpaRepository<Teacher, Long> {
    Teacher findByEmail(String email);
    @Query("""
            SELECT t
            FROM Teacher t
            JOIN t.courses c
            GROUP BY t
            HAVING COUNT(c) > :count
            """)
    List<Teacher> findTeachersWithMoreThanNCourses(@Param("count") long count);
}
