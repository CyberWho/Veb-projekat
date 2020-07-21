package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Bioskop;
import com.example.vebprojekat.entity.Menadzer;
import com.example.vebprojekat.entity.Sala;
import com.example.vebprojekat.service.BioskopService;
import com.example.vebprojekat.service.MenadzerService;
import com.example.vebprojekat.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String menadzer_bioskopi(@PathVariable(name = "id") Long id, Model model) throws Exception {
        Menadzer menadzer = menadzerService.findOne(id);
        List<Bioskop> bioskopi = bioskopService.findAll();
        List<Bioskop> bioskopi_model = new ArrayList<>();

        for (Bioskop bioskop : bioskopi) {
            for (Menadzer men : bioskop.getMenadzeri()) {
                if (menadzer.getKorisnicko_ime().equals(men.getKorisnicko_ime())) {
                    bioskopi_model.add(bioskop);
                }
            }
        }
        model.addAttribute("menadzer", menadzer);
        model.addAttribute("bioskopi", bioskopi_model);

        return "menadzer_bioskopi.html";
    }

    @GetMapping("/menadzer_bioskop_detalji/{id_mng}/{id_bio}")
    public String menadzer_bioskopi_detalji(@PathVariable(name = "id_mng") Long id_mng, @PathVariable(name = "id_bio") Long id_bio, Model model) throws Exception {
        Bioskop bioskop = bioskopService.findOne(id_bio);
        Menadzer menadzer = menadzerService.findOne(id_mng);
        model.addAttribute("menadzer", menadzer);
        model.addAttribute("bioskop", bioskop);
        model.addAttribute("sale", bioskop.getSale());
        return "menadzer_bioskop_detalji.html";
    }

    @GetMapping("/menadzer_izmeni_salu/{id_mng}/{id_bio}/{id_sala}")
    public String izmeni_salu(@PathVariable(name = "id_mng") Long id_mng, @PathVariable(name = "id_bio") Long id_bio, @PathVariable(name = "id_sala") Long id_sala, Model model) {
        Bioskop bioskop = bioskopService.findOne(id_bio);
        Menadzer menadzer = menadzerService.findOne(id_mng);
        Sala sala = salaService.findOne(id_sala);
        model.addAttribute("menadzer", menadzer);
        model.addAttribute("bioskop", bioskop);
        model.addAttribute("sala", sala);
        model.addAttribute("nova_sala", new Sala());

        return "menadzer_izmeni_salu.html";
    }

    @RequestMapping(value = "/menadzer_izmeni_salu_upis/{id_mng}/{id_bio}/{id_sala}", method = {RequestMethod.POST, RequestMethod.GET})
    public String izmeni_salu_upis(@ModelAttribute Sala sala_sa_forme, @PathVariable(name = "id_mng") Long id_mng, @PathVariable(name = "id_bio") Long id_bio, @PathVariable(name = "id_sala") Long id_sala, Model model) throws Exception {
        Bioskop bioskop = bioskopService.findOne(id_bio);
        Menadzer menadzer = menadzerService.findOne(id_mng);
        Sala sala = salaService.findOne(id_sala);

        System.out.println("Naziv sa forme: " + sala_sa_forme.getNaziv());
        System.out.println("Vrednost kapaciteta sa forme: " + sala_sa_forme.getKapacitet());

        if(sala_sa_forme.getKapacitet() != null) sala.setKapacitet(sala_sa_forme.getKapacitet());
        if(!(sala_sa_forme.getNaziv().isEmpty())) sala.setNaziv(sala_sa_forme.getNaziv());
        for(Sala s : bioskop.getSale()){
            if(s.getNaziv().equals(sala_sa_forme.getNaziv())){
                bioskop.getSale().remove(s);
                break;
            }
        }
        bioskop.getSale().add(sala);
        bioskopService.update(bioskop);
        return "redirect:/menadzer_bioskop_detalji/" + id_mng + "/" + id_bio;
    }

    @GetMapping("/menadzer_obrisi_salu/{id_mng}/{id_bio}/{id_sala}")
    public String obrisi_salu(@PathVariable(name = "id_mng") Long id_mng, @PathVariable(name = "id_bio") Long id_bio, @PathVariable(name = "id_sala") Long id_sala, Model model) throws Exception {
        Menadzer menadzer = menadzerService.findOne(id_mng);
        Bioskop bioskop = bioskopService.findOne(id_bio);
        Sala sala = salaService.findOne(id_sala);

        System.out.println("");
        bioskop.getSale().remove(sala);
        bioskopService.update(bioskop);
        salaService.delete(id_sala);

        return "redirect:/menadzer_bioskop_detalji/" + id_mng + "/" + id_bio;
    }

    @GetMapping("/menadzer_button_dodaj_salu/{id_mng}/{id_bio}")
    public String dodaj_salu(@PathVariable(name = "id_mng") Long id_mng, @PathVariable(name = "id_bio") Long id_bio, Model model){
        Menadzer menadzer = menadzerService.findOne(id_mng);
        Bioskop bioskop = bioskopService.findOne(id_bio);

        model.addAttribute("menadzer", menadzer);
        model.addAttribute("bioskop", bioskop);
        model.addAttribute("sala", new Sala());

        return "menadzer_dodaj_salu_forma.html";
    }

    @RequestMapping(value = "/menadzer_dodaj_salu_upis/{id_mng}/{id_bio}", method = {RequestMethod.GET, RequestMethod.POST})
    public String dodaj_salu_upis(@ModelAttribute Sala sala, @PathVariable(name = "id_mng") Long id_mng, @PathVariable(name = "id_bio") Long id_bio, Model model) throws Exception {
        Menadzer menadzer = menadzerService.findOne(id_mng);
        Bioskop bioskop = bioskopService.findOne(id_bio);

        sala.setBioskop(bioskop);
        bioskop.getSale().add(sala);
        salaService.create(sala);
        bioskopService.update(bioskop);


        return "redirect:/menadzer_bioskop_detalji/" + id_mng + "/" + id_bio;
    }

}