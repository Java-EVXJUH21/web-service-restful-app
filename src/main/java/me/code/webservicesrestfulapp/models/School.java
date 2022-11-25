package me.code.webservicesrestfulapp.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "web_services_school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @OneToMany
    private List<Student> students;

    public School(String name) {
        this.name = name;
        this.students = Collections.emptyList();
    }
}
