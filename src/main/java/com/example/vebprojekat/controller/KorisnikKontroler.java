package com.example.vebprojekat.controller;



import com.example.vebprojekat.entity.*;
import com.example.vebprojekat.entity.dto.KorisnikDTO;
import com.example.vebprojekat.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class KorisnikKontroler {


    @Autowired
    private FilmService filmService;

    @Autowired
    private GledalacService gledalacService;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SalaService salaService;

    public Ulogovan ulogovan;

    private Boolean program_poceo = false;


    // WELCOME I SESIJE ---------------------------

    @GetMapping("/")
    public String welcome() throws Exception {
        if(!program_poceo){
            program_poceo = true;
            ulogovan = new Ulogovan();
            Admin superAdmin = adminService.findByUsername("SuperAdmin");
            for(Korisnik korisnik : korisnikService.findAll()){
                if(!korisnik.getApproved()) {
                    superAdmin.getApprovalList().add(korisnik);
                    korisnik.setAdmin_approve(superAdmin);
                    korisnikService.update(korisnik);
                    adminService.update(superAdmin);
                }
            }
        }

        if(ulogovan.getUlogovan()){
            if(ulogovan.getUloga() == UlogaEnum.Uloga.GLEDALAC) return "redirect:/gledalac_index";
            else if(ulogovan.getUloga() == UlogaEnum.Uloga.MENADZER) return "redirect:/menadzer_index";
            else if (ulogovan.getUloga() == UlogaEnum.Uloga.ADMIN) return "redirect:/admin_index";
        }
        return "index.html";

    }

    @GetMapping("/gledalac_index")
    public String gledalac_index(){
        if(ulogovan.getUloga() == UlogaEnum.Uloga.GLEDALAC) return "gledalac_index.html";
        else return "no_access.html";
    }

    @GetMapping("/menadzer_index")
    public String menadzer_index(){
        if(ulogovan.getUloga() == UlogaEnum.Uloga.MENADZER) return "menadzer_index.html";
        else return "no_access.html";
    }

    @GetMapping("/admin_index")
    public String admin_index(Model model){
        if(ulogovan.getUloga() == UlogaEnum.Uloga.ADMIN) {
            Admin trenutni_admin = adminService.findByUsername(ulogovan.getKorisnicko_ime());
            List<Korisnik> korisnici = trenutni_admin.getApprovalList();
            List<KorisnikDTO> korisniciDTO = new ArrayList<>();

            for(Korisnik korisnik : korisnici){
                KorisnikDTO korisnikDTO = new KorisnikDTO();
                korisnikDTO.setIme(korisnik.getIme());
                korisnikDTO.setPrezime(korisnik.getPrezime());
                korisnikDTO.setUloga(korisnik.getUloga());
                korisnikDTO.setId(korisnik.getId());
                korisnikDTO.setKorisnicko_ime(korisnik.getKorisnicko_ime());

                korisniciDTO.add(korisnikDTO);
            }

            model.addAttribute("korisnici", korisniciDTO);
            return "admin_index.html";
        }
        else return "no_access.html";
    }



    @GetMapping("/profil")
    public String prikazProfila(/*@PathVariable(name = "id")  Long id, Model model*/){
        return "profil.html";
    }

    @GetMapping("/dodaj_bioskop")
    public String dodaj_bioskop(){
        return "dodaj_bioskop.html";
    }


    // PRIJAVA I ODJAVA -----------------------


    @GetMapping("/prijava")
    public String prijava(Model model){

        model.addAttribute("trenutniKorisnik", new Ulogovan());
        model.addAttribute("pass_check", false);
        model.addAttribute("approved_check", false);

        return "prijava.html";
    }

    @RequestMapping(value = "/authentication", method = {RequestMethod.GET, RequestMethod.POST})
    public String authentication(@ModelAttribute Ulogovan trenutniKorisnik, RedirectAttributes r, BindingResult error, Model model){
        String un = trenutniKorisnik.getKorisnicko_ime();
        String psw = trenutniKorisnik.getLozinka();

        System.out.println("Prosledjeno korisnicko ime: " + un);
        System.out.println("Prosledjena lozinka: " + psw);

        Korisnik korisnik = korisnikService.findByUsername(un);

        if(korisnik == null) {
            model.addAttribute("trenutniKorisnik", new Ulogovan());
            model.addAttribute("pass_check", true);
            return "prijava.html";
        }

        if(!korisnik.getApproved()){
            return "not_approved.html";
        }

        if(!korisnik.getAktivan()){
            return "not_active.html";
        }

        if(korisnik.getLozinka().equals(psw)){
            trenutniKorisnik.setId(korisnik.getId());
            trenutniKorisnik.setUloga(korisnik.getUloga());
            trenutniKorisnik.setUlogovan(true);
            trenutniKorisnik.setLozinka(psw);
            trenutniKorisnik.setKorisnicko_ime(un);
            if(korisnik.getUloga() == UlogaEnum.Uloga.GLEDALAC) return "gledalac_index.html";
            else if(korisnik.getUloga() == UlogaEnum.Uloga.MENADZER) return "menadzer_index.html";
            else if (korisnik.getUloga() == UlogaEnum.Uloga.ADMIN) return "redirect:/admin_index";
        } else {
            System.out.println("Usao sam gde treba, pogresna lozinka!");
            model.addAttribute("trenutniKorisnik", new Ulogovan());
            model.addAttribute("pass_check", true);
            return "prijava.html";
        }

        return "neuspesna_prijava.html";
    }

    @GetMapping("/odjava")
    public String odjava(){
        ulogovan.setUlogovan(false);
        return "index.html";
    }



    // REGISTRACIJA ------------------------------------------


    @GetMapping("/registracija")
    public String registracija(Model model){
        model.addAttribute("korisnik", new Korisnik());
        model.addAttribute("reg_check", false);
        model.addAttribute("un_check", false);
        model.addAttribute("email_check", false);
        model.addAttribute("pass_check", false);
        return  "registracija.html";
    }

    @RequestMapping(
            value = "/registruj",
            method = {RequestMethod.GET, RequestMethod.POST})
    public String createKorisnik(@ModelAttribute Korisnik korisnik, BindingResult errors, RedirectAttributes r, Model model) throws Exception{

        if(korisnik == null){
            model.addAttribute("reg_check", true);
            return "registracija.html";
        }

        List<Korisnik> svi_korisnici = korisnikService.findAll();

        for(Korisnik kor : svi_korisnici){
            if(kor.getKorisnicko_ime().equals(korisnik.getKorisnicko_ime())){
                model.addAttribute("un_check", true);
                return "registracija.html";
            } else if(kor.getEmail().equals(korisnik.getEmail())){
                model.addAttribute("email_check", true);
                return "registracija.html";
            }
        }


        korisnik.setAktivan(true);
        korisnik.setApproved(false);
        Calendar currenttime = Calendar.getInstance();
        korisnik.setDatum(new Date((currenttime.getTime()).getTime()));

        if(korisnik.getUloga() == UlogaEnum.Uloga.GLEDALAC){
            korisnik.setApproved(true);
            Gledalac novi_gl = gledalacService.create(korisnikService.create(korisnik));
        } else if (korisnik.getUloga() == UlogaEnum.Uloga.MENADZER){
            Menadzer novi_mng = menadzerService.create(korisnikService.create(korisnik));

            adminService.addToApprovalList(novi_mng);
            return "ceka_odobrenje.html";
        } else if (korisnik.getUloga() == UlogaEnum.Uloga.ADMIN) {
            Admin novi_adm = adminService.create(korisnikService.create(korisnik));

            adminService.addToApprovalList(novi_adm);
            return "ceka_odobrenje.html";
        }

        return "uspesna_registracija.html";

    }


    // PROFIL -----------------------------------------------


    @GetMapping(value="/profil/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KorisnikDTO> prikazProfila(@PathVariable(name = "id")  Long id, Model model){
        Korisnik korisnik = this.korisnikService.findOne(id);

        KorisnikDTO korisnikDTO = new KorisnikDTO();
        korisnikDTO.setIme(korisnik.getIme());
        korisnikDTO.setPrezime(korisnik.getPrezime());
        korisnikDTO.setUloga(korisnik.getUloga());

        return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
    }

    // GRESKE ------------------------------------------------


    /*@RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
    public String error(HttpServletRequest request, Model model){

        if(request.getHeader("Referer").equals("registruj")){
            model.addAttribute("pass_check", true);
            return "registracija.html";
        }
        System.out.println(request.getHeader("Referer"));
        return "error.html";
    }*/

    @GetMapping("/error")
    public String error(){
        return "error.html";
    }



}
