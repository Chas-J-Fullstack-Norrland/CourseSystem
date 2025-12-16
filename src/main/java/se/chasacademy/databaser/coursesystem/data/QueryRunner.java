package se.chasacademy.databaser.coursesystem.data;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import se.chasacademy.databaser.coursesystem.models.*;
import se.chasacademy.databaser.coursesystem.repository.*;

import java.time.LocalDate;
import java.util.List;

@Component
@Order(2)
public class QueryRunner implements CommandLineRunner {

    private final CourseRepository courseRepo;
    private final TeacherRepository teacherRepo;
    private final RoomRepository roomRepo;
    private final CourseSessionRepository sessionRepo;

    public QueryRunner(CourseRepository courseRepo, TeacherRepository teacherRepo,
                       RoomRepository roomRepo, CourseSessionRepository sessionRepo) {
        this.courseRepo = courseRepo;
        this.teacherRepo = teacherRepo;
        this.roomRepo = roomRepo;
        this.sessionRepo = sessionRepo;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("\n========================================");
        System.out.println("   STARTAR QUERY RUNNER (Order 2)");
        System.out.println("========================================");

        // Hämta alla kurser med sina lärare
        runQuery1_AllCoursesWithTeachers();

        // Hämta alla deltagare för en viss kurs
        runQuery2_ParticipantsForCourse("DataInstuderingskurs");

        // Hämta alla kommande kurstillfällen
        runQuery3_UpcomingSessions();

        // Hämta alla kurstillfällen för en viss lokal
        runQuery4_SessionsInRoom("Sal A");

        // Hämta alla lokaler där capacity > X
        runQuery5_LargeRooms(20);

        // Hitta lärare som har fler än N kurser
        runQuery6_BusyTeachers(0); // Söker lärare med mer än 0 kurser (dvs 1 eller fler)

        // Kontrollera om en kurs är fullbokad
        runQuery7_CheckIfFull("DataInstuderingskurs");

        System.out.println("\n========================================");
        System.out.println("   QUERY RUNNER KLAR");
        System.out.println("========================================");
    }

    private void runQuery1_AllCoursesWithTeachers() {
        System.out.println("\n--- 1. Alla kurser och lärare ---");
        List<Course> courses = courseRepo.findAll();
        for (Course c : courses) {
            System.out.println("Kurs: " + c.getTitle() +
                    " | Lärare: " + c.getTeacher().getFirstName() + " " + c.getTeacher().getLastName());
        }
    }

    private void runQuery2_ParticipantsForCourse(String title) {
        System.out.println("\n--- 2. Deltagare i kursen: " + title + " ---");
        Course course = courseRepo.findByTitle(title);
        if (course != null) {
            for (Participant p : course.getParticipants()) {
                System.out.println("- " + p.getFullName() + " (" + p.getEmail() + ")");
            }
        } else {
            System.out.println("Ingen kurs hittades med namnet " + title);
        }
    }

    private void runQuery3_UpcomingSessions() {
        System.out.println("\n--- 3. Kommande kurstillfällen (efter idag) ---");
        List<CourseSession> futureSessions = sessionRepo.findByDateAfter(LocalDate.now());

        if (futureSessions.isEmpty()) System.out.println("Inga kommande pass.");

        for (CourseSession s : futureSessions) {
            System.out.println("Datum: " + s.getDate() +
                    " | Kurs: " + s.getCourse().getTitle() +
                    " | Rum: " + s.getRoom().getName());
        }
    }

    private void runQuery4_SessionsInRoom(String roomName) {
        System.out.println("\n--- 4. Pass i lokalen: " + roomName + " ---");
        List<CourseSession> sessions = sessionRepo.findByRoom_Name(roomName);
        for (CourseSession s : sessions) {
            System.out.println("Datum: " + s.getDate() + " - " + s.getCourse().getTitle());
        }
    }

    private void runQuery5_LargeRooms(int minCapacity) {
        System.out.println("\n--- 5. Rum med kapacitet större än " + minCapacity + " ---");
        List<Room> largeRooms = roomRepo.findByCapacityGreaterThan(minCapacity);
        for (Room r : largeRooms) {
            System.out.println("Rum: " + r.getName() + " (Kapacitet: " + r.getCapacity() + ")");
        }
    }

    private void runQuery6_BusyTeachers(int minCourses) {
        System.out.println("\n--- 6. Lärare med fler än " + minCourses + " kurser ---");
        List<Teacher> busyTeachers = teacherRepo.findTeachersWithMoreThanNCourses(minCourses);
        for (Teacher t : busyTeachers) {
            System.out.println("Lärare: " + t.getFirstName() + " (Antal kurser: " + t.getCourses().size() + ")");
        }
    }

    private void runQuery7_CheckIfFull(String title) {
        System.out.println("\n--- 7. Är kursen fullbokad? ---");
        Course course = courseRepo.findByTitle(title);
        if (course != null) {
            int current = course.getParticipants().size();
            int max = course.getMaxParticipants();
            boolean isFull = current >= max;

            System.out.println("Kurs: " + title);
            System.out.println("Anmälda: " + current + "/" + max);
            System.out.println("Fullbokad? " + (isFull ? "JA" : "NEJ"));
        }
    }
}