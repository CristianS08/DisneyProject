package com.alkemy.disney.disney.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "kind")
@Getter
@Setter
@SQLDelete(sql = "UPDATE kind SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class KindEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private boolean deleted = Boolean.FALSE;

}
