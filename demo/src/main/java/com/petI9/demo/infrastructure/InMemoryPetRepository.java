package com.petI9.demo.infrastructure;

import com.petI9.demo.domain.Pet;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryPetRepository {
    private final Map<Long, Pet> pets = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Pet save(Pet pet) {
        if (pet.getId() == null) {
            pet.setId(idGenerator.getAndIncrement());
        }
        pets.put(pet.getId(), pet);
        return pet;
    }

    public Pet findById(Long id) {
        return pets.get(id);
    }

    public List<Pet> findByNome(String nome) {
        List<Pet> result = new ArrayList<>();
        for (Pet p : pets.values()) {
            if (p.getNome().toLowerCase().contains(nome.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    public void deleteById(Long id) {
        pets.remove(id);
    }

    public boolean existsByNomeAndTutor(String nome, Long tutorId) {
        return pets.values().stream().anyMatch(p -> p.getNome().equalsIgnoreCase(nome) && p.getTutor() != null && p.getTutor().getId().equals(tutorId));
    }
}
