package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class OdgledanFilm implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Film film;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Gledalac gledalac;

    @OneToOne(mappedBy = "odgledan_film" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private OcenjenFilm ocenjen_film;

    public OdgledanFilm(){}

    public OdgledanFilm(Long id, Film film, Gledalac gledalac, OcenjenFilm ocenjen_film) {
        this.id = id;
        this.film = film;
        this.gledalac = gledalac;
        this.ocenjen_film = ocenjen_film;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Gledalac getGledalac() {
        return gledalac;
    }

    public void setGledalac(Gledalac gledalac) {
        this.gledalac = gledalac;
    }

    public OcenjenFilm getOcenjen_film() {
        return ocenjen_film;
    }

    public void setOcenjen_film(OcenjenFilm ocenjen_film) {
        this.ocenjen_film = ocenjen_film;
    }
}
