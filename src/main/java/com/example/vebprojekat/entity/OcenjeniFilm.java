package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OcenjeniFilm implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer ocena;

    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private OdgledanFilm odgledan_film;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Gledalac gledalac;

    public OcenjeniFilm() {}


}
