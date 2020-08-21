package com.example.vebprojekat.entity.dto;

import com.example.vebprojekat.entity.Menadzer;

public class MenadzerDTO {
    private Long id;
    private String korisnickoime;
    private String ime;
    private String prezime;

    public MenadzerDTO(){}

    public MenadzerDTO(Menadzer m){
        this.id = m.getId();
        this.korisnickoime = m.getKorisnickoime();
        this.ime = m.getIme();
        this.prezime = m.getPrezime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoime() {
        return korisnickoime;
    }

    public void setKorisnickoime(String korisnickoime) {
        this.korisnickoime = korisnickoime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
