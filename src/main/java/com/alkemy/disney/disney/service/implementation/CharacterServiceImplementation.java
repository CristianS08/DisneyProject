package com.alkemy.disney.disney.service.implementation;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.FilmEntity;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImplementation implements CharacterService {
    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterRepository characterRepository;

    @Lazy
    @Autowired
    private FilmService filmService;

    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity characterEntity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity characterEntitySaved = characterRepository.save(characterEntity);
        CharacterDTO result = characterMapper.characterEntity2DTO(characterEntitySaved, false);

        return result;
    }


    public CharacterEntity getCharacterById(Long id) {
        Optional<CharacterEntity> characterEntity = characterRepository.findById(id);
        if (!characterEntity.isPresent()) {
//            throw new ParamNotFound("This Disney character does not exist."+ id);
        }
        return characterEntity.get();
    }


    public void addFilm(Long id, Long idFilm) {
        CharacterEntity characterEntity = this.getCharacterById(id);
        FilmEntity film = filmService.getById(idFilm);
        characterEntity.getFilms().add(film);

        characterRepository.save(characterEntity);
    }


    public void removeFilm(Long id, Long idFilm) {
        CharacterEntity characterEntity = this.getCharacterById(id);
        FilmEntity film = filmService.getById(idFilm);
        characterEntity.getFilms().remove(film);

        characterRepository.save(characterEntity);
    }


    public CharacterDTO modify(Long id, CharacterDTO characterDTO) {
        CharacterEntity savedCharacter = this.getCharacterById(id);

        savedCharacter.setName(characterDTO.getName());
        savedCharacter.setImage(characterDTO.getImage());
        savedCharacter.setHistory(characterDTO.getHistory());
        savedCharacter.setWeight(savedCharacter.getWeight());
        savedCharacter.setAge(characterDTO.getAge());

        CharacterEntity characterEntity = characterRepository.save(savedCharacter);

        return characterMapper.characterEntity2DTO(characterEntity, false);
    }

    public Set<CharacterDTO> getAllCharacters() {
        Set<CharacterEntity> characterEntities = (Set<CharacterEntity>) characterRepository.findAll();

        return characterMapper.characterEntityList2DTOList(characterEntities, true);
    }

    public void delete(Long id) {
        characterRepository.deleteById(id);
    }


//    public List<CharacterDTO> getByFilters(String name, Set<Long> films, Integer age) {
//      CharacterFilterDTO characterFilters = new CharacterFilterDTO(name, movies, age);
//    List<CharacterEntity> entityList = characterRepository.findAll(characterSpecification.getFiltered(characterFilters));
//        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(entityList, true);
//        return result;
//    }
}
