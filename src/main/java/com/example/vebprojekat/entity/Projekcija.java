package com.example.vebprojekat.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Projekcija implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Film film;

    @Column
    private Integer cena;

    @Column
    private LocalDateTime datumvreme;

    @ManyToMany(mappedBy = "lista_projekcija" , fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Sala> sale = new HashSet<Sala>();

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Bioskop bioskop;

    @ManyToMany(mappedBy = "rezervisane_karte", fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    private Set<Gledalac> gledaoci = new HashSet<Gledalac>();

    public Projekcija(){}

    public Projekcija(Long id, Film film, Integer cena, LocalDateTime datumvreme, Set<Sala> sale, Bioskop bioskop, Set<Gledalac> gledaoci) {
        this.id = id;
        this.film = film;
        this.cena = cena;
        this.datumvreme = datumvreme;
        this.sale = sale;
        this.bioskop = bioskop;
        this.gledaoci = gledaoci;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public LocalDateTime getDatumvreme() {
        return datumvreme;
    }

    public void setDatumvreme(LocalDateTime datum_vreme) {
        this.datumvreme = datum_vreme;
    }

    public Set<Sala> getSale() {
        return sale;
    }

    public void setSale(Set<Sala> sale) {
        this.sale = sale;
    }

    public Bioskop getBioskop() {
        return bioskop;
    }

    public void setBioskop(Bioskop bioskop) {
        this.bioskop = bioskop;
    }

    public Set<Gledalac> getGledaoci() {
        return gledaoci;
    }

    public void setGledaoci(Set<Gledalac> gledaoci) {
        this.gledaoci = gledaoci;
    }
}
