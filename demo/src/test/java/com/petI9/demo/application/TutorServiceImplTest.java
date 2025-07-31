package com.petI9.demo.application;

import com.petI9.demo.domain.Tutor;
import com.petI9.demo.domain.Pet;
import com.petI9.demo.repository.TutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TutorServiceImplTest {

    @Mock
    TutorRepository tutorRepository;

    @InjectMocks
    TutorServiceImpl service;

    @Test
    void testCadastrarTutorComSucesso() {
        Tutor tutor = new Tutor();
        tutor.setName("João");
        tutor.setNickname("Joca");
        tutor.setBirthDate(LocalDate.of(1980, 5, 10));
        tutor.setPets(List.of());

        when(tutorRepository.existsByNameIgnoreCase("João")).thenReturn(false);
        when(tutorRepository.save(any(Tutor.class))).thenAnswer(invocation -> {
            Tutor t = invocation.getArgument(0);
            t.setId(1L);
            return t;
        });

        Tutor salvo = service.cadastrarTutor(tutor);
        assertNotNull(salvo.getId());
        assertEquals("João", salvo.getName());
    }

    @Test
    void testNaoPermiteTutorComMesmoNome() {
        Tutor t1 = new Tutor();
        t1.setName("Maria");
        t1.setNickname("Ma");
        t1.setBirthDate(LocalDate.of(1990, 1, 1));
        t1.setPets(List.of());

        when(tutorRepository.existsByNameIgnoreCase("Maria")).thenReturn(false).thenReturn(true);
        when(tutorRepository.save(any(Tutor.class))).thenAnswer(invocation -> {
            Tutor t = invocation.getArgument(0);
            t.setId(2L);
            return t;
        });

        service.cadastrarTutor(t1);

        Tutor t2 = new Tutor();
        t2.setName("Maria");
        t2.setNickname("Mari");
        t2.setBirthDate(LocalDate.of(1992, 2, 2));
        t2.setPets(List.of());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.cadastrarTutor(t2));
        assertEquals("Já existe um tutor com esse nome.", ex.getMessage());
    }

    @Test
    void testNaoPermiteDoisPetsComMesmoNomeParaTutor() {
        Tutor tutor = new Tutor();
        tutor.setName("Carlos");
        tutor.setNickname("Carlinhos");
        tutor.setBirthDate(LocalDate.of(1985, 3, 3));
        Pet pet1 = new Pet();
        pet1.setName("Rex");
        Pet pet2 = new Pet();
        pet2.setName("Rex");
        tutor.setPets(List.of(pet1, pet2));

        when(tutorRepository.existsByNameIgnoreCase("Carlos")).thenReturn(false);

        Exception ex = assertThrows(IllegalArgumentException.class, () -> service.cadastrarTutor(tutor));
        assertEquals("Não pode haver dois pets com o mesmo nome para o mesmo tutor.", ex.getMessage());
    }}