package me.code.webservicesrestfulapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.code.webservicesrestfulapp.models.Student;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class StudentDTO extends RepresentationModel<StudentDTO> {

    private final UUID id;
    private final String name, email;
    private final UUID school;

    public static StudentDTO from(Student student) {
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getSchool().getId()
        );
    }

}
