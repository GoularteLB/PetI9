package com.petI9.demo.controller;


import org.springframework.web.bind.annotation.*;

import com.petI9.demo.application.TutorServiceImpl;
import com.petI9.demo.dto.TutorDTO;
import com.petI9.demo.mapper.TutorMapper;

import java.util.List;

@RestController
@RequestMapping("/tutores")
public class TutorController {
    private final TutorServiceImpl tutorService;

    public TutorController(TutorServiceImpl tutorService) {
        this.tutorService = tutorService;
    }
    @PostMapping
    public TutorDTO cadastrarTutor(@RequestBody TutorDTO tutorDTO) {
        return TutorMapper.toDTO(tutorService.cadastrarTutor(TutorMapper.toEntity(tutorDTO)));
    }

    @GetMapping("/{id}")
    public TutorDTO consultarPorId(@PathVariable Long id) {
        return TutorMapper.toDTO(tutorService.consultarPorId(id));
    }

    @GetMapping("/search")
    public List<TutorDTO> consultarPorNome(@RequestParam String nome) {
        return tutorService.consultarPorNome(nome).stream().map(TutorMapper::toDTO).toList();
    }

    @GetMapping
    public List<TutorDTO> listarTodos() {
        return tutorService.listarTodos().stream().map(TutorMapper::toDTO).toList();
    }

    @PutMapping("/{id}")
    public TutorDTO atualizarTutor(@PathVariable Long id, @RequestBody TutorDTO tutorDTO) {
        var updated = tutorService.atualizarTutor(id, TutorMapper.toEntity(tutorDTO));
        if (updated == null) {
            return null;
        }
        return TutorMapper.toDTO(updated);
    }

    @DeleteMapping("/{id}")
    public void removerTutor(@PathVariable Long id) {
        tutorService.removerTutor(id);
    }
}
