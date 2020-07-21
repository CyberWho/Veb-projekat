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
    private  KorisnikRepository korisnikRepository;

    @Override
    public Korisnik create(Korisnik korisnik) {
        if(korisnik.getId() != null) return null;

        Korisnik novi = this.korisnikRepository.save(korisnik);
        return novi;
    }

    @Override
    public Korisnik findOne(Long id){
        Korisnik novi = this.korisnikRepository.getOne(id);
        return novi;

    }

    @Override
    public Korisnik findByUsername(String username){
        List<Korisnik> korisnici = this.korisnikRepository.findAll();
        for(Korisnik korisnik : korisnici){
            if(username.equals(korisnik.getKorisnicko_ime())){
                return korisnik;
            }
        }
        return null;
    }

    @Override
    public Korisnik update(Korisnik korisnik) throws  Exception{
        Korisnik zaAzurirati = this.korisnikRepository.getOne(korisnik.getId());
        if(zaAzurirati == null) throw new Exception("Traženi korisnik ne postoji!");

        zaAzurirati.setIme(korisnik.getIme());
        zaAzurirati.setPrezime(korisnik.getPrezime());
        zaAzurirati.setAktivan(korisnik.getAktivan());
        zaAzurirati.setDatum(korisnik.getDatum());
        zaAzurirati.setEmail(korisnik.getEmail());
        zaAzurirati.setKorisnicko_ime(korisnik.getKorisnicko_ime());
        zaAzurirati.setLozinka(korisnik.getLozinka());
        zaAzurirati.setUloga(korisnik.getUloga());
        zaAzurirati.setKontakt_tel(korisnik.getKontakt_tel());
        zaAzurirati.setApproved(korisnik.getApproved());

        return korisnikRepository.save(zaAzurirati);
    }

    @Override
    public void delete(Long id){
        this.korisnikRepository.deleteById(id);
    }

    @Override
    public void deleteIfMoreThanOne(Korisnik korisnik){
        Integer count=-1;
        for(Korisnik kor : korisnikRepository.findAll()){
            if(kor.getKorisnicko_ime().equals(korisnik.getKorisnicko_ime())) count++;
        }

        for(int i = 0; i < count; i++){
            korisnik.setAdmin_approve(null);
            korisnik.setApproved(true);
            delete(findByUsername(korisnik.getKorisnicko_ime()).getId());
        }

    }

    @Override
    public List<Korisnik> findAll(){
        List<Korisnik> korisnici = this.korisnikRepository.findAll();
        return korisnici;
    }
}
