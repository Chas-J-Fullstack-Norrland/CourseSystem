package se.chasacademy.databaser.coursesystem.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

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
    @NotNull @Column(nullable = false, unique = true)
    private String email;

    public Teacher(String firstname,String lastname, String email){
        this.firstName = firstname;
        this.lastName=lastname;
        this.email = email;
    }

    public Teacher(String firstname,String lastname, String email,List<Course> courselist){
        this.firstName = firstname;
        this.lastName=lastname;
        this.email = email;
        this.courses = courselist;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
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



}
