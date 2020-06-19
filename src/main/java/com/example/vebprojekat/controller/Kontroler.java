package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.service.*;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class Kontroler {


    @Autowired
    private BioskopService bioskopService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private GledalacService gledalacService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private SalaService salaService;


    @GetMapping("/")
    public String welcome() {
        return "temp_index.html";
    }

    @GetMapping("/registracija")
    public String registracija(){
        return  "registracija.html";
    }

    @GetMapping("/registruj")
    public String registruj(){
        return "uspesna_registracija";
    }

    @GetMapping("/profil")
    public String prikazProfila(/*@PathVariable(name = "id")  Long id, Model model*/){
        return "profil.html";
    }

    @GetMapping("/prijava")
    public String prijava(){
        return "prijava.html";
    }

    @GetMapping("/dodaj_bioskop")
    public String dodaj_bioskop(){
        return "dodaj_bioskop.html";
    }








}
