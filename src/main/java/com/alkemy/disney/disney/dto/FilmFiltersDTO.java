package com.alkemy.disney.disney.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class FilmFiltersDTO {
    private String title;
    private Set<Long> characters;
    private String order;

    public FilmFiltersDTO(String name, Set<Long> characters, String order) {
        this.title = name;
        this.characters = characters;
        this.order = order;
    }

    public boolean isASC(){
        return this.order.compareToIgnoreCase("ASC") == 0;
    }

    public boolean isDESC(){
        return this.order.compareToIgnoreCase("DESC") == 0;
    }
}
