package com.petI9.demo.repository;

import com.petI9.demo.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    boolean existsByNomeIgnoreCaseAndTutorId(String nome, Long tutorId);
    List<Pet> findByNomeContainingIgnoreCase(String nome);
}
