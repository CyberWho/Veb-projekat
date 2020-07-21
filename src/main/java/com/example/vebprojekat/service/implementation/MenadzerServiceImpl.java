package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Bioskop;
import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.entity.Menadzer;
import com.example.vebprojekat.entity.UlogaEnum;
import com.example.vebprojekat.repository.MenadzerRepository;
import com.example.vebprojekat.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenadzerServiceImpl implements MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Override
    public Menadzer create(Korisnik korisnik) throws Exception{
        if(korisnik == null) throw new Exception("Niste uneli korisnika");

        Menadzer novi = new Menadzer();
        novi.setKorisnicko_ime(korisnik.getKorisnicko_ime());
        novi.setLozinka(korisnik.getLozinka());
        novi.setIme(korisnik.getIme());
        novi.setPrezime(korisnik.getPrezime());
        novi.setKontakt_tel(korisnik.getKontakt_tel());
        novi.setEmail(korisnik.getEmail());
        novi.setDatum(korisnik.getDatum());
        novi.setUloga(UlogaEnum.Uloga.MENADZER);
        novi.setAktivan(korisnik.getAktivan());
        novi.setApproved(korisnik.getApproved());
        novi.setAdmin_approve(korisnik.getAdmin_approve());

        return menadzerRepository.save(novi);
    }

    @Override
    public Menadzer findOne(Long id){
        Menadzer novi = this.menadzerRepository.getOne(id);
        return novi;
    }

    @Override
    public Menadzer update(Menadzer menadzer) throws Exception{
        Menadzer zaAzurirati = menadzerRepository.getOne(menadzer.getId());
        if(zaAzurirati == null) throw new Exception("Traženi menadžer ne postoji!");

        zaAzurirati.setIme(menadzer.getIme());
        zaAzurirati.setPrezime(menadzer.getPrezime());
        zaAzurirati.setAktivan(menadzer.getAktivan());
        zaAzurirati.setDatum(menadzer.getDatum());
        zaAzurirati.setEmail(menadzer.getEmail());
        zaAzurirati.setKorisnicko_ime(menadzer.getKorisnicko_ime());
        zaAzurirati.setLozinka(menadzer.getLozinka());
        zaAzurirati.setUloga(menadzer.getUloga());
        zaAzurirati.setKontakt_tel(menadzer.getKontakt_tel());
        zaAzurirati.setBioskopi(menadzer.getBioskopi());
        zaAzurirati.setApproved(menadzer.getApproved());


        //Menadzer novi = this.menadzerRepository.save(zaAzurirati);


        return zaAzurirati;
    }

    @Override
    public void delete(Long id){
        this.menadzerRepository.deleteById(id);
    }

    @Override
    public List<Menadzer> findAll(){
        List<Menadzer> menadzeri = this.menadzerRepository.findAll();
        return menadzeri;
    }

    @Override
    public Menadzer findByUsername(String username){
        List<Menadzer> menadzeri = findAll();
        for(Menadzer menadzer : menadzeri){
            if(menadzer.getKorisnicko_ime().equals(username)){
                return menadzer;
            }
        }
        return null;
    }

    @Override
    public void save(Menadzer menadzer){
        menadzerRepository.save(menadzer);
    }

}
