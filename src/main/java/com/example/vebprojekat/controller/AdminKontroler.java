package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Admin;
import com.example.vebprojekat.entity.Bioskop;
import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.entity.Menadzer;
import com.example.vebprojekat.service.AdminService;
import com.example.vebprojekat.service.BioskopService;
import com.example.vebprojekat.service.KorisnikService;
import com.example.vebprojekat.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

        Set<Bioskop> bioskopi = menadzer.getBioskopi();


        for(Bioskop bioskop : bioskopi){
            System.out.println(bioskop.getMenadzeri().size());
            if(bioskop.getMenadzeri().size()==1){
                return "poslednji_menadzer.html";
            }
        }

        Long new_id = null;
        if(!menadzer.getApproved()){
            new_id = adminService.approveRegistration(korisnikService.findOne(id));
        } else new_id = id;
        menadzerService.delete(new_id);
        return "redirect:/admin_menadzeri";

    }

    @GetMapping("/admin_menadzer_detalji/{id}")
    public String admin_menadzer_detalji(@PathVariable(name = "id") Long id, Model model) throws Exception{
        Menadzer menadzer = menadzerService.findOne(id);
        System.out.println("Br bioskopa za ovgo menadzera: " + menadzer.getBioskopi().size());
        Set<Bioskop> bioskopi = menadzer.getBioskopi();
        model.addAttribute("korisnik", menadzer);
        model.addAttribute("bioskopi", bioskopi);
        model.addAttribute("last_mng", false);
        return "admin_menadzer_detalji.html";
    }

    @GetMapping("/admin_menadzer_detalji_ukloni_menadzera/{id}/{id_mng}")
    public String admin_menadzer_detalji_ukloni_menadzera(@PathVariable(name = "id") Long id, @PathVariable(name = "id_mng") Long id_mng) throws Exception{
        Bioskop bioskop = bioskopService.findOne(id);
        Menadzer menadzer = menadzerService.findOne(id_mng);

        if(bioskop.getMenadzeri().size()==1){
            return "poslednji_menadzer.html";
        }
        bioskop.getMenadzeri().remove(menadzer);
        menadzer.getBioskopi().remove(bioskop);

        bioskopService.update(bioskop);

        return "redirect:/admin_menadzer_detalji/" + id_mng;
    }


    // BIOSKOPI ----------------------------------------------------


    @GetMapping("/admin_bioskopi")
    public String admin_bioskop(Model model){
        model.addAttribute("bioskopi", bioskopService.findAll());
        return "admin_bioskopi.html";
    }


    @GetMapping("/admin_obrisi_bioskop/{id}")
    public String admin_obrisi_bioskop(@PathVariable(name = "id") Long id, Model model) throws Exception{
        Bioskop bioskop = bioskopService.findOne(id);
        bioskopService.delete(id);
        List<Menadzer> menadzeri = menadzerService.findAll();

        for(Menadzer menadzer : menadzeri){
            if(menadzer.getBioskopi().contains(bioskop)){
                menadzer.getBioskopi().remove(bioskop);
                menadzerService.update(menadzer);
            }
        }

        return "redirect:/admin_bioskopi";
    }

    @GetMapping("/admin_dodaj_bioskopu_menadzera/{id}")
    public String admin_dodaj_bioskopu_menadzera(@PathVariable(name = "id") Long id, Model model) throws Exception{
        Bioskop bioskop = bioskopService.findOne(id);
        List<Menadzer> menadzeri_model = new ArrayList<>();
        List<Menadzer> menadzeri = menadzerService.findAll();


        for(Menadzer menadzer : menadzeri) {
            if(!menadzer.getBioskopi().contains(bioskop) && menadzer.getApproved()) menadzeri_model.add(menadzer);
        }
        model.addAttribute("korisnici", menadzeri_model);
        model.addAttribute("bioskop", bioskop);

        return "admin_dodaj_bioskopu_menadzera.html";
    }

    @GetMapping("/admin_dodaj_bioskopu_menadzera/{id}/{id_mng}")
    public String admin_dodaj_bioskopu_menadzera_exe(@PathVariable(name = "id") Long id, @PathVariable(name = "id_mng") Long id_mng, Model model) throws Exception {

        Bioskop bioskop = bioskopService.findOne(id);
        Menadzer menadzer = menadzerService.findOne(id_mng);
        bioskop.getMenadzeri().add(menadzer);
        menadzer.getBioskopi().add(bioskop);
        bioskopService.update(bioskop);

        return "redirect:/admin_bioskopi";
    }

    @GetMapping("/admin_button_dodaj_bioskop")
    public String admin_dodaj_bioskop(Model model){
        model.addAttribute("bioskop", new Bioskop());
        return "admin_dodaj_bioskop.html";
    }

    @RequestMapping(value = "/admin_dodaj_bioskop", method = {RequestMethod.GET, RequestMethod.POST})
    public String admin_dodaj_bioskop(@ModelAttribute Bioskop bioskop, BindingResult errors, RedirectAttributes r, Model model) throws Exception {
        Bioskop novi = bioskopService.create(bioskop);

        if(novi == null) return "error.html";

        return "redirect:/admin_bioskopi";
    }

    @GetMapping("/admin_izmeni_bioskop/{id}")
    public String admin_izmeni_bioskop(@PathVariable(name = "id") Long id, Model model) throws Exception{
        model.addAttribute("id", id);
        model.addAttribute("bioskop", new Bioskop(id));
        return "admin_izmeni_bioskop_unos.html";
    }

    @RequestMapping(value = "/admin_izmeni_bioskop_post", method = {RequestMethod.GET, RequestMethod.POST})
    public String admin_izmeni_bioskop_post(@ModelAttribute Bioskop bioskop, Long id) throws Exception {
        System.out.println("Is empty?? " + bioskop.getNaziv().isEmpty());
        System.out.println("ID: " + id);
        Bioskop novi = bioskopService.findOne(id);
        System.out.println("Novi naziv: " + novi.getNaziv());
        if(!bioskop.getEmail().isEmpty()) novi.setEmail(bioskop.getEmail());
        if(!bioskop.getAdresa().isEmpty()) novi.setAdresa(bioskop.getAdresa());
        if(!bioskop.getBr_telefona().isEmpty()) novi.setBr_telefona(bioskop.getBr_telefona());
        if(!bioskop.getNaziv().isEmpty()) novi.setNaziv(bioskop.getNaziv());

        bioskopService.update(novi);



        return "redirect:/admin_bioskopi";
    }


}
