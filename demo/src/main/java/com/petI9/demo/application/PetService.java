package com.petI9.demo.application;

import com.petI9.demo.domain.Pet;
import java.util.List;

public interface PetService {
    Pet cadastrarPet(Pet pet);
    List<Pet> listarPets();
}
