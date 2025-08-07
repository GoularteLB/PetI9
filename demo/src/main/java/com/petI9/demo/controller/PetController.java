package com.petI9.demo.controller;

import com.petI9.demo.domain.Pet;
import com.petI9.demo.service.*;

import jakarta.validation.Valid;

import com.petI9.demo.dto.PetDTO;
import com.petI9.demo.mapper.PetMapper;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {
    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PostMapping
    public PetDTO cadastrarPet(@RequestBody @Valid PetDTO petDTO) {

        return PetMapper.toDTO(petService.cadastrarPet(PetMapper.toEntity(petDTO)));
    }

    @GetMapping("/{id}")
    public PetDTO consultarPorId(@PathVariable Long id) {
        return PetMapper.toDTO(petService.consultarPorId(id));
    }

    @GetMapping("/search")
    public List<PetDTO> consultarPorNome(@RequestParam String name) {
        return petService.consultarPorNome(name).stream().map(PetMapper::toDTO).toList();
    }

    @GetMapping
    public List<PetDTO> listarTodos() {
        return petService.listarPets().stream().map(PetMapper::toDTO).toList();
    }

    public static class NameDTO {
        public String name;
    }

    @PutMapping("/{id}")
    public PetDTO Update(@PathVariable Long id, @RequestBody @Valid PetDTO dto) {
        Pet pet = petService.editar(id, dto);
        return PetMapper.toDTO(pet);
    }

    @DeleteMapping("/{id}")
    public void removerPet(@PathVariable Long id) {
        petService.removerPet(id);
    }
}
