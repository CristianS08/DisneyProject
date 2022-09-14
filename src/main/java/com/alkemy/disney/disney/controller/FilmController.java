package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.FilmBasicDTO;
import com.alkemy.disney.disney.dto.FilmDTO;
import com.alkemy.disney.disney.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<FilmDTO>> getAll(){
        List<FilmDTO> films = filmService.getAllFilms();

        return ResponseEntity.ok().body(films);
    }

    @PostMapping
    public ResponseEntity<FilmDTO> save(@RequestBody FilmDTO film){

        FilmDTO filmSaved = filmService.save(film);

        return ResponseEntity.status(HttpStatus.CREATED).body(filmSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        filmService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/alldetails")
    public ResponseEntity<List<FilmDTO>> getAllFilmsAndCharacters(){
        List<FilmDTO> films = filmService.getAllFilmsAndCharacters();
        return ResponseEntity.ok().body(films);
    }

    @PutMapping("/{filmId}/kind/{kindId}")
    public ResponseEntity<Void> addKind(@PathVariable Long filmId, @PathVariable Long kindId) {
        filmService.addKind(filmId, kindId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDTO> modify(@PathVariable Long id, @RequestBody FilmDTO filmDTO) {

        FilmDTO modifiedFilm = filmService.modify(id, filmDTO);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(modifiedFilm);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<FilmBasicDTO>> getByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Set<Long> character,
            @RequestParam(required = false, defaultValue = "ASC") String order){
        List<FilmBasicDTO> dtoList = this.filmService.getByFilters(title, character, order);
        return ResponseEntity.ok(dtoList);
    }
}
