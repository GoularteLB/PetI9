package com.petI9.demo.service;


import com.petI9.demo.domain.Vacina;
import com.petI9.demo.dto.VacinaDTO;
import com.petI9.demo.repository.VacinaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class VacinaServiceImpl implements VacinaService {
    @Autowired
    private  VacinaRepository vacinasRepository;

    @Override
    public Vacina cadastrarVacina(Vacina vacina) {
        vacinasRepository.save(vacina);
        return vacina;
    }

    @Override
    public List<Vacina> listarTodas() {
        return new ArrayList<>(vacinasRepository.findAll());
    }

     @Override
    public Vacina editar(Long id, VacinaDTO vacina) {
        Vacina existente = vacinasRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setType(vacina.getType());
        existente.setDate(vacina.getDate());
        
        return vacinasRepository.save(existente);
    }

    @Override
    public void removerVacina(Long id) {
        vacinasRepository.deleteById(id);
    }
}
