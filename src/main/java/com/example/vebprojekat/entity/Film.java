package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Film implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String naziv;

    @Column
    private String opis;

    @Column
    private String zanr;

    @Column
    private Time trajanje;

    @Column
    private Double prosecna_ocena;

    @ManyToMany
    @JoinTable(
            name = "Filmovi i terminske liste",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "projekcija_id", referencedColumnName = "id")
    )
    private Set<TerminskaLista> terminske_liste = new HashSet<TerminskaLista>();

    public Film(Long id, String naziv, String opis, String zanr, Time trajanje, Double prosecna_ocena, Set<TerminskaLista> terminske_liste) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.zanr = zanr;
        this.trajanje = trajanje;
        this.prosecna_ocena = prosecna_ocena;
        this.terminske_liste = terminske_liste;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public Time getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Time trajanje) {
        this.trajanje = trajanje;
    }

    public Double getProsecna_ocena() {
        return prosecna_ocena;
    }

    public void setProsecna_ocena(Double prosecna_ocena) {
        this.prosecna_ocena = prosecna_ocena;
    }

    public Set<TerminskaLista> getTerminske_liste() {
        return terminske_liste;
    }

    public void setTerminske_liste(Set<TerminskaLista> terminske_liste) {
        this.terminske_liste = terminske_liste;
    }
}