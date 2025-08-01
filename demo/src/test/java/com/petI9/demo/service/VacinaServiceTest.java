package com.petI9.demo.service;

import com.petI9.demo.domain.Vacina;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class VacinaServiceTest {
    @Test
    void testInterfaceMethods() {
        assertDoesNotThrow(() -> {
            VacinaService service = new VacinaService() {
                public Vacina cadastrarVacina(Vacina v) { return v; }
                public List<Vacina> listarTodas() { return List.of(); }
            };
            Vacina v = new Vacina();
            assertEquals(v, service.cadastrarVacina(v));
            assertTrue(service.listarTodas().isEmpty());
        });
    }
}
