package com.alkemy.disney.disney.service;


import com.alkemy.disney.disney.dto.FilmBasicDTO;
import com.alkemy.disney.disney.dto.FilmDTO;
import com.alkemy.disney.disney.entity.FilmEntity;

import java.util.List;
import java.util.Set;

public interface FilmService {

    FilmDTO save(FilmDTO dto);

    List<FilmDTO> getAllFilms();

    void addCharacter(Long filmId, Long characterId);

    void delete(Long id);

    FilmEntity getById(Long id);

    FilmDTO modify (Long id, FilmDTO filmDTO);

    List<FilmDTO> getAllFilmsAndCharacters();

    void addKind(Long filmId, Long kindId);

    List<FilmBasicDTO> getByFilters(String title, Set<Long> characters, String order);
}
