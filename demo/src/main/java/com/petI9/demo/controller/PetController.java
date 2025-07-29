package com.petI9.demo.controller;

import com.petI9.demo.domain.Pet;
import com.petI9.demo.application.PetService;
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
    public Pet cadastrarPet(@RequestBody Pet pet) {
        return petService.cadastrarPet(pet);
    }

    @GetMapping("/{id}")
    public Pet consultarPorId(@PathVariable Long id) {
        return petService.consultarPorId(id);
    }

    @GetMapping
    public List<Pet> consultarPorNome(@RequestParam(required = false) String nome) {
        if (nome == null || nome.isEmpty()) {
            return petService.listarPets();
        }
        return petService.consultarPorNome(nome);
    }

    @PutMapping("/{id}/nome")
    public Pet editarNome(@PathVariable Long id, @RequestBody String novoNome) {
        return petService.editarNome(id, novoNome);
    }

    @DeleteMapping("/{id}")
    public void removerPet(@PathVariable Long id) {
        petService.removerPet(id);
    }
}
