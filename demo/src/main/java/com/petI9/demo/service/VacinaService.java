package com.petI9.demo.service;

import com.petI9.demo.domain.Vacina;
import java.util.List;

public interface VacinaService {
    Vacina cadastrarVacina(Vacina vacina);
    List<Vacina> listarTodas();
}
