package com.petI9.demo.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class VacinaTest {
    @Test
    void testVacinaCreationAndFields() {
        Vacina vacina = new Vacina();
        vacina.setId(Long.valueOf(1L));
        vacina.setName("Rabies");
        vacina.setDate(LocalDate.of(2023, 1, 1));
        vacina.setManufacturer("Pfizer");
        assertEquals(Long.valueOf(1L), vacina.getId());
        assertEquals("Rabies", vacina.getName());
        assertEquals(LocalDate.of(2023, 1, 1), vacina.getDate());
        assertEquals("Pfizer", vacina.getManufacturer());
    }

    @Test
    void testVacinaEqualsAndHashCode() {
        Vacina v1 = new Vacina();
        v1.setId(Long.valueOf(1L));
        Vacina v2 = new Vacina();
        v2.setId(Long.valueOf(1L));
        assertEquals(v1, v2);
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    void testVacinaToString() {
        Vacina vacina = new Vacina();
        vacina.setId(Long.valueOf(1L));
        vacina.setName("Rabies");
        String str = vacina.toString();
        assertTrue(str.contains("Rabies"));
    }
}
