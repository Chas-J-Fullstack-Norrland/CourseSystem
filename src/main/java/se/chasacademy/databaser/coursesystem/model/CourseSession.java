package se.chasacademy.databaser.coursesystem.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class CourseSession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

}
