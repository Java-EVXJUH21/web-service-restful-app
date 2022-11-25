package me.code.webservicesrestfulapp.repositories;

import me.code.webservicesrestfulapp.models.School;
import me.code.webservicesrestfulapp.models.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    @Override
    @Cacheable("students")
    Optional<Student> findById(UUID id);

    @Cacheable("students")
    Optional<Student> findByName(String name);

    @Override
    @CacheEvict(value = "students", key = "#result.id")
    <S extends Student> S save(S s);

    @Override
    @CacheEvict(value = "students", key = "#student.id")
    void delete(Student student);

}
