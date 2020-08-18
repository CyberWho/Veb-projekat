package com.example.vebprojekat.service.implementation;

import com.example.vebprojekat.entity.Projekcija;
import com.example.vebprojekat.repository.ProjekcijaRepository;
import com.example.vebprojekat.service.ProjekcijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjekcijaServiceImpl implements ProjekcijaService {

    @Autowired
    private ProjekcijaRepository projekcijaRepository;

    @Override
    public Projekcija create(Projekcija projekcija) throws Exception{
        if(projekcija==null) throw new Exception("Loš unos projekcije");

        return projekcijaRepository.save(projekcija);
    }

    @Override
    public Projekcija findOne(Long id){
        Projekcija novi = projekcijaRepository.getOne(id);
        return novi;
    }

    @Override
    public Projekcija update(Projekcija projekcija) throws Exception{
        Projekcija zaAzurirati = projekcijaRepository.getOne(projekcija.getId());
        if(zaAzurirati==null) throw new Exception("Došlo je do greške prilikom prenosa");

        zaAzurirati.setBioskop(projekcija.getBioskop());
        zaAzurirati.setCena(projekcija.getCena());
        zaAzurirati.setDatumvreme(projekcija.getDatumvreme());
        zaAzurirati.setFilm(projekcija.getFilm());
        zaAzurirati.setSala(projekcija.getSala());
        zaAzurirati.setKarte(projekcija.getKarte());
        //zaAzurirati.setGledaoci(projekcija.getGledaoci());


        return zaAzurirati;
    }

    public @Override
    void Delete(Long id){
        projekcijaRepository.deleteById(id);
    }

    @Override
    public List<Projekcija> findAll(){
        return projekcijaRepository.findAll();
    }

    @Override
    public void save(Projekcija projekcija){
        projekcijaRepository.save(projekcija);
    }

    @Override
    public List<Projekcija> findByDatumvreme(LocalDateTime datum_vreme) {

        return projekcijaRepository.findByDatumvreme(datum_vreme);
    }
}
