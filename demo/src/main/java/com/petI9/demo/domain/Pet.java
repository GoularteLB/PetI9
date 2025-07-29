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
    private List<Vaccine> vaccines;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

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
    public List<Vaccine> getVaccines() { return vaccines; }
    public void setVaccines(List<Vaccine> vaccines) { this.vaccines = vaccines; }
    public Owner getOwner() { return owner; }
    public void setOwner(Owner owner) { this.owner = owner; }
}
