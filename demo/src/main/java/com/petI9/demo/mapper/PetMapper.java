package com.petI9.demo.mapper;

import com.petI9.demo.domain.Pet;
import com.petI9.demo.domain.Tutor;
import com.petI9.demo.domain.Vacina;
import com.petI9.demo.domain.Pet.Breed;

import com.petI9.demo.dto.PetDTO;
import com.petI9.demo.dto.VacinaDTO;
import java.util.List;
import java.util.stream.Collectors;

public class PetMapper {
    public static Pet toEntity(PetDTO dto) {
        Pet pet = new Pet();
        pet.setId(dto.getId());
        pet.setName(dto.getName());
        pet.setSpecies(dto.getSpecies());
        pet.setBreed(dto.getBreed() != null ? Breed.valueOf(dto.getBreed()) : null);
        pet.setBirthDate(dto.getBirthDate());
        pet.setColor(dto.getColor());
        pet.setWeight(dto.getWeight());
        if (dto.getVaccines() != null) {
            List<Vacina> vaccines = dto.getVaccines().stream().map(v -> {
                Vacina vac = new Vacina();
                vac.setType(v.getType());
                vac.setDate(v.getDate());
                return vac;
            }).collect(Collectors.toList());
            pet.setVaccines(vaccines);
        }
        Tutor tutor = new Tutor();
        tutor.setId(dto.getOwnerId());
        pet.setTutor(tutor);
        return pet;
    }

    public static PetDTO toDTO(Pet pet) {
        PetDTO dto = new PetDTO();
        dto.setId(pet.getId());
        dto.setName(pet.getName());
        dto.setSpecies(pet.getSpecies());
        dto.setBreed(pet.getBreed() != null ? pet.getBreed().name() : null);
        dto.setBirthDate(pet.getBirthDate());
        dto.setColor(pet.getColor());
        dto.setWeight(pet.getWeight());
        if (pet.getVaccines() != null) {
            List<VacinaDTO> vaccines = pet.getVaccines().stream().map(v -> {
                VacinaDTO vac = new VacinaDTO();
                vac.setType(v.getType());
                vac.setDate(v.getDate());
                return vac;
            }).collect(Collectors.toList());
            dto.setVaccines(vaccines);
        }
        dto.setOwnerId(pet.getOwner() != null ? pet.getOwner().getId() : null);
        return dto;
    }
}
