package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Film;
import com.example.vebprojekat.entity.dto.FilmDTO;
import com.example.vebprojekat.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FilmKontroler {

    @Autowired
    private FilmService filmService;

    @GetMapping("/filmovi")
    public String filmovi(Model model){
        List<Film> filmovi = filmService.findAll();

        List<FilmDTO> filmoviDTO = new ArrayList<>();

        for(Film film : filmovi){
            FilmDTO temp = new FilmDTO();
            temp.setId(film.getId());
            temp.setNaziv(film.getNaziv());
            temp.setOpis(film.getOpis());
            temp.setTrajanje(film.getTrajanje());
            temp.setZanr(film.getZanr());
            temp.setProsecna_ocena(film.getProsecna_ocena());
            filmoviDTO.add(temp);
        }

        model.addAttribute("filmovi", filmoviDTO);
        return "filmovi.html";
    }

}
