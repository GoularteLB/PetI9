package com.petI9.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.petI9.demo.enums.PetSpecies;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class PetDTO {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Species cannot be null")
    private PetSpecies species;

    @NotBlank(message = "Breed cannot be blank")
    private String breed;
    
    @NotNull(message = "Birth date cannot be null")
    @PastOrPresent(message = "Birth date must be in the past or present")
    private LocalDate birthDate;

    private String color;
    private Double weight;
    private List<VacinaDTO> vaccines;
    private Long ownerId;
    private String tutorName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public PetSpecies getSpecies() { return species; }
    public void setSpecies(PetSpecies species) { this.species = species; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }
    public List<VacinaDTO> getVaccines() { return vaccines; }
    public void setVaccines(List<VacinaDTO> vaccines) { this.vaccines = vaccines; }
    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }
    public String getTutorName() { return tutorName; }
    public void setTutorName(String tutorName) { this.tutorName = tutorName;
}}
