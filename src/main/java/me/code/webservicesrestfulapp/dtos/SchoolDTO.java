package me.code.webservicesrestfulapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import me.code.webservicesrestfulapp.models.School;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class SchoolDTO extends RepresentationModel<SchoolDTO> {

    private final UUID id;
    private final String name;
    private final List<StudentDTO> students;

    public static SchoolDTO from(School school) {
        return new SchoolDTO(
                school.getId(),
                school.getName(),
                school.getStudents()
                        .stream()
                        .map(StudentDTO::from)
                        .collect(Collectors.toList())
        );
    }
}
