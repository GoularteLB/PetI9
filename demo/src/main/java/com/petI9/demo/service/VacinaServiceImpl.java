package com.petI9.demo.service;

import com.petI9.demo.domain.Vacina;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacinaServiceImpl implements VacinaService {
    private final List<Vacina> vacinas = new ArrayList<>();

    @Override
    public Vacina cadastrarVacina(Vacina vacina) {
        for (Vacina v : vacinas) {
            if (v.getName().equalsIgnoreCase(vacina.getName())) {
                throw new IllegalArgumentException("Já existe uma vacina com esse nome.");
            }
        }
        vacinas.add(vacina);
        return vacina;
    }

    @Override
    public List<Vacina> listarTodas() {
        return new ArrayList<>(vacinas);
    }
}
