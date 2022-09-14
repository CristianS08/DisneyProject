package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.KindDTO;
import com.alkemy.disney.disney.service.KindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("kinds")
public class KindController {

    @Autowired
    private KindService kindService;

    @PostMapping
    public ResponseEntity<KindDTO> save(@RequestBody KindDTO kind){

        KindDTO kindSaved = kindService.save(kind);

        return ResponseEntity.status(HttpStatus.CREATED).body(kindSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        kindService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
