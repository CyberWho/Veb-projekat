package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Admin;
import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.entity.Menadzer;
import com.example.vebprojekat.service.AdminService;
import com.example.vebprojekat.service.BioskopService;
import com.example.vebprojekat.service.KorisnikService;
import com.example.vebprojekat.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AdminKontroler {
    @Autowired
    AdminService adminService;

    @Autowired
    KorisnikService korisnikService;

    @Autowired
    MenadzerService menadzerService;

    @Autowired
    BioskopService bioskopService;

    // MENADZERI --------------------------------------------------

    @GetMapping(value = "/odobri_registraciju/{id}")
    public String odobri_registraciju(@PathVariable(name = "id") Long id, Model model) throws Exception {
        Korisnik korisnik = korisnikService.findOne(id);
        adminService.approveRegistration(korisnik);
        korisnikService.deleteIfMoreThanOne(korisnikService.findByUsername(korisnik.getKorisnicko_ime()));
        return "redirect:/admin_index";
    }

    @GetMapping("/admin_menadzeri")
    public String admin_menadzeri(Model model){
        List<Menadzer> menadzeri = menadzerService.findAll();
        model.addAttribute("korisnici", menadzeri);

        return "admin_menadzeri.html";
    }

    @GetMapping("/obrisi_menadzera/{id}")
    public String obrisi_menadzera(@PathVariable(name = "id") Long id, Model model) throws Exception{
        Menadzer menadzer = menadzerService.findOne(id);
        Long new_id = null;
        if(!menadzer.getApproved()){
            new_id = adminService.approveRegistration(korisnikService.findOne(id));
        } else new_id = id;
        menadzerService.delete(new_id);
        return "redirect:/admin_menadzeri";

    }


    // BIOSKOPI ----------------------------------------------------


    @GetMapping("/admin_bioskopi")
    public String admin_bioskopi(Model model){
        model.addAttribute("bioskopi", bioskopService.findAll());
        return "admin_bioskopi.html";
    }

}
