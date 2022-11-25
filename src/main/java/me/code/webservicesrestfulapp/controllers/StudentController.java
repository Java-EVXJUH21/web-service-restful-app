package me.code.webservicesrestfulapp.controllers;

import lombok.Getter;
import lombok.Setter;
import me.code.webservicesrestfulapp.dtos.SchoolDTO;
import me.code.webservicesrestfulapp.dtos.StudentDTO;
import me.code.webservicesrestfulapp.dtos.StudentInput;
import me.code.webservicesrestfulapp.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentDTO>> info(
            @PathVariable UUID id
    ) {
        return studentService
                .getById(id)
                .map(StudentDTO::from)
                .map(this::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/")
    public ResponseEntity<EntityModel<StudentDTO>> create(
            @RequestBody StudentInput input
    ) {
        return studentService
                .create(input)
                .map(StudentDTO::from)
                .map(this::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    public EntityModel<StudentDTO> toModel(StudentDTO student) {
        var method = WebMvcLinkBuilder
                .methodOn(SchoolController.class)
                .info(student.getSchool());

        var link = WebMvcLinkBuilder
                .linkTo(method)
                .withRel("school");

        student.add(link);

        return EntityModel.of(student);
    }
}