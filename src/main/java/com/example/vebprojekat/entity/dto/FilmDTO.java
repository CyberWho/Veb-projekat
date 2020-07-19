package com.example.vebprojekat.entity.dto;

import javax.persistence.Column;
import java.sql.Time;

public class FilmDTO {
    private Long id;
    private String naziv;
    private String opis;
    private String zanr;
    private Integer trajanje;
    private Double prosecna_ocena;

    public FilmDTO() {}

    public FilmDTO(Long id, String naziv, String opis, String zanr, Integer trajanje, Double prosecna_ocena) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.zanr = zanr;
        this.trajanje = trajanje;
        this.prosecna_ocena = prosecna_ocena;
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

    public Double getProsecna_ocena() {
        return prosecna_ocena;
    }

    public void setProsecna_ocena(Double prosecna_ocena) {
        this.prosecna_ocena = prosecna_ocena;
    }
}
