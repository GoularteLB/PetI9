package com.petI9.demo.controller;

import com.petI9.demo.domain.Vacina;
import com.petI9.demo.dto.VacinaDTO;
import com.petI9.demo.service.*;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {
    private final VacinaService vacinaService;

    public VacinaController(VacinaService vacinaService) {
        this.vacinaService = vacinaService;
    }

    @PostMapping
    public ResponseEntity<Vacina> cadastrarVacina(@RequestBody Vacina vacina) {
        try {
            return ResponseEntity.ok(vacinaService.cadastrarVacina(vacina));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<Vacina>> listarTodas() {
        return ResponseEntity.ok(vacinaService.listarTodas());
    }

    @PutMapping("/{id}")
    public VacinaDTO Update(@PathVariable Long id, @RequestBody @Valid VacinaDTO vacinadto) {
        Vacina vacina = vacinaService.editar(id, vacinadto);
        return new VacinaDTO(vacina.getType(), vacina.getDate());
    }

    @DeleteMapping("/{id}")
    public void removerPet(@PathVariable Long id) {
        vacinaService.removerVacina(id);
    }


}
