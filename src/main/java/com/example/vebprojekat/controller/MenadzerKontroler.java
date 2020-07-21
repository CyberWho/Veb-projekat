package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Bioskop;
import com.example.vebprojekat.entity.Menadzer;
import com.example.vebprojekat.service.BioskopService;
import com.example.vebprojekat.service.MenadzerService;
import com.example.vebprojekat.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenadzerKontroler {
    @Autowired
    MenadzerService menadzerService;

    @Autowired
    SalaService salaService;

    @Autowired
    BioskopService bioskopService;

    @GetMapping("/menadzer_bioskopi/{id}")
    public String menadzer_bioskopi(@PathVariable(name = "id") Long id, Model model) throws Exception{
        Menadzer menadzer = menadzerService.findOne(id);
        List<Bioskop> bioskopi = bioskopService.findAll();
        List<Bioskop> bioskopi_model = new ArrayList<>();

        for(Bioskop bioskop : bioskopi){
            for(Menadzer men : bioskop.getMenadzeri()){
                if(menadzer.getKorisnicko_ime().equals(men.getKorisnicko_ime())){
                    bioskopi_model.add(bioskop);
                }
            }
        }
        model.addAttribute("menadzer", menadzer);
        model.addAttribute("bioskopi", bioskopi_model);

        return "menadzer_bioskopi.html";
    }

    @GetMapping("/menadzer_bioskop_detalji/{id}")
    public String menadzer_bioskopi_detalji(@PathVariable(name = "id") Long id, Model model) throws Exception{
        Bioskop bioskop = bioskopService.findOne(id);
        model.addAttribute("bioskop", bioskop);
        model.addAttribute("sale", bioskop.getSale());
        return "menadzer_bioskop_detalji.html";
    }

}
