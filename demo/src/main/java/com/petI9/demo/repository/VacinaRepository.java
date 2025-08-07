package com.petI9.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.petI9.demo.domain.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Long> {
}
