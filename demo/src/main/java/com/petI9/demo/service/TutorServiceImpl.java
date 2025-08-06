package com.petI9.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petI9.demo.domain.Pet;
import com.petI9.demo.domain.Tutor;
import com.petI9.demo.exceptions.RegraDeNegocioException;
import com.petI9.demo.repository.TutorRepository;

@Service
public class TutorServiceImpl implements TutorService {
    @Autowired
    private TutorRepository tutorRepository;

    @Override
    public Tutor cadastrarTutor(Tutor tutor) {
        if (tutor.getName() == null || tutor.getName().isEmpty()) {
            throw new RegraDeNegocioException("Nome do tutor é obrigatório.");
        }
        if (tutorRepository.existsByNameIgnoreCase(tutor.getName())) {
            throw new IllegalArgumentException("Já existe um tutor com esse nome.");
        }
        if (tutor.getPets() != null) {
            for (Pet pet : tutor.getPets()) {
                long count = tutor.getPets().stream().filter(p -> p.getName().equalsIgnoreCase(pet.getName())).count();
                if (count > 1) {
                    throw new RegraDeNegocioException("Não pode haver dois pets com o mesmo nome para o mesmo tutor.");
                }
            }
        }
        return tutorRepository.save(tutor);
    }

    @Override
    public Tutor consultarPorId(Long id) {
        return tutorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Tutor> consultarPorNome(String nome) {
        return tutorRepository.findByNameContainingIgnoreCase(nome);
    }

    @Override
    public List<Tutor> listarTodos() {
        return tutorRepository.findAll();
    }

    @Override
    public Tutor atualizarTutor(Long id, Tutor tutor) {
        Tutor existente = tutorRepository.findById(id).orElse(null);
        if (existente == null) return null;
        existente.setName(tutor.getName());
        existente.setNickname(tutor.getNickname());
        existente.setBirthDate(tutor.getBirthDate());
        existente.setPets(tutor.getPets());
        return tutorRepository.save(existente);
    }

    @Override
    public void removerTutor(Long id) {
        tutorRepository.deleteById(id);
    }
}
