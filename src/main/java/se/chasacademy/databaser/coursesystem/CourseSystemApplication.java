package se.chasacademy.databaser.coursesystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.chasacademy.databaser.coursesystem.models.CourseSession;
import se.chasacademy.databaser.coursesystem.repository.*;

@SpringBootApplication
public class CourseSystemApplication implements CommandLineRunner {
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final ParticipantRepository participantRepository;
    private final RoomRepository roomRepository;
    private final CourseSessionRepository sessionRepository;


    public CourseSystemApplication(
            TeacherRepository teacherRepository,
            CourseRepository courseRepository,
            ParticipantRepository participantRepository,
            RoomRepository roomRepository,
            CourseSessionRepository sessionRepository
    ) {

            this.teacherRepository =teacherRepository;
            this.courseRepository =courseRepository;
            this.participantRepository =participantRepository;
            this.roomRepository =roomRepository;
            this.sessionRepository =sessionRepository;
}

	public static void main(String[] args) {SpringApplication.run(CourseSystemApplication.class,args);}

	@Override
	public void run(String... args) {

	}
}
