package se.chasacademy.databaser.coursesystem.data;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import se.chasacademy.databaser.coursesystem.models.*;
import se.chasacademy.databaser.coursesystem.repository.*;
import se.chasacademy.databaser.coursesystem.validation.CourseValidator;

import java.time.LocalDate;
import java.util.List;

@Order(1)
@Component
public class InitData implements CommandLineRunner {
    private final TeacherRepository teacherRepo;
    private final CourseRepository courseRepo;
    private final ParticipantRepository participantRepo;
    private final RoomRepository roomRepo;
    private final CourseSessionRepository sessionRepo;
    private final CourseValidator validator;

    public InitData(TeacherRepository teacherRepo, CourseRepository courseRepo,
                           ParticipantRepository participantRepo, RoomRepository roomRepo,
                           CourseSessionRepository sessionRepo, CourseValidator validator) {
        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;
        this.participantRepo = participantRepo;
        this.roomRepo = roomRepo;
        this.sessionRepo = sessionRepo;
        this.validator = validator;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("--- Startar datainitiering ---");

        // Skapa grunddata (Lärare, Lokaler, Deltagare)
        createBasicData();

        // Koppla relationer (Kurser, Sessioner, Deltagare till kurs)
        createRelations();

        System.out.println("--- Datainitiering klar ---");
    }

    private void createBasicData() {
        // Skapa Lärare
        Teacher t1 = new Teacher("Anna", "Andersson", "anna@mymail.com");
        Teacher t2 = new Teacher("Mina", "Mick", "mina@mymail.com");
        teacherRepo.saveAll(List.of(t1, t2));

        // Skapa Lokaler
        Room r1 = new Room("Sal A", "Hus 1", 10);
        Room r2 = new Room("Stora Salen", "Hus 2", 50);
        roomRepo.saveAll(List.of(r1, r2));

        // Skapa Deltagare
        Participant p1 = new Participant("elev 1", "elev1@mymail.com");
        Participant p2 = new Participant("elev 2", "elev2@mymail.com");
        Participant p3 = new Participant("elev 3", "elev3@mymail.com");
        participantRepo.saveAll(List.of(p1, p2, p3));
    }

    private void createRelations() {
        Teacher t1 = teacherRepo.findByEmail("anna@mymail.com");
        Room r1 = roomRepo.findByName("Sal A");

        Course c1 = new Course("DataInstuderingskurs", 20);
        c1.setTeacher(t1);
        courseRepo.save(c1);

        List<Participant> students = participantRepo.findAll();

        try {
            if (students.size() > c1.getMaxParticipants()) {
                validator.validateCourseCapacity(c1);
            }

            c1.getParticipants().addAll(students);
            courseRepo.save(c1);

        } catch (Exception e) {
            System.out.println("VALIDERINGSFEL FÅNGAT: " + e.getMessage());
        }

        // --- Validera CourseSession ---
        CourseSession s1 = new CourseSession();
        s1.setCourse(c1);
        s1.setRoom(r1);
        s1.setDate(LocalDate.now().plusDays(2));

        try {
            validator.validateSession(s1);
            sessionRepo.save(s1);
        } catch (Exception e) {
            System.out.println("Kunde inte skapa pass: " + e.getMessage());
        }
    }
}
