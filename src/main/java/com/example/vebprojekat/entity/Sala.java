package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer kapacitet;

    @Column
    private String oznaka;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private TerminskaLista terminska_lista = new TerminskaLista();

    public Sala(Long id, Integer kapacitet, String oznaka, TerminskaLista terminska_lista) {
        this.id = id;
        this.kapacitet = kapacitet;
        this.oznaka = oznaka;
        this.terminska_lista = terminska_lista;
    }

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

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public TerminskaLista getTerminska_lista() {
        return terminska_lista;
    }

    public void setTerminska_lista(TerminskaLista terminska_lista) {
        this.terminska_lista = terminska_lista;
    }
}
