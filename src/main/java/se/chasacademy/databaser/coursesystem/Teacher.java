package se.chasacademy.databaser.coursesystem;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.NotFound;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToMany (mappedBy = "teacher", cascade = CascadeType.ALL) private List<Course> courses = new ArrayList<>();
    @NotNull @Size(min = 2)
    private String firstName;
    @NotNull
    @Size(min = 2)
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.course = courses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @NotNull @Column(nullable = false, unique = true)

    private String email;
    private protected Teacher(){

    }
}
