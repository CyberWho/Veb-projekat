package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Menadzer;
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
    public Menadzer create(Menadzer menadzer) throws Exception{
        if(menadzer.getId() != null) throw new Exception("ID mora biti null!");

        Menadzer novi = this.menadzerRepository.save(menadzer);
        return novi;
    }

    @Override
    public Menadzer findOne(Long id){
        Menadzer novi = this.menadzerRepository.getOne(id);
        return novi;
    }

    @Override
    public Menadzer update(Menadzer menadzer) throws Exception{
        Menadzer zaAzurirati = this.menadzerRepository.getOne(menadzer.getId());
        if(zaAzurirati == null) throw new Exception("Traženi menadžer ne postoji!");

        zaAzurirati.setIme(menadzer.getIme());
        zaAzurirati.setPrezime(menadzer.getPrezime());
        zaAzurirati.setAktivan(menadzer.getAktivan());
        zaAzurirati.setDatum(menadzer.getDatum());
        zaAzurirati.setEmail(menadzer.getEmail());
        zaAzurirati.setKorisnickoIme(menadzer.getKorisnickoIme());
        zaAzurirati.setLozinka(menadzer.getLozinka());
        zaAzurirati.setUloga(menadzer.getUloga());
        zaAzurirati.setKontaktTel(menadzer.getKontaktTel());
        zaAzurirati.setBioskop(menadzer.getBioskop());

        Menadzer novi = this.menadzerRepository.save(zaAzurirati);
        return novi;
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
}
