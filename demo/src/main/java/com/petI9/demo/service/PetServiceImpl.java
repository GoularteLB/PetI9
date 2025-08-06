package com.petI9.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.petI9.demo.domain.Pet;
import com.petI9.demo.domain.Tutor;
import com.petI9.demo.exceptions.RegistroNaoEncontradoException;
import com.petI9.demo.exceptions.RegraDeNegocioException;
import com.petI9.demo.repository.PetRepository;
import com.petI9.demo.repository.TutorRepository;

@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepo;
    private final TutorRepository tutorRepo;

    public PetServiceImpl(PetRepository petRepo, TutorRepository tutorRepo) {
        this.petRepo = petRepo;
        this.tutorRepo = tutorRepo;
    }

    @Override
    public Pet cadastrarPet(Pet pet) {
        if (pet.getTutor() == null || pet.getTutor().getId() == null) {
            throw new RegraDeNegocioException("Tutor é obrigatório");
        }
        Tutor tutor = tutorRepo.findById(pet.getTutor().getId()).orElse(null);
        if (tutor == null) {
            throw new RegistroNaoEncontradoException("Tutor não encontrado");
        }
        if (petRepo.existsByNameIgnoreCaseAndTutorId(pet.getName(), tutor.getId())) {
    throw new RegraDeNegocioException("Já existe um pet com esse nome para este tutor");
}
        pet.setTutor(tutor);
        Pet novoPet = petRepo.save(pet);
        tutor.getPets().add(novoPet);
        tutorRepo.save(tutor);
        return novoPet;
    }

    @Override
    public List<Pet> listarPets() {
        return petRepo.findAll();
    }

    @Override
    public Pet editarNome(Long petId, String newName) {
        Pet pet = petRepo.findById(petId).orElseThrow(() -> new IllegalArgumentException("Pet não encontrado"));
        if (petRepo.existsByNameIgnoreCaseAndTutorId(newName, pet.getTutor().getId())) {
    throw new IllegalArgumentException("Já existe um pet com esse nome para este tutor");
}
        pet.setName(newName);
        return petRepo.save(pet);
    }

    @Override
    public List<Pet> consultarPorNome(String name) {
        return petRepo.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Pet consultarPorId(Long id) {
        return petRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Pet não encontrado"));
    }

    @Override
    public void removerPet(Long id) {
        if (!petRepo.existsById(id)) {
            throw new IllegalArgumentException("Pet não encontrado");
        }
        petRepo.deleteById(id);
    }
}
