package com.petI9.demo.application;

import com.petI9.demo.domain.Pet;
import com.petI9.demo.domain.Tutor;
import com.petI9.demo.infrastructure.InMemoryPetRepository;
import com.petI9.demo.infrastructure.InMemoryTutorRepository;
import java.util.List;

public class PetServiceImpl implements PetService {
    private final InMemoryPetRepository petRepo = new InMemoryPetRepository();
    private final InMemoryTutorRepository tutorRepo = new InMemoryTutorRepository();

    @Override
    public Pet cadastrarPet(Pet pet) {
        if (pet.getTutor() == null || pet.getTutor().getId() == null) {
            throw new IllegalArgumentException("Tutor é obrigatório");
        }
        Tutor tutor = tutorRepo.findById(pet.getTutor().getId());
        if (tutor == null) {
            throw new IllegalArgumentException("Tutor não encontrado");
        }
        if (petRepo.existsByNomeAndTutor(pet.getNome(), tutor.getId())) {
            throw new IllegalArgumentException("Já existe um pet com esse nome para este tutor");
        }
        Pet novoPet = petRepo.save(pet);
        tutor.getPets().add(novoPet);
        tutorRepo.save(tutor);
        return novoPet;
    }

    @Override
    public List<Pet> listarPets() {
        throw new UnsupportedOperationException("Não implementado");
    }
}
