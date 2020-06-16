package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class KorisnikController {


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
    public String welcome() {return "temp_index.html";}



}
