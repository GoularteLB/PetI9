package com.petI9.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.petI9.demo.domain.Tutor;
import com.petI9.demo.dto.PetDTO;
import com.petI9.demo.dto.TutorDTO;

public class TutorMapper {
    public static TutorDTO toDTO(Tutor tutor) {
        TutorDTO dto = new TutorDTO();
        dto.setId(tutor.getId());
        dto.setName(tutor.getName());
        dto.setNickname(tutor.getNickname());
        dto.setBirthDate(tutor.getBirthDate());
        if (tutor.getPets() != null) {
            List<PetDTO> pets = tutor.getPets().stream().map(PetMapper::toDTO).collect(Collectors.toList());
            dto.setPets(pets);
        }
        return dto;
    }

    public static Tutor toEntity(TutorDTO dto) {
        Tutor tutor = new Tutor();
        tutor.setId(dto.getId());
        tutor.setName(dto.getName());
        tutor.setNickname(dto.getNickname());
        tutor.setBirthDate(dto.getBirthDate());
        return tutor;
    }
}
