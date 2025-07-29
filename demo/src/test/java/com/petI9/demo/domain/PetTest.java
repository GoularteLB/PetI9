package com.petI9.demo.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class PetTest {
    @Test
    void testValidarObrigatoriosComSucesso() {
        Pet pet = new Pet();
        pet.setEspecie("Cachorro");
        pet.setDataNascimento(LocalDate.of(2020, 1, 1));
        Vacina vacina = new Vacina();
        vacina.setTipo("Raiva");
        vacina.setData(LocalDate.of(2021, 1, 1));
        pet.setVacinas(List.of(vacina));
        assertDoesNotThrow(pet::validarObrigatorios);
    }

    @Test
    void testValidarObrigatoriosSemVacinas() {
        Pet pet = new Pet();
        pet.setEspecie("Gato");
        pet.setDataNascimento(LocalDate.of(2020, 1, 1));
        pet.setVacinas(null);
        Exception ex = assertThrows(IllegalArgumentException.class, pet::validarObrigatorios);
        assertEquals("Vacinas são obrigatórias", ex.getMessage());
    }

    @Test
    void testValidarObrigatoriosSemDataNascimento() {
        Pet pet = new Pet();
        pet.setEspecie("Gato");
        Vacina vacina = new Vacina();
        vacina.setTipo("Raiva");
        vacina.setData(LocalDate.of(2021, 1, 1));
        pet.setVacinas(List.of(vacina));
        Exception ex = assertThrows(IllegalArgumentException.class, pet::validarObrigatorios);
        assertEquals("Data de nascimento é obrigatória", ex.getMessage());
    }

    @Test
    void testValidarObrigatoriosSemEspecie() {
        Pet pet = new Pet();
        pet.setDataNascimento(LocalDate.of(2020, 1, 1));
        Vacina vacina = new Vacina();
        vacina.setTipo("Raiva");
        vacina.setData(LocalDate.of(2021, 1, 1));
        pet.setVacinas(List.of(vacina));
        Exception ex = assertThrows(IllegalArgumentException.class, pet::validarObrigatorios);
        assertEquals("Espécie é obrigatória", ex.getMessage());
    }
}
