package com.petI9.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petI9.demo.application.PetServiceImpl;
import com.petI9.demo.domain.Pet;
import com.petI9.demo.domain.Vacina;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PetController.class)
class PetControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PetServiceImpl petService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCadastrarPet() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Rex");
        pet.setSpecies("Cachorro");
        pet.setBirthDate(LocalDate.of(2020, 1, 1));
        Vacina vacina = new Vacina();
        vacina.setType("Raiva");
        vacina.setDate(LocalDate.of(2021, 1, 1));
        pet.setVaccines(List.of(vacina));

        Mockito.when(petService.cadastrarPet(any(Pet.class))).thenReturn(pet);

        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Rex"));
    }

    @Test
    void testConsultarPorId() throws Exception {
        Pet pet = new Pet();
        pet.setId(2L);
        pet.setName("Mimi");
        pet.setSpecies("Gato");

        Mockito.when(petService.consultarPorId(2L)).thenReturn(pet);

        mockMvc.perform(get("/pets/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2L))
                .andExpect(jsonPath("$.name").value("Mimi"));
    }

    @Test
    void testConsultarPorNome() throws Exception {
        Pet pet = new Pet();
        pet.setId(3L);
        pet.setName("Bidu");
        pet.setSpecies("Cachorro");

        Mockito.when(petService.consultarPorNome("Bidu")).thenReturn(List.of(pet));

        mockMvc.perform(get("/pets/search?name=Bidu"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Bidu"));
    }

    @Test
    void testListarTodos() throws Exception {
        Pet p1 = new Pet();
        p1.setId(1L);
        p1.setName("Rex");
        Pet p2 = new Pet();
        p2.setId(2L);
        p2.setName("Mimi");

        Mockito.when(petService.listarPets()).thenReturn(List.of(p1, p2));

        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Rex"))
                .andExpect(jsonPath("$[1].name").value("Mimi"));
    }

    @Test
    void testEditarNomePet() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("RexNovo");
        pet.setSpecies("Cachorro");
        Mockito.when(petService.editarNome(1L, "RexNovo")).thenReturn(pet);

        mockMvc.perform(put("/pets/1/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"RexNovo\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("RexNovo"));
    }

    @Test
    void testRemoverPet() throws Exception {
        Mockito.doNothing().when(petService).removerPet(1L);
        mockMvc.perform(delete("/pets/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testEditarNomePetNotFound() throws Exception {
        Mockito.when(petService.editarNome(99L, "Ghost")).thenThrow(new IllegalArgumentException("Pet não encontrado"));
        mockMvc.perform(put("/pets/99/name")
                .contentType(MediaType.APPLICATION_JSON)
                .content("\"Ghost\""))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testRemoverPetNotFound() throws Exception {
        Mockito.doThrow(new IllegalArgumentException("Pet não encontrado")).when(petService).removerPet(99L);
        mockMvc.perform(delete("/pets/99"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testCadastrarPetSemCamposObrigatorios() throws Exception {
        Pet pet = new Pet();
        pet.setName("Rex");
        
        Mockito.when(petService.cadastrarPet(any(Pet.class))).thenThrow(new IllegalArgumentException("Espécie é obrigatória"));
        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Espécie é obrigatória"));
    }

    @Test
    void testCadastrarPetTutorInexistente() throws Exception {
        Pet pet = new Pet();
        pet.setName("Rex");
        pet.setSpecies("Cachorro");
        pet.setBirthDate(LocalDate.of(2020, 1, 1));
        Vacina vacina = new Vacina();
        vacina.setType("Raiva");
        vacina.setDate(LocalDate.of(2021, 1, 1));
        pet.setVaccines(List.of(vacina));
        Mockito.when(petService.cadastrarPet(any(Pet.class))).thenThrow(new IllegalArgumentException("Tutor não encontrado"));
        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Tutor não encontrado"));
    }

    @Test
    void testCadastrarPetNomeDuplicado() throws Exception {
        Pet pet = new Pet();
        pet.setName("Rex");
        pet.setSpecies("Cachorro");
        pet.setBirthDate(LocalDate.of(2020, 1, 1));
        Vacina vacina = new Vacina();
        vacina.setType("Raiva");
        vacina.setDate(LocalDate.of(2021, 1, 1));
        pet.setVaccines(List.of(vacina));
        Mockito.when(petService.cadastrarPet(any(Pet.class))).thenThrow(new IllegalArgumentException("Já existe um pet com esse nome para este tutor"));
        mockMvc.perform(post("/pets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pet)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Já existe um pet com esse nome para este tutor"));
    }

    @Test
    void testListarTodosVazio() throws Exception {
        Mockito.when(petService.listarPets()).thenReturn(List.of());
        mockMvc.perform(get("/pets"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void testConsultarPorIdInexistente() throws Exception {
        Mockito.when(petService.consultarPorId(99L)).thenThrow(new IllegalArgumentException("Pet não encontrado"));
        mockMvc.perform(get("/pets/99"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Pet não encontrado"));
    }
}
