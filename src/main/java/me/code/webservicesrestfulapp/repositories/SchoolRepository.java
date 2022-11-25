package me.code.webservicesrestfulapp.repositories;

import me.code.webservicesrestfulapp.models.School;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SchoolRepository extends JpaRepository<School, UUID> {

    @Override
    @Cacheable("schools")
    Optional<School> findById(UUID id);

    @Cacheable("schools")
    Optional<School> findByName(String name);

    @Override
    @CacheEvict(value = "schools", key = "#result.id")
    <S extends School> S save(S s);

    @Override
    @CacheEvict(value = "schools", key = "#school.id")
    void delete(School school);
}
