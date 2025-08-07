package com.petI9.demo.service;

import com.petI9.demo.domain.Vacina;
import com.petI9.demo.dto.VacinaDTO;

import java.util.List;

public interface VacinaService {
    Vacina cadastrarVacina(Vacina vacina);
    List<Vacina> listarTodas();
    Vacina editar(Long id, VacinaDTO vacinaDtyo);
    void removerVacina(Long id);
}
