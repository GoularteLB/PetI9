package com.petI9.demo.application;

import com.petI9.demo.domain.Tutor;
import com.petI9.demo.domain.Pet;
import com.petI9.demo.infrastructure.InMemoryTutorRepository;
import java.util.List;
import java.util.Iterator;

public class TutorServiceImpl implements TutorService {
    private final InMemoryTutorRepository repo = new InMemoryTutorRepository();

    @Override
    public Tutor cadastrarTutor(Tutor tutor) {
        if (repo.existsByNome(tutor.getNome())) {
            throw new IllegalArgumentException("Já existe um tutor com esse nome.");
        }
        if (tutor.getPets() != null) {
            for (Pet pet : tutor.getPets()) {
                long count = tutor.getPets().stream().filter(p -> p.getNome().equalsIgnoreCase(pet.getNome())).count();
                if (count > 1) {
                    throw new IllegalArgumentException("Não pode haver dois pets com o mesmo nome para o mesmo tutor.");
                }
            }
        }
        return repo.save(tutor);
    }

    @Override
    public Tutor consultarPorId(Long id) {
        return repo.findById(id);
    }

    @Override
    public List<Tutor> consultarPorNome(String nome) {
        return repo.findByNome(nome);
    }

    @Override
    public List<Tutor> listarTodos() {
        return repo.findAll();
    }

    @Override
    public boolean removerPetDoTutor(Long tutorId, Long petId) {
        Tutor tutor = repo.findById(tutorId);
        if (tutor == null || tutor.getPets() == null) return false;
        Iterator<Pet> it = tutor.getPets().iterator();
        boolean removed = false;
        while (it.hasNext()) {
            Pet pet = it.next();
            if (pet.getId().equals(petId)) {
                it.remove();
                removed = true;
                break;
            }
        }
        if (removed) repo.save(tutor);
        return removed;
    }
}
