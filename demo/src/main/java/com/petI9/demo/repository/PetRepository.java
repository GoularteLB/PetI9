package com.petI9.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petI9.demo.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    boolean existsByNameIgnoreCaseAndTutorId(String name, Long tutorId);
    List<Pet> findByNameContainingIgnoreCase(String name);
}
