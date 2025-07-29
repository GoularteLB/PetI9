package com.petI9.demo.application;

import com.petI9.demo.domain.Tutor;
import com.petI9.demo.domain.Pet;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TutorServiceImplTest {
    @Test
    void testCadastrarTutorComSucesso() {
        TutorServiceImpl service = new TutorServiceImpl();
        Tutor tutor = new Tutor();
        tutor.setNome("João");
        tutor.setApelido("Joca");
        tutor.setDataNascimento(LocalDate.of(1980, 5, 10));
        tutor.setPets(List.of());
        Tutor salvo = service.cadastrarTutor(tutor);
        assertNotNull(salvo.getId());
        assertEquals("João", salvo.getNome());
    }

    @Test
    void testNaoPermiteTutorComMesmoNome() {
        TutorServiceImpl service = new TutorServiceImpl();
        Tutor t1 = new Tutor();
        t1.setNome("Maria");
        t1.setApelido("Ma");
        t1.setDataNascimento(LocalDate.of(1990, 1, 1));
        t1.setPets(List.of());
        service.cadastrarTutor(t1);
        Tutor t2 = new Tutor();
        t2.setNome("Maria");
        t2.setApelido("Mari");
        t2.setDataNascimento(LocalDate.of(1992, 2, 2));
        t2.setPets(List.of());
        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.cadastrarTutor(t2));
        assertEquals("Já existe um tutor com esse nome.", ex.getMessage());
    }

    @Test
    void testNaoPermiteDoisPetsComMesmoNomeParaTutor() {
        TutorServiceImpl service = new TutorServiceImpl();
        Tutor tutor = new Tutor();
        tutor.setNome("Carlos");
        tutor.setApelido("Carlinhos");
        tutor.setDataNascimento(LocalDate.of(1985, 3, 3));
        Pet pet1 = new Pet();
        pet1.setNome("Rex");
        Pet pet2 = new Pet();
        pet2.setNome("Rex");
        tutor.setPets(List.of(pet1, pet2));
        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.cadastrarTutor(tutor));
        assertEquals("Não pode haver dois pets com o mesmo nome para o mesmo tutor.", ex.getMessage());
    }
}
