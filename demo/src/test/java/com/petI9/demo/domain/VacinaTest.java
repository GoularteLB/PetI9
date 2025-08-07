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
        assertEquals(Long.valueOf(1L), vacina.getId());
        assertEquals("Rabies", vacina.getName());
        assertEquals(LocalDate.of(2023, 1, 1), vacina.getDate());

    }

    @Test
    void testVacinaEqualsAndHashCode() {
        Vacina v1 = new Vacina();
        v1.setId(Long.valueOf(1L));
        v1.setName("Rabies");
        Vacina v2 = new Vacina();
        v2.setId(Long.valueOf(2L));
        v2.setName("Rabies");
        assertEquals(v1, v2, "Vacinas with same name should be equal (equals/hashCode only uses name/type)");
        assertEquals(v1.hashCode(), v2.hashCode());
        assertNotSame(v1, v2);
    }

    @Test
    void testVacinaToString() {
        Vacina vacina = new Vacina();
        vacina.setId(Long.valueOf(1L));
        vacina.setName("Rabies");
        String str = vacina.toString();
        assertTrue(str.contains("Rabies"));
    }

    @Test
    void testVacinaSettersAndGettersNulls() {
        Vacina vacina = new Vacina();
        vacina.setId(null);
        vacina.setName(null);
        vacina.setDate(null);
        assertNull(vacina.getId());
        assertNull(vacina.getName());
        assertNull(vacina.getDate());
    }

    @Test
    void testVacinaEqualsDifferentNames() {
        Vacina v1 = new Vacina();
        v1.setName("Rabies");
        Vacina v2 = new Vacina();
        v2.setName("Lepto");
        assertNotEquals(v1, v2);
    }

    @Test
    void testVacinaHashCodeConsistency() {
        Vacina v1 = new Vacina();
        v1.setName("Rabies");
        int hash1 = v1.hashCode();
        int hash2 = v1.hashCode();
        assertEquals(hash1, hash2);
    }

    @Test
    void testVacinaToStringNullFields() {
        Vacina vacina = new Vacina();
        String str = vacina.toString();
        assertTrue(str.contains("Vacina{"));
    }
}
