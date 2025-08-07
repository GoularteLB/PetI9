package com.petI9.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petI9.demo.service.*;
import com.petI9.demo.domain.Tutor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TutorController.class)
class TutorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    TutorService tutorService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCadastrarTutor() throws Exception {
        Tutor tutor = new Tutor();
        tutor.setId(1L);
        tutor.setName("João");
        tutor.setNickname("Joca");
        tutor.setBirthDate(LocalDate.of(1980, 5, 10));

        Mockito.when(tutorService.cadastrarTutor(any(Tutor.class))).thenReturn(tutor);

        String json = "{" +
                "\"id\":1," +
                "\"name\":\"João\"," +
                "\"nickname\":\"Joca\"," +
                "\"birthDate\":\"1980-05-10\"}";

        mockMvc.perform(post("/tutores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("João"));
    }

    @Test
    void testConsultarPorId() throws Exception {
        Tutor tutor = new Tutor();
        tutor.setId(2L);
        tutor.setName("Maria");
        tutor.setNickname("Mari");
        tutor.setBirthDate(LocalDate.of(1990, 1, 1));

        Mockito.when(tutorService.consultarPorId(2L)).thenReturn(tutor);

        mockMvc.perform(get("/tutores/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Maria"));
    }

    @Test
    void testConsultarPorNome() throws Exception {
        Tutor tutor = new Tutor();
        tutor.setId(3L);
        tutor.setName("Carlos");
        tutor.setNickname("Carlinhos");
        tutor.setBirthDate(LocalDate.of(1995, 2, 2));

        Mockito.when(tutorService.consultarPorNome("Carlos")).thenReturn(List.of(tutor));

        mockMvc.perform(get("/tutores/search?nome=Carlos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Carlos"));
    }

    @Test
    void testListarTodos() throws Exception {
        Tutor t1 = new Tutor();
        t1.setId(1L);
        t1.setName("João");
        t1.setNickname("Joca");
        t1.setBirthDate(LocalDate.of(1980, 5, 10));
        Tutor t2 = new Tutor();
        t2.setId(2L);
        t2.setName("Maria");
        t2.setNickname("Mari");
        t2.setBirthDate(LocalDate.of(1990, 1, 1));

        Mockito.when(tutorService.listarTodos()).thenReturn(List.of(t1, t2));

        mockMvc.perform(get("/tutores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("João"))
                .andExpect(jsonPath("$[1].name").value("Maria"));
    }

    @Test
    void testAtualizarTutor() throws Exception {
        Tutor tutor = new Tutor();
        tutor.setId(1L);
        tutor.setName("João Atualizado");
        tutor.setNickname("Joca");
        tutor.setBirthDate(LocalDate.of(1980, 5, 10));

        Mockito.when(tutorService.atualizarTutor(Mockito.eq(1L), any(Tutor.class))).thenReturn(tutor);

        String json = "{" +
                "\"id\":1," +
                "\"name\":\"João Atualizado\"," +
                "\"nickname\":\"Joca\"," +
                "\"birthDate\":\"1980-05-10\"}";

        mockMvc.perform(put("/tutores/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("João Atualizado"));
    }

    @Test
    void testRemoverTutor() throws Exception {
        Mockito.doNothing().when(tutorService).removerTutor(1L);
        mockMvc.perform(delete("/tutores/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testAtualizarTutorNotFound() throws Exception {
        Tutor tutor = new Tutor();
        tutor.setId(99L);
        tutor.setName("Ghost");
        Mockito.when(tutorService.atualizarTutor(Mockito.eq(99L), any(Tutor.class))).thenReturn(null);
        mockMvc.perform(put("/tutores/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tutor)))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    void testRemoverTutorNotFound() throws Exception {
        Mockito.doThrow(new IllegalArgumentException("Tutor não encontrado")).when(tutorService).removerTutor(99L);
        mockMvc.perform(delete("/tutores/99"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCadastrarTutorNomeDuplicado() throws Exception {
        Tutor tutor = new Tutor();
        tutor.setName("Duplicado");
        tutor.setNickname("Dup");
        tutor.setBirthDate(LocalDate.of(1990, 1, 1));
        Mockito.when(tutorService.cadastrarTutor(any(Tutor.class))).thenThrow(new IllegalArgumentException("Já existe um tutor com esse nome."));
        mockMvc.perform(post("/tutores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tutor)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCadastrarTutorSemCamposObrigatorios() throws Exception {
        Tutor tutor = new Tutor();
        tutor.setNickname("Joca");
        tutor.setBirthDate(LocalDate.of(1980, 5, 10));
        Mockito.when(tutorService.cadastrarTutor(any(Tutor.class))).thenThrow(new IllegalArgumentException("Nome do tutor é obrigatório."));
        mockMvc.perform(post("/tutores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(tutor)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Nome do tutor é obrigatório."));
    }

    @Test
    void testListarTodosVazio() throws Exception {
        Mockito.when(tutorService.listarTodos()).thenReturn(List.of());
        mockMvc.perform(get("/tutores"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testConsultarPorIdInexistente() throws Exception {
        Mockito.when(tutorService.consultarPorId(99L)).thenThrow(new IllegalArgumentException("Tutor não encontrado"));
        mockMvc.perform(get("/tutores/99"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Tutor não encontrado"));
    }
}
