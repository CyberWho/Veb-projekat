package com.example.vebprojekat.entity;

import java.sql.Time;
import java.util.Set;

public class FilmURasporedu extends Film{
    private Integer cena;
    private Integer br_rezervisanih;
    private String vreme_projekcije;

    public FilmURasporedu(Long id, String naziv, String opis, String zanr, Time trajanje, Double prosecna_ocena, Set<TerminskaLista> terminske_liste, Integer cena, Integer br_rezervisanih, String vreme_projekcije) {
        super(id, naziv, opis, zanr, trajanje, prosecna_ocena, terminske_liste);
        this.cena = cena;
        this.br_rezervisanih = br_rezervisanih;
        this.vreme_projekcije = vreme_projekcije;
    }
}
