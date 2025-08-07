package com.petI9.demo.service;

import com.petI9.demo.domain.Pet;
import com.petI9.demo.dto.PetDTO;

import java.util.List;

public interface PetService {
    Pet cadastrarPet(Pet pet);
    List<Pet> listarPets();
    Pet consultarPorId(Long id);
    List<Pet> consultarPorNome(String name);
    Pet editar(Long id, PetDTO petDTO);
    void removerPet(Long id);
}
