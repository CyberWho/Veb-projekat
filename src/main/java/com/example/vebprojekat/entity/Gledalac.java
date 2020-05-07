package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@DiscriminatorValue("Gledalac")
public class Gledalac extends Korisnik {
    @ManyToMany
    @JoinTable(
            name = "Odgledani filmovi",
            joinColumns = @JoinColumn(name = "GledalacID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "FilmID", referencedColumnName = "id")
    )
    private Set<Film> odgledaniFilmovi = new HashSet<Film>();

    @ManyToMany
    @JoinTable(
        name = "Rezervisani filmovi",
        joinColumns =  @JoinColumn(name = "GledalacID", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "FilmID", referencedColumnName = "id")
    )
    private Set<Film> rezervisaniFilmovi = new HashSet<Film>();

    @ElementCollection
    private Map<Film, Integer> ocenjeniFilmovi = new HashMap<Film, Integer>();

    public Gledalac(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTel, String email, Date datum, Uloga uloga, Boolean aktivan, Set<Film> odgledaniFilmovi, Set<Film> rezervisaniFilmovi, Map<Film, Integer> ocenjeniFilmovi) {
        super(id, korisnickoIme, lozinka, ime, prezime, kontaktTel, email, datum, uloga, aktivan);
        this.odgledaniFilmovi = odgledaniFilmovi;
        this.rezervisaniFilmovi = rezervisaniFilmovi;
        this.ocenjeniFilmovi = ocenjeniFilmovi;
    }

    public Set<Film> getOdgledaniFilmovi() {
        return odgledaniFilmovi;
    }

    public void setOdgledaniFilmovi(Set<Film> odgledaniFilmovi) {
        this.odgledaniFilmovi = odgledaniFilmovi;
    }

    public Set<Film> getRezervisaniFilmovi() {
        return rezervisaniFilmovi;
    }

    public void setRezervisaniFilmovi(Set<Film> rezervisaniFilmovi) {
        this.rezervisaniFilmovi = rezervisaniFilmovi;
    }

    public Map<Film, Integer> getOcenjeniFilmovi() {
        return ocenjeniFilmovi;
    }

    public void setOcenjeniFilmovi(Map<Film, Integer> ocenjeniFilmovi) {
        this.ocenjeniFilmovi = ocenjeniFilmovi;
    }
}
