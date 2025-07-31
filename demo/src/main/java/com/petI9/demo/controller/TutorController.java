package com.petI9.demo.controller;


import org.springframework.web.bind.annotation.*;

import com.petI9.demo.application.TutorServiceImpl;
import com.petI9.demo.domain.Tutor;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {
    private final TutorServiceImpl tutorService;

    public TutorController(TutorServiceImpl tutorService) {
        this.tutorService = tutorService;
    }
    @PostMapping
    public Tutor cadastrarTutor(@RequestBody Tutor tutor) {
        return tutorService.cadastrarTutor(tutor);
    }

    @GetMapping("/{id}")
    public Tutor consultarPorId(@PathVariable Long id) {
        return tutorService.consultarPorId(id);
    }

    @GetMapping("/search")
    public List<Tutor> consultarPorNome(@RequestParam String nome) {
        return tutorService.consultarPorNome(nome);
    }

    @GetMapping
    public List<Tutor> listarTodos() {
        return tutorService.listarTodos();
    }
}
