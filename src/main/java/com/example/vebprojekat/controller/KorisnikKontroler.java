package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.*;
import com.example.vebprojekat.entity.dto.FilmDTO;
import com.example.vebprojekat.entity.dto.LoginDTO;
import com.example.vebprojekat.entity.dto.ProjekcijaDTO;
import com.example.vebprojekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.ArrayList;
import java.util.List;

@Controller
public class KorisnikKontroler {
    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private GledalacService gledalacService;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private FilmService filmService;

    @Autowired
    private ProjekcijaService projekcijaService;

    public Ulogovan ulogovan;

    private Boolean program_poceo=false;

    @GetMapping("/")
    public String welcome() throws Exception {
        System.out.println("Program počinje");
        if(!program_poceo){
            System.out.println("Program počeo!");
            program_poceo = true;
            ulogovan = new Ulogovan();
            Admin superAdmin = adminService.findByUsername("SuperAdmin");
            for(Korisnik korisnik: korisnikService.findAll()){
                if(!korisnik.getApproved()) {
                    superAdmin.getApprovalList().add(korisnik);
                    korisnik.setAdmin_approve(superAdmin);
                    korisnikService.update(korisnik);
                    adminService.update(superAdmin);
                }
            }
        }

        if(ulogovan.getUlogovan()){
            if(ulogovan.getUloga() == Uloga.GLEDALAC) return "gledalac_index.html";
            else if(ulogovan.getUloga() == Uloga.MENADZER) return "menadzer_index.html";
            else if (ulogovan.getUloga() == Uloga.ADMIN) return "admin_index.html";
        }
        return "index.html";
    }

    @GetMapping("/index_provera")
    public ResponseEntity<Ulogovan> index_provera(){
        System.out.println("Ulogovan uloga: " + ulogovan.getUloga());

        return new ResponseEntity<>(ulogovan, HttpStatus.OK);
    }

    //@GetMapping("/gledalac_index")


    // REGISTRACIJA

    @PostMapping(value = "/registracija", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> registracija(@RequestBody Korisnik korisnik) throws Exception {
        if(korisnik == null) return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

        //Korisnik noviKorisnik = korisnikService.create(korisnik);

        if(korisnik.getUloga() == Uloga.GLEDALAC) gledalacService.create(korisnik);
        else if (korisnik.getUloga() == Uloga.MENADZER) {
            Menadzer novi = menadzerService.create(korisnik);
            adminService.addToApprovalList(novi);
        }
        else {
            Admin novi = adminService.create(korisnik);
            adminService.addToApprovalList(novi);
        }

        return new ResponseEntity<>(korisnik, HttpStatus.OK);
    }

    // PRIJAVA I ODJAVA

    @PostMapping(value = "/prijava", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoginDTO> prijava(@RequestBody LoginDTO loginDTO) throws Exception{
        if(loginDTO == null) return new ResponseEntity<>(null, HttpStatus.OK);

        /*System.out.println("Prosledjeno korisnicko ime: " + loginDTO.getKorisnickoime());
        System.out.println("Prosledjena lozinka: " + loginDTO.getLozinka());
        System.out.println("Duzina prosledjene lozinke: " + loginDTO.getLozinka().length());*/

        Korisnik korisnik = korisnikService.findByKorisnickoime(loginDTO.getKorisnickoime());


        /*System.out.println("Pronadjeno korisnicko ime: " + korisnik.getKorisnickoime());
        System.out.println("Pronadjena lozinka: " + korisnik.getLozinka());
        System.out.println("Duzina pronadjene lozinke: " + korisnik.getLozinka().length());

        System.out.println(korisnik==null);
        //System.out.println(korisnik.getLozinka() != loginDTO.getLozinka());
        System.out.println(korisnik == null || !(korisnik.getLozinka().equals(loginDTO.getLozinka())));*/

        if(korisnik == null || !(korisnik.getLozinka().equals(loginDTO.getLozinka()))) {
            System.out.println("Pogrešno korisničko ime ili lozinka");
            LoginDTO novi = new LoginDTO(korisnik);
            novi.setLogin(false);
            return new ResponseEntity<>(novi, HttpStatus.OK);
        }
        else {
            if(!korisnik.getApproved()){
                System.out.println("Korisnik nije odobren.");
                return new ResponseEntity<>(new LoginDTO(korisnik), HttpStatus.OK);
            }

            if(!korisnik.getAktivan()){
                System.out.println("Korisnik nije aktivan.");
                return new ResponseEntity<>(new LoginDTO(korisnik), HttpStatus.OK);
            }

            System.out.println("Ispravno korisničko ime i lozinka. Prijava korisnika: " + korisnik.toString());

            /*Ulogovan trenutniKorisnik = new Ulogovan();
            trenutniKorisnik.setId(korisnik.getId());
            trenutniKorisnik.setKorisnicko_ime(korisnik.getKorisnickoime());
            trenutniKorisnik.setLozinka(korisnik.getLozinka());
            trenutniKorisnik.setUloga(korisnik.getUloga());
            trenutniKorisnik.setUlogovan(true);*/

            ulogovan.setId(korisnik.getId());
            ulogovan.setKorisnicko_ime(korisnik.getKorisnickoime());
            ulogovan.setLozinka(korisnik.getLozinka());
            ulogovan.setUloga(korisnik.getUloga());
            ulogovan.setUlogovan(true);

            return new ResponseEntity<>(new LoginDTO(korisnik), HttpStatus.OK);
        }

    }

    @GetMapping("/odjava")
    public ResponseEntity<Ulogovan> odjava(){
        ulogovan.setUlogovan(false);
        return new ResponseEntity<>(ulogovan, HttpStatus.OK);
    }

    // ISPIS FILMOVA I PROJEKCIJA (FILMOVI.HTML)

    @GetMapping("/get_filmovi")
    public ResponseEntity<List<FilmDTO>> get_filmovi(){
        List<Film> filmovi = filmService.findAll();
        List<FilmDTO> filmoviDTO = new ArrayList<>();

        for(Film film : filmovi){
            FilmDTO temp = new FilmDTO(film);
            filmoviDTO.add(temp);
        }

        return new ResponseEntity<>(filmoviDTO, HttpStatus.OK);
    }

    @GetMapping("/get_projekcije/{id}")
    public ResponseEntity<List<ProjekcijaDTO>> get_projekcije(@PathVariable(name = "id") Long id){
        String naziv_film = filmService.findOne(id).getNaziv();
        List<ProjekcijaDTO> slanje = new ArrayList<>();
        List<Projekcija> projekcije = projekcijaService.findAll();

        for(Projekcija p: projekcije){
            if(p.getFilm().getNaziv().equals(naziv_film)){
            ProjekcijaDTO temp = new ProjekcijaDTO(p);
                slanje.add(temp);
            }
        }

       return new ResponseEntity<>(slanje, HttpStatus.OK);
    }

}
