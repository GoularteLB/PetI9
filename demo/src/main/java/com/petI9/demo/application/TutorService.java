package com.petI9.demo.application;

import com.petI9.demo.domain.Tutor;
import java.util.List;

public interface TutorService {
    Tutor cadastrarTutor(Tutor tutor);
    Tutor consultarPorId(Long id);
    List<Tutor> consultarPorNome(String name);
    List<Tutor> listarTodos();
    Tutor atualizarTutor(Long id, Tutor tutor);
    void removerTutor(Long id);
}
