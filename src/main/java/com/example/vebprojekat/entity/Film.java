package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.io.Serializable;
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
    private Integer trajanje;

    @Column
    private Double prosecnaocena;

    /*
    @ManyToMany
    @JoinTable(
            name = "Filmovi i terminske liste",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "projekcija_id", referencedColumnName = "id")
    )
    private Set<Projekcija> terminske_liste = new HashSet<Projekcija>();
     */

    public Film(){}

    public Film(Long id, String naziv, String opis, String zanr, Integer trajanje, Double prosecnaocena, Set<Projekcija> terminske_liste) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.zanr = zanr;
        this.trajanje = trajanje;
        this.prosecnaocena = prosecnaocena;
        //this.terminske_liste = terminske_liste;
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

    public Integer getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(Integer trajanje) {
        this.trajanje = trajanje;
    }

    public Double getProsecnaocena() {
        return prosecnaocena;
    }

    public void setProsecnaocena(Double prosecna_ocena) {
        this.prosecnaocena = prosecna_ocena;
    }

    /*
    public Set<Projekcija> getTerminske_liste() {
        return terminske_liste;
    }

    public void setTerminske_liste(Set<Projekcija> terminske_liste) {
        this.terminske_liste = terminske_liste;
    }
    */
}