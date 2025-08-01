package com.petI9.demo.dto;

import java.time.LocalDate;
import java.util.List;

public class TutorDTO {
    private Long id;
    private String name;
    private String nickname;
    private LocalDate birthDate;
    private List<PetDTO> pets;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
    public List<PetDTO> getPets() { return pets; }
    public void setPets(List<PetDTO> pets) { this.pets = pets; }
}
