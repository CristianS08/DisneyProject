package com.alkemy.disney.disney.service.implementation;

import com.alkemy.disney.disney.dto.FilmBasicDTO;
import com.alkemy.disney.disney.dto.FilmDTO;
import com.alkemy.disney.disney.dto.FilmFiltersDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.FilmEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.FilmMapper;
import com.alkemy.disney.disney.repository.FilmRepository;
import com.alkemy.disney.disney.repository.specification.FilmSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.FilmService;
import com.alkemy.disney.disney.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FilmServiceImplementation implements FilmService {

    @Autowired
    private FilmMapper filmMapper;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CharacterService characterService;

    @Autowired
    private KindService kindService;

    @Autowired
    FilmSpecification filmSpecification;




    public FilmDTO save(FilmDTO dto) {
        FilmEntity filmEntity = filmMapper.filmDTO2Entity(dto, true);
        FilmEntity filmEntitySaved = filmRepository.save(filmEntity);
        FilmDTO result = filmMapper.filmEntity2DTO(filmEntitySaved, false);

        return result;
    }


    public List<FilmDTO> getAllFilms() {
        List<FilmEntity> filmEntities = filmRepository.findAll();
        List<FilmDTO> results = filmMapper.filmEntityList2FilmDTOList(filmEntities, false);

        return results;
    }


    public void addCharacter(Long filmId, Long characterId) {
        FilmEntity filmEntity = this.getById(filmId);

        CharacterEntity character = characterService.getCharacterById(characterId);
        filmEntity.getCharacters().add(character);
        filmRepository.save(filmEntity);
    }

    public void delete(Long id) {
        filmRepository.deleteById(id);
    }


    public FilmEntity getById(Long id) {
        Optional<FilmEntity> filmEntity = filmRepository.findById(id);
        if (!filmEntity.isPresent()) {
            throw new ParamNotFound("Movie does not exist.");
        }
        return filmEntity.get();
    }


    public FilmDTO modify(Long id, FilmDTO filmDTO) {
        FilmEntity savedFilm = this.getById(id);

        savedFilm.setTitle(filmDTO.getTitle());
        savedFilm.setImage(filmDTO.getImage());
        savedFilm.setScore(filmDTO.getScore());

        FilmEntity filmEntity = filmRepository.save(savedFilm);
        FilmDTO result = filmMapper.filmEntity2DTO(filmEntity, true);

        return result;
    }


    public List<FilmDTO> getAllFilmsAndCharacters() {
        List<FilmEntity> entities = filmRepository.findAll();
        List<FilmDTO> result = filmMapper.filmEntityList2FilmDTOList(entities, true);

        return result;
    }


    public void addKind(Long filmId, Long kindId) {
        FilmEntity filmEntity = this.getById(filmId);

        filmEntity.setKindId(kindService.getKindById(kindId).getId());
        filmRepository.save(filmEntity);
    }


    public List<FilmBasicDTO> getByFilters(String title, Set<Long> characters, String order) {
        FilmFiltersDTO filmFilters = new FilmFiltersDTO(title, characters, order);
        List<FilmEntity> entityList = filmRepository.findAll(filmSpecification.getFiltered(filmFilters));
        List<FilmBasicDTO> result = filmMapper.filmEntityList2FilmBasicDTOList(entityList, true);

        return result;
    }


}
