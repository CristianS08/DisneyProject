package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.KindEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class FilmDTO {

    private Long id;

    private String image;

    private String title;

    private Integer score;

    private String creationDate;

    private KindDTO kind;

    private Set<CharacterDTO> characters;

}
