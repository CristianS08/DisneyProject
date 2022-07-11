package entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "character")
@Getter
@Setter
public class CharacterEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid" , strategy = "uuid2")
    private String id;

    private String image;

    private String name;

    private Integer age;

    private Float weight;

    private String history;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<FilmEntity> films = new ArrayList<>();
}
