package me.code.webservicesrestfulapp.services;

import me.code.webservicesrestfulapp.dtos.SchoolInput;
import me.code.webservicesrestfulapp.models.School;
import me.code.webservicesrestfulapp.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public Optional<School> create(SchoolInput input) {
        var school = new School(input.getName());
        return Optional.of(schoolRepository.save(school));
    }

    public Optional<School> getById(UUID id) {
        return schoolRepository.findById(id);
    }
}
