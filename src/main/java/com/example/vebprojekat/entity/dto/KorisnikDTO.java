package com.example.vebprojekat.entity.dto;
import com.example.vebprojekat.entity.UlogaEnum.Uloga;

public class KorisnikDTO {
    private Long id;
    private String ime;
    private String prezime;
    private Uloga uloga;
    private String korisnicko_ime;

    public KorisnikDTO() {}

    public KorisnikDTO(Long id, String ime, String prezime, Uloga uloga) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.uloga = uloga;
    }

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
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
    }
}
