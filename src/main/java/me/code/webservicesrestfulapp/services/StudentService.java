package me.code.webservicesrestfulapp.services;

import me.code.webservicesrestfulapp.controllers.StudentController;
import me.code.webservicesrestfulapp.dtos.StudentInput;
import me.code.webservicesrestfulapp.models.Student;
import me.code.webservicesrestfulapp.repositories.SchoolRepository;
import me.code.webservicesrestfulapp.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    public Optional<Student> create(StudentInput input) {
        var school = schoolRepository
                .findByName(input.getSchool())
                .orElse(null);

        if (school == null)
            return Optional.empty();

        var student = new Student(input.getName(), input.getEmail(), school);
        student = studentRepository.save(student);
        school.getStudents().add(student);
        schoolRepository.save(school);

        return Optional.of(student);
    }

    public Optional<Student> getById(UUID id) {
        return studentRepository.findById(id);
    }
}
