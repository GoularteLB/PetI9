package com.petI9.demo.application;

import com.petI9.demo.domain.Pet;
import com.petI9.demo.domain.Tutor;
import com.petI9.demo.repository.PetRepository;
import com.petI9.demo.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
        if (petRepo.existsByNomeIgnoreCaseAndTutorId(pet.getNome(), tutor.getId())) {
            throw new IllegalArgumentException("Já existe um pet com esse nome para este tutor");
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
    // ...outros métodos...
}
