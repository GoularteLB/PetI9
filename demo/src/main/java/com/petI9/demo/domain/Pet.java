package com.petI9.demo.domain;

import java.time.LocalDate;
import java.util.List;

public class Pet {
    private Long id;
    private String nome;
    private String especie;
    private String raca;
    private LocalDate dataNascimento;
    private String cor;
    private Double peso;
    private List<Vacina> vacinas;
    private Tutor tutor;

    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }
    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }
    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }
    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    public List<Vacina> getVacinas() { return vacinas; }
    public void setVacinas(List<Vacina> vacinas) { this.vacinas = vacinas; }
    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }
}
