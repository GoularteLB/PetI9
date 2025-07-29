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

    @GetMapping("/search")
    public List<Pet> consultarPorNome(@RequestParam String name) {
        return petService.consultarPorNome(name);
    }

    @GetMapping
    public List<Pet> listarTodos() {
        return petService.listarPets();
    }

    @PutMapping("/{id}/name")
    public Pet editarNome(@PathVariable Long id, @RequestBody String newName) {
        return petService.editarNome(id, newName);
    }

    @DeleteMapping("/{id}")
    public void removerPet(@PathVariable Long id) {
        petService.removerPet(id);
    }
}
