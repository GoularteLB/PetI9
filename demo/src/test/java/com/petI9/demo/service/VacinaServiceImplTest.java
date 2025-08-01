package com.petI9.demo.service;

import com.petI9.demo.domain.Vacina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VacinaServiceImplTest {
    private VacinaServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new VacinaServiceImpl();
    }

    @Test
    void testCadastrarVacinaSuccess() {
        Vacina vacina = new Vacina();
        vacina.setName("Rabies");
        vacina.setDate(LocalDate.of(2023, 1, 1));
        Vacina result = service.cadastrarVacina(vacina);
        assertEquals("Rabies", result.getName());
        assertEquals(1, service.listarTodas().size());
    }

    @Test
    void testCadastrarVacinaNomeDuplicado() {
        Vacina v1 = new Vacina();
        v1.setName("Rabies");
        service.cadastrarVacina(v1);
        Vacina v2 = new Vacina();
        v2.setName("Rabies");
        assertThrows(IllegalArgumentException.class, () -> service.cadastrarVacina(v2));
    }

    @Test
    void testListarTodasVazio() {
        List<Vacina> vacinas = service.listarTodas();
        assertNotNull(vacinas);
        assertTrue(vacinas.isEmpty());
    }
}
