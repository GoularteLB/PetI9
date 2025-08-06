package com.petI9.demo.service;

import com.petI9.demo.domain.Pet;
import java.util.List;

public interface PetService {
    Pet cadastrarPet(Pet pet);
    List<Pet> listarPets();
    Pet consultarPorId(Long id);
    List<Pet> consultarPorNome(String name);
    Pet editarNome(Long id, String newName);
    void removerPet(Long id);
}
