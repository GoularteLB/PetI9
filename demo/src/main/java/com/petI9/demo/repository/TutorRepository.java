package com.petI9.demo.repository;

import com.petI9.demo.domain.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    boolean existsByNameIgnoreCase(String name);
    List<Tutor> findByNameContainingIgnoreCase(String name);
}
