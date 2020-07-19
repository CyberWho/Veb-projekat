package com.example.vebprojekat.entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.example.vebprojekat.entity.UlogaEnum.Uloga;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class Korisnik implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String korisnicko_ime;

    @Column
    private String lozinka;

    @Column
    private  String ime;

    @Column
    private String prezime;

    @Column
    private String kontakt_tel;

    @Column
    private String email;

    @Column
    private Date datum;

    @Column
    private Uloga uloga;

    @Column
    private Boolean aktivan;

    @Column
    private Boolean approved;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Admin admin_approve;

    public Korisnik(){}

    public Korisnik(String ime, String prezime, Uloga uloga){
        this.ime = ime;
        this.prezime = prezime;
        this.uloga = uloga;

    }

    public Korisnik(Long id, String korisnicko_ime, String lozinka, String ime, String prezime, String kontakt_tel, String email, Date datum, UlogaEnum.Uloga uloga, Boolean aktivan) {
        this.id = id;
        this.korisnicko_ime = korisnicko_ime;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.kontakt_tel = kontakt_tel;
        this.email = email;
        this.datum = datum;
        this.uloga = uloga;
        this.aktivan = aktivan;
        this.approved = false;
        this.admin_approve = null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKorisnicko_ime() {
        return korisnicko_ime;
    }

    public void setKorisnicko_ime(String korisnicko_ime) {
        this.korisnicko_ime = korisnicko_ime;
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

    public String getKontakt_tel() {
        return kontakt_tel;
    }

    public void setKontakt_tel(String kontakt_tel) {
        this.kontakt_tel = kontakt_tel;
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

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Admin getAdmin_approve() {
        return admin_approve;
    }

    public void setAdmin_approve(Admin admin_approve) {
        this.admin_approve = admin_approve;
    }
}
