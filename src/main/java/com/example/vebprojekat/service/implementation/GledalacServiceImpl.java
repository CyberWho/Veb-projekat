package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Gledalac;
import com.example.vebprojekat.entity.Korisnik;
import com.example.vebprojekat.entity.UlogaEnum;
import com.example.vebprojekat.repository.GledalacRepository;
import com.example.vebprojekat.service.GledalacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GledalacServiceImpl implements GledalacService {
    @Autowired
    private GledalacRepository gledalacRepository;

    @Override
    public Gledalac create(Korisnik korisnik) throws Exception {

        if(korisnik == null) throw new Exception("Niste uneli korisnika");

        Gledalac novi = new Gledalac();
        novi.setKorisnicko_ime(korisnik.getKorisnicko_ime());
        novi.setLozinka(korisnik.getLozinka());
        novi.setIme(korisnik.getIme());
        novi.setPrezime(korisnik.getPrezime());
        novi.setKontakt_tel(korisnik.getKontakt_tel());
        novi.setEmail(korisnik.getEmail());
        novi.setDatum(korisnik.getDatum());
        novi.setUloga(UlogaEnum.Uloga.GLEDALAC);
        novi.setAktivan(korisnik.getAktivan());

        return gledalacRepository.save(novi);
    }

    @Override
    public Gledalac findOne(Long id){
        Gledalac novi = this.gledalacRepository.getOne(id);
        return novi;
    }

    @Override
    public Gledalac update(Gledalac gledalac) throws Exception{
        Gledalac zaAzurirati = this.gledalacRepository.getOne(gledalac.getId());
        if(zaAzurirati == null) throw new Exception("Traženi gledalac ne postoji!");

        zaAzurirati.setIme(gledalac.getIme());
        zaAzurirati.setPrezime(gledalac.getPrezime());
        zaAzurirati.setAktivan(gledalac.getAktivan());
        zaAzurirati.setDatum(gledalac.getDatum());
        zaAzurirati.setEmail(gledalac.getEmail());
        zaAzurirati.setKorisnicko_ime(gledalac.getKorisnicko_ime());
        zaAzurirati.setLozinka(gledalac.getLozinka());
        zaAzurirati.setUloga(gledalac.getUloga());
        zaAzurirati.setKontakt_tel(gledalac.getKontakt_tel());
        zaAzurirati.setOcenjeni_filmovi(gledalac.getOcenjeni_filmovi());
        zaAzurirati.setOdgledani_filmovi(gledalac.getOdgledani_filmovi());
        zaAzurirati.setRezervisani_filmovi(gledalac.getRezervisani_filmovi());

        Gledalac novi = this.gledalacRepository.save(zaAzurirati);
        return novi;
    }

    @Override
    public void delete(Long id){
        this.gledalacRepository.deleteById(id);
    }

    @Override
    public List<Gledalac> findAll(){
        List<Gledalac> gledaoci = this.gledalacRepository.findAll();
        return gledaoci;
    }

}
