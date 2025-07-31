package com.petI9.demo.application;

<<<<<<< HEAD
=======
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

>>>>>>> master
import com.petI9.demo.domain.Pet;
import com.petI9.demo.domain.Tutor;
import com.petI9.demo.repository.PetRepository;
import com.petI9.demo.repository.TutorRepository;
<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
=======
>>>>>>> master

@Service
public class PetServiceImpl implements PetService {
    @Autowired
    private PetRepository petRepo;
    @Autowired
    private TutorRepository tutorRepo;

    @Override
    public Pet cadastrarPet(Pet pet) {
        if (pet.getTutor() == null || pet.getTutor().getId() == null) {
            throw new IllegalArgumentException("Tutor é obrigatório");
        }
        Tutor tutor = tutorRepo.findById(pet.getTutor().getId()).orElse(null);
        if (tutor == null) {
            throw new IllegalArgumentException("Tutor não encontrado");
        }
<<<<<<< HEAD
        if (petRepo.existsByNomeIgnoreCaseAndTutorId(pet.getName(), tutor.getId())) {
            throw new IllegalArgumentException("Já existe um pet com esse nome para este tutor");
        }
=======
        if (petRepo.existsByNameIgnoreCaseAndTutorId(pet.getName(), tutor.getId())) {
    throw new IllegalArgumentException("Já existe um pet com esse nome para este tutor");
}
>>>>>>> master
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
<<<<<<< HEAD
    // ...outros métodos...

    @Override
    public Pet editarNome(Long petId, String novoNome) {
        Pet pet = petRepo.findById(petId).orElseThrow(() -> new IllegalArgumentException("Pet não encontrado"));
        if (petRepo.existsByNomeIgnoreCaseAndTutorId(novoNome, pet.getTutor().getId())) {
            throw new IllegalArgumentException("Já existe um pet com esse nome para este tutor");
        }
        pet.setName(novoNome);
=======

    @Override
    public Pet editarNome(Long petId, String newName) {
        Pet pet = petRepo.findById(petId).orElseThrow(() -> new IllegalArgumentException("Pet não encontrado"));
        if (petRepo.existsByNameIgnoreCaseAndTutorId(newName, pet.getTutor().getId())) {
    throw new IllegalArgumentException("Já existe um pet com esse nome para este tutor");
}
        pet.setName(newName);
>>>>>>> master
        return petRepo.save(pet);
    }

    @Override
<<<<<<< HEAD
    public List<Pet> consultarPorNome(String nome) {
        return petRepo.findByNomeContainingIgnoreCase(nome);
=======
    public List<Pet> consultarPorNome(String name) {
        return petRepo.findByNameContainingIgnoreCase(name);
>>>>>>> master
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
