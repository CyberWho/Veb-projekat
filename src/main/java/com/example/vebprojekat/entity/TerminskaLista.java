package com.example.vebprojekat.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TerminskaLista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "Filmovi i terminske liste",
            joinColumns = @JoinColumn(name = "FilmID", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ProjekcijaID", referencedColumnName = "id")
    )
    private Set<Film> film = new HashSet<>();

}
