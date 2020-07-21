package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@DiscriminatorValue("Gledalac")
public class Gledalac extends Korisnik {

    @OneToMany(mappedBy = "gledalac_odgledan" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<OdgledanFilm> odgledani_filmovi = new HashSet<OdgledanFilm>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Projekcija> rezervisane_karte = new HashSet<Projekcija>();

    @OneToMany(mappedBy = "gledalac" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<OcenjeniFilm> ocenjeni_filmovi = new HashSet<OcenjeniFilm>();

    public Gledalac(){}

    public Set<OdgledanFilm> getOdgledani_filmovi() {
        return odgledani_filmovi;
    }

    public void setOdgledani_filmovi(Set<OdgledanFilm> odgledani_filmovi) {
        this.odgledani_filmovi = odgledani_filmovi;
    }

    public Set<Projekcija> getRezervisane_karte() {
        return rezervisane_karte;
    }

    public void setRezervisane_karte(Set<Projekcija> rezervisaneKarte) {
        this.rezervisane_karte = rezervisaneKarte;
    }

    public Set<OcenjeniFilm> getOcenjeni_filmovi() {
        return ocenjeni_filmovi;
    }

    public void setOcenjeni_filmovi(Set<OcenjeniFilm> ocenjeni_filmovi) {
        this.ocenjeni_filmovi = ocenjeni_filmovi;
    }
}
