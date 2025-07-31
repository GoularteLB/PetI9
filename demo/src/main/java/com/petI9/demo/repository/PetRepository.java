package com.petI9.demo.repository;

<<<<<<< HEAD
import com.petI9.demo.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    boolean existsByNomeIgnoreCaseAndTutorId(String nome, Long tutorId);
    List<Pet> findByNomeContainingIgnoreCase(String nome);
=======
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petI9.demo.domain.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
    boolean existsByNameIgnoreCaseAndTutorId(String name, Long tutorId);
    List<Pet> findByNameContainingIgnoreCase(String name);
>>>>>>> master
}
