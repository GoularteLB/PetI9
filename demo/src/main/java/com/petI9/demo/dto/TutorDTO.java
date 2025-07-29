package com.petI9.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class TutorDTO {
    private Long id;
    private String nome;
    private String apelido;
    private LocalDate dataNascimento;
    private List<PetDTO> pets;
    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getApelido() { return apelido; }
    public void setApelido(String apelido) { this.apelido = apelido; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public List<PetDTO> getPets() { return pets; }
    public void setPets(List<PetDTO> pets) { this.pets = pets; }
}
