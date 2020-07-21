package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.Film;
import com.example.vebprojekat.entity.Pretraga;
import com.example.vebprojekat.entity.Projekcija;
import com.example.vebprojekat.entity.Sort;
import com.example.vebprojekat.service.GledalacService;
import com.example.vebprojekat.service.ProjekcijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class GledalacKontroler {

    @Autowired
    ProjekcijaService projekcijaService;

    @Autowired
    GledalacService gledalacService;


    @PostMapping("/gledalac_filmovi_pretraga")
    public String gledalac_filmovi_pretraga(@ModelAttribute Pretraga pretraga_string, Model model){
        List<Projekcija> sve_projekcije = projekcijaService.findAll();
        List<Projekcija> projekcije = new ArrayList<>();
        List<Film> filmovi = new ArrayList<>();
        Pretraga ps = pretraga_string;
        model.addAttribute("pretraga_string", new Pretraga());
        model.addAttribute("sort", new Sort());

        System.out.println("Unet naziv: " + ps.getNaziv());
        System.out.println("Unet opis: " + ps.getOpis());
        System.out.println("Unet zanr: " + ps.getZanr());
        System.out.println("Uneta cena: " + ps.getCena());
        System.out.println("Uneta ocena: " + ps.getOcena());
        System.out.println("Uneto vreme: " + ps.getVreme());


        if(ps.getNaziv().equals("") && ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()==null){
            System.out.println("1. Usao sam gde treba");
            projekcije.addAll(sve_projekcije);
        } else {
            for(Projekcija projekcija : sve_projekcije){
                // PO NAZIVU
                if(!ps.getNaziv().equals("") && ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()==null){
                    if(pretragaNaziv(projekcija, ps)){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                //PO OPISU
                if(ps.getNaziv().equals("") && !ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()==null){
                    System.out.println("2. Usao sam gde treba");
                    if(pretragaOpis(projekcija, ps)){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                //PO ZANRU
                if(ps.getNaziv().equals("") && ps.getOpis().equals("") && !ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()==null){
                    System.out.println("2. Usao sam gde treba");
                    if(pretragaZanr(projekcija, ps)){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                //PO CENI
                if(ps.getNaziv().equals("") && ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() != null && ps.getVreme()==null && ps.getOcena()==null){
                    System.out.println("2. Usao sam gde treba");
                    if(projekcija.getCena() < ps.getCena()){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                //PO OCENI
                if(ps.getNaziv().equals("") && ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()!=null){
                    System.out.println("2. Usao sam gde treba");
                    if(projekcija.getFilm().getProsecna_ocena() >= ps.getOcena()){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                //PO DANU
                if(ps.getNaziv().equals("") && ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()!=null && ps.getOcena()==null){
                    System.out.println("2. Usao sam gde treba");
                    projekcije = projekcijaService.findByDatumvreme(ps.getVreme());
                }
                // PO NAZIVU I OPISU
                if(!ps.getNaziv().equals("") && !ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()==null){
                    if(pretragaNaziv(projekcija, ps) && pretragaOpis(projekcija, ps)){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                // PO NAZIVU I ZANRU
                if(!ps.getNaziv().equals("") && ps.getOpis().equals("") && !ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()==null){
                    if(pretragaNaziv(projekcija, ps) && pretragaZanr(projekcija, ps)){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                // PO NAZIVU I CENI
                if(!ps.getNaziv().equals("") && ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() != null && ps.getVreme()==null && ps.getOcena()!=null){
                    if(pretragaNaziv(projekcija, ps) && projekcija.getCena() < ps.getCena()){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                // PO NAZIVU I OCENI
                if(!ps.getNaziv().equals("") && ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()!=null){
                    if(pretragaNaziv(projekcija, ps) && projekcija.getFilm().getProsecna_ocena() >= ps.getOcena()){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                // PO NAZIVU I DATUMU
                if(!ps.getNaziv().equals("") && ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()!=null && ps.getOcena()==null){
                    List<Projekcija> temp = projekcijaService.findByDatumvreme(ps.getVreme());
                    for(Projekcija t : temp){
                        if(pretragaNaziv(t, ps)){
                            if(!projekcije.contains(t)){
                                projekcije.add(t);
                            }
                        }
                    }
                }
                // PO OPISU I ZANRU
                if(ps.getNaziv().equals("") && !ps.getOpis().equals("") && !ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()==null){
                    if(pretragaOpis(projekcija, ps) && pretragaZanr(projekcija, ps)){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                // PO OPISU I CENI
                if(ps.getNaziv().equals("") && !ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() != null && ps.getVreme()==null && ps.getOcena()==null){
                    if(pretragaOpis(projekcija, ps) && projekcija.getCena() < ps.getCena() ){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                // PO OPISU I OCENI
                if(ps.getNaziv().equals("") && !ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()==null && ps.getOcena()!=null){
                    if(pretragaOpis(projekcija, ps) && projekcija.getCena() < ps.getCena() ){
                        if(!projekcije.contains(projekcija)){
                            projekcije.add(projekcija);
                        }
                    }
                }
                // PO OPISU I DATUMU
                if(ps.getNaziv().equals("") && !ps.getOpis().equals("") && ps.getZanr().equals("") && ps.getCena() == null && ps.getVreme()!=null && ps.getOcena()==null){
                    List<Projekcija> temp = projekcijaService.findByDatumvreme(ps.getVreme());
                    for(Projekcija t : temp){
                        if(pretragaOpis(t, ps)){
                            if(!projekcije.contains(t)){
                                projekcije.add(t);
                            }
                        }
                    }
                }
                //SVE ZAJEDNO
                if(!ps.getNaziv().equals("") && !ps.getOpis().equals("") && !ps.getZanr().equals("") && ps.getCena() != null && ps.getVreme()!=null && ps.getOcena()!=null){
                    List<Projekcija> temp = projekcijaService.findByDatumvreme(ps.getVreme());
                    for(Projekcija t : temp){
                        if(pretragaNaziv(t, ps) && pretragaOpis(t, ps) && pretragaZanr(t, ps) && pretragaCena(t, ps) && pretragaOcena(t, ps)){
                            if(!projekcije.contains(t)){
                                projekcije.add(t);
                            }
                        }
                    }
                }
            }
        }

        model.addAttribute("projekcije", projekcije);

        return "gledalac_filmovi.html";
    }

    public Boolean pretragaNaziv(Projekcija projekcija, Pretraga ps){
        if(projekcija.getFilm().getNaziv().toLowerCase().contains(ps.getNaziv().toLowerCase())){
            return true;
        }
        return false;
    }
    public Boolean pretragaOpis(Projekcija projekcija, Pretraga ps) {
        if (projekcija.getFilm().getOpis().toLowerCase().contains(ps.getOpis().toLowerCase())) {
            return true;
        }
        return false;
    }
    public Boolean pretragaZanr(Projekcija projekcija, Pretraga ps){
        if(projekcija.getFilm().getZanr().toLowerCase().contains(ps.getZanr().toLowerCase())) {
            return true;
        }
        return false;
    }
    public Boolean pretragaCena(Projekcija projekcija, Pretraga ps){
        if(projekcija.getCena() < ps.getCena()) {
            return true;
        }
        return false;
    }
    public Boolean pretragaOcena(Projekcija projekcija, Pretraga ps){
        if(projekcija.getFilm().getProsecna_ocena() > ps.getOcena()){
            return true;
        }
        return false;
    }

    @PostMapping("/gledalac_sortiraj")
    public String gledalac_sortiraj(@ModelAttribute(name = "sort") Sort sort, Model model){
        model.addAttribute("pretraga_string", new Pretraga());
        model.addAttribute("sort", new Sort());

        System.out.println("Sortiranje: " + sort.sort);

        List<Projekcija> projekcije = projekcijaService.findAll();

        if(sort.sort == 1){
            for(int i = 0; i < projekcije.size(); i++)
            {
                for(int j = i + 1; j < projekcije.size(); j++)
                {
                    if(projekcije.get(i).getCena() < projekcije.get(j).getCena())
                    {
                        Long id = projekcije.get(i).getId();
                        projekcije.set(i, projekcije.get(j));
                        projekcije.set(j, projekcijaService.findOne(id));
                    }
                }
            }
        } else {
            for(int i = 0; i < projekcije.size(); i++)
            {
                for(int j = i + 1; j < projekcije.size(); j++)
                {
                    if(projekcije.get(i).getCena() > projekcije.get(j).getCena())
                    {
                        Long id = projekcije.get(i).getId();
                        projekcije.set(i, projekcije.get(j));
                        projekcije.set(j, projekcijaService.findOne(id));
                    }
                }
            }
        }

        model.addAttribute("projekcije", projekcije);

        return "gledalac_filmovi.html";
    }


}
