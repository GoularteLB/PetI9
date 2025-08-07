package com.petI9.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(VacinaController.class)
class VacinaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    com.petI9.demo.service.VacinaService vacinaService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testCadastrarVacina() throws Exception {
        Vacina vacina = new Vacina();
        vacina.setId(1L);
        vacina.setName("Rabies");
        vacina.setDate(LocalDate.of(2023, 1, 1));
        Mockito.when(vacinaService.cadastrarVacina(any(Vacina.class))).thenReturn(vacina);
        mockMvc.perform(post("/vacinas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vacina)))
                .andExpect(status().isOk());
    }

    @Test
    void testCadastrarVacinaNomeDuplicado() throws Exception {
        Vacina vacina = new Vacina();
        vacina.setName("Rabies");
        vacina.setDate(LocalDate.of(2023, 1, 1));
        Mockito.when(vacinaService.cadastrarVacina(any(Vacina.class))).thenThrow(new IllegalArgumentException("Já existe uma vacina com esse nome."));
        mockMvc.perform(post("/vacinas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vacina)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testListarTodasVazio() throws Exception {
        Mockito.when(vacinaService.listarTodas()).thenReturn(List.of());
        mockMvc.perform(get("/vacinas"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }


}
