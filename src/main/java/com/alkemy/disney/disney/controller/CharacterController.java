package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {
    @Autowired
    private CharacterService characterService;

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<Set<CharacterDTO>> getAll(){
        Set<CharacterDTO> characters = characterService.getAllCharacters();

        return ResponseEntity.ok().body(characters);
    }

    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character){

        CharacterDTO characterSaved = characterService.save(character);

        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> modify(@PathVariable Long id, @RequestBody CharacterDTO characterDTO) {

        CharacterDTO modifiedCharacter = characterService.modify(id, characterDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modifiedCharacter);
    }

    @PutMapping("/{id}/film/{idFilm}")
    public ResponseEntity<Void> addFilm(@PathVariable Long id, @PathVariable Long idFilm) {
        this.characterService.addFilm(id, idFilm);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}/film/{idMovie}")
    public ResponseEntity<Void> removeFilm(@PathVariable Long id, @PathVariable Long idFilm) {
        this.characterService.removeFilm(id, idFilm);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{filmId}/character/{characterId}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long filmId, @RequestParam Long characterId) {
        filmService.addCharacter(filmId, characterId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @GetMapping("/characters")
//    public ResponseEntity<List<CharacterDTO>> getByFilters(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) Set<Long> movies,
//            @RequestParam(required = false) Integer age){
//        List<CharacterDTO> dtoList = this.characterService.getByFilters(name, movies, age);
//        return ResponseEntity.ok(dtoList);
//    }
}
