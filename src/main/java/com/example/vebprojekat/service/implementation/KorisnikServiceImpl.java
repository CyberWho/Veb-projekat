package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.repository.*;
import com.example.vebprojekat.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikServiceImpl implements KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Override
    public Korisnik create(Korisnik korisnik) throws Exception{
        if(korisnik.getId() != null) throw new Exception("ID mora biti null!");

        Korisnik novi = this.korisnikRepository.save(korisnik);
        return novi;
    }

    public Korisnik findOne(Long id){
        Korisnik novi = this.korisnikRepository.getOne(id);
        return novi;

    }

    public Korisnik update(Korisnik korisnik) throws  Exception{
        Korisnik zaAzurirati = this.korisnikRepository.getOne(korisnik.getId());
        if(zaAzurirati == null) throw new Exception("Traženi korisnik ne postoji!");

        zaAzurirati.setIme(korisnik.getIme());
        zaAzurirati.setPrezime(korisnik.getPrezime());
        zaAzurirati.setAktivan(korisnik.getAktivan());
        zaAzurirati.setDatum(korisnik.getDatum());
        zaAzurirati.setEmail(korisnik.getEmail());
        zaAzurirati.setKorisnickoIme(korisnik.getKorisnickoIme());
        zaAzurirati.setLozinka(korisnik.getLozinka());
        zaAzurirati.setUloga(korisnik.getUloga());
        zaAzurirati.setKontaktTel(korisnik.getKontaktTel());

        Korisnik novi = this.korisnikRepository.save(zaAzurirati);

        return novi;
    }

    public void delete(Long id){
        this.korisnikRepository.deleteById(id);
    }

    public List<Korisnik> findAll(){
        List<Korisnik> korisnici = this.korisnikRepository.findAll();
        return korisnici;
    }
}
