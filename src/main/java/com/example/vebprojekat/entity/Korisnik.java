package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

enum Uloga {
    GLEDALAC, MENADZER, ADMIN;
}

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String korisnickoIme;

    @Column
    private String lozinka;

    @Column
    private  String ime;

    @Column
    private String prezime;

    @Column
    private String kontaktTel;

    @Column
    private String email;

    @Column
    private Date datum;

    @Column
    private Uloga uloga;

    @Column
    private Boolean aktivan;

    public Korisnik(Long id, String korisnickoIme, String lozinka, String ime, String prezime, String kontaktTel, String email, Date datum, Uloga uloga, Boolean aktivan) {
        this.id = id;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.kontaktTel = kontaktTel;
        this.email = email;
        this.datum = datum;
        this.uloga = uloga;
        this.aktivan = aktivan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
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

    public String getKontaktTel() {
        return kontaktTel;
    }

    public void setKontaktTel(String kontaktTel) {
        this.kontaktTel = kontaktTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Uloga getUloga() {
        return uloga;
    }

    public void setUloga(Uloga uloga) {
        this.uloga = uloga;
    }

    public Boolean getAktivan() {
        return aktivan;
    }

    public void setAktivan(Boolean aktivan) {
        this.aktivan = aktivan;
    }
}
