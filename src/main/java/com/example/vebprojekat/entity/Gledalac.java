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
    private Set<Film> odgledani_filmovi = new HashSet<Film>();

    @ManyToMany
    @JoinTable(
        name = "Rezervisani filmovi",
        joinColumns =  @JoinColumn(name = "GledalacID", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "FilmID", referencedColumnName = "id")
    )
    private Set<Film> rezervisani_filmovi = new HashSet<Film>();

    @ElementCollection
    private Map<Film, Integer> ocenjeni_filmovi = new HashMap<Film, Integer>();

    public Gledalac(){}

    public Gledalac(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTel, String email, Date datum, UlogaEnum.Uloga uloga, Boolean aktivan, Set<Film> odgledani_filmovi, Set<Film> rezervisani_filmovi, Map<Film, Integer> ocenjeni_filmovi) {
        super(id, korisnickoIme, lozinka, ime, prezime, kontaktTel, email, datum, uloga, aktivan);
        this.odgledani_filmovi = odgledani_filmovi;
        this.rezervisani_filmovi = rezervisani_filmovi;
        this.ocenjeni_filmovi = ocenjeni_filmovi;
    }

    public Set<Film> getOdgledani_filmovi() {
        return odgledani_filmovi;
    }

    public void setOdgledani_filmovi(Set<Film> odgledaniFilmovi) {
        this.odgledani_filmovi = odgledaniFilmovi;
    }

    public Set<Film> getRezervisani_filmovi() {
        return rezervisani_filmovi;
    }

    public void setRezervisani_filmovi(Set<Film> rezervisaniFilmovi) {
        this.rezervisani_filmovi = rezervisaniFilmovi;
    }

    public Map<Film, Integer> getOcenjeni_filmovi() {
        return ocenjeni_filmovi;
    }

    public void setOcenjeni_filmovi(Map<Film, Integer> ocenjeniFilmovi) {
        this.ocenjeni_filmovi = ocenjeniFilmovi;
    }
}
