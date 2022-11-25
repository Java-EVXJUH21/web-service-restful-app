package me.code.webservicesrestfulapp.controllers;

import lombok.Getter;
import me.code.webservicesrestfulapp.dtos.SchoolDTO;
import me.code.webservicesrestfulapp.dtos.SchoolInput;
import me.code.webservicesrestfulapp.dtos.StudentDTO;
import me.code.webservicesrestfulapp.services.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SchoolDTO>> info(
            @PathVariable UUID id
    ) {
        return schoolService
                .getById(id)
                .map(SchoolDTO::from)
                .map(this::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/")
    public ResponseEntity<EntityModel<SchoolDTO>> create(
            @RequestBody SchoolInput input
    ) {
        return schoolService
                .create(input)
                .map(SchoolDTO::from)
                .map(this::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    public EntityModel<SchoolDTO> toModel(SchoolDTO school) {
        for (var student : school.getStudents()) {
            var method = WebMvcLinkBuilder
                    .methodOn(StudentController.class)
                    .info(student.getId());

            var link = WebMvcLinkBuilder
                    .linkTo(method)
                    .withRel("school");

            school.add(link);
        }

        return EntityModel.of(school);
    }
}
