package com.petI9.demo.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String species;
    @Enumerated(EnumType.STRING)
    private Breed breed;
    private LocalDate birthDate;
    private String color;
    private Double weight;
    @ElementCollection
    private List<Vacina> vaccines;
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    public enum Breed {
        LABRADOR, POODLE, BULLDOG, SIAMESE, PERSIAN, OTHER
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }
    public Breed getBreed() { return breed; }
    public void setBreed(Breed breed) { this.breed = breed; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public List<Vacina> getVaccines() { return vaccines; }
    public void setVaccines(List<Vacina> vaccines) { this.vaccines = vaccines; }
    public Tutor getOwner() { return tutor; }
    public void setOwner(Tutor tutor) { this.tutor = tutor; }
    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }

    public void validarObrigatorios() {
        if (species == null || species.isBlank()) {
            throw new IllegalArgumentException("Espécie é obrigatória");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória");
        }
        if (vaccines == null || vaccines.isEmpty()) {
            throw new IllegalArgumentException("Vacinas são obrigatórias");
        }
    }
}
