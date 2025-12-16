package se.chasacademy.databaser.coursesystem.data;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import se.chasacademy.databaser.coursesystem.models.*;
import se.chasacademy.databaser.coursesystem.repository.*;

import java.time.LocalDate;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {
    private final TeacherRepository teacherRepo;
    private final CourseRepository courseRepo;
    private final ParticipantRepository participantRepo;
    private final RoomRepository roomRepo;
    private final CourseSessionRepository sessionRepo;

    public InitData(TeacherRepository teacherRepo, CourseRepository courseRepo,
                           ParticipantRepository participantRepo, RoomRepository roomRepo,
                           CourseSessionRepository sessionRepo) {
        this.teacherRepo = teacherRepo;
        this.courseRepo = courseRepo;
        this.participantRepo = participantRepo;
        this.roomRepo = roomRepo;
        this.sessionRepo = sessionRepo;
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

        // Skapa Kurser och koppla till Lärare
        Course c1 = new Course("DataInstuderingskurs", 20);
        c1.setTeacher(t1);

        Course c2 = new Course("Försvar mot Ints", 30);
        c2.setTeacher(t1);

        courseRepo.saveAll(List.of(c1, c2));

        List<Participant> students = participantRepo.findAll();
        c1.participants().addAll(students);
        courseRepo.save(c1);

        // Skapa CourseSessions (Kurstillfällen)
        CourseSession s1 = new CourseSession();
        s1.setCourse(c1);
        s1.setRoom(r1);
        s1.setDate(LocalDate.now().plusDays(2));

        CourseSession s2 = new CourseSession();
        s2.setCourse(c1);
        s2.setRoom(r1);
        s2.setDate(LocalDate.now().plusDays(9));

        sessionRepo.saveAll(List.of(s1, s2));
    }
}
