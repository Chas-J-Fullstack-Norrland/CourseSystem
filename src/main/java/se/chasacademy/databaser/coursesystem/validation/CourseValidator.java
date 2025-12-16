package se.chasacademy.databaser.coursesystem.validation;

import org.springframework.stereotype.Component;
import se.chasacademy.databaser.coursesystem.models.Course;
import se.chasacademy.databaser.coursesystem.models.CourseSession;
import se.chasacademy.databaser.coursesystem.models.Room;

@Component
public class CourseValidator {
    public void validateCourseCapacity(Course course) {
        if (course.getParticipants().size() >= course.getMaxParticipants()) {
            throw new IllegalStateException(
                    "Kursen '" + course.getTitle() + "' är fullbokad! (" +
                            course.getParticipants().size() + "/" + course.getMaxParticipants() + ")"
            );
        }
    }

    public void validateRoomCapacityForCourse(Course course, Room room) {
        if (room.getCapacity() < course.getMaxParticipants()) {
            throw new IllegalArgumentException(
                    "Lokalen '" + room.getName() + "' är för liten för kursen '" +
                            course.getTitle() + "'. (Kapacitet: " + room.getCapacity() +
                            ", Kursmax: " + course.getMaxParticipants() + ")"
            );
        }
    }

    public void validateSession(CourseSession session) {
        if (session.getRoom() == null) {
            throw new IllegalArgumentException("CourseSession saknar lokal!");
        }
        if (session.getCourse() == null) {
            throw new IllegalArgumentException("CourseSession saknar kurs!");
        }

        validateRoomCapacityForCourse(session.getCourse(), session.getRoom());
    }
}
