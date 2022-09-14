package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.FilmDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.FilmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CharacterMapper {

    @Lazy
    @Autowired
    FilmMapper filmMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {

        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setImage(dto.getImage());
        characterEntity.setHistory(dto.getHistory());
        characterEntity.setWeight(dto.getWeight());

        return characterEntity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadFilm) {

        CharacterDTO dto = new CharacterDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setAge(entity.getAge());
        dto.setHistory(entity.getHistory());
        dto.setWeight(entity.getWeight());

        if (loadFilm) {
            List<FilmDTO> dtoList = new ArrayList<>();
            for (FilmEntity fEntity : entity.getFilms()) {
                dtoList.add(filmMapper.filmEntity2DTO(fEntity, false));
            }
            dto.setFilms(dtoList);
        }

        return dto;
    }

    public Set<CharacterDTO> characterEntityList2DTOList(Set<CharacterEntity> entities, boolean loadMovie) {
        Set<CharacterDTO> dtoList = new HashSet<>();

        for (CharacterEntity entity : entities) {
            dtoList.add(characterEntity2DTO(entity, loadMovie));
        }
        return dtoList;
    }

    public Set<CharacterEntity> characterDTOList2EntityList(Set<CharacterDTO> dtoList, boolean load) {
        Set<CharacterEntity> entities = new HashSet<>();

        for (CharacterDTO dto : dtoList) {
            entities.add(this.characterDTO2Entity(dto));
        }
        return entities;
    }
}
