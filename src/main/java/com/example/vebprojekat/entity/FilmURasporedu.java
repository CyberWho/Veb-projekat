package com.example.vebprojekat.entity;

import java.sql.Date;
import java.util.Set;

public class FilmURasporedu extends Film{
    private Integer cena;
    private Integer br_rezervisanih;
    private Date vreme_projekcije;

    FilmURasporedu(){}

    public FilmURasporedu(Long id, String naziv, String opis, String zanr, Integer trajanje, Double prosecna_ocena, Set<Projekcija> terminske_liste, Integer cena, Integer br_rezervisanih, Date vreme_projekcije) {
        super(id, naziv, opis, zanr, trajanje, prosecna_ocena, terminske_liste);
        this.cena = cena;
        this.br_rezervisanih = br_rezervisanih;
        this.vreme_projekcije = vreme_projekcije;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Integer getBr_rezervisanih() {
        return br_rezervisanih;
    }

    public void setBr_rezervisanih(Integer br_rezervisanih) {
        this.br_rezervisanih = br_rezervisanih;
    }

    public Date getVreme_projekcije() {
        return vreme_projekcije;
    }

    public void setVreme_projekcije(Date vreme_projekcije) {
        this.vreme_projekcije = vreme_projekcije;
    }
}
