package com.alkemy.disney.disney.service;


import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto);

    CharacterEntity getCharacterById(Long id);

    void addFilm(Long id, Long idFilm);

    void removeFilm(Long id, Long idFilm);

    CharacterDTO modify(Long id, CharacterDTO characterDTO);

    Set<CharacterDTO> getAllCharacters();

    void delete(Long id);

//    List<CharacterDTO> getByFilters(String name, Set<Long> films, Integer age);
}
