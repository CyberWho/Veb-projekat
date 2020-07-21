package com.example.vebprojekat.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sala implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer kapacitet;

    @Column
    private String naziv;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bioskop bioskop;

    @ManyToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Projekcija> listaProjekcija = new HashSet<Projekcija>();

    public Sala(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getKapacitet() {
        return kapacitet;
    }

    public void setKapacitet(Integer kapacitet) {
        this.kapacitet = kapacitet;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public void setBioskop(Bioskop bioskop) {
        this.bioskop = bioskop;
    }

    public Set<Projekcija> getListaProjekcija() {
        return listaProjekcija;
    }

    public void setListaProjekcija(Set<Projekcija> listaProjekcija) {
        this.listaProjekcija = listaProjekcija;
    }
}
