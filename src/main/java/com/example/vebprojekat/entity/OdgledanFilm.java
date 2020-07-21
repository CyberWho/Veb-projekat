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
    private Gledalac gledalac_odgledan;

    @OneToOne(mappedBy = "odgledan_film" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private OcenjeniFilm ocenjeni_film;

    public OdgledanFilm(){}

    public OdgledanFilm(Long id, Film film, Gledalac gledalac_odgledan, OcenjeniFilm ocenjeniFilm) {
        this.id = id;
        this.film = film;
        this.gledalac_odgledan = gledalac_odgledan;
        this.ocenjeni_film = ocenjeniFilm;
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

    public Gledalac getGledalac_odgledan() {
        return gledalac_odgledan;
    }

    public void setGledalac_odgledan(Gledalac gledalac_odgledan) {
        this.gledalac_odgledan = gledalac_odgledan;
    }

    public OcenjeniFilm getOcenjeniFilm() {
        return ocenjeni_film;
    }

    public void setOcenjeniFilm(OcenjeniFilm ocenjeniFilm) {
        this.ocenjeni_film = ocenjeniFilm;
    }
}
