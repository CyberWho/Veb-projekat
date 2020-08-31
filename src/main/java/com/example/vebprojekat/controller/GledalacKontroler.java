package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Film;
import com.example.vebprojekat.entity.Gledalac;
import com.example.vebprojekat.entity.OdgledanFilm;
import com.example.vebprojekat.entity.Projekcija;
import com.example.vebprojekat.entity.dto.FilmDTO;
import com.example.vebprojekat.entity.dto.OdgledanFilmDTO;
import com.example.vebprojekat.entity.dto.ProjekcijaDTO;
import com.example.vebprojekat.service.IF.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GledalacKontroler {
    @Autowired
    FilmService filmService;

    @Autowired
    GledalacService gledalacService;

    @Autowired
    KartaService kartaService;

    @Autowired
    ProjekcijaService projekcijaService;

    @Autowired
    OdgledanFilmService odgledanFilmService;


    @GetMapping(value = "/oceni/{movie_id}/{grade}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Object>> oceni(@PathVariable(name="movie_id") Long movie_id, @PathVariable(name="grade") Integer grade) throws Exception {
        Gledalac gledalac = gledalacService.findOne(KorisnikKontroler.ulogovan.getId());

        OdgledanFilm odgledanFilm = null;

        for(OdgledanFilm of: gledalac.getOdgledani_filmovi()){
            if(movie_id == of.getId()){
                odgledanFilm = of;
                odgledanFilm.setOcena(grade);
                odgledanFilm.setOcenjen(true);

                odgledanFilmService.update(odgledanFilm);
                break;
            }
        }

        Film film = filmService.findOne(odgledanFilm.getFilm().getId());

        film.ocenjivaciIncrement();
        film.noviZbir(grade);
        film.racunajProsek();

        filmService.update(film);

        if(odgledanFilm == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Object> retVal = new ArrayList<>();
        retVal.add(new OdgledanFilmDTO(odgledanFilm));
        retVal.add(grade);

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/gledalac_rezervisi/{projection_id}/{broj_karata}")
    public ResponseEntity<List<Object>> gledalac_rezervisi(@PathVariable(name="projection_id") Long projection_id, @PathVariable(name="broj_karata") Integer broj_karata){
        
    }

}
