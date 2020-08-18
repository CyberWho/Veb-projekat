package com.example.vebprojekat.entity.dto;

import com.example.vebprojekat.entity.Admin;
import com.example.vebprojekat.entity.Menadzer;
import com.example.vebprojekat.entity.Uloga;

public class KorisnikDTO {
    private Long id;
    private String ime;
    private String prezime;
    private Uloga uloga;
    private String korisnickoime;

    public KorisnikDTO() {}

    public KorisnikDTO(Long id, String ime, String prezime, Uloga uloga) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.uloga = uloga;
    }

    /*public KorisnikDTO(Menadzer menadzer){
        this.id = menadzer.getId();
        this.ime = menadzer.getIme();
        this.prezime = menadzer.getPrezime();
        this.uloga = menadzer.getUloga();
        this.korisnickoime = menadzer.getKorisnickoime();
    }

    public KorisnikDTO(Admin admin){
        this.id = admin.getId();
        this.ime = admin.getIme();
        this.prezime = admin.getPrezime();
        this.uloga = admin.getUloga();
        this.korisnickoime = admin.getKorisnickoime();
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public String getKorisnicko_ime() {
        return korisnickoime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnickoime = korisnicko_ime;
    }
}
