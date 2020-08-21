package com.example.vebprojekat.controller;

import com.example.vebprojekat.entity.*;
import com.example.vebprojekat.entity.dto.*;
import com.example.vebprojekat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Autowired
    private BioskopService bioskopService;

    public Ulogovan ulogovan;

    private Boolean program_poceo=false;

    @GetMapping("/")
    //public ResponseEntity<Ulogovan> welcome() throws Exception {
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
        System.out.println("Ulogovan uloga ('/') mapping): " + ulogovan.getUloga());
        if(ulogovan.getUlogovan()){
            if(ulogovan.getUloga() == Uloga.GLEDALAC) return "gledalac_index.html";
            else if(ulogovan.getUloga() == Uloga.MENADZER) return "menadzer_index.html";
            else if (ulogovan.getUloga() == Uloga.ADMIN) {
                System.out.println("Usao da vratim admin_index.html");
                return "admin_index.html";
            }
        }
        //return new ResponseEntity<>(ulogovan, HttpStatus.OK);
        return "index.html";
    }

    @GetMapping(value = "/index_provera", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Ulogovan> index_provera(){
        System.out.println("Ulogovan uloga ('/index_provera' mapping): " + ulogovan.getUloga());
        System.out.println("Ulogovan korisnicko ime ('/index_provera' mapping): " + ulogovan.getKorisnicko_ime());

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    //@GetMapping("/gledalac_index")



    // ADMIN


    @GetMapping(value = "/admin_index", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisnikDTO>> admin_index(){

        System.out.println("Pogodio /admin_index");

        if(ulogovan == null || ulogovan.getUloga() != Uloga.ADMIN){
            System.out.println("Šaljem null kao odgovor iz /admin_indexa");
            return null;
        }

        List<Korisnik> approveList = adminService.findByUsername(ulogovan.getKorisnicko_ime()).getApprovalList();
        List<KorisnikDTO> approveListDTO = new ArrayList<>();
        for(Korisnik k: approveList) {
            System.out.println("To be approved: " + k.getKorisnickoime());
            approveListDTO.add(new KorisnikDTO(k));
        }

        return new ResponseEntity<>(approveListDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/admin_odobri_registraciju/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KorisnikDTO>> admin_odobri_registraciju(@PathVariable(name = "id") Long id) throws Exception {
        System.out.println("Pogodio /admin_odobri_registraciju/" + id);

        adminService.approveRegistration(korisnikService.findOne(id));

        /*Admin SuperAdmin = adminService.findByUsername("SuperAdmin");
        List<Korisnik> approvalList = SuperAdmin.getApprovalList();
        System.out.println("Brisanje korisnika iz approvalListe");
        approvalList.remove(korisnikService.findOne(id));
        adminService.update(SuperAdmin);*/
        List<KorisnikDTO> approvalListDTO = new ArrayList<>();

        /*for(Korisnik k: approvalList){
            approvalListDTO.add(new KorisnikDTO(k));
        }*/

        return new ResponseEntity<>(approvalListDTO, HttpStatus.OK);
    }

    @GetMapping(value="/admin_bioskopi", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BioskopDTO>> admin_bioskopi(){
        System.out.println("Pogodio /admin_bioskopi");
        List<BioskopDTO> bioskopiDTO = new ArrayList<>();
        for(Bioskop b: bioskopService.findAll()){
            bioskopiDTO.add(new BioskopDTO(b));
        }

        return new ResponseEntity<>(bioskopiDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/admin_bioskop_obrisi/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BioskopDTO>> admin_bioskop_obrisi(@PathVariable(name = "id") Long id){
        System.out.println("Pogodio /admin_bioskopi_obrisi/" + id);
        bioskopService.delete(id);
        List<BioskopDTO> bioskopiDTO = new ArrayList<>();

        return new ResponseEntity<>(bioskopiDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/admin_bioskop_izmeni_priprema", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MenadzerDTO>> admin_bioskop_izmeni_priprema(){
        System.out.println("Pogodio /admin_bioskopi_izmeni_priprema");
        List<MenadzerDTO> menadzeriDTO = new ArrayList<>();

        for(Menadzer m: menadzerService.findAll()){
            menadzeriDTO.add(new MenadzerDTO(m));
        }

        return new ResponseEntity(menadzeriDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/admin_bioskop_izmeni", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BioskopDTO> admin_bioskop_izmeni(@RequestBody BioskopDTO noviBioskopDTO) throws Exception {
        System.out.println("Pogodio /admin_bioskopi_izmeni");
        System.out.println("BioskopDTO ID: " + noviBioskopDTO.getId());
        Bioskop bioskop = bioskopService.findOne(noviBioskopDTO.getId());

        System.out.println("Prosledjen naziv: " + noviBioskopDTO.getNaziv());
        System.out.println("Prosledjena adresa: " + noviBioskopDTO.getAdresa());
        System.out.println("Duzina prosledjene adrese: " + noviBioskopDTO.getAdresa().length());

        if(noviBioskopDTO.getNaziv().length() != 0) bioskop.setNaziv(noviBioskopDTO.getNaziv());
        if(noviBioskopDTO.getEmail().length() != 0) bioskop.setEmail(noviBioskopDTO.getEmail());
        if(noviBioskopDTO.getAdresa().length() != 0) bioskop.setAdresa(noviBioskopDTO.getAdresa());
        if(noviBioskopDTO.getBr_telefona().length() != 0) bioskop.setBr_telefona(noviBioskopDTO.getBr_telefona());

        bioskopService.update(bioskop);

        return new ResponseEntity<>(new BioskopDTO(bioskop), HttpStatus.OK);
    }



    @GetMapping(value = "/admin_bioskop_dodaj_mngr_priprema/{id}")
    public ResponseEntity<List<MenadzerDTO>> admin_bioskop_dodaj_mngr_priprema(@PathVariable(name = "id") Long id){
        System.out.println("Pogodio /admin_bioskopi_dodaj_mngr_priprema/" + id);

        List<MenadzerDTO> menadzeriDTO = new ArrayList<>();
        Set<Menadzer> menadzeri_u_bioskopu  = bioskopService.findOne(id).getMenadzeri();
        for(Menadzer m: menadzerService.findAll()){
            if(!menadzeri_u_bioskopu.contains(m) && m.getApproved() && m.getAktivan()) {
                System.out.println("Menadzer koji nije vezan za dati bioskop: " + m.getKorisnickoime());
                menadzeriDTO.add(new MenadzerDTO(m));
            }
        }
        return new ResponseEntity<>(menadzeriDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/admin_bioskop_dodaj_mngr/{bioskop_id}/{mngr_id}")
    public ResponseEntity<List<BioskopDTO>> admin_bioskop_dodaj_mngr(@PathVariable(name = "bioskop_id") Long bioskop_id, @PathVariable(name = "mngr_id") Long mngr_id) throws Exception{
        System.out.println("Pogodio /admin_bioskop_dodaj_mngr/" + bioskop_id + "/" + mngr_id);

        Menadzer menadzer = menadzerService.findOne(mngr_id);
        System.out.println("Korisnicko ime menadzera: " + menadzer.getKorisnickoime());

        Bioskop bioskop = bioskopService.findOne(bioskop_id);
        bioskop.getMenadzeri().add(menadzer);
        menadzer.getBioskopi().add(bioskop);
        Bioskop novi = bioskopService.update(bioskop);


        List<BioskopDTO> b = new ArrayList<>();
        b.add(new BioskopDTO(novi));

        return new ResponseEntity<>(b, HttpStatus.OK);
    }


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

        if(korisnik == null){
            System.out.println("Pogrešno korisničko ime");
            return new ResponseEntity<>(new LoginDTO(), HttpStatus.OK);
        }

        if(!(korisnik.getLozinka().equals(loginDTO.getLozinka()))) {
            System.out.println("Pogrešna lozinka");
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
        ulogovan = new Ulogovan();
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

    @GetMapping("/error")
    public String error(){
        return "error.html";
    }

}
