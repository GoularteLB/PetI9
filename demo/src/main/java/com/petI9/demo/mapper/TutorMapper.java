package com.petI9.demo.mapper;

import com.petI9.demo.domain.Tutor;

import com.petI9.demo.dto.TutorDTO;
import com.petI9.demo.dto.PetDTO;
import java.util.List;
import java.util.stream.Collectors;

public class TutorMapper {
    public static TutorDTO toDTO(Tutor tutor) {
        TutorDTO dto = new TutorDTO();
        dto.setId(tutor.getId());
        dto.setNome(tutor.getName());
        dto.setApelido(tutor.getNickname());
        dto.setDataNascimento(tutor.getBirthDate());
        if (tutor.getPets() != null) {
            List<PetDTO> pets = tutor.getPets().stream().map(PetMapper::toDTO).collect(Collectors.toList());
            dto.setPets(pets);
        }
        return dto;
    }

    public static Tutor toEntity(TutorDTO dto) {
        Tutor tutor = new Tutor();
        tutor.setId(dto.getId());
        tutor.setName(dto.getNome());
        tutor.setNickname(dto.getApelido());
        tutor.setBirthDate(dto.getDataNascimento());
        // Pets devem ser associados separadamente, pois precisam do tutor
        return tutor;
    }
}
