package me.code.webservicesrestfulapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "web_services_student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name, email;

    @ManyToOne
    private School school;

    public Student(String name, String email, School school) {
        this.name = name;
        this.email = email;
        this.school = school;
    }
}
