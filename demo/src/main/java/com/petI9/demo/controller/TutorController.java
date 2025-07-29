package com.petI9.demo.controller;

import com.petI9.demo.domain.Tutor;
import com.petI9.demo.application.TutorService;
import com.petI9.demo.application.TutorServiceImpl;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {
    private final TutorService tutorService = new TutorServiceImpl();

    @PostMapping
    public Tutor cadastrarTutor(@RequestBody Tutor tutor) {
        return tutorService.cadastrarTutor(tutor);
    }

    @GetMapping("/{id}")
    public Tutor consultarPorId(@PathVariable Long id) {
        return tutorService.consultarPorId(id);
    }

    @GetMapping
    public List<Tutor> consultarPorNome(@RequestParam(required = false) String nome) {
        if (nome == null || nome.isEmpty()) {
            return tutorService.listarTodos();
        }
        return tutorService.consultarPorNome(nome);
    }
}
