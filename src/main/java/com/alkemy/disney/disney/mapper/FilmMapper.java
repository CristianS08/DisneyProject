package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.FilmBasicDTO;
import com.alkemy.disney.disney.dto.FilmDTO;
import com.alkemy.disney.disney.entity.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmMapper {

    @Autowired
    CharacterMapper characterMapper;

    @Autowired
    KindMapper kindMapper;


    public FilmEntity filmDTO2Entity(FilmDTO dto, boolean load) {

        FilmEntity filmEntity = new FilmEntity();

        filmEntity.setImage(dto.getImage());
        filmEntity.setScore(dto.getScore());
        filmEntity.setTitle(dto.getTitle());
        //PRUEBA
        String date = dto.getCreationDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate transformedDate = LocalDate.parse(date, formatter);
        filmEntity.setCreationDate(transformedDate);
        //FIN PRUEBA
        return filmEntity;
    }

    public FilmDTO filmEntity2DTO(FilmEntity entity, boolean load) {
        FilmDTO dto = new FilmDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setScore(entity.getScore());
        dto.setTitle(entity.getTitle());

        //PRUEBA
        //1. Get la forma original de la fecha
        LocalDate date = entity.getCreationDate();
        //2. Convierte en String
        String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dto.setCreationDate(formatDate);
        if (load) {
            dto.setCharacters(characterMapper.characterEntityList2DTOList(entity.getCharacters(), false));
            dto.setKind(kindMapper.kindEntity2DTO(entity.getKind()));
        }
        //FIN PRUEBA
        return dto;
    }

    public List<FilmDTO> filmEntityList2FilmDTOList(List<FilmEntity> entities, boolean load) {

        List<FilmDTO> dtos = new ArrayList<>();

        for (FilmEntity entity: entities) {
            dtos.add(filmEntity2DTO(entity, load));
        }

        return dtos;
    }

    public List<FilmBasicDTO> filmEntityList2FilmBasicDTOList(List<FilmEntity> entities, boolean load) {

        List<FilmBasicDTO> dtos = new ArrayList<>();

        for (FilmEntity entity: entities) {
            dtos.add(filmEntity2BasicDTO(entity, load));
        }

        return dtos;
    }

    public FilmBasicDTO filmEntity2BasicDTO(FilmEntity entity, boolean load) {
        FilmBasicDTO dto = new FilmBasicDTO();
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        //1. Get la forma original de la fecha
        LocalDate date = entity.getCreationDate();
        //2. Convierte en String
        String formatDate = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        dto.setCreationDate(formatDate);

        return dto;
    }
}
