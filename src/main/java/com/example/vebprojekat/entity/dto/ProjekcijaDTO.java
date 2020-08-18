package com.example.vebprojekat.entity.dto;

import com.example.vebprojekat.entity.Karta;
import com.example.vebprojekat.entity.Projekcija;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ProjekcijaDTO implements Serializable {
    private String naziv;
    private String opis;
    private String zanr;
    private Integer trajanje;
    private Double prosecnaocena;
    private Integer cena;
    private String datumvreme;
    private String sala;
    private String bioskop;
    private Integer broj_preostalih_mesta;

    public ProjekcijaDTO(){}

    public ProjekcijaDTO(String naziv, String opis, String zanr, Integer trajanje, Double prosecnaocena, Integer cena, String datumvreme, String sala, String bioskop, Integer broj_preostalih_mesta) {
        this.naziv = naziv;
        this.opis = opis;
        this.zanr = zanr;
        this.trajanje = trajanje;
        this.prosecnaocena = prosecnaocena;
        this.cena = cena;
        this.datumvreme = datumvreme;
        this.sala = sala;
        this.bioskop = bioskop;
        this.broj_preostalih_mesta = broj_preostalih_mesta;
    }

    public ProjekcijaDTO(Projekcija p){
        this.naziv = p.getFilm().getNaziv();
        this.opis = p.getFilm().getOpis();
        this.zanr = p.getFilm().getZanr();
        this.trajanje = p.getFilm().getTrajanje();
        this.prosecnaocena = p.getFilm().getProsecnaocena();
        this.cena = p.getCena();
        this.datumvreme = "";
        String temp = p.getDatumvreme().toString();
        String[] strings = temp.split("T");
        for(String s: strings){
            this.datumvreme += s;
            this.datumvreme += " ";
        }
        this.sala = p.getSala().getNaziv();
        this.bioskop = p.getBioskop().getNaziv();

        Integer broj_rezervisanih_mesta = 0;
        for(Karta k: p.getKarte()){
            broj_rezervisanih_mesta += k.getBroj_mesta();
        }

        this.broj_preostalih_mesta = p.getSala().getKapacitet() - broj_rezervisanih_mesta;
        /*System.out.println("Sala: " + this.sala);
        System.out.println("Kapacitet sale: " + p.getSala().getKapacitet());*/
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

    public Double getProsecnaocena() {
        return prosecnaocena;
    }

    public void setProsecnaocena(Double prosecnaocena) {
        this.prosecnaocena = prosecnaocena;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public String getDatumvreme() {
        return datumvreme;
    }

    public void setDatumvreme(String datumvreme) {
        this.datumvreme = datumvreme;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getBioskop() {
        return bioskop;
    }

    public void setBioskop(String bioskop) {
        this.bioskop = bioskop;
    }

    public Integer getBroj_preostalih_mesta() {
        return broj_preostalih_mesta;
    }

    public void setBroj_preostalih_mesta(Integer broj_preostalih_mesta) {
        this.broj_preostalih_mesta = broj_preostalih_mesta;
    }
}
